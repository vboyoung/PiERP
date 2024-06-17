package pierp.app.mis.bizMH.common.service.impl;


import org.springframework.stereotype.Repository;

import pierp.common.base.dao.BaseAbstractMapper;

@Repository
public class MHSrnoDAO extends BaseAbstractMapper {


	public Long generatePicNo() {
		return selectOne( "MHSrno.generatePicNo", null );
	}


	/**
	 * generateObstSrno 장애정보(TB_MH_OBST) 일련번호 생성
	 *
	 * @return
	 */
	public int generateObstSrno(String empUniqNo) {
		return (Integer) selectOne("MHSrno.generateObstSrno", empUniqNo);
	}

	/**
	 * generatePsptSrno 여권정보(TB_MH_PSPT_MGNE) 일련번호 생성
	 * @param empUniqNo
	 * @return
	 */
	public int generatePsptSrno(String empUniqNo) {
		return (Integer) selectOne("MHSrno.generatePsptSrno", empUniqNo);
	}

	/**
	 *  generateVisaSrno 비자정보(TB_MH_VISA_MGNE) 일련번호 생성
	 * @param empUniqNo
	 * @return
	 */
	public int generateVisaSrno(String empUniqNo) {
		return (Integer) selectOne("MHSrno.generateVisaSrno", empUniqNo);
	}

	/**
	 *  generateLangSrno 어학정보(TB_MH_LANG_MGNE) 일련번호 생성
	 * @param empUniqNo
	 * @return
	 */
	public int generateLangSrno(String empUniqNo) {
		return (Integer) selectOne("MHSrno.generateLangSrno", empUniqNo);
	}
	
	/**
	 *  generateCrtiSrno 자격정보(TB_MH_CRTI_MATT) 일련번호 생성
	 * @param empUniqNo
	 * @return
	 */
	public int generateCrtiSrno(String empUniqNo) {
		return (Integer) selectOne("MHSrno.generateCrtiSrno", empUniqNo);
	}
	
	/**
	 *  generatePsbjSrno 특이사항(TB_MH_PSBJ_MGNE) 일련번호 생성
	 * @param empUniqNo
	 * @return
	 */
	public int generatePsbjSrno(String empUniqNo) {
		return (Integer) selectOne("MHSrno.generatePsbjSrno", empUniqNo);
	}

	/**
	 *  generateFmlySrno 가족사항(TB_MH_FMLY_MGNE) 일련번호 생성
	 * @param empUniqNo
	 * @return
	 */
	public int generateFmlySrno(String empUniqNo) {
		return (Integer) selectOne("MHSrno.generateFmlySrno", empUniqNo);
	}


	/**
	 *  generateScreSrno 학력사항(TB_MH_SCRE) 일련번호 생성
	 * @param empUniqNo
	 * @return
	 */
	public int generateScreSrno(String empUniqNo) {
		return (Integer) selectOne("MHSrno.generateScreSrno", empUniqNo);
	}

	/**
	 * generateDeptHistSrno 부서이력 일련번호 생성(TB_MH_DEPT_HIS)
	 * @return
	 */
	public Long generateDeptHistSrno() {
		  return (Long)selectOne( "MHSrno.generateDeptHistSrno", null );
	}
	
	/**
	 * generateDeptChgSrno 부서변경 일련번호 생성(TB_MH_DEPT_CHG)
	 * @return
	 */
	public Long generateDeptChgSrno() {
		  return (Long)selectOne( "MHSrno.generateDeptChgSrno", null );
	}

	/**
	 * generateChgPicNo 사진 변경 신청 일련번호 생성(TB_MH_EMP_PIC_CHG_APL)
	 * @return
	 */
	public Long generateChgPicNo() {
		return (Long)selectOne( "MHSrno.generateChgPicNo", null );
	}

	/** generateCarrSrno 경력사항  (TB_MH_CARR CARR_SRNO ) 생성
	 * @return
	 */
	public Long generateCarrSrno(String empUniqNo) {
		  return (Long)selectOne( "MHSrno.generateCarrSrno", empUniqNo );
	}

	/**
	 * generateApntSrno 발령일련번호(TB_MH_APNT.APNT_SRNO)생성
	 * @param empUniqNo
	 * @return
	 */
	public String generateApntSrno( String empUniqNo ) {
		  return selectOne( "MHSrno.generateApntSrno", empUniqNo );
	}


	/**
	 * generateObstSrno 장애정보(TB_MH_OBST) 일련번호 생성
	 *
	 * @return
	 */
	public Long generateTrxpHlstSrno() {
		return selectOne("MHSrno.generateTrxpHlstSrno", null);
	}


	/**
	 * generateAccnSrno TB_MY_PYMT_ACCN(지급계좌)
	 * @return
	 */
	public int generateAccnSrno( String empUniqNo ) {
		  return  (Integer) super.selectOne( "MHSrno.generateAccnSrno", empUniqNo );
	}


	/**
	 * generateCarOilnSrno 계좌별지급항목이력 일련번호 생성(TB_MY_ACCN_PYOBJT_HIST.HIST_SRNO ) 생성
	 * @return
	 */
	public Long generateAccnPyobjtHistSrno() {

		return (Long)super.selectOne( "MHSrno.generateAccnPyobjtHistSrno", null );
	}


	/**
	 * generateBstrMltiMttrAplNo 출장다건신청번호(TB_MH_DT_BSTR_APL)
	 * @return
	 */
	public String generateBstrMltiMttrAplNo() {

		return selectOne( "MHSrno.generateBstrMltiMttrAplNo", null );
	}

	/**
	 * generateRtmnCfmStndSeq 퇴직확인기준 순번 생성(TB_MH_RTMN_CFM_STND.SEQ ) 생성
	 * @return
	 */
	public Long generateRtmnCfmStndSeq() {
		
		return (Long)selectOne( "MHSrno.generateRtmnCfmStndSeq", null );
	}

	/**
	 * generateOdbyUsePlnNo 연차사용계획등록(TB_MH_ODBY_USP) 연차사용계획번호 생성
	 *
	 * @return
	 */
	public String generateOdbyUsePlnNo(String aplDt) {
		return selectOne("MHSrno.generateOdbyUsePlnNo", aplDt);
	}

	/**
	 * generateOdbyUseDtlSeq 연차사용계획세부정보등록(TB_MH_ODBY_USP_DTL) 일련번호 생성
	 *
	 * @param obdyUsePlnNo
	 * @return
	 */
	public int generateOdbyUseDtlSeq(String obdyUsePlnNo) {
		return (Integer) super.selectOne("MHSrno.generateOdbyUseDtlSeq", obdyUsePlnNo);
	}
	
	/**
	 * generateCertiAplSrno 증명서발급신청번호 일련번호(TB_MH_CERTI_APL.APL_SRNO) 생성
	 * @return
	 */
	public String generateCertiAplSrno() {
		return selectOne( "MHSrno.generateCertiAplSrno", null );
	}
	
	/**
	 * generateCertiSrno 직원제증명정보 일련번호(TB_MH_CERTI.SRNO) 생성
	 * @return
	 */
	public String generateCertiSrno(String empUniqNo) {
		return selectOne( "MHSrno.generateCertiSrno", empUniqNo );
	}
	
	/**
	 * generateEduCmplBondSrno 교육수료증발급번호 일련번호(TB_MH_EDU_CMPL_BOND.SRNO) 생성
	 * @param eduMngeNo
	 * @return
	 */
	public String generateEduCmplBondSrno(String eduMngeNo) {
		return selectOne("MHSrno.generateEduCmplBondSrno", eduMngeNo);
	}
	
}
