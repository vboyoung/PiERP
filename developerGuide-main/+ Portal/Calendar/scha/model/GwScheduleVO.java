package pierp.app.eip.bizSCH.scha.model;

/**
 * @Class GwScheduleVO
 * @Description
 *  그룹웨어 DB 일정 조회 용 VO
 * @author 정성희
 * @since 2022. 06. 25.
 * @version
 *
 */

public class GwScheduleVO {

	private 	 String calendarId;	 //달력ID
	private 	 String calendarTitle;	 //달력명
	private 	 String authTp;	 //일정공유여부
	private 	 String authEmpNo;	 //권한소유자 사원번호
	private 	 String ownerTp;	 //소유자유형
	private 	 String eventId;	 //일정ID
	private 	 String title;	 //일정제목
	private 	 String location;	 //장소
	private 	 String startDt;	 //일정시작일
	private 	 String endDt;	 //일정종료일


	public String getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}
	public String getCalendarTitle() {
		return calendarTitle;
	}
	public void setCalendarTitle(String calendarTitle) {
		this.calendarTitle = calendarTitle;
	}
	public String getAuthTp() {
		return authTp;
	}
	public void setAuthTp(String authTp) {
		this.authTp = authTp;
	}
	public String getAuthEmpNo() {
		return authEmpNo;
	}
	public void setAuthEmpNo(String authEmpNo) {
		this.authEmpNo = authEmpNo;
	}
	public String getOwnerTp() {
		return ownerTp;
	}
	public void setOwnerTp(String ownerTp) {
		this.ownerTp = ownerTp;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	@Override
	public String toString() {
		return "GwScheduleVO [calendarId=" + calendarId + ", calendarTitle=" + calendarTitle
				+ ", authTp=" + authTp + ", authEmpNo=" + authEmpNo + ", ownerTp=" + ownerTp
				+ ", eventId=" + eventId + ", title=" + title + ", location=" + location
				+ ", startDt=" + startDt + ", endDt=" + endDt + "]";
	}

}


