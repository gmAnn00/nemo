$(window).on("load", function () {
    setFlowBanner1();
  });
  function setFlowBanner1() {
    const $wrap = $(".flowBanner1");
    const $list = $(".flowBanner1 .list01");
    let wrapWidth = $wrap.width();
    let listWidth = $list.width();
    const speed = 50; //1초에 몇픽셀 이동하는지 설정

    //리스트 복제
    let $clone = $list.clone();
    $wrap.append($clone);
    flowBannerAct();

    //배너 실행 함수
    function flowBannerAct() {
      //무한 반복을 위해 리스트를 복제 후 배너에 추가
      if (listWidth < wrapWidth) {
        const listCount = Math.ceil((wrapWidth * 2) / listWidth);
        for (let i = 2; i < listCount; i++) {
          $clone = $clone.clone();
          $wrap.append($clone);
        }
      }
      $wrap.find(".list01").css({
        animation: `${listWidth / speed}s linear infinite flowRolling1`,
      });
    }
  }

  $(window).on("load", function () {
    setFlowBanner2();
  });

  
  function setFlowBanner2() {
    const $wrap = $(".flowBanner2");
    const $list = $(".flowBanner2 .list02");
    let wrapWidth = $wrap.width();
    let listWidth = $list.width();
    const speed = 50; //1초에 몇픽셀 이동하는지 설정

    //리스트 복제
    let $clone = $list.clone();
    $wrap.append($clone);
    flowBannerAct();

    //배너 실행 함수
    function flowBannerAct() {
      //무한 반복을 위해 리스트를 복제 후 배너에 추가
      if (listWidth < wrapWidth) {
        const listCount = Math.ceil((wrapWidth * 2) / listWidth);
        for (let i = 2; i < listCount; i++) {
          $clone = $clone.clone();
          $wrap.append($clone);
        }
      }
      $wrap.find(".list02").css({
        animation: `${listWidth / speed}s linear infinite flowRolling2`,
      });
    }
  }
  $(function () {
      const slideshowImages = document.querySelectorAll(".intro-slideshow img");

      const nextImageDelay = 5000;
      let currentImageCounter = 0; // setting a variable to keep track of the current image (slide)

      // slideshowImages[currentImageCounter].style.display = "block";
      slideshowImages[currentImageCounter].style.opacity = 1;

      setInterval(nextImage, nextImageDelay);

      function nextImage() {
      // slideshowImages[currentImageCounter].style.display = "none";
      slideshowImages[currentImageCounter].style.opacity = 0;

      currentImageCounter = (currentImageCounter+1) % slideshowImages.length;

      // slideshowImages[currentImageCounter].style.display = "block";
      slideshowImages[currentImageCounter].style.opacity = 1;
      }
    });
$(document).ready(function() {

    //마우스 컨트롤
    /*
    $(document).ready(function(){
        // $fn.scrollSpeed(step, speed, easing);
        jQuery.scrollSpeed(200, 800);
    });

    // Custom scrolling speed with jQuery
    // Source: github.com/ByNathan/jQuery.scrollSpeed
    // Version: 1.0.2

    (function($) {

    jQuery.scrollSpeed = function(step, speed, easing) {
        
        var $document = $(document),
            $window = $(window),
            $body = $('html, body'),
            option = easing || 'default',
            root = 0,
            scroll = false,
            scrollY,
            scrollX,
            view;
            
        if (window.navigator.msPointerEnabled)
        
            return false;
            
        $window.on('mousewheel DOMMouseScroll', function(e) {
            
            var deltaY = e.originalEvent.wheelDeltaY,
                detail = e.originalEvent.detail;
                scrollY = $document.height() > $window.height();
                scrollX = $document.width() > $window.width();
                scroll = true;
            
            if (scrollY) {
                
                view = $window.height();
                    
                if (deltaY < 0 || detail > 0)
            
                    root = (root + view) >= $document.height() ? root : root += step;
                
                if (deltaY > 0 || detail < 0)
            
                    root = root <= 0 ? 0 : root -= step;
                
                $body.stop().animate({
            
                    scrollTop: root
                
                }, speed, option, function() {
            
                    scroll = false;
                
                });
            }
            
            if (scrollX) {
                
                view = $window.width();
                    
                if (deltaY < 0 || detail > 0)
            
                    root = (root + view) >= $document.width() ? root : root += step;
                
                if (deltaY > 0 || detail < 0)
            
                    root = root <= 0 ? 0 : root -= step;
                
                $body.stop().animate({
            
                    scrollLeft: root
                
                }, speed, option, function() {
            
                    scroll = false;
                
                });
            }
            
            return false;
            
        }).on('scroll', function() {
            
            if (scrollY && !scroll) root = $window.scrollTop();
            if (scrollX && !scroll) root = $window.scrollLeft();
            
        }).on('resize', function() {
            
            if (scrollY && !scroll) view = $window.height();
            if (scrollX && !scroll) view = $window.width();
            
        });       
    };

    jQuery.easing.default = function (x,t,b,c,d) {

        return -c * ((t=t/d-1)*t*t*t - 1) + b;
    };

    })(jQuery);   
	*/
});
