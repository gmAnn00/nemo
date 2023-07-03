<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 

<%
	request.setCharacterEncoding("utf-8");
%>   
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>네모: 멤버</title>
        <script src="https://kit.fontawesome.com/bc604c01cc.js" crossorigin="anonymous"></script>
        <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css" rel="stylesheet" />
        <link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
        <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
        <link rel="stylesheet" href="${contextPath}/css/common.css" />
        <link rel="stylesheet" href="${contextPath}/css/submenu.css" />
        <link rel="stylesheet" href="${contextPath}/css/tabmenu.css" />
        <link rel="stylesheet" href="${contextPath}/css/sectionTitle.css" />
        <link rel="stylesheet" href="${contextPath}/css/groupMember.css" />
        <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
        <script src="${contextPath}/js/myGroupMember.js"></script>
        <script src="${contextPath}/js/header.js"></script>
        <script src="${contextPath}/js/tabmenu.js"></script>
    </head>
    <body>
       <jsp:include page="../header.jsp" flush="true"></jsp:include>
        <div id="wrapper">
            
            <!-- section1 -->
			<jsp:include page="./groupHeader.jsp" flush="true"></jsp:include>
			<!-- section1종료 -->

            <!-- 컨텐츠 시작 -->
            <div class="section2"> 
                <div class="sc2_contents">
                    
                    <!-- 메뉴바 시작 -->
                    <div class="sc2_menu_contents">
                        <div class="sc2_menu">
                            <h2 class="sc2_menu_title">멤버</h2>
                            <ul class="sc2_menu_list">
                                <li>
                                    <a href="${contextPath}/group/schedule?group_id=${param.group_id}">
                                        <div class="sc2_icon_menu">
                                            <div class="menu_submenu_name"><span>일정</span></div>
                                            <i class="fa-solid fa-angle-right menu_angle"></i>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="${contextPath}/group/board?group_id=${param.group_id}">
                                        <div class="sc2_icon_menu">
                                            <div class="menu_submenu_name"><span>게시판</span></div>
                                            <i class="fa-solid fa-angle-right menu_angle"></i>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="${contextPath}/group/manager/member?group_id=${param.group_id}">
                                        <div class="sc2_icon_menu">
                                            <div class="menu_submenu_name submenu_select"><span>멤버</span></div>
                                            <i class="fa-solid fa-minus submenu_select"></i>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="${contextPath}/group/manager/setting?group_id=${param.group_id}">
                                        <div class="sc2_icon_menu">
                                            <div class="menu_submenu_name"><span>소모임관리</span></div>
                                            <i class="fa-solid fa-angle-right menu_angle"></i>
                                        </div>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- 메뉴바 종료 -->

                    <!-- 메인 컨텐츠 시작 -->
                    <div class="sc2_subsection">
                        <div class="sc2_subsection_title">
                            <h2 class="sc2_subsection_title_name">멤버</h2>
                            
                            <!-- nav 바 시작 -->
                            <div class="nav_bar">
                                <a href="../index.html">
                                    <i class="fa-solid fa-house nav_icon"></i>
                                </a>
                                <i class="fa-solid fa-angle-right nav_icon"></i>
                                <span>나의 모임</span>
                                <i class="fa-solid fa-angle-right nav_icon"></i>
                                <span>멤버</span>
                            </div>
                            <!-- nav 바 종료 -->

                        </div>

                        <div class="sc2_subcontents">
                            
                            <!-- tab menu 시작 -->
                            <div class="tab_container">
                                <div class="tab-slider--nav">
                                    <ul class="tab-slider--tabs">
                                        <li class="tab-slider--trigger active" rel="tab1"><span>회원목록</span></li>
                                        <li class="tab-slider--trigger" rel="tab2"><span>가입승인</span></li>
                                    </ul>
                                </div>
                                <div class="tab-slider--container">
                                    
                                    <!-- tab1 시작 -->
                                    <div id="tab1" class="tab-slider--body">
                                        <div class="tab_box_profile">
                                            
                                            
                                            <c:forEach var="user" items="${userList}">
                                            <c:set var="idx" value="${user.user_addr1.indexOf(' ', user.user_addr1.indexOf(' ') + 1)}" />
											<c:set var="locationU" value="${user.user_addr1.substring(0, idx)}"/>
	                                            <!-- 프로필 카드 시작 -->
	                                            <div class="profile_box">
	                                            	<c:set var="isMng" value="true" />
													<c:forEach var="elem" items="${grpMemberList}" >
														<c:if test="${elem eq user.user_id}">
															<c:set var="isMng" value="false" />
														</c:if>
													</c:forEach>
	                                            	<c:if test="${isMng != true}">

		                                                <div class="dot_menu">
		                                                    <div class="dot">
		                                                        <span></span> 
		                                                    </div>
		                                                    
		                                                    <div class="hidden_menu">
		                                                    
		                                                        <ul class="hidden_menu_bar">
		                                                            <li><a href="${contextPath}/report/member?accused_id=${user.user_id}&group_id=${param.group_id}">신고</a></li>
		                                                            <li><a href="${contextPath}/group/manager/member/mandate.do?user_id=${user.user_id}&group_id=${param.group_id}">위임</a></li>
		                                                            <li><a href="${contextPath}/group/manager/member/exile.do?user_id=${user.user_id}&group_id=${param.group_id}">추방</a></li>
		                                                        </ul>
		                                                 
		                                                    </div>
		                                                    
		                                                </div>
	                                                </c:if>
	                                                <div class="profile profile-smallimg">
	                                                    <div class="profile_image"><img src="${contextPath}/userImages/${user.user_id}/${user.user_img}" alt="UserImg"/></div>
	                                                    <div class="profile_info">
	                                                        <h3>${user.nickname}</h3>
	                                                        <div class="profile_info_detail">
	                                                            <div class="profile_loc">
	                                                                <i class="fa-solid fa-location-dot profile_icon"></i>
	                                                                <span class="profile_loc_info">${locationU}</span>
	                                                                
	                                                            </div>
	                                                            <div class="profile_group_joinDate">
	                                                                <i class="fa-regular fa-calendar profile_icon"></i>
	                                                                <span>${user.join_date}</span>
	                                                            </div>
	                                                        </div>
	                                                    </div>
	                                                </div>
	                                            </div>
	                                            <!-- 프로필 카드 종료 -->
                                            </c:forEach>
                                            
                                        </div> 
                                    </div>
                                    <!-- tab1 종료 -->
                                    
                                    <!-- tab2 시작 -->
                                    <div id="tab2" class="tab-slider--body">
                                        <div class="tab_box_profile">
                                            
                                            <c:forEach var="approveUser" items="${approveUserList}">
                                            <c:set var="idx" value="${approveUser.user_addr1.indexOf(' ', approveUser.user_addr1.indexOf(' ') + 1)}" />
											<c:set var="locationU" value="${approveUser.user_addr1.substring(0, idx)}"/>
	                                            <div class="profile_box">
	                                                <div class="dot_menu">
	                                                    <div class="dot">
	                                                        <span></span> 
	                                                    </div>
	                                                    <div class="hidden_menu">
	                                                        <ul class="hidden_menu_bar">
	                                                            <li><a href="${contextPath}/group/manager/member/approve.do?user_id=${approveUser.user_id}&group_id=${param.group_id}">승인</a></li>
	                                                            <li><a href="${contextPath}/group/manager/member/reject.do?user_id=${approveUser.user_id}&group_id=${param.group_id}">거절</a></li>
	                                                        </ul>
	                                                    </div>
	                                                </div>
	                                                <div class="profile profile-smallimg">
	                                                    <div class="profile_image"><img src="${contextPath}/userImageDownload?user_id=${approveUser.user_id}&user_img=${approveUser.user_img}" alt="Doggo"/></div>
	                                                    <div class="profile_info">
	                                                        <h3>${approveUser.nickname}</h3>
	                                                        <div class="profile_info_detail">
	                                                            <div class="profile_loc">
	                                                                <i class="fa-solid fa-location-dot profile_icon"></i>
	                                                                <span class="profile_loc_info">${locationU}</span>
	                                                            </div>
	                                                            <div class="profile_group_joinDate">
	                                                                <i class="fa-regular fa-calendar profile_icon"></i>
	                                                                <span>${approveUser.join_date}</span>
	                                                            </div>
	                                                        </div>
	                                                    </div>
	                                                </div>
	                                            </div>
                        					</c:forEach>
                        					
                                        </div>
                                    </div>
                                    <!-- tab2 종료 -->
                                
                                </div>
                            </div>
                        </div>
                        <!-- tab menu 종료 -->
                    
                    </div>
                    <!-- 메인 컨텐츠 종료 -->
                </div>
            </div>
            <!-- 컨텐츠 종료 -->
        
        </div>

        <jsp:include page="../footer.jsp" flush="true"></jsp:include>
    </body>
</html>
