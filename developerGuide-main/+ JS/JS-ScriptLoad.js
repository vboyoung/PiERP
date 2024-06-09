
<script>

 https://opentutorials.org/course/1375/6681

    [ IIFE(Immediately-invoked function expression: 즉시 작동하는 함수식) ]

    1. html 문서가 로딩되면

       $(document).ready(function(){...});  
       $(document).ready(function($){...});  
       $(function(){...});  
    


    2. 즉시 실행하라는 함수(익명함수에 괄호를 덧붙임) 

      (function(){...})();
    


    3. jQuery를 인자로 넘겨 $라는 매개변수로 받아서 사용하는 즉시 실행 함수

      (function($){...})()(jQuery);  
      (function($) {
        // 코드  
      })(jQuery);
    
  
    + 사용법 +
    (function($){

      $(document).ready(function($){...});  

    })(jQuery);      





    [ 이벤트 핸들러 종류]
    
    1. 마우스 이벤트
    click, dbclick, mouseover, mouseout, mousedown, mouseup, mousemove, contextmenu

    2. 키 이벤트
    keydown, keyup, keypress
    
    3. 폼 이벤트
    focus, blur, change, submit, reset, select(input안에 텍스트 드래그)
   
    4. 로드 및 기타 이벤트 
    load, abort, unload, resize, scroll




    + 사용법 +

    1. 인라인 방식
    <div onclick="view()">클릭</div>
    function view() {alert("클릭했습니다2.");}
 

    2. 고전 방식

    var _navbar = $( '#navbarCollapse' );
    _navbar.on( 'click', function( e ) { 
        ...
    });


    3. 권장 방식
   
    var t = document.getElementById('target');
    t.addEventListener('click', function(e){
        alert(1);
    });
    t.addEventListener('click', function(e){
        alert(2);
    });





  [ 윈도우 이벤트 : javascript ]   
 
  //리소스가 모두 다 로드 된 후 작동, 지연 초래
  window.addEventListener('load', function(){
    console.log('load');
  })
 
  //리소스 로드 전에 작동
  window.addEventListener('DOMContentLoaded', function(){
    console.log('DOMContentLoaded');
  })
 
  document.addEventListener('DOMContentLoaded', KTSwapper.init);





  [ 윈도우 이벤트 : JQuery ]   

  $( window ).on( 'load resize', function() { ... }); 

  $( window ).scroll( function() { ... });

  $( window ).on( 'load', function() { ... });
  dom, css, img 가 모두 로드된 후 콜백함수를 실행. 
  document의 ready 이벤트가 window의 load 이벤트보다 먼저 발생한다.




  
  [jQuery 프로토타입  |  jQuery 함수를 직접 추가 ]
  
  선언 1.
  jQuery.fn.exists = function() {
    return this.length > 0;
  }


  선언 2.
  $.fn.CrayCss = function(color){
  $(this).each(function(){
    $(this).css('border', color + ' solid 1px');
  });
  };

  $('.CrayTest').CrayCss('red');
  
  //사용
  var _daterange = $( 'input[name="daterange"]' );
  _daterange.exists();




  [jQuery 생성자 함수]

  $() 는 내부적으로는 new JQuery() 를 init 하여 공장처럼 찍어낸다.
  돔객체를 던지면 제이쿼리 객체로 반환해준다.

  $()
  jQuery();
  jQuery( function( $ ) { ...	});


</script>