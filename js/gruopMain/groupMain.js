$(function(){
    memImageSlidePanel();

    /*
    $('.fa-chevron-left').click(function(){
        if(qoutient==1 && mod==0) {
        
        } else {
            cnt--;
            memImgSlide(cnt);
        }
    });

    $('.fa-chevron-right').click(function(){
        if(cnt==(qoutient+1)) {

        }else {
            cnt++;
            memImgSlide(cnt);
        }
    });
    */


    
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
    
});
let memNum=memberNum(1,50); // 소모임 멤버 받아오기 현재는 1~50 랜덤
let slideIndx=0;    //image slide index
let mod=memNum%5;
let qoutient=memNum/5;
let slideCnt=0;
let cnt=0;

let aniSlideWidth=$('.animationSlide').width();


//소모임 멤버 수 받아오는 함수
function memberNum(min,max) {
    return Math.floor(Math.random() * (max - min)) + min;
}

//이미지 슬라이드 패널 생성하는 함수
function memImageSlidePanel() {
    $('.currentNum').html(memNum);
    let width=(memNum*125)+((memNum+2)*13);
    
    /*let aniWidth=$('.animationSlide').width();
    let _width=aniWidth/5;
    console.log("animationSlide 너비: " +aniWidth);
    console.log("너비: " +_width);
    $(".memImg").css({
        width: _width,
        height: _width,
    });*/

    //$('.sliderPanel').css('width', _width*memNum); 
    //console.log(141*memNum);
    //console.log(memNum);
    
    //let aniWidth = $('.animationSlide').width();
    //let modWidth = aniWidth-(125*5);
    //let padding = modWidth/5;
    //let width = (memNum*125)+((memNum+2)*padding);

    $('.sliderPanel').css('width', width*memNum); 
    //동적으로 바꿔줘야함ㅋㅋㅋㅋㅋㅋ
    // .animationSlide의 width를 받아와서
    // (한개의이미지width+패딩)*memNum;
    // 한개의 이미지의 width는 animationSlide의 width를 받아와서 퍼센트계산
    // 패딩도... animationSldie의 넓이를 받아와 퍼센트 계산
    //

    //소모임 멤버 수 만큼 멤버프로필 div생성
    for(let i=0; i<memNum; i++) {
        let item='';
        /*$(".memImg").css({
            width: _width,
            height: _width,
            borderRadius: "50%"
        });*/
        
        item+='<div class="slideContent">';

        item+='<div class="memImg" </div><br>';        //이 부분에서 멤버 프로필 사진 받아와서 프사넣기
        item+='<span class="memName">member'+(i+1)+'</span>'; //// 여기 멤버이름
        item+='</div>';
        /*
        $('.slideContent').css("paddingLeft",padding);
        $('.slideContent').css("paddingRight",padding/2);
        */
        //위에서 계산한 width값을 너비높이로 설정해줌, $('memImg').css('width',계싼값)
        $('.sliderPanel').append(item); //생성한 멤버 프로필 슬라이더패널에 추가하기
    }
    memImgSlide(cnt);
}


// 이미지 슬라이드 함수
function memImgSlide(indexNum) {

    console.log("cnt:" + cnt);
    console.log("몫:" + qoutient);
    console.log("나머지" + mod);
    console.log("멤버수:" + memNum);

    if(memNum<6) { // 총멤버수가 5이하일떄
        $('.fa-solid').removeClass('btnActive');
        $('.btnEventPrev').css({
            cursor:'default',
            color:'#999'
        });
        $('.btnEventNext').css({
            cursor:'default',
            color:'#999'
        });
    }

    /*
    if(((indexNum==0) && (qoutient>1))){
        $('.fa-chevron').addClass('btnActive');
        $('.fa-chevron-left').css({
            cursor:'default',
            color:'#999'
        });
    }
    if((indexNum!=0)&&(indexNum<qoutient)){
        $('.fa-solid').addClass('btnActive');
        $('.sliderPanel').animate({
            left: -(141*indexNum*5)
        });
    }
    if(indexNum>=qoutient) {
        $('.fa-chevron-right').css({
            cursor:'default',
            color:'#999'
        }); 
        $('.sliderPanel').animate({
            left: -(141*mod)
        });
    }
    */



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

    // cnt랑
    
}