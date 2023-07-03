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
<title>네모: 비밀번호찾기</title>
<link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
        <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
        <link rel="stylesheet" href="${contextPath}/css/common.css" />
        <link rel="stylesheet" href="${contextPath}/css/findIdPass.css" />
        <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
        <script src="https://kit.fontawesome.com/f9a2702e84.js" crossorigin="anonymous"></script>
        <script src="${contextPath}/js/header.js"></script>
</head>
<body>

<jsp:include page="../header.jsp" flush="true"></jsp:include>

        <!-- 아이디/비밀번호 찾기 영역 -->
        <div class="login_container">
            <ul class="tabs">
                <li class="tabId" data-tab="idTab">
                    <a href="${contextPath}/search/findidForm">아이디 찾기</a>
                </li>
                <li class="tabPass" data-tab="passTab">
                    <a href="${contextPath}/search/findpassForm">비밀번호 찾기</a>
                </li>
            </ul>

            <div id="passTab" class="tabContent current">
                <h2>비밀번호 재설정</h2>
                <p>비밀번호를 입력하시면 재설정 하실 수 있습니다.</p>
                <form action="${contextPath}/search/findPassReset"  method="post">
                    <label for="password"></label>
                    <input type="password" id="password" name="password" placeholder="비밀번호 입력(문자, 숫자, 특수문자포함 ?~??자)" required />
                    <label for="passwordCheck"></label>
                    <input type="password" id="passwordCheck" placeholder="비밀번호 확인" required />
                    <button type="submit" class="button">비밀번호 재설정 하기</button>
                </form>
            </div>
        </div>
        <!-- 아이디/비밀번호 찾기 영역 종료 -->
        
        <jsp:include page="../footer.jsp" flush="true"></jsp:include>
        
</body>
</html>