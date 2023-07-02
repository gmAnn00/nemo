<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    import="java.util.*, nemo.*"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% request.setCharacterEncoding("utf-8"); %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />



<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>네모: 회원 관리</title>
        <link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
        <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
        <link rel="stylesheet" href="${contextPath}/css/common.css" />
        <link rel="stylesheet" href="${contextPath}/css/submenu.css" />
        <link href="${contextPath}/DataTables/datatables.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="${contextPath}/css/adminUser.css" />
        <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
        <script src="https://kit.fontawesome.com/97cbadfe25.js" crossorigin="anonymous"></script>
        <script src="${contextPath}/js/header.js"></script>
        <script src="${contextPath}/DataTables/datatables.min.js"></script>
        <script src="${contextPath}/js/adminUser.js"></script>
    </head>

    <body>
	<jsp:include page="../header.jsp" flush="true"></jsp:include>

        <!-- 콘텐츠 영역 시작 -->
        <div class="section2">
            <div class="sc2_contents">
                <!-- 메뉴바 시작 -->
                <div class="sc2_menu_contents">
                    <div class="sc2_menu">
                        <h2 class="sc2_menu_title">관리자</h2>
                        <ul class="sc2_menu_list">
                            <li>
                                <a href="${contextPath}/adminGroup">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name"><span>소모임 관리</span></div>
                                        <i class="fa-solid fa-angle-right menu_angle"></i>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="${contextPath}/adminUser">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name submenu_select"><span>회원 관리</span></div>
                                        <i class="fa-solid fa-minus submenu_select"></i>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="${contextPath}/adminReport">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name">
                                            <span>신고 관리</span>
                                        </div>
                                        <i class="fa-solid fa-angle-right menu_angle"></i>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="${contextPath}/viewQna">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name"><span>고객센터 Q&A</span></div>
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
                        <h2 class="sc2_subsection_title_name">회원 관리</h2>
                        <!-- nav 바 시작 -->
                        <div class="nav_bar">
                            <a href="${contextPath}/index">
                                <i class="fa-solid fa-house nav_icon"></i>
                            </a>
                            <i class="fa-solid fa-angle-right nav_icon"></i>
                            <span>관리자</span>
                            <i class="fa-solid fa-angle-right nav_icon"></i>
                            <span>회원 관리</span>
                        </div>
                        <!-- nav 바 종료 -->
                    </div>

                    <div class="sc2_subcontents">
                        <div class="sc2_subcontent">
                            <div class="searchMemberArea">
                                <!-- <input type="text" placeholder="아이디로 검색" /> -->
                                <!-- <button id="adminSearchMemberIdBtn">검색</button> -->
                            </div>
                            <!-- <div class="sortMemberArea">
                <button id="adminSortByMemberIdBtn">번호순 정렬</button>
                <button id="adminSortByMemberReportBtn">신고 횟수 정렬</button>
                </div> -->
                            <div class="userListArea">
                                <div class="userList">
                                    <!-- 테이블 동적생성 해야함 -->
                                    <table border="1" class="adminUserTbl">
                                        <thead>
                                            <tr>
                                                <th>NO</th>
                                                <th>회원 ID</th>
                                                <th>닉네임</th>
                                                <th>가입일</th>
                                                <th>신고누적횟수</th>
                                                <th>회원삭제</th>
                                            </tr>
                                        </thead>
                                        
										<c:choose>
												<c:when test="${empty userList}">
													<tr>
														<td colspan="6" align="center">
															등록된 회원이 없습니다.
														</td>	
													</tr>
												</c:when>
												<c:when test="${!empty userList}">
													<c:forEach var="user" items="${userList}" varStatus="status">
														<tr align="center">
															<td>${status.count}</td>
															<td>${user.userVO.user_id}</td>
															<td>${user.userVO.nickname}</td>
															<td>${user.userVO.join_date}</td>
															<td>${user.reportCnt}</td>
															<td><a role="button" class="button" href="#" onclick="fn_delete('${user.userVO.user_id}')">삭제</a></td>
															
														</tr>
													</c:forEach>
												</c:when>
										</c:choose>

           
                                    </table>
                                </div>
                                <!-- <div class="memberTablePage"></div> -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 콘텐츠 영역 종료-->

        <!-- 푸터 영역 시작 -->
        <footer class="footer_section">
            <div class="container">
                <div class="footer_section1">
                    <div class="footer_section1_content">
                        <i class="fas fa-map-marker-alt"></i>
                        <div class="cta-text">
                            <h4>Address</h4>
                            <span>서울시 종로구 종로78</span>
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
                            <a href="index.html"><img src="${contextPath}/images/logo_white.png" class="img-fluid" alt="logo" /></a>
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