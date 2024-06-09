package pierp.app.login.common.service.impl;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.property.EgovPropertyService;
import pierp.app.common.security.model.TbSyUsrComSecuSetVO;
import pierp.app.common.security.service.SecurityService;
import pierp.app.login.common.model.UserInfoEip;
import pierp.app.login.common.service.SSOLoginService;
import pierp.common.base.Constants;
import pierp.common.cmmn.service.BaseAbstractServiceImpl;
import pierp.common.crypto.service.CryptoService;
import pierp.common.main.dao.LoginDAO;
import pierp.common.main.model.IUserInfo;
import pierp.common.main.model.UsrLgnVO;
import pierp.common.main.service.LoginService;
import pierp.common.session.SessionContext;
import pierp.common.util.StringUtils;


/**
 * @Class SSOLoginServiceImpl
 * @Description
 *  SSOLogin Service 구현체
 * @author 정성희
 * @since 2022.05.13.
 * @version
 *
 *  * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *
 *
 */

@Service(value="ssoLoginService")
public class SSOLoginServiceImpl extends BaseAbstractServiceImpl implements SSOLoginService{

	@Resource(name="sysPropService")
	protected EgovPropertyService sysPropService;

	@Resource(name = "messageSource")
	protected MessageSource messageSource;

	@Resource(name="cryptoService")
	protected CryptoService cryptoService;

	@Resource(name="securityService")
	protected SecurityService securityService;


	@Resource(name="sessionContextFactory")
	protected ObjectFactory<?> sessionContextFactory;

	@Resource(name="loginService")
	protected LoginService loginService;


	@Autowired
	private LoginDAO loginDAO;

	@Autowired
	private SSOLoginDAO ssoLoginDAO;


	@Override
	public UserInfoEip getUsrLoginInfo(String usrId)  throws EgovBizException {
		return ssoLoginDAO.getUsrLoginInfo(usrId);
	}



	@Override
	public String getReqularExpression() {

		String minLnth  = sysPropService.getString("system.auth.password.minLnth","8");
		String maxLnth  = sysPropService.getString("system.auth.password.maxLnth","16");

		try {
			//관리자 로그인 관련 설정 조회
			TbSyUsrComSecuSetVO tbSysUsrComSecuSetVO   = securityService.selectSysUsrComSecuSet(); //사용자공통보안설정 조회
			if( tbSysUsrComSecuSetVO.getMinPwdLen() != null && tbSysUsrComSecuSetVO.getMaxPwdLen() != null ) {
				minLnth = tbSysUsrComSecuSetVO.getMinPwdLen();
				maxLnth = tbSysUsrComSecuSetVO.getMaxPwdLen();
			}
		}catch (EgovBizException e) {
				getLogger().error("selectSysUsrComSecuSet 조회 오류 발생!");
		} //사용자공통보안설정 조회


        String regExAlpabetYn  = sysPropService.getString("system.auth.password.regularExpression.alpabet.useYn","N");
        String regExNumbericYn  = sysPropService.getString("system.auth.password.regularExpression.numberic.useYn","N");
        String regExSpecialCharacter    = sysPropService.getString("system.auth.password.regularExpression.specialCharacter","");
        String regExSpecialCharacterYn  = sysPropService.getString("system.auth.password.regularExpression.specialCharacter.useYn","N");

        String labelAlpabet     = "";
		String labelNumberic    = "";
		String labelSpecialChar = "";
        String specialCharacter = "";

        if(regExAlpabetYn.equals("Y")){
			labelAlpabet = messageSource.getMessage( "label.alpabet", null , Locale.getDefault());
		}

		if(regExNumbericYn.equals("Y")&&StringUtils.isNotEmpty(labelAlpabet)){
			labelNumberic = ", " + messageSource.getMessage( "label.numberic", null , Locale.getDefault());
		}else if(regExNumbericYn.equals("Y")){
			labelNumberic = messageSource.getMessage( "label.numberic", null , Locale.getDefault());
		}

		if(regExSpecialCharacterYn.equals("Y")&&(StringUtils.isNotEmpty(labelAlpabet)||StringUtils.isNotEmpty(labelNumberic))){
			labelSpecialChar = ", "+messageSource.getMessage( "label.specialCharacter", null , Locale.getDefault());
			specialCharacter = "("+regExSpecialCharacter+")";
		}else if(regExSpecialCharacterYn.equals("Y")){
			labelSpecialChar = messageSource.getMessage( "label.specialCharacter", null , Locale.getDefault());
			specialCharacter = "("+regExSpecialCharacter+")";
		}

		Object[] parameter = { new String(labelAlpabet),new String(labelNumberic),new String(labelSpecialChar),new String(specialCharacter),new String(minLnth),new String(maxLnth) };

		String mixStr = messageSource.getMessage( "info.login.password.regularExpression", parameter , Locale.getDefault());

		return mixStr;
	}

	/**
	 * 비밀번호 유효성 체크
	 * @param usrId
	 * @param pswrd
	 * @param newPswrd
	 * @param newChkPaswrd
	 * @return
	 * @throws EgovBizException
	 */
	@Override
	public String passwordCharCheck(String usrId, String pswrd, String newPswrd, String newChkPaswrd) throws EgovBizException{

		String message = "";
        String tempStr  = "";

        // 비밀번호 길이 디폴드 갑은 8~16
        String minLnth  = sysPropService.getString("system.auth.password.minLnth","8");
        String maxLnth  = sysPropService.getString("system.auth.password.maxLnth","16");

		try {
			//관리자 로그인 관련 설정 조회
			TbSyUsrComSecuSetVO tbSysUsrComSecuSetVO   = securityService.selectSysUsrComSecuSet(); //사용자공통보안설정 조회
			if( tbSysUsrComSecuSetVO.getMinPwdLen() != null && tbSysUsrComSecuSetVO.getMaxPwdLen() != null ) {
				minLnth = tbSysUsrComSecuSetVO.getMinPwdLen();
				maxLnth = tbSysUsrComSecuSetVO.getMaxPwdLen();
			}
		}catch (EgovBizException e) {
				getLogger().error("selectSysUsrComSecuSet 조회 오류 발생!");
		} //사용자공통보안설정 조회

        String regExAlpabet    = sysPropService.getString("system.auth.password.regularExpression.alpabet","a-zA-Z");
        String regExAlpabetYn  = sysPropService.getString("system.auth.password.regularExpression.alpabet.useYn","N");
        String regExAlpabetRequiredYn  = sysPropService.getString("system.auth.password.regularExpression.alpabet.groupYn","N");

        String regExNumberic    = sysPropService.getString("system.auth.password.regularExpression.numberic","0-9");
        String regExNumbericYn  = sysPropService.getString("system.auth.password.regularExpression.numberic.useYn","N");
        String regExNumbericRequiredYn  = sysPropService.getString("system.auth.password.regularExpression.numberic.groupYn","N");

        String regExSpecialCharacter    = sysPropService.getString("system.auth.password.regularExpression.specialCharacter","");
        String regExSpecialCharacterYn  = sysPropService.getString("system.auth.password.regularExpression.specialCharacter.useYn","N");
        String regExSpecialCharacterRequiredYn  = sysPropService.getString("system.auth.password.regularExpression.specialCharacter.groupYn","N");

        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();

        //정규식 시작문자
        sb.append("^");
        sb1.append("[");

        if(regExAlpabetYn.equals("Y")){
        	if(regExAlpabetRequiredYn.equals("Y")){
        		sb.append("(?=.*");
        		sb.append("[");
        		sb.append(regExAlpabet);
        		sb.append("]+)");
        	}
        	sb1.append(regExAlpabet);
        }

        if(regExNumbericYn.equals("Y")){
        	if(regExNumbericRequiredYn.equals("Y")){
        		sb.append("(?=.*");
        		sb.append("[");
        		sb.append(regExNumberic);
        		sb.append("]+)");
        	}
        	sb1.append(regExNumberic);
        }

        if(regExSpecialCharacterYn.equals("Y")){
        	if(regExSpecialCharacterRequiredYn.equals("Y")){
        		sb.append("(?=.*");
        		sb.append("[");
        		sb.append(regExSpecialCharacter);
        		sb.append("]+)");
        	}
        	sb1.append(regExSpecialCharacter);
        }
        sb1.append("]");

        //길이만 체크하고 무조건인경우
        if(sb.toString().equals("^")){
        	sb.append(".");
        }

        //값이 있는경우에만 append한다. ?=.*[]+)
        if(!sb1.toString().	equals("[]")){
        	sb.append(sb1);
        }

        //정규식 길이부분
        sb.append("{");
        sb.append(minLnth);
        sb.append(",");
        sb.append(maxLnth);
        sb.append("}");
        //정규식 끝문자
        sb.append("$");

        String passwordPattern = sb.toString();
//        	sb.toString();
        getLogger().debug(passwordPattern);
		tempStr = StringUtils.removeWhitespace(newPswrd);


		//정규식 체크 안돼는 부분
        if(pswrd.equals(newPswrd)){
        	message = "fail.login.Samnpasswd";
        }else if(!newPswrd.equals(newChkPaswrd)){
        	message = "fail.login.NotSamnNewPasswd";
        }else if(usrId.equals(newPswrd)){
        	message = "fail.login.SamnPasswdId";
        }else if(tempStr.length()!=newPswrd.length()){
        	message = "fail.login.NotVlPasswd";
        }

        if(StringUtils.isNotEmpty(message)){
        	return message;
        }

        Pattern pattern  = Pattern.compile(passwordPattern);
        Matcher  match   = pattern.matcher(newPswrd);
        boolean bl       = match.matches();

        if(!bl){
        	message = "fail.login.NotVlPasswd";
        }
		return message;
	}


	/**
	 * 비밀번호 암호화 처리 반환.
	 * @param plainText
	 * @return
	 * @throws Exception
	 */
	private String encryptPassword(String plainText) throws EgovBizException{
		String cryptoText = null;

		String useEncryptionString = sysPropService.getString("system.auth.password.useEncryption","N");

		if(Constants.YES.equals(useEncryptionString)){
			try {
				cryptoText = cryptoService.encryptPassword(plainText);
			}catch (Exception e) { //CORE 함수에서 Exception 으로 던지고 있음(수정불가)
				getLogger().error("cryptoService.encryptPassword 오류 발생!");
				throw new EgovBizException("cryptoService.encryptPassword 오류 발생!");
			}
		}else{
			cryptoText = plainText;
		}

		return cryptoText;
	}


	@Override
	public String updateLoginPswd(HttpServletRequest request, String usrId, String modrId, String secrNo, String newSecrNo, boolean pswdIintz) throws EgovBizException {

		String sysId    = sysPropService.getString("system.sysId");
		String clntIp= request.getRemoteAddr();

		UsrLgnVO usrLgnVO = loginDAO.checkUser(sysId, usrId);	//사용자 비밀번호 조회
		IUserInfo userInfo = loginDAO.selectUserInfo(usrId);	//사용자 사번 조회

		int procCnt = 0;
		String passwd=null, newPasswd=null, message = null ;

		//비밀번호 초기화(pswdIintz) 여부
		if(pswdIintz){
			String initPwd = userInfo.getEmpNo();	//창업진흥원 초기 비밀번호 : 사번
			try {
				newPasswd = encryptPassword(initPwd);
			} catch (EgovBizException e) {
				getLogger().error("cryptoService.encryptPassword 오류 발생!");
				throw super.processException("fail.login.InitzPasswd",new String[]{usrId});
			}
		}else {
			try {
				passwd    = encryptPassword(secrNo);
				newPasswd = encryptPassword(newSecrNo);
			} catch (EgovBizException e) {
				getLogger().error("cryptoService.encryptPassword 오류 발생!");
				throw super.processException("fail.login.ChgPasswd");
			}

			if(!passwd.equals(usrLgnVO.getSecrNo())){
				getLogger().debug("login check:["+passwd+"]:["+usrLgnVO.getSecrNo()+"]");
				throw super.processException("fail.login.WrongPasswd"); //비밀번호가 일치하지 않습니다.
			}
		}

		// 비밀번호 update
		procCnt = loginDAO.updateLoginPswd(usrId, modrId, newPasswd);

		if(procCnt!=1){
			if(pswdIintz){
				throw super.processException("fail.login.InitzPasswd",new String[]{usrId});
			}else{
				throw super.processException("fail.login.ChgPasswd");
			}
		}else{

			//succYn는 로그인 성공여부가 아닌 패스워드 초기화(변경) 성공으로 판단
			boolean succYn = true;

			if(pswdIintz){
				message = "success.login.InitzPasswd";
				loginService.saveLoginLog(sysId, usrId, "IN", clntIp, succYn, message, "N");
			}else{
				message = "success.login.ChgPasswd";
				loginService.saveLoginLog(sysId, usrId, "CH", clntIp, succYn, message, "N");
			}
		}
		return message;
	}


	@Override
	public String updateNewPswrd(HttpServletRequest request, String usrId, String newPswrd) throws EgovBizException {

		int procCnt = 0;
		String newPasswd=null, message = null;
		String sysId    = sysPropService.getString("system.sysId");
		String clntIp= request.getRemoteAddr();

		try {
			newPasswd = encryptPassword(newPswrd);
		} catch (EgovBizException e) {
			getLogger().error("cryptoService.encryptPassword 오류 발생!");
			throw super.processException("fail.login.InitzPasswd",new String[]{usrId});
		}

		// 비밀번호 update
		procCnt = loginDAO.updateLoginPswd(usrId, usrId, newPasswd);

		if(procCnt!=1){
			throw super.processException("fail.login.ChgPasswd");

		}else{
			message = "success.login.ChgPasswd";

        	//succYn는 로그인 성공여부가 아닌 패스워드 변경여부라고 판단 , 비밀번호 변경시는 비밀번호 오류 카운드 안함.
			boolean succYn = true;
        	loginService.saveLoginLog(sysId, usrId, "CH", clntIp, succYn, message, "N");
		}
		return message;

	}


	@Override
	public void doLogout(HttpServletRequest request, HttpSession session, SessionStatus status) throws EgovBizException {

    	SessionContext sessionContext = (SessionContext) sessionContextFactory.getObject();
    	IUserInfo userInfo = sessionContext.getUserInfo();

    	String usrId = null;
		if(userInfo != null) {
			usrId = userInfo.getUsrId();
		}

		String loginDtm = (String) session.getAttribute("loginDtm");
		String sysId = sysPropService.getString("system.sysId");

		String iToken = (String)session.getAttribute("iToken"); //itmate sso Token
		if(iToken == null || "".equals(iToken)) { //세션에서 받는 게 없으면
			iToken = request.getParameter("iToken"); //파라미터도 검사
		}
		getLogger().debug("iToken :" + iToken);

		sessionLogout(session, status);

		/* ********* logout API 와 백도어 토큰 개념이 있을때...
		String bToken	= sysPropService.getString("system.login.sso.bToken");	//백도어 토큰
		if( (iToken != null) && (!iToken.equals(bToken)) ) { //백도어 토큰이 아닌 경우만 SSO 에 로그아웃 요청
			ApiLogService ssoService = new ApiLogService();
			String logoutResult = ssoService.logoutLog(pniToken);
			getLogger().debug("logoutResult :" + logoutResult);

		}
		* **************/
		if(usrId != null) {
			loginService.logout(loginDtm, sysId, usrId);
		}

	}

	@Override
	public void sessionLogout(HttpSession session, SessionStatus status) {
		SessionContext sessionContext = (SessionContext) sessionContextFactory.getObject();

		session.removeAttribute("userInfo");
		session.removeAttribute("sessionContext");
		session.removeAttribute("usedSSO");
		session.removeAttribute("iToken");
		session.removeAttribute("secondAuth");
		//session.removeAttribute("eipAuht");		//업무포털 권한 (예전 포털 권한 방식-권한처리 끝나면 이 주석도 삭제!)

		session.invalidate();

		sessionContext.setUserInfo(null);
		sessionContext.setAuthenticated(false);

		sessionContext.clear();

		status.setComplete();
	}

}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *
 */