package pierp.app.mis.bizMH.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.mis.bizMH.common.model.MHLockTableDAO;
import pierp.app.mis.common.table.model.TbMhApntKey;
import pierp.app.mis.common.table.model.TbMhDtBstrAplVO;
import pierp.app.mis.common.table.model.TbMhOfryAplVO;
import pierp.app.mis.common.table.model.TbMhRptmAplVO;
import pierp.app.mis.common.table.model.TbMhRtmnAplVO;
import pierp.app.mis.common.table.model.TbMhTrxpAplVO;
import pierp.app.mis.common.table.model.TbMhVactAplVO;
import pierp.common.cmmn.service.BaseAbstractServiceImpl;


@Service
public class MHLockTableServiceImpl extends BaseAbstractServiceImpl implements MHLockTableService {

	// 인사
	@Autowired private MHLockTableDAO mhLockTableDAO;


	@Override
	public void rejectTbMhDeptChg( Long chgSrno ) throws EgovBizException {
		if( 1 != mhLockTableDAO.lockTbMhDeptChg( chgSrno, null ) ){
			throw processException("fail.MHCommon.alreadyAccepted",new String[]{"조직개편"});
		}
	}


	@Override
	public void rejectTbMhDeptChg( Long chgSrno, String dfnYn ) throws EgovBizException {
		if( 1 != mhLockTableDAO.lockTbMhDeptChg( chgSrno, dfnYn ) ){
			throw processException("fail.MHCommon.alreadyAccepted",new String[]{"조직개편"});
		}
	}


//	@Override
//	public void rejectTbMhFwpChoice( TbMhFwpVO vo ) throws Throwable {
//		if( 1 != mhLockTableDAO.lockTbMhFwp( vo ) ){
//			throw processException("fail.MHCommon.alreadyAccepted",new String[]{"선택근무신청"});
//		}
//	}


//	@Override
//	public void rejectTbMhFwpPart( TbMhFwpVO vo ) throws Throwable {
//		if( 1 != mhLockTableDAO.lockTbMhFwp( vo ) ){
//			throw processException("fail.MHCommon.alreadyAccepted",new String[]{"단시간근로신청"});
//		}
//	}


	@Override
	public void rejectTbMhTrxpApl( TbMhTrxpAplVO vo ) throws EgovBizException {
		if( 1 != mhLockTableDAO.lockTbMhTrxpApl( vo ) ){
			throw processException("fail.MHCommon.alreadyAccepted",new String[]{"출장신청서"});
		}
	}


	@Override
	public void rejectTbMhTrxpApl2( TbMhTrxpAplVO vo ) throws EgovBizException {
		if( 1 != mhLockTableDAO.lockTbMhTrxpApl2( vo ) ){
			throw processException("fail.MHCommon.alreadyAccepted",new String[]{"출장신청서"});
		}
	}

	@Override
	public void rejectTbMhOfryApl( TbMhOfryAplVO vo ) throws EgovBizException {
		if( 1 != mhLockTableDAO.lockTbMhOfryApl( vo ) ){
			throw processException("fail.MHCommon.alreadyAccepted",new String[]{"조퇴/외출신청"} );
		}
	}


	@Override
	public void rejectTbMhVactApl( TbMhVactAplVO vo ) throws EgovBizException {
		if( 1 != mhLockTableDAO.lockTbMhVactApl( vo ) ){
			throw processException("fail.common.runError",new String[]{"해당건(휴가신청)은 이미 결재처리되어 수정 불가 합니다.\n자료를 확인하시기 바랍니다."});
		}
	}


//	@Override
//	public void rejectTbMhOvWrkApl( TbMhOvWrkAplVO vo ) throws Throwable {
//		if( 1 != mhLockTableDAO.lockTbMhOvWrkApl( vo ) ){
//			throw processException("fail.MHCommon.alreadyAccepted",new String[]{"시간외근무신청"});
//		}
//	}



	@Override
	public void rejectTbMhRptmApl( TbMhRptmAplVO vo ) throws EgovBizException {
		if( 1 != mhLockTableDAO.lockTbMhRptmApl( vo ) ){
			throw processException("fail.MHCommon.alreadyAccepted",new String[]{"출장복명신청서"});
		}
	}


	@Override
	public void rejectTbMhDtBstrApl( TbMhDtBstrAplVO vo ) throws EgovBizException {
		if( 1 != mhLockTableDAO.lockTbMhDtBstrApl( vo ) ){
			throw processException("fail.MHCommon.alreadyAccepted",new String[]{"근무지내출장신청서"});
		}
	}


	@Override
	public void rejectTbMhApnt( TbMhApntKey vo ) throws EgovBizException {
		if( 1 != mhLockTableDAO.lockTbMhApnt( vo ) ){
			throw processException("fail.MHCommon.alreadyAccepted",new String[]{"인사발령"});
		}
	}

	@Override
	public void rejectTbMhApnt( List<? extends TbMhApntKey> list ) throws Throwable {
		for( TbMhApntKey vo : list ){
			if( 1 != mhLockTableDAO.lockTbMhApnt( vo ) ){
				throw processException("fail.MHCommon.alreadyAccepted",new String[]{"인사발령("+vo.getRn()+"라인)"});
			}
		}
	}

//	@Override
//	public void rejectTbMhDayempMst( TbMhDayempMstVO vo ) throws Throwable {
//		if( 1 != mhLockTableDAO.lockTbMhDayempMst( vo ) ){
//			throw processException("fail.MHCommon.alreadyAccepted",new String[]{"일용직채용등록"});
//		}
//	}

//	@Override
//	public void rejectTbMhShortempMst( TbMhShortempMstVO vo ) throws Throwable {
//		if( 1 != mhLockTableDAO.lockTbMhShortempMst( vo ) ){
//			throw processException("fail.MHCommon.alreadyAccepted",new String[]{"위촉직채용등록"});
//		}
//	}

//	@Override
//	public void appReqRegister( TbMhEduLectMstVO vo ) throws Throwable {
//		if( 1 != mhLockTableDAO.appReqRegister( vo ) ){
//			throw processException("fail.MHCommon.alreadyAccepted",new String[]{"일용직채용등록"});
//		}
//	}

//	@Override
//	public void appReqRptmAplList( TbMhRptmAplVO fvo ) throws Throwable {
//		if( 1 != mhLockTableDAO.appReqRptmAplList( fvo ) ){
//			throw processException("fail.MHCommon.alreadyAccepted",new String[]{"국외출장정산등록"});
//		}
//	}

	@Override
	public void rejectTbMhRtmnApl( TbMhRtmnAplVO vo ) throws Throwable {
		if( 1 != mhLockTableDAO.lockTbMhRtmnApl( vo ) ){
			throw processException("fail.MHCommon.alreadyAccepted",new String[]{"퇴직신청"});
		}
	}

//	@Override
//	public void lockTbMhEduApl(TbMhEduAplVO vo) throws Throwable {
//		if (1 != mhLockTableDAO.lockTbMhEduApl(vo)) {
//			throw processRuntimeException("valid.empty", "전자결재가 진행중인 건입니다. 자료를 확인해주시기 바랍니다.");
//		}
//	}

//	@Override
//	public void lockTbMhEduRult(TbMhEduRultVO vo) throws Throwable {
//		if (1 != mhLockTableDAO.lockTbMhEduRult(vo)) {
//			throw processRuntimeException("valid.empty", "전자결재가 진행중인 건입니다. 자료를 확인해주시기 바랍니다.");
//		}
//	}


//	@Override
//	public void rejectTbMhScsvVactApl( TbMhScsvVactAplVO vo ) throws Throwable {
//		if( 1 != mhLockTableDAO.lockTbMhScsvVactApl( vo ) ){
//			throw processException("fail.MHCommon.alreadyAccepted",new String[]{"연속휴가신청"});
//		}
//	}


//	@Override
//	public void lockTbMhSosvPlnApl(TbMhSosvPlnAplVO vo) throws Throwable {
//		if( 1 != mhLockTableDAO.lockTbMhSosvPlnApl( vo ) ){
//			throw processRuntimeException("valid.empty", "전자결재가 진행중인 건입니다. 자료를 확인해주시기 바랍니다.");
//		}
//	}


//	@Override
//	public void lockTbMhSosvRsltRept(TbMhSosvRsltReptVO vo) throws Throwable {
//		if( 1 != mhLockTableDAO.lockTbMhSosvRsltRept( vo ) ){
//			throw processRuntimeException("valid.empty", "전자결재가 진행중인 건입니다. 자료를 확인해주시기 바랍니다.");
//		}
//	}
}

















