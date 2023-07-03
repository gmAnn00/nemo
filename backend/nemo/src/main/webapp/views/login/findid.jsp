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
<title>네모: 아이디찾기</title>
<!-- <title>아이디/비밀번호 찾기</title> -->
        <link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
        <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
        <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
        <link rel="stylesheet" href="${contextPath}/css/common.css" />
        <link rel="stylesheet" href="${contextPath}/css/findIdPass.css" />
        <script src="https://kit.fontawesome.com/f9a2702e84.js" crossorigin="anonymous"></script>
        <script src="${contextPath}/js/header.js"></script>
</head>
<body>

<jsp:include page="../header.jsp" flush="true"></jsp:include>

        <div class="login_container">
            <ul class="tabs">
                <li class="tabId" data-tab="idTab">
                    <a href="${contextPath}/search/findidForm">아이디 찾기</a>
                </li>
                <li class="tabPass" data-tab="passTab">
                    <a href="${contextPath}/search/findpassForm">비밀번호 찾기</a>
                </li>
            </ul>

            <div id="idTab" class="tabContent current">
                <h2>아이디 찾기</h2>
                <p>아이디는 가입시 입력하신 이메일로 찾으실 수 있습니다.</p>
                <form action="${contextPath}/search/findid" method="post">
                    <label for="nameInput"></label>
                    <input type="text" id="nameInput" name="user_name" placeholder="이름을 입력해주세요" required />
                    <label for="emailInput"></label>
                    <input type="text" id="emailInput" name="email" placeholder="이메일을 입력해주세요" required />
                    <button type="submit" class="button">아이디 찾기</button>
                </form>
            </div>
        </div>
        
        <jsp:include page="../footer.jsp" flush="true"></jsp:include>
        
</body>
</html>