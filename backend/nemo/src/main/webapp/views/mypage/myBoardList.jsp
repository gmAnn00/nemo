<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
<link rel="stylesheet" href="${contextPath}/css/normalize.css" />
<link rel="stylesheet" href="${contextPath}/css/common.css" />
<link rel="stylesheet" href="${contextPath}/css/submenu.css" />
<link rel="stylesheet" href="${contextPath}/css/myBoardList.css" />
<link rel="stylesheet" href="${contextPath}/css/tabmenu.css">
<script src="https://kit.fontawesome.com/bc604c01cc.js"crossorigin="anonymous"></script>
<script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/tabmenu.js"></script>
    
</head>
<body>
	<jsp:include page="../header.jsp" flush="true"></jsp:include>

	<!--1. 전체 부분-->
	<div class="section2">
		<div class="sc2_contents">
			<!-- 메뉴바 시작 -->
			<div class="sc2_menu_contents">
				<div class="sc2_menu">
					<h2 class="sc2_menu_title">프로필</h2>
					<ul class="sc2_menu_list">
						<li><a href="${contextPath}/mypage">
								<div class="sc2_icon_menu">
									<div class="menu_submenu_name">
										<span>프로필</span>
									</div>
									<i class="fa-solid fa-minus menu_angle"></i>
								</div>
						</a></li>
						<li><a href="${contextPath}/mypage/mySchedule">
								<div class="sc2_icon_menu">
									<div class="menu_submenu_name">
										<span>내 일정</span>
									</div>
									<i class="fa-solid fa-angle-right menu_angle"></i>
								</div>
						</a></li>
						<li><a href="${contextPath}/mypage/myGroupList">
								<div class="sc2_icon_menu">
									<div class="menu_submenu_name menu_angle">
										<span>내 소모임</span>
									</div>
									<i class="fa-solid fa-angle-right menu_angle"></i>
								</div>
						</a></li>
						<li><a href="${contextPath}/mypage/myboardList">
								<div class="sc2_icon_menu">
									<div class="menu_submenu_name menu_angle">
										<span>내가 쓴 글·댓글</span>
									</div>
									<i class="fa-solid fa-angle-right submenu_select"></i>
								</div>
						</a></li>
					</ul>
				</div>
			</div>
			<!-- 메뉴바 종료 -->

			<!--3. 컨텐츠부분-->
			<div class="sc2_subsection">
				<div class="sc2_subsection_title">
					<h2 class="sc2_subsection_title_name">내가 쓴 글·댓글</h2>
					<!-- nav 바 시작 -->
					<div class="nav_bar">
					  <a href="${contextPath}/index">
					  	<i class="fa-solid fa-house nav_icon"></i>
					  </a>
					  <i class="fa-solid fa-angle-right nav_icon"></i>
		              <span>마이페이지</span>
		              <i class="fa-solid fa-angle-right nav_icon"></i>
		              <span>내가 쓴 글·댓글</span>
		            </div>
					<!-- nav 바 종료 -->
				</div>
				
				<!-- 내가 쓴 글/댓글 영역 -->
				<div class="sc2_subcontents">
	            <!-- tab menu 시작 -->
	            <div class="tab_container">
	              <div class="tab-slider--nav">
	                <ul class="tab-slider--tabs">
	                  <li class="tab-slider--trigger active" rel="tab1"><span>작성글</span></li>
	                  <li class="tab-slider--trigger" rel="tab2"><span>작성댓글</span></li>
	                </ul>
	              </div>
	              <div class="tab-slider--container">
	                <!-- tab1 시작 -->
	                <div id="tab1" class="tab-slider--body">
	                  <div class="tab_box">
	                    <!-- comment 시작 -->
	                    <div class="user-comment">
	                      <div class="comments-section">
	                        <div class="comment_box">
	                          <div class="comment_title">
	                            <div class="comment_textarea">
	                              <h3 class="comment_group_title">이젠종로학원</h3>
	                              <span class="comment_board_title">글 제목</span>
	                              <span class="comment-time">30 minutes ago</span>
	                            </div>
	                          </div>
	
	                          <div class="comment-post">
	                            <div class="comment-img"><img src="${contextPath}/images/profile-test.jpg" /></div>
	                            <div class="comment-details">
	                              <div class="comment_details_titlebox">
	                                <div class="comment_details_title">
	                                  <span class="comment-author" id="user">도지</span>
	                                </div>
	                                <div class="comment-like-unlike">
	                                  <span><i class="fa-solid fa-pen"></i></span>
	                                  <span><i class="fa-solid fa-xmark"></i></span>
	                                </div>
	                              </div>
	                              <p class="comment-content">
	                                Maecenas eu maximus tellus, vel placerat massa. Nullam neque magna, hendrerit ac lacinia in, consequat nec ipsum.
	                                Vivamus tincidunt fringilla diam et sagittis. Suspendisse tincidunt hendrerit nisi, sit amet aliquet enim ornare at.
	                              </p>
	                            </div>
	                          </div>
	                        </div>
	                      </div>
	                    </div>
	                    <!-- comment 종료 -->
	
	                    <!-- comment 시작 -->
	                    <div class="user-comment">
	                      <div class="comments-section">
	                        <div class="comment_box">
	                          <div class="comment_title">
	                            <div class="comment_textarea">
	                              <h3 class="comment_group_title">이젠종로학원</h3>
	                              <span class="comment_board_title">글 제목</span>
	                              <span class="comment-time">30 minutes ago</span>
	                            </div>
	                          </div>
	
	                          <div class="comment-post">
	                            <div class="comment-img"><img src="${contextPath}/images/profile-test.jpg" /></div>
	                            <div class="comment-details">
	                              <div class="comment_details_titlebox">
	                                <div class="comment_details_title">
	                                  <span class="comment-author" id="user">도지</span>
	                                </div>
	                                <div class="comment-like-unlike">
	                                  <span><i class="fa-solid fa-pen"></i></span>
	                                  <span><i class="fa-solid fa-xmark"></i></span>
	                                </div>
	                              </div>
	                              <p class="comment-content">
	                                Maecenas eu maximus tellus, vel placerat massa. Nullam neque magna, hendrerit ac lacinia in, consequat nec ipsum.
	                                Vivamus tincidunt fringilla diam et sagittis. Suspendisse tincidunt hendrerit nisi, sit amet aliquet enim ornare at.
	                              </p>
	                            </div>
	                          </div>
	                        </div>
	                      </div>
	                    </div>
	                  </div>
	                  <!-- comment 종료 -->
	                </div>
	                <!-- tab1 종료 -->
	
	                <!-- tab2 시작 -->
	                <div id="tab2" class="tab-slider--body">
	                  <div class="tab_box">
	                    <!-- comment 시작 -->
	                    <div class="user-comment">
	                      <div class="comments-section">
	                        <div class="comment_box">
	                          <div class="comment_title">
	                            <div class="comment_textarea">
	                              <h3 class="comment_group_title">이젠종로학원</h3>
	                              <span class="comment_board_title">글 제목</span>
	                              <span class="comment-time">30 minutes ago</span>
	                            </div>
	                          </div>
	
	                          <div class="comment-post">
	                            <div class="comment-img"><img src="${contextPath}/images/profile-test.jpg" /></div>
	                            <div class="comment-details">
	                              <div class="comment_details_titlebox">
	                                <div class="comment_details_title">
	                                  <span class="comment-author" id="user">도지</span>
	                                </div>
	                                <div class="comment-like-unlike">
	                                  <span><i class="fa-solid fa-pen"></i></span>
	                                  <span><i class="fa-solid fa-xmark"></i></span>
	                                </div>
	                              </div>
	                              <p class="comment-content">
	                                Maecenas eu maximus tellus, vel placerat massa. Nullam neque magna, hendrerit ac lacinia in, consequat nec ipsum.
	                                Vivamus tincidunt fringilla diam et sagittis. Suspendisse tincidunt hendrerit nisi, sit amet aliquet enim ornare at.
	                              </p>
	                            </div>
	                          </div>
	
	                          <div class="comment-post-reply">
	                            <div class="comment-img">
	                              <img src="${contextPath}/images/172e317de5420a65269e6c58868117778f324a0b9c48f77dbce3a43bd11ce785.png" />
	                            </div>
	                            <div class="comment-details">
	                              <div class="comment_details_titlebox">
	                                <div class="comment_details_title">
	                                  <span class="comment-author" id="user">곽철이</span>
	                                  <span class="comment-time">10 minutes ago</span>
	                                </div>
	                                <div class="comment-like-unlike">
	                                  <span><i class="fa-solid fa-reply"></i></span>
	                                </div>
	                              </div>
	                              <p class="comment-content">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
	                            </div>
	                          </div>
	                        </div>
	                      </div>
	                    </div>
	                    <!-- comment 종료 -->
	
	                    <!-- comment 시작 -->
	                    <div class="user-comment">
	                      <div class="comments-section">
	                        <div class="comment_box">
	                          <div class="comment_title">
	                            <div class="comment_textarea">
	                              <h3 class="comment_group_title">이젠종로학원</h3>
	                              <span class="comment_board_title">글 제목</span>
	                              <span class="comment-time">30 minutes ago</span>
	                            </div>
	                          </div>
	
	                          <div class="comment-post">
	                            <div class="comment-img"><img src="${contextPath}/images/profile-test.jpg" /></div>
	                            <div class="comment-details">
	                              <div class="comment_details_titlebox">
	                                <div class="comment_details_title">
	                                  <span class="comment-author" id="user">도지</span>
	                                </div>
	                                <div class="comment-like-unlike">
	                                  <span><i class="fa-solid fa-pen"></i></span>
	                                  <span><i class="fa-solid fa-xmark"></i></span>
	                                </div>
	                              </div>
	                              <p class="comment-content">
	                                Maecenas eu maximus tellus, vel placerat massa. Nullam neque magna, hendrerit ac lacinia in, consequat nec ipsum.
	                                Vivamus tincidunt fringilla diam et sagittis. Suspendisse tincidunt hendrerit nisi, sit amet aliquet enim ornare at.
	                              </p>
	                            </div>
	                          </div>
	
	                          <div class="comment-post-reply">
	                            <div class="comment-img">
	                              <img src="${contextPath}/images/172e317de5420a65269e6c58868117778f324a0b9c48f77dbce3a43bd11ce785.png" />
	                            </div>
	                            <div class="comment-details">
	                              <div class="comment_details_titlebox">
	                                <div class="comment_details_title">
	                                  <span class="comment-author" id="user">곽철이</span>
	                                  <span class="comment-time">10 minutes ago</span>
	                                </div>
	                                <div class="comment-like-unlike">
	                                  <span><i class="fa-solid fa-reply"></i></span>
	                                </div>
	                              </div>
	                              <p class="comment-content">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
	                            </div>
	                          </div>
	
	                          <div class="comment-post-reply">
	                            <div class="comment-img">
	                              <img src="${contextPath}/images/172e317de5420a65269e6c58868117778f324a0b9c48f77dbce3a43bd11ce785.png" />
	                            </div>
	                            <div class="comment-details">
	                              <div class="comment_details_titlebox">
	                                <div class="comment_details_title">
	                                  <span class="comment-author" id="user">곽철이</span>
	                                  <span class="comment-time">10 minutes ago</span>
	                                </div>
	                                <div class="comment-like-unlike">
	                                  <span><i class="fa-solid fa-reply"></i></span>
	                                </div>
	                              </div>
	                              <p class="comment-content">
	                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas eu maximus tellus, vel placerat massa. Nullam neque
	                                magna, hendrerit ac lacinia in, consequat nec ipsum. Vivamus tincidunt fringilla diam et sagittis. Suspendisse
	                                tincidunt hendrerit nisi, sit amet aliquet enim ornare at.
	                              </p>
	                            </div>
	                          </div>
	                        </div>
	                      </div>
	                    </div>
	                    <!-- comment 종료 -->
	                  </div>
	                </div>
	                <!-- tab2 종료 -->
	              </div>
	            </div>
	          </div>
				
			  <!-- 내가 쓴 글/댓글 영역 끝 -->
				
			</div>
			<!-- 컨텐츠 종료 -->
		</div>
	</div>

	<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>