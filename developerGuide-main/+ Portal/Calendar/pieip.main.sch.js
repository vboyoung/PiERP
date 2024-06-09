/**
 * pieip.main.sch.js : 메인 일정 관련
 *
 * 참고 - 일정, 부서원 근무현황 더보기 버튼 이벤트 핸들러는 pieip.main.a.js 에 있음
**/


(function($, _, R){

  if( typeof R.sch === "object"){
    return;
  }

	var PRE_FIX = "SCHA_";
  	var SEARCH_FORM = "#" + PRE_FIX + "SearchForm"	;

  	var _targetDate = null;
  	var _gwUrl = "";

	/**
	 * 기능 초기화 - 메인화면 로딩 시 호출 필요
	 * ex) pieip.main.sch.initMain();
	 */
	function initMain( gwUrl ) {
		markingThisWeek();	//미니달력 이번주 표시
	    schNotice(); 		//조기 퇴근일 표시 (매월 2, 4주 금요일 문구 노출!)

	    _gwUrl = gwUrl;
	}


	/*
	 * 그룹웨어 화면이 열렸다가 닫힐때 호출될 콜백 함수
	 */
	var moreCallback = function(url, winId) {

		getList(targetDate());
	}

	/*
	 * 동일 하게 그룹웨어 라도 callback 기준으로 more(새창열기)를 나눈다.
	 */
	function more(params, mdulId) {

		var gwURL = _gwUrl + "/custom/sso/SSOLogin.jsp?" + params;
		console.log(gwURL);

		if(mdulId == "_new") {
			window.open(gwURL);
		}else{
			openPop(gwURL, mdulId, moreCallback , '1700', '900');
		}
	}



	/**
	 * 현재 주를 표시함 (오늘 날짜를 기준으로 함)
	 * 외부에 노출이 필요 없는 함수
	 */
	function markingThisWeek() {
    	if( $(".calendar_wrap").find(".current_date").length == 1 ) {
    		$(".calendar_wrap").find(".current_date").closest("tr").addClass("current_week");
    	}
	}


	/**
	 * 조기 퇴근일 표시
	 *  외부노출이 필요 없는 함수
	 */
	function schNotice() {

		  $(".sch_info").css("height", "150px"); //공지 없을때 좀 늘려준다. 를 기본으로.. (CSS 수정 하기 싫으니깐)
		  $(".sch_notice").hide();

		  var todayStr = getToday('YYYY-MM-DD');
		  var todayIdx = new Date(todayStr).getDay();
		  //console.log(todayIdx); //0: 일, 1: 월, 2: 화. 3: 수, 4: 목, [5: 금], 6: 토
		  if(todayIdx == 5) {
			  //금요일 이면.., 오늘이 몇 주차 인지 확인
			  var objWeek = weekNumberByMonth(todayStr); //console.log(objWeek); //{year: 2022, month: 5, weekNo: 4}
			  if(objWeek.weekNo == 2 || objWeek.weekNo == 4) {
				  $(".sch_info").css("height", "108px"); //공지 있을때 (원래 사이즈)
				  $(".sch_notice").show(); //오늘은 조기퇴근 대상일이에요
			  }
		  }
	}


	/**
	 * 부서원 근무현황 등에서 끌어다 쓸때 pieip.main.sch.targetDate() 로 가져갈것
	 */
	function targetDate() {
		if(_targetDate == null) {
			getTargetDate();
		}
		return _targetDate;
	}

	/**
	 * (선택한) 날짜를 가져오는 함수
	 * 외부에 노출이 필요 없는 함수
	 */
  	function getTargetDate( obj ) {
  		var year, month;
  		var now = new Date();

  		if(typeof obj === 'undefined' || obj == null || obj == ''  ) {
  			year = now.getFullYear();
  			month= now.getMonth() + 1;
  			day  = now.getDate();
  		}else{
  			year = $(obj).parent().data("year");
  			month= $(obj).parent().data("month");
  			day  = $(obj).text().trim();
  		}

  		if(String(month).length == 1) {
  			month = "0" + month;
  		}
  		if(String(day).length == 1) {
  			day = "0" + day;
  		}


  		_targetDate = new Object();
  		_targetDate.year = year;
  		_targetDate.month= month;
  		_targetDate.day	= day;

  		return _targetDate;
  	}

	/**
	 * 달력 월 이동
	 */
	function moveMonth(month, year) {

		if(typeof month === "undefined" || month == "" || month == null) {
			month = "";
		}
		if(typeof year === "undefined" || year == "" || year == null) {
			year = "";
		}

		$(SEARCH_FORM + " > input[name='schMonth']").val(month);
		$(SEARCH_FORM + " > input[name='schYear']").val(year);


	    var formData = pieip.trx.getFormData($(SEARCH_FORM), 0);
	    var url = contextPath + "/ajaxR/SCHA0101/moveMonth.do";

	    pieip.trx.postAjax(url, formData, function(data){
	    	$(".calendar_wrap").html(data);

	    	//금주표시
	    	markingThisWeek();
	    }, 'html');
	}

	/*
	 * 달력의 날짜  이동
	 */
	function moveDay(obj) {
		var year, month, day;

		//선택 날짜의 스타일
		$(obj).closest("tbody").find("span").removeClass("current_number");
		$(obj).addClass("current_number");

		//선택 날짜의 년, 월, 일 추출
		//day = $(obj).text().trim();
		var objDate = getTargetDate(obj);

		getList(objDate);
		pieip.main.sri.getList();	//부재자 목록 조회

	}

	/*
	 * 오늘로 이동
	 */
	function moveToday() {
		moveMonth(); //달력을 이번달로 표시

		var now = new Date();
  	  	var	tmptDate = new Object();
  	  	tmptDate.year = now.getFullYear();
  	  	tmptDate.month= now.getMonth() + 1;
  	  	tmptDate.day	= now.getDate();

		getList(tmptDate);
		pieip.main.sri.getList();	//부재자 목록 조회
	}



	/**
	 * 전사, 부서, 개인 등 탭 클릭 시
	 * ex) pieip.main.sch.tab();
	 */
	function tab(obj) {
		var tab = $(obj).parent();
		var ownerTp = $(tab).data("id"); //전사, 부서, 개인, (원장, 본부장)

		//페이지내 id 는 가급적 자제 하고, 대표적인 폼을 통해 접근
		$(SEARCH_FORM + " > input[name='ownerTp']").val(ownerTp); //해당 폼은 SCHA0101.jsp 에 있음

		getList(targetDate());
	}


	/*
	 * 화면에 오류 메시지를 출력 한다.
	 * 외부 노출 안함
	 */
	function printListMsg( msg ) {
		var obj = $(".sch_info_tab").next()	; //일정 목록을 출력하는 <ul>
		var tmsg = '<li><span class="sc_normal"><div class="text_ellipsis_df no_data">' + msg + '</div></span></li>';

		pieip.page.printDivMsg(obj, tmsg);
	}


	/**
	 * 날짜에 맞는 데이터 목록 가져오기
	 * 선택 날짜에 맞는 일정 목록을 가져온다.
	 *
	 * arg : 탭 구분
	 */
	function getList(objDate) {
		year = objDate.year;
		month= objDate.month;
		day	 = objDate.day;

		console.log("pieip.main.sch.getList() : year :" + year + ", month :" +  month + ", day : " + day);

		$(SEARCH_FORM + " > input[name='schMonth']").val(month);
		$(SEARCH_FORM + " > input[name='schYear']").val(year);
		$(SEARCH_FORM + " > input[name='schDay']").val(day);


		var params = $(SEARCH_FORM).serialize();
		return pieip.trx.postJson(contextPath + "/ajaxR/SCHA0102/selectScheduleList.do?" + params, function(data){
			  if(data.status != "success"){
			      //EgovBizException
				  printListMsg(data.serviceMessage);
			  }else {
				  $(".sch_info_tab").next().html("");	//조회 데이터 출력을 위한 초기화 - 탭을 연속 클릭 한 경우 대비 하여 콜백에서 초기화함

			      var resObject = data.objects;
			      //console.log(resObject);

			      //데이터가 없을 때
			      if(resObject.gwScheduleVOList.records == "0"){
			         printListMsg("데이터가 없습니다.");
			      }

			      $.each(resObject.gwScheduleVOList.rows, function(index, vo){ //받은 객체 루프..
			        appendListRow(index, vo);
			      });

			  }

		}).fail(function(){//시스템 오류
			printListMsg("데이터 조회 오류입니다.");
		});
	} //getList()


	/*
	 * 데이터 목록 그리기 (내부함수)
	 */
	function appendListRow(index, rowData) {
		if(!rowData){
			return;
		}

		var obj = new Object();
		  obj.authTp  	= rowData.authTp;
		  obj.ownerTp	= rowData.ownerTp;
		  obj.calendarId= rowData.calendarId;
		  obj.eventId	= rowData.eventId;
		  obj.endDt		= rowData.endDt;
		  obj.startDt	= rowData.startDt;

		var jsonParam = JSON.stringify(obj) ;   //오브젝트를 json 문자열로...



		var clsCdNmSpan = "t_green";
		var clsNm = "";
		if(rowData.ownerTp == "1"){
			clsNm = "[전체]";
		}else if(rowData.ownerTp == "2"){
			clsNm = "[부서]";
		}else if(rowData.ownerTp == "4"){
			clsNm = "[개인]";
			clsCdNmSpan = "t_blue";
		}else{
			clsNm = "[...]"; //확정되면 추가...
		}

		/* 디자인 끝나면 이거 지울 것
		 * 현재는 구분, 제목만 출력해 놓았음.. if  문등으로 체크 하면서 장소와 시간 등도 다 출력 할 것!
		 *
        <li><a href="#"><span class="sc_normal"><div class="text_ellipsis_df"><span class="t_green">[종일]</span> 개인정보보호 산출물 검토</div></span></a></li>
        <li><a href="#"><span class="sc_normal"><div class="text_ellipsis_df"><span class="t_blue">[국내출장]</span> 차세대사업 WBS 일정 협의일정 협의일정 협의</div></span></a></li>
        <li><a href="#"><span class="sc_normal"><span class="sc_left"><div class="text_ellipsis_df">MIS시설관리 업무회의</div></span><span class="sc_right">14:00~15:30</span></span></a></li>
        <li><a href="#"><span class="sc_normal"><span class="sc_left"><div class="text_ellipsis_df"><span class="t_red">[오전휴가]</span></div></span><span class="sc_right">08:00~12:00</span></span></a></li>
        <li><a href="#"><span class="sc_normal"><div class="text_ellipsis_df">기타내용</div></span></a></li>
        */

		$(".sch_info_tab").next().append(
	      '<li data-id="'+rowData.eventId+'">' +
	      '<a href="javascript:void(0);" onClick="javascript:pieip.main.sch.viewDetail(this);"><div class="text_ellipsis_df"><span class="'+clsCdNmSpan+'">' + clsNm + '</span>' +  rowData.title + '</div></span></a>' +
	      '<span id="param" style="display:none;">' + jsonParam + '</span>' +
	      '</li>'
		);
	}


	/**
	 * 상세 페이지 이동 (내부함수)
	 */
	function viewDetail(obj) {
		var json = JSON.parse($(obj).closest("li").find("#param").html()); //json 문자열을 json 오브젝트로
		//console.log(json);

		var param = "prtName=hscalendar&subLink=eventread&calendarId=" + json.calendarId + "&eventId=" + json.eventId;
		more(param, 'CALENDAR');
	}



  R.sch = {
		  initMain 		: initMain
		  , moveMonth	: moveMonth
		  , moveDay		: moveDay
		  , moveToday	: moveToday
		  , targetDate  : targetDate
		  , tab			: tab
		  , getList		: getList
		  , viewDetail	: viewDetail
		  , more 		: more
  };


})(jQuery, _, pieip.main);