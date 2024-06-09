package pierp.app.common.security.model;

/**
 * 
 * @Class AprhCtrlVO
 * @Description IP 접근 제어 VO
 * @author 이정민
 * @since 2022. 5. 11.
 * @version
 *
 */
public class AprhCtrlVO {

	private String begnIp;   // 시작 IP
	private String endIp;    // 끝 IP
	private String sysCls;   // 접근제어 구분

	public String getBegnIp() {
		return begnIp;
	}

	public void setBegnIp(String begnIp) {
		this.begnIp = begnIp;
	}

	public String getEndIp() {
		return endIp;
	}

	public void setEndIp(String endIp) {
		this.endIp = endIp;
	}
	
	public String getSysCls() {
		return sysCls;
	}
	
	public void setSysCls(String sysCls) {
		this.sysCls = sysCls;
	}

	@Override
	public String toString() {
		return "AprhCtrlVO [begnIp=" + begnIp + ", endIp=" + endIp + "sysCls=" + sysCls +"]";
	}
}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 * 
 */