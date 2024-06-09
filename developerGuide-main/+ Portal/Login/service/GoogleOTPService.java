package pierp.app.login.common.service;

import java.util.HashMap;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * @Class GoogleOTPService
 * @Description
 * 	Google OTP Service
 * @author 정성희
 * @since 2022.04.20.
 * @version
 *
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 * 2022.04.20.   정성희		최초작성
 *
 */
public interface GoogleOTPService {



	//public List<EipPqueDutyVO> selectPqueDutyList(SearchCmd searchCmd) throws EgovBizException;	
	
	/**
	 * OTP 키 생성
	 * @param userName
	 * @param hostName
	 * @return
	 */
	public HashMap<String, String> generate(String userName, String hostName) throws EgovBizException;
	
	/**
	 * QR 코드 URL 리턴
	 * @param user
	 * @param host
	 * @param secret
	 * @return
	 */
	public String getQRBarcodeURL(String user, String host, String secret) throws EgovBizException;
	
	/**
	 * QR 코드 바이너리 리턴 (OtpQrBinTest 코드 참조)
	 * @param qrCodeURL
	 * @return
	 * @throws EgovBizException
	 */
	public byte[] getQRBarcode(String qrCodeURL) throws EgovBizException;
	
	/**
	 * OTP 번호 인증
	 * @param userCode
	 * @param otpkey
	 * @return
	 */
	public boolean checkCode(String userCode, String otpkey) throws EgovBizException;
	
	
}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 * 
 */