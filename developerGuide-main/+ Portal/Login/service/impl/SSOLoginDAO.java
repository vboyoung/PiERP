package pierp.app.login.common.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.login.common.model.UserInfoEip;
import pierp.common.base.dao.BaseAbstractMapper;

/**
 * @Class SSOLoginDAO
 * @Description
 *  SSO 및 로그인 정보 관련 DAO 정의
 * @author 정성희
 * @since 2022.05.17.
 * @version
 *
/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *
 */

@Repository
public class SSOLoginDAO extends BaseAbstractMapper {

	/**
	 * 로그인한 사용자 정보 - PI_CORE에서 (포털용)추가된 정보들 포함한 조회
	 * @param usrId
	 * @return
	 * @throws EgovBizException
	 */
	public UserInfoEip getUsrLoginInfo(String usrId) throws EgovBizException {
		return super.selectOne("SSO.getUsrLoginInfo", usrId);
	}



}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *
 */