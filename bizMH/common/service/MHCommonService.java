package pierp.app.mis.bizMH.common.service;

import pierp.app.mis.bizMH.common.model.MHEmpVO;
import pierp.common.util.QueryResultMap;

public interface MHCommonService {

	/**
	 * 직원정보조회( MHEmpVO, EgovMap )
	 * @param empUniqNo
	 * @return
	 */
	MHEmpVO selectEmpVO(String empUniqNo);
	QueryResultMap selectEmpInfo(String empUniqNo);
	QueryResultMap selectEmpInfo2(String empNo);
	QueryResultMap selectEmpInfo3(String deptCd);

	/**
	 * 상위팀장(전결자) 조회
	 * @param empUniqNo
	 * @return
	 */
	QueryResultMap selectMngEmp(String empUniqNo);

	/**
	 * 상위팀장(대결자) 조회
	 * @param empUniqNo
	 * @return
	 */
	QueryResultMap selectMngEmp2(String empUniqNo);

//	/**
//	 * 부서정보조회( MHDeptVO, EgovMap )
//	 * @param deptUniqNo
//	 * @return
//	 */
//	MHDeptVO selectDeptVO(String deptUniqNo);
	QueryResultMap selectDeptInfo(String deptUniqNo);
//
//
//
	/**
	 * 근무정보조회( EgovMap )
	 * @param dt
	 * @return
	 */
	QueryResultMap selectWrkPl(String dt);
//
//
//
//	/**
//	 * 사진정보 조회
//	 * selectEmpPicByPicSrno	사진고유번호
//	 * selectEmpPicByEmpUniqNo	직원고유번호
//	 * selectEmpPicByExmNo		수험번호
//	 * selectCoOfslImg			사업장번호
//	 * @param vo
//	 * @return
//	 */
//	TbMhPicVO selectEmpPicByPicSrno( TbMhPicVO vo );
//	TbMhPicVO selectEmpPicByEmpUniqNo( TbMhPicVO vo );
//	TbMhPicVO selectEmpPicByExmNo( TbMhPicVO vo );
//	TbMhBzplVO selectCoOfslImg(TbMhBzplVO vo);
//
//
//
//	/**
//	 * selectAttchFilePath 공통 첨부파일정보 조회
//	 * @param attchDoctId
//	 * @param athflSeq
//	 * @return
//	 */
//	EgovMap selectAttchFilePath(String attchDoctId, int athflSeq );
//
//	/**
//	 * 사진정보 저장
//	 * saveEmpPicByEmpUniqNo	직원고유번호
//	 * saveEmpPicByExamNo		수험번호
//	 * saveBizPicByBizplCd      사업장번호
//	 */
//	void saveEmpPicByEmpUniqNo( String empUniqNo, String attchDoctId, UserInfo usr ) throws Throwable;
//	void saveEmpPicByExamNo( String examNo, String attchDoctId, UserInfo usr ) throws Throwable;
//	void saveBizPicByBizplCd( String bzplCd, String bzplNm, String attchDoctId, UserInfo usr ) throws Throwable;

//	/**
//	 * 전자결재 고정수신부서 조회
//	 * @param wrkGb
//	 * @param deptCd
//	 * @return
//	 */
//	String selectRcivDeptNm( String wrkGb, String deptCd );
//
//
//
//	/**
//	 * copyAttchDoc 첨부파일 마스터&디테일 복사(DB및 물리파일), EDMS연계 부분은 제외됨
//	 * @param srcAttchDoctNo
//	 * @param uploadPathKey
//	 * @param usrId
//	 * @return
//	 * @throws Throwable
//	 */
//	String copyAttchDoc( String srcAttchDoctNo, String uploadPathKey, String usrId ) throws Throwable;
//
//
//
//	/**
//	 * selectTopPotletMap 직원별 메인화면 내역 조회
//	 * @param empUniqNo
//	 * @return
//	 */
//	EgovMap selectTopPotletMap(String empUniqNo);



	/**
	 * getPublicActrAccn 대표계좌정보 조회
	 * @param empUniqNo
	 * @return
	 */
	QueryResultMap getPublicActrAccn(String empUniqNo);



	/**
	 * setPublicActrAccn 대표계좌 설정
	 * @param actrCd
	 * @param srno
	 * @throws Throwable
	 */
	void setPublicActrAccn(String actrCd, String srno) throws Throwable;



//	/**
//	 * 이미 생성되어있는 EDMS문서ID로 MIS공통 첨부테이블 정보를 생성 처리한다.
//	 * 2013.04.24 : 사업관리 시스템에서 EDMS문서키를 받아 공통 테이블 생성 처리 로직 필요함.
//	 * createAttchDocByEdmsDoctId
//	 * @param edmsDoctId	: EDMS문서번호
//	 * @param uploadPathKey	: 업무구분코드(dutiDivCd) : ex)MHA0101
//	 * @param usr			: 사용자 정보
//	 * @param bFileCrtn		: 물리파일 MIS에 복사여부(현재 지원하지 않음)
//	 * @return
//	 * @throws Throwable
//	 */
//	String createAttchDocByEdmsDoctId( String edmsDoctId, String uploadPathKey, UserInfo usr, boolean bFileCrtn ) throws Throwable;
//
//	void saveEmpPicByCertiSignEmpUniqNo(String empUniqNo, String attchDoctId,
//			String aplNo, UserInfo userInfo) throws Throwable;
//
//	TbMhCertiSignVO selectBizCertiSignAplNo(TbMhCertiSignVO vo);
}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *
 */