package pierp.app.eip.bizENV.envb.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.cmd.SearchCmd;
import pierp.app.common.model.table.TbBpMyMenuVO;
import pierp.app.eip.bizENV.envb.model.MenuSearchCmd;
import pierp.app.eip.bizENV.envb.model.MyMenuListParam;
import pierp.app.eip.bizENV.envb.model.SyMenuVO;
import pierp.common.util.QueryResultMap;

/**
 * @Class ENVB01Service
 * @Description
 * 마이 메뉴 서비스를 정의한다.
 * @author 이정민
 * @since 2022.06.22.
 * @version
 *
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 * 2022.06.22.   이정민		최초 작성
 *
 */
public interface ENVB01Service {


	/**
	 * 시스템 구분
	 * @param param
	 * @return
	 * @throws EgovBizException
	 */
	public List<QueryResultMap> selectSysMenuDivList(SearchCmd searchCmd) throws EgovBizException ;

	/**
	 * 메뉴 1댑스 이하 전체 하위 메뉴 조회
	 * @param searchCmd
	 * @return
	 * @throws EgovBizException
	 */
	public List<SyMenuVO> select1DepthList(SearchCmd searchCmd) throws EgovBizException ;


	/**
	 * My메뉴 목록 조회
	 * @param menuSearchCmd
	 * @return
	 * @throws EgovBizException
	 */
	public List<TbBpMyMenuVO> selectMyMenuList(MenuSearchCmd menuSearchCmd) throws EgovBizException;



	/**
	 * my메뉴 저장
	 * @param bbsListParam
	 * @throws EgovBizException
	 */
	public void myMenuSave(MyMenuListParam myMenuListParam) throws EgovBizException;


	public List<String> selectAuthMenuList(SearchCmd searchCmd) throws EgovBizException;

}

