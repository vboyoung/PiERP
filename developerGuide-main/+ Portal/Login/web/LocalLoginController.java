package pierp.app.login.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.common.util.StringUtils;

/**
 * 로컬 로그인 controller.
 *  - Portal용 abstract login controller 를 상속 받는다.
 * @author 정성희
 * 
 */
@Controller
public class LocalLoginController extends AbstractLoginController{
	
	/**
	 * 로그인 page view
	 * 
	 * @param request
	 * @param session
	 * @param response
	 * @param message
	 * @return
	 * @throws EgovBizException
	 */
	@RequestMapping(method=RequestMethod.GET, value="/localLogin.do")
	public ModelAndView localLoginForm(HttpServletRequest request, HttpServletResponse response) throws EgovBizException
	{
		return super.loadLocalLoginForm(request, response);
	}
	
	/**
	 * Local 로그인 처리.
	 * 
	 * @param request
	 * @param session
	 * @param status
	 * @param scrdUsrId
	 * @param scrdPswrd
	 * @return
	 * @throws EgovBizException
	 */
	@RequestMapping(method=RequestMethod.POST, value="/localLogin.do")
	public ModelAndView localLogin(HttpServletRequest request, HttpSession session, SessionStatus status,
			@RequestParam(value="scrdUsrId", 	required=true) String scrdUsrId,
			@RequestParam(value="scrdPswrd", 	required=true) String scrdPswrd,
			@RequestParam(value="rUrl", 	required=false) String rUrl		//바로가기 URL
			) throws EgovBizException {

		
		//로컬 로그인일 경우도 포털 권한  처리...
		// 권한은 같이 사용하는 형태로 변경할 예정 session.setAttribute("eipAuht", ssoLoginService.selectAuhtOne(usrId));
		
		ModelAndView mv = super.doLocalLogin(request, session, status, scrdUsrId, scrdPswrd);
		if(StringUtils.isNotEmpty(rUrl)) {
			//돌아가야 하는 URL 이 있을 경우에만 처리함

			//SessionContext sessionContext = (SessionContext) session.getAttribute("sessionContext");
			//if(sessionContext != null && sessionContext.isAuthenticated()) { ... }

			//위와 같이 세션으로 체크 하면 안됨(부모에서 세션을 일부러 흐트러 트림)
			//로그인이 성공 했다면 index.do 로 간다. 이를 가로채서 돌린다.
			String oldViewName = mv.getViewName();
			if(oldViewName != null && oldViewName.endsWith("/index.do")) {
				mv.setViewName("redirect:/eip/comm/oProxy.do?r=" + rUrl);
			}
			//System.out.println("rUrl 로 변경함 :" + mv.getViewName());
		}
		return mv;		
		
		//return super.doLocalLogin(request, session, status, scrdUsrId, scrdPswrd);
	}

    /**
     * logout처리.
     * @param session
     * @param status
     * @return
     * @throws EgovBizException
     */
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpSession session, SessionStatus status) throws EgovBizException {
		return super.doLocalLogout(session, status);
	}


	
}

