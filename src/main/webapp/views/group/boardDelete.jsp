<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    import="java.util.*, nemo.*"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="group" value="${groupInfo}" />

<% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>게시판</title>
    <link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
    <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
    <link rel="stylesheet" href="${contextPath}/css/common.css" />
    <link rel="stylesheet" href="${contextPath}/css/submenu.css" />
    <link rel="stylesheet" href="${contextPath}/css/sectionTitle.css" />
    <link rel="stylesheet" href="${contextPath}/css/boardDelete.css" />
    <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
    <script src="https://kit.fontawesome.com/97cbadfe25.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="${contextPath}/js/header.js"></script>
  </head>
  <body>
    <!-- header 시작 -->
	<jsp:include page="../header.jsp" flush="true"></jsp:include>
    <!-- header 종료 -->

	<!-- section1 -->
	<jsp:include page="./groupHeader.jsp" flush="true"></jsp:include>
	<!-- section1종료 -->

    <!-- 콘텐츠 영역 -->
    <div class="section2">
      <div class="sc2_contents">
        <!-- 메뉴바 시작 -->
        <div class="sc2_menu_contents">
          <div class="sc2_menu">
            <h2 class="sc2_menu_title">게시판</h2>
            <ul class="sc2_menu_list">
              <li>
                <a href="${contextPath}/schedule.html">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name"><span>일정</span></div>
                    <i class="fa-solid fa-angle-right menu_angle"></i>
                  </div>
                </a>
              </li>
              <li>
                <a href="${contextPath}/group/board?group_id=${param.group_id}">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name submenu_select">
                      <span>게시판</span>
                    </div>
                    <i class="fa-solid fa-minus submenu_select"></i>
                  </div>
                </a>
              </li>
              <li>
                <a href="${contextPath}/myGroupMember.html">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name"><span>멤버</span></div>
                    <i class="fa-solid fa-angle-right menu_angle"></i>
                  </div>
                </a>
              </li>
              <li>
                <a href="${contextPath}/groupSetting.html">
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

        <!-- 게시판 컨텐츠 영역 시작 -->
        <div class="board sc2_subsection">
          <div class="sc2_subsection_title">
            <h2 class="sc2_subsection_title_name">게시판</h2>

            <!-- nav 바 시작 -->
            <div class="nav_bar">
              <a href="index.html">
                <i class="fa-solid fa-house nav_icon"></i>
              </a>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>나의 모임</span>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>게시판</span>
            </div>
            <!-- nav 바 종료 -->
          </div>

		  
		  <div class="boardArea deleteArea">
		 	<!-- 컨텐츠영역 -->
		 	<div class="innerContent">
		 		<div class="contentTitle">
		 			<form action="${contextPath}/group/board/delete?group_id=${param.group_id}&article_no=${param.article_no}" method="post">
		 				<c:if test="${deleteInfo eq 'deleteArticle'}">
		 					<input type="hidden" name="number" value="${articleInfo.article_no}">
		 					<input type="hidden" name="deleteInfo" value="deleteArticle" />
				 			<p class="title">${articleInfo.title}</p>
				 			<p class="msg">이 게시글을 삭제하시겠습니까?</p>
		 				</c:if>
		 				<c:if test="${deleteInfo eq 'deleteComment'}">
		 					<input type="hidden" name="number" value="${commentInfo.comment_no}">
		 					<input type="hidden" name="deleteInfo" value="deleteComment" />
		 					<p class="comTitle">제목 : ${commentInfo.articleVO.title}</p>
		 					<p calss="comment">ㄴ${commentInfo.com_cont}</p>
		 					<p class="msg">이 댓글을 삭제하시겠습니까?</p>
		 				</c:if>
		 			<!-- 버튼영역 -->
			 			<div class="btnArea">
				  			<input type="submit" value="삭제" class="button">
				  			<input type="reset" value="취소" class="buttonCancle">
				  		</div>
	          		<!-- 버튼영역 종류 -->
	          		</form>
		 		</div>	
		 	

          	</div>
          </div>
        </div>
        <!-- 게시판 컨텐츠 영역 끝 -->
        
        
      </div>
    </div>

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
              <a href="index.html"
                ><img
                  src="${contextPath}/images/logo_white.png"
                  class="img-fluid"
                  alt="logo"
              /></a>
            </div>
            <div class="footer_text">
              <p>© 2023 NEMO</p>
            </div>
            <div class="footer_social_icon">
              <span>Follow us</span>
              <a href="#"><i class="fab fa-facebook-f facebook-bg"></i></a>
              <a href="#"><i class="fab fa-twitter twitter-bg"></i></a>
              <a href="#"><i class="fab fa-google-plus-g google-bg"></i></a>
            </div>
          </div>
        </div>
      </div>
    </footer>
    <!-- 푸터 영역 끝 -->
  </body>
</html>