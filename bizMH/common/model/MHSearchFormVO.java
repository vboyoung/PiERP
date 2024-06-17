package pierp.app.mis.bizMH.common.model;

import pierp.common.base.vo.AbstractRecordVO;

/**
 * VW_MHC_EMP(${model.tableDesc}) VO class
 */
@SuppressWarnings("serial")
public class MHSearchFormVO extends AbstractRecordVO {
    
	/** 
	 * VW_MHC_EMP.EMP_UNIQ_NO(직원고유번호)
	 */
	private String empUniqNo;
    
	/** 
	 * VW_MHC_EMP.EMP_NO(직원번호)
	 */
	private String empNo;
    
	/** 
	 * VW_MHC_EMP.EMP_NM(직원명)
	 */
	private String empNm;
    
	/** 
	 * VW_MHC_EMP.ENG_EMP_NM(영문직원명)
	 */
	private String engEmpNm;
    
	/** 
	 * VW_MHC_EMP.CHCT_EMP_NM(한자직원명)
	 */
	private String chctEmpNm;
    
	/** 
	 * VW_MHC_EMP.ENTC_DT(입사일자)
	 */
	private String entcDt;
    
	/** 
	 * VW_MHC_EMP.LVBC_DT(휴직일자)
	 */
	private String lvbcDt;
    
	/** 
	 * VW_MHC_EMP.RTMN_DT(퇴직일자)
	 */
	private String rtmnDt;
    
	/** 
	 * VW_MHC_EMP.RTMN_RSON(퇴직사유)
	 */
	private String rtmnRson;
    
	/** 
	 * VW_MHC_EMP.ENTC_DIV_CD(입사구분코드)
	 */
	private String entcDivCd;
    
	/** 
	 * VW_MHC_EMP.STF_DIV_CD(직원구분코드)
	 */
	private String stfDivCd;
    
	/** 
	 * VW_MHC_EMP.WRK_STAT_CD(근무상태코드)
	 */
	private String wrkStatCd;
    
	/** 
	 * VW_MHC_EMP.BLNG_DEPT_PART_CD(소속부서부코드)
	 */
	private String blngDeptPartCd;
    
	/** 
	 * VW_MHC_EMP.BLNG_DEPT_TEAM_CD(소속부서팀코드)
	 */
	private String blngDeptTeamCd;
    
	/** 
	 * VW_MHC_EMP.MNGE_DEPT_CD(관리부서코드)
	 */
	private String mngeDeptCd;
    
	/** 
	 * VW_MHC_EMP.DEPT_UNIQ_NO(부서고유번호)
	 */
	private String deptUniqNo;
    
	/** 
	 * VW_MHC_EMP.UPPR_DEPT_UNIQ_NO(상위부서고유번호)
	 */
	private String upprDeptUniqNo;
    
	/** 
	 * VW_MHC_EMP.CNTR_CD(센터코드)
	 */
	private String cntrCd;
    
	/** 
	 * VW_MHC_EMP.OCGRP_CD(직군코드)
	 */
	private String ocgrpCd;
    
	/** 
	 * VW_MHC_EMP.PSIT_CD(직위코드)
	 */
	private String psitCd;
    
	/** 
	 * VW_MHC_EMP.POS_CD(직급코드)
	 */
	private String posCd;
    
	/** 
	 * VW_MHC_EMP.ORGL_BLNG_INST_CD(원소속기관코드)
	 */
	private String orglBlngInstCd;
    
	/** 
	 * VW_MHC_EMP.ORGL_BLNG_POS_NM(원소속직급명)
	 */
	private String orglBlngPosNm;
    
	/** 
	 * VW_MHC_EMP.CTRC_CLSE_DT(계약종료일자)
	 */
	private String ctrcClseDt;
    
	/** 
	 * VW_MHC_EMP.VERT_DIV_CD(보훈구분코드)
	 */
	private String vertDivCd;
    
	/** 
	 * VW_MHC_EMP.VERT_NO(보훈번호)
	 */
	private String vertNo;
    
	/** 
	 * VW_MHC_EMP.SCAL_LCAL_DIV_CD(양력음력구분코드)
	 */
	private String scalLcalDivCd;
    
	/** 
	 * VW_MHC_EMP.DAYB(생년월일)
	 */
	private String dayb;
    
	/** 
	 * VW_MHC_EMP.SCLD_LCLD_DIV_CD1(양력음력구분코드1)
	 */
	private String scldLcldDivCd1;
    
	/** 
	 * VW_MHC_EMP.TRLY_DAYB(실제생년월일)
	 */
	private String trlyDayb;
    
	/** 
	 * VW_MHC_EMP.MARG_ANVSR(결혼기념일)
	 */
	private String margAnvsr;
    
	/** 
	 * VW_MHC_EMP.SEX_DIV_CD(성별구분코드)
	 */
	private String sexDivCd;
    
	/** 
	 * VW_MHC_EMP.IHIDNUM(주민등록번호)
	 */
	private String ihidnum;
    
	/** 
	 * VW_MHC_EMP.FRST_ASGN_DT(최초보직일자)
	 */
	private String frstAsgnDt;
    
	/** 
	 * VW_MHC_EMP.ASGN_TRNF_DT(보직전보일자)
	 */
	private String asgnTrnfDt;
    
	/** 
	 * VW_MHC_EMP.RGLR_PRMO_DT(정기승급일자)
	 */
	private String rglrPrmoDt;
    
	/** 
	 * VW_MHC_EMP.EMAL(전자메일)
	 */
	private String emal;
    
	/** 
	 * VW_MHC_EMP.BF_OFF(이전직장)
	 */
	private String bfOff;
    
	/** 
	 * VW_MHC_EMP.BF_CTPL(이전연락처)
	 */
	private String bfCtpl;
    
	/** 
	 * VW_MHC_EMP.RTMN_AFTR_OFFC(퇴직후직장)
	 */
	private String rtmnAftrOffc;
    
	/** 
	 * VW_MHC_EMP.LAUN_YN(노동조합여부)
	 */
	private String launYn;
    
	/** 
	 * VW_MHC_EMP.EXM_NO(채용년도(2)_순번(2)_일련번호(6))
	 */
	private String exmNo;
    
	/** 
	 * VW_MHC_EMP.EXTG_EMP_NO(기존직원번호)
	 */
	private String extgEmpNo;
    
	/** 
	 * VW_MHC_EMP.EXTG_BLNG_INST_NM(기존소속기관명)
	 */
	private String extgBlngInstNm;
    
	/** 
	 * VW_MHC_EMP.FINL_SCRE_CD(최종학력코드)
	 */
	private String finlScreCd;
    
	/** 
	 * VW_MHC_EMP.MJR_DIV_CD(전공구분코드)
	 */
	private String mjrDivCd;
    
	/** 
	 * VW_MHC_EMP.OBST_TY_CD(장애유형코드)
	 */
	private String obstTyCd;
    
	/** 
	 * VW_MHC_EMP.OBST_GRADE_DIV_CD(장애등급구분코드)
	 */
	private String obstGradeDivCd;
    
	/** 
	 * VW_MHC_EMP.OBST_GRADE_CD(장애등급코드)
	 */
	private String obstGradeCd;
    
	/** 
	 * VW_MHC_EMP.OBST_APRL_DT(장애인정일자)
	 */
	private String obstAprlDt;
	
	/** 
	 * TB_MH_EMP.INT_RCMT_EXPR_YN(청년인턴경험여부)
	 */
	private String intRcmtExprYn;
	
	/** 
	 * TB_MH_EMP.FNEP_SUPOJ_YN(취업지원대상여부)
	 */
	private String fnepSupojYn;
    
	/** 
	 * VW_MHC_EMP.ZON_DIV_CD(지역구분코드)
	 */
	private String zonDivCd;
    
	/** 
	 * VW_MHC_EMP.ASAL_MNY(연봉액)
	 */
	private Double asalMny;
    
	/** 
	 * VW_MHC_EMP.HTHM(건강보험액)
	 */
	private Double hthm;
    
	/** 
	 * VW_MHC_EMP.NAPST(국민연금액)
	 */
	private Double napst;
    
	/** 
	 * VW_MHC_EMP.PTM_SAL(시간급여)
	 */
	private Double ptmSal;
    
	/** 
	 * VW_MHC_EMP.RTRT_DIV_CD(퇴직연금구분코드)
	 */
	private String rtrtDivCd;
    
	/** 
	 * VW_MHC_EMP.RTRT_KIND_CD(퇴직연금종류코드)
	 */
	private String rtrtKindCd;
    
	/** 
	 * VW_MHC_EMP.RTRT_ADMS_DT(퇴직연금가입일자)
	 */
	private String rtrtAdmsDt;
    
	/** 
	 * VW_MHC_EMP.DPDT_NUM(부양가족수)
	 */
	private Integer dpdtNum;
    
	/** 
	 * VW_MHC_EMP.PFRG(본적)
	 */
	private String pfrg;
    
	/** 
	 * VW_MHC_EMP.ZIP_NO(우편번호)
	 */
	private String zipNo;
    
	/** 
	 * VW_MHC_EMP.PSNT_ADDR(현재주소)
	 */
	private String psntAddr;
    
	/** 
	 * VW_MHC_EMP.DTLS_ADDR(상세주소)
	 */
	private String dtlsAddr;
    
	
	/** 
	 * VW_MHC_EMP.ENG_PSNT_ADDR(영문주소)
	 */
	private String engPsntAddr;
	
	
	/** 
	 * VW_MHC_EMP.HOUS_TEL_NO(집전화번호)
	 */
	private String housTelNo;
    
	/** 
	 * VW_MHC_EMP.CO_TEL_NO(회사전화번호)
	 */
	private String coTelNo;
    
	/** 
	 * VW_MHC_EMP.CPON_NO(핸드폰번호)
	 */
	private String cponNo;
    
	/** 
	 * VW_MHC_EMP.CPON_OWN_DIV_CD(핸드폰소유구분코드)
	 */
	private String cponOwnDivCd;
    
	/** 
	 * VW_MHC_EMP.CALL_ALRD_NO(호출기번호)
	 */
	private String callAlrdNo;
    
	/** 
	 * VW_MHC_EMP.HEGT(신장)
	 */
	private Double hegt;
    
	/** 
	 * VW_MHC_EMP.WGHT(체중)
	 */
	private Double wght;
    
	/** 
	 * VW_MHC_EMP.BLGR(혈액형)
	 */
	private String blgr;
    
	/** 
	 * VW_MHC_EMP.SIHT_LEFT(시력좌)
	 */
	private String sihtLeft;
    
	/** 
	 * VW_MHC_EMP.SIHT_RIHT(시력우)
	 */
	private String sihtRiht;
    
	/** 
	 * VW_MHC_EMP.BLPR_HIGH(혈압최고)
	 */
	private Integer blprHigh;
    
	/** 
	 * VW_MHC_EMP.BLPR_LOWT(혈압최저)
	 */
	private Integer blprLowt;
    
	/** 
	 * VW_MHC_EMP.GCST(흉위)
	 */
	private Integer gcst;
    
	/** 
	 * VW_MHC_EMP.CRBL_DIV_CD(색맹구분코드)
	 */
	private String crblDivCd;
    
	/** 
	 * VW_MHC_EMP.MAIN_MHTRY(주요병력)
	 */
	private String mainMhtry;
    
	/** 
	 * VW_MHC_EMP.HBBY(취미)
	 */
	private String hbby;
    
	/** 
	 * VW_MHC_EMP.SPM(특기)
	 */
	private String spm;
    
	/** 
	 * VW_MHC_EMP.RELI_DIV_CD(종교구분코드)
	 */
	private String reliDivCd;
    
	/** 
	 * VW_MHC_EMP.PROP_STAT_CHAT(재산상태동산)
	 */
	private Double propStatChat;
    
	/** 
	 * VW_MHC_EMP.PROP_STAT_ESTA(재산상태부동산)
	 */
	private Double propStatEsta;
    
	/** 
	 * VW_MHC_EMP.DWEL_STAT_DIV_CD(주거상태구분코드)
	 */
	private String dwelStatDivCd;
    
	/** 
	 * VW_MHC_EMP.MRGE_YN(혼인여부)
	 */
	private String mrgeYn;
    
	/** 
	 * VW_MHC_EMP.MARG_DT(결혼일자)
	 */
	private String margDt;
	
	/** 
	 * VW_MHC_EMP.VERT_APRL_YN(보훈인정여부)
	 */
	private String vertAprlYn;
    
	/** 
	 * VW_MHC_EMP.ITG_CSMR_ID(통합고객ID)
	 */
	private String itgCsmrId;
	
	/** 
	 * VW_MHC_EMP.SALC_CD(호봉구분코드)
	 */
	private String salcCd;
    
	/** 
	 * TB_MH_EMP.OBST_EXNO(장애인정여부)
	 */
	private String obstExno;
    	
	/**
	 * TB_MH_EMP.HOPE_DEPT_CD1(희망부서코드1)
	 */
	private String hopeDeptCd1;
	
	/**
	 * TB_MH_EMP.HOPE_DEPT_CD2(희망부서코드2)
	 */
	private String hopeDeptCd2;
	
	/**
	 * TB_MH_EMP.HOPE_DEPT_CD3(희망부서코드3)
	 */
	private String hopeDeptCd3;
	
	/**
	 * TB_MH_EMP.HOPE_BEGN_DT(희망시작일자)
	 */
	private String hopeBegnDt;
	
	/**
	 * TB_MH_EMP.HOPE_CLSE_DT(희망종료일자)
	 */
	private String hopeClseDt;
	
	/**
	 * TB_MH_EMP.HOPE_RSON(희망사유)
	 */
	private String hopeRson;
	
	/**
	 * TB_MH_EMP.PFM_DUTI(수행업무)
	 */
	private String pfmDuti;

	/**
	 * TB_MH_EMP.DFPD_SWCH_DT(유기전환일자)
	 */
	private String dfpdSwchDt;

	/**
	 * TB_MH_EMP.INFT_SWCH_DT(무기전환일자)
	 */
	private String inftSwchDt;
	
	/**
	 * TB_MH_EMP.PWKR_SWCH_DT(정규직전환일자)
	 */
	private String pwkrSwchDt;
    
	/** 
	 * TB_MH_EMP.CHLD_NUM(자녀수)
	 */
	private Integer chldNum;
    
	/** 
	 * TB_MH_EMP.MDPT_STTL_DT(중도정산일자)
	 */
	private String mdptSttlDt;
    
	/** 
	 * TB_MH_EMP.WKPL_DIV_CD(근무지구분코드)
	 */
	private String wkplDivCd;
    
	/** 
	 * TB_MH_EMP.REMK(비고)
	 */
	private String remk;
    
	/** 
	 * TB_MH_EMP.MARG_YN(혼인여부)
	 */
	private String margYn;	
	
    
	/** 
	 * TB_MH_EMP.LANG_SDTR_CNTS(어학연수내용)
	 */
	private String langSdtrCnts;
	
	/** 
	 * TB_MH_EMP.TFTM_APNT_CNTS(TF팀발령내용)
	 */
	private String tftmApntCnts;
	
	/** 
	 * TB_MH_EMP.YMN_ITN_BEGN_DT(청년인턴시작일자)
	 */
	private String ymnItnBegnDt;
    
	/** 
	 * TB_MH_EMP.YMN_ITN_CLSE_DT(청년인턴종료일자)
	 */
	private String ymnItnClseDt;
	
	/**
     * TB_MH_EMP.ADJB_DEPT_CD(겸직부서코드)
     */
    private String adjbDeptCd;

    /**
     * TB_MH_EMP.ADJB_PSIT_CD(겸직직위코드)
     */
    private String adjbPsitCd;

    /**
     * TB_MH_EMP.DUTI_SUPPT_DEPT_CD(업무지원부서코드)
     */
    private String dutiSupptDeptCd;


    /**
     * TB_MH_EMP.FORN_WRK_YN(해외근무여부)
     */
    private String fornWrkYn;

    /**
     * TB_MH_EMP.DNGR_ALWC_YN(위험수당여부)
     */
    private String dngrAlwcYn;

    /**
     * TB_MH_EMP.RP_ALWC_YN(출납수당여부)
     */
    private String rpAlwcYn;

    /**
     * TB_MH_EMP.WTHT_TXAM_RT_DVCD(원천징수세액비율구분코드)
     */
    private String wthtTxamRtDvcd;

    /**
     * TB_MH_EMP.BF_MTRL_YN(이전자료여부)
     */
    private String bfMtrlYn;

    /**
     * TB_MH_EMP.FRCO_DIWK_YN(외국법인소속파견근로자여부 여(1)부(2))
     */
    private String frcoDiwkYn;

    /**
     * TB_MH_EMP.DUTY_CD(직책코드)
     */
    private String dutyCd;

    /**
     * TB_MH_EMP.AMAS_OBJT_YN(상조회대상여부)
     */
    private String amasObjtYn;

    /**
     * TB_MH_EMP.FRAT_DEDT_DIV_CD(우수리공제구분코드)
     */
    private String fratDedtDivCd;

    /**
     * TB_MH_EMP.FRAT_DEDT_AMT(우수리공제금액)
     */
    private Double fratDedtAmt;

    /**
     * TB_MH_EMP.HRNG_DIV_CD(고용구분코드)
     */
    private String hrngDivCd;

    /**
     * TB_MH_EMP.DUTI_ALWC_DIV_CD(특수업무수당구분코드)
     */
    private String dutiAlwcDivCd;

    /**
     * TB_MH_EMP.APRT_YN(수습여부)
     */
    private String aprtYn;

    /**
     * TB_MH_EMP.APRT_CLSE_DT(수습해제일자)
     */
    private String aprtClseDt;

    /**
     * TB_MH_EMP.LAUN_ADMS_DT(노동조합가입일자)
     */
    private String launAdmsDt;

    /**
     * TB_MH_EMP.RTMN_RCK_DT(퇴직기산일자)
     */
    private String rtmnRckDt;

    /**
     * TB_MH_EMP.CTRC_BEGN_DT(계약시작일자)
     */
    private String ctrcBegnDt;

    /**
     * TB_MH_EMP.VERT_AQ_DT(보훈취득일자)
     */
	private String vertAqDt;

	/**
	 * TB_SY_USR.USR_ID(사용자ID)
	 */
	private String usrId;

	/**
	 * TB_MH_EMP.PTAL_USR_ID(포탈사용자ID)
	 */
	private String ptalUsrId;

	public String getLangSdtrCnts() {
		return langSdtrCnts;
	}

	public void setLangSdtrCnts(String langSdtrCnts) {
		this.langSdtrCnts = langSdtrCnts;
	}

	public String getTftmApntCnts() {
		return tftmApntCnts;
	}

	public void setTftmApntCnts(String tftmApntCnts) {
		this.tftmApntCnts = tftmApntCnts;
	}		
	
	
	public String getMargYn() {
		return margYn;
	}

	public void setMargYn(String margYn) {
		this.margYn = margYn;
	}

	/**
	 * VW_MHC_EMP.EMP_UNIQ_NO(직원고유번호) 반환.
	 * @return String - VW_MHC_EMP.EMP_UNIQ_NO(직원고유번호)값
	 */
	public String getEmpUniqNo() {
		return this.empUniqNo;
	}
    
	/**
	 * VW_MHC_EMP.EMP_UNIQ_NO(직원고유번호) 설정.
	 * @param empUniqNo - VW_MHC_EMP.EMP_UNIQ_NO(직원고유번호)값
	 */
	public void setEmpUniqNo(String empUniqNo) {
		this.empUniqNo = empUniqNo;
	}
    
	/**
	 * VW_MHC_EMP.EMP_NO(직원번호) 반환.
	 * @return String - VW_MHC_EMP.EMP_NO(직원번호)값
	 */
	public String getEmpNo() {
		return this.empNo;
	}
    
	/**
	 * VW_MHC_EMP.EMP_NO(직원번호) 설정.
	 * @param empNo - VW_MHC_EMP.EMP_NO(직원번호)값
	 */
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
    
	/**
	 * VW_MHC_EMP.EMP_NM(직원명) 반환.
	 * @return String - VW_MHC_EMP.EMP_NM(직원명)값
	 */
	public String getEmpNm() {
		return this.empNm;
	}
    
	/**
	 * VW_MHC_EMP.EMP_NM(직원명) 설정.
	 * @param empNm - VW_MHC_EMP.EMP_NM(직원명)값
	 */
	public void setEmpNm(String empNm) {
		this.empNm = empNm;
	}
    
	/**
	 * VW_MHC_EMP.ENG_EMP_NM(영문직원명) 반환.
	 * @return String - VW_MHC_EMP.ENG_EMP_NM(영문직원명)값
	 */
	public String getEngEmpNm() {
		return this.engEmpNm;
	}
    
	/**
	 * VW_MHC_EMP.ENG_EMP_NM(영문직원명) 설정.
	 * @param engEmpNm - VW_MHC_EMP.ENG_EMP_NM(영문사원명)값
	 */
	public void setEngEmpNm(String engEmpNm) {
		this.engEmpNm = engEmpNm;
	}
    
	/**
	 * VW_MHC_EMP.CHCT_EMP_NM(한자사원명) 반환.
	 * @return String - VW_MHC_EMP.CHCT_EMP_NM(한자사원명)값
	 */
	public String getChctEmpNm() {
		return this.chctEmpNm;
	}
    
	/**
	 * VW_MHC_EMP.CHCT_EMP_NM(한자사원명) 설정.
	 * @param chctEmpNm - VW_MHC_EMP.CHCT_EMP_NM(한자사원명)값
	 */
	public void setChctEmpNm(String chctEmpNm) {
		this.chctEmpNm = chctEmpNm;
	}
    
	/**
	 * VW_MHC_EMP.ENTC_DT(입사일자) 반환.
	 * @return String - VW_MHC_EMP.ENTC_DT(입사일자)값
	 */
	public String getEntcDt() {
		return this.entcDt;
	}
    
	/**
	 * VW_MHC_EMP.ENTC_DT(입사일자) 설정.
	 * @param entcDt - VW_MHC_EMP.ENTC_DT(입사일자)값
	 */
	public void setEntcDt(String entcDt) {
		this.entcDt = entcDt;
	}
    
	/**
	 * VW_MHC_EMP.LVBC_DT(휴직일자) 반환.
	 * @return String - VW_MHC_EMP.LVBC_DT(휴직일자)값
	 */
	public String getLvbcDt() {
		return this.lvbcDt;
	}
    
	/**
	 * VW_MHC_EMP.LVBC_DT(휴직일자) 설정.
	 * @param lvbcDt - VW_MHC_EMP.LVBC_DT(휴직일자)값
	 */
	public void setLvbcDt(String lvbcDt) {
		this.lvbcDt = lvbcDt;
	}
    
	/**
	 * VW_MHC_EMP.RTMN_DT(퇴직일자) 반환.
	 * @return String - VW_MHC_EMP.RTMN_DT(퇴직일자)값
	 */
	public String getRtmnDt() {
		return this.rtmnDt;
	}
    
	/**
	 * VW_MHC_EMP.RTMN_DT(퇴직일자) 설정.
	 * @param rtmnDt - VW_MHC_EMP.RTMN_DT(퇴직일자)값
	 */
	public void setRtmnDt(String rtmnDt) {
		this.rtmnDt = rtmnDt;
	}
    
	/**
	 * VW_MHC_EMP.RTMN_RSON(퇴직사유) 반환.
	 * @return String - VW_MHC_EMP.RTMN_RSON(퇴직사유)값
	 */
	public String getRtmnRson() {
		return this.rtmnRson;
	}
    
	/**
	 * VW_MHC_EMP.RTMN_RSON(퇴직사유) 설정.
	 * @param rtmnRson - VW_MHC_EMP.RTMN_RSON(퇴직사유)값
	 */
	public void setRtmnRson(String rtmnRson) {
		this.rtmnRson = rtmnRson;
	}
    
	/**
	 * VW_MHC_EMP.ENTC_DIV_CD(입사구분코드) 반환.
	 * @return String - VW_MHC_EMP.ENTC_DIV_CD(입사구분코드)값
	 */
	public String getEntcDivCd() {
		return this.entcDivCd;
	}
    
	/**
	 * VW_MHC_EMP.ENTC_DIV_CD(입사구분코드) 설정.
	 * @param entcDivCd - VW_MHC_EMP.ENTC_DIV_CD(입사구분코드)값
	 */
	public void setEntcDivCd(String entcDivCd) {
		this.entcDivCd = entcDivCd;
	}
    
	/**
	 * VW_MHC_EMP.STF_DIV_CD(직원구분코드) 반환.
	 * @return String - VW_MHC_EMP.STF_DIV_CD(직원구분코드)값
	 */
	public String getStfDivCd() {
		return this.stfDivCd;
	}
    
	/**
	 * VW_MHC_EMP.STF_DIV_CD(직원구분코드) 설정.
	 * @param stfDivCd - VW_MHC_EMP.STF_DIV_CD(직원구분코드)값
	 */
	public void setStfDivCd(String stfDivCd) {
		this.stfDivCd = stfDivCd;
	}
    
	/**
	 * VW_MHC_EMP.WRK_STAT_CD(근무상태코드) 반환.
	 * @return String - VW_MHC_EMP.WRK_STAT_CD(근무상태코드)값
	 */
	public String getWrkStatCd() {
		return this.wrkStatCd;
	}
    
	/**
	 * VW_MHC_EMP.WRK_STAT_CD(근무상태코드) 설정.
	 * @param wrkStatCd - VW_MHC_EMP.WRK_STAT_CD(근무상태코드)값
	 */
	public void setWrkStatCd(String wrkStatCd) {
		this.wrkStatCd = wrkStatCd;
	}
    
	/**
	 * VW_MHC_EMP.BLNG_DEPT_PART_CD(소속부서부코드) 반환.
	 * @return String - VW_MHC_EMP.BLNG_DEPT_PART_CD(소속부서부코드)값
	 */
	public String getBlngDeptPartCd() {
		return this.blngDeptPartCd;
	}
    
	/**
	 * VW_MHC_EMP.BLNG_DEPT_PART_CD(소속부서부코드) 설정.
	 * @param blngDeptPartCd - VW_MHC_EMP.BLNG_DEPT_PART_CD(소속부서부코드)값
	 */
	public void setBlngDeptPartCd(String blngDeptPartCd) {
		this.blngDeptPartCd = blngDeptPartCd;
	}
    
	/**
	 * VW_MHC_EMP.BLNG_DEPT_TEAM_CD(소속부서팀코드) 반환.
	 * @return String - VW_MHC_EMP.BLNG_DEPT_TEAM_CD(소속부서팀코드)값
	 */
	public String getBlngDeptTeamCd() {
		return this.blngDeptTeamCd;
	}
    
	/**
	 * VW_MHC_EMP.BLNG_DEPT_TEAM_CD(소속부서팀코드) 설정.
	 * @param blngDeptTeamCd - VW_MHC_EMP.BLNG_DEPT_TEAM_CD(소속부서팀코드)값
	 */
	public void setBlngDeptTeamCd(String blngDeptTeamCd) {
		this.blngDeptTeamCd = blngDeptTeamCd;
	}
    
	/**
	 * VW_MHC_EMP.MNGE_DEPT_CD(관리부서코드) 반환.
	 * @return String - VW_MHC_EMP.MNGE_DEPT_CD(관리부서코드)값
	 */
	public String getMngeDeptCd() {
		return this.mngeDeptCd;
	}
    
	/**
	 * VW_MHC_EMP.MNGE_DEPT_CD(관리부서코드) 설정.
	 * @param mngeDeptCd - VW_MHC_EMP.MNGE_DEPT_CD(관리부서코드)값
	 */
	public void setMngeDeptCd(String mngeDeptCd) {
		this.mngeDeptCd = mngeDeptCd;
	}
    
	/**
	 * VW_MHC_EMP.DEPT_UNIQ_NO(부서고유번호) 반환.
	 * @return String - VW_MHC_EMP.DEPT_UNIQ_NO(부서고유번호)값
	 */
	public String getDeptUniqNo() {
		return this.deptUniqNo;
	}
    
	/**
	 * VW_MHC_EMP.DEPT_UNIQ_NO(부서고유번호) 설정.
	 * @param deptUniqNo - VW_MHC_EMP.DEPT_UNIQ_NO(부서고유번호)값
	 */
	public void setDeptUniqNo(String deptUniqNo) {
		this.deptUniqNo = deptUniqNo;
	}
    
	/**
	 * VW_MHC_EMP.UPPR_DEPT_UNIQ_NO(상위부서고유번호) 반환.
	 * @return String - VW_MHC_EMP.UPPR_DEPT_UNIQ_NO(상위부서고유번호)값
	 */
	public String getUpprDeptUniqNo() {
		return this.upprDeptUniqNo;
	}
    
	/**
	 * VW_MHC_EMP.UPPR_DEPT_UNIQ_NO(상위부서고유번호) 설정.
	 * @param upprDeptUniqNo - VW_MHC_EMP.UPPR_DEPT_UNIQ_NO(상위부서고유번호)값
	 */
	public void setUpprDeptUniqNo(String upprDeptUniqNo) {
		this.upprDeptUniqNo = upprDeptUniqNo;
	}
    
	/**
	 * VW_MHC_EMP.CNTR_CD(센터코드) 반환.
	 * @return String - VW_MHC_EMP.CNTR_CD(센터코드)값
	 */
	public String getCntrCd() {
		return this.cntrCd;
	}
    
	/**
	 * VW_MHC_EMP.CNTR_CD(센터코드) 설정.
	 * @param cntrCd - VW_MHC_EMP.CNTR_CD(센터코드)값
	 */
	public void setCntrCd(String cntrCd) {
		this.cntrCd = cntrCd;
	}
    
	/**
	 * VW_MHC_EMP.OCGRP_CD(직군코드) 반환.
	 * @return String - VW_MHC_EMP.OCGRP_CD(직군코드)값
	 */
	public String getOcgrpCd() {
		return this.ocgrpCd;
	}
    
	/**
	 * VW_MHC_EMP.OCGRP_CD(직군코드) 설정.
	 * @param ocgrpCd - VW_MHC_EMP.OCGRP_CD(직군코드)값
	 */
	public void setOcgrpCd(String ocgrpCd) {
		this.ocgrpCd = ocgrpCd;
	}
    
	/**
	 * VW_MHC_EMP.PSIT_CD(직위코드) 반환.
	 * @return String - VW_MHC_EMP.PSIT_CD(직위코드)값
	 */
	public String getPsitCd() {
		return this.psitCd;
	}
    
	/**
	 * VW_MHC_EMP.PSIT_CD(직위코드) 설정.
	 * @param psitCd - VW_MHC_EMP.PSIT_CD(직위코드)값
	 */
	public void setPsitCd(String psitCd) {
		this.psitCd = psitCd;
	}
    
	/**
	 * VW_MHC_EMP.POS_CD(직급코드) 반환.
	 * @return String - VW_MHC_EMP.POS_CD(직급코드)값
	 */
	public String getPosCd() {
		return this.posCd;
	}
    
	/**
	 * VW_MHC_EMP.POS_CD(직급코드) 설정.
	 * @param posCd - VW_MHC_EMP.POS_CD(직급코드)값
	 */
	public void setPosCd(String posCd) {
		this.posCd = posCd;
	}
    
	/**
	 * VW_MHC_EMP.ORGL_BLNG_INST_CD(원소속기관코드) 반환.
	 * @return String - VW_MHC_EMP.ORGL_BLNG_INST_CD(원소속기관코드)값
	 */
	public String getOrglBlngInstCd() {
		return this.orglBlngInstCd;
	}
    
	/**
	 * VW_MHC_EMP.ORGL_BLNG_INST_CD(원소속기관코드) 설정.
	 * @param orglBlngInstCd - VW_MHC_EMP.ORGL_BLNG_INST_CD(원소속기관코드)값
	 */
	public void setOrglBlngInstCd(String orglBlngInstCd) {
		this.orglBlngInstCd = orglBlngInstCd;
	}
    
	/**
	 * VW_MHC_EMP.ORGL_BLNG_POS_NM(원소속직급명) 반환.
	 * @return String - VW_MHC_EMP.ORGL_BLNG_POS_NM(원소속직급명)값
	 */
	public String getOrglBlngPosNm() {
		return this.orglBlngPosNm;
	}
    
	/**
	 * VW_MHC_EMP.ORGL_BLNG_POS_NM(원소속직급명) 설정.
	 * @param orglBlngPosNm - VW_MHC_EMP.ORGL_BLNG_POS_NM(원소속직급명)값
	 */
	public void setOrglBlngPosNm(String orglBlngPosNm) {
		this.orglBlngPosNm = orglBlngPosNm;
	}
    
	/**
	 * VW_MHC_EMP.CTRC_CLSE_DT(계약종료일자) 반환.
	 * @return String - VW_MHC_EMP.CTRC_CLSE_DT(계약종료일자)값
	 */
	public String getCtrcClseDt() {
		return this.ctrcClseDt;
	}
    
	/**
	 * VW_MHC_EMP.CTRC_CLSE_DT(계약종료일자) 설정.
	 * @param ctrcClseDt - VW_MHC_EMP.CTRC_CLSE_DT(계약종료일자)값
	 */
	public void setCtrcClseDt(String ctrcClseDt) {
		this.ctrcClseDt = ctrcClseDt;
	}
    
	/**
	 * VW_MHC_EMP.VERT_DIV_CD(보훈구분코드) 반환.
	 * @return String - VW_MHC_EMP.VERT_DIV_CD(보훈구분코드)값
	 */
	public String getVertDivCd() {
		return this.vertDivCd;
	}
    
	/**
	 * VW_MHC_EMP.VERT_DIV_CD(보훈구분코드) 설정.
	 * @param vertDivCd - VW_MHC_EMP.VERT_DIV_CD(보훈구분코드)값
	 */
	public void setVertDivCd(String vertDivCd) {
		this.vertDivCd = vertDivCd;
	}
    
	/**
	 * VW_MHC_EMP.VERT_NO(보훈번호) 반환.
	 * @return String - VW_MHC_EMP.VERT_NO(보훈번호)값
	 */
	public String getVertNo() {
		return this.vertNo;
	}
    
	/**
	 * VW_MHC_EMP.VERT_NO(보훈번호) 설정.
	 * @param vertNo - VW_MHC_EMP.VERT_NO(보훈번호)값
	 */
	public void setVertNo(String vertNo) {
		this.vertNo = vertNo;
	}
    
	/**
	 * VW_MHC_EMP.SCAL_LCAL_DIV_CD(양력음력구분코드) 반환.
	 * @return String - VW_MHC_EMP.SCAL_LCAL_DIV_CD(양력음력구분코드)값
	 */
	public String getScalLcalDivCd() {
		return this.scalLcalDivCd;
	}
    
	/**
	 * VW_MHC_EMP.SCAL_LCAL_DIV_CD(양력음력구분코드) 설정.
	 * @param scalLcalDivCd - VW_MHC_EMP.SCAL_LCAL_DIV_CD(양력음력구분코드)값
	 */
	public void setScalLcalDivCd(String scalLcalDivCd) {
		this.scalLcalDivCd = scalLcalDivCd;
	}
    
	/**
	 * VW_MHC_EMP.DAYB(생년월일) 반환.
	 * @return String - VW_MHC_EMP.DAYB(생년월일)값
	 */
	public String getDayb() {
		return this.dayb;
	}
    
	/**
	 * VW_MHC_EMP.DAYB(생년월일) 설정.
	 * @param dayb - VW_MHC_EMP.DAYB(생년월일)값
	 */
	public void setDayb(String dayb) {
		this.dayb = dayb;
	}
    
	/**
	 * VW_MHC_EMP.SCLD_LCLD_DIV_CD1(양력음력구분코드1) 반환.
	 * @return String - VW_MHC_EMP.SCLD_LCLD_DIV_CD1(양력음력구분코드1)값
	 */
	public String getScldLcldDivCd1() {
		return this.scldLcldDivCd1;
	}
    
	/**
	 * VW_MHC_EMP.SCLD_LCLD_DIV_CD1(양력음력구분코드1) 설정.
	 * @param scldLcldDivCd1 - VW_MHC_EMP.SCLD_LCLD_DIV_CD1(양력음력구분코드1)값
	 */
	public void setScldLcldDivCd1(String scldLcldDivCd1) {
		this.scldLcldDivCd1 = scldLcldDivCd1;
	}
    
	/**
	 * VW_MHC_EMP.TRLY_DAYB(실제생년월일) 반환.
	 * @return String - VW_MHC_EMP.TRLY_DAYB(실제생년월일)값
	 */
	public String getTrlyDayb() {
		return this.trlyDayb;
	}
    
	/**
	 * VW_MHC_EMP.TRLY_DAYB(실제생년월일) 설정.
	 * @param trlyDayb - VW_MHC_EMP.TRLY_DAYB(실제생년월일)값
	 */
	public void setTrlyDayb(String trlyDayb) {
		this.trlyDayb = trlyDayb;
	}
    
	/**
	 * VW_MHC_EMP.MARG_ANVSR(결혼기념일) 반환.
	 * @return String - VW_MHC_EMP.MARG_ANVSR(결혼기념일)값
	 */
	public String getMargAnvsr() {
		return this.margAnvsr;
	}
    
	/**
	 * VW_MHC_EMP.MARG_ANVSR(결혼기념일) 설정.
	 * @param margAnvsr - VW_MHC_EMP.MARG_ANVSR(결혼기념일)값
	 */
	public void setMargAnvsr(String margAnvsr) {
		this.margAnvsr = margAnvsr;
	}
    
	/**
	 * VW_MHC_EMP.SEX_DIV_CD(성별구분코드) 반환.
	 * @return String - VW_MHC_EMP.SEX_DIV_CD(성별구분코드)값
	 */
	public String getSexDivCd() {
		return this.sexDivCd;
	}
    
	/**
	 * VW_MHC_EMP.SEX_DIV_CD(성별구분코드) 설정.
	 * @param sexDivCd - VW_MHC_EMP.SEX_DIV_CD(성별구분코드)값
	 */
	public void setSexDivCd(String sexDivCd) {
		this.sexDivCd = sexDivCd;
	}
    
	/**
	 * VW_MHC_EMP.IHIDNUM(주민등록번호) 반환.
	 * @return String - VW_MHC_EMP.IHIDNUM(주민등록번호)값
	 */
	public String getIhidnum() {
		return this.ihidnum;
	}
    
	/**
	 * VW_MHC_EMP.IHIDNUM(주민등록번호) 설정.
	 * @param ihidnum - VW_MHC_EMP.IHIDNUM(주민등록번호)값
	 */
	public void setIhidnum(String ihidnum) {
		this.ihidnum = ihidnum;
	}
    
	/**
	 * VW_MHC_EMP.FRST_ASGN_DT(최초보직일자) 반환.
	 * @return String - VW_MHC_EMP.FRST_ASGN_DT(최초보직일자)값
	 */
	public String getFrstAsgnDt() {
		return this.frstAsgnDt;
	}
    
	/**
	 * VW_MHC_EMP.FRST_ASGN_DT(최초보직일자) 설정.
	 * @param frstAsgnDt - VW_MHC_EMP.FRST_ASGN_DT(최초보직일자)값
	 */
	public void setFrstAsgnDt(String frstAsgnDt) {
		this.frstAsgnDt = frstAsgnDt;
	}
    
	/**
	 * VW_MHC_EMP.ASGN_TRNF_DT(보직전보일자) 반환.
	 * @return String - VW_MHC_EMP.ASGN_TRNF_DT(보직전보일자)값
	 */
	public String getAsgnTrnfDt() {
		return this.asgnTrnfDt;
	}
    
	/**
	 * VW_MHC_EMP.ASGN_TRNF_DT(보직전보일자) 설정.
	 * @param asgnTrnfDt - VW_MHC_EMP.ASGN_TRNF_DT(보직전보일자)값
	 */
	public void setAsgnTrnfDt(String asgnTrnfDt) {
		this.asgnTrnfDt = asgnTrnfDt;
	}
    
	/**
	 * VW_MHC_EMP.RGLR_PRMO_DT(정기승급일자) 반환.
	 * @return String - VW_MHC_EMP.RGLR_PRMO_DT(정기승급일자)값
	 */
	public String getRglrPrmoDt() {
		return this.rglrPrmoDt;
	}
    
	/**
	 * VW_MHC_EMP.RGLR_PRMO_DT(정기승급일자) 설정.
	 * @param rglrPrmoDt - VW_MHC_EMP.RGLR_PRMO_DT(정기승급일자)값
	 */
	public void setRglrPrmoDt(String rglrPrmoDt) {
		this.rglrPrmoDt = rglrPrmoDt;
	}
    
	/**
	 * VW_MHC_EMP.EMAL(전자메일) 반환.
	 * @return String - VW_MHC_EMP.EMAL(전자메일)값
	 */
	public String getEmal() {
		return this.emal;
	}
    
	/**
	 * VW_MHC_EMP.EMAL(전자메일) 설정.
	 * @param emal - VW_MHC_EMP.EMAL(전자메일)값
	 */
	public void setEmal(String emal) {
		this.emal = emal;
	}
    
	/**
	 * VW_MHC_EMP.BF_OFF(이전직장) 반환.
	 * @return String - VW_MHC_EMP.BF_OFF(이전직장)값
	 */
	public String getBfOff() {
		return this.bfOff;
	}
    
	/**
	 * VW_MHC_EMP.BF_OFF(이전직장) 설정.
	 * @param bfOff - VW_MHC_EMP.BF_OFF(이전직장)값
	 */
	public void setBfOff(String bfOff) {
		this.bfOff = bfOff;
	}
    
	/**
	 * VW_MHC_EMP.BF_CTPL(이전연락처) 반환.
	 * @return String - VW_MHC_EMP.BF_CTPL(이전연락처)값
	 */
	public String getBfCtpl() {
		return this.bfCtpl;
	}
    
	/**
	 * VW_MHC_EMP.BF_CTPL(이전연락처) 설정.
	 * @param bfCtpl - VW_MHC_EMP.BF_CTPL(이전연락처)값
	 */
	public void setBfCtpl(String bfCtpl) {
		this.bfCtpl = bfCtpl;
	}
    
	/**
	 * VW_MHC_EMP.RTMN_AFTR_OFFC(퇴직후직장) 반환.
	 * @return String - VW_MHC_EMP.RTMN_AFTR_OFFC(퇴직후직장)값
	 */
	public String getRtmnAftrOffc() {
		return this.rtmnAftrOffc;
	}
    
	/**
	 * VW_MHC_EMP.RTMN_AFTR_OFFC(퇴직후직장) 설정.
	 * @param rtmnAftrOffc - VW_MHC_EMP.RTMN_AFTR_OFFC(퇴직후직장)값
	 */
	public void setRtmnAftrOffc(String rtmnAftrOffc) {
		this.rtmnAftrOffc = rtmnAftrOffc;
	}
    
	/**
	 * VW_MHC_EMP.LAUN_YN(노동조합여부) 반환.
	 * @return String - VW_MHC_EMP.LAUN_YN(노동조합여부)값
	 */
	public String getLaunYn() {
		return this.launYn;
	}
    
	/**
	 * VW_MHC_EMP.LAUN_YN(노동조합여부) 설정.
	 * @param launYn - VW_MHC_EMP.LAUN_YN(노동조합여부)값
	 */
	public void setLaunYn(String launYn) {
		this.launYn = launYn;
	}
    
	/**
	 * VW_MHC_EMP.EXM_NO(채용년도(2)_순번(2)_일련번호(6)) 반환.
	 * @return String - VW_MHC_EMP.EXM_NO(채용년도(2)_순번(2)_일련번호(6))값
	 */
	public String getExmNo() {
		return this.exmNo;
	}
    
	/**
	 * VW_MHC_EMP.EXM_NO(채용년도(2)_순번(2)_일련번호(6)) 설정.
	 * @param exmNo - VW_MHC_EMP.EXM_NO(채용년도(2)_순번(2)_일련번호(6))값
	 */
	public void setExmNo(String exmNo) {
		this.exmNo = exmNo;
	}
    
	/**
	 * VW_MHC_EMP.EXTG_EMP_NO(기존사원번호) 반환.
	 * @return String - VW_MHC_EMP.EXTG_EMP_NO(기존사원번호)값
	 */
	public String getExtgEmpNo() {
		return this.extgEmpNo;
	}
    
	/**
	 * VW_MHC_EMP.EXTG_EMP_NO(기존사원번호) 설정.
	 * @param extgEmpNo - VW_MHC_EMP.EXTG_EMP_NO(기존사원번호)값
	 */
	public void setExtgEmpNo(String extgEmpNo) {
		this.extgEmpNo = extgEmpNo;
	}
    
	/**
	 * VW_MHC_EMP.EXTG_BLNG_INST_NM(기존소속기관명) 반환.
	 * @return String - VW_MHC_EMP.EXTG_BLNG_INST_NM(기존소속기관명)값
	 */
	public String getExtgBlngInstNm() {
		return this.extgBlngInstNm;
	}
    
	/**
	 * VW_MHC_EMP.EXTG_BLNG_INST_NM(기존소속기관명) 설정.
	 * @param extgBlngInstNm - VW_MHC_EMP.EXTG_BLNG_INST_NM(기존소속기관명)값
	 */
	public void setExtgBlngInstNm(String extgBlngInstNm) {
		this.extgBlngInstNm = extgBlngInstNm;
	}
    
	/**
	 * VW_MHC_EMP.FINL_SCRE_CD(최종학력코드) 반환.
	 * @return String - VW_MHC_EMP.FINL_SCRE_CD(최종학력코드)값
	 */
	public String getFinlScreCd() {
		return this.finlScreCd;
	}
    
	/**
	 * VW_MHC_EMP.FINL_SCRE_CD(최종학력코드) 설정.
	 * @param finlScreCd - VW_MHC_EMP.FINL_SCRE_CD(최종학력코드)값
	 */
	public void setFinlScreCd(String finlScreCd) {
		this.finlScreCd = finlScreCd;
	}
    
	/**
	 * VW_MHC_EMP.MJR_DIV_CD(전공구분코드) 반환.
	 * @return String - VW_MHC_EMP.MJR_DIV_CD(전공구분코드)값
	 */
	public String getMjrDivCd() {
		return this.mjrDivCd;
	}
    
	/**
	 * VW_MHC_EMP.MJR_DIV_CD(전공구분코드) 설정.
	 * @param mjrDivCd - VW_MHC_EMP.MJR_DIV_CD(전공구분코드)값
	 */
	public void setMjrDivCd(String mjrDivCd) {
		this.mjrDivCd = mjrDivCd;
	}
    
	/**
	 * VW_MHC_EMP.OBST_TY_CD(장애유형코드) 반환.
	 * @return String - VW_MHC_EMP.OBST_TY_CD(장애유형코드)값
	 */
	public String getObstTyCd() {
		return this.obstTyCd;
	}
    
	/**
	 * VW_MHC_EMP.OBST_TY_CD(장애유형코드) 설정.
	 * @param obstTyCd - VW_MHC_EMP.OBST_TY_CD(장애유형코드)값
	 */
	public void setObstTyCd(String obstTyCd) {
		this.obstTyCd = obstTyCd;
	}
    
	/**
	 * VW_MHC_EMP.OBST_GRADE_DIV_CD(장애등급구분코드) 반환.
	 * @return String - VW_MHC_EMP.OBST_GRADE_DIV_CD(장애등급구분코드)값
	 */
	public String getObstGradeDivCd() {
		return this.obstGradeDivCd;
	}
    
	/**
	 * VW_MHC_EMP.OBST_GRADE_DIV_CD(장애등급구분코드) 설정.
	 * @param obstGradeDivCd - VW_MHC_EMP.OBST_GRADE_DIV_CD(장애등급구분코드)값
	 */
	public void setObstGradeDivCd(String obstGradeDivCd) {
		this.obstGradeDivCd = obstGradeDivCd;
	}
    
	/**
	 * VW_MHC_EMP.OBST_GRADE_CD(장애등급코드) 반환.
	 * @return String - VW_MHC_EMP.OBST_GRADE_CD(장애등급코드)값
	 */
	public String getObstGradeCd() {
		return this.obstGradeCd;
	}
    
	/**
	 * VW_MHC_EMP.OBST_GRADE_CD(장애등급코드) 설정.
	 * @param obstGradeCd - VW_MHC_EMP.OBST_GRADE_CD(장애등급코드)값
	 */
	public void setObstGradeCd(String obstGradeCd) {
		this.obstGradeCd = obstGradeCd;
	}
    
	/**
	 * VW_MHC_EMP.OBST_APRL_DT(장애인정일자) 반환.
	 * @return String - VW_MHC_EMP.OBST_APRL_DT(장애인정일자)값
	 */
	public String getObstAprlDt() {
		return this.obstAprlDt;
	}
    
	/**
	 * VW_MHC_EMP.OBST_APRL_DT(장애인정일자) 설정.
	 * @param obstAprlDt - VW_MHC_EMP.OBST_APRL_DT(장애인정일자)값
	 */
	public void setObstAprlDt(String obstAprlDt) {
		this.obstAprlDt = obstAprlDt;
	}
    
	/**
	 * TB_MH_EMP.INT_RCMT_EXPR_YN(청년인턴경험여부) 반환.
	 * @return String - TB_MH_EMP.INT_RCMT_EXPR_YN(청년인턴경험여부)값
	 */
	public String getIntRcmtExprYn() {
		return intRcmtExprYn;
	}
	
	/**
	 * TB_MH_EMP.INT_RCMT_EXPR_YN(청년인턴경험여부) 설정.
	 * @param intRcmtExprYn - TB_MH_EMP.INT_RCMT_EXPR_YN(청년인턴경험여부)값
	 */
	public void setIntRcmtExprYn(String intRcmtExprYn) {
		this.intRcmtExprYn = intRcmtExprYn;
	}

	/**
	 * TB_MH_EMP.FNEP_SUPOJ_YN(취업지원대상여부) 반환.
	 * @return String - TB_MH_EMP.FNEP_SUPOJ_YN(취업지원대상여부)값
	 */
	public String getFnepSupojYn() {
		return fnepSupojYn;
	}

	/**
	 * TB_MH_EMP.FNEP_SUPOJ_YN(취업지원대상여부) 설정.
	 * @param fnepSupojYn - TB_MH_EMP.FNEP_SUPOJ_YN(취업지원대상여부)값
	 */
	public void setFnepSupojYn(String fnepSupojYn) {
		this.fnepSupojYn = fnepSupojYn;
	}
	
	/**
	 * VW_MHC_EMP.ZON_DIV_CD(지역구분코드) 반환.
	 * @return String - VW_MHC_EMP.ZON_DIV_CD(지역구분코드)값
	 */
	public String getZonDivCd() {
		return this.zonDivCd;
	}
    
	/**
	 * VW_MHC_EMP.ZON_DIV_CD(지역구분코드) 설정.
	 * @param zonDivCd - VW_MHC_EMP.ZON_DIV_CD(지역구분코드)값
	 */
	public void setZonDivCd(String zonDivCd) {
		this.zonDivCd = zonDivCd;
	}
    
	/**
	 * VW_MHC_EMP.ASAL_MNY(연봉액) 반환.
	 * @return Double - VW_MHC_EMP.ASAL_MNY(연봉액)값
	 */
	public Double getAsalMny() {
		return this.asalMny;
	}
    
	/**
	 * VW_MHC_EMP.ASAL_MNY(연봉액) 설정.
	 * @param asalMny - VW_MHC_EMP.ASAL_MNY(연봉액)값
	 */
	public void setAsalMny(Double asalMny) {
		this.asalMny = asalMny;
	}
    
	/**
	 * VW_MHC_EMP.HTHM(건강보험액) 반환.
	 * @return Double - VW_MHC_EMP.HTHM(건강보험액)값
	 */
	public Double getHthm() {
		return this.hthm;
	}
    
	/**
	 * VW_MHC_EMP.HTHM(건강보험액) 설정.
	 * @param hthm - VW_MHC_EMP.HTHM(건강보험액)값
	 */
	public void setHthm(Double hthm) {
		this.hthm = hthm;
	}
    
	/**
	 * VW_MHC_EMP.NAPST(국민연금액) 반환.
	 * @return Double - VW_MHC_EMP.NAPST(국민연금액)값
	 */
	public Double getNapst() {
		return this.napst;
	}
    
	/**
	 * VW_MHC_EMP.NAPST(국민연금액) 설정.
	 * @param napst - VW_MHC_EMP.NAPST(국민연금액)값
	 */
	public void setNapst(Double napst) {
		this.napst = napst;
	}
    
	/**
	 * VW_MHC_EMP.PTM_SAL(시간급여) 반환.
	 * @return Double - VW_MHC_EMP.PTM_SAL(시간급여)값
	 */
	public Double getPtmSal() {
		return this.ptmSal;
	}
    
	/**
	 * VW_MHC_EMP.PTM_SAL(시간급여) 설정.
	 * @param ptmSal - VW_MHC_EMP.PTM_SAL(시간급여)값
	 */
	public void setPtmSal(Double ptmSal) {
		this.ptmSal = ptmSal;
	}
    
	/**
	 * VW_MHC_EMP.RTRT_DIV_CD(퇴직연금구분코드) 반환.
	 * @return String - VW_MHC_EMP.RTRT_DIV_CD(퇴직연금구분코드)값
	 */
	public String getRtrtDivCd() {
		return this.rtrtDivCd;
	}
    
	/**
	 * VW_MHC_EMP.RTRT_DIV_CD(퇴직연금구분코드) 설정.
	 * @param rtrtDivCd - VW_MHC_EMP.RTRT_DIV_CD(퇴직연금구분코드)값
	 */
	public void setRtrtDivCd(String rtrtDivCd) {
		this.rtrtDivCd = rtrtDivCd;
	}
    
	/**
	 * VW_MHC_EMP.RTRT_KIND_CD(퇴직연금종류코드) 반환.
	 * @return String - VW_MHC_EMP.RTRT_KIND_CD(퇴직연금종류코드)값
	 */
	public String getRtrtKindCd() {
		return this.rtrtKindCd;
	}
    
	/**
	 * VW_MHC_EMP.RTRT_KIND_CD(퇴직연금종류코드) 설정.
	 * @param rtrtKindCd - VW_MHC_EMP.RTRT_KIND_CD(퇴직연금종류코드)값
	 */
	public void setRtrtKindCd(String rtrtKindCd) {
		this.rtrtKindCd = rtrtKindCd;
	}
    
	/**
	 * VW_MHC_EMP.RTRT_ADMS_DT(퇴직연금가입일자) 반환.
	 * @return String - VW_MHC_EMP.RTRT_ADMS_DT(퇴직연금가입일자)값
	 */
	public String getRtrtAdmsDt() {
		return this.rtrtAdmsDt;
	}
    
	/**
	 * VW_MHC_EMP.RTRT_ADMS_DT(퇴직연금가입일자) 설정.
	 * @param rtrtAdmsDt - VW_MHC_EMP.RTRT_ADMS_DT(퇴직연금가입일자)값
	 */
	public void setRtrtAdmsDt(String rtrtAdmsDt) {
		this.rtrtAdmsDt = rtrtAdmsDt;
	}
    
	/**
	 * VW_MHC_EMP.DPDT_NUM(부양가족수) 반환.
	 * @return Integer - VW_MHC_EMP.DPDT_NUM(부양가족수)값
	 */
	public Integer getDpdtNum() {
		return this.dpdtNum;
	}
    
	/**
	 * VW_MHC_EMP.DPDT_NUM(부양가족수) 설정.
	 * @param dpdtNum - VW_MHC_EMP.DPDT_NUM(부양가족수)값
	 */
	public void setDpdtNum(Integer dpdtNum) {
		this.dpdtNum = dpdtNum;
	}
    
	/**
	 * VW_MHC_EMP.PFRG(본적) 반환.
	 * @return String - VW_MHC_EMP.PFRG(본적)값
	 */
	public String getPfrg() {
		return this.pfrg;
	}
    
	/**
	 * VW_MHC_EMP.PFRG(본적) 설정.
	 * @param pfrg - VW_MHC_EMP.PFRG(본적)값
	 */
	public void setPfrg(String pfrg) {
		this.pfrg = pfrg;
	}
    
	/**
	 * VW_MHC_EMP.ZIP_NO(우편번호) 반환.
	 * @return String - VW_MHC_EMP.ZIP_NO(우편번호)값
	 */
	public String getZipNo() {
		return this.zipNo;
	}
    
	/**
	 * VW_MHC_EMP.ZIP_NO(우편번호) 설정.
	 * @param zipNo - VW_MHC_EMP.ZIP_NO(우편번호)값
	 */
	public void setZipNo(String zipNo) {
		this.zipNo = zipNo;
	}
    
	/**
	 * VW_MHC_EMP.PSNT_ADDR(현재주소) 반환.
	 * @return String - VW_MHC_EMP.PSNT_ADDR(현재주소)값
	 */
	public String getPsntAddr() {
		return this.psntAddr;
	}
    
	/**
	 * VW_MHC_EMP.PSNT_ADDR(현재주소) 설정.
	 * @param psntAddr - VW_MHC_EMP.PSNT_ADDR(현재주소)값
	 */
	public void setPsntAddr(String psntAddr) {
		this.psntAddr = psntAddr;
	}
    
	/**
	 * VW_MHC_EMP.DTLS_ADDR(상세주소) 반환.
	 * @return String - VW_MHC_EMP.DTLS_ADDR(상세주소)값
	 */
	public String getDtlsAddr() {
		return this.dtlsAddr;
	}
    
	/**
	 * VW_MHC_EMP.DTLS_ADDR(상세주소) 설정.
	 * @param dtlsAddr - VW_MHC_EMP.DTLS_ADDR(상세주소)값
	 */
	public void setDtlsAddr(String dtlsAddr) {
		this.dtlsAddr = dtlsAddr;
	}
    

	public String getEngPsntAddr() {
		return engPsntAddr;
	}

	public void setEngPsntAddr(String engPsntAddr) {
		this.engPsntAddr = engPsntAddr;
	}

	/**
	 * VW_MHC_EMP.HOUS_TEL_NO(집전화번호) 반환.
	 * @return String - VW_MHC_EMP.HOUS_TEL_NO(집전화번호)값
	 */
	public String getHousTelNo() {
		return this.housTelNo;
	}
    
	/**
	 * VW_MHC_EMP.HOUS_TEL_NO(집전화번호) 설정.
	 * @param housTelNo - VW_MHC_EMP.HOUS_TEL_NO(집전화번호)값
	 */
	public void setHousTelNo(String housTelNo) {
		this.housTelNo = housTelNo;
	}
    
	/**
	 * VW_MHC_EMP.CO_TEL_NO(회사전화번호) 반환.
	 * @return String - VW_MHC_EMP.CO_TEL_NO(회사전화번호)값
	 */
	public String getCoTelNo() {
		return this.coTelNo;
	}
    
	/**
	 * VW_MHC_EMP.CO_TEL_NO(회사전화번호) 설정.
	 * @param coTelNo - VW_MHC_EMP.CO_TEL_NO(회사전화번호)값
	 */
	public void setCoTelNo(String coTelNo) {
		this.coTelNo = coTelNo;
	}
    
	/**
	 * VW_MHC_EMP.CPON_NO(핸드폰번호) 반환.
	 * @return String - VW_MHC_EMP.CPON_NO(핸드폰번호)값
	 */
	public String getCponNo() {
		return this.cponNo;
	}
    
	/**
	 * VW_MHC_EMP.CPON_NO(핸드폰번호) 설정.
	 * @param cponNo - VW_MHC_EMP.CPON_NO(핸드폰번호)값
	 */
	public void setCponNo(String cponNo) {
		this.cponNo = cponNo;
	}
    
	/**
	 * VW_MHC_EMP.CPON_OWN_DIV_CD(핸드폰소유구분코드) 반환.
	 * @return String - VW_MHC_EMP.CPON_OWN_DIV_CD(핸드폰소유구분코드)값
	 */
	public String getCponOwnDivCd() {
		return this.cponOwnDivCd;
	}
    
	/**
	 * VW_MHC_EMP.CPON_OWN_DIV_CD(핸드폰소유구분코드) 설정.
	 * @param cponOwnDivCd - VW_MHC_EMP.CPON_OWN_DIV_CD(핸드폰소유구분코드)값
	 */
	public void setCponOwnDivCd(String cponOwnDivCd) {
		this.cponOwnDivCd = cponOwnDivCd;
	}
    
	/**
	 * VW_MHC_EMP.CALL_ALRD_NO(호출기번호) 반환.
	 * @return String - VW_MHC_EMP.CALL_ALRD_NO(호출기번호)값
	 */
	public String getCallAlrdNo() {
		return this.callAlrdNo;
	}
    
	/**
	 * VW_MHC_EMP.CALL_ALRD_NO(호출기번호) 설정.
	 * @param callAlrdNo - VW_MHC_EMP.CALL_ALRD_NO(호출기번호)값
	 */
	public void setCallAlrdNo(String callAlrdNo) {
		this.callAlrdNo = callAlrdNo;
	}
    
	/**
	 * VW_MHC_EMP.HEGT(신장) 반환.
	 * @return Double - VW_MHC_EMP.HEGT(신장)값
	 */
	public Double getHegt() {
		return this.hegt;
	}
    
	/**
	 * VW_MHC_EMP.HEGT(신장) 설정.
	 * @param hegt - VW_MHC_EMP.HEGT(신장)값
	 */
	public void setHegt(Double hegt) {
		this.hegt = hegt;
	}
    
	/**
	 * VW_MHC_EMP.WGHT(체중) 반환.
	 * @return Double - VW_MHC_EMP.WGHT(체중)값
	 */
	public Double getWght() {
		return this.wght;
	}
    
	/**
	 * VW_MHC_EMP.WGHT(체중) 설정.
	 * @param wght - VW_MHC_EMP.WGHT(체중)값
	 */
	public void setWght(Double wght) {
		this.wght = wght;
	}
    
	/**
	 * VW_MHC_EMP.BLGR(혈액형) 반환.
	 * @return String - VW_MHC_EMP.BLGR(혈액형)값
	 */
	public String getBlgr() {
		return this.blgr;
	}
    
	/**
	 * VW_MHC_EMP.BLGR(혈액형) 설정.
	 * @param blgr - VW_MHC_EMP.BLGR(혈액형)값
	 */
	public void setBlgr(String blgr) {
		this.blgr = blgr;
	}
    
	/**
	 * VW_MHC_EMP.SIHT_LEFT(시력좌) 반환.
	 * @return String - VW_MHC_EMP.SIHT_LEFT(시력좌)값
	 */
	public String getSihtLeft() {
		return this.sihtLeft;
	}
    
	/**
	 * VW_MHC_EMP.SIHT_LEFT(시력좌) 설정.
	 * @param sihtLeft - VW_MHC_EMP.SIHT_LEFT(시력좌)값
	 */
	public void setSihtLeft(String sihtLeft) {
		this.sihtLeft = sihtLeft;
	}
    
	/**
	 * VW_MHC_EMP.SIHT_RIHT(시력우) 반환.
	 * @return String - VW_MHC_EMP.SIHT_RIHT(시력우)값
	 */
	public String getSihtRiht() {
		return this.sihtRiht;
	}
    
	/**
	 * VW_MHC_EMP.SIHT_RIHT(시력우) 설정.
	 * @param sihtRiht - VW_MHC_EMP.SIHT_RIHT(시력우)값
	 */
	public void setSihtRiht(String sihtRiht) {
		this.sihtRiht = sihtRiht;
	}
    
	/**
	 * VW_MHC_EMP.BLPR_HIGH(혈압최고) 반환.
	 * @return Integer - VW_MHC_EMP.BLPR_HIGH(혈압최고)값
	 */
	public Integer getBlprHigh() {
		return this.blprHigh;
	}
    
	/**
	 * VW_MHC_EMP.BLPR_HIGH(혈압최고) 설정.
	 * @param blprHigh - VW_MHC_EMP.BLPR_HIGH(혈압최고)값
	 */
	public void setBlprHigh(Integer blprHigh) {
		this.blprHigh = blprHigh;
	}
    
	/**
	 * VW_MHC_EMP.BLPR_LOWT(혈압최저) 반환.
	 * @return Integer - VW_MHC_EMP.BLPR_LOWT(혈압최저)값
	 */
	public Integer getBlprLowt() {
		return this.blprLowt;
	}
    
	/**
	 * VW_MHC_EMP.BLPR_LOWT(혈압최저) 설정.
	 * @param blprLowt - VW_MHC_EMP.BLPR_LOWT(혈압최저)값
	 */
	public void setBlprLowt(Integer blprLowt) {
		this.blprLowt = blprLowt;
	}
    
	/**
	 * VW_MHC_EMP.GCST(흉위) 반환.
	 * @return Integer - VW_MHC_EMP.GCST(흉위)값
	 */
	public Integer getGcst() {
		return this.gcst;
	}
    
	/**
	 * VW_MHC_EMP.GCST(흉위) 설정.
	 * @param gcst - VW_MHC_EMP.GCST(흉위)값
	 */
	public void setGcst(Integer gcst) {
		this.gcst = gcst;
	}
    
	/**
	 * VW_MHC_EMP.CRBL_DIV_CD(색맹구분코드) 반환.
	 * @return String - VW_MHC_EMP.CRBL_DIV_CD(색맹구분코드)값
	 */
	public String getCrblDivCd() {
		return this.crblDivCd;
	}
    
	/**
	 * VW_MHC_EMP.CRBL_DIV_CD(색맹구분코드) 설정.
	 * @param crblDivCd - VW_MHC_EMP.CRBL_DIV_CD(색맹구분코드)값
	 */
	public void setCrblDivCd(String crblDivCd) {
		this.crblDivCd = crblDivCd;
	}
    
	/**
	 * VW_MHC_EMP.MAIN_MHTRY(주요병력) 반환.
	 * @return String - VW_MHC_EMP.MAIN_MHTRY(주요병력)값
	 */
	public String getMainMhtry() {
		return this.mainMhtry;
	}
    
	/**
	 * VW_MHC_EMP.MAIN_MHTRY(주요병력) 설정.
	 * @param mainMhtry - VW_MHC_EMP.MAIN_MHTRY(주요병력)값
	 */
	public void setMainMhtry(String mainMhtry) {
		this.mainMhtry = mainMhtry;
	}
    
	/**
	 * VW_MHC_EMP.HBBY(취미) 반환.
	 * @return String - VW_MHC_EMP.HBBY(취미)값
	 */
	public String getHbby() {
		return this.hbby;
	}
    
	/**
	 * VW_MHC_EMP.HBBY(취미) 설정.
	 * @param hbby - VW_MHC_EMP.HBBY(취미)값
	 */
	public void setHbby(String hbby) {
		this.hbby = hbby;
	}
    
	/**
	 * VW_MHC_EMP.SPM(특기) 반환.
	 * @return String - VW_MHC_EMP.SPM(특기)값
	 */
	public String getSpm() {
		return this.spm;
	}
    
	/**
	 * VW_MHC_EMP.SPM(특기) 설정.
	 * @param spm - VW_MHC_EMP.SPM(특기)값
	 */
	public void setSpm(String spm) {
		this.spm = spm;
	}
    
	/**
	 * VW_MHC_EMP.RELI_DIV_CD(종교구분코드) 반환.
	 * @return String - VW_MHC_EMP.RELI_DIV_CD(종교구분코드)값
	 */
	public String getReliDivCd() {
		return this.reliDivCd;
	}
    
	/**
	 * VW_MHC_EMP.RELI_DIV_CD(종교구분코드) 설정.
	 * @param reliDivCd - VW_MHC_EMP.RELI_DIV_CD(종교구분코드)값
	 */
	public void setReliDivCd(String reliDivCd) {
		this.reliDivCd = reliDivCd;
	}
    
	/**
	 * VW_MHC_EMP.PROP_STAT_CHAT(재산상태동산) 반환.
	 * @return Double - VW_MHC_EMP.PROP_STAT_CHAT(재산상태동산)값
	 */
	public Double getPropStatChat() {
		return this.propStatChat;
	}
    
	/**
	 * VW_MHC_EMP.PROP_STAT_CHAT(재산상태동산) 설정.
	 * @param propStatChat - VW_MHC_EMP.PROP_STAT_CHAT(재산상태동산)값
	 */
	public void setPropStatChat(Double propStatChat) {
		this.propStatChat = propStatChat;
	}
    
	/**
	 * VW_MHC_EMP.PROP_STAT_ESTA(재산상태부동산) 반환.
	 * @return Double - VW_MHC_EMP.PROP_STAT_ESTA(재산상태부동산)값
	 */
	public Double getPropStatEsta() {
		return this.propStatEsta;
	}
    
	/**
	 * VW_MHC_EMP.PROP_STAT_ESTA(재산상태부동산) 설정.
	 * @param propStatEsta - VW_MHC_EMP.PROP_STAT_ESTA(재산상태부동산)값
	 */
	public void setPropStatEsta(Double propStatEsta) {
		this.propStatEsta = propStatEsta;
	}
    
	/**
	 * VW_MHC_EMP.DWEL_STAT_DIV_CD(주거상태구분코드) 반환.
	 * @return String - VW_MHC_EMP.DWEL_STAT_DIV_CD(주거상태구분코드)값
	 */
	public String getDwelStatDivCd() {
		return this.dwelStatDivCd;
	}
    
	/**
	 * VW_MHC_EMP.DWEL_STAT_DIV_CD(주거상태구분코드) 설정.
	 * @param dwelStatDivCd - VW_MHC_EMP.DWEL_STAT_DIV_CD(주거상태구분코드)값
	 */
	public void setDwelStatDivCd(String dwelStatDivCd) {
		this.dwelStatDivCd = dwelStatDivCd;
	}
    
	/**
	 * VW_MHC_EMP.MRGE_YN(혼인여부) 반환.
	 * @return String - VW_MHC_EMP.MRGE_YN(혼인여부)값
	 */
	public String getMrgeYn() {
		return this.mrgeYn;
	}
    
	/**
	 * VW_MHC_EMP.MRGE_YN(혼인여부) 설정.
	 * @param mrgeYn - VW_MHC_EMP.MRGE_YN(혼인여부)값
	 */
	public void setMrgeYn(String mrgeYn) {
		this.mrgeYn = mrgeYn;
	}
    
	/**
	 * VW_MHC_EMP.MARG_DT(결혼일자) 반환.
	 * @return String - VW_MHC_EMP.MARG_DT(결혼일자)값
	 */
	public String getMargDt() {
		return this.margDt;
	}
    
	/**
	 * VW_MHC_EMP.MARG_DT(결혼일자) 설정.
	 * @param margDt - VW_MHC_EMP.MARG_DT(결혼일자)값
	 */
	public void setMargDt(String margDt) {
		this.margDt = margDt;
	}
    
	/**
	 * VW_MHC_EMP.VERT_APRL_YN(보훈인정여부) 반환.
	 * @return String - VW_MHC_EMP.VERT_APRL_YN(보훈인정여부)값
	 */
	public String getVertAprlYn() {
		return this.vertAprlYn;
	}
    
	/**
	 * VW_MHC_EMP.VERT_APRL_YN(보훈인정여부) 설정.
	 * @param vertAprlYn - VW_MHC_EMP.VERT_APRL_YN(보훈인정여부)값
	 */
	public void setVertAprlYn(String vertAprlYn) {
		this.vertAprlYn = vertAprlYn;
	}
    
	/**
	 * VW_MHC_EMP.ITG_CSMR_ID(통합고객ID) 반환.
	 * @return String - VW_MHC_EMP.ITG_CSMR_ID(통합고객ID)값
	 */
	public String getItgCsmrId() {
		return this.itgCsmrId;
	}
    
	/**
	 * VW_MHC_EMP.ITG_CSMR_ID(통합고객ID) 설정.
	 * @param itgCsmrId - VW_MHC_EMP.ITG_CSMR_ID(통합고객ID)값
	 */
	public void setItgCsmrId(String itgCsmrId) {
		this.itgCsmrId = itgCsmrId;
	}
	
	/**
	 * VW_MHC_EMP.SALC_CD(호봉구분코드) 반환.
	 * @return String - VW_MHC_EMP.SALC_CD(호봉구분코드)값
	 */
	public String getSalcCd() {
		return this.salcCd;
	}
    
	/**
	 * VW_MHC_EMP.SALC_CD(호봉구분코드) 설정.
	 * @param salcCd - VW_MHC_EMP.SALC_CD(호봉구분코드)값
	 */
	public void setSalcCd(String salcCd) {
		this.salcCd = salcCd;
	}

	public String getObstExno() {
		return obstExno;
	}

	public void setObstExno(String obstExno) {
		this.obstExno = obstExno;
	}

	public String getHopeDeptCd1() {
		return hopeDeptCd1;
	}

	public void setHopeDeptCd1(String hopeDeptCd1) {
		this.hopeDeptCd1 = hopeDeptCd1;
	}

	public String getHopeDeptCd2() {
		return hopeDeptCd2;
	}

	public void setHopeDeptCd2(String hopeDeptCd2) {
		this.hopeDeptCd2 = hopeDeptCd2;
	}

	public String getHopeDeptCd3() {
		return hopeDeptCd3;
	}

	public void setHopeDeptCd3(String hopeDeptCd3) {
		this.hopeDeptCd3 = hopeDeptCd3;
	}

	public String getHopeBegnDt() {
		return hopeBegnDt;
	}

	public void setHopeBegnDt(String hopeBegnDt) {
		this.hopeBegnDt = hopeBegnDt;
	}

	public String getHopeClseDt() {
		return hopeClseDt;
	}

	public void setHopeClseDt(String hopeClseDt) {
		this.hopeClseDt = hopeClseDt;
	}

	public String getHopeRson() {
		return hopeRson;
	}

	public void setHopeRson(String hopeRson) {
		this.hopeRson = hopeRson;
	}
	
	public String getPfmDuti() {
		return pfmDuti;
	}

	public void setPfmDuti(String pfmDuti) {
		this.pfmDuti = pfmDuti;
	}

	public String getInftSwchDt() {
		return inftSwchDt;
	}

	public void setInftSwchDt(String inftSwchDt) {
		this.inftSwchDt = inftSwchDt;
	}

	public String getPwkrSwchDt() {
		return pwkrSwchDt;
	}

	public void setPwkrSwchDt(String pwkrSwchDt) {
		this.pwkrSwchDt = pwkrSwchDt;
	}

	public String getDfpdSwchDt() {
		return dfpdSwchDt;
	}

	public void setDfpdSwchDt(String dfpdSwchDt) {
		this.dfpdSwchDt = dfpdSwchDt;
	}

	public Integer getChldNum() {
		return chldNum;
	}

	public void setChldNum(Integer chldNum) {
		this.chldNum = chldNum;
	}

	public String getMdptSttlDt() {
		return mdptSttlDt;
	}

	public void setMdptSttlDt(String mdptSttlDt) {
		this.mdptSttlDt = mdptSttlDt;
	}

	public String getWkplDivCd() {
		return wkplDivCd;
	}

	public void setWkplDivCd(String wkplDivCd) {
		this.wkplDivCd = wkplDivCd;
	}

	public String getRemk() {
		return remk;
	}

	public void setRemk(String remk) {
		this.remk = remk;
	}

	public String getYmnItnBegnDt() {
		return ymnItnBegnDt;
	}

	public void setYmnItnBegnDt(String ymnItnBegnDt) {
		this.ymnItnBegnDt = ymnItnBegnDt;
	}

	public String getYmnItnClseDt() {
		return ymnItnClseDt;
	}

	public void setYmnItnClseDt(String ymnItnClseDt) {
		this.ymnItnClseDt = ymnItnClseDt;
	}

	public String getAdjbDeptCd() {
		return adjbDeptCd;
	}

	public void setAdjbDeptCd(String adjbDeptCd) {
		this.adjbDeptCd = adjbDeptCd;
	}

	public String getAdjbPsitCd() {
		return adjbPsitCd;
	}

	public void setAdjbPsitCd(String adjbPsitCd) {
		this.adjbPsitCd = adjbPsitCd;
	}

	public String getDutiSupptDeptCd() {
		return dutiSupptDeptCd;
	}

	public void setDutiSupptDeptCd(String dutiSupptDeptCd) {
		this.dutiSupptDeptCd = dutiSupptDeptCd;
	}

	public String getFornWrkYn() {
		return fornWrkYn;
	}

	public void setFornWrkYn(String fornWrkYn) {
		this.fornWrkYn = fornWrkYn;
	}

	public String getDngrAlwcYn() {
		return dngrAlwcYn;
	}

	public void setDngrAlwcYn(String dngrAlwcYn) {
		this.dngrAlwcYn = dngrAlwcYn;
	}

	public String getRpAlwcYn() {
		return rpAlwcYn;
	}

	public void setRpAlwcYn(String rpAlwcYn) {
		this.rpAlwcYn = rpAlwcYn;
	}

	public String getWthtTxamRtDvcd() {
		return wthtTxamRtDvcd;
	}

	public void setWthtTxamRtDvcd(String wthtTxamRtDvcd) {
		this.wthtTxamRtDvcd = wthtTxamRtDvcd;
	}

	public String getBfMtrlYn() {
		return bfMtrlYn;
	}

	public void setBfMtrlYn(String bfMtrlYn) {
		this.bfMtrlYn = bfMtrlYn;
	}

	public String getFrcoDiwkYn() {
		return frcoDiwkYn;
	}

	public void setFrcoDiwkYn(String frcoDiwkYn) {
		this.frcoDiwkYn = frcoDiwkYn;
	}

	public String getDutyCd() {
		return dutyCd;
	}

	public void setDutyCd(String dutyCd) {
		this.dutyCd = dutyCd;
	}

	public String getAmasObjtYn() {
		return amasObjtYn;
	}

	public void setAmasObjtYn(String amasObjtYn) {
		this.amasObjtYn = amasObjtYn;
	}

	public String getFratDedtDivCd() {
		return fratDedtDivCd;
	}

	public void setFratDedtDivCd(String fratDedtDivCd) {
		this.fratDedtDivCd = fratDedtDivCd;
	}

	public Double getFratDedtAmt() {
		return fratDedtAmt;
	}

	public void setFratDedtAmt(Double fratDedtAmt) {
		this.fratDedtAmt = fratDedtAmt;
	}

	public String getHrngDivCd() {
		return hrngDivCd;
	}

	public void setHrngDivCd(String hrngDivCd) {
		this.hrngDivCd = hrngDivCd;
	}

	public String getDutiAlwcDivCd() {
		return dutiAlwcDivCd;
	}

	public void setDutiAlwcDivCd(String dutiAlwcDivCd) {
		this.dutiAlwcDivCd = dutiAlwcDivCd;
	}

	public String getAprtYn() {
		return aprtYn;
	}

	public void setAprtYn(String aprtYn) {
		this.aprtYn = aprtYn;
	}

	public String getAprtClseDt() {
		return aprtClseDt;
	}

	public void setAprtClseDt(String aprtClseDt) {
		this.aprtClseDt = aprtClseDt;
	}

	public String getLaunAdmsDt() {
		return launAdmsDt;
	}

	public void setLaunAdmsDt(String launAdmsDt) {
		this.launAdmsDt = launAdmsDt;
	}

	public String getRtmnRckDt() {
		return rtmnRckDt;
	}

	public void setRtmnRckDt(String rtmnRckDt) {
		this.rtmnRckDt = rtmnRckDt;
	}

	public String getCtrcBegnDt() {
		return ctrcBegnDt;
	}

	public void setCtrcBegnDt(String ctrcBegnDt) {
		this.ctrcBegnDt = ctrcBegnDt;
	}

	public String getVertAqDt() {
		return vertAqDt;
	}

	public void setVertAqDt(String vertAqDt) {
		this.vertAqDt = vertAqDt;
	}

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getPtalUsrId() {
		return ptalUsrId;
	}

	public void setPtalUsrId(String ptalUsrId) {
		this.ptalUsrId = ptalUsrId;
	}
}
