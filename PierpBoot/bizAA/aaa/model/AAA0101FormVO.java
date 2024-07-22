package pierp.app.mis.bizAA.aaa.model;

import pierp.common.base.vo.AbstractRecordVO;

/**
 * 클래스
 * file name : MHA0201FormVO.java
 *
 * @author 공통팀 dev.vmfhrmfoaj
 * @since 2012. 9. 5.
 * @version 1.0
 *
 * <pre>
 * == 개정이력(Modification Information) ==
 * 
 * 수정일                    수정자       수정내용
 * -------------- -------- ---------------------------
 * 2012. 9. 5.   dev.vmfhrmfoaj     최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class AAA0101FormVO extends AbstractRecordVO {
	
	private String useYn;

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}
