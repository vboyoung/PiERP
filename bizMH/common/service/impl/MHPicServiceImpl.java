package pierp.app.mis.bizMH.common.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.property.EgovPropertyService;
import pierp.app.mis.bizMH.common.service.MHKeyGenService;
import pierp.app.mis.bizMH.common.service.MHPicService;
import pierp.app.mis.common.table.dao.TbMhBzplDAO;
import pierp.app.mis.common.table.dao.TbMhPicDAO;
import pierp.app.mis.common.table.model.TbMhBzplVO;
import pierp.app.mis.common.table.model.TbMhCertiSignVO;
import pierp.app.mis.common.table.model.TbMhPicVO;
import pierp.common.attach.service.FileAttachService;
import pierp.common.attach.service.TbSysAthflVO;
import pierp.common.attach.service.impl.FileAttachDAO;
import pierp.common.cmmn.service.BaseAbstractServiceImpl;
import pierp.common.mvc.web.ParamVO;
import pierp.common.util.FileUtils;


@Service
public class MHPicServiceImpl extends BaseAbstractServiceImpl implements MHPicService {

	
	@Resource(name="sysPropService")
	protected EgovPropertyService sysPropService;
	
	@Autowired
	protected FileAttachService fileAttachService;
	@Autowired private FileAttachDAO fileAttachDAO;
	
	@Autowired private MHKeyGenService	mhKeyGenService;
	
	
	@Autowired protected TbMhPicDAO tbMhPicDAO;
	@Autowired private TbMhBzplDAO tbMhBzplDAO;

	@Autowired private MHPicDAO cmmDAO;
	
	@Override
	public TbMhPicVO selectEmpPicByEmpNo(ParamVO vo) {
		return cmmDAO.selectEmpPicByEmpNo( vo );		
	}
	
	
	@Override
	public void uploadPicImage(ParamVO paramVo, MultipartFile multipartFile) throws EgovBizException{

		String orgFileNm = multipartFile.getOriginalFilename();
		
		byte[] picImg = null;
		try {
			picImg = multipartFile.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		TbMhPicVO vo = new TbMhPicVO();
		vo.setExmNo( paramVo.getString("empNo") );
		vo.setEmpUniqNo( paramVo.getString("empNo") );
		vo.setPicFileNm( orgFileNm );
		vo.setPicImg( picImg );
		vo.setModrId(paramVo.getString("modrId"));
		
		if( 1 != cmmDAO.updatePicByExamNo( vo ) ){
			Long picSrno = mhKeyGenService.generatePicNo();
			vo.setPicSrno(picSrno);
			vo.setGrndDivCd( "1" );
			vo.setRgtrId(paramVo.getString("modrId"));
			cmmDAO.insertPicByExamNo( vo );
		}			
		
	}
	
	/**
	 * selectCoOfslImg 사업장 직인사진 조회
	 * @param param
	 * @return
	 */
	@Override
	public TbMhBzplVO selectCoOfslImg(ParamVO param) {
		return cmmDAO.selectCoOfslImg(param);
	}
	
	/**
	 * selectBizCertiSignAplNo 제증명서 서명사진 조회
	 * @param param
	 * @return
	 */
	@Override
	public TbMhCertiSignVO selectBizCertiSignAplNo(ParamVO param) {
		return cmmDAO.selectBizCertiSignAplNo(param);
	}
	
	/**
	 * saveBizPicByBizplCd 사업장 직인사진 저장( 공통 첨부테이블 정보 읽어서 저장함 )
	 * @param param
	 * @throws EgovBizException
	 */
	@Override
	public void saveBizPicByBizplCd(ParamVO param) throws EgovBizException {
		try {
			TbSysAthflVO fileVo = new TbSysAthflVO();
			fileVo.setProgramId( param.getString("programId") );
			fileVo.setDocId( "BZPL_" + param.getString("bzplCd") );
			List<TbSysAthflVO> fileList = fileAttachDAO.selectAttachFiles(fileVo);
			
			// 파일첨부 데이터 없을 때, 업무데이터 이미지도 초기화
			if( fileList.size() < 1 ) {
				TbMhBzplVO vo = new TbMhBzplVO();
				vo.setBzplCd( param.getString("bzplCd") );
				vo.setBzplNm( param.getString("bzplNm") );
				vo.setCoOfslImg( null );
				vo.setLastChgEmpNo( param.getString("lastChgEmpNo") );
				vo.setLastChgUsrId( param.getString("lastChgUsrId") );
				
				cmmDAO.updateTbMhBzpl(vo);
			} else {
				for( TbSysAthflVO file : fileList ) {
					String fileNm = file.getFilePath() + File.separator + file.getFileName();
					byte[] picImg = FileUtils.readFileToByteArray( new File( fileNm ) );
					
					TbMhBzplVO vo = new TbMhBzplVO();
					vo.setBzplCd( param.getString("bzplCd") );
					vo.setBzplNm( param.getString("bzplNm") );
					vo.setCoOfslImg( picImg );
					vo.setRegEmpNo( param.getString("regEmpNo") );
					vo.setRegUsrId( param.getString("regUsrId") );
					vo.setLastChgEmpNo( param.getString("lastChgEmpNo") );
					vo.setLastChgUsrId( param.getString("lastChgUsrId") );
					
					if( cmmDAO.updateTbMhBzpl(vo) != 1 ) {
						tbMhBzplDAO.insert(vo);
					}
				}
			}
		} catch (IOException e) {
			throw processException("fail.common.serviceRunError", new String[] {"사진저장 실패"});
		}
	}
	
	/**
	 * saveEmpPicByCertiSignEmpUniqNo 증명서 서명사진 저장( 공통 첨부테이블 정보 읽어서 저장함 )
	 * @param param
	 * @throws EgovBizException
	 */
	@Override
	public void saveEmpPicByCertiSignEmpUniqNo(ParamVO param) throws EgovBizException {
		try {
			TbSysAthflVO fileVo = new TbSysAthflVO();
			fileVo.setProgramId( param.getString("programId") );
			fileVo.setDocId( "SIGN_" + param.getString("aplNo") + "_" + param.getString("empUniqNo") );
			List<TbSysAthflVO> fileList = fileAttachDAO.selectAttachFiles(fileVo);
			
			// 파일첨부 데이터 없을 때, 업무데이터 이미지도 초기화
			if( fileList.size() < 1 ) {
				TbMhCertiSignVO vo = new TbMhCertiSignVO();
				vo.setAplNo( param.getString("aplNo") );
				vo.setEmpUniqNo( param.getString("empUniqNo") );
				vo.setSignImg( null );
				vo.setLastChgEmpNo( param.getString("lastChgEmpNo") );
				vo.setLastChgUsrId( param.getString("lastChgUsrId") );
				
				cmmDAO.updateTbMhCertiSign(vo);
			} else {
				for( TbSysAthflVO file : fileList ) {
					String fileNm = file.getFilePath() + File.separator + file.getFileName();
					byte[] picImg = FileUtils.readFileToByteArray( new File( fileNm ) );
					
					TbMhCertiSignVO vo = new TbMhCertiSignVO();
					vo.setAplNo( param.getString("aplNo") );
					vo.setEmpUniqNo( param.getString("empUniqNo") );
					vo.setSignImg( picImg );
					vo.setRegEmpNo( param.getString("regEmpNo") );
					vo.setRegUsrId( param.getString("regUsrId") );
					vo.setLastChgEmpNo( param.getString("lastChgEmpNo") );
					vo.setLastChgUsrId( param.getString("lastChgUsrId") );
					
					if( cmmDAO.updateTbMhCertiSign(vo) != 1 ) {
						throw processException("fail.common.serviceRunError", new String[] {"저장되어있는 직원정보가 없습니다."});
					}
				}
			}
		} catch (IOException e) {
			throw processException("fail.common.serviceRunError", new String[] {"사진저장 실패"});
		}
	}
}