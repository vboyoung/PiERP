package pierp.app.login.common.model;

import pierp.common.main.model.UserInfo;

/**
 * TB_SY_USR기반 사용자 정보를 담는 Class UserInfo 를 상속
 * 포털에서 추가된 정보 포함.
 *
 * @author 포털팀 정성희
 * @since 2020. 8. 19.
 * @version 1.0
 * @see
 *
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 * 수정일     수정자   수정내용
 * ---------- -------- ---------------------------
 * 2020. 8. 19. 정성희   최초 생성
 *
 * </pre>
 */
public class UserInfoEip extends UserInfo {

	private static final long serialVersionUID = 1L;

	private		String empNo;			//사번
	private 	String lockYn;	 		//잠김여부
	private 	String lockRsn;	 		//잠김사유
	private 	String scndAhtnUseYn;	//2차인증사용여부
	private 	String exptnRsn;	 	//예외사유
	private 	String otpAhtnKey;		//OTP 키



	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getLockYn() {
		return lockYn;
	}
	public void setLockYn(String lockYn) {
		this.lockYn = lockYn;
	}
	public String getLockRsn() {
		return lockRsn;
	}
	public void setLockRsn(String lockRsn) {
		this.lockRsn = lockRsn;
	}
	public String getScndAhtnUseYn() {
		return scndAhtnUseYn;
	}
	public void setScndAhtnUseYn(String scndAhtnUseYn) {
		this.scndAhtnUseYn = scndAhtnUseYn;
	}
	public String getExptnRsn() {
		return exptnRsn;
	}
	public void setExptnRsn(String exptnRsn) {
		this.exptnRsn = exptnRsn;
	}
	public String getOtpAhtnKey() {
		return otpAhtnKey;
	}
	public void setOtpAhtnKey(String otpAhtnKey) {
		this.otpAhtnKey = otpAhtnKey;
	}


}
