$(document).ready(function () {
	let main_name_hidden = $("#main_name_hidden").val();
	let sub_name_hidden = $("#sub_name_hidden").val();
	
	$("select[name=main_name]").val(main_name_hidden).trigger("change");
	$("select[name=sub_name]").val(sub_name_hidden);
});

const category = {
    "문화 · 공연 · 축제": ["뮤지컬 · 오페라", "공연 · 연극", "영화", "전시회", "연기 · 공연제작", "문화재 탐방", "파티 · 페스티벌"],
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
};

function selectInit() {
	console.log("selectInit 호출");
	
    let mainHtml = `<option value="">대분류</option>`;
    let smallHtml = `<option value="">소분류</option>`;
    
    //let main_name_hidden = $(main_name_hidden).val();
    
    
    for (const key in category) {
        mainHtml += `<option value="${key}">${key}</option>`;

        const list = category[key];
        for (let i = 0; i < list.length; i++) {
			//console.log("list=", list[i]);
            smallHtml += `<option value='${list[i]}' data-class='${key}'>${list[i]}</option>`;
        }
    }

    $("select[name=main_name]").html(mainHtml);
    $("select[name=sub_name]").html(smallHtml);

    $("select[name=sub_name] option").each(function (idx, item) {
        if ($(this).val == "") {
            return true;
        }
        $(this).hide();
    });
}

selectInit();



$(document).on("change", "select[name=main_name]", function () {
    const mainVal = $(this).val();
    $("select[name=sub_name] option").each(function (idx, item) {
        if ($(this).data("class") == mainVal || $(this).val == "") {
            $(this).show();
            //console.log(mainVal);
        } else {
            $(this).hide();
        }
    });
    $("select[name=sub_name]").val("");
});

$(document).on("change", "select[name=sub_name]", function(){
	console.log("소분류=", $(this).val());
});

function fn_category_selected(select){	
	let main_name_hidden = $("#main_name_hidden").val();
	let sub_name_hidden = $("#sub_name_hidden").val();
	
	if($(select).val() == ''){ 
		console.log("not selected");
		$(select).removeClass("selected");
		$(select).parent("div").css("border", "1px solid #ccc");
		
		
	}else{
		if($(select).val() == main_name_hidden) {
			$(".commonSelect.input").css("border", "1px solid #ccc");
			console.log("1");
			console.log("1select", $(select).val());
		}else{
			console.log("2");
			console.log("2select", $(select).val());
			$(select).addClass("selected");
			$(select).parent("div").css("border", "none");
			$(".commonSelect.input.selected").css("border", "2px solid var(--sub-color)");
		}
		
		if($(select).val() == sub_name_hidden){
			console.log("3");
			console.log("3select", $(select).val());
		}else{
			console.log("4");
			console.log("4select", $(select).val());
			$(select).addClass("selected");
			$(select).parent("div").css("border", "none");
			$(".commonSelect.select.selected").css("border", "2px solid var(--sub-color)");
		}
		
	}
}

//textarea 글자수 카운터
$(".textAreaTd>textarea").keyup(function () {
  let inputLength = $(this).val().length;
  if (inputLength > 1000) {
    inputLength.substring(0, 1000);
  }
  $(".textAreaTd>p>span").html(inputLength + "/1000");
});

function readImage(input) {
  // 인풋 태그에 파일이 있는 경우
  if (input.files && input.files[0]) {
    // 이미지 파일인지 검사 (생략)
    // FileReader 인스턴스 생성
    const reader = new FileReader();
    // 이미지가 로드가 된 경우
    reader.onload = (e) => {
      const previewImage = document.getElementById("previewImage");
      previewImage.src = e.target.result;
    };
    // reader가 이미지 읽도록 하기
    reader.readAsDataURL(input.files[0]);
  }
  // 업로드한 파일 주소값 보기
  //console.log($('#file').val());
}

function popupImgFileRm() {
  $("#grp_img").val("");
  document.getElementById("previewImage").src = "../../images/tmp01.jpg";
}

// input file에 change 이벤트 부여
const inputImage = document.getElementById("file");
inputImage.addEventListener("change", (e) => {
  readImage(e.target);
});

//우편번호 찾기
function execDaumPostcode() {
  new daum.Postcode({
    oncomplete: function (data) {
      // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

      // 각 주소의 노출 규칙에 따라 주소를 조합한다.
      // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
      var addr = ""; // 주소 변수

      // var extraAddr = ''; // 참고항목 변수

      //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
      if (data.userSelectedType === "R") {
        // 사용자가 도로명 주소를 선택했을 경우
        addr = data.roadAddress;
      } else {
        // 사용자가 지번 주소를 선택했을 경우(J)
        addr = data.jibunAddress;
      }

      /*
            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("sample6_extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }*/

      // 우편번호와 주소 정보를 해당 필드에 넣는다.
      document.getElementById("zipcode").value = data.zonecode;
      document.getElementById("grp_addr1").value = addr;
      // 커서를 상세주소 필드로 이동한다.
      // document.getElementById("zipcode").readOnly = true;
      // document.getElementById("address").readOnly = true;
      $("#grp_addr1").css({
        background: "var(--sub3-color)",
      });
      $("#zipcode").css({
        background: "var(--sub3-color)",
      });
      document.getElementById("grp_addr2").focus();
    },
  }).open();
}


