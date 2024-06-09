package pierp.app.login.common.service.impl;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.login.common.service.GoogleOTPService;
import pierp.common.cmmn.service.BaseAbstractServiceImpl;
import pierp.ext.link.rest.core.service.RestService;


/**
 * @Class GooGleOTPServiceImpl
 * @Description
 *  Google OTP Service 구현체
 * @author 정성희
 * @since 2022.04.20.
 * @version
 *
 *  * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 * 
 *
 */

@Service
public class GoogleOTPServiceImpl extends BaseAbstractServiceImpl implements GoogleOTPService{

	private final int QR_BIN_SIZE = 1024 * 4; 
	
	@Autowired
	private RestService restService;
	
	
	@Override
	public HashMap<String, String> generate(String userName, String hostName) throws EgovBizException{
		HashMap<String, String> map = new HashMap<String, String>();
		byte[] buffer = new byte[5 + 5 * 5];
		new Random().nextBytes(buffer);
		Base32 codec = new Base32();
		byte[] secretKey = Arrays.copyOf(buffer, 10);
		byte[] bEncodedKey = codec.encode(secretKey);

		String encodedKey = new String(bEncodedKey);
		String url = getQRBarcodeURL(userName, hostName, encodedKey);
		// Google OTP 앱에 userName@hostName 으로 저장됨
		// key를 입력하거나 생성된 QR코드를 바코드 스캔하여 등록

		map.put("encodedKey", encodedKey);
		map.put("url", url);
		return map;		
	}
	
	@Override
	public String getQRBarcodeURL(String user, String host, String secret) throws EgovBizException{
		// QR코드 주소 생성
		String format2 = "http://chart.apis.google.com/chart?"
				+ "cht=qr&chs=100x100&chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s&chld=H|0";
		return String.format(format2, user, host, secret);		
	}
	
	@Override
	public byte[] getQRBarcode(String qrCodeURL) throws EgovBizException {
		
		byte[] byteArray = null;
        ByteBuffer buf = ByteBuffer.allocate(QR_BIN_SIZE);
        boolean bRet = restService.invokeGet(qrCodeURL, buf);
        
        if(bRet) {
        	byteArray = new byte[buf.remaining()];
        	buf.get(byteArray, 0, byteArray.length);
        }else {
        	getLogger().error("invokeGet 오류!");
        }
		
		return byteArray;
	}
	
	@Override
	public boolean checkCode(String userCode, String otpkey) throws EgovBizException{
		long otpnum = Integer.parseInt(userCode); // Google OTP 앱에 표시되는 6자리 숫자
		long wave = new Date().getTime() / 30000; // Google OTP의 주기는 30초
		boolean result = false;
		try {
			Base32 codec = new Base32();
			byte[] decodedKey = codec.decode(otpkey);
			int window = 3;
			for (int i = -window; i <= window; ++i) {
				long hash = verify_code(decodedKey, wave + i);
				if (hash == otpnum)
					result = true;
			}
		} catch (InvalidKeyException | NoSuchAlgorithmException e) {
			getLogger().error("GoogleOTPServiceImpl::checkCode:: InvalidKeyException | NoSuchAlgorithmExceptio!!!");			
			//e.printStackTrace();
			throw new EgovBizException("OTP checkCode 오류가 발생 하였습니다.");
		}
		return result;		
	}
	
	
	private int verify_code(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
		byte[] data = new byte[8];
		long value = t;
		for (int i = 8; i-- > 0; value >>>= 8) {
			data[i] = (byte) value;
		}

		SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(signKey);
		byte[] hash = mac.doFinal(data);

		int offset = hash[20 - 1] & 0xF;

		// We're using a long because Java hasn't got unsigned int.
		long truncatedHash = 0;
		for (int i = 0; i < 4; ++i) {
			truncatedHash <<= 8;
			// We are dealing with signed bytes:
			// we just keep the first byte.
			truncatedHash |= (hash[offset + i] & 0xFF);
		}

		truncatedHash &= 0x7FFFFFFF;
		truncatedHash %= 1000000;

		return (int) truncatedHash;
	}	
	
}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 * 
 */