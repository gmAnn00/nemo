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
<c:set var="group" value="${articleMap.group }" />
<c:set var="filter" value="${articleMap.filter }" />
<c:set var="keyword" value="${articleMap.keyword}" />


<c:choose>
	<c:when test="${section >totArticles/100 }">
		<c:set var="endValue" value="${(totArticles mod 100)%10==0 ? (totArticles mod 100)/10 : (totArticles mod 100)/10 +1}"/>
	</c:when>
	
	<c:otherwise>
		<c:set var="endValue" value="10" />
	</c:otherwise>
</c:choose>

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
    <link rel="stylesheet" href="${contextPath}/css/board.css" />
    <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
    <script src="https://kit.fontawesome.com/97cbadfe25.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="${contextPath}/js/header.js"></script>
    <script src="${contextPath}/js/board.js"></script>
  </head>
  <body>
    <!-- header 시작 -->
    <!-- 사이드 메뉴시 배경색 조정 -->
    <div class="menu_bg"></div>
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

    <!-- section1 시작 -->
    <div class="section1">
      <div class="group_containter">
        <div class="group_all">
          <div class="group_img">
            <img
              src="${contextPath}/images/free-icon-group-8847475.png"
              alt="group_img"
            />
          </div>
          <div class="group_name">
            <a href="groupMain.html">
              <span>${group.grp_name}</span>
            </a>
          </div>
          <div class="group_info">
            <div class="group_info_category">
              <div class="category_box group_info_category_box">${group.main_name}</div>
              <div class="category_box group_info_category_box">${group.sub_name}</div>
            </div>
            <div class="group_info_member">
              <div class="group_info_title"><span>멤버수</span></div>
              <div class="group_info_contents"><span>${group.mem_no}</span></div>
            </div>
            <div class="group_info_follower">
              <div class="group_info_title"><span>개설일</span></div>
              <div class="group_info_contents"><span>${group.create_date}</span></div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- section1 종료 -->

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
                <a href="${contextPath}/group/board?group_id=${group.grp_id}">
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

        <!-- 게시판 영역 시작 -->
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

		  
		  <div class="boardArea">
		  	 <!-- <form action="${contextPath}/group/board/search?group_id=${param.group_id}" id="search" name="search" method="get"> -->
		     <form action="${contextPath}/group/board/search?group_id=${group.grp_id}" id="search" name="search" method="get">
	          	<div class="search">
	            <!-- 검색 부분 필요할 듯 , input 태그에 name, id 넣어야 함 -->
		            <input type="hidden" name="group_id" value="${group.grp_id}"/>
		            <div class="selectArea">
			            <select name="filter" id="searchOption">
			              <option value="title">제목</option>
			              <option value="content">내용</option>
			              <option value="writer">작성자</option>
			            </select>
		            </div><div class="keywordArea">
		            	<input type="text" name="keyword" id="keyword" onkeyup="if(window.event.keyCode==13){submitSearchForm()}"/>
		            	<a href="#" role="button" class="button searchBtn" type="submit" onclick="submitSearchForm()">검색</a>
	         		</div>
	          	</div>
	          </form>
	          <div class="category">
	          	<a href="${contextPath}/group/board?group_id=${group.grp_id}" class="titleLink">
					<span class="title">전체</span>
				</a>
				<a href="${contextPath}/group/board/search?group_id=${group.grp_id}&filter=brackets&keyword=공지" class="titleLink">
					<span class="title">공지</span>
				</a>
		        <a href="${contextPath}/group/board/search?group_id=${group.grp_id}&filter=brackets&keyword=자유" class="titleLink">
					<span class="title">자유</span>
				</a>
		        <a href="${contextPath}/group/board/search?group_id=${group.grp_id}&filter=brackets&keyword=후기" class="titleLink">
					<span class="title">후기</span>
				</a>
	          </div>
	          <div class="boardListArea">
	            <table class="boardList">
	              <tr class="boardListHead">
	                <th>번호</th>
	                <th>말머리</th>
	                <th>제목</th>
	                <th>작성자</th>
	                <th>작성일</th>
	                <th>조회</th>
	              </tr>
	              <tbody>
	              	<c:choose>
	              		<c:when test="${empty articlesList}">
	              			<tr>
	              				<td colspan="6" class="emptyList">등록된 글이 없습니다.</td>
	              			</tr>
	              		</c:when>
	              		<c:when test="${!empty articlesList}">
	              			<c:forEach var="article" items="${articlesList}" varStatus="articleNum">
				                <tr>
				                	<!--  <td>${totArticles-(section-1)*10-articleNum.index}</td>-->
				                    <td>${(totArticles-(pageNum-1)*10-articleNum.index)-((section-1)*100)}</td>
				                  <td>${article.brackets}</td>
				                  <td class="tdArticle">
				                    <div class="titleArea">
				                    	<div class="titleInner">
				                    		<c:choose>
				                    			<c:when test="${not empty filter}">
							                      <a href="${contextPath}/group/board/viewArticle?group_id=${article.grp_id}&article_no=${article.article_no}" class="titleLink">
							                        <span class="title">${article.title}</span>
							                      </a>
								                  <div class="commentArea">
								                     <a href="${contextPath}/group/board/viewArticle?group_id=${article.grp_id}&article_no=${article.article_no}" class="cmtLink">
								                      <span class="cmt">${article.com_cnt}</span>
								                     </a>
						                     	</c:when>
						                    	<c:otherwise>
						                     	 <a href="${contextPath}/group/board/viewArticle?group_id=${article.grp_id}&article_no=${article.article_no}" class="titleLink">
						                        	<span class="title">${article.title}</span>
						                      	 </a>
							                  	 <div class="commentArea">
							                     	<a href="${contextPath}/group/board/viewArticle?group_id=${article.grp_id}&article_no=${article.article_no}" class="cmtLink">
							                      		<span class="cmt">${article.com_cnt}</span>
							                     	</a>
						                     	</c:otherwise>
						                     </c:choose>
						                   </div>
					                    </div>
				                  </td>
				                  <td>${article.userVO.nickname}</td>
				                  <td>${article.create_date}</td>
				                  <td>${article.view_cnt}</td>
				                </tr>
			            	</c:forEach>
		                </c:when>
					</c:choose>
	              </tbody>
	            </table>
	            
	            <div class="bottomBtn">
	              <a href="${contextPath}/group/board/write?group_id=${group.grp_id}" role="button" class="button">글쓰기</a>
	            </div>
	            
	            <div class="pagenation">
	            	<c:choose>
	            		<c:when test="${(totArticles != 0) and (!empty filter)}">
							<c:forEach var="page" begin="1" end="${endValue}" step="1">
								<c:if test="${section>1 && page==1}">
									<span class="paging prev"><a href="${contextPath}/group/board/search?group_id=${group.grp_id}&filter=${filter}&keyword=${keyword}&section=${section-1}&pageNum=10">&lt</a></span>
								</c:if>
									
								<c:choose>
									<c:when test="${page==pageNum}">
										<span class="paging currentPage"><a href="${contextPath}/group/board/search?group_id=${group.grp_id}&filter=${filter}&keyword=${keyword}&section=${section}&pageNum=${page}">${(section-1)*10+page}</a></span>
									</c:when>
									
									<c:otherwise>
										<span class="paging notCurrent"><a href="${contextPath}/group/board/search?group_id=${group.grp_id}&filter=${filter}&keyword=${keyword}&section=${section}&pageNum=${page}">${(section-1)*10+page}</a></span>
									</c:otherwise>
								</c:choose>
									
								<c:if test="${page==10 and totArticles/100>section}">
									<span class="paing next"><a href="${contextPath}/group/board/search?group_id=${group.grp_id}&filter=${filter}&keyword=${keyword}&section=${section+1}&pageNum=1">&gt</a></span>
								</c:if>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach var="page" begin="1" end="${endValue}" step="1">
								<c:if test="${section>1 && page==1}">
									<span class="paging prev"><a href="${contextPath}/group/board?group_id=${group.grp_id}&section=${section-1}&pageNum=10">&lt</a></span>
								</c:if>
									
								<c:choose>
									<c:when test="${page==pageNum}">
										<span class="paging currentPage"><a href="${contextPath}/group/board?group_id=${group.grp_id}&section=${section}&pageNum=${page}">${(section-1)*10+page}</a></span>
									</c:when>
									
									<c:otherwise>
										<span class="paging notCurrent"><a href="${contextPath}/group/board?group_id=${group.grp_id}&section=${section}&pageNum=${page}">${(section-1)*10+page}</a></span>
									</c:otherwise>
								</c:choose>
									
								<c:if test="${page==10 and totArticles/100>section}">
									<span class="paing next"><a href="${contextPath}/group/board?group_id=${group.grp_id}&section=${section+1}&pageNum=1">&gt</a></span>
								</c:if>
							</c:forEach>
						</c:otherwise>
					</c:choose>
	            </div>
	            <!-- 페이징 영역 끝 -->
            </div>
          </div>
        </div>
        <!-- 게시판 영역 끝 -->
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
