<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="isMng" value="false" />
<c:forEach var="elem" items="${grpMngList}" >
	<c:if test="${elem eq param.group_id}">
		<c:set var="isMng" value="true" />
	</c:if>
</c:forEach>

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
<script src="${contextPath}/js/groupMain.js"></script>
<script src="https://kit.fontawesome.com/f9a2702e84.js" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="../header.jsp" flush="true"></jsp:include>
	
	<!-- section1 시작 -->
        <div class="section1">
            <div class="group_containter">
                <div class="group_all">
                    <div class="group_img">
                    <img src="${contextPath}/groupImageDownload?group_id=${param.group_id}&group_img=${groupVO.grp_img}" alt="group_img" />
                    </div>
                    <div class="group_name">
                        <a href="#">
                            <span>${groupVO.grp_name}</span>
                        </a>
                    </div>
                    <div class="group_info">
                        <div class="group_info_category">
                            <div class="category_box group_info_category_box">${groupVO.main_name}</div>
                            <div class="category_box group_info_category_box">${groupVO.sub_name}</div>
                        </div>
                        <div class="group_info_member">
                            <div class="group_info_title"><span>멤버수</span></div>
                            <div class="group_info_contents"><span>${groupNum}</span></div>
                        </div>
                        <div class="group_info_follower">
                            <div class="group_info_title"><span>개설일</span></div>
                            <div class="group_info_contents"><span>${groupVO.create_date}</span></div>
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
                                <a href="${contextPath}/group/schedule?group_id=${param.group_id}">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name"><span>일정</span></div>
                                        <i class="fa-solid fa-angle-right"></i>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="${contextPath}/group/board?group_id=${param.group_id}">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name"><span>게시판</span></div>
                                        <i class="fa-solid fa-angle-right"></i>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="${contextPath}/group/member?group_id=${param.group_id}">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name"><span>멤버</span></div>
                                        <i class="fa-solid fa-angle-right"></i>
                                    </div>
                                </a>
                            </li>
                            <c:if test="${isMng == true}">
	                            <li>
	                                <a href="${contextPath}/group/groupSetting?group_id=${param.group_id}">
	                                    <div class="sc2_icon_menu">
	                                        <div class="menu_submenu_name"><span>소모임관리</span></div>
	                                        <i class="fa-solid fa-angle-right"></i>
	                                    </div>
	                                </a>
	                            </li>
                            </c:if>
                            
                        </ul>
                    </div>
                </div>
                <!-- 메뉴바 종료 -->

                <div class="sc2_subsection">
                    <div class="sc2_subsection_title">
                        <h2 class="sc2_subsection_title_name"></h2>
                        <!-- nav 바 시작 -->
                        <div class="nav_bar">
                            <a href="${contextPath}/index">
                                <i class="fa-solid fa-house"></i>
                            </a>
                            <i class="fa-solid fa-angle-right nav_icon"></i>
                            <span>${groupVO.grp_name}</span>
                        </div>
                        <!-- nav 바 종료 -->
                    </div>
                    <!-- 최근 일정 영역 시작-->
                    <div class="recentSchedule recentDiv sc2_subsection">
                        <div class="mainTitle">
                            <h3>최근 일정</h3>
                            <a href="${contextPath}/group/schedule?group_id=${param.group_id}"
                                ><span class="more"><i class="fa-solid fa-plus"></i>더보기</span></a
                            >
                        </div>
                        <table class="recentScheduledTbl recentTbl">
                            <tr>
                                <th>모임날짜</th>
                                <th>모임제목</th>
                                <th>모임장소</th>
                            </tr>
                            <c:choose>
                        		<c:when test="${schdulesList.isEmpty()}">
                        			<tr>
                        				<td colspan="3" align="center">등록된 일정이 없습니다.</td>
                        			</tr>
                        		</c:when>
                        		<c:when test="${!schdulesList.isEmpty()}">
                        			<c:forEach var="schedule" items="${schdulesList}">
                        				<tr>
                        					<td>${schedule.schedule}</td>
                        					<td>${schedule.sche_title}</td>
                        					<td>${schedule.location}</td>
                        				</tr>
                        			</c:forEach>
                        		</c:when>
                        	</c:choose>
                        </table>
                    </div>

                    <!-- 최신 글 영역 시작-->
                    <div class="recentBoard recentDiv sc2_subsection">
                        <div class="mainTitle">
                            <h3>최신 글</h3>
                            <a href="${contextPath}/group/board?group_id=${param.group_id}"
                                ><span class="more"><i class="fa-solid fa-plus"></i>더보기</span></a
                            >
                        </div>
                        <table class="recentBordTbl recentTbl">
                        	<tr>
                                <th>글제목</th>
                                <th>작성자</th>
                                <th>날짜</th>
                            </tr>
                           <c:choose>
                        		<c:when test="${boardsList.isEmpty()}">
                        			<tr>
                        				<td colspan="3" align="center">등록된 글이 없습니다.</td>
                        			</tr>
                        		</c:when>
                        		<c:when test="${!boardsList.isEmpty()}">
                        			<c:forEach var="board" items="${boardsList}">
                        				<tr>
                        					<td>
                        						<a href="${contextPath}/group/board/viewArticle?group_id=${param.group_id}&article_no=${board.article_no}">
                        							<p>${board.title}</p>
                        						</a>
                        					</td>
                        					<td>${board.user_id}</td>
                        					<td>${board.create_date}</td>
                        				</tr>
                        			</c:forEach>
                        		</c:when>
                        	</c:choose>
                        </table>
                    </div>

                    <!-- 멤버 영역 시작-->
                    <div class="memberArea sc2_subsection">
                        <div class="mainTitle">
                            <h3 class="nemoMemTitle">네모멤버</h3>
                            <span>(<span class="currentNum">${groupNum}</span>/<span class="maxNum">${groupVO.mem_no}</span>)</span>
                        </div>
                        <span class="btnEventPrev" title="이전보기"><i class="fa-solid fa-chevron fa-chevron-left"></i></span>
                        <div class="animationSlide">
                            <div class="sliderPanel">
                                <!-- <p><center>이미지슬라이드영역~</center></p> -->
                                <c:forEach var="user" items="${usersList}">
                                	<div class="slideContent">
                                		<div class="memImg">
                                			<img src="${contextPath}/userImageDownload?user_id=${user.user_id}&user_img=${user.user_img}" />
                                		</div>
                                		<br/>
                                		<div class="memName">
                                			<span>${user.nickname}</span>
                                		</div>
                                	</div>
                                </c:forEach>
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
        <input type="hidden" id="groupNum_hidden" name="groupNum_hidden" value="${groupNum}" />
        <input type="hidden" id="user_id_hidden" name="user_id_hidden" value="${user_id}" />
        <input type="hidden" id="isMember_hidden" name="isMember_hidden" value="${isMember}" />
	
	<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>