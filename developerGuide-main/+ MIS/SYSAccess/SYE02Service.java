package pierp.app.stm.bizSY.sye.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.common.mvc.web.ParamVO;
import pierp.common.util.QueryResultMap;

/**
 * 접근제어 서비스 기능 정의.
 * file name : SYE02Service.java
 * 
 * @author 이정민
 * @since 2022.05.09
 * @version 1.0
 * @see
 * 
 * <pre>
 * == 개정이력(Modification Information) ==
 * 
 * 수정일     수정자   수정내용
 * ---------- -------- ---------------------------
 * 2022.05.09 이정민   최초 생성
 * </pre>
 */
public interface SYE02Service {

	/**
	 * 관리자/사용자 접근제어 조회
	 * 
	 * @param vo
	 * @return
	 * @throws EgovBizException
	 */
	List<QueryResultMap> selectAprhCtrlList(ParamVO vo);
	
	/**
	 * 관리자 접근제어 사용여부 조회
	 * @return
	 */
	String getMngrAprhCtrlYn();
	
	/**
	 * 관리자/사용자 접근제어 정보 저장
	 * @param vo
	 * @throws EgovBizException
	 * @throws Exception 
	 */
	void saveAprhCtrl(ParamVO vo) throws EgovBizException, Exception;
	
	/**
	 * 관리자/사용자 접근제어 정보 삭제
	 * @param vo
	 * @throws EgovBizException
	 * @throws Exception 
	 */
	void deleteAprhCtrl(ParamVO vo) throws EgovBizException, Exception;
	
	/**
	 * 관리자 접근제어 사용유무 수정
	 * @param vo
	 * @throws EgovBizException
	 * @throws Exception 
	 */
	void changeMngrAprhCtrl(String mngrIpAprhCtrlUseYn) throws EgovBizException, Exception;
	
	
	/**
	 * 사용자 접근제어 사용여부 조회
	 * @return
	 */
	String getUsrAprhCtrlYn();
	
	
	/**
	 * 사용자 접근제어 사용유무 수정
	 * @param vo
	 * @throws EgovBizException
	 * @throws Exception 
	 */
	void changeUsrAprhCtrl(String usrIpAprhCtrlUseYn) throws EgovBizException, Exception;
	
	
	
}
