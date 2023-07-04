<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    import="java.util.*, nemo.*"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<ul class="sc2_menu_list">
  <li>
    <a href="${contextPath}/adminUser">
      <div class="sc2_icon_menu">
        <div class="menu_submenu_name <c:if test="${menuSelected eq 1}">submenu_select</c:if>">
          <span>회원 관리</span>
        </div>
        <c:choose>
			<c:when test="${menuSelected eq 1}"><i class="fa-solid fa-minus submenu_select"></i></c:when>
			<c:otherwise><i class="fa-solid fa-angle-right menu_angle"></i></c:otherwise>
		</c:choose>
      </div>
    </a>
  </li>
  <li>
    <a href="${contextPath}/adminGroup">
      <div class="sc2_icon_menu">
        <div class="menu_submenu_name <c:if test="${menuSelected eq 0}">submenu_select</c:if>">
        	<span>소모임 관리</span></div>
        <c:choose>
			<c:when test="${menuSelected eq 0}"><i class="fa-solid fa-minus submenu_select"></i></c:when>
			<c:otherwise><i class="fa-solid fa-angle-right menu_angle"></i></c:otherwise>
		</c:choose>
      </div>
    </a>
  </li>  
  
    <li>
    <a href="${contextPath}/adminReport">
      <div class="sc2_icon_menu">
        <div class="menu_submenu_name <c:if test="${menuSelected eq 2}">submenu_select</c:if>">
        	<span>신고관리</span></div>
        <c:choose>
			<c:when test="${menuSelected eq 2}"><i class="fa-solid fa-minus submenu_select"></i></c:when>
			<c:otherwise><i class="fa-solid fa-angle-right menu_angle"></i></c:otherwise>
		</c:choose>
      </div>
    </a>
  </li>
  
  <li>
    <a href="${contextPath}/viewQna">
      <div class="sc2_icon_menu">
        <div class="menu_submenu_name <c:if test="${menuSelected eq 3}">submenu_select</c:if>">
        	<span>고객센터 Q&A</span></div>
        <c:choose>
			<c:when test="${menuSelected eq 3}"><i class="fa-solid fa-minus submenu_select"></i></c:when>
			<c:otherwise><i class="fa-solid fa-angle-right menu_angle"></i></c:otherwise>
		</c:choose>
      </div>
    </a>
  </li>

</ul>