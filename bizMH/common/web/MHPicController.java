package pierp.app.mis.bizMH.common.web;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.mis.bizMH.common.service.MHPicService;
import pierp.app.mis.common.table.model.TbMhBzplVO;
import pierp.app.mis.common.table.model.TbMhCertiSignVO;
import pierp.app.mis.common.table.model.TbMhPicVO;
import pierp.common.mvc.bind.InjectSessionInfo;
import pierp.common.mvc.web.BaseAbstractController;
import pierp.common.mvc.web.ParamVO;
import pierp.common.mvc.web.ResponseDTO;
import pierp.common.web.view.AjaxDtoView;


@Controller
public class MHPicController extends BaseAbstractController {

	@Autowired
    protected ServletContext servletContext;

	@Resource(name="multipartResolver")
	private MultipartResolver multipartResolver;

	@Autowired
	MHPicService service;
	
	/**
     * 직원사진관리 화면 오픈
     * @param commandMap
     * @return
     */
	@RequestMapping("/popup/MHCPic0101.do")
	public ModelAndView popupHRMA0103(@RequestParam Map<String, Object> commandMap) {

		ModelAndView mv = new ModelAndView("mis/bizMH/mhc/MHCPic0101");

		mv.addObject("commandMap", commandMap);

        return mv;
	}


	@RequestMapping("/ajaxR/MHCommon/selectEmpPicByEmpUniqNo.do")
	public void selectEmpPicByEmpNo( @InjectSessionInfo ParamVO vo, BindingResult br, HttpServletResponse response ) throws Throwable {

		
		TbMhPicVO picVO = service.selectEmpPicByEmpNo( vo );
		byte[] bytes = null;
		
		if(picVO == null) {
			bytes = null;
		}else {
			bytes = picVO.getPicImg();
		}

        if( bytes == null )
       		bytes = this.getDefaultNoImage();
    	
		
		responsePicView( bytes, response );
    }
	
	@RequestMapping("/ajaxR/MHCommon/selectEmpPicChk.do")
	public @ResponseBody Object selectEmpPicChk(ParamVO params, BindingResult br) {

		ResponseDTO resDto = createResponseDTO();

		TbMhPicVO picVO = service.selectEmpPicByEmpNo( params );

		if(picVO == null) {
			resDto.put("picYn", "N");
		} else {
			resDto.put("picYn", "Y");
		}

		return resDto;
    }
	
	/**
	 * selectBzplImgByBzplCd 사업장 직인사진 조회
	 * @param param
	 * @param br
	 * @param response
	 * @throws Throwable 
	 */
	@RequestMapping("/ajaxR/MHCommon/selectBzplImgByBzplCd.do")
	public void selectBzplImgByBzplCd(ParamVO param, BindingResult br, HttpServletResponse response) throws Throwable {
		
		TbMhBzplVO imgVo = service.selectCoOfslImg( param );
		
		byte[] bytes = null;
		
		if( imgVo == null ) {
			bytes = null;
		} else {
			bytes = imgVo.getCoOfslImg();
		}
		
		if( bytes == null )
			bytes = this.getDefaultNoImage();
		
		responsePicView( bytes, response );
	}
	
	/**
	 * selectBizCertiSignAplNo 제증명서 서명사진 조회
	 * @param param
	 * @param br
	 * @param response
	 * @throws Throwable 
	 */
	@RequestMapping("/ajaxR/MHCommon/selectBizCertiSignAplNo.do")
	public void selectBizCertiSignAplNo(ParamVO param, BindingResult br, HttpServletResponse response) throws Throwable {
		
		TbMhCertiSignVO imgVo = service.selectBizCertiSignAplNo( param );
		
		byte[] bytes = null;
		
		if( imgVo == null ) {
			bytes = null;
		} else {
			bytes = imgVo.getSignImg();
		}
		
		if( bytes == null )
			bytes = this.getDefaultNoImage();
		
		responsePicView( bytes, response );
	}
	
	/**
	 * saveBizPicByBizplCd 사업장 직인사진 저장( 공통 첨부테이블 정보 읽어서 저장함 )
	 * @param param
	 * @param br
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ajaxS/MHCommon/saveBizPicByBizplCd.do")
	public @ResponseBody Object saveBizPicByBizplCd(@InjectSessionInfo ParamVO param, BindingResult br) throws Exception {
		
		ResponseDTO resDto = null;
		
		try {
			
			service.saveBizPicByBizplCd( param );
			
			resDto = createResponseDTO();
			
		} catch(EgovBizException e) {
			resDto = createResponseDTO(e);
		}
		
		return resDto;
	}
	
	/**
	 * saveEmpPicByCertiSignEmpUniqNo 증명서 서명사진 저장( 공통 첨부테이블 정보 읽어서 저장함 )
	 * @param param
	 * @param br
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ajaxS/MHCommon/saveEmpPicByCertiSignEmpUniqNo.do")
	public @ResponseBody Object saveEmpPicByCertiSignEmpUniqNo(@InjectSessionInfo ParamVO param, BindingResult br) throws Exception {
		
		ResponseDTO resDto = null;
		
		try {
			
			service.saveEmpPicByCertiSignEmpUniqNo( param );
			
			resDto = createResponseDTO();
			
		} catch(EgovBizException e) {
			resDto = createResponseDTO(e);
		}
		
		return resDto;
	}
	
	@RequestMapping("/ajaxR/MHCommon/downloadPicByEmpUniqNo.do")
	public void downloadPicByEmpUniqNo( ParamVO params, HttpServletResponse response ) throws Throwable {

		ResponseDTO dto = null;
		
		TbMhPicVO vo  = service.selectEmpPicByEmpNo( params );

		byte[] img = vo.getPicImg();
		if( img == null ){
			dto = super.createResponseDTO("valid.empty", (Object[]) new String[]{"해당 파일이 존재하지 않습니다.\\\\n관리자에게 확인바랍니다아."});
		}
		
		String fileNm = vo.getEmpUniqNo() + ".jpg";
		
		responsePicDownload( fileNm, img, response );
	}
	
	/**
	 * downloadPicByBzplCd 직인사진 이미지 다운로드
	 * @param param
	 * @param response
	 * @throws Throwable
	 */
	@RequestMapping("/ajaxR/MHCommon/downloadPicByBzplCd.do")
	public @ResponseBody Object downloadPicByBzplCd( ParamVO param, HttpServletResponse response ) throws Throwable {
		
		TbMhBzplVO vo  = service.selectCoOfslImg( param );
		
		byte[] img = vo.getCoOfslImg();
		if( img == null ){
			// 메세지처리 안됨. 확인해봐야함
			return createResponseDTO("valid.empty", (Object[]) new String[]{"해당 파일이 존재하지 않습니다.\n관리자에게 확인바랍니다."});
		}
		
		String fileNm = vo.getBzplNm() + "_직인.jpg";
		
		responsePicDownload( fileNm, img, response );
		
		return createResponseDTO();
	}
	
	/**
	 * downloadPicByAplNo 서명사진 이미지 다운로드
	 * @param param
	 * @param response
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping("/ajaxR/MHCommon/downloadPicByAplNo.do")
	public @ResponseBody Object downloadPicByAplNo( ParamVO param, HttpServletResponse response ) throws Throwable {
		
		TbMhCertiSignVO vo  = service.selectBizCertiSignAplNo( param );
		
		byte[] img = vo.getSignImg();
		if( img == null ){
			// 메세지처리 안됨. 확인해봐야함
			return createResponseDTO("valid.empty", (Object[]) new String[]{"해당 파일이 존재하지 않습니다.\n관리자에게 확인바랍니다."});
		}
		
		String fileNm = "사인" + ".jpg";
		
		responsePicDownload( fileNm, img, response );
		
		return createResponseDTO();
	}

	@RequestMapping("/ajaxS/MHCommon/processUpload.do")
	public ModelAndView processUpload_single( @InjectSessionInfo ParamVO vo,
			HttpServletRequest request, HttpServletResponse response) {

		ResponseDTO dto = null;
		ModelAndView mv = new ModelAndView(AjaxDtoView.VIEW_NAME);
		
		String fileId = "";
		try {
			
			if(multipartResolver.isMultipart(request)) {
				final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Collection<MultipartFile> multipartFiles = multiRequest.getFileMap().values();
				
				if( multipartFiles.size() != 1) {
					throw new IllegalArgumentException("지정된 request data 형식이 아님. (1개의 파일만 업로드 가능합니다.)");
				}
				
				service.uploadPicImage(vo, multipartFiles.toArray(new MultipartFile[] {})[0]);
//				fileId = fileUploadService.tempUpload(multipartFiles, userInfo);
			}
			dto = super.createResponseDTO();
			dto.put("fileId", fileId);
		} catch (EgovBizException ex) {
			if(super.logger.isDebugEnabled()){
				super.logger.debug("첨부문서저장 오류", ex);
			}
			dto = super.createResponseDTO("fail.common.run.msg", (Object[]) new String[]{"첨부문서저장"});
		}
		
		mv.addObject(AjaxDtoView.MODEL_NAME, dto);
		
		return mv;
	}
	
	
	
	
	/**
	 * responsePicDownload 파일다운로드
	 * @param fileNm
	 * @param img
	 * @param response
	 * @throws Exception
	 */
	private void responsePicDownload( String fileNm, byte[] img, HttpServletResponse response ) throws Exception{

		try {
			String fileName = java.net.URLEncoder.encode(fileNm,"UTF-8");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename="+fileName+";");
			response.setHeader("Content-Transfer-Encoding", "binary;");
			response.setHeader("Content-Length", img.length +";");

			FileCopyUtils.copy( img, response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			
		}catch(Exception ex){
			response.setHeader( "Content-Type", "text/html; charset=utf-8" );
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println( "<HTML>" );
			out.println( "<HEAD>" );
			out.println( "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">" );
			out.println( "<SCRIPT>" );
			out.println( "	try{" );
			out.println( "	  parent.cfn_SubmitInternal.callback( '"+ex.getMessage()+"' );" );
			out.println( "	}catch(ex){" );
			out.println( "	  alert('"+ex.getMessage()+"');" );
			out.println( "	}" );
			out.println( "</SCRIPT>" );
			out.println( "</HEAD>" );
			out.println( "<BODY>" );
			out.println( "</BODY>" );
			out.println( "</HTML>" );
			out.flush();
			out.close();
		}
	}

	private byte[] getDefaultNoImage() throws IOException{
		String defImgPath = "/resources/pierp/images/no_photo.gif";
		String realPath = servletContext.getRealPath( defImgPath );

		byte[] rtnByte = FileUtils.readFileToByteArray( new File( realPath ));

		return rtnByte;
	}

	public void responsePicView( final byte[] bytes, HttpServletResponse response ) throws Throwable  {

		ServletOutputStream os =  null;
		
		try {

	    	 os = response.getOutputStream();

		   	 response.setContentType("image/jpeg");
		     response.setContentLength((int)bytes.length);

		     FileCopyUtils.copy(bytes, os);

		     os.flush();
		} catch (IOException e) {
			logger.error("responsePicView IOException", e);
		}finally{
			safeClose(os);
		}
    }
	
	private void safeClose(OutputStream out) {
		
		if( out != null ) {

			try {
				out.close();
			} catch (IOException e) {
				logger.error("", e);
			}
		}
	}
	
	
	
    
}
