package pierp.app.mis.bizMH.common.web;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pierp.app.mis.bizMH.common.model.MHEmpVO;
import pierp.app.mis.bizMH.common.service.MHCommonService;
import pierp.common.mvc.web.BaseAbstractController;
import pierp.common.mvc.web.ParamVO;
import pierp.common.mvc.web.ResponseDTO;
import pierp.common.util.DateUtils;
import pierp.common.util.QueryResultMap;


@Controller
public class MHCommonController extends BaseAbstractController {

	@Autowired
    protected ServletContext servletContext;

	@Autowired
	MHCommonService service;

	private ResponseDTO createResponseDTO;



    /**
     * calcPeriod 입력된 두 날자의 시간 차이를 계산함( 일자, 시간, 분 )
     * @param vo
     * @param br
     * @return
     */
//    @Validation(target = MHSearchFormVO.class, isThrow = true, isCheck = true)
    @RequestMapping("/ajaxR/MHCommon/calcPeriod.do")
	public @ResponseBody Object calcPeriod( ParamVO vo, BindingResult br ) {

		String begnDt	= vo.getString("dtFrom").substring( 0, 8 );
		String begnTime	= vo.getString("DtFrom").substring( 8 );
		String clseDt	= vo.getString("DtTo").substring( 0, 8 );
		String clseTime	= vo.getString("DtTo").substring( 8 );

		int minute;

		// 일수
		minute	= diffMinute( begnDt + begnTime, clseDt + clseTime );
		int diffDay	= (int)Math.floor( minute / 60 / 24 );

		// 시간
		String tmpDys = DateUtils.addDay(begnDt, diffDay );
		minute	= diffMinute( tmpDys + begnTime, clseDt + clseTime );
		int diffHour = (int)Math.floor( minute / 60 );

		// 분
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmm", Locale.getDefault() );
		Date dt = sdf.parse( tmpDys + begnTime, new ParsePosition(0) );

		// weblogic에서 못찾음...;;;;;;
		// low level 코딩
		// Date begnMinute = org.apache.commons.lang.time.DateUtils.addHours( dt, diffHour );
		Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add( Calendar.HOUR_OF_DAY, diffHour );
        Date begnMinute = c.getTime();
        // 종료

		Date clseMinute = sdf.parse( clseDt + clseTime, new ParsePosition(0) );

		int diffMinute = (int)Math.floor( ( clseMinute.getTime() - begnMinute.getTime() ) / 60000.0 );


    	ResponseDTO resDto = createResponseDTO();

    	resDto.put( "diffDay",		String.valueOf( diffDay ) );
    	resDto.put( "diffHour",	String.valueOf( diffHour ) );
    	resDto.put( "diffMinute",	String.valueOf( diffMinute ) );

    	return resDto;

	}



	/**
	 * diffMinute 시간 계산(분단위)
	 * @param st
	 * @param ed
	 * @return
	 */
	private int diffMinute( String st, String ed ) {

		SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmm", Locale.getDefault() );

		Date dBegn = sdf.parse( st, new ParsePosition(0) );
		Date dClse = sdf.parse( ed, new ParsePosition(0) );

		Long lBegn = dBegn.getTime();
		Long lClse = dClse.getTime();

		Long sec = lClse - lBegn;
		int minute = (int)Math.floor( sec / 60000.0 );

		return minute;
	}



	/**
	 * getAuths (현재 로그인한 세션의)권한 존재여부 확인
	 * @return
	 */
	@RequestMapping("/ajaxR/MHCommon/hasAuth.do")
	public @ResponseBody Object getHasAuth(@RequestParam(value="authId", required=true, defaultValue="") String authId) {

		boolean has = super.hasAuth( authId );

		ResponseDTO resDto = createResponseDTO();
    	resDto.put( "has", has );

    	return resDto;
	}



	/**
	 * getUserInfo 사원정보조회( UserInfo )
	 * @param empUniqNo
	 * @return
	 */
	@RequestMapping("/ajaxR/MHCommon/getEmpInfo.do")
	public @ResponseBody Object getEmpInfo(@RequestParam(value="empUniqNo", required=true, defaultValue="") String empUniqNo) {

		QueryResultMap info = service.selectEmpInfo( empUniqNo );

		ResponseDTO resDto = createResponseDTO();
    	resDto.put( "info", info );

    	return resDto;
	}

	/**
	 * getUserInfo 사원정보조회( UserInfo )
	 * @param empUniqNo
	 * @return
	 */
	@RequestMapping("/ajaxR/MHCommon/getEmpInfo2.do")
	public @ResponseBody Object getEmpInfo2(@RequestParam(value="empNo", required=true, defaultValue="") String empNo) {

		QueryResultMap info = service.selectEmpInfo2( empNo );

		ResponseDTO resDto = createResponseDTO();
		resDto.put( "info", info );

    	return resDto;
	}

	/**
	 * getUserInfo 사원정보조회( UserInfo )
	 * @param empUniqNo
	 * @return
	 */
	@RequestMapping("/ajaxR/MHCommon/getEmpInfo3.do")
	public @ResponseBody Object getEmpInfo3(@RequestParam(value="deptCd", required=true, defaultValue="") String deptCd) {

		QueryResultMap info = service.selectEmpInfo3( deptCd );

    	ResponseDTO resDto = createResponseDTO();
		resDto.put( "info", info );

    	return resDto;
	}

	/**
	 * getEmpVO 사원정보조회( MHEmpVO )
	 * @param empUniqNo
	 * @return
	 */
	@RequestMapping("/ajaxR/MHCommon/getEmpVO.do")
	public @ResponseBody Object getEmpVO(@RequestParam(value="empUniqNo", required=true, defaultValue="") String empUniqNo) {

		MHEmpVO info = service.selectEmpVO( empUniqNo );

		ResponseDTO resDto = createResponseDTO();
		resDto.put( "info", info );

    	return resDto;
	}





	/**
	 * SearchRsn 화면 오픈
	 *
	 * @return
	 */
	@RequestMapping("/popup/SearchRsn.do")
	public ModelAndView loadSearchRsn(@RequestParam Map<String, Object> commandMap) {
		ModelAndView mv = new ModelAndView("mis/bizMH/common/searchRsn");

		mv.addObject("usr", getUserInfo());
		mv.addObject("params", commandMap);

		return mv;
	}




	/**
	 * getEmpVO 사원정보조회( MHEmpVO )
	 * @param empUniqNo
	 * @return
	 */
	@RequestMapping("/ajaxR/MHCommon/getMngEmp.do")
	public @ResponseBody Object selectMngEmp(@RequestParam(value="empUniqNo", required=true, defaultValue="") String empUniqNo) {

		QueryResultMap info = service.selectMngEmp( empUniqNo );

    	ResponseDTO dto = createResponseDTO();
    	dto.put( "info", info );

    	return dto;
	}

	/**
	 * getEmpVO 사원정보조회( MHEmpVO )
	 * @param empUniqNo
	 * @return
	 */
	@RequestMapping("/ajaxR/MHCommon/getMngEmp2.do")
	public @ResponseBody Object selectMngEmp2(@RequestParam(value="empUniqNo", required=true, defaultValue="") String empUniqNo) {

		QueryResultMap info = service.selectMngEmp2( empUniqNo );

		ResponseDTO resDto = createResponseDTO();
		resDto.put( "info", info );

    	return resDto;
	}

	/**
	 * getUserInfo 부서정보조회( EgovMap )
	 * @param empUniqNo
	 * @return
	 */
	@RequestMapping("/ajaxR/MHCommon/getDeptInfo.do")
	public @ResponseBody Object getDeptInfo(@RequestParam(value="deptUniqNo", required=true, defaultValue="") String deptUniqNo) {

		QueryResultMap info = service.selectDeptInfo( deptUniqNo );

		ResponseDTO resDto = createResponseDTO();
		resDto.put("info", info);
//		resDto.putAsGrid("info", info);
//    	AjaxDTO dto = new AjaxDTO();
//    	dto.put( "info", info );

    	return resDto;
	}
//
//
//
//	/**
//	 * getEmpVO 사원정보조회( MHEmpVO )
//	 * @param empUniqNo
//	 * @return
//	 */
//	@RequestMapping("/ajaxR/MHCommon/getDeptVO.do")
//	public @ResponseBody Object selectDeptVO(@RequestParam(value="deptUniqNo", required=true, defaultValue="") String deptUniqNo) {
//
//		MHDeptVO info = service.selectDeptVO( deptUniqNo );
//
//    	AjaxDTO dto = new AjaxDTO();
//    	dto.put( "info", info );
//
//    	return dto;
//	}
//
//
	@RequestMapping("/ajaxR/MHCommon/getWrkPl.do")
    public @ResponseBody Object getWrkPl(@RequestParam(value="dt", required=true, defaultValue="") String dt) {

		QueryResultMap info = service.selectWrkPl( dt );
		
		ResponseDTO dto = createResponseDTO();
		
		dto.put( "info", info );
		return dto;
    }


	@RequestMapping("/ajaxR/MHCommon/getPublicActrAccn.do")
    public @ResponseBody Object getPublicActrAccn(@RequestParam(value="empUniqNo", required=true, defaultValue="") String empUniqNo) {

		QueryResultMap info = service.getPublicActrAccn( empUniqNo );

		ResponseDTO dto = createResponseDTO();
		dto.put( "info", info );
		return dto;
    }


	@RequestMapping("/ajaxS/MHCommon/setPublicActrAccn.do")
    public @ResponseBody Object setPublicActrAccn(@RequestParam(value="actrCd", required=true, defaultValue="") String actrCd,
    		@RequestParam(value="srno", required=true, defaultValue="") String srno) throws Throwable {

			service.setPublicActrAccn( actrCd, srno );
			ResponseDTO dto = createResponseDTO();
			return dto;
    }



//	@RequestMapping("/ajaxR/MHCommon/edmsTest.do")
//    public @ResponseBody Object edmsTest(@RequestParam(value="edmsDoctId", required=true, defaultValue="") String edmsDoctId) throws Throwable {
//
//		String attDoctId = service.createAttchDocByEdmsDoctId( edmsDoctId, "kyktest", getUserInfo(), true );
//
//		AjaxDTO dto = new AjaxDTO();
//		dto.put( "attDoctId", attDoctId );
//		return dto;
//    }
//
//
//	private boolean validateEmpInfo(String empUniqNo, BindingResult br) {
//
//		boolean isValid = true;
//		if(StringUtils.isNotEmpty(empUniqNo)) {
//			if(!ValidatorUtil.isMaxLength(empUniqNo, 10)) {
//				addMessage(br, "valid.maxlength", "empUniqNo","10" );
//			}
//		} else {
//			addMessage(br, "valid.required", "empUniqNo" );
//		}
//
//		if (br.hasErrors()) {
//			isValid = false;
//		}
//
//		return isValid;
//	}

}
