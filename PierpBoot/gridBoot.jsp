<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
File Name   : gridBoot.jsp
Description : 
Modification Information
수정일      수정자            수정 내용
-------    --------   ---------------------------
author : 
since : 2024.01.01
--%>
<html>
<head>
<title>${selMdulVo.mdulNm}</title>
<script type="text/javascript" src="${contextPath}/js/mis/common/mis_common.js"></script>
<script type="text/javascript" src="${contextPath}/js/mis/bizMH/bizMH.js"></script>
<script type="text/javascript">



const uploader = null;
const _CURRENT_ROW = -1; 

$(document).ready(function() {

 	const programId = '${selMdulVo.mdulId}';
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


	const cmodels = [
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
    const prop = $.extend( jsAA.gProp.getWriteProp(), {
        height      : '200',
        multiselect : true,
        onSelectRow : function( rowid, status ) {

        	if (rowid == _CURRENT_ROW) return; 
        	_CURRENT_ROW = rowid;
        	
        	const rowData = $('#list').getRowData(rowid);
        	if(rowData.stateFlag == 'I') {
        		$('#bfList').clearGridData();
        	} else {
        		fn_selectBfDeptList(rowData);
        	}
        	
        	if($('#dptList')[0].p.cellEdit == true) { 
        		fn_listButtonAuth(rowData);
        	}
        },
		loadComplete : function(){
            const editOpts = $('#voList').getColProp('bdgtDiv').editoptions;
            console.log(editOpts, 'editOpts');
            
            editOpts = $.extend(editOpts, {
            	value:pierp.grid.makeSelectOptions(cfn_ajaxCodeList({sid: 'MB.sb_selectBdgtChgDivCd',}),'')
            });

            $('#voList').setColProp('bdgtDiv', editOpts);
        },
        validEditCell : function(rowid, cellname){

			const rowData = $('#dptList').getRowData( rowid );
    		// 변경무 or 폐지일 경우, 수정불가
    		if( cellname != 'deptChgDivCd' && (rowData.deptChgDivCd == '0' || rowData.deptChgDivCd == '5') )
    			return false;
    		// 부서코드는 신규일 경우만 수정가능
    		if( cellname == 'deptCd' && rowData.stateFlag != 'I' )
    			return false;
    		return true;
         },
		 afterSaveCell: function( rowid, cellname, value, iRow, iCol ) {
   
    	}
    	
         
    });

    pierp.grid.create( '#list', cmodels, prop );



    function btnFormatter(cellvalue, options) {
        return '<input type="button" value="문서보기" onclick="fn_DoctView(' + options.rowid + ');" style="cursor:pointer">';
    }
    

    
    
    
    
    
    /* --------------------------------------------------------------------------------------
    * Button Event
    * -------------------------------------------------------------------------------------- */
    
    
    
    // ======================= initailize ========================== //
    
    $('#btnInit').click( function( event ) {
        	
    	_CURRENT_ROW = -1;
    	$('#viewChg').attr('checked', false);
    	cfn_UnFilterGrid( 'dptList' ); //필터링 해제
    	
    	$('#srchBox').resetForm();
    	$('#mstBox').resetForm();
    	$('#dptList').clearGridData();
    	$('#bfList').clearGridData();
    	
    	const initData = { };
    	$('#srchBox').setFormData( initData );
    	
    	btnMode.init();
    });


    // ======================= 조회, 저장, 삭제 ========================== //
    
    // 조회
    $('#btnSearch').click(function( event, args ) {
    		
    	_CURRENT_ROW = -1;
    	$('#viewChg').attr('checked', false);
    	cfn_UnFilterGrid('dptList');
    	
    	pierp.trx.request({		
    		async : false,
    		url	: '<c:url value="/ajaxR/MHA0203/selectOrgzChgDeptList.do"/>',
    		iDSs : [
    			{type:'form', id:'srchBox', name:'param'}
    		],	
    		  oDSs : [
    			  {type:'form', id:'mstBox'}
    			 ,{type:'grid', id:'dptList'}
    		  ],
    		  callback: function(status, resData){
    			  if( jsAA.cfn.isAjaxTransactionResSuccess(resData) ){
    				  const mstBox = resData.objects.mstBox;
    				  
    				  if( mstBox.aplyDt <= '${today}' || mstBox.btchDealDivCd == '20' ) {
    					  if(mstBox.dfnYn == 'Y') {
    						  btnMode.readonly();
    					  } else {
    						  btnMode.bfUpdate(mstBox);
    					  }
    				  } else {
    					  if(mstBox.dfnYn == 'Y') {
    						  btnMode.readonly();
    						  $('#btnUnDfn').disableObj( false );
    						 $('#btnOrgzChg').disableObj( false );
    					  } else {
    						  btnMode.update(mstBox);
    					  }
    				  }
    				  
    				jsAA.cfn.restoreGridSelection( '#dptList', args );
    			}
    		}
    	});
    });
    
    // 저장
    $( '#btnSave' ).click( function() {
    	const chgSrno = $('#mstBox [name=chgSrno]').val();
    	if(chgSrno == '') {
    		pierp.ui.alert('저장할 데이터가 없습니다.');
    		return;
    	}
    
    	pierp.trx.request({
    		url	: '<c:url value="/ajaxS/MHA0203/saveOrgzChgDeptList.do"/>',
    		iDSs : [
    			{type:'form', id:'mstBox'},
    			{type:'grid', id:'dptList', flag:'I|U'}
    		],
    		cfrmMsgConvertor : function(gridSmmry, confirmMesg) {
    			return "저장 하시겠습니까?";
    		},
    		callback: function(status, resData) {
    			if( jsAA.cfn.isAjaxTransactionResSuccess(resData) ) {
    				$('#btnSearch').trigger( 'click' );
    			}
    		}
    	});
    });


   



    // 삭제
    $('#btnDelete').click(function() {
    	if($('#mstBox [name=dfnYn]').val() == 'Y') {
    		pierp.ui.alert('이미 확정되어 있는 데이터 입니다.');
    		return;
    	}
    	
    	const chgSrno = $('#mstBox [name=chgSrno]').val();
    	if(chgSrno == '') {
    		pierp.ui.alert('삭제할 데이터가 없습니다.');
    		return;
    	}
    	
    	pierp.trx.request({
    		url	: '<c:url value="/ajaxD/MHA0203/deleteAllOrgzChg.do"/>',
    		iDSs : [
    			{type:'form', id:'mstBox'}
    		],
    		validator : function(data) {
    			const dfnYn = data.dfnYn;
    			if(dfnYn == 'Y') {
    				pierp.ui.alert('이미 확정 처리된 데이터입니다.');
    				return false;
    			}
    			
    			return true;
    		},
    		cfrmMsgConvertor : function(gridSmmry, confirmMesg) {
    			return "삭제 하시겠습니까?";
    		},
    	    callback: function(status, resData){
    		  if( jsAA.cfn.isAjaxTransactionResSuccess(resData) ) {
    			  
    			  pierp.ui.component.ajaxLoadSelect($('#srchBox [name="chgSrno"]'), {
    				  sid : 'MH.sb_selectDeptChgCode'
    			  }).done(function() {
    				  $('#btnInit').trigger( 'click' );
    			  });
    		  }
    	    }
    	});
    });

    
    
    
    
    //등록
    $('#btnComplete').click(function() {
    	pierp.trx.request({
    		url	: '<c:url value="/ajaxP/doneIntrevalDeptIndcRegt.do"/>',
    		iDSs : [
    			{type:'form', id:'indcBox', name:'param'}
    		],
    		validator : function( data ) {
    			
    			const param = data.param;
    			if( param.intrEvalPrgsStatNm == '' ) {
    				pierp.ui.alert( '다시 시도해주시기 바랍니다.' );
    				return false;
    			}
    			
    			//footerData('get')
    			if( $('#mesuList').footerData('get').adwtMrt != 40 ) {
    				pierp.ui.alert( '다시 시도해주시기 바랍니다.' );
    				return false;
    			}
    			
    			if( $('#nmesuList').footerData('get').adwtMrt != 40 ) {
    				pierp.ui.alert( '다시 시도해주시기 바랍니다.' );
    				return false;
    			}
    			
    			return true;
    		},
    		cfrmMsgConvertor : function(gridSmmry, confirmMesg) {
    		    const deptNm = $('#indcBox [name=deptNm]').val();
    			return '[' + deptNm + '] 등록완료 하시겠습니까?';
    		},
    		callback: function(status, resData) {
				if(jsAA.cfn.isAjaxTransactionResSuccess(resData)) {
					$('#btnSearch').trigger( 'click' );
				}
    		}
    	});
    });







    
    /* --------------------------------------------------------------------------------------
    * Grid Header, Footer Setting
    * -------------------------------------------------------------------------------------- */
    
    function fn_gridLoadComplete( gridDiv ) {
    
    	const gridObj = $('#'+gridDiv+'List');
    
    	// 그리드 footer 세팅
    	gridObj.footerData( 'set', {
    		evalGoalNm : '합계',
    		adwtMrt : gridObj.getCol( 'adwtMrt', false, 'sum' )
    	});
    
    	jsSV.cfn.disableForm( gridDiv+'DtlBox', true );	//폼 비활성화
    }
    




    /* --------------------------------------------------------------------------------------
    * EXCEL Event
    * -------------------------------------------------------------------------------------- */
    
    //Excel Upload
    $('#btnExcelUpload').click( function() {
        const options = {
            procId		: 'AAS102P2',
            callback	: function(data) {
                
                const excelData = data.rows;	// 엑셀 입력 데이터 목록
                excelData.forEach(function(edata) {
                    edata.stateFlag = 'I';	// 상태값
                });
                
                $('#list').setGridData( data );
            }
        };
        
        cfn_OpenExcelUpload( options );
    });
    
    
    

    /* --------------------------------------------------------------------------------------
    * Grid Add ROW, Delete ROW Event
    * -------------------------------------------------------------------------------------- */

    $('#btnAdd').click(function() {
    		
        $('#list').setSelection(null);
        
        const comuActyYy = $('#srchBox [name=comuActyYy]').val();
        const comuActyHalfyyCd = $('#srchBox [name=comuActyHalfyyCd]').val();
        const stateFlag = $('#dtlBox [name=stateFlag]').val();
        const param = {
            stateFlag : 'I',
            comuActyYy : comuActyYy,
            comuActyHalfyyCd : comuActyHalfyyCd,
            milgOffrYn : 'Y'
        }
        if(stateFlag == 'I' || stateFlag == 'U'){
            const message = '수정중인 내용을 삭제하시겠습니까?';
               
            pierp.ui.confirm(message).then(function(btn){
               if(btn == 'yes') {
                   
                $('#dtlBox').resetForm();
                   
                $('#dtlBox input').disableObj(false);
                $('#dtlBox textarea').disableObj(false);
                $('#dtlBox select').disableObj(false);
                   
                $('#dtlBox').setFormData(param);
                $('#btnSave').disableObj(false);
                $('#btnDelete').disableObj(false);
               }
            })
        }else{
    
            $('#dtlBox').resetForm();
            $('#dtlBox input').disableObj(false);
            $('#dtlBox textarea').disableObj(false);
            $('#dtlBox select').disableObj(false);
            
            $('#dtlBox').setFormData(param);
            $('#btnSave').disableObj(false);
            $('#btnDelete').disableObj(false);
        }
    });


    $('#btnAddRow').click(function() {
    	$('#list').gridAddRow({
    		position : 'last',
    		initdata : [{
    			stateFlag				: 'I',
    			csmrStsfDegEvalBegnYy	: $('#srchBox [name=srchYy]').val(),
    			csmrStsfDegEvalYy4		: $('#srchBox [name=srchYy]').val(),
    			checkBoxYn4				: 'Y'
    		}]
    	});
    	
    	// 행추가된 checkBoxYn4 컬럼 비활성화
    	const selrow = $('#list').getGridParam( 'selrow' );
    	const ind = $('#list')[0].rows.namedItem(selrow);
    	const pos = $('#list').getGridParam('colModel').findIndex(function(item, index) { return item.name == 'checkBoxYn4'; });
    	$("td:eq(" + pos + ") [type=checkbox]", ind).disableObj(true);
    });
    	

    $('#btnDelRow').click(function() {
    	$('#list').gridDelRow({
    		rowid : $('#list').getGridParam( 'selrow' )
    	});
    });
    




    
    /* --------------------------------------------------------------------------------------
    * imgage Buuton Event
    * -------------------------------------------------------------------------------------- */
    
    function imageFormatter(cellvalue, options, rowObject) {
    	if(options.colModel.name == 'btnEvalAtcl') {	
    		if(options.rowId != null) {
    			return '<span class="btn_basic"><a onclick="openPopup0102P1('+ options.gid + ', ' + options.rowId+');" style="text-decoration:unset;"><span>항목선택</span></a></span>';
    		}
    	}
    }
    
    
    function openPopupSVA0102P1( grid, rowid ) {
    
    	const rowData = $(grid).getRowData( rowid );
    
    	const param = {
    		evalYy			: rowData.evalYy,
    		evalIndcDivCd	: '02',
    		evalFmlaCd		: rowData.evalFmlaCd,
    		otEvalIndcNm	: rowData.otEvalIndcNm
    	};
    
    	jsSV.popup.SVA0102P1( param, function( retData ) {
    		$(grid).setCell( rowid, 'evalYy',	 retData.evalYy );
    		$(grid).setCell( rowid, 'evalDgr',	 retData.evalDgr );
    		$(grid).setCell( rowid, 'evalGoalCd', retData.evalGoalCd );
    		$(grid).setCell( rowid, 'evalGoalNm', retData.evalGoalNm );
    		$(grid).setCell( rowid, 'evalDrtnCd', retData.evalDrtnCd );
    		$(grid).setCell( rowid, 'evalDrtnNm', retData.evalDrtnNm );
    		$(grid).setCell( rowid, 'evalAtclCd', retData.evalAtclCd );
    		$(grid).setCell( rowid, 'evalAtclNm', retData.evalAtclNm );
    	});
    }




    function imageFormatter(cellvalue, options, rowObject) {
    	if (options.colModel.name == 'attchDoctBtn') { // 첨부파일
    		if (options.rowId != null) {
    			return '<span class="btn_basic"><a onclick="cfnFilePopup('
    					+ options.rowId
    					+ ');"><img id="apvImg" src="${contextPath}/resources/pierp/images/icon_disk.gif" style="cursor:pointer"></a></span>'
    		}
    	}
    }


    function cfnFilePopup(rowId) {
    	const attchDoctId = $('#mstBox [name=attchDoctId]').val();
    	const permDivCd = $('#mstBox [name=permDivCd]').getSelectData();
    
    	// 요청건만 파일첨부 가능함
    	const uploadable = 'N';
    	if (permDivCd != '' && (permDivCd == '1' || permDivCd == '4')) {
    		uploadable = 'Y';
    	}
    
    	const data = $('#list').getRowData(rowId);
    
    	const pId = "MHK5101";
    
    	const docId = data.attchDoctId;
    	const lvbcAplNo = data.lvbcAplNo;
    
    	$('#attachFile [name=attchDoctId]').val(docId);
    	$('#attachFile [name=lvbcAplNo]').val(lvbcAplNo);
    
    	// 첨부파일 팝업 호출
    	const fileUploadPop = cfn_OpenFileAttach({
    		programId : pId,
    		docId : docId,
    		readonly : true,
    		multiple : false,
    		maxFileCount : 5,
    	});
    }
    


    /* --------------------------------------------------------------------------------------
    * keyboard Event
    * -------------------------------------------------------------------------------------- */
    
    $("input[name=yy]").keyup(function() { // 키 코드를 체크하여 년도조절
    	var tempYear = document.getElementsByName('yy')[0].value;
    	if(tempYear.length>3){
    		var keycode = event.keyCode; //현재 클릭한 키코드를 받는다.
    		if(keycode==38){ // 방향키 위쪽을 누른다.
    			document.getElementsByName('yy')[0].value = Number(document.getElementsByName('yy')[0].value)+1;
    		}else if(keycode==40){   //방향키 아래쪽을 누른다.
    			document.getElementsByName('yy')[0].value = Number(document.getElementsByName('yy')[0].value)-1;
    		}
    	}
    });

   // Enter키 검색
   $('#srchBox').keydown(function(event) {
	if(event.keyCode == 13) {
		
		if(event.target.name == 'aplEmpNm' || event.target.name == 'aplDeptNm')
			return false;
		else
			$('#btnSearch').trigger('click');
    	}
    });
   
       
    /* --------------------------------------------------------------------------------------
    * initailzie Event
    * -------------------------------------------------------------------------------------- */
    $( '#btnInit' ).trigger( 'click' );
    $( '#btnSearch' ).trigger( 'click' );

}); //documentReady END




/* --------------------------------------------------------------------------------------
 * Custom Function
 * -------------------------------------------------------------------------------------- */


// search
function fn_selectMstInfo(fwpAplNo) {

	const rtnData = {};

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
        			if (jsAA.cfn.isAjaxTransactionResSuccess(data)) {
        				rtnData = data.objects['mstBox'];
        			}
          		}
      });
	return rtnData;
}



//Delete 
function fn_deleteDeptIndc( evalFmlaCd ) {

	const gridId = '';
	const procNm = '';

	if( evalFmlaCd == "01" ) {
		gridId = 'mesuList';
		procNm = '계량';
	} else {
		gridId = 'nmesuList';
		procNm = '비계량';
	}

	const gridObj = $('#'+gridId);

	const selrow = gridObj.getGridParam( 'selrow' );
	if( !selrow ) {
		pierp.ui.alert('삭제할 데이터를 선택하세요.');
		return;
	}

	const flag = gridObj.getCell( selrow, 'stateFlag' );
	if( flag == '${ CONST_INSERT }' ) {
		gridObj.gridDelRow({ rowid: selrow });
		return;
	}

	pierp.trx.request({
		url	: '<c:url value="/ajaxD/deleteDeptIndc.do"/>',
		params : {
			procNm : procNm
		},
		iDSs : [
			{type:'grid', id:gridId, flag:'S', name:'vo'}
		],
		confirmMesg : '삭제',
		callback: function(status, resData) {
  	    	if( status == 'success' && resData.status == 'success' ) {
  	    		$('#btnSearch').trigger( 'click' );
  	    	}
  	    }
	});
}


function fn_saveDeptIndcList( evalFmlaCd ) {

	const gridId = '';
	const procNm = '';

	if( evalFmlaCd == "01" ) {
		gridId = 'mesuList';
		procNm = '지표';
	} else {
		gridId = 'nmesuList';
		procNm = '지표)';
	}

	pierp.trx.request({
		url	: '<c:url value="/ajaxS/saveDeptIndcList.do"/>',
		params : {
			procNm : procNm
		},
		iDSs : [
			{type:'grid', id:gridId, flag:'I|U', name:'list'}
		],
		confirmMesg : '저장',
  	    callback: function(status, resData) {
  	    	if( status == 'success' && resData.status == 'success' ) {
  	    		$('#btnSearch').trigger( 'click' );
  	    	}
  	    }
	});
}



function fn_deleteDeptIndc( evalFmlaCd ) {

	const gridId = '';
	const procNm = '';

	if( evalFmlaCd == "01" ) {
		gridId = 'mesuList';
		procNm = '계량';
	} else {
		gridId = 'nmesuList';
		procNm = '비계량';
	}

	const gridObj = $('#'+gridId);

	const selrow = gridObj.getGridParam( 'selrow' );
	if( !selrow ) {
		pierp.ui.alert('삭제할 데이터를 선택하세요.');
		return;
	}

	const flag = gridObj.getCell( selrow, 'stateFlag' );
	if( flag == '${ CONST_INSERT }' ) {
		gridObj.gridDelRow({ rowid: selrow });
		return;
	}

	pierp.trx.request({
		url	: '<c:url value="/ajaxD/deleteDeptIndc.do"/>',
		params : {
			procNm : procNm
		},
		iDSs : [
			{type:'grid', id:gridId, flag:'S', name:'vo'}
		],
		confirmMesg : '삭제',
		callback: function(status, resData) {
  	    	if( status == 'success' && resData.status == 'success' ) {
  	    		$('#btnSearch').trigger( 'click' );
  	    	}
  	    }
	});
}


// 부서 선택 시, 선택가능한 버튼 목록 설정
function fn_listButtonAuth( rowData ) {
	if(rowData.deptYn == 'Y') {
		pierp.ui.disableObj('#btnDeptDelRow', false);
		pierp.ui.disableObj('#btnDeptSplitRow', false);
	} else {
		pierp.ui.disableObj('#btnDeptDelRow', false);
		pierp.ui.disableObj('#btnDeptSplitRow', true);
	}
	
	if(rowData.stateFlag == 'I' ||
			rowData.stateFlag != 'I' && (rowData.deptChgDivCd == '0' || rowData.deptChgDivCd == '5')) {
		pierp.ui.disableObj('#btnBfDeptAddRow', true);
	} else {
		pierp.ui.disableObj('#btnBfDeptAddRow', false);
	}
}


// 조직개편 유무 체크 함수
function fn_chkOrgzChg() {
	const chkOrgzChg = '';
	pierp.trx.request({		
		async : false,	
		url	: '<c:url value="/ajaxP/MHA0203/selectChkOrgzChg.do"/>',
		iDSs : [
			{type:'form', id:'mstBox'}
		],	
  	    callback: function(status, resData){
  	    	if( jsAA.cfn.isAjaxTransactionResSuccess(resData) ){
				chkOrgzChg  = resData.objects.btchDealDivCd;
			}
		}
	});
	return chkOrgzChg;
}


//check Row settings
function fn_CheckForm() {
	fn_changeFormLabel();	// 라벨 세팅
	
	$('#srchBox').setFormData({
		evalYy			: '${ params.evalYy }',
		evalIndcDivCd	: '${ params.evalIndcDivCd }',
		deptUniqNo		: '${ params.deptUniqNo }',
		intrEvalIndcCd	: '${ params.intrEvalIndcCd }'
	});
	
	// 수정상태인지 판단 후 연결 버튼 활성화
	$('#btnLk').disableObj( '${ params.isReadOnly }' == 'true' );
	
	pierp.trx.request({
		url	: '<c:url value="/ajaxR/SVA0102P2/selectOtevalIndcList.do"/>',
		iDSs : [
			{ type:'form', id:'srchBox', name:'param' }
		],
		  oDSs : [
			  { type:'grid', id:'indcList' }
		  ],
		  callback: function(status, resData) {
			  if( status == 'success' && resData.status == 'success' ) {
				  //체크된 목록 filter 후 settings
				  const indcList = resData.objects.indcList.rows;
				  const chkedRow = indcList.filter(function(data) { return data.chk; });
				  chkedRow.forEach(function(data) {
					  $('#indcList').setMultiCheck( ''+data.id, true );
				  });
				  
				  $('#indcList').setSelection(0);
			  }
		  }
	});
}


//grid prop change
function fn_gridEdit(t_f){
    $('#list').setColProp('bznsNm',           {editable:t_f});  // 소속기관
    $('#list').setColProp('bznsPsitNm',       {editable:t_f});  // 직위
}



function fn_changeDtlBox( chgObj ) {
	const boxid		= $(chgObj).closest('.indcDtlBox').attr('id');	// 수정한 boxid
	const cellname	= $(chgObj).attr('name');						// 수정한 cellname
	const divCd		= boxid.replace( 'DtlBox', '' );				// boxid의 구분값 추출 - mesu:계량 / nmesu:비계량
	const gridObj		= $('#'+divCd+'List');							// grid객체

	const selrow = gridObj.getGridParam( 'selrow' );
	if( !selrow ) {
		return;
	}

	gridObj.setCell( selrow, cellname, $(chgObj).val() );
}



function fn_gridOnSelectRow( gridDiv, rowid ) {

	const formNm = gridDiv+'DtlBox';

	// 폼에 데이터 세팅
	const rowData = $('#'+gridDiv+'List').getRowData( rowid );
	$('#'+formNm).setFormData( rowData );

	// 폼 활성화
	const prgsStatCd = $('#indcBox [name=intrEvalPrgsStatCd]').val();
	if( prgsStatCd == '02' ) jsSV.cfn.disableForm( formNm, false );
}


function fn_gridAfterSaveCell( gridDiv, rowid, cellname, value ) {

	const gridObj = $('#'+gridDiv+'List');

	if( cellname == 'adwtMrt' ) {
		gridObj.footerData( 'set', {
	    	evalGoalNm : '합계',
	    	adwtMrt : gridObj.getCol( 'adwtMrt', false, 'sum' )
	    });
	}
}


//파일첨부 현재날짜로 docId 채번하는 함수
function fn_getAttchDocId() {
	const today = new Date();
	const docId = today.getFullYear() + '' 
			+ ('' + (today.getMonth() + 1)).padStart(2, '0') + ('' + today.getDate()).padStart(2, '0') 
			+ today.getHours() + today.getMinutes() + today.getSeconds() + today.getMilliseconds();
	return 'OE' + docId;
}

//Disable MODE Object
const btnMode = {
	init : function(){
		$('#btnAdd').disableObj( false );	
		pierp.ui.disableObj(['#btnSave', '#btnDelete', '#btnAppReq' ,'#btnAddTarget', '#btnDelTarget' ,'#btnAddDiff', '#btnDelDiff'], true);
		pierp.ui.readonlyObj(['[name="wrpDeptCd"]','[name="wrpDeptNm"]', '[name="deptUniqNo"]'], false);
		// $('#mstBox [name=aplyDt]').readonlyObj( false ).removeClass('inp_none2');
	},
	
	readonly : function(){
		pierp.ui.disableObj(['#btnSave', '#btnDelete', '#btnAppReq','#btnAddTarget', '#btnDelTarget','#btnAddDiff', '#btnDelDiff'], true);
		pierp.ui.readonlyObj(['[name="wrpDeptCd"]','[name="wrpDeptNm"]', '[name="deptUniqNo"]'], true);

        // $('#mstBox [name=aplyDt]').readonlyObj( true ).addClass('inp_none2');
        const prop = {cellEdit: false};
        $('#dptList').setGridParam( prop );
	},
    
    update : function(){
		pierp.ui.disableObj(['#btnSave', '#btnDelete', '#btnAppReq','#btnAddTarget', '#btnDelTarget','#btnAddDiff', '#btnDelDiff'], true);
		pierp.ui.readonlyObj(['[name="wrpDeptCd"]','[name="wrpDeptNm"]', '[name="deptUniqNo"]'], false);
        
        const prop = {cellEdit: true};
        $('#dptList').setGridParam( prop );
    },
    
    bfUpdate : function(){
		pierp.ui.disableObj(['#btnSave', '#btnDelete', '#btnAppReq','#btnAddTarget', '#btnDelTarget','#btnAddDiff', '#btnDelDiff'], true);
		pierp.ui.readonlyObj(['[name="wrpDeptCd"]','[name="wrpDeptNm"]', '[name="deptUniqNo"]'], false);

        const prop = {cellEdit: false};
        $('#dptList').setGridParam( prop );
	}
};



</script>





<style>
.detail_table5 {
  width: 100%;
  border-collapse: collapse;
  border: 1px solid #ccc;
  clear: both;
}

.detail_table5 th {
  padding: 5px 5px;
  border: 1px solid #ccc;
  text-align: left;
  background: #f9f9f9;
  color: #798999;
  font-weight: bold;
}

.detail_table5 td {
  padding: 5px 5px;
  border: 1px solid #ccc;
}

.inp_none2 {
  border: 0;
  background: #fff;
}
</style>
</head>
<body>

    <jsp:include page="/WEB-INF/jsp/common/moduleHeader.jsp" />
    <div id="content">
        <table id="srchBox" class="detail_table">
            <tr>
                <th width="100px"><span class="inp_reqr">개편일자</span></th>
                <td><pierp:select name="chgSrno" title="개편일자" sid="MH.sb_selectDeptChgCode"
                        required="true" mode="layer"></pierp:select></td>
            </tr>
        </table>

        <div class="title2_end_btn">
            <div class="title2_box">조직 개편 정보</div>
            <div class="right_btn_box">
                <pierp:button bttn="${bttnMap['btnDfn']}" type="2" />
                <pierp:button bttn="${bttnMap['btnUnDfn']}" type="2" />
                <pierp:button bttn="${bttnMap['btnOrgzChg']}" type="2" disabled="true" />
            </div>
        </div>

        <div id="mstBox">
            <input type="hidden" name="chgSrno" /> <input type="hidden" name="btchDealDivCd" />

            <table class="detail_table margin_top0px" border="0" cellspacing="0" cellpadding="0">
                <colgroup>
                    <col width="100px" />
                    <col width="160px" />
                    <col width="100px" />
                </colgroup>
                <tr>
                    <th><span class="inp_reqr">개편일자</span></th>
                    <td><pierp:date name="aplyDt" title="개편일자" required="true" /></td>
                    <th>확정여부</th>
                    <td><select name="dfnYn" title="확정여부" mode="layer" val="N"
                        disabled="true" width="120">
                            <option value="N">미확정</option>
                            <option value="Y">확정</option>
                    </select></td>
                </tr>
                <tr>
                    <th><span class="inp_reqr">개편내용</span></th>
                    <td colspan="3"><pierp:text name="chgCnts" title="개편내용" required="true"
                            style="width:95%;" /></td>
                </tr>
            </table>
        </div>

        <div class="two-cols">
            <div class="col" style="width: 72%;">
                <div class="title2_end_btn">
                    <div class="title2_box">부서 목록</div>
                    <div class="right_btn_box">
                        <pierp:check id="viewChg" name="viewChg" value="Y" label="변경부서" />
                        <pierp:button bttn="${bttnMap['btnDeptSplitRow']}" type="2" />
                        <pierp:button bttn="${bttnMap['btnDeptAddRow']}" type="2" />
                        <pierp:button bttn="${bttnMap['btnDeptDelRow']}" type="2" />
                    </div>
                </div>
                <pierp:grid pager="true" id="dptList" />
            </div>

            <div class="col" style="width: 27%;">
                <div class="title2_end_btn">
                    <div class="title2_box">이전 부서</div>
                    <div class="right_btn_box">
                        <pierp:button bttn="${bttnMap['btnBfDeptAddRow']}" type="2" />
                        <pierp:button bttn="${bttnMap['btnBfDeptDelRow']}" type="2" />
                    </div>
                </div>
                <pierp:grid pager="true" id="bfList" />
            </div>
        </div>
    </div>
</body>
</html>