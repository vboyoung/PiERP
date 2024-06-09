
/* 
그리드 설정
트랜잭션
헤더병합
footer 합계
팝업 호출
팝업 호출 데이터 선택 후 팝업종료
첨부파일
버튼 첨부파일
공통코드 가져오기
탭
*/


// {name:'stateFlag',    cname:'상태', width:'30',  align:'center', hidden:true,  editable:false}
// stateFlag : I(그리드추가), U , D, C(체크) , A(전체), S(셀렉트)

var f2vos;

var cmodels = [
    {name:'usrEmpNm',   cname:'사용자', width:'80',  align:'center', hidden:false, editable:true,  edittype:'search'}
];

//  getReadProp, getWriteProp
f2vos = pierp.grid.create('#list', cmodels, pierp.grid.getWriteProp({
    rownum         : 15,
    height		   : 300,
    scroll         : true,
    rownumbers     : false,
    sortable       : true,
    multiselect    : true,
    multiexecute   : true,
    editmode       : false,
    cellEdit       : true,
    loadComplete  : function() {
        $("#voList").footerData('set',{
            bzId:'합계', drAmt:$("#voList").getCol('drAmt',true,'sum'), crAmt:$("#voList").getCol('crAmt',true,'sum')
        });
    },
    beforeSaveCell: function(rowid, cellname, value, iRow, iCol) {

    },
    afterSaveCell  : function(rowid, cellname, value, iRow, iCol) {
    
    },
    enterEditCell  : function(rowid, cellname, value, iRow, iCol) {
    
    },
    ondblClickRow : function(rowId, iRow, iCol, e){
        // cell 더블클릭 되었을때 발생하는 이벤트 : 팝업띄울때

    },
    onSelectRow  : function(rowId) { 
        // 한 줄 선택시 이벤트 : 폼에 데이터 바인딩 할때
        
        $('#maa0101List').GridToForm(rowId, '#detailBzBox'); //selectData Form에 set
        $('#detailBzBox input[name=fxastClssCd]').attr('readonly', true); // 분류구분코드 비활성
        $('#detailBzBox input[name=crudFlag]').val('U');  // 처리구분플래그 : 수정/삭제("U")로 설정한다.
        $('#btnDel').show();  // 삭제버튼 표시

    },
    footerrow    : true,
    propsEnd       : null
}));


  

/* 트랜잭션 */

//async : true -> save : 블로킹+동기 :  요청받은 함수의 작업이 끝나야 제어권 돌려받음 + 요청자는 결과가 나올때까지 계속 확인
    pierp.trx.request({
        block : true,
        confirmMseg : '반려',
        url : '<c:url value="/ajaxS/MAA0604/updateValsSuvyMngeGvb.do"/>',
        iDSs : [
                { type	: 'form',   id : 'gvbBox' ,  /* name : params */ },
                { type	: 'grid',   id : 'mngeList', flag    : 'C' }
        ],
        oDSs: [
        //  {type:'form', id:'mstBox'}, { type : 'grid', id:'voList'}
        ],
        callback : function(status, data){
        if(status == 'success') {
            $('#gvbBox input[name=astsGvbRson]').val('');
            $('#btnSearch').trigger('click');
        }
        }
    });


//async : true -> search
$('#btnSearch').click(function() {

    pierp.ui.blockBtn('btnSearch', true);

    pierp.trx.request({
        async : true,
        cfrmMsgConvertor: function(gridSmmry, defMsg) {
            return null;
        },
        url: '<c:url value="/ajaxR/MBD0103/selectMnthlExeSttsList.do"/>',
        iDSs: [ {type:'form', id:'srchBox'} ],
        oDSs:[ {type:'grid', id:'mnthlExeSttsList'}],
        callback: function(status, resData){
              if (status == 'success' && resData.status == 'success') {
              }
        }
    });

    pierp.ui.blockBtn('btnSearch', false);

});


//async : true -> save
$('#btnSave').click(function() {
	
    pierp.ui.blockBtn('btnSave', true);
    
    pierp.trx.request({
        async : true,
        cfrmMsgConvertor: function(gridSmmry, defMsg) {
            return '입력한 데이터를 저장하시겠습니까?';
        },
        url: '<c:url value="/ajaxS/MBB0215/saveMnthlTrgtRtList.do"/>',
        iDSs: [ {type:'grid', id:'mnthlTrgtRtList', flag:'U'} ],
        callback: function(status, resData){
            if (status == 'success' && resData.status == 'success') {
                $('#mnthlTrgtRtList').clearGridData();
                $('#btnSearch').trigger('click');
            }
          }
    });   
    
    pierp.ui.blockBtn('btnSave', false);

});

//async : true -> delete : 블로킹 + 비동기 :요청받은 함수의 작업이 끝나야 제어권 돌려받음 + 결과는 요청받은 함수가 알려줌
$('#btnDelete').click(function() {

    pierp.ui.blockBtn('btnDelete');

    var isptMngeNo  = $('#detailBzBox input[name=isptMngeNo]').val();
    if(isptMngeNo == '') {
        pierp.ui.alert("삭제 할 점검일지를 선택하십시요.");
        return;
    }
    pierp.trx.request({
		async : false,
		cfrmMsgConvertor: function(gridSmmry, defMsg) {
            return '그리드에 존재하는 모든 데이터가 삭제됩니다.\n삭제하시겠습니까?';
        },
		url: '<c:url value="/ajaxD/MFP0201P31/deleteRsolDutiCost.do"/>',
		iDSs: [{type:'grid', id:'voList', flag:'D'}],
        callback: function(status, resData) {
            if (status == 'success' && resData.status == 'success') {
                // 입력폼 초기화
                $('#btnInit2').trigger('click');
                //재조회
                $('#btnSearch').trigger('click');
            }
        }	   	
	});
                        
    pierp.ui.blockBtn('btnDelete', false);
});



/* 헤더병합 */
$('#list').setGroupHeaders({
    useColSpanStyle:true,
    groupHeaders:[
        {startColumnName: 'tnoBzplCd', numberOfColumns: 8, titleText: '인계'},
        {startColumnName: 'bzplCd',    numberOfColumns: 7, titleText: '인수'}
    ]
});



/* footer 합계 */
function fn_sumAmt() {

    $("#voList1").footerData('set',{chgAftrBdgtAmt:$("#voList1").getCol('chgAftrBdgtAmt',true,'sum')
								  ,chgBfrBdgtYildAmt:$("#voList1").getCol('chgBfrBdgtYildAmt',true,'sum')
								  ,chgBfrBdgtAmt:$("#voList1").getCol('chgBfrBdgtAmt',true,'sum')
								  ,chgAftrBdgtYildAmt:$("#voList1").getCol('chgAftrBdgtYildAmt',true,'sum')
								  ,variAmt:$("#voList1").getCol('variAmt',true,'sum')
								  });
    fn_colorFmt(); //컬러변경
}



/* 팝업 호출 */

//팝업 bizMA.js에 만들어서 호출
$('#btnAddAsts').click(function() {

    var isDup = false; // 중복체크 플러그
    var arrAstsNos = $('#list').getCol('astsNo');

    cfn_SearchAstsPop({astsNm:'', isMulti:true, radioDivCd:'1'}, function(data, rowId) {

        // 자산번호 중복체크
        for(var i = 0; i < arrAstsNos.length; i++) {
            for(var j = 0; j < data.length; j++) {
                if(data[j].astsNo == arrAstsNos[i]) {
                    isDup = true;
                    break;
                }
            }
        }

        if(isDup) pierp.ui.alert("이미 추가된 자산항목입니다.");

        if(!isDup) {
            for(var i = 0; i < data.length; i++) {
                $('#list').gridAddRow({
                    editmode : true,
                    position : 'last',
                    initdata : {
                         stateFlag    : 'I'
                        ,astsNo       : data[i].astsNo
                        ,astsNm       : data[i].astsNm
                        ,modlNm       : data[i].modlNm
                        ,astsStatCd   : data[i].astsStatCd
                        ,fxastClssCd  : data[i].fxastClssCd
                        ,tnoBzplCd    : data[i].bzplCd
                        ,tnoBzplNm    : data[i].bzplNm
                        ,tnoUseDeptCd : data[i].useDeptCd
                        ,tnoUseDeptNm : data[i].useDeptNm
                        ,bzplCd		  :'1'
                        ,tnoUsePlcCd  : data[i].usePlcCd
                        ,tnoUsePlcNm  : data[i].usePlcNm
                        ,tnoUsrEmpNo  : data[i].usrEmpNo
                        ,tnoUsrEmpNm  : data[i].usrEmpNm
                        ,infozAstsYn  : data[i].infozAstsYn
                    }
                });
            } //for-end
        } //if-end
    }); // popup-end

});

//bizMA.js 
function cfn_SearchAstsPop(options, callback){

    var astsNm	    = (options.astsNm != null) ?     options.astsNm : '';
    var astsNo	    = (options.astsNo != null) ?     options.astsNo : '';
    var astsStatCd  = (options.astsStatCd != null) ? options.astsStatCd : '';    // 자산상태
    var astsKndCd   = (options.astsKndCd != null) ?  options.astsKndCd : '';    // 자산종류
    var autoSelect	= (options.autoSelect != null) ? options.autoSelect : true;
    var rowId		= (options.rowId != null) ?      options.rowId:'';
    var isMulti     = options.isMulti != null ?      options.isMulti : false;    // 다중행 선택가능 여부
    var radioDivCd  = options.radioDivCd != null ? options.radioDivCd : '';      // 자산 radio 구분 코드(1:자산추가,2:자산불용,3:정보자산불용,default:단순조회)
    
    cfn_PopUpModule({
        mdulId : 'MAA0401P1',
        params : {
          astsNm:astsNm, 
          astsNo:astsNo, 
          astsStatCd:astsStatCd, 
          astsKndCd:astsKndCd, 
          autoSelect:autoSelect, 
          rowId:rowId, 
          isMulti:isMulti,
          radioDivCd:radioDivCd
        },
        width  : 1000,
        height : 655
    });

    // callback function
    cfn_SearchAstsPop.cb_Set = function(data, rowId, isMulti){
        if( $.isFunction(callback) ){
            callback(data, rowId, isMulti);
        }
    };
}

//팝업 닫기버튼 클릭
$('#btnClose').click(function() {
    self.close();
    // 부모창에 알림-부모창 검색 refresh
    if( opener && opener.fn_notifyWindowClosed ) {
        opener.fn_notifyWindowClosed();
        opener.focus();
    }
});



/* 팝업 호출 데이터 선택 후 팝업종료 : 자산분류코드[MAA0101] 계정과목팝업호출 MFZ0201P1*/
// callback function
cfn_SearchAcctPop.cb_Set = function(data, rowId){
    if( $.isFunction(callback) ){
        callback(data, rowId);
    }
};

//MFZ0201P1.jsp
if(window.dialogArguments){
    opener = window.dialogArguments;
}
var callbackFn = opener.cfn_SearchAcctPop.cb_Set;callbackFn

var props = {
    ondblClickRow : function(rowId, iRow, iCol, e){
        $('#mfAcctList').setSelection(rowId).focus();
        pfn_SelectAcctCd();
    },
};

// 선택함수
function pfn_SelectAcctCd(){
    var selRow = $('#mfAcctList').getGridParam('selrow');
    if(!selRow){pierp.ui.alert('선택된 행이 없습니다.'); return;}

    var rowData = $('#mfAcctList').getRowData(selRow);
    callbackFn(rowData, _selRowId);
    window.close();

}





/*  첨부파일  */

var uploader = null;
var programId = 'MBB0211.do';
// 업로더 생성
uploader = new pierp.GridFileUploader('gridfileupload-container', {
    params: {
      programId: 'MAA0501.do',
    },
    multiple: true,              // 추가버튼클릭시 여러개 선택가능여부 default: true
    height: 80,                 // 그리드 height default : 150
    maxFileSize: 1024*1024*500,  // 파일크기제한 ( KB*MB*size ) default : 31MB
// 	    maxFileCount: 10,            // upload파일갯수제한 default : 10
// 	    seqUpdatable: false          // 순서 column 사용여부. default: false
});

//파일첨부 팝업
cfn_OpenFileAttach({
    programId: 'MHC0503', //학력관리 모듈ID로 프로그램아이디 사용
    docId: docId,
    //fileCls: 'A'
    readonly: readonly
    //multiple: true
    //maxFileSize: 1024*1024*31
    //maxFileCount: 10
    //allowTypes: 'all'
    ,callback: function(returnData){
    }
});

<div id="gridfileupload-container"> </div>


// 버튼 첨부파일








/* 공통코드 가져오기 */

// 1. serachForm에 selectBox 공통코드 가져오기, sid : MBAjax_Mapper.xml에 쿼리로 

pierp.ui.component.ajaxLoadSelect($('[name=srchPodrInstCd]'), {
    sid: 'MB.sb_selectPodrInstCd',
    params: {
        scode : $('#srchBox [name="actsYy"]').val() //년월 던지기
    },
    first : '전체'
});

<select id="sb_selectPodrInstCd" parameterType="ajaxVO" resultType="codeVO">
    <![CDATA[
SELECT A.BINF_CD as code, A.BINF_CD_NM as codeNm
  FROM TB_MB_BINF_CD A
 WHERE 1=1
   AND A.BINF_DIV_CD = '10'
   AND A.ACTS_YY = (SELECT NVL(MNGE_ATCL_VALU1, #{scode})
                      FROM VW_SY_COM_CD_DTL
                     WHERE COM_CLSS_CD = 'BZ024'
                       AND COM_CD = A.BINF_DIV_CD
                       AND USE_YN = 'Y')
 ORDER BY BINF_CD
    ]]>
</select>


// 2. Grid에 selectBox 공통코드 가져오기 (sid)

,{name:'bzplCd',cname:'사업장', width:'100', align:'center', hidden:false,  editable:true,  edittype:'select', 
    editoptions:{value:<pierp:gs first='' sid='bizMA.sb_selectBzplCd'/>}}


// 3. callback 후 selectBox 공통코드 set 
// 3-1 sid, scode로 가져오기 (MBAjax_Mapper.xml)

var editOpts = $('#bzCdList').getColProp('bzTyCd').editoptions;
        
editOpts = $.extend(editOpts, {
          value:pierp.grid.makeSelectOptions(cfn_ajaxCodeList({ sid: 'MB.sb_selectBzTyCd', scode : $('#srchBox [name=actsYy]').val() } ), ' ')
});

// 3-2 cid 로 가져오기
var editOpts = $('#voList').getColProp('bdgtChgDiv').editoptions;
editOpts = $.extend(editOpts, {
           value:pierp.grid.makeSelectOptions(cfn_ajaxCodeList({cid: 'BZ012', }) )
});









/* pierp.grid Grid 공통함수명 */
R.grid = {
    getContainer: _getContainer,
    create: wrap_createBasicGrid,   //그리드생성
    getReadProp: getReadProp,   //읽기만 가능
    getWriteProp: getWriteProp, //저장 
    setSelection: _restoreGridSelection,    //rowid에 해당하는 row를 선택표시 한다. 
    locateSelRow: _LocateSelRow,
    locateSelPage: _LocateSelPage,
    disable: _DisableGrid,
    filter: _FilterGrid,
    unFilter: _UnFilterGrid,
    getStateFlag: _GetStateFlag,
    makeSelectOptions: _makeGridSelectOptions,   //pierp.grid.makeSelectOptions : select된 값 가져오기
    exportExcel: _gridExcelExport,              //엑셀다운로드
    createBasicGrid: _createBasicGrid
  };

  

/* 탭 */
<div id="tabs">
    <ul>
        <li><a href="#tab-detail1" onclick=""> 전자계산서 </a></li>
        <li><a href="#tab-detail2" onclick=""> 전자계산서 외 </a></li>
    </ul>

    <div id="tab-detail1">
        <pierp:grid id="eltrTaxbList1" />
    </div>

    <div id="tab-detail2">
        <pierp:grid id="unEltrTaxbList1" />
    </div>
</div>








//논블로킹..블로킹.. 비동기..동기  https://joooing.tistory.com/entry/동기비동기-블로킹논블로킹 [joooing:티스토리]