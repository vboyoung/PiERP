package pierp.app.eip.bizENV.envb.model;


/**
 * @Class MenuDataVO
 * @Description
 * @author 이정민
 * @since 2022. 06. 21.
 * @version
 *
 */

public class MenuDataVO {


	private String menuId;
	private String text;
	private String menuDivCd;
	private String mdulId;
	private String upMenuId;
	private String menuNo;
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getMenuDivCd() {
		return menuDivCd;
	}
	public void setMenuDivCd(String menuDivCd) {
		this.menuDivCd = menuDivCd;
	}
	public String getMdulId() {
		return mdulId;
	}
	public void setMdulId(String mdulId) {
		this.mdulId = mdulId;
	}
	public String getUpMenuId() {
		return upMenuId;
	}
	public void setUpMenuId(String upMenuId) {
		this.upMenuId = upMenuId;
	}
	public String getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}


}