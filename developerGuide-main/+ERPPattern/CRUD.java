
/* 
 Controller Code
 Service Code
 SeviceImpl Code
 Dao Code
 Mapper Code
 Procedure 호출
 */


 /* Controller Code */

@Autowired
MAA03Service maa03Service;


//조회 : Form setting
@RequestMapping("/ajaxR/MAA0302/selectAstsMngList.do")
public @ResponseBody Object selectAstsMngList(@InjectSessionInfo ParamVO param, BindingResult br) throws EgovBizException
{
   ResponseDTO resDto = createResponseDTO();
   try {
       QueryResultMap mstBox = maa03Service.selectAstsMngList(param);
       resDto.put("detailBzBox", mstBox);

   } catch (EgovBizException ex) {
       throw ex;
   }

   return resDto;
}

//조회 : Grid setting
@RequestMapping("/ajaxR/MAA0302/selectAstsMngHisList.do")
public @ResponseBody Object selectAstsMngHisList(@InjectSessionInfo ParamVO param, BindingResult br) throws EgovBizException {
        
    ResponseDTO resDto = createResponseDTO();
    try {
        List<QueryResultMap> list = maa03Service.selectAstsMngHisList(param);
        resDto.putAsGrid("maa0302List", list);
    } catch (EgovBizException ex) {
        throw ex;
    }
    return resDto;
}

//저장
@RequestMapping("/ajaxS/MAA0302/saveAstsMngInfo.do")
@Validation(target=TbMaAstsVO.class, form="#detailBzBox", argIdx = 0)
public @ResponseBody Object saveAstsMngInfo(@InjectSessionInfo TbMaAstsVO detailBzBox, BindingResult br) throws EgovBizException {

    ResponseDTO resDto  = null;
    try {
        maa03Service.saveAstsMngInfo(detailBzBox);
        
        resDto = createResponseDTO("success.common.runResult", "자산정보", "저장");
        resDto.put("astsNo", detailBzBox.getAstsNo()); // 처리한 자산번호 세팅
        
    }catch (EgovBizException e) {
        resDto = createResponseDTO(e);
    }
    return resDto;

}


//삭제
@RequestMapping("/ajaxD/MAA0302/deleteAstsMngInfo.do")
public @ResponseBody Object deleteAstsMngInfo(@InjectSessionInfo ParamVO param, BindingResult br) throws EgovBizException {
    
    ResponseDTO resDto = null;
    try {
        maa03Service.deleteAstsMngInfo(param);
        
        resDto = createResponseDTO("success.common.runResult", "자산정보", "삭제");
    }catch (EgovBizException e) {
        resDto = createResponseDTO(e);
    }
    return resDto;
}

 
 /* Service Code */

 //조회 : Form setting
 QueryResultMap selectAstsMngList(ParamVO param) throws EgovBizException;  

 //조회 : Grid setting
 List<QueryResultMap> selectAstsMngHisList(ParamVO param) throws EgovBizException;

 // 저장
 void saveAstsMngInfo(TbMaAstsVO vo) throws EgovBizException;
 void saveAstsBzDtlList(List<MAA0302P1F1VO> list) throws EgovBizException;

 //삭제
 void deleteAstsMngInfo(ParamVO param) throws EgovBizException;




 /* SeviceImpl Code */

 @Autowired MAA03DAO maa03DAO;

//저장, 업데이트처리
@Override
public QueryResultMap selectAstsMngList(ParamVO param) throws EgovBizException {
    return maa03DAO.selectAstsMngList(param);
}

@Override
public List<QueryResultMap> selectAstsMngHisList(ParamVO param) throws EgovBizException {
    return maa03DAO.selectAstsMngHisList(param);
}


@Override
public void saveBdgtUseAuhtDeptList(List<ParamVO> bdgtUseAuhtDeptList) throws EgovBizException {
    
    for(ParamVO vo : bdgtUseAuhtDeptList){

        String audtCnu = "";

        if( Constants.STATE_FLAG_INSERT.equals( vo.getStateFlag() ) ) {
            
           if( mba02DAO.selectByPk(vo) == null ) {

               audtCnu = tableKeyGenService.getNextKey("AUDT_CNU", null, null, null);
               vo.setAudtCnu(audtCnu);

               try{
                   mba02DAO.insertBdgtUseAuhtDeptList(vo);
                }catch(DuplicateKeyException dukey){
                    throw processException("fail.common.runError",new String[]{"예산사용권한 부서","코드 중복값이 존재합니다."});
                }catch(Exception ex){
                    throw ex;
               }
           } //if end

        } else if( Constants.STATE_FLAG_UPDATE.equals( vo.getStateFlag()) ) {

            try{
                mba02DAO.updateBdgtUseAuhtDeptList(vo);
            }catch(DuplicateKeyException dukey){
                throw processException("fail.common.runError",new String[]{"예산사용권한 부서","코드 중복값이 존재합니다."});
            }catch(Exception ex){
                throw ex;
            }
        } //else if end
    } //for end
}

@Override
public void saveAprhCtrl(ParamVO vo) throws Exception{
    if(Constants.STATE_FLAG_INSERT.equals(vo.getString("stateFlag"))){
        sye02DAO.insertAprhCtrl(vo);
    }else if(Constants.STATE_FLAG_UPDATE.equals(vo.getString("stateFlag"))){
        sye02DAO.updateAprhCtrl(vo);
    }
}

//삭제
@Override
public void deleteBdgtReqtAtcl(ParamVO vo)  throws EgovBizException  {
    try{
        mba02DAO.deleteTbMbBdgtReqtAtcl(vo);
    }catch(Exception ex){
        if(DataIntegrityViolationException.class.equals(ex.getClass())){
            throw processException("fail.common.runError",new String[]{"산출근거 삭제",""});
        }else{
            throw ex;
        }
    }				
}

 /* Dao Code */

//한건 조회
public QueryResultMap selectAstsMngList(ParamVO param) {
    return selectOne("MAA03.selectAstsMngList", param.getMap());
}

//리스트 조회
public List<QueryResultMap> selectAstsMngHisList(ParamVO param) {
    return selectList("MAA03.selectAstsMngHisList",param.getMap());
}

//자산번호채번
public String selectAstsMaxNo(Map<String, Object> paramMap) {
    return (String) selectOne("MAA03.selectAstsMaxNo", paramMap);
}


//저장
public void tbMaAstsInsert(ParamVO param) {
    insert("TbMaAsts.insertTbMaAsts", param.getMap());
}

//업데이트
public void updateAstsMngInfo(TbMaAstsVO vo) {
    update("TbMaAsts.updateTbMaAsts", vo);
}

//삭제
public void deleteAstsBzDtlInfo(MAA0302P1F1VO vo, Map<String, Object> paramMap) {
    // 삭제처리
    delete("MAA03.deleteAstsBzDtlInfo", vo);

    // 사업정보 체크
    selectOne("MAA03.callSpAstsBz", paramMap);
}



 
/* Mapper  Code */
<select id="selectAstsMngList" parameterType="map"  resultType="qrmap"> </select>  

<select id="selectAstsMaxNo" parameterType="map" resultType="java.lang.String"> </select>

<insert id="insertAstsBzDtlInfo" parameterType="pierp.app.mis.bizMA.maa.model.MAA0302P1F1VO">   </insert>

<update id="updateAstsBzDtlInfo" parameterType="pierp.app.mis.bizMA.maa.model.MAA0302P1F1VO"></update>

<delete id="deleteAstsBzDtlInfo"  parameterType="pierp.app.mis.bizMA.maa.model.MAA0302P1F1VO">



    
/* Procedure 호출  */

/* 생성 */
//생성 - Controller (callSpWkplncrtn 근무계획 생성)
@Validation(target = MHSearchFormVO.class, isThrow = true, isCheck = true)
@RequestMapping("/ajaxR/MHA0102/callSpWkplncrtn.do")
public @ResponseBody Object callSpWkplncrtn( @InjectSessionInfo ParamVO callBox, BindingResult br) throws Exception {

    try {
        service.callSpWkplncrtn( callBox );
        return createResponseDTO( "success.common.runResult", callBox.getMap().get("yy") + "년도 근무계획이 정상적으로 생성 되었습니다." , "생성" );
        
    } catch (EgovBizException bse) {
        return createResponseDTO( bse );
    }
}

//생성 - Service, SeviceImpl
void callSpWkplncrtn(ParamVO vo)  throws EgovBizException;


@Override
public void callSpWkplncrtn(ParamVO vo)  throws EgovBizException  {
    
    Map<String, Object> map =  mha01Dao.spMhf01Wkplncrtn(vo);
    String oRtnCd = map.get("oRtnCd").toString();
    String oRtnMsg  = "";

    if( !"S".equals(oRtnCd) ){
        oRtnMsg = map.get("oRtnMsg").toString();
        throw processException("fail.common.runError",  new String[] {"프로시저 실행 중 에러가 발생했습니다" , oRtnMsg});
    }
    
}

//생성 - DAO, Mapper
public Map<String, Object> spMhf01Wkplncrtn(ParamVO param) {
        
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("yy", param.getString("yy"));
    map.put("usrId", param.getString("rgtrId"));
    
    selectOne( "MHA01.spMhf01Wkplncrtn", map );
    
    return map;
}

<select id="spMhf01Wkplncrtn" statementType="CALLABLE" parameterType="map">
    {call PG_MH_HR.SP_MHF01_WKPLNCRTN( 
            #{yy, mode=IN, jdbcType=VARCHAR, javaType=string}
        , #{regUsrId, mode=IN, jdbcType=VARCHAR, javaType=string}
        , #{oRtnCd, mode=OUT, jdbcType=VARCHAR, javaType=string}
        , #{oRtnMsg, mode=OUT, jdbcType=VARCHAR, javaType=string})
    }
</select>




/* 리스트 삭제 */ 
//삭제 - Controller (callSpWkplncrtn 근무계획 생성)
@RequestMapping("/ajaxD/MAA0301/deleteAstsIzInfo.do")
public @ResponseBody Object deleteAstsIzInfo(@InjectSessionInfo List<MAA0301F2VO> list, BindingResult br) throws EgovBizException {   

ResponseDTO resDto = null;
try {
        int intIdx = maa03Service.deleteAstsIzInfo(list);
        resDto = createResponseDTO("info.MAA0301.pgMaSpAstsRegt"         // 메세지 Properties
                                        , list.get(intIdx).getRtnMsg()   // 처리결과메세지
                                        , list.get(intIdx).getRtnCd());  // 처리결과코드
}catch (EgovBizException e) {
    resDto = createResponseDTO(e);
}
    return resDto;
}

//삭제 - Service, SeviceImpl (자산확인자산등재 - 등재취소)
int deleteAstsIzInfo(List<MAA0301F2VO> list) throws EgovBizException;

@Override
public int deleteAstsIzInfo(List<MAA0301F2VO> list) throws EgovBizException {
        
    Map<String, Object> paramMap = null;
    MAA0301F2VO vo = null;
    int intIdx = 0;

    for(int i = 0; i < list.size(); i++) {

        vo = list.get(i);

        // 1. paramMap생성
        paramMap = new HashMap<String, Object>();

        paramMap.put("I_ASTS_NO",       vo.getAstsNo());        // 자산번호
        paramMap.put("I_USR_ID",        vo.getLastChgUsrId());  // 사용자ID
        paramMap.put("O_RTN_CD",        "");                    // 처리결과코드(S=정상,E=에러)
        paramMap.put("O_RTN_MSG",       "");                    // 처리결과MSG

        // 2. "자산확인자산등재 - 자산등재내역 삭제" 프로시져호출
        maa03DAO.callSpAstsDelt(paramMap);

        // 3. 처리결과코드를 vo에 세팅한다.
        vo.setRtnCd((String) paramMap.get("O_RTN_CD"));

        // 4. 처리결과MSG를 vo에 세팅한다.
        vo.setRtnMsg((String) paramMap.get("O_RTN_MSG"));

        // 5. 처리결과코드가 S=정상이 아닌 일경우 처리결과코드를 리턴
        if(!"S".equals(paramMap.get("O_RTN_CD"))) {
            return i;
        }
        intIdx = i;
    }
    return intIdx;
}


//삭제- DAO, Mapper
@Override
public void deleteAstsMngInfo(Map<String, Object> paramMap) {
    update("MAA03.callSpAstsDelt",paramMap);
}

//삭제
@Override
public void deleteAstsMngInfo(ParamVO param) throws EgovBizException {

    Map<String, Object> paramMap = new HashMap<String, Object>();
    
    paramMap.put("I_ASTS_NO", param.get("astsNo"));              // 자산번호
    paramMap.put("I_USR_ID",  param.get("usrId")); // 로그인ID
    paramMap.put("O_RTN_CD",  "");                          // 처리결과코드(S=정상,E=에러)
    paramMap.put("O_RTN_MSG", "");                          // 처리결과MSG

    // 2. "자산정보 삭제" 프로시져호출
    maa03DAO.deleteAstsMngInfo(paramMap);

    // 3. 처리결과코드를 vo에 세팅한다.
    param.put("rtnCd", paramMap.get("O_RTN_CD"));

    // 4. 처리결과MSG를 vo에 세팅한다.
    param.put("rtnMsg", paramMap.get("O_RTN_MSG"));
}




<select id="callSpAstsDelt" statementType="CALLABLE" parameterType="map">
    {
        call PG_MA.SP_ASTS_DELT(
            #{I_ASTS_NO},
            #{I_USR_ID},
            #{O_RTN_CD, mode=OUT, jdbcType=VARCHAR, javaType=string},
            #{O_RTN_MSG, mode=OUT, jdbcType=VARCHAR, javaType=string}
        )
    }
</select>   
