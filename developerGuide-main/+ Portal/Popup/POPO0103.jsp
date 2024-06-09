<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
File Name : POPO0103.jsp
Description : 팝업 미리보기 화면
Modification Information
author : 이정민
since : 2022.05.24.
수정일 수정자 수정내용
------- -------- ---------------------------
2022.05.24. 이정민 최초 생성
--%>
<html>
<head>

<title>팝업 미리보기</title>

<script type="text/javascript">

$(document).ready(function() {

    /* ============================================================================== */
    /*                                 HTML SETTING                                   */
    /* ============================================================================== */

    /* 목록에서 데이터를 갖고 페이지 전환이 이뤄졌을 시 단건 데이터의 팝업 미리보기 조회 */
    if ("${cmd.selectedId}" != "") {
        goPreview();
    } else {
        //임시 저장을 안했을 경우 - POPO0102에서 일단 막아뒀음.
        pierp.ui.alert("임시 저장 후 다시 시도해주세요.").done(function() {
            self.close();
        });
    }
});

/* ============================================================================== */
/*                              function Event                                    */
/* ============================================================================== */

//상세 데이터 조회
function goPreview() {
    var pupNo = "${cmd.selectedId}" + getNoCacheParam();

    return pieip.trx.postJson(contextPath +"/ajaxR/POPO0103/selectPreview.do?selectedId="+ pupNo, function (data){
        if (data.status != "success") { //EgovBizException
            pierp.ui.alert(data.serviceMessage);
        }else{
            //성공일때...
            var resObject = data.objects;

            // 이미지 포함 여부
            var imgInclud = resObject.imgInclud;

            console.log(imgInclud);

            itemDispatch(resObject.pupVO, imgInclud);
        }
    }).fail(function(){ //시스템오류(HTTP500)
        pierp.ui.alert("서버오류 입니다. 잠시후 다시 시도해 주세요.");
    });
}


/* 단건 데이터 조회 후 세팅 */
function itemDispatch(item, imgInclud) {

    if(item.cntsYn == "Y"){
        var cnts = item.cnts;
        $('#cntsYn').html(cnts);
        $('#cntsYn').show();
    }

    if (item.ahflTyp == "A") {
        //이미지
        if(imgInclud == "Y"){
          loadImg();
        }
    } else {
        console.log("파라미터가 잘 못 되었습니다.");
    }

    //상태 표시
    $(".popds_bg").css({
        "background" : "#1a1a1a"
    });
    $(".opin").css({
        "width" : +item.imgWidthSz,
        "height" : "30px"
    });

    //상태 표시 한글 처리
    var opinDiv = item.opinDiv;
    var opinStatus = eip.prop.getVal("opinDiv", opinDiv);

    $("#opinDiv").append(opinStatus);
}

/* 이미지 로딩 */
function loadImg() {
    var popImage = $("#popImage");
    popImage.attr( 'src', '<c:url value="/ajaxR/POPO0103/getPopImg.do?selectedId=${cmd.selectedId}" />');
    popImage.show();
}

/* opiDiv 설정에 따른 이벤트 처리가 필요하다면 해당 function 이름 및 내용 수정해서 사용하면됨. */
function untitleFunc(){
//     pierp.ui.alert("!!!");
}
</script>
</head>
<body>
     <div id="pop-Wrap">
        <p class="" id="cntsYn" style="display:none; text-align: left;"></p>
        <img id="popImage" style="display:none;" />
     </div>


    <div class="popds_bg">
       <span class="opin" onclick="javascript:untitleFunc();" id="opinDiv"></span>
    </div>

</body>
</html>