package pierp.app.mis.bizMH.common.service.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import pierp.app.mis.common.table.model.TbMhBzplVO;
import pierp.app.mis.common.table.model.TbMhCertiSignVO;
import pierp.app.mis.common.table.model.TbMhPicVO;
import pierp.common.base.dao.BaseAbstractMapper;
import pierp.common.mvc.web.ParamVO;
import pierp.common.util.QueryResultMap;

@Repository
public class MHPicDAO extends BaseAbstractMapper {
	
	/**
	 * selectEmpPicByEmpNo 사원번호로 사진정보 조회
	 * @param vo
	 * @return
	 */
	public TbMhPicVO selectEmpPicByEmpNo(ParamVO param) {
		return selectOne( "MHCommon.selectEmpPicByEmpNo", param.getMap() );
	}
	
	public List<QueryResultMap> selectEmpPicList( ParamVO param ) {
		return selectList( "MHCommon.selectEmpPicList", param.getMap() );
	}
	
	public QueryResultMap selectAttachFile(ParamVO param) {
		return selectOne( "MHCommon.selectAttachFile", param.getMap() );
	}
	
	public int insertPicByExamNo(TbMhPicVO vo) {
		return 	update( "MHCommon.insertTbMhPic", vo );
	}
	
	public int updatePicByExamNo(TbMhPicVO vo) {
		return 	update( "MHCommon.updatePicByExamNo", vo );
	}
	
	/**
	 * selectCoOfslImg 사업장 직인사진 조회
	 * @param param
	 * @return
	 */
	public TbMhBzplVO selectCoOfslImg(ParamVO param) {
		return (TbMhBzplVO)selectOne( "MHCommon.selectCoOfslImg", param.getMap() );
	}
	
	/**
	 * selectBizCertiSignAplNo 제증명서 서명사진 조회
	 * @param param
	 * @return
	 */
	public TbMhCertiSignVO selectBizCertiSignAplNo(ParamVO param) {
		return (TbMhCertiSignVO)selectOne( "MHCommon.selectBizCertiSignAplNo", param.getMap() );
	}
	
	/**
	 * updateTbMhBzpl 사업장 직인사진 저장
	 * @param vo
	 * @return
	 */
	public int updateTbMhBzpl(TbMhBzplVO vo) {
		return update( "MHCommon.updateTbMhBzpl", vo );
	}
	
	/**
	 * updateTbMhCertiSign 제증명서 서명사진 저장
	 * @param vo
	 * @return
	 */
	public int updateTbMhCertiSign(TbMhCertiSignVO vo) {
		return update( "MHCommon.updateTbMhCertiSign", vo );
	}
}
