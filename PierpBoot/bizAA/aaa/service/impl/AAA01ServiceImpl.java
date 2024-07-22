package pierp.app.mis.bizAA.aaa.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.mis.bizAA.aaa.service.AAA01Service;
import pierp.app.mis.bizMH.common.service.MHKeyGenService;
import pierp.app.mis.bizMH.common.service.impl.MHCommonDAO;
import pierp.app.mis.bizMH.mhk.model.MHK6001FormVO;
import pierp.app.mis.common.table.dao.TbMhEvyeEpdtAncDAO;
import pierp.app.mis.common.table.dao.TbMhEvyeEpdtPlnDAO;
import pierp.app.mis.common.table.dao.TbMhOdbyUspDAO;
import pierp.app.mis.common.table.dao.TbMhOdbyUspDtlDAO;
import pierp.app.mis.common.table.model.TbMhEvyeEpdtAncVO;
import pierp.app.mis.common.table.model.TbMhEvyeEpdtPlnVO;
import pierp.app.mis.common.table.model.TbMhOdbyUspDtlKey;
import pierp.app.mis.common.table.model.TbMhOdbyUspDtlVO;
import pierp.app.mis.common.table.model.TbMhOdbyUspVO;
import pierp.common.base.Constants;
import pierp.common.cmmn.service.BaseAbstractServiceImpl;
import pierp.common.mvc.web.ParamVO;
import pierp.common.util.DateUtils;
import pierp.common.util.QueryResultMap;
import pierp.common.util.StringUtils;

@Service("aaa01Service")
public class AAA01ServiceImpl extends BaseAbstractServiceImpl implements AAA01Service {
	
	
	// 인사
	@Autowired private MHKeyGenService		mhKeyGenService;
	@Autowired private MHCommonDAO			mhCommonDAO;
		
	// 업무
	@Autowired private AAA01DAO				aaa01Dao;
	
	// 테이블
	@Autowired private TbMhEvyeEpdtPlnDAO	tbMhEvyeEpdtPlnDAO;
	@Autowired private TbMhEvyeEpdtAncDAO	tbMhEvyeEpdtAncDAO;
	@Autowired private TbMhOdbyUspDAO		tbMhOdbyUspDAO;
	@Autowired private TbMhOdbyUspDtlDAO	tbMhOdbyUspDtlDAO;

	
	
	@Override
	public void selectaa(MHK6001FormVO vo) {
		aaa01Dao.selectaa();
	}
	
	
	
	
	//목록 조회 1
	@Override
	public List<QueryResultMap> selectEvyeEpdtPlnList(ParamVO srchBox) {
		return aaa01Dao.selectEvyeEpdtPlnList(srchBox);
	}
	
	
	//목록 조회 2
	@Override
	public List<QueryResultMap> selectEvyeEpdtAncList(ParamVO mstBox) {
		if(!StringUtils.isEmpty(mstBox.get("wrkStatCd"))) {
			mstBox.put("wrkStatLs", Arrays.asList( ((String)mstBox.get("wrkStatCd")).split(",")));
		}
		if(!StringUtils.isEmpty(mstBox.get("psitCd"))) {
			mstBox.put("psitLs", Arrays.asList( ((String)mstBox.get("psitCd")).split(",")));
		}
		return aaa01Dao.selectEvyeEpdtAncList(mstBox);
	} 
	
	

	
	//목록 저장 
	@Override
	public void selectEvyeEpdtPln(List<TbMhEvyeEpdtPlnVO> list) throws EgovBizException {
		
		for(TbMhEvyeEpdtPlnVO vo : list) {
			if(Constants.STATE_FLAG_INSERT.equals(vo.getStateFlag().toString())) {
				int checkNum = aaa01Dao.checkEvyeEpdtPlnPk(vo);
				if(checkNum > 0) {
					throw processException("vaild.empty", new String[]{"" + vo.getYy() + "년도 " + vo.getTrn() + "회차 정보가 존재 합니다." });
				}
				String epdtMngeNo = mhKeyGenService.generateEpdtMngeNo();
				vo.setEpdtMngeNo(epdtMngeNo);
				tbMhEvyeEpdtPlnDAO.insert(vo);
			}else if(Constants.STATE_FLAG_UPDATE.equals(vo.getStateFlag().toString())) {
				if(StringUtils.isEmpty(vo.getEpdtMngeNo())) {
					throw processException("fail.common.runError", new String[]{"연차촉진 계획 저장 오류"});
				}
				tbMhEvyeEpdtPlnDAO.update(vo);
			}
		}
	}
	
	
	
	
	//list, parmaVO 저장 
	@Override
	public void SaveEvyeEpdtAnc(List<TbMhEvyeEpdtAncVO> list2, ParamVO mstBox) throws EgovBizException {
		
		for(TbMhEvyeEpdtAncVO vo : list2) {
			if(!StringUtils.isEmpty(vo.getEpdtMngeNo())) {
				int checkNum = aaa01Dao.checkEvyeEpdtAncList(vo);
				if(checkNum > 0) {
					throw processException("fail.common.runError", new String[]{"연차촉진관리번호가 존재 합니다. 연차 촉진계획을 확인해주세요(" + vo.getEmpUniqNo() + ")"});
				}				
			}
		}
		
		for(TbMhEvyeEpdtAncVO vo : list2) {
			String epdtMngeNo = mstBox.getString("epdtMngeNo");
			vo.setAnctDt(DateUtils.getCurrentDate("yyyyMMdd"));
		
			if(StringUtils.isEmpty(vo.getEpdtMngeNo())) {
				vo.setEpdtMngeNo(epdtMngeNo);
				tbMhEvyeEpdtAncDAO.insert(vo);
			}else {
				tbMhEvyeEpdtAncDAO.update(vo);  
			}
		}
		
		
	}
	
    
	
	
	// 삭제 
	@Override
	public String deleteOdbyUspInfo(TbMhOdbyUspVO vo) throws EgovBizException {
		
		//PK 확인 
		TbMhOdbyUspVO chkVO = tbMhOdbyUspDAO.selectByPK(vo);
		if (chkVO == null) {
			throw processException("fail.common.runError", new String[] {"삭제할 데이터가 존재하지 않습니다."});
		}

		// 디테일삭제
		aaa01Dao.deleteodbyUspDtlAll(vo);

		// 마스터삭제
		tbMhOdbyUspDAO.delete(vo);

		return vo.getAplrEmpUniqNo();
	}
	
	
	
	
	
	
/*************************************************************************************************************************/
	
	
	
	
	
	//목록 조회 
	@Override
	public List<QueryResultMap> selectEvyeSituList(ParamVO vo) {
		return aaa01Dao.selectEvyeSituList(vo);
	}
	
	
	
	//정보 조회
	@Override
	public QueryResultMap getOdbyUspInfo(TbMhOdbyUspVO vo) {
		return aaa01Dao.getOdbyUspInfo(vo);
	}
	
	
	
	
	@Override
	public String saveOdbyUspInfo(TbMhOdbyUspVO vo) throws EgovBizException {
		TbMhOdbyUspVO chkVO = tbMhOdbyUspDAO.selectByPK(vo);
		

		try {
			if (chkVO == null) {
				// key 세팅
				String odbyUsePlnNo = mhKeyGenService.generateOdbyUsePlnNo(vo.getAplDt());
				vo.setOdbyUsePlnNo(odbyUsePlnNo);
				tbMhOdbyUspDAO.insert(vo);
			} else {
				tbMhOdbyUspDAO.update(vo);
			}
		} catch (DuplicateKeyException dukey) {
			throw processException("fail.common.runError",	new String[] { "저장", "동일한 연차사용계획번호가 존재합니다." });
		}
		return vo.getOdbyUsePlnNo();
	}
	
	
	
	
	
	@Override
	public List<QueryResultMap> selectOdbyUspList(TbMhOdbyUspVO vo) {
		return aaa01Dao.selectOdbyUspList(vo);
	}
	
	
	

	
	
	
	
	@Override
	public Double calcOdbyUspDys(ParamVO vo) throws EgovBizException {
		return aaa01Dao.calcOdbyUspDys(vo);
	}
	
	
	
	
	@Override
	public String saveOdbyUspDtlList(List<TbMhOdbyUspDtlVO> list) throws EgovBizException {
		String odbyUsePlnNo = list.get(0).getOdbyUsePlnNo();
		for(TbMhOdbyUspDtlVO vo : list){

			// 날짜 기간 계산
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm", Locale.KOREA);
			Date begn;
			try {
				begn = format.parse(vo.getVactBegnDt() + vo.getVactBegnPtm());
				Date clse = format.parse(vo.getVactClseDt() + vo.getVactClsePtm());
				if(begn.compareTo(clse) >= 0){
					throw processException("fail.common.runError", new String[] {"계획기간을 확인하시기 바랍니다."} );
				}
			} catch (ParseException e) {
				throw processException("fail.common.runError", new String[] {"계획기간을 확인하시기 바랍니다."} );
			}

			// begn >= clse

			if(vo.getStateFlag() != null){
				if (vo.getStateFlag().toString().equals(Constants.STATE_FLAG_INSERT)) {
					int srno = mhKeyGenService.generateOdbyUseDtlSeq(vo.getOdbyUsePlnNo());
					vo.setSrno(srno);
					tbMhOdbyUspDtlDAO.insert(vo);
				} else if (vo.getStateFlag().toString().equals(Constants.STATE_FLAG_UPDATE)) {
					tbMhOdbyUspDtlDAO.update(vo);
				}
			}
		}
		
		List<QueryResultMap> uspDtl = aaa01Dao.checkDateOdbyUspDtl(odbyUsePlnNo);
		if(uspDtl.size() > 0) {
			Date begDate = null;
			Date endDate = null;
			Date eqBeDate = null;
			Date eqEnDate = null;
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
			for(int i=0;i<uspDtl.size()-1;i++) {
				String begnDt = (String)uspDtl.get(i).get("vactBegnDt") + (String)uspDtl.get(i).get("vactBegnPtm");
				String clseDt = (String)uspDtl.get(i).get("vactClseDt") + (String)uspDtl.get(i).get("vactClsePtm");
				try {
					begDate = simpleDateFormat.parse(begnDt);
					endDate = simpleDateFormat.parse(clseDt);
					for(int j=i+1;j<uspDtl.size();j++) {
						String begnEqDt = (String)uspDtl.get(j).get("vactBegnDt") + (String)uspDtl.get(j).get("vactBegnPtm");
						String clseEqDt = (String)uspDtl.get(j).get("vactClseDt") + (String)uspDtl.get(j).get("vactClsePtm");
						eqBeDate = simpleDateFormat.parse(begnEqDt);
						eqEnDate = simpleDateFormat.parse(clseEqDt);
				
						if(begDate.before(eqEnDate) && endDate.after(eqBeDate)) {
							throw processException("fail.common.runError", new String[] {"중복", "휴가사용계획기간이 중복 됩니다 기간 : " + uspDtl.get(j).get("vactBegnDt") + "~" + uspDtl.get(j).get("vactClseDt")} );
						}
					}
				} catch (ParseException e) {
					throw processException("fail.common.runError", new String[] {"중복체크", "휴가사용계획기간이 중복체크 오류"} );
				}
			}
		}

		// mst 정보 update
		TbMhOdbyUspVO mstVO = new TbMhOdbyUspVO();
		mstVO.setOdbyUsePlnNo(odbyUsePlnNo);
		int effectRow = aaa01Dao.updateOdbyUsePlnDys(mstVO);
		if(effectRow != 1){
			throw processException("fail.common.runError",new String[] {"데이터 오류"});
		}

		return odbyUsePlnNo;
	}
	
	
	
	
	
	
	@Override
	public String deleteOdbyUspDtlList(List<TbMhOdbyUspDtlKey> list) throws EgovBizException {
		for(TbMhOdbyUspDtlKey vo : list){
			TbMhOdbyUspDtlVO chkVO = tbMhOdbyUspDtlDAO.selectByPK(vo);
			if (chkVO == null) {
				throw processException("fail.common.runError", new String[] {"삭제할 데이터가 존재하지 않습니다."} );

			}
			tbMhOdbyUspDtlDAO.delete(vo);
			TbMhOdbyUspVO tbMhOdbyUspVO = new TbMhOdbyUspVO();
			tbMhOdbyUspVO.setOdbyUsePlnNo(chkVO.getOdbyUsePlnNo());
			aaa01Dao.updateOdbyUsePlnDys(tbMhOdbyUspVO);
		}
		return list.get(0).getOdbyUsePlnNo();
	}
	
	
	
	
	
	
	@Override
	public String approvalReqOdbyUsp(TbMhOdbyUspVO vo) throws EgovBizException {
		TbMhOdbyUspVO chkVO= tbMhOdbyUspDAO.selectByPK(vo);
		if (chkVO == null) {
			throw processException("vaild.empty", new String[] {"승인할 데이터가 존재하지 않습니다."} );
		}  else {
			// 승인요청 시 dtl 테이블 존재 확인
			List<?> chkDtlVO = aaa01Dao.selectOdbyUspList(vo);
			if (chkDtlVO.size() == 0) {
				throw processException("fail.common.serviceRunError", new String[] {"세부계획이 존재하지 않습니다."} );
//				throw processRuntimeException("fail.MFP.serviceRunError", "세부계획이 존재하지 않습니다.");
			}

			if(!chkVO.getPermDivCd().equals("1") && !chkVO.getPermDivCd().equals("4") ){
//				throw processRuntimeException("fail.MFP.serviceRunError", "승인요청 할 수 없는 상태입니다.");
				throw processException("vaild.empty", new String[] {"승인요청 할 수 없는 상태입니다."} );
			}

			int effectRow = aaa01Dao.approvalReqOdbyUsp(vo);
			if(effectRow != 1){
//				throw processRuntimeException("fail.MFP.serviceRunError", "승인요청 시 오류가 발생하였습니다. (다건 승인요청 오류)");
				throw processException("vaild.empty", new String[] {"승인요청 시 오류가 발생하였습니다. (다건 승인요청 오류)"} );
			}

		}

		return vo.getOdbyUsePlnNo();
	}
	
	
	
	
	
	@Override
	public void approvalOdbyUsp(TbMhOdbyUspVO list) throws EgovBizException {
		list.setPermDivCd("3");
		tbMhOdbyUspDAO.update(list);
	}
	

}


