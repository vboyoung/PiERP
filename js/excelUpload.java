

/* --------------------------------------------------------------------------------------
* JSP
* -------------------------------------------------------------------------------------- */
    
$('#uploadFrm').ajaxForm({
    type        : 'post',
    url         : '<c:url value="/ajaxS/MYA0201/uploadExcelFile.do"/>',
    dataType    : 'text',
    async       : false,
    beforeSend : function(f, e){
        $('#list').clearGridData();
        $('#xlsUploadNo').val( '' );
        $('#oRtnCd').val( '' );
        $('#oRtnMsg').val( '업로드 처리중입니다......' );
    },
    success     : function (dataStr, textStatus, jqXHR) {
        var data = getJsonData(dataStr);
        if( data.status == 'success' ){
            $('#xlsUploadNo').val( data.objects.xlsUploadNo );
            $('#oRtnCd').val( data.objects.oRtnCd );
            $('#oRtnMsg').val( data.objects.oRtnMsg );
            
            fn_search( data.objects.xlsUploadNo );
            pierp.ui.alert(data.objects.oRtnMsg);
        } else {
            pierp.ui.alert(data.serviceMessage);
        }
    },
    error : function (jqXHR, textStatus, errorThrown) {
    },
    complete : function (jqXHR, textStatus) {
    }
});



/* --------------------------------------------------------------------------------------
* JAVA
* -------------------------------------------------------------------------------------- */

//contorller
@RequestMapping("/ajaxS/MYA0201/uploadExcelFile.do")
public ModelAndView processExcelFile(MultipartHttpServletRequest req, MYA0201FormVO vo) throws Throwable{

    ModelAndView mv = new ModelAndView("common/ajaxResp");
    ResponseDTO dto = new ResponseDTO();  
    MultipartFile file = req.getFile("excelFile");
    
    try {

        Map<String,Object> obj = service.uploadTbMyPymtAcotXlsList(vo, file, (UserInfo) getUserInfo());

        dto.put( "xlsUploadNo",	obj.get("xlsUploadNo").toString() );
        dto.put( "oRtnCd",		obj.get("oRtnCd").toString() );
        dto.put( "oRtnMsg",		obj.get("oRtnMsg").toString() );
        
    }  catch (EgovBizException e) {
        dto = super.createResponseDTO(e);
    }
    
    mv.addObject("AJaxDTO", dto);
    return mv;
}



//ServiceImpl
@Override
public Map<String,Object> uploadTbMyPymtAcotXlsList(MYA0201FormVO param, MultipartFile excelFile, UserInfo usr) throws Throwable {
    
    Map<String,Object> rtn = new HashMap<String,Object>();
    
    
    String aplyYm = param.getYy() + param.getMm();
    String bzplCd = param.getBzplCd();
    
    final String xlsUploadNo = generateXlsUploadNo();
    TbMyPymtAcotXlsVO vo = new TbMyPymtAcotXlsVO();
    vo.setXlsUploadNo( xlsUploadNo );
    vo.setAplyYm( aplyYm );
    
    // 엑셀 파일 파싱
    //final MultipartFile file = param.getExcelFile();
    final MultipartFile file = excelFile;
    final int sheetIdx = 0;

    InputStream istream = null;
    Workbook workbook = null;

    try {
        istream = new BufferedInputStream( file.getInputStream() );
        workbook = WorkbookFactory.create( istream );

        Sheet sheet = workbook.getSheetAt( sheetIdx );

        Iterator<Row> it = sheet.rowIterator();
        while( it.hasNext() ){
            
            Row row = it.next();
            Cell cell = null;
            
            if( isWrokRow(row) ){

                int lineNo = row.getRowNum() + 1;
                vo.setLineNo( (long)lineNo );
                
                // 참여사업ID
                cell = row.getCell( 1, Row.CREATE_NULL_AS_BLANK );
                cell.setCellType( Cell.CELL_TYPE_STRING );
                vo.setPrtpBzId( cell.getStringCellValue().trim() );
                
                // 직렬코드
                cell = row.getCell( 3, Row.CREATE_NULL_AS_BLANK );
                cell.setCellType( Cell.CELL_TYPE_STRING );
                vo.setStfDivCd( cell.getStringCellValue().trim() );
                
                // 지금공제코드
                cell = row.getCell( 5, Row.CREATE_NULL_AS_BLANK );
                cell.setCellType( Cell.CELL_TYPE_STRING );
                vo.setPymtDedtCd( cell.getStringCellValue().trim() );
                
                // 예산사업ID
                cell = row.getCell( 7, Row.CREATE_NULL_AS_BLANK );
                cell.setCellType( Cell.CELL_TYPE_STRING );
                vo.setBdgtBzId( cell.getStringCellValue().trim() );
                
                // 비목코드
                cell = row.getCell( 9, Row.CREATE_NULL_AS_BLANK );
                cell.setCellType( Cell.CELL_TYPE_STRING );
                vo.setIxnsId( cell.getStringCellValue().trim() );
                
                // 세부항목코드
                cell = row.getCell( 11, Row.CREATE_NULL_AS_BLANK );
                cell.setCellType( Cell.CELL_TYPE_STRING );
                vo.setDtalAtclCd( cell.getStringCellValue().trim() );
                
                tbMyPymtAcotXlsDAO.insert( vo );
            }
        }
        
        // DB처리 SP호출
        rtn = mya02Dao.spMya0201Xls( xlsUploadNo, bzplCd, usr.getUsrId() );
        String oRtnCd = rtn.get("oRtnCd").toString();
        if( "E".equals(oRtnCd) ){
            throw processException("fail.common.serviceRunError", new String[] {rtn.get("oRtnMsg").toString()});
        }
        
        rtn.put( "xlsUploadNo", xlsUploadNo );
    } catch(Exception e){
        throw e;
    } finally {
        if( istream != null ){ try { istream.close(); } catch (Exception e2) { ; } }
    }
    return rtn;
}


//DAO SP 호출

public Map<String, Object> spMya0201Xls(String xlsUploadNo, String bzplCd, String usrId) {
		
    Map<String, Object> param = new HashMap<String, Object>();

    param.put( "iXlsUploadNo",	xlsUploadNo );
    param.put( "iBzplCd",		bzplCd );
    param.put( "iUsrId",		usrId );
    param.put( "oRtnCd",		"" );
    param.put( "oRtnMsg",		"" );
    
    selectOne( "MYA02.spMya0201Xls", param );
    
    return param;
}

//SP Mapper.xml

<select id="spMya0201Xls" statementType="CALLABLE" parameterType="map">
/* 예산코드매핑 validation */
{
    call PG_MY_XLS.SP_MYA0201_XLS(
        #{iXlsUploadNo},
        #{iBzplCd},
        #{iUsrId},
        #{oRtnCd, mode=OUT, jdbcType=VARCHAR, javaType=string},
        #{oRtnMsg, mode=OUT, jdbcType=VARCHAR, javaType=string}
    )
}
</select>