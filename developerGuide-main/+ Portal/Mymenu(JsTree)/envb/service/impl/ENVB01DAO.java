package pierp.app.eip.bizENV.envb.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.cmd.SearchCmd;
import pierp.app.common.model.table.TbBpMyMenuVO;
import pierp.app.eip.bizENV.envb.model.MenuSearchCmd;
import pierp.app.eip.bizENV.envb.model.SyMenuVO;
import pierp.common.base.dao.BaseAbstractMapper;
import pierp.common.util.QueryResultMap;

/**
 * @Class ENVB01DAO
 * @Description
 * 마이메뉴 DAO 클래스를 정의한다.
 * @author 이정민
 * @since 2022.06.22.
 * @version
 *
/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *  2022.06.22.   이정민		최초 작성
 *
 */

@Repository
public class ENVB01DAO extends BaseAbstractMapper {


	/**
	 * 시스템 구분
	 * @param param
	 * @return
	 * @throws EgovBizException
	 */
	public List<QueryResultMap> selectSysMenuDivList(SearchCmd searchCmd) throws EgovBizException {
		return super.selectList("ENVB01.selectSysMenuDivList", searchCmd);
	}

	/**
	 * 메뉴 1댑스 이하 전체 하위 메뉴 조회
	 * @param searchCmd
	 * @return
	 * @throws EgovBizException
	 */
	public List<SyMenuVO> select1DepthList(SearchCmd searchCmd) throws EgovBizException {
		return super.selectList("ENVB01.select1DepthList", searchCmd);
	}

	/**
	 *
	 * @param searchCmd
	 * @return
	 * @throws EgovBizException
	 */
	public List<String> selectAuthMenuList(SearchCmd searchCmd) throws EgovBizException {
		return super.selectList("ENVB01.selectAuthMenuList", searchCmd);
	}


	/**
	 * 마이 메뉴 전체 목록 조회
	 * @param param
	 * @return
	 */
	public List<TbBpMyMenuVO> selectMyMenuList(MenuSearchCmd menuSearchCmd) {
		return selectList("ENVB01.selectMyMenuList", menuSearchCmd);
	}


	/**
	 * 마이메뉴 목록 저장
	 * @param param
	 * @return
	 */
	public int saveMyMenu(TbBpMyMenuVO tbBpMyMenuVO) {
		return super.update("ENVB01.saveMyMenu", tbBpMyMenuVO);
	}


	/**
	 * MY메뉴 전체 삭제
	 * @param param
	 * @return
	 */
	public int deleteMyMenu(MenuSearchCmd menuSearchCmd) throws EgovBizException {
		return super.delete("ENVB01.deleteMyMenu", menuSearchCmd);
	}




}

