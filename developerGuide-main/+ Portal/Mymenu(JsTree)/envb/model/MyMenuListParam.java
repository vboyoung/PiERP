package pierp.app.eip.bizENV.envb.model;

import pierp.app.common.cmd.ParamCmd;
import pierp.common.main.model.UserInfo;

/**
 * @Class MyMenuListParam
 * @Description LIST 파라미터 전달 용
 * @author 이정민
 * @since 2022. 06. 21.
 * @version
 *
 */

public class MyMenuListParam extends ParamCmd{

	/**
	 *
	 */
	private static final long serialVersionUID = 2046058655520803030L;

	private String[] menuNm;			//메뉴명
	private String[] chocTopMenuId;		//상위메뉴id
	private String[] chocMenuId;		//메뉴id
	private String[] chocMdulId;		//모듈id

	private MenuSearchCmd menuSearchCmd;


	private UserInfo userInfo;


	public MenuSearchCmd getMenuSearchCmd() {
		return menuSearchCmd;
	}
	public void setMenuSearchCmd(MenuSearchCmd menuSearchCmd) {
		this.menuSearchCmd = menuSearchCmd;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public String[] getMenuNm() {
		String[] ret = null;
		if(this.menuNm != null) {
			ret = new String[this.menuNm.length];
			for(int i=0; i<this.menuNm.length; i++) {
				ret[i] = this.menuNm[i];
			}
		}
		return ret;
	}
	public void setMenuNm(String[] menuNm) {
		this.menuNm = new String[menuNm.length];
		for(int i = 0; i < menuNm.length ; i++) {
			this.menuNm[i] = menuNm[i];
		}
	}
	public String[] getChocTopMenuId() {
		String[] ret = null;
		if(this.chocTopMenuId != null) {
			ret = new String[this.chocTopMenuId.length];
			for(int i=0; i<this.chocTopMenuId.length; i++) {
				ret[i] = this.chocTopMenuId[i];
			}
		}
		return ret;
	}
	public void setChocTopMenuId(String[] chocTopMenuId) {
		this.chocTopMenuId = new String[chocTopMenuId.length];
		for(int i = 0; i < chocTopMenuId.length ; i++) {
			this.chocTopMenuId[i] = chocTopMenuId[i];
		}
	}
	public String[] getChocMenuId() {
		String[] ret = null;
		if(this.chocMenuId != null) {
			ret = new String[this.chocMenuId.length];
			for(int i=0; i<this.chocMenuId.length; i++) {
				ret[i] = this.chocMenuId[i];
			}
		}
		return ret;
	}
	public void setChocMenuId(String[] chocMenuId) {
		this.chocMenuId = new String[chocMenuId.length];
		for(int i = 0; i < chocMenuId.length ; i++) {
			this.chocMenuId[i] = chocMenuId[i];
		}
	}
	public String[] getChocMdulId() {
		String[] ret = null;
		if(this.chocMdulId != null) {
			ret = new String[this.chocMdulId.length];
			for(int i=0; i<this.chocMdulId.length; i++) {
				ret[i] = this.chocMdulId[i];
			}
		}
		return ret;
	}
	public void setChocMdulId(String[] chocMdulId) {
		this.chocMdulId = new String[chocMdulId.length];
		for(int i = 0; i < chocMdulId.length ; i++) {
			this.chocMdulId[i] = chocMdulId[i];
		}
	}

}


