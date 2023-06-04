const category = {
    "문화/공연/축제": ["뮤지컬/오페라", "공연/연극", "영화,", "전시회", "연기/공연제작", "문화재 탐방", "파티/페스티벌"],
    "음악/악기": [
        "노래/보컬",
        "기타/베이스",
        "우쿨렐레",
        "드럼",
        "피아노",
        "바이올린",
        "플롯",
        "오카리나",
        "밴드/합주",
        "작사/작곡",
        "인디음악",
        "랩/힙합/DJ",
        "클래식",
        "재즈",
        "락/메탈",
        "일렉트로닉",
        "국악/사물놀이",
        "찬양/CCM",
        "뉴에이지",
    ],
    "사진/영상": ["DSLR", "필름카메라", "영상제작", "디지털카메라"],
    "아웃도어/여행": ["등산", "산책/트래킹", "캠핑/백패킹", "국내여행", "해외여행", "낚시", "패러글라이딩"],
    "운동/스포츠": ["축구", "농구", "야구", "배구"],
    "인문학/책/글": ["책/독서", "인문학", "심리학", "철학", "역사", "시사/경제", "작문/글쓰기"],
    "공예/만들기": [
        "미술/그림",
        "캘리그라피",
        "플라워아트",
        "캔들/디퓨져/석고",
        "천연비누/화장품",
        "소품공예",
        "가죽공에",
        "가구/목공예",
        "설탕/앙금공예",
        "도자/점토공예",
        "자수/뜨개",
        "키덜트/프라모델",
        "메이크업/네일",
    ],
    "댄스/무용": ["라틴댄스", "사교댄스", "방송/힙합", "스트릿댄스", "발레", "재즈댄스", "한국무용", "밸리댄스", "현대무용", "스윙댄스"],
    봉사활동: ["양로원", "보육원", "환경봉사", "사회봉사", "교육/재능 나눔", "유기동물 보호"],
    "사교/인맥": ["지역", "인맥", "성별", "싱글/연애", "기혼/유부", "돌싱", "와인/커피/차", "맛집/미식회"],
    "차/오토바이": ["현대", "기아", "르노", "GM", "쌍용", "바이크"],
    "스포츠 관람": ["축구", "야구", "배구", "농구"],
    "게임/오락": ["보드게임", "온라인게임", "콘솔게임", "단체놀이", "타로카드", "마술", "바둑"],
    "요리/제조": ["한식", "중식", "일식", "베이킹/제과", "핸드드립", "소믈리에/와인", "주류제조/칵테일"],
    반려동물: ["강아지", "고양이", "물고기", "파충류", "설치류/중치류"],
};

function selectInit() {
    let mainHtml = "<option value=''>선택하세요.</option>";
    let smallHtml = "<option value=''>선택하세요.</option>";

    for (const key in category) {
        mainHtml += `<option value="${key}">${key}</option>`;

        const list = category[key];
        for (let i = 0; i < list.length; i++) {
            smallHtml += `<option value=${list[i]} data-class='${key}'>${list[i]}</option>`;
        }
    }

    $("select[name=adminMainCategory]").html(mainHtml);
    $("select[name=adminSmallCategory]").html(smallHtml);

    $("select[name=adminSmallCategory] option").each(function (idx, item) {
        if ($(this).val == "") {
            return true;
        }
        $(this).hide();
    });
}

selectInit();

$(document).on("change", "select[name=adminMainCategory]", function () {
    const mainVal = $(this).val();
    $("select[name=adminSmallCategory] option").each(function (idx, item) {
        if ($(this).data("class") == mainVal || $(this).val == "") {
            $(this).show();
            console.log(mainVal);
        } else {
            $(this).hide();
        }
    });
    $("select[name=adminSmallCategory]").val("");
});

$("table.adminGroupTbl").DataTable();
