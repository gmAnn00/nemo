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
<link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
        <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
        <link rel="stylesheet" href="${contextPath}/css/common.css" />
        <link rel="stylesheet" href="${contextPath}/css/findId.css" />
        <link rel="stylesheet" href="${contextPath}/css/footerheight.css">
        <script src="https://kit.fontawesome.com/f9a2702e84.js" crossorigin="anonymous"></script>
        <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
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
            
              <div id="idTab" class="tabContent current" >
	                	<p>회원가입시 사용하신 아이디는 ${user_id_find} 입니다.</p>
	                	
	                <button type="button" class="button">
	                    <a href="${contextPath}/login/loginForm">로그인 화면으로 돌아가기</a>
	                </button>
	                
	            	</div>
           
            <div id="passTab" class="tabContent">
                <h2>비밀번호 재설정</h2>
                <p>비밀번호를 입력하시면 재설정 하실 수 있습니다.</p>
                <form action="${contextPath}/find/findPassResetForm" method="post">
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
        
        <jsp:include page="../footer.jsp" flush="true"></jsp:include>
        
</body>
</html>