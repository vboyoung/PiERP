/*
 * Project : kier-sys
 * 위치 : kier.batch.job.mis.service.impl
 * File : BatchMis01ServiceImpl.java
 * 생성일자 : 2020. 11. 25.
 * 생성자 : user
 *
 * Copyright(c) 2020 by ITMATE
 */
package kier.batch.job.mis.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import kier.batch.job.common.JobReturnVO;
import kier.batch.job.mis.service.BatchHrm01Service;
import pierp.common.cmmn.service.BaseAbstractServiceImpl;
import pierp.common.link.mail.service.MailService;
import pierp.common.link.mail.service.MailVO;

/**
 * @Class BatchMis01ServiceImpl
 * @Description 
 * 
 * @author user
 * @since 2020. 11. 25.
 * @version 
 *
 */
@Service
public class BatchHrm01ServiceImpl extends BaseAbstractServiceImpl implements BatchHrm01Service {

	@Autowired
	private BatchHrm01Dao batchMis01Dao;
	
	@Autowired
	private MailService mailService;
	
	/* (non-Javadoc)
	 * @see kier.batch.job.mis.service.BatchMis01Service#execSpOutingBtch()
	 */
	@Override
	public JobReturnVO execSpOutingBtch() throws EgovBizException{
		
		String retCode = "";
		String retMesg = "";
		
		Map<String, String> retMap = batchMis01Dao.execSpOutingBtch();
		
		retCode = retMap.get(JobReturnVO.OUT_CODE_KEY);
		retMesg = retMap.get(JobReturnVO.OUT_MESG_KEY);

		if(!"S".equalsIgnoreCase(retCode)){
			if(retMesg.indexOf("ORA-") >= 0){
				throw this.processException("error.common.sqlRunError", new String[]{"PG_HRM_BTCH.SP_OUTING_BTCH", retMesg});
			}else{
				throw this.processException("fail.common.runError", new String[] {"PG_HRM_BTCH.SP_OUTING_BTCH", retMesg});
			}
		}
		
		return new JobReturnVO(retCode, retMesg);
	}

	/* (non-Javadoc)
	 * @see kier.batch.job.mis.service.BatchHrm01Service#execSpMakeFxWorkhourBtch()
	 */
	@Override
	public JobReturnVO execSpMakeFxWorkhourBtch() throws EgovBizException {
		
		String retCode = "";
		String retMesg = "";
		
		Map<String, String> retMap = batchMis01Dao.execSpMakeFxWorkhourBtch();
		
		retCode = retMap.get(JobReturnVO.OUT_CODE_KEY);
		retMesg = retMap.get(JobReturnVO.OUT_MESG_KEY);

		if(!"S".equalsIgnoreCase(retCode)){
			if(retMesg.indexOf("ORA-") >= 0){
				throw this.processException("error.common.sqlRunError", new String[]{"PG_HRM_BTCH.SP_MAKE_FX_WORKHOUR_BTCH", retMesg});
			}else{
				throw this.processException("fail.common.runError", new String[] {"PG_HRM_BTCH.SP_MAKE_FX_WORKHOUR_BTCH", retMesg});
			}
		}
		
		return new JobReturnVO(retCode, retMesg);
	}

	/* (non-Javadoc)
	 * @see kier.batch.job.mis.service.BatchHrm01Service#execSpVacUsedBtch()
	 */
	@Override
	public JobReturnVO execSpVacUsedBtch() throws EgovBizException {
		
		String retCode = "";
		String retMesg = "";
		
		Map<String, String> retMap = batchMis01Dao.execSpVacUsedBtch();
		
		retCode = retMap.get(JobReturnVO.OUT_CODE_KEY);
		retMesg = retMap.get(JobReturnVO.OUT_MESG_KEY);

		if(!"S".equalsIgnoreCase(retCode)){
			if(retMesg.indexOf("ORA-") >= 0){
				throw this.processException("error.common.sqlRunError", new String[]{"PG_HRM_BTCH.SP_VAC_USED_BTCH", retMesg});
			}else{
				throw this.processException("fail.common.runError", new String[] {"PG_HRM_BTCH.SP_VAC_USED_BTCH", retMesg});
			}
		}
		
		return new JobReturnVO(retCode, retMesg);
	}

	/* (non-Javadoc)
	 * @see kier.batch.job.mis.service.BatchHrm01Service#execSpRwnptCnclExptBtch()
	 */
	@Override
	public JobReturnVO execSpRwnptCnclExptBtch() throws EgovBizException {
		
		String retCode = "";
		String retMesg = "";
		
		Map<String, String> retMap = batchMis01Dao.execSpRwnptCnclExptBtch();
		
		retCode = retMap.get(JobReturnVO.OUT_CODE_KEY);
		retMesg = retMap.get(JobReturnVO.OUT_MESG_KEY);

		if(!"S".equalsIgnoreCase(retCode)){
			if(retMesg.indexOf("ORA-") >= 0){
				throw this.processException("error.common.sqlRunError", new String[]{"PG_HRM_BTCH.SP_RWNPT_CNCL_EXPT_BTCH", retMesg});
			}else{
				throw this.processException("fail.common.runError", new String[] {"PG_HRM_BTCH.SP_RWNPT_CNCL_EXPT_BTCH", retMesg});
			}
		}
		
		return new JobReturnVO(retCode, retMesg);
	}

	/* (non-Javadoc)
	 * @see kier.batch.job.mis.service.BatchHrm01Service#execSpDegr2CrtnBtch()
	 */
	@Override
	public JobReturnVO execSpDegr2CrtnBtch() throws EgovBizException {
		
		String retCode = "";
		String retMesg = "";
		
		Map<String, String> retMap = batchMis01Dao.execSpDegr2CrtnBtch();
		
		retCode = retMap.get(JobReturnVO.OUT_CODE_KEY);
		retMesg = retMap.get(JobReturnVO.OUT_MESG_KEY);

		if(!"S".equalsIgnoreCase(retCode)){
			if(retMesg.indexOf("ORA-") >= 0){
				throw this.processException("error.common.sqlRunError", new String[]{"PG_HRM_BTCH.SP_DEGR2_CRTN_BTCH", retMesg});
			}else{
				throw this.processException("fail.common.runError", new String[] {"PG_HRM_BTCH.SP_DEGR2_CRTN_BTCH", retMesg});
			}
		}
		
		return new JobReturnVO(retCode, retMesg);
	}

	/* (non-Javadoc)
	 * @see kier.batch.job.mis.service.BatchHrm01Service#execEmailRsrvSendBtch()
	 */
	@Override
	public JobReturnVO execEmailRsrvSendBtch() throws EgovBizException {
		
		String retCode = "S";
		String retMesg = "";
		
		List<Map<String, Object>> list = batchMis01Dao.selectEmailRsrvTargetList();
		int size = list.size();
		int succCnt = 0;
		int failCnt = 0;
		
		if( size > 0 ) {
			for(Map<String, Object> cur : list) {
				
				BigDecimal seq = (BigDecimal)cur.get("SEQ");
				try {
					
					// 메일발송
					MailVO mailVO = new MailVO();
					
					mailVO.setSubject((String)cur.get("SBJT"));
					mailVO.setHtmlMessage((String)cur.get("CNTS"));
					mailVO.setFrom((String)cur.get("FR_EMAIL_ADDR"));
					mailVO.addTo((String)cur.get("TO_EMAIL_ADDR"));
					
					mailVO.setSendSysId("Batch");
					mailVO.setSendUsrId("Batch");
					mailVO.setSendMdulId("BatchHrm01");
					
					mailService.sendMail(mailVO);
					
					batchMis01Dao.updateEmailRsrvSendResult(seq.longValue(), "S");
					succCnt++;
				} catch (Exception e) {
					logger.error("메일발송 실패.", e);
					batchMis01Dao.updateEmailRsrvSendResult(seq.longValue(), "F");
					failCnt++;
				}
			}
			
			retMesg = String.format("%d개의 메일 발송 : 성공[%d], 실패[%d]", list.size(), succCnt, failCnt);
		}
		
		return new JobReturnVO(retCode, retMesg);
	}

	/* (non-Javadoc)
	 * @see kier.batch.job.mis.service.BatchHrm01Service#execSpTodoCrtnBtch()
	 */
	@Override
	public JobReturnVO execSpTodoCrtnBtch() throws EgovBizException {
		
		String retCode = "";
		String retMesg = "";
		
		Map<String, String> retMap = batchMis01Dao.execSpTodoCrtnBtch();
		
		retCode = retMap.get(JobReturnVO.OUT_CODE_KEY);
		retMesg = retMap.get(JobReturnVO.OUT_MESG_KEY);

		if(!"S".equalsIgnoreCase(retCode)){
			if(retMesg.indexOf("ORA-") >= 0){
				throw this.processException("error.common.sqlRunError", new String[]{"PG_HRM_BTCH.SP_TODO_CRTN_BTCH", retMesg});
			}else{
				throw this.processException("fail.common.runError", new String[] {"PG_HRM_BTCH.SP_TODO_CRTN_BTCH", retMesg});
			}
		}
		
		return new JobReturnVO(retCode, retMesg);
	}

	/* (non-Javadoc)
	 * @see kier.batch.job.mis.service.BatchHrm01Service#execSpApintAplyBtch()
	 */
	@Override
	public JobReturnVO execSpApintAplyBtch() throws EgovBizException {
		
		String retCode = "";
		String retMesg = "";
		
		Map<String, String> retMap = batchMis01Dao.execSpApintAplyBtch();
		
		retCode = retMap.get(JobReturnVO.OUT_CODE_KEY);
		retMesg = retMap.get(JobReturnVO.OUT_MESG_KEY);

		if(!"S".equalsIgnoreCase(retCode)){
			if(retMesg.indexOf("ORA-") >= 0){
				throw this.processException("error.common.sqlRunError", new String[]{"PG_HRM_BTCH.SP_APINT_APLY_BTCH", retMesg});
			}else{
				throw this.processException("fail.common.runError", new String[] {"PG_HRM_BTCH.SP_APINT_APLY_BTCH", retMesg});
			}
		}
		
		return new JobReturnVO(retCode, retMesg);
	}

	/* (non-Javadoc)
	 * @see kier.batch.job.mis.service.BatchHrm01Service#execSpEmailEmpnoChgBtch()
	 */
	@Override
	public JobReturnVO execSpEmailEmpnoChgBtch() throws EgovBizException {
		
		String retCode = "";
		String retMesg = "";
		
		Map<String, String> retMap = batchMis01Dao.execSpEmailEmpnoChgBtch();
		
		retCode = retMap.get(JobReturnVO.OUT_CODE_KEY);
		retMesg = retMap.get(JobReturnVO.OUT_MESG_KEY);

		if(!"S".equalsIgnoreCase(retCode)){
			if(retMesg.indexOf("ORA-") >= 0){
				throw this.processException("error.common.sqlRunError", new String[]{"PG_HRM_BTCH.SP_EMAIL_EMPNO_CHG_BTCH", retMesg});
			}else{
				throw this.processException("fail.common.runError", new String[] {"PG_HRM_BTCH.SP_EMAIL_EMPNO_CHG_BTCH", retMesg});
			}
		}
		
		return new JobReturnVO(retCode, retMesg);
	}

}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 * 
 */