<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="article" value="${article}" />

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>네모: 고객센터</title>
    <link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
    <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
    <link rel="stylesheet" href="${contextPath}/css/common.css" />
    <link rel="stylesheet" href="${contextPath}/css/submenu.css" />
    <link rel="stylesheet" href="${contextPath}/css/sectionTitle.css" />
    <link rel="stylesheet" href="${contextPath}/css/qnaWrite.css" />
    <link rel="stylesheet" href="${contextPath}/resources/summernote/summernote-lite.css"/>
    <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
    <script src="https://kit.fontawesome.com/97cbadfe25.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="${contextPath}/resources/summernote/summernote-lite.js"></script>
    <script src="${contextPath}/resources/summernote/lang/summernote-ko-KR.js"></script>
    <script src="${contextPath}/js/header.js"></script>
    <script src="${contextPath}/js/modQnaArticle.js"></script>

 </head>
 
 
 <body>
    <!-- header 시작 -->
	<jsp:include page="../header.jsp" flush="true"></jsp:include>
    <!-- header 종료 -->

    <!-- 콘텐츠 영역 -->
    <div class="section2">
      <div class="sc2_contents">
      <c:choose>
      	<c:when test="${admin eq 1}">
	        <!-- 메뉴바 시작 -->
	        <div class="sc2_menu_contents">
	          <div class="sc2_menu">
	            <h2 class="sc2_menu_title">관리자</h2>
	      
	            
	            <!-- include -->
	            <jsp:include page="/views/qna/includes/admin_header.jsp"/>
	          </div>
	        </div>
        	<!-- 메뉴바 종료 -->
        </c:when>
        <c:otherwise>
        <!-- 메뉴바 시작 -->
        <div class="sc2_menu_contents">
          <div class="sc2_menu">
            <h2 class="sc2_menu_title">고객센터</h2>
            <ul class="sc2_menu_list">
              <li>
                <a href="${contextPath}/viewQna">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name submenu_select"><span>Q&A</span></div>
                    <i class="fa-solid fa-angle-right menu_angle"></i>
                  </div>
                </a>
              </li>
            </ul>
          </div>
        </div>
        <!-- 메뉴바 종료 -->
        </c:otherwise>
       </c:choose>

        <!-- 게시판 글쓰기 영역 시작 -->
        <div class="boardWrite sc2_subsection">

          <!-- 메인 상단 타이틀 출력 부분-->
          <div class="sc2_subsection_title">
            <h2 class="sc2_subsection_title_name">고객센터 Q&A</h2>

            <!-- nav 바 시작 -->
            <div class="nav_bar">
              <a href="index.html">
                <i class="fa-solid fa-house nav_icon"></i>
              </a>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>고객센터</span>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>Q&A</span>
            </div>
            <!-- nav 바 종료 -->
          </div>
          <!-- 메인 상단 타이틀 출력 부분 종료 -->

          <!-- 글쓰기 영역 -->
          <div class="boardWriteArea">
          
            <form action="${contextPath}/viewQna/modArticle.do" method="post" name="qnaArticleForm" id="qnaArticleForm">
            	<input type="hidden" name="qna_id" value="${article.qna_id}"/>
              <!-- 제목 영역 -->
              <c:if test="${admin eq 0}">
              <div class="articleWritingTitle">
				<div class="headTitleArea">
	                <select name="headTitle" id="headTitle" class="headTitle">
		                  <option value="afterMeeting">문의사항</option>
	                </select>
                </div>
                </c:if>
                <!-- 제목 -->
                <div class="titleArea">
                	<input type="text" name="title" id="writeTitle" class="writeTitle" value=${article.title}></input>
              	</div>
               </div>
              <!-- 글쓰는 영역 -->
              <div class="editorArea">
                <textarea id="summernote" name="content">${article.content}</textarea>
              </div>              


              <!-- 등록 버튼 -->
              <div class="btnRegister">
                <a href="#" role="button" class="button">수정</a>
                <a href="#" role="button" class="buttonCancle" onclick="fn_cancel()">취소</a>
              </div>
            </form>

            </div>
          </div>
        </div>
        <!-- 게시판 글쓰기 영역 끝 -->
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
              <li><a href="${contextPath}/index">Home</a></li>
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
