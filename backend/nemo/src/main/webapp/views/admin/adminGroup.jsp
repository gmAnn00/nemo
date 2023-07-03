<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    import="java.util.*, nemo.*"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html>
<html lang="ko">
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>네모: 소모임 관리</title>
        <link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
        <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
        <link rel="stylesheet" href="${contextPath}/css/common.css" />
        <link rel="stylesheet" href="${contextPath}/css/submenu.css" />
        <link href="${contextPath}/DataTables/datatables.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="${contextPath}/css/adminGroup.css" />
        <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
    	<script src="https://kit.fontawesome.com/97cbadfe25.js" crossorigin="anonymous"></script>
    	<script src="${contextPath}/js/header.js"></script>
        <script src="${contextPath}/DataTables/datatables.min.js"></script>
        <script src="${contextPath}/js/adminGroup.js"></script>
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
                                        <div class="menu_submenu_name submenu_select"><span>소모임 관리</span></div>
                                        <i class="fa-solid fa-minus submenu_select"></i>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="${contextPath}/adminUser">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name"><span>회원 관리</span></div>
                                        <i class="fa-solid fa-angle-right menu_angle"></i>
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
                        <h2 class="sc2_subsection_title_name">소모임 관리</h2>
                        
                        <!-- nav 바 시작 -->
                        <div class="nav_bar">
                            <a href="${contextPath}/index">
                                <i class="fa-solid fa-house nav_icon"></i>
                            </a>
                            <i class="fa-solid fa-angle-right nav_icon"></i>
                            <span>관리자</span>
                            <i class="fa-solid fa-angle-right nav_icon"></i>
                            <span>소모임관리</span>
                        </div>
                        <!-- nav 바 종료 -->

                    </div>

                    <div class="sc2_subcontents">
                    <!--  
                        <div class="searchGroupArea">
                            <select name="adminMainCategory" id="adminMainCategory"></select>
                            <select name="adminSmallCategory" id="adminSmallCategory"></select>
                            <input type="text" placeholder="소모임 이름으로 검색" />
                            <button role="button" id="adminSearchGroupNameBtn" class="btn">검색</button>
                        </div>
                        -->
                        <!-- <div class="sortGroupArea">
            <a href="#" id="adminSortByGroupIndexBtn" class="button">
                번호순 정렬
            </a>
            <a href="#" id="adminSortByGroupReportBtn" class="button">
                신고 횟수 정렬s
            </a>
            </div> -->
                        <div class="groupListArea">
                            <div class="groupList">
                                <table class="adminGroupTbl">
                                    <thead>
                                        <tr>
                                        	<th>No</th>
                                            <th>소모임 ID</th>
                                            <th>소모임장</th>
                                            <th>소모임 이름</th>
                                            <th>현재 인원</th>
                                            <th>최대 인원</th>
                                            <th>생성일</th>
                                            <th>신고횟수</th>
                                            <th>그룹삭제</th>
                                        </tr>
                                    </thead>
                                    
                                    <c:choose>
                                    	<c:when test="${empty groupList}">
                                    		<tr>
                                    			<td colspan="9" align="center">
                                    				등록된 소모임이 없습니다.
                                    			</td>
                                    		</tr>
                                    	</c:when>
                                    	
                                    	<c:when test="${!empty groupList}">
                                    	 	<c:forEach var="group" items="${groupList}" varStatus="status">
                                    	 		<tr align="center">
                                    	 			<td>${status.count}</td>
                                    	 			<td>${group.groupVO.grp_id}</td>
                                    	 			<td>${group.groupVO.grp_mng}</td>
                                    	 			<td>${group.groupVO.grp_name}</td>
                                    	 			<td>${group.currentMemNO}</td>
                                    	 			<td>${group.groupVO.mem_no}</td>
                                    	 			<td>${group.groupVO.create_date}</td>
                                    	 			<td>${group.reportCnt}</td>
                                    	 			<td><a role="button" class="button" href="#" onclick="fn_delete(${group.groupVO.grp_id})">삭제</a></td>
                                    	 		</tr>
                                    	 	</c:forEach>
                                    	</c:when>
                                    </c:choose>
                                    
                                </table>
                            </div>
                            <!--<div class="groupTablePage"></div>  -->
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
