package pierp.app.mis.bizMH.mhl.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import pierp.app.mis.bizMH.common.service.MHKeyGenService;
import pierp.common.attach.service.TbSysAthflVO;
import pierp.common.attach.service.impl.FileAttachDAO;
import pierp.common.main.model.IUserInfo;
import pierp.common.mvc.web.ParamVO;
import pierp.common.util.DateUtils;
import pierp.common.util.QueryResultMap;
import pierp.module.fileAttach.service.FileParamVO;
import pierp.module.fileAttach.service.FileUploadService;
import pierp.module.snctn.gw.service.GwReqDocDTO;
import pierp.module.snctn.service.SnctLnBaseDTO;
import pierp.module.snctn.service.SnctnDutyInfo;
import pierp.module.snctn.service.SnctnReqDutyBaseIF;
import pierp.module.snctn.service.impl.SnctnSupplementServiceAbstractImpl;


/**
 *
 * @Class MHL03SnctnServiceImpl
 * @Description
 *
 * @author hangy3ol
 * @since 2022. 10. 12
 * @version
 *
 */
@Service("mhl03SnctnService") // 시스템관리(SYS) -> 권한과메뉴 탭 -> 결재관리 -> 결재업무유형관리[SYH0101] -> 업무유형 정보 -> 결재처리 서비스ID
public class MHL03SnctnServiceImpl extends SnctnSupplementServiceAbstractImpl {

	@Autowired	private	MHL03DAO			mhl03DAO;

	@Autowired	private	MHKeyGenService		mhKeyGenService;

	@Autowired	private FileUploadService	uploadService;
	@Autowired	private FileAttachDAO		fileAttachDAO;
	@Autowired	private FileUploadService	fileUploadService;

	final String SNCTN_DUTY_CD_MISMH00002 = "MISMH00002"; // 업무코드
	final String MISMH00002_SNCTLN_TYPE_ID_001 = "001"; // 결재라인유형ID 1번

	// 결재요청 팝업창에 데이터 세팅1
	@Override
	public void supplementSnctnReqBaseVo(IUserInfo userInfo, SnctnDutyInfo dutyInfo, String dutyDataPks, SnctnReqDutyBaseIF snctnReqDutyBaseInfo) throws EgovBizException {
		/*
		 * 파라미터 개요
		 * userInfo -> 세션 user정보.
		 * dutyInfo -> 시스템관리(SYS) -> 결재업무유형관리[SYH0101] -> 업무유형 정보에 입력(설정)한 정보.
		 * dutyDataPks -> 업무key
		 * snctnReqDutyBaseInfo -> 업무서비스에서 지정할 data interface.
		 */

		QueryResultMap qrMap = null; // 업무key로 SELECT한 결과값을 저장할 map

		if(SNCTN_DUTY_CD_MISMH00002.equals(dutyInfo.getSnctnDutyCd())) {
			qrMap = mhl03DAO.selectCtomAplVO(dutyDataPks);
			snctnReqDutyBaseInfo.setRqstTtl("["+dutyInfo.getSnctnDutyNm()+"] " + qrMap.getString("ctomDivNm") + " 발급 승인요청 건"); // 간편결재 제목
			snctnReqDutyBaseInfo.setRqstCnts(qrMap.getString("isuCnts")); // 간편결재 내용
		}

		// 해당 data가 존재하는 지 check
		if(qrMap == null || qrMap.isEmpty()) {
			throw processException("valid.empty", new String[] {"발급신청번호가 " + dutyDataPks + "인 데이터가 존재하지 않습니다."}); // fail.common.runError, fail.common.serviceRunError 사용 시 에러(supplementSnctnReqBaseVo메소드 에서만 syntax 에러 발생)
		}

		// 해당 data의 상태가 결재요청할 수 있는 상태인지 check.
		if(!"1".equals(qrMap.getString("permDivCd"))) { // permDivCd(승인상태) - 1(작성중), 2(승인요청), 3(승인), 4(반려)
			throw processException("valid.empty", new String[] {"상장발급 승인요청 건 처리에 실패하였습니다."}); // fail.common.runError, fail.common.serviceRunError 사용 시 에러(supplementSnctnReqBaseVo메소드 에서만 syntax 에러 발생)
		}
	}

	// 결재요청 팝업창에 데이터 세팅2 - 결재유형Id, 결재유형 세팅
	@Override
	public SnctLnBaseDTO supplementSnctlnBaseInfo(IUserInfo userInfo, String snctnDutyCd, String dutyDataPks) {
		/*
		 * 파라미터 개요
		 * userInfo -> 세션 user정보.
		 * snctnDutyCd -> 업무코드(결재업무유형관리에서 신규로 등록하는 경우 생성되는 key)
		 * dutyDataPks -> 업무key
		 */

		// 결재선유형이 '001' 하나만 있으므로 바로 setting
		SnctLnBaseDTO dto = null;
		dto = super.createSnctLnBaseDTO(snctnDutyCd, MISMH00002_SNCTLN_TYPE_ID_001); // 결재유형ID, 결재유형명 조회

		return dto;
	}

	// 승인요청 시
	@Override
	public void postRequest(IUserInfo userInfo, String snctnDutyCd, String dutyDataPks, String snctnReqNo, ParamVO options) throws EgovBizException {
		/*
		 * 파라미터 개요
		 * userInfo -> 세션 user정보.
		 * snctnDutyCd -> 업무코드(결재업무유형관리에서 신규로 등록하는 경우 생성되는 key)
		 * dutyDataPks -> 업무key
		 * snctnReqNo -> 결재요청번호(요청 시 새로 생성됨)
		 * options -> 추가로 처리사항할 사항
		 */

		ParamVO vo = new ParamVO();
		int procCnt = 0;

		// 결재요청관련 update
		vo.put("ctomAplNo",		dutyDataPks); // 업무 key
		vo.put("permDivCd",		"2"); // permDivCd(승인상태) - 1(작성중), 2(승인요청), 3(승인), 4(반려)
		vo.put("snctnReqNo",	snctnReqNo); // 결재요청번호
		vo.put("lastChgEmpNo",	userInfo.getEmpNo()); // 최종수정사원번호
		vo.put("lastChgUsrId",	userInfo.getUsrId()); // 최종수정사용자ID

		mhl03DAO.updateCtomAplReqStts(vo);
		procCnt++;

		if( procCnt != 1 ) {
			throw processException("fail.common.serviceRunError", new String[] {"상장 발급신청 간편결재 승인요청  처리 실패."});
		}
	}

	// 승인, 반려 시
	@Override
	public void postProcess(IUserInfo userInfo, String snctnDutyCd, String dutyDataPks, String snctnReqNo, String snctnStatCd, String snctnOpnn, boolean cancelFlag, ParamVO options) throws EgovBizException {
		/*
		 * 파라미터 개요
		 * userInfo -> 세션 user정보.
		 * snctnDutyCd -> 업무코드(결재업무유형관리에서 신규로 등록하는 경우 생성되는 key)
		 * dutyDataPks -> 업무key
		 * snctnReqNo -> 결재요청번호(요청 시 새로 생성됨)
		 * snctnStatCd -> 결재처리코드 - 00(작성중), 01(요청), 02(결재중), 03(반려됨), 04(회수됨), 05(완료)
		 * snctnOpnn -> 반려사유
		 * cancelFlag -> 취소flag
		 * options -> 추가로 처리사항할 사항
		 */

		QueryResultMap qrMap = mhl03DAO.selectCtomAplVO(dutyDataPks);
		String permDivCd = qrMap.getString("permDivCd"); // 승인상태  - 1(작성중), 2(승인요청), 3(승인), 4(반려)
		String permYn = ""; // 승인여부 초기값 - Y, N
		int procCnt = 0;
		String msg = ""; // Exception메세지

		ParamVO ctomAplUpdtVO = new ParamVO();
		if("2".equals(permDivCd) && "05".equals(snctnStatCd)) { // 승인상태가 2(승인요청)이고, 결재요청이 05(완료)인 경우
			if(cancelFlag) {
				permDivCd = "2"; // 승인상태 2(승인요청)

				ctomAplUpdtVO.put("ctomAplNo",		dutyDataPks); // 업무 key -> 조회조건
				ctomAplUpdtVO.put("permDivCd",		permDivCd); // permDivCd(승인상태) - 1(작성중), 2(승인요청), 3(승인), 4(반려)
				ctomAplUpdtVO.put("snctnReqNo",		snctnReqNo); // 결재요청번호
				ctomAplUpdtVO.put("lastChgEmpNo",	userInfo.getEmpNo()); // 최종수정사원번호
				ctomAplUpdtVO.put("lastChgUsrId",	userInfo.getUsrId()); // 최종수정사용자ID

				mhl03DAO.updateCtomAplReqStts(ctomAplUpdtVO);
				procCnt++;

				msg = "승인요청(유형1)";

			} else { // 승인
				permDivCd = "3"; // 승인상태 3(승인)
				permYn = "Y"; // 승인여부 Y(여)

				// 발급번호 생성
				String ctomDivCd = "-" + qrMap.getString("ctomDivCd") + "-"; // 상장구분코드 - 상장(01) / 수료증(02) / 위촉장(03) / 표창장(04) / 임명장(05) / 발령장(06) / 인증서(07)
				String isuNo = "제" + mhKeyGenService.generateIsuNo(ctomDivCd) + "호"; // 발급번호: "제" + "두자리 연도" + "-구분코드두자리(01~06)-" + "순번(000~)"+ "호" -> 예) 제22-01-001호

				String today = DateUtils.getToday().substring(0, 8);

				// 결재요청관련 update
				ctomAplUpdtVO.put("ctomAplNo",		dutyDataPks); // 업무 key -> 조회조건
				ctomAplUpdtVO.put("permDivCd",		permDivCd); // permDivCd(승인상태) - 1(작성중), 2(승인요청), 3(승인), 4(반려)
				ctomAplUpdtVO.put("permYn",			permYn); // permYn(승인여부) - Y, N
				ctomAplUpdtVO.put("prmrEmpNo",		userInfo.getEmpNo()); // 승인자사원번호
				ctomAplUpdtVO.put("permDtm",		today); // 승인일자
				ctomAplUpdtVO.put("gvbRson",		snctnOpnn); // 반려사유
				ctomAplUpdtVO.put("isuNo",			isuNo); // 발급번호
				ctomAplUpdtVO.put("snctnReqNo",		snctnReqNo); // 결재요청번호
				ctomAplUpdtVO.put("lastChgEmpNo",	userInfo.getEmpNo()); // 최종수정사원번호
				ctomAplUpdtVO.put("lastChgUsrId",	userInfo.getUsrId()); // 최종수정사용자ID

				mhl03DAO.updateCtomAplReqStts(ctomAplUpdtVO);
				procCnt++;

				msg = "승인";

				String isuTyCd = qrMap.getString("isuTyCd"); // 발급유형 - 1(상장), 2(표창장), 3(위임장)
				if(isuTyCd.equals("1")) { // 발급유형이 1(상장)인 경우 포상징계테이블(TB_MH_REWA_DISA)에 Insert
					// 포상징계일련번호 생성
					String rewaDisaSrno = mhKeyGenService.generateRewaDisaSrno();
					String ctomAplNo = qrMap.getString("ctomAplNo"); // 상장발급신청번호 얻기

					// 포상징계테이블에 Insert할 데이터 세팅.
					ParamVO rewaDisaIsrtVO = new ParamVO();
					rewaDisaIsrtVO.put("rewaDisaSrno",		rewaDisaSrno); // 포상징계일련번호
					rewaDisaIsrtVO.put("dfnDivCd",			"20"); // 확정구분코드 20(확정)
					rewaDisaIsrtVO.put("dfnDt",				today); // 확정일자를 승인일자로 세팅
					rewaDisaIsrtVO.put("mtrDivCd",			"20"); // 자료구분코드 20(적용)

					rewaDisaIsrtVO.put("rewaDisaNo",		isuNo); // 발급번호를 포상징계번호로 세팅
					rewaDisaIsrtVO.put("rewaDisaDivCd",		"1"); // 포상징계구분코드 1(포상)
					rewaDisaIsrtVO.put("ctomDivCd",			qrMap.getString("ctomDivCd")); // 포상징계종류코드 추가
					rewaDisaIsrtVO.put("rewaCd",			qrMap.getString("rewaCd")); // 포상코드
					rewaDisaIsrtVO.put("inotDivCd",			qrMap.getString("inotDivCd")); // 내외부 구분코드
					rewaDisaIsrtVO.put("rewaDisaDt",		qrMap.getString("isuDt")); // 포상일자를 발급일자로 세팅
					rewaDisaIsrtVO.put("attchDoctId",		rewaDisaSrno); // 첨부문서(파일) ID를 포상징계일련번호로 세팅

					rewaDisaIsrtVO.put("empUniqNo",			qrMap.getString("empUniqNo")); // 직원고유번호
					rewaDisaIsrtVO.put("empNm",				qrMap.getString("empNm")); // 직원이름

					rewaDisaIsrtVO.put("rewrNm",			"진흥원장"); // 포상자명
					rewaDisaIsrtVO.put("rewrCd",			"01"); // 포상자코드 01(진흥원장)
					rewaDisaIsrtVO.put("enfcInstNm",		"창업진흥원"); // 시행기관명
					rewaDisaIsrtVO.put("rewaNm",			qrMap.getString("ctomDivCd")); // 상장종류를 포상명(상훈)으로 세팅
					rewaDisaIsrtVO.put("rewaDisaRson",		qrMap.getString("isuCnts")); // 발급내용을 포상사유로 세팅

					rewaDisaIsrtVO.put("regEmpNo",			userInfo.getEmpNo()); // 등록직원번호
					rewaDisaIsrtVO.put("regUsrId",			userInfo.getUsrId()); // 등록직원 아이디

					rewaDisaIsrtVO.put("ctomAplNo",			ctomAplNo); // 상장발급신청번호

					mhl03DAO.saveCtomToRewa(rewaDisaIsrtVO); // 포상징계테이블에 Insert

					// 첨부파일 포상징계테이블로 복사
					TbSysAthflVO tbSysAthflVO = new TbSysAthflVO(); // 파일복사대상 파라미터 생성
					tbSysAthflVO.setProgramId("MHL0302"); // MHL0302(상장 발급신청) -> 파일을 업로드처리한 모듈ID
					tbSysAthflVO.setDocId(ctomAplNo); // MHL0302(상장 발급신청)의 업무 key값 -> 상장발급신청번호
					List<TbSysAthflVO> selectAttachFiles = fileAttachDAO.selectAttachFiles(tbSysAthflVO); // programId와 docId를 조회 조건으로 첨부파일 목록조회
					List<TbSysAthflVO> listSysAthflVo = new ArrayList<TbSysAthflVO>();

					for(TbSysAthflVO sysAVo : selectAttachFiles) {
						InputStream input = null;
						OutputStream os = null;
						try {
							File file = new File(sysAVo.getFilePath() + sysAVo.getFileName()); // 복사할 파일가져오기
							DiskFileItem fileItem = new DiskFileItem("file", Files.probeContentType(file.toPath()), false, sysAVo.getOrgFileNm(), (int) file.length() , file.getParentFile());

							input = new FileInputStream(file);
							os = fileItem.getOutputStream();
							IOUtils.copy(input, os);
							MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
							Collection<MultipartFile> multiFile = new ArrayList<MultipartFile>();
							multiFile.add(multipartFile);

				 		    String fileId = fileUploadService.tempUpload(multiFile, userInfo);
				 		    sysAVo.setFileId(fileId);
				 		    sysAVo.setFileFlag("I");

				 		    listSysAthflVo.add(sysAVo);

						} catch (IOException e) {
							throw processException("fail.common.runError", new String[] {"저장", "포상등록 첨부파일 오류"});

						} finally {
							try {
								if(input != null) {
									input.close();
								}
								if(os != null) {
									os.close();
								}
							} catch (IOException e) {
								throw processException("fail.common.runError", new String[] {"저장", "포상등록 첨부파일 오류"});
							}
						}
					}

					if(selectAttachFiles.size() > 0) {
						FileParamVO fileParamVO = new FileParamVO();
						fileParamVO.setFiles(listSysAthflVo);
						fileParamVO.setProgramId("MHL0102"); // MHL0102(포상등록)에 첨부파일 세팅
						fileParamVO.setDocId(rewaDisaSrno); // MHL0102(포상등록)의 업무 key값 -> 포상징계일련번호
						uploadService.updateFileInfo(fileParamVO);
					}
				}
			}

		} else if("2".equals(permDivCd) && "04".equals(snctnStatCd)) { // 승인상태가 2(승인요청)이고, 결재요청이 04(회수됨)인 경우
			permDivCd = "4"; // 승인상태 4(반려)
			permYn = "N"; // 승인여부 N(부)

			ctomAplUpdtVO.put("ctomAplNo",		dutyDataPks); // 업무 key -> 조회조건
			ctomAplUpdtVO.put("permDivCd",		permDivCd); // permDivCd(승인상태) - 1(작성중), 2(승인요청), 3(승인), 4(반려)
			ctomAplUpdtVO.put("permYn",			permYn); // permYn(승인여부) - Y, N
			ctomAplUpdtVO.put("prmrEmpNo",		null); // 승인자사원번호
			ctomAplUpdtVO.put("permDtm",		null); // 승인일자
			ctomAplUpdtVO.put("gvbRson",		snctnOpnn); // 반려사유
			ctomAplUpdtVO.put("snctnReqNo",		snctnReqNo); // 결재요청번호
			ctomAplUpdtVO.put("lastChgEmpNo",	userInfo.getEmpNo()); // 최종수정사원번호
			ctomAplUpdtVO.put("lastChgUsrId",	userInfo.getUsrId()); // 최종수정사용자ID

			mhl03DAO.updateCtomAplReqStts(ctomAplUpdtVO);
			procCnt++;

			msg = "반려(회수)";

		} else if("2".equals(permDivCd) && "03".equals(snctnStatCd)) { // 승인상태가 2(승인요청)이고, 결재요청이 03(반려됨)인 경우
			permDivCd = "4"; // 승인상태 4(반려)
			permYn = "N"; // 승인여부 N(부)

			ctomAplUpdtVO.put("ctomAplNo",		dutyDataPks); // 업무 key -> 조회조건
			ctomAplUpdtVO.put("permDivCd",		permDivCd); // permDivCd(승인상태) - 1(작성중), 2(승인요청), 3(승인), 4(반려)
			ctomAplUpdtVO.put("permYn",			permYn); // permYn(승인여부) - Y, N
			ctomAplUpdtVO.put("prmrEmpNo",		null); // 승인자사원번호
			ctomAplUpdtVO.put("permDtm",		null); // 승인일자
			ctomAplUpdtVO.put("gvbRson",		snctnOpnn); // 반려사유
			ctomAplUpdtVO.put("snctnReqNo",		snctnReqNo); // 결재요청번호
			ctomAplUpdtVO.put("lastChgEmpNo",	userInfo.getEmpNo()); // 최종수정사원번호
			ctomAplUpdtVO.put("lastChgUsrId",	userInfo.getUsrId()); // 최종수정사용자ID

			mhl03DAO.updateCtomAplReqStts(ctomAplUpdtVO);
			procCnt++;

			msg = "반려";

		} else if("2".equals(permDivCd) && "02".equals(snctnStatCd)) { // 승인상태가 2(승인요청)이고, 결재요청이 02(결재중)인 경우
			permDivCd = "2"; // 승인상태 2(승인요청)

			ctomAplUpdtVO.put("ctomAplNo",		dutyDataPks); // 업무 key -> 조회조건
			ctomAplUpdtVO.put("permDivCd",		permDivCd); // permDivCd(승인상태) - 1(작성중), 2(승인요청), 3(승인), 4(반려)
			ctomAplUpdtVO.put("prmrEmpNo",		null); // 승인자사원번호
			ctomAplUpdtVO.put("permDtm",		null); // 승인일자
			ctomAplUpdtVO.put("snctnReqNo",		snctnReqNo); // 결재요청번호
			ctomAplUpdtVO.put("lastChgEmpNo",	userInfo.getEmpNo()); // 최종수정사원번호
			ctomAplUpdtVO.put("lastChgUsrId",	userInfo.getUsrId()); // 최종수정사용자ID

			mhl03DAO.updateCtomAplReqStts(ctomAplUpdtVO);
			procCnt++;
			msg = "승인요청(유형2)"; // 해당 결재권자는 승인처리 했으나, 결재라인 상 다른 결재권자의 승인 대기 중인 상태.
		}

		if( procCnt != 1 ) {
			throw processException("fail.common.serviceRunError", new String[] {"상장 발급신청 간편결재 " + msg + "처리 실패."});
		}
	}

	// 전자결재
	@Override
	public GwReqDocDTO makeGwReqDocData(IUserInfo userInfo, String snctnDutyCd, String dutyDataPks, String snctnReqNo)
			throws EgovBizException {
		// TODO Auto-generated method stub
		return null;
	}

}