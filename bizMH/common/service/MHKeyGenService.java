package pierp.app.mis.bizMH.common.service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * 인사업무의 Key Generate 서비스입니다.
 * <p>
 * <b>NOTE</b>:
 * 공통 KeyGenerator를 wrap하고있으며,
 * 추가적으로 인사업무에서 사용중인 키생성 로직을 포함합니다.
 *
 * @author
 * @since 2022.--.--
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2022.05.26  vmfhrmfoaj          최초 생성
 *
 * </pre>
 */
public interface MHKeyGenService {


	Long generatePicNo();

	/**
	 * generateObstSrno 장애정보(TB_MH_OBST) 일련번호 생성
	 * @return
	 */
	int generateObstSrno(String empUniqNo) throws EgovBizException;

	/**
	 * generatBdutSrno 직무사항일련번호 (TB_MH_BUDT)생성
	 * @return
	 */
	Long generateBdutSrno() throws Throwable;

	/**
	 * generateRewaDisaSrno 포상정보(TB_MH_REWA_DISA) 일련번호 생성
	 * @return
	 */
	String generateRewaDisaSrno() throws EgovBizException;

	/**
	 * generatePsptSrno 여권정보(TB_MH_PSPT_MGNE) 일련번호 생성
	 * @return
	 */
	int generatePsptSrno(String empUniqNo) throws EgovBizException;

	/**
	 *  generateVisaSrno 비자정보(TB_MH_VISA_MGNE) 일련번호 생성
	 * @return
	 */
	int generateVisaSrno(String empUniqNo) throws EgovBizException;

	/**
	 *  generateLangSrno 어학정보(TB_MH_LANG_MGNE) 일련번호 생성
	 * @return
	 */
	int generateLangSrno(String empUniqNo) throws EgovBizException;

	/**
	 *  generateCrtiSrno 자격정보(TB_MH_CRTI_MATT) 일련번호 생성
	 * @return
	 */
	int generateCrtiSrno(String empUniqNo) throws EgovBizException;

	/**
	 *  generatePsbjSrno 특이사항(TB_MH_PSBJ_MGNE) 일련번호 생성
	 * @return
	 */
	int generatePsbjSrno(String empUniqNo) throws EgovBizException;

	/**
	 * generateFmlySrno 가족사항 일련번호 (TB_MH_FMLY) 생성
	 * @return
	 */
	Long generateFmlySrno2() throws Exception;

	/**
	 *  generateFmlySrno 가족사항(TB_MH_FMLY) 일련번호 생성
	 * @return
	 */
	Long generateFmlySrno(String empUniqNo) throws EgovBizException;

	/**
	 *  generateScreSrno 학력사항(TB_MH_SCRE) 일련번호 생성
	 * @return
	 */
	Long generateScreSrno(String empUniqNo) throws EgovBizException;

	/**
	 * generateDeptHistSrno 부서이력 일련번호 생성(TB_MH_DEPT_HIS)
	 * @return
	 */
	Long generateDeptHistSrno();

	/**
	 * generateDeptChgSrno 부서변경 일련번호 생성(TB_MH_DEPT_CHG)
	 * @return
	 */
	Long generateDeptChgSrno();

	/**
	 * generateChgPicNo 사진 변경 신청 일련번호 생성(TB_MH_EMP_PIC_CHG_APL)
	 * @return
	 */
	Long generateChgPicNo();

	/**
	 * generateCarrSrno 경력사항일련번호 (TB_MH_CARR)생성
	 * @return
	 */
	Long generateCarrSrno(String empUniqNo) throws EgovBizException;

	/**
	 * generateApntSrno 발령일련번호(TB_MH_APNT.APNT_SRNO)생성
	 * @param empUniqNo
	 * @return
	 */
	String generateApntSrno( String empUniqNo );

	/**
	 * generateHopeDeptMngeNo 희망부서관리번호(TB_MH_HOPE_DEPT_STND.HOPE_DEPT_MNGE_NO)생성
	 * @param
	 * @return
	 */
	String generateHopeDeptMngeNo(String trn) throws EgovBizException;

	/**
	 * generateCmutMediAplNo 출퇴근시간조정신청내역(TB_MH_ATTE_RECMED) 일련번호 생성
	 * @return
	 */
	String generateCmutMediAplNo() throws EgovBizException;

	/**
	 * generateCtomAplNo 상장신청번호(TB_MH_CTOM_APL.CTOM_APL_NO) 일련번호 생성
	 * @return
	 */
	String generateCtomAplNo() throws EgovBizException;

	/**
	 * generateIsuNo 상장발급번호(TB_MH_CTOM_APL.ISU_NO) 일련번호 생성
	 * @param cdtnNm
	 * @return
	 * @throws EgovBizException
	 */
	String generateIsuNo(String cdtnNm) throws EgovBizException;

	/**
	 * 국내출장 즐겨찾기 (TB_MH_TRXP_HLST.SRNO) 생성
	 * @param srno
	 * @return
	 * @throws EgovBizException
	 */
	Long generateTrxpHlstSrno() throws EgovBizException;

	/**
	 * generateIdtfAplNo 신분증발급신청번호(TB_MH_IDTF_APL.IDTF_APL_NO) 생성
	 * @return
	 * @throws EgovBizException
	 */
	String generateIdtfAplNo() throws EgovBizException;

	/**
	 * generatePbncNo 공고번호(TB_MH_RCMT_PBNC.PBNC_NO) 생성
	 * @return
	 * @throws EgovBizException
	 */
	String generatePbncNo() throws EgovBizException;

	/**
	 * generateLvbcAplNo 휴직신청번호(TB_MH_LVBC_APL.LVBC_APL_NO)
	 * @return
	 * @throws EgovBizException
	 */
	String generateLvbcAplNo() throws EgovBizException;

	/**
	 * generateRnmtAplNo 복직신청번호(TB_MH_RNMT_APL.RNMT_APL_NO)
	 * @return
	 * @throws EgovBizException
	 */
	String generateRnmtAplNo() throws EgovBizException;

	/**
	 * generateAccnSrno TB_MY_PYMT_ACCN(지급계좌)
	 * @return
	 */
	int generateAccnSrno( String empUniqNo );

	/**
	 * generateAccnChgSrno 급여계좌관리 자료일련번호 (TB_MY_PYMT_ACCN_HIST.ACCN_CHG_SRNO)생성
	 * @return
	 */
	String generateAccnChgSrno() throws Exception;

	/**
	 * generateAccnPyobjtHistSrno 계좌별지급항목이력 일련번호 생성
	 * @return
	 * @throws Exception
	 */
	Long generateAccnPyobjtHistSrno() throws Exception;


	/**
	 * generateDtBstrAplNo 근무지내출장신청서 신청일련번호 생성( TB_MH_DT_BSTR_APL.APL_NO )
	 * @return
	 * @throws Exception
	 */
	String generateDtBstrAplNo() throws EgovBizException;


	/**
	 * generateBstrAplNo 근무지외출장신청서 신청번호 (TB_MH_TRXP_APL) 생성
	 *
	 * @return
	 */
	String generateBstrAplNo() throws EgovBizException;


	/**
	 * generateBstrMltiMttrAplNo 출장다건신청번호(TB_MH_DT_BSTR_APL)
	 * @return
	 */
	String generateBstrMltiMttrAplNo();


	/**
	 * generateBstrAplNo 근무지외출장신청서 신청번호 (TB_MH_TRXP_APL) 생성
	 *
	 * @return
	 */
//	Integer generateEtcSalDtlsSrno( TbMyEtcSalDtlsVO vo ) throws EgovBizException;

	/**
	 * generateExmNo 수험번호(TB_MH_RCTM_BASE_RESULT.EXM_NO) 생성
	 * @return String
	 * @throws EgovBizException
	 */
	String generateExmNo() throws EgovBizException;

	/**
	 * generateSrno 채용 평가자 정보관리 일련번호(TB_MH_RCMT_INTV_NEW.SRNO) 생성
	 * @return String
	 * @throws EgovBizException
	 */
	String generateSrno() throws EgovBizException;

	/**
	 * generateRtmnCfmStndSeq 채용 평가자 정보관리 일련번호(TB_MH_RTMN_CFM_STND.SEQ) 생성
	 * @return Long
	 * @throws EgovBizException
	 */
	Long generateRtmnCfmStndSeq() throws EgovBizException;

	/**
	 * generateSrno 동호회 기준정보 일련번호(TB_MH_SCEY.SCEY_MNGE_NO) 생성
	 * @return String
	 * @throws EgovBizException
	 */
	String generateSceyMngeNo() throws EgovBizException;


	/**
	 * generateRsolRqetNo 결의요청번호 생성
	 * @return
	 */
	String generateRsolRqetNo() throws EgovBizException;

	/**
	 * generateEveyChgSrno 연차변경이력 일련번호(EVYE_CHG_SRNO) 생성
	 * @return
	 * @throws Exception
	 */
	Long generateEveyChgSrno() throws EgovBizException;

	/**
	 * generateEpdtMngeNo 연차촉진관리번호(EPDT_MNGE_NO) 생성
	 * @return 연차촉진관리번호
	 * @throws EgovBizException
	 */
	String generateEpdtMngeNo() throws EgovBizException;


	/**
	 * generateRptmAplNo 복명신청번호생성(TB_MH_RPTM_APL)
	 * @return
	 */
	String generateRptmAplNo() throws EgovBizException;

	/**
	 * generateEduMngeNo 교육과정관리번호(TB_MH_EDU_PROS_MNGE.EDU_MNGE_NO) 생성
	 * @return String
	 * @throws EgovBizExceitopn
	 */
	String generateEduMngeNo() throws EgovBizException;

	/**
	 * generateOdbyUsePlnNo 연차사용계획등록(TB_MH_ODBY_USP) 연차사용계획번호 생성
	 *
	 * @return
	 */
	String generateOdbyUsePlnNo(String aplDt) throws EgovBizException;

	/**
	 * generateOdbyUseDtlSeq 연차사용계획세부정보등록(TB_MH_ODBY_USP_DTL) 일련번호 생성
	 *
	 * @return
	 */
	int generateOdbyUseDtlSeq(String obdyUsePlnNo) throws EgovBizException;

	/**
	 * generateVactAplNo 휴가신청번호 (TB_MH_VACT_APL)생성
	 * @return
	 */
	String generateVactAplNo() throws EgovBizException;

	/**
	 * generateTklcAplNo 교육과정 수강신청(TB_MH_EDU_TKLC_APL.TKLC_APL_NO) 수강신청번호 생성
	 * @return
	 * @throws EgovBizException
	 */
	String generateTklcAplNo() throws EgovBizException;

	/**
	 * generateOtTklcAplNo 외부교육 수강결과신청(TB_MH_EDU_OT_TKLC_APL.OT_TKLC_APL_NO) 외부교육결과신청번호 생성
	 * @return
	 * @throws EgovBizException
	 */
	String generateOtTklcAplNo() throws EgovBizException;

	 /**
	 * generateFwpAplNo 유연근무신청(TB_MH_FWP_NEW.FWP_APL_NO) 유연근무신청 번호
	 * @return
	 * @throws EgovBizException
	 */
	String generateFwpAplNo() throws EgovBizException;

	/**
	 * generateBookAplNo 교육독서 통신신청(PIERP_KISED_ERP.TB_MH_EDU_BOOK_APL) 독서통신신청번호 생성
	 * @return
	 * @throws EgovBizException
	 */
	String generateBookAplNo() throws EgovBizException;

	/**
	 * generateDawkAplNo 일용직 채용 (TB_MH_DAYEMP_MST) 일용직신청번호 생성
	 * @return
	 * @throws EgovBizException
	 */
	String generateDawkAplNo() throws EgovBizException;

	/**
	 * generateOvtmWrkAplNo 시간외근무 신청번호 (TB_MH_OV_WRK_APL.OVTM_WRK_APL_NO)생성
	 * @return
	 */
	String generateOvtmWrkAplNo() throws EgovBizException;

	/**
	 * generateBookRsltAplNo 교육독서통신결과신청(TB_MH_EDU_BOOK_RSLT_APL.BOOK_RSLT_APL_NO) 독서통신결과신청번호 생성
	 */
	String generateBookRsltAplNo() throws EgovBizException;

	/**
	 * generaRsolRqetNoDayemp 일용직급여결의생성요청번호(RSOL_RQET_NO_DAYEMP) 생성
	 * RSOL_RQET_NO_DAYEMP
	 */
	String generaRsolRqetNoDayemp(String bzplCd, String pymdDt) throws EgovBizException;

	/**
	 * generateElpdLvffcAplNo 조기퇴근 신청번호 (TB_MH_ELPD_LVFFC_APL.ELPD_LVFFC_APL_NO)생성
	 * @return
	 */
	String generateElpdLvffcAplNo() throws EgovBizException;

	/**
	 * generateCertiAplSrno 증명서발급신청번호 일련번호(TB_MH_CERTI_APL.APL_SRNO) 생성
	 * @return
	 */
	String generateCertiAplSrno() throws EgovBizException;

	/**
	 * generateCertiSrno 증명서내역관리 일련번호(TB_MH_CERTI.SRNO) 생성
	 * @param empUniqNo
	 * @return
	 * @throws EgovBizException
	 */
	String generateCertiSrno(String empUniqNo) throws EgovBizException;

	/**
	 * generateEduCmplBondSrno 교육수료증발급번호 일련번호(TB_MH_EDU_CMPL_BOND.SRNO) 생성
	 * @param eduMngeNo
	 * @return
	 * @throws EgovBizException
	 */
	String generateEduCmplBondSrno(String eduMngeNo) throws EgovBizException;

	/**
	 * generateEduCmplBondIsuNo 수료증발급번호(TB_MH_EDU_CMPL_BOND.ISU_NO) 생성
	 * @return String
	 * @throws EgovBizException
	 */
	String generateEduCmplBondIsuNo() throws EgovBizException;


	/**
	 * generateFdbkDealNo 소통환류 환류처리번호(TB_CMF_FDBK_MST.FDBK_DEAL_NO) 생성
	 * @return TB_CMF_FDBK_MST.FDBK_DEAL_NO
	 * @throws EgovBizException
	 */
	String generateFdbkDealNo() throws EgovBizException;



}



/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *
 */