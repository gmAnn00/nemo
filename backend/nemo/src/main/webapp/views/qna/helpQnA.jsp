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
<c:set var="qna" value="${articleMap.qna }" />

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
    <title>네모: 고객센터</title>
    <link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
    <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
    <link rel="stylesheet" href="${contextPath}/css/common.css" />
    <link rel="stylesheet" href="${contextPath}/css/submenu.css" />
    <link rel="stylesheet" href="${contextPath}/css/sectionTitle.css" />
    <link rel="stylesheet" href="${contextPath}/css/helpQnA.css" />
    <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
    <script src="https://kit.fontawesome.com/97cbadfe25.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="${contextPath}/js/header.js"></script>
    <script src="${contextPath}/js/helpQnA.js"></script>
  </head>
  <body>
    <!-- header 시작 -->
	<jsp:include page="../header.jsp" flush="true"></jsp:include>
    <!-- header 종료 -->

    <!-- section1 시작 -->
    
   	
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
	                    <i class="fa-solid fa-minus submenu_select"></i>
	                  </div>
	                </a>
	              </li>
	            </ul>
	          </div>
	        </div>
	        <!-- 메뉴바 종료 -->
	        </c:otherwise>
	       </c:choose>
       
       
        <!-- 게시판 영역 시작 -->
        <div class="board sc2_subsection">
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

		  
		  <div class="boardArea">
			  <form action="${contextPath}/viewQna/search" id="search" name="search" method="get">
		          <div class="search">
		            <!-- 검색 부분 필요할 듯 , input 태그에 name, id 넣어야 함 -->
		            <select name="filter" id="searchSelect">
		              <option value="title">제목</option>
		              <option value="content">내용</option>
		              <option value="writer">작성자</option>
		            </select>
		            <input type="text" name="keyword" id="keyword" onkeyup="if(window.event.keyCode==13){submitSearchForm()}"/>
		            <a href="#" role="button" class="button searchBtn" type="submit" onclick="submitSearchForm()">검색</a>
		          </div>
		       </form>
	          
	          <div class="boardListArea">
	            <table class="boardList">
	              <tr class="boardListHead">
	                <th>번호</th>
	                <th>제목</th>
	                <th>작성자</th>
	                <th>작성일</th>
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
				                	<!-- <td>${totArticles-(pageNum-1)*10+articleNum.count}</td> -->
				                	<!-- <td>${totArticles-(section-1)*10-articleNum.index}</td>  -->
				                  <td>${(totArticles-(pageNum-1)*10-articleNum.index)-((section-1)*100)}</td>
				                  <td>
				                    <div class="titleArea">
				                    	<div class="titleInner">
					                        <c:choose>
					                        	<c:when test="${article.level>1}">
					                        		<c:forEach begin="1" end="${article.level-1}" step="1">
									               		<span style="padding-left: 10px"></span>
					                        		</c:forEach>
					                        		└ Re : <a href="${contextPath}/viewQna/QnAView.do?qna_id=${article.qna_id}">${article.title}</a>
					                        	</c:when>
					                        	<c:otherwise>
					                        		<a href="${contextPath}/viewQna/QnAView.do?qna_id=${article.qna_id}">${article.title}</a>
					                        	</c:otherwise>
					                        </c:choose>
					                      </a>
						                  
					                    </div>
				                    </div>
				                  </td>
				                  <td>${article.userVO.nickname}</td>
				                  <td>${article.create_date}</td>
				                  
				                </tr>
			            	</c:forEach>
		                </c:when>
					</c:choose>
	              </tbody>
	            </table>
	            <c:if test="${admin eq 0}">
		            <div class="bottomBtn">
		              <a href="${contextPath}/viewQna/QnAWrite.do" role="button" class="button">글쓰기</a>
		            </div>
	            </c:if>
	            
	            <div class="pagenation">
					<c:if test="${totArticles != 0}">
						<c:forEach var="page" begin="1" end="${endValue}" step="1">
							<c:if test="${section>1 && page==1}">
								<span class="paging prev"><a href="${contextPath}/viewQna?section=${section-1}&pageNum=10">&lt</a></span>
							</c:if>
								
							<c:choose>
								<c:when test="${page==pageNum}">
									<span class="paging currentPage"><a href="${contextPath}/viewQna?section=${section}&pageNum=${page}">${(section-1)*10+page}</a></span>
								</c:when>
								
								<c:otherwise>
									<span class="paging notCurrent"><a href="${contextPath}/viewQna?section=${section}&pageNum=${page}">${(section-1)*10+page}</a></span>
								</c:otherwise>
							</c:choose>
								
							<c:if test="${page==10 and totArticles/100>section}">
								<span class="paing next"><a href="${contextPath}/viewQna?section=${section+1}&pageNum=1">&gt</a></span>
							</c:if>
						</c:forEach>
					</c:if>

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
