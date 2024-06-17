package pierp.app.mis.bizMH.common.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import pierp.app.mis.common.table.model.TbMfRsolMstKey;
import pierp.app.mis.common.table.model.TbMhApntKey;






public interface MHSpService {

	/**
	 * MIS.PG_MH_HR
	 * SP_MHC03_GEN_EMP_NO
	 * 직원번호 생성
	 * @param empNo		: 직원번호
	 * @param stfDivCd	: 직원구분
	 * @param psitCd	: 직위
	 * @return
	 */
	String spMhc03GenEmpNo( String empNo, String stfDivCd, String psitCd ) throws Throwable;


	/**
	 * PG_MH_APNT.SP_APNT_HIS_SET
	 * SP_MHC03_GEN_EMP_NO
	 * 직원번호시 history 생성
	 * @param empUniqNo		: 직원번호
	 * @param apntDivCd		: 발령코드
	 * @param usrId			: 입력자
	 * @return
	 */
	void spEmpHisInsert( String empUniqNo, String usrId ) throws Throwable;


	/**
	 * MIS.PG_MH_HR
	 * SP_MHB03_MSTCRTN
	 * 합격자확정 인사정보 생성
	 * @param exmNo		: 수험번호
	 * @param empNo		: 직원번호
	 * @param usrId		: 작업자ID
	 * @return
	 */
	void spMhb03Mstcrtn( String exmNo, String usrId ) throws Throwable;



	/**
	 * MIS.PG_MF_HMRS_LINK
	 * SP_MF_HMRS_LINK_02
	 * 근무지내출장 신청 결의서 생성
	 * @param rsolRqetNo	: 결의요청번호
	 * @param deptCd		: 부서코드
	 * @param empNo			: 직원번호
	 * @param usrId			: 사용자ID
	 * @return 결의마스터 키
	 */
	TbMfRsolMstKey spMfHmrsLink02( String rsolRqetNo, String deptCd, String empNo, String usrId, String expnRqetDt, String mdulId ) throws Throwable;


	TbMfRsolMstKey spMfHmrsLink04( String rsolRqetNo, String deptCd, String empNo, String fnshYm ,String usrId ) throws Throwable;



	/**
	 * MIS.PG_MH_HR
	 * SP_MHF01_CHECK_OFRY
	 * @param procType	: 처리구분 - 저장(S), 삭제(D), 결재(P)
	 * @param ofryAplNo	: 신청번호
	 * @param aplDivCd	: 신청구분코드(HR079) : 조퇴(1), 외출(2)
	 * @param empUniqNo	: 직원고유번호
	 * @param atteDt	: 일자
	 * @param begnPtm	: 시작시간
	 * @param clsePtm	: 종료시간
	 * @return
	 */
	void spMhf01CheckOfry( String procType, String ofryAplNo, String aplDivCd, String empUniqNo, String atteDt, String begnPtm, String clsePtm ) throws EgovBizException;



	/**
	 * MIS.PG_MH_HR
	 * SP_MHF01_WKPLNCRTN
	 * 근무계획 생성
	 * @param yy	: 처리년도
	 * @param usrId	: 처리자ID
	 * @return
	 */
	void spMhf01Wkplncrtn( String yy, String usrId ) throws Throwable;



	/**
	 * MIS.PG_MH_RPAR_VACT.SP_RPAR_VACT_USE_DELT
	 * 보상휴가 사용 삭제
	 * @param vactAplNo	: 휴가신청번호
	 * @param usrId		: 사용자
	 * @return
	 */
	void spRparVactUseDelt( String vactAplNo, String usrId ) throws EgovBizException;



	/**
	 * MIS.PG_MH_RPAR_VACT.SP_RPAR_VACT_USE_MAIN
	 * 보상휴가 사용 처리
	 * @param vactAplNo	: 휴가신청번호
	 * @param usrId		: 사용자
	 * @return
	 */
	void spRparVactUseMain( String vactAplNo, String usrId ) throws EgovBizException;



	/**
	 * MIS.PG_MH_RPAR_VACT
	 * SP_RPAR_VACT_CRTN_MAIN
	 * 보상휴가 생성
	 * @param ovtmWrkAplNo
	 * @return
	 */
	void spRparVactCrtnMain( String ovtmWrkAplNo, String usrId ) throws EgovBizException;



	/**
	 * MIS.PG_MH_RPAR_VACT
	 * SP_RPAR_VACT_DELT
	 * 보상휴가 삭제
	 * @param ovtmWrkAplNo
	 * @return
	 */
	void spRparVactDelt( String ovtmWrkAplNo, String usrId ) throws EgovBizException;



	/**
	 * MIS.PG_MH_OVTM_WRK.SP_OVTM_CHECK_MAIN
	 * 시간외근무 신청 체크
	 * @param hodyDivCd		: 보상구분 1:보상휴가, 2:시간외반영
	 * @param ovtmWrkAplNo	: 신청번호
	 * @param empUniqNo		: 직원고유번호
	 * @param atteDt		: 근태일자
	 * @param begnPtm		: 시작시간
	 * @param clsePtm		: 종료시간
	 * @param forAdmin		: 관리자 여부
	 * @return
	 */
	void spMhf04Chk( String hodyDivCd, String ovtmWrkAplNo, String empUniqNo, String atteDt, String begnPtm, String clsePtm, boolean forAdmin ) throws EgovBizException;

	/**
	 * MIS.PG_MH_OVTM_WRK.SP_OVTM_CHECK_WITH_HOUR
	 * 시간외근무 신청 체크
	 * @param hodyDivCd		: 보상구분 1:보상휴가, 2:시간외반영
	 * @param ovtmWrkAplNo	: 신청번호
	 * @param empUniqNo		: 직원고유번호
	 * @param atteDt		: 근태일자
	 * @param begnPtm		: 시작시간
	 * @param clsePtm		: 종료시간
	 * @param forAdmin		: 관리자 여부
	 * @return
	 */
	void spMhf05Chk( String hodyDivCd, String ovtmWrkAplNo, String empUniqNo, String atteDt, String begnPtm, String clsePtm, String aplPtm, boolean forAdmin ) throws Throwable;



	/**
	 * MIS.PG_MF_HMRS_LINK
	 * SP_MF_TRXP_APL
	 * 출장 발의 생성/수정/삭제
	 * @param bstrAplNo		: 출장신청번호
	 * @param expnMontNo	: 발의번호
	 * @param gubun			: 처리구분(1:저장, 2:삭제)
	 * @param bstrDivCd		: 출장구분
	 * @param mdulId		: 모듈ID
	 * @return : 정상처리시 발의번호
	 */
	String spTrxpAplMontNo( String bstrAplNo, String expnMontNo, String gubun, String bstrDivCd, String mdulId ) throws Throwable;



	/**
	 * MIS.PG_MH_HR
	 * SP_MHF01_GET_FWP
	 * 인사 선택, 단시간 근무 신청 가능여부 확인 SP
	 * @param gb		: 구분("1":선택근무, "2":단시간근무)
	 * @param fwpAplNo	: 신청번호
	 * @param empUniqNo : 직원고유번호
	 * @param begnYm	: 시작년월
	 * @param begnPtm	: 시작시간
	 * @param clseYm	: 종료년월
	 * @param clsePtm	: 종료시간
	 * @throws Throwable
	 */
	void spMhf01GetFwp( String gb, String fwpAplNo, String empUniqNo, String begnYm, String begnPtm, String clseYm, String clsePtm) throws Throwable;





	/**
	 * MIS.PG_MH_HR
	 * SP_MHJ01_CHECK_TRXP
	 * 인사 출장신청 가능여부 확인 SP
	 * @param procType	: 처리구분 - 저장(S), 삭제(D), 결재(P)
	 * @param bstrAplNo	: 출장신청번호
	 * @param bstrDivCd	: 출장구분코드(HR096): 국내(1), 국외(2)
	 * @param empUniqNo	: 직원고유번호
	 * @param begnDt	: 시작일자
	 * @param clseDt	: 종료일자
	 * @return
	 */
	void spMhj01CheckTrxp( String procType, String bstrAplNo, String bstrDivCd, String empUniqNo, String begnDt, String clseDt ) throws Throwable;



	/**
	 * MIS.PG_MH_HMPG_LINK
	 * SP_MHB01_RCMTPBNCLINK
	 * 인사 채용(채용공고) 연계정보 처리 SP
	 * @param linkDoctNo
	 * @param linkId
	 * @throws Throwable
	 */
	void spMhb01RcmtPbncLink( String linkDoctNo, String linkId ) throws Throwable;



	/**
	 * MIS.PG_MH_HMPG_LINK
	 * SP_MHB01_RCMTBASCLINK
	 * 인사 채용(기본정보) 연계정보 처리 SP
	 * @param linkDoctNo
	 * @param linkId
	 * @throws Throwable
	 */
	void spMhb01RcmtBascLink( String linkDoctNo, String linkId ) throws Throwable;



	/**
	 * MIS.PG_MH_HMPG_LINK
	 * SP_MHB01_SFTDLINK
	 * 인사 채용(자기소개) 연계정보 처리 SP
	 * @param linkDoctNo
	 * @param linkId
	 * @throws Throwable
	 */
	void spMhb01SftdLink( String linkDoctNo, String linkId ) throws Throwable;



	/**
	 * MIS.PG_MH_HMPG_LINK
	 * SP_MHB01_RCMTSCRELINK
	 * 인사 채용(학력사항) 연계정보 처리 SP
	 * @param linkDoctNo
	 * @param linkId
	 * @throws Throwable
	 */
	void spMhb01RcmtScreLink( String linkDoctNo, String linkId ) throws Throwable;



	/**
	 * MIS.PG_MH_HMPG_LINK
	 * SP_MHB01_RCMTCARRLINK
	 * 인사 채용(경력사항) 연계정보 처리 SP
	 * @param linkDoctNo
	 * @param linkId
	 * @throws Throwable
	 */
	void spMhb01RcmtCarrLink( String linkDoctNo, String linkId ) throws Throwable;



	/**
	 * MIS.PG_MH_HMPG_LINK
	 * SP_MHB01_RCMTFRLNLINK
	 * 인사 채용(외국어) 연계정보 처리 SP
	 * @param linkDoctNo
	 * @param linkId
	 * @throws Throwable
	 */
	void spMhb01RcmtFrlnLink( String linkDoctNo, String linkId ) throws Throwable;



	/**
	 * MIS.PG_MH_HMPG_LINK
	 * SP_MHB01_RCMTCRTILINK
	 * 인사 채용(자격) 연계정보 처리 SP
	 * @param linkDoctNo
	 * @param linkId
	 * @throws Throwable
	 */
	void spMhb01RcmtCrtiLink( String linkDoctNo, String linkId ) throws Throwable;



	/**
	 * MIS.PG_MH_HMPG_LINK
	 * SP_MHB01_RCMTPBNCSTNDLINK
	 * 인사 채용(개요기준) 연계정보 처리 SP
	 * @param linkDoctNo
	 * @param linkId
	 * @throws Throwable
	 */
	void spMhb01RcmtPbncStndLink(String linkDoctNo, String linkId) throws Throwable;



	/**
	 * MIS.PG_MH_HMPG_LINK
	 * SP_MHLINK_INSERT_LOG
	 * 인사 연계 로그 저장( 개별 트랜잭션임 )
	 * @param linkDoctNo
	 * @param linkId
	 * @param trptDivCd
	 * @param trptRslt
	 * @throws Throwable
	 */
	void spMhLinkInsertLog(String linkDoctNo, String linkId, String trptDivCd, String trptRslt ) throws Throwable;



	/**
	 * MIS.PG_MH_EHRD_LINK
	 * SP_MHC03_SCRELINK_DEL
	 * 인사 학력사항 삭제연계처리
	 * @param empUniqNo
	 * @param screSrno
	 * @throws Throwable
	 */
	void spMhc03ScrelinkDel(String empUniqNo, Long screSrno ) throws Throwable;



	/**
	 * MIS.PG_MH_EHRD_LINK
	 * SP_MHC03_FRNRLINK_DEL
	 * 인사 언어사항 삭제연계처리
	 * @param empUniqNo
	 * @param frlnSrno
	 * @throws Throwable
	 */
	void spMhc03FrnrlinkDel(String empUniqNo, Long frlnSrno ) throws Throwable;



	/**
	 * MIS.PG_MH_EHRD_LINK
	 * SP_MHC03_SRTIMATTLINK_DEL
	 * 인사 자격면허사항 삭제연계처리
	 * @param empUniqNo
	 * @param crtiSrno
	 * @throws Throwable
	 */
	void spMhc03SrtimattlinkDel(String empUniqNo, Long crtiSrno ) throws Throwable;



	/**
	 * MIS.PG_MH_EHRD_LINK
	 * SP_MHC03_REWADISALINK_DEL
	 * 인사 포상징계사항 삭제연계처리
	 * @param rewaDisaSrno
	 * @throws Throwable
	 */
	void spMhc03RewadisalinkDel(String rewaDisaSrno ) throws Throwable;



	/**
	 * MIS.PG_MH_HR
	 * SP_MHF02_CHECK_VACT
	 * 휴가 신청 체크
	 * @param procType		: 처리구분 - 저장(S), 삭제(D), 결재(P)
	 * @param vactAplNo		: 휴가신청번호
	 * @param vactDivCd		: 휴가신청구분코드
	 * @param empUniqNo		: 직원고유번호
	 * @param begnDt		: 시작일자
	 * @param vactBegnDtm	: 시작시간
	 * @param clseDt		: 종료일자
	 * @param vactClseDtm	: 종료시간
	 * @param aplDivCd      : 신청구분
	 * @throws Throwable
	 */
	void spMhf02CheckVact( String procType, String vactAplNo, String vactDivCd, String empUniqNo, String begnDt, String vactBegnDtm, String clseDt, String vactClseDtm, String aplDivCd ) throws EgovBizException;



	/**
	 * MIS.PG_MH_HR
	 * SP_MHJ01_CHECK_BSTR
	 * 인사 근무지내출장신청 가능여부 확인 SP
	 * @param procType	: 처리구분 - 저장(S), 삭제(D), 결재(P)
	 * @param aplNo		: 출장신청번호
	 * @param empUniqNo	: 직원고유번호
	 * @param atteDt	: 출장일자
	 * @param begnPtm	: 시작시간
	 * @param clsePtm	: 종료시간
	 * @return
	 */
	void spMhj01CheckBstr( String procType, String aplNo, String empUniqNo, String atteDt, String begnPtm, String clsePtm ) throws Throwable;



	/**
	 * MIS.PG_MH_HR
	 * SP_MHF04_CHECK_OVTM_WRK
	 * 시간외근무신청 승인 CHECK sp
	 * 보상휴가 생성
	 * @param ovtmWrkAplNo
	 * @return
	 * @throws Throwable
	 */
	void spMhf04CheckOvtmWrk(String ovtmWrkAplNo) throws Throwable;



	/**
	 * MIS.PG_MF_HMRS_LINK
	 * SP_MF_HMRS_LINK_03
	 * 복명신청시 발의정정 처리
	 * @param rptmAplNo
	 * @param gubun(1:저장, 2:결재완료)
	 * @return
	 * @throws Throwable
	 */
	void spMfHmrsLink03(String rptmAplNo, String gubun) throws Throwable;



	/**
	 * MIS.PG_MH_APNT
	 * SP_APNT_GW_HTML_CRTN
	 * 발령 전자결재 문서(HTML) 생성
	 * @param list
	 * @return
	 * @throws Throwable
	 */
	EgovMap spApntGWHtmlCrtn( List<TbMhApntKey> list ) throws Throwable;

	/**
	 * MIS.PG_MF_HMRS_LINK
	 * SP_MF_RPTM_APL
	 * 근무지외출장정산 저장, 삭제 시 호출되는 프로시저
	 * @param rptmAplNo 출장복명번호
	 * @param bstrAplNo 출장신청번호
	 * @param igubun 1:저장, 2:삭제
	 * @param bgubun 출장구분(1:국내, 2:국외)
	 * @return
	 * @throws Throwable
	 */
	void spMfRptmApl(String rptmAplNo, String bstrAplNo, String igubun, String bgubun, String expnRqetDt, String mdulId) throws Throwable;


	/**
	 * MIS.PG_MF_HMRS_LINK
	 * SP_RPTM_SAVECHECK_MAIN
	 * 근무지외출장정산 저장시 호출되는 프로시저
	 * @param rptmAplNo 출장복명번호
	 * @param bgubun 출장구분(1:국내, 2:국외)
	 * @return
	 * @throws Throwable
	 */
	void spRptmSavecheckMain(String procType ,String rptmAplNo, String bgubun) throws Throwable;

	/**
	 * MIS.PG_MH_ATTE
	 * SP_ATTE_ATTE_RECORD
	 * 근태 재생성 SP
	 * @param usrId
	 * @param
	 * @return
	 */
	void spAtteAtteRecord( String atteDt,String atteDt2, String usrId, String atteJnrlYn ) throws Throwable;


	/**
	 * MIS.PG_MH_OVTM_WRK
	 * SP_OVTM_WRK_APL_OT_PAY
	 * 시간외수당생성 SP
	 * @param  rgtrId
	 * @param
	 * @return
	 */
	void spOvtmWrkAplOtPay(String fnshYm, String rgtrId)throws Throwable;



	/**
	 * MIS.PG_MH_MHRS_LINK
	 * SP_MH_EDU_APPR
	 * 교육신청 SP
	 * @param  rgtrId
	 * @param
	 * @return
	 */
	void spMhEduAppr(String rsltReptNo, String usrId)throws Throwable;

	void spTrxpSavecheckMain(String pROC_TYPE, String bstrAplNo,String bSTR_DIV_CD) throws Throwable;

	void spMhEmpBdgtChk(String gubn, String aplNo, String usrId)throws Throwable;

	/*void spMfTrxpApl03(String bstrAplNo, String gubun) throws Throwable;*/

	String spMfTrxpApl03(String bstrAplNo, String gubun, String expnRqetDt) throws Throwable;

	String spMfTrxpApl04(String bstrAplNo, String stafSrno) throws Throwable;

	void spMhEduApprGvb(String rsltReptNo, String permDivCd, String usrId)throws Throwable;


	void spTbMhHireempSaveChk(String gubn, String aplNo, String usrId) throws Throwable;

	/**
	 * MIS.PG_MH_RTMN
	 * SP_RTMN_APL_CFM_CRTN
	 * 퇴직신청 확인자료 생성
	 * @param empUniqNo		: 퇴직자직원고유번호
	 * @param usrId			: 작업자ID
	 * @throws Throwable
	 */
	String spMhg05RtmnAplCfmCrtn( String empUniqNo, String usrId ) throws Throwable;

	/**
	 * MIS.PG_MH_ODBY
	 * SP_ODBY_USE_VACT
	 * 연차저축휴가 사용/사용취소
	 * @param empUniqNo		: 직원고유번호
	 * @param procDvcd      : 처리구분('1': 사용, '2': 취소)
	 * @param vactAplNo     : 휴가신청번호
	 * @param vactDys       : 휴가사용일수
	 * @param rgtrId        : 작업자ID
	 * @throws Throwable
	 */
	String spMhOdbyUseVact( String empUniqNo, String procDvcd, String vactAplNo, Double vactDys, String rgtrId) throws EgovBizException;

	/**
	 * 가족돌봄휴가 사용가능여부 체크
	 * @param empUniqNo
	 * @param vactAplNo
	 * @return
	 * @throws Throwable
	 */
	String spMhCheckFmlyCareVact(String empUniqNo, String vactAplNo) throws Throwable;

	/**
	 * 자녀돌봄휴가 사용가능여부 체크
	 * @param empUniqNo
	 * @param vactAplNo
	 * @return
	 * @throws Throwable
	 */
	String spMhCheckChldCareVact(String empUniqNo, String vactAplNo) throws Throwable;

	/**
	 * 대체휴일 관련 프로시저
	 * @param vactAplNo
	 * @param procDivCd
	 * @param usrId
	 * @return
	 * @throws Throwable
	 */
	String spRplmVactProc(String vactAplNo, String procDivCd, String usrId) throws EgovBizException;

	/**
	 * 연속휴가 저장 가능 체크 프로시저
	 * @param procDivCd
	 * @param scsvVactAplNo
	 * @throws Throwable
	 */
	void spCheckScsvVactSave( String procDivCd, String scsvVactAplNo ) throws Throwable;

	/**
	 * 육아시간 관련 프로시저
	 * @param procDivCd
	 * @param vactAplNo
	 * @param usrId
	 * @return
	 * @throws Throwable
	 */
	void spPrtnPtmVactProc( String procDivCd, String vactAplNo, String usrId ) throws EgovBizException;

	/**
	 * 모성보호 사용가능여부 체크
	 * @param vactAplNo
	 * @return
	 * @throws Throwable
	 */
	String spMhCheckMthdPrtcVact(String vactAplNo) throws Throwable;
	
	/**
	 * 휴가 최소 근무시간 충족 여부 체크
	 * @param vactAplNo
	 * @param ofryAplNo
	 * @throws Throwable
	 */
	void spMhf02CheckVactMinWrkPtm(String vactAplNo, String ofryAplNo) throws EgovBizException;

}



