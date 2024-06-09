package pierp.app.eip.bizSCH.scha.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.cmd.SearchCmd;
import pierp.app.eip.bizSCH.scha.model.GwScheduleVO;

/**
 * @Class SCHA01Service
 * @Description
 * 그룹웨어 측 일정 I/F 서비스를 정의한다.
 * @author 정성희
 * @since 2022.06.25.
 * @version
 *
 */
public interface SCHA01Service {

	/**
	 * 그룹웨어 측 일정 I/F 목록 조회
	 * @param searchCmd
	 * @return
	 * @throws EgovBizException
	 */
	public List<GwScheduleVO> selectScheduleList(SearchCmd searchCmd) throws EgovBizException;





}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *
 */