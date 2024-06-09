@Controller
@RequestMapping("")
public class PicViewController extends BaseAbstractController{ 

    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name="pictureView") //이미지 공통 뷰 (context-mvc 지정)
    private  View pictureView;

    @Autowired
	private GoogleOTPService googleOTPService;

    /** 프로퍼티 정보 관리*/
	@Resource(name="sysPropService")
	protected EgovPropertyService sysPropService;


	@RequestMapping("/ajaxR/PICVIEW/getProfileImg.do")
	public ModelAndView getProfileImg(@InjectSessionInfo SearchCmd searchCmd) {
		ModelAndView mv = new ModelAndView();
		mv.setView(pictureView);      //@Resource(name="pictureView") --> 상단에 선언

		byte[] bytes = null;

		try {
			logger.debug("Req Path : " + searchCmd.getSelectedId() );
			//logger.debug(searchCmd.toString());
			//full 경로로 넘어오므로 보안처리 여기서...
			String uploadPre = sysPropService.getString("system.fileUpload.uploadPrefix");
			logger.debug("uploadPre : " + uploadPre );

			if( searchCmd.getSelectedId().startsWith(uploadPre) ) { //지정된 업로드 경로에 있을 때만 읽는다.
				bytes = FileUtils.readFileToByteArray( new File( searchCmd.getSelectedId() ));
			}
		}catch (IOException e) {
			logger.error("파일 I/O 오류");
			//throw new EgovBizException("이미지 경로 접근 오류 입니다.");
		}

		//약속된 변수명
		mv.addObject("defaultImgPath", "/resources/pierp/images/no_photo.gif");	//기본파일을 비지니스 로직에서 변경 할 수 있게 함
	    mv.addObject("bytes", bytes);

	    return mv;
	}


    @RequestMapping("/ajaxR/PICVIEW/getQrCode.do")
    public ModelAndView getQrCode(@InjectSessionInfo SearchCmd searchCmd) {
        logger.debug(searchCmd.getSelectedId());
        
        ModelAndView mv = new ModelAndView();
        mv.setView(pictureView);      //@Resource(name="pictureView") --> 상단에 선언

        byte[] bytes = null;
        
        String qrUrl = "";
        
        try {
            qrUrl = URLDecoder.decode(searchCmd.getSelectedId(), "UTF-8");
            qrUrl = qrUrl.replace("&amp;", "&"); //URL Encode, Decode 로 변경 되지 않는 문자가 있음....
            logger.debug("qrUrl : " + qrUrl);
            
            bytes = googleOTPService.getQRBarcode(qrUrl);
            
            
            /*** 저장 테스트 
            Path path = Paths.get("d:/qr_fin.jpg");
            try {
                Files.write(path, bytes);
            } catch (IOException e) {
                System.out.println("IOException!!");
            }		
            ***/			
            
        } catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
            logger.error("UnsupportedEncodingException" + searchCmd.toString());
        } catch (EgovBizException e) {
            //e.printStackTrace();
            logger.error("EgovBizException" + searchCmd.toString() );
        }
        
        //약속된 변수명
        mv.addObject("defaultImgPath", "/resources/pierp/images/no_photo.gif");	//기본파일을 비지니스 로직에서 변경 할 수 있게 함
        mv.addObject("bytes", bytes);

        return mv;
        
    }

}