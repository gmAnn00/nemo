// dot 애니메이션
$(function () {
  $('.dot').click(function() {
      $(this).toggleClass('open');
      $(this).siblings().toggle();
  });
});
