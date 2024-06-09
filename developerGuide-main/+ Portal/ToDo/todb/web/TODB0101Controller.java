package pierp.app.eip.bizTOD.todb.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.cmd.SearchCmd;
import pierp.app.eip.bizTOD.toda.model.TodoSearchCmd;
import pierp.app.eip.bizTOD.toda.service.TODA01Service;
import pierp.app.eip.bizTOD.todb.model.BpTodoForm;
import pierp.app.eip.bizTOD.todb.service.TODB01Service;
import pierp.common.mvc.bind.InjectSessionInfo;
import pierp.common.mvc.validation.Validation;
import pierp.common.mvc.web.BaseAbstractController;


/**
*
* 클래스
* file name : TODB0101Controller.java
* TO_DO 나의 할일 목록 화면
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
public class TODB0101Controller extends BaseAbstractController{


	Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired
    private TODB01Service todb01Service;

	@Autowired
    private TODA01Service toda01Service;

    /**
     * [TODB0101] TO_DO 나의 할일 목록 화면 오픈
     * @param SearchCmd
     * @return
     *
     */
    @RequestMapping("/eip/TOD/TODB0101.do")
    public ModelAndView mainTODB0101(@InjectSessionInfo SearchCmd searchCmd) {
    	logger.debug(searchCmd.getRegUsrId());
    	ModelAndView mv = new ModelAndView("eip/bizTOD/todb/TODB0101");
    	mv.addObject("cmd", searchCmd);
        return mv;
    }


    @RequestMapping("/ajaxR/TODB0101/selectTodoCnt.do")
    public ModelAndView selectPubAnucMattCnt(@InjectSessionInfo TodoSearchCmd searchCmd) {

    	ModelAndView mv = new ModelAndView("eip/comm/paging");

    	try {
    		mv.addObject("totalRowCount", toda01Service.selectTodoCnt(searchCmd));

    	}catch(EgovBizException e) {
    		mv.addObject("totalRowCount", "0");
    		logger.error("ERROR:::selectTodoCnt");
    	}

    	mv.addObject("cmd", searchCmd);
    	return mv;
    }


    /**
     * TO-DO 보류 상태값 변경
     * @param bpTodoForm
     * @param br
     * @return
     */
    @RequestMapping("/ajaxS/TODB0101/updateTodoStat.do")
    @Validation(target = BpTodoForm.class)
    public @ResponseBody Object updateTodoStat(@InjectSessionInfo BpTodoForm bpTodoForm, BindingResult br){

    	try {
    		todb01Service.updateTodoStat(bpTodoForm);
    		return createResponseDTO("success.common.runResult", "TO-DO 보류 상태", "변경");

		}catch (EgovBizException e) {
    		return createResponseDTO(e);
		}
    }


    /**
     * TO-DO 중요 상태값 변경
     * @param bpTodoForm
     * @param br
     * @return
     */
    @RequestMapping("/ajaxS/TODB0101/updateTodoImpr.do")
    @Validation(target = BpTodoForm.class)
    public @ResponseBody Object updateTodoImpr(@InjectSessionInfo BpTodoForm bpTodoForm, BindingResult br){

    	try {
    		todb01Service.updateTodoImpr(bpTodoForm);
    		return createResponseDTO();

    	}catch (EgovBizException e) {
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