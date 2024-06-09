<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
File Name : TODB0101.jsp
Description :
	TO-DO 나의할일 화면

Modification Information
수정일 수정자 수정내용
------- -------- ---------------------------
2022.05.17. 이정민 목록 조회

author : 이정민
since : 2022.05.17.
--%>
<html>
<head>
<style>

</style>
    <title>TODO 나의할일</title>
<script>

$(document).ready(function(){

	pieip.ui.bsDtpicker($("#datepicker .date"));

	/* tab */
	var todoTab = $(".todo_tab > li > a");
	todoTab.on('click', function() {
		$(this).parent().addClass('on').siblings().removeClass('on');
        var todoDiv = $(this).parent().data("id");
		location.href = '<c:url value="/eip/TOD/' + todoDiv + '"/>';
		return false;
	});


    /* ============================================================================== */
    /*                              button Event                                      */
    /* ============================================================================== */

	$('#btnSearch').click(function(ev) {
		search();
	});


	initSearchForm(); //검색폼 초기

	//검색
	search().done(function() {
// 		console.log("search done");
	})

});


/* ============================================================================== */
/*                              function Event                                    */
/* ============================================================================== */


/* 검색폼 초기화 */
function initSearchForm() {
    //enterkey event
	pieip.ui.addEvtSearchText();
	$("#searchField").val("${cmd.searchField}");

	//tab 초기화
	var todoTab = $(".todo_tab > li:nth(0)");
	todoTab.addClass('on');
}


/* transaction - Search */
function search(args) {

	$("#DataList > tbody").html("");

	var params = $("#searchForm").serialize();

	var thCnt = $('#sortHeader > th').length;

	return pieip.trx.postJson(contextPath + "/ajaxR/TODA0101/selectTodoList.do?"  + params, function(data) {
		if(data.status != "success") {
			//egovBizException
			pieip.page.printListMsg(thCnt, data.serviceMessage);
		}else {

			var resObject = data.objects;

			//data가 없을 때
			if(resObject.todoVOList.records == "0") {
				pieip.page.printListMsg(thCnt, "데이터가 없습니다.");
			}

			//성공일때 페이징 처리
			 pieip.page.renderPaging(contextPath + "/ajaxR/TODB0101/selectTodoCnt.do");

			$.each(resObject.todoVOList.rows, function(index, vo) {
				appendListRow(index, vo);
			});

			if(args != null && args != ""){
			    pieip.page.paging(1);
			}

		}
	}).fail(function() {
		pieip.page.printListMsg(thCnt, "데이터 조회 오류입니다.");
	})


}



function appendListRow(index, rowData){
	if(!rowData) return;

	var todoVO = rowData;

	var chkBox  = '<label class="checkbox-inline"><input type="checkbox" disabled></label>';
		chkBox = '<label class="checkbox-inline" for="'+index+'">' +
		         '<input type="checkbox" id="'+index+'" name="'+index+'" data-id="'+todoVO.todoId+'" value="'+todoVO.todoId+'" onclick="updateImpr(\'' + index +'\');"/></label>';

	var delayBtn = '<span id="delay_btn" class="btn delay_btn" onclick="delay(\'' + index +'\');">보류</span>';

	var todoStat = "";
	switch(todoVO.statCd) {
		case 'C':
			todoStat = "<span class='yellow'>회수</span>" ;
			break;
		case 'D':
			todoStat = " <span class='blue'>완료</span>";
			break;
		case 'J':
			todoStat = "<span class='apply'>반려</span>";
			break;
		case 'P':
			todoStat = "<span class='green'>진행중</span>";
			break;
		case 'R':
			todoStat = "<span class='pink'>요청</span>";
			break;
		case 'S':
			todoStat = "<span class='yellow'>보류</span>";
			break;
		  default:
			todoStat = "";
	}

	$("#DataList > tbody").append(
	    '<tr data-id="'+todoVO.todoId+'" data-seq="' + todoVO.seq + '" data-objt="' + todoVO.objt +'" data-stat="'+ todoVO.statCd +'" data-impr="'+ todoVO.impr +'">' +
			'<td>' + chkBox + '</td>' +
			'<td>' + delayBtn + '</td>' +
			'<td>' + todoVO.tyCdNm + '</td>' +
			'<td>' + todoStat + '</td>' +
			'<td title="상세보기" class="t_left"><a onClick="javascript:viewDetail(this);">'+ todoVO.cntn + '</a></td>' +
			'<td>' + todoVO.rqsterNm + '</td>' +
			'<td>' + todoVO.rqstNo + '</td>' +
			'<td>' + strToDateFormat(todoVO.rqstDt, "YYYY-MM-DD HH:mm","YYYYMMDDHHmmss") +'</td>' +
		'</tr>'
	);

	// 중요 선택이된 check 박스를 선택 표시한다.
    if(todoVO.impr == 'A'){
        $("input:checkbox[id="+ index +"]").prop("checked", true);
    } else {
        $("input:checkbox[id="+ index +"]").prop("checked", false);
    }
}

function viewDetail(){
    alert("해당 업무로 이동");
}


/**
 * 해당 TO-DO 보류 상태값 전환
 */
function delay(idx){

    var id = $('#DataList tbody').children('tr:eq(' + idx + ')').data('id');
    var seq = $('#DataList tbody').children('tr:eq(' + idx + ')').data('seq');
    var objt = $('#DataList tbody').children('tr:eq(' + idx + ')').data('objt');
    var statCd = $('#DataList tbody').children('tr:eq(' + idx + ')').data('stat');

    var param = new Object;

    param.todoId = id;
    param.seq = seq;
    param.objt = objt;
    param.statCd = statCd;
    param.todoDiv = 'A';

    var message = "선택하신 TO-DO를 보류 상태로 변경하시겠습니까?";

    pierp.ui.confirm(message).then(function(btn) {
        if (btn === 'yes') {
            var url = '<c:url value="/ajaxS/TODB0101/updateTodoStat.do" />';

            pierp.trx.request({
                block : true,
                url : url,
                params : { bpTodoForm : param },
                callback : function(status) {
                    search();
                }
            }); //request

        } else {
            return;
        }
    });
}


/**
 * 해당 TO-DO 중요 상태값 전환
 */
function updateImpr(idx){

    var id = $('#DataList tbody').children('tr:eq(' + idx + ')').data('id');
    var seq = $('#DataList tbody').children('tr:eq(' + idx + ')').data('seq');
    var objt = $('#DataList tbody').children('tr:eq(' + idx + ')').data('objt');
    var impr = $('#DataList tbody').children('tr:eq(' + idx + ')').data('impr');

    var param = new Object;

    if(impr == null){
        impr = "";
    }

    param.todoId = id;
    param.seq = seq;
    param.objt = objt;
    param.impr = impr;

    var url = '<c:url value="/ajaxS/TODB0101/updateTodoImpr.do" />';

    pierp.trx.request({
        block : true,
        url : url,
        params : { bpTodoForm : param },
        callback : function(status) {
            search("fixPage");
        }
    });
}

</script>
</head>
<body class="contents_bg">
	<header><h2 class="sub_title">TO-DO</h2></header>

	<div class="contents_wrap_board">
		<div class="sub_con_all">

		   <ul class="todo_tab">
		       <li data-id="TODB0101.do"><a href="#">나의 할일</a></li>
		       <li data-id="TODB0102.do"><a href="#">진행 상황</a></li>
		       <li data-id="TODB0103.do"><a href="#">완료 내역</a></li>
		       <li data-id="TODB0104.do"><a href="#">보류 목록</a></li>
		   </ul>

	       <!-- contents start -->
	       <nav class="navbar search-form">
	        <div class="top_wrap">
		        <p class="total_txt navbar-text">
		          <span>총 게시물 <strong id="totalRowCount">000</strong> 개,
		              <span>페이지 <strong id="pageIndex">1</strong>/<em id="totalPage">0</em></span>
		          </span>
		        </p>

  			<div class="sort_right">
				<form role="search" name="searchForm" id="searchForm">

		           <!--  파라미터 전송 -->
		            <input hidden="hidden" title="검색폼" >  <!-- 엔터 자동 submit 방지 -->
		            <input type="hidden" name="pageIndex" value="${cmd.pageIndex }">
		            <input type="hidden" name="pageSize" value="${cmd.pageSize }">
		            <input type="hidden" name="todoDiv" value="A">

         		    <div class="cal-group">
                       <div class="input-daterange input-group" style="/*margin-right: 81px;*/">
                           <div id="datepicker" class="input-daterange input-group">
                               <div class="date">
                                   <input type="text" class="input-sm form-control" name="rangeStartDate" id="rangeStartDate"  value="${cmd.rangeStartDate}" title="검색날짜 입력" autocomplete="off">
                                   <span class="input-group-addon input-radius"><i class="far fa-calendar-alt"></i></span>
                               </div>
                               <span class="input-group-addon input-between">~</span>
                               <div class="date">
                                   <input type="text" class="input-sm form-control" name="rangeEndDate" id="rangeEndDate"  value="${cmd.rangeStartDate}" title="검색날짜 입력"  autocomplete="off">
                                   <span class="input-group-addon"><i class="far fa-calendar-alt"></i></span>
                               </div>
                           </div>

                       </div>
                       <div class="btngruop todo-period">
                        <span class="btn_date"><a href="#" onclick="javascript:pieip.ui.dtPickerSet(this);">전체</a></span>
                        <span class="btn_date"><a href="#" onclick="javascript:pieip.ui.dtPickerSet(this, 'week');">1주</a></span>
                        <span class="btn_date"><a href="#" onclick="javascript:pieip.ui.dtPickerSet(this, 'month');">1달</a></span>
                        <span class="btn_date"><a href="#" onclick="javascript:pieip.ui.dtPickerSet(this, 'sixmonth');">6개월</a></span>
                   	   </div>


                       <div class="state">
                           <label for="search">선택</label>
                           <select class="sub_select" style="padding-right: 50px;" id="searchField" name="searchField" title="검색선택" >
                               <option value="">-전체-</option>
                               <option value="cntn">제목(내용)</option>
                               <option value="rqstNo">고유번호</option>
                               <option value="rqsterNm">요청자(요청부서)</option>
                               <option value="tyCdNm">업무구분</option>
                           </select>
                       </div>
                       <div style="float:left;margin-left:5px;">
                           <label for="search_member">검색</label>
                           <input type="text" class="search_list base" placeholder="검색어를 입력해주세요" title="검색어 입력" name="searchText" value=""/>
                           <span id="btnSearch" class="btn_find sbt">검색</span>
                       </div>
                   </div>
		         </form>
				</div><!-- sortRight -->
	        </div>
	       </nav>

			<form name="applyForm" id="applyForm"></form> <!-- 목록 반영 -->

			<table id="DataList"  class="type_list todo_list"  class="table table-bordered board-basic">
			<caption>전체공지 조회</caption>
            <colgroup>
                <col style="width:6%">
                <col style="width:10%">
                <col style="width:10%">
                <col style="width:10%">
                <col style="width:24%">
                <col style="width:10%">
                <col style="width:10%">
            </colgroup>
            <thead>
                <tr id="sortHeader"> <%-- 컬럼정렬시  id, 해당 컬럼에 em 및 data 요소 추가 --%>
                    <th scope="col" class="first">　중요</th>
                    <th scope="col">보류</th>
                    <th scope="col">업무구분</th>
                    <th scope="col">상태구분</th>
                    <th scope="col">제목</th>
                    <th scope="col">요청자</th>
                    <th scope="col">고유번호</th>
                    <th scope="col" class="last">요청일</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                     <td class="t_left" colspan="8">로딩중...</td>
                </tr>
            </tbody>
			</table>

            <div class="bottom_wrap">
                <nav class="page-area">
                    <div id="paging"></div>
                </nav>
            </div>

        </div> <!-- subConAll end -->
    </div>
</body>
</html>

