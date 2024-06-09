package pierp.app.eip.bizTOD.todb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.cmd.SearchCmd;
import pierp.app.eip.bizTOD.todb.model.BpTodoForm;
import pierp.app.eip.bizTOD.todb.model.VwBpTodoVO;
import pierp.app.eip.bizTOD.todb.service.TODB01Service;
import pierp.common.cmmn.service.BaseAbstractServiceImpl;

/**
 * @Class TODB01erviceImpl
 * @Description
 * TO_DO 나의 할일 서비스구현체를 정의한다.
 * @author 이정민
 * @since 2022.06.15.
 * @version
 *
 *  * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *
 *
 */

@Service
public class TODB01ServiceImpl extends BaseAbstractServiceImpl implements TODB01Service{

	@Autowired
	TODB01DAO todb01DAO;

	@Override
	public List<VwBpTodoVO> selectTodoList(SearchCmd searchCmd) throws EgovBizException {
		return todb01DAO.selectTodoList(searchCmd);
	}



	@Override
	public int selectTodoCnt(SearchCmd searchCmd) throws EgovBizException {
		return todb01DAO.selectTodoCnt(searchCmd);
	}

	@Override
	public int updateTodoStat(BpTodoForm bpTodoForm) throws EgovBizException {

		// 모듈 및 todoDiv값에 따라 ( A : 나의할일에서 취소하면 null or N -> A 로 변경, D : 보류목록에서 변경하면 A -> N 으로 변경 )
		String todoDiv = bpTodoForm.getTodoDiv();

		if("A".equals(todoDiv)) {
			bpTodoForm.setOldStatCd(bpTodoForm.getStatCd());
			bpTodoForm.setStatCd("S");
		} else if("D".equals(todoDiv)) {
			bpTodoForm.setStatCd(bpTodoForm.getOldStatCd());
			bpTodoForm.setOldStatCd("");
		}

		return todb01DAO.updateTodoStat(bpTodoForm);
	}



	@Override
	public int updateTodoImpr(BpTodoForm bpTodoForm) throws EgovBizException {

		String impr = bpTodoForm.getImpr();

		// 중요 정보가 표시되어있으면 "" 없으면 A값으로 toggle 형식으로 변경한다.
		if(impr.isEmpty()) {
			bpTodoForm.setImpr("A");
		} else {
			bpTodoForm.setImpr("");
		}

		return todb01DAO.updateTodoImpr(bpTodoForm);
	}




}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *
 */