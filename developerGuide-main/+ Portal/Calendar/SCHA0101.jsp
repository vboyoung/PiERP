<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Calendar"%>
<%--
File Name : SCHA0101.jsp
Description :
	일정 메인화면 (달력)

Modification Information
수정일 수정자 수정내용
------- -------- ---------------------------
2022.05.17. 이정민 목록 조회

author : 이정민
since : 2022.05.17.
--%>
<%! // declaration, 필드에 상수 생성
      public static final int START_DAY = 1;
%>
<%
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DATE, 1);  //년,월 계산 달력은 항상 있는 1일로 세팅!

    String month = request.getParameter("schMonth");
    if (month != null && !"".equals(month)) { // "" empty 조건 추가
          cal.set(Calendar.MONTH, Integer.parseInt(month)-1);
    }
    String year = request.getParameter("schYear");
    if (year != null && !"".equals(year)) { // "" empty 조건 추가
          cal.set(Calendar.YEAR, Integer.parseInt(year));
    }

    int nowYear = cal.get(Calendar.YEAR);
    int nowMonth = cal.get(Calendar.MONTH)+1 ;
    int nowDay = cal.get(Calendar.DAY_OF_MONTH);

    pageContext.setAttribute("nowYear", nowYear);
    pageContext.setAttribute("nowMonth", nowMonth);
    pageContext.setAttribute("nowDay", nowDay);


    Calendar todayCal = Calendar.getInstance();
    int currYear = todayCal.get(Calendar.YEAR);
    int currMon = todayCal.get(Calendar.MONTH);
    int currDay = todayCal.get(Calendar.DAY_OF_MONTH);

%>
<form name="SCHA_SearchForm" id="SCHA_SearchForm">
    <input type="hidden" name="pageSize"  value="50">
    <input type="hidden" name="sortField" value="A.RQST_DT">
    <input type="hidden" name="direction" value="DESC">
    <input type="hidden" name="pageIndex" value="">

    <input type="hidden" name="schMonth" >  <!-- 달 이동시에 사용 -->
    <input type="hidden" name="schYear"  >  <!-- 달 이동시에 사용 -->
    <input type="hidden" name="schDay"  >   <!-- 해당일에 데이터 조회시에 사용 -->
    <input type="hidden" name="ownerTp" value="1" >  <!-- SCHA0102(일정목록조회)에서 사용 -->
</form>
           <div class="calendar_top">
             <div class="date_choice1">
                 <span class="btn_pre sbt"  onClick="javascript:pieip.main.sch.moveMonth(${ nowMonth-1 eq 0 ? 12 : nowMonth-1 },${  nowMonth-1 eq 0 ? nowYear-1 : nowYear });"><span class="blind">이전</span></span>
                 <span class="date_view">${ nowYear }. <fmt:formatNumber type="number" pattern="##" minIntegerDigits="2" value="${ nowMonth }" /></span>
                 <span class="btn_next sbt" onClick="javascript:pieip.main.sch.moveMonth(${ nowMonth == 13 ? 1 : nowMonth+1  },${ nowMonth == 13 ? nowYear+1 : nowYear });"><span class="blind">다음</span></span>
             </div>
           </div>

           <table class="calendar_tbl" summary="이표는 일정표로 일요일, 월요일, 화요일, 수요일, 목요일, 금요일, 토요일 항목으로 구성되어 있습니다">
               <caption>월별 달력</caption>
               <colgroup>
                 <col class="tbl_wcal">
                 <col class="tbl_wcal">
                 <col class="tbl_wcal">
                 <col class="tbl_wcal">
                 <col class="tbl_wcal">
                 <col class="tbl_wcal">
                 <col class="tbl_wcal">
               </colgroup>

               <thead>
               <tr>
                 <th><span class="t_red">일</span></th>
                 <th><span class="t_black">월</span></th>
                 <th><span class="t_black">화</span></th>
                 <th><span class="t_black">수</span></th>
                 <th><span class="t_black">목</span></th>
                 <th><span class="t_black">금</span></th>
                 <th><span class="t_blue">토</span></th>
               </tr>
               </thead>

               <tbody>
               <tr>
            <%
               int lastDay=0; //마지막날이 토요일이면 한줄 더 생기는 현상 제거를 위해
               // 매월마다 끝나는 날짜가 다르기 때문에
               for(int tempDay = 1; ;tempDay++) {
                   cal.set(Calendar.DAY_OF_MONTH, tempDay); //임시일자를 설정
                   if (cal.get(Calendar.DAY_OF_MONTH) != tempDay) {
                        // 설정된 날짜가 현재일자가 아니라면  마지막 일자 다음달 1일이므로 반복문을 빠져나간다.

                        // 마지막 공백에 다음달의 시작날짜를 찍어준다.
                         int nextCnt = 1; //다음달 시작 날짜
                         for(int blankTd=cal.get(Calendar.DAY_OF_WEEK); blankTd<8; blankTd++) {
                            if (lastDay == Calendar.SATURDAY) {
                                break; // 마지막날이 토요일이면 한 줄 더 생기는 현상 제거
                            }
                             out.println("<td data-year='"+(nowMonth == 12 ? nowYear+1 : nowYear)+"' data-month='"+(nowMonth == 12 ? 1 : nowMonth+1)+"'>");
                             out.println("<span style='color: #dbd7d7;' onclick='javascript:pieip.main.sch.moveDay(this);'>" + (nextCnt++) + "</span></td>");
                         }
                         break;
                   }

                   // 1일을 출력하기 전 1일 요일까지 공백 출력
                   switch(tempDay) {
                   case START_DAY:
                       //1일 전에 이전달 끝나는 날짜를 찍어준다.
                        Calendar prevCal = Calendar.getInstance(); //지난달을 저장할 calendar
                        prevCal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1); //  지난달로 설정

                        int prevLastDay = 0;
                        for(int temp = 1; ;temp++) { // 지난 달 마지막 날까지 반복
                        	prevCal.set(Calendar.DAY_OF_MONTH, temp);
                        	if(prevCal.get(Calendar.DAY_OF_MONTH) != temp) { // 다음달로 바뀔 때까지
                                 break;
                            }
                            prevLastDay = prevCal.get(Calendar.DATE); // 마지막 날짜 저장
                        }
                        int cnt = -1;
                        for(int blankTd=1; blankTd<cal.get(Calendar.DAY_OF_WEEK);  blankTd++) {
                        	cnt++; // 공백의 수를 카운트
                        }

                        for(int blankTd=1; blankTd < cal.get(Calendar.DAY_OF_WEEK); blankTd++) {
                            out.println("<td data-year='"+(nowMonth-1 == 0 ? nowYear-1 : nowYear)+"' data-month='"+(nowMonth-1 == 0 ? 12 : nowMonth-1)+"'>");
                            out.println("<span style='color: #dbd7d7;' onclick='javascript:pieip.main.sch.moveDay(this);'>" + (prevLastDay - cnt) + "</span>" ); //마지막 날짜 - 공백수
                            out.println("</td>");
                            cnt--;
                        }
                   }

                   //오늘 표시
                   String dayClass = "cal_date";
                   if(currYear == cal.get(Calendar.YEAR) && currMon ==  cal.get(Calendar.MONTH) && currDay == tempDay) {
                	   dayClass = "current_date";
                   }

                   //요일색도 요건이 있다면 여기서... switch(cal.get(Calendar.DAY_OF_WEEK)) case Calendar.SUNDAY: 이런식으로...

                   out.println("<td data-year='"+nowYear+"' data-month='"+nowMonth+"'><span class='"+dayClass+"' onclick='javascript:pieip.main.sch.moveDay(this);'>" + tempDay + "</span></td>");    //날짜찍고

                   switch (cal.get(Calendar.DAY_OF_WEEK)) {
                   case Calendar.SATURDAY :
                         out.println("</tr><tr>");
                   }
                   lastDay = cal.get(Calendar.DAY_OF_WEEK); //마지막날
               } //for
            %>
               </tr>
               </tbody>
           </table>