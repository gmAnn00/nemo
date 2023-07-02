<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@
	page import="java.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<script src="https://kit.fontawesome.com/bc604c01cc.js"
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
	<!-- section1 -->
	<jsp:include page="./groupHeader.jsp" flush="true"></jsp:include>
	<!-- section1종료 -->

	<!-- 콘텐츠 영역 -->
	<!-- <div id="contentsArea"> -->
	<div class="section2">
		<div class="sc2_contents">
			<!-- 메뉴바 시작 -->
			<div class="sc2_menu_contents">
				<div class="sc2_menu">
					<h2 class="sc2_menu_title">
						<a href="${contextPath}/group/groupMain?group_id=${param.group_id}">나의 모임</a>
					</h2>
					<ul class="sc2_menu_list">
						<li><a href="${contextPath}/group/schedule?group_id=${param.group_id}">
								<div class="sc2_icon_menu">
									<div class="menu_submenu_name submenu_select">
										<span>일정</span>
									</div>
									<i class="fa-solid fa-minus submenu_select"></i>
								</div>
						</a></li>
						<li><a href="${contextPath}/group/board?group_id=${param.group_id}">
								<div class="sc2_icon_menu">
									<div class="menu_submenu_name">
										<span>게시판</span>
									</div>
									<i class="fa-solid fa-angle-right menu_angle"></i>
								</div>
						</a></li>
						<li><a href="${contextPath}/group/manager/member?group_id=${param.group_id}">
								<div class="sc2_icon_menu">
									<div class="menu_submenu_name">
										<span>멤버</span>
									</div>
									<i class="fa-solid fa-angle-right menu_angle"></i>
								</div>
						</a></li>
						<li><a href="${contextPath}/group/manager/setting?group_id=${param.group_id}">
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
				<div class="sc2_subcontents">
            	  <div class="sc2_subcontent">
            	  <div class="myScheduleCalAndList">
	                <div id="myScheduleCalendarArea"></div>
	                <div class="myScheduleListArea">
	                  <h3>다가오는 일정</h3>
	                
	                  <c:choose>
	                    <c:when test="${empty commingScheduleList}">
	                      <p>등록된 일정이 없습니다.</p>
	                    </c:when>
	                    <c:when test="${!empty commingScheduleList}"> 	                                             
	                      <c:forEach var="comGrpSchedule" items="${commingScheduleList}">
	                     	      
			                  <div class="mySchedule">		                  	
			                    <p class="myScheduleDate">${comGrpSchedule.scheduleDate}<span> ${comGrpSchedule.scheduleTime}</span></p>
			                    <div class="myScheduleImgContent">		                                           
			                      <div class="myScheduleContent">		                        
			                        <p class="contents">${comGrpSchedule.scheduleVO.sche_title}</p>
			                        <p class="contents"><i class="fa-solid fa-location-dot"></i>${comGrpSchedule.scheduleVO.location}</p>
			                      </div>
			                    </div>		                    
			                  </div>    		                                                 
	                  	     	 
	                  	  </c:forEach>
	                    </c:when>
	                  </c:choose>
	                  
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
											<br>
											<input id="sche_dateTime" type="datetime-local" name="sche_dateTime_new" value="">
											<input type="datetime-local" id="sche_dateTime_old" value="" name="sche_dateTime_old" style="display:none"/>
											<script>
												var currentDate2 = new Date();
												var currentYear = currentDate2.getFullYear();
												var currentMonth = (currentDate2.getMonth() + 1).toString().padStart(2, '0');
												var currentDay = currentDate2.getDate().toString().padStart(2, '0');
												var currentHour = currentDate2.getHours().toString().padStart(2, '0');
												var currentMinute = currentDate2.getMinutes().toString().padStart(2, '0');
												
												var koreanDateTime = currentYear + '-' + currentMonth + '-' + currentDay + 'T' + currentHour + ':' + currentMinute;
												document.getElementById("sche_dateTime").min = koreanDateTime;
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
										<ul id="partMemberArea">
											
										</ul>
									</div>
						
									<div class="partBtn clearfixed">
											<button type="button" id="joinSchedule" class="button btnPart" onclick="fn_joinSchedule(${param.group_id})">참석</button>
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
										var currentDate = new Date();
										var currentYear = currentDate.getFullYear();
										var currentMonth = (currentDate.getMonth() + 1).toString().padStart(2, '0');
										var currentDay = currentDate.getDate().toString().padStart(2, '0');
										var currentHour = currentDate.getHours().toString().padStart(2, '0');
										var currentMinute = currentDate.getMinutes().toString().padStart(2, '0');
										
										var koreanDateTime = currentYear + '-' + currentMonth + '-' + currentDay + 'T' + currentHour + ':' + currentMinute;
										document.getElementById("myDateTimeInput").min = koreanDateTime;
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