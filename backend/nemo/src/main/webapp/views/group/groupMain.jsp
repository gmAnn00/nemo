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
<title>네모: 모임 메인페이지</title>
<link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
<link rel="stylesheet" href="${contextPath}/css/normalize.css" />
<link rel="stylesheet" href="${contextPath}/css/common.css" />
<link rel="stylesheet" href="${contextPath}/css/submenu.css" />
<link rel="stylesheet" href="${contextPath}/css/sectionTitle.css" />
<link rel="stylesheet" href="${contextPath}/css/groupMain2.css" />
<link rel="stylesheet" href="${contextPath}/css/jquery-ui.min.css" />
<script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/js/header.js"></script>
<script src="https://kit.fontawesome.com/f9a2702e84.js" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="../header.jsp" flush="true"></jsp:include>
	
	<!-- section1 시작 -->
        <div class="section1">
            <div class="group_containter">
                <div class="group_all">
                    <div class="group_img">
                        <img src="${contextPath}/images/free-icon-group-8847475.png" alt="group_img" />
                    </div>
                    <div class="group_name">
                        <a href="#">
                            <span>이젠종로학원</span>
                        </a>
                    </div>
                    <div class="group_info">
                        <div class="group_info_category">
                            <div class="category_box group_info_category_box">sports</div>
                            <div class="category_box group_info_category_box">game</div>
                        </div>
                        <div class="group_info_member">
                            <div class="group_info_title"><span>멤버수</span></div>
                            <div class="group_info_contents"><span>7</span></div>
                        </div>
                        <div class="group_info_follower">
                            <div class="group_info_title"><span>개설일</span></div>
                            <div class="group_info_contents"><span>2023. 05. 19.</span></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- section1 종료 -->
	
	<!-- main content 시작-->
        <div class="section2">
            <div class="sc2_contents">
                <!-- 메뉴바 시작 -->
                <div class="sc2_menu_contents">
                    <div class="sc2_menu">
                        <h2 class="sc2_menu_title">나의 모임</h2>
                        <ul class="sc2_menu_list">
                            <li>
                                <a href="schedule.html">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name"><span>일정</span></div>
                                        <i class="fa-solid fa-angle-right"></i>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="board.html">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name"><span>게시판</span></div>
                                        <i class="fa-solid fa-angle-right"></i>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="myPageGroup.html">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name"><span>멤버</span></div>
                                        <i class="fa-solid fa-angle-right"></i>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="groupSetting.html">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name"><span>소모임관리</span></div>
                                        <i class="fa-solid fa-angle-right"></i>
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <!-- 메뉴바 종료 -->

                <div class="sc2_subsection">
                    <div class="sc2_subsection_title">
                        <h2 class="sc2_subsection_title_name"></h2>
                        <!-- nav 바 시작 -->
                        <div class="nav_bar">
                            <a href="${contextPath}/index.html">
                                <i class="fa-solid fa-house"></i>
                            </a>
                            <i class="fa-solid fa-angle-right nav_icon"></i>
                            <span>이젠종로학원</span>
                        </div>
                        <!-- nav 바 종료 -->
                    </div>
                    <!-- 최근 일정 영역 시작-->
                    <div class="recentSchedule recentDiv sc2_subsection">
                        <div class="mainTitle">
                            <h3>최근 일정</h3>
                            <a href="schedule.html"
                                ><span class="more"><i class="fa-solid fa-plus"></i>더보기</span></a
                            >
                        </div>
                        <table class="recentScheduledTbl recentTbl">
                            <tr>
                                <th>모임날짜</th>
                                <th>모임제목</th>
                                <th>모임장소</th>
                            </tr>
                            <tr>
                                <td>2023-05-08</td>
                                <td>
                                    <a href=""><p>세번째모임</p></a>
                                </td>
                                <td>홍대</td>
                            </tr>
                            <tr>
                                <td>2020-05-02</td>
                                <td>
                                    <a href=""><p>두번째모임</p></a>
                                </td>
                                <td>종각</td>
                            </tr>
                            <tr>
                                <td>2023-04-29</td>
                                <td>
                                    <a href=""><p>첫번째모임</p></a>
                                </td>
                                <td>종각</td>
                            </tr>
                        </table>
                    </div>

                    <!-- 최신 글 영역 시작-->
                    <div class="recentBoard recentDiv sc2_subsection">
                        <div class="mainTitle">
                            <h3>최신 글</h3>
                            <a href="board.html"
                                ><span class="more"><i class="fa-solid fa-plus"></i>더보기</span></a
                            >
                        </div>
                        <table class="recentBordTbl recentTbl">
                            <tr>
                                <th>글제목</th>
                                <th>작성자</th>
                                <th>날짜</th>
                            </tr>
                            <tr>
                                <td>
                                    <a href="#"
                                        ><p>
                                            안녕하세요 반갑습니다.안녕하세요 반갑습니다.안녕하세요 반갑습니다.안녕하세요 반갑습니다안녕하세요
                                            반갑습니다
                                        </p></a
                                    >
                                </td>
                                <td>김네모</td>
                                <td>2023-05-07</td>
                            </tr>
                            <tr>
                                <td>
                                    <a href="#"><p>안녕하세요 반갑습니다.</p></a>
                                </td>
                                <td>이네모</td>
                                <td>2023-05-06</td>
                            </tr>
                            <tr>
                                <td>
                                    <a href="#"><p>안녕하세요 반갑습니다.</p></a>
                                </td>
                                <td>박네모</td>
                                <td>2023-05-05</td>
                            </tr>
                        </table>
                    </div>

                    <!-- 멤버 영역 시작-->
                    <div class="memberArea sc2_subsection">
                        <div class="mainTitle">
                            <h3 class="nemoMemTitle">네모멤버</h3>
                            <span>(<span class="currentNum">0</span>/<span class="maxNum">50</span>)</span>
                        </div>
                        <span class="btnEventPrev" title="이전보기"><i class="fa-solid fa-chevron fa-chevron-left"></i></span>
                        <div class="animationSlide">
                            <div class="sliderPanel">
                                <!-- <p><center>이미지슬라이드영역~</center></p> -->
                            </div>
                            <!-- slide panel 종료-->
                        </div>
                        <!--animation-slide종료-->
                        <span class="btnEventNext" title="다음보기"><i class="fa-solid fa-chevron fa-chevron-right"></i></span>
                    </div>
                </div>
            </div>

            <!-- main content 종료-->
        </div>
	
	<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>