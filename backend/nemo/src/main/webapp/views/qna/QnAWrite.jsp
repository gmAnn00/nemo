<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>NEMO</title>
    <link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
    <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
    <link rel="stylesheet" href="${contextPath}/css/common.css" />
    <link rel="stylesheet" href="${contextPath}/css/submenu.css" />
    <link rel="stylesheet" href="${contextPath}/css/sectionTitle.css" />
    <link rel="stylesheet" href="${contextPath}/css/boardWrite.css" />
    <link rel="stylesheet" href="${contextPath}/resources/summernote/summernote-lite.css"/>
    <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
    <script
      src="https://kit.fontawesome.com/3d4603cd1d.js"
      crossorigin="anonymous"
    ></script>
    
    <!-- summernote
    <script src="${contextPath}/resources/summernote/summernote-lite.js"></script>
    <script src="${contextPath}/resources/summernote/lang/summernote-ko-KR.js"></script>
     -->

     
<script type="text/javascript">
	//이미지 미리보기 구현
	function readImage(input) {
		if(input.files && input.files[0]) {
			let reader=new FileReader();
			reader.onload=function (event) {
				$("#preview").attr('src',event.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}else {
			$("#preview").attr('src','');
		}
	}
	//다른 액션으로 submit
	function backToList(obj) {
		obj.action="${contextPath}/qna/helpQnA.do";
		obj.submit();
	}
</script>
     
    <script src="${contextPath}/js/header.js"></script>
    <script src="${contextPath}/js/boardWrite.js"></script>
 </head>
 
 
 <body>
    <!-- header 시작 -->
    <!-- 사이드 메뉴시 배경색 조정 -->
    <div class="menu_bg"></div>
    <header>
      <h1 class="logo">
        <a href="${contextPath}/index.html"><img src="${contextPath}/images/logo.png" alt="logo"
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
            <div class="profile">
              <i class="fa-solid fa-circle-user"></i
              ><span class="profile_name">사이다</span>
            </div>
          </a>
        </li>
        <li><a href="#">소모임 만들기</a></li>
        <li><a href="#">소모임 검색</a></li>
        <li><a href="#">프로필</a></li>
        <li><a href="#">내 일정</a></li>
        <li><a href="#">내 소모임</a></li>
        <li><a href="#">고객센터</a></li>
        <li><a href="#">로그아웃</a></li>
      </ul>
      <div class="sidemenu_footer">
        <h3>Contact details</h3>
        <p>글 넣을 거 있으면 넣기</p>
      </div>
    </div>
    <!-- header 종료 -->

    <!-- 콘텐츠 영역 -->
    <div class="section2">
      <div class="sc2_contents">
        <!-- 메뉴바 시작 -->
        <div class="sc2_menu_contents">
          <div class="sc2_menu">
            <h2 class="sc2_menu_title">관리자</h2>
            <!-- include -->
            <jsp:include page="/views/qna/includes/admin_header.jsp"/>
            
          </div>
        </div>
        <!-- 메뉴바 종료 -->

        <!-- 게시판 글쓰기 영역 시작 -->
        <div class="boardWrite sc2_subsection">

          <!-- 메인 상단 타이틀 출력 부분-->
          <div class="sc2_subsection_title">
            <h2 class="sc2_subsection_title_name">Q&A</h2>

            <!-- nav 바 시작 -->
            <div class="nav_bar">
              <a href="${contextPath}/index">
                <i class="fa-solid fa-house nav_icon"></i>
              </a>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>나의 모임</span>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>게시판</span>
            </div>
            <!-- nav 바 종료 -->
          </div>
          <!-- 메인 상단 타이틀 출력 부분 종료 -->

          <!-- 글쓰기 영역 -->
          <div class="boardWriteArea">
          
            <form action="${contextPath}/viewQna/addArticle.do" method="post" enctype="multipart/form-data">
              <!-- 제목 영역 -->
              <div class="articleWritingTitle">

                <select name="headTitle" id="headTitle" class="headTitle">
                  <option value="notice">공지사항</option>
                  <option value="afterMeeting">문의사항</option>
                </select>
                <!-- 제목 -->
                <textarea name="writeTitle" id="writeTitle" class="writeTitle" rows="1" placeholder="제목을 입력해주세요"></textarea>
              </div>
              <!-- 글쓰는 영역 -->
              <div class="editorArea article">
                <textarea  placeholder="내용을 입력해주세요"></textarea>
              </div>
              
              <!-- 이미지 첨부 -->
              <div class="qna_image">
              	<input type="file" name="qna_img" onchange="readImage(this)"><tr>
              	<img id="preview" src="#" width="200" alt="">
              </div>

            </form>

              <!-- 등록 버튼 -->
              <div class="btnRegister">
                <a href="${contextPath}/viewQna/QnAView.do" role="button" class="button">등록</a>
                <a href="${contextPath}/viewQna/helpQnA.do" role="button" class="buttonCancle">취소</a>
              </div>
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
