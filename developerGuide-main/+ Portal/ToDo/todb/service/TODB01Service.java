package pierp.app.eip.bizTOD.todb.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.cmd.SearchCmd;
import pierp.app.eip.bizTOD.todb.model.BpTodoForm;
import pierp.app.eip.bizTOD.todb.model.VwBpTodoVO;

/**
 * @Class TODB01Service
 * @Description
 * 	TO_DO 나의 할일 서비스를 정의한다.
 * @author 이정민
 * @since 2022.06.15.
 * @version
 *
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 * 2022.06.15.    이정민     최초 생성
 *
 */
public interface TODB01Service {


	/**
	 * TO_DO 나의 할일 목록  조회
	 * @param searchCmd
	 * @return
	 * @throws EgovBizException
	 */
	public List<VwBpTodoVO> selectTodoList(SearchCmd searchCmd) throws EgovBizException;


	/**
	 * TO_DO 나의 할일 목록 카운트 조회
	 * @param searchCmd
	 * @return
	 * @throws EgovBizException
	 */

	public int selectTodoCnt(SearchCmd searchCmd) throws EgovBizException;


	/**
	 * TO_DO 보류 상태값 변경
	 * @param paramCmd
	 * @return
	 * @throws EgovBizException
	 */
	public int updateTodoStat(BpTodoForm bpTodoForm) throws EgovBizException;


	/**
	 * TO_DO 중요 상태 변경
	 * @param paramCmd
	 * @return
	 * @throws EgovBizException
	 */
	public int updateTodoImpr(BpTodoForm bpTodoForm) throws EgovBizException;




}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *
 */