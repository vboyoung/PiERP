
/* toMenuList 가져오기 */

String sysId = sysPropService.getString("system.sysId");
String subSysId =  pieripAppTypeUtills.getCurrentApp(requset).Id;

List<MenuVo> toMenuList = null;
    toMenuList = appMainService.getTopMenuList(sysId, subSysId); //서브시스템별 TOP 메뉴

try {

}catch(BaseException be) {


}


/* 번외 : 공통  getString(); */
EgovPropertyServiceImpl.class
public class EgovPropertyServiceImpl 
	implements EgovPropertyService, ApplicationContextAware, InitializingBean, DisposableBean, ResourceLoaderAware { 

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovPropertyServiceImpl.class);

	private ExtendedProperties egovProperties = null;
	private ResourceLoader resourceLoader = null;

	private MessageSource messageSource;
	private Set<?> extFileName;
	private Map<?, ?> properties;

	public String getString(String name) {
		return getConfiguration().getString(name);
	}

	public String getString(String name, String def) {
		return getConfiguration().getString(name, def);
	}

    private ExtendedProperties getConfiguration() {
		return egovProperties;
	}

}







