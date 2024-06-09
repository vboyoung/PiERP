/* paging 처리 - v0.1 - 25/03/2020
 * http://itmate.co.kr/
 * Copyright (c) 2020 "itmate"
 *
 *
 * require :
 *     jquery.xx
 *     jquery.number.min.js
 *
 */

function renderPaging(selectCntURL) {

  var formData = new FormData (document.getElementById('searchForm') );

  $.ajax({
      url: selectCntURL,
      type: 'POST',
      data:formData,
      contentType: false,
      processData: false,
      dataType: 'html',
      error : function(error) {
          console.log("페이지 네비게이션 출력 오류 : " +  error.statusText + "("+ error.status + ")");  //서버오류
      },
      success: function (data) {
          //console.log("data :" +data);
          $("#paging").html(data);
      }
  });
}


//paging
function paging(pageIndex) {
    //console.log(pageIndex);
    searchForm.pageIndex.value = pageIndex;
    search();
}

//pageSize 조정
function chgPageSize(obj) {
    var pageSize = $(obj).val();

    setCookie("pageSize", pageSize, 30); //페이지 사이즈를 쿠키에 저장함

    searchForm.pageSize.value = pageSize;
    paging("1");
}

//쿠키에 저장된 페이지 사이즈 읽기
function readLocalPageSize() {
  var pageSize = getCookie("pageSize");

  if( false == $.isNumeric( pageSize ) ) {
    //쿠키에 값이 없다면.. 콤보 박스 확인
    pageSize = $("#pageSizeCombo").val();

    if( false == $.isNumeric( pageSize ) ) {
      //여전히 없다면 기본값
      pageSize = "10";
    }
  }

  //쿠키 조작 방지
  if( parseInt(pageSize) <= 0 || parseInt(pageSize) > 100 ) {
    pageSize = "10";
  }

  return pageSize;
}

//컬럼 정렬
function sorting(obj) {
    var sortField = $(obj).data("id");

    if(typeof(sortField) == "undefined" || sortField == "") {
      return;
    }

    var sortDB = $(obj).data("db");
    if(typeof(sortDB) == "undefined" || sortDB == "") {
      //무시
    }else{
      sortField = sortDB + "." + sortField; //있다면 DB Alias를 붙인다.
    }

    var direction = searchForm.direction.value;
    if(sortField == searchForm.sortField.value) {
      //토글효과
      if(direction == "DESC") {
        direction = "ASC";
      }else if (direction == "ASC"){
        direction = "DESC";
      }else{
        direction = "DESC";
      }
    }else{
      //역 정렬우선
      direction = "DESC";
    }

    searchForm.sortField.value = sortField;
    searchForm.direction.value = direction;
    paging("1");
}

function sortStyle() {
    var sortField = searchForm.sortField.value;
    var direction = searchForm.direction.value;
    //console.log(sortField);

    if(sortField == "" || direction == "")
      return;

    if( sortField.indexOf(".") != -1 ) {
      //DB alias 가 붙어 있는 경우
      var arr = sortField.split("."); //DB alias 가 붙어있는지 확인
      var sortDB = arr[0];
      sortField = arr[1];
    }else{
      //.(도트) 없으면 무시..
    }

    //일단은 id 로만...
    var obj = $("#sortHeader").find("[data-id=" +sortField+ "]");

    $("#sortHeader > th > em").removeClass("sort_asc sort_desc");
    if(direction == "DESC") {
      $(obj).find("em").addClass('sort_desc');
    }else{
      $(obj).find("em").addClass('sort_asc');
    }
}

//총건수 표시
function setTotalRowCount(totalRowCount, pageIndex, totalPage) {
  $("#totalRowCount").html($.number(totalRowCount));
  $("#pageIndex").html($.number(pageIndex));
  $("#totalPage").html($.number(totalPage));
}

//목록에 메시지 표시
function printListMsg(colspan, msg) {
  $("#DataList > tbody").html("");
  $("#DataList > tbody").append(
      '<tr data-id="msg"><td colspan="' + colspan + '" style="text-align: center;">' + msg + '</td></tr>'
 );
}

//목록에 메시지 표시
function printDivMsg(obj, msg) {
  $(obj).html("");
  $(obj).append(
      '<ul class="list-group"><li class="list-group-item-env node-treeview-checkable" data-nodeid="0"><i class="icon glyphicon"></i><i class="icon check-icon"></i>'+ msg + '</li></ul>'
  );
}

//sitemesh Deco에 있는 zForm(POST)을 이용해 특정 페이지로 이동
function goPage(url, paramForm, appendText) {
  document.zForm.action = url; //이동해야 하는 URL;

  //zForm 폼을 이용해서 넘겨줄 데이터를 세팅 한다.
  var form = $('form[id="zForm"]');
  var paramData = paramForm.serializeObject();
  for (var nm in paramData) {
    form.append('<input type="hidden" name="' + nm + '" value="' + paramData[nm] + '">');
  }

  if(appendText != "") {
    form.append(appendText);
  }
  //console.log($(form).html());
  form.submit();
}
