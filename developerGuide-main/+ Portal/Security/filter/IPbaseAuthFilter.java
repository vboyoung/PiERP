/*
 * Project : pierp2-app-stm
 * 위치 : pierp.app.stm.common.security.filter
 * File : IPbaseAuthFilter.java
 * 생성일자 : 2022. 5. 10
 * 생성자 : 이정민
 *
 * Copyright(c) 2022 by ITMATE
 */
package pierp.app.common.security.filter;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.MDC;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.property.EgovPropertyService;
import pierp.app.common.security.model.AprhCtrlVO;
import pierp.app.common.security.model.TbSyUsrComSecuSetVO;
import pierp.app.common.security.service.SecurityService;
import pierp.common.base.Constants;
import pierp.common.main.model.IUserInfo;
import pierp.common.security.filter.AbstractCheckFilter;

/**
 * @Class IPbaseAuthFilter
 * @Description 
 * 
 * @author itmate
 * @since 2022. 3. 10
 * @version 
 *
 */
public class IPbaseAuthFilter extends AbstractCheckFilter {

	protected EgovPropertyService sysPropService;

	protected SecurityService securityService;
	
	private String sysId;
	
	public String getSysId() {
		return this.sysId;
	}
	
	/* (non-Javadoc)
	 * @see pierp.common.security.filter.AbstractCheckFilter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		ServletContext ctx = filterConfig.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);

		sysPropService = (EgovPropertyService)wac.getBean("sysPropService");
		securityService = (SecurityService)wac.getBean("securityService");
		
		sysId 	= sysPropService.getString("system.sysId");
		
		if(logger.isDebugEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("sysPropService is ");
			if(this.sysPropService == null) {
				sb.append("null.");
			}else {
				sb.append(sysPropService.getClass().getSimpleName()).append(".");
			}
			sb.append(" ").append("securityService is ");
			if(this.securityService == null) {
				sb.append("null.");
			}else {
				sb.append(securityService.getClass().getSimpleName()).append(".");
			}
			sb.append(" ").append("sysId is ").append(sysId).append(".");
			super.logger.debug(sb.toString());
		}
		
		setReturnRequestUrl(filterConfig);
		super.readExcluedURLs(filterConfig);
	}

	/* (non-Javadoc)
	 * @see pierp.common.security.filter.AbstractCheckFilter#destroy()
	 */
	@Override
	public void destroy() {
		hasExcludeURL = false;
		
		excludeUrlList.clear();
		excludeUrlList = null;
		
		sysPropService = null;
		securityService = null;
	}

	/* (non-Javadoc)
	 * @see pierp.common.security.filter.AbstractCheckFilter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		super.logger.debug("IPbaseAuthFilter...BGN...");

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
				returnRequest(retMsgKey, req, res);
			}
		}


		super.logger.debug("IPbaseAuthFilter...END...");
	}
	

	/**
	 * request가 인증된 사용자의 request인지 check.
	 * @param req
	 * @return
	 */
	private String checkAuthenticated(ServletRequest req)
	{
		HttpServletRequest request = (HttpServletRequest) req ;
		//HttpSession session = request.getSession();
		//SessionContext sessionContext = (SessionContext) session.getAttribute("sessionContext");

        String ip = remoteAddr(request);
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	return "IP조회 오류 입니다. 관리자에게 문의해 주세요!";
        }

        logger.debug("remote IP : " + ip + ", sysId :" + sysId);	//EIP 면 사용자 접근제어, SYS 면 관리자 접근제어

        //접근 제어 사용 여부 조회(EIP 면 사용자 접근제어, SYS 면 관리자 접근제어)
        List<AprhCtrlVO> aprhCtrlList = null; //허용 IP 목록

        try {
	        TbSyUsrComSecuSetVO tbSysUsrComSecuSetVO   = securityService.selectSysUsrComSecuSet(); //사용자공통보안설정 조회

	        if( sysId.equals("EIP") ) {
	        	if( tbSysUsrComSecuSetVO.getUsrIpAprhCtrlUseYn().equals("N") ) { //사용자IP접근제어사용여부
	        		return ""; //사용안함이면 바로 리턴
	        	}
	        	aprhCtrlList = securityService.selectAprhCtrlList("B");  //사용자접근제어 허용 IP 범위 조회
	        }else if( sysId.equals("STM") ){
	        	if( tbSysUsrComSecuSetVO.getMngrIpAprhCtrlUseYn().equals("N") ) { //사용자IP접근제어사용여부
	        		return ""; //사용안함이면 바로 리턴
	        	}
	        	aprhCtrlList = securityService.selectAprhCtrlList("A");  // 관리자접근제어 허용 IP 범위 조회
	        }
	        else{ // SSO
	        	aprhCtrlList = securityService.selectAprhCtrlList("C"); // SSO 접근제어 허용 IP 범위 조회
//        		return ""; //사용안함이면 바로 리턴
	        }
        }catch (EgovBizException e) {
        	logger.error("IP 설정 정보 조회 오류 입니다.");
        	return "IP 설정 정보 조회 오류 입니다. 관리자에게 문의해 주세요!";
		}catch (NullPointerException e) {
			logger.error("IP 설정 정보확인 필요");
        	return "IP 설정 정보가 등록되지 않았습니다. 관리자에게 문의해 주세요!";
		}

        if(aprhCtrlList == null || aprhCtrlList.size() <= 0 ) {
        	logger.info("허용 IP 가 등록되지 않음");
        	return "접근허용 IP가 아닙니다!";
        }


        //접속  IP 가 해당 범위에 있는지 판단
        for(AprhCtrlVO aprhCtrlVO : aprhCtrlList) {
        	logger.debug("범위 " + aprhCtrlVO.getBegnIp() + " ~ " + aprhCtrlVO.getEndIp() + " : " + ip );

            if( ipRangeChk(aprhCtrlVO.getBegnIp(), aprhCtrlVO.getEndIp(), ip) ) {
            	logger.debug("범위내 있음 OOOOOOOOOOOOOOOOOOOO");
            	return ""; //범위내 있으면 바로 리턴
            }else {
            	logger.debug("범위내 없음 XXXXXXXXXXXXXXXXXXXX");
            }

        }


        //---------테스트 코드 ---------
        //접속  IP 가 해당 범위에 있는지 판단
//        String begin  = "10.100.0.0";
//        String end	= "10.100.0.255";
//
//        String target = "10.100.0.1";
//
//        if( ipRangeChk(begin, end, target) ) {
//        	log.debug("범위내 있음 OOOOOOOOOOOOOOOOOOOOOO");
//        	return ""; //범위내 있으면 바로 리턴
//        }else {
//        	log.debug("범위내 없음 XXXXXXXXXXXXXXXXXXXXXX");
//        }
         //---------테스트 코드 ---------


		return "접근허용 IP가 아닙니다!";
	}

	/**
	 * 검사 대상이 범위 안에 있는지 체크
	 * @param begin : IP 범위 시작
	 * @param end   : IP 범위 끝
	 * @param target: 검사 대상
	 * @return : 범위안에 있다 true, 없다 false
	 */
	private boolean ipRangeChk(String begin, String end, String target) {
		try {
			long lBegin = ipToBigInteger(begin).longValue();
			long lEnd = ipToBigInteger(end).longValue();
			long lTarget = ipToBigInteger(target).longValue();

			if(lTarget >= lBegin && lTarget <= lEnd) {
				return true;
			}else {
				return false;
			}

		} catch (UnknownHostException e) {
			logger.error("UnknownHostException: " + begin + ", " + end + ", " + target);
			return false;
		}
	}

	/*
	 * IPv4, IPv6 를 숫자로 변경
	 */
	private BigInteger ipToBigInteger(String addr) throws UnknownHostException{
	    InetAddress a = InetAddress.getByName(addr);
	    byte[] bytes = a.getAddress();
	    return new BigInteger(1, bytes);
	}

	/*
	 * 접속한 사용자의 IP를 구함
	 */
	private String remoteAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-FORWARDED-FOR");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		     ip = request.getRemoteAddr();
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		     ip = request.getHeader("Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		     ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		     ip = request.getHeader("HTTP_CLIENT_IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		     ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		logger.debug("remoteAddr: " + ip);
		return ip;
	}

}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 * 
 */