package pierp.app.eip.bizSCH.scha.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.cmd.SearchCmd;
import pierp.app.eip.bizSCH.scha.model.GwScheduleVO;
import pierp.app.eip.bizSCH.scha.service.SCHA01Service;
import pierp.common.cmmn.service.BaseAbstractServiceImpl;

/**
 * @Class SCHA01ServiceImpl
 * @Description
 * 그룹웨어 측 일정 I/F 서비스구현체를 정의한다.
 * @author 정성희
 * @since 2022.06.25.
 * @version
 *
 *  * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *
 *
 */

@Service
public class SCHA01ServiceImpl extends BaseAbstractServiceImpl implements SCHA01Service{

	@Autowired
	SCHA01DAO scha01Dao;


	@Override
	public List<GwScheduleVO> selectScheduleList(SearchCmd searchCmd) throws EgovBizException {
		return scha01Dao.selectScheduleList(searchCmd);
	}





}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *
 */