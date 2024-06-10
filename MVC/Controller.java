
@Controller
public class MHK1001Controller extends BaseAbstractController
{
	@Autowired MHK10Service mhk10Service;





	/** 매개변수
	 * @RequestParam : parameter
	 * @RequestBody : Object
	 * @ModelAttribute : 1. 매개변수에서 @ModelAttribute를 생략가능. Spring은 자동으로 매개변수 타입을 보고 @ModelAttribute가 사용된 것으로 간주
	 * 					 2. 컨트롤러 메서드에 데이터를 바인딩, 뷰에서 참조할 수 있도록 모델 데이터를 준비
	 */


	@RequestMapping("/ajaxR/MHK1001/selectChoiceFwpInfo.do")
	public @ResponseBody 
	Object selectFwpInfo(@RequestParam(value="empNo", required=true, defaultValue="") String fwpAplNo, BindingResult br) {

		QueryResultMap info = mhk10Service.selectFwpInfo(fwpAplNo);
		ResponseDTO resDto = createResponseDTO();
		resDto.put("mstBox", info);

		return resDto;
	}


}
