package pierp.app.mis.bizMH.common.model;


import org.springframework.stereotype.Repository;

import pierp.app.mis.common.table.model.TbMhApntKey;
import pierp.app.mis.common.table.model.TbMhDeptChgVO;
import pierp.app.mis.common.table.model.TbMhDtBstrAplVO;
import pierp.app.mis.common.table.model.TbMhOfryAplVO;
import pierp.app.mis.common.table.model.TbMhRptmAplVO;
import pierp.app.mis.common.table.model.TbMhRtmnAplVO;
import pierp.app.mis.common.table.model.TbMhTrxpAplVO;
import pierp.app.mis.common.table.model.TbMhVactAplVO;
import pierp.common.base.dao.BaseAbstractMapper;


/**
 * 클래스
 * file name : MHLockTableDAO.java
 *
 * 테이블과 관련하여 결재상태를 컨트롤 합니다.
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
@Repository
public class MHLockTableDAO extends BaseAbstractMapper {

	/**
	 * 조직개편 저장
	 */
	public int lockTbMhDeptChg(Long chgSrno, String dfnYn) {
		TbMhDeptChgVO vo = new TbMhDeptChgVO();
		vo.setChgSrno( chgSrno );
		vo.setDfnYn( dfnYn );

		int cnt = (Integer)selectByPk( "MHLockTable.lockTbMhDeptChg", vo );
		return cnt;
	}


	/**
	 * 유연근무(선택,단시간) 신청
	 */
//	public int lockTbMhFwp( TbMhFwpVO vo ) {
//		return update( "MHLockTable.lockTbMhFwp", vo );
//	}


	/**
	 * 조퇴/외출 신청
	 */
	public int lockTbMhOfryApl( TbMhOfryAplVO vo ) {
		return update( "MHLockTable.lockTbMhOfryApl", vo );
	}


	/**
	 * 휴가 신청
	 */
	public int lockTbMhVactApl( TbMhVactAplVO vo ) {
		return update( "MHLockTable.lockTbMhVactApl", vo );
	}


	/**
	 * 시간외근무 신청
	 */
//	public int lockTbMhOvWrkApl( TbMhOvWrkAplVO vo ) {
//		return update( "MHLockTable.lockTbMhOvWrkApl", vo );
//	}



	/**
	 * 출장 신청
	 */
	public int lockTbMhTrxpApl( TbMhTrxpAplVO vo ){
		return update( "MHLockTable.lockTbMhTrxpApl", vo );
	}

	/**
	 * 출장 신청
	 */
	public int lockTbMhTrxpApl2( TbMhTrxpAplVO vo ){
		return update( "MHLockTable.lockTbMhTrxpApl2", vo );
	}

	/**
	 * 출장복명 신청
	 */
	public int lockTbMhRptmApl( TbMhRptmAplVO vo ) {
		return update( "MHLockTable.lockTbMhRptmApl", vo );
	}


	/**
	 * 근무지내출장 신청
	 */
	public int lockTbMhDtBstrApl( TbMhDtBstrAplVO vo ) {
		return update( "MHLockTable.lockTbMhDtBstrApl", vo );
	}


	/**
	 * lockTbMhApnt 인사발령
	 * @param vo
	 * @return
	 */
	public int lockTbMhApnt( TbMhApntKey vo ) {
		return update( "MHLockTable.lockTbMhApnt", vo );
	}

	/**
	 * lockTbMhDayempMst 일용직채용
	 * @param vo
	 * @return
	 */
//	public int lockTbMhDayempMst(TbMhDayempMstVO vo) {
//		return update( "MHLockTable.lockTbMhDayempMst", vo );
//	}

	/**
	 * lockTbMhShortempMst 임시직채용
	 * @param vo
	 * @return
	 */
//	public int lockTbMhShortempMst(TbMhShortempMstVO vo) {
//		return update( "MHLockTable.lockTbMhShortempMst", vo );
//	}

//	public int appReqRegister(TbMhEduLectMstVO vo) {
//		return update( "MHLockTable.appReqRegister", vo );
//	}


//	public int appReqRptmAplList(TbMhRptmAplVO fvo) {
//		return update( "MHLockTable.appReqRptmAplList", fvo );
//	}

	/**
	 * 퇴직 신청
	 */
	public int lockTbMhRtmnApl( TbMhRtmnAplVO vo ) {
		return update( "MHLockTable.lockTbMhRtmnApl", vo );
	}


//	public int lockTbMhEduApl(TbMhEduAplVO vo) {
//		return update( "MHLockTable.lockTbMhEduApl", vo );
//	}


//	public int lockTbMhEduRult(TbMhEduRultVO vo) {
//		return update( "MHLockTable.lockTbMhEduRult", vo );
//	}


	/**
	 * 연속휴가 신청
	 */
//	public int lockTbMhScsvVactApl( TbMhScsvVactAplVO vo ) {
//		return update( "MHLockTable.lockTbMhScsvVactApl", vo );
//	}

//	public int lockTbMhSosvPlnApl( TbMhSosvPlnAplVO vo ) {
//		return update( "MHLockTable.lockTbMhSosvPlnApl", vo );
//	}

//	public int lockTbMhSosvRsltRept( TbMhSosvRsltReptVO vo ) {
//		return update( "MHLockTable.lockTbMhSosvRsltRept", vo );
//	}

}
