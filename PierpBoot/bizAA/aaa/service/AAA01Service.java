package pierp.app.mis.bizAA.aaa.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.mis.bizMH.mhk.model.MHK6001FormVO;
import pierp.app.mis.common.table.model.TbMhEvyeEpdtAncVO;
import pierp.app.mis.common.table.model.TbMhEvyeEpdtPlnVO;
import pierp.app.mis.common.table.model.TbMhOdbyUspDtlKey;
import pierp.app.mis.common.table.model.TbMhOdbyUspDtlVO;
import pierp.app.mis.common.table.model.TbMhOdbyUspVO;
import pierp.common.mvc.web.ParamVO;
import pierp.common.util.QueryResultMap;




public interface AAA01Service {
	
	
	
	void selectaa(MHK6001FormVO vo);


	/**
	 * selectEvyeEpdtPlnList 목록 조회 1
	 * @param srchBox
	 * @return 연차 촉진계획 목록
	 */
	List<QueryResultMap> selectEvyeEpdtPlnList(ParamVO srchBox);
	
	

    /**
     * selectEvyeEpdtAncList 목록 조회 2
     * @param mstBox
     * @return 연차 촉진 대상자 목록
     */
	List<QueryResultMap> selectEvyeEpdtAncList(ParamVO mstBox);
	
	
	
	/**
	 * selectEvyeEpdtPln 목록 저장
	 * @param vo
	 * @throws EgovBizException
	 */
	void selectEvyeEpdtPln(List<TbMhEvyeEpdtPlnVO> list) throws EgovBizException;

	

    /**
     * SaveEvyeEpdtAnc list, parmaVO 저장 
     * @param list2
     * @param mstBox
     * @param br
     * @return
     * @throws EgovBizException
     */
	void SaveEvyeEpdtAnc(List<TbMhEvyeEpdtAncVO> list2, ParamVO mstBox) throws EgovBizException;
	
	

	
	
	/**
	 * 삭제
	 * @param vo
	 * @return
	 */
	String deleteOdbyUspInfo(TbMhOdbyUspVO vo) throws EgovBizException;
	
	
	
	
	
	
	
/*************************************************************************************************************************/	
	
	
	
	
	/**
	 * 연차사용계획 목록조회ParamVO
	 * @param vo
	 * @return
	 */
	List<QueryResultMap> selectEvyeSituList(ParamVO vo);
	
	/**
	 * 연차사용계획 마스터단건조회
	 * @param vo
	 * @return
	 */
	QueryResultMap getOdbyUspInfo(TbMhOdbyUspVO vo);
	
	/**
	 * 연차사용계획 저장
	 * @param vo
	 * @return
	 */
	String saveOdbyUspInfo(TbMhOdbyUspVO vo) throws EgovBizException;

	/**
	 * 연차사용계획 세부계획조회
	 * @param vo
	 * @return
	 */
	List<QueryResultMap> selectOdbyUspList(TbMhOdbyUspVO vo);
	

	
	/**
	 * calcOdbyUspDys 계획일수가져오기
	 * @param vo
	 * @return
	 */
	Double calcOdbyUspDys(ParamVO vo) throws EgovBizException;

	/**
	 * saveOdbyUspDtlList
	 * 세부계획정보 저장
	 *
	 */
	String saveOdbyUspDtlList(List<TbMhOdbyUspDtlVO> dtlList) throws EgovBizException;

	/**
	 * deleteOdbyUspDtlList
	 * 세부계획정보 삭제
	 *
	 */
	String deleteOdbyUspDtlList(List<TbMhOdbyUspDtlKey> dtlList) throws EgovBizException;

	/**
	 * 연차사용계획 승인요청
	 * @param vo
	 * @return
	 */
	String approvalReqOdbyUsp(TbMhOdbyUspVO mstBox) throws EgovBizException;

	/**
	 * approvalOdbyUsp 연차사용계획 승인
	 * @param list
	 * @param br
	 * @return
	 * @throws EgovBizException
	 */
	void approvalOdbyUsp(TbMhOdbyUspVO list) throws EgovBizException;

}


