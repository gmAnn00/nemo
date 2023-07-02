<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@
	page import="java.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>일정</title>
<link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
<link rel="stylesheet" href="${contextPath}/css/normalize.css" />
<link rel="stylesheet" href="${contextPath}/css/common.css" />
<link rel="stylesheet" href="${contextPath}/css/submenu.css" />
<link rel="stylesheet" href="${contextPath}/css/sectionTitle.css" />
<link rel="stylesheet" href="${contextPath}/css/schedule.css" />
<script src="https://kit.fontawesome.com/3d4603cd1d.js"
	crossorigin="anonymous"></script>
<script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/js/header.js"></script>


<script>
    var contextPath = "${pageContext.request.contextPath}";
</script>

<script src="${contextPath}/js/schedule.js"></script>

<!--<script>
    	const ctx = ${contextPath};
    </script> -->

</head>
<body>
	<!-- header 시작 -->
	<jsp:include page="../header.jsp" flush="true"></jsp:include>
	<!-- header 종료 -->

	<!-- section1 시작 -->
	<div class="section1">
		<div class="group_containter">
			<div class="group_all">
				<div class="group_img">
					<img src="${contextPath}/images/free-icon-group-8847475.png"
						alt="group_img">
				</div>
				<div class="group_name">
					<a href="${contextPath}/groupMain.html"> <span>이젠종로학원</span>
					</a>
				</div>
				<div class="group_info">
					<div class="group_info_category">
						<div class="category_box group_info_category_box">sports</div>
						<div class="category_box group_info_category_box">game</div>
					</div>
					<div class="group_info_member">
						<div class="group_info_title">
							<span>멤버수</span>
						</div>
						<div class="group_info_contents">
							<span>7</span>
						</div>
					</div>
					<div class="group_info_follower">
						<div class="group_info_title">
							<span>개설일</span>
						</div>
						<div class="group_info_contents">
							<span>2023. 05. 19.</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- section1 종료 -->

	<!-- 콘텐츠 영역 -->
	<!-- <div id="contentsArea"> -->
	<div class="section2">
		<div class="sc2_contents">
			<!-- 메뉴바 시작 -->
			<div class="sc2_menu_contents">
				<div class="sc2_menu">
					<h2 class="sc2_menu_title">
						<a href="${contextPath}/groupMain.html">나의 모임</a>
					</h2>
					<ul class="sc2_menu_list">
						<li><a href="${contextPath}/schedule">
								<div class="sc2_icon_menu">
									<div class="menu_submenu_name submenu_select">
										<span>일정</span>
									</div>
									<i class="fa-solid fa-minus submenu_select"></i>
								</div>
						</a></li>
						<li><a href="${contextPath}/board/board">
								<div class="sc2_icon_menu">
									<div class="menu_submenu_name">
										<span>게시판</span>
									</div>
									<i class="fa-solid fa-angle-right menu_angle"></i>
								</div>
						</a></li>
						<li><a href="${contextPath}/myGroupMember.html">
								<div class="sc2_icon_menu">
									<div class="menu_submenu_name">
										<span>멤버</span>
									</div>
									<i class="fa-solid fa-angle-right menu_angle"></i>
								</div>
						</a></li>
						<li><a href="${contextPath}/groupSetting.html">
								<div class="sc2_icon_menu">
									<div class="menu_submenu_name">
										<span>소모임관리</span>
									</div>
									<i class="fa-solid fa-angle-right menu_angle"></i>
								</div>
						</a></li>
					</ul>
				</div>
			</div>
			<!-- 메뉴바 종료 -->

			<!-- 일정 등록, 확인 영역 시작 -->
			<div class="schedule sc2_subsection">
				<div class="sc2_subsection_title">
					<h2 class="sc2_subsection_title_name">일정</h2>
					<!-- nav 바 시작 -->
					<div class="nav_bar">
						<a href="${contextPath}/index"> <i
							class="fa-solid fa-house nav_icon"></i>
						</a> <i class="fa-solid fa-angle-right nav_icon"></i> <span>나의
							모임</span> <i class="fa-solid fa-angle-right nav_icon"></i> <span>일정</span>
					</div>
					<!-- nav 바 종료 -->
				</div>

				<div class="calendarSchedule clearfixed">
					<!-- 달력 및 간단일정 영역 -->
					<!-- 달력부분 -->
					<div class="scheduleCalendar">
						<div id="myScheduleCalendarArea"></div>
					</div>
					<div class="scheduleNP">
						<div class="nextSchedule">
							<strong>예정된 모임일정</strong>
							<div class="nextList">
								<table>
									<tr>
										<th>모임날짜</th>
										<th>모임제목</th>
										<th>모임장소</th>
									</tr>
									<tr>
										<td>2023-05-10</td>
										<td>html,css끝내자</td>
										<td>이젠종로302호</td>
									</tr>
									<tr>
										<td>2023-05-10</td>
										<td>html,css끝내자</td>
										<td>이젠종로302호</td>
									</tr>
									<tr>
										<td>2023-05-10</td>
										<td>html,css끝내자</td>
										<td>이젠종로302호</td>
									</tr>
								</table>
							</div>
						</div>
						<div class="pastSchedule">
							<strong>지난 모임일정</strong>
							<div class="pastList">
								<table>
									<tr>
										<th>모임날짜</th>
										<th>모임제목</th>
										<th>모임장소</th>
									</tr>
									<tr>
										<td>2023-04-27</td>
										<td>html,css끝내자</td>
										<td>이젠종로302호</td>
									</tr>
									<tr>
										<td>2023-04-27</td>
										<td>html,css끝내자</td>
										<td>이젠종로302호</td>
									</tr>
									<tr>
										<td>2023-04-27</td>
										<td>html,css끝내자</td>
										<td>이젠종로302호</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- 상세일정부분 3가지(빈일정, 조회, 등록) 겹쳐져 있는 부분 -->

				<div class="scheduleArea clearfixed">
					<!-- 빈 일정 (등록버튼만 있음) -->
					<!-- <div class="scheduleNone">
			              <h3>빈 일정</h3>
			              <div class="topBtn">
			                <a href="#" role="button" class="button btnResi">등록</a>
			              </div>
			            </div> -->
					<!-- 일정 상세보기 -->
					<form name = "frmSchedule"  action="">
						<div class="scheduleDetailArea clearfixed">
							<div class="scheduleDetail">
								<div class="detailTop">
									<h3>일정 상세보기</h3>
								</div>
								<div id="schedule_Title">
									<p>
										<input type="text" id="scheduleTitle" name="scheduleTitle_new" class="scheduleTitle" value="">
									</p>
								</div>
								<div class="contentLocationMap">
									<div class="content">
										<div class="dateTime">
											<!-- <input type="datetime-local" name="schedule" id="schedule"/>  -->
											<!-- <p>
												<input type="text"id="schedule" disabled>
											</p>
											<br>
											<p>
												<input type="text" id="sche_time" disabled>
											</p> -->
											<br>
											<input id="sche_dateTime" type="datetime-local" name="sche_dateTime_new" value="">
											<input type="datetime-local" id="sche_dateTime_old" value="" name="sche_dateTime_old" style="display:none"/>
											<script>
											var currentDate2 = new Date().toISOString().slice(0, 16);
											document.getElementById("sche_dateTime").min = currentDate2;
											</script>
										</div>
										<div class="contentDetail">
											<textarea id="sche_cont" name="sche_cont_new" class="contentDetailText" rows="10"></textarea>
										</div>
									</div>
									
									<input type="hidden" id="detailAddr2" name="location_new" value="" required>
									
									<!-- 모임 위치 -->
									<div class="locationMap" id="newMap">
										<h4>모임 위치</h4>
										<div class="map" id="map">
											<div id="menu_wrap" class="bg_white menu_wrap_class">
												<div class="option">
													<div>
														<form>
															키워드 : <input type="text" id="keyword" value=""
																size="15" placeholder="만남 장소">
															<button type="button"
																onclick="fn_mod_schedule(); return false;">검색하기</button>
														</form>
													</div>
												</div>
												<hr>
												<ul id="placesList"></ul>
												<div id="pagination"></div>
											</div>
										
										</div>
										<br>
										<div id="clickLatlng"></div>
										<p style="margin-top: 12px">
											<em class="link"> <a href="javascript:void(0);"
												onclick="window.open('http://fiy.daum.net/fiy/map/CsGeneral.daum', '_blank', 'width=981, height=650')">
													혹시 주소 결과가 잘못 나오는 경우에는 여기에 제보해주세요. </a>
											</em>
										</p>

										<script type="text/javascript"
											src="//dapi.kakao.com/v2/maps/sdk.js?appkey=20b89d26720633d863b9bff60fd0e841&libraries=services"></script>

									</div>
								</div>
								<div class="participant clearfixed">
									<h4>참석자</h4>
									<div class="partMember">
										<ul>
											<li><img src="${contextPath}/images/temp.png"
												alt="프로필사진" width="40px" /> <span>닉네임</span></li>
											<li><img src="${contextPath}/images/temp.png"
												alt="프로필사진" width="40px" /> <span>닉네임</span></li>
											<li><img src="${contextPath}/images/temp.png"
												alt="프로필사진" width="40px" /> <span>닉네임</span></li>
										</ul>
									</div>

									<div class="partBtn clearfixed">
										<!-- 한번 더 누르면 참석취소 -->
										<!-- <a href="#" role="button" class="button btnPart">참석</a> -->
										<!-- <a href="#" role="button" class="button btnPartcan">참석취소</a> -->
										<a type="button" id="joinSchedule" onclick="joinSchedule(${p<aram.group_id})" role="button" class="button btnPart">참석</a>
									</div>
								</div>
								<div class="editBtn" id="button_modify">
									<button type="button" id="modScheduleBtn" class="button btnEdit" onclick="fn_modify_schedule(this.form, ${param.group_id})">수정반영하기</button>
									<input type="button" value="취소" onclick="toSchedule(frmSchedule, ${param.group_id})" class="button buttonCancle">
								</div>
								
								
								<div id="editButton" class="editBtn">
									<button type="button" id="delScheduleBtn" class="buttonCancle btnDel" onclick="delSchedule(${param.group_id})">삭제</button>
									<input type="button" value="수정" onclick="fn_enable(this.form);" class="button btnEdit">
								</div>
							</div>

						</div>
					</form>

					<!-- 상세 일정 등록하기 -->
					<form
						action="${contextPath}/group/schedule/addSchedule?group_id=${param.group_id}"
						method="post" onkeydown="return handleOuterFormKeyDown(event);">
						<div class="scheduleEditArea clearfixed">
							<div class="scheduleEdit">
								<div class="detailTop">
									<h3>상세 일정 등록하기</h3>
								</div>
								<div class="scheduleTitle">
									<textarea name="sche_title" placeholder="모임일정 제목을 입력하세요"
										class="scheduleTitleText" rows="1" required></textarea>
								</div>
								<div class="contentLocationMap">
									<div class="content">
										<div class="dateTime">
											<input type="datetime-local" name="schedule"
												id="myDateTimeInput" required/>
										</div>
										<script>
											var currentDate = new Date()
													.toISOString().slice(0, 16);
											document
													.getElementById("myDateTimeInput").min = currentDate;
										</script>
										<div class="contentDetail">
											<textarea name="sche_cont" placeholder="모임일정 내용을 입력하세요"
												class="contentDetailText" rows="10" required></textarea>
										</div>
									</div>

									<!-- 모임 위치 설정 -->
									<div class="locationMap">
										<h4>모임 위치</h4>
										<div class="hAddr">
											<span class="title">지도중심기준 행정동 주소정보</span> <span
												id="centerAddr"></span>
										</div>
										<div class="map" id="map2">

											<!-- 모임 위치 지번 주소 -->
											<input type="hidden" id="detailAddr" name="location" value="" required>

											<div id="menu_wrap2" class="bg_white">
												<div class="option">
													<div>
														<form>
															키워드 : <input type="text" value="종로구" id="keyword2"
																size="15" placeholder="만남 장소">
															<button type="button"
																onclick="createKakaoMap2(); return false;">검색하기</button>
														</form>
													</div>
												</div>
												<hr>
												<ul id="placesList2"></ul>
												<div id="pagination2"></div>
											</div>

											<script type="text/javascript"
												src="//dapi.kakao.com/v2/maps/sdk.js?appkey=20b89d26720633d863b9bff60fd0e841&libraries=services"></script>
											
										</div>
									</div>
								</div>
								<div class="editBtn">
									<a href="#" role="button" class="buttonCancle">취소</a>
									<button type="submit" class="button btnResi">등록</button>
								</div>
							</div>

						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- </div> -->

	<!-- 푸터 영역 시작 -->
	<jsp:include page="../footer.jsp" flush="true"></jsp:include>
	<!-- 푸터 영역 끝 -->

</body>
</html>
