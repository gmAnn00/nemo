$(function () {
    $(".grpLike").on("click", function () {
        // grpLike 클래스 클릭시 ♡♥ 토글됨
        $(this).toggleClass("on");

        // 찜했는지 안했는지 체크하는 부분
        if ($(this).hasClass("on")) {
            checkOnOff = true;
        } else {
            checkOnOff = false;
        }
        console.log(checkOnOff);
    });

    /*$(".joinBtn").on("click", function () {
        alert("로그인 후 이용해주세요");
    });*/
});

let checkOnOff = false; //찜인지 아닌지 체크하는 변수
