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
        <!-- 배경색 조정 -->
        <div class="menu_bg"></div>
        <!-- header 시작 -->
        <header>
            <h1 class="logo">
                <a href="../index.html"><img src="../../images/logo.png" alt="logo" /></a>
            </h1>
        </header>
        <button class="burger">
            <span></span>
        </button>
        <div class="sidemenu">
            <ul class="main_menu">
                <li>
                    <a href="#">
                        <div class="profile"><i class="fa-solid fa-circle-user"></i><span class="profile_name">사이다</span></div>
                    </a>
                </li>
                <li><a href="createGroup.html">소모임 만들기</a></li>
                <li><a href="search.html">소모임 검색</a></li>
                <li><a href="myPage.html">프로필</a></li>
                <li><a href="mySchedule.html">내 일정</a></li>
                <li><a href="myGroupList.html">내 소모임</a></li>
                <li><a href="adminHelp.html">고객센터</a></li>
                <li><a href="#">로그아웃</a></li>
            </ul>
            <div class="sidemenu_footer">
                <h3>Contact details</h3>
                <p>글 넣을 거 있으면 넣기</p>
            </div>
        </div>
        <!-- header 종료 -->
        <div id="wrapper">
            <!-- section1 시작 -->
            <div class="section1">
                
                	<c:forEach var="group" items="${groupList}">
                		<div class="group_containter">
		                    <div class="group_all">
		                        <div class="group_img">
		                            <img src="../../images/free-icon-group-8847475.png" alt="group_img" />
		                        </div>
		                        <div class="group_name">
		                            <a href="groupMain.html">
		                                <span>${group.grp_name}</span>
		                            </a>
		                        </div>
		                        <div class="group_info">
		                            <div class="group_info_category">
		                                <div class="category_box group_info_category_box">${group.main_name}</div>
		                                <div class="category_box group_info_category_box">${group.sub_name}</div>
		                            </div>
		                            <div class="group_info_member">
		                                <div class="group_info_title"><span>멤버수</span></div>
		                                <div class="group_info_contents"><span>${group.mem_no}</span></div>
		                            </div>
		                            <div class="group_info_follower">
		                                <div class="group_info_title"><span>개설일</span></div>
		                                <div class="group_info_contents"><span><fmt:formatDate pattern="yyyy-MM-dd" value="${group.create_date}"/></span></div>
		                            </div>
		                        </div>
		                    </div>
                		</div>
                	</c:forEach>
                
                
                
            </div>
            <!-- section1 종료 -->

            <!-- 컨텐츠 시작 -->
            <div class="section2"> 
                <div class="sc2_contents">
                    
                    <!-- 메뉴바 시작 -->
                    <div class="sc2_menu_contents">
                        <div class="sc2_menu">
                            <h2 class="sc2_menu_title">멤버</h2>
                            <ul class="sc2_menu_list">
                                <li>
                                    <a href="schedule.html">
                                        <div class="sc2_icon_menu">
                                            <div class="menu_submenu_name"><span>일정</span></div>
                                            <i class="fa-solid fa-angle-right menu_angle"></i>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="board.html">
                                        <div class="sc2_icon_menu">
                                            <div class="menu_submenu_name"><span>게시판</span></div>
                                            <i class="fa-solid fa-angle-right menu_angle"></i>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="myPageGroup.html">
                                        <div class="sc2_icon_menu">
                                            <div class="menu_submenu_name submenu_select"><span>멤버</span></div>
                                            <i class="fa-solid fa-minus submenu_select"></i>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="${contextPath}/group/manager/setting/myGroupSetting">
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
                                        <div class="tab_box">
                                            
                                            
                                            <c:forEach var="user" items="${userList}">
	                                            <!-- 프로필 카드 시작 -->
	                                            <div class="profile_box">
	                                                <div class="dot_menu">
	                                                    <div class="dot">
	                                                        <span></span> 
	                                                    </div>
	                                                    
		                                                    <div class="hidden_menu">
		                                                    
		                                                        <ul class="hidden_menu_bar">
		                                                            <li><a href="${contextPath}/group/manager/member/report.do?user_id=${user.user_id}&grp_id=${param.grp_id}">신고</a></li>
		                                                            <li><a href="${contextPath}/group/manager/member/mandate.do?user_id=${user.user_id}&grp_id=${param.grp_id}">위임</a></li>
		                                                            <li><a href="${contextPath}/group/manager/member/exile.do?user_id=${user.user_id}&grp_id=${param.grp_id}">추방</a></li>
		                                                        </ul>
		                                                 
		                                                    </div>
	                                                    
	                                                </div>
	                                                <div class="profile profile-smallimg">
	                                                    <div class="profile_image"><img src="../../images/172e317de5420a65269e6c58868117778f324a0b9c48f77dbce3a43bd11ce785.png" alt="Doggo"/></div>
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
	                                                            <li><a href="${contextPath}/group/manager/member/approve.do?user_id=${approveUser.user_id}&grp_id=${param.grp_id}">승인</a></li>
	                                                            <li><a href="${contextPath}/group/manager/member/reject.do?user_id=${approveUser.user_id}&grp_id=${param.grp_id}">거절</a></li>
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

         <!-- 푸터 영역 시작 -->
         <footer class="footer_section">
            <div class="container">
                <div class="footer_section1">
                    <div class="footer_section1_content">
                        <i class="fas fa-map-marker-alt"></i>
                        <div class="cta-text">
                            <h4>Address</h4>
                            <span>서울시 양천구 목동</span>
                        </div>
                    </div>

                    <div class="footer_section1_content">
                        <i class="fas fa-phone"></i>
                        <div class="cta-text">
                            <h4>Call us</h4>
                            <span>02-123-4567</span>
                        </div>
                    </div>

                    <div class="footer_section1_content">
                        <i class="far fa-envelope-open"></i>
                        <div class="cta-text">
                            <h4>Mail us</h4>
                            <span>admin@nemo.com</span>
                        </div>
                    </div>
                </div>

                <div class="footer_section2">
                    <div class="footer_section2_content">
                        <div class="footer_section2_content_title">
                            <h3>NEMO Links</h3>
                        </div>
                        <ul>
                            <li><a href="#">Home</a></li>
                            <li><a href="#">고객센터</a></li>
                            <li><a href="#">이용약관</a></li>
                            <li><a href="#">공지사항</a></li>
                            <li><a href="#">저작권정책</a></li>
                            <li><a href="#">개인정보 처리방침</a></li>
                        </ul>
                    </div>

                    <div class="footer_section2_content">
                        <div class="footer_logo">
                            <a href="index.html"><img src="../../images/logo_white.png" class="img-fluid" alt="logo" /></a>
                        </div>
                        <div class="footer_text">
                            <p>© 2023 NEMO</p>
                        </div>
                        <div class="footer_social_icon">
                            <span>Follow us</span>
                            <div class="footer_social_icon_box">
                                <a href="#"><i class="fab fa-facebook-f facebook-bg"></i></a>
                                <a href="#"><i class="fab fa-twitter twitter-bg"></i></a>
                                <a href="#"><i class="fab fa-google-plus-g google-bg"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!-- 푸터 영역 끝 -->
    </body>
</html>
