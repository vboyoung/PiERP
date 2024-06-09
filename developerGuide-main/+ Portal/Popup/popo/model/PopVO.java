package pierp.app.mgt.bizPOP.popo.model;

import java.util.ArrayList;
import java.util.List;

import pierp.app.common.model.table.SyAthflVO;
import pierp.app.common.model.table.TbBpPupVO;

/**
 * @Class PopVO
 * @Description
 *  TB_BP_PUP : 팝업 테이블, 추가 컬럼 (첨부파일)
 * @author 이정민
 * @since 2022. 05. 24.
 * @version
 *
 */

public class PopVO extends TbBpPupVO {

	private static final long serialVersionUID = 1L;
	//첨부파일 조회를 위해
	private List<SyAthflVO> tbSyAthflVO;
	private String programId;

	public List<SyAthflVO> getTbSyAthflVO() {

		List<SyAthflVO> ret = new ArrayList<>();	//code-ray
		ret = tbSyAthflVO;
		return ret;

		//return tbSyAthflVO;
	}

	public void setTbSyAthflVO(List<SyAthflVO> tbSyAthflVO) {

		this.tbSyAthflVO = new ArrayList<>(tbSyAthflVO); //code-ray

		//this.tbSyAthflVO = tbSyAthflVO;
	}


	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}
}
