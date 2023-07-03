<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    import="java.util.*, nemo.*"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:set var="articlesList" value="${articleMap.articlesList}" />
<c:set var="totArticles" value="${articleMap.totArticles}" />
<c:set var="section" value="${articleMap.section }" />
<c:set var="pageNum" value="${articleMap.pageNum }" />


<% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html>
<html lang="ko">
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>관리자-소모임 관리 페이지</title>
        <link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
        <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
        <link rel="stylesheet" href="${contextPath}/css/common.css" />
        <link rel="stylesheet" href="${contextPath}/css/submenu.css" />
        <link href="${contextPath}/DataTables/datatables.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="${contextPath}/css/adminGroup.css" />
        <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
        <script src="https://kit.fontawesome.com/3d4603cd1d.js" crossorigin="anonymous"></script>
        <script src="${contextPath}/js/header.js"></script>
    </head>
    
        <c:choose>
		<c:when test='${msg == "addGroup"}'>
		<script>
			window.onload=function () {
				alert("소모임을 등록 하셨습니다.");
			}
		</script>
		</c:when>
		<c:when test="${msg == 'deleted'}">
		<script>
			window.onload=function () {
				alert("소모임 정보를 삭제 하였습니다.");
			}
		</script>
		</c:when>
	
	</c:choose>
    
    <body>
        <!-- 배경색 조정 -->
        <div class="menu_bg"></div>
        <!-- header 시작 -->
        <header>
            <h1 class="logo">
        <a href="${contextPath}/index"
          ><img src="${contextPath}/images/logo.png" alt="logo"
        /></a>
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
                <li><a href="../group/createGroup.html">소모임 만들기</a></li>
                <li><a href="../search.html">소모임 검색</a></li>
                <li><a href="../myPage/myProfile.html">프로필</a></li>
                <li><a href="../myPage/mySchedule.html">내 일정</a></li>
                <li><a href="../myPage/myGroupList.html">내 소모임</a></li>
                <li><a href="../help/helpQnA.html">고객센터</a></li>
                <li><a href="#">로그아웃</a></li>
            </ul>
            <div class="sidemenu_footer">
                <h3>Contact details</h3>
                <p>글 넣을 거 있으면 넣기</p>
            </div>
        </div>
        <!-- header 종료 -->

        <!-- 콘텐츠 영역 시작 -->
        <div class="section2">
            <div class="sc2_contents">
                <!-- 메뉴바 시작 -->
                <div class="sc2_menu_contents">
                    <div class="sc2_menu">
                        <h2 class="sc2_menu_title">관리자</h2>
                        <ul class="sc2_menu_list">
                            <li>
                                <a href="adminGroup.html">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name submenu_select"><span>소모임 관리</span></div>
                                        <i class="fa-solid fa-minus submenu_select"></i>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="adminMember.html">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name"><span>회원 관리</span></div>
                                        <i class="fa-solid fa-angle-right menu_angle"></i>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="adminReport.html">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name">
                                            <span>신고 관리</span>
                                        </div>
                                        <i class="fa-solid fa-angle-right menu_angle"></i>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="adminHelpQnA.html">
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
                            <a href="index.html">
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
                        <div class="searchGroupArea">
                            <select name="adminMainCategory" id="adminMainCategory"></select>
                            <select name="adminSmallCategory" id="adminSmallCategory"></select>
                            <input type="text" placeholder="소모임 이름으로 검색" />
                            <button role="button" id="adminSearchGroupNameBtn" class="btn">검색</button>
                        </div>
                        <!-- <div class="sortGroupArea">
            <a href="#" id="adminSortByGroupIndexBtn" class="button">
                번호순 정렬
            </a>
            <a href="#" id="adminSortByGroupReportBtn" class="button">
                신고 횟수 정렬
            </a>
            </div> -->
                        <div class="groupListArea">
                            <div class="groupList">
                                <table border="1" class="adminGroupTbl">
                                    <thead>
                                        <tr>
                                            <th>소모임 번호</th>
                                            <th>대분류</th>
                                            <th>소분류</th>
                                            <th>소모임 이름</th>
                                            <th>현재 인원</th>
                                            <th>최대 인원</th>
                                            <th>소모임장</th>
                                            <th>생성일</th>
                                            <th>신고횟수</th>
                                            <th>삭제 처리</th>
                                        </tr>
                                    </thead>
                                    
                                    <c:choose>
                                    	<c:when test="${empty groupsList}">
                                    		<tr>
                                    			<td colspan="10" align="center">
                                    				등록된 소모임이 없습니다.
                                    			</td>
                                    		</tr>
                                    	</c:when>
                                    	
                                    	<c:when test="${!empty groupsList}">
                                    	 	<c:forEach var="group" items="${groupsList}">
                                    	 		<tr align="center">
                                    	 			<td>${group.grp_id}</td>
                                    	 			<td>${group.main_name}</td>
                                    	 			<td>${group.sub_name}</td>
                                    	 			<td>${group.grp_name}</td>
                                    	 			<td>${group.mem_no}</td>
                                    	 			<td>${group.mem_no}</td>
                                    	 			<td>${group.grp_mng}</td>
                                    	 			<td>${group.create_date}</td>
                                    	 			<td>${group.report_cnt}</td>
                                    	 			<td><a class="button" href="${contextPath}/admingroup/delGroup.do?grp_id=${group.grp_id}">삭제 처리</a></td>
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
        
        <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>

        <script src="${contextPath}/DataTables/datatables.min.js"></script>
        <script src="${contextPath}/js/adminGroup.js"></script>
    </body>
</html>