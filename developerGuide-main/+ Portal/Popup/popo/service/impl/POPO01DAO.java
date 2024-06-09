package pierp.app.mgt.bizPOP.popo.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.cmd.ParamCmd;
import pierp.app.common.cmd.SearchCmd;
import pierp.app.common.model.table.TbBpPupVO;
import pierp.app.mgt.bizPOP.popo.model.PopVO;
import pierp.common.base.dao.BaseAbstractMapper;

/**
 * @Class POPO01DAO
 * @Description
 * 팝업 관리 DAO 클래스를 정의한다.
 * @author 이정민
 * @since 2022.05.24.
 * @version
 *
/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *  2022.05.24.   이정민		최초 작성
 *
 */

@Repository
public class POPO01DAO extends BaseAbstractMapper {

	/**
	 * 팝업 목록 조회
	 */
	public List<TbBpPupVO> selectPupList(SearchCmd searchCmd) throws EgovBizException {
		return super.selectList("POPO01.selectPupList",searchCmd);
	}


	/**
	 * 팝업 목록 건수 조회
	 */
	public int selectPupCnt(SearchCmd searchCmd) throws EgovBizException {
		return super.selectOne("POPO01.selectPupCnt", searchCmd);
	}


	/**
	 * 팝업 목록 단건 조회
	 */
	public TbBpPupVO selectPupOne(SearchCmd searchCmd) throws EgovBizException {
		return super.selectOne("POPO01.selectPupOne", searchCmd);
	}


	/**
	 * 팝업 데이터 삭제
	 */
	public int deletePup(ParamCmd paramCmd) throws EgovBizException{
		return super.delete("POPO01.deletePup", paramCmd);
	}


	/**
	 * 팝업 데이터 생성
	 */
	public int insertPup(ParamCmd paramCmd) throws EgovBizException{
		return super.update("POPO01.insertPup", paramCmd);
	}


	/**
	 * 팝업 데이터 수정
	 */
	public int updatePup(ParamCmd paramCmd) throws EgovBizException{
		return super.update("POPO01.updatePup", paramCmd);
	}


	/**
	 * 팝업 단건 조회 + 첨부파일 조회 (팝업 미리보기)
	 */
	public PopVO selectPupOneAhfl(SearchCmd searchCmd) throws EgovBizException {
		return super.selectOne("POPO01.selectPupOneAhfl", searchCmd);
	}
}

