package pierp.app.common.security.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.security.model.AprhCtrlVO;
import pierp.app.common.security.model.TbSyUsrComSecuSetVO;

/**
 * 클래스
 * file name : SecurityService.java
 *
 * @author 정성희
 * @since 2020. 8. 12..
 * @version 1.0
 *
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 * 수정일           수정자     수정내용
 * -------------- -------- ---------------------------
 * 2020. 8. 12..    정성희     최초 생성
 * 2022. 5. 11.     이정민     접근제어 통합관리로 인해 메서드 수정
 *
 * </pre>
 */

public interface SecurityService {

	/**
	 * 사용자공통보안설정 조회
	 * @return
	 * @throws EgovBizException
	 */
	public TbSyUsrComSecuSetVO selectSysUsrComSecuSet() throws EgovBizException;
	
	/**
	 * 접근제어 허용 IP 범위 조회
	 * @return
	 * @throws EgovBizException
	 */
	public List<AprhCtrlVO> selectAprhCtrlList(String sysCls)  throws EgovBizException;
	
}

