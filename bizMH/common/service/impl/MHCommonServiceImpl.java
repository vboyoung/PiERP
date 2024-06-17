package pierp.app.mis.bizMH.common.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.property.EgovPropertyService;
import pierp.app.mis.bizMH.common.model.MHEmpVO;
import pierp.app.mis.bizMH.common.service.MHCommonService;
import pierp.common.cmmn.service.BaseAbstractServiceImpl;
import pierp.common.crypto.service.CryptoService;
import pierp.common.util.QueryResultMap;


@Service
public class MHCommonServiceImpl extends BaseAbstractServiceImpl implements MHCommonService {

	@Resource(name="sysPropService")
	protected EgovPropertyService sysPropService;

	@Resource(name="cryptoService")
	protected CryptoService cryptoService;

	@Autowired private MHCommonDAO		cmmDAO;

	@Override
	public MHEmpVO selectEmpVO(String empUniqNo) {
		return cmmDAO.selectEmpVO( empUniqNo );
	}


	@Override
	public QueryResultMap selectEmpInfo(String empUniqNo) {
		return cmmDAO.selectEmpMap( empUniqNo );
	}


	@Override
	public QueryResultMap selectEmpInfo2(String empNo) {
		return cmmDAO.selectEmpMap2( empNo );
	}

	@Override
	public QueryResultMap selectEmpInfo3(String deptCd) {
		return cmmDAO.selectEmpMap3( deptCd );
	}


	@Override
	public QueryResultMap selectDeptInfo(String deptUniqNo) {
		return cmmDAO.selectDeptMap( deptUniqNo );
	}


	@Override
	public QueryResultMap selectMngEmp2(String empUniqNo) {

		QueryResultMap hddpEmpMap = cmmDAO.selectMngEmp2( empUniqNo );
		Object hddpEmpUniqNo = hddpEmpMap.get( "empUniqNo" );

		// 전결자 없을경우 처리
		if( hddpEmpUniqNo == null )
			return new QueryResultMap();

		return this.selectEmpInfo( hddpEmpUniqNo.toString() );
	}


	@Override
	public QueryResultMap selectMngEmp(String empUniqNo) {
		QueryResultMap hddpEmpMap = cmmDAO.selectMngEmp( empUniqNo );
		Object hddpEmpUniqNo = hddpEmpMap.get( "empUniqNo" );

		// 전결자 없을경우 처리
		if( hddpEmpUniqNo == null )
			return new QueryResultMap();

		return this.selectEmpInfo( hddpEmpUniqNo.toString() );
	}


	@Override
	public void setPublicActrAccn(String actrCd, String srno) throws Throwable {
		// 계좌전체 N
		// 계좌 100개 넘는 거래처가 없을거야....
		if( cmmDAO.updatePublicActrAccnNoAll( actrCd ) > 100 ){
			throw processException("fail.common.runError",new String[]{"대표계좌설정","대표계좌 변경"});
		}

		// 대표계좌 Y
		if( 1 != cmmDAO.setPublicActrAccn( actrCd, srno ) ){
			throw processException("fail.common.runError",new String[]{"대표계좌설정","대표계좌 설정"});
		}
	}



	@Override
	public QueryResultMap getPublicActrAccn(String empUniqNo) {
		return cmmDAO.getPublicActrAccn( empUniqNo );
	}


	@Override
	public QueryResultMap selectWrkPl(String dt) {
		return cmmDAO.selectWrkPl( dt );
	}

}
















