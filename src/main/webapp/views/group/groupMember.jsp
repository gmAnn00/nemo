<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
<c:forEach var="elem" items="${grpMngList}" >
	<c:if test="${elem eq param.group_id}">
		<c:set var="isMng" value="true" />
	</c:if>
</c:forEach>
<%
	request.setCharacterEncoding("utf-8");
%>   
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>네모</title>
        <script src="https://kit.fontawesome.com/bc604c01cc.js" crossorigin="anonymous"></script>
        <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css" rel="stylesheet" />
        <link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
        <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
        <link rel="stylesheet" href="${contextPath}/css/common.css" />
        <link rel="stylesheet" href="${contextPath}/css/submenu.css" />
        <link rel="stylesheet" href="${contextPath}/css/tabmenu.css" />
        <link rel="stylesheet" href="${contextPath}/css/sectionTitle.css" />
        <link rel="stylesheet" href="${contextPath}/css/myGroupMember.css" />
        <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
        <script src="${contextPath}/js/myGroupMember.js"></script>
        <script src="${contextPath}/js/header.js"></script>
        <script src="${contextPath}/js/tabmenu.js"></script>
    </head>
    <body>
        <jsp:include page="../header.jsp" flush="true"></jsp:include>
        
        
        <div id="wrapper">
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

            <!-- 컨텐츠 시작 -->
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
                                        <div class="menu_submenu_name submenu_select"><span>멤버</span></div>
                                         <i class="fa-solid fa-minus submenu_select"></i>
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
                                    </ul>
                                </div>
                                <div class="tab-slider--container">
                                    
                                    <!-- tab1 시작 -->
                                    <div id="tab1" class="tab-slider--body">
                                        <div class="tab_box">
                                            
                                            
                                            <c:forEach var="user" items="${usersList}">
	                                            <!-- 프로필 카드 시작 -->
	                                            <div class="profile_box">
	                                                <div class="dot_menu">
	                                                    <div class="dot">
	                                                        <span></span> 
	                                                    </div>
	                                                    
		                                                    <div class="hidden_menu">
		                                                    
		                                                        <ul class="hidden_menu_bar">
		                                                        	<c:if test="${user.user_id == user_id}">
		                                                        		<li><a href="${contextPath}/group/member/delete?group_id=${param.group_id}">탈퇴</a></li>
		                                                        	</c:if>
		                                                            <c:if test="${user.user_id != user_id}">
		                                                            	<li><a href="${contextPath}/group/manager/member/report.do?user_id=${user.user_id}">신고</a></li>
		                                                            </c:if>
		                                                        </ul>
		                                                 
		                                                    </div>
	                                                    
	                                                </div>
	                                                <div class="profile profile-smallimg">
	                                                    <div class="profile_image"><img src="${contextPath}/userImageDownload?user_id=${user.user_id}&user_img=${user.user_img}" /></div>
	                                                    <div class="profile_info">
	                                                        <h3>${user.nickname}</h3>
	                                                        <div class="profile_info_detail">
	                                                            <div class="profile_loc">
	                                                                <i class="fa-solid fa-location-dot profile_icon"></i>
	                                                                <span class="profile_loc_info">${user.user_addr1}</span>
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
                                        <div class="tab_box">
                                            
                                            <c:forEach var="approveUser" items="${approveUserList}">
	                                            <div class="profile_box">
	                                                <div class="dot_menu">
	                                                    <div class="dot">
	                                                        <span></span> 
	                                                    </div>
	                                                    <div class="hidden_menu">
	                                                        <ul class="hidden_menu_bar">
	                                                            <li><a href="${contextPath}/group/manager/approve.do?user_id=${approveUser.user_id}">승인</a></li>
	                                                            <li><a href="${contextPath}/group/manager/reject.do?user_id=${approveUser.user_id}">거절</a></li>
	                                                        </ul>
	                                                    </div>
	                                                </div>
	                                                <div class="profile profile-smallimg">
	                                                    <div class="profile_image"><img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/567707/dog.png" alt="Doggo"/></div>
	                                                    <div class="profile_info">
	                                                        <h3>${approveUser.nickname}</h3>
	                                                        <div class="profile_info_detail">
	                                                            <div class="profile_loc">
	                                                                <i class="fa-solid fa-location-dot profile_icon"></i>
	                                                                <span class="profile_loc_info">${approveUser.user_addr1}</span>
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