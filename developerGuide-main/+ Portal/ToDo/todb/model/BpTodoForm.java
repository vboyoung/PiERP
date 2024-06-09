package pierp.app.eip.bizTOD.todb.model;

import pierp.app.common.cmd.ParamCmd;

/**
 * @Class BpTodoForm
 * @Description
 *  TO-DO create/update 처리
 * @author 이정민
 * @since 2022. 06. 28.
 * @version
 *
 */

public class BpTodoForm extends ParamCmd{

	private static final long serialVersionUID = 3769938415858106041L;

	private String todoId;
	private int seq;
	private String objt;
	private String statCd;

	private String todoDiv;
	private String oldStatCd;
	private String impr;

	public String getImpr() {
		return impr;
	}
	public void setImpr(String impr) {
		this.impr = impr;
	}
	public String getOldStatCd() {
		return oldStatCd;
	}
	public void setOldStatCd(String oldStatCd) {
		this.oldStatCd = oldStatCd;
	}
	public String getTodoDiv() {
		return todoDiv;
	}
	public void setTodoDiv(String todoDiv) {
		this.todoDiv = todoDiv;
	}
	public String getTodoId() {
		return todoId;
	}
	public void setTodoId(String todoId) {
		this.todoId = todoId;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getObjt() {
		return objt;
	}
	public void setObjt(String objt) {
		this.objt = objt;
	}
	public String getStatCd() {
		return statCd;
	}
	public void setStatCd(String statCd) {
		this.statCd = statCd;
	}




}
