$(document).ready(function(){
    $('ul.tabs > li').click(function(){
        let tabId = $(this).attr('data-tab');

        $('ul.tabs li').removeClass('current');
        $('.tabContent').removeClass('current');

        $(this).addClass('current');
        $("#"+tabId).addClass('current');
    })
})