package pierp.app.eip.bizGRP.grpa.model;

/**
 * @Class GwSelectVO
 * @Description
 *  그룹웨어 DB 조회 용 VO
 * @author 정성희
 * @since 2022. 06. 22.
 * @version
 *
 */

public class GwSelectVO {

	private String empNo;
	private String item;
	private String code;
	private String auth;
	private String cnt;


	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "GwSelectVO [empNo=" + empNo + ", item=" + item + ", code=" + code + ", auth="
				+ auth + ", cnt=" + cnt + "]";
	}

}


