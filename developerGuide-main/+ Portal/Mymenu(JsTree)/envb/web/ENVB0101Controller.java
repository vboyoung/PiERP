package pierp.app.eip.bizENV.envb.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.property.EgovPropertyService;
import pierp.app.common.cmd.SearchCmd;
import pierp.app.common.model.table.TbBpMyMenuVO;
import pierp.app.eip.bizENV.envb.model.MenuSearchCmd;
import pierp.app.eip.bizENV.envb.model.MyMenuListParam;
import pierp.app.eip.bizENV.envb.model.SyMenuVO;
import pierp.app.eip.bizENV.envb.service.ENVB01Service;
import pierp.common.mvc.bind.InjectSessionInfo;
import pierp.common.mvc.web.BaseAbstractController;
import pierp.common.mvc.web.ResponseDTO;
import pierp.common.util.QueryResultMap;


/**
*
* 클래스
* file name : ENVB0101Controller.java
* 환경설정 - 마이메뉴 설정 화면
* @author 이정민
* @since 2022.06.22.
* @version 1.0
*
* <pre>
* == 개정이력(Modification Information) ==
*
* 수정일           수정자     수정내용
* -------------- -------- ---------------------------
* 2022.06.22.    이정민     최초 생성
*
* </pre>
*/

@Controller
@RequestMapping("")
public class ENVB0101Controller extends BaseAbstractController{

	Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ENVB01Service envb01Service;

	@Resource(name="eipPropService")
	protected EgovPropertyService eipPropService;


    /**
     * [ENVB0101] 마이메뉴 설정 화면 오픈
     * @param SearchCmd
     * @return
     */
    @RequestMapping("/eip/ENV/ENVB0101.do")
    public ModelAndView mainENVB0101(@InjectSessionInfo SearchCmd searchCmd) {
    	logger.debug(searchCmd.getRegUsrId());

    	String maxMnuCnt = eipPropService.getString("eip.env.mym.maxCnt", "8"); // eip.properties, default 값

    	ModelAndView mv = new ModelAndView("eip/bizENV/envb/ENVB0101");

    	mv.addObject("maxMnuCnt", maxMnuCnt);
    	mv.addObject("cmd", searchCmd);

        return mv;
    }


    /**
     * 마이메뉴 시스템구분 목록조회
     * @param searchCmd
     * @return
     */
    @RequestMapping("/ajaxR/ENVB0101/selectSysMenuDivList.do")
    public @ResponseBody Object selectSysMenuDivList(@InjectSessionInfo SearchCmd searchCmd) {

    	ResponseDTO resDto = createResponseDTO();
    	List<QueryResultMap> list;
    	try {
    		list = envb01Service.selectSysMenuDivList(searchCmd);
    		resDto.putAsGrid("sysList", list);

    		return resDto;
    	} catch (EgovBizException e) {
    		return createResponseDTO(e);
    	}
    }


    /**
     * 메뉴 1댑스 이하 전체 하위 메뉴 조회
     * @param searchCmd
     * @return
     */
    @RequestMapping("/ajaxR/ENVB0101/select1DepthList.do")
    public @ResponseBody Object select1DepthList(@InjectSessionInfo SearchCmd searchCmd) {

    	if(StringUtils.isEmpty(searchCmd.getSearchText())) {
    		searchCmd.setSearchText("MIS");
    	}

    	ResponseDTO resDto = createResponseDTO();
    	List<SyMenuVO> list;
    	try {
    		list = envb01Service.select1DepthList(searchCmd);

            List<String> authList = envb01Service.selectAuthMenuList(searchCmd);
            List<SyMenuVO> filterList = menuAuthFilter(list, authList);

            List<SyMenuVO> resultList = menuFilter(filterList);

            resDto.putAsGrid("SyMenuVO", resultList, SyMenuVO.class);

    		return resDto;
    	} catch (EgovBizException e) {
    		return createResponseDTO(e);
    	}
    }



    private List<SyMenuVO> menuAuthFilter(List<SyMenuVO> list, List<String> authList) {
       List<SyMenuVO> ret = new ArrayList<>();
       for(SyMenuVO vo : list) {

          vo.setSubMenuList(menuAuthFilter(vo.getSubMenuList(), authList ));

          if( vo.getMdulId() == null ) {
             ret.add(vo);
          }else if( authChk(vo.getMenuId(),  authList) ) {
             ret.add(vo);
          }
       }

       return ret;
    }

    private List<SyMenuVO> menuFilter(List<SyMenuVO> list){
    	List<SyMenuVO> ret = new ArrayList<>();

    	for(SyMenuVO vo : list) {

    		// 재귀 호출 -> subMenuList를 재귀하여 역순 호출
    		vo.setSubMenuList(menuFilter(vo.getSubMenuList() ));

    		// subMenuList가 존재할경우 List에 ret에 담는다.
			if(vo.getSubMenuList() != null && vo.getSubMenuList().size() > 0) {
				ret.add(vo);
			}

			// 메뉴구분아이디 (T,G,M 의 경우중 M(모듈)일 경우 무조건 결과 list에 담는다.
			if( vo.getMenuDivCd().equals("M") ) {
				ret.add(vo);
			}

    	}

    	return ret;
    }


    private boolean authChk(String menuId, List<String> list) {
       return list.contains(menuId);
    }


	/**
	 * 사용자 마이메뉴 설정 목록 조회
	 * @param searchCmd
	 * @param br
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ajaxR/ENVB0101/selectMyMenuList.do")
	public @ResponseBody Object selectMyMenuList(@InjectSessionInfo MenuSearchCmd menuSearchCmd) {
		ResponseDTO resDto = createResponseDTO();
		try {
			List<TbBpMyMenuVO> list = envb01Service.selectMyMenuList(menuSearchCmd);
			resDto.putAsGrid("myMenuList", list, TbBpMyMenuVO.class);
		} catch (EgovBizException eBiz) {
			resDto = createResponseDTO(eBiz);
		}

		return resDto;
	}


	/**
	 * 마이메뉴 설정 목록 저장
	 * @param menuSearchCmd
	 * @param listData
	 * @return
	 */
    @RequestMapping("/ajaxS/ENVB0101/myMenuSave.do")
    public @ResponseBody Object myMenuSave(@InjectSessionInfo MenuSearchCmd menuSearchCmd, String listData )  {
    	try {

    		MyMenuListParam myMenuListParam = new MyMenuListParam();
    		myMenuListParam.setQmap(listData);
    		myMenuListParam.setMenuSearchCmd(menuSearchCmd);

    		envb01Service.myMenuSave(myMenuListParam);

        	return createResponseDTO("success.common.runResult", "마이메뉴", "저장");

    	} catch (EgovBizException e) {
    		return createResponseDTO(e);
		}
    }


}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *
 */