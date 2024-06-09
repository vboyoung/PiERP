package pierp.app.stm.bizSY.sye.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.stm.bizSY.sye.service.SYE02Service;
import pierp.common.mvc.bind.InjectSessionInfo;
import pierp.common.mvc.validation.Validation;
import pierp.common.mvc.web.BaseAbstractController;
import pierp.common.mvc.web.ParamVO;
import pierp.common.mvc.web.ResponseDTO;
import pierp.common.util.QueryResultMap;

/**
 * SYE0201 접근제어 > 관리자 접근제어 controller.
 * @Class SYE0202Controller
 * @Description 
 * 
 * @author 이정민
 * @since 2022. 5. 9
 * @version 
 *
 */
@Controller
public class SYE0201Controller extends BaseAbstractController {
	
	@Autowired
	private SYE02Service sye02Service;
	/**
	 * SYE0201 화면 load
	 * @return
	 */
	@RequestMapping({"/main/SYE0201.do","/popup/SYE0201.do"})
	public ModelAndView loadSYE0201() {
		

		String aprhCtrlYn = sye02Service.getMngrAprhCtrlYn();
		
		ModelAndView mv = new ModelAndView("stm/bizSY/sye/SYE0201");
		mv.addObject("aprhCtrlYn", aprhCtrlYn);

		return mv;
	}
	
	
	/**
	 * 관리자 접근제어 목록 조회
	 * @param vo
	 * @param br
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping("/ajaxR/SYE0201/selectMngrAprhCtrlList.do")
	public @ResponseBody Object selectMngrAprhCtrlList(ParamVO vo, BindingResult br) {
		
    	ResponseDTO dto = createResponseDTO();
    	
		List<QueryResultMap> list = sye02Service.selectAprhCtrlList(vo);
		
		dto.putAsGrid("mngrAprhCtrlList", list);
    	
    	return dto;
	}
	
	
	/**
	 * 관리자 접근제어 사용여부 조회
	 * @param vo
	 * @param br
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping("/ajaxR/SYE0201/getMngrAprhCtrlYn.do")
	public @ResponseBody Object getMngrAprhCtrlYn(ParamVO vo, BindingResult br) {
		
		ResponseDTO dto = createResponseDTO();
		
		String aprhCtrlYn = sye02Service.getMngrAprhCtrlYn();
		
		dto.put("aprhCtrlYn", aprhCtrlYn);
		
		return dto;
	}
	
	/**
	 * saveUsrAprhCtrl 접근제어 정보 저장
	 * @param vo
	 * @param br
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping("/ajaxS/SYE0201/saveMngrAprhCtrl.do")
	@Validation(target=ParamVO.class, isThrow=true)
	public @ResponseBody Object saveMngrAprhCtrl(@InjectSessionInfo ParamVO vo, BindingResult br ){
	
		try{
			sye02Service.saveAprhCtrl(vo);
			ResponseDTO dto = createResponseDTO("success.common.runResult", "관리자접근제어 정보", "저장");
			dto.put("usrId", vo.getString("usrId"));
			dto.put("aprhCtrlId", vo.getString("aprhCtrlId"));
			return dto;
		} catch (DuplicateKeyException eDup) {
			return createResponseDTO("사용자ID가 중복되어 사용할 수 없습니다.");
		} catch (EgovBizException eBiz) {
			return createResponseDTO(eBiz);
		} catch (Exception e) {
			return createResponseDTO(e.toString());
		}
	}
	
	/**
	 * deleteMngrAprhCtrl 접근제어 정보 삭제
	 * @param vo
	 * @param br
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping("/ajaxD/SYE0201/deleteMngrAprhCtrl.do")
	@Validation(target=ParamVO.class, isThrow=true)
	public @ResponseBody Object deleteMngrAprhCtrl(@InjectSessionInfo ParamVO vo, BindingResult br ){
		
		try{
			sye02Service.deleteAprhCtrl(vo);
			ResponseDTO dto = createResponseDTO("success.common.runResult", "관리자접근제어 정보", "삭제");
			dto.put("usrId", vo.getString("usrId"));
			dto.put("aprhCtrlId", vo.getString("aprhCtrlId"));
			return dto;
		} catch (DuplicateKeyException eDup) {
			return createResponseDTO("사용자ID가 중복되어 사용할 수 없습니다.");
		} catch (EgovBizException eBiz) {
			return createResponseDTO(eBiz);
		} catch (Exception e) {
			return createResponseDTO(e.toString());
		}
	}
	
	/**
	 * changeMngrAprhCtrl 관리자접근제어 사용여부 정보 저장
	 * @param vo
	 * @param br
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping("/ajaxS/SYE0201/changeMngrAprhCtrl.do")
	public @ResponseBody Object changeMngrAprhCtrl(
			@RequestParam(value="mngrIpAprhCtrlUseYn", required=true) String mngrIpAprhCtrlUseYn
			){
		try{
			sye02Service.changeMngrAprhCtrl(mngrIpAprhCtrlUseYn);
			ResponseDTO dto = createResponseDTO("success.common.runResult", "관리자접근제어 정보", "");
			dto.put("mngrIpAprhCtrlUseYn", mngrIpAprhCtrlUseYn);
			return dto;
		} catch (DuplicateKeyException eDup) {
			return createResponseDTO("사용자ID가 중복되어 사용할 수 없습니다.");
		} catch (EgovBizException eBiz) {
			return createResponseDTO(eBiz);
		} catch (Exception e) {
			return createResponseDTO(e.toString());
		}
	}
	
}
