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
<c:set var="isSame" value="${articleViewMap.isSame }" />
<c:set var="qna_id" value="${articleViewMap.qna_id }" />
    
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
    <link rel="stylesheet" href="${contextPath}/css/qnaView.css" />
    <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
    <script src="https://kit.fontawesome.com/97cbadfe25.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="${contextPath}/js/header.js"></script>
    <script src="${contextPath}/js/qnaView.js"></script>
    
    <script type="text/javascript">
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
	<jsp:include page="../header.jsp" flush="true"></jsp:include>
    <!-- header 종료 -->


    <!-- 콘텐츠 영역 -->
    <div class="section2">
      <!-- 소모임 내부 메뉴(공통) 코딩 필요 -->
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
       
        <!-- 게시글 조회 영역 시작 -->
        <div class="boardView sc2_subsection">
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

			<div class="atricleArea">
	          <div class="articleContentBox">
	            <!-- 제목 영역 -->
	            <div class="articleHeader">
	              <div class="articleTitle">
	              	<div class="titleHead">
	              		<span class="brackets">
			             	<c:choose>
			             		<c:when test="${article.user_id eq 'admin'}">답변</c:when>
			             		<c:otherwise>문의</c:otherwise>
			             	</c:choose>
	                	</span>
	                </div>
	                <div class="titleArea">${article.title}</div>
	              </div>
	              <div class="writerInfo">
	                <div class="thumbArea">
	                  <img src="${contextPath}/userImageDownload?user_id=${article.user_id}&user_img=${article.userVO.user_img}" alt="프로필사진"/>
	                </div>
	                <div class="profileArea">
	                  <div class="profileInfo">
	                    <p class="writerNick">${article.userVO.nickname }</button>
	                    <p class="date comDate">${article.create_date }</p>
	                  </div>
	                  <!--  
	                  <div class="articleInfo">
	                    <p class="date">${article.create_date }</p>
	                  </div>
	                  -->
	                </div>
	              </div>
	              </div>
	            </div>
	            <!-- 내용 영역 -->
	            <div id="contentArea" class="contentArea">
	          		<div class="contentEditTool">
		            <!-- 기능 구현에 따라 코딩 수정요 -->
			            <c:if test="${admin eq 0 }">			          
				            <a href="${contextPath}/viewQna/modArticleForm.do?qna_id=${article.qna_id}" role="button" class="btnEdit btn">수정</a>
				            <a href="#" role="button" class="btnDel btn" onclick="fn_deleteChk(${article.qna_id})">삭제</a>
			            </c:if>
			            <c:if test="${admin eq 1 and article.user_id != user_id}">
				            <a href="${contextPath}/viewQna/replyForm.do?parent_no=${article.qna_id}" role="button" class="btnEdit btn">답글</a>
			            </c:if>
			         <c:if test="${admin eq 1 and article.user_id eq user_id}">
			         	<a href="${contextPath}/viewQna/modArticleForm.do?qna_id=${article.qna_id}" role="button" class="btnEdit btn">수정</a>
			            <a href="#" role="button" class="btnDel btn" onclick="fn_deleteChk(${article.qna_id})">삭제</a>
		            </c:if>
		       		</div>
	            
	              <div class="content">
	            		${article.content}
	            	</div>
	            </div>
	            
	          <!-- 글 아래쪽 버튼 영역 -->
	          <div class="articleBottomBtns">
	            <!-- 기능 구현에 따라 코딩 수정요 -->
	            <div class="leftArea">
	              <!-- 본인 글이면 수정 삭제 뜨고 아니면 글쓰기만 뜨도록 -->
		            <c:if test="${admin eq 0 and article.user_id eq user_id}">
		            	<a href="${contextPath}/viewQna/QnAWrite.do" role="button" class="btnWrite btn">글쓰기</a>
			            <a href="${contextPath}/viewQna/modArticleForm.do?qna_id=${article.qna_id}" role="button" class="btnEdit btn">수정</a>
			            <a href="#" role="button" class="btnDel btn" onclick="fn_deleteChk(${article.qna_id})">삭제</a>
		            </c:if>
		            <c:if test="${admin eq 1 and article.user_id != user_id}">
			            <a href="${contextPath}/viewQna/replyForm.do?parent_no=${article.qna_id}" role="button" class="btnEdit btn">답글</a>
			        </c:if>
			        <c:if test="${admin eq 1 and article.user_id eq user_id}">
			        	<a href="${contextPath}/viewQna/modArticleForm.do?qna_id=${article.qna_id}" role="button" class="btnEdit btn">수정</a>
			            <a href="#" role="button" class="btnDel btn" onclick="fn_deleteChk(${article.qna_id})">삭제</a>
		            </c:if>
	            </div>
	            <div class="rightArea">
	              <!-- 목록을 전에 눌렀던 페이지 기억해서 돌아갈거면 바꿔야 함 -->
	              <a href="${contextPath}/viewQna" role="button" class="btnList btn">목록</a>
	              <a href="#boardView" role="button" class="btn btnTop "><i class="fa-solid fa-caret-up"></i>TOP</a>
	            </div>
	          </div>
          </div>
        </div>
        <!-- 게시글 조회 영역 끝 -->
      </div>
    </div>

<jsp:include page="../footer.jsp" flush="true"></jsp:include>
  </body>
</html>
