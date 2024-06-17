package pierp.app.mis.bizMH.common.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.mis.common.table.model.TbMhApntKey;
import pierp.app.mis.common.table.model.TbMhDtBstrAplVO;
import pierp.app.mis.common.table.model.TbMhOfryAplVO;
import pierp.app.mis.common.table.model.TbMhRptmAplVO;
import pierp.app.mis.common.table.model.TbMhRtmnAplVO;
import pierp.app.mis.common.table.model.TbMhTrxpAplVO;
import pierp.app.mis.common.table.model.TbMhVactAplVO;



/**
 * 클래스
 * file name : MHLockTableService.java
 *
 * Table row에 block을 겁니다.
 *
 * @author 공통팀 dev.vmfhrmfoaj
 * @since 2012. 9. 21.
 * @version 1.0
 *
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 * 수정일                    수정자       수정내용
 * -------------- -------- ---------------------------
 * 2012. 9. 21.   dev.vmfhrmfoaj     최초 생성
 *
 * </pre>
 */
public interface MHLockTableService {

	/**
	 * <pre>
	 * 조직개편 : TB_MH_FWP
	 * @throws EgovBizException
	 * 		미확정상태(10), 배치처리여부 대기중(10)이 아닐경우
	 * </pre>
	 */
	void rejectTbMhDeptChg( Long chgSrno ) throws EgovBizException;
	void rejectTbMhDeptChg( Long chgSrno, String dfnYn ) throws EgovBizException;


	/**
	 * <pre>
	 * 선택근무 신청 : TB_MH_FWP
	 * @throws Throwable
	 * 		데이터의 결재상태가 기안전(1), 반려(4) 가 아닐경우
	 * </pre>
	 */
//	void rejectTbMhFwpChoice( TbMhFwpVO vo ) throws Throwable;



	/**
	 * <pre>
	 * 단시간근무 신청 : TB_MH_FWP
	 * @throws Throwable
	 * 		데이터의 결재상태가 기안전(1), 반려(4) 가 아닐경우
	 * </pre>
	 */
//	void rejectTbMhFwpPart( TbMhFwpVO vo ) throws Throwable;



	/**
	 * <pre>
	 * 조퇴/외출 신청 : TB_MH_OFRY_APL
	 * @throws Throwable
	 * 		데이터의 신청상태가 작성(01), 신청(02),반려(90) 가 아닐경우
	 * </pre>
	 */
	void rejectTbMhOfryApl( TbMhOfryAplVO vo ) throws EgovBizException;



	/**
	 * <pre>
	 * 휴가 신청 : TB_MH_VACT_APL
	 * @throws Throwable
	 * 		데이터의 결재상태가 기안전(1), 반려(4) 가 아닐경우
	 * </pre>
	 */
	void rejectTbMhVactApl( TbMhVactAplVO vo ) throws EgovBizException;



	/**
	 * <pre>
	 * 시간외근무 신청 : TB_MH_OV_WRK_APL
	 * @throws Throwable
	 * 		데이터의 신청상태가 작성(01), 신청(02),반려(90) 가 아닐경우
	 * </pre>
	 */
//	void rejectTbMhOvWrkApl( TbMhOvWrkAplVO vo ) throws Throwable;



	/**
	 * <pre>
	 * 출장 신청 : TB_MH_TRXP_APL
	 * @throws Throwable
	 * 		데이터의 결재상태가 기안전(1), 반려(4) 가 아닐경우
	 * </pre>
	 */
	void rejectTbMhTrxpApl( TbMhTrxpAplVO vo ) throws EgovBizException;

	void rejectTbMhTrxpApl2( TbMhTrxpAplVO vo ) throws EgovBizException;

	/**
	 * <pre>
	 * 출장복명 신청 : TB_MH_RPTM_APL
	 * @throws Throwable
	 * 		데이터의 결재상태가 기안전(1), 반려(4) 가 아닐경우
	 * </pre>
	 */
	void rejectTbMhRptmApl( TbMhRptmAplVO vo ) throws EgovBizException;



	/**
	 * <pre>
	 * 근무지내출장 신청 : TB_MH_DT_BSTR_APL
	 * @throws Throwable
	 * 		데이터의 신청상태가 작성(01), 신청(02),반려(90) 가 아닐경우
	 * </pre>
	 */
	void rejectTbMhDtBstrApl( TbMhDtBstrAplVO vo ) throws EgovBizException;



	/**
	 * <pre>
	 * 인사발령 : TB_MH_RPTM_APL
	 * @throws Throwable
	 * 		데이터의 결재상태가 기안전(1), 반려(4) 가 아닐경우
	 * </pre>
	 */
	void rejectTbMhApnt( TbMhApntKey vo ) throws EgovBizException;
	void rejectTbMhApnt( List<? extends TbMhApntKey> list ) throws Throwable;


	/**
	 * <pre>
	 * 일용직채용등록 : TB_MH_DAYEMP_MST
	 * @throws Throwable
	 * 		데이터의 결재상태가 기안전(1), 반려(4) 가 아닐경우
	 * </pre>
	 */
//	void rejectTbMhDayempMst (TbMhDayempMstVO vo) throws Throwable;


	/**
	 * <pre>
	 * 임시직채용등록 : TB_MH_SHORTEMP_MST
	 * @throws Throwable
	 * 		데이터의 결재상태가 기안전(1), 반려(4) 가 아닐경우
	 * </pre>
	 */
//	void rejectTbMhShortempMst (TbMhShortempMstVO vo) throws Throwable;

//	void appReqRegister (TbMhEduLectMstVO vo) throws Throwable;

//	void appReqRptmAplList(TbMhRptmAplVO fvo)throws Throwable;

	/**
	 * <pre>
	 * 퇴직 신청 : TB_MH_RTMN_APL
	 * @throws Throwable
	 * 		데이터의 결재상태가 기안전(1), 반려(4) 가 아닐경우
	 * </pre>
	 */
	void rejectTbMhRtmnApl( TbMhRtmnAplVO vo ) throws Throwable;

	/* 교육신청 */
//	void lockTbMhEduApl(TbMhEduAplVO vo) throws Throwable;

	/* 교육결과서 */
//	void lockTbMhEduRult(TbMhEduRultVO vo) throws Throwable;



	/**
	 * <pre>
	 * 연속휴가 신청 : TB_MH_SCSV_VACT_APL
	 * @throws Throwable
	 * 		데이터의 결재상태가 기안전(1), 반려(4) 가 아닐경우
	 * </pre>
	 */
//	void rejectTbMhScsvVactApl( TbMhScsvVactAplVO vo ) throws Throwable;

	/* 사회공헌활동계획서 */
//	void lockTbMhSosvPlnApl(TbMhSosvPlnAplVO vo) throws Throwable;

	/* 사회공헌활동결과보고서 */
//	void lockTbMhSosvRsltRept(TbMhSosvRsltReptVO vo) throws Throwable;

}



