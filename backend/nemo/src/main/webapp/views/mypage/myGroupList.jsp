<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false"%> <%@taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>내 소모임</title>
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
                <a href="${contextPath}/mypage/">
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
                <a href="${contextPath}/mypage/myboardList">
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
              <a href="index.html">
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
                      <c:forEach
                        var="mngGroup"
                        items="${mngGroupList}"
                        varStatus="loop"
                      >
                        <div class="card card--1">
                          <div class="card__info-hover">
                            <span>바로가기</span>
                            <i class="fa-solid fa-arrow-right"></i>
                          </div>
                          <div class="card__img"></div>
                          <a href="#" class="card_link">
                            <div class="card__img--hover"></div>
                          </a>
                          <div class="card__info">
                            <span class="card__category">대분류</span>
                            <span class="card__category">소분류</span>
                            <h3 class="card__title">이젠종로학원</h3>
                            <span class="card__by"
                              ><i class="fa-solid fa-location-dot"></i>
                              <a href="#" class="card__author"
                                >서울 종로구</a
                              ></span
                            >
                          </div>
                        </div>
                      </c:forEach>
                    </c:when>
                  </div>
                </c:choose>
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
                    <c:when test="${empty userGroupList}">
                      <p>가입한 소모임이 없습니다.</p>
                    </c:when>
                  <div class="cards">
                    <c:when test="${!empty userGroupList}">
                      <c:forEach
                        var="userGroup"
                        items="${userGroupList}"
                        varStatus="loop"
                      >
                        <div class="card card--1">
                          <div class="card__info-hover">
                            <span>바로가기</span>
                            <i class="fa-solid fa-arrow-right"></i>
                          </div>
                          <div class="card__img"></div>
                          <a href="#" class="card_link">
                            <div class="card__img--hover"></div>
                          </a>
                          <div class="card__info">
                            <span class="card__category">${userGroupList.main_name}</span>
                            <span class="card__category">${userGroupList.sub_name}</span>
                            <h3 class="card__title">${userGroupList.grp_name}</h3>
                            <span class="card__by"
                              ><i class="fa-solid fa-location-dot"></i>
                              <a href="#" class="card__author">${userGroupList.grp_addr1}</a></span
                            >
                          </div>
                        </div>
                      </c:forEach>
                    </c:when>
                  </div>
                </c:choose>
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
                  <div class="card card--1">
                    <div class="card__info-hover">
                      <span>바로가기</span>
                      <i class="fa-solid fa-arrow-right"></i>
                    </div>
                    <div class="card__img"></div>
                    <a href="#" class="card_link">
                      <div class="card__img--hover"></div>
                    </a>
                    <div class="card__info">
                      <span class="card__category">대분류</span>
                      <span class="card__category">소분류</span>
                      <h3 class="card__title">이젠종로학원</h3>
                      <span class="card__by"
                        ><i class="fa-solid fa-location-dot"></i>
                        <a href="#" class="card__author">서울 종로구</a></span
                      >
                    </div>
                  </div>

                  <div class="card card--2">
                    <div class="card__info-hover">
                      <span>바로가기</span>
                      <i class="fa-solid fa-arrow-right"></i>
                    </div>
                    <div class="card__img"></div>
                    <a href="#" class="card_link">
                      <div class="card__img--hover"></div>
                    </a>
                    <div class="card__info">
                      <span class="card__category">대분류</span>
                      <span class="card__category">소분류</span>
                      <h3 class="card__title">이젠종로학원</h3>
                      <span class="card__by"
                        ><i class="fa-solid fa-location-dot"></i>
                        <a href="#" class="card__author">서울 종로구</a></span
                      >
                    </div>
                  </div>

                  <div class="card card--3">
                    <div class="card__info-hover">
                      <span>바로가기</span>
                      <i class="fa-solid fa-arrow-right"></i>
                    </div>
                    <div class="card__img"></div>
                    <a href="#" class="card_link">
                      <div class="card__img--hover"></div>
                    </a>
                    <div class="card__info">
                      <span class="card__category">대분류</span>
                      <span class="card__category">소분류</span>
                      <h3 class="card__title">이젠종로학원</h3>
                      <span class="card__by"
                        ><i class="fa-solid fa-location-dot"></i>
                        <a href="#" class="card__author">서울 종로구</a></span
                      >
                    </div>
                  </div>
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
