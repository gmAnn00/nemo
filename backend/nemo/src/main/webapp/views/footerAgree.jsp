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
<title>네모: 이용약관</title>
	<link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
    <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
    <script src="${contextPath}/js/jquery-ui.min.js"></script>
    <script src="${contextPath}/js/agree.js"></script>
    <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
    <link rel="stylesheet" href="${contextPath}/css/common.css" />
    <link rel="stylesheet" href="${contextPath}/css/footerAgree.css" />
	<script src="https://kit.fontawesome.com/f9a2702e84.js" crossorigin="anonymous"></script>
    <script src="${contextPath}/js/header.js"></script>
</head>
<body>

<jsp:include page="./header.jsp" flush="true"></jsp:include>

    <div id="contentsArea">
      <!-- 이용 약관 동의 영역 -->
      <form class="terms" action="${contextPath}/join/joinForm" method="post">
        <h2>이용 약관</h2>

        <h3>서비스 이용 약관</h3>
        <div class="termsContent">
          <p>제1조 (목적)</p>
          <br />
          <p>
            본 약관은 네모 주식회사(이하 "회사"라 합니다)가 제공하는 서비스(이하
            "서비스"라 합니다)의 이용조건 및 절차 등을 정함을 목적으로 합니다.
          </p>
          <br />
          <p>제2조 (이용계약의 체결 및 해지)</p>
          <br />
          <p>
            회원으로 가입하여 서비스를 이용하고자 하는 자는 회사가 정한 가입
            양식에 따라 회원정보를 기입한 후 이용신청을 하고, 회사는 이를
            승낙함으로써 이용계약이 체결됩니다.
          </p>
          <br />
          <p>제3조 (서비스의 내용)</p>
          <br />
          <p>회사는 회원에게 아래와 같은 서비스를 제공합니다.</p>
          <br />
          <p>제4조 (서비스 이용료)</p>
          <br />
          <p>서비스 이용에 대한 이용료는 별도로 정하지 않습니다.</p>
          <br />
          <p>제5조 (개인정보보호)</p>
          <br />
          <p>
            회사는 서비스 제공을 위해 회원으로부터 제공받은 개인정보를 보호하기
            위해 최선을 다합니다.
          </p>
        </div>
		<p></p>
		<p></p>
        <h3>개인정보 수집</h3>
        <div class="termsContent">
          <p>개인정보 수집 동의 및 수집 목적, 보관 기간, 파기절차</p>
        </div>
        <p></p>
        <p></p>
        <h3>위치 기반 서비스 이용 약관</h3>
        <div class="termsContent">
          <p>위치 기반 서비스 목적과 수집 정보, 서비스내용, 처리방침</p>
        </div>

      </form>
    </div>
    
	<jsp:include page="./footer.jsp" flush="true"></jsp:include>
	
</body>
</html>