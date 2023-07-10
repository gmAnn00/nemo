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
<title>네모: 소모임삭제</title>
<link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
<link rel="stylesheet" href="${contextPath}/css/normalize.css" />
<link rel="stylesheet" href="${contextPath}/css/common.css" />
<link rel="stylesheet" href="${contextPath}/css/submenu.css" />
<link rel="stylesheet" href="${contextPath}/css/delUser.css" />
 <link rel="stylesheet" href="${contextPath}/css/sectionTitle.css" />
<script
  src="https://kit.fontawesome.com/bc604c01cc.js"
  crossorigin="anonymous"
></script>
<script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/js/header.js"></script>

</head>
<body>
				<jsp:include page="../header.jsp" flush="true"></jsp:include>
	
	 		<!-- section1 -->
				<jsp:include page="./groupHeader.jsp" flush="true"></jsp:include>
			<!-- section1종료 -->
			
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
		                                        <div class="menu_submenu_name"><span>멤버</span></div>
		                                        <i class="fa-solid fa-angle-right menu_angle"></i>
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
		                            <li>
		                                <a href="${contextPath}/group/manager/member/delGroupForm?group_id=${param.group_id}">
		                                    <div class="sc2_icon_menu reportArea">
		                                        <div class="menu_submenu_name reportTitle"><span>소모임삭제</span></div>
		                                        <i class="fa-solid fa-arrow-right menu_angle"></i>
		                                    </div>
		                                </a>
		                            </li>
                        </ul>
                    </div>
                </div>
                <!-- 메뉴바 종료 -->
	 

        <!--3. 컨텐츠부분-->
        <div class="sc2_subsection">
          <div class="sc2_subsection_title">
            <h2 class="sc2_subsection_title_name">소모임 삭제</h2>
            
            <!-- nav 바 시작 -->
            <div class="nav_bar">
              <a href="index.html">
                <i class="fa-solid fa-house nav_icon"></i>
              </a>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>나의 모임</span>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>소모임 삭제</span>
            </div>
            <!-- nav 바 종료 -->
          </div>

          <!-- 탈퇴 -->
          <div class="withdrawal">
            <h3>정말로 소모임을 <span>삭제</span>하시겠습니까?</h3>
            <h4>삭제하시려면 "삭제하기"를 입력하고 버튼을 눌러주세요</h4>
            <form action="${contextPath}/group/manager/member/delGroup?group_id=${param.group_id}" method="post">
              <input type="text" name="delString" placeholder="'삭제하기'를 입력하세요"/>
              <input type="submit" class="buttonCancle" value="소모임 삭제" />
            </form>
          </div>
        </div>
      </div>
    </div>

	<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>