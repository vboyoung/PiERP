package pierp.app.mgt.bizPOP.popo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.model.table.TbBpPupVO;
import pierp.app.mgt.bizPOP.popo.model.PopForm;
import pierp.app.mgt.bizPOP.popo.model.PopSearchCmd;
import pierp.app.mgt.bizPOP.popo.service.POPO01Service;
import pierp.common.mvc.bind.InjectSessionInfo;
import pierp.common.mvc.validation.Validation;
import pierp.common.mvc.web.BaseAbstractController;
import pierp.common.mvc.web.ResponseDTO;
import pierp.common.util.StringUtils;
import pierp.module.fileAttach.service.FileParamVO;

/**
* 클래스
* file name : POPO0102Controller.java
* 팝업 목록 상세화면
* @author 이정민
* @since 2020.05.24.
* @version 1.0
* <pre>
* == 개정이력(Modification Information) ==
* 수정일           수정자     수정내용
* -------------- -------- ---------------------------
* 2022.05.24.   이정민	최초 생성
* </pre>
*
*/


@Controller
public class POPO0102Controller extends BaseAbstractController {

    @Autowired
    private POPO01Service popo01Service;

    /**
     * 팝업관리 등록/수정 화면 오픈
     * @param searchCmd
     * @return
     */
    @RequestMapping("/mgt/POP/POPO0102.do")
    public ModelAndView mainPOPO0102(@InjectSessionInfo PopSearchCmd searchCmd) {

    	logger.debug(searchCmd.getRegUsrId());

    	ModelAndView mv = new ModelAndView("mgt/bizPOP/popo/POPO0102");
    	mv.addObject("cmd", searchCmd);
        return mv;
    }


    /**
     * 팝업 단건 상세 조회
     * @param searchCmd
     * @return
     */
    @RequestMapping("/ajaxR/POPO0102/selectPubOne.do")
    public @ResponseBody Object selectPqueDutyOne(@InjectSessionInfo PopSearchCmd searchCmd) {

    	ResponseDTO resDto = createResponseDTO();

	   	try {
	   			TbBpPupVO pupVO = popo01Service.selectPupOne(searchCmd);
	    		resDto.put("pupVO", pupVO);

			} catch (EgovBizException eBiz) {
				resDto = createResponseDTO(eBiz);
			}

	    	return resDto;
    }



    /**
     * 팝업 데이터 저장
     * @param popPupForm
     * @param br
     * @return
     * @throws Exception
     */
    @RequestMapping("/ajaxS/POPO0102/savePub.do")
    @Validation(target = PopForm.class)
    public @ResponseBody Object savePup(@InjectSessionInfo PopForm popForm, FileParamVO uploader, BindingResult br) {

    	try {

        	//필수 공지 사항 체크박스 처리...체크되지 않으면 NULL로 들어옴
        	if( StringUtils.isEmpty(popForm.getMndtAnucYn()) ) {
        		popForm.setMndtAnucYn("N");
        	}

        	//텍스트 포함 여부 체크박스 처리...체크되지 않으면 NULL로 들어옴
        	if( StringUtils.isEmpty(popForm.getCntsYn()) ) {
        		popForm.setCntsYn("N");
        	}

        	//popPupForm.setRgtrId(popPupForm.getRegUsrId());
        	popForm.setTmpSaveYn("N");
    		popo01Service.savePup(popForm, uploader);
    		return createResponseDTO("success.common.runResult", "팝업목록", "저장");

		}catch (EgovBizException e) {
    		return createResponseDTO(e);
		}

    }



    /**
     * 팝업 데이터 임시 저장
     * @param popPupForm
     * @param br
     * @return
     * @throws Exception
     */
    @RequestMapping("/ajaxS/POPO0102/tmpSavePub.do")
    @Validation(target = PopForm.class)
    public @ResponseBody Object tmpSavePup(@InjectSessionInfo PopForm popForm, FileParamVO uploader, BindingResult br) {

    	try {
    		ResponseDTO resDto = createResponseDTO("success.common.runResult", "팝업목록", "임시저장");

        	//필수 공지 사항 체크박스 처리...체크되지 않으면 NULL로 들어옴
        	if( StringUtils.isEmpty(popForm.getMndtAnucYn()) ) {
        		popForm.setMndtAnucYn("N");
        	}

        	//텍스트 포함 여부 체크박스 처리...체크되지 않으면 NULL로 들어옴
        	if( StringUtils.isEmpty(popForm.getCntsYn()) ) {
        		popForm.setCntsYn("N");
        	}

        	popForm.setTmpSaveYn("Y");

        	//save 처리
        	popo01Service.savePup(popForm, uploader);

        	resDto.put("pupNo", popForm.getPupNo());
    		return resDto;

		}catch (EgovBizException e) {
    		return createResponseDTO(e);
		}
    }


}


