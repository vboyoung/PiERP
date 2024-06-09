package pierp.app.stm.bizSY.sye.service.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import pierp.common.base.dao.BaseAbstractMapper;
import pierp.common.mvc.web.ParamVO;
import pierp.common.util.QueryResultMap;

/**
 * 접근제어 관리관련 data access 기능 정의.
 * file name : SYE02DAO.java
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
 * 2022.05.09 이정민 최초생성
 * </pre>
 */
@Repository
public class SYE02DAO extends BaseAbstractMapper {

	/**
	 * 관리자 접근제어 목록 조회.
	 * 
	 * @param vo
	 * @return
	 */
    public List<QueryResultMap> selectAprhCtrlList(ParamVO vo) {
        return selectList("SYE02.selectAprhCtrlList", vo.getMap());
	}
    
    /**
     * 관리자 접근제어 사용여부 조회
     * @return
     */
    public String getMngrAprhCtrlYn() {
    	return selectOne("SYE02.getMngrAprhCtrlYn");
    }


	/**
	 * 관리자/사용자 접근제어 정보 생성
	 * @param vo
	 */
	public void insertAprhCtrl(ParamVO vo) {
		insert("SYE02.insertAprhCtrl", vo.getMap());
	}

	/**
	 * 관리자 접근제어 정보 수정
	 * @param vo
	 */
	public void updateAprhCtrl(ParamVO vo) {
		update("SYE02.updateAprhCtrl", vo.getMap());
	}
	
	/**
	 * 관리자 접근제어 정보 생성
	 * @param vo
	 */
	public void deleteAprhCtrl(ParamVO vo) {
		insert("SYE02.deleteAprhCtrl", vo.getMap());
	}
	
	/**
	 * 관리자 접근제어 사용유무 수정
	 * @param vo
	 */
	public void changeMngrAprhCtrl(String mngrIpAprhCtrlUseYn) {
		update("SYE02.changeMngrAprhCtrl", mngrIpAprhCtrlUseYn);
	}

    
    /**
     * 사용자 접근제어 사용여부 조회
     * @return
     */
    public String getUsrAprhCtrlYn() {
    	return selectOne("SYE02.getUsrAprhCtrlYn");
    }


	/**
	 * 사용자 접근제어 정보 생성
	 * @param vo
	 */
	public void insertTbSyUsrAprhCtrl(ParamVO vo) {
		insert("SYE02.insertTbSyUsrAprhCtrl", vo.getMap());
	}

	
	/**
	 * 사용자 접근제어 사용유무 수정
	 * @param vo
	 */
	public void changeUsrAprhCtrl(String usrIpAprhCtrlUseYn) {
		update("SYE02.changeUsrAprhCtrl", usrIpAprhCtrlUseYn);
	}
	

}
