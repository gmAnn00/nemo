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
<title>잘못된 주소입니다.</title>
<link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
<link rel="stylesheet" href="${contextPath}/css/normalize.css" />
<link rel="stylesheet" href="${contextPath}/css/common.css" />
<script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
<script src="https://kit.fontawesome.com/f9a2702e84.js" crossorigin="anonymous"></script>

<style type="text/css">
	a:hover{
		color: var(--main-color);
	}
</style>

</head>
<body>
	<div style="text-align:center; padding-top: 100px">
		<h2>페이지를 찾을 수 없습니다.</h2>
		<h2>잘못된 주소입니다.</h2>
		<br/>
		<a href="${contextPath}/index">메인 페이지로 돌아가기</a>
	</div>
</body>
</html>