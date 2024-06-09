package pierp.app.common.security.model;


/**
 * 
 * @Class TbSysUsrComSecuSetVO
 * @Description 
 *   TB_SYS_USR_COM_SECU_SET	 : SYS 측 테이블로 sys측에도 포함될소스 이므로 공통컬럼 상속 안함
 * @author 정성희
 * @since 2020. 5. 13.
 * @version 
 *
 */
public class TbSyUsrComSecuSetVO {
	
	private 	 String secuSetId;	 //공통보안설정번호
	private 	 String loginScndAhtnUseYn;	 //로그인2차인증 사용여부
	private 	 String chgPwdScndAhtnUseYn;	 //비밀번호재설정 사용여부
	private 	 String minPwdLen;	 //최소비밀번호길이
	private 	 String maxPwdLen;	 //최대비밀번호길이
	private 	 String chgPwdPirdUseYn;	 //비밀번호변경주기사용여부
	private 	 String chgPwdPird;	 //비밀번호변경주기
	private 	 String forcChgPwdYn;	 //비밀번호강제변경여부
	private 	 String autoLogoutTm;	 //자동로그아웃시간
	private 	 String mngrIpAprhCtrlUseYn;	 //관리자IP접근제어사용여부
	private 	 String usrIpAprhCtrlUseYn;	 //사용자IP접근제어사용여부

	private 	 String regEmpNo;	 //등록자
	private 	 String regUsrId;	 //등록유저
	private 	 String regDt;	 //등록일자
	private 	 String lastChgEmpNo;	 //최종등록자
	private 	 String lastChgUsrId;	 //최종유저
	private 	 String lastChgDt;	 //최종변경일자
	
	public String getSecuSetId() {
		return secuSetId;
	}
	public void setSecuSetId(String secuSetId) {
		this.secuSetId = secuSetId;
	}
	public String getLoginScndAhtnUseYn() {
		return loginScndAhtnUseYn;
	}
	public void setLoginScndAhtnUseYn(String loginScndAhtnUseYn) {
		this.loginScndAhtnUseYn = loginScndAhtnUseYn;
	}
	public String getChgPwdScndAhtnUseYn() {
		return chgPwdScndAhtnUseYn;
	}
	public void setChgPwdScndAhtnUseYn(String chgPwdScndAhtnUseYn) {
		this.chgPwdScndAhtnUseYn = chgPwdScndAhtnUseYn;
	}
	public String getMinPwdLen() {
		return minPwdLen;
	}
	public void setMinPwdLen(String minPwdLen) {
		this.minPwdLen = minPwdLen;
	}
	public String getMaxPwdLen() {
		return maxPwdLen;
	}
	public void setMaxPwdLen(String maxPwdLen) {
		this.maxPwdLen = maxPwdLen;
	}
	public String getChgPwdPirdUseYn() {
		return chgPwdPirdUseYn;
	}
	public void setChgPwdPirdUseYn(String chgPwdPirdUseYn) {
		this.chgPwdPirdUseYn = chgPwdPirdUseYn;
	}
	public String getChgPwdPird() {
		return chgPwdPird;
	}
	public void setChgPwdPird(String chgPwdPird) {
		this.chgPwdPird = chgPwdPird;
	}
	public String getForcChgPwdYn() {
		return forcChgPwdYn;
	}
	public void setForcChgPwdYn(String forcChgPwdYn) {
		this.forcChgPwdYn = forcChgPwdYn;
	}
	public String getAutoLogoutTm() {
		return autoLogoutTm;
	}
	public void setAutoLogoutTm(String autoLogoutTm) {
		this.autoLogoutTm = autoLogoutTm;
	}
	public String getMngrIpAprhCtrlUseYn() {
		return mngrIpAprhCtrlUseYn;
	}
	public void setMngrIpAprhCtrlUseYn(String mngrIpAprhCtrlUseYn) {
		this.mngrIpAprhCtrlUseYn = mngrIpAprhCtrlUseYn;
	}
	public String getUsrIpAprhCtrlUseYn() {
		return usrIpAprhCtrlUseYn;
	}
	public void setUsrIpAprhCtrlUseYn(String usrIpAprhCtrlUseYn) {
		this.usrIpAprhCtrlUseYn = usrIpAprhCtrlUseYn;
	}
	public String getRegEmpNo() {
		return regEmpNo;
	}
	public void setRegEmpNo(String regEmpNo) {
		this.regEmpNo = regEmpNo;
	}
	public String getRegUsrId() {
		return regUsrId;
	}
	public void setRegUsrId(String regUsrId) {
		this.regUsrId = regUsrId;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getLastChgEmpNo() {
		return lastChgEmpNo;
	}
	public void setLastChgEmpNo(String lastChgEmpNo) {
		this.lastChgEmpNo = lastChgEmpNo;
	}
	public String getLastChgUsrId() {
		return lastChgUsrId;
	}
	public void setLastChgUsrId(String lastChgUsrId) {
		this.lastChgUsrId = lastChgUsrId;
	}
	public String getLastChgDt() {
		return lastChgDt;
	}
	public void setLastChgDt(String lastChgDt) {
		this.lastChgDt = lastChgDt;
	}
	
	@Override
	public String toString() {
		return "TbSysUsrComSecuSetVO [secuSetId=" + secuSetId + ", loginScndAhtnUseYn="
				+ loginScndAhtnUseYn + ", chgPwdScndAhtnUseYn=" + chgPwdScndAhtnUseYn
				+ ", minPwdLen=" + minPwdLen + ", maxPwdLen=" + maxPwdLen + ", chgPwdPirdUseYn="
				+ chgPwdPirdUseYn + ", chgPwdPird=" + chgPwdPird + ", forcChgPwdYn="
				+ forcChgPwdYn + ", autoLogoutTm=" + autoLogoutTm + ", mngrIpAprhCtrlUseYn="
				+ mngrIpAprhCtrlUseYn + ", usrIpAprhCtrlUseYn=" + usrIpAprhCtrlUseYn
				+ ", regEmpNo=" + regEmpNo + ", regUsrId=" + regUsrId + ", regDt=" + regDt
				+ ", lastChgEmpNo=" + lastChgEmpNo + ", lastChgUsrId=" + lastChgUsrId
				+ ", lastChgDt=" + lastChgDt + "]";
	}
	
}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 * 
 */