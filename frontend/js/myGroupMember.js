$(function () {
  // 탭
  $(".tab-slider--body").hide();
  $(".tab-slider--body:first").show();

  $(".tab-slider--nav li").click(function() {
      $(".tab-slider--body").hide();
      var activeTab = $(this).attr("rel");
      $("#"+activeTab).fadeIn();
      if($(this).attr("rel") == "tab2"){
          $('.tab-slider--tabs').addClass('slide');
      }else{
          $('.tab-slider--tabs').removeClass('slide');
      }
      $(".tab-slider--nav li").removeClass("active");
      $(this).addClass("active");
  });
});

// dot 애니메이션
$(function () {
  $('.dot').click(function() {
      $(this).toggleClass('open');
      $(this).siblings().toggle();
  });
});
