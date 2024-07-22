package pierp.app.mis.bizAA.aaa.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.mis.bizAA.aaa.service.AAA01Service;
import pierp.app.mis.bizMH.mhk.model.MHK6001FormVO;
import pierp.app.mis.common.table.model.TbMhEvyeEpdtAncVO;
import pierp.app.mis.common.table.model.TbMhEvyeEpdtPlnVO;
import pierp.app.mis.common.table.model.TbMhOdbyUspDtlKey;
import pierp.app.mis.common.table.model.TbMhOdbyUspDtlVO;
import pierp.app.mis.common.table.model.TbMhOdbyUspVO;
import pierp.common.mvc.bind.InjectSessionInfo;
import pierp.common.mvc.validation.Validation;
import pierp.common.mvc.validation.Validations;
import pierp.common.mvc.web.BaseAbstractController;
import pierp.common.mvc.web.ParamVO;
import pierp.common.mvc.web.ResponseDTO;
import pierp.common.util.DateUtils;
import pierp.common.util.QueryResultMap;

@Controller
public class AAA0101Controller extends BaseAbstractController{
    
	@Autowired
    private AAA01Service aaa01Service;
	
	 /**
     * loadAAA0101
     * @param commandMap
     * @return
     */
    @RequestMapping({"/main/AAA0101.do","/popup/AAA0101.do"})
    public ModelAndView loadAAA0101(@RequestParam Map<String, Object> commandMap) {
    	
    	ModelAndView mv = new ModelAndView("mis/bizMH/mha/AAA0101"); 
    	
    	mv.addObject( "yy",	DateUtils.getCurrentDate("yyyy") );
      	mv.addObject( "stndDt",	DateUtils.getCurrentDate("yyyyMMdd") );
    	mv.addObject( "regtClseDt", DateUtils.addDay(DateUtils.getCurrentDate("yyyyMMdd"), 10) );
    	
    	mv.addObject("dtFrom", DateUtils.getCurrentDate("yyyy") + "0101");
		mv.addObject("dtTo", DateUtils.getToday());

		mv.addObject("userInfo", super.getUserInfo());
		mv.addObject("usr", super.getUserInfo());
		mv.addObject("vo",   commandMap);
        return mv;
    }
    
    
    /**
     * selectOneBelowEvyeList 정보 조회
     * @param vo
     * @return
     */
    @RequestMapping("/ajaxR/MHK6101/selectOneBelowEvyeList.do")
    public @ResponseBody Object selectOneBelowEvyeList(MHK6001FormVO vo) {
    	ResponseDTO resDto = createResponseDTO();
    	aaa01Service.selectaa(vo);
//    	resDto.putAsGrid("list", list);
    	
    	return resDto;
    }
    
    
    
    /**
     * selectEvyeEpdtPlnList 목록 조회1
     * @param srchBox
     * @return
     */
    @RequestMapping("/ajaxR/MHK6101/selectEvyeEpdtPlnList.do")
    public @ResponseBody Object selectEvyeEpdtPlnList(ParamVO srchBox) {
    	ResponseDTO resDto = createResponseDTO();
    	List<QueryResultMap> list = aaa01Service.selectEvyeEpdtPlnList(srchBox);
    	resDto.putAsGrid("list", list);
    	return resDto;
    }
    
    
    
    
    /**
     * selectEvyeEpdtAncList 목록 조회2
     * @param mstBox
     * @return 연차 촉진 대상자 목록
     */
    @RequestMapping("/ajaxR/MHK6101/selectEvyeEpdtAncList.do")
    public @ResponseBody Object selectEvyeEpdtAncList(ParamVO mstBox) {
    	ResponseDTO resDto = createResponseDTO();
    	List<QueryResultMap> list = aaa01Service.selectEvyeEpdtAncList(mstBox);
    	resDto.putAsGrid("list2", list);
    	return resDto;
    }
    
    
    
    
    
    /**
     * selectEvyeEpdtPln 목록 저장 
     * @param list
     * @return
     * @throws EgovBizException
     */
    @RequestMapping("/ajaxS/MHK6101/selectEvyeEpdtPln.do")
    @Validations(validations={@Validation(generic=List.class, target=TbMhEvyeEpdtPlnVO.class, form="#list", argIdx=0)})
    public @ResponseBody Object selectEvyeEpdtPln(@InjectSessionInfo List<TbMhEvyeEpdtPlnVO> list, BindingResult br) throws EgovBizException{
    	try {
    		aaa01Service.selectEvyeEpdtPln(list);
    		return createResponseDTO("success.common.runResult", "연차촉진계획", "저장");
		} catch (EgovBizException e) {
			return createResponseDTO(e);
		}
    }

    

    
    /**
     * SaveEvyeEpdtAnc list, parmaVO 저장
     * @param list2
     * @param mstBox
     * @param br
     * @return
     * @throws EgovBizException
     */
    @RequestMapping("/ajaxS/MHK6101/SaveEvyeEpdtAnc.do")
    @Validations(validations={@Validation(generic=List.class, target=TbMhEvyeEpdtAncVO.class, form="#list2", argIdx=0)})
    public @ResponseBody Object SaveEvyeEpdtAnc(@InjectSessionInfo List<TbMhEvyeEpdtAncVO> list2, ParamVO mstBox, BindingResult br) throws EgovBizException{
    	try {
    		aaa01Service.SaveEvyeEpdtAnc(list2, mstBox);
    		return createResponseDTO("success.common.runResult", "연차촉진통보대상자", "저장");
		} catch (EgovBizException e) {
			return createResponseDTO(e);
		}
    }
    
    
    
    
    
	/**
	 * deleteOdbyUspInfo 삭제 
	 *  
	 */
	@Validation(target = TbMhOdbyUspVO.class)
	@RequestMapping("/ajaxD/MHK6102/deleteOdbyUspInfo.do")
	public @ResponseBody
	Object deleteOdbyUspInfo(@InjectSessionInfo TbMhOdbyUspVO mstBox, BindingResult br) throws EgovBizException {
		ResponseDTO resDto = createResponseDTO();
		
		try {
			String key = aaa01Service.deleteOdbyUspInfo(mstBox);
			resDto = createResponseDTO("success.common.runResult", "연차사용계획등록", "삭제");
			resDto.put("key", key);
			return resDto;
		} catch (EgovBizException e) {
			return createResponseDTO(e);
		}

	}
    
    

    
    
/*************************************************************************************************************************/
    
    

	/**
	 * selectEvyeSituList 목록 조회
	 *
	 * @param vo
	 * @return
	 */
	@Validation(target = ParamVO.class)
	@RequestMapping("/ajaxR/MHK6102/selectEvyeSituList.do")
	public @ResponseBody Object selectEvyeSituList(ParamVO vo, BindingResult br) {

		List<QueryResultMap> list = aaa01Service.selectEvyeSituList(vo);
		ResponseDTO resDto = createResponseDTO();
		resDto.putAsGrid("list", list);
		
		return resDto;
	}

	
	
	
	/**
	 * getOdbyUspInfo 정보 조회
	 *
	 * @param vo
	 * @return
	 */
	@RequestMapping("/ajaxR/MHK6102/getOdbyUspInfo.do")
	public @ResponseBody Object getOdbyUspInfo(TbMhOdbyUspVO vo) {

		QueryResultMap info = aaa01Service.getOdbyUspInfo(vo);
		ResponseDTO resDto = createResponseDTO();
		resDto.put("info", info);
		return resDto;
	}
	
	
	

	/**
	 * selectOdbyUspList 목록 조회
	 *
	 * @param vo
	 * @return
	 */
	@RequestMapping("/ajaxR/MHK6102/selectOdbyUspList.do")
	public @ResponseBody
	Object selectOdbyUspList(TbMhOdbyUspVO vo) {

		List<QueryResultMap> list = aaa01Service.selectOdbyUspList(vo);
		ResponseDTO resDto = createResponseDTO();
		resDto.putAsGrid("dtlList", list);
		return resDto;
	}

	
	
	/**
	 * saveOdbyUspInfo 저장 
	 * 
	 */
	@Validation(target = TbMhOdbyUspVO.class)
	@RequestMapping("/ajaxS/MHK6102/saveOdbyUspInfo.do")
	public @ResponseBody
	Object saveOdbyUspInfo(@InjectSessionInfo TbMhOdbyUspVO mstBox, BindingResult br) throws EgovBizException {
		ResponseDTO resDto = createResponseDTO();
		try {
			String odbyUsePlnNo = aaa01Service.saveOdbyUspInfo(mstBox);
			resDto.put("odbyUsePlnNo", odbyUsePlnNo);
			resDto = createResponseDTO("success.common.runResult", "연차사용계획등록", "저장");
			return resDto;
		} catch (EgovBizException e) {
			return createResponseDTO(e);
		}
	}
	
	
	
	



	
	
	
	/**
	 * calcOdbyUspDys 계획일수 조회 
	 * 
	 *
	 */
	@RequestMapping("/ajaxR/MHK6102/calcOdbyUspDys.do")
	public @ResponseBody
	Object calcOdbyUspDys(ParamVO vo, BindingResult br) throws EgovBizException {

		try {
			Double dys = aaa01Service.calcOdbyUspDys(vo);
			
			ResponseDTO resDto = createResponseDTO();
			resDto.put("dys", dys);
			return resDto;
		} catch (EgovBizException bse) {
			return createResponseDTO(bse);
		}
	}

	
	
	
	
	
	/**
	 * saveOdbyUspDtlList
	 * 세부계획정보 저장
	 *
	 */
	@Validations(validations={@Validation(generic=List.class, target=TbMhOdbyUspDtlVO.class, form="#dtlList", argIdx=0)})
	@RequestMapping("/ajaxS/MKH6102/saveOdbyUspDtlList.do")
	public @ResponseBody
	Object saveOdbyUspDtlList(@InjectSessionInfo List<TbMhOdbyUspDtlVO> dtlList, BindingResult br) throws EgovBizException {

		try {

			String odbyUsePlnNo = aaa01Service.saveOdbyUspDtlList(dtlList);
			ResponseDTO resDto = null;
			resDto = createResponseDTO("success.common.runResult", "세부계획정보", "저장");
			resDto.put("odbyUsePlnNo", odbyUsePlnNo);
			return resDto;
		} catch (EgovBizException bse) {
			return createResponseDTO(bse);
		}
	}

	/**
	 * deleteOdbyUspDtlList
	 * 세부계획정보 삭제
	 *
	 */
	@Validations(validations={@Validation(generic=List.class, target=TbMhOdbyUspDtlKey.class, form="#dtlList", argIdx=0)})
	@RequestMapping("/ajaxD/MHK6102/deleteOdbyUspDtlList.do")
	public @ResponseBody
	Object deleteOdbyUspDtlList(@InjectSessionInfo List<TbMhOdbyUspDtlKey> dtlList, BindingResult br) throws EgovBizException {

		try {
//			setUsrId(dtlList);
			String odbyUsePlnNo = aaa01Service.deleteOdbyUspDtlList(dtlList);
			ResponseDTO resDto = createResponseDTO("success.common.runResult", "세부계획정보", "삭제");
			resDto.put("odbyUsePlnNo", odbyUsePlnNo);
			
//			AjaxDTO dto = createAjaxDTO("success.common.runResult", "세부계획정보", "삭제");
//			dto.put("odbyUsePlnNo", odbyUsePlnNo);
			return resDto;
		} catch (EgovBizException bse) {
			return createResponseDTO(bse);
		} 
	}
	
	/**
	 * approvalReqOdbyUsp
	 * 연차사용계획등록 기본정보 승인요청
	 */
	@Validation(target = TbMhOdbyUspVO.class)
	@RequestMapping("/ajaxP/MHK6102/approvalReqOdbyUsp.do")
	public @ResponseBody
	Object approvalReqOdbyUsp(@InjectSessionInfo TbMhOdbyUspVO mstBox, BindingResult br) throws EgovBizException {
		try {
			String odbyUsePlnNo = aaa01Service.approvalReqOdbyUsp(mstBox);
			ResponseDTO resDto = createResponseDTO("success.common.runResult", "연차사용계획등록", "승인요청");
			resDto.put("odbyUsePlnNo", odbyUsePlnNo);
			return resDto;
		} catch (EgovBizException bse) {
			return createResponseDTO(bse);
		}
	}
    
    
    
    
    
    
    
    
    


	    
}

