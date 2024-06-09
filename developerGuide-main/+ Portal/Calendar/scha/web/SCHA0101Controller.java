package pierp.app.eip.bizSCH.scha.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pierp.app.common.cmd.SearchCmd;
import pierp.common.mvc.bind.InjectSessionInfo;
import pierp.common.mvc.web.BaseAbstractController;

/**
*
* 클래스
* file name : SCHA0101Controller.java
* 일정 메인화면 출력
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
public class SCHA0101Controller extends BaseAbstractController{


	Logger logger = LoggerFactory.getLogger(getClass());



    /**
     * 일정 메인화면 출력
     * @param SearchCmd
     * @return
     *
     * @deprecated 메인에서 뷰만 사용함
     */
	@Deprecated
    //@RequestMapping("/eip/SCH/SCHA0101.do")
    public ModelAndView mainSCHA0101(@InjectSessionInfo SearchCmd searchCmd) {
    	logger.debug(searchCmd.getRegUsrId());
    	ModelAndView mv = new ModelAndView("eip/bizSCH/scha/SCHA0101");

    	mv.addObject("cmd", searchCmd);
        return mv;
    }


    @RequestMapping("/ajaxR/SCHA0101/moveMonth.do")
    public ModelAndView moveMonth(@InjectSessionInfo SearchCmd searchCmd) {
    	logger.debug(searchCmd.getRegUsrId());
    	ModelAndView mv = new ModelAndView("eip/bizSCH/scha/SCHA0101");

    	mv.addObject("cmd", searchCmd);
        return mv;
    }

}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *
 */