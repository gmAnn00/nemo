<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>네모: 마이페이지</title>
    <link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
    <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
    <link rel="stylesheet" href="${contextPath}/css/common.css" />
    <link rel="stylesheet" href="${contextPath}/css/submenu.css" />
    <link rel="stylesheet" href="${contextPath}/css/myGroupList.css" />
    <link
      href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css"
      rel="stylesheet"
    />
    <link
      href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-round.css"
      rel="stylesheet"
    />
    <script
      src="https://kit.fontawesome.com/bc604c01cc.js"
      crossorigin="anonymous"
    ></script>
    <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
    <script src="${contextPath}/js/header.js"></script>
    <script src="${contextPath}/js/myGroupList.js"></script>
  </head>
  <body>
    <jsp:include page="../header.jsp" flush="true"></jsp:include>

    <!-- 컨텐츠 시작 -->
    <div class="section2">
      <div class="sc2_contents">
        <!-- 메뉴바 시작 -->
        <div class="sc2_menu_contents">
          <div class="sc2_menu">
            <h2 class="sc2_menu_title">마이페이지</h2>
            <ul class="sc2_menu_list">
              <li>
                <a href="${contextPath}/mypage">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name"><span>프로필</span></div>
                    <i class="fa-solid fa-angle-right menu_angle"></i>
                  </div>
                </a>
              </li>
              <li>
                <a href="${contextPath}/mypage/mySchedule">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name"><span>내 일정</span></div>
                    <i class="fa-solid fa-angle-right menu_angle"></i>
                  </div>
                </a>
              </li>
              <li>
                <a href="${contextPath}/mypage/myGroupList">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name submenu_select">
                      <span>내 소모임</span>
                    </div>
                    <i class="fa-solid fa-minus submenu_select"></i>
                  </div>
                </a>
              </li>
              <li>
                <a href="${contextPath}/mypage/myBoardList">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name">
                      <span>내가 쓴 글·댓글</span>
                    </div>
                    <i class="fa-solid fa-angle-right menu_angle"></i>
                  </div>
                </a>
              </li>
            </ul>
          </div>
        </div>
        <!-- 메뉴바 종료 -->

        <!-- 메인 컨텐츠 시작 -->
        <div class="sc2_subsection">
          <div class="sc2_subsection_title">
            <h2 class="sc2_subsection_title_name">내 소모임</h2>

            <!-- nav 바 시작 -->
            <div class="nav_bar">
              <a href="${contextPath}/index">
                <i class="fa-solid fa-house nav_icon"></i>
              </a>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>마이페이지</span>
              <i class="fa-solid fa-angle-right nav_icon"></i>
              <span>내 소모임</span>
            </div>
            <!-- nav 바 종료 -->
          </div>

          <div class="sc2_subcontents">
            <!-- 리더 영역 시작 -->
            <div class="sc2_subcontent">
              <div class="sc2_contents_title">
                <div class="sc2_contents_title_name">
                  <span>리더</span>
                </div>               
                <div class="cards">
                  <c:choose>
                    <c:when test="${empty mngGroupList}">
                      <p>만든 소모임이 없습니다.</p>
                    </c:when>
                    <c:when test="${!empty mngGroupList}">
                      <c:forEach var="mngGroup" items="${mngGroupList}" varStatus="loop">
                      <c:set var="idx" value="${mngGroup.grp_addr1.indexOf(' ', mngGroup.grp_addr1.indexOf(' ') + 1)}" />
                      <c:set var="locationM" value="${mngGroup.grp_addr1.substring(0, idx)}"/>
                        <div class="card card--1">
                          <div class="card__info-hover">                            
                            <span></span>
                            <span>바로가기 <i class="fa-solid fa-arrow-right"></i></span>                            
                          </div>
                          <div class="card__img"></div>
                          <a href="${contextPath}/group/groupMain?group_id=${mngGroup.grp_id}" class="card_link">
                            <div class="card__img--hover" style="background-image:url('${contextPath}/groupImages/${mngGroup.grp_id}/${mngGroup.grp_img}')"></div>
                          </a>
                          <div class="card__info">
                            <span class="card__category">${mngGroup.main_name}</span>
                            <span class="card__category">${mngGroup.sub_name}</span>
                            <h3 class="card__title">${mngGroup.grp_name}</h3>
                            
                            <span class="card__by">
                              <i class="fa-solid fa-location-dot"></i>
                              <a href="#" class="card__author">${locationM}</a>
                            </span>
                          </div>                        
                        </div>
                      </c:forEach>
                    </c:when>
                    </c:choose>
                  </div>
                
              </div>
            </div>
            <!-- 리더 영역 종료 -->

            <!-- 회원 영역 시작 -->
            <div class="sc2_subcontent">
              <div class="sc2_contents_title">
                <div class="sc2_contents_title_name">
                  <span>회원</span>
                </div>

                <div class="cards">
                  <c:choose>
                    <c:when test="${empty userGroupList && empty waitGroupList}">
                      <p>가입한 소모임이 없습니다.</p>
                    </c:when>
                  
                    <c:when test="${!empty userGroupList}">
                      <c:forEach var="userGroup" items="${userGroupList}" varStatus="loop">
                      <c:set var="idx" value="${userGroup.grp_addr1.indexOf(' ', userGroup.grp_addr1.indexOf(' ') + 1)}" />
                      <c:set var="locationU" value="${userGroup.grp_addr1.substring(0, idx)}"/>
                        <div class="card card--1">
                          <div class="card__info-hover">
                            <a href="${contextPath}/group/member/delete?group_id=${userGroup.grp_id}"><span class="leave">탈퇴하기</span></a>
                            <a href="${contextPath}/group/groupMain?group_id=${userGroup.grp_id}" class="card_link">
                            <span>바로가기</span>	<i class="fa-solid fa-arrow-right"></i></a>
                          </div>
                          <div class="card__img"></div>
                          <div class="card__img--hover" style="background-image:url('${contextPath}/groupImages/${userGroup.grp_id}/${userGroup.grp_img}')">
                          	<div class="link">
	                          	<a href="${contextPath}/group/member/delete?group_id=${userGroup.grp_id}"><span class="leave"> &nbsp; &nbsp; &nbsp; &nbsp; </span></a>
	                            <a href="${contextPath}/group/groupMain?group_id=${userGroup.grp_id}" class="card_link">
	                              <span> &nbsp; &nbsp; &nbsp; &nbsp; </span>
	                          	</a>
                          	</div>
                          </div>
                          
                          <div class="card__info">
	                        <a href="${contextPath}/group/groupMain?group_id=${userGroup.grp_id}" class="card_link">
	                          <span class="card__category">${userGroup.main_name}</span>
	                          <span class="card__category">${userGroup.sub_name}</span>
	                          <h3 class="card__title">${userGroup.grp_name}</h3>
	                        </a>
                            <span class="card__by"><i class="fa-solid fa-location-dot"></i>
                              <a href="#" class="card__author">${locationU}</a>
                            </span>
                          </div>
                         
                        </div>
                      </c:forEach>
                    </c:when>
                    </c:choose>
                    
                    <!-- 가입 대기중인 소모임 -->
                    <c:choose>
                    <c:when test="${!empty waitGroupList}">
                      <c:forEach var="waitGroup" items="${waitGroupList}" varStatus="loop">
                      <c:set var="idx" value="${waitGroup.grp_addr1.indexOf(' ', waitGroup.grp_addr1.indexOf(' ') + 1)}" />
                      <c:set var="locationW" value="${waitGroup.grp_addr1.substring(0, idx)}"/>                      	
                        <div class="card card--${waitGroup.grp_id}">
                          <div class="card__info-hover">
                            <a href="${contextPath}/group/member/delete?group_id=${waitGroup.grp_id}"><span class="leave">취소하기</span></a>
                            <a href="${contextPath}/group/groupMain?group_id=${waitGroup.grp_id}" class="card_link">
                            <span>바로가기</span>	<i class="fa-solid fa-arrow-right"></i></a>
                          </div>
                          <div class="card__img"></div>   
                          <div class="card__img--hover" style="background-image:url('${contextPath}/groupImages/${waitGroup.grp_id}/${waitGroup.grp_img}')">
                        	<div class="link">
                        		<div class="wait_grp">가입 대기중</div>
                         		<a href="${contextPath}/group/member/cancel?group_id=${waitGroup.grp_id}"><span class="leave"> &nbsp; &nbsp; &nbsp; &nbsp; </span></a>
	                            <a href="${contextPath}/group/groupInfo?group_id=${waitGroup.grp_id}" class="card_link">
	                              <span> &nbsp; &nbsp; &nbsp; &nbsp; </span>
	                         	</a>
                        	</div>
                          </div>                          
                         
                          <div class="card__info">
                             <a href="${contextPath}/group/groupMain?group_id=${waitGroup.grp_id}" class="card_link">
	                          <span class="card__category">${waitGroup.main_name}</span>
	                          <span class="card__category">${waitGroup.sub_name}</span>
	                          <h3 class="card__title">${waitGroup.grp_name}</h3>
	                        </a>
                            <span class="card__by">
                              <i class="fa-solid fa-location-dot"></i>
                              <a href="#" class="card__author">${locationW}</a>
                            </span>
                          </div>
                        </div>
                      </c:forEach>
                    </c:when>
                    </c:choose>
                  </div>               
              </div>
            </div>
            <!-- 회원 영역 종료 -->

            <!-- 찜 영역 시작 -->
            <div class="sc2_subcontent">
              <div class="sc2_contents_title">
                <div class="sc2_contents_title_name">
                  <span>찜</span>
                </div>
                <div class="cards">
                
                  <c:choose>
                    <c:when test="${empty bookmarkGroupList}">
                      <p>찜한 소모임이 없습니다.</p>
                    </c:when>
                  
                    <c:when test="${!empty bookmarkGroupList}">
                      <c:forEach var="bookmarkGroup" items="${bookmarkGroupList}" varStatus="loop">
                      <c:set var="idx" value="${bookmarkGroup.grp_addr1.indexOf(' ', bookmarkGroup.grp_addr1.indexOf(' ') + 1)}" />
                      <c:set var="locationB" value="${bookmarkGroup.grp_addr1.substring(0, idx)}"/>                      
		                  <div class="card card--1">		                    
		                    <div class="card__info-hover">
		                      <span></span>
		                      <span>바로가기 <i class="fa-solid fa-arrow-right"></i></span>		                    
		                    </div>
		                    <div class="card__img"></div>
		                    <a href="${contextPath}/group/groupInfo?group_id=${bookmarkGroup.grp_id}" class="card_link">
		                      <div class="card__img--hover" style="background-image:url('${contextPath}/groupImages/${bookmarkGroup.grp_id}/${bookmarkGroup.grp_img}')"></div>
		                    </a>
		                    <div class="card__info">
		                      <span class="card__category">${bookmarkGroup.main_name}</span>
		                      <span class="card__category">${bookmarkGroup.sub_name}</span>
		                      <h3 class="card__title">${bookmarkGroup.grp_name}</h3>
		                      <span class="card__by"
		                        ><i class="fa-solid fa-location-dot"></i>
		                        <a href="#" class="card__author">${locationB}</a>
		                          <button type="button" class="grpLikeBtn" onchli title="네모찜하기">
			                        <c:if test="${isBookmark}">
				
										<span class="grpLike grpLike${bookmarkGroup.grp_id} on" onclick="bookmarkClick('${user_id}', '${bookmarkGroup.grp_id}')"> <svg viewBox="0 0 24 24">
				                                <use xlink:href="#heart" />
				                                <!-- filled heart -->
				                                <use xlink:href="#heart" />
				                                <!-- outline heart -->
				                            </svg> <!-- reference path for both outline, and filled, hearts -->
											<svg class="hide" viewBox="0 0 24 24">
				                                <defs>
				                                    <path id="heart"
													d="M12 4.435c-1.989-5.399-12-4.597-12 3.568 0 4.068 3.06 9.481 12 14.997 8.94-5.516 12-10.929 12-14.997 0-8.118-10-8.999-12-3.568z" />
				                                </defs>
				                            </svg>
										</span>
										<span class="hidden">찜하기</span>
									
									</c:if>
									
									<c:if test="${!isBookmark}">
				
										<span class="groupLike grpLike${bookmarkGroup.grp_id}" onclick="bookmarkClick('${user_id}', '${bookmarkGroup.grp_id}')"> <svg viewBox="0 0 24 24">
				                                <use xlink:href="#heart" />
				                                <!-- filled heart -->
				                                <use xlink:href="#heart" />
				                                <!-- outline heart -->
				                            </svg> <!-- reference path for both outline, and filled, hearts -->
											<svg class="hide" viewBox="0 0 24 24">
				                                <defs>
				                                    <path id="heart"
													d="M12 4.435c-1.989-5.399-12-4.597-12 3.568 0 4.068 3.06 9.481 12 14.997 8.94-5.516 12-10.929 12-14.997 0-8.118-10-8.999-12-3.568z" />
				                                </defs>
				                            </svg>
										</span>
										<span class="hidden">찜하기</span>
				
									</c:if>
									
			                      </button>
		                      </span>
		                    </div>
		                    <input type="hidden" id="user_id_hidden" name="user_id_hidden" value="${user_id}"/>
                            <input type="hidden" id="grp_id_hidden" name="grp_id_hidden" value="${bookmarkGroup.grp_id}"/>
						    <input type="hidden" id="isBookmark_hidden" name="isBookmark_hidden" value="${isBookmark}"/>
		                  </div>
						</c:forEach>
                    </c:when>
                  </c:choose>
                   
                </div>
              </div>
            </div>
            <!-- 찜 영역 종료 -->
          </div>
        </div>
        <!-- 메인 컨텐츠 종료 -->
      </div>
    </div>
    <!-- 컨텐츠 종료 -->

    <jsp:include page="../footer.jsp" flush="true"></jsp:include>
  </body>
</html>