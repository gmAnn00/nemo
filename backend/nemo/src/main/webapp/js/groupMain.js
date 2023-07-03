$(function(){
	let memNum=$("#groupNum_hidden").val()// 소모임 멤버 받아오기
	let slideIndx=0;    //image slide index
	let aniSlidewidth=0;  //animationSlide width 값 받아오는 변수
	let viewNum=0;        //animationSlide 화면에 보여질 멤버 수 
	let slidContpadding=0; //slideContent div의 padding
	let quotient=0;
	let mod=0;
	
	$(".sliderPanel").css("width", (memNum*160));
	
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
    
    console.log("memNum="+memNum);


	
	//이미지 슬라이드 패널 생성하는 함수
function memImageSlidePanel() {
	console.log("width="+$('.animationSlide').width());
    //let width=(memNum*125)+((memNum+2)*13);
    aniSlidewidth=$('.animationSlide').width();
    
    console.log("aniSlidewidth="+aniSlidewidth);
    //viewNum=Math.floor(aniSlidewidth/125);
    viewNum=6;
    //slidContpadding=Math.floor((aniSlidewidth-(viewNum*125))/(viewNum*2));
    let slidContpaddingNew = 10;
    slidContpadding = 11;
    quotient = Math.floor(memNum/viewNum);
    mod=memNum%viewNum;
    
    $('.memImg').css('padding-left',slidContpaddingNew);
    $('.memImg').css('padding-right',slidContpaddingNew);
    
    $('.currentNum').html(memNum);
    //$('.sliderPanel').css('width', (125+(slidContpadding*2))*memNum);

    //소모임 멤버 수 만큼 멤버프로필 div생성
    /*
    for(let i=0; i<memNum; i++) {
        let item='';
        item+='<div class="slideContent">';
        $('.slideContent').css("padding",slidContpadding);  //padding 값 세팅 
        item+='<div class="memImg"></div><br>';        //이 부분에서 멤버 프로필 사진 받아와서 프사넣기
        item+='<span class="memName">member'+(i+1)+'</span>'; //// 여기 멤버이름
        item+='</div>';
        $('.sliderPanel').append(item); //생성한 멤버 프로필 슬라이더패널에 추가하기
    }
*/
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

let user_id = $("#user_id_hidden").val();
let isMember = $("#isMember_hidden").val();
let isAdmin = $("#isAdmin_hidden").val();
console.log(user_id);
console.log(typeof user_id);

console.log(isMember);
console.log(typeof isMember);

console.log("isAdmin",isAdmin);
console.log(typeof isAdmin);

let group_id = new URL(location.href).searchParams;
group_id = group_id.get("group_id");
console.log("group_id=" + group_id);

if(user_id == ""){
	alert("로그인 후 이용할 수 있습니다.");
	location.href="/nemo/login/loginForm";
}
if(user_id != "" && isMember == "false" && isAdmin =='0'){
	alert("소모임 가입 후 이용할 수 있습니다.");
	location.href="/nemo/group/groupInfo?group_id="+group_id;
}

});







