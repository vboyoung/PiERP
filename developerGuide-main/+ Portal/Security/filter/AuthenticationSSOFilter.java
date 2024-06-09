/**
 * SSO 로그인 필터
 */
package pierp.app.common.security.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.MDC;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.property.EgovPropertyService;
import pierp.app.link.sso.service.SsoService;
import pierp.common.base.Constants;
import pierp.common.main.dao.LoginDAO;
import pierp.common.main.model.IAuthVO;
import pierp.common.main.model.IUserInfo;
import pierp.common.main.model.UserInfo;
import pierp.common.main.service.LoginService;
import pierp.common.security.filter.AbstractCheckFilter;
import pierp.common.session.SessionContext;
import pierp.common.util.StringUtils;

/**
 * @author neat98
 *
 */
public class AuthenticationSSOFilter extends AbstractCheckFilter {

	private EgovPropertyService sysPropService;
	private SsoService ssoService;


	private ObjectFactory<?> sessionContextFactory;
	private LoginService loginService;
	private LoginDAO loginDAO;

	private String sysId;
	private String usedSSO;
	private String bToken;


	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext ctx = filterConfig.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);

		sysPropService = (EgovPropertyService)wac.getBean("sysPropService");
		ssoService	   = (SsoService)wac.getBean("ssoService");

		loginService 	= (LoginService)wac.getBean("loginService");
		loginDAO		= (LoginDAO)wac.getBean("loginDao");
		sessionContextFactory= (ObjectFactory<?>)wac.getBean("sessionContextFactory");



		usedSSO = sysPropService.getString("system.auth.login.sso.useYn");
		sysId 	= sysPropService.getString("system.sysId");
		bToken	= sysPropService.getString("system.auth.login.sso.bToken");	//백도어 토큰

		logger.debug("usedSSO : " + usedSSO + ", sysId :" + sysId);
		readExcluedURLs(filterConfig);
	}


	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		hasExcludeURL = false;

		excludeUrlList.clear();
		excludeUrlList = null;

		sysPropService = null;
		ssoService = null;
		loginService = null;
		loginDAO = null;
		sessionContextFactory = null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{

		logger.debug("AuthenticationSSOFilter...BGN...");

		HttpServletRequest  req = (HttpServletRequest) request ;
		HttpServletResponse res = (HttpServletResponse) response ;

		String retMsgKey = null;

		HttpSession ses = req.getSession();
		IUserInfo userInfo = (IUserInfo) ses.getAttribute("userInfo");
		MDC.put("reqId", ( userInfo==null ? request.getRemoteAddr()+"-"+Long.toString(System.currentTimeMillis()) : userInfo.getUsrId()+"-"+Long.toString(System.currentTimeMillis()) ));


		if(hasExcludeURL && isExcludeURL(req)){
			//걸러내는 URI일 경우 요청값 그대로 처리
			chain.doFilter(request, res);
		}else{
			//아닐경우 요청값 변경
			retMsgKey = checkAuthenticated(req);

			if(logger.isDebugEnabled()){
				logger.debug("checked action="+req.getServletPath()+"; checkResult="+retMsgKey);
			}
			if(Constants.BLANK.equals(retMsgKey)){
				chain.doFilter(request, response);
			}else{
				//로그인 인증 오류 발생 시
				String action = req.getServletPath().trim();
				if(sysId.equals("EIP") && action.endsWith("oProxy.do")) {
					//외부 요청 프락시에서 들어온거면 로그인 페이지 가기전에 거쳐 갈 수 있도록.. 루트에 있는 jsp 를 호출 한다.
					//log.debug("oProxy.do param:" + req.getQueryString());
					RequestDispatcher dispatcher = req.getRequestDispatcher("/oProxy.jsp?"+req.getQueryString());
					dispatcher.forward(req, res);
				}else { //원래 루틴
					returnRequest(retMsgKey, req, res);
				}
			}
		}


		logger.debug("AuthenticationSSOFilter...END...");
	}



	/**
	 * request가 인증된 사용자의 request인지 check.
	 * @param req
	 * @return
	 */
	private String checkAuthenticated(ServletRequest req) {
		String msgKey = "";

		HttpServletRequest request = (HttpServletRequest) req ;

		HttpSession session = request.getSession();
		SessionContext sessionContext = (SessionContext) session.getAttribute("sessionContext");

		IUserInfo userInfo = null;


		if(usedSSO.equals(Constants.NO)) {
			//로컬 로그인 이면 기존 로직 그대로...
			if(sessionContext == null) return "fatal.session.NotFoundSessionContext";
			if(!sessionContext.isAuthenticated()) return "fatal.session.NotAuthenticated";
			userInfo = sessionContext.getUserInfo();
			if(userInfo == null) return "fatal.session.NotFoundUserInfo";
		}else {

			//SSO 로그인 이면...
			String iToken = StringUtils.nullConvert(request.getParameter("iToken"));
			String secondAuth = StringUtils.nullConvert(request.getParameter("secondAuth")); //포털에서 토큰과 함께 무조건 넘겨줘야하는 값 (없으면 인증오류)

			if(iToken.length() > 0) {
				//토큰을 물고 들어왔다면 세션의 토큰과 비교
				logger.debug("iToken param : " + iToken);
				logger.debug("iToken session : " + session.getAttribute("iToken"));

				if( !iToken.equals(session.getAttribute("iToken")) ) {
					//세션의 토큰과 다르다면 기존 세션 로그아웃!
					sessionLogout(sessionContext, session);
					sessionContext = null; //null은 call by ref 안됨 (여기서 해야 함)
				}
			}

			//이미 로그인 돼 있는 상황
			if(sessionContext != null && !(iToken.equals(bToken)) ) {
				logger.debug("sessionContext NOT NULL : " + sessionContext);
				//sessionContext 가 있다면 로그인 정보를 가져온 상태! 기존 검사 로직으로 처리.. (백도어 토큰이 아닐 경우만)
				if(!sessionContext.isAuthenticated()) return "fatal.session.NotAuthenticated"; //인증되지 않은 사용자 요청입니다. 로그인부터 다시 하세요!!!
				userInfo = sessionContext.getUserInfo();
				if(userInfo == null) return "fatal.session.NotFoundUserInfo"; //인증된 사용자 정보가 없습니다. 다시 로그인하세요!!!

				secondAuth = (String)session.getAttribute("secondAuth"); //토큰 로그인 시 세션에 세팅됨
				if(secondAuth == null || !secondAuth.equals("Y")) {	//2차 인증을 안 한경우 (관리자가 사용하지 않음 설정시 로그인하면 Y로 세팅됨)
					logger.info("Failed secondAuth : "+ secondAuth );
					return "fatal.session.NotAuthenticated"; //인증되지 않은 사용자 요청입니다. 로그인부터 다시 하세요!!!
				}

				iToken = (String)session.getAttribute("iToken");
			}else {
				//sessionContext 가 없다면 토큰으로 만들기...
				logger.debug("sessionContext NULL, iToken :" + iToken);
				String clntIp= request.getRemoteAddr();
				String usrId = "";

				if( iToken.equals(bToken) ) {
					//백도어 토큰 이면...,
					usrId = StringUtils.nullConvert( request.getParameter("usrId") ); //usrId를 파라미터로 넘겨 받는다.
					if(StringUtils.isEmpty(usrId)) {
						logger.info("Failed SSO Backdoor Login ID is Empty! ");
						return "fatal.session.NotAuthenticated"; //인증되지 않은 사용자 요청입니다. 로그인부터 다시 하세요!!!
					}
					secondAuth = "Y"; //백도어면 2차 인증 패스
				}else {
					//토큰으로 사용자 정보 조회
					try {
						userInfo = ssoService.getUsrInfo(iToken, clntIp);
					}catch (EgovBizException e) {
						logger.error("Exception SSO getUsrInfo iToken : "+ iToken );
						return "fatal.framework.mvc.NotSupported"; //해당 요청을 처리할 수 없습니다.
					}
					if(userInfo == null) {
						logger.info("Failed SSO getUsrInfo iToken : "+ iToken );
						return "fatal.session.NotAuthenticated"; //인증되지 않은 사용자 요청입니다. 로그인부터 다시 하세요!!!
					}

					logger.debug("userInfo : " + userInfo.getUsrId());
					usrId = userInfo.getUsrId();
				}
				userInfo = sessionLogin(request, usrId, clntIp, iToken, secondAuth);	//사용자 정보 조회 후 세션에 담는다.
			}

			//토큰 검증 ---
			if( iToken.equals(bToken) ) {
				//백도어 토큰 이면...,
				logger.info("관리자 백도어 접근 중 usrId: "+ userInfo.getUsrId() );
			}else {

				//2차 인증 검증
				if(secondAuth == null || !secondAuth.equals("Y")) {	//2차 인증을 안 한경우 (관리자가 사용하지 않음 설정시 로그인하면 Y로 세팅됨)
					logger.info("Failed secondAuth : "+ secondAuth );
					return "fatal.session.NotAuthenticated"; //인증되지 않은 사용자 요청입니다. 로그인부터 다시 하세요!!!
				}
				/* *****************
				 * 중복 로그인 API 등이 있을때 실시간으로 계속 토큰을 체크 하는 기능......
				errCode = ssoService.executeUserLoginCheck(iToken);
				if( !("".equals(errCode)) ) {
					log.info("Failed SSO executeUserLoginCheck errCode : " + errCode + "; iToken : "+ iToken );
					return "fatal.session.NotFoundSessionContext"; //세션이 종료되었습니다. 다시 로그인하세요!!!
				}
				boolean loginFlag = ssoService.isLogin();	//후입자 정책
				log.info("SSO loginFlag : " + loginFlag + "; iToken : "+ iToken );
				if(loginFlag) {
					log.info("SSO loginFlag TRUE(후입자) ; resultData : "+ ssoService.getResultData() + ", iToken: " + iToken );
				}else {
					log.info("SSO loginFlag FALSE(선입자) ; resultData : "+ ssoService.getResultData() + ", iToken: " + iToken );
					return "fatal.session.duplicated"; //다른곳에서 로그인 되었습니다.  다시 로그인하세요!!!
				}
				******************* */
			}
		}
		return msgKey;
	}



	/**
	 * 토큰으로 로그인 시킴
	 * @param request
	 * @param usrId
	 * @param clntIp
	 * @param iToken
	 * @param secondAuth
	 * @return
	 */
	private UserInfo sessionLogin(HttpServletRequest request, String usrId, String clntIp, String iToken, String secondAuth) {
		boolean succYn = true;
		UserInfo userInfo;
		String encrId=null;
		String failMesg = "", lgnDivCd="SL", pswdErrYn="N";  //lgnDivCd


		userInfo = (UserInfo)loginDAO.selectUserInfo(usrId);
		userInfo.setPswdDivCd("LP");  // SP 는 super user
		userInfo.setUserIP(clntIp);

		HttpSession session = request.getSession();

		SessionContext sessionContext = (SessionContext) sessionContextFactory.getObject();
		session.setAttribute("userInfo", userInfo);

		sessionContext.setUserInfo(null);
		sessionContext.setAuthenticated(false);
		sessionContext.clear();

		List<IAuthVO> authList = loginService.getAuthList(sysId, usrId);

		sessionContext.setUserInfo(userInfo);
		sessionContext.setAuthenticated(true);
		sessionContext.setAuthList(authList);
		sessionContext.setEncrID(encrId);

		session.setAttribute("sessionContext", sessionContext);
		session.setAttribute("usedSSO", Constants.YES);	//SSO 사용으로 변경

		session.setAttribute("iToken", iToken);
		session.setAttribute("secondAuth", secondAuth);

		String loginDtm = new SimpleDateFormat ("yyyyMMddHHmmss").format(System.currentTimeMillis());
		if( !iToken.equals(bToken) ) { //백도어 진입시는 로그를 남기지 않도록 함
			try {
				loginDtm = loginService.saveLoginLog(sysId, usrId, lgnDivCd, clntIp, succYn, failMesg, pswdErrYn);
			}catch (DataIntegrityViolationException e) {
				logger.error("saveLoginLog 입력(중복) 오류");
			}catch (Exception e) {
				logger.error("saveLoginLog 예외발생");
			}
		}

		session.setAttribute("loginDtm", loginDtm);
		return userInfo;
	}



	/**
	 * session logout 처리.
	 * @param sessionContext
	 * @param session
	 */
	private void sessionLogout(SessionContext sessionContext, HttpSession session) {
		logger.debug("SSO Filter sessionLogout ");
		session.removeAttribute("userInfo");
		session.removeAttribute("sessionContext");
		session.removeAttribute("usedSSO");
		session.removeAttribute("iToken");
		session.removeAttribute("secondAuth");

		//session.invalidate();
		if( sessionContext != null ) {
			sessionContext.setUserInfo(null);
			sessionContext.setAuthenticated(false);
			sessionContext.clear();
		}
	}

}
