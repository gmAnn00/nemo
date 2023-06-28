<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var="article" value="${article}" />
<c:set var="admin" value="${admin}" />
<c:set var="comments" value="${articleViewMap.comments}" />
<c:set var="isSame" value="${articleViewMap.isSame }" />
<c:set var="qna_id" value="${articleViewMap.qna_id }" />
    
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Q&A 조회</title>
    <link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
    <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
    <link rel="stylesheet" href="${contextPath}/css/common.css" />
    <link rel="stylesheet" href="${contextPath}/css/submenu.css" />
    <link rel="stylesheet" href="${contextPath}/css/sectionTitle.css" />
    <link rel="stylesheet" href="${contextPath}/css/boardView.css" />
    <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
    <script
      src="https://kit.fontawesome.com/3d4603cd1d.js"
      crossorigin="anonymous"
    ></script>
    <script src="${contextPath}/js/header.js"></script>
    <script src="${contextPath}/js/boardView.js"></script>
    
    <script type="text/javascript">
	//이미지 미리보기 구현
	function readImage(input) {
		if(input.files && input.files[0]) {
			let reader=new FileReader();
			reader.onload=function (event) {
				$("#preview").attr('src',event.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	function fn_enable(obj) {
		document.getElementById("id_title").disabled=false;
		document.getElementById("id_content").disabled=false;
		let imgName=document.getElementById("id_imgFile");
		if(imgName != null) {
			imgName.disabled=false;
		}
		document.getElementById("tr_button_modify").style.display="block";
		document.getElementById("tr_button").style.display="none";
	}
	function toList(obj) {
		obj.action="${contextPath}/viewQna/QnAView.do?qna_id=${article.qna_id}";
		obj.submit();
	}
	function fn_modify_article(obj) {
		obj.action="${contextPath}/viewQna/modArticle.do";
		obj.submit();
	}
	//23-05-25
	function fn_remove_article(url, qna_id) {
		let d_form=document.createElement("form");  //동적으로 form태그를 생성
		d_form.setAttribute("action",url);
		d_form.setAttribute("method","post");
		let qna_idInput=document.createElement("input");
		qna_idInput.setAttribute("type","hidden");
		qna_idInput.setAttribute("name","qna_id");
		qna_idInput.setAttribute("value",qna_id);
		d_form.appendChild(qna_idInput);
		document.body.appendChild(d_form);
		d_form.submit();
	}
	function backToList(obj) {  //리스트로 돌아가는 함수
		obj.action="${contextPath}/viewQna/helpQnA.do";
		obj.submit();
	}
	function fn_reply_form(url, parent_no) {  //답글쓰기 폼 구현
		let r_form=document.createElement("form");
		r_form.setAttribute("action",url);
		r_form.setAttribute("method","post");
		let parent_noInput=document.createElement("input");
		parent_noInput.setAttribute("type","hidden");
		parent_noInput.setAttribute("name","parent_no");
		parent_noInput.setAttribute("value",parent_no);
		r_form.appendChild(parentNoInput);
		document.body.appendChild(r_form);
		r_form.submit();
	}
	</script>


  </head>
  <body>
    <!-- header 시작 -->
    <!-- 사이드 메뉴시 배경색 조정 -->
    <div class="menu_bg"></div>
    <header>
      <h1 class="logo">
        <a href="${contextPath}/index.html"
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
      <!-- 소모임 내부 메뉴(공통) 코딩 필요 -->
      <div class="sc2_contents">
        
        <!-- 메뉴바 시작 -->
        <div class="sc2_menu_contents">
          <div class="sc2_menu">
            <h2 class="sc2_menu_title">관리자</h2>
            <ul class="sc2_menu_list">
              <li>
                <a href="#">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name"><span>소모임관리</span></div>
                    <i class="fa-solid fa-angle-right menu_angle"></i>
                  </div>
                </a>
              </li>
              <li>
                <a href="#">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name submenu_select"><span>회원관리</span></div>
                    <i class="fa-solid fa-minus submenu_select"></i>
                  </div>
                </a>
              </li>
              <li>
                <a href="#">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name"><span>신고관리</span></div>
                    <i class="fa-solid fa-angle-right menu_angle"></i>
                  </div>
                </a>
              </li>
              <li>
                <a href="${contextPath}/viewQna/helpQnA.do">
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

        <!-- 게시글 조회 영역 시작 -->
        <div class="boardView sc2_subsection">
          <div class="sc2_subsection_title">
            <h2 class="sc2_subsection_title_name">Q&A</h2>
            <!-- nav 바 시작 -->
            <div class="nav_bar">
              <a href="${contextPath}/index.html">
                <i class="fa-solid fa-house nav_icon"></i>
              </a>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>나의 모임</span>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>게시글 작성</span>
            </div>
            <!-- nav 바 종료 -->
          </div>

			<div class="atricleArea">
	          <!-- 글 위쪽 버튼 영역 -->
	          <div class="articleToolBtns">
	            <!-- 기능 구현에 따라 코딩 수정요 -->
	            <c:if test="${admin eq 0 }">
		            <a href="#" role="button" class="button btnEdit">수정</a>
		            <a href="#" role="button" class="buttonCancle btnDel">삭제</a>
	            </c:if>
	            <c:if test="${admin eq 1 }">
		            <a href="#" role="button" class="button btnEdit">답글</a>
	            </c:if>
	            <!-- 목록을 전에 눌렀던 페이지 기억해서 돌아갈거면 동적으로 바꿔야 함 -->
	            <!--  <a href="javascript:history.back();" role="button" class="button2 btnList">목록</a>-->
	            <a href="${contextPath}/qna/helpQnA?qna_id=${qna_id}" role="button" class="button2 btnList">목록</a>
	          </div>
	          <div class="articleContentBox">
	            <!-- 제목 영역 -->
	            <div class="articleHeader">
	              <div class="articleTitle">
	                <div class="titleHead"><span></span></div>
	                <div class="titleArea">${article.title}</div>
	              </div>
	              <div class="writerInfo">
	                <div class="thumbArea">
	                  <img src="${contextPath}/images/icon_prof_wh.png" alt="프로필사진"/>
	                </div>
	                <div class="profileArea">
	                  <div class="profileInfo">
	                    <button class="writerNick">${article.nickname }</button>
	                  </div>
	                  <div class="articleInfo">
	                    <p class="date">${article.create_date }</p>
	                  </div>
	                </div>
	              </div>
	              </div>
	            </div>
	            <!-- 내용 영역 -->
	            <div id="contentArea" class="contentArea">
	              <p>내용 ${article.content}</p>
	            </div>
	            <!-- 이미지 부분? -->
	            <c:out value="${contextPath }/qnaImages/${article.qna_id}/${article.qna_img}"/>
	            <c:if test="${not empty article.qna_img}">
	            	<div>
	            		<input type="hidden" name="originalFileName" value="${article.qna_img}">
	            		<img src="${contextPath}/qnaImagesDownload?qna_id=${param.qna_id}&qna_img=${qnaVO.qna_img}" alt="">
	            		<!--<img src="${contextPath}/qnaImages/${article.qna_id}/${article.qna_img}">-->
	            		<!-- <img id="preview" src="${contextPath}/download.do?qna_id=${aricle.qna_id}&qna_id=${article.qna_id}">-->
	            	</div>
	            </c:if>
	            
	          <!-- 글 아래쪽 버튼 영역 -->
	          <div class="articleBottomBtns">
	            <!-- 기능 구현에 따라 코딩 수정요 -->
	            <div class="leftArea">
	              <!-- 본인 글이면 수정 삭제 뜨고 아니면 글쓰기만 뜨도록 -->
		            <c:if test="${admin eq 0 }">
			            <a href="#" role="button" class="button btnEdit">수정</a>
			            <a href="#" role="button" class="buttonCancle btnDel">삭제</a>
		            </c:if>
		            <c:if test="${admin eq 1 }">
			            <a href="#" role="button" class="button btnEdit">답글</a>
		            </c:if>
	            </div>
	            <div class="rightArea">
	              <!-- 목록을 전에 눌렀던 페이지 기억해서 돌아갈거면 바꿔야 함 -->
	              <a href="board.html" role="button" class="button2 btnList">목록</a>
	              <a href="#boardView" role="button" class="button2 btnTop">Top</a>
	            </div>
	          </div>
          </div>
        </div>
        <!-- 게시글 조회 영역 끝 -->
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
