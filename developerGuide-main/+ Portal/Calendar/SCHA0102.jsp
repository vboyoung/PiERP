<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
File Name : SCHA0102.jsp
Description :
	일정 메인화면 (일정목록)

Modification Information
수정일 수정자 수정내용
------- -------- ---------------------------
2022.05.17. 이정민 목록 조회

author : 이정민
since : 2022.05.17.
--%>
                <ul class="sch_info_tab">
                  <li data-id="1" class="sch_active"><a href="javascript:void(0);" onclick="javascript:pieip.main.sch.tab(this);">전체</a></li>
                  <li data-id="2"><a href="javascript:void(0);" onclick="javascript:pieip.main.sch.tab(this);">부서</a></li>
                  <li data-id="4"><a href="javascript:void(0);" onclick="javascript:pieip.main.sch.tab(this);">개인</a></li>
                  <li data-id="9"><a href="javascript:void(0);" onclick="javascript:pieip.main.sch.tab(this);">원장</a></li>
                  <li data-id="8"><a href="javascript:void(0);" onclick="javascript:pieip.main.sch.tab(this);">본부장</a></li>
                </ul>

                <ul class="sch_info scroll_container">
                  <li><a href="#"><span class="sc_normal"><div class="text_ellipsis_df"><span class="t_green">[종일]</span> 개인정보보호 산출물 검토</div></span></a></li>
                  <li><a href="#"><span class="sc_normal"><div class="text_ellipsis_df"><span class="t_blue">[국내출장]</span> 차세대사업 WBS 일정 협의일정 협의일정 협의</div></span></a></li>
                  <li><a href="#"><span class="sc_normal"><span class="sc_left"><div class="text_ellipsis_df">MIS시설관리 업무회의</div></span><span class="sc_right">14:00~15:30</span></span></a></li>
                  <li><a href="#"><span class="sc_normal"><span class="sc_left"><div class="text_ellipsis_df"><span class="t_red">[오전휴가]</span></div></span><span class="sc_right">08:00~12:00</span></span></a></li>
                  <li><a href="#"><span class="sc_normal"><div class="text_ellipsis_df">기타내용</div></span></a></li>
                  <li><a href="#"><span class="sc_normal"><div class="text_ellipsis_df">기타내용</div></span></a></li>
                </ul>

               <div class="sch_notice info_ellipsis_tb" style="display: none;">
                 <strong class="title sch_notice_icon">짜잔~ 오늘은 조기퇴근 대상일이에요.</strong>
               </div>