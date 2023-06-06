$(function () {
    console.log("ani width : " + aniSlidewidth);
    console.log("갯수: " + viewNum);
    console.log("padding : " + slidContpadding);
    console.log("멤버수: " + memNum);
    console.log("memnum/viewnum:" + quotient);
    console.log("mod: " + mod);

    $(".fa-chevron-left").click(function () {
        if (slideIndx == 0) {
        } else {
            slideIndx--;
            memImgSlide(slideIndx);
        }
    });

    $(".fa-chevron-right").click(function () {
        //if(slideIndx>=(memNum-viewNum)) {
        if (slideIndx >= quotient) {
        } else {
            slideIndx++;
            memImgSlide(slideIndx);
        }
    });
});

let memNum = memberNum(1, 50); // 소모임 멤버 받아오기 현재는 1~50 랜덤
let slideIndx = 0; //image slide index
let aniSlidewidth; //animationSlide width 값 받아오는 변수
let viewNum; //animationSlide 화면에 보여질 멤버 수
let slidContpadding; //slideContent div의 padding
let quotient;
let mod;

// 이미지 슬라이드 함수
function memImgSlide(slideIndx) {
    console.log("memnum/viewnum:" + quotient);
    console.log("sliIndx: " + slideIndx);
    console.log("mod: " + mod);
    console.log("quotient: " + quotient);

    if (memNum <= viewNum) {
        // 총멤버수가 한번에 보여지는 수보다 작을떄
        $(".fa-solid").removeClass("btnActive");
        $(".btnEventPrev").css({
            cursor: "default",
            color: "#999",
        });
        $(".btnEventNext").css({
            cursor: "default",
            color: "#999",
        });
    }
    if (slideIndx == 0 && quotient > 0) {
        $(".btnEventPrev").css({
            cursor: "default",
            color: "#999",
        });
        $(".btnEventNext").css({
            cursor: "pointer",
            color: "#222",
        });
        $(".sliderPanel").animate({
            left: -((125 + slidContpadding * 2) * slideIndx * viewNum),
        });

        console.log("여긴가1");
    }

    if (slideIndx >= quotient && mod != 0 && quotient != 0) {
        $(".btnEventPrev").css({
            cursor: "pointer",
            color: "#222",
        });
        $(".btnEventNext").css({
            cursor: "default",
            color: "#999",
        });
        $(".sliderPanel").animate({
            left: -((125 + slidContpadding * 2) * slideIndx * viewNum - (125 + slidContpadding * 2) * (viewNum - mod)),
        });
        console.log(-((125 + slidContpadding * 2) * slideIndx * viewNum));
        console.log($(".sliderPanel").width());
        console.log("여긴가2");
    }
    if (slideIndx != 0 && slideIndx < quotient) {
        $(".btnEventPrev").css({
            cursor: "pointer",
            color: "#222",
        });
        $(".btnEventNext").css({
            cursor: "pointer",
            color: "#222",
        });
        $(".sliderPanel").animate({
            left: -((125 + slidContpadding * 2) * slideIndx * viewNum),
        });
        console.log("여긴가3");
    }

    if (slideIndx == quotient - 1 && mod == 0) {
        $(".btnEventNext").css({
            cursor: "default",
            color: "#999",
        });
        $(".sliderPanel").animate({
            left: -((125 + slidContpadding * 2) * slideIndx * viewNum),
        });
        console.log("여긴가4");
    }
}
