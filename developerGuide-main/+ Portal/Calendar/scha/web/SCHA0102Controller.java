package pierp.app.eip.bizSCH.scha.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.cmd.SearchCmd;
import pierp.app.eip.bizSCH.scha.model.GwScheduleVO;
import pierp.app.eip.bizSCH.scha.model.ScheduleSearchCmd;
import pierp.app.eip.bizSCH.scha.service.SCHA01Service;
import pierp.common.mvc.bind.InjectSessionInfo;
import pierp.common.mvc.web.BaseAbstractController;
import pierp.common.mvc.web.ResponseDTO;
import pierp.common.util.StringUtils;

/**
*
* 클래스
* file name : SCHA0102Controller.java
* 일정 목록 메인화면 출력
* @author 이정민
* @since 2022.05.17.
* @version 1.0
*
* <pre>
* == 개정이력(Modification Information) ==
*
* 수정일           수정자     수정내용
* -------------- -------- ---------------------------
* 2022.05.17.    이정민     최초 생성
*
* </pre>
*/

@Controller
@RequestMapping("")
public class SCHA0102Controller extends BaseAbstractController{


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
    private SCHA01Service scha01Service;

    /**
     * 일정 목록 메인화면 출력
     * @param SearchCmd
     * @return
     *
     * @deprecated 메인에서 뷰만 사용함
     */
	@Deprecated
    //@RequestMapping("/eip/SCH/SCHA0102.do")
    public ModelAndView mainSCHA0102(@InjectSessionInfo SearchCmd searchCmd) {
    	logger.debug(searchCmd.getRegUsrId());
    	ModelAndView mv = new ModelAndView("eip/bizSCH/scha/SCHA0102");

    	mv.addObject("cmd", searchCmd);
        return mv;
    }


    /**
     * 일정 목록조회
     * @param searchCmd
     * @return
     */
    @RequestMapping("/ajaxR/SCHA0102/selectScheduleList.do")
    public @ResponseBody Object selectScheduleList(@InjectSessionInfo ScheduleSearchCmd searchCmd) {

    	logger.debug(searchCmd.toString());
    	ResponseDTO resDto = createResponseDTO();

    	if(StringUtils.isNotEmpty(searchCmd.getSchYear()) && StringUtils.isNotEmpty(searchCmd.getSchMonth()) && StringUtils.isNotEmpty(searchCmd.getSchDay()) ) {
    		searchCmd.setSearchText(searchCmd.getSchYear() + "-" + searchCmd.getSchMonth() + "-" + searchCmd.getSchDay());
    	}
    	if(StringUtils.isEmpty(searchCmd.getOwnerTp())) {
    		searchCmd.setOwnerTp("1");
    	}

	  	List<GwScheduleVO> list;
		try {
			list = scha01Service.selectScheduleList(searchCmd);
			resDto.putAsGrid("gwScheduleVOList", list, GwScheduleVO.class);
			return resDto;
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