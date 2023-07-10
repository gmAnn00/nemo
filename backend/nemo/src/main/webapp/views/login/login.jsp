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
<title>네모: 로그인</title>
	<script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
    <script src="${contextPath}/js/header.js"></script>
    <script src="${contextPath}/js/loginCook.js"></script>
    <link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
    <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
    <link rel="stylesheet" href="${contextPath}/css/common.css" />
    <link rel="stylesheet" href="${contextPath}/css/login.css" />
    <script src="https://kit.fontawesome.com/f9a2702e84.js" crossorigin="anonymous"></script>
    
</head>
<body>

<jsp:include page="../header.jsp" flush="true"></jsp:include>
	
    <!-- 로그인 영역 -->
    <div id="contentsArea">
      <section class="imageArea box">
        <!-- <a href="index.jsp">
          <img src="/images/logo.png" alt="Logo" />
        </a> -->
        <p class="msg">로그인하여 다양한</p>
        <p class="msg">사람들과 만나보세요</p>
        <!-- <p class="url">
                    <a href="index.jsp"><span>www.nemo.com</span></a>
                </p> -->
      </section>
      <section class="loginArea box">
        <h2>로그인</h2>
        <form method="post" action="${contextPath}/login/logintry" id="loginForm">
          <input
            type="text"
            name="user_id"
            id="userName"
            placeholder="아이디"
            onfocus="this.placeholder=''"
            onblur="this.placeholder='아이디'"
            required
          />
          <input
            type="password"
            name="password"
            id="userPassword"
            placeholder="비밀번호"
            onfocus="this.placeholder=''"
            onblur="this.placeholder='비밀번호'"
            required
          />
          <input type="submit" value="로그인" />
          <label for="saveId">
            <input type="checkbox" id="saveId" name="checkId" class="rememberCheck" />아이디 저장
          </label>
          <!-- <label for="keepLogin">
            <input type="checkbox" id="keepLogin" name="keepLogin" class="keepLogin" />로그인 상태 유지
          </label>  -->
          <p class="joinFind">
            <a href="${contextPath}/join/agreeForm" class="join">회원가입</a>
            <a href="${contextPath}/search/findidForm"class="find">아이디/비밀번호 찾기</a>
          </p>
          <!-- 
          <input
            type="image"
            src="${contextPath}/images/login_naver.png"
            alt="네이버 간편 로그인"
            class="naverLog"
          />
          <input
            type="image"
            src="${contextPath}/images/login_kakao.png"
            alt="카카오 아이디로 로그인"
            class="kakaoLog"
          />
          <input
            type="image"
            src="${contextPath}/images/login_google.png"
            alt="구글 아이디로 로그인"
            class="googleLog"
          />
           -->
        </form>
      </section>
    </div>
    
	
	<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>