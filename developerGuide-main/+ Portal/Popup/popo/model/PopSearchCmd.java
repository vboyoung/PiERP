package pierp.app.mgt.bizPOP.popo.model;

import pierp.app.common.cmd.SearchCmd;

/**
 *
 * @Class PopSearchCmd
 * @Description
 * 팝업 검색용
 * @author 이정민
 * @since 2022. 5. 24.
 * @version
 *
 */
public class PopSearchCmd extends SearchCmd{

	private static final long serialVersionUID = 1L;

	private String rangeDate;
	private String rgtrLoca;
	private String tmpSaveYn;

	private String[] pupNo;	 //팝업번호

	public String[] getPupNo() {
		String[] ret = null;
		if(this.pupNo != null) {
			ret = new String[this.pupNo.length];
			for(int i=0; i<this.pupNo.length; i++) {
				ret[i] = this.pupNo[i];
			}
		}
		return ret;
		//return pupNo;
	}

	public void setPupNo(String[] pupNo) {

		this.pupNo = new String[pupNo.length];
		for(int i = 0; i < pupNo.length ; i++) {
			this.pupNo[i] = pupNo[i];
		}
		//this.pupNo = pupNo;
	}

	public String getRangeDate() {
		return rangeDate;
	}


	public void setRangeDate(String rangeDate) {
		this.rangeDate = rangeDate;
	}


	public String getRgtrLoca() {
		return rgtrLoca;
	}


	public void setRgtrLoca(String rgtrLoca) {
		this.rgtrLoca = rgtrLoca;
	}


	public String getTmpSaveYn() {
		return tmpSaveYn;
	}


	public void setTmpSaveYn(String tmpSaveYn) {
		this.tmpSaveYn = tmpSaveYn;
	}


}

