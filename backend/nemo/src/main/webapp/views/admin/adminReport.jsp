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
<head>
<title>네모: 신고관리</title>

	<meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	 <link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
        <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
        <link rel="stylesheet" href="${contextPath}/css/common.css" />
        <link rel="stylesheet" href="${contextPath}/css/submenu.css" />
        <link rel="stylesheet" href="${contextPath}/css/tabmenuAdmin.css" />
        <link href="${contextPath}/DataTables/datatables.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="${contextPath}/css/adminReport.css" />
        <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
        <script src="https://kit.fontawesome.com/f9a2702e84.js" crossorigin="anonymous"></script>
        <script src="${contextPath}/js/header.js"></script>
        <script src="${contextPath}/js/tabmenu.js"></script>
        <script src="${contextPath}/DataTables/datatables.min.js"></script>
        <script src="${contextPath}/js/adminReport.js"></script>
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
                                <a href="${contextPath}/adminUser">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name"><span>회원 관리</span></div>
                                        <i class="fa-solid fa-angle-right menu_angle"></i>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="${contextPath}/adminGroup">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name"><span>소모임 관리</span></div>
                                        <i class="fa-solid fa-angle-right menu_angle"></i>
                                    </div>
                                </a>
                            </li>                            
                            <li>
                                <a href="${contextPath}/adminReport">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name submenu_select">
                                            <span>신고 관리</span>
                                        </div>
                                        <i class="fa-solid fa-minus submenu_select"></i>
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
                        <h2 class="sc2_subsection_title_name">신고 관리</h2>
                        <!-- nav 바 시작 -->
                        <div class="nav_bar">
                            <a href="${contextPath}/index">
                                <i class="fa-solid fa-house nav_icon"></i>
                            </a>
                            <i class="fa-solid fa-angle-right nav_icon"></i>
                            <span>관리자</span>
                            <i class="fa-solid fa-angle-right nav_icon"></i>
                            <span>신고 관리</span>
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
                                                        <tr text-align="center">
                                                            <th>No</th>
                                                            <th>소모임ID</th>
                                                            <th>소모임명</th>
                                                            <th>소모임장ID</th>
                                                            <th>신고자ID</th>
                                                            <th>총신고횟수</th>
                                                            <th>신고일</th>
                                                            <th>삭제</th>                                                                                                                    </tr>
                                                    </thead>
			    										<c:choose>
															<c:when test="${empty groupList}">
																<tr>
																	<td colspan="8" align="center">
																		신고된 소모임이 없습니다.
																	</td>	
																</tr>
															</c:when>
															<c:when test="${!empty groupList}">
																<c:forEach var="group" items="${groupList}" varStatus="status">
																	<tr align="center">
																		<td>${status.count}</td>
																		<td><a href="${contextPath}/group/groupMain?group_id=${group.grpRepVO.grp_id}">${group.grpRepVO.grp_id}</a></td>
																		<td><a href="${contextPath}/group/groupMain?group_id=${group.grpRepVO.grp_id}">${group.grpRepVO.groupVO.grp_name}</a></td>
																		<td>${group.grpRepVO.groupVO.grp_mng}</td>
																		<td>${group.grpRepVO.reporter_id}</td>																		
																		<td>${group.repCnt}</td>
																		<td>${group.grpRepVO.rep_date}</td>
																		<td><button role="button" class="button" onclick="fn_Grpdelete(${group.grpRepVO.grp_id})">삭제</button></td>
																	</tr>
																</c:forEach>
															</c:when>
														</c:choose>                                                 
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
                                                            <th>No</th>
                                                            <th>피신고자ID</th>
                                                            <th>신고횟수</th>
                                                            <th>신고자ID</th>
                                                            <th>신고일</th>
                                                            <th>회원삭제</th>                                                           
                                                        </tr>
                                                    </thead>									
		                                               <c:choose>
														<c:when test="${empty userList}">
															<tr>
																<td colspan="6" align="center">
																	신고된 회원이 없습니다. 
																</td>	
															</tr>
														</c:when>
														<c:when test="${!empty userList}">
															<c:forEach var="user" items="${userList}" varStatus="status">
																<tr align="center">
																	<td>${status.count}</td>
																	<td>${user.userRepVO.accused_id}</td>
																	<td>${user.repCnt}</td>
																	<td>${user.userRepVO.reporter_id}</td>
																	<td>${user.userRepVO.rep_date}</td>
																	<td><button role="button" class="button" onclick="fn_Userdelete('${user.userRepVO.accused_id}')">삭제</button></td>
																</tr>
															</c:forEach>
														</c:when>
													  </c:choose>
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



</body>
</html>