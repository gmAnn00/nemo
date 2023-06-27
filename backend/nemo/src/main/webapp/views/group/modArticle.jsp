<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="article" value="${articleViewMap.article}"/>
<c:set var="group" value="${groupInfo}" />

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>네모: 게시판</title>
    <link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
    <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
    <link rel="stylesheet" href="${contextPath}/css/common.css" />
    <link rel="stylesheet" href="${contextPath}/css/submenu.css" />
    <link rel="stylesheet" href="${contextPath}/css/sectionTitle.css" />
    <link rel="stylesheet" href="${contextPath}/css/boardWrite.css" />
    <link rel="stylesheet" href="${contextPath}/resources/summernote/summernote-lite.css"/>
    <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
	<script src="https://kit.fontawesome.com/97cbadfe25.js" crossorigin="anonymous"></script>
    <script src="${contextPath}/resources/summernote/summernote-lite.js"></script>
    <script src="${contextPath}/resources/summernote/lang/summernote-ko-KR.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="${contextPath}/js/header.js"></script>
    <!-- <script src="${contextPath}/js/modArticle.js"></script> -->
    <script type="text/javascript">
    $(document).ready(function() {
    	// a href='#' 클릭 무시 스크립트
    	$('a[href="#"]').click(function(ignore) {
    	    ignore.preventDefault();
    	});
    	
    	$('.button').click(function(){
    		let headTitle=$('#headTitle').val();
    		let writeTitle=$('#writeTitle').val();

    		let summernote=$('#summernote').val();
    		let text=$('#summernote').summernote('code');
    		console.log(text.length);

    		if(!headTitle){
    			alert('말머리를 선택해주세요');
    		}else if(!writeTitle) {
    			alert('제목을 입력해주세요');
    		}else if(summernote.length<=0){
    			alert('내용을 입력해주세요');			
    		} else {
    			console.log("content");
    			 $('textarea[name="content"]').val($('#summernote').summernote('code'));
    			console.log($('textarea[name="content"]').val($('#summernote').summernote('code')));
    			$('#articleForm').submit();
    		}
    			console.log("말머리"+headTitle);
    			console.log("제목"+writeTitle);
    			console.log("tedg:  "+ summernote.length);

    	});
    	

    	$('#summernote').summernote('code','${article.content}');
    	//여기 아래 부분
    	$('#summernote').summernote({
    		  height: 500,                 // 에디터 높이
    		  minHeight: null,             // 최소 높이
    		  maxHeight: null,             // 최대 높이
    		  focus: false,                  // 에디터 로딩후 포커스를 맞출지 여부
    		  lang: "ko-KR",					// 한글 설정
              toolbar: [
    		    // 글꼴 설정
    		    ['fontname', ['fontname']],
    		    // 글자 크기 설정
    		    ['fontsize', ['fontsize']],
    		    // 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
    		    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
    		    // 글자색
    		    ['color', ['forecolor','color']],
    		    ['para', ['ul', 'ol', 'paragraph']],    // 글머리 기호, 번호매기기, 문단정렬
    		    // 그림첨부, 링크만들기, 동영상첨부
    		    ['insert',['picture',]],
    		    ['undo', ['undo','redo']]
    		  ],
    		  // 추가한 글꼴
    		fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
    		 // 추가한 폰트사이즈
    		fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
    		callbacks: {
    			onImageUpload: function(files, editor, welEditable) {
                    sendFile(files[0], editor, welEditable);
                },/*
    			onImageUpload: function(files, editor, welEditable) {


    				//파일 다중 업로드 위해 반복문사용
    				for(var i=files.lenght-1; i>=0; i--) {
    					uploadSummerNoteImageFile(files[i],this);
    				}
    				
    			},*/
    			    onChange:function(contents, $editable){ //텍스트 글자수 및 이미지등록개수
                    setContentsLength(contents, 0);
                }
    			
    		}
    		
    	});
    	
    	
    });



    function uploadSummerNoteImage(file,el) {
    		let ctx=getContextPath();
    		let data = new FormData();
    		data.append("file",file);
    			$.ajax({
    				url: ctx+'/summernote/summer_imgae.do',
    				type:'POST',
    				enctype: 'multipart/form-data',
    				data: data,
    				contentType: false,
    				processData: false,
    				success :function(data) {
    					let json=JSON.parse(data);
    					$(el).summernote('editor.insertImage',son["url"]);
    						jsonArray.push(json["url"]);
    						jsonFn(jsonArray);
    				},
    				error:function(e) {
    					console.log(e);
    				}
    			});
    }

    function jsonFn(jsonArray) {
    	console.log(jsonArray);
    }

    //에디터 내용 텍스트 제거
    function f_SkipTags_html(input, allowed) {
    	// 허용할 태그는 다음과 같이 소문자로 넘겨받습니다. (<a><b><c>)
        allowed = (((allowed || "") + "").toLowerCase().match(/<[a-z][a-z0-9]*>/g) || []).join('');
        var tags = /<\/?([a-z][a-z0-9]*)\b[^>]*>/gi,
        commentsAndPhpTags = /<!--[\s\S]*?-->|<\?(?:php)?[\s\S]*?\?>/gi;
        return input.replace(commentsAndPhpTags, '').replace(tags, function ($0, $1) {
            return allowed.indexOf('<' + $1.toLowerCase() + '>') > -1 ? $0 : '';
        });
    }

    //태그와 줄바꿈, 공백을 제거하고 텍스트 글자수만 가져옵니다.
    function setContentsLength(str, index) {
    	    var status = false;
    	    var textCnt = 0; //총 글자수
    	    var maxCnt = 100; //최대 글자수
    	    var editorText = f_SkipTags_html(str); //에디터에서 태그를 삭제하고 내용만 가져오기
    	    editorText = editorText.replace(/\s/gi,""); //줄바꿈 제거
    	    editorText = editorText.replace(/&nbsp;/gi, ""); //공백제거

            textCnt = editorText.length;
    	    if(maxCnt > 0) {
            	if(textCnt > maxCnt) {
                    status = true;
            	}
    	    }

    	    if(status) {
            	var msg = "등록오류 : 글자수는 최대 "+maxCnt+"까지 등록이 가능합니다. / 현재 글자수 : "+textCnt+"자";
            	console.log(msg);
    	    }
    	}


    function getContextPath() {
        return sessionStorage.getItem("contextpath");
    }
    </script>
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
                <a href="${contextPath}/group/board?group_id=${groupInfo.groupVO.grp_id}">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name submenu_select"><span>게시판</span></div>
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

        <!-- 게시판 글쓰기 영역 시작 -->
        <div class="boardWrite sc2_subsection">

          <!-- 메인 상단 타이틀 출력 부분-->
          <div class="sc2_subsection_title">
            <h2 class="sc2_subsection_title_name">게시판</h2>

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
            <!-- <form action="/group/board/addArticle" method="post" name="articleForm" id="articleForm"> -->
              <form action="${contextPath}/group/board/updateArticle" method="post" name="articleForm" id="articleForm">
              <input type="hidden" name="group_id" value="${group.groupVO.grp_id}"/>
              <input type="hidden" name="article_no" value="${article.article_no}"/>
              <!-- 제목 영역 -->
              <div class="articleWritingTitle">
                <!-- 말머리 컨텐츠 확인 필요 -->
                <div class="headTitleArea">
	                <select name="brackets" id="headTitle" class="headTitle">
	                  <option value="">말머리</option>
	                  <c:if test="${user_id==group.groupVO.grp_mng}">
	                  	<option value="notice" <c:if test="${article.brackets eq '공지'}">selected</c:if>>공지</option>
	                  </c:if>
	                  <option value="freeArticle" <c:if test="${article.brackets eq '자유'}">selected</c:if>>자유</option>
	                  <option value="afterMeeting" <c:if test="${article.brackets eq '후기'}">selected</c:if>>후기</option>
	                </select>
                </div>
                <!-- 제목 -->
                <div class="titleArea">
                	<input type="text" name="title" id="writeTitle" class="writeTitle" value="${article.title}"></input>
              	</div>
              </div>
              <!-- 글쓰는 영역 -->
              <div class="editorArea">
                <textarea id="summernote" name="content">
                </textarea>
              </div>
              <!-- 수정 버튼 -->
              <div class="btnRegister">
                <a href="#" role="button" class="button">수정</a>
                <a href="${contextPath}/group/board/viewArticle?group_id=${group.groupVO.grp_id}&article_no=${article.article_no}" role="button" class="buttonCancle">취소</a>
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