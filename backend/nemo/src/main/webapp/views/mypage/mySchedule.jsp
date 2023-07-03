<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd hh:mm" var="today" />

<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네모: 마이페이지</title>
<link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
<link rel="stylesheet" href="${contextPath}/css/normalize.css" />
<link rel="stylesheet" href="${contextPath}/css/common.css" />
<link rel="stylesheet" href="${contextPath}/css/submenu.css" />
<link rel="stylesheet" href="${contextPath}/css/mySchedule.css" />

<script src="https://kit.fontawesome.com/bc604c01cc.js"
  crossorigin="anonymous"></script>
<script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/mySchedule.js"></script>

</head>
<body>
	<jsp:include page="../header.jsp" flush="true"></jsp:include>
	
    <!-- 콘텐츠 영역 시작 -->
    <div class="section2">
      <div class="sc2_contents">
        <!-- 메뉴바 시작 -->
        <div class="sc2_menu_contents">
          <div class="sc2_menu">
            <h2 class="sc2_menu_title">내 일정</h2>
            <ul class="sc2_menu_list">
              <li>
                <a href="${contextPath}/mypage">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name"><span>프로필</span></div>
                    <i class="fa-solid fa-angle-right menu_angle"></i>
                  </div>
                </a>
              </li>
              <li>
                <a href="${contextPath}/mypage/mySchedule">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name submenu_select"><span>내 일정</span></div>
                    <i class="fa-solid fa-minus submenu_select"></i>
                  </div>
                </a>
              </li>
              <li>
                <a href="${contextPath}/mypage/myGroupList">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name"><span>내 소모임</span></div>
                    <i class="fa-solid fa-angle-right menu_angle"></i>
                  </div>
                </a>
              </li>
              <li>
                <a href="${contextPath}/mypage/myBoardList">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name">
                      <span>내가 쓴 글·댓글</span>
                    </div>
                    <i class="fa-solid fa-angle-right menu_angle"></i>
                  </div>
                </a>
              </li>
            </ul>
          </div>
        </div>
        <!-- 메뉴바 종료 -->

        <div class="sc2_subsection">
          <div class="sc2_subsection_title">
            <h2 class="sc2_subsection_title_name">내 일정</h2>
            <!-- nav 바 시작 -->
            <div class="nav_bar">
              <a href="${contextPath}/index">
                <i class="fa-solid fa-house nav_icon"></i>
              </a>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>마이페이지</span>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>내 일정</span>
            </div>
            <!-- nav 바 종료 -->
          </div>

          <div class="sc2_subcontents">
            <div class="sc2_subcontent">
              
              <div class="myScheduleCalAndList">
                <div id="myScheduleCalendarArea"></div>
                <div class="myScheduleListArea">
                  <h3>다가오는 일정</h3>
                
                  <c:choose>
                    <c:when test="${empty commingScheduleList}">
                      <p>등록된 일정이 없습니다.</p>
                    </c:when>
                    <c:when test="${!empty commingScheduleList}">                                       
                      <c:forEach var="comMySchedule" items="${commingScheduleList}">
                      <fmt:formatDate value="${comMySchedule.date}" pattern="yyyy-MM-dd hh:mm" var="date"/>
                      <c:if test = "${today<=date}" >
                                            
	                  <div class="mySchedule">
	                  	<a href="${contextPath}/group/schedule?group_id=${comMySchedule.scheduleVO.grp_id}">
	                    <p class="myScheduleDate">${comMySchedule.scheduleDate}<span> ${comMySchedule.scheduleTime}</span></p>
	                    <div class="myScheduleImgContent">
	                      <div class="groupImg">
	                        <img src="${contextPath}/groupImages/${comMySchedule.scheduleVO.grp_id}/${comMySchedule.grp_img}" alt="소모임 사진" />
	                      </div>	                      
	                      <div class="myScheduleContent">
	                        <p class="myScheduleGroupName">${comMySchedule.grp_name}</p>
	                        <p class="contents">${comMySchedule.scheduleVO.sche_title}</p>
	                        <p class="contents"><i class="fa-solid fa-location-dot"></i>${comMySchedule.scheduleVO.location}</p>
	                      </div>
	                    </div>
	                    </a>
	                  </div>    
	                                                 
                  	  </c:if>    	 
                  	  </c:forEach>
                    </c:when>
                  </c:choose>
                  
                </div>
                
              </div>
              
              <div class="thisMonthMyScheduleList">
              <%-- 
               <h3>이 달의 일정</h3>
               
                 <c:choose>
                    <c:when test="${empty scheduleList}">
                      <p>등록된 일정이 없습니다.</p>
                    </c:when>
                    <c:when test="${!empty scheduleList}">                                       
                      <c:forEach var="mySchedule" items="${scheduleList}">                      
                                            
	                  <div class="mySchedule">
	                  	<a href="${contextPath}/group/schedule?group_id=${comMySchedule.grp_id}">
	                    <p class="myScheduleDate">${mySchedule.scheduleDate}<span> ${mySchedule.scheduleTime}</span></p>
	                    <div class="myScheduleImgContent">
	                      <div class="groupImg">
	                        <img src="${contextPath}/groupImages/${mySchedule.scheduleVO.grp_id}/${mySchedule.grp_img}" alt="소모임 사진" />
	                      </div>	                      
	                      <div class="myScheduleContent">
	                        <p class="myScheduleGroupName">${mySchedule.grp_name}</p>
	                        <p class="contents">${mySchedule.scheduleVO.sche_title}</p>
	                        <p class="contents"><i class="fa-solid fa-location-dot"></i>${mySchedule.scheduleVO.location}</p>
	                      </div>
	                    </div>
	                    </a>
	                  </div>    
	                                                 
                  	  </c:forEach>
                    </c:when>
                  </c:choose>--%>
              </div>
              
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 콘텐츠 영역 종료-->	
	
	<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>