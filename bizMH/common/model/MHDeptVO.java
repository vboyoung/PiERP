package pierp.app.mis.bizMH.common.model;

import pierp.common.base.vo.AbstractRecordVO;

/**
 * TB_MH_DEPT(부서) VO class
 */
@SuppressWarnings("serial")
public class MHDeptVO extends AbstractRecordVO {

    private String juriGuofCd;


	/** 
	 * TB_MH_DEPT.ROLE(역할)
	 */
	private String role;
    
	/** 
	 * TB_MH_DEPT.ACTY_BEGN_DT(활동시작일자)
	 */
	private String actyBegnDt;
    
	/** 
	 * TB_MH_DEPT.ACTY_CLSE_DT(활동종료일자)
	 */
	private String actyClseDt;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getActyBegnDt() {
		return actyBegnDt;
	}

	public void setActyBegnDt(String actyBegnDt) {
		this.actyBegnDt = actyBegnDt;
	}

	public String getActyClseDt() {
		return actyClseDt;
	}

	public void setActyClseDt(String actyClseDt) {
		this.actyClseDt = actyClseDt;
	}

	
	
	/** 
	 * VW_MHC_EMP.ITG_CSMR_ID(통합고객ID)
	 */
	private String partyNumber;
	
	/** 
	 * TB_MH_DEPT.DEPT_UNIQ_NO(부서고유번호)
	 */
	private String deptUniqNo;
    
	/** 
	 * TB_MH_DEPT.DEPT_CD(부서코드)
	 */
	private String deptCd;
    
	/** 
	 * TB_MH_DEPT.DEPT_NM(부서명)
	 */
	private String deptNm;
    
	/** 
	 * TB_MH_DEPT.DEPT_ENG_NM(부서영문명)
	 */
	private String deptEngNm;
    
	/** 
	 * TB_MH_DEPT.UPPR_DEPT_CD(상위부서코드)
	 */
	private String upprDeptCd;
    
	/** 
	 * TB_MH_DEPT.UPPR_DEPT_NM(상위부서명)
	 */
	private String upprDeptNm;
    
	/** 
	 * TB_MH_DEPT.ORGZ_DIV_CD(조직구분코드)
	 */
	private String orgzDivCd;
    
	/** 
	 * TB_MH_DEPT.ORGZ_LV_CD(조직레벨코드)
	 */
	private String orgzLvCd;
    
	/** 
	 * TB_MH_DEPT.ORGZ_ORD(조직순서)
	 */
	private Integer orgzOrd;
    
	/** 
	 * TB_MH_DEPT.DEPT_PSN_NUM(부서정원수)
	 */
	private Double deptPsnNum;
    
	/** 
	 * TB_MH_DEPT.HDDP_EMP_UNIQ_NO(부서장사원고유번호)
	 */
	private String hddpEmpUniqNo;
    
	/** 
	 * TB_MH_DEPT.ASTS_CHRG_EMP_UNIQ_NO(자산담당사원고유번호)
	 */
	private String astsChrgEmpUniqNo;
    
	/** 
	 * TB_MH_DEPT.TEL_NO(전화번호)
	 */
	private String telNo;
    
	/** 
	 * TB_MH_DEPT.CNTR_CD(센터코드)
	 */
	private String cntrCd;
    
	/** 
	 * TB_MH_DEPT.BZPL_CD(사업장코드)
	 */
	private String bzplCd;
    
	/** 
	 * TB_MH_DEPT.DEPT_CHG_DIV_CD(부서변경구분코드)
	 */
	private String deptChgDivCd;
    
	/** 
	 * TB_MH_DEPT.CHG_AL_DEPT_CD(변경전부서코드)
	 */
	private String chgAlDeptCd;
    
	/** 
	 * TB_MH_DEPT.APLY_DT(적용일자)
	 */
	private String aplyDt;
    
	/** 
	 * TB_MH_DEPT.CLSE_DT(종료일자)
	 */
	private String clseDt;
    
	/** 
	 * TB_MH_DEPT.PRTG_ORD(인쇄순서)
	 */
	private Double prtgOrd;
    
	/** 
	 * TB_MH_DEPT.PWKR_PSN_NUM(정규직정원수)
	 */
	private Double pwkrPsnNum;
    
	/** 
	 * TB_MH_DEPT.DSPH_POSI_RSTF_NUM(파견직정원수)
	 */
	private Double dsphPosiRstfNum;
    
	/** 
	 * TB_MH_DEPT.CTRC_POSI_PSN_NUM(계약직정원수)
	 */
	private Double ctrcPosiPsnNum;
    
	/** 
	 * TB_MH_DEPT.USE_YN(사용여부)
	 */
	private String useYn;
    
	/** 
	 * TB_MH_DEPT.REMK(비고)
	 */
	private String remk;
	
	
	
	

	public String getPartyNumber() {
		return partyNumber;
	}

	public void setPartyNumber(String partyNumber) {
		this.partyNumber = partyNumber;
	}

	/**
	 * TB_MH_DEPT.DEPT_UNIQ_NO(부서고유번호) 반환.
	 * @return String - TB_MH_DEPT.DEPT_UNIQ_NO(부서고유번호)값
	 */
	public String getDeptUniqNo() {
		return this.deptUniqNo;
	}
    
	/**
	 * TB_MH_DEPT.DEPT_UNIQ_NO(부서고유번호) 설정.
	 * @param deptUniqNo - TB_MH_DEPT.DEPT_UNIQ_NO(부서고유번호)값
	 */
	public void setDeptUniqNo(String deptUniqNo) {
		this.deptUniqNo = deptUniqNo;
	}
    
	/**
	 * TB_MH_DEPT.DEPT_CD(부서코드) 반환.
	 * @return String - TB_MH_DEPT.DEPT_CD(부서코드)값
	 */
	public String getDeptCd() {
		return this.deptCd;
	}
    
	/**
	 * TB_MH_DEPT.DEPT_CD(부서코드) 설정.
	 * @param deptCd - TB_MH_DEPT.DEPT_CD(부서코드)값
	 */
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
    
	/**
	 * TB_MH_DEPT.DEPT_NM(부서명) 반환.
	 * @return String - TB_MH_DEPT.DEPT_NM(부서명)값
	 */
	public String getDeptNm() {
		return this.deptNm;
	}
    
	/**
	 * TB_MH_DEPT.DEPT_NM(부서명) 설정.
	 * @param deptNm - TB_MH_DEPT.DEPT_NM(부서명)값
	 */
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
    
	/**
	 * TB_MH_DEPT.DEPT_ENG_NM(부서영문명) 반환.
	 * @return String - TB_MH_DEPT.DEPT_ENG_NM(부서영문명)값
	 */
	public String getDeptEngNm() {
		return this.deptEngNm;
	}
    
	/**
	 * TB_MH_DEPT.DEPT_ENG_NM(부서영문명) 설정.
	 * @param deptEngNm - TB_MH_DEPT.DEPT_ENG_NM(부서영문명)값
	 */
	public void setDeptEngNm(String deptEngNm) {
		this.deptEngNm = deptEngNm;
	}
    
	/**
	 * TB_MH_DEPT.UPPR_DEPT_CD(상위부서코드) 반환.
	 * @return String - TB_MH_DEPT.UPPR_DEPT_CD(상위부서코드)값
	 */
	public String getUpprDeptCd() {
		return this.upprDeptCd;
	}
    
	/**
	 * TB_MH_DEPT.UPPR_DEPT_CD(상위부서코드) 설정.
	 * @param upprDeptCd - TB_MH_DEPT.UPPR_DEPT_CD(상위부서코드)값
	 */
	public void setUpprDeptCd(String upprDeptCd) {
		this.upprDeptCd = upprDeptCd;
	}
    
	/**
	 * TB_MH_DEPT.UPPR_DEPT_NM(상위부서명) 반환.
	 * @return String - TB_MH_DEPT.UPPR_DEPT_NM(상위부서명)값
	 */
	public String getUpprDeptNm() {
		return this.upprDeptNm;
	}
    
	/**
	 * TB_MH_DEPT.UPPR_DEPT_NM(상위부서명) 설정.
	 * @param upprDeptNm - TB_MH_DEPT.UPPR_DEPT_NM(상위부서명)값
	 */
	public void setUpprDeptNm(String upprDeptNm) {
		this.upprDeptNm = upprDeptNm;
	}
    
	/**
	 * TB_MH_DEPT.ORGZ_DIV_CD(조직구분코드) 반환.
	 * @return String - TB_MH_DEPT.ORGZ_DIV_CD(조직구분코드)값
	 */
	public String getOrgzDivCd() {
		return this.orgzDivCd;
	}
    
	/**
	 * TB_MH_DEPT.ORGZ_DIV_CD(조직구분코드) 설정.
	 * @param orgzDivCd - TB_MH_DEPT.ORGZ_DIV_CD(조직구분코드)값
	 */
	public void setOrgzDivCd(String orgzDivCd) {
		this.orgzDivCd = orgzDivCd;
	}
    
	/**
	 * TB_MH_DEPT.ORGZ_LV_CD(조직레벨코드) 반환.
	 * @return String - TB_MH_DEPT.ORGZ_LV_CD(조직레벨코드)값
	 */
	public String getOrgzLvCd() {
		return this.orgzLvCd;
	}
    
	/**
	 * TB_MH_DEPT.ORGZ_LV_CD(조직레벨코드) 설정.
	 * @param orgzLvCd - TB_MH_DEPT.ORGZ_LV_CD(조직레벨코드)값
	 */
	public void setOrgzLvCd(String orgzLvCd) {
		this.orgzLvCd = orgzLvCd;
	}
    
	/**
	 * TB_MH_DEPT.ORGZ_ORD(조직순서) 반환.
	 * @return Integer - TB_MH_DEPT.ORGZ_ORD(조직순서)값
	 */
	public Integer getOrgzOrd() {
		return this.orgzOrd;
	}
    
	/**
	 * TB_MH_DEPT.ORGZ_ORD(조직순서) 설정.
	 * @param orgzOrd - TB_MH_DEPT.ORGZ_ORD(조직순서)값
	 */
	public void setOrgzOrd(Integer orgzOrd) {
		this.orgzOrd = orgzOrd;
	}
    
	/**
	 * TB_MH_DEPT.DEPT_PSN_NUM(부서정원수) 반환.
	 * @return Double - TB_MH_DEPT.DEPT_PSN_NUM(부서정원수)값
	 */
	public Double getDeptPsnNum() {
		return this.deptPsnNum;
	}
    
	/**
	 * TB_MH_DEPT.DEPT_PSN_NUM(부서정원수) 설정.
	 * @param deptPsnNum - TB_MH_DEPT.DEPT_PSN_NUM(부서정원수)값
	 */
	public void setDeptPsnNum(Double deptPsnNum) {
		this.deptPsnNum = deptPsnNum;
	}
    
	/**
	 * TB_MH_DEPT.HDDP_EMP_UNIQ_NO(부서장사원번호) 반환.
	 * @return String - TB_MH_DEPT.HDDP_EMP_UNIQ_NO(부서장사원번호)값
	 */
	public String getHddpEmpUniqNo() {
		return this.hddpEmpUniqNo;
	}
    
	/**
	 * TB_MH_DEPT.HDDP_EMP_UNIQ_NO(부서장사원번호) 설정.
	 * @param hddpEmpUniqNo - TB_MH_DEPT.HDDP_EMP_UNIQ_NO(부서장사원번호)값
	 */
	public void setHddpEmpUniqNo(String hddpEmpUniqNo) {
		this.hddpEmpUniqNo = hddpEmpUniqNo;
	}
    
	/**
	 * TB_MH_DEPT.ASTS_CHRG_EMP_UNIQ_NO(자산담당사원고유번호) 반환.
	 * @return String - TB_MH_DEPT.ASTS_CHRG_EMP_UNIQ_NO(자산담당사원고유번호)값
	 */
	public String getAstsChrgEmpUniqNo() {
		return this.astsChrgEmpUniqNo;
	}
    
	/**
	 * TB_MH_DEPT.ASTS_CHRG_EMP_UNIQ_NO(자산담당사원고유번호) 설정.
	 * @param astsChrgEmpUniqNo - TB_MH_DEPT.ASTS_CHRG_EMP_UNIQ_NO(자산담당사원고유번호)값
	 */
	public void setAstsChrgEmpUniqNo(String astsChrgEmpUniqNo) {
		this.astsChrgEmpUniqNo = astsChrgEmpUniqNo;
	}
    
	/**
	 * TB_MH_DEPT.TEL_NO(전화번호) 반환.
	 * @return String - TB_MH_DEPT.TEL_NO(전화번호)값
	 */
	public String getTelNo() {
		return this.telNo;
	}
    
	/**
	 * TB_MH_DEPT.TEL_NO(전화번호) 설정.
	 * @param telNo - TB_MH_DEPT.TEL_NO(전화번호)값
	 */
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
    
	/**
	 * TB_MH_DEPT.CNTR_CD(사업장코드) 반환.
	 * @return String - TB_MH_DEPT.CNTR_CD(사업장코드)값
	 */
	public String getCntrCd() {
		return this.cntrCd;
	}
    
	/**
	 * TB_MH_DEPT.CNTR_CD(사업장코드) 설정.
	 * @param cntrCd - TB_MH_DEPT.CNTR_CD(사업장코드)값
	 */
	public void setCntrCd(String cntrCd) {
		this.cntrCd = cntrCd;
	}
    
	/**
	 * TB_MH_DEPT.BZPL_CD(사업장코드) 반환.
	 * @return String - TB_MH_DEPT.BZPL_CD(사업장코드)값
	 */
	public String getBzplCd() {
		return this.bzplCd;
	}
    
	/**
	 * TB_MH_DEPT.BZPL_CD(사업장코드) 설정.
	 * @param bzplCd - TB_MH_DEPT.BZPL_CD(사업장코드)값
	 */
	public void setBzplCd(String bzplCd) {
		this.bzplCd = bzplCd;
	}
    
	/**
	 * TB_MH_DEPT.DEPT_CHG_DIV_CD(부서변경구분코드) 반환.
	 * @return String - TB_MH_DEPT.DEPT_CHG_DIV_CD(부서변경구분코드)값
	 */
	public String getDeptChgDivCd() {
		return this.deptChgDivCd;
	}
    
	/**
	 * TB_MH_DEPT.DEPT_CHG_DIV_CD(부서변경구분코드) 설정.
	 * @param deptChgDivCd - TB_MH_DEPT.DEPT_CHG_DIV_CD(부서변경구분코드)값
	 */
	public void setDeptChgDivCd(String deptChgDivCd) {
		this.deptChgDivCd = deptChgDivCd;
	}
    
	/**
	 * TB_MH_DEPT.CHG_AL_DEPT_CD(변경전부서코드) 반환.
	 * @return String - TB_MH_DEPT.CHG_AL_DEPT_CD(변경전부서코드)값
	 */
	public String getChgAlDeptCd() {
		return this.chgAlDeptCd;
	}
    
	/**
	 * TB_MH_DEPT.CHG_AL_DEPT_CD(변경전부서코드) 설정.
	 * @param chgAlDeptCd - TB_MH_DEPT.CHG_AL_DEPT_CD(변경전부서코드)값
	 */
	public void setChgAlDeptCd(String chgAlDeptCd) {
		this.chgAlDeptCd = chgAlDeptCd;
	}
    
	/**
	 * TB_MH_DEPT.APLY_DT(적용일자) 반환.
	 * @return String - TB_MH_DEPT.APLY_DT(적용일자)값
	 */
	public String getAplyDt() {
		return this.aplyDt;
	}
    
	/**
	 * TB_MH_DEPT.APLY_DT(적용일자) 설정.
	 * @param aplyDt - TB_MH_DEPT.APLY_DT(적용일자)값
	 */
	public void setAplyDt(String aplyDt) {
		this.aplyDt = aplyDt;
	}
    
	/**
	 * TB_MH_DEPT.CLSE_DT(종료일자) 반환.
	 * @return String - TB_MH_DEPT.CLSE_DT(종료일자)값
	 */
	public String getClseDt() {
		return this.clseDt;
	}
    
	/**
	 * TB_MH_DEPT.CLSE_DT(종료일자) 설정.
	 * @param clseDt - TB_MH_DEPT.CLSE_DT(종료일자)값
	 */
	public void setClseDt(String clseDt) {
		this.clseDt = clseDt;
	}
    
	/**
	 * TB_MH_DEPT.PRTG_ORD(인쇄순서) 반환.
	 * @return Double - TB_MH_DEPT.PRTG_ORD(인쇄순서)값
	 */
	public Double getPrtgOrd() {
		return this.prtgOrd;
	}
    
	/**
	 * TB_MH_DEPT.PRTG_ORD(인쇄순서) 설정.
	 * @param prtgOrd - TB_MH_DEPT.PRTG_ORD(인쇄순서)값
	 */
	public void setPrtgOrd(Double prtgOrd) {
		this.prtgOrd = prtgOrd;
	}
    
	/**
	 * TB_MH_DEPT.PWKR_PSN_NUM(정규직정원수) 반환.
	 * @return Double - TB_MH_DEPT.PWKR_PSN_NUM(정규직정원수)값
	 */
	public Double getPwkrPsnNum() {
		return this.pwkrPsnNum;
	}
    
	/**
	 * TB_MH_DEPT.PWKR_PSN_NUM(정규직정원수) 설정.
	 * @param pwkrPsnNum - TB_MH_DEPT.PWKR_PSN_NUM(정규직정원수)값
	 */
	public void setPwkrPsnNum(Double pwkrPsnNum) {
		this.pwkrPsnNum = pwkrPsnNum;
	}
    
	/**
	 * TB_MH_DEPT.DSPH_POSI_RSTF_NUM(파견직정원수) 반환.
	 * @return Double - TB_MH_DEPT.DSPH_POSI_RSTF_NUM(파견직정원수)값
	 */
	public Double getDsphPosiRstfNum() {
		return this.dsphPosiRstfNum;
	}
    
	/**
	 * TB_MH_DEPT.DSPH_POSI_RSTF_NUM(파견직정원수) 설정.
	 * @param dsphPosiRstfNum - TB_MH_DEPT.DSPH_POSI_RSTF_NUM(파견직정원수)값
	 */
	public void setDsphPosiRstfNum(Double dsphPosiRstfNum) {
		this.dsphPosiRstfNum = dsphPosiRstfNum;
	}
    
	/**
	 * TB_MH_DEPT.CTRC_POSI_PSN_NUM(계약직정원수) 반환.
	 * @return Double - TB_MH_DEPT.CTRC_POSI_PSN_NUM(계약직정원수)값
	 */
	public Double getCtrcPosiPsnNum() {
		return this.ctrcPosiPsnNum;
	}
    
	/**
	 * TB_MH_DEPT.CTRC_POSI_PSN_NUM(계약직정원수) 설정.
	 * @param ctrcPosiPsnNum - TB_MH_DEPT.CTRC_POSI_PSN_NUM(계약직정원수)값
	 */
	public void setCtrcPosiPsnNum(Double ctrcPosiPsnNum) {
		this.ctrcPosiPsnNum = ctrcPosiPsnNum;
	}
    
	/**
	 * TB_MH_DEPT.USE_YN(사용여부) 반환.
	 * @return String - TB_MH_DEPT.USE_YN(사용여부)값
	 */
	public String getUseYn() {
		return this.useYn;
	}
    
	/**
	 * TB_MH_DEPT.USE_YN(사용여부) 설정.
	 * @param useYn - TB_MH_DEPT.USE_YN(사용여부)값
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
    
	/**
	 * TB_MH_DEPT.REMK(비고) 반환.
	 * @return String - TB_MH_DEPT.REMK(비고)값
	 */
	public String getRemk() {
		return this.remk;
	}
    
	/**
	 * TB_MH_DEPT.REMK(비고) 설정.
	 * @param remk - TB_MH_DEPT.REMK(비고)값
	 */
	public void setRemk(String remk) {
		this.remk = remk;
	}

	public String getJuriGuofCd() {
		return juriGuofCd;
	}

	public void setJuriGuofCd(String juriGuofCd) {
		this.juriGuofCd = juriGuofCd;
	}
}
