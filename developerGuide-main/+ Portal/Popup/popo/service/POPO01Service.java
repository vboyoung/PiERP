package pierp.app.mgt.bizPOP.popo.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.cmd.ParamCmd;
import pierp.app.common.cmd.SearchCmd;
import pierp.app.common.model.table.TbBpPupVO;
import pierp.app.mgt.bizPOP.popo.model.PopForm;
import pierp.app.mgt.bizPOP.popo.model.PopVO;
import pierp.module.fileAttach.service.FileParamVO;

/**
 * @Class POPO01Service
 * @Description
 * 	팝업 관리 서비스를 정의한다.
 * @author 이정민
 * @since 2022.05.24.
 * @version
 *
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 * 2022.05.24.   이정민		최초 작성
 *
 */
public interface POPO01Service {


	/**
	 * 팝업 목록  조회
	 * @param searchCmd
	 * @return
	 * @throws EgovBizException
	 */

	public List<TbBpPupVO> selectPupList(SearchCmd searchCmd) throws EgovBizException;


	/**
	 * 팝업 목록 카운트 조회
	 * @param searchCmd
	 * @return
	 * @throws EgovBizException
	 */
	public int selectPupCnt(SearchCmd searchCmd) throws EgovBizException;


	/**
	 * 팝업 상세 조회
	 * @param searchCmd
	 * @return
	 * @throws EgovBizException
	 */
	public TbBpPupVO selectPupOne(SearchCmd searchCmd) throws EgovBizException;


	/**
	 * 팝업 저장
	 * @param popForm
	 * @param uploader
	 * @throws EgovBizException
	 */
	public void savePup(PopForm popForm, FileParamVO uploader) throws EgovBizException;

	/**
	 * 팝업 목록 체크 삭제
	 * @param paramCmd
	 * @return
	 * @throws EgovBizException
	 */
	public void deletePup(ParamCmd paramCmd) throws EgovBizException;

	/**
	 * 상세 조회 + 첨부 콘텐츠 조회
	 * @param searchCmd
	 * @return
	 * @throws EgovBizException
	 */
	public PopVO selectPupOneAhfl(SearchCmd searchCmd) throws EgovBizException;


}

