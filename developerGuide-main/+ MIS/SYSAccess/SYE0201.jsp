<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%--
File Name : SYE0201.jsp
Description : 관리자 접근제어
author : 이정민
since : 2022.05.09

Modification Information
수정일     수정자   수정내용
---------- -------- ---------------------------
2022.05.09 이정민 최초 생성
--%>
<html>
<head>
<title>관리자 접근제어[SYE0201]</title>
<script type="text/javascript">
var mstGrid = null, hstGrid = null, dlgMst = null;
var _LST_APRH_CTRL_ID = null;

$(document).ready(function() {
    
    var usrBox = $('#usrBox');

    /* ============================================================================== */
    /*                              button Event                                      */
    /* ============================================================================== */
    
    /* 조회 버튼 이벤트 */
    $('#btnSearch').click(function(){
        fn_SearchMngrAprhCtrlList();
    });
    
    /* 저장 버튼 이벤트 */
    $('#btnSave').click(function(){
        fn_SaveMngrAprhCtrl();
    });
    
    /* 추가 버튼 이벤트  */
    $('#btnAdd').click(function(){
        $('#usrBox').resetFormData({ 
            stateFlag   : 'I',
            sysCls      : 'A'
        });
        $('#usrBox input[name=startIpA]').focus();
    });
    
    /* 삭제 버튼 이벤트 */
    $('#btnDelete').click(function(){
        fn_DeleteMngrAprhCtrl();
    });

    /* ============================================================================== */
    /*                                    Grid                                        */
    /* ============================================================================== */
    
    var cmodels = [
         {name:'stateFlag',             cname: '상태',                                             width:'100',    align:'left',    hidden:true,   editable:true,  edittype:"text"}
        ,{name:'usrId',                 cname:'등록자아이디',           index:'usrId',               width:'100',    align:'left',    hidden:true,   editable:true,  edittype:"text"}
        ,{name:'regEmpNo',              cname:'등록자사번',            index:'regEmpNo',             width:'40',     align:'center',     hidden:false,    editable:false, edittype:"text"}
        ,{name:'usrNm',                 cname:'등록자성명',            index:'usrNm',               width:'40',      align:'center',     hidden:false,    editable:false, edittype:"text"}
        ,{name:'mngrIpAprhCtrlUseYn',   cname:'관리자IP접근제어 사용여부', index:'mngrIpAprhCtrlUseYn', width:'0',       align:'center', hidden:true,     editable:false,    edittype:"text"}
        ,{name:'aprhCtrlId',            cname:'접근제어번호',           index:'aprhCtrlId',          width:'0',      align:'center',       hidden:true,    editable:false, edittype:"text"}
        ,{name:'begnIp',                cname:'시작IP',               index:'begnIp',              width:'80',     align:'center',     hidden:false,     editable:false,    edittype:"text"}
        ,{name:'endIp',                 cname:'끝IP',                index:'endIp',               width:'80',      align:'center', hidden:false,     editable:false,    edittype:"text"}
        ,{name:'rmk',                   cname:'비고',                index:'rmk',                 width:'90',       align:'left',     hidden:false,    editable:false, edittype:"text"}
        ,{name:'regDt',                 cname:'등록일시',             index:'regDt',                width:'60',      align:'center', hidden:false,     editable:false,    edittype:"text", formatter: fmDtmSet}
        ,{name:'lastChgDt',             cname:'수정일시',             index:'lastChgDt',            width:'60',      align:'center', hidden:false,     editable:false,    edittype:"text", formatter: fmDtmSet}
        ,{name:'secuSetId',             cname:'공통보안설정번호',       index:'secuSetId',           width:'0',        align:'center', hidden:true,     editable:false,    edittype:"text"}
    ];

    mngrAprhCtrlGrid = pierp.grid.create('#mngrAprhCtrlList', cmodels, pierp.grid.getReadProp({
        height      : 240,
        shrinkToFit : true,
        onSelectRow : function(rowid){
            fn_CopyMngrAprhList2UsrBox(rowid);
        }
    }));
    


    /* ============================================================================== */
    /*                                   Etc Event                                    */
    /* ============================================================================== */

    
    /* IP 접근제어 설정 변경에 따른 이벤트 */
    $('#mstBox [name=mngrIpAprhCtrlUseYn]').change(function(){
        var mngrIpAprhCtrlUseYn = $('#content').getFormData().mngrIpAprhCtrlUseYn;
        settingForUsrBox();
        
        var msg = "관리자 접근제어를 변경하면 현재 접속한 시스템 관리에 접속이 불가할 수 있습니다.\n그래도 IP 접근제어 변경을 진행하시겠습니까?"
    	pierp.ui.confirm(msg).then(function(btn){
            if(btn === 'yes'){
                pierp.trx.request({
                    url         : '<c:url value="/ajaxS/SYE0201/changeMngrAprhCtrl.do"/>',
                    params      : { mngrIpAprhCtrlUseYn : mngrIpAprhCtrlUseYn },
                    oDSs        : [ {type : 'form', id : 'itemData'} ],
                    callback    : function(status, data){
                        if(status === 'success' && data.status === 'success'){
                            var mngrIpAprhCtrlUseYn = data.objects.mngrIpAprhCtrlUseYn;
                            $('#mstBox').setFormData({
                                mngrIpAprhCtrlUseYn : mngrIpAprhCtrlUseYn
                            });
                            fn_SearchMngrAprhCtrlList();
                        }else if(data.serviceMessage){ } 
                    }
                });
            }else{
                fn_Init();
            }
        });
    });
    

    /* ============================================================================== */
    /*                                 HTML SETTING                                   */
    /* ============================================================================== */

    
    fn_Init();
});


/* ============================================================================== */
/*                              function Event                                    */
/* ============================================================================== */

/* 초기화  */
function fn_Init(){

    var aprhCtrlYn = "${aprhCtrlYn}";

    $('#content').setFormData({
        mngrIpAprhCtrlUseYn : aprhCtrlYn
    });

    if(aprhCtrlYn == "N"){
        $('#btnAdd').disableObj(true);
        $('#btnSave').disableObj(true);
        $('#btnDelete').disableObj(true);
        cfn_LockBox('usrBox');
    } else{
        $('#btnAdd').disableObj(false);
        $('#btnSave').disableObj(false);
        $('#btnDelete').disableObj(false);
        cfn_unLockBox('usrBox');
    }
    
    fn_SearchMngrAprhCtrlList();
}

/* date Formatter */
function fmDtmSet(cellvalue, options, rowObject){
    if( !cellvalue ) return '';
    cellvalue = cellvalue.replace(/:/gi,"");
    var mkvalue = cellvalue.substr(0,4) + "-" + cellvalue.substr(4,2) + "-" + cellvalue.substr(6,2) +" "+ cellvalue.substr(8,2) +":"+ cellvalue.substr(10,2)+ ":" + cellvalue.substr(12,2);
    return mkvalue;
}

/* UI Controll */ 
function settingForUsrBox(){
    var mngrIpAprhCtrlUseYn = $('#content').getFormData().mngrIpAprhCtrlUseYn;
  
    if(mngrIpAprhCtrlUseYn === "N"){
        $('#btnAdd').disableObj(true);
        $('#btnSave').disableObj(true);
        $('#btnDelete').disableObj(true);
        cfn_LockBox('usrBox');
    } else{
        $('#btnAdd').disableObj(false);
        $('#btnSave').disableObj(false);
        $('#btnDelete').disableObj(false);
        cfn_unLockBox('usrBox');
    }
}

/* UI Controll */ 
function cfn_unLockBox(id) {
    $("#"+id+" :input:not(button)").each(function () {
        var jqObj = $(this);
        switch (this.type) {
        case "text":
            if(jqObj.attr('edittype')=='date'){
                jqObj.attr('readonly', false);
                jqObj.datepicker('disable');
            }else{
                jqObj.removeClass('inp_read');
                this.readOnly = false;
            }
            break;
        case "radio":
            this.disabled = false;
            break;
        case "checkbox":
            this.disabled = false;
            break;
        case "select-one":
        case "select-multiple":
            $(this).disableObj(false);
            break;
        default:
            jqObj.removeClass('inp_read');
            this.readOnly = false;
            break;
        }
    });
}

/* data Setting */
function fn_CopyMngrAprhList2UsrBox(rowid){
    rowData = $('#mngrAprhCtrlList').getRowData(rowid);
    
    var aprhCtrlId          = rowData.aprhCtrlId;
    var begnIp              = rowData.begnIp;
    var endIp               = rowData.endIp;
    var rmk                 = rowData.rmk;
    
    var begnIpArry = begnIp.split(".");
    var endIpArry = endIp.split(".");
      
    $('#content').setFormData({
        aprhCtrlId  : aprhCtrlId,
        rmk         : rmk,
        startIpA    : begnIpArry[0],
        startIpB    : begnIpArry[1],
        startIpC    : begnIpArry[2],
        startIpD    : begnIpArry[3],
        endIpA      : endIpArry[0],
        endIpB      : endIpArry[1],
        endIpC      : endIpArry[2],
        endIpD      : endIpArry[3]
    });
    
    settingForUsrBox();
}

/* transaction - Delete */
function fn_DeleteMngrAprhCtrl(){
    var message = "삭제하시겠습니까?";
    pierp.ui.confirm(message).then(function(btn){
        if(btn === 'yes'){
            $('#usrBox').setFormData({ sysCls : 'A' });
            pierp.trx.request({
                url        : '<c:url value="/ajaxD/SYE0201/deleteMngrAprhCtrl.do"/>',
                iDSs       : [{ type : 'form', id : 'content', name : 'vo' }],
                callback   : function(status, data){
                    if(status === 'success' && data.status === 'success'){
                        _LST_APRH_CTRL_ID = data.objects.aprhCtrlId;
                        fn_SearchMngrAprhCtrlList(_LST_APRH_CTRL_ID);
                        $('#usrBox').resetForm();
                    }else if(data.serviceMessage){ }
                }
            });
        }
    });
}

/* transaction - Save */
function fn_SaveMngrAprhCtrl(){

    var message = "저장하시겠습니까?";
    
    var startIpA = $('#usrBox input[name=startIpA]').val();
    var startIpB = $('#usrBox input[name=startIpB]').val();
    var startIpC = $('#usrBox input[name=startIpC]').val();
    var startIpD = $('#usrBox input[name=startIpD]').val();
    
    var endIpA = $('#usrBox input[name=endIpA]').val();
    var endIpB = $('#usrBox input[name=endIpB]').val();
    var endIpC = $('#usrBox input[name=endIpC]').val();
    var endIpD = $('#usrBox input[name=endIpD]').val();
    
    var begnIp = startIpA + '.' + startIpB + '.' + startIpC + '.' + startIpD;
    var endIp  = endIpA   + '.' + endIpB   + '.' + endIpC   + '.' + endIpD;
    
    $('#usrBox').setFormData({
        begnIp : begnIp,
        endIp : endIp
    });
    
    var aprhCtrlId = $('#usrBox').getFormData().aprhCtrlId;
    if(aprhCtrlId == ''){
        $('#usrBox').setFormData({ stateFlag : 'I' });
    }else{
        $('#usrBox').setFormData({ stateFlag : 'U' });
    }
    
    $('#usrBox').setFormData({ sysCls : 'A' });
    
    pierp.ui.confirm(message).then(function(btn){
        if(btn === 'yes'){
            pierp.trx.request({
                url    : '<c:url value="/ajaxS/SYE0201/saveMngrAprhCtrl.do"/>',
                iDSs   : [{ type : 'form', id : 'content', name : 'vo' }],
                callback : function(status, data){
                    if(status === 'success' && data.status === 'success'){
                        _LST_APRH_CTRL_ID = data.objects.aprhCtrlId;
                        fn_SearchMngrAprhCtrlList(_LST_APRH_CTRL_ID);
                    }else if(data.serviceMessage){ }
                }
            });
        }
    });
}

/* transaction - Search */
function fn_SearchMngrAprhCtrlList(args){
    pierp.trx.request({
        url         : '<c:url value="/ajaxR/SYE0201/selectMngrAprhCtrlList.do"/>',
        iDSs        : [ {type: 'form', id: 'usrBox', name : 'vo'} ],
        oDSs        : [ {type:'grid', id:'mngrAprhCtrlList'} ],
        callback    : function(status) {
            if(_LST_APRH_CTRL_ID == null){
                mngrAprhCtrlGrid.setSelection(0, true);
                return;
            }
            pierp.grid.setSelection('#mngrAprhCtrlList', {aprhCtrlId : args});
            $('#usrBox').setFormData({ stateFlag : 'U' });
        }
    });
}

/* Validate & IP input check */
function fn_copyToEndIpA(){
    var startIpA = $('#usrBox').getFormData().startIpA;
    
    if(isNaN(startIpA) == true){
        pierp.ui.alert('숫자만 입력해주세요');
        $('#usrBox').setFormData({
            startIpA : '',
            endIpA   : ''
        });
        return false;
    }
    
    if( (parseInt(startIpA) > 255) || (parseInt(startIpA < 0)) ){
        pierp.ui.alert('0 ~ 255 숫자를 입력해 주세요');
        $('#usrBox').setFormData({
            startIpA : '',
            endIpA   : ''
        });
        return false;
    }
    
    $('#usrBox').setFormData({
        endIpA   : startIpA
    });
}

/* Validate & IP input check */
function fn_copyToEndIpB(){
    var startIpB = $('#usrBox').getFormData().startIpB;
    if(isNaN(startIpB) == true){
        pierp.ui.alert('숫자만 입력해주세요');
        $('#usrBox').setFormData({
        	startIpB : '',
        	endIpB   : ''
        });
        return false;
    }
    if( (parseInt(startIpB) > 255) || (parseInt(startIpB < 0)) ){
        pierp.ui.alert('0 ~ 255 숫자를 입력해 주세요');
        $('#usrBox').setFormData({
            startIpB : '',
            endIpB   : ''
        });
        return false;
    }
    
    $('#usrBox').setFormData({
        endIpB : startIpB
    });
}

/* IP Validate */
function fn_copyToEndIpC(){
    var startIpC = $('#usrBox').getFormData().startIpC;
    if(isNaN(startIpC) == true){
        pierp.ui.alert('숫자만 입력해주세요');
        $('#usrBox').setFormData({
        	startIpC : '',
        	endIpC   : ''
        });
        return false;
    }
    if( (parseInt(startIpC) > 255) || (parseInt(startIpC < 0)) ){
        pierp.ui.alert('0 ~ 255 숫자를 입력해 주세요');
        $('#usrBox').setFormData({
            startIpC : '',
            endIpC   : ''
        });
        return false;
    }
}

/* IP Validate */
function fn_checkNumber(){
    var startIpD = $('#usrBox').getFormData().startIpD;
    var endIpD = $('#usrBox').getFormData().endIpD;
    
    if(isNaN(endIpD) == true || isNaN(startIpD)){
        pierp.ui.alert('숫자만 입력해주세요');
        $('#usrBox').setFormData({
        	startIpD : '',
        	endIpD   : ''
        });
        return false;
    }
    
    if( (parseInt(startIpD) > 255) || (parseInt(startIpD < 0)) || (parseInt(endIpD) > 255) || (parseInt(endIpD < 0)) ){
        pierp.ui.alert('0 ~ 255 숫자를 입력해 주세요');
        $('#usrBox').setFormData({
            startIpD : '',
            endIpD   : ''
        });
        return false;
    }
}
</script>
</head>
<body>
<!-- 서브 메뉴 타이틀 & 탑 버튼 -->
<jsp:include page="/WEB-INF/jsp/common/moduleHeader.jsp"/>
<!-- 내용이 들어가는 곳 -->
<div id="content">
    <div class="left_box" style="width: 100%;">
        <div class="title2_end_btn">
            <div class="title2_box">IP 접근제어 설정</div>
        </div>
        <table id="mstBox" class="detail_table" border="0" cellspacing="0" cellpadding="0">
            <colgroup>
                <col width="20%"/>
                <col width="80%"/>
            </colgroup>
            <tr>
                <th>IP 접근제어</th>
                <td>
                    <pierp:radio name="mngrIpAprhCtrlUseYn" value="Y" label="사용함"/>
                    <pierp:radio name="mngrIpAprhCtrlUseYn" value="N" label="사용안함" />
                </td>
            </tr>
        </table>
        
        <div class="title2_end_btn">
            <div class="title2_box">IP 접근제어 목록</div>
            <div class="right_btn_box">
                <pierp:button bttn="${bttnMap['btnSearch']}" id="btnSearch" label="조회" type="2"/>
            </div>
        </div>
        
        <pierp:grid id="mngrAprhCtrlList"/>
        
        <div id="usrBox" class="col">
            <div class="title2_end_btn">
                <div class="title2_box">IP 접근제어 상세</div>
                <div class="right_btn_box">
                    <pierp:button bttn="${bttnMap['btnAdd']}"       id="btnAdd"      label="추가"  type="2"/>
                    <pierp:button bttn="${bttnMap['btnSave']}"      id="btnSave"     label="저장"  type="2"/>
                    <pierp:button bttn="${bttnMap['btnDelete']}"    id="btnDelete"   label="삭제"  type="2"/>
                </div>
            </div>
            <table id="" class="detail_table" border="0" cellspacing="0" cellpadding="0">
                <input type="hidden" name ="begnIp" value="">
                <input type="hidden" name ="endIp" value="">
                <input type="hidden" name ="stateFlag" value="">
                <input type="hidden" name ="aprhCtrlId" value="">
                <input type="hidden" name ="sysCls" value="A">
                <colgroup>
                    <col width="20%"/>
                    <col width="80%"/>
                </colgroup>
                <tr>
                    <th>시작IP</th>
                    <td>
                        <pierp:number name="startIpA"  title="" size="3" maxlength="3" onkeyup = "fn_copyToEndIpA()"/>.
                        <pierp:number name="startIpB"  title="" size="3" maxlength="3" onkeyup = "fn_copyToEndIpB()"/>.
                        <pierp:number name="startIpC"  title="" size="3" maxlength="3" onkeyup = "fn_copyToEndIpC()"/>.
                        <pierp:number name="startIpD"  title="" size="3" maxlength="3" onkeyup = "fn_checkNumber()"/>
                    </td>
                </tr>
                <tr>
                    <th>끝IP</th>
                    <td>
                        <pierp:number name="endIpA" readonly ="true"  title="" maxlength="3" size="3" disabled="true"/>.
                        <pierp:number name="endIpB" readonly ="true"  title="" maxlength="3" size="3" disabled="true"/>.
                        <pierp:number name="endIpC"  title="" maxlength="3" size="3" onkeyup = "fn_copyToEndIpC()"/>.
                        <pierp:number name="endIpD"  title="" maxlength="3" size="3" onkeyup = "fn_checkNumber()"/>
                    </td>
                </tr>
                <tr>
                    <th>비고</th>
                    <td>
                        <textarea name="rmk" cols="" rows="11" style="width:99%" imeMode="inactive"></textarea>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>

</body>
</html>