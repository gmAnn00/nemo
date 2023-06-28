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
<title>500에러</title>
</head>
<body>
	<h2>관리자에게 문의하세요.<br>(500에러)</h2>
	<img src="${contextPath}/images/error500.jpg" alt="500error">
</body>
</html>