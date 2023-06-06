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
<meta charset="UTF-8">
<title>헤더</title>
</head>
<body>
	<!-- 배경색 조정 -->
        <div class="menu_bg"></div>
        <!-- header 시작 -->
        <header>
            <h1 class="logo">
                <a href="${contextPath}/index"><img src="${contextPath}/images/logo.png" alt="logo" /></a>
            </h1>
        </header>
        <button class="burger">
            <span></span>
        </button>
        <div class="sidemenu">
            <ul class="main_menu">
            	<c:choose>
            		<c:when test="${user_id != null}">
	            		<li>
	                    	<a href="#">
	                       	 <div class="profile"><i class="fa-solid fa-circle-user"></i><span class="profile_name">${nickname}</span></div>
	                    	</a>
	               		 </li>
	              		 <li><a href="">소모임 만들기</a></li>
	               		 <li><a href="">소모임 검색</a></li>
	               		 <li><a href="">프로필</a></li>
	               		 <li><a href="">내 일정</a></li>
	               		 <li><a href="">내 소모임</a></li>
	                	 <li><a href="">고객센터</a></li>
	                	 <li><a href="">로그아웃</a></li>
            		</c:when>
            		<c:otherwise>
            			<li></li>
            			<li><a href="${contextPath}/search">소모임 검색</a></li>
            			<li><a href="${contextPath}/login">로그인</a></li>
            			<li><a href="${contextPath}/join">회원가입</a></li>
            		</c:otherwise>
            	</c:choose>
            </ul>
            <div class="sidemenu_footer">
                <h3>Contact details</h3>
                <p>글 넣을 거 있으면 넣기</p>
            </div>
        </div>
        <!-- header 종료 -->
</body>
</html>