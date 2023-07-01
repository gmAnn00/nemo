<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고페이지</title>
</head>
	 <link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
        <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
        <link rel="stylesheet" href="${contextPath}/css/common.css" />
        <link rel="stylesheet" href="${contextPath}/css/submenu.css" />
        <link rel="stylesheet" href="${contextPath}/css/tabmenu.css" />
        <link href="${contextPath}/DataTables/datatables.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="${contextPath}/css/adminReport.css" />
        <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
        <script src="https://kit.fontawesome.com/3d4603cd1d.js" crossorigin="anonymous"></script>
        <script src="${contextPath}/js/header.js"></script>
        <script src="${contextPath}/js/tabmenu.js"></script>
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
                                <a href="${contextPath}/adminGroup.html">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name"><span>소모임 관리</span></div>
                                        <i class="fa-solid fa-angle-right menu_angle"></i>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="${contextPath}/adminMember.html">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name"><span>회원 관리</span></div>
                                        <i class="fa-solid fa-angle-right menu_angle"></i>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="${contextPath}/adminReport.html">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name submenu_select">
                                            <span>신고 관리</span>
                                        </div>
                                        <i class="fa-solid fa-minus submenu_select"></i>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="${contextPath}/adminHelpQnA.html">
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
                        <h2 class="sc2_subsection_title_name">신고 관리</h2>
                        <!-- nav 바 시작 -->
                        <div class="nav_bar">
                            <a href="index.html">
                                <i class="fa-solid fa-house nav_icon"></i>
                            </a>
                            <i class="fa-solid fa-angle-right nav_icon"></i>
                            <span>나의 모임</span>
                            <i class="fa-solid fa-angle-right nav_icon"></i>
                            <span>소모임관리</span>
                        </div>
                        <!-- nav 바 종료 -->
                    </div>

                    <div class="sc2_subcontents">
                        <div class="sc2_subcontent">
                            
                             <!-- tab menu 시작 -->
                            <div class="tab_container">
                                <div class="tab-slider--nav">
                                    <ul class="tab-slider--tabs">
                                        <li class="tab-slider--trigger active" rel="tab1"><span>신고 소모임 목록</span></li>
                                        <li class="tab-slider--trigger" rel="tab2"><span>신고 회원 목록</span></li>
                                    </ul>
                                </div>
                                <div class="tab-slider--container">
                                    
                                    <!-- tab1 시작 -->
                                    <div id="tab1" class="tab-slider--body">
                                        <div class="tab_box">

                                            <!-- 리스트 시작 -->
                                            <div class="list">
                                                <table class="adminGroupTbl">
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
                                                            <th>삭제</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>1</td>
                                                            <td>스포츠</td>
                                                            <td>헬스</td>
                                                            <td>소모임 이름1</td>
                                                            <td>2</td>
                                                            <td>10</td>
                                                            <td>honggilddong</td>
                                                            <td>2023/05/01</td>
                                                            <td>1</td>
                                                            <td><button class="btn">삭제</button></td>
                                                        </tr>
                                                        <tr>
                                                            <td>2</td>
                                                            <td>스포츠</td>
                                                            <td>헬스</td>
                                                            <td>소모임 이름2</td>
                                                            <td>2</td>
                                                            <td>10</td>
                                                            <td>honggilddong</td>
                                                            <td>2023/05/01</td>
                                                            <td>2</td>
                                                            <td><button class="btn">삭제</button></td>
                                                        </tr>
                                                      
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>    
                                    <!-- tab1 종료 -->
                                    <!-- <div class="groupTablePage"></div> -->
                                
                                    <!-- tab2 시작 -->
                                    <div id="tab2" class="tab-slider--body">
                                        <div class="tab_box">
                                            <!-- 리스트 시작 -->
                                            <div class="list">
                                                <table class="adminMemberTbl">
                                                    <thead>
                                                        <tr>
                                                            <th>회원번호</th>
                                                            <th>아이디</th>
                                                            <th>이름</th>
                                                            <th>생년월일</th>
                                                            <th>성별</th>
                                                            <th>이메일</th>
                                                            <th>휴대전화</th>
                                                            <th>가입일</th>
                                                            <th>신고횟수</th>
                                                            <th>탈퇴</th>
                                                        </tr>
                                                    </thead>

                                                    <tbody>
                                                        <tr>
                                                            <td>1</td>
                                                            <td>ho</td>
                                                            <td>홍길동</td>
                                                            <td>2000/01/01</td>
                                                            <td>M</td>
                                                            <td>gildong@gmail.com</td>
                                                            <td>01012345678</td>
                                                            <td>2023/05/01</td>
                                                            <td>1</td>
                                                            <td><button class="btn">탈퇴</button></td>
                                                        </tr>
                                                        <tr>
                                                            <td>2</td>
                                                            <td>hongg</td>
                                                            <td>홍길동</td>
                                                            <td>2000/01/01</td>
                                                            <td>M</td>
                                                            <td>gildong@gmail.com</td>
                                                            <td>01012345678</td>
                                                            <td>2023/05/01</td>
                                                            <td>2</td>
                                                            <td><button class="btn">탈퇴</button></td>
                                                        </tr>
                                                       
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- tab2 종료 -->
                                    <!-- <div class="groupTablePage"></div> -->
                                </div>
                            </div>
                            <!-- tab menu 종료 -->

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 콘텐츠 영역 종료-->


<jsp:include page="../footer.jsp" flush="true"></jsp:include>

		<script src="${contextPath}/DataTables/datatables.min.js"></script>
        <script src="${contextPath}/js/adminReport.js"></script>

</body>
</html>