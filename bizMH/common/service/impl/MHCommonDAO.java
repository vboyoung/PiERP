package pierp.app.mis.bizMH.common.service.impl;

import org.springframework.stereotype.Repository;

import pierp.app.mis.bizMH.common.model.MHDeptVO;
import pierp.app.mis.bizMH.common.model.MHEmpVO;
import pierp.common.base.dao.BaseAbstractMapper;
import pierp.common.mvc.web.ParamVO;
import pierp.common.util.QueryResultMap;

@Repository
public class MHCommonDAO extends BaseAbstractMapper {

	/**
	 * selectEmpVO  사원정보조회( MHEmpVO )
	 * @param empUniqNo
	 * @return
	 */
	public MHEmpVO selectEmpVO(String empUniqNo) {
		return (MHEmpVO)selectOne( "MHCommon.selectEmpVO", empUniqNo );
	}


	/**
	 * selectEmpMap 사원정보조회( QueryResultMap )
	 * @param empUniqNo
	 * @return
	 */
	public QueryResultMap selectEmpMap(String empUniqNo) {
		return selectOne( "MHCommon.selectEmpMap", empUniqNo );
	}

	/**
	 * selectEmpMap 사원정보조회( QueryResultMap )
	 * @param empNo
	 * @return
	 */
	public QueryResultMap selectEmpMap2(String empNo) {
		return selectOne( "MHCommon.selectEmpMap2", empNo );
	}

	/**
	 * QueryResultMap 사원정보조회( deptCd )
	 * @param deptCd
	 * @return
	 */
	public QueryResultMap selectEmpMap3(String deptCd) {
		return selectOne( "MHCommon.selectEmpMap3", deptCd );
	}

	/**
	 * selectDeptVO 부서정보조회( MHDeptVO )
	 * @param deptUniqNo
	 * @return
	 */
	public MHDeptVO selectDeptVO(String deptUniqNo) {
		return (MHDeptVO)selectOne( "MHCommon.selectDeptVO", deptUniqNo );
	}

	/**
	 * selectDeptMap 부서정보조회( EgovMap )
	 * @param deptUniqNo
	 * @return
	 */
	public QueryResultMap selectDeptMap(String deptUniqNo) {
		return selectOne( "MHCommon.selectDeptMap", deptUniqNo );
	}


	public QueryResultMap selectMngEmp2(String empUniqNo) {
		return selectOne("MHCommon.selectMngEmp2", empUniqNo);
	}


	/**
	 * selectMngEmp 상위팀장 조회
	 * @param empUniqNo
	 * @return
	 */
	public QueryResultMap selectMngEmp(String empUniqNo) {
		return selectOne( "MHCommon.selectMngEmp", empUniqNo );
	}

	/**
	 * updatePublicActrAccnNoAll 대표계좌 비활성화(최근사용계좌)
	 * @param empUniqNo
	 * @return
	 */
	public int updatePublicActrAccnNoAll(String actrCd) {

		QueryResultMap param = new QueryResultMap();
		param.put( "actrCd",	actrCd );

		return update( "MHCommon.updatePublicActrAccnNoAll", param );
	}


	/**
	 * setPublicActrAccn 대표계좌 설정
	 * @param empUniqNo
	 * @return
	 */
	public int setPublicActrAccn(String actrCd, String srno) {

		QueryResultMap param = new QueryResultMap();
		param.put( "actrCd",	actrCd );
		param.put( "srno",		srno );

		return update( "MHCommon.setPublicActrAccn", param );
	}



	/**
	 * getPublicActrAccn 대표계좌 조회
	 * @param empUniqNo
	 * @return
	 */
	public QueryResultMap getPublicActrAccn(String empUniqNo) {

		ParamVO param = new ParamVO();
		param.put( "empUniqNo",	empUniqNo );

		return selectOne( "MHCommon.getPublicActrAccn", param.getMap() );
	}
	
	/**
	 * selectDeptMap 근무정보조회
	 * @param dt
	 * @return
	 */
	public QueryResultMap selectWrkPl(String dt) {
		return selectOne( "MHCommon.selectWrkPl", dt );
	}

}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 *
 */