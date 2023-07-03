<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    import="java.util.*, nemo.*"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:set var="articlesList" value="${articleMap.articlesList}"/>
<c:set var="totArticles" value="${articleMap.totArticles}" />
<c:set var="section" value="${articleMap.section }" />
<c:set var="pageNum" value="${articleMap.pageNum }" />
<c:set var="filter" value="${articleMap.filter }" />
<c:set var="keyword" value="${articleMap.keyword}" />
<c:set var="group" value="${groupInfo}" />
<c:set var="noticeList" value="${articleMap.noticeList}"/>


<c:choose>
	<c:when test="${section >totArticles/100 }">
		<c:set var="endValue" value="${(totArticles mod 100)%10==0 ? (totArticles mod 100)/10 : (totArticles mod 100)/10 +1}"/>
	</c:when>
	
	<c:otherwise>
		<c:set var="endValue" value="10" />
	</c:otherwise>
</c:choose>

<c:set var="isMng" value="false" />
<c:forEach var="elem" items="${grpMngList}" >
	<c:if test="${elem eq param.group_id}">
		<c:set var="isMng" value="true" />
	</c:if>
</c:forEach>

<% request.setCharacterEncoding("utf-8"); %>

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
    <link rel="stylesheet" href="${contextPath}/css/board.css" />
    <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
    <script src="https://kit.fontawesome.com/97cbadfe25.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="${contextPath}/js/header.js"></script>
    <script src="${contextPath}/js/board.js"></script>
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
               <c:choose>
					<c:when test="${isMng == true }">
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
	                              <div class="menu_submenu_name submenu_select"><span>게시판</span></div>
	                              <i class="fa-solid fa-minus submenu_select"></i>
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
					</c:when>
				
					<c:otherwise>
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
		                              <div class="menu_submenu_name submenu_select"><span>게시판</span></div>
		                              <i class="fa-solid fa-minus submenu_select"></i>
		                          </div>
		                      </a>
		                  </li>
		                  <li>
		                      <a href="${contextPath}/group/member?group_id=${param.group_id}">
		                          <div class="sc2_icon_menu">
		                              <div class="menu_submenu_name"><span>멤버</span></div>
		                              <i class="fa-solid fa-angle-right menu_angle"></i>
		                          </div>
		                      </a>
		                  </li>
					</c:otherwise>

                </c:choose>
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
		     <form action="${contextPath}/group/board/search?group_id=${group.groupVO.grp_id}" id="search" name="search" method="get">
	          	<div class="search">
	            <!-- 검색 부분 필요할 듯 , input 태그에 name, id 넣어야 함 -->
		            <input type="hidden" name="group_id" value="${group.groupVO.grp_id}"/>
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
	          	<a href="${contextPath}/group/board?group_id=${group.groupVO.grp_id}" class="categoryLink">
					<span class="title">전체</span>
				</a>
				<a href="${contextPath}/group/board/search?group_id=${group.groupVO.grp_id}&filter=brackets&keyword=공지" class="categoryLink">
					<span class="title brackets">공지</span>
					<div class="notice bracketFilter"></div>
				</a>
		        <a href="${contextPath}/group/board/search?group_id=${group.groupVO.grp_id}&filter=brackets&keyword=자유" class="categoryLink">
					<span class="title brackets">자유	</span>
					<div class="free bracketFilter"></div>
				</a>
		        <a href="${contextPath}/group/board/search?group_id=${group.groupVO.grp_id}&filter=brackets&keyword=후기" class="categoryLink">
					<span class="title brackets">후기	</span>
					<div class="review bracketFilter"></div>
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
	              			<c:if test="${not empty noticeList}">
	              				<c:forEach var="notice" items="${noticeList}" varStatus="noticeNum">
	              					<tr class="noticeTr">
	              						<!-- <td>${fn:length(noticeList)-noticeNum.index}</td> -->
	              						<td>${noticeNum.count}</td>
	              						<td>${notice.brackets}</td>
						                <td class="tdArticle">
					                    	<div class="titleArea">
						                    	<div class="titleInner">
									            	<a href="${contextPath}/group/board/viewArticle?group_id=${group.groupVO.grp_id}&article_no=${notice.article_no}" class="titleLink">
									                	<span class="title">${notice.title}</span>
									                 </a>
										             <div class="commentArea">
										             	<a href="${contextPath}/group/board/viewArticle?group_id=${group.groupVO.grp_id}&article_no=${notice.article_no}" class="cmtLink">
										                	<span class="cmt">${notice.com_cnt}</span>
										                </a>
										             </div>
								                 </div>
						                    </div>
						                  </td>
						                  <td>${notice.userVO.nickname}</td>
				                  		  <td>${notice.create_date}</td>
				                  		  <td>${notice.view_cnt}</td>
	              					</tr>
	              				</c:forEach>
	              			</c:if>
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
							                      <a href="${contextPath}/group/board/viewArticle?group_id=${group.groupVO.grp_id}&filter=${filter}&keyword=${keyword }&article_no=${article.article_no}" class="titleLink">
							                        <span class="title">${article.title}</span>
							                      </a>
								                  <div class="commentArea">
								                     <a href="${contextPath}/group/board/viewArticle?group_id=${group.groupVO.grp_id}&filter=${filter}&keyword=${keyword }&article_no=${article.article_no}" class="cmtLink">
								                      <span class="cmt">${article.com_cnt}</span>
								                     </a>
								                  </div>
						                     	</c:when>
						                    	<c:otherwise>
						                     	 <a href="${contextPath}/group/board/viewArticle?group_id=${group.groupVO.grp_id}&article_no=${article.article_no}" class="titleLink">
						                        	<span class="title">${article.title}</span>
						                      	 </a>
							                  	 <div class="commentArea">
							                     	<a href="${contextPath}/group/board/viewArticle?group_id=${group.groupVO.grp_id}&article_no=${article.article_no}" class="cmtLink">
							                      		<span class="cmt">${article.com_cnt}</span>
							                     	</a>
							                     </div>
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
	              <a href="${contextPath}/group/board/write?group_id=${group.groupVO.grp_id}" role="button" class="button">글쓰기</a>
	            </div>
	            
	            <div class="pagenation">
	            	<c:choose>
	            		<c:when test="${(totArticles != 0) and (!empty filter)}">
							<c:forEach var="page" begin="1" end="${endValue}" step="1">
								<c:if test="${section>1 && page==1}">
									<span class="paging prev"><a href="${contextPath}/group/board/search?group_id=${group.groupVO.grp_id}&filter=${filter}&keyword=${keyword}&section=${section-1}&pageNum=10">&lt</a></span>
								</c:if>
									
								<c:choose>
									<c:when test="${page==pageNum}">
										<span class="paging currentPage"><a href="${contextPath}/group/board/search?group_id=${group.groupVO.grp_id}&filter=${filter}&keyword=${keyword}&section=${section}&pageNum=${page}">${(section-1)*10+page}</a></span>
									</c:when>
									
									<c:otherwise>
										<span class="paging notCurrent"><a href="${contextPath}/group/board/search?group_id=${group.groupVO.grp_id}&filter=${filter}&keyword=${keyword}&section=${section}&pageNum=${page}">${(section-1)*10+page}</a></span>
									</c:otherwise>
								</c:choose>
									
								<c:if test="${page==10 and totArticles/100>section}">
									<span class="paing next"><a href="${contextPath}/group/board/search?group_id=${group.groupVO.grp_id}&filter=${filter}&keyword=${keyword}&section=${section+1}&pageNum=1">&gt</a></span>
								</c:if>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach var="page" begin="1" end="${endValue}" step="1">
								<c:if test="${section>1 && page==1}">
									<span class="paging prev"><a href="${contextPath}/group/board?group_id=${group.groupVO.grp_id}&section=${section-1}&pageNum=10">&lt</a></span>
								</c:if>
									
								<c:choose>
									<c:when test="${page==pageNum}">
										<span class="paging currentPage"><a href="${contextPath}/group/board?group_id=${group.groupVO.grp_id}&section=${section}&pageNum=${page}">${(section-1)*10+page}</a></span>
									</c:when>
									
									<c:otherwise>
										<span class="paging notCurrent"><a href="${contextPath}/group/board?group_id=${group.groupVO.grp_id}&section=${section}&pageNum=${page}">${(section-1)*10+page}</a></span>
									</c:otherwise>
								</c:choose>
									
								<c:if test="${page==10 and totArticles/100>section}">
									<span class="paing next"><a href="${contextPath}/group/board?group_id=${group.groupVO.grp_id}&section=${section+1}&pageNum=1">&gt</a></span>
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

	<jsp:include page="../footer.jsp" flush="true"></jsp:include>
  </body>
</html>
