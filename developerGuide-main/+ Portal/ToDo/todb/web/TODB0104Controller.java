package pierp.app.eip.bizTOD.todb.web;

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
* file name : TODB0104Controller.java
* TO_DO 보류 목록 목록 화면
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
public class TODB0104Controller extends BaseAbstractController{


	Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * [TODB0104] TO_DO 보류 목록 화면 오픈
     * @param SearchCmd
     * @return
     *
     */
    @RequestMapping("/eip/TOD/TODB0104.do")
    public ModelAndView mainTODB0104(@InjectSessionInfo SearchCmd searchCmd) {
    	logger.debug(searchCmd.getRegUsrId());
    	ModelAndView mv = new ModelAndView("eip/bizTOD/todb/TODB0104");
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