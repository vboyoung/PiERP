package pierp.app.eip.bizENV.envb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.property.EgovPropertyService;
import pierp.app.common.cmd.SearchCmd;
import pierp.app.common.model.table.TbBpMyMenuVO;
import pierp.app.eip.bizENV.envb.model.MenuSearchCmd;
import pierp.app.eip.bizENV.envb.model.MyMenuListParam;
import pierp.app.eip.bizENV.envb.model.SyMenuVO;
import pierp.app.eip.bizENV.envb.service.ENVB01Service;
import pierp.app.mgt.bizMNU.mnuo.service.impl.MNUO01DAO;
import pierp.common.cmmn.service.BaseAbstractServiceImpl;
import pierp.common.util.QueryResultMap;

/**
 * @Class ENVB01ServiceImpl
 * @Description
 * 마이 메뉴 서비스구현체를 정의한다.
 * @author 이정민
 * @since 2022.06.22.
 * @version
 *
 *  * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 * 2022.06.22.   이정민		최초 작성
 *
 */

@Service
public class ENVB01ServiceImpl extends BaseAbstractServiceImpl implements ENVB01Service{


	@Autowired
	ENVB01DAO envb01Dao;

	@Autowired
	MNUO01DAO mnuo01Dao;

	@Resource(name="eipPropService")
	protected EgovPropertyService eipPropService;

	@Override
	public List<QueryResultMap> selectSysMenuDivList(SearchCmd searchCmd) throws EgovBizException {
		return envb01Dao.selectSysMenuDivList(searchCmd);
	}

	@Override
	public List<SyMenuVO> select1DepthList(SearchCmd searchCmd) throws EgovBizException {
		return envb01Dao.select1DepthList(searchCmd);
	}


	@Override
	public List<TbBpMyMenuVO> selectMyMenuList(MenuSearchCmd menuSearchCmd) throws EgovBizException {
		return envb01Dao.selectMyMenuList(menuSearchCmd);
	}


	@Override
	public void myMenuSave(MyMenuListParam myMenuListParam) throws EgovBizException {
		TbBpMyMenuVO tbBpMyMenuVO = new TbBpMyMenuVO();
		MenuSearchCmd menuSearchCmd = myMenuListParam.getMenuSearchCmd();

		int chkCnt = 0;
		int deleteCnt = 0;

		if(menuSearchCmd != null && !menuSearchCmd.getSavedMenuCnt().equals("0")) { // 기존 MyMenu 전체 삭제 처리
			int getMyMenuCnt = Integer.parseInt(menuSearchCmd.getSavedMenuCnt());
			deleteCnt = envb01Dao.deleteMyMenu(menuSearchCmd);
			if (deleteCnt != getMyMenuCnt ) {
				getLogger().error("MyMenu 전체삭제 오류");
				throw processException("fail.common.runError",new String[]{"MyMenu","삭제시 오류가 발생 하였습니다."});
	        }
		}

		Map<String, ArrayList<String>> map = myMenuListParam.getQmap();
		if(map == null) {
			getLogger().error("MyMenu 전체삭제 오류");
			throw processException("fail.common.runError",new String[]{"MyMenu","삭제시 요청 데이터 오류가 발생 하였습니다."});
		}
		ArrayList<String> menuNmList = (ArrayList<String>)map.get("menuNm");
		ArrayList<String> upMenuList = (ArrayList<String>)map.get("chocTopMenuId");
		ArrayList<String> menuIdList = (ArrayList<String>)map.get("chocMenuId");
		ArrayList<String> mdulIdList = (ArrayList<String>)map.get("chocMdulId");
		if(menuNmList != null && upMenuList != null && menuIdList != null && mdulIdList != null) {

			int MAX_MYMENU_CNT = Integer.parseInt(eipPropService.getString("eip.env.mym.maxCnt"));

			if(menuNmList.size() > MAX_MYMENU_CNT) {
				getLogger().error("MyMenu 최대 저장 갯수 오류");
				throw processException("fail.common.runError",new String[]{"MyMenu","최대 " + MAX_MYMENU_CNT + "개 까지 설정 가능합니다."});
			}
			if(!menuNmList.isEmpty() && !upMenuList.isEmpty() && !menuIdList.isEmpty() && !mdulIdList.isEmpty()) {
				if(menuNmList.size() == upMenuList.size() && menuNmList.size() == menuIdList.size() && menuNmList.size() == mdulIdList.size()) {
					for(int i=0; i < menuNmList.size(); i++) {
						tbBpMyMenuVO.setMenuNm(menuNmList.get(i));
						tbBpMyMenuVO.setChocTopMenuId(upMenuList.get(i));
						tbBpMyMenuVO.setChocMenuId(menuIdList.get(i));
						tbBpMyMenuVO.setChocMdulId(mdulIdList.get(i));
						tbBpMyMenuVO.setLupOrd(Integer.toString(i+1));
						tbBpMyMenuVO.setUsrId(menuSearchCmd.getRegUsrId());

						tbBpMyMenuVO.setRegEmpNo(menuSearchCmd.getRegEmpNo());
						tbBpMyMenuVO.setRegUsrId(menuSearchCmd.getRegUsrId());
						tbBpMyMenuVO.setLastChgEmpNo(menuSearchCmd.getLastChgEmpNo());
						tbBpMyMenuVO.setLastChgUsrId(menuSearchCmd.getLastChgUsrId());
						chkCnt += envb01Dao.saveMyMenu(tbBpMyMenuVO);
					}
				}
				if (chkCnt != menuNmList.size() ) {
					getLogger().error("MyMenu 저장 오류");
					throw processException("fail.common.runError",new String[]{"MyMenu","저장시 오류가 발생 하였습니다."});
				}
			}

		}
	}

	@Override
	public List<String> selectAuthMenuList(SearchCmd searchCmd) throws EgovBizException {
		return envb01Dao.selectAuthMenuList(searchCmd);
	}
}

