/**
 * UI 에서 공통으로 사용하는 함수
 */

    //해당 체크 박스가 포함된 테이블 tbody 내에서 사용한다
    function allChk(obj) {
        var objTBody = $(obj).closest("table").find("tbody");
        var b = $(obj).is(":checked");
        $(objTBody).find("input[type=checkbox]").prop("checked", b);
        $(objTBody).find("input[type=checkbox][disabled]").prop("checked", false);  //disable 속성은 빼준다
    }

    //obj :  form 객체
    function disableCtrlAll(obj, enable) {
      disableCtrl($(obj).find("input[type=text]"), enable);
      disableCtrl($(obj).find("input[type=select]"), enable);
      disableCtrl($(obj).find("input[type=checkbox]"), enable);
      disableCtrl($(obj).find("input[type=radio]"), enable);

      $(obj).find("textarea").each(function(){
        disableCtrl($(this), enable);
      });
    }

    function disableCtrl(obj, enable) {
      $(obj).prop('disabled', enable);
      $(obj).prop('readonly', enable);

      //필요시 스타일 지정...
    }

    //버튼용 disable (버튼을 모두 span 으로 사용 한다)
    function disableBtn(obj) {
      $(obj).attr("disabled", true);
      $(obj).unbind(); //모든 이벤트 제거
    }

    //버튼용 enable (버튼을 모두 span 으로 사용 한다)
    //ex) enableBtn( $("#btnDelete"), function(){alert('delete btn click!');} );
    function enableBtn(obj, fnClick) {
      $(obj).attr("disabled", false);
      $(obj).bind("click",fnClick);
    }

    //모든 목록형태 페이지의 ready() 에서 불러 준다.
    function addEvtSearchText() {
      $('input:text[name=searchText]').keydown(function() {
        if (event.keyCode === 13) {
          searchForm.pageIndex.value = "1";
          search();
        }
      });
    }


    //radio value 값으로 true 체크
    function radioValSet(name, value) {
      $('input:radio[name='+name+']:input[value=' + value + ']').attr("checked", true);
    }

    //부트스트랩 DatePicker 초기화
    /* old
    function bsDtpicker(obj) {
      $(obj).datepicker({
        language: "ko",
        format: "yyyy-mm-dd",
        //todayHighlight : true,
        //toggleActive : false,
        //todayBtn: "linked",
        //calendarWeeks: true,
        autoclose: true
      });
      //$(obj).datepicker("setDate", "2020-06-30");
      //console.log("bsDtpicker(obj)");
    }
    */

    //부트스트랩 DatePicker 초기화 및 값 세팅
    function bsDtpicker(obj, val){
      $(obj).closest('.date').datepicker({
        language: "ko",
        format: "yyyy-mm-dd",
        todayHighlight: true,
        autoclose: true
      }).datepicker("setDate", val);
      //console.log("bsDtpicker(obj, val) :" + val);
    }

    //부트스트랩 DateTimePicker 초기화
    function bsDtimepicker(obj) {
      $(obj).datetimepicker({
        language: "ko",
        format: "yyyy-mm-dd hh:ii",
        autoclose: true,
        //pickerPosition: "top-left",
        //todayBtn: true,
        //startDate: "2013-02-14 10:00",
        minuteStep: 5
      });
    }

    function dtPickerKeyup(obj) {
      $(obj).on("keyup", function(e){
          //37~40 : 방향키, 8 : back space, 46 : del
          if (e.which != 37 && e.which != 38 && e.which != 39 && e.which != 40 && e.which != 8 && e.which != 46) { // 방향키 체크

              //$(this).val($(this).val().replace(/[^0-9\-]/g,""));
              var dateNumText = $.trim($(this).val()).replace(/[^0-9]/g,"");
              var textLength = dateNumText.length;

              if (textLength < 4) {
                $(this).val(dateNumText);
              }else {
                  if (textLength >= 4 && textLength < 6) {
                    $(this).val(dateNumText.substr(0, 4) + "-" + dateNumText.substr(4, dateNumText.length));
                  }else if (textLength >= 6) {
                    $(this).val(dateNumText.substr(0, 4) + "-" + dateNumText.substr(4, 2) + "-" + dateNumText.substr(6, 2));

                  }
              }
          }
      });
    }

    function fileUploaderShow() {
      $("#FileList").show();
    }

    function fileUploaderHide() {
      $("#FileList").hide();
    }

    //테이블 내 row(<tr>) 선택 표시 (첨부파일  선택용)
    function tbRowSelFile(obj) {
      $(obj).parent().find("tr").removeClass("row_sel__success");
      $(obj).addClass("row_sel__success");
    }

    //테이블 내 row(<tr>) 선택 표시
    function tbRowSel(obj) {
      $(obj).parent().find("tr").removeClass("row_sel");
      $(obj).addClass("row_sel");
    }

    //선택 row(<tr data-id>) 위로 이동
    function tbRowMovePrev() {
      var obj = $("#DataList > tbody").find(".row_sel");

      if(typeof $(obj) === 'undefined' || $(obj).length <= 0 ){
        alert("설정할 row를 선택 하세요");
        return;
      }

      var prev = $(obj).prev();
      if(typeof $(prev).data("id") === 'undefined')
        return;

      $(prev).before($(obj));
    }

    //선택 row(<tr data-id>) 아래로 이동
    function tbRowMoveNext() {
      var obj = $("#DataList > tbody").find(".row_sel");

      if(typeof $(obj) === 'undefined' || $(obj).length <= 0 ){
        alert("설정할 row를 선택 하세요");
        return;
      }

      var next = $(obj).next();
      if(typeof $(next).data("id") === 'undefined')
        return;

      $(next).after($(obj));
    }




    //오픈되는 모든 윈도우를 기록 함
    var __g_appWin = [];


    //콜백 받아서 오픈한 윈도우 모두 종료
    function closeCallbackAll() {
      console.log("close pre :" + __g_appWin.length);

      $.each(__g_appWin, function(index, vo){
        vo.win.close();
      });

      console.log("closed :" + __g_appWin.length);
    }


    //콜백 받는 윈도우 오픈
    var openCallbackWin = function(config, callback) {
      var win = cfn_OpenWindow(config); //common_win.js
      var interval = window.setInterval(function() {
          try {
            if (win == null || win.closed) {
              window.clearInterval(interval);
              callback(win);
            }
          }catch (e) {console.log("callback error!"); console.log(e); }
      }, 1000);

      //console.log(win);
      __g_addWin(win.name, win);
      return win;
    };


    function __g_addWin(winId, win) {
      var item = new Object();
          item.winId = winId;
          item.win = win;

      var bExist = false;
      $.each(__g_appWin, function(index, vo){
        if(vo.winId == winId) {
          bExist = true; //동일 windowId 이면 넣지 않는다.
        }
      });
      if(!bExist) {
        __g_appWin.push(item);
      }
      //console.log("__g_addWin :" + __g_appWin.length);
    }


    function __g_removeWin(winId) {
      $.each(__g_appWin, function(index, vo){
        //console.log(vo);
        //console.log(index);
          if(vo.winId == winId) {
            __g_appWin.splice(index, 1);
            return false; //each문 탈출  참고 : return false : break , return true : continue
          }
      });
      //console.log("__g_removeWin :" + __g_appWin.length);
    }


    /*
     - 포털에서 행정지원 등 세션 공유가 필요 한 경우 사용
       kier 특성상 포털에서 무조건 팝업 형태로 이동 하므로 팝업을 띄운다
    */
    function openProxy(url, winId, callback, isModal, size) {

      if(typeof winId === 'undefined' || winId == '') {
        winId = "MIS";
      }

      var width = "0";
      var height= "0";
      if(typeof size === 'undefined' || size == null || size == '' ) {
        width = "1700";
        height= "900";
      }else{
        width = size.width    ? size.width    : "1700";
        height= size.height   ? size.height   : "900";
      }

      var config = new Object();
        config.url = contextPath + '/eip/comm/proxy.do';
        config.windowId = winId;
        config.params = 'url=' + encodeURIComponent(url);
        config.scroll = 'yes';
        config.isdialog = "N";
        config.width  = width;
        config.height = height;
        config.modal = isModal;

      var win=openCallbackWin(config, function(win){
        if ($.isFunction(callback)){
          callback(url, winId);
        }
        __g_removeWin(winId);
      });
    }


    function openPortal(url, winId, callback, isModal, size) {

      if(typeof winId === 'undefined' || winId == '') {
        winId = "EIP";
      }

      if(url != 'about:blank') {
        url = contextPath + url;
      }

      var width = "0";
      var height= "0";
      if(typeof size === 'undefined' || size == null || size == '' ) {
        width = "1300";
        height= "768";
      }else{
        width = size.width    ? size.width    : "1300";
        height= size.height   ? size.height   : "768";
      }

      var config = new Object();
        config.url = url;
        config.windowId = winId;
        /*config.params = 'url=' + url;*/
        config.scroll = 'yes';
        config.isdialog = "N";
        config.width  = width;
        config.height = height;
        config.modal = isModal;

      var win=openCallbackWin(config, function(win){
        if ($.isFunction(callback)){
          callback(url, winId);
        }
        __g_removeWin(winId);
      });
    }

    /*
     * SSO 필요시 - 팝업 형태로 오픈
     */
    function openPop(url, mdulId, callback, width, height) {
      var winId  = mdulId;

      if(mdulId == null || typeof mdulId === 'undefined' || mdulId == '' ) {
        winId =  getTimestamp();  //모듈 ID 가 없으면 계속 새창이 뜨도록...
      }

      var size = null;
      if(width != null || height != null) {
        size = new Object();
        size.width = width;
        size.height= height;
      }

      openProxy(url, winId, callback, null, size);
    }

    /*
     * 타 시스템 오픈 SSO 필요시 - 브라우저의 새탭 형태로 오픈
     */
    function openOther(url) {
      //SSO 연동이 필요한 타 시스템 토큰 정보를 같이 넘기기위해 proxy를 통해 간다.
      var proxyUrl = contextPath + '/eip/comm/proxy.do?url=' + encodeURIComponent(url);
      window.open(proxyUrl, "_blank").focus();
    }



   /*
    - 업로드 콤포넌트가 없는 페이지에서 파일 다운로드 하기
          여러개 파일 다운로드인 경우 fileIds 에 콤마(,) 로  구분
   */
   function downloadFile(docId, programId, fileIds){

     var form = jQuery("<form />")
                     .attr("method", "get")
                     .attr("action", contextPath + "/fileAttach/downloadAll.do");

     form.append( jQuery("<input />").attr("type", "hidden").attr("name", "programId").attr("value", programId) );
     form.append( jQuery("<input />").attr("type", "hidden").attr("name", "docId").attr("value", docId) );
     form.append( jQuery("<input />").attr("type", "hidden").attr("name", "fileIds").attr("value", fileIds) );

     $("body").append(form);
     form.get(0).submit();

     form.remove();
   }

   /*
    * 숫자만 입력받는 경우
    *  input type="number" maxlength="3" onInput="numMaxLen(event);" 형태로 사용함
    */
   function numMaxLen(e) {

     var e     = e || window.event;
     var keyCode = (e.which) ? e.which : e.keyCode;
     var obj   = e.target;

     if (keyCode == 8 || keyCode == 46 || keyCode == 37 || keyCode == 39) {
       return;
     } else {
       obj.value = e.target.value.replace(/[^0-9]/g, '');
     }

     if(obj.value.length > obj.maxLength) {
       obj.value = obj.value.slice(0,obj.maxLength);
     }
   }




    // ---------------------- OLD ------------------------ //

    //검색 연도 초기화
    function initSearchYear(beginId, endId) {

        var d = new Date();
        var y = parseInt(d.getFullYear());
        var beginY = y - 10;
        var endY = y + 2;

        console.log("y :" + y + ",beginY :" + beginY + ", endY2 :" + endY);
        var optionStr = "";
        for( var i = beginY ; i < endY; i++) {
            optionStr += "<option value="+ i +">" + i + "</option>\n";
        }

        var beginObj = $("#" + beginId);
        var endObj = $("#" + endId);

        beginObj.append(optionStr);
        endObj.append(optionStr);

        $(beginObj).val(y-1);
        $(endObj).val(y);
    }

    //폼에 콤보로 년도가 들어가야 할때 초기화
    function initYearCombo(ctrlId) {
        var d = new Date();
        var y = parseInt(d.getFullYear());
        var beginY = y - 10;
        var endY = y + 2;

        var optionStr = "";
        for( var i = beginY ; i < endY; i++) {
            if(i == y) {
                optionStr += "<option value="+ i +" selected>" + i + "</option>\n";
            }else{
                optionStr += "<option value="+ i +">" + i + "</option>\n";
            }
        }
        var ctrlObj = $("#" + ctrlId);
        ctrlObj.append(optionStr);
    }

    //플래타그리드 콤보
    function initFletaCombo(id, width, optText ) {
        $("#" + id).html( optText ) ;
        $("#" + id).attr("width", width);
        $("#" + id).setMultiSelect();
    }

