package pierp.app.mis.bizMH.common.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.common.base.dao.BaseAbstractMapper;

@Repository
public class MHSpDAO extends BaseAbstractMapper {


	private Object vactAplDivCd;



	/**
	 * MIS.PG_MH_HR
	 * SP_MHC03_GEN_EMP_NO
	 * 사원번호 생성
	 * @param empNo		: 사원번호
	 * @param stfDivCd	: 직원구분
	 * @param psitCd	: 직위
	 * @return
	 */
	public Map<String, Object> spMhc03GenEmpNo( String empNo, String stfDivCd, String psitCd ) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put( "empNo",		empNo );
		param.put( "stfDivCd",	stfDivCd );
		param.put( "psitCd",	psitCd );

	    selectByPk( "MHSp.spMhc03GenEmpNo", param );

	    return param;
	}



	/**
	 * MIS.PG_MH_RCMT
	 * SP_EMP_MST_CRTN
	 * 합격자확정 인사정보 생성
	 * @param exmNo		: 수험번호
	 * @param usrId		: 작업자ID
	 * @return
	 */
	public Map<String, Object> spMhb03Mstcrtn( String exmNo, String usrId) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put( "exmNo",		exmNo );
		param.put( "usrId",		usrId );

	    selectByPk( "MHSp.spMhb03Mstcrtn", param );

	    return param;
	}



	/**
	 * MIS.PG_MF_HMRS_LINK
	 * SP_MF_HMRS_LINK_02
	 * 근무지내출장 신청 결의서 생성
	 * @param rsolRqetNo	: 결의요청번호
	 * @param deptCd		: 부서코드
	 * @param empNo			: 사원번호
	 * @param usrId			: 사용자ID
	 * @return 결의번호
	 */
	public Map<String, Object> spMfHmrsLink02( String rsolRqetNo, String deptCd, String empNo, String usrId, String expnRqetDt, String mdulId) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put( "rsolRqetNo",	rsolRqetNo );
		param.put( "deptCd",		deptCd );
		param.put( "empNo",			empNo );
		param.put( "usrId",			usrId );
		param.put( "expnRqetDt",    expnRqetDt);
		param.put( "mdulId",   		mdulId);

	    selectByPk( "MHSp.spMfHmrsLink02", param );

	    return param;
	}

	/**
	 * MIS.PG_MF_HMRS_LINK
	 * SP_MF_HMRS_LINK_04
	 * 시간외결의생성
	 * @param rsolRqetNo	: 결의요청번호
	 * @param deptCd		: 부서코드
	 * @param empNo			: 사원번호
	 * @param fnshYm        : 마감년월
	 * @param usrId			: 사용자ID
	 * @return 결의번호
	 */
	public Map<String, Object> spMfHmrsLink04( String rsolRqetNo, String deptCd, String empNo,String fnshYm ,String usrId) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put( "rsolRqetNo",	rsolRqetNo );
		param.put( "deptCd",		deptCd );
		param.put( "empNo",			empNo );
		param.put( "fnshYm",		fnshYm );
		param.put( "usrId",			usrId );

	    selectByPk( "MHSp.spMfHmrsLink04", param );

	    return param;
	}

	/**
	 * MIS.PG_MH_HR
	 * SP_MHF01_CHECK_OFRY
	 * @param procType	: 처리구분 - 저장(S), 삭제(D), 결재(P)
	 * @param ofryAplNo	: 신청번호
	 * @param aplDivCd	: 신청구분코드(HR079) : 조퇴(1), 외출(2)
	 * @param empUniqNo	: 사원고유번호
	 * @param atteDt	: 일자
	 * @param begnPtm	: 시작시간
	 * @param clsePtm	: 종료시간
	 * @return
	 */
	public Map<String, Object> spMhf01CheckOfry( String procType, String ofryAplNo, String aplDivCd, String empUniqNo, String atteDt, String begnPtm, String clsePtm ) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("procType",	procType );
		param.put("ofryAplNo",	ofryAplNo );
		param.put("aplDivCd",	aplDivCd );
		param.put("empUniqNo",	empUniqNo );
		param.put("atteDt",		atteDt );
		param.put("begnPtm",	begnPtm);
		param.put("clsePtm",	clsePtm);

	    selectByPk( "MHSp.spMhf01CheckOfry", param );

	    return param;
	}



	/**
	 * MIS.PG_MH_HR
	 * SP_MHF01_WKPLNCRTN
	 * 근무계획 생성
	 * @param yy	: 처리년도
	 * @param usrId	: 처리자ID
	 * @return
	 */
	public Map<String, Object> spMhf01Wkplncrtn( String yy, String usrId ) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("yy",		yy );
		param.put("usrId",	usrId);

	    selectByPk( "MHSp.spMhf01Wkplncrtn", param );

	    return param;
	}



	/**
	 * MIS.PG_MH_RPAR_VACT.SP_RPAR_VACT_USE_DELT
	 * 보상휴가 사용 삭제
	 * @param vactAplNo	: 휴가신청번호
	 * @param usrId		: 사용자
	 * @return
	 */
	public Map<String, Object> spRparVactUseDelt( String vactAplNo, String usrId ) {

		Map<String, Object> param = new HashMap<String, Object>();
	    param.put( "vactAplNo",	vactAplNo );
	    param.put( "usrId",		usrId );

	    selectByPk( "MHSp.spRparVactUseDelt", param );

	    return param;
	}



	/**
	 * MIS.PG_MH_RPAR_VACT.SP_RPAR_VACT_USE_MAIN
	 * 보상휴가 사용 처리
	 * @param vactAplNo	: 휴가신청번호
	 * @param usrId		: 사용자
	 * @return
	 */
	public Map<String, Object> spRparVactUseMain( String vactAplNo, String usrId ) {

		Map<String, Object> param = new HashMap<String, Object>();
	    param.put( "vactAplNo",	vactAplNo );
	    param.put( "usrId",		usrId );

	    selectByPk( "MHSp.spRparVactUseMain", param );

	    return param;
	}



	/**
	 * MIS.PG_MH_RPAR_VACT
	 * SP_RPAR_VACT_CRTN_MAIN
	 * 보상휴가 생성
	 * @param ovtmWrkAplNo
	 * @return
	 */
	public Map<String, Object> spRparVactCrtnMain( String ovtmWrkAplNo, String usrId ) {

		Map<String, Object> param = new HashMap<String, Object>();
	    param.put( "ovtmWrkAplNo",	ovtmWrkAplNo );
	    param.put( "usrId",			usrId );

	    selectByPk( "MHSp.spRparVactCrtnMain", param );

	    return param;
	}



	/**
	 * MIS.PG_MH_RPAR_VACT
	 * SP_RPAR_VACT_DELT
	 * 보상휴가 삭제
	 * @param ovtmWrkAplNo
	 * @return
	 */
	public Map<String, Object> spRparVactDelt( String ovtmWrkAplNo, String usrId ) {

		Map<String, Object> param = new HashMap<String, Object>();
	    param.put( "ovtmWrkAplNo",	ovtmWrkAplNo );
	    param.put( "usrId",			usrId );

	    selectOne("MHSp.spRparVactDelt", param);

	    return param;
	}

	/**
	 * MIS.PG_MH_OVTM_WRK.SP_OVTM_CHECK_MAIN
	 * 시간외근무 신청 체크
	 * @param hodyDivCd		: 보상구분 1:보상휴가, 2:시간외반영
	 * @param ovtmWrkAplNo	: 신청번호
	 * @param empUniqNo		: 사원고유번호
	 * @param atteDt		: 근태일자
	 * @param begnPtm		: 시작시간
	 * @param clsePtm		: 종료시간
	 * @param forAdmin		: 관리자 여부
	 * @return
	 */
	public Map<String, Object> spMhf04Chk( String hodyDivCd, String ovtmWrkAplNo, String empUniqNo, String atteDt, String begnPtm, String clsePtm, boolean forAdmin ) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hodyDivCd",	hodyDivCd );
		map.put("ovtmWrkAplNo",	ovtmWrkAplNo );
		map.put("empUniqNo",	empUniqNo );
		map.put("atteDt",		atteDt );
		map.put("begnPtm",		begnPtm );
		map.put("clsePtm",		clsePtm );
		map.put("forAdmin",		(forAdmin)?"Y":"N" );

		selectByPk( "MHSp.spMhf04Chk", map );

		return map;
	}


	/**
	 * MIS.PG_MH_OVTM_WRK.SP_OVTM_CHECK_WITH_HOUR
	 * 시간외근무 신청 체크
	 * @param hodyDivCd		: 보상구분 1:보상휴가, 2:시간외반영
	 * @param ovtmWrkAplNo	: 신청번호
	 * @param empUniqNo		: 사원고유번호
	 * @param atteDt		: 근태일자
	 * @param begnPtm		: 시작시간
	 * @param clsePtm		: 종료시간
	 * @param aplPtm		: 총근무시간(수)
	 * @param forAdmin		: 관리자 여부
	 * @return
	 */
	public Map<String, Object> spMhf05Chk( String hodyDivCd, String ovtmWrkAplNo, String empUniqNo, String atteDt, String begnPtm, String clsePtm, String aplPtm, boolean forAdmin ) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hodyDivCd",	hodyDivCd );
		map.put("ovtmWrkAplNo",	ovtmWrkAplNo );
		map.put("empUniqNo",	empUniqNo );
		map.put("atteDt",		atteDt );
		map.put("begnPtm",		begnPtm );
		map.put("clsePtm",		clsePtm );
		map.put("aplPtm",		aplPtm );
		map.put("forAdmin",		(forAdmin)?"Y":"N" );

		selectByPk( "MHSp.spMhf05Chk", map );

		return map;
	}



	/**
	 * MIS.PG_MF_HMRS_LINK
	 * SP_MF_TRXP_APL
	 * 출장 발의 생성/수정/삭제
	 * @param bstrAplNo		: 출장신청번호
	 * @param expnMontNo	: 발의번호
	 * @param gubun			: 처리구분(1:저장, 2:삭제)
	 * @param bstrDivCd		: 출장구분
	 * @return
	 */
	public Map<String, Object> spTrxpAplMontNo( String bstrAplNo, String expnMotnNo, String gubun, String bstrDivCd, String mdulId ) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bstrAplNo",	bstrAplNo );
		map.put("expnMotnNo",	expnMotnNo );
		map.put("gubun",		gubun );
		map.put("bstrDivCd",	bstrDivCd );
		map.put("mdulId",		mdulId );
		
		selectByPk("MHSp.spTrxpAplMontNo", map);

		return map;
	}



	/**
	 * MIS.PG_MH_HR
	 * SP_MHF01_GET_FWP
	 * 인사 선택, 단시간 근무 신청 가능여부 확인 SP
	 * @param gb		: 구분("1":선택근무, "2":단시간근무)
	 * @param fwpAplNo	: 신청번호
	 * @param empUniqNo : 사원고유번호
	 * @param begnYm	: 시작년월
	 * @param begnPtm	: 시작시간
	 * @param clseYm	: 종료년월
	 * @param clsePtm	: 종료시간
	 * @return
	 */
	public Map<String,Object> spMhf01GetFwp( String gb, String fwpAplNo, String empUniqNo, String begnYm, String begnPtm, String clseYm, String clsePtm){

		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "gb",			gb );
		map.put( "fwpAplNo",	fwpAplNo );
		map.put( "empUniqNo",	empUniqNo );
		map.put( "begnYm",		begnYm );
		map.put( "begnPtm",		begnPtm );
		map.put( "clseYm",		clseYm );
		map.put( "clsePtm",		clsePtm );

		selectByPk( "MHSp.spMhf01GetFwp", map );

		return map;
	}



	/**
	 * MIS.PG_MH_HR
	 * SP_MHJ01_CHECK_TRXP
	 * 인사 출장신청 가능여부 확인 SP
	 * @param procType	: 처리구분 - 저장(S), 삭제(D), 결재(P)
	 * @param bstrAplNo	: 출장신청번호
	 * @param bstrDivCd	: 출장구분코드(HR096): 국내(1), 국외(2)
	 * @param empUniqNo	: 사원고유번호
	 * @param begnDt	: 시작일자
	 * @param clseDt	: 종료일자
	 * @return
	 */
	public Map<String,Object> spMhj01CheckTrxp( String procType, String bstrAplNo, String bstrDivCd, String empUniqNo, String begnDt, String clseDt ) {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "procType",	procType );
		map.put( "bstrAplNo",	bstrAplNo );
		map.put( "bstrDivCd",	bstrDivCd );
		map.put( "empUniqNo",	empUniqNo );
		map.put( "begnDt",		begnDt );
		map.put( "clseDt",		clseDt );

		selectByPk("MHSp.spMhj01CheckTrxp", map);

		return map;
	}



	/**
	 * MIS.PG_MH_HMPG_LINK
	 * SP_MHB01_RCMTPBNCLINK
	 * 인사 채용(채용공고) 연계정보 처리 SP
	 * @param linkDoctNo
	 * @param linkId
	 * @return
	 */
	public Map<String,Object> spMhb01RcmtPbncLink( String linkDoctNo, String linkId ){

		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "linkDoctNo",	linkDoctNo );
		map.put( "linkId",		linkId );
		selectByPk( "MHSp.spMhb01RcmtPbncLink", map );

		return map;
	}



	/**
	 * MIS.PG_MH_HMPG_LINK
	 * SP_MHB01_RCMTBASCLINK
	 * 인사 채용(채용기본) 연계정보 처리 SP
	 * @param linkDoctNo
	 * @param linkId
	 * @return
	 */
	public Map<String, Object> spMhb01RcmtBascLink(String linkDoctNo, String linkId) {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "linkDoctNo",	linkDoctNo );
		map.put( "linkId",		linkId );
		selectByPk( "MHSp.spMhb01RcmtBascLink", map );

		return map;
	}



	/**
	 * MIS.PG_MH_HMPG_LINK
	 * SP_MHB01_SFTDLINK
	 * 인사 채용(자기소개) 연계정보 처리 SP
	 * @param linkDoctNo
	 * @param linkId
	 * @return
	 */
	public Map<String, Object> spMhb01SftdLink(String linkDoctNo, String linkId) {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "linkDoctNo",	linkDoctNo );
		map.put( "linkId",		linkId );
		selectByPk( "MHSp.spMhb01SftdLink", map );

		return map;
	}



	/**
	 * MIS.PG_MH_HMPG_LINK
	 * SP_MHB01_RCMTSCRELINK
	 * 인사 채용(학력사항) 연계정보 처리 SP
	 * @param linkDoctNo
	 * @param linkId
	 * @return
	 */
	public Map<String, Object> spMhb01RcmtScreLink(String linkDoctNo, String linkId) {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "linkDoctNo",	linkDoctNo );
		map.put( "linkId",		linkId );
		selectByPk( "MHSp.spMhb01RcmtScreLink", map );

		return map;
	}



	/**
	 * MIS.PG_MH_HMPG_LINK
	 * SP_MHB01_RCMTCARRLINK
	 * 인사 채용(경력사항) 연계정보 처리 SP
	 * @param linkDoctNo
	 * @param linkId
	 * @return
	 */
	public Map<String, Object> spMhb01RcmtCarrLink(String linkDoctNo, String linkId) {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "linkDoctNo",	linkDoctNo );
		map.put( "linkId",		linkId );
		selectByPk( "MHSp.spMhb01RcmtCarrLink", map );

		return map;
	}



	/**
	 * MIS.PG_MH_HMPG_LINK
	 * SP_MHB01_RCMTFRLNLINK
	 * 인사 채용(외국어사항) 연계정보 처리 SP
	 * @param linkDoctNo
	 * @param linkId
	 * @return
	 */
	public Map<String, Object> spMhb01RcmtFrlnLink(String linkDoctNo, String linkId) {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "linkDoctNo",	linkDoctNo );
		map.put( "linkId",		linkId );
		selectByPk( "MHSp.spMhb01RcmtFrlnLink", map );

		return map;
	}



	/**
	 * MIS.PG_MH_HMPG_LINK
	 * SP_MHB01_RCMTCRTILINK
	 * 인사 채용(자격) 연계정보 처리 SP
	 * @param linkDoctNo
	 * @param linkId
	 * @return
	 */
	public Map<String, Object> spMhb01RcmtCrtiLink(String linkDoctNo, String linkId) {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "linkDoctNo",	linkDoctNo );
		map.put( "linkId",		linkId );
		selectByPk( "MHSp.spMhb01RcmtCrtiLink", map );

		return map;
	}



	/**
	 * MIS.PG_MH_HMPG_LINK
	 * SP_MHB01_RCMTPBNCSTNDLINK
	 * 인사 채용(개요기준) 연계정보 처리 SP
	 * @param linkDoctNo
	 * @param linkId
	 * @throws Throwable
	 */
	public Map<String, Object> spMhb01RcmtPbncStndLink(String linkDoctNo, String linkId) {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "linkDoctNo",	linkDoctNo );
		map.put( "linkId",		linkId );
		selectByPk( "MHSp.spMhb01RcmtPbncStndLink", map );

		return map;
	}



	/**
	 * MIS.PG_MH_HMPG_LINK
	 * SP_MHLINK_INSERT_LOG
	 * 인사 연계 로그 저장( 개별 트랜잭션임 )
	 * @param linkDoctNo
	 * @param linkId
	 * @param trptDivCd
	 * @param trptRslt
	 * @return
	 */
	public Map<String, Object> spMhLinkInsertLog(String linkDoctNo, String linkId, String trptDivCd, String trptRslt ) {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "linkDoctNo",	linkDoctNo );
		map.put( "linkId",		linkId );
		map.put( "trptDivCd",	trptDivCd );
		map.put( "trptRslt",	trptRslt );
		selectByPk( "MHSp.spMhLinkInsertLog", map );

		return map;
	}



	/**
	 * MIS.PG_MH_EHRD_LINK
	 * SP_MHC03_SCRELINK_DEL
	 * 인사 학력사항 삭제연계처리
	 * @param empUniqNo
	 * @param screSrno
	 * @return
	 */
	public Map<String, Object> spMhc03ScrelinkDel(String empUniqNo, Long screSrno ) {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "empUniqNo",	empUniqNo );
		map.put( "screSrno",	screSrno );
		selectByPk( "MHSp.spMhc03ScrelinkDel", map );

		return map;
	}



	/**
	 * MIS.PG_MH_EHRD_LINK
	 * SP_MHC03_FRNRLINK_DEL
	 * 인사 언어사항 삭제연계처리
	 * @param empUniqNo
	 * @param screSrno
	 * @return
	 */
	public Map<String, Object> spMhc03FrnrlinkDel(String empUniqNo, Long frlnSrno ) {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "empUniqNo",	empUniqNo );
		map.put( "frlnSrno",	frlnSrno );
		selectByPk( "MHSp.spMhc03FrnrlinkDel", map );

		return map;
	}



	/**
	 * MIS.PG_MH_EHRD_LINK
	 * SP_MHC03_SRTIMATTLINK_DEL
	 * 인사 자격면허사항 삭제연계처리
	 * @param empUniqNo
	 * @param crtiSrno
	 * @return
	 */
	public Map<String, Object> spMhc03SrtimattlinkDel(String empUniqNo, Long crtiSrno ) {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "empUniqNo",	empUniqNo );
		map.put( "crtiSrno",	crtiSrno );
		selectByPk( "MHSp.spMhc03SrtimattlinkDel", map );

		return map;
	}



	/**
	 * MIS.PG_MH_EHRD_LINK
	 * SP_MHC03_REWADISALINK_DEL
	 * 인사 포상징계사항 삭제연계처리
	 * @param rewaDisaSrno
	 * @return
	 */
	public Map<String, Object> spMhc03RewadisalinkDel(String rewaDisaSrno ) {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "rewaDisaSrno",	rewaDisaSrno );
		selectByPk( "MHSp.spMhc03RewadisalinkDel", map );

		return map;
	}



	/**
	 * MIS.PG_MH_HR
	 * SP_MHF02_CHECK_VACT
	 * 휴가 신청 체크
	 * @param procType		: 처리구분 - 저장(S), 삭제(D), 결재(P)
	 * @param vactAplNo		: 휴가신청번호
	 * @param vactDivCd		: 휴가신청구분코드
	 * @param empUniqNo		: 사원고유번호
	 * @param begnDt		: 시작일자
	 * @param vactBegnDtm	: 시작시간
	 * @param clseDt		: 종료일자
	 * @param vactClseDtm	: 종료시간
	 * @throws Throwable
	 */
	public Map<String, Object> spMhf02CheckVact(String procType, String vactAplNo, String vactDivCd, String empUniqNo, String begnDt, String vactBegnDtm, String clseDt, String vactClseDtm, String aplDivCd) {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "procType",	procType );
		map.put( "vactAplNo",	vactAplNo );
		map.put( "vactDivCd",	vactDivCd );
		map.put( "empUniqNo",	empUniqNo );
		map.put( "begnDt",		begnDt );
		map.put( "vactBegnDtm",	vactBegnDtm );
		map.put( "clseDt",		clseDt );
		map.put( "vactClseDtm",	vactClseDtm );
		map.put( "aplDivCd", aplDivCd );
		selectByPk( "MHSp.spMhf02CheckVact", map );

		return map;
	}



	/**
	 * MIS.PG_MH_HR
	 * SP_MHJ01_CHECK_BSTR
	 * 인사 근무지내출장신청 가능여부 확인 SP
	 * @param procType	: 처리구분 - 저장(S), 삭제(D), 결재(P)
	 * @param aplNo		: 출장신청번호
	 * @param empUniqNo	: 사원고유번호
	 * @param atteDt	: 출장일자
	 * @param begnPtm	: 시작시간
	 * @param clsePtm	: 종료시간
	 * @return
	 */
	public Map<String,Object> spMhj01CheckBstr( String procType, String aplNo, String empUniqNo, String atteDt, String begnPtm, String clsePtm ) {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "procType",	procType );
		map.put( "aplNo",		aplNo );
		map.put( "empUniqNo",	empUniqNo );
		map.put( "atteDt",		atteDt );
		map.put( "begnPtm",		begnPtm );
		map.put( "clsePtm",		clsePtm );

		selectByPk("MHSp.spMhj01CheckBstr", map);

		return map;
	}



	/**
	 * MIS.PG_MH_HR
	 * SP_MHF04_CHECK_OVTM_WRK
	 * 시간외근무신청 승인 CHECK
	 * @param ovtmWrkAplNo
	 * @return
	 */
	public Map<String, Object> spMhf04CheckOvtmWrk( String ovtmWrkAplNo ) {

		Map<String, Object> param = new HashMap<String, Object>();
	    param.put( "ovtmWrkAplNo", ovtmWrkAplNo );

	    selectByPk( "MHSp.spMhf04CheckOvtmWrk", param );

	    return param;
	}



	/**
	 * MIS.PG_MF_HMRS_LINK
	 * SP_MF_HMRS_LINK_03
	 * 복명신청시 발의정정 처리
	 * @param rptmAplNo
	 * @param gubun(1:저장, 2:결재완료)
	 * @return
	 * @throws Throwable
	 */
	public Map<String, Object> spMfHmrsLink03(String rptmAplNo, String gubun) {

		Map<String, Object> param = new HashMap<String, Object>();
	    param.put( "rptmAplNo", rptmAplNo );
	    param.put( "gubun",		gubun );

	    selectByPk( "MHSp.spMfHmrsLink03", param );

	    return param;
	}



	/**
	 * MIS.PG_MH_APNT
	 * SP_APNT_GW_HTML_CRTN
	 * 발령 전자결재 문서(HTML) 생성
	 * spApntGWHtmlCrtn
	 * @param list
	 * @return
	 */
	
//	public Map<String, Object> spApntGWHtmlCrtn( List<TbMhApntKey> list ) {
//
//		StringBuffer sbParam = new StringBuffer();
//		for( TbMhApntKey key : list ){
//			sbParam.append( "," + key.getEmpUniqNo() + key.getApntSrno().toString() );
//		}
//
//		Map<String, Object> param = new HashMap<String, Object>();
//	    param.put( "keys",		sbParam.substring(1) );
//
//	    selectByPk( "MHSp.spApntGWHtmlCrtn", param );
//
//
//		return param;
//	}


	/**
	 * MIS.PG_MF_HMRS_LINK
	 * SP_MF_RPTM_APL
	 * 근무지외출장정산 저장, 삭제 시 호출되는 프로시저
	 * @param rptmAplNo
	 * @param gubun(1:저장, 2:결재완료)
	 * @return
	 * @throws Throwable
	 */
	public Map<String, Object> spMfRptmApl(String rptmAplNo, String bstrAplNo, String igubun, String bgubun, String expnRqetDt, String mdulId) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("rptmAplNo", rptmAplNo);
		param.put("bstrAplNo", bstrAplNo);
		param.put("igubun", igubun);
		param.put("bgubun", bgubun);
		param.put("expnRqetDt", expnRqetDt);
		param.put("mdulId", mdulId);

		selectByPk( "MHSp.spMfRptmApl", param );

	    return param;
	}

	/**
	 * MIS.PG_MF_HMRS_LINK
	 * SP_MF_RPTM_APL
	 * 근무지외출장정산 저장, 삭제 시 호출되는 프로시저
	 * @param rptmAplNo
	 * @param gubun(1:저장, 2:결재완료)
	 * @return
	 * @throws Throwable
	 */
	public Map<String, Object> spRptmSavecheckMain(String procType, String rptmAplNo,String bgubun) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put( "procType",	procType );
		param.put( "rptmAplNo", rptmAplNo );
	    param.put( "bgubun",	bgubun );
	    selectByPk( "MHSp.spRptmSavecheckMain", param );

	    return param;
	}

	/**
	 * MIS.PG_MH_ATTE
	 * SP_ATTE_ATTE_RECORD
	 * 일자별 출퇴근시간 집계
	 * @param atteDt
	 * @return
	 * @throws Throwable
	 */
	public Map<String, Object> spAtteAtteRecord(String atteDt, String atteDt2,String usrId,String atteJnrlYn) {
		Map<String, Object> param = new HashMap<String, Object>();
	    param.put( "atteDt", atteDt );
	    param.put( "atteDt2", atteDt2 );
	    param.put( "usrId", usrId );
	    param.put( "atteJnrlYn", atteJnrlYn );
	    selectByPk( "MHSp.spAtteAtteRecord", param );

	    return param;
	}



	public Map<String, Object> spOvtmWrkAplOtPay(String date, String rgtrId) {
		Map<String, Object> param = new HashMap<String, Object>();
	    param.put( "date", date );
	    param.put( "rgtrId", rgtrId );
	    selectByPk( "MHSp.spOvtmWrkAplOtPay", param );

	    return param;
	}



	public Map<String, Object> spMhEduAppr(String rsltReptNo, String usrId) {
		Map<String, Object> param = new HashMap<String, Object>();
	    param.put( "rsltReptNo", rsltReptNo );
	    param.put( "usrId", usrId );
	    selectByPk( "MHSp.spMhEduAppr", param );

	    return param;
	}



	public Map<String, Object> spTrxpSavecheckMain(String pROC_TYPE, String bstrAplNo, String bSTR_DIV_CD) {
		Map<String, Object> param = new HashMap<String, Object>();
	    param.put( "procType", pROC_TYPE );
	    param.put( "trxpAplNo", bstrAplNo );
	    param.put( "bstrDivCd", bSTR_DIV_CD );
	    selectByPk( "MHSp.spTrxpSavecheckMain", param );

	    return param;
	}



	public Map<String, Object> spMhEmpBdgtChk(String gubn, String aplNo, String usrId) {
		Map<String, Object> param = new HashMap<String, Object>();
	    param.put( "gubn", gubn );
	    param.put( "aplNo", aplNo );
	    param.put( "usrId", usrId );
	    selectByPk( "MHSp.spMhEmpBdgtChk", param );

	    return param;
	}

	public Map<String, Object> spTbMhHireempSaveChk(String gubn, String aplNo, String usrId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put( "gubn", gubn );
		param.put( "aplNo", aplNo );
		param.put( "usrId", usrId );
		selectByPk( "MHSp.spTbMhHireempSaveChk", param );

		return param;
	}

	public Map<String, Object> spEmpHisInsert(String empUniqNo, String usrId) {
		Map<String, Object> param = new HashMap<String, Object>();
	    param.put( "empUniqNo", empUniqNo );
	    param.put( "usrId", usrId );
	    selectByPk( "MHSp.spEmpHisInsert", param );

	    return param;
	}

	public Map<String, Object> spMfTrxpApl03(String bstrAplNo, String gubun, String expnRqetDt) {

		Map<String, Object> param = new HashMap<String, Object>();
	    param.put( "bstrAplNo", bstrAplNo );
	    param.put( "gubun",		gubun );
	    param.put( "expnRqetDt", expnRqetDt);
	    selectByPk( "MHSp.spMfTrxpApl03", param );

	    return param;
	}


	public Map<String, Object> spMfTrxpApl04(String bstrAplNo, String stafSrno) {

		Map<String, Object> param = new HashMap<String, Object>();
	    param.put( "bstrAplNo", bstrAplNo );
	    param.put( "stafSrno",		stafSrno );

	    selectByPk( "MHSp.spMfTrxpApl04", param );

	    return param;
	}

	public Map<String, Object> spMhEduApprGvb( String rsltReptNo, String permDivCd, String usrId ) {
		Map<String, Object> param = new HashMap<String, Object>();
	    param.put( "rsltReptNo", rsltReptNo );
	    param.put( "permDivCd", permDivCd );
	    param.put( "usrId", usrId );
	    selectByPk( "MHSp.spMhEduApprGvb", param );

	    return param;
	}

	/**
	 * MIS.PG_MH_RTMN
	 * SP_RTMN_APL_CFM_CRTN
	 * 퇴직신청 확인자료 생성
	 * @param empUniqNo		: 퇴직자사원고유번호
	 * @param usrId			: 작업자ID
	 * @throws Throwable
	 */
	public Map<String, Object> spMhg05RtmnAplCfmCrtn(String empUniqNo, String usrId) {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "empUniqNo",	empUniqNo );
		map.put( "usrId",	usrId );
		selectByPk( "MHSp.spMhg05RtmnAplCfmCrtn", map );

		return map;
	}

		/**
	 * MIS.PG_MH_ODBY
	 * SP_ODBY_USE_VACT
	 * 연차저축휴가 사용/사용취소
	 *
	 * @param empUniqNo : 직원고유번호
	 * @param procDvcd  : 처리구분('1': 사용, '2': 취소)
	 * @param vactAplNo : 휴가신청번호
	 * @param vactDys   : 휴가사용일수
	 * @param rgtrId    : 작업자ID
	 * @throws Throwable
	 */
	public Map<String, Object> spMhOdbyUseVact(String empUniqNo, String procDvcd, String vactAplNo, Double vactDys, String rgtrId) throws EgovBizException {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("empUniqNo",	empUniqNo);
		map.put("procDvcd",	    procDvcd);
		map.put("vactAplNo",	vactAplNo);
		map.put("vactDys",		vactDys);
		map.put("rgtrId",		rgtrId);
		selectByPk( "MHSp.spMhOdbyUseVact", map );

		return map;
	}

	/**
	 * 가족돌봄휴가 사용가능여부 체크
	 * @param empUniqNo
	 * @param vactAplNo
	 * @return
	 * @throws Throwable
	 */
	public Map<String, Object> spMhCheckFmlyCareVact(String empUniqNo, String vactAplNo) throws Throwable {

		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("empUniqNo",	empUniqNo);
		map.put("vactAplNo",	vactAplNo);
		
		selectOne("MHSp.spMhCheckFmlyCareVact", map);

		return map;
	}

	/**
	 * 가족돌봄휴가 사용가능여부 체크
	 * @param empUniqNo
	 * @param vactAplNo
	 * @return
	 * @throws Throwable
	 */
	public Map<String, Object> spMhCheckChldCareVact(String empUniqNo, String vactAplNo) throws Throwable {

		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("empUniqNo",	empUniqNo);
		map.put("vactAplNo",	vactAplNo);
		
		selectOne("MHSp.spMhCheckChldCareVact", map);

		return map;
	}
	
	/**
	 * 대체휴일 관련 프로시저
	 * @param vactAplNo
	 * @param procDivCd
	 * @param usrId
	 * @return
	 * @throws Throwable
	 */
	public Map<String, Object> spRplmVactProc(String vactAplNo, String procDivCd, String usrId) throws EgovBizException {

		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("vactAplNo", vactAplNo);
		map.put("procDivCd", procDivCd);
		map.put("usrId", usrId);
		
		selectOne("MHSp.spRplmVactProc", map);

		return map;
	}


	/**
	 * 연속휴가 저장 가능 체크 프로시저
	 * @param procDivCd
	 * @param scsvVactAplNo
	 * @return
	 * @throws Throwable
	 */
	public Map<String, Object> spCheckScsvVactSave(String procDivCd, String scsvVactAplNo) throws Throwable {

		Map<String,Object> map = new HashMap<String,Object>();

		map.put("procDivCd", procDivCd);
		map.put("scsvVactAplNo", scsvVactAplNo);
		
		selectOne("MHSp.spCheckScsvVactSave", map);

		return map;
	}
	
	/**
	 * 대체휴일 관련 프로시저
	 * @param procDivCd
	 * @param vactAplNo
	 * @param usrId
	 * @return
	 * @throws Throwable
	 */
	public Map<String, Object> spPrtnPtmVactProc(String procDivCd, String vactAplNo, String usrId) throws EgovBizException {

		Map<String,Object> map = new HashMap<String,Object>();

		map.put("procDivCd", procDivCd);
		map.put("vactAplNo", vactAplNo);
		map.put("usrId", usrId);
		
		selectOne("MHSp.spPrtnPtmVactProc", map);

		return map;
	}

	/**
	 * 모성보호 사용가능여부 체크
	 * @param vactAplNo
	 * @return
	 * @throws Throwable
	 */
	public Map<String, Object> spMhCheckMthdPrtcVact(String vactAplNo) throws Throwable {

		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("vactAplNo",	vactAplNo);
		
		selectOne("MHSp.spMhCheckMthdPrtcVact", map);

		return map;
	}

	/**
	 * 휴가 최소 근무시간 충족 여부 체크
	 * @param vactAplNo
	 * @param ofryAplNo
	 * @return
	 * @throws Throwable
	 */
	public Map<String, Object> spMhf02CheckVactMinWrkPtm(String vactAplNo, String ofryAplNo) throws EgovBizException {

		Map<String,Object> map = new HashMap<String,Object>();

		map.put("vactAplNo", vactAplNo);
		map.put("ofryAplNo", ofryAplNo);
		
		selectOne("MHSp.spMhf02CheckVactMinWrkPtm", map);

		return map;
	}

}
