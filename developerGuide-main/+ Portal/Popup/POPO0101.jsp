<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
File Name : POPO0101.jsp
Description : 업무포털 관리자 팝업 관리 > 팝업관리 목록 화면
author : 이정민
since : 2022.05.24
Modification Information
수정일 수정자 수정내용
------- -------- ---------------------------
2022.05.24. 이정민 목록 조회

--%>
<html>
<head>
    <title>팝업 관리 목록</title>
<script>

$(document).ready(function(){

    //날짜 formatter
    $("#rangeStartDate").inputmask("9999-99-99");
    $("#rangeEndDate").inputmask("9999-99-99");

    /* ============================================================================== */
    /*                                   Etc Event                                    */
    /* ============================================================================== */

    //검색 datepicker
    pieip.ui.bsDtpicker($("#datepicker .date")); //공통함수 : datepicker 한꺼번에 주기

    /* ============================================================================== */
    /*                              button Event                                      */
    /* ============================================================================== */

    $('#btnSearch').click(function(ev){
        searchForm.pageIndex.value = "1";
        search();
    });


    $('#btnDelete').click(function(ev){
        checkedDelete(); //목록 체크 삭제
    });


    $('#btnCreate').click(function(ev){
        create();   //등록 화면 이동
    });

    /* ============================================================================== */
    /*                                 HTML SETTING                                   */
    /* ============================================================================== */

    initSearchForm(); //검색폼 초기화 후



});





/* ============================================================================== */
/*                              function Event                                    */
/* ============================================================================== */

/* 검색폼 초기화 */
function initSearchForm() {

  //각 searchForm의 자동 submit 방지를 위해 <input hidden="hidden"> 추가
  pieip.ui.addEvtSearchText(); //공통함수 : searchText 엔터시 search() 실행
  $("#searchField").val("${cmd.searchField}");  //검색콤보  초기화

  //검색 datepicker
  if("${cmd.rangeStartDate}" != "") {
    pieip.ui.bsDtpicker($("#rangeStartDate"), strToDateFormat("${cmd.rangeStartDate}", 'yyyy-MM-DD'));
  }

  if("${cmd.rangeEndDate}" != "") {
	pieip.ui.bsDtpicker($("#rangeEndDate"), strToDateFormat("${cmd.rangeEndDate}", 'yyyy-MM-DD'));
  }

  // rageDate 설정에따른 게재일자, 등록일자 구분별 Radio 버튼 셋팅
  if("${cmd.rangeDate}" != "") {
	pieip.ui.radioValSet("rangeDate", "${cmd.rangeDate}");
  }else{
	pieip.ui.radioValSet("rangeDate", "A");
  }

  searchForm.pageSize.value = pieip.page.readLocalPageSize(); //쿠키 페이지 사이즈 값

  search(); //검색
}


/* 조회 */
function search(){
//조회 데이터 출력을 위한 초기화
$("#DataList > tbody").html("");

var params = $("#searchForm").serialize() + getNoCacheParam();

return pieip.trx.postJson(contextPath + "/ajaxR/POPO0101/selectPupList.do?" + params, function(data){
    if(data.status != "success"){
        //EgovBizException
        pieip.page.printListMsg('', data.serviceMessage);
    }else {
        var resObject = data.objects;
        //데이터가 없을 때
        if(resObject.pupVOList.records == "0"){
           pieip.page.printListMsg('', "데이터가 없습니다.");
        }
        //성공일때 페이징 처리
        pieip.page.renderPaging(contextPath + "/ajaxR/POPO0101/selectPupCnt.do");  //목록 카운트 URLs

        $.each(resObject.pupVOList.rows, function(index, vo){ //받은 객체 루프..
          appendListRow(index, vo);
        });
    }
}).fail(function(){//시스템 오류
  pieip.page.printListMsg('', "데이터 조회 오류입니다.");
});
} //search()


/* Grid Setting */
function appendListRow(index, rowData){
    if(!rowData){
        return;
    }

    var pupVO = rowData;
    var chkBox  = '<label class="checkbox-inline"><input type="checkbox" disabled></label>';

    var inputBox = '<input class="form-control" type="inputbox" title="노출순서 입력">';
    chkBox = '<label class="checkbox-inline" for="'+index+'"><input type="checkbox" id="'+index+'" name="'+index+'" data-id="'+pupVO.pupNo+'" value="'+pupVO.pupNo+'"/></label>';

    var dispStat = "";
    var bBetween = todayIsBetween(pupVO.pstpBegnDtm, pupVO.pstpClseDtm);

    if(bBetween) {
      //게시중
      dispStat = "<b>게재중</b>";
    }else {
      var bBefore = todayIsBefore(pupVO.pstpBegnDtm);
      if(bBefore) {
        dispStat = "게재전";
      }else{
        dispStat = "게재종료";
      }
    }

    $("#DataList > tbody").append(
        '<tr data-id="'+pupVO.pupNo+'">' +
            '<td>'+ chkBox +'</td>' +
            '<td>'+ eip.prop.getVal("rgtrLoca", pupVO.rgtrLoca)+ '</td>' +                                      // 위치
            '<td title="상세보기" class="t-left"><a onClick="javascript:viewDetail(this);">'+pupVO.ttl+'</a></td>' +            // 팝업명
            '<td>'+ eip.prop.getVal("tmpSaveYn", pupVO.tmpSaveYn)+ '</td>' +                                    // 저장상태
            '<td>'+ dispStat +'</td>' +                                                                         // 게재 상태
            '<td>'+ pupVO.mndtAnucYn +'</td>' +                                                                 // 필수 공지여부
            '<td>'+ strToDateFormat(pupVO.pstpBegnDtm, "YYYY-MM-DD HH:mm","YYYYMMDDHHmmss") +'</td>' +          // 게재 시작일시
            '<td>'+ strToDateFormat(pupVO.pstpClseDtm, "YYYY-MM-DD HH:mm","YYYYMMDDHHmmss") +'</td>' +          // 게재 종료일시
            '<td>'+ nullToEmpty(pupVO.lastChgUsrId)+'</td>' +                                                   // 등록자
        '</tr>'
    );
}

/* 신규 생성화면 이동 */
function create() {
  viewDetail();
}

/* 상세 (수정) 페이지로 이동 */
function viewDetail(obj) {
  var url = '<c:url value="/mgt/POP/POPO0102.do" />';
  var selectedId = $(obj).closest('tr').data("id");
  var appendText = "";

  if ( typeof selectedId != "undefined") {
    appendText = '<input type="hidden" name="selectedId" value="' + selectedId + '">';
  }

  var paramForm = $("#searchForm");
  pieip.page.goPage(url, paramForm, appendText);
}



/* 목록 체크 삭제 확인 */
function checkedDelete() {
  var chkCnt = 0;

  $("#applyForm").html(""); //적용폼 초기화
  $("#DataList > tbody").find("input[type=checkbox]").each(function() {
      if($(this).is(":checked")){
        chkCnt++;
        $("#applyForm").append('<input type="hidden" name="pupNo" value="' + $(this).val() + '">');
      }
  });

  if(chkCnt <= 0) {
      pierp.ui.alert("삭제 할 항목을 체크해 주세요.");
      return;
  }

  var message = "선택하신 팝업이 삭제 됩니다.\n삭제하시겠습니까?";
  pierp.ui.confirm(message).then(function(btn) {
      if (btn === 'no') {
        return;
      }
      removeList("delete");
  }); //confirm
}

/* 목록 체크 삭제 */
function removeList(arg){
  if(arg != "delete") {
      console.log("direct call nope!");
      return;
  }

  var url = '<c:url value="/ajaxD/POPO0101/deletePup.do" />';
  var formData = pieip.trx.getFormData($('#applyForm'), 0); //$form , form 인덱스 (동일 id 갯수가 여러개 일때)

  pieip.trx.postAjax(url, formData, function(){
      search();   //성공시 실행시킬 콜백
  });
}

</script>
</head>
<body>
    <div class="card">
        <div class="card-body">
        <div id="tit-wrap">
            <h2 class="h2-tit">팝업 관리 목록</h2>
        </div>
        <!-- contents start -->
        <nav class="navbar search-form">
        <div class="container-fluid">
        <p class="navbar-text">
          <span>총 게시물 <strong id="totalRowCount">000</strong> 개,
              <span>페이지 <strong id="pageIndex">1</strong>/<em id="totalPage">0</em></span>
          </span>
        </p>
        <form class="navbar-form navbar-right" role="search" name="searchForm" id="searchForm">

           <!--  파라미터 전송 -->
            <input hidden="hidden" title="검색폼" >  <!-- 엔터 자동 submit 방지 -->
            <input type="hidden" name="pageIndex" value="${cmd.pageIndex }">
            <input type="hidden" name="pageSize" value="${cmd.pageSize }">



          <!-- 라디오 버튼  -->
            <div class="form-group">
                <label for="rangeDate_B"  class="radio-inline">
                    <input type="radio" name="rangeDate" id="rangeDate_B" value="B">게재일자
                </label>
               <label for="rangeDate_A"  class="radio-inline">
                    <input type="radio" name="rangeDate" id="rangeDate_A" checked="checked" value="A">등록일자
                </label>
            </div>



           <!-- 날짜 조회 캘린더 -->
            <div class="form-group form-group__pad">
                <div class="input-daterange input-group">
                    <div id="datepicker" class="input-daterange input-group">
                        <div class="date">
                         <label for="rangeStartDate">
                            <input type="text" class="input-sm form-control" name="rangeStartDate" id="rangeStartDate" value="${cmd.rangeStartDate}" autocomplete="off">
                            <span class="input-group-addon input-radius"><i class="far fa-calendar-alt"></i></span>
                            </label>
                        </div>
                        <span class="input-group-addon input-between">~</span>
                        <div class="date">
                        <label for="rangeEndDate">
                            <input type="text" class="input-sm form-control" name="rangeEndDate" id="rangeEndDate" value="${cmd.rangeEndDate}" autocomplete="off">

                            <span class="input-group-addon"><i class="far fa-calendar-alt"></i></span>
                         </label>
                        </div>
                    </div>
                </div>
            </div>

            <div class="btngruop">
	           <span class="btn_date"><a href="#" onclick="javascript:pieip.ui.dtPickerSet(this);">전체</a></span>
	           <span class="btn_date"><a href="#" onclick="javascript:pieip.ui.dtPickerSet(this, 'week');">1주</a></span>
	           <span class="btn_date"><a href="#" onclick="javascript:pieip.ui.dtPickerSet(this, 'month');">1달</a></span>
	           <span class="btn_date"><a href="#" onclick="javascript:pieip.ui.dtPickerSet(this, 'sixmonth');">6개월</a></span>
	       </div>


            <div class="form-group">
                 <!-- select start -->
                <div class="rail-select">
                    <div class="select-search">
                        <span class="caret"></span>
                    </div>
                    <select class="form-control" id="searchField" name="searchField" title="검색어를 입력">
                        <option value="">-전체-</option>
                        <option value="title">제목</option>
                        <option value="regEmp">등록자</option>
<!--                        <option value="aplyYn">적용여부</option> -->
                    </select>
                </div>
                <!-- select end -->
            </div>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="검색어를 입력해주세요" title="검색어를 입력" name="searchText" value="${cmd.searchText }">
                <span class="btn btn-primary btn-no-icon" id="btnSearch">검색</span>
            </div>
       </form>
        </div>
        </nav>


         <form name="applyForm" id="applyForm"></form> <!-- 목록 반영 -->


            <table id="DataList" class="table table-bordered board-basic">
            <caption>팝업 목록 조회</caption>
            <colgroup>
                <col style="width:10%">
                <col style="width:10%">
                <col>
                <col style="width:10%">
                <col style="width:10%">
                <col style="width:10%">
                <col style="width:10%">
                <col style="width:10%">
                <col style="width:10%">
            </colgroup>
            <thead>
            <tr id="sortHeader"> <%-- 컬럼정렬시  id, 해당 컬럼에 em 및 data 요소 추가 --%>
                <th scope="col" class="first"><label class="checkbox-inline"><input type="checkbox" id="allChk" name="allChk" onClick="javascript:pieip.ui.allChk(this);"></label></th>
                <th scope="col">위치</th>
                <th scope="col">팝업명</th>
                <th scope="col">저장상태</th>
                <th scope="col">게재 상태</th>
                <th scope="col">필수 공지여부</th>
                <th scope="col">게재 시작일시</th>
                <th scope="col">게재 종료일시</th>
                <th scope="col" class="last">등록자</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                 <td colspan="10">로딩중...</td>
            </tr>

            </tbody>
            </table>
        <!-- contents end -->
            <nav class="page-area">

               <div id="paging"></div>

                <div class="pull-right">
                    <span id="btnCreate" class="btn btn-gray"><i class="fas fa-pen"></i>등록</span>
                    <span id="btnDelete" class="btn btn-danger"><i class="fa fa-times"></i>삭제</span>
                </div>
            </nav>

        <!-- contents end -->
        </div>
    </div>
</body>
</html>



