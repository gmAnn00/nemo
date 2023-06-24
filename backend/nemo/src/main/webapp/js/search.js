$(function() {
	cAreaTop = $("header").css("height");
	cAreaTop = parseInt(cAreaTop);
	console.log(cAreaTop);
	$(".categoriesArea").css("top", cAreaTop + "px");

	$(".grpLike").on("click", function() {
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

	const category = {
    "문화 · 공연 · 축제": ["뮤지컬 · 오페라", "공연 · 연극", "영화,", "전시회", "연기 · 공연제작", "문화재 탐방", "파티 · 페스티벌"],
    "음악 · 악기": [
        "노래 · 보컬",
        "기타 · 베이스",
        "우쿨렐레",
        "드럼",
        "피아노",
        "바이올린",
        "플롯",
        "오카리나",
        "밴드 · 합주",
        "작사 · 작곡",
        "인디음악",
        "랩 · 힙합 · DJ",
        "클래식",
        "재즈",
        "락 · 메탈",
        "일렉트로닉",
        "국악 · 사물놀이",
        "찬양 · CCM",
        "뉴에이지",
    ],
    "사진 · 영상": ["DSLR", "필름카메라", "영상제작", "디지털카메라"],
    "아웃도어 · 여행": ["등산", "산책 · 트래킹", "캠핑 · 백패킹", "국내여행", "해외여행", "낚시", "패러글라이딩"],
    "운동 · 스포츠": [
        "자전거",
        "배드민턴",
        "볼링",
        "테니스 · 스쿼시",
        "스키 · 보드",
        "골프",
        "클라이밍",
        "다이어트",
        "헬스 · 크로스핏",
        "요가 · 필라테스",
        "탁구",
        "당구 · 포켓볼",
        "러닝 · 마라톤",
        "수영 · 스쿠버다이빙",
        "서핑 · 웨이크보드 · 요트",
        "축구 · 풋살",
        "농구",
        "야구",
        "배구",
        "승마",
        "펜싱",
        "복싱",
        "태권도 · 유도",
        "검도",
        "무술 · 주짓수",
        "스케이트 · 인라인",
        "크루즈보드",
        "족구",
        "양궁",
    ],
    "인문학 · 책 · 글": ["책 · 독서", "인문학", "심리학", "철학", "역사", "시사 · 경제", "작문 · 글쓰기"],
    "공예 · 만들기": [
        "미술 · 그림",
        "캘리그라피",
        "플라워아트",
        "캔들 · 디퓨져 · 석고",
        "천연비누 · 화장품",
        "소품공예",
        "가죽공에",
        "가구 · 목공예",
        "설탕 · 앙금공예",
        "도자 · 점토공예",
        "자수 · 뜨개",
        "키덜트 · 프라모델",
        "메이크업 · 네일",
    ],
    "댄스 · 무용": ["라틴댄스", "사교댄스", "방송 · 힙합", "스트릿댄스", "발레", "재즈댄스", "한국무용", "밸리댄스", "현대무용", "스윙댄스"],
    "봉사활동": ["양로원", "보육원", "환경봉사", "사회봉사", "교육 · 재능 나눔", "유기동물 보호"],
    "사교 · 인맥": ["지역", "인맥", "성별", "싱글 · 연애", "기혼 · 유부", "돌싱", "와인 · 커피 · 차", "맛집 · 미식회"],
    "차 · 오토바이": ["현대", "기아", "르노", "GM", "쌍용", "바이크"],
    "스포츠 관람": ["야구", "배구", "농구"],
    "게임 · 오락": ["보드게임", "온라인게임", "콘솔게임", "단체놀이", "타로카드", "마술", "바둑"],
    "요리 · 제조": ["한식", "중식", "일식", "베이킹 · 제과", "핸드드립", "소믈리에 · 와인", "주류제조 · 칵테일"],
    "반려동물": ["강아지", "고양이", "물고기", "파충류", "설치류 · 중치류"],
    "기타" : ["기타"],
};

	function selectInit() {
		let mainHtml = "<option value='none'>대분류</option>";
		let smallHtml = "<option value='none'>소분류</option>";

		for (const key in category) {
			mainHtml += `<option value="${key}">${key}</option>`;

			const list = category[key];
			for (let i = 0; i < list.length; i++) {
				smallHtml += `<option value=${list[i]} data-class='${key}'>${list[i]}</option>`;
			}
		}

		$("select[name=bigCate]").html(mainHtml);
		$("select[name=smallCate]").html(smallHtml);

		$("select[name=smallCate] option").each(function(idx, item) {
			if ($(this).val == "") {
				return true;
			}
			$(this).hide();
		});
	}

	selectInit();

	$(document).on("change", "select[name=bigCate]", function() {
		const mainVal = $(this).val();
		$("select[name=smallCate] option").each(function(idx, item) {
			if ($(this).data("class") == mainVal || $(this).val == "") {
				$(this).show();
				console.log(mainVal);
			} else {
				$(this).hide();
			}
		});
		$("select[name=smallCate]").val("");
	});

	// 가입가능한 소모임만 표시
	$("#joinAbleLabel").on("click", function() {
		$(this).toggleClass("sortAble");
	});

	// 정렬
	$(".buttonSort").on("click", function() {
		$(".buttonSort").not(this).removeClass("sortOn");
		if ($(this).hasClass("sortOn")) {
			console.log("hi");
			$(this).removeClass("sortOn");
		} else {
			$(this).addClass("sortOn");
		}
	});

	// 현재 위치 가져오기
	navigator.geolocation.getCurrentPosition(getSuccess, getError);

	// 가져오기 성공
	function getSuccess(position) {
		// 위도
		const lat = position.coords.latitude;
		// 경도
		const lng = position.coords.longitude;

		console.log("lat=" + lat);
		console.log("lng=" + lng);
		// 위도 경도 오차(m)
		const accuracy = Math.floor(position.coords.accuracy);

		let container = document.getElementById('groupMap'); //지도를 담을 영역의 DOM 레퍼런스
		let options = { //지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(lat, lng), //지도의 중심좌표.
			level: 3 //지도의 레벨(확대, 축소 정도)
		};

		let map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

		// 마커가 표시될 위치입니다 
		var markerPosition = new kakao.maps.LatLng(lat, lng);

		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
			position: markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);

	}

	// 가지오기 실패(거부)
	function getError() {
		alert('Geolocation Error');
	}

	// 검색 후 필터 표시
	let joinAble_hidden = $("#joinAble_hidden").val();
	if(joinAble_hidden == "on"){
		 $("#joinAbleLabel").trigger("click");
	}
	
	let sort_hidden = $("#sort_hidden").val();
	if(sort_hidden == "sortByName"){
		$("#buttonName").trigger("click");
	}else if(sort_hidden == "sortByBookmark"){
		$("#buttonInterest").trigger("click");
	}else if(sort_hidden == "sortByNumber"){
		$("#buttonMember").trigger("click");
	}
	
	let main_name_hidden = $("#main_name_hidden").val();
	console.log("main_name_hidden=", main_name_hidden);
	if(main_name_hidden != "none"){
		$("select[name=bigCate]").val(main_name_hidden).prop("selected", true);
		
	}
	
	let sub_name_hidden = $("#sub_name_hidden").val();
	if(sub_name_hidden != "none"){
		$("select[name=smallCate]").val(sub_name_hidden).prop("selected", true);
	}
	

});

let checkOnOff = false; //찜인지 아닌지 체크하는 변수
