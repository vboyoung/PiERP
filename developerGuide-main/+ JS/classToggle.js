
/* 
    // popView 함수 호출 예시
    popView(width,height);
    function popView(width, height) {
    var config            = new Object();

    $.extend(config , {
        url        : 'about:blank' //이동해야 하는 URL;
        ,windowId   : "POP"
        ,scroll     : 'yes'
        ,isdialog   : "N"
        ,width      : width
        ,height     : parseInt(height) + parseInt(40)
        ,resizable  : 'yes' //팝업창 크기 고정
        ,modal      : true
    });

    var win = openCallbackWin(config, function(win){
        __g_removeWin("POP");
    });

    var frm = document.applyForm;
    frm.action = contextPath + '/popds/POP/POPO0103.do';
    frm.target ="POP";
    frm.method ="post";
    frm.submit();

    }


    // testPlugin 함수 호출 예시
    testPlugin({width:50, height:100});
    $.fn.testPlugin  = function(opt) {
        var settings = $.extend({Default}, opt);
    }

*/


/*
   // main classToggle 호출
   $('.hnb1').classtoggle
    ({
        'button'            : '.label',     // 이벤트 받을 타겟 선택
        'respond'           : true,         // 반응형일 때 (true 시 반응형일때 가로 사이즈 이하에서만 / click 일때만)
        'respondWidth'      : '1024',       // 반응형 가로 사이즈
    });
*/

$.fn.classtoggle = function( options )
{
    var settings = $.extend
    ({
        'button'            : '.open',      // 이벤트 받을 타겟 선택
        'action'            : 'click',      // 액션 선택 (click | over)
        'classname'         : 'active',     // 추가할 클래스명
        'accordion'         : false,        // active 될 때 형제요소의 반응 여부
        'respond'           : false,        // 반응형일 때 (true 시 반응형일때 가로 사이즈 이하에서만 / click 일때만)
        'respondWidth'      : '768',        // 반응형 가로 사이즈
        'close'             : '.close'      // 닫기 버튼이 별도로 존재하는 경우 (닫기 버튼은 클릭시에만)
    }, 
    options );

    return this.each(function()
    {
        var $selecter = $(this);

        function clickActive()
        {
            if ( $selecter.hasClass(settings.classname) == false )
            {
                $selecter.addClass(settings.classname);
                if (settings.accordion == true)
                {
                    $selecter.siblings().removeClass(settings.classname);
                }
            }
            else
            {
                // 닫기 버튼 존재할 경우 토글되지 않음
                if ( $selecter.find(settings.close).length == 0 )
                {
                    $selecter.removeClass(settings.classname);
                }
            }
        }

        if ( settings.action == 'click' )
        {
            $selecter.find(settings.button).on(settings.action, function()
            {
                if ( settings.respond == false )
                {
                    clickActive();
                    return false;
                }
                else
                {
                    if ( $(window).width() <= settings.respondWidth )
                    {
                        clickActive();
                        return false;
                    }
                    else
                    {
                        $selecter.find(settings.button).off();
                    }
                }
            });
        }
        else
        {
            $selecter.find(settings.button).on
            ({
                mouseenter  : function()
                {
                    $selecter.addClass(settings.classname);
                },
                focusin     : function()
                {
                    $selecter.addClass(settings.classname);
                },
                mouseleave  : function()
                {
                    $selecter.removeClass(settings.classname);
                },
                focusout    : function()
                {
                    $selecter.removeClass(settings.classname);
                }
            });
        }

        // 닫기 버튼이 별도로 존재하는 경우
        if ( $selecter.find(settings.close).length > 0 )
        {
            $selecter.find(settings.close).on('click', function()
            {
                $selecter.removeClass(settings.classname);

                return false;
            });
        }
        else
        {
            // 탭 아웃
            $selecter.find('a:last').keydown(function(e) 
            {
                if(e.keyCode === 9) 
                {
                    $selecter.removeClass(settings.classname);
                }
            });

            // 역탭 아웃
            $selecter.find('a:first').keydown(function(e) 
            {
                if(e.keyCode === 9 && e.shiftKey) 
                {
                    $selecter.removeClass(settings.classname);
                }
            });
        }
    });
};