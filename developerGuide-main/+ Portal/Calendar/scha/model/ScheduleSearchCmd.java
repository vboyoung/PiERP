package pierp.app.eip.bizSCH.scha.model;

import pierp.app.common.cmd.SearchCmd;

/**
 *
 * @Class ScheduleSearchCmd
 * @Description
 * 일정목록 검색용
 * @author 정성희
 * @since 2022. 5. 24.
 * @version
 *
 */
public class ScheduleSearchCmd extends SearchCmd{

	private static final long serialVersionUID = 1L;


	private String schMonth;
	private String schYear;
	private String schDay;
	private String ownerTp;		//V_P_SCHEDULE 소유자유형 : 1:전사, 2:부서, 4:개인 ...


	public String getSchMonth() {
		return schMonth;
	}
	public void setSchMonth(String schMonth) {
		this.schMonth = schMonth;
	}
	public String getSchYear() {
		return schYear;
	}
	public void setSchYear(String schYear) {
		this.schYear = schYear;
	}
	public String getSchDay() {
		return schDay;
	}
	public void setSchDay(String schDay) {
		this.schDay = schDay;
	}
	public String getOwnerTp() {
		return ownerTp;
	}
	public void setOwnerTp(String ownerTp) {
		this.ownerTp = ownerTp;
	}


}

