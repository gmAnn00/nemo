$(function(){
    memImageSlidePanel();
    console.log("ani width : "+aniSlidewidth);
    console.log("갯수: " + viewNum);
    console.log("padding : "+ slidContpadding);
    console.log("멤버수: "+ memNum);
    console.log("memnum/viewnum:" + quotient);
    console.log("mod: "+ mod); 

    $('.fa-chevron-left').click(function(){
        if(slideIndx==0) {

        } else {
            slideIndx--;
            memImgSlide(slideIndx);
        }
    });
    
    $('.fa-chevron-right').click(function(){
        //if(slideIndx>=(memNum-viewNum)) {
        if(slideIndx>=quotient){
        } else {
            slideIndx++;
            memImgSlide(slideIndx);
        }
    });

});

let memNum=memberNum(1,50); // 소모임 멤버 받아오기 현재는 1~50 랜덤
let slideIndx=0;    //image slide index
let aniSlidewidth;  //animationSlide width 값 받아오는 변수
let viewNum;        //animationSlide 화면에 보여질 멤버 수 
let slidContpadding; //slideContent div의 padding
let quotient;
let mod;

//소모임 멤버 수 받아오는 함수
function memberNum(min,max) {
    return Math.floor(Math.random() * (max - min)) + min;
}

//이미지 슬라이드 패널 생성하는 함수
function memImageSlidePanel() {
    //let width=(memNum*125)+((memNum+2)*13);
    aniSlidewidth=$('.animationSlide').width();
    viewNum=Math.floor(aniSlidewidth/125);
    slidContpadding=Math.floor((aniSlidewidth-(viewNum*125))/(viewNum*2));
    quotient = Math.floor(memNum/viewNum);
    mod=memNum%viewNum;
    
    $('.currentNum').html(memNum);
    $('.sliderPanel').css('width', (125+(slidContpadding*2))*memNum);

    //소모임 멤버 수 만큼 멤버프로필 div생성
    for(let i=0; i<memNum; i++) {
        let item='';
        item+='<div class="slideContent">';
        $('.slideContent').css("padding",slidContpadding);  //padding 값 세팅 
        item+='<div class="memImg"></div><br>';        //이 부분에서 멤버 프로필 사진 받아와서 프사넣기
        item+='<span class="memName">member'+(i+1)+'</span>'; //// 여기 멤버이름
        item+='</div>';
        $('.sliderPanel').append(item); //생성한 멤버 프로필 슬라이더패널에 추가하기
    }

    memImgSlide(slideIndx);
    
}


// 이미지 슬라이드 함수
function memImgSlide(slideIndx) {
    console.log("memnum/viewnum:" + quotient);
    console.log("sliIndx: "+ slideIndx); 
    console.log("mod: "+ mod);
    console.log("quotient: "+quotient);

    if(memNum<=viewNum) { // 총멤버수가 한번에 보여지는 수보다 작을떄
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
    if(slideIndx==0&&quotient>0){
        $('.btnEventPrev').css({
            cursor:'default',
            color:'#999'
            
        });         
        $('.btnEventNext').css({
            cursor:'pointer',
            color:'#222'
        });
        $('.sliderPanel').animate({
            left: -((125+(slidContpadding*2))*slideIndx*viewNum)
        });

        console.log('여긴가1');
    }

    if(slideIndx>=quotient && mod!=0 && quotient!=0) {
        $('.btnEventPrev').css({
            cursor:'pointer',
            color:'#222'
            
        });
        $('.btnEventNext').css({
            cursor:'default',
            color:'#999'
        });
        $('.sliderPanel').animate({
            left: -(((125+(slidContpadding*2))*slideIndx*viewNum)-((125+(slidContpadding*2))*(viewNum-mod)))
        });
        console.log(-((125+(slidContpadding*2))*slideIndx*viewNum));
        console.log($('.sliderPanel').width())
        console.log('여긴가2');
    }
    if(slideIndx!=0&&slideIndx<quotient){
        $('.btnEventPrev').css({
            cursor:'pointer',
            color:'#222'
        });
        $('.btnEventNext').css({
            cursor:'pointer',
            color:'#222'
        });
        $('.sliderPanel').animate({
            left: -((125+(slidContpadding*2))*slideIndx*viewNum)
        });
        console.log('여긴가3');
    }

    if(slideIndx==(quotient-1) && mod ==0 ) {
        $('.btnEventNext').css({
            cursor:'default',
            color:'#999'
        });
        $('.sliderPanel').animate({
            left: -((125+(slidContpadding*2))*slideIndx*viewNum)
        });
        console.log('여긴가4');
    }
}