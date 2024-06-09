/*
 * Project : kier-sys
 * 위치 : kier.batch.job.mis.service
 * File : BatR036Cmd.java
 * 생성일자 : 2020. 11. 25.
 * 생성자 : user
 *
 * Copyright(c) 2020 by ITMATE
 */
package kier.batch.job.mis;

import org.springframework.beans.factory.annotation.Autowired;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import kier.batch.common.job.BatchExecDTO;
import kier.batch.common.job.BatchExecException;
import kier.batch.job.common.AbstractJobCmd;
import kier.batch.job.common.JobReturnVO;
import kier.batch.job.mis.service.BatchHrm01Service;

/**
 * @Class BatR036Cmd
 * @Description 
 * 인사 BAT-R-036 메일사번변경알림(PG_HRM_BTCH.SP_EMAIL_EMPNO_CHG_BTCH) 실행.
 * @author user
 * @since 2020. 11. 25.
 * @version 
 *
 */
public class BatR036Cmd extends AbstractJobCmd {

	private static final String JOB_ID = "BAT-R-036";
	private static final String JOB_NM = "메일사번변경알림";
	private static final String JOB_SP = "PG_HRM_BTCH.SP_EMAIL_EMPNO_CHG_BTCH";

	@Autowired
	private BatchHrm01Service batchHrm01Service;
	
	/* (non-Javadoc)
	 * @see kier.batch.common.job.BatchExecCmd#executeBatchJob(kier.batch.common.job.BatchExecDTO)
	 */
	@Override
	public void executeBatchJob(BatchExecDTO batchJobDto) throws BatchExecException {
		try {
			JobReturnVO resultVo = batchHrm01Service.execSpEmailEmpnoChgBtch();
			
			batchJobDto.setExecRsltCd(BatchExecDTO.RESULT_CD_SUCCESS);
			batchJobDto.setExecRsltMsg(resultVo.getRetMesg());
		} catch (EgovBizException e) {
			batchJobDto.setExecRsltCd(BatchExecDTO.RESULT_CD_FAIL);
			batchJobDto.setExecRsltMsg(e.getMessageKey()+":"+e.getMessage());
			String logText = String.format("%s %s [%s] 처리 실패", JOB_ID, JOB_NM, JOB_SP);
			logger.warn(logText, e);
		} catch (Exception e) {
			String logText = String.format("%s %s [%s] 실행 중 오류 발생", JOB_ID, JOB_NM, JOB_SP);
			logger.error(logText, e);
			throw new BatchExecException(logText+":"+e.getMessage(), e);
		}
	}

}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 * 
 */