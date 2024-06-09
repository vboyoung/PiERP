package pierp.app.login.common.web;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.property.EgovPropertyService;
import pierp.common.base.Constants;
import pierp.common.main.model.IAuthVO;
import pierp.common.main.model.IUserInfo;
import pierp.common.main.model.UserInfo;
import pierp.common.main.service.LoginService;
import pierp.common.mvc.web.ResponseDtoSupport;
import pierp.common.session.SessionContext;

/**
 * @Class AbstractLoginController
 * @Description
 * 로그인 기본 기능 정의 class.
 * @author parang
 * @since 2019. 4. 26.
 * @version
 *
 */
public abstract class AbstractLoginController extends ResponseDtoSupport {

	/** root context path = / */
	protected final static String ROOT_CONTEXT_PATH="/";

	/** 암호Key크기 */
	protected final static int KEY_SIZE = 1024;

	protected final Log log = LogFactory.getLog(getClass());


	@SuppressWarnings("rawtypes")
	@Resource(name="sessionContextFactory")
	protected ObjectFactory sessionContextFactory;

	@Resource(name="loginService")
	protected LoginService loginService;

	@Resource(name="sysPropService")
	protected EgovPropertyService sysPropService;

	@Resource(name = "messageSource")
	protected MessageSource messageSource;

	/**
	 * 로그인 page view 기본 기능
	 * @param request
	 * @param response
	 * @return
	 * @throws EgovBizException
	 */
	protected ModelAndView loadLocalLoginForm(HttpServletRequest request, HttpServletResponse response) throws EgovBizException
	{
		final String viewName   = sysPropService.getString("system.auth.login.viewName");

		ModelAndView mv = new ModelAndView();

		setCryptoKey(request.getSession(), mv);

		mv.setViewName(viewName);

		return mv;
	}

	/**
	 * local로그인 기본 처리.
	 * @param request
	 * @param session
	 * @param status
	 * @param scrdUsrId  사용자ID
	 * @param scrdPswrd  비밀번호
	 * @return
	 * @throws EgovBizException
	 */
	protected ModelAndView doLocalLogin(HttpServletRequest request, HttpSession session, SessionStatus status,
			String scrdUsrId,  String scrdPswrd) throws EgovBizException {
		ModelAndView mv = new ModelAndView();

		IUserInfo userInfo = null;
		List<IAuthVO> authList = null;
		String encrId=null, usrId=null, secrNo=null, failMesg="", loginDtm="", pswdErrYn="N", lgnDivCd="LC";

		boolean succYn = true;

		String sysId = sysPropService.getString("system.sysId");

		//String chgPswrd = sysPropService.getString("system.auth.password.changeYn","N");
		//int termPswrd = Integer.parseInt( sysPropService.getString("system.auth.password.term","0"));

		String clntIp= request.getRemoteAddr();

		session = changeSessionId(request);

		try {
	        PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
	        session.removeAttribute("__rsaPrivateKey__"); // 키의 재사용을 막는다. 항상 새로운 키를 받도록 강제.

	        if (privateKey == null) {
	        	mv.addObject("message", "암/복호화 전처리 오류.\\n다시 로그인하세요.");
	        	setCryptoKey(session, mv);
				return mv;
	        }

	        try {
	            usrId = decryptRsa(privateKey, scrdUsrId);
	            secrNo = decryptRsa(privateKey, scrdPswrd);
	        }catch(EgovBizException e){
				log.error("로그인 ID/PWD 복호화 오류", e);
	        	mv.addObject("message", "비정상적인 login요청입니다.\\n다시 로그인하세요.");
	        	setCryptoKey(session, mv);
				return mv;
	        }

			userInfo = loginService.login(request, sysId, usrId, secrNo);
			authList = loginService.getAuthList(sysId, usrId);

			if(log.isDebugEnabled()){
				if(authList == null){
					log.debug("authList is Null.");
				}else if(authList.size()<1){
					log.debug("authList is Zero.");
				}else{
					log.debug("1st Auth="+authList.get(0).getAuthNm());
				}
				log.debug("User-ID:"+usrId+",User-IP:"+request.getRemoteAddr()+",User-Agent:"+request.getHeader("User-Agent"));
			}

			SessionContext sessionContext = (SessionContext) sessionContextFactory.getObject();
			session.setAttribute("userInfo", userInfo);

			sessionContext.setUserInfo(null);
			sessionContext.setAuthenticated(false);
			sessionContext.clear();

			sessionContext.setUserInfo(userInfo);
			sessionContext.setAuthenticated(true);
			sessionContext.setAuthList(authList);
			sessionContext.setEncrID(encrId);

			session.setAttribute("sessionContext", sessionContext);
			session.setAttribute("usedSSO", Constants.NO);

			UserInfo userVo = (UserInfo) userInfo;

			mv.setViewName("redirect:/index.do");

			succYn = true;
			failMesg = "";
			if(userVo.getPswdDivCd().equals("SP")) lgnDivCd = "PL";
		} catch (EgovBizException ebEx) {
			succYn = false;
			failMesg = ebEx.getMessageKey();
			if(failMesg.contains("WrongPasswdCnt")) pswdErrYn="Y";
			mv.addObject("usrId", usrId);
			mv.addObject("message", ebEx.getMessage());
			//mv.addObject("message", "EgovBizException 예외오류 발생"); //code-ray
			mv.addObject("pswdErrYn", pswdErrYn);
        	setCryptoKey(session, mv);
		} catch (Exception e) {
			log.error("login처리실패", e);
			succYn = false;
			//failMesg = e.getMessage().substring(0, 70);
			failMesg = "Exception 발생"; //code-ray
			mv.addObject("usrId", usrId);
			mv.addObject("message", "로그인할 수 없습니다.\n관리자에 문의바랍니다.");
        	setCryptoKey(session, mv);
		}

		loginDtm = loginService.saveLoginLog(sysId, usrId, lgnDivCd, clntIp, succYn, failMesg, pswdErrYn);
		session.setAttribute("loginDtm", loginDtm);

		return mv;
	}

    /**
     * local로그아웃 기본 처리.
     * @param session
     * @param status
     * @return
     * @throws EgovBizException
     */
	protected ModelAndView doLocalLogout(HttpSession session, SessionStatus status) throws EgovBizException {
		ModelAndView mv = new ModelAndView();

		SessionContext sessionContext = (SessionContext) sessionContextFactory.getObject();

		IUserInfo userInfo = sessionContext.getUserInfo();

		String usedSSO = (String) session.getAttribute("usedSSO");
		String loginDtm = (String) session.getAttribute("loginDtm");
		String sysId = sysPropService.getString("system.sysId");
		String usrId = userInfo.getUsrId();

		sessionLogout(session, status, mv);

		loginService.logout(loginDtm, sysId, usrId);

		// SSO login이면서 sso page url이 존재하면 sso page url로 이동, 그렇지 않으면 loginPage로 이동.
		String context = sysPropService.getString("system.context");
		String loginURL = (ROOT_CONTEXT_PATH.equals(context)?"":context)+"/localLogin.do";
		String ssoURL = sysPropService.getString("system.auth.login.sso.page.url",Constants.BLANK);
		if(!Constants.BLANK.equals(ssoURL) && Constants.YES.equals(usedSSO)){
			loginURL = ssoURL;
		}


		final String viewName   = sysPropService.getString("system.auth.logout.viewName");
		mv.setViewName(viewName);
		mv.addObject("loginURL", loginURL);

		return mv;
	}



	/**
	 * 로그인 view page에 필요한 정보 생성.
	 *
	 * @param session
	 * @param mv
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	protected void setCryptoKey(HttpSession session, ModelAndView mv) throws EgovBizException {
		String viewName   = sysPropService.getString("system.auth.login.viewName");

		mv.setViewName(viewName);

		try {
	        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
	        generator.initialize(KEY_SIZE);

	        KeyPair keyPair = generator.genKeyPair();
	        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

	        PublicKey publicKey = keyPair.getPublic();
	        PrivateKey privateKey = keyPair.getPrivate();

	        // 세션에 공개키의 문자열을 키로하여 개인키를 저장한다.
	        session.setAttribute("__rsaPrivateKey__", privateKey);

	        // 공개키를 문자열로 변환하여 JavaScript RSA 라이브러리 넘겨준다.
	        RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);

	        String publicKeyModulus = publicSpec.getModulus().toString(16);
	        String publicKeyExponent = publicSpec.getPublicExponent().toString(16);

	        mv.addObject("publicKeyModulus", publicKeyModulus);
	        mv.addObject("publicKeyExponent", publicKeyExponent);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			//throw new EgovBizException("setCryptoKey 오류 발생 :" + e.getMessage());
			throw new EgovBizException("setCryptoKey 오류 발생 " ); //code-ray
			//e.printStackTrace();
		}
	}

	/**
	 * session logout 처리.
	 * @param session
	 * @param status
	 * @param mav
	 * @throws EgovBizException
	 */
	protected void sessionLogout(HttpSession session, SessionStatus status, ModelAndView mav) throws EgovBizException {
		SessionContext sessionContext = (SessionContext) sessionContextFactory.getObject();

		session.removeAttribute("userInfo");
		session.removeAttribute("sessionContext");
		session.removeAttribute("usedSSO");
		session.invalidate();

		sessionContext.setUserInfo(null);
		sessionContext.setAuthenticated(false);

		sessionContext.clear();

		status.setComplete();
	}

	/**
	 * RSA 복호화
	 * @param privateKey
	 * @param securedValue
	 * @return
	 * @throws EgovBizException
	 */
	protected String decryptRsa(PrivateKey privateKey, String securedValue) throws EgovBizException {
		try {
			Cipher cipher = Cipher.getInstance("RSA");

	        byte[] encryptedBytes = hexToByteArray(securedValue);
	        cipher.init(Cipher.DECRYPT_MODE, privateKey);
	        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
	        String decryptedValue = new String(decryptedBytes, "utf-8"); // 문자 인코딩 주의.
	        return decryptedValue;

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
					IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			//throw new EgovBizException("decryptRsa 오류 발생 :" + e.getMessage());
			throw new EgovBizException("decryptRsa 오류 발생 ");  //code-ray
		}

    }

    /**
     * 16진 문자열을 byte 배열로 변환한다.
     */
    private byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() % 2 != 0) {
            return new byte[]{};
        }

        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            byte value = (byte)Integer.parseInt(hex.substring(i, i + 2), 16);
            bytes[(int) Math.floor(i / 2)] = value;
        }
        return bytes;
    }

	/**
	 * 세션아이디 변경
	 * @param request
	 * @return {@link HttpSession}
	 * @throws EgovBizException
	 */
	protected HttpSession changeSessionId(HttpServletRequest request) throws EgovBizException {

		Map<String, Object> sessMap = new HashMap<String, Object>();
		HttpSession oldSession = request.getSession();

		@SuppressWarnings("rawtypes")
		Enumeration enumaration = oldSession.getAttributeNames();
        while (enumaration.hasMoreElements()) {
            String name = enumaration.nextElement().toString();
            if(log.isDebugEnabled()){
                log.debug("BEFORE copy session attribute >>> " + name + " : " + (oldSession.getAttribute(name)).toString());
            }
            sessMap.put(name, oldSession.getAttribute(name));
        }

        oldSession.invalidate();
        HttpSession session = request.getSession(true);
        for (String sessMapKey: sessMap.keySet()) {
            if(log.isDebugEnabled()){
            	log.debug("AFTER copy session attribute >>> " + sessMapKey + " : " + (sessMap.get(sessMapKey)).toString());
            }
        	session.setAttribute(sessMapKey, sessMap.get(sessMapKey));
        }

        return session;
	}





}
