package pierp.app.mis.bizAA.aaa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import pierp.app.mis.common.table.model.TbMhEvyeEpdtAncVO;
import pierp.app.mis.common.table.model.TbMhEvyeEpdtPlnVO;
import pierp.app.mis.common.table.model.TbMhOdbyUspVO;
import pierp.common.base.dao.BaseAbstractMapper;
import pierp.common.mvc.web.ParamVO;
import pierp.common.util.QueryResultMap;




@Repository
public class AAA01DAO extends BaseAbstractMapper {
	
	
		
	public void selectaa() {
		
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> map1 = new HashMap<String, Object>();
		
		map.put("yo1", "mapyo1");
		map.put("yo2", "mapyo2");
		
		map1.put("yo1", "map2yo1");
		map1.put("yo2", "map2yo2");
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
		list.add(map);
		list.add(map1);

		EgovMap param = new EgovMap();
		
		param.put("yyoon", list);
		param.put("ymap", map);
		param.put("mu", "hh");
		
		selectList("AAA01.selectaa",list);
	}


	/**
	 * selectEvyeEpdtPlnList 목록 조회 1
	 * @param srchBox
	 * @return 연차 촉진계획 목록
	 */
	public List<QueryResultMap> selectEvyeEpdtPlnList(ParamVO srchBox) {
		return selectList("AAA01.selectEvyeEpdtPlnList", srchBox.getMap());
	}

	
	
	
	/**
	 * checkEvyeEpdtPlnPk 연차 촉진계획 PK 체크
	 * @param vo
	 * @return
	 */
	public int checkEvyeEpdtPlnPk(TbMhEvyeEpdtPlnVO vo) {
		return selectOne("AAA01.checkEvyeEpdtPlnPk", vo);
	}
	
	

    /**
     * selectEvyeEpdtAncList 목록 조회 2
     * @param mstBox
     * @return 연차 촉진 대상자 목록
     */
	public List<QueryResultMap> selectEvyeEpdtAncList(ParamVO mstBox) {
		return selectList("AAA01.selectEvyeEpdtAncList", mstBox.getMap());
	}
	
	

	/**
	 * checkEvyeEpdtAncList 다른 여차촉진관리번호 체크
	 * @param mstBox
	 * @return
	 */
	public int checkEvyeEpdtAncList(TbMhEvyeEpdtAncVO vo) {
		return selectOne("AAA01.checkEvyeEpdtAncList", vo);
	}
	
	
	

	
	
	
/*************************************************************************************************************************/	
	
	
	
	

	/**
	 * 연차사용계획 목록조회
	 *
	 * @param vo
	 * @return
	 */
	public List<QueryResultMap> selectEvyeSituList(ParamVO vo) {
		return selectList("AAA01.selectEvyeSituList", vo.getMap());
	}

	public QueryResultMap getOdbyUspInfo(TbMhOdbyUspVO vo) {
		return selectOne("AAA01.getOdbyUspInfo", vo);
	}

	/**
	 * 연차사용계획 목록조회
	 *
	 * @param vo
	 * @return
	 */
	public List<QueryResultMap> selectOdbyUspList(TbMhOdbyUspVO vo) {
		return selectList("AAA01.selectOdbyUspList", vo);
	}

	public String deleteOdbyUspInfo(TbMhOdbyUspVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteodbyUspDtlAll(TbMhOdbyUspVO vo) {
		delete("AAA01.deleteodbyUspDtlAll", vo);		
	}

	/**
	 * calcVactDys 휴가 기간 계산 - 일단위 환산
	 * @param begnDt		: 휴가 시작일자
	 * @param begnDtm		: 휴가 시작시간
	 * @param clseDt		: 휴가 종료일자
	 * @param clseDtm		: 휴가 종료시간
	 * @return
	 */
	public Double calcOdbyUspDys(ParamVO vo) {
		
//		vo.put("procDvcd", "1");
		EgovMap param = new EgovMap();
		param.put( "procDvcd",	"1" );
		param.put( "vactBegnDt",	vo.get("vactBegnDt"));
		param.put( "vactBegnPtm",	vo.get("vactBegnPtm") == null?"":vo.get("vactBegnPtm"));
		param.put( "vactClseDt",	vo.get("vactClseDt"));
		param.put( "vactClsePtm",	vo.get("vactClsePtm") == null?"":vo.get("vactClsePtm"));
		param.put( "vactDivCd", 	vo.get("vactDivCd"));
		param.put( "empUniqNo",     vo.get("empUniqNo"));

		return (Double)selectOne( "AAA01.calcOdbyUspDys", param );
	}

	/**
	 * 마스터 계획일자 업데이트
	 *
	 * @param vo
	 * @return
	 */
	public int updateOdbyUsePlnDys(TbMhOdbyUspVO mstVO) {
		return update("AAA01.updateOdbyUsePlnDys", mstVO);
	}

	/**
	 * 연차사용계획등록 승인요청하기
	 *
	 * @param vo
	 * @return
	 */
	public int approvalReqOdbyUsp(TbMhOdbyUspVO vo) {
		return update("AAA01.approvalReqOdbyUsp", vo);
	}

	/**
	 * 연차사용세부계획 중복 날짜 체크
	 * @param vo
	 * @return
	 */
	public List<QueryResultMap> checkDateOdbyUspDtl(String odbyUsePlnNo) {
		return selectList("AAA01.checkDateOdbyUspDtl", odbyUsePlnNo);
	}


}
	


