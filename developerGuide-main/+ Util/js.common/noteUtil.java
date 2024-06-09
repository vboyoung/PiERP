/**
 * summerNote (편집기)에서 공통으로 사용하는 함수
 */


//편집기 생성
function createSummerNote() {
  $('#summernote').summernote({
      height: 320,                  // 에디터 높이
      minHeight: null,              // 최소 높이
      maxHeight: null,              // 최대 높이
      focus: false,                 // 에디터 로딩후 포커스를 맞출지 여부
      lang: "ko-KR",        // 한글 설정
      placeholder: '내용을 입력 하세요' //placeholder 설정

      , disableDragAndDrop : true
      , dialogsInBody: true
      , toolbar : [
            //['style', ['style']],
            //['font', ['bold', /*'italic'*/, 'underline', 'clear']],
            ['fontname', ['fontname']],
            ['fontsize', ['fontsize']],
            ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['table', ['table']],
            ['insert', ['link', 'picture', 'video']],
            ['view', ['codeview', 'help']],
            ['custom', ['specialchars']]
          ],
          fontNames: ['나눔고딕','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체','Arial', 'Arial Black', 'Helvetica', 'Times New Roman', 'Verdana'],
          fontSizes: ['8','9','10','11','12','14','16','24','30','36','50','72'],
          codeviewFilter: false,
          codeviewIframeFilter: false
   });
  //$('#summernote').summernote('fontName', 'Arial'); //

  //$('.note-editable').css('font-size','18px');
  $('.note-editable').css('font-family','나눔고딕');
}

function summerNoteSet(val) {
  $('#summernote').summernote('reset'); //1. 초기화
  //$('#summernote').summernote('insertText', val); //텍스트로 넣기
  $('#summernote').summernote('code', val); //코드로 넣기
}

function summerNoteGet() {
  htmlStr = $('#summernote').summernote('code'); //내용 가져오기(코드 다 가져옴)
  return htmlStr;
}

function summerNoteGetText() {
  return htmlDecode(summerNoteGet()); // cmmUtil.js
}

function showNomalNote() {
  $(".note-frame").hide();
  $("#nomalnote").show();
}

function showSummerNote() {
  $("#nomalnote").hide();
  $(".note-frame").show();
  //$('#summernote').summernote('fontName', '나눔고딕');  //억지로 설정하면 폰트깨짐
}

//편집기 선택
function noteSelection(cntntCls) {
  var message = "양식을 변경할 경우 본문내용이 없어지거나 보이지 않을 수 있습니다. 계속 진행 하시겠습니까?";
  var oldSel = ($(cntntCls).val() == "A" ? "B" : "A" ); //둘중 하나니까..

  pierp.ui.confirm(message).then(function(btn) {
      if (btn === 'no') {
        $(cntntCls).val(oldSel);
        return;
      }

      if($(cntntCls).val() == "A") {         //HTML
        summerNoteSet($("#nomalnote").val());
        showSummerNote();
      }else {  //TEXT
        $("#nomalnote").val(summerNoteGetText()); //HTML 없앤후 넣기
        showNomalNote();
      }
  }); //confirm
}

