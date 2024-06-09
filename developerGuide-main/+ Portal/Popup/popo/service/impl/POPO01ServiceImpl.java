package pierp.app.mgt.bizPOP.popo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.cmd.ParamCmd;
import pierp.app.common.cmd.SearchCmd;
import pierp.app.common.model.table.TbBpPupVO;
import pierp.app.mgt.bizPOP.popo.model.PopForm;
import pierp.app.mgt.bizPOP.popo.model.PopVO;
import pierp.app.mgt.bizPOP.popo.service.POPO01Service;
import pierp.common.attach.service.TbSysAthflVO;
import pierp.common.cmmn.service.BaseAbstractServiceImpl;
import pierp.common.util.StringUtils;
import pierp.module.fileAttach.service.FileParamVO;
import pierp.module.fileAttach.service.FileUploadService;

/**
 * @Class MNUO01ServiceImpl
 * @Description
 * 팝업 관리 서비스구현체를 정의한다.
 * @author 이정민
 * @since 2022.05.24.
 * @version
 *
 *  * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 * 2022.05.24.   이정민		최초 작성
 *
 */

@Service
public class POPO01ServiceImpl extends BaseAbstractServiceImpl implements POPO01Service{

	@Autowired
	POPO01DAO popo01Dao;


	@Autowired
	FileUploadService uploadService;	//공통 파일 첨부 기능

	@Override
	public List<TbBpPupVO> selectPupList(SearchCmd searchCmd) throws EgovBizException {
		return popo01Dao.selectPupList(searchCmd);
	}

	@Override
	public int selectPupCnt(SearchCmd searchCmd) throws EgovBizException {
		return popo01Dao.selectPupCnt(searchCmd);
	}

	@Override
	public TbBpPupVO selectPupOne(SearchCmd searchCmd) throws EgovBizException {
		return popo01Dao.selectPupOne(searchCmd);
	}

	@Override
	public void savePup(PopForm popForm, FileParamVO uploader) throws EgovBizException {

		int nRet = 0;

		//KISED 팝업 등록시 이미지만 첨부된다.
		popForm.setAhflTyp("A");

		//폼 처리
		if(StringUtils.isEmpty(popForm.getPupNo())) {
			nRet = popo01Dao.insertPup(popForm); //INSERT

		}else {
			nRet = popo01Dao.updatePup(popForm); //UPDATE
		}

		uploader.setDocId(popForm.getPupNo());	//업무키 설정
		uploadService.updateFileInfo(uploader);

		//오류가 발생할 소지가 있는 부분에 대한 처리..
		if (nRet != 1) {
			getLogger().error("팝업 저장 오류");
			throw processException("fail.common.runError",new String[]{"팝업저장","저장시 오류가 발생 했습니다."});
        }

	}

	@Override
	public void deletePup(ParamCmd paramCmd) throws EgovBizException {
		int nRet = popo01Dao.deletePup(paramCmd);

		//오류 발생 처리(물리 파일 처리 보다 먼저 검사)
		if (nRet != paramCmd.getSelectedIds().length) {
			getLogger().error("팝업 삭제 오류");
			throw processException("fail.common.runError",new String[]{"팝업 삭제","요청건수와 삭제건수가 다릅니다."});
        }

		//실제 파일 삭제! (마지막에 할 것)
		TbSysAthflVO file = new TbSysAthflVO();

		String[] menuId = paramCmd.getSelectedIds();
		if(menuId !=  null) {
			for(int i = 0 ; i < menuId.length ; i++) {
				file.setProgramId("EIP_POP");
				file.setDocId(menuId[i]);
				uploadService.deleteFileInfo(file);
			}
		}
	}


	@Override
	public PopVO selectPupOneAhfl(SearchCmd searchCmd) throws EgovBizException {
		return popo01Dao.selectPupOneAhfl(searchCmd);
	}
}

