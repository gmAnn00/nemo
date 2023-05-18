$(function(){
    memImageSlidePanel();
    $('.fa-chevron-left').click(function(){
        if(slideIndx==0) {

        } else {
            slideIndx--;
            memImgSlide(slideIndx);
        }
    });

    $('.fa-chevron-right').click(function(){
        if(slideIndx==memNum) {
        } else {
            slideIndx++;
            memImgSlide(slideIndx);
        }
    });

    //
    $('.currentNum').html(memNum);
});
let memNum=memberNum(1,50); // 소모임 멤버 받아오기 현재는 1~50 랜덤
let slideIndx=0;    //image slide index


//소모임 멤버 수 받아오는 함수
function memberNum(min,max) {
    return Math.floor(Math.random() * (max - min)) + min;
}

//이미지 슬라이드 패널 생성하는 함수
function memImageSlidePanel() {
    //let width=(memNum*125)+((memNum+2)*13);
    
    console.log(141*memNum);
    console.log(memNum);
    $('.sliderPanel').css('width', 141*memNum);

    //소모임 멤버 수 만큼 멤버프로필 div생성
    for(let i=0; i<memNum; i++) {
        let item='';
        item+='<div class="slideContent">';
        item+='<div class="memImg"></div><br>';        //이 부분에서 멤버 프로필 사진 받아와서 프사넣기
        item+='<span class="memName">member'+(i+1)+'</span>'; //// 여기 멤버이름
        item+='</div>';
        $('.sliderPanel').append(item); //생성한 멤버 프로필 슬라이더패널에 추가하기
    }
    memImgSlide(slideIndx);
}


// 이미지 슬라이드 함수
function memImgSlide(slideIndx) {
    if(memNum<6) {
        /*$('.fa-solid').removeClass('btnActive');
        $('.btnEventPrev').css({
            cursor:'default',
            color:'#999'
        });
        $('.btnEventNext').css({
            cursor:'default',
            color:'#999'
        });*/
        $('.fa-solid').css({
            display:'none'
        });
    }
    if(slideIndx<(memNum-4)&&slideIndx>0) { //화살표 색상 가능하게함
        $('.fa-solid').addClass('btnActive');
        $('.sliderPanel').animate({
            left: -(141*slideIndx)
        });
        
    }
    if(slideIndx==0 && !(memNum<6)) {
        $('.fa-solid').removeClass('btnActive');
        $('.btnEventPrev').css({
            cursor:'default',
            color:'#999'
        });
        $('.btnEventNext').css({
            cursor:'pointer',
            color:'#222'
        });
        $('.sliderPanel').animate({
            left: -(141*slideIndx)
        });
        
    }
    if(slideIndx==(memNum-5)) {
        $('.fa-solid').removeClass('btnActive');
        $('.btnEventNext').css({
            cursor:'default',
            color:'#999'
        });
        $('.btnEventPrev').css({
            cursor:'pointer',
            color:'#222'
        });
    }
}