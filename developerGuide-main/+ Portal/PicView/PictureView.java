


public class PictureView extends AbstractView {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String DefaultContextType = "image/gif";
	private static final String DefaultImagePath = "/resources/pierp/images/no_img.jpg";


    @Autowired
    protected ServletContext servletContext;

    public PictureView() {
    }

    
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ServletOutputStream os = null;

		byte[] bytes = (byte[]) model.get("bytes"); //약속된 이름
    	String reDefaultImagePath = (String) model.get("defaultImgPath"); //약속된 이름
    	if( bytes == null || bytes.length <=0 ) {
    		logger.error("파일 메타정보 없음");
    		bytes = getDefaultNoImage(reDefaultImagePath);
    	}

    	//Try-with-resources
    	try(InputStream is  = new ByteArrayInputStream(bytes);) {

        	String contentType = URLConnection.guessContentTypeFromStream(is);
        	logger.debug("contentType :" + contentType);

        	if(contentType == null) {
        		contentType = DefaultContextType;
        	}

		   	response.setContentType(contentType);
		    response.setContentLength((int)bytes.length);

            os = response.getOutputStream();
            FileCopyUtils.copy(is, os);
            os.flush();

        } catch (Exception e) {
            throw e;
        } finally {
            //if (is != null) is.close();
            if (os != null) os.close();
		}
    }


    /* 사진없는 경우 기본이미지 byte[] 형태로 리턴*/
	private byte[] getDefaultNoImage(String reDefaultImagePath) throws IOException{
		String realPath;

		if(StringUtils.isEmpty(reDefaultImagePath)) {
			realPath = servletContext.getRealPath( DefaultImagePath );
		}else {
			realPath = servletContext.getRealPath( reDefaultImagePath );
		}

		byte[] rtnByte = FileUtils.readFileToByteArray( new File( realPath ));
		return rtnByte;
	}


}