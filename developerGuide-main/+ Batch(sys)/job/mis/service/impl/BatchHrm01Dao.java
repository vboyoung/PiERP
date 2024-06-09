/*
 * Project : kier-sys
 * 위치 : kier.batch.job.mis.service.impl
 * File : MIS01Dao.java
 * 생성일자 : 2020. 11. 25.
 * 생성자 : user
 *
 * Copyright(c) 2020 by ITMATE
 */
package kier.batch.job.mis.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import pierp.common.base.dao.BaseAbstractMapper;

/**
 * @Class BatchMis01Dao
 * @Description 
 * 
 * @author user
 * @since 2020. 11. 25.
 * @version 
 *
 */
@Repository
public class BatchHrm01Dao extends BaseAbstractMapper {

	/**
	 * 인사 BAT-R-015 외출건상태변경(PG_HRM_BTCH.SP_OUTING_BTCH) 실행.
	 */
	public Map<String, String> execSpOutingBtch() {
		Map<String, String> map = new HashMap<String, String>();

		selectOne("BatchHrm01.execSpOutingBtch", map);
		
		return map;
	}

	/**
	 * 인사 BAT-R-017 선택적근로시간자료생성(PG_HRM_BTCH.SP_MAKE_FX_WORKHOUR_BTCH) 실행
	 */
	public Map<String, String> execSpMakeFxWorkhourBtch() {
		Map<String, String> map = new HashMap<String, String>();

		selectOne("BatchHrm01.execSpMakeFxWorkhourBtch", map);
		
		return map;
	}

	/**
	 * BAT-R-018 휴가사용일수갱신(PG_HRM_BTCH.SP_VAC_USED_BTCH) 실행.
	 */
	public Map<String, String> execSpVacUsedBtch() {
		Map<String, String> map = new HashMap<String, String>();

		selectOne("BatchHrm01.execSpVacUsedBtch", map);
		
		return map;
	}

	/**
	 * 인사 BAT-R-019 징계기록말소(PG_HRM_BTCH.SP_RWNPT_CNCL_EXPT_BTCH) 실행.
	 */
	public Map<String, String> execSpRwnptCnclExptBtch() {
		Map<String, String> map = new HashMap<String, String>();

		selectOne("BatchHrm01.execSpRwnptCnclExptBtch", map);
		
		return map;
	}

	/**
	 * 인사 BAT-R-020 연차생성(PG_HRM_BTCH.SP_DEGR2_CRTN_BTCH) 실행.
	 */
	public Map<String, String> execSpDegr2CrtnBtch() {
		Map<String, String> map = new HashMap<String, String>();

		selectOne("BatchHrm01.execSpDegr2CrtnBtch", map);
		
		return map;
	}

	/**
	 * 인사 BAT-R-031 예약메일발송 대상건 목록 조회.
	 */
	public List<Map<String, Object>> selectEmailRsrvTargetList(){
		Map<String, String> parameterObject = new HashMap<String, String>();
		return selectList("BatchHrm01.selectEmailRsrvTargetList", parameterObject);
	}

	/**
	 * 인사 BAT-R-031 예약메일발송 결과 저장.
	 */
	public int updateEmailRsrvSendResult(long seq, String sendRsltCd){
		Map<String, Object> parameterObject = new HashMap<String, Object>();
		parameterObject.put("seq", seq);
		parameterObject.put("sendRsltCd", sendRsltCd);
		return update("BatchHrm01.updateEmailRsrvSendResult", parameterObject);
	}

	/**
	 * 인사 BAT-R-034 인사/직원외 TODO 배치생성(PG_HRM_BTCH.SP_TODO_CRTN_BTCH) 실행.
	 */
	public Map<String, String> execSpTodoCrtnBtch() {
		Map<String, String> map = new HashMap<String, String>();

		selectOne("BatchHrm01.execSpTodoCrtnBtch", map);
		
		return map;
	}

	/**
	 * 인사 BAT-R-035 인사발령 반영(PG_HRM_BTCH.SP_APINT_APLY_BTCH) 실행.
	 */
	public Map<String, String> execSpApintAplyBtch() {
		Map<String, String> map = new HashMap<String, String>();

		selectOne("BatchHrm01.execSpApintAplyBtch", map);
		
		return map;
	}

	/**
	 * 인사 BAT-R-036 메일사번변경알림(PG_HRM_BTCH.SP_EMAIL_EMPNO_CHG_BTCH) 실행.
	 */
	public Map<String, String> execSpEmailEmpnoChgBtch() {
		Map<String, String> map = new HashMap<String, String>();

		selectOne("BatchHrm01.execSpEmailEmpnoChgBtch", map);
		
		return map;
	}

}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 * 
 */