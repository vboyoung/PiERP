<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
File Name : ENVB0101.jsp
Description :
	환경설정 > 마이메뉴 설정 화면
author : 이정민
since : 2022.06.22.

Modification Information
수정일 수정자 수정내용
------- -------- ---------------------------
2022.05.30. 정성희 마이메뉴 설정 화면
2022.06.22. 이정민 마이메뉴 기능 설정

--%>
<html>
<head>
    <title>마이메뉴 설정</title>
<script type="text/javascript" src="${contextPath}/resources/jstree/jstree.min.js"></script>
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/jstree/style.min.css">

<style type="text/css">
.no_checkbox > i.jstree-checkbox{
    display: none;
}
</style>

<script>
$(document).ready(function() {

    /* ============================================================================== */
    /*                                   Init                                         */
    /* ============================================================================== */

    // tree생성
    searchSysMenuList();

    // 현재 저장된 MYMENU 호출
    search();

    /* ============================================================================== */
    /*                              button Event                                      */
    /* ============================================================================== */


    // 검색 버튼 이벤트
    $('#btnSearch').on('click', function() {
        var searchText = $('#searchText').val();
        searchTree(searchText);
    });

    // + 버튼 이벤트
    $('#btnOpenNode').on('click', function() {
        $("#tree").jstree("open_all");
    });

    // - 버튼 이벤트
    $('#btnCloseNode').on('click', function() {
        $("#tree").jstree("close_all");
    });

    // 추가 버튼 이벤트
    $('#btnAdd').click(function() {
        addEvent();
    });

    // 삭제 버튼 이벤트
    $('#btnRemove').click(function() {
        checkedDelete();
    });

    // 저장 버튼 이벤트
    $('#btnSave').click(function() {
        auhtSave();
    });

    // keyboard 이벤트
    $('#searchText').keydown(function(event) {
        if (event.keyCode == 13) {
            $('#btnSearch').trigger('click');
        }
    });


    /* ============================================================================== */
    /*                                 HTML SETTING                                   */
    /* ============================================================================== */


    // tobdy 하위 데이터 drag&drop 기능
    $('tbody').sortable();

    // 트리 자체 이벤트
    $('#tree')
    // tree refresh 후 이벤트
    .on('refresh.jstree', function(event, data) {
        checkBoxCtrl();
    })
    // tree 생성 후 이벤트
    .on("loaded.jstree", function(event, data) {

    })
    // node 선택 이벤트
    .on('select_node.jstree', function(event, data) {

    })
    // tree 변경시 이벤트 (선택 포함)
    .on('changed.jstree', function(event, data) {

    })
    .on('open_node.jstree', function (event, data) {

    });
});

/* ============================================================================== */
/*                              function Event                                    */
/* ============================================================================== */

/* 기본 대메뉴 트리구조 세팅 */
function searchSysMenuList() {

    // 시스템 구분 트리 호출
    var url = contextPath + "/ajaxR/ENVB0101/selectSysMenuDivList.do"
    pieip.trx.postJson(url, function(data) {
        //         var result = data.objects.sysList.rows;
    }).done(function(data) {

        var treeFinalResult = [];
        var distinctClassID = {};

        // 현재 생성된 parent Data
        var treeData = data.objects.sysList.rows;

        $.each(treeData, function(i, row) {

            treeFinalResult.push({
                id : row.id,
                parent : row.parent,
                text : row.text,
                menuDivCd : 'T'
            });

            // 하위 메뉴 forEach 호출 (시스템별로)
            select1DepthList(treeFinalResult, row.id);
        });

        // 트리생성
        $('#tree').jstree({
            'core' : {
                'data' : treeFinalResult,
                'themes' : { 'icons' : false },
                'strings' : { 'Loading ...' : 'Please wait ...' }
            },
            'checkbox' : {
                "whole_node" : true             // 노드의 체크박스를 눌러야만 체크박스 활성화되게, 텍스트 선택시 현재까지 선택된 노드 모두 비활성화
                ,"keep_selected_style" : true   // 체크박스를 눌렀을 때 텍스트까지 누른 표시(배경 하늘색표시)가 되도록
                ,"three_state" : false           // 하위 체크박스 기본 체크되도록 설정 여부 (T/F)
            },
            'plugins' : [ "search", "checkbox", "wholerow" ]
        });
    }).fail(function() { //시스템오류(HTTP500)
        pieip.ui.alert("데이터 조회 오류입니다.");
    });
}

/**
 * 하위 depth 트리 구조 조회
 * treeFinalResult : 현재까지 저장된 tree Data
 * sysId : 상위 시스템 구분값 ID ( EAU, EIS, MIS, SMP, SSK )
 */
function select1DepthList(treeFinalResult, sysId) {
    var url = contextPath + "/ajaxR/ENVB0101/select1DepthList.do?searchText=" + sysId;

    pieip.trx.postJson(url, function(data) {
        var depthData = data.objects.SyMenuVO.rows;

//         console.log(depthData);

        $.each(depthData, function(i, row) {
            var childrenLength = row.subMenuList.length;

            // 하위 메뉴에 children(subMenuList) 값이 존재할 경우 계속 돌려서 treeFinalResult에 하위 데이터를 계속 넣어줘야한다.
            if (childrenLength > 0) {
                var childData = row.subMenuList;
                findChildred(childData, treeFinalResult);
            }

            treeFinalResult.push({
                id : row.id,
                parent : row.parent,
                text : row.text,
                menuDivCd : row.menuDivCd
            });

        });

    }).done(function() {
        addData(treeFinalResult);
    });
}

/**
 * 현재 데이터 내부에 있는 children(subMenuList) 데이터를 찾고 없을 때까지 하위 데이터에 붙여준다.
 */
function findChildred(data, treeFinalResult) {

    $.each(data, function(idx, lowData) {
        treeFinalResult.push({
            id : lowData.id,
            parent : lowData.parent,
            text : lowData.text,
            menuDivCd : lowData.menuDivCd
        });

        var childrenLength = lowData.subMenuList.length;
        var chchild = lowData.subMenuList;
        if (childrenLength > 0) {
            findChildred(chchild, treeFinalResult);
        }
    });
}

/**
 * jstree 노드에 저장된 id로 데이터 찾기
 */
function findCoreData(id) {
    var coreData = $('#tree').jstree(true).settings.core.data;
    for (var i = 0; i < coreData.length; i++) {
        var data = coreData[i];
        if (data.id == id) {
            return data;
        }
    }
    return null;
};

/**
 * 현재 jstree 데이터 정보 조회
 */
function getData() {
    var newData = [];
    var jdata = $('#tree').jstree(true).get_json("#", {
        flat : true
    });
    for (var i = 0; i < jdata.length; i++) {
        var current = jdata[i];
        var id = current.id;
        var data = this.findCoreData(id);
        if (data) {
            newData.push($.extend(data, current));
        }
    }
    return newData;
}

/**
 * jstree 데이터 추가
 */
function addData(d) {
    var data = getData();

    $.each(d, function(index, row) {
        // 중복데이터가 발생하면 더이상 데이터를 생성하지 않음.
        for (i = 0; i < data.length; i++) {
            if (row.id == data[i].id) {
                return;
            }
        }
        data.splice(data.length, 0, {
            'id' : row.id,
            'parent' : row.parent,
            'text' : row.text,
            'menuDivCd' : row.menuDivCd
        });
    });

    setCoreData(data);
    refresh();
}

/**
 * jstree 데이터 세팅
 */
function setCoreData(data) {
    $('#tree').jstree(true).settings.core.data = data;
};

/**
 * jstree 재조회
 */
function refresh() {
    $('#tree').jstree(true).refresh(true);
}

/**
 * jstree 내부 검색
 */
function searchTree(searchText) {
    $('#tree').jstree(true).search(searchText);
}

/**
 * 현재 선택된 jstree 데이터 조회
 */
function getSelectedNodeData() {
    var sendNodeObj = [];
    var selectedNodeObj = $('#tree').jstree('get_selected', true);

    $.each(selectedNodeObj,
            function(idx, obj) {
                var leafChk = $('#tree').jstree(true).is_leaf(selectedNodeObj[idx]);

                // leaf 노드이면서 현재 id값을 걸어서 하위 모듈만 추가 가능하도록 처리
                if (leafChk == true && selectedNodeObj[idx].id.length > 6) {
                    sendNodeObj.push({
                        id : obj.id,
                        parent : obj.parent,
                        text : obj.text
                    });
                }
            });

    return sendNodeObj;
}

/**
 * 사용자가 설정한 마이메뉴 조회
 */
function search() {
    var params = $("#myMenuForm").serialize() + getNoCacheParam();
    return pieip.trx.postJson(contextPath + "/ajaxR/ENVB0101/selectMyMenuList.do?" + params, function(data) {
        if (data.status != "success") { //EgovBizException
            pieip.page.printListMsg(3, data.serviceMessage);
        } else {
            var resObject = data.objects;
            if (resObject.myMenuList.records == "0") {
                pieip.page.printListMsg(3, "데이터가 없습니다.");
                $('#savedMenuCnt').val(0);
            } else {
                appendListRow(resObject.myMenuList.rows);
            }
        }
    }).fail(function() { //시스템오류(HTTP500)
        pieip.page.printListMsg(3, "데이터 조회 오류입니다.");
    });
}

/**
 * 데이터 조회 후 그리드 생성
 */
function appendListRow(rows) { // 그룹데이터 append
    $('#DataList > tbody > tr').remove();
    var html = "";
    var chkBox = '<label class="checkbox-inline"><input type="checkbox" disabled></label>';
    for (var i = 0; i < rows.length; i++) {
        chkBox = '<label class="checkbox-inline" for="'+rows[i].lupOrd+'"><input type="checkbox" id="'+rows[i].lupOrd+'"data-menuid="'+rows[i].chocMenuId+'"data-mdulid='+rows[i].chocMdulId+' data-menunm="'+rows[i].menuNm+'" value="'+rows[i].menuId+'"/></label>';
        html += '<tr data-menuid=' + rows[i].chocMenuId + ' data-upmenuid=' + rows[i].chocTopMenuId + ' data-id=' + rows[i].menuId
                + ' data-mdulid=' + rows[i].chocMdulId + ' data-menunm=' + rows[i].menuNm
                + ' onclick="javascript:pieip.ui.tbRowSel(this);" style="cursor:move">';
        html += '<td class="al_center" colspan="1">' + chkBox + '</td>';
        html += '<td class="al_center" colspan="1">' + rows[i].lupOrd + '</td>';
        html += '<td class="al_center" colspan="1">' + rows[i].menuNm + '</td>';
        html += '</tr>';
    }
    $('#DataList').append(html);
    $('#savedMenuCnt').val(rows.length);
}

/**
 * 추가 버튼 눌렀을 때의 좌측 데이터 추가 이벤트
 */
function addEvent() {

    var maxCnt = ${maxMnuCnt};

    var reset = $("#DataList > tbody > tr").data("id");
    if (reset == "msg") {
        $("#DataList > tbody > tr").remove();
    }
    var checkedNodes = getSelectedNodeData();
    var html = "";
    var chkBox = '<label class="checkbox-inline"><input type="checkbox" disabled></label>';
    var chkCnt = 0;
    $("#DataList > tbody > tr").each(function(index) {
        chkCnt = index + 1;
    });

    var j = 0;

    if (checkedNodes.length == 0) {
        pierp.ui.alert("추가할 메뉴를 선택해주세요.");
        return;
    }

    if(checkedNodes.length + chkCnt > maxCnt){
        pierp.ui.alert("최대 갯수는 " + maxCnt + "입니다.");
        return;
    }

    for (var i = 0; i < checkedNodes.length; i++) {
        var flg = true;
        $("#DataList > tbody > tr").each(function(index) {
            if ($(this).data("menuid") == checkedNodes[i].id) {
                flg = false;
                return;
            }
        });

        if (flg) {
            chkBox = '<label class="checkbox-inline" for="' + (chkCnt + j + 1) + '">'
                    + '<input type="checkbox" id="' + (chkCnt + j + 1) + '"data-menuid="' + checkedNodes[i].parent + '"data-mdulid=' + checkedNodes[i].id
                    + ' data-menunm="' + checkedNodes[i].text + '" value="' + checkedNodes[i].id + '"/></label>';
            html += '<tr data-menuid=' + checkedNodes[i].id + ' data-upmenuid=' + checkedNodes[i].parent.substr(0,2) + ' data-id=' + checkedNodes[i].id
                    + ' data-mdulid=' + checkedNodes[i].id + ' data-menunm=' + checkedNodes[i].text
                    + ' onclick="javascript:pieip.ui.tbRowSel(this);">';
            html += '<td class="al_center" colspan="1">' + chkBox + '</td>';
            html += '<td class="al_center" colspan="1"></td>';
            html += '<td class="al_center" colspan="1">' + checkedNodes[i].text + '</td>';
            html += '</tr>';
            j++;
            flg = true;
        }
    }

    if (!flg) {
        pierp.ui.alert("이미 등록된 메뉴가 있습니다. <br>다시 선택해 주세요.");
    }

    $('#tree').jstree("deselect_all");
    $('#DataList').append(html);

}

/**
 * 현재 설정된 마이메뉴를 삭제한다. (화면상의 삭제 추후 저장 필요)
 */
function checkedDelete() {
    var chkCnt = 0;
    var checkBoxId = 0;
    var dataId;

    $("#DataList > tbody").find("input[type=checkbox]").each( function() {
        if ($(this).is(":checked")) {
            chkCnt++;
            mdulid = $(this).data("mdulid");
            $('#DataList > tbody').children( 'tr[data-mdulid=' + mdulid + ']').remove();
        }
    });

    if (chkCnt <= 0) {
        pierp.ui.alert("삭제 할 항목을 체크해 주세요.");
        return false;
    }

    var message = "삭제가 완료되었습니다.<br>변경사항을 저장하시겠습니까?";
    pierp.ui.confirm(message).then(function(btn) {
        if (btn === 'yes') {
          auhtSave();
          return;
        }
    });
}

/**
 * 현재 설정된 마이메뉴 설정을 저장한다.
 */
function auhtSave() {
    //노출순서 저장 (목록저장)
    $("#applyForm").html(""); //적용폼 초기화
    var chkCnt = 0;

    $("#DataList > tbody") .find("tr") .each( function() {
        var menuNm = $(this).children('td').eq(2).text();
        $("#applyForm").append(
            '<input type="hidden" name="menuNm" value="'+ menuNm + '">'
          + '<input type="hidden" name="chocTopMenuId" value="' + $(this).data("upmenuid") + '">'
          + '<input type="hidden" name="chocMenuId" value="' + $(this).data("menuid") + '">'
          + '<input type="hidden" name="chocMdulId" value="' + $(this).data("mdulid") + '">');
        chkCnt++;
    });

    var usrId = $('#searchForm input[name=usrId]').val();
    $("#myMenuForm").append(
            '<input type="hidden" name="usrId" value="' +usrId+ '">')
    var menuSearchCmd = $("#myMenuForm").serializeObject(); //jquery.serialize-object
    var url = '<c:url value="/ajaxS/ENVB0101/myMenuSave.do" />';
    var formData = $("#applyForm").serialize();

    pierp.trx.request({
        block : true,
        url : url,
        params : { menuSearchCmd : menuSearchCmd, listData : formData },
        callback : function(status) {
            search();
        }
    }); //request
}

/**
 * 입력받은 depth별로 현재 트리는 펼쳐준다.
 */
function depthOpenNode(depth){

    $("#tree").jstree("close_all");

    var jstreeObject = $("#tree").jstree()._model.data;
    var id;

    for(i = 1; i <= depth; i++){
      for (variable in jstreeObject) {
          if(jstreeObject[variable].parents.length == i) {
            id = jstreeObject[variable].a_attr.id
            $("#tree").jstree("toggle_node", id);
          }
      }
    }

}

/**
 * 체크박스 설정 parents.length가 3 이하인 경우
 * + root id를 제외한 나머지 데이터의 menuDivCd를 조회하여 M(모듈)값이 아닌 경우 checkbox를 생성하지 않는다. (22.06.27)
 */
function checkBoxCtrl(){
    var jstreeObject = $("#tree").jstree()._model.data;
    var id;
    for (variable in jstreeObject) {

        // root id를 제외한 나머지 데이터의 menuDivCd를 조회하여 M(모듈)값이 아닌 경우 checkbox를 생성하지 않는다.
        id = jstreeObject[variable].id;
        if(id != '#'){
            if(jstreeObject[variable].original.menuDivCd != 'M'){
                $("#tree").jstree().get_node(id).a_attr["class"] = "no_checkbox";
            }
        }

    }
}



</script>
</head>
<body>
  <div class="contents_wrap">
    <header>
      <h2 class="sub_title">환경설정</h2>
    </header>

    <div class="contents_area">

      <!--메뉴 영역-->
      <div class="menu_con">
        <%@ include file="/WEB-INF/jsp/eip/bizENV/comm/left_env.jsp"%>
      </div>

        <form role="search" name="searchForm" id="searchForm">
            <input type="hidden" name="usrId" value="${userInfo.usrId }">
            <input type="hidden" name="searchText" value="">
        </form>

        <!-- 목록 반영 -->
        <form name="applyForm" id="applyForm"></form>


        <form name="myMenuForm" id="myMenuForm">
            <input type="hidden" name="usrId" value="${userInfo.usrId}">
            <input type="hidden" id="savedMenuCnt" name="savedMenuCnt" value="">
        </form>


      <!--주요내용-->
      <div class="sub_con">
        <p class="set_txt t_black">마이메뉴설정</p>
        <!--왼쪽-->
        <div class="my_menu_left">
          <!--상단-->
          <div class="top_wrap">
            <div class="search_list_box2">
              <label for="search_menu">검색</label>
            <div class="form-group">
                <input type="text" class="sub_input base my_menu_input" name="searchText" id="searchText" placeholder="검색어를 입력해주세요." title="검색어 입력" autocomplete="off">
                <span id="btnSearch" class="btn btn_blue" onclick="">검색</span>

                <div class="mymenu_btn">
	                <span id="btnCloseNode" class="btn btn_gray w_mybtn" onclick="">-</span>
	                <span id="btnOpenNode" class="btn btn_gray w_mybtn" onclick="">+</span>
	                <!-- FIXME depth별 기능 버튼
                    <span id="btnOpen3Depth" class="btn btn_gray w_mybtn" onclick="javascript:depthOpenNode(3)">3</span>
	                <span id="btnOpen2Depth" class="btn btn_gray w_mybtn" onclick="javascript:depthOpenNode(2)">2</span>
	                <span id="btnOpen1Depth" class="btn btn_gray w_mybtn" onclick="javascript:depthOpenNode(1)">1</span>
                     -->
	            </div>
            </div>
            </div>
          </div>
          <!--리스트-->
          <div class="menu_list_wrap scroll_container">
            <div id="tree"></div>
            <!--//리스트-->
          </div>
        </div>
        <!--//왼쪽-->
        <div class="my_menu_right">
          <div class="menu_name_list">
            <div class="top_wrap">
              <div class="sort_left">
                <span id="btnAdd" class="lbtn add sbt">추가</span> <span id="btnRemove" class="lbtn del sbt">삭제</span>
              </div>
              <div class="sort_right">
                <span class="sort_chtext">노출 순서 변경</span>
                <span class="ar_up sbt" onclick="javascript:pieip.ui.tbRowMovePrev();"><span class="blind">오름차순</span></span>
                <span class="ar_down sbt" onclick="javascript:pieip.ui.tbRowMoveNext();"><span class="blind">내림차순</span></span>
                <span id="btnSave" class="btn btn_blue" onclick="">저장</span>
              </div>
            </div>
            <div class="menu_list_wrap scroll_container">
              <table id="DataList" class="type_list" summary="이표는 메뉴명 목록표로 항목은 체크, 노출순서, 메뉴명으로 구성되어 있습니다">
                <caption>메뉴명 목록</caption>
                <colgroup>
                  <col class="tbl_w15">
                  <col class="tbl_w15">
                  <col class="tbl_w70">
                </colgroup>
                <thead>
                  <tr>
                    <th scope="col" class="first"><label class="checkbox-inline"><input type="checkbox" id="allChk" name="allChk" onClick="javascript:pieip.ui.allChk(this);"></label></th>
                    <th>노출순서</th>
                    <th>메뉴명</th>
                  </tr>
                </thead>
                <tbody>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
      <!--//주요내용-->
    </div>
    <!--//컨텐츠-->
  </div>
</body>
</html>

