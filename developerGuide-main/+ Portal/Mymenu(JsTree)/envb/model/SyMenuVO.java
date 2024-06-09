package pierp.app.eip.bizENV.envb.model;

import java.util.List;

import pierp.app.common.model.table.TbSyMenuVO;




public class SyMenuVO extends TbSyMenuVO{

	private String id;
	private String parent;
	private String text;

	private List<SyMenuVO> subMenuList;


	public List<SyMenuVO> getSubMenuList() {
		return subMenuList;
	}

	public void setSubMenuList(List<SyMenuVO> subMenuList) {
		this.subMenuList = subMenuList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}



}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *
 */