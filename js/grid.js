
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
        validEditCell : function(rowid, cellname){
            //소스 확인필요
            // const rowData = $('#list').getRowData(rowid);
            // const result = true;
            // ( rowData.stateFlag == "I" ||  cellname == 'aplyDt' ) ?  result : !result;
            //  return result;

			var rowData = $('#dptList').getRowData( rowid );
    		// 변경무 or 폐지일 경우, 수정불가
    		if( cellname != 'deptChgDivCd' && (rowData.deptChgDivCd == '0' || rowData.deptChgDivCd == '5') )
    			return false;
    		// 부서코드는 신규일 경우만 수정가능
    		if( cellname == 'deptCd' && rowData.stateFlag != 'I' )
    			return false;
    		return true;

         },
		 afterSaveCell: function( rowid, cellname, value, iRow, iCol ) {
    		var rowData = $('#dptList').getRowData( rowid );
    		var arrRowData = $('#dptList').getGridParam('data');
    		
    		if( cellname == 'deptCd' ) {
    			
    			for( var i = 0; i < arrRowData.length; i++ ) {
    				var row = arrRowData[i];
    				if(row.id != rowid && row.deptCd == value) {
    					pierp.ui.alert('존재하는 부서코드입니다.');
    					$('#dptList').setCell(rowid, 'deptCd', cfn_GetOldValueInGridCell( 'dptList', iRow, 'deptCd' ));
    					return;
    				}
    			}
    			
    			var chgSrno = $('#mstBox [name="chgSrno"]').val();
    			var deptUniqNo = String(chgSrno).padLeft(3, '0') + value;
    			$('#dptList').setCell(rowid, 'deptUniqNo', deptUniqNo);
    			
    		} else if ( cellname == 'upprDeptCd' ) {
    			
    			for( var i = 0; i < arrRowData.length; i++ ) {
    				var row = arrRowData[i];
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
    			var msg = null;
    			
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
    			
    			fn_listButtonAuth( $('#dptList').getRowData( rowid ) );
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




	// ======================= 조회, 저장, 삭제 =============================== //












	//등록
	$('#btnComplete').click(function() {
		pierp.trx.request({
			url	: '<c:url value="/ajaxP/doneIntrevalDeptIndcRegt.do"/>',
			iDSs : [
				{type:'form', id:'indcBox', name:'param'}
			],
			validator : function( data ) {
				
				var param = data.param;
				if( param.intrEvalPrgsStatNm == '' ) {
					pierp.ui.alert( '다시 시도해주시기 바랍니다.' );
					return false;
				}

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
				  var deptNm = $('#indcBox [name=deptNm]').val();
				return '[' + deptNm + '] 등록완료 하시겠습니까?';
			  },
			callback: function(status, resData) {
				  if( status == 'success' && resData.status == 'success' ) {
					  $('#btnSearch').trigger( 'click' );
				  }
			  }
		});
	});















	// ======================= 팝업 호출 =============================== //


	//부서 팝업 클릭
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
		var arrData = $('#list2').getGridParam( 'selrow' );
		var formData = $('#xpctBox').getFormData();
		
		arrData.forEach(function(row, index) {
			var evalSu = Number($('#list2').getCell( index, 'xpctGrde' )) >= Number($('#list2').getCell( index, 'evalGrde4' )) ? 1 : 0;
			$('#list2').setCell( index, 'evalSu', evalSu );
		
			var adwtSu = Number($('#list2').getCell( index, 'adwtMrt4' )) * Number($('#list2').getCell( index, 'evalSu' ))
			$('#list2').setCell( index, 'adwtSu', adwtSu );
			
			var achvDeg = evalSu == 1 ? Number(formData.adwtAchv) : Number(formData.adwtNach);
			$('#list2').setCell( index, 'achvDeg', achvDeg );
			
			var adwtAchv = Number($('#list2').getCell( index, 'adwtMrt4' )) * Number($('#list2').getCell( index, 'achvDeg' ))
			$('#list2').setCell( index, 'adwtAchv', adwtAchv );
		});
		
		
		var rsltDstm = $('#list2').getCol( 'adwtSu', false, 'sum' ) / 100 * Number(formData.otEvalDstm);
		$('#xpctBox [name=rsltDstm]').val( rsltDstm );
		
		var cmpaRslt = $('#list2').getCol( 'adwtAchv', false, 'sum' );
		$('#xpctBox [name=cmpaRslt]').val( cmpaRslt );

    });
    


	
	$('#xpctBox [name=avgGrde]').change(function() {

		var avgGrde = $('#xpctBox [name=avgGrde]').val();
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
		
		var arrData = $('#list2').getGridParam( 'selrow' );
		arrData.forEach(function( row, index ) {
			var avgGrdeSub = '';	
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





	//cell 선택시 팝업 호출
	var otEvalLkParam = {
		cellname : 'otEvalIndcNm',
		clear : function(rowid, value, obj) {
			obj.setCell(rowId, 'otEvalIndcNm',	'');
		},
		open : function(rowid, value, autoselect, obj) {
			
			var prgsStat = $('#indcBox [name=intrEvalPrgsStatCd]').val();
			var options = {
				evalYy			: obj.getCell( rowid, 'evalYy' ),
				evalIndcDivCd	: obj.getCell( rowid, 'evalIndcDivCd' ),
				deptUniqNo		: obj.getCell( rowid, 'deptUniqNo' ),
				intrEvalIndcCd	: obj.getCell( rowid, 'evalIndcCd' ),
				isReadOnly		: prgsStat != '02',
				autoSelect		: autoselect,
				rowId			: rowid
			};
			
			jsAA.popup.SVA0102P2(options, function(data, rowId) {
				$('#btnSearch').trigger( 'click' );
			});
		}
	};
	$('#mesuList').makeGridSearchEvent(otEvalLkParam);








// ======================= 그리드 헤더, 푸터 세팅 =============================== //







// ======================= 엑셀 =============================== //

// 엑셀업로드 버튼 클릭
$('#btnExcelUpload').click( function() {
    var options = {
        procId		: 'AAS102P2',
        callback	: function(data) {
            
            var excelData = data.rows;	// 엑셀 입력 데이터 목록
            excelData.forEach(function(edata) {
                edata.stateFlag = 'I';	// 상태값
            });
            
            $('#list').setGridData( data );
        }
    };
    s
    cfn_OpenExcelUpload( options );
});



	// ======================= 행추가, 행삭제 =============================== //

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
	



}); //documentReady END




/* --------------------------------------------------------------------------------------
 * Custom Function
 * -------------------------------------------------------------------------------------- */

//initailize
function fn_init() {





}




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
        			if (jsMH.cfn.isAjaxTransactionResSuccess(data)) {
        				rtnData = data.objects['mstBox'];
        			}
          		}
      });
	return rtnData;
}





//delete 
function fn_deleteDeptIndc( evalFmlaCd ) {

	var gridId = '';
	var procNm = '';

	if( evalFmlaCd == "01" ) {
		gridId = 'mesuList';
		procNm = '계량';
	} else {
		gridId = 'nmesuList';
		procNm = '비계량';
	}

	var gridObj = $('#'+gridId);

	var selrow = gridObj.getGridParam( 'selrow' );
	if( !selrow ) {
		pierp.ui.alert('삭제할 데이터를 선택하세요.');
		return;
	}

	var flag = gridObj.getCell( selrow, 'stateFlag' );
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
