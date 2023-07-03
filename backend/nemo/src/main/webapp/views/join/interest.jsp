<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네모: 관심사</title>
 <link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
    <link rel="stylesheet" href="${contextPath}/css/common.css" />
    <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
    <link rel="stylesheet" href="${contextPath}/css/interest.css" />
    <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
    <script src="${contextPath}/js/interest.js"></script>
    <script src="https://kit.fontawesome.com/f9a2702e84.js" crossorigin="anonymous"></script>
    <script src="${contextPath}/js/header.js"></script>
</head>
<body>

<jsp:include page="../header.jsp" flush="true"></jsp:include>

	<form action="${contextPath}/join/interestsChoice" method="post" id="interestForm">

    <!-- 콘텐츠 영역 -->
    <div class="contentsArea">
      <!-- 단계 bar -->
      <div class="progress-bar">
        <div class="step step-3"><span>3단계. 관심사 입력하기</span></div>
        <div class="step step-2"><span>2단계. 회원가입하기</span></div>
        <div class="step step-1"><span>1단계. 약관동의</span></div>
      </div>
      <!-- 관심사 선택 영역 -->
      <div class="category">
        <div class="largeCategory">
          <!--대분류-->
          <h2>내 관심사</h2>
          <h3>대분류</h3>
          <button type="button" class="large btnInterest" onclick="showSubcategories('문화')">문화 · 공연 · 축제</button>
          <button type="button" class="large btnInterest" onclick="showSubcategories('음악')">음악 · 악기</button>
          <button type="button" class="large btnInterest" onclick="showSubcategories('사진')">사진 · 영상</button>
          <button type="button" class="large btnInterest" onclick="showSubcategories('아웃도어')">아웃도어 · 여행</button>
          <button type="button" class="large btnInterest" onclick="showSubcategories('운동')">운동 · 스포츠</button>
          <button type="button" class="large btnInterest" onclick="showSubcategories('인문학')">인문학 · 책 · 글</button>
          <!-- 
          <button type="button" class="large btnInterest" onclick="showSubcategories('업종')">업종 · 직무</button> -->
          <button type="button" class="large btnInterest" onclick="showSubcategories('외국')">외국 · 언어</button>
          <button type="button" class="large btnInterest" onclick="showSubcategories('공예')">공예 · 만들기</button>
          <button type="button" class="large btnInterest" onclick="showSubcategories('댄스')">댄스 · 무용</button>
          <button type="button" class="large btnInterest" onclick="showSubcategories('봉사활동')">봉사활동</button>
          <button type="button" class="large btnInterest" onclick="showSubcategories('사교')">사교 · 인맥</button>
          <button type="button" class="large btnInterest" onclick="showSubcategories('차')">차 · 오토바이</button>
          <button type="button" class="large btnInterest" onclick="showSubcategories('스포츠')">스포츠 관람</button>
          <button type="button" class="large btnInterest" onclick="showSubcategories('게임')">게임 · 오락</button>
          <button type="button" class="large btnInterest" onclick="showSubcategories('요리')">요리 · 제조</button>
          <button type="button" class="large btnInterest" onclick="showSubcategories('반려동물')">반려동물</button>
        </div>

        <!--소분류-->
        <h3>소분류</h3>
        <div class="smallCategory" id="smallCate">
          
          <!-- 문화, 공연, 축제 -->
          <div id="문화" style="display: none;">
            <button type="button" class="small btnInterest" data-class="문화 · 공연 · 축제" onclick="addMyInterest(this)" value="문화 · 공연 · 축제">문화 · 공연 · 축제</button>
            <button type="button" class="small btnInterest" data-class="문화 · 공연 · 축제" onclick="addMyInterest(this)" value="공연 · 연극">공연 · 연극</button>
            <button type="button" class="small btnInterest" data-class="문화 · 공연 · 축제" onclick="addMyInterest(this)" value="영화">영화</button>
            <button type="button" class="small btnInterest" data-class="문화 · 공연 · 축제" onclick="addMyInterest(this)" value="전시회">전시회</button>
            <button type="button" class="small btnInterest" data-class="문화 · 공연 · 축제" onclick="addMyInterest(this)" value="연기 · 공연제작">연기 · 공연제작</button>
            <button type="button" class="small btnInterest" data-class="문화 · 공연 · 축제" onclick="addMyInterest(this)" value="문화재 탐방">문화재 탐방</button>
            <button type="button" class="small btnInterest" data-class="문화 · 공연 · 축제" onclick="addMyInterest(this)" value="파티 · 페스티벌">파티 · 페스티벌</button>
          </div>
          <!-- 음악, 악기 -->
          <div id="음악" style="display: none;">
            <button type="button" class="small btnInterest" data-class="음악 · 악기" onclick="addMyInterest(this)" value="노래 · 보컬">노래 · 보컬</button>
            <button type="button" class="small btnInterest" data-class="음악 · 악기" onclick="addMyInterest(this)" value="기타 · 베이스">기타 · 베이스</button>
            <button type="button" class="small btnInterest" data-class="음악 · 악기" onclick="addMyInterest(this)" value="우쿨렐레">우쿨렐레</button>
            <button type="button" class="small btnInterest" data-class="음악 · 악기" onclick="addMyInterest(this)" value="드럼">드럼</button>
            <button type="button" class="small btnInterest" data-class="음악 · 악기" onclick="addMyInterest(this)" value="피아노">피아노</button>
            <button type="button" class="small btnInterest" data-class="음악 · 악기" onclick="addMyInterest(this)" value="바이올린">바이올린</button>
            <button type="button" class="small btnInterest" data-class="음악 · 악기" onclick="addMyInterest(this)" value="플룻">플룻</button>
            <button type="button" class="small btnInterest" data-class="음악 · 악기" onclick="addMyInterest(this)" value="오카리나">오카리나</button>
            <button type="button" class="small btnInterest" data-class="음악 · 악기" onclick="addMyInterest(this)" value="밴드 · 합주">밴드 · 합주</button>
            <button type="button" class="small btnInterest" data-class="음악 · 악기" onclick="addMyInterest(this)" value="작사 · 작곡">작사 · 작곡</button>
            <button type="button" class="small btnInterest" data-class="음악 · 악기" onclick="addMyInterest(this)" value="인디음악">인디음악</button>
            <button type="button" class="small btnInterest" data-class="음악 · 악기" onclick="addMyInterest(this)" value="랩 · 힙합 · DJ">랩 · 힙합 · DJ</button>
            <button type="button" class="small btnInterest" data-class="음악 · 악기" onclick="addMyInterest(this)" value="클래식">클래식</button>
            <button type="button" class="small btnInterest" data-class="음악 · 악기" onclick="addMyInterest(this)" value="재즈">재즈</button>
            <button type="button" class="small btnInterest" data-class="음악 · 악기" onclick="addMyInterest(this)" value="락 · 메탈">락 · 메탈</button>
            <button type="button" class="small btnInterest" data-class="음악 · 악기" onclick="addMyInterest(this)" value="일렉트로닉">일렉트로닉</button>
            <button type="button" class="small btnInterest" data-class="음악 · 악기" onclick="addMyInterest(this)" value="국악 · 사물놀이">국악 · 사물놀이</button>
            <button type="button" class="small btnInterest" data-class="음악 · 악기" onclick="addMyInterest(this)" value="찬양 · CCM">찬양 · CCM</button>
            <button type="button" class="small btnInterest" data-class="음악 · 악기" onclick="addMyInterest(this)" value="뉴에이지">뉴에이지</button>
          </div>
          <!-- 사진, 영상 -->
          <div id="사진" style="display: none;">
            <button type="button" class="small btnInterest" data-class="사진 · 영상" onclick="addMyInterest(this)" value="DSLR">DSLR</button>
            <button type="button" class="small btnInterest" data-class="사진 · 영상" onclick="addMyInterest(this)" value="필름카메라">필름카메라</button>
            <button type="button" class="small btnInterest" data-class="사진 · 영상" onclick="addMyInterest(this)" value="영상제작">영상제작</button>
            <button type="button" class="small btnInterest" data-class="사진 · 영상" onclick="addMyInterest(this)" value="디지털카메라">디지털카메라</button>
          </div>
          <!-- 아웃도어, 여행 -->
          <div id="아웃도어" style="display: none;">
            <button type="button" class="small btnInterest" data-class="아웃도어 · 여행" onclick="addMyInterest(this)" value="등산">등산</button>
            <button type="button" class="small btnInterest" data-class="아웃도어 · 여행" onclick="addMyInterest(this)" value="산책 · 트래킹">산책 · 트래킹</button>
            <button type="button" class="small btnInterest" data-class="아웃도어 · 여행" onclick="addMyInterest(this)" value="캠핑 · 백패킹">캠핑 · 백패킹</button>
            <button type="button" class="small btnInterest" data-class="아웃도어 · 여행" onclick="addMyInterest(this)" value="국내여행">국내여행</button>
            <button type="button" class="small btnInterest" data-class="아웃도어 · 여행" onclick="addMyInterest(this)" value="해외여행">해외여행</button>
            <button type="button" class="small btnInterest" data-class="아웃도어 · 여행" onclick="addMyInterest(this)" value="낚시">낚시</button>
            <button type="button" class="small btnInterest" data-class="아웃도어 · 여행" onclick="addMyInterest(this)" value="패러글라이딩">패러글라이딩</button>
          </div>
          <!-- 운동, 스포츠 -->
          <div id="운동" style="display: none;">
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="자전거">자전거</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="배드민턴">배드민턴</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="볼링">볼링</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="테니스 · 스쿼시">테니스 · 스쿼시</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="스키 · 보드">스키 · 보드</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="골프">골프</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="클라이밍">클라이밍</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="다이어트">다이어트</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="헬스 · 크로스핏">헬스 · 크로스핏</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="요가 · 필라테스">요가 · 필라테스</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="탁구">탁구</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="당구 · 포켓볼">당구 · 포켓볼</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="러닝 · 마라톤">러닝 · 마라톤</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="수영 · 스쿠버다이빙">수영 · 스쿠버다이빙</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="서핑 · 웨이크보드">서핑 · 웨이크보드</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="축구 · 풋살 ">축구 · 풋살</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="농구">농구</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="야구">야구</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="배구">배구</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="승마">승마</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="펜싱">펜싱</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="복싱">복싱</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="태권도 · 유도">태권도 · 유도</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="검도">검도</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="무술 · 주짓수">무술 · 주짓수</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="스케이트 · 인라인">스케이트 · 인라인</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="크루즈보드">크루즈보드</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="족구">족구</button>
            <button type="button" class="small btnInterest" data-class="운동 · 스포츠" onclick="addMyInterest(this)" value="양궁">양궁</button>
          </div>
          <!-- 인문학, 책, 글 -->
          <div id="인문학" style="display: none;">
            <button type="button" class="small btnInterest" data-class="인문학 · 책 · 글" onclick="addMyInterest(this)" value="책 · 독서">책 · 독서</button>
            <button type="button" class="small btnInterest" data-class="인문학 · 책 · 글" onclick="addMyInterest(this)" value="인문학">인문학</button>
            <button type="button" class="small btnInterest" data-class="인문학 · 책 · 글" onclick="addMyInterest(this)" value="심리학">심리학</button>
            <button type="button" class="small btnInterest" data-class="인문학 · 책 · 글" onclick="addMyInterest(this)" value="철학">철학</button>
            <button type="button" class="small btnInterest" data-class="인문학 · 책 · 글" onclick="addMyInterest(this)" value="역사">역사</button>
            <button type="button" class="small btnInterest" data-class="인문학 · 책 · 글" onclick="addMyInterest(this)" value="시사 · 경제">시사 · 경제</button>
            <button type="button" class="small btnInterest" data-class="인문학 · 책 · 글" onclick="addMyInterest(this)" value="작문 · 글쓰기">작문 · 글쓰기</button>
          </div>
          <!-- 업종, 직무 
          <div id="업종" style="display: none;">
            <button type="button" class="small btnInterest" onclick="addMyInterest(this)" value="업종 · 직무">업종 · 직무</button>
          </div>-->
          <!-- 외국, 언어 -->
          <div id="외국" style="display: none;">
            <button type="button" class="small btnInterest" data-class="외국 · 언어" onclick="addMyInterest(this)" value="영어">영어</button>
            <button type="button" class="small btnInterest" data-class="외국 · 언어" onclick="addMyInterest(this)" value="일본어">일본어</button>
            <button type="button" class="small btnInterest" data-class="외국 · 언어" onclick="addMyInterest(this)" value="중국어">중국어</button>
            <button type="button" class="small btnInterest" data-class="외국 · 언어" onclick="addMyInterest(this)" value="프랑스어">프랑스어</button>
            <button type="button" class="small btnInterest" data-class="외국 · 언어" onclick="addMyInterest(this)" value="스페인어">스페인어</button>
            <button type="button" class="small btnInterest" data-class="외국 · 언어" onclick="addMyInterest(this)" value="러시아어">러시아어</button>
          </div>
          <!-- 공예, 만들기 -->
          <div id="공예" style="display: none;">
            <button type="button" class="small btnInterest" data-class="공예 · 만들기" onclick="addMyInterest(this)" value="미술 · 그림">미술 · 그림</button>
            <button type="button" class="small btnInterest" data-class="공예 · 만들기" onclick="addMyInterest(this)" value="캘리그라피">캘리그라피</button>
            <button type="button" class="small btnInterest" data-class="공예 · 만들기" onclick="addMyInterest(this)" value="플라워아트">플라워아트</button>
            <button type="button" class="small btnInterest" data-class="공예 · 만들기" onclick="addMyInterest(this)" value="캔들 · 디퓨저">캔들 · 디퓨저</button>
            <button type="button" class="small btnInterest" data-class="공예 · 만들기" onclick="addMyInterest(this)" value="천연비누 · 화장품">천연비누 · 화장품</button>
            <button type="button" class="small btnInterest" data-class="공예 · 만들기" onclick="addMyInterest(this)" value="소품공예">소품공예</button>
            <button type="button" class="small btnInterest" data-class="공예 · 만들기" onclick="addMyInterest(this)" value="가죽공예">가죽공예</button>
            <button type="button" class="small btnInterest" data-class="공예 · 만들기" onclick="addMyInterest(this)" value="가구 · 목공예">가구 · 목공예</button>
            <button type="button" class="small btnInterest" data-class="공예 · 만들기" onclick="addMyInterest(this)" value="설탕 · 앙금공예">설탕 · 앙금공예</button>
            <button type="button" class="small btnInterest" data-class="공예 · 만들기" onclick="addMyInterest(this)" value="도자 · 점토공예">도자 · 점토공예</button>
            <button type="button" class="small btnInterest" data-class="공예 · 만들기" onclick="addMyInterest(this)" value="자수 · 뜨개">자수 · 뜨개</button>
            <button type="button" class="small btnInterest" data-class="공예 · 만들기" onclick="addMyInterest(this)" value="키덜트 · 프라모델">키덜트 · 프라모델</button>
            <button type="button" class="small btnInterest" data-class="공예 · 만들기" onclick="addMyInterest(this)" value="메이크업 · 네일">메이크업 · 네일</button>
          </div>
          <!-- 댄스, 무용 -->
          <div id="댄스" style="display: none;">
            <button type="button" class="small btnInterest" data-class="댄스 · 무용" onclick="addMyInterest(this)" value="라틴댄스">라틴댄스</button>
            <button type="button" class="small btnInterest" data-class="댄스 · 무용" onclick="addMyInterest(this)" value="사교댄스">사교댄스</button>
            <button type="button" class="small btnInterest" data-class="댄스 · 무용" onclick="addMyInterest(this)" value="방송 · 힙합">방송 · 힙합</button>
            <button type="button" class="small btnInterest" data-class="댄스 · 무용" onclick="addMyInterest(this)" value="스트릿댄스">스트릿댄스</button>
            <button type="button" class="small btnInterest" data-class="댄스 · 무용" onclick="addMyInterest(this)" value="발레">발레</button>
            <button type="button" class="small btnInterest" data-class="댄스 · 무용" onclick="addMyInterest(this)" value="재즈댄스">재즈댄스</button>
            <button type="button" class="small btnInterest" data-class="댄스 · 무용" onclick="addMyInterest(this)" value="한국무용">한국무용</button>
            <button type="button" class="small btnInterest" data-class="댄스 · 무용" onclick="addMyInterest(this)" value="밸리댄스">밸리댄스</button>
            <button type="button" class="small btnInterest" data-class="댄스 · 무용" onclick="addMyInterest(this)" value="현대무용">현대무용</button>
            <button type="button" class="small btnInterest" data-class="댄스 · 무용" onclick="addMyInterest(this)" value="스윙댄스">스윙댄스</button>
          </div>
          <!-- 봉사활동 -->
          <div id="봉사활동" style="display: none;">
            <button type="button" class="small btnInterest" data-class="봉사활동" onclick="addMyInterest(this)" value="양로원">양로원</button>
            <button type="button" class="small btnInterest" data-class="봉사활동" onclick="addMyInterest(this)" value="보육원">보육원</button>
            <button type="button" class="small btnInterest" data-class="봉사활동" onclick="addMyInterest(this)" value="환경봉사">환경봉사</button>
            <button type="button" class="small btnInterest" data-class="봉사활동" onclick="addMyInterest(this)" value="사회봉사">사회봉사</button>
            <button type="button" class="small btnInterest" data-class="봉사활동" onclick="addMyInterest(this)" value="교육 · 재능나눔">교육 · 재능나눔</button>
            <button type="button" class="small btnInterest" data-class="봉사활동" onclick="addMyInterest(this)" value="유기동물 보호">유기동물 보호</button>
          </div>
          <!-- 사교, 인맥 -->
          <div id="사교" style="display: none;">
            <button type="button" class="small btnInterest" data-class="사교 · 인맥" onclick="addMyInterest(this)" value="지역">지역</button>
            <button type="button" class="small btnInterest" data-class="사교 · 인맥" onclick="addMyInterest(this)" value="인맥">인맥</button>
            <button type="button" class="small btnInterest" data-class="사교 · 인맥" onclick="addMyInterest(this)" value="성별">성별</button>
            <button type="button" class="small btnInterest" data-class="사교 · 인맥" onclick="addMyInterest(this)" value="싱글 · 연애">싱글 · 연애</button>
            <button type="button" class="small btnInterest" data-class="사교 · 인맥" onclick="addMyInterest(this)" value="기혼 · 유부">기혼 · 유부</button>
            <button type="button" class="small btnInterest" data-class="사교 · 인맥" onclick="addMyInterest(this)" value="돌싱">돌싱</button>
            <button type="button" class="small btnInterest" data-class="사교 · 인맥" onclick="addMyInterest(this)" value="와인 · 커피 · 차">와인 · 커피 · 차</button>
            <button type="button" class="small btnInterest" data-class="사교 · 인맥" onclick="addMyInterest(this)" value="맛집 · 미식회">맛집 · 미식회</button>
          </div>
          <!-- 차, 오토바이 -->
          <div id="차" style="display: none;">
            <button type="button" class="small btnInterest" data-class="차 · 오토바이" onclick="addMyInterest(this)" value="현대">현대</button>
            <button type="button" class="small btnInterest" data-class="차 · 오토바이" onclick="addMyInterest(this)" value="기아">기아</button>
            <button type="button" class="small btnInterest" data-class="차 · 오토바이" onclick="addMyInterest(this)" value="르노">르노</button>
            <button type="button" class="small btnInterest" data-class="차 · 오토바이" onclick="addMyInterest(this)" value="GM">GM</button>
            <button type="button" class="small btnInterest" data-class="차 · 오토바이" onclick="addMyInterest(this)" value="쌍용">쌍용</button>
            <button type="button" class="small btnInterest" data-class="차 · 오토바이" onclick="addMyInterest(this)" value="바이크">바이크</button>
          </div>
          <!-- 스포츠 관람 -->
          <div id="스포츠" style="display: none;">
            <button type="button" class="small btnInterest" data-class="스포츠 관람" onclick="addMyInterest(this)" value="야구">야구</button>
            <button type="button" class="small btnInterest" data-class="스포츠 관람" onclick="addMyInterest(this)" value="배구">배구</button>
            <button type="button" class="small btnInterest" data-class="스포츠 관람" onclick="addMyInterest(this)" value="농구">농구</button>
          </div>
          <!-- 게임, 오락 -->
          <div id="게임" style="display: none;">
            <button type="button" class="small btnInterest" data-class="게임 · 오락" onclick="addMyInterest(this)" value="보드게임">보드게임</button>
            <button type="button" class="small btnInterest" data-class="게임 · 오락" onclick="addMyInterest(this)" value="온라인게임">온라인게임</button>
            <button type="button" class="small btnInterest" data-class="게임 · 오락" onclick="addMyInterest(this)" value="콘솔게임">콘솔게임</button>
            <button type="button" class="small btnInterest" data-class="게임 · 오락" onclick="addMyInterest(this)" value="단체놀이">단체놀이</button>
            <button type="button" class="small btnInterest" data-class="게임 · 오락" onclick="addMyInterest(this)" value="타로카드">타로카드</button>
            <button type="button" class="small btnInterest" data-class="게임 · 오락" onclick="addMyInterest(this)" value="마술">마술</button>
            <button type="button" class="small btnInterest" data-class="게임 · 오락" onclick="addMyInterest(this)" value="바둑">바둑</button>
          </div>
          <!--요리, 제조-->
          <div id="요리" style="display: none;">
            <button type="button" class="small btnInterest" data-class="요리 · 제조" onclick="addMyInterest(this)" value="한식">한식</button>
            <button type="button" class="small btnInterest" data-class="요리 · 제조" onclick="addMyInterest(this)" value="중식">중식</button>
            <button type="button" class="small btnInterest" data-class="요리 · 제조" onclick="addMyInterest(this)" value="일식">일식</button>
            <button type="button" class="small btnInterest" data-class="요리 · 제조" onclick="addMyInterest(this)" value="베이킹 · 제과">베이킹 · 제과</button>
            <button type="button" class="small btnInterest" data-class="요리 · 제조" onclick="addMyInterest(this)" value="핸드드립">핸드드립</button>
            <button type="button" class="small btnInterest" data-class="요리 · 제조" onclick="addMyInterest(this)" value="소믈리에 · 와인">소믈리에 · 와인</button>
            <button type="button" class="small btnInterest" data-class="요리 · 제조" onclick="addMyInterest(this)" value="주류제조 · 칵테일">주류제조 · 칵테일</button>
          </div>
          <!-- 반려동물 -->
          <div id="반려동물" style="display: none;">
            <button type="button" class="small btnInterest" data-class="반려동물" onclick="addMyInterest(this)" value="강아지">강아지</button>
            <button type="button" class="small btnInterest" data-class="반려동물" onclick="addMyInterest(this)" value="고양이">고양이</button>
            <button type="button" class="small btnInterest" data-class="반려동물" onclick="addMyInterest(this)" value="물고기">물고기</button>
            <button type="button" class="small btnInterest" data-class="반려동물" onclick="addMyInterest(this)" value="파충류">파충류</button>
            <button type="button" class="small btnInterest" data-class="반려동물" onclick="addMyInterest(this)" value="설치류 · 중치류">설치류 · 중치류</button>
          </div>
          
        </div>
        
        <!--내 관심사-->
        <h3>내 관심사<span>(최대 3개 선택가능) 눌러서 제거가능</span></h3>
        <div class="myInterest" id="myInt">
          
        </div>
        
        <div class="saveInterest">
          <button type="button" class="button" id="btnSubmit" disabled>선택하기</button>
         
         <a href="${contextPath}/login"> <button type="button" class="buttonCancle">넘어가기</button></a>
          
        </div>
      </div>
    </div>
    
 	</form>
 	<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>