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
});