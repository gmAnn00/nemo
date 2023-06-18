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
<title>아이디 찾기 결과창</title>
<link rel="shortcut icon" href="/images/favicon.png" />
        <link rel="stylesheet" href="/css/normalize.css" />
        <link rel="stylesheet" href="/css/common.css" />
        <link rel="stylesheet" href="/css/findId.css" />
        <link rel="stylesheet" href="/css/footerheight.css">
        <script src="https://kit.fontawesome.com/3d4603cd1d.js" crossorigin="anonymous"></script>
        <script src="/js/jquery-3.6.4.min.js"></script>
        <script src="/js/header.js"></script>
</head>
<body>

        <!-- 아이디/비밀번호 찾기 영역 -->
        <div class="login_container">
            <ul class="tabs">
                <li class="tabId" data-tab="idTab">
                    <a href="findId.jsp">아이디 찾기</a>
                </li>
                <li class="tabPass" data-tab="passTab">
                    <a href="findPass.jsp">비밀번호 재설정</a>
                </li>
            </ul>

            <div id="idTab" class="tabContent current" >
                <p>회원가입시 사용하신 아이디는 ???? 입니다.</p>
                <button type="button" class="button">
                    <a href="login.jsp">로그인 화면으로 돌아가기</a>
                </button>
            </div>
            <div id="passTab" class="tabContent">
                <h2>비밀번호 재설정</h2>
                <p>비밀번호는 아이디, 이름, 이메일을 입력하시면 재설정 하실 수 있습니다.</p>
                <form action="${contextPath}/views/findPassReset.jsp" method="post">
                    <label for="idInput"></label>
                    <input type="text" id="idInput" placeholder="아이디를 입력해주세요" required />
                    <label for="nameInput"></label>
                    <input type="text" id="nameInput" placeholder="이름을 입력해주세요" required />
                    <label for="emailInput"></label>
                    <input type="text" id="emailInput" placeholder="이메일을 입력해주세요" required />
                    <button type="submit" class="button">비밀번호 재설정 하기</button>
                </form>
            </div>
        </div>
        
</body>
</html>