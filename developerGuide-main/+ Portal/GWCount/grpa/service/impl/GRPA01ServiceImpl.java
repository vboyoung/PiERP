package pierp.app.eip.bizGRP.grpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.common.cmd.SearchCmd;
import pierp.app.eip.bizGRP.grpa.model.GwRetCntVO;
import pierp.app.eip.bizGRP.grpa.model.GwSelectVO;
import pierp.app.eip.bizGRP.grpa.service.GRPA01Service;
import pierp.common.cmmn.service.BaseAbstractServiceImpl;

/**
 * @Class GRPA01ServiceImpl
 * @Description
 * 메인 TO_DO 서비스구현체를 정의한다.
 * @author 정성희
 * @since 2022.06.15.
 * @version
 *
 */
@Service
public class GRPA01ServiceImpl extends BaseAbstractServiceImpl implements GRPA01Service {

	@Autowired
	GRPA01DAO grpa01Dao;



	@Override
	public GwRetCntVO selectGwCnt(SearchCmd searchCmd) throws EgovBizException {
		GwRetCntVO gwRetCntVO = new GwRetCntVO();

		List<GwSelectVO> list = grpa01Dao.selectGwAtSancCnt(searchCmd);
		getLogger().debug("GwSelectVO select Recode CNT : " + list.size());

		//TODO 결재 용어 정의 협의 후 로직 수정 할 것!
		for(GwSelectVO grpaSelectVO : list) {

			//TODO 차후에 클라이언트 사이와 이 부분 로직 맞출것!!
			//접수대기,발송처리 2개 개인별 권한에 따라 Y,N으로 표시되며    Y값만 업무포털에 표시
			String cnt = "-1"; //음수값(음수면 클라이언트 사이드에서 숨김)
		//	if(grpaSelectVO.getAuth().equals("Y")) {
				cnt = grpaSelectVO.getCnt(); //Y값만 업무포털에 표시
		//	}

			//리턴될 변수에 매핑해 준다.(JDK1.7 이상 문자열비교 가능)
			switch (grpaSelectVO.getCode()) {
			case "APPRWAIT": 	//결재대기
				gwRetCntVO.setApprwait(cnt);
				break;

			case "APPRING": 	//결재진행
				gwRetCntVO.setAppring(cnt);
				break;

			case "PUBWAIT": 	//공람대기
				gwRetCntVO.setPubwait(cnt);
				break;

			case "RECVUSER": 	//개인접수
				getLogger().debug("개인접수 : " + cnt); //표시안함
				break;

			case "RECVING": 	//수신반송
				gwRetCntVO.setRecving(cnt);
				break;

			case "RECVWAIT": 	//접수대기 (권한)
				gwRetCntVO.setRecvwait(cnt);
				break;

			case "EXAMPROC": 	//발송처리	(권한)
				gwRetCntVO.setExamproc(cnt);
				break;

			default:
				getLogger().error("오류케이스 : " + grpaSelectVO.getCode());
				break;
			}
		} //for


		//최근 게시글 여기서... (그룹웨어 건수조회는 같은 트랜잭션에서 진행 한다)
		GwRetCntVO newBbsCntVO = grpa01Dao.selectNewBbsCnt(searchCmd);
		gwRetCntVO.setBbsNewHom(newBbsCntVO.getBbsNewHom());

		return gwRetCntVO;
	}


}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 * 2020.07.23.   박성현
 */