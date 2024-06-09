package pierp.app.eip.bizGRP.grpa.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.cmd.SearchCmd;
import pierp.app.eip.bizGRP.grpa.model.GwRetCntVO;
import pierp.app.eip.bizGRP.grpa.service.GRPA01Service;
import pierp.app.eip.bizTOD.toda.model.TodoSearchCmd;
import pierp.common.mvc.bind.InjectSessionInfo;
import pierp.common.mvc.web.BaseAbstractController;
import pierp.common.mvc.web.ResponseDTO;

/**
*
* 클래스
* file name : GRPA0101Controller.java
* 전자결재 메인화면 출력
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
public class GRPA0101Controller extends BaseAbstractController{


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
    private GRPA01Service grpa01Service;

    /**
     * 전자결재 메인화면 출력
     * @param SearchCmd
     * @return
     *
     * @deprecated 메인에서 뷰만 사용함
     */
	@Deprecated
    //@RequestMapping("/eip/GRP/GRPA0101.do")
    public ModelAndView mainGRPA0101(@InjectSessionInfo SearchCmd searchCmd) {
    	logger.debug(searchCmd.getRegUsrId());
    	ModelAndView mv = new ModelAndView("eip/bizGRP/grpa/GRPA0101");

    	mv.addObject("cmd", searchCmd);
        return mv;
    }


    /**
     * 전자결재 관련 건수 조회
     * @param searchCmd
     * @return
     */
    @RequestMapping("/ajaxR/GRPA0101/selectGwCnt.do")
    public @ResponseBody Object selectGwCnt(@InjectSessionInfo TodoSearchCmd searchCmd) {

    	ResponseDTO resDto = createResponseDTO();

		try {
			GwRetCntVO gwRetCntVO = grpa01Service.selectGwCnt(searchCmd);
			resDto.put("gwRetCntVO", gwRetCntVO);
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