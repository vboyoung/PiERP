
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
File Name : POPO0102.jsp
Description : 업무포털 관리자 팝업 관리 > 상세보기
Modification Information
author : 이정민
since : 2022.05.24.

수정일 수정자 수정내용
------- -------- ---------------------------
2022.05.24. 이정민 최초 생성

--%>
<html>
<head>
  <title>팝업 관리 상세보기</title>
<script type="text/javascript">
$(document).ready(function() {

    /* ============================================================================== */
    /*                                   Etc Event                                    */
    /* ============================================================================== */

 	//등록일자 -현재 날짜 가져오기
	var month = getToday("YYYY-MM-DD");

    var uploader; //파일업로더

	//선택한 카테고리 정보 조회
    if("${cmd.selectedId}" != ""){
    	getSelectOne().done(function(){
    		uploader = createUploader(); //수정인 경우는 게시물 조회 이후에 생성해야 함 (내부에서 docID를 세팅해야 함)
    	});
    }else {
    	//신규 입력이면
        uploader = createUploader();
        $("#regDt").val(month);
        pieip.ui.disableBtn( $("#btnDelete") );
    }

    /* ============================================================================== */
    /*                              button Event                                      */
    /* ============================================================================== */

 	//목록 보기
	$('#btnList').click(function(ev) {
		goList();
	});


  	//저장
 	$('#btnSave').click(function(ev){
		save(uploader);
    });


	//임시 저장
 	$('#btnTmpSave').click(function(ev){
		tmpSave(uploader);
    });


    //삭제
    $('#btnDelete').click(function(){
    	remove();
    });


    //미리 보기
    $('#btnPreview').click(function(){
    	var tmpSaveYn = $('#tmpSaveYn').val();

    	if(tmpSaveYn == null || tmpSaveYn == ''){
    	  pierp.ui.alert("임시저장 후 다시 시도해 주세요.");
    	} else {
          var width = $("#imgWdthSize").val();
          var height = $("#imgVertSize").val();
          popView(width,height);
    	}

    });

    /* ============================================================================== */
    /*                                 HTML SETTING                                   */
    /* ============================================================================== */

  	//캘린더
    pieip.ui.bsDtimepicker($("#pstpBegntime"));
    pieip.ui.bsDtimepicker($("#pstpClsetime"));

    //우선 편의성만 넣는다. (날짜포맷인지는 moment로 체크.. 필요시에..)
    $("#pstpBegnDtm").inputmask("9999-99-99 99:99");
    $("#pstpClseDtm").inputmask("9999-99-99 99:99");

});

/* ============================================================================== */
/*                              function Event                                    */
/* ============================================================================== */

/* 팝업 미리보기 */
function popView(width, height) {
  var config            = new Object();

  $.extend(config , {
	   url        : 'about:blank' //이동해야 하는 URL;
      ,windowId   : "POP"
      ,scroll     : 'yes'
      ,isdialog   : "N"
      ,width      : width
      ,height     : parseInt(height) + parseInt(40)
      ,resizable  : 'yes' //팝업창 크기 고정
      ,modal      : true
  });

  var win = openCallbackWin(config, function(win){
    __g_removeWin("POP");
  });

  var frm = document.applyForm;
  frm.action = contextPath + '/popds/POP/POPO0103.do';
  frm.target ="POP";
  frm.method ="post";
  frm.submit();

}


/* 상세 데이터 조회 */
function getSelectOne() {
  var params = $("#searchForm").serialize() + getNoCacheParam();
  return pieip.trx.postJson(contextPath + "/ajaxR/POPO0102/selectPubOne.do?" +params, function (data){
      if (data.status != "success") { //EgovBizException
          pierp.ui.alert(data.serviceMessage);
      }else{
          //성공일때...
          var resObject = data.objects;
          itemDispatch(resObject.pupVO);
      }
  }).fail(function(){ //시스템오류(HTTP500)
      pierp.ui.alert("데이터 조회 오류입니다.");
  });
}


/* 선택된 데이터 세팅 */
function itemDispatch(item) {

  $("#rgtrLoca").val(item.rgtrLoca);
  $("#ttl").val(item.ttl);

  var tmpSaveYn = item.tmpSaveYn;
  var saveStatus = eip.prop.getVal("tmpSaveYn", tmpSaveYn);
  $("#tmpSaveYn").val(saveStatus);

  //등록일자
  $("#regDt").val(strToDateFormat(item.regDt,'YYYY-MM-DD','YYYY-MM-DD'));

  //게시기간
  if( item.pstpBegnDtm == null || item.pstpBegnDtm == '') {
    $("#pstpBegnDtm").val();
  }else {
    $("#pstpBegnDtm").val(item.pstpBegnDtm );
  }

  if( item.pstpClseDtm == null || item.pstpClseDtm == ''  ) {
    $("#pstpClseDtm").val();
  }else {
    $("#pstpClseDtm").val(item.pstpClseDtm );
  }

  $("#cnts").val(item.cnts);

  //옵션구분
  pieip.ui.radioValSet("opinDiv", item.opinDiv);

  if( item.mndtAnucYn == "Y" ) {		// 필수 공지 여부
     $("#mndtAnucYn").prop("checked", true);
   }else{
     $("#mndtAnucYn").prop("checked", false);
  }

  if( item.cntsYn == "Y" ) {		// 텍스트 포함 여부
     $("#cntsYn").prop("checked", true);
   }else{
     $("#cntsYn").prop("checked", false);
  }

  $("#imgWdthSize").val(item.imgWdthSize);
  $("#imgVertSize").val(item.imgVertSize);
} //itemDispatch()


/* 임시저장 */
function tmpSave( uploader ) {

  /* 날짜 validation 체크  */
  var pstpBegnDtm =  $("#pstpBegnDtm").val();
  var pstpClseDtm =  $("#pstpClseDtm").val();

  var valDate = validatorForDate(pstpBegnDtm,pstpClseDtm,'YYYY-MM-DD HH:mm');

  //적용 시간 체크
  if(valDate == 0){
  	pierp.ui.alert('게시기간을 입력해주세요.');
  	return false;
  }

  //첨부파일이 필수 인 경우!, 텍스트 포함여부가 선택된 상태라면 파일첨부를 하지 않아도 해당 팝업 정보가 저장된다.
  if(!$('#cntsYn').is(':checked')){
    if( uploader.getAttachedFileCount() <= 0 ) {
        pierp.ui.alert('첨부파일을 등록해 주세요.');
        return false;
    }
  }

  if(valDate == -1){
  	alert('게시 시작시간은 게시 종료시간보다 과거일수 없습니다.');
  	return false;
  }

  //값을 Y로 바꿔 준뒤 저장
  $("#tmpSaveYn").val("Y");

  //체크박스는 언체크시 안 올라가므로 콘트롤러에서 NULL 체크 해서 NULL이면 N로 일괄 체크
  var popForm = $("#popForm").serializeObject(); //jquery.serialize-object

  pierp.trx.request({
     block : true,
     url : '<c:url value="/ajaxS/POPO0102/tmpSavePub.do" />',
     params : { popForm : popForm },
     iDSs: [ {type:'file', id:'uploader', uploader: uploader} ],      //파일업로드 처리 id값이 controller의 파라미터명과 동일해야함.
     callback: function(status, objects) {
       if(objects.status == "success") {
         //일반적으로 성공했을때 수행..
         $("#tmpSaveYn").val("임시저장");
         $("#pupNo").val(objects.objects.pupNo); //저장된 키값

         uploader.setDocId(objects.objects.pupNo);       // 업로더 없무key값 설정
         uploader.refresh();         // 업로더 새로 고침
         applyForm.selectedId.value = $("#pupNo").val(); //미리보기용 세팅
       }
     },eip: function(status, objects){
       //pierp.ui.alert 이후 실행될 내용
    if(objects.status == "success") {
     // itemDispatch(); //목록으로
       }
     }//eip
  });
}


/* 저장 */
function save(uploader) {
    /* 날짜 validation 체크  */
    var pstpBegnDtm =  $("#pstpBegnDtm").val();
    var pstpClseDtm =  $("#pstpClseDtm").val();


    //적용 시간 체크
    if(pstpBegnDtm == '' || pstpClseDtm == ''){
    	pierp.ui.alert('적용시간을 입력해주세요.');
    	return false;
    }

    //첨부파일이 필수 인 경우!, 텍스트 포함여부가 선택된 상태라면 파일첨부를 하지 않아도 해당 팝업 정보가 저장된다.
    if(!$('#cntsYn').is(':checked')){
      if( uploader.getAttachedFileCount() <= 0 ) {
          pierp.ui.alert('첨부파일을 등록해 주세요.');
          return false;
      }
    }

    var valDate = validatorForDate(pstpBegnDtm,pstpClseDtm,'YYYY-MM-DD HH:mm');

    if(valDate == 0){
    	pierp.ui.alert('적용시작시간은 적용종료시간과 같을 수 없습니다.');
    	return false;
    }

    if(valDate == -1){
        alert('게시 시작시간은 게시 종료시간보다 과거일수 없습니다.');
        return false;
    }

    //값을 N으로 바꿔 준뒤 저장
  	$("#tmpSaveYn").val("N");

 	<%-- 체크박스는 언체크시 안 올라가므로 콘트롤러에서 NULL 체크 해서 NULL이면 N로 일괄 체크 --%>
 	var popForm = $("#popForm").serializeObject(); //jquery.serialize-object

 	pierp.trx.request({
 	   block : true,
       url : '<c:url value="/ajaxS/POPO0102/savePub.do" />',
       params : { popForm : popForm },
       iDSs: [ {type:'file', id:'uploader', uploader: uploader} ],//파일업로드 처리 id값이 controller의 파라미터명과 동일해야함.
       callback: function(status, objects) {
         if(objects.status == "success") {
           //일반적으로 성공했을때 수행..
           $("#tmpSaveYn").val("게재");
         }
       },eip: function(status, objects){
         //pierp.ui.alert 이후 실행될 내용
		  if(objects.status == "success") {
           goList(); //목록으로
         }
       }//eip
 	});
}


/* 이전 목록 */
function goList() {
    var url = '<c:url value="/mgt/POP/POPO0101.do" />'; //이동해야 하는 URL;

    //넘겨줄 파라미터를 담고 있는 form 과 추가 파라미터 세팅
    var paramForm = $("#searchForm");
    var appendText = '';
    pieip.page.goPage(url, paramForm, appendText);
}


/* 파일첨부 업로드 콤포넌트 생성 */
function createUploader() {
  var docId = $("#pupNo").val(); //각 업무 테이블의 인조키값 (각 업무에 맞는 변수로 수정!!)
  if(typeof docId === 'undefined') {
    docId = ""; //최초저장시에는 업무key가 없음.
  }

  return new pierp.GridFileUploader('gridfileupload-container', {
      params: {
          programId: 'EIP_POP',// 각업무에 맞는 ID로 수정!! (포털은 EIP_XXX)
          docId: docId,        // 최초저장시에는 업무key가 없음.
          // fileCls: 'A'      // option
      },
      readonly: false,             // 읽기여부 default: false
      hideTitle: true,			 //상단숨길것
      multiple: true,              // 추가버튼클릭시 여러개 선택가능여부 default: true
      height: 300,                 // 그리드 height default : 150 (포털은 의미 없음)
      allowTypes: 'bmp|png|jpg|jpeg|gif',  // upload가능 파일유형들 default : 'all'
      maxFileSize: 350 * (1024*1024),  // 파일크기제한 ( KB*MB*size ) default : 350MB
      maxFileCount: 1,            // upload파일갯수제한 default : 10
  });
}


/* 삭제 이벤트 (팝업 - 단건) */
function remove(){
  var pupNo = $("#pupNo").val();
  var message = "";

  if(pupNo == "") return;

  var message = "선택하신 팝업을 삭제하시겠습니까?";
  pierp.ui.confirm(message).then(function(btn) {
      if (btn === 'no') { return; }

      return pieip.trx.postJson(contextPath +"/ajaxD/POPO0101/deletePup.do?pupNo="+ pupNo, function (data){
          if (data.status != "success") { //EgovBizException
              pierp.ui.alert(data.serviceMessage);
          }else{
              //성공일때...
              goList();
          }
      }).fail(function(){ //시스템오류(HTTP500)
          pierp.ui.alert("서버오류 입니다. 잠시후 다시 시도해 주세요.");
      });
  }); //confirm
}
</script>
</head>
<body>
  <div class="card">
    <div class="card-body">

      <div id="tit-wrap">
        <h2 class="h2-tit">팝업 관리 상세보기</h2>
      </div>


      <%-- 필요한 파라미터를 서버의 paramCmd를 이용해 세팅 한다. --%>
      <form name="searchForm" id="searchForm">
        <!-- 팝업 상세화면에서 사용하는 값 -->
        <input type="hidden" name="rangeDate"       value="${cmd.rangeDate }">
        <input type="hidden" name="rangeStartDate"  value="${cmd.rangeStartDate }">
        <input type="hidden" name="rangeEndDate"    value="${cmd.rangeEndDate }">
        <input type="hidden" name="tmpSaveYn"       value="${cmd.tmpSaveYn }">

        <!-- 검색값 및 페이징 처리 기본값 -->
        <input type="hidden" name="pageIndex"   value="${cmd.pageIndex }">
        <input type="hidden" name="pageSize"    value="${cmd.pageSize }">
        <input type="hidden" name="searchField" value="${cmd.searchField }">
        <input type="hidden" name="searchText"  value="${cmd.searchText }">
        <input type="hidden" name="selectedId"  value="${cmd.selectedId }">
      </form>


      <!-- 미리보기용  -->
      <form name="applyForm" id="applyForm">
        <input type="hidden" name="selectedId" value="${cmd.selectedId }">
      </form>


      <!-- contents start -->
      <form name="popForm" id="popForm">
        <!-- 팝업번호 -->
        <input type="hidden" id="pupNo" name="pupNo" value="${cmd.selectedId }">
        <!-- 첨부파일번호 -->
        <input type="hidden" name="ahflNo" value="1">
        <!-- 첨부파일번호 -->
        <input type="hidden" name="rgtrId" value="${cmd.regUsrId}">

        <div class="form-horizontal board-basic-write">


          <!-- 저장 상태 -->
          <div class="form-group">
            <div class="col-sm-2 control-label">
              <label for="tmpSaveYn">저장 상태</label>
            </div>
            <div class="col-sm-3">
              <input class="form-control" type="text" name="tmpSaveYn" id="tmpSaveYn" value="" title="상태" readonly>
            </div>
          </div>


          <!-- 팝업명 -->
          <div class="form-group">
            <div class="col-sm-2 control-label">
              <label for="ttl">제목<span class="necessary">필수입력</span></label>
            </div>
            <div class="col-sm-4">
              <input class="form-control" type="text" name="ttl" id="ttl" placeholder="제목을 입력해 주세요" title="제목">
            </div>
          </div>


          <!-- 등록위치 -->
          <div class="form-group">
            <div class="col-sm-2 control-label">
              <label for="rgtrLoca">등록위치<span class="necessary">필수입력</span></label>
            </div>
            <div class="col-sm-3">
              <!-- select start -->
              <div class="rail-select">
                <div class="select-side">
                  <span class="caret"></span>
                </div>
                <select class="form-control" id="rgtrLoca" name="rgtrLoca" title="등록위치선택">
                  <option value="C">전체</option>
                  <option value="A" selected="selected">포털메인</option>
                  <option value="B">MIS메인</option>
                </select>
              </div>
              <!-- select end -->
            </div>
          </div>


          <!-- 등록일자 -->
          <div class="form-group" style="display: none;">
            <div class="col-sm-2 control-label">
              <label for="regDt">등록일자</label>
            </div>
            <div class="col-sm-3">
              <input class="form-control" type="text" name="regDt" id="regDt" title="등록일자" value="" readonly>
            </div>
          </div>


          <!-- 필수입력 -->
          <div class="form-group">
            <div class="col-sm-2 control-label">
              <label for="pstpBegnDtm">게시기간<span class="necessary">필수입력</span></label>
            </div>
            <div class="col-sm-6">
              <div class="navbar-form">
                <div class="input-daterange input-group mr_30">

                  <div class="input-daterange input-group">
                    <div class="input-daterange input-group">
                      <div class="input-group date" id="pstpBegntime">

                        <label for="pstpBegnDtm"> <input type="text" class="form-control input-time" id="pstpBegnDtm" name="pstpBegnDtm" value="" autocomplete="off">
                        </label> <span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"><i class="far fa-calendar-alt"></i></span>
                        </span>
                      </div>
                      <span class="input-group-addon input-between">~</span>

                      <div class="input-group date" id="pstpClsetime">
                        <label for="pstpClseDtm"> <input type="text" class="form-control input-time" name="pstpClseDtm" id="pstpClseDtm" value="" autocomplete="off">
                        </label> <span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"><i class="far fa-calendar-alt"></i></span>
                        </span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>


          <!-- 파일첨부 -->
          <div class="form-group">
            <div class="col-sm-2 control-label">
              <label for="ahflTyp">파일첨부</label>
            </div>

            <div class="col-sm-10 row">
              <div class="col-sm-10">
                <!-- 첨부파일 업로드 -->
                <div id="gridfileupload-container"></div>
              </div>
            </div>
          </div>


          <!-- 옵션 -->
          <div class="form-group">
            <div class="col-sm-2 control-label">
              <label for="opinDiv">팝업옵션</label>
            </div>
            <div class="col-sm-10">
              <label class="radio-inline" for="opinDiv_A"> <input type="radio" name="opinDiv" id="opinDiv_A" value="A" title="오늘 하루 보지 않기" checked="checked">오늘 하루 보지 않기</label>
              <label class="radio-inline" for="opinDiv_B"> <input type="radio" name="opinDiv" id="opinDiv_B" value="B" title="3일 보지 않기">3일 보지 않기</label>
              <label class="radio-inline mr_30" for="opinDiv_C"> <input type="radio" name="opinDiv" id="opinDiv_C" value="C" title="일주일 보지 않기">일주일 보지 않기</label>
              <label class="checkbox-inline" for="mndtAnucYn"> <input type="checkbox" name="mndtAnucYn" id="mndtAnucYn" value="Y" title="필수 공지">필수공지</label>
              <label class="checkbox-inline" for="cntsYn"> <input type="checkbox" name="cntsYn" id="cntsYn" value="Y" title="텍스트 포함">텍스트 포함</label>
            </div>
          </div>


          <!-- 팝업크기 -->
          <div class="form-group">
            <div class="col-sm-2 control-label">
              <label for="imgWdthSize">팝업 크기(px)<span class="necessary">필수입력</span></label>
            </div>
            <div class="col-sm-10 row">
              <div class="col-sm-5 row">
                <div class="col-sm-3 combo-inline__pad">
                  <span>가로 </span>
                </div>
                <div class="col-sm-9">
                  <input type="number" maxlength="4" onInput="pieip.ui.numMaxLen(event);" name="imgWdthSize" id="imgWdthSize" class="form-control" placeholder="px를 입력해주세요." title="가로px사이즈" value="">
                </div>
              </div>
              <div class="col-sm-1 combo-inline__pad text__center">X</div>
              <div class="col-sm-5 row">
                <div class="col-sm-3 combo-inline__pad">
                  <span>세로</span>
                </div>
                <div class="col-sm-9">
                  <input type="number" maxlength="4" onInput="pieip.ui.numMaxLen(event);" name="imgVertSize" id="imgVertSize" class="form-control" placeholder="px를 입력해주세요." title="세로px사이즈" value="">
                </div>
              </div>
            </div>
          </div>


          <!-- 비고 -->
          <div class="form-group">
            <div class="col-sm-2 control-label">
              <label for="cnts">비고</label>
            </div>
            <div class="col-sm-10">
              <textarea class="form-control" rows="10" title="비고" name="cnts" id="cnts" placeholder="비고를 입력해주세요."></textarea>
            </div>
          </div>


          <!-- footer start -->
          <!-- 버튼  -->
          <div class="board-basic__footer">
            <div class="pull-right">
              <span id="btnTmpSave" class="btn btn-gray btn-de">   <i class="far fa-check-circle"></i>임시저장</span>
              <span id="btnSave"    class="btn btn-primary"><i class="fas fa-edit"></i>저장</span>
              <span id="btnDelete"  class="btn btn-danger"> <i class="fa fa-times"></i>삭제</span>
            </div>
            <span id="btnList"      class="btn btn-default"><i class="fas fa-list-ul"></i>목록</span>
            <span id="btnPreview"   class="btn btn-default btn-de"><i class="fas fa-search"></i>미리보기</span>
          </div>

          <!-- footer end -->
        </div>
      </form>
      <!-- contents end -->
    </div>
  </div>

</body>
</html>