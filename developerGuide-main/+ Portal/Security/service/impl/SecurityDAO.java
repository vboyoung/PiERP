package pierp.app.common.security.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.security.model.AprhCtrlVO;
import pierp.app.common.security.model.TbSyUsrComSecuSetVO;
import pierp.common.base.dao.BaseAbstractMapper;

/**
 * @Class SecurityDAO
 * @Description 
 * DAO 클래스를 정의한다.
 * @author 정성희
 * @since 2022. 5. 11.
 * @version 
 *
 */
@Repository
public class SecurityDAO extends BaseAbstractMapper {

	public TbSyUsrComSecuSetVO selectSysUsrComSecuSet() throws EgovBizException {
		return super.selectOne("Security.selectSysUsrComSecuSet");
	}
		
	public List<AprhCtrlVO> selectAprhCtrlList(String sysCls)  throws EgovBizException {
		return super.selectList("Security.selectAprhCtrlList", sysCls);
	}
}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 * 2020. 8. 12.   정성희
 */