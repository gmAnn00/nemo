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