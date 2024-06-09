/*
 * Project : kier-sys
 * 위치 : kier.batch.job.mis.service
 * File : BatchMis01Service.java
 * 생성일자 : 2020. 11. 25.
 * 생성자 : user
 *
 * Copyright(c) 2020 by ITMATE
 */
package kier.batch.job.mis.service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import kier.batch.job.common.JobReturnVO;

/**
 * @Class BatchMis01Service
 * @Description 
 * 
 * @author user
 * @since 2020. 11. 25.
 * @version 
 *
 */
public interface BatchHrm01Service {
	/**
	 * 인사 BAT-R-015 외출건상태변경(PG_HRM_BTCH.SP_OUTING_BTCH) 실행.
	 */
	JobReturnVO execSpOutingBtch() throws EgovBizException;

	/**
	 * 인사 BAT-R-017 선택적근로시간자료생성(PG_HRM_BTCH.SP_MAKE_FX_WORKHOUR_BTCH) 실행
	 */
	JobReturnVO execSpMakeFxWorkhourBtch() throws EgovBizException;

	/**
	 * BAT-R-018 휴가사용일수갱신(PG_HRM_BTCH.SP_VAC_USED_BTCH) 실행.
	 */
	JobReturnVO execSpVacUsedBtch() throws EgovBizException;

	/**
	 * 인사 BAT-R-019 징계기록말소(PG_HRM_BTCH.SP_RWNPT_CNCL_EXPT_BTCH) 실행.
	 */
	JobReturnVO execSpRwnptCnclExptBtch() throws EgovBizException;

	/**
	 * 인사 BAT-R-020 연차생성(PG_HRM_BTCH.SP_DEGR2_CRTN_BTCH) 실행.
	 */
	JobReturnVO execSpDegr2CrtnBtch() throws EgovBizException;

	/**
	 * 인사 BAT-R-031 예약메일 발송 실행.
	 */
	JobReturnVO execEmailRsrvSendBtch() throws EgovBizException;

	/**
	 * 인사 BAT-R-034 인사/직원외 TODO 배치생성(PG_HRM_BTCH.SP_TODO_CRTN_BTCH) 실행.
	 */
	JobReturnVO execSpTodoCrtnBtch() throws EgovBizException;

	/**
	 * 인사 BAT-R-035 인사발령 반영(PG_HRM_BTCH.SP_APINT_APLY_BTCH) 실행.
	 */
	JobReturnVO execSpApintAplyBtch() throws EgovBizException;

	/**
	 * 인사 BAT-R-036 메일사번변경알림(PG_HRM_BTCH.SP_EMAIL_EMPNO_CHG_BTCH) 실행.
	 */
	JobReturnVO execSpEmailEmpnoChgBtch() throws EgovBizException;

}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 * 
 */