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
<title>관심자창</title>
<link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
    <link rel="stylesheet" href="${contextPath}/css/common.css" />
    <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
    <link rel="stylesheet" href="${contextPath}/css/interest.css" />
    <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
    <script
      src="https://kit.fontawesome.com/3d4603cd1d.js"
      crossorigin="anonymous"
    ></script>
    <script src="${contextPath}/js/header.js"></script>
</head>
<body>

<form action="${contextPath}/join/interest" method="post">

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
          <button type="button" class="1 btnInterest">
            문화 · 공연 · 축제
          </button>
          <button type="button" class="2 btnInterest">음악 · 악기</button>
          <button type="button" class="3 btnInterest">사진 · 영상</button>
          <button type="button" class="4 btnInterest">아웃도어 · 여행</button>
          <button type="button" class="5 btnInterest">운동 · 스포츠</button>
          <button type="button" class="6 btnInterest">인문학 · 책 · 글</button>
          <button type="button" class="7 btnInterest">업종 · 직무</button>
          <button type="button" class="8 btnInterest">외국 · 언어</button>
          <button type="button" class="9 btnInterest">공예 · 만들기</button>
          <button type="button" class="10 btnInterest">댄스 · 무용</button>
          <button type="button" class="11 btnInterest">봉사활동</button>
          <button type="button" class="12 btnInterest">사교 · 인맥</button>
          <button type="button" class="13 btnInterest">차 · 오토바이</button>
          <button type="button" class="14 btnInterest">스포츠 관람</button>
          <button type="button" class="15 btnInterest">게임 · 오락</button>
          <button type="button" class="16 btnInterest">요리 · 제조</button>
          <button type="button" class="17 btnInterest">반려동물</button>
        </div>
        <div class="smallCategory">
          <!--소분류-->
          <h3>소분류</h3>
          <button type="button" class="1-1 btnInterest">
            문화 · 공연 · 축제
          </button>
          <button type="button" class="1-2 btnInterest">공연 · 연극</button>
          <button type="button" class="1-3 btnInterest">영화</button>
          <button type="button" class="1-4 btnInterest">전시회</button>
          <button type="button" class="1-5 btnInterest">연기 · 공연제작</button>
          <button type="button" class="1-6 btnInterest">문화재 탐방</button>
          <button type="button" class="1-7 btnInterest">파티 · 페스티벌</button>
        </div>
        <div class="myInterest">
          <!--내 관심사-->
          <h3>내 관심사<span>(최대 3개 선택가능)</span></h3>
          <button type="button" class="btnInterest">
            뮤지컬 · 오페라<span>X</span>
          </button>
          <button type="button" class="btnInterest">영화<span>X</span></button>
          <button type="button" class="btnInterest">
            전시회<span>X</span>
          </button>
        </div>
        <div class="saveInterest">
          <button type="submit" class="button">선택하기</button>
          <button type="button" class="buttonCancle">넘어가기</button>
        </div>
      </div>
    </div>
    
    </form>
</body>
</html>