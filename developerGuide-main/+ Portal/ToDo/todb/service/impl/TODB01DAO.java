package pierp.app.eip.bizTOD.todb.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.cmd.ParamCmd;
import pierp.app.common.cmd.SearchCmd;
import pierp.app.eip.bizTOD.todb.model.VwBpTodoVO;
import pierp.common.base.dao.BaseAbstractMapper;

/**
 * @Class TODB01DAO
 * @Description
 * TO_DO 나의 할일 DAO 클래스를 정의한다.
 * @author 이정민
 * @since 2022.06.15.
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
public class TODB01DAO extends BaseAbstractMapper {

	public List<VwBpTodoVO> selectTodoList(SearchCmd searchCmd) throws EgovBizException {
		return super.selectList("TODB01.selectTodoList",searchCmd);
	}

	public int selectTodoCnt(SearchCmd searchCmd) throws EgovBizException {
		return super.selectOne("TODB01.selectTodoCnt", searchCmd);
	}

	/**
	 * TO-DO 보류 상태값 변경
	 * @param paramCmd
	 * @return
	 */
	public int updateTodoStat(ParamCmd paramCmd) {
		return update("TODB01.updateTodoStat", paramCmd);
	}

	/**
	 * TO-DO 중요 상태값 변경
	 * @param paramCmd
	 * @return
	 */
	public int updateTodoImpr(ParamCmd paramCmd) {
		return update("TODB01.updateTodoImpr", paramCmd);
	}


}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *
 */