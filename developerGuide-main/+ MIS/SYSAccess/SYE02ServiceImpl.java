package pierp.app.stm.bizSY.sye.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.property.EgovPropertyService;
import pierp.app.stm.bizSY.sye.service.SYE02Service;
import pierp.common.base.Constants;
import pierp.common.cmmn.service.BaseAbstractServiceImpl;
import pierp.common.main.service.LoginService;
import pierp.common.mvc.web.ParamVO;
import pierp.common.util.QueryResultMap;

/**
 * 접근제어 서비스 기능 구현.
 * file name : SYSB02ServiceImpl.java
 * 
 * @author 공통팀 김영기
 * @since 2022.05.09
 * @version 1.0
 * @see
 * 
 * <pre>
 * == 개정이력(Modification Information) ==
 * 
 * 수정일     수정자   수정내용
 * ---------- -------- ---------------------------
 * 2022.05.09 이정민  최초 생성
 * </pre>
 */
@Service
public class SYE02ServiceImpl extends BaseAbstractServiceImpl implements SYE02Service {
	
	@Autowired
	private SYE02DAO sye02DAO;
	
	@Resource(name="loginService")
	protected LoginService loginService;
	
	@Resource(name="sysPropService")
	protected EgovPropertyService sysPropService;

	/**
	 * 관리자/사용자 접근제어목록 조회
	 * 
	 * @param vo
	 * @return
	 * @throws EgovBizException
	 */
	@Override
	public List<QueryResultMap> selectAprhCtrlList(ParamVO vo) {
		return sye02DAO.selectAprhCtrlList(vo);
	}
	

	@Override
	public String getMngrAprhCtrlYn() {
		return sye02DAO.getMngrAprhCtrlYn();
	}
	
	
	/**
	 * 관리자/사용자 접근제어 정보 저장.
	 * 
	 * @param ParamVO
	 * @throws Exception 
	 * @throws EgovBizException
	 * @throws SQLException 
	 */
	@Override
	public void saveAprhCtrl(ParamVO vo) throws Exception{
		if(Constants.STATE_FLAG_INSERT.equals(vo.getString("stateFlag"))){
			sye02DAO.insertAprhCtrl(vo);
		}else if(Constants.STATE_FLAG_UPDATE.equals(vo.getString("stateFlag"))){
			sye02DAO.updateAprhCtrl(vo);
		}
		
	}
	
	/**
	 * 관리자/사용자 접근제어 정보 삭제.
	 * 
	 * @param ParamVO
	 * @throws Exception 
	 * @throws EgovBizException
	 * @throws SQLException 
	 */
	@Override
	public void deleteAprhCtrl(ParamVO vo) throws EgovBizException {
		sye02DAO.deleteAprhCtrl(vo);
	}

	@Override
	public void changeMngrAprhCtrl(String mngrIpAprhCtrlUseYn) throws EgovBizException {
		sye02DAO.changeMngrAprhCtrl(mngrIpAprhCtrlUseYn);
	}

	@Override
	public void changeUsrAprhCtrl(String usrIpAprhCtrlUseYn) throws EgovBizException {
		sye02DAO.changeUsrAprhCtrl(usrIpAprhCtrlUseYn);		
	}

	
	@Override
	public String getUsrAprhCtrlYn() {
		return sye02DAO.getUsrAprhCtrlYn();
	}
	
}
