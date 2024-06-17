package pierp.app.mis.bizMH.common.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.mis.bizMH.common.service.MHKeyGenService;
import pierp.common.cmmn.service.BaseAbstractServiceImpl;
import pierp.common.code.service.TableKeyGenService;

@Service
public class MHKeyGenServiceImpl extends BaseAbstractServiceImpl implements MHKeyGenService {

	// 공통
	@Autowired	private TableKeyGenService	tableKeyGenService;

	// 인사
	@Autowired	private MHSrnoDAO			srnoDAO;





	/***************************************************************************
	 * TableKeyGenService 사용
	 ***************************************************************************/

	@Override
	public Long generateBdutSrno() throws Throwable {
		String generatedKey = tableKeyGenService.getNextKey( "BDUT_SRNO", null, null, null );
		return Long.parseLong( generatedKey );
	}

	@Override
	public String generateRewaDisaSrno() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "REWA_DISA_SRNO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"포상징계일련번호 생성",""});
		}
	}

	@Override
	public Long generateFmlySrno2() throws Exception {
		String generatedKey = tableKeyGenService.getNextKey( "FMLY_SRNO", null, null, null );
		return Long.parseLong( generatedKey );
	}




	/***************************************************************************
	 * 각 테이블 MAX + 1 : MHSrnoDAO 사용
	 ***************************************************************************/

	@Override
	public Long generatePicNo() {
		return srnoDAO.generatePicNo();
	}

	/**
	 * generateObstSrno 장애정보(TB_MH_OBST) 일련번호 생성
	 * @return
	 */
	@Override
	public int generateObstSrno(String empUniqNo) throws EgovBizException {
		return srnoDAO.generateObstSrno(empUniqNo);
	}

	/**
	 * generatePsptSrno 여권정보(TB_MH_PSPT_MGNE) 일련번호 생성
	 * @return
	 */
	@Override
	public int generatePsptSrno(String empUniqNo) throws EgovBizException {
		return srnoDAO.generatePsptSrno(empUniqNo);
	}

	/**
	 *  generateVisaSrno 비자정보(TB_MH_VISA_MGNE) 일련번호 생성
	 * @return
	 */
	@Override
	public int generateVisaSrno(String empUniqNo) throws EgovBizException {
		return srnoDAO.generateVisaSrno(empUniqNo);
	}

	/**
	 *  generateLangSrno 어학정보(TB_MH_LANG_MGNE) 일련번호 생성
	 * @return
	 */
	@Override
	public int generateLangSrno(String empUniqNo) throws EgovBizException {
		return srnoDAO.generateLangSrno(empUniqNo);
	}

	/**
	 *  generateCrtiSrno 자격정보(TB_MH_CRTI_MATT) 일련번호 생성
	 * @return
	 */
	@Override
	public int generateCrtiSrno(String empUniqNo) throws EgovBizException {
		return srnoDAO.generateCrtiSrno(empUniqNo);
	}

	/**
	 *  generatePsbjSrno 특이사항(TB_MH_PSBJ_MGNE) 일련번호 생성
	 * @return
	 */
	@Override
	public int generatePsbjSrno(String empUniqNo) throws EgovBizException {
		return srnoDAO.generatePsbjSrno(empUniqNo);
	}

	/**
	 *  generateFmlySrno 가족사항(TB_MH_FMLY) 일련번호 생성
	 * @return
	 */
	@Override
	public Long generateFmlySrno(String empUniqNo) throws EgovBizException {
//		return srnoDAO.generateFmlySrno(empUniqNo);
		String generatedKey = "";
		try {
			generatedKey = tableKeyGenService.getNextKey( "FMLY_SRNO", null, null, null );
		} catch (Exception e) {
			throw processException("일련번호 생성");

		}
		return Long.parseLong( generatedKey );
	}

	/**
	 *  generateScreSrno 학력사항(TB_MH_SCRE) 일련번호 생성
	 * @return
	 */
	@Override
	public Long generateScreSrno(String empUniqNo) throws EgovBizException {
//		return srnoDAO.generateScreSrno(empUniqNo);
		String generatedKey = "";
		try {
			generatedKey = tableKeyGenService.getNextKey( "SCRE_SRNO", null, null, null );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw processException("fail.common.runError",new String[]{"학력사항 일련번호 생성",""});
		}
		return Long.parseLong( generatedKey );

	}

	@Override
	public Long generateDeptHistSrno() {
		return srnoDAO.generateDeptHistSrno();
	}

	@Override
	public Long generateDeptChgSrno() {
		return srnoDAO.generateDeptChgSrno();
	}

	@Override
	public Long generateChgPicNo() {
		return srnoDAO.generateChgPicNo();
	}

	@Override
	public Long generateCarrSrno(String empUniqNo) throws EgovBizException {
		return srnoDAO.generateCarrSrno(empUniqNo);
	}

	@Override
	public String generateApntSrno(String empUniqNo) {
		return srnoDAO.generateApntSrno(empUniqNo);
	}

	@Override
	public String generateHopeDeptMngeNo(String trn) throws EgovBizException{
		String generatedKey = "";
		try {
			generatedKey = tableKeyGenService.getNextKey( "HOPE_DEPT_MNGE_NO", null, null, trn );
		} catch (Exception e) {
			throw processException("일련번호 생성");

		}
		return generatedKey;
	}

	/**
	 * generateCmutMediAplNo 출퇴근시간조정신청내역(TB_MH_ATTE_RECMED) 일련번호 생성
	 * @return
	 */
	@Override
	public String generateCmutMediAplNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "CMUT_MEDI_APL_NO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"출퇴근시간조정신청 일련번호 생성",""});
		}
	}

	/**
	 * generateCtomAplNo 상장신청번호(TB_MH_CTOM_APL.CTOM_APL_NO) 일련번호 생성
	 */
	@Override
	public String generateCtomAplNo()  throws EgovBizException  {
		try {
			return tableKeyGenService.getNextKey( "CTOM_APL_NO", null, null, null);
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"상장신청 일련번호 생성",""});
		}
	}

	/**
	 * generateIsuNo 상장발급번호(TB_MH_CTOM_APL.ISU_NO) 일련번호 생성
	 */
	@Override
	public String generateIsuNo(String cdtnNm) throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "ISU_NO", null, null, cdtnNm );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"상장발급 일련번호 생성",""});
		}
	}

	@Override
	public Long generateTrxpHlstSrno() throws EgovBizException {
		return srnoDAO.generateTrxpHlstSrno();
	}

	/**
	 * generateIdtfAplNo 신분증발급신청번호(TB_MH_IDTF_APL.IDTF_APL_NO) 생성
	 */
	@Override
	public String generateIdtfAplNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "IDTF_APL_NO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"신분증발급신청 일련번호 생성",""});
		}
	}

	/**
	 * generatePbncNo 공고번호(TB_MH_RCMT_PBNC.PBNC_NO) 생성
	 */
	@Override
	public String generatePbncNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "PBNC_NO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"채용공고 일련번호 생성",""});
		}
	}
	/**
	 * generateLvbcAplNo 휴직신청번호(TB_MH_LVBC_APL.LVBC_APL_NO) 일련번호 생성
	 */
	@Override
	public String generateLvbcAplNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "LVBC_APL_NO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"휴직신청 일련번호 생성",""});
		}
	}

	/**
	 * generateRnmtAplNo 복직신청번호(TB_MH_RNMT_APL.RNMT_APL_NO) 일련번호 생성
	 */
	@Override
	public String generateRnmtAplNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "RNMT_APL_NO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"복직신청 일련번호 생성",""});
		}
	}

	/**
	 * generateAccnSrno TB_MY_PYMT_ACCN(지급계좌)
	 * @return
	 */
	@Override
	public int generateAccnSrno( String empUniqNo ) {
		return srnoDAO.generateAccnSrno( empUniqNo );
	}

	/**
	 * generateAccnChgSrno 급여계좌관리 자료일련번호 (TB_MY_PYMT_ACCN_HIST.ACCN_CHG_SRNO)생성
	 * @return
	 */
	@Override
	public String generateAccnChgSrno() throws Exception {
		return tableKeyGenService.getNextKey( "ACCN_CHG_SRNO", null, null, null );
	}

	/**
	 * generateAccnPyobjtHistSrno 계좌별지급항목이력 일련번호 생성
	 * @return
	 * @throws Exception
	 */
	@Override
	public Long generateAccnPyobjtHistSrno() throws Exception{
		return srnoDAO.generateAccnPyobjtHistSrno();
	}

	@Override
	public String generateDtBstrAplNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "DT_APL_NO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"근무지내출장신청서 일련번호 생성",""});
		}
	}


	@Override
	public String generateBstrAplNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "BSTR_APL_NO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"근무지외출장신청서 일련번호 생성",""});
		}
	}


	@Override
	public String generateBstrMltiMttrAplNo() {
		return srnoDAO.generateBstrMltiMttrAplNo();
	}


//	@Override
//	public Integer generateEtcSalDtlsSrno( TbMyEtcSalDtlsVO vo ) {
//		return srnoDAO.generateEtcSalDtlsSrno( vo );
//	}


	/**
	 * generateExmNo 수험번호(TB_MH_RCTM_BASE_RESULT.EXM_NO) 생성
	 */
	@Override
	public String generateExmNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "EXM_NO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"수험번호 생성",""});
		}
	}

	/**
	 * generateSrno 채용 평가자 정보관리 일련번호(TB_MH_RCMT_INTV_NEW.SRNO) 생성
	 */
	@Override
	public String generateSrno() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "SRNO", null, null, null);
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"채용 평가자 정보관리 일련번호 생성",""});
		}
	}

	/**
	 * generateRtmnCfmStndSeq 채용 평가자 정보관리 일련번호(TB_MH_RTMN_CFM_STND.SEQ) 생성
	 */
	@Override
	public Long generateRtmnCfmStndSeq() throws EgovBizException {
		return srnoDAO.generateRtmnCfmStndSeq();
	}

	/**
	 * generateSceyMngeNo 동호회 기준정보 일련번호(TB_MH_RTMN_CFM_STND.SEQ) 생성
	 */
	@Override
	public String generateSceyMngeNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "SCEY_MNGE_NO", null, null, null);
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"동호회 기준정보 일련번호 생성",""});
		}
	}



	@Override
	public String generateRsolRqetNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "RSOL_RQET_NO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"결의요청번호 생성",""});
		}
	}

	@Override
	public Long generateEveyChgSrno() throws EgovBizException {
		String generatedKey = "";
		try {
			generatedKey = this.tableKeyGenService.getNextKey("EVYE_CHG_SRNO", null, null, null); }
	    catch (Exception e)
	    {
	      throw processException("fail.common.runError", new String[] { "연차이력 일련번호 생성", "" });
	    }
	    return Long.valueOf(Long.parseLong(generatedKey));
	}

	@Override
	public String generateEpdtMngeNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "EPDT_MNGE_NO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"연차촉진관리번호 생성",""});
		}
	}

	@Override
	public String generateRptmAplNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "RPTM_APL_NO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"복명신청번호 생성",""});
		}
	}

	/**
	 * generateEduMngeNo 교육과정관리번호(TB_MH_EDU_PROS_MNGE.EDU_MNGE_NO) 생성
	 */
	@Override
	public String generateEduMngeNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "EDU_MNGE_NO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"교육과정관리번호 생성",""});
		}
	}

	@Override
	public String generateOdbyUsePlnNo(String aplDt) throws EgovBizException {
		return srnoDAO.generateOdbyUsePlnNo(aplDt);
	}

	@Override
	public int generateOdbyUseDtlSeq(String obdyUsePlnNo) throws EgovBizException {
		return srnoDAO.generateOdbyUseDtlSeq(obdyUsePlnNo);
	}

	@Override
	public String generateVactAplNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "VACT_APL_NO", null, null, null ) + "0000";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw processException("fail.common.runError",new String[]{"휴가외출관리번호 생성",""});
		}
	}

	/**
	 * generateTklcAplNo 교육과정 수강신청(TB_MH_EDU_TKLC_APL.TKLC_APL_NO) 수강신청번호 생성
	 */
	@Override
	public String generateTklcAplNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "TKLC_APL_NO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"교육과정 수강신청 생성",""});
		}
	}

	/**
	 * generateOtTklcAplNo 교육과정 수강신청(TB_MH_EDU_OT_TKLC_APL.OT_TKLC_APL_NO) 외부교육결과신청번호 생성
	 */
	@Override
	public String generateOtTklcAplNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "OT_TKLC_APL_NO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"외부교육결과신청번호 생성",""});
		}
	}

	/**
	 * generateFwpAplNo 유연근무신청(TB_MH_EDU_OT_TKLC_APL.OT_TKLC_APL_NO) 외부교육결과신청번호 생성
	 */
	@Override
	public String generateFwpAplNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "FWP_APL_NO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"유연근무신청번호 생성",""});
		}
	}

	/**
	 * generateBookAplNo 교육독서 통신신청(PIERP_KISED_ERP.TB_MH_EDU_BOOK_APL) 독서통신신청번호 생성
	 */
	@Override
	public String generateBookAplNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "BOOK_APL_NO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"독서통신신청번호 생성",""});
		}
	}

		@Override
	public String generateDawkAplNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "DAWK_APL_NO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"일용직 신청번호 생성",""});
		}
	}

	@Override
	public String generateOvtmWrkAplNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "OVTM_WRK_APL_NO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"시간외근무 신청번호",""});
		}
	}

	/**
	 * generateBookRsltAplNo 교육독서통신결과신청(TB_MH_EDU_BOOK_RSLT_APL.BOOK_RSLT_APL_NO) 독서통신결과신청번호 생성
	 */
	@Override
	public String generateBookRsltAplNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "BOOK_RSLT_APL_NO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"일용직 신청번호 생성",""});
		}
	}

	@Override
	public String generaRsolRqetNoDayemp(String bzplCd, String pymdDt) throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "RSOL_RQET_NO_DAYEMP", bzplCd, pymdDt, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"일용직 급여 결의 생성 요청번호",""});
		}
	}

	@Override
	public String generateElpdLvffcAplNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "ELPD_LVFFC_APL_NO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"조기퇴근 신청번호 생성",""});
		}
	}

	/**
	 * generateCertiAplSrno 증명서발급신청번호 일련번호(TB_MH_CERTI_APL.APL_SRNO) 생성
	 * @return
	 */
	@Override
	public String generateCertiAplSrno() throws EgovBizException {
		try {
			return srnoDAO.generateCertiAplSrno();
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"증명서발급신청번호 일련번호 생성",""});
		}
	}

	/**
	 * generateCertiSrno 증명서내역관리 일련번호(TB_MH_CERTI.SRNO) 생성
	 * @param empUniqNo
	 * @return
	 * @throws EgovBizException
	 */
	@Override
	public String generateCertiSrno(String empUniqNo) throws EgovBizException {
		try {
			return srnoDAO.generateCertiSrno(empUniqNo);
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"증명서내역관리 일련번호 생성",""});
		}
	}

	/**
	 * generateEduCmplBondSrno 교육수료증발급번호 일련번호(TB_MH_EDU_CMPL_BOND.SRNO) 생성
	 */
	@Override
	public String generateEduCmplBondSrno(String eduMngeNo) throws EgovBizException {
		try {
			return srnoDAO.generateEduCmplBondSrno(eduMngeNo);
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"교육관리 수료증 일련번호 생성",""});
		}
	}

	/**
	 * generateEduCmplBondIsuNo 수료증발급번호(TB_MH_EDU_CMPL_BOND.ISU_NO) 생성
	 */
	@Override
	public String generateEduCmplBondIsuNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "EDU_CMPL_BOND_ISU_NO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"수료증 발급번호",""});
		}
	}



	@Override
	public String generateFdbkDealNo() throws EgovBizException {
		try {
			return tableKeyGenService.getNextKey( "FDBK_DEAL_NO", null, null, null );
		} catch (Exception e) {
			throw processException("fail.common.runError",new String[]{"소통환류 환류처리번호 생성",""});
		}
	}
}