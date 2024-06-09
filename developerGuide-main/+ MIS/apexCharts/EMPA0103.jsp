<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
File Name : EMPA0101.jsp
Description :
Modification Information
수정일 수정자 수정내용
------- -------- ---------------------------
2020.05.06. 김영기 최초 생성

author : 김영기
since : 2020.05.06.
--%>
<html>
<head>
<title>${selMdulVo.mdulNm}</title>

<%@include file="/WEB-INF/jsp/common/import_apexcharts.inc"%>


<script type="text/javascript">


$(document).ready(function() {
	
  /* 검색 결과 리스트  */
	var cmodels = [
		 {name:'rqstNo',       cname: '등록번호',    width:'120', align:'center',   hidden:false, editable:false, edittype:"text"}
		,{name:'detailPop',    cname: '보기',        width:'40',  align:'center',   hidden:false, editable:false,  formatter:btnRqstPop, excel:false}
		,{name:'srcRqstEmpNm', cname: '작성자',      width:'65',  align:'center',   hidden:false, editable:false, edittype:"text"}
		,{name:'submitDt',     cname: '접수일자',    width:'85',  align:'center',   hidden:false, editable:false, edittype:"date"}
		,{name:'srcLimitDt',   cname: '제출기한',    width:'85',  align:'center',   hidden:false, editable:false, edittype:"date"}
		,{name:'srcLimitTm',   cname: '마감시간',    width:'65',  align:'center',   hidden:false, editable:false, edittype:"time"}
		,{name:'rqstOrgCd',    cname: '요청기관',    width:'140', align:'left',     hidden:false, editable:false, edittype:'select', editoptions:{value:<pierp:gs sid="bizGEA.selectGEA015List"/>}}
        ,{name:'rqstOrgNm',    cname: '기타명',      width:'140', align:'left',     hidden:false, editable:false, edittype:"text"}
		,{name:'docuTitle',    cname: '제목',        width:'360', align:'left',   	hidden:false, editable:false, edittype:"text"}
	    ,{name:'urgentYn',     cname: '긴급여부',    width:'70',  align:'center',   hidden:false, editable:false, edittype:"text"}
	    ,{name:'dataCls',      cname: '자료구분',    width:'70',  align:'center',   hidden:false, editable:false, edittype:"text"}
		,{name:'submitYn',     cname: '진행상태',    width:'80',  align:'center',   hidden:false, editable:false, edittype:'select',  editoptions:{value:"N:접수중:;Y:제출완료:"}}
		,{name:'file',         cname: '첨부',        width:'60',  align:'center',   hidden:false, editable:false, edittype:"text", formatter:imageFormatter}
		,{name:'docId',        cname: '첨부1',        width:'90',  align:'center',   hidden:true,  editable:false, edittype:"text"}
		,{name:'docId2',       cname: '첨부2',        width:'90',  align:'center',   hidden:true,  editable:false, edittype:"text"}
		,{name:'srcRqstEmpNo', cname: '담당자',    	 width:'80',  align:'center',   hidden:true,  editable:false, edittype:"text"}
	];

	pierp.grid.create('#list', cmodels, pierp.grid.getReadProp({
	    shrinkToFit : true,
	    height : '80',
		onSelectRow  : function(rowId){
			var rowData = $('#list').getRowData(rowId);
		},
		ondblClickRow: function(rowId, iRow, iCol, e) {
		    var rowData = $('#list').getRowData(rowId);
		}
	}));

	//인원현황 : chartData
	
	
	
	var _chartData = new Map();
  
	
	pierp.trx.request({
    	url: '<c:url value="/ajaxR/EMPA0103/getDocuRqstLists.do"/>',
    	block:true,
    	oDSs:[
      		{type:'grid', id:'list', objkey:'data'}
    	],
    	callback: function(status, resData){
    	  
    			
    	  	  //name, data 세팅
    	     _chartData =  $.map(resData.objects.list.rows, function(item, idx){ 
    	       // return item.srcLimitTm;
    	       return {name:item.srcRqstEmpNm, value:item.srcLimitTm, key:idx}
    	     });
    	  	
    	     var _name =  _chartData.map(function(a) {return a.name});
    	     var _data =  _chartData.map(function(a) {return a.value});
    	     var _donutData = JSON.parse( "[" + _data.toString() + "]" );
    	     
    	     
    	     console.log("_donutData", _donutData);
    	     
    	     //Donut-Chart
    	     var donut = new Mychart("chartdonut", _donutData);
    	     donut.exec();
    	     
    	     
    	     //radialBar-Chart
    	     var radial = new Mychart("radialdonut", _donutData);
    	     radial.exec();
    	     
    	     
    	     //bar-chart
    	     var bar = new Mychart("chartbar",_data);
    	     bar.execGroup();
    	    
    	     
    	     //Line-Chart
    	     var line = new Mychart("chartline", _data, _name[0]);
    	     line.execName();

    	     
    	     //Area-Chart
    	     var _arrdataXy = arrData(_donutData);
    	     optionSet (_arrdataXy);
    	     
    	     
    	     //Adword-Chart
    	     var radial = new Mychart("chartAdword", _data);
    	     radial.execGroup();
    	     
    	     
    	     //two-Chart
    	     var twoLine = new Mychart("chartTwoLine", _data);
    	     twoLine.execGroup();
    	     
    	     
    	     //lineAdwords-Chart
    	     var lineAdwords = new Mychart("lineAdwords", _donutData);
    	     lineAdwords.execGroup();
    	     
    	     
    	}
  	});
	
	
	
	
	
	
	
	
	function imageFormatter(cellvalue, options, rowObject) {
	  if (options.colModel.name == 'file'){ // 첨부파일
		if(rowObject.docId){
		  return '<span class="btn_basic"><a onclick="callFilePopup(\''+options.rowId+'\');"><img id="apvImg" src="${contextPath}/resources/pierp/images/icon_apv.gif" style="cursor:pointer"></a></span>'
	  	}
	  }

	}

	// 닫기
	$('#btnClose').click(function() {
    	window.close();d
	});

    //Enter키 조회
	$('#searchBox').keydown(function(event) {
	  if(event.keyCode == 13) {
		 $('#btnSearch').trigger('click');
	  }
	});

	//조회
	var btnSearch = $('#btnSearch').click(function(ev){
	    pierp.trx.request({
	        url: '<c:url value="/ajaxR/EMPA0103/getDocuRqstLists.do" />',
	        iDSs: [
	            {type:'form', id:'searchBox'}
	        ],
	        oDSs: [
	            {type:'grid', id:'list'}
	        ],
	        callback: function(status) {
	        }
	    });
	});

	//엑셀 export
	var btnExcel = $('#btnExcel').click(function() {
	    pierp.grid.exportExcel({
	      fileName: '자료요청제출목록',
	      sheets: [
	         {grid: 'list', name: '자료요청제출목록'}
	      ]
	    });
	});

	fn_init();
});


	


//상세 팝업 버튼
function btnRqstPop(cellvalue, options) {
	return '<input type="button" value="상세" onclick="cfn_EMPAPop(' + options.rowId + ');" style="cursor:pointer">';
}

//상세 팝업
function cfn_EMPAPop(rowId){
  var rowData  = $('#list').getRowData(rowId);
  var rqstNo   = rowData.rqstNo;
  var docId    = rowData.docId2;
  var submitYn = rowData.submitYn;

  var param = {
      rqstNo : rqstNo,
      docId  : docId,
      submitYn : submitYn
  };

  cfn_PopUpModule({
      mdulId : 'EMPA0103P1',
      params : param,
      width  : 1280,
      height : 768,
      scroll : 'yes'
  });
};

//첨부파일 팝업
function callFilePopup(rowId) {

	var rowData  = $('#list').getRowData(rowId);
	var rqstNo   = rowData.rqstNo;
	var srcRqstEmpNo = rowData.srcRqstEmpNo;
	var docId    = rqstNo + srcRqstEmpNo;  //신청번호+사번

  	cfn_OpenFileAttach({
        programId : "DutyRptSubmit.do",
        docId	  :  docId
	});
}

//초기화
function fn_init(){
// 	var today = ('${today}').toString(10);

// 	var strMonth = "";
// 	var endMonth = "";
// 	var strDate = "";
// 	var endDate = "";

// 	if( today.substring(4,6) < 10 ) {
// 		strMonth = "0" + (parseInt(today.substring(4,6)) - 1).toString(10);
// 		endMonth = "0" + (parseInt(today.substring(4,6)) + 1).toString(10);
// 	} else {
// 		strMonth = (parseInt(today.substring(4,6)) - 1).toString(10);
// 		endMonth = (parseInt(today.substring(4,6)) + 1).toString(10);
// 	}
	
// 	strDate = (today.substring(6,8)).toString(10);
// 	endDate = (today.substring(6,8)).toString(10);
	
// 	var strDt = today.substring(0,4) + strMonth + strDate;
// 	var endDt = today.substring(0,4) + endMonth + endDate;

// 	$('#searchBox').resetFormData({
// 	   q_limitEndDt: endDt,
// 	   q_limitStrDt: strDt
// 	});
	
	
	$('#searchBox').resetFormData({
	   q_limitEndDt: '${toDt}',
	   q_limitStrDt: '${frmDt}'
	});

	fn_searchClick();

	//pierp.ui.alert(" 부서 소속원의 제출 내역에 대하여 확인 가능합니다.\n개인정보보호법에 위배되는 사항이나 개인정보 등의 민감정보를 포함시에는\n기획실 담당자와 먼저 협의하시기 바랍니다");
}

//팝업창 닫고 조회
function fn_searchClick(){
  $('#btnSearch').trigger('click');
}




</script>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/common/moduleHeader.jsp"/>

<div id="content">
	<input type="hidden" name = "empMdulYn" value="Y">
	
    <!-- 검색조건 내용 -->
    <div class="title2_end_btn">
        <div class="title2_box">조회 조건</div>
    </div>

    <!-- 검색조건 -->
    <table id="searchBox" class="detail_table" border="0" cellspacing="0" cellpadding="0">
        <tr>
                <th>등록번호</th>
                <td><pierp:text name="q_docNo" styleClass="w80"/></td>

                <th>요청기관명</th>
                <td><pierp:text name="q_rqstOrgNm" styleClass="w70"/></td>

                <th>제목</th>
                <td colspan="3"><pierp:text name="q_docuTitle" styleClass="w90"/></td>
           </tr>

           <tr>
                <th width="10%">제출기간</th>
                <td width="25%"><pierp:date name="q_limitStrDt"/> ~ <pierp:date name="q_limitEndDt"/></td>

                <th width="10%">진행상태</th>
                <td>
                    <pierp:select name="q_submitYn" sid="bizGEA.selectGEA018List" width="100px" first="전체" mode="layer" multi="false" filter="true" header="true" />
                </td>
                <th width="10%">긴급여부</th>
                <td>
                    <pierp:select name="q_urgentYn" sid="bizGEA.selectGEA013List" width="100px" first="전체" mode="layer" multi="false" filter="true" header="true" />
                </td>
                <th width="10%">자료구분</th>
                <td>
                    <pierp:select name="q_dataCls" sid="bizGEA.selectGEA014List" width="100px" first="전체" mode="layer" multi="false" filter="true" header="true" />
                </td>

           </tr>
     </table>


     <!-- 검색리스트 -->
    <div class="title2_end_btn">
        <div class="title2_box">제출 목록</div>
        <!--
        <div class="right_btn_box">
            <span id="btnExcel" class="btn_basic" authed="true"><a><span><i class="fas fa-file-import color-4"></i> 엑셀보기</span></a></span>
        </div>
        -->
    </div>

    <pierp:grid id="list" />
    
    
     <!-- chart view -->
     <div id="wrapper">
      <div class="content-area">
        <div class="container-fluid">
        
              <div class="row mt-5 mb-4">
                  <div class="col">
                    <div class="box">
                      <div id="donut"></div>
                    </div>
                  </div>
                  <div class="col">
                    <div class="box">
                      <div id="radial"></div>
                    </div>
                  </div>
              
              </div>
              
              
              <div class="row mt-5 mb-4">
                  <div class="col">
                    <div class="box">
                      <div id="bar"></div>
                    </div>
                  </div>
                  
                  <div class="col">
                    <div class="box">
                      <div id="chart"></div>
                    </div>
                  </div>
              </div>
              
              
              <div class="row mt-4 mb-4">
                  <div class="col-md-6">
                    <div class="box">
                      <div id="area"></div>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="box">
                       <div id="twoline"></div>
                    </div>
                  </div>
              </div>
        
        
        
        
              <div class="row mt-4 mb-4">
                  <div class="col-md-6">
                    <div class="box">
                     <div id="adwords"></div>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="box">
                       <div id="line-adwords"></div>
                    </div>
                  </div>
              </div>
          
        </div>
      </div>
    </div>
    

</div>

</body>
</html>
