package pierp.app.mgt.bizPOP.popo.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.cmd.ParamCmd;
import pierp.app.common.model.table.TbBpPupVO;
import pierp.app.mgt.bizPOP.popo.model.PopSearchCmd;
import pierp.app.mgt.bizPOP.popo.service.POPO01Service;
import pierp.common.mvc.bind.InjectSessionInfo;
import pierp.common.mvc.web.BaseAbstractController;
import pierp.common.mvc.web.ResponseDTO;


/**
*
* 클래스
* file name : POPO0101Controller.java
* 팝업 목록 화면
* @author 정성희
* @since 2022.05.21.
* @version 1.0
*
* <pre>
* == 개정이력(Modification Information) ==
*
* 수정일           수정자     수정내용
* -------------- -------- ---------------------------
* 2022.05.21.    정성희     최초 생성
* 2022.05.24.   이정민	기능 구현
* </pre>
*/

@Controller
@RequestMapping("")
public class POPO0101Controller extends BaseAbstractController{


	Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired
    private POPO01Service popo01Service;


    /**
     * 목록 화면 오픈
     * @param SearchCmd
     * @return
     */
    @RequestMapping("/mgt/POP/POPO0101.do")
    public ModelAndView mainPOPO0101(@InjectSessionInfo PopSearchCmd searchCmd) {
    	logger.debug(searchCmd.getRegUsrId());

//		String today = DateUtils.getToday();
//
//    	//달의 마지막 날 구하기
//    	Calendar cal = Calendar.getInstance();
//    	cal.set(Integer.parseInt(today.substring(0,4)), Integer.parseInt(today.substring(4,6))-1, Integer.parseInt(today.substring(6,8)));
//    	String lastDay = String.valueOf(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
//
//    	searchCmd.setRangeStartDate(today.substring(0,6) + "01");
//    	searchCmd.setRangeEndDate(today.substring(0,6) + lastDay);

    	ModelAndView mv = new ModelAndView("mgt/bizPOP/popo/POPO0101");
    	mv.addObject("cmd", searchCmd);
        return mv;
    }


    /**
     * 목록 조회
     * @param searchCmd
     * @return
     */
    @RequestMapping("/ajaxR/POPO0101/selectPupList.do")
    public @ResponseBody Object selectPupList(@InjectSessionInfo PopSearchCmd searchCmd) {

    	ResponseDTO resDto = createResponseDTO();
	  	List<TbBpPupVO> list;
		try {
			list = popo01Service.selectPupList(searchCmd);
			resDto.putAsGrid("pupVOList", list, TbBpPupVO.class);
			return resDto;
		} catch (EgovBizException e) {
    		return createResponseDTO(e);
		}
    }

    /**
     * 목록 건수 조회
     * @param searchCmd
     * @return
     */
    @RequestMapping("/ajaxR/POPO0101/selectPupCnt.do")
    public ModelAndView selectPupCnt(@InjectSessionInfo PopSearchCmd searchCmd) {
    	//페이징 처리 공통 뷰
    	ModelAndView mv = new ModelAndView("mgt/comm/paging");

    	try {
	    	mv.addObject("totalRowCount", popo01Service.selectPupCnt(searchCmd) );
    	} catch (EgovBizException e) {
    		mv.addObject("totalRowCount", "0" );
    		logger.error("ERROR:::selectPupCnt");
    	}

		mv.addObject("cmd", searchCmd);
		return mv;
    }


    /**
     * 목록 선택 삭제
     * @param paramCmd
     * @param pupNo
     * @return
     */
    @RequestMapping("/ajaxD/POPO0101/deletePup.do")
    public @ResponseBody Object deletePup(@InjectSessionInfo ParamCmd paramCmd, String[] pupNo) {
    	try {
    		paramCmd.setSelectedIds(pupNo);
    		popo01Service.deletePup(paramCmd);
    		return createResponseDTO("success.common.runResult", "팝업 목록", "삭제");
    	} catch (EgovBizException e) {
    		return createResponseDTO(e);
		}

    }



}
