package pierp.app.mis.bizMH.common.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.mis.common.table.model.TbMhBzplVO;
import pierp.app.mis.common.table.model.TbMhCertiSignVO;
import pierp.app.mis.common.table.model.TbMhPicVO;
import pierp.common.mvc.web.ParamVO;
import pierp.common.util.QueryResultMap;

public interface MHPicService {
	
	/**
	 * 사진정보 조회
	 * selectEmpPicByEmpNo	직원번호
	 * @param vo
	 * @return
	 */
	TbMhPicVO selectEmpPicByEmpNo( ParamVO vo );
	
	/**
	 * selectCoOfslImg 사업장 직인사진 조회
	 * @param param
	 * @return
	 */
	TbMhBzplVO selectCoOfslImg(ParamVO param);
	
	/**
	 * selectBizCertiSignAplNo 제증명서 서명사진 조회
	 * @param param
	 * @return
	 */
	TbMhCertiSignVO selectBizCertiSignAplNo(ParamVO param);
	
	/**
	 * saveBizPicByBizplCd 사업장 직인사진 저장( 공통 첨부테이블 정보 읽어서 저장함 )
	 * @param param
	 * @throws EgovBizException
	 */
	void saveBizPicByBizplCd(ParamVO param) throws EgovBizException;
	
	/**
	 * saveEmpPicByCertiSignEmpUniqNo 증명서 서명사진 저장( 공통 첨부테이블 정보 읽어서 저장함 )
	 * @param param
	 * @throws EgovBizException
	 */
	void saveEmpPicByCertiSignEmpUniqNo(ParamVO param) throws EgovBizException;
	
	void uploadPicImage(ParamVO vo, MultipartFile multipartFile) throws EgovBizException;

}


/**
 * Modification Information
 * ------------  ----------  ---------------------
 *   수정일자      수정자    수정내용
 * ------------  ----------  ---------------------
 * 
 */