
var uploader = null;
var _CURRENT_ROW = -1; 

$(document).ready(function() {

 	var programId = '${selMdulVo.mdulId}';
 	uploader = new pierp.GridFileUploader('fileGrid', {
 	    params: {
 	      programId: programId,
 	    },
 	    multiple: true,              // 추가버튼클릭시 여러개 선택가능여부 default: true
 	    height: 50,                 // 그리드 height default : 150
 	    maxFileSize: 1024*1024*500,  // 파일크기제한 ( KB*MB*size ) default : 31MB
        maxFileCount: 10,            // upload파일갯수제한 default : 10
 	    seqUpdatable: false          // 순서 column 사용여부. default: false
 	});


	var cmodels = [
		 {name:'stateFlag',		cname : '상태',                width:' 30',  align:'center', hidden:true,	editable:false}
		,{name:'fwpAplNo',		cname : '신청번호',            width:' 80',  align:'center', hidden:true,	editable:false}
		,{name:'aplDivCd',		cname : '신청구분',            width:' 80',  align:'center', hidden:false,	editable:false,  edittype:'select', editoptions:{value:<pierp:gs cid="HR179"/>}}
		,{name:'aplDt',			cname : '신청일자',            width:' 80',  align:'center', hidden:false,	editable:false,  edittype:'date'}
		,{name:'empNm',			cname : '유연근무자',          width:' 80',  align:'center', hidden:false,	editable:false}
                                
		,{name:'mainDuti',		cname : '주요업무',            width:'120',  align:'center', hidden:false,	editable:false}
		,{name:'aplEmpNm',		cname : '신청자',              width:' 80',  align:'center', hidden:false,	editable:false}
		,{name:'wrkBegnDt',		cname : '시작일자',            width:' 80',  align:'center', hidden:false,	editable:false,  edittype:'date'}
		,{name:'wrkClseDt',		cname : '종료일자',            width:' 80',  align:'center', hidden:false,	editable:false,  edittype:'date'}
                                
		,{name:'fwpRsonCd',		cname : '사유',                width:'158',  align:'left'  , hidden:false,	editable:true,  edittype:'select', editoptions:{value:<pierp:gs cid="HR247"/>}}
		,{name:'fwpDtlsRson',	cname : '사유(상세)',          width:'150',  align:'left'  , hidden:false,	editable:false}
		,{name:'eltrAppr',		cname : '전자결재',            width:' 58',  align:'center', hidden:false,	editable:false, formatter:btnFormatter}
		,{name:'rqapNo',		cname : '결재문서번호',        width:'110',  align:'center', hidden:true,	editable:false}
		,{name:'appStatDivCd',	cname : '결재상태',            width:' 80',  align:'center', hidden:false,	editable:true,  edittype:'select', editoptions:{value:<pierp:gs cid="AC901"/>}}

		,{name:'bfFwpAplNo',	cname : '기 신청번호',         width:' 80',  align:'center', hidden:false,	editable:false}
		,{name:'delYn',			cname : '자료상태',            width:' 80',  align:'center', hidden:false,	editable:false}

		,{name:"monBegnPtm",    cname : '월요일시작시간',      width:' 40',  align:'center', hidden:true,	editable:false,  edittype:'text'}
		,{name:"monClsePtm",    cname : '월요일종료시간',      width:' 40',  align:'center', hidden:true,	editable:false,  edittype:'text'}
		,{name:"monWrkPtm",     cname : '월요일근무시간',      width:' 40',  align:'center', hidden:true,	editable:false,  edittype:'text'}
	];


    
    
    // getWriteProp, getReadProp  @extend bizAA 공통코드
    var prop = $.extend( jsAA.gProp.getWriteProp(), {
        height      : '200',
        multiselect : true,
        onSelectRow : function( rowid, status ) {

        	if (rowid == _CURRENT_ROW) return; 
        	_CURRENT_ROW = rowid;
        	
        	var rowData = $('#list').getRowData(rowid);
        	if(rowData.stateFlag == 'I') {
        		$('#bfList').clearGridData();
        	} else {
        		fn_selectBfDeptList(rowData);
        	}
        	
        	if($('#dptList')[0].p.cellEdit == true) {
        		fn_listButtonAuth(rowData);
        	}
        },
        validEditCell : function(rowid, cellname){
            //소스 확인필요
            var rowData = $('#list').getRowData(rowid);
            var result = true;
            ( rowData.stateFlag == "I" ||  cellname == 'aplyDt' ) ?  result : !result;
             return result;
         },
    





    });

    pierp.grid.create( '#list', cmodels, prop );



    function btnFormatter(cellvalue, options) {
        return '<input type="button" value="문서보기" onclick="fn_DoctView(' + options.rowid + ');" style="cursor:pointer">';
    }
    
    
    /* --------------------------------------------------------------------------------------
    * Button Event
    * -------------------------------------------------------------------------------------- */



	$('#btnDeptSplitRow').click(function() {
		var selRow = $('#dptList').getGridParam('selrow');
		if( !selRow ) {
			pierp.ui.alert('부서를 선택하세요.');
			return;
		}
		
		var rowData = $('#dptList').getRowData(selRow);
		var addData = {
				deptYn				: "N",
				deptChgDivCd		: "2",
				chgSrno				: rowData.chgSrno,
				upprDeptCd			: rowData.upprDeptCd,
				upprDeptNm			: rowData.upprDeptNm,
				orgzDivCd			: rowData.orgzDivCd,
				orgzLvCd			: rowData.orgzLvCd,
				cntrCd				: rowData.cntrCd,
				orgzRorgLinkDivCd	: "I"
		};
		
		$('#dptList').gridAddRow({
			editmode : true,
			position : 'after',
			initdata : addData
		});
		
		if( jsAA.status.isChecked ) {
			var selRow = $('#dptList').getGridParam('selrow');
			cfn_UnFilterGrid('dptList');
			cfn_FilterGrid('dptList', {
				filters			: "",
				searchField		: "deptChgDivCd",
				searchOper		: "ne",
				searchString	: "0"
			});
			
			$('#dptList').setSelection(selRow);
		}
	});
 
    

}); //documentReady END




/* --------------------------------------------------------------------------------------
 * Custom Function
 * -------------------------------------------------------------------------------------- */







// MstInfo ajaxR
function fn_selectMstInfo(fwpAplNo) {

	var rtnData = {};

    pierp.trx.request({
	    async : false,
	    block : false,
	    validate : true,
		cfrmMsgConvertor: function(gridSmmry, defMsg) {
			return null;
		},
		params : {fwpAplNo: fwpAplNo},
	    url     : '<c:url value="/ajaxR/MHK1001/selectChoiceFwpInfo.do"/>',
  	    callback: function(status, data){
        			if (jsMH.cfn.isAjaxTransactionResSuccess(data)) {
        				rtnData = data.objects['mstBox'];
        			}
          		}
      });
	return rtnData;
}


