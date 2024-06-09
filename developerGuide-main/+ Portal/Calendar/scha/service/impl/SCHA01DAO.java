package pierp.app.eip.bizSCH.scha.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.cmd.SearchCmd;
import pierp.app.eip.bizSCH.scha.model.GwScheduleVO;
import pierp.common.base.dao.BaseAbstractMapper;

/**
 * @Class SCHA01DAO
 * @Description
 * 그룹웨어 측 일정 I/F DAO 클래스를 정의한다.
 * @author 정성희
 * @since 2022.06.25.
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
public class SCHA01DAO extends BaseAbstractMapper {

	public List<GwScheduleVO> selectScheduleList(SearchCmd searchCmd) throws EgovBizException {
		return super.selectList("SCHA01.selectScheduleList",searchCmd);
	}


}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *
 */