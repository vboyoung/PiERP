/* pierip.globals.fn.js */

cfn_getCodeList(vars)  //잘 안씀.. cmodel에서 설정
editoptions:{value:cfn_makeGridSelectOptions(cfn_getCodeList({comClssCd:'AC005', mngeAtclValu2:'Y', useLike:true}), '99')}


cfn_ajaxCodeList(opts) //ajax요청 코드조회 
//ex : 예산전용등록 전용구분 (callback) MBB0209.jsp


if (status == 'success' && resData.status == 'success') {
    
    /* 셀 선택시 셀렉트 값 조회 후 val */
    var records = $('#voList').getGridParam('records');  // 그리드 전체건수조회   $('#gridid').getGridParam('selrow'); $('#gridid').getGridParam('data');
   
    for(i = 0; i < records; i++){ 

        var bdgtChgDivCd = $('#voList').getCell(i, 'bdgtChgDivCd'); // 해당하는 컬럼값 조회 $('#gridid').getCell(rowid, iCol);  
        var bdgtBdtrDivCd = $('#voList').getCell(i, 'bdgtBdtrDivCd');

        if(bdgtBdtrDivCd != ''){
           
            $('#voList').setCell(i, 'bdgtChgDiv', '1');  //1로 자동세팅 :  지정된 컬럼 값 및 속성 세팅

            var editOpts = $('#voList').getColProp('bdgtChgDiv').editoptions;    //선택된 값 반환 후 코드명칭 변경
            editOpts = $.extend(editOpts, {
                value:pierp.grid.makeSelectOptions(cfn_ajaxCodeList({cid: 'BZ012',
                    })
                  )
            });

            $('#voList').setColProp('bdgtChgDiv', editOpts);  //코드명칭 변경된 값 set

            $('#voList').setCell(i, 'bdgtDiv', bdgtBdtrDivCd);  
        } 

        $('#voList').setSelection(0);  //해당하는 row 선택표시
    }

}


cfn_ButtonBlock(bttnId, mesg) 
pierp.ui.blockBtn('btnDelete', false);


cfn_ButtonUnblock(bttnId)

cfn_SubmitInternal(formId, actionURL, method, params, callback)

cfn_GetOldValueInGridCell(gridId, irow, colNm)

cfn_LocateSelRow(gridId, selRowId, evf)

cfn_SetRadio(objNm, objVl)

cfn_CheckInputKey(event)

cfn_FireEvent(objNm, eh, flag, boxid)

cfn_mergeDownload(arr)

cfn_FileDownload (config) 