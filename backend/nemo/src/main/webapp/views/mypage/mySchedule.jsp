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
<title>내 일정</title>
<link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
<link rel="stylesheet" href="${contextPath}/css/normalize.css" />
<link rel="stylesheet" href="${contextPath}/css/common.css" />
<link rel="stylesheet" href="${contextPath}/css/submenu.css" />
<link rel="stylesheet" href="${contextPath}/css/mySchedule.css" />

<script
  src="https://kit.fontawesome.com/bc604c01cc.js"
  crossorigin="anonymous"
></script>
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
                <a href="${contextPath}/mypage/myschedule">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name submenu_select"><span>내 일정</span></div>
                    <i class="fa-solid fa-minus submenu_select"></i>
                  </div>
                </a>
              </li>
              <li>
                <a href="${contextPath}/mypage/mygrouplist">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name"><span>내 소모임</span></div>
                    <i class="fa-solid fa-angle-right menu_angle"></i>
                  </div>
                </a>
              </li>
              <li>
                <a href="${contextPath}/mypage/myboardlist">
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
                  <div class="mySchedule">
                    <p class="myScheduleDate">2023년 4월 25일</p>
                    <div class="myScheduleImgContent">
                      <div class="groupImg"></div>
                      <!-- <img src="images/books.jpg" alt="소모임 사진" /> -->
                      <div class="myScheduleContent">
                        <p class="myScheduleGroupName">이젠 종로 학원</p>
                        <p class="contents">
                          와이어프레임 짜기 | 오후 3시 30분 | 서울시 종로구
                        </p>
                      </div>
                    </div>
                  </div>
                  <div class="mySchedule">
                    <p class="myScheduleDate">2023년 4월 27일</p>
                    <div class="myScheduleImgContent">
                      <!-- <img src="images/salmon.jpg" alt="소모임 사진" /> -->
                      <div class="groupImg"></div>
                      <div class="myScheduleContent">
                        <p class="myScheduleGroupName">맛집 탐방 모임</p>
                        <p class="contents">
                          인스타 맛집 탐방 | 오후 1시 30분 | 서울시 강남구
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
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