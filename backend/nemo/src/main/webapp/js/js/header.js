$(function () {
    $('.burger').click(function() {
        $(this).toggleClass('open');
        $('.sidemenu').toggleClass('open');
        $('.menu_bg').toggle();
    });
    $('.menu_bg, .burger.open').click(function(){
        $('.menu_bg').hide();
        $('.sidemenu').removeClass('open');
        $('.burger').removeClass('open');
        
    });

    // 스크롤
    $(window).scroll(function(){
        var scroll = $(window).scrollTop();
        if (scroll > 1) {
            $('header').css('backgroundColor','white');
        }
        else{
            $('header').css("backgroundColor" , "rgba(0,0,0,0)");  
        }
      });
});
