package pierp.app.mgt.bizPOP.popo.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.cmd.SearchCmd;
import pierp.app.common.model.table.SyAthflVO;
import pierp.app.common.model.table.TbBpPupVO;
import pierp.app.mgt.bizPOP.popo.model.PopVO;
import pierp.app.mgt.bizPOP.popo.service.POPO01Service;
import pierp.common.mvc.bind.InjectSessionInfo;
import pierp.common.mvc.web.BaseAbstractController;
import pierp.common.mvc.web.ResponseDTO;

/**
*
* 클래스
* file name : PUBO0103Controller.java
* 팝업 목록 상세화면 > 미리보기
* @author 이정민
* @since 2022.05.24.
* @version 1.0
*
* <pre>
* == 개정이력(Modification Information) ==
*
* 수정일           수정자     수정내용
* -------------- -------- ---------------------------
* 2022.05.24.    이정민     최초 생성
* </pre>
*/



@Controller
public class POPO0103Controller extends BaseAbstractController {


    @Resource(name="pictureView") //다운로드 엑셀 뷰 테스트 (context-mvc 지정)
    private  View pictureView;

    @Autowired
    private POPO01Service popo01Service;

    /**
     * 팝업 미리보기 화면 오픈
     * @param commandMap
     * @return
     */
    @RequestMapping("/popds/POP/POPO0103.do")
    public ModelAndView POPO0103(@InjectSessionInfo SearchCmd searchCmd){
    	ModelAndView mv = new ModelAndView("mgt/bizPOP/popo/POPO0103");
    	mv.addObject("cmd", searchCmd);
    	return mv;
    }


    /**
     * 팝업 미리보기 조회
     */
	@RequestMapping("/ajaxR/POPO0103/selectPreview.do")
	public @ResponseBody Object selectPreview(@InjectSessionInfo SearchCmd searchCmd) {
		ResponseDTO resDto = createResponseDTO();
		try {
			TbBpPupVO pupVO = popo01Service.selectPupOne(searchCmd);

			String cnts = pupVO.getCnts().replaceAll("\\n", "<br>");
			pupVO.setCnts(cnts);

			String imgInclud = "N";
			PopVO fileChkVO = popo01Service.selectPupOneAhfl(searchCmd);

			if(fileChkVO.getTbSyAthflVO().size() != 0) {
				imgInclud = "Y";
			};

			resDto.put("pupVO", pupVO);
			resDto.put("imgInclud", imgInclud);

		} catch (EgovBizException eBiz) {
			resDto = createResponseDTO(eBiz);
		}
		return resDto;
	}


	/**
	 * 이미지 조회
	 * @param searchCmd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ajaxR/POPO0103/getPopImg.do")
	public ModelAndView getPopImg(@InjectSessionInfo SearchCmd searchCmd) {
		ModelAndView mv = new ModelAndView();
		mv.setView(pictureView);      //@Resource(name="pictureView") --> 상단에 선언
		byte[] bytes = null;
		try {

			PopVO popVO = popo01Service.selectPupOneAhfl(searchCmd);
			List<SyAthflVO> list = popVO.getTbSyAthflVO();
			SyAthflVO syAthflVO = new SyAthflVO();
			if(list.size() != 0) {
				syAthflVO = list.get(0);
				bytes = FileUtils.readFileToByteArray( new File( syAthflVO.getFilePath() + syAthflVO.getFileName() ));
			}

		}catch (IOException e) {
			logger.error("파일 I/O 오류");
		}catch (EgovBizException eBiz) {
    		logger.error("ERROR:::getPopImg");
		}
		//약속된 변수명
		//mv.addObject("defaultImgPath", "/resources/pierp/images/no_img.jpg");	//기본파일을 비지니스 로직에서 변경 할 수 있게 함
	    mv.addObject("bytes", bytes);
	    return mv;
	}
}


