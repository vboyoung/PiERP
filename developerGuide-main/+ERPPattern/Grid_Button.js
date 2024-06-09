/* 
부서검색
그리드 add, delete
그리드 내 첨부파일
*/




/*  그리드 내 버튼 검색 팝업 (부서검색)  */
var cmodels = [
    ,{name:'useDeptNm',    cname:'사용부서',         	width:'100', align:'left',   hidden:false, editable:true,  edittype:'search',
     editoptions:{custom_click: onclick_gridUseDeptNmBtn}   }
];


function onclick_gridUseDeptNmBtn(irow, colnm, event){
    var oldVal = cfn_GetOldValueInGridCell('list', irow, colnm);    // 변경전 사용부서명 셀값 취득

    f2vos.skipAfterSaveCell = true;
    f2vos.gridEditClose();
    f2vos.skipAfterSaveCell = false;

    var selRow = f2vos.getGridParam('selrow');
    var deptNm  = f2vos.getRowData(selRow).useDeptNm;                       // 사용부서명 셀값 취득

    // 사용부서명이 변경되었을 경우
    if(oldVal != deptNm){
        f2vos.setCell(selRow, 'useDeptCd', '');                             // 사용부서코드 Clear
    }
    fn_OpenSearchUseDeptNm(selRow, colnm, deptNm, false);                   // 사용부서 팝업 호출
}


function fn_OpenSearchUseDeptNm(rowId, colnm, colval, autoSelect){
  
    // 부서검색 팝업 호출
    cfn_SearchDeptPop(
        {deptNm     : colval                                                // 사용부서명
        ,autoSelect : autoSelect                                            // 조회시 일치하는데이터가 1개일경우 자동선택여부 false
        ,rowId      : rowId},                                               // 선택한 rowId
        // 부서검색 팝업 으로 부터 취득한 값을 설정
        function(data,rowId){
            f2vos.setCell(rowId, 'useDeptNm', data[0].deptNm);                 // 사용부서명
            f2vos.setCell(rowId, 'useDeptCd', data[0].deptCd);                 // 사용부서코드
            f2vos.setSelection(rowId).focus();
        }
    );
}


/* 그리드 add, delete : 품목추가, 품목삭제 MPB0101P1 */

//품목 추가버튼 클릭
$('#btnAddProduct').click( function(){
    gridCtsDtl.gridAddRow({
        position: 'last'
    }); 
});
    


// 품목 삭제버튼 클릭
$( '#btnDelProduct' ).click( function(){  
pierp.ui.blockBtn('btnDelProduct');

var chkIds = gridCtsDtl.getCheckedIndex();
var chkRow = {};
var flag = 'N';

if(chkIds.length == 0){
    pierp.ui.alert( '선택된 행이 없습니다.' );
    pierp.ui.blockBtn('btnDelProduct', false);//버튼활성화
    return;
}

for(var i = 0; i < chkIds.length; i++) {
    chkRow = gridCtsDtl.getRowData(chkIds[i]);
    var stateFlag = chkRow.stateFlag;
    
    if(stateFlag == 'I'){
        gridCtsDtl.gridDelRow({rowid: chkIds[i]});
        pierp.ui.blockBtn('btnDelProduct', false);//버튼활성화
    } else {
        flag = 'Y';
    }
}

if(flag == 'Y') {
    var ret = pierp.trx.request({
        confirmMesg	: '삭제',
        block : true,
        url : '<c:url value="/ajaxD/MPA0101P1/deleteProduct.do"/>',
        iDSs : [{
                    type	: 'grid',
                    id		: 'ctsReqDtlList',
                    flag 	: "C"
                }],
        callback : function(status, e){
            
            if (status == 'success' && e.status == 'success') {
                fn_setDefaultCtsReqInfoList();
            }
            pierp.ui.blockBtn('btnDelProduct', false);//버튼활성화

        }
    });
    
    // 버튼 활성화
    if(!ret){ pierp.ui.blockBtn('btnDelProduct', false); }
}
});





/* 그리드 내 첨부파일 */

var cmodels = [ 
    , {name:'attchDoctBtn', cname:'첨부'      ,width:' 30', align:'center', hidden:false, editable:false,  formatter:imageFormatter}
    , {name:'attchDoctId',  cname:'파일첨부'  ,  width:'100', align:'center', hidden:true,  editable:false}
    , {name:'attchCnt',     cname:''        , width:' 15', align:'right',  hidden:true,  editable:false}
];


function imageFormatter(cellvalue, options, rowObject) {

    if (options.colModel.name == 'attchDoctBtn'){ // 첨부파일
    	if(options.rowId != null){
	      	return '<span class="btn_basic"><a onclick="callFilePopup('+options.rowId+');"><img id="apvImg" src="${contextPath}/resources/pierp/images/icon_disk.gif" style="cursor:pointer"></a></span>'
    	}
    }
}

//파일첨부 팝업
function imageFormatter(cellvalue, options, rowObject) {
	var gid = options.gid;
	var rowId = options.rowId;
	
	var srno = rowObject.fmlySrno;
	var attchDoctId = (rowObject.attchDoctId) ? rowObject.attchDoctId : rowObject.empUniqNo + srno;
	
	if(attchDoctId) { //파일첨부id 저장되어있으면 사용 
		return '<a onclick="callFilePopup(\''+ gid +'\',\''+ rowId +'\',\''+ attchDoctId + '\',\''+ srno +'\');"><img src="${contextPath}/resources/pierp/images/icon_disk.gif" style="cursor:pointer"></a>';
	} else {
		return '<a onclick="callFilePopup(\''+ gid +'\',\''+ rowId +'\',\''+ rowObject.empUniqNo + srno + '\',\''+ srno +'\');"><img src="${contextPath}/resources/pierp/images/icon_disk.gif" style="cursor:pointer"></a>';
	}
}


