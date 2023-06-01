$(function () {

 
  $(".dot").click(function () {
      if($(".dot").hasClass('open')) {
          $(this).removeClass('open');
          $('.hidden_menu>ul').hide();
      }
      else {
          $(this).addClass('open');
          $('.hidden_menu>ul').show();
      }   
  });
 

  // dot 애니메이션
  $('.dot').click(function() {
      $(this).toggleClass('open');
  });
  $('.dot.open').click(function(){
      $('.dot').removeClass('open');
  });
  


  // 탭
  (function($, document) {
  
      // get tallest tab__content element
      let height = -1;
  
        $('.tab__content').each(function() {
            height = height > $(this).outerHeight() ? height : $(this).outerHeight();
          $(this).css('position', 'absolute');
        });
      
      // set height of tabs + top offset
        $('[data-tabs]').css('min-height', height + 40 + 'px');
    
      }(jQuery, document));
});