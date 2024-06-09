<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
File Name : bottom.jsp
Description :
	메인화면 하단에 위치 (메인화면의 클라이언트 스크립트를 담당 한다)

Modification Information
수정일 수정자 수정내용
------- -------- ---------------------------
2022.05.26. 정성희

author : 정성희
since : 2022.05.26.
--%>

<!-- -------------------------------스크립트 시작------------------------------- -->
<script>

$(document).ready(function() {

	pieip.main.initMain();		//배너, 공통 탭 클릭 이벤트 등 기능 초기화

	pieip.main.qck.initMain();	//퀵 메뉴 숨기기, resize 기능 초기화
	pieip.main.tai.initMain();	//근태정보 초기화 (몇 주차 표시)

	pieip.main.sch.initMain('${gwUrl}');	//미니달력 초기화 (조기퇴근일)
	pieip.main.brd.initMain('${gwUrl}');	//게시판 초기화 (그룹웨어 링크 URL)


	mainContLoad();	//메인 콘텐츠 로딩
});

<%-- 메인 화면에서 사용할 공통 글로벌 자바스크립트 함수 정의 --%>

/**
 * 메인 콘텐츠 로딩 - 메인의 리로딩 기능으로 인해 콘텐츠만 조회 하는 기능을 init 과 분리 하여 구성 한다.
 * 화면 상단 부터 먼저 조회..
 */
function mainContLoad() {

	// 전자결재 카드 영역 건수, 정보마당 카드 영역의 최근 게시글 건수
	pieip.main.grp.getGwCnt();




	//게시판 조회
	pieip.main.brd.getList();


	//TO_DO 조회
	//pieip.main.tod.getList(); 우선 주석...
	pieip.main.tod.getCntAll(); //뱃지 카운트

	//MY MENU 조회
	pieip.main.mym.getList();


	//일정 조회
	pieip.main.sch.moveToday(); //pieip.main.sch.getList(pieip.main.sch.targetDate());

	//부서 목록 조회
	pieip.main.sri.getDeptList().done(function(){
		$(".sch_select > select").val('${userInfo.deptCd}');	//로그인 사용자 부서 선택
		pieip.main.sri.getList(); 								//해당 부서의 부재자 목록 조회
	});


	//전체 공지 조회
	pieip.main.pub.getList();

	//팝업 조회
	pieip.main.pop.getList();

	//환경설정 OTP 등록여부 검사 (및 OTP 설정 화면 오픈)
	//pieip.main.env.getUsrOtp();   //QR 코드 보여주는 기능을 제거 한다.

	//업무연락 조회
	pieip.main.bsc.getList();

	//설문투표 조회
	pieip.main.rnv.getList();


	//퀵 메뉴 조회
	pieip.main.qck.getList();
}


/**
 * 리로딩 한 것 처럼 메인을 초기화 함
 */
function mainReload() {


	mainContLoad();


	//pieip.main.sch.moveToday();
}

/**
 * 로그아웃
 *  - 모든 서브 윈도우를 종료 하고 로그아웃 URL 로 간다.
 *  - 서브 윈도우에서 호출하므로 입력 파라미터는 없도록 한다.
 */
function logout() {
  closeCallbackAll(); //openCallbackWin 으로 오픈한 윈도우 모두 종료
  location.href = "${logoutUrl }"; //로그아웃 URL 로 이동
}

/**
 * APP(MIS/EIS/EAU/...) 열기
 */
function openApp(url, mdulId, callback) {
  var winId  = mdulId;
  var appUrl = "${appUrl}" + url;		// 접속프로토콜(http://  https://) .properties로 관리

  if(mdulId == null || typeof mdulId === 'undefined' || mdulId == '' ) {
    winId =  getTimestamp();	//모듈 ID 가 없으면 계속 새창이 뜨도록...
  }

  var size = null;
  if( url.startsWith('/popup.do') ) {
    size = new Object();
    size.width  = "1300";
    size.height = "768";
  }

  openProxy(appUrl, winId, callback, null, size); //url, winId, callback, isModal, size
}

</script>