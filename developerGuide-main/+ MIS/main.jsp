<%@ page language="java" contentType="text/html; charset=UTF-8"%><%@
page import="org.springframework.web.context.WebApplicationContext" %><%@
page import="org.springframework.web.context.support.WebApplicationContextUtils" %><%@
page import="org.springframework.beans.factory.ObjectFactory" %><%@
page import="egovframework.rte.fdl.property.EgovPropertyService" %><%@
page import="pierp.app.main.service.PIERP_APP_TYPE" %><%@
page import="pierp.app.main.service.PierpAppTypeUtils" %><%@
page import="pierp.common.session.SessionContext" %><%@
page import="pierp.common.util.StringUtils" %><%--
File Name : main.jsp
Description : 기본 메인 Area Page
Modification Information
  수정일      수정자            수정내용
----------   --------   ---------------------------
2012.02.27   cyj        최초 생성

author : cyj
since : 2012.02.27
--%><%
ServletContext ctx = pageContext.getServletContext();
WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);

ObjectFactory sessionContextFactory = (ObjectFactory)wac.getBean("sessionContextFactory");
EgovPropertyService sysPropService = (EgovPropertyService)wac.getBean("sysPropService");

PIERP_APP_TYPE currentApp = PierpAppTypeUtils.getCurrentApp(request);

String siteNm  = StringUtils.unicodeToChar(sysPropService.getString("site.name"));
String sysNm   = StringUtils.unicodeToChar(sysPropService.getString("system.name"));
String sysMode = StringUtils.unicodeToChar(sysPropService.getString("system.mode"));
String subSysNm = currentApp.Name;

String goMainUrl = contextPath + "/" + (currentApp.ID.toLowerCase()) + "/main.do";

%><html>
<head>
<title>경영관리 시스템</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/fleta-ui/css/jquery.autocomplete.css?ver=20201030.01" />
<script type="text/javascript" src="${contextPath}/resources/fleta-ui/js/jquery.autocomplete.src.js?ver=20220705.01"></script>
<style>
#ui-dialog-title-dlgAplyMenuList{ font-size: 17px;}
#dlgAplyMenuList ul{list-style:none;font-size: 17px;display:flex;align-content:flex-start;flex-direction:column;flex-wrap:wrap;overflow:hidden;height:596px;}
#dlgAplyMenuList ul li {list-style:none;padding-bottom: 5px;float: left;background: url(${contextPath}/resources/pierp/images/icon_title4.png) no-repeat 0px 6px;margin-left: 10px;margin-top: 2px;}
#dlgAplyMenuList ul li a {display: block;text-decoration: none;width: 230px;padding-left: 17px;}
#dlgAplyMenuList ul li a:focus { outline:none;}
</style>

<script type="text/javascript">



var empNo = ${userInfo.empNo};

$(document).ready(function() {

	// 화면 초기 설정
    $('input[name="menuSrch"]').focus();


	/* 현황조회 카드 */
	// dataBinding
	var toYear = '<%=pierp.common.util.DateUtils.getToday().substring(0,4)%>';
	$("#srchBox01 [name='actsYy']").val(toYear);
	$("#srchBox01 [name='bzplCd']").val("1");

	$("#srchBox02 [name='mngrEmpNo']").val(empNo);
	$("#srchBox02 [name='bzplCd']").val("1");

	searchGrid01();
	searchGrid02();



    /* 메뉴 검색 */
    $('input[componentType=autocomplete]:text').each(function() {
      $(this).setAutoComplete(
        function(evnt, data, sltText){
          doMenu(data.value2, data.value3, data.value4);
        }
        ,'#ac_result_area'
      );
    });

    $('#btnMisMenuSrch').click(function(){
      $('input[name="menuSrch"]').trigger(jQuery.Event('keydown', {keyCode: 13}));
    });

    /* 주요신청서 목록 */
    var dlgAplyMenuList = $('#dlgAplyMenuList').dialog({
      title: '주요신청서목록 (가나다, ABC 순 정렬)',
      width     : ${aplyMenuListCols}*240+30,
      height    : 660,
      modal     : true,
      stack: false,
      resizable: false,
      autoOpen: false
    });

    $('#btnApplyList').click(function(){
      dlgAplyMenuList.dialog('open');
    });



    //팝업 조회 결과
    var pupList = new Array();
  	<c:forEach var="item" items="${pupList}" varStatus="status">
  		var json = new Object();
  		json.pupNo = "${item.pupNo}";
  		json.essntlAnucYn = "${item.essntlAnucYn}";
  		json.imgWidthSz = "${item.imgWidthSz}";
  		json.imgVrticlSz = "${item.imgVrticlSz}";
  		json.essntlAnucYn = "${item.essntlAnucYn}";
  		pupList.push(json);
  	</c:forEach>

  	if(pupList.length != 0){
      	$.each(pupList, function (index, vo){ //받는 객체로 루프..
      	var pupNo = vo.pupNo;
          var essntlAnucYn = (vo.essntlAnucYn == "Y") ? true : false ; //필수 공지 여부
  		var url = contextPath + "/popup/mainPopup.do?selectedIdentifier=" + pupNo;
  		var winId = "pup_" + pupNo;

        	//쿠키 조회 로직 (오늘하루보지않기: A, 3일보지않기: B, 일주일보지않기: C) - 쿠키명을 pup_[pupNo]로 저장할 것
        	var chk = getCookie(winId)
        	if(chk != "N") {
          	var config = new Object();
            	config.url = url;
            	config.windowId = winId;
            	config.scroll = 'no';
            	config.isdialog = "N";
            	config.width  = vo.imgWidthSz;
              config.height = parseInt(vo.imgVrticlSz) + parseInt(40);
              config.modal = essntlAnucYn;
              cfn_OpenWindow(config);
        	}
      	});
  	}


});


    function searchGrid01() {
		//예산변경 현황
    	pierp.trx.request({
			async : true,
			cfrmMsgConvertor: function(gridSmmry, defMsg) {
				return null;
			},
			url     : '<c:url value="/ajaxR/MBE0101/selectYesChgList.do"/>',
			iDSs	: [
				{type:'form', id:'srchBox01', name:'srchBox'}
  		   	],
  		  	callback: function(status, data){
  		  	  var resObject = data.objects;
  		      var listId  =  "#" + "DataList01";
  		      if(resObject.voList.records == "0"){
	  		        //데이터가 없을 때
  		    		printListMsg( listId  , "데이터가 없습니다.");
		       }
  		  	  //목록조회
  		  	  $.each(resObject.voList.rows, function(index, vo){
  		  		appendListRow01(index, vo);
  		  	  });
          	}
   		});
    }


    function appendListRow01(index, vo) {


		if(!vo){return;}

		var amt = vo.bdgtDfnAmt;

		//사업유형
		var bizCd = "";
		switch (vo.bzTyCd) {
        case '10' :
      	 	 bizCd = "<span>수탁사업</span>";
       		 break;
          	default: bizCd = "";
    	}

		$( "#DataList01 > tbody").append(
		  '<tr>' +
		  '<td class="al_center">'+	vo.bzId +'</td>' +
		  '<td class="al_center">'+	vo.bzNm +'</td>' +
		  '<td class="al_center">'+	bizCd +'</td>' +
		  '<td class="al_center">'+	strDate(vo.bzBegnDt) +'</td>' +
		  '<td class="al_center">'+	strDate(vo.pjclDt) +'</td>' +
		  '<td class="al_center">'+	amt.toLocaleString('ko-KR') +'</td>' +
		  + '<tr>'
		);
	}


    function searchGrid02() {
		//예산변경 현황
    	pierp.trx.request({
			async : true,
			cfrmMsgConvertor: function(gridSmmry, defMsg) {
				return null;
			},
			url     : '<c:url value="/ajaxR/MSB0101/selectRescMngeList.do"/>',
			iDSs	: [
				{type:'form', id:'srchBox02', name:'param' }
  		   	],
  		  	callback: function(status, data){
  		  	  var resObject = data.objects;

  		      var listId  =  "#" + "DataList02";
  		      if(resObject.rescList.records == "0"){
	  		        //데이터가 없을 때
  		    		printListMsg( listId  , "데이터가 없습니다.");
		       }
  		  	  //목록조회
  		  	  $.each(resObject.rescList.rows, function(index, vo){
  		  		appendListRow02(index, vo);
  		  	  });
          	}
   		});
    }


    function appendListRow02(index, vo) {
		if(!vo){return;}
		$( "#DataList02 > tbody").append(
		  '<tr>' +
		  '<td class="al_center">'+	vo.astsNo +'</td>' +
		  '<td class="al_center">'+	vo.rescNm +'</td>' +
		  '<td class="al_center">'+	vo.modlNm +'</td>' +
		  '<td class="al_center">'+	vo.gageNm +'</td>' +
		  '<td class="al_center">'+	vo.mngeDeptNm +'</td>' +
		  '<td class="al_center">'+	vo.mngeDeptCd +'</td>' +
		  + '<tr>'
		);

	}

	function printListMsg( dataName , msg ) {
		dataName = dataName + ' > tbody';
		var obj = $(dataName);
		$(obj).html("");
		$( obj ).append(
			'<tr>' +
				'<td colspan="6" class="no_data">' + msg + '</td>' +
		    '</tr>'
		);
	}




	function doMenu(topMenuId, menuId, mdulId)
	{
	  if(mdulId == "") return;

	  document.zForm.selTopMenuId.value = topMenuId;
	  document.zForm.selMenuId.value = menuId;
	  document.zForm.selMdulId.value = mdulId;

	  document.zForm.action="<%=goMainUrl%>";
	  document.zForm.submit();
	}

	//날짜 형식 변경
	function strDate(str) {
		if(!str){return;}

		var yy = str.substr(0,4);
		var mm = str.substr(4,2);
		var dd = str.substr(6,2);
		return yy + '-' + mm + '-' + dd;
	}


</script>


</head>
<body>
  <div class="mis_main_con">
	<div id="content">
	    <div class="mis_main">
	      <div class="mis_area">
	        <div class="mis_search_form" style="z-index:2">
	          <input type="text" name="menuSrch" title="바로가기" class="search_mis" value="" require="false" cols="2" componentType="autocomplete" url="/main/getMenuAc.do" placeholder="메뉴 검색">
	          <span id="btnMisMenuSrch" class="mis_search_btn sbt"><span class="blind">검색</span></span>
	        </div>
	        <span id="btnApplyList" class="apply_list sbt">주요 신청서 목록</span>
	        <div id="ac_result_area" style="z-index:-1"></div>

	      </div>
	    </div>
	    <!-- mis main -->
		<div class="usemenu_area">
	        <h3 class="big_tit usemenu_tit"><span class="tit_txt">최근 사용한 메뉴</span></h3>
	        <ul class="use_menu">
	        <c:forEach begin="1" end="11" items="${lastMenuList}" var="menuVo" varStatus="status">
<%-- 	          <li><a href="#${menuVo.getMdulId()}" id="${menuVo.getMenuId()}" data-mdul="${menuVo.getMdulId()}" data-topMenuId="${menuVo.getUpMenuId()}"><div class="use_bg use${status.index+1}"><span class="blind">아이콘</span></div><p>${menuVo.getMenuNm()}</p></a></li> --%>
	          <li><a href="#${menuVo.getMdulId()}" id="${menuVo.getMenuId()}" data-mdul="${menuVo.getMdulId()}" data-topMenuId="${menuVo.getUpMenuId()}"><div class="use_bg MIS_${menuVo.getUpMenuId()}"><span class="blind">아이콘</span></div><p>${menuVo.getMenuNm()}</p></a></li>
	        </c:forEach>
	        </ul>
 		</div>

		<div class="list_area">
			<div class="two-cols">
		        <div class="col">
					<h3 class="big_tit usemenu_tit">
						<span class="tit_txt">예산변경&nbsp;현황</span>
						<a href="#MBE0101" id="MBE0101" data-mdul="MBE0101" data-topmenuid="MB">
							<span class="btn_more sbt">더보기</span>
						</a>
					</h3>
					<div class="w_box_b card-h">
				      <form name="srchBox01" id="srchBox01" method="post">
						 <input type="hidden" name="actsYy" value="" />
						 <input type="hidden" name="bzplCd" value="" />
					  </form>
					  <table id="DataList01" class="mis_type01_1" summary="이표는 전체게시판표로 번호, 내용, 이름, 날짜 항목으로 구성되어 있습니다">
	                     <caption>이표는 예산변경현황 상세목록입니다</caption>

	                     <colgroup class="col_type01_s"><!--1280 이하일때-->
	                       <col class="tbl_w10" />
	                 <!--  <col class="tbl_w15" /> -->
	                       <col class="tbl_w61" />
	                       <col class="tbl_w11" />
	                       <col class="tbl_w20" />
	                     </colgroup>

	                     <colgroup class="col_type01_m"><!--1281~1919 이하일때-->
	                       <col class="tbl_w10" />
	                  <!-- <col class="tbl_w10" /> -->
	                       <col class="tbl_w60" />
	                       <col class="tbl_w10" />
	                       <col class="tbl_w20" />
	                     </colgroup>
	                     <thead>
	                     <tr>
	                       <th class="al_center">사업코드</th>
	                       <th class="al_center">사업명</th>
	                       <th class="al_center">사업유형</th>
	                       <th class="al_center">시작일자</th>
	                       <th class="al_center">종료일자</th>
	                       <th class="al_center">최종예산</th>
	                     </tr>
	                     </thead>
	                     <tbody>
	                     </tbody>
	                   </table>
					</div>
				</div>


		        <div class="col">
					<h3 class="big_tit usemenu_tit">
						<span class="tit_txt">자원관리&nbsp;현황</span>
						<a href="#MSB0101" id="MSB0101" data-mdul="MSB0101" data-topmenuid="MS">
							<span class="btn_more sbt">더보기</span>
						</a>
					</h3>
					<div class="w_box_b card-h">
					  <form name="srchBox02" id="srchBox02" method="post">
						 <input type="hidden" name="mngrEmpNo" value="" />
						 <input type="hidden" name="bzplCd" value="" />
					  </form>
					   <table id="DataList02" class="mis_type01_1" summary="이표는 전체게시판표로 번호, 내용, 이름, 날짜 항목으로 구성되어 있습니다">
	                     <caption>이표는 자원관리 상세목록입니다</caption>

	                     <colgroup class="col_type01_s"><!--1280 이하일때-->
	                       <col class="tbl_w10" />
	                 <!--  <col class="tbl_w15" /> -->
	                       <col class="tbl_w61" />
	                       <col class="tbl_w11" />
	                       <col class="tbl_w20" />
	                     </colgroup>

	                     <colgroup class="col_type01_m"><!--1281~1919 이하일때-->
	                       <col class="tbl_w10" />
	                  <!-- <col class="tbl_w10" /> -->
	                       <col class="tbl_w60" />
	                       <col class="tbl_w10" />
	                       <col class="tbl_w20" />
	                     </colgroup>
	                     <thead>
	                     <tr>
	                       <th class="al_center">자원관리번호</th>
	                       <th class="al_center">자원명</th>
	                       <th class="al_center">모델명</th>
	                       <th class="al_center">규격명</th>
	                       <th class="al_center">부서명</th>
	                       <th class="al_center">부서번호</th>
	                     </tr>
	                     </thead>
	                     <tbody>
	                     </tbody>
	                   </table>
					</div>
				</div>

	 		</div>
 		</div>

  <!-- 신청서목록창 -->
  <div id="dlgAplyMenuList" style="display:none; overflow-x:hidden;">
    <ul>
      <c:forEach items="${aplyMenuList}" var="menuVo" varStatus="status" >
     	 <li><a href="#${menuVo.getMdulId()}" id="${menuVo.getMenuId()}" data-mdul="${menuVo.getMdulId()}" data-topMenuId="${menuVo.getUpMenuId()}">${menuVo.getMenuNm()}</a></li>
     	<!--  11개만 뿌린다.  -->
      </c:forEach>
    </ul>
  </div>

  </div><!-- contnets end -->
 </div>


<!--   <div id="content" style="padding-top: 20%;"> -->
<%--     <div style="font-size: 90px; vertical-align: bottom; text-align: center;"><%=siteNm%></div> --%>
<%--     <div style="font-size: 58px; vertical-align: bottom; text-align: center; padding-top: 10px;"><%=sysNm%> MIS(경영관리) (<%=sysMode%>)</div> --%>
<!--   </div> -->

<script language="javascript">
$(document).ready(function(){
  $("#dlgAplyMenuList").on('click', 'li', function(e){
      var mdulId = $(e.target).attr('data-mdul');
      var menuId = $(e.target).attr('id');
      var topMenuId = $(e.target).attr('data-topMenuId');
      if(!!mdulId && mdulId != ''){
        loadModule(menuId, mdulId, topMenuId);
      }
      return false;
  });
  $(".use_menu li a").click(function(e){
      var mdulId = $(this).attr('data-mdul');
      var menuId = $(this).attr('id');
      var topMenuId = $(this).attr('data-topMenuId');
      if(!!mdulId && mdulId != ''){
        loadModule(menuId, mdulId, topMenuId);
      }
  });

  $(".usemenu_tit a").click(function(e) {
      var mdulId = $(this).attr('data-mdul');
      var menuId = $(this).attr('id');
      var topMenuId = $(this).attr('data-topMenuId');
      if(!!mdulId && mdulId != ''){
        loadModule(menuId, mdulId, topMenuId);
      }
  });


});
</script>
</body>
</html>