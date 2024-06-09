package pierp.app.login.common.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.login.common.model.UserInfoEip;

/**
 * @Class SSOLoginService
 * @Description
 * 	SSO Login Service
 * @author 정성희
 * @since 2022.05.13.
 * @version
 *
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 * 2022.05.13.   정성희		최초작성
 *
 */
public interface SSOLoginService {


	//public UserInfo selectUserInfo(String empNo, String usrNm, String resNo)  throws EgovBizException;
	//public UserInfo selectUserInfoByUserId(String usrId, String empNo, String usrNm, String resNo)  throws EgovBizException;

	/**
	 * 로그인한 사용자 정보 - PI_CORE에서 (포털용)추가된 정보들 포함한 조회
	 * @param usrId
	 * @return
	 * @throws EgovBizException
	 */
	public UserInfoEip getUsrLoginInfo(String usrId)  throws EgovBizException;

	/**
	 * 비밀번호 관련 Help 메시지.
	 * @return
	 * @throws EgovBizException
	 */
	public String getReqularExpression();


	/**
	 * 비밀번호 유효성 체크
	 * @param usrId
	 * @param pswrd
	 * @param newPswrd
	 * @param newChkPaswrd
	 * @return
	 * @throws EgovBizException
	 */
	String passwordCharCheck(String usrId, String pswrd, String newPswrd, String newChkPaswrd) throws EgovBizException;


	/**
	 * 비밀번호 변경
	 * @param usrId
	 * @param modrId	-- 수정자
	 * @param secrNo	-- 현 비밀번호
	 * @param newSecrNo	-- 새 비밀번호
	 * @param pswdIintz  -- 초기화 여부
	 * @return 처리메시지 초기화의 경우 'success.login.InitzPasswd', 변경일 경우 'success.login.ChgPasswd'를 return. 실패의 경우 exception발생
	 * @throws EgovBizException
	 */
	public String updateLoginPswd(HttpServletRequest request, String usrId, String modrId, String secrNo, String newSecrNo, boolean pswdIintz) throws EgovBizException;


	/**
	 * 비밀번호 수정
	 * 새 비밀번호, 비밀번호 확인 으로 변경 할 경우
	 * @param usrId
	 * @param modrId
	 * @param newSecrNo
	 * @return
	 * @throws EgovBizException
	 */
	public String updateNewPswrd(HttpServletRequest request, String usrId, String newPswrd) throws EgovBizException;


	/**
	 * 로그아웃 처리
	 * @param request
	 * @param session
	 * @param status
	 * @throws EgovBizException
	 */
	public void doLogout(HttpServletRequest request, HttpSession session, SessionStatus status) throws EgovBizException;

	/**
	 * 로그인 관련 세션 삭제 처리
	 * @param session
	 * @param status
	 */
	public void sessionLogout(HttpSession session, SessionStatus status);

}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *
 */