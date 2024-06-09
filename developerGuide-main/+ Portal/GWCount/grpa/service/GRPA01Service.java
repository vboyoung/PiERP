package pierp.app.eip.bizGRP.grpa.service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.cmd.SearchCmd;
import pierp.app.eip.bizGRP.grpa.model.GwRetCntVO;

/**
 * @Class GRPA01Service
 * @Description
 * 메인 전자결재 서비스를 정의한다.
 * @author 정성희
 * @since 2022.06.22.
 * @version
 *
 */
public interface GRPA01Service {

	/**
	 * 메인 전자결재 카드 영역 건수 조회
	 * @param searchCmd
	 * @return
	 * @throws EgovBizException
	 */
	public GwRetCntVO selectGwCnt(SearchCmd searchCmd) throws EgovBizException;


}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *
 */