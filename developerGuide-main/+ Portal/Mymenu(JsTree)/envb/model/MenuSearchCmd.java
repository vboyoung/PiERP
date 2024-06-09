package pierp.app.eip.bizENV.envb.model;

import pierp.app.common.cmd.SearchCmd;

/**
 *
 * @Class MenuSearchCmd
 * @Description
 * @author 이정민
 * @since 2022. 06. 21.
 * @version
 *
 */
public class MenuSearchCmd extends SearchCmd{


	private static final long serialVersionUID = 1L;

	private String usrId;
	private String savedMenuCnt;



	public String getSavedMenuCnt() {
		return savedMenuCnt;
	}
	public void setSavedMenuCnt(String savedMenuCnt) {
		this.savedMenuCnt = savedMenuCnt;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

}

/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *
 */
