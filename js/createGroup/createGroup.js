
let category0 = ["선택하기"];
let category1 = ["선택하기","뮤지컬/오페라","공연/연극","영화,","전시회","연기/공연제작","문화재 탐방","파티/페스티벌"];
let category2 = ["선택하기","노래/보컬","기타/베이스","우쿨렐레","드럼","피아노","바이올린","플롯","오카리나","밴드/합주","작사/작곡","인디음악","랩/힙합/DJ","클래식","재즈","락/메탈","일렉트로닉","국악/사물놀이","찬양/CCM","뉴에이지",];
let category3 = ["선택하기","DSLR", "필름카메라", "영상제작", "디지털카메라"];
let category4 = ["선택하기","등산","산책/트래킹","캠핑/백패킹","국내여행","해외여행","낚시","패러글라이딩"];
let category5 = ["선택하기","축구", "농구", "야구", "배구","러닝"];
let category6 = ["선택하기","책/독서","인문학","심리학","철학","역사","시사/경제","작문/글쓰기"];
let category7 = ["선택하기","업종/직무"];
let category8 = ["선택하기","미술/그림","캘리그라피","플라워아트","캔들/디퓨져/석고","천연비누/화장품","소품공예","가죽공에","가구/목공예","설탕/앙금공예","도자/점토공예","자수/뜨개","키덜트/프라모델","메이크업/네일"];
let category9 = ["선택하기","라틴댄스","사교댄스","방송/힙합","스트릿댄스","발레","재즈댄스","한국무용","밸리댄스","현대무용","스윙댄스"];
let category10 = ["선택하기","양로원","보육원","환경봉사","사회봉사","교육/재능 나눔","유기동물 보호"];
let category11 = ["선택하기","지역","인맥","성별","싱글/연애","기혼/유부","돌싱","와인/커피/차","맛집/미식회"];
let category12 = ["선택하기","현대", "기아", "르노", "GM", "쌍용","외제차","바이크"];
let category13 = ["선택하기","야구", "배구", "농구"];
let category14 = ["선택하기","보드게임","온라인게임","콘솔게임","단체놀이","타로카드","마술","바둑"];
let category15 = ["선택하기","한식","중식","일식","베이킹/제과","핸드드립","소믈리에/와인","주류제조/칵테일"];
let category16 = ["선택하기","강아지", "고양이", "물고기", "파충류", "설치류/중치류"];


// 소분류 카테고리
function change(add) {
    let target=document.getElementById("smallCate");
    let d;
    if(add.value=="0") {
        d=category0;
    } else if(add.value=="1") {
        d=category1;
    }else if(add.value=="2") {
        d=category2;
    }else if(add.value=="3") {
        d=category3;
    }else if(add.value=="4") {
        d=category4;
    }else if(add.value=="5") {
        d=category5;
    }else if(add.value=="6") {
        d=category6;
    }else if(add.value=="7") {
        d=category7;
    }else if(add.value=="8") {
        d=category8;
    }else if(add.value=="9") {
        d=category9;
    }else if(add.value=="10") {
        d=category10;
    }else if(add.value=="11") {
        d=category11;
    }else if(add.value=="12") {
        d=category12;
    }else if(add.value=="13") {
        d=category13;
    } else if(add.value=="14") {
        d=category14;
    } else if(add.value=="15") {
        d=category15;
    } else {
        d=category16;
    }
    target.options.length=0;
    for(x in d) {
        let opt = document.createElement("option");
        opt.value = d[x];
        opt.innerHTML = d[x];
        target.appendChild(opt);
    }
}

//textarea 글자수 카운터
$('.textAreaTd>textarea').keyup(function(){
    let inputLength=$(this).val().length;
    if(inputLength>1000) {
        inputLength.substring(0,1000);
    } 
    $('.textAreaTd>p>span').html(inputLength+'/1000');   
});