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
    		const rowData = $('#dptList').getRowData( rowid );
    		const arrRowData = $('#dptList').getGridParam('data');
    		
    		if( cellname == 'deptCd' ) {
    			
    			for( const i = 0; i < arrRowData.length; i++ ) {
    				const row = arrRowData[i];
    				if(row.id != rowid && row.deptCd == value) {
    					pierp.ui.alert('존재하는 부서코드입니다.');
    					//cfn_GetOldValueInGridCell() : grid 코드 value 
    					$('#dptList').setCell(rowid, 'deptCd', cfn_GetOldValueInGridCell( 'dptList', iRow, 'deptCd' ));
    					return;
    				}
    			}
    			
    			const chgSrno = $('#mstBox [name="chgSrno"]').val();
    			const deptUniqNo = String(chgSrno).padLeft(3, '0') + value;
    			$('#dptList').setCell(rowid, 'deptUniqNo', deptUniqNo);
    			
    		} else if ( cellname == 'upprDeptCd' ) {
    			
    			for( const i = 0; i < arrRowData.length; i++ ) {
    				const row = arrRowData[i];
    				if(row.deptCd == value) {
    					
    					$('#dptList').setCell(rowid, 'upprDeptCd', row.deptCd);
    					$('#dptList').setCell(rowid, 'upprDeptNm', row.deptNm);
    					if(rowData.deptChgDivCd == '2' || rowData.deptChgDivCd == '3' || rowData.deptChgDivCd == '4') {
    						$('#dptList').setCell(rowid, 'orgzRorgLinkDivCd', 'M');
    					}
    					return;
    				}
    			}
    			
    			pierp.ui.alert('존재하지 않는 부서코드입니다.');
    			$('#dptList').setCell(rowid, 'upprDeptCd', cfn_GetOldValueInGridCell( 'dptList', iRow, 'upprDeptCd' ));
    			
    		} else if ( cellname == 'deptChgDivCd' ) {
    			// (변경구분) 현재 존재하는 부서이면, 신규 불가
    			const msg = null;
    			
    			if(rowData.deptYn == 'Y' && value == '1') {
    				msg = '기존 부서는 신규로 변경 할 수 없습니다.';
    			} else if(rowData.deptYn == 'N' && value == '0') {
    				msg = '추가된 부서는 변경무로 변경 할 수 없습니다.';
    			} else if(rowData.deptYn == 'N' && value == '3') {
    				msg = '추가된 부서는 변경으로 변경 할 수 없습니다.';
    			} else if(rowData.deptYn == 'N' && value == '5') {
    				msg = '추가된 부서는 폐지로 변경 할 수 없습니다.';
    			}
    			
    			if( msg != null ) {
    				pierp.ui.alert( msg );
    				$('#dptList').setCell(rowid, cellname, cfn_GetOldValueInGridCell( 'dptList', iRow, cellname ));
    			} else if( cellname == 'deptChgDivCd' ) {
    				if( rowData.deptChgDivCd == '1' ) {
            			$('#dptList').setCell(rowid, 'orgzRorgLinkDivCd', 'I' );
            		} if( rowData.deptChgDivCd == '5' ) {
            			$('#dptList').setCell(rowid, 'orgzRorgLinkDivCd', 'D' );
            		} if( rowData.deptChgDivCd == '3' || rowData.deptChgDivCd == '2' || rowData.deptChgDivCd == '4' ) {
            			$('#dptList').setCell(rowid, 'orgzRorgLinkDivCd', 'U' );
            		}
    			}
    			
				const rowid =  $('#dptList').getRowData( rowid );
    			fn_listButtonAuth(rowid);
    		}
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


    $('#btnSave').click(function() {
        	
        const delRow = [];
        const list = $('#list').getGridParam( 'data' );
        
        if( list.length < 1 ) {
            pierp.ui.alert( '저장할 데이터가 없습니다.' );
            return;
        }
        
    	pierp.ui.confirm( '저장하시겠습니까?' ).then(function(btn) {
       		if(btn == 'yes') {
                list.forEach(function(rowData) { 
                	const param = Object.assign(rowData, $('#srchBox').getFormData());
                    pierp.trx.request({
                        async : false,
                        url	: '<c:url value="/ajaxS/AAA102P2/saveComuActyOtStaf.do"/>',
                        params : param,
                        callback: function(status, resData) {
                          if(jsAA.cfn.isAjaxTransactionResSuccess(resData)) {
                              const errorMsg = resData.objects.errorMsg;
                              
                              if( errorMsg ) {
                                  $('#list').setCell( rowData.id, 'errorMsg', errorMsg ); // 에러메세지 세팅
                              } else {
                                  delRow.push( rowData.id );	// 삭제처리
                              }
                          }
                        }
                    });
                });
                    
          		 // 저장된 건이 있으면 그리드 삭제 해주기
           		 if(delRow.length > 0) {
                    $('#list').gridDelRow({
                        rowid : delRow,
                        state : false
                    }); 
                    // 모두 저장되었으면 처리메세지 호출 후 닫기
                    if( $('#list').getGridParam( 'records' ) < 1 ) {
                        pierp.ui.alert( '등록되었습니다.' ).then(function() {
                            $('#btnClose').trigger( 'click' );
                        });
                    }
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




    // 조직개편실행 버튼 클릭
    $('#btnOrgzChg').click(function() {
    	const aplyDt = $('#mstBox [name=aplyDt]').val().replace(/-/g, '');
    	if(Number('${today}') > Number(aplyDt)) {
    		pierp.ui.alert('개편일자가 현재일자보다 이후여야 합니다.');
    		return;
    	}
    	
    	pierp.trx.request({
    		async : false,
    		url	: '<c:url value="/ajaxP/MHA0203/orgzChgRunList.do"/>',
    		iDSs : [
    			{type:'form', id:'mstBox'}
    		],
    		cfrmMsgConvertor : function(gridSmmry, confirmMesg) {
    			return "조직개편실행을 하시겠습니까?";
    		},
    		callback: function(status, resData){
    			if( jsAA.cfn.isAjaxTransactionResSuccess(resData) ){
    				$('#btnSearch').trigger( 'click' );
    			}
    		}
    	});
    });



    // Filtering Grid 
    $('#btnDeptSplitRow').click(function() {
    	const selRow = $('#dptList').getGridParam('selrow');
    	if( !selRow ) {
    		pierp.ui.alert('부서를 선택하세요.');
    		return;
    	}
    	
    	const rowData = $('#dptList').getRowData(selRow);
    	const addData = {
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
    		const selRow = $('#dptList').getGridParam('selrow');
    		//필터링 
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

    
    
    $('[name=viewChg]').click(function() {
    	const obj = $('#viewChg');
    	if( obj.is(':checked') ) {
    		
    		const rowid = -1;
    		const arrRowData = $('#dptList').getGridParam('data');
    		for(const i = 0; i < arrRowData.length; i++) {
    			const rowData = arrRowData[i];
    			if(rowData.deptChgDivCd != '0') {
    				rowid = rowData.id;
    				break;
    			}
    		}
    		
    		cfn_FilterGrid('dptList', {
    			filters			: "",
    			searchField		: "deptChgDivCd",
    			searchOper		: "ne",
    			searchString	: "0"
    		});
    		
    		if(rowid == -1) {
    			$('#dptList').resetSelection();
    		} else {
    			$('#dptList').setSelection(rowid);
    		}
    	} else {
    		cfn_UnFilterGrid('dptList');
    		$('#dptList').setSelection(0);
    	}
    });




    $('#btnLk').click(function() {
    	const chkIndex = $('#indcList').getCheckedIndex();
    	if( chkIndex.length < 1 ) {
    		pierp.ui.alert('선택된 행이 없습니다.');
    		return;
    	}
    	
    	// 선택한 데이터의 arr배열 얻기
    	const chkData = chkIndex.map(function( rowid ) {
    		return $('#indcList').getCell( rowid, 'evalIndcCd' );
    	});
    	
    	// 세트 값 세팅
    	const otEvalIndcCdSet = chkData.join('|');
    	$('#srchBox [name=otEvalIndcCdSet]').val( otEvalIndcCdSet );
    	
    	// 연결
    	pierp.trx.request({
    		url	: '<c:url value="/ajaxS/SVA0102P2/insertOtEvalLink.do"/>',
    		iDSs : [
    			{ type:'form', id:'srchBox', name:'param' }
    		],
    		showMsg : false,
    	    callback: function(status, resData) {
    		  if( status == 'success' && resData.status == 'success' ) {
    			  pierp.ui.alert( '연결이 완료되었습니다.' ).then(function() {
    				  callbackFn();
    				  window.close();
    			  });
    		  }
    	    }
    	});
    });

 

    $('#btnXpctCfm').click(function() {
    
    	const selRow = $('#list').getGridParam( 'selrow' );
    
    	//required check
    	const chkIdx = selRow.filter(function(chkIdx) {
    		return chkIdx.checkBoxYn4 == 'Y' && (!chkIdx.evalGoalGrde4 || !chkIdx.evalGrde4 || !chkIdx.adwtMrt4);
    	});
    	if( chkIdx.length > 0 ) {
    		pierp.ui.alert( $('#srchBox [name=srchYy]').val() + '년 대상여부가 선택된 건의 목표점수/평가점수/가중치 입력을 다시 확인해주시기 바랍니다.' );
    		return;
    	}
    
    	//save check
    	chkIndex = selRow.filter( function(row) {
    		return row.stateFlag == 'I' || row.stateFlag == 'U'
    	});
    	if( chkIndex.length > 0 ) {
    		pierp.ui.alert( ' 저장 후 다시 진행해주시기 바랍니다.' );
    		return;
    	}
    
    	//input check
    	const xpctBox = $('#xpctBox').getFormData();
    	xpctBox.map(function(xpct, idx) {
    		if(!xpct) {
    			$("#xpctBox [name=" +  xpctBox[idx] + "]").focus();
    		}   
    		return; 
    	})
    
    
    	// calculator
    	const arrData = $('#list2').getGridParam( 'selrow' );
    	const formData = $('#xpctBox').getFormData();
    	
    	arrData.forEach(function(row, index) {
    		const evalSu = Number($('#list2').getCell( index, 'xpctGrde' )) >= Number($('#list2').getCell( index, 'evalGrde4' )) ? 1 : 0;
    		$('#list2').setCell( index, 'evalSu', evalSu );
    	
    		const adwtSu = Number($('#list2').getCell( index, 'adwtMrt4' )) * Number($('#list2').getCell( index, 'evalSu' ))
    		$('#list2').setCell( index, 'adwtSu', adwtSu );
    		
    		const achvDeg = evalSu == 1 ? Number(formData.adwtAchv) : Number(formData.adwtNach);
    		$('#list2').setCell( index, 'achvDeg', achvDeg );
    		
    		const adwtAchv = Number($('#list2').getCell( index, 'adwtMrt4' )) * Number($('#list2').getCell( index, 'achvDeg' ))
    		$('#list2').setCell( index, 'adwtAchv', adwtAchv );
    	});
    	
    	
    	const rsltDstm = $('#list2').getCol( 'adwtSu', false, 'sum' ) / 100 * Number(formData.otEvalDstm);
    	$('#xpctBox [name=rsltDstm]').val( rsltDstm );
    	
    	const cmpaRslt = $('#list2').getCol( 'adwtAchv', false, 'sum' );
    	$('#xpctBox [name=cmpaRslt]').val( cmpaRslt );
    
    });


    
    
    $('#btnRegPop1').click(function() {
    
        // 하나라도 수정된 값이 있으면 true
        const isModified = $('#list3').getGridParam('data').some(function(rowData) { return rowData.stateFlag != null; });
        if( isModified ) {
            pierp.ui.confirm( '수정사항이 있습니다.\n저장 진행하지 않으면 수정된 정보는 저장되지 않습니다.\n팝업을 호출하시겠습니까?' ).then(function(btn) {
                if( btn == 'yes' ) {
                    $('#list2').setSelection( $('#list2').getGridParam( 'selrow' ) );
                    fn_openSVB0102P1();
                }
            });
        } else {
            fn_openAAAS102P1();
        }
        
    });




    $('#xpctBox [name=avgGrde]').change(function() {
    
    	const avgGrde = $('#xpctBox [name=avgGrde]').val();
    	if( avgGrde < 0.01 || avgGrde > 100 ) {
    		pierp.ui.alert('점수는 0.01 ~ 100.00 사이의 값만 입력하실 수 있습니다.').then(function() {
    			$('#list2').getGridParam( 'data' ).forEach(function(row, index) {
    				$('#list2').setCell( index, 'avgGrdeSub', '' );
    			});
    			$('#xpctBox [name=avgGrde]').val('');
    			$('#xpctBox [name=avgGrde]').focus();
    		});
    		return;
    	}
    	
    	const arrData = $('#list2').getGridParam( 'selrow' );
    	arrData.forEach(function( row, index ) {
    		const avgGrdeSub = '';	
    		if( !$('#list2').getCell( index, 'stdGoalGrde' ) ) {
    			avgGrdeSub = '-';
    		} else if( Number(avgGrde) <= Number($('#list2').getCell( index, 'stdGoalGrde' )) ) {
    			avgGrdeSub = '이상';
    		} else {
    			avgGrdeSub = '이하';
    		}
    		$('#list2').setCell( index, 'avgGrdeSub', avgGrdeSub );
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
    


    // 이전부서 행삭제 버튼 클릭
    $('#btnBfDeptDelRow').click(function() {
    	const selRow = $('#bfList').getGridParam('selrow');
    	if( !selRow ) {
    		pierp.ui.alert('선택된 이전 부서가 없습니다.');
    		return;
    	}
    	
    	const rowData = $('#bfList').getRowData(selRow);
    	if(rowData.deptYn == 'Y') {
    		pierp.ui.alert('기본 부서는 삭제할 수 없습니다.');
    		return;
    	} else if (rowData.stateFlag == '${CONST_INSERT}') {
    		// 신규건은 그리드 삭제
    		$('#bfList').gridDelRow({ rowid: selRow });
    		return;
    	}
    	
    	pierp.trx.request({
    		url	: '<c:url value="/ajaxD/MHA0203/deleteOrgzChgBfDeptList.do"/>',
    		validator : function(data) {
    			return true;
    		},
    		iDSs : [
    			{type:'grid', id:'bfList', flag:'S'}
    		],
    		confirmMesg : '삭제',
    		callback: function(status, resData){
    			if( jsAA.cfn.isAjaxTransactionResSuccess(resData) ) {
    				$('#bfList').gridDelRow({ rowid: selRow });
    			}
    		}
    	});
    });


    $('#btnFileDtls').click(function() {
    		
        const indcRow = $('#indcList').getGridParam( 'selrow' );
        if( !indcRow ) {
            pierp.ui.alert( '목록을 선택 후 진행하시기 바랍니다.' );
            return;
        }
        
        // 파일첨부 docId 얻어오기
        const rsltStat = $('#rsltBox [name=otEvalPrgsStatCd]').val() == '04';
        const dtlsDoctId = $('#dtlBox [name=evalDtlsAttchDoctId]').val();
        const docId = rsltStat || dtlsDoctId ? dtlsDoctId : fn_getAttchDocId();
        
        $('#dtlBox [name=evalDtlsAttchDoctId]').val( docId );
        
        if( !docId ) {
            pierp.ui.alert( '등록된 첨부문서가 없습니다.' );
            return;
        }
        
        cfn_OpenFileAttach({
            programId	: "ASS0201",
            docId		: docId,
            readonly	: rsltStat
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


// 결재상태값에 따른 input, btn 처리
function fn_DisableObjMstBox(t_f){
    pierp.ui.disableObj([
      '[name="wrpDeptCd"]','[name="wrpDeptNm"]', '[name="deptUniqNo"]',
      '#btn_wrpDeptNm', '#btn_wrpEmpNM'
     ], t_f); 
}


switch(snctnStatDivCd) {
   
    case "00" : {
      //00: 기안중(저장)
      pierp.ui.disableObj(['#btnSave', '#btnDelete', '#btnAppReq'
                          ,'#btnAddTarget', '#btnDelTarget'
                          ,'#btnAddDiff', '#btnDelDiff'], false);
      fn_DisableObjMstBox(false);
      fn_gridEdit(true);
      break;
    }

    case "01" : {
      //01: 요청
      pierp.ui.disableObj(['#btnSave', '#btnDelete', '#btnAppReq'
                          ,'#btnAddTarget', '#btnDelTarget'
                          ,'#btnAddDiff', '#btnDelDiff'], true);
      fn_DisableObjMstBox(true);
      fn_gridEdit(!true);
      break;
    }

    case "02" : {
      //02:결재진행 
      pierp.ui.disableObj(['#btnSave', '#btnDelete', '#btnAppReq'
                          ,'#btnAddTarget', '#btnDelTarget'
                          ,'#btnAddDiff', '#btnDelDiff'], true);
      fn_DisableObjMstBox(true);
      fn_gridEdit(!true);
      break;
    }

    case "03" : {
     //03:반려됨
      pierp.ui.disableObj(['#btnSave', '#btnDelete'
                          ,'#btnAddTarget', '#btnDelTarget'
                          ,'#btnAddDiff', '#btnDelDiff'], false);
      pierp.ui.disableObj('#btnAppReq', true);
      fn_DisableObjMstBox(false);

      $('#snctnOpnn').show();

      break;
    }

    case "05" : {
      //05:결재완료
      pierp.ui.disableObj(['#btnSave', '#btnDelete', '#btnAppReq'
                          ,'#btnAddTarget', '#btnDelTarget'
                          ,'#btnAddDiff', '#btnDelDiff'], true);
      fn_DisableObjMstBox(true);
      fn_gridEdit(!true);
      break;
    }
  }



// 신청번호가 있을시 기본셋팅
if( !_.isEmpty(_fdbkDealNo) ){
	fn_OneCase(_fdbkDealNo);
	} else {
	// input, button control
	fn_DisableObjMstBox(false);
	fn_DisableObjFdbkBox(false);

	// 조회결과 초기화
	$('#list').clearGridData();
	$('#fdbkBox').resetFormData();
	$('#btnAdminSave').hide();
	}
function isEmpty( str ){
	if( str == null ) return true;
	if( str == undefined ) return true;
	if( str == '' ) return true;

	return false;
}

function isNotEmpty( str ){
	return !isEmpty( str );
}




function fn_setGridHeaderTitle( yy ) {
	// 헤더 해제
	$('#list').destroyGroupHeader();
	
	// 헤더 병합
	$('#list').setGroupHeaders({
		useColSpanStyle:true,
		groupHeaders:[
			{startColumnName:'csmrStsfDegEvalYy1',	numberOfColumns:7, titleText:(yy-3) + '년'}
			, {startColumnName:'csmrStsfDegEvalYy2',	numberOfColumns:7, titleText:(yy-2) + '년'}
			, {startColumnName:'csmrStsfDegEvalYy3',	numberOfColumns:7, titleText:(yy-1) + '년'}
			, {startColumnName:'csmrStsfDegEvalYy4',	numberOfColumns:7, titleText:yy + '년'}
		]
	});
	
	// 헤더 해제
	$('#list2').destroyGroupHeader();
	
	$('#list2').setLabel( 'evalGrde1', (yy-3)+'년' );
	$('#list2').setLabel( 'evalGrde2', (yy-2)+'년' );
	$('#list2').setLabel( 'evalGrde3', (yy-1)+'년' );
	
	// 헤더 병합
	$('#list2').setGroupHeaders({
		useColSpanStyle:true,
		groupHeaders:[
			{startColumnName:'evalGrde1',	numberOfColumns:4, titleText:'평균'}
		]
	});
}   


function fn_setFooterData() {
	// getCol 함수의 count가 숨겨져있는 행 한줄도 포함하기 때문에 -1 처리
	const colCnt = {
		checkBoxYn1			: $('#list').getCol('checkBoxYn1',	 true).filter(function(item){ return item.value == 'Y' }).length,
    	evalGoalGrde1		: $('#list').getCol('evalGoalGrde1', false, 'count')-1,
    	evalGrde1			: $('#list').getCol('evalGrde1',	 false, 'count')-1,
    	checkBoxYn2			: $('#list').getCol('checkBoxYn2',	 true).filter(function(item){ return item.value == 'Y' }).length,
    	evalGoalGrde2		: $('#list').getCol('evalGoalGrde2', false, 'count')-1,
    	evalGrde2			: $('#list').getCol('evalGrde2',	 false, 'count')-1,
    	checkBoxYn3			: $('#list').getCol('checkBoxYn3',	 true).filter(function(item){ return item.value == 'Y' }).length,
    	evalGoalGrde3		: $('#list').getCol('evalGoalGrde3', false, 'count')-1,
    	evalGrde3			: $('#list').getCol('evalGrde3',	 false, 'count')-1,
    	checkBoxYn4			: $('#list').getCol('checkBoxYn4',	 true).filter(function(item){ return item.value == 'Y' }).length,
    	evalGoalGrde4		: $('#list').getCol('evalGoalGrde4', false, 'count')-1,
    	evalGrde4			: $('#list').getCol('evalGrde4',	 false, 'count')-1
	};
	
	// getCol 함수의 avg가 숨겨져있는 행 한줄도 포함하기 때문에 sum / count-1 처리
	const colAvg = {
    	evalGoalGrde1		: colCnt.evalGoalGrde1	< 1 ? 0	: $('#list').getCol('evalGoalGrde1', false, 'sum') / colCnt.evalGoalGrde1,
    	evalGrde1			: colCnt.evalGrde1		< 1 ? 0	: $('#list').getCol('evalGrde1',	 false, 'sum') / colCnt.evalGrde1,
 		evalGoalGrde2		: colCnt.evalGoalGrde2	< 1 ? 0	: $('#list').getCol('evalGoalGrde2', false, 'sum') / colCnt.evalGoalGrde2,
 		evalGrde2			: colCnt.evalGrde2		< 1 ? 0	: $('#list').getCol('evalGrde2',	 false, 'sum') / colCnt.evalGrde2,
 		evalGoalGrde3		: colCnt.evalGoalGrde3	< 1 ? 0	: $('#list').getCol('evalGoalGrde3', false, 'sum') / colCnt.evalGoalGrde3,
 		evalGrde3			: colCnt.evalGrde3		< 1 ? 0	: $('#list').getCol('evalGrde3',	 false, 'sum') / colCnt.evalGrde3,
 		evalGoalGrde4		: colCnt.evalGoalGrde4	< 1 ? 0	: $('#list').getCol('evalGoalGrde4', false, 'sum') / colCnt.evalGoalGrde4,
 		evalGrde4			: colCnt.evalGrde4		< 1 ? 0	: $('#list').getCol('evalGrde4',	 false, 'sum') / colCnt.evalGrde4
	};
	
	// 푸터 세팅
    $('#list').footerData('set', {
    	csmrStsfDegObjtBzNm	: '건수·평균·합계',
    	checkBoxYn1			: colCnt.checkBoxYn1,
    	evalGoalGrde1		: colAvg.evalGoalGrde1.toFixed(2),
    	evalGrde1			: colAvg.evalGrde1.toFixed(2),
    	adwtMrt1			: $('#list').getCol('adwtMrt1',		false, 'sum'),
    	evalResult1			: $('#list').getCol('evalResult1',	false, 'sum'),
    	checkBoxYn2			: colCnt.checkBoxYn2,
    	evalGoalGrde2		: colAvg.evalGoalGrde2.toFixed(2),
    	evalGrde2			: colAvg.evalGrde2.toFixed(2),
    	adwtMrt2			: $('#list').getCol('adwtMrt2',		false, 'sum'),
    	evalResult2			: $('#list').getCol('evalResult2',	false, 'sum'),
    	checkBoxYn3			: colCnt.checkBoxYn3,
    	evalGoalGrde3		: colAvg.evalGoalGrde3.toFixed(2),
    	evalGrde3			: colAvg.evalGrde3.toFixed(2),
    	adwtMrt3			: $('#list').getCol('adwtMrt3',		false, 'sum'),
    	evalResult3			: $('#list').getCol('evalResult3',	false, 'sum'),
    	checkBoxYn4			: colCnt.checkBoxYn4,
    	evalGoalGrde4		: colAvg.evalGoalGrde4.toFixed(2),
    	evalGrde4			: colAvg.evalGrde4.toFixed(2),
    	adwtMrt4			: $('#list').getCol('adwtMrt4',		false, 'sum'),
    	evalResult4			: $('#list').getCol('evalResult4',	false, 'sum')
    }, false);
}





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