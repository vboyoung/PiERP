package pierp.app.common.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.security.model.AprhCtrlVO;
import pierp.app.common.security.model.TbSyUsrComSecuSetVO;
import pierp.app.common.security.service.SecurityService;
import pierp.common.cmmn.service.BaseAbstractServiceImpl;

/**
 * @Class SecurityServiceImpl
 * @Description 
 *  서비스구현체를 정의한다.
 * @author 이정민
 * @since 2022. 5. 10.
 * @version 
 *
 */
@Service("securityService")
public class SecurityServiceImpl extends BaseAbstractServiceImpl implements SecurityService {

	@Autowired
	private SecurityDAO securityDAO;
	
	/* 사용자공통보안설정 조회
	 * (non-Javadoc)
	 * @see kier.portal.common.security.service.SessionService#selectSysUsrComSecuSet()
	 */
	public TbSyUsrComSecuSetVO selectSysUsrComSecuSet() throws EgovBizException {
		getLogger().debug("사용자 공통 보안설정 조회");
		return securityDAO.selectSysUsrComSecuSet();
	}
	
	/* 관리자접근제어 허용 IP 범위 조회
	 * (non-Javadoc)
	 * @see kier.portal.common.security.service.SessionService#selectSysMngrAprhCtrlList()
	 */
	public List<AprhCtrlVO> selectAprhCtrlList(String sysCls)  throws EgovBizException {
		getLogger().debug("접근제어 허용 IP 범위 조회");
		return securityDAO.selectAprhCtrlList(sysCls);
	}
	
}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 * 2020.8. 12.   정성희
 */