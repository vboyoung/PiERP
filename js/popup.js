
/* --------------------------------------------------------------------------------------
* Grid Cell 선택 시 팝업 호출 방법1, 방법 2 - makeGridSearchEvent()
* -------------------------------------------------------------------------------------- */

//직원검색 팝업 호출, common_win.js 확인
$('#list').makeGridSearchEvent({
	cellname: 'empNm',
	clear: function(rowId, value, obj) {
		obj.setCell(rowId, 'empUniqNo', '');
		obj.setCell(rowId, 'empNo', '');
		obj.setCell(rowId, 'empNm', '');
		obj.setCell(rowId, 'deptCd', '');
		obj.setCell(rowId, 'deptNm', '');
	},
	open: function(rowid, value, autoselect, obj) {
		var options = {
			empNm: value,
			autoSelect: autoselect,
			rowId: rowid
	};
		
	cfn_SearchEmpPop( options, function(data, rowId) {
		obj.setCell(rowId, 'empUniqNo',	data.empNo);
		obj.setCell(rowId, 'empNo',		data.empNo);
		obj.setCell(rowId, 'empNm',		data.empNm);
		obj.setCell(rowId, 'deptCd',	data.deptCd);
		obj.setCell(rowId, 'deptNm',	data.deptNm);
	});
	}
});


// 업무 팝업 호출 
$('#list').makeGridSearchEvent({
	cellname : 'objpRltnCdlstNm',
	clear    : function( rowId, value, obj ){
		obj.setCell(rowId, 'objpRltnCdlst', '' );
		obj.setCell(rowId, 'objpRltnCdlstNm', '' );
	},
	open     : function( rowid, value, autoselect, obj ){
		var row = $('#list').getRowData( rowid );
		var options = {
			empUniqNo	  : $('#list').getCell(rowid, 'empUniqNo'),
			apntSrno	  : $('#list').getCell(rowid, 'apntSrno'),
			rowId         : rowid
		};
		
		cfn_objpRltnCdlstPop(options, function(data, rowId){
				var objpRltnCdlst = "";
				var objpRltnCdlstNm = "";
				for( var i=0; i<data.length; i++ ){
					var row = data[i];
					objpRltnCdlst += row.objpRltnCdlst+",";
					objpRltnCdlstNm += row.objpRltnCdlstNm+",";
				}
				objpRltnCdlst = objpRltnCdlst.substring(0, objpRltnCdlst.length-1);     
				obj.setCell(rowid, 'objpRltnCdlst', objpRltnCdlst);
				
				objpRltnCdlstNm = objpRltnCdlstNm.substring(0, objpRltnCdlstNm.length-1);     
				obj.setCell(rowid, 'objpRltnCdlstNm', objpRltnCdlstNm);
			}
		);
	}
});




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
		jsAA.popup.orgzChgDept(options, function(data, rowId) {
			$('#btnSearch').trigger( 'click' );
		});
	}
};
$('#mesuList').makeGridSearchEvent(otEvalLkParam);




/* --------------------------------------------------------------------------------------
* input 선택 시 직원검색 팝업 호출 - setSearchHandler()
* -------------------------------------------------------------------------------------- */

$('#srchBox input[name=srchEmpNm]').setSearchHandler({
	srchFunc: function (params, callback) {
		cfn_SearchEmpPop(params, callback);
	},
	getParams: function (autoSelect) {
		return {
			empNm: $('#srchBox input[name=srchEmpNm]').val(),
			autoSelect: autoSelect
		};
	},
	callback: function (data, rowId) {
		$('#srchBox input[name=srchEmpNm]').val(data.empNm);
		$('#srchBox input[name=srchEmpNo]').val(data.empNo);
		$('#srchBox input[name=srchEmpUniqNo]').val(data.empUniqNo);
	},
	clearSync: function () {
		$('#srchBox input[name=srchEmpNm]').val('');
		$('#srchBox input[name=srchEmpNo]').val('');
		$('#srchBox input[name=srchEmpUniqNo]').val('');
	}
});




/* --------------------------------------------------------------------------------------
* Callback 받기 : bizAA.popup.cfn_objpRltnCdlstPop 팝업 호출시 flow 
* -------------------------------------------------------------------------------------- */

jsAA.popup = {
	orgzChgDept : function( param, callback) {
		cfn_PopUpModule({
			mdulId : 'MHC0505P1',
			params : param,
			width  : 580,
			height : 370
	   });
	   jsAA.popup.orgzChgDept.cb_Set = function( data, rowid ){
		   if( $.isFunction(callback) ){
			   callback( data, rowid );
		   }
	   };
	},
}

// MHC0505P1 : callback 등록
var _selRowId       = "${params.rowId}";  // 팝업 호출시 그리드번호

if(window.dialogArguments){
	opener = window.dialogArguments;
}
var callbackFn = opener.jsAA.popup.orgzChgDept.cb_Set;
function pfn_Select(){
	var selRow = $('#list').getGridParam('selrow');
	if(!selRow){alert('선택된 행이 없습니다.'); return;}
	
	callbackFn($('#list').getRowData(selRow), _selRowId);
	window.close();
}

//MHC0505
$('#setList').makeGridSearchEvent({
	cellname : 'deptNm',
	clear    : function( rowid, value, obj ){
		obj.setCell(rowid, 'upprDeptUniqNo',     '' );
		obj.setCell(rowid, 'deptUniqNo',     '' );
		obj.setCell(rowid, 'deptCd',         '' );
		obj.setCell(rowid, 'deptNm',         '' );
	},
	open     : function( rowid, value, autoselect, obj ){
            var options = {
                deptNm      : value,
                autoSelect  : false,
                rowId       : rowid
            };
            //callback data 
            jsMH.popup.orgzChgDept(
                options,
                function (data, rowid) {
                    if (data.deptCd == '0200') {
                        data.up1DeptUniqNo = data.deptUniqNo;
                        data.up1DeptNm = data.deptNm;
                    }

                    obj.setCell(rowid, 'upprDeptUniqNo', data.up1DeptUniqNo);
                    obj.setCell(rowid, 'deptUniqNo', data.deptUniqNo);
                    obj.setCell(rowid, 'deptCd', data.deptCd);
                    obj.setCell(rowid, 'deptNm', data.deptNm);
                }
            );
	}
});





/* --------------------------------------------------------------------------------------
* ajaxLoadSelect : sid 조회 
* -------------------------------------------------------------------------------------- */

$( '#btnAdd' ).click( function() {
    	
	if( $('#mstBox [name=aplyDt]').val() == '' ) {
		pierp.ui.alert('개편일자를 입력하세요.');
		$('#mstBox [name=aplyDt]').focus();
		return false;
	}
	
	if( $('#mstBox [name=chgCnts]').val() == '' ) {
		pierp.ui.alert('개편내용을 입력하세요');
		$('#mstBox [name=chgCnts]').focus();
		return false;
	}
	
	pierp.trx.request({		
		url	: '<c:url value="/ajaxS/MHA0203/generateOrgzChg.do"/>',
		iDSs : [
			{type:'form', id:'mstBox'}
		],	
		  callback: function(status, resData){
			  if( jsMH.cfn.isAjaxTransactionResSuccess(resData) ){
				  
				  var chgSrno = resData.objects.chgSrno;
				  pierp.ui.component.ajaxLoadSelect($('#srchBox [name="chgSrno"]'), {
					  sid : 'MH.sb_selectDeptChgCode'
				  }).done(function() {
					  $('#srchBox [name="chgSrno"]').setSelectData( chgSrno );
					  $('#btnSearch').trigger( 'click' );
				  });
			}
		}
	});
});



function getSceyMngeNoListCombo() {
	pierp.ui.component.ajaxLoadSelect($('[name=sceyMngeNo]'), {
        sid: 'MH.sb_selectSceyMngeNoList',
        params: {
        	scode : '${authFlag}',
        	scode2: '${usr.empNo}',
        	scode3: '01'
        },
        first : "선택"
    }).done(function(data){});
}




$('select[name=rsolTyCd]').cfn_ajaxLoadSelect( {comClssCd:'AC004', mngeAtclValu1 : 'Y'} ); 





/* --------------------------------------------------------------------------------------
* jQuery Dialog Open, ajaxLoadSelect
* -------------------------------------------------------------------------------------- */

$('#btnDeptTrsf').click(function() {
    
    var deptRow = $('#deptList').getGridParam( 'selrow' );
    if( !deptRow ) {
        pierp.ui.alert( '부서를 선택하세요.' );
        return;
    }
    
    $('#deptTrsfBox').resetForm();	// 상태변경 폼 초기화
    
    // 상태변경 폼 초기값 세팅
    var deptData = $('#deptList').getRowData( deptRow );
    $('#deptTrsfBox').setFormData({
        evalYy		: deptData.evalYy,
        deptUniqNo	: deptData.deptUniqNo,
        deptNm		: deptData.deptNm
    });
    
    // 목록 세팅
    pierp.ui.component.ajaxLoadSelect( $('#dlgDeptTrsfBox [name=transDeptUniqNo]'), {
        sid : 'SV.sb_selectTransDeptList',
        first : '선택',
        params : {
            deptUniqNo : deptData.deptUniqNo
        }
    });
    
    //dialog open
    dlgDeptTrsfBox = $('#dlgDeptTrsfBox').dialog({
        title		: '이관',
        width		: 1000,
        height		: 500,
        modal		: true,
        stack		: false,
        resizable	: false,
        autoOpen	: false
    });
    
    dlgDeptTrsfBox.dialog('open');
});

