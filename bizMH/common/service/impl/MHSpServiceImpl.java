package pierp.app.mis.bizMH.common.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import pierp.app.mis.bizMH.common.service.MHSpService;
import pierp.app.mis.common.table.model.TbMfRsolMstKey;
import pierp.app.mis.common.table.model.TbMhApntKey;
import pierp.common.cmmn.service.BaseAbstractServiceImpl;

@Service
public class MHSpServiceImpl extends BaseAbstractServiceImpl implements MHSpService {

	// 인사
	@Autowired private MHSpDAO mhSpDAO;

	// 로그
	protected Log logger = LogFactory.getLog(getClass());

	protected void log( String spNm, Object obj ){
		if( logger.isDebugEnabled() )
			logger.debug( spNm + " : " + obj.toString() );
	}

	@Override
	public String spMhc03GenEmpNo( String empNo, String stfDivCd, String psitCd ) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMhc03GenEmpNo( empNo, stfDivCd, psitCd );
		this.log( "KOHI_MIS.PG_MH_HR.SP_MHC03_GEN_EMP_NO", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty", new String[] {map.get( "oRtnMsg" ).toString()} );
		}
		return map.get( "oRtnMsg" ).toString();
	}

	@Override
	public void spMhb03Mstcrtn( String exmNo, String usrId ) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMhb03Mstcrtn( exmNo, usrId );
		this.log( "KOHI_MIS.PG_MH_RCMT.SP_EMP_MST_CRTN", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty", new String[] {map.get( "oRtnMsg" ).toString()} );
		}
	}

	@Override
	public TbMfRsolMstKey spMfHmrsLink02( String rsolRqetNo, String deptCd, String empNo, String usrId, String expnRqetDt, String mdulId ) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMfHmrsLink02( rsolRqetNo, deptCd, empNo, usrId, expnRqetDt, mdulId );
		this.log( "KOHI_MIS.PG_MF_HMRS_LINK.SP_MF_HMRS_LINK_02", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		String oRtnMsg	= map.get( "oRtnMsg" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty", new String[] {map.get( "oRtnMsg" ).toString()} );
		}

		String actsYy = oRtnMsg.substring(0,4);
		String bzplCd = oRtnMsg.substring(4,5);
		String rsolNo = oRtnMsg.substring(5);

		TbMfRsolMstKey key = new TbMfRsolMstKey();
		key.setActsYy( actsYy );
		key.setBzplCd( bzplCd );
		key.setRsolNo( rsolNo );

		return key;
	}

	@Override
	public TbMfRsolMstKey spMfHmrsLink04( String rsolRqetNo, String deptCd, String empNo, String fnshYm,String usrId ) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMfHmrsLink04( rsolRqetNo, deptCd, empNo, fnshYm, usrId );
		this.log( "KOHI_MIS.PG_MF_HMRS_LINK.SP_MF_HMRS_LINK_04", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		String oRtnMsg	= map.get( "oRtnMsg" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty", new String[] {map.get( "oRtnMsg" ).toString()} );
		}

		String actsYy = oRtnMsg.substring(0,4);
		String bzplCd = oRtnMsg.substring(4,5);
		String rsolNo = oRtnMsg.substring(5);

		TbMfRsolMstKey key = new TbMfRsolMstKey();
		key.setActsYy( actsYy );
		key.setBzplCd( bzplCd );
		key.setRsolNo( rsolNo );

		return key;
	}

	@Override
	public void spMhf01CheckOfry( String procType, String ofryAplNo, String aplDivCd, String empUniqNo, String atteDt, String begnPtm, String clsePtm ) throws EgovBizException {

		Map<String,Object> map = mhSpDAO.spMhf01CheckOfry( procType, ofryAplNo, aplDivCd, empUniqNo, atteDt, begnPtm, clsePtm );
		this.log( "KOHI_MIS.PG_MH_HR.SP_MHF01_CHECK_OFRY", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty", new String[] {map.get( "oRtnMsg" ).toString()} );
		}
	}

	@Override
	public void spMhf01Wkplncrtn( String yy, String usrId ) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMhf01Wkplncrtn( yy, usrId );
		this.log( "KOHI_MIS.PG_MH_HR.SP_MHF01_WKPLNCRTN", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty", new String[] {map.get( "oRtnMsg" ).toString()} );
		}
	}

	@Override
	public void spRparVactUseDelt( String vactAplNo, String usrId ) throws EgovBizException {
		Map<String,Object> map = mhSpDAO.spRparVactUseDelt( vactAplNo, usrId );
		this.log( "KOHI_MIS.PG_MH_VACT.SP_RPAR_VACT_USE_DELT", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty", new String[] {map.get( "oRtnMsg" ).toString()} );
		}
	}

	@Override
	public void spRparVactUseMain( String vactAplNo, String usrId ) throws EgovBizException {
		Map<String,Object> map = mhSpDAO.spRparVactUseMain( vactAplNo, usrId );
		this.log( "KOHI_MIS.PG_MH_VACT.SP_RPAR_VACT_USE_MAIN", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty", new String[] {map.get( "oRtnMsg" ).toString()} );
		}
	}

	@Override
	public void spRparVactCrtnMain( String ovtmWrkAplNo, String usrId ) throws EgovBizException {
		Map<String,Object> map = mhSpDAO.spRparVactCrtnMain( ovtmWrkAplNo, usrId );
		this.log( "PG_MH_VACT.SP_RPAR_VACT_CRTN_MAIN", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty", new String[] {map.get( "oRtnMsg" ).toString()} );
		}
	}

	// 보상휴가 삭제
	@Override
	public void spRparVactDelt( String ovtmWrkAplNo, String usrId ) throws EgovBizException {
		Map<String,Object> map = mhSpDAO.spRparVactDelt( ovtmWrkAplNo, usrId );
		this.log( "KOHI_MIS.PG_MH_VACT.SP_RPAR_VACT_DELT", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty", new String[] {map.get( "oRtnMsg" ).toString()} );
		}
	}

	@Override
	public void spMhf04Chk( String hodyDivCd, String ovtmWrkAplNo, String empUniqNo, String atteDt, String begnPtm, String clsePtm, boolean forAdmin ) throws EgovBizException{
		Map<String,Object> map = mhSpDAO.spMhf04Chk( hodyDivCd, ovtmWrkAplNo, empUniqNo, atteDt, begnPtm, clsePtm, forAdmin );
		this.log( "KOHI_MIS.PG_MH_OVTM_WRK.SP_OVTM_CHECK_MAIN", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty", new String[] {map.get( "oRtnMsg" ).toString()} );
		}
	}

	@Override
	public void spMhf05Chk( String hodyDivCd, String ovtmWrkAplNo, String empUniqNo, String atteDt, String begnPtm, String clsePtm, String aplPtm, boolean forAdmin ) throws Throwable{
		Map<String,Object> map = mhSpDAO.spMhf05Chk( hodyDivCd, ovtmWrkAplNo, empUniqNo, atteDt, begnPtm, clsePtm, aplPtm, forAdmin );
		this.log( "KOHI_MIS.PG_MH_OVTM_WRK.SP_OVTM_CHECK_WITH_HOUR", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty", new String[] {map.get( "oRtnMsg" ).toString()} );
		}
	}

	@Override
	public String spTrxpAplMontNo( String bstrAplNo, String expnMontNo, String gubun, String bstrDivCd, String mdulId) throws Throwable {
		Map<String,Object> map = mhSpDAO.spTrxpAplMontNo( bstrAplNo, expnMontNo, gubun, bstrDivCd, mdulId );
		this.log( "KOHI_MIS.PG_MF_HMRS_LINK.SP_MF_TRXP_APL", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty", new String[] {map.get( "oRtnMsg" ).toString()} );
		}
		String expnMotnNo = expnMontNo;
		if( StringUtils.equals( "1", gubun )){
			expnMotnNo = map.get( "oRtnMsg" ).toString();
		}
		return expnMotnNo;
	}

	@Override
	public void spMhf01GetFwp( String gb, String fwpAplNo, String empUniqNo, String begnYm, String begnPtm, String clseYm, String clsePtm) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMhf01GetFwp( gb, fwpAplNo, empUniqNo, begnYm, begnPtm, clseYm, clsePtm );
		this.log( "KOHI_KOHI_MIS.PG_MH_HR.SP_MHF01_GET_FWP", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty", new String[] {map.get( "oRtnMsg" ).toString()} );
		}
	}

	@Override
	public void spMhj01CheckTrxp( String procType, String bstrAplNo, String bstrDivCd, String empUniqNo, String begnDt, String clseDt ) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMhj01CheckTrxp( procType, bstrAplNo, bstrDivCd, empUniqNo, begnDt, clseDt );
		this.log( "KOHI_MIS.PG_MH_BSTR.SP_CHECK_TRXP_MAIN", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty", new String[] {map.get( "oRtnMsg" ).toString()} );
		}
	}

	@Override
	public void spMhb01RcmtPbncLink(String linkDoctNo, String linkId) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMhb01RcmtPbncLink( linkDoctNo, linkId );
		this.log( "KOHI_MIS.PG_MH_HMPG_LINK.SP_MHB01_RCMTPBNCLINK", map );
	}

	@Override
	public void spMhb01RcmtBascLink( String linkDoctNo, String linkId ) throws Throwable{
		Map<String,Object> map = mhSpDAO.spMhb01RcmtBascLink( linkDoctNo, linkId );
		this.log( "KOHI_MIS.PG_MH_HMPG_LINK.SP_MHB01_RCMTBASCLINK", map );
	}

	@Override
	public void spMhb01SftdLink( String linkDoctNo, String linkId ) throws Throwable{
		Map<String,Object> map = mhSpDAO.spMhb01SftdLink( linkDoctNo, linkId );
		this.log( "KOHI_MIS.PG_MH_HMPG_LINK.SP_MHB01_SFTDLINK", map );
	}

	@Override
	public void spMhb01RcmtScreLink( String linkDoctNo, String linkId ) throws Throwable{
		Map<String,Object> map = mhSpDAO.spMhb01RcmtScreLink( linkDoctNo, linkId );
		this.log( "KOHI_MIS.PG_MH_HMPG_LINK.SP_MHB01_RCMTSCRELINK", map );
	}

	@Override
	public void spMhb01RcmtCarrLink( String linkDoctNo, String linkId ) throws Throwable{
		Map<String,Object> map = mhSpDAO.spMhb01RcmtCarrLink( linkDoctNo, linkId );
		this.log( "KOHI_MIS.PG_MH_HMPG_LINK.SP_MHB01_RCMTCARRLINK", map );
	}

	@Override
	public void spMhb01RcmtFrlnLink( String linkDoctNo, String linkId ) throws Throwable{
		Map<String,Object> map = mhSpDAO.spMhb01RcmtFrlnLink( linkDoctNo, linkId );
		this.log( "KOHI_MIS.PG_MH_HMPG_LINK.SP_MHB01_RCMTFRLNLINK", map );
	}

	@Override
	public void spMhb01RcmtCrtiLink( String linkDoctNo, String linkId ) throws Throwable{
		Map<String,Object> map = mhSpDAO.spMhb01RcmtCrtiLink( linkDoctNo, linkId );
		this.log( "KOHI_MIS.PG_MH_HMPG_LINK.SP_MHB01_RCMTCRTILINK", map );
	}

	@Override
	public void spMhb01RcmtPbncStndLink( String linkDoctNo, String linkId ) throws Throwable{
		Map<String,Object> map = mhSpDAO.spMhb01RcmtPbncStndLink( linkDoctNo, linkId );
		this.log( "KOHI_MIS.PG_MH_HMPG_LINK.SP_MHB01_RCMTPBNCSTNDLINK", map );
	}

	@Override
	public void spMhLinkInsertLog(String linkDoctNo, String linkId, String trptDivCd, String trptRslt ) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMhLinkInsertLog( linkDoctNo, linkId, trptDivCd, trptRslt );
		this.log( "KOHI_MIS.PG_MH_HMPG_LINK.SP_MHLINK_INSERT_LOG", map );
		if( !"S".equals( map.get( "oRtnCd").toString() ) ){
			throw processException("fail.common.serviceRunError", new String[] {map.get( "oRtnMsg" ).toString()} );
		}
	}

	@Override
	public void spMhc03ScrelinkDel(String empUniqNo, Long screSrno ) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMhc03ScrelinkDel( empUniqNo, screSrno );
		this.log( "KOHI_MIS.PG_MH_EHRD_LINK.SP_MHC03_SCRELINK_DEL", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
	}

	@Override
	public void spMhc03FrnrlinkDel(String empUniqNo, Long frlnSrno ) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMhc03FrnrlinkDel( empUniqNo, frlnSrno );
		this.log( "KOHI_MIS.PG_MH_EHRD_LINK.SP_MHC03_FRNRLINK_DEL", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
	}

	@Override
	public void spMhc03SrtimattlinkDel(String empUniqNo, Long crtiSrno ) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMhc03SrtimattlinkDel( empUniqNo, crtiSrno );
		this.log( "KOHI_MIS.PG_MH_EHRD_LINK.SP_MHC03_SRTIMATTLINK_DEL", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
	}

	@Override
	public void spMhc03RewadisalinkDel(String rewaDisaSrno ) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMhc03RewadisalinkDel( rewaDisaSrno );
		this.log( "KOHI_MIS.PG_MH_EHRD_LINK.SP_MHC03_REWADISALINK_DEL", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
	}

	@Override
	public void spMhf02CheckVact( String procType, String vactAplNo, String vactDivCd, String empUniqNo, String begnDt, String vactBegnDtm, String clseDt, String vactClseDtm, String aplDivCd) throws EgovBizException {
		Map<String,Object> map = mhSpDAO.spMhf02CheckVact( procType, vactAplNo, vactDivCd, empUniqNo, begnDt, vactBegnDtm, clseDt, vactClseDtm, aplDivCd );
		this.log( "KOHI_MIS.PG_MH_HR.SP_MHF02_CHECK_VACT", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
	}

	@Override
	public void spMhj01CheckBstr( String procType, String aplNo, String empUniqNo, String atteDt, String begnPtm, String clsePtm ) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMhj01CheckBstr( procType, aplNo, empUniqNo, atteDt, begnPtm, clsePtm );
		this.log( "KOHI_MIS.PG_MH_BSTR.SP_CHECK_BSTR_MAIN", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
	}

	@Override
	public void spMhf04CheckOvtmWrk( String ovtmWrkAplNo ) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMhf04CheckOvtmWrk( ovtmWrkAplNo );
		this.log( "KOHI_MIS.PG_MH_HR.SP_MHF04_CHECK_OVTM_WRK", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
	}

	@Override
	public void spMfHmrsLink03(String rptmAplNo, String gubun) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMfHmrsLink03( rptmAplNo, gubun );
		this.log( "KOHI_MIS.PG_MF_HMRS_LINK.SP_MF_HMRS_LINK_03", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
	}

	@Override
	public EgovMap spApntGWHtmlCrtn( List<TbMhApntKey> list ) throws Throwable {
//		Map<String,Object> map = mhSpDAO.spApntGWHtmlCrtn( list );
//		this.log( "KOHI_KOHI_MIS.PG_MH_APNT.SP_APNT_GW_HTML_CRTN", map );
//		String oRtnCd	= map.get( "oRtnCd" ).toString();
//		if( !"S".equals( oRtnCd ) ){
//			throw processException( "valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
//		}
		EgovMap rtn = new EgovMap();
//		rtn.put( "html",	map.get( "oRtnMsg" ).toString());
//		rtn.put( "title",	map.get( "oRtnTitle" ).toString());
		return rtn;
	}

	@Override
	public void spMfRptmApl(String rptmAplNo, String bstrAplNo, String igubun, String bgubun, String expnRqetDt, String mdulId) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMfRptmApl( rptmAplNo, bstrAplNo, igubun, bgubun, expnRqetDt, mdulId);
		this.log( "KOHI_MIS.PG_MF_HMRS_LINK.SP_MF_RPTM_APL", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
	}

	@Override
	public void spRptmSavecheckMain(String procType,String rptmAplNo, String bgubun) throws Throwable {
		Map<String,Object> map = mhSpDAO.spRptmSavecheckMain(procType,rptmAplNo,bgubun);
		this.log( "KOHI_MIS.PG_MH_BSTR.SP_RPTM_SAVECHECK_MAIN", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
	}

	@Override
	public void spAtteAtteRecord( String atteDt,String atteDt2, String usrId, String atteJnrlYn ) throws Throwable{
		Map<String,Object> map = mhSpDAO.spAtteAtteRecord( atteDt,atteDt2, usrId, atteJnrlYn );
		this.log( "KOHI_MIS.PG_MH_ATTE.SP_ATTE_ATTE_RECORD", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
	}

	@Override
	public void spOvtmWrkAplOtPay( String fnshYm, String rgtrId ) throws Throwable{
		Map<String,Object> map = mhSpDAO.spOvtmWrkAplOtPay( fnshYm, rgtrId );
		this.log( "KOHI_MIS.PG_MH_OVTM_WRK.SP_OVTM_WRK_APL_OT_PAY", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
	}

	@Override
	public void spMhEduAppr( String rsltReptNo, String usrId ) throws Throwable{
		Map<String,Object> map = mhSpDAO.spMhEduAppr( rsltReptNo, usrId );
		this.log( "KOHI_MIS.PG_MH_MHRS_LINK.SP_MH_EDU_APPR", map );
	}

	@Override
	public void spTrxpSavecheckMain( String pROC_TYPE, String bstrAplNo, String bSTR_DIV_CD ) throws Throwable{
		Map<String,Object> map = mhSpDAO.spTrxpSavecheckMain( pROC_TYPE, bstrAplNo, bSTR_DIV_CD);
		this.log( "KOHI_MIS.PG_MH_BSTR.SP_TRXP_SAVECHECK_MAIN", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
	}

	@Override
	public void spMhEmpBdgtChk( String gubn, String aplNo, String usrId ) throws Throwable{
		Map<String,Object> map = mhSpDAO.spMhEmpBdgtChk( gubn, aplNo, usrId);
		this.log( "KOHI_MIS.PG_MH_MHRS_LINK.SP_MH_EMP_BDGT_CHK", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
	}

	@Override
	public void spTbMhHireempSaveChk( String gubn, String aplNo, String usrId ) throws Throwable{
		Map<String,Object> map = mhSpDAO.spTbMhHireempSaveChk( gubn, aplNo, usrId);
		this.log( "KOHI_MIS.PG_MH_HIRE.SP_TB_MH_HIREEMP_SAVE_CHK", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
//		getLog().info(oRtnCd);
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
	}

	@Override
	public void spEmpHisInsert( String empUniqNo, String usrId ) throws Throwable{
		Map<String,Object> map = mhSpDAO.spEmpHisInsert( empUniqNo, usrId );
		this.log( "KOHI_MIS.PG_MH_MHRS_LINK.SP_EMP_HIS_SET", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
	}

/*	@Override
	public void spMfTrxpApl03(String bstrAplNo, String gubun) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMfTrxpApl03( bstrAplNo, gubun );
		this.log( "KOHI_MIS.PG_MF_HMRS_LINK.SP_MF_TRXP_APL_03", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
	}
*/

	@Override
	public String spMfTrxpApl03(String bstrAplNo, String gubun, String expnRqetDt) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMfTrxpApl03( bstrAplNo, gubun, expnRqetDt );
		this.log( "KOHI_MIS.PG_MF_HMRS_LINK.SP_MF_TRXP_APL_03", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		String rsolYn = null;
		if(map.get("rsolNo") != null){
			rsolYn = "S";
		}else{
			rsolYn = "E";
		}
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
		return rsolYn;
	}

	@Override
	public String spMfTrxpApl04(String bstrAplNo, String stafSrno) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMfTrxpApl04( bstrAplNo, stafSrno );
		this.log( "KOHI_MIS.PG_MF_HMRS_LINK.SP_MF_TRXP_APL_04", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
		return oRtnCd;
	}

	@Override
	public void spMhEduApprGvb( String rsltReptNo, String permDivCd, String usrId ) throws Throwable{
		Map<String,Object> map = mhSpDAO.spMhEduApprGvb( rsltReptNo, permDivCd, usrId);
		this.log( "KOHI_MIS.PG_MH_MHRS_LINK.SP_MH_EDU_APPR_GVB", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
	}

	// 퇴직신청 확인자료 생성
	@Override
	public String spMhg05RtmnAplCfmCrtn( String empUniqNo, String usrId ) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMhg05RtmnAplCfmCrtn( empUniqNo, usrId );
		this.log( "KOHI_MIS.PG_MH_RTMN.SP_RTMN_APL_CFM_CRTN", map );
		String oRtnCd	= map.get( "oRtnCd" ).toString();
		if( !"S".equals( oRtnCd ) ){
			throw processException( "valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
		return oRtnCd;
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
	@Override
	public String spMhOdbyUseVact(String empUniqNo, String procDvcd, String vactAplNo, Double vactDys, String rgtrId) throws EgovBizException {
		Map<String,Object> map = mhSpDAO.spMhOdbyUseVact( empUniqNo, procDvcd, vactAplNo, vactDys, rgtrId);
		this.log("KOHI_MIS.PG_MH_ODBY.SP_ODBY_USE_VACT", map);
		String oRtnCd = map.get("oRtnCd").toString();
		if (!"S".equals(oRtnCd)) {
			throw processException("valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
		return oRtnCd;
	}

	// 가족돌봄휴가 사용가능여부 체크
	@Override
	public String spMhCheckFmlyCareVact(String empUniqNo, String vactAplNo) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMhCheckFmlyCareVact( empUniqNo, vactAplNo);
		this.log("KOHI_MIS.PG_MH_VACT.SP_CHK_FMLY_CARE_VACT", map);
		String oRtnCd = map.get("oRtnCd").toString();
		if (!"S".equals(oRtnCd)) {
			throw processException("valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
		return oRtnCd;
	}

	// 자녀돌봄휴가 사용가능여부 체크
	@Override
	public String spMhCheckChldCareVact(String empUniqNo, String vactAplNo) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMhCheckChldCareVact( empUniqNo, vactAplNo);
		this.log("KOHI_MIS.PG_MH_VACT.SP_CHK_CHLD_CARE_VACT", map);
		String oRtnCd = map.get("oRtnCd").toString();
		if (!"S".equals(oRtnCd)) {
			throw processException("valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
		return oRtnCd;
	}

	// 대체휴일 관련 프로시저
	@Override
	public String spRplmVactProc(String vactAplNo, String procDivCd, String usrId) throws EgovBizException {
		Map<String,Object> map = mhSpDAO.spRplmVactProc( vactAplNo, procDivCd, usrId);
		this.log("KOHI_MIS.PG_MH_VACT.SP_RPLM_VACT_PROC", map);
		String oRtnCd = map.get("oRtnCd").toString();
		if (!"S".equals(oRtnCd)) {
			throw processException("valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
		return oRtnCd;
	}
	
	// 연속휴가 저장 가능 체크 프로시저
	@Override
	public void spCheckScsvVactSave( String procDivCd, String scsvVactAplNo ) throws Throwable {
		Map<String,Object> map = mhSpDAO.spCheckScsvVactSave( procDivCd, scsvVactAplNo );
		this.log("KOHI_MIS.PG_MH_VACT.SP_CHECK_SCSV_VACT_SAVE", map);
		String oRtnCd = map.get("oRtnCd").toString();
		if (!"S".equals(oRtnCd)) {
			throw processException("valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
	}
	
	// 육아시간 관련 프로시저
	@Override
	public void spPrtnPtmVactProc( String procDivCd, String vactAplNo, String usrId ) throws EgovBizException {
		Map<String,Object> map = mhSpDAO.spPrtnPtmVactProc( procDivCd, vactAplNo, usrId );
		this.log("KOHI_MIS.PG_MH_VACT.SP_PRTN_PTM_VACT_PROC", map);
		String oRtnCd = map.get("oRtnCd").toString();
		if (!"S".equals(oRtnCd)) {
			throw processException("valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
	}

	// 모성보호 사용가능여부 체크
	@Override
	public String spMhCheckMthdPrtcVact(String vactAplNo) throws Throwable {
		Map<String,Object> map = mhSpDAO.spMhCheckMthdPrtcVact(vactAplNo);
		this.log("KOHI_MIS.PG_MH_VACT.SP_CHECK_MTHD_PRTC", map);
		String oRtnCd = map.get("oRtnCd").toString();
		if (!"S".equals(oRtnCd)) {
			throw processException("valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
		return oRtnCd;
	}

	// 휴가 최소 근무시간 충족 여부 체크
	@Override
	public void spMhf02CheckVactMinWrkPtm(String vactAplNo, String ofryAplNo) throws EgovBizException {
		Map<String,Object> map = mhSpDAO.spMhf02CheckVactMinWrkPtm(vactAplNo, ofryAplNo);
		this.log("KOHI_MIS.PG_MH_VACT.SP_MHF02_CHECK_VACT_MIN_WRK_PTM", map);
		String oRtnCd = map.get("oRtnCd").toString();
		if (!"S".equals(oRtnCd)) {
			throw processException("valid.empty",new String[] {map.get( "oRtnMsg" ).toString()});
		}
	}
}