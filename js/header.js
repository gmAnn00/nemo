$(document).ready(function(){
    $('.menu_btn>a').click(function(){
        $('.menu_bg').show();
        $('.wrapper').addClass('active');
        $('body,html').css({
            height:'100vh',
            overflow:'hidden'
        });
        $('.sidebar_menu').show().animate({
            right:0
         });  
    });
    $('.close_btn>a, .menu_bg').click(function(){
        $('.menu_bg').hide();
        $('.wrapper').removeClass('active');
        $('body,html').css({
            height:'auto',
            overflow:'visible',
        });
        $('.sidebar_menu').animate({
            right: '-25%'
        },function(){
            $('.sidebar_menu').hide(); 
        }); 
    });
});