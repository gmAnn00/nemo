<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>네모 고객센터</title>
        <link rel="shortcut icon" href="../../images/favicon.png" />
        <link rel="stylesheet" href="../../css/normalize.css" />
        <link rel="stylesheet" href="../../css/common.css" />
        <link rel="stylesheet" href="../../css/help.css" />
        <script src="../../js/jquery-3.6.4.min.js"></script>
        <script src="https://kit.fontawesome.com/3d4603cd1d.js" crossorigin="anonymous"></script>
        <script src="../../js/header.js"></script>
    </head>
<body>
	<jsp:include page="../../header.jsp" flush="true"></jsp:include>
	
	<!-- 콘텐츠 영역 시작 -->
        <div id="contentsArea">
            <h2 class="helpTitle">고객센터 1:1 문의</h2>
            <div class="chattingArea">
                <div class="MayIHelpYouArea">
                    <img src="../../images/logo_only.png" alt="무엇을 도와드릴까요 프로필 이미지" />
                    <p>무엇을 도와드릴까요?</p>
                </div>

                <div class="chattingMessageArea">
                    <div class="userMessageArea">
                        <p class="userMessage">회원가입은 어떻게 하나요?</p>
                        <img src="../../images/temp.png" alt="유저 프로필" />
                    </div>
                    <div class="helpMessageArea">
                        <img src="../../images/logo_only.png" alt="고객센터 프로필" />
                        <p class="helpMessage">회원가입 하는 방법을 알려드리겠습니다.</p>
                    </div>
                </div>
                <div class="sendMessageArea">
                    <input type="text" id="messageInput" placeholder="문의사항을 입력해 주세요" />
                    <button type="button" id="sendMessage" class="sendMessage">
                        <!-- <img src="images/temp.png" alt="전송사진" /> -->
                    </button>
                    <input type="hidden" id="idSend" class="idSend" />
                </div>
            </div>
        </div>
        <!-- 콘텐츠 영역 종료-->
	
	
	
	<jsp:include page="../../footer.jsp" flush="true"></jsp:include>
</body>
</html>