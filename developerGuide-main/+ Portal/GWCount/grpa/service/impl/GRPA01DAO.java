package pierp.app.eip.bizGRP.grpa.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.cmd.SearchCmd;
import pierp.app.eip.bizGRP.grpa.model.GwRetCntVO;
import pierp.app.eip.bizGRP.grpa.model.GwSelectVO;
import pierp.common.base.dao.BaseAbstractMapper;

/**
 * @Class GRPA01DAO
 * @Description
 * 메인 전자결재 관련  DAO 클래스를 정의한다.
 * @author 박성현
 * @since 2022.06.22.
 * @version
 *
 */
@Repository
public class GRPA01DAO extends BaseAbstractMapper {


	/**
	 * 전자결재 건수 조회 (그룹웨어측에서 제공한 인터페이스 뷰 조회)
	 * @param searchCmd
	 * @return
	 * @throws EgovBizException
	 */
	public List<GwSelectVO> selectGwAtSancCnt(SearchCmd searchCmd) throws EgovBizException {
		return super.selectList("GRPA01.selectGwAtSancCnt", searchCmd);
	}

	/**
	 * 그룹웨어 게시판 최신 글 건수 조회
	 * @param searchCmd
	 * @return
	 * @throws EgovBizException
	 */
	public GwRetCntVO selectNewBbsCnt(SearchCmd searchCmd) throws EgovBizException {
		GwRetCntVO gwRetCntVO = super.selectOne("GRPA01.selectNewBbsCnt", searchCmd);

		if(gwRetCntVO == null) {
			gwRetCntVO = new GwRetCntVO();
			gwRetCntVO.setBbsNewHom("0");
		}

		return gwRetCntVO;
	}

}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 * 2020.07.24.   박성현
 */