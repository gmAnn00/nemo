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
<title>네모: 마이페이지</title>
<link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
<link rel="stylesheet" href="${contextPath}/css/normalize.css" />
<link rel="stylesheet" href="${contextPath}/css/common.css" />
<link rel="stylesheet" href="${contextPath}/css/submenu.css" />
<link rel="stylesheet" href="${contextPath}/css/myProfile.css" />
<script src="https://kit.fontawesome.com/bc604c01cc.js"
	crossorigin="anonymous"></script>
<script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/js/header.js"></script>
<script>
	//이미지 미리보기 구현
	function readImage(input) {
		//선택한 파일에 뭔가 값이 들어있으면 수행
		if (input.files && input.files[0]) {
			//FileReader() : 파읽을 읽어주는 jQuery에서 제공해주는 객체
			let reader = new FileReader();

			reader.onload = function(event) {
				//event.target.result : 이미지 경로?
				$("#userImg").attr('src', event.target.result);
			}
			reader.readAsDataURL(input.files[0]);
			$("#userIMGform").submit();
		} else {
			//취소 눌러서 선택한 파일에 값이 없을 때, 미리보기 없애기
			$("#userImg").attr('src', '#');
		}
	}
</script>
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
									<div class="menu_submenu_name submenu_select">
										<span>프로필</span>
									</div>
									<i class="fa-solid fa-minus submenu_select"></i>
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
						<li><a href="${contextPath}/mypage/myBoardList">
								<div class="sc2_icon_menu">
									<div class="menu_submenu_name">
										<span>내가 쓴 글·댓글</span>
									</div>
									<i class="fa-solid fa-angle-right menu_angle"></i>
								</div>
						</a></li>
					</ul>
				</div>
			</div>
			<!-- 메뉴바 종료 -->

			<!--3. 컨텐츠부분-->
			<div class="sc2_subsection">
				<div class="sc2_subsection_title">
					<h2 class="sc2_subsection_title_name">프로필</h2>
					<!-- nav 바 시작 -->
					<div class="nav_bar">
					  <a href="${contextPath}/index">
					  	<i class="fa-solid fa-house nav_icon"></i>
					  </a>
					  <i class="fa-solid fa-angle-right nav_icon"></i>
		              <span>마이페이지</span>
		              <i class="fa-solid fa-angle-right nav_icon"></i>
		              <span>프로필</span>
		            </div>
					<!-- nav 바 종료 -->
				</div>

				<!--4.-->
				<div class="myProfile">
					<!--4-1-->
					<div class="myProMo">
						<h3>내정보 조회</h3>
						<a href="${contextPath}/mypage/modProfileForm" role="button"
							class="button">수정</a>
					</div>

					<!--4-2-->
					<div class="myInfo">
						<div class="myImage">
							<form id="userIMGform"
								action="${contextPath}/mypage/userImgUpload" method="post"
								enctype="multipart/form-data">
								<c:choose>
									<c:when test="${empty userVO.user_img}">
										<img id="userImg" src="" alt=" 프로필 사진" />
									</c:when>
									<c:when test="${!empty userVO.user_img}">
										<img id="userImg"
											src="${contextPath}/userImageDownload?user_id=${user_id}&user_img=${userVO.user_img}"
											alt=" 프로필 사진" />
									</c:when>
								</c:choose>
								<label class="imageM button" for="hidden" id="file">수정 </label>
								<input id="hidden" type="file" style="display: none" name="user_img"
									onchange="readImage(this)" />
								<input type="hidden" name="originalFileName" value="${userVO.user_img}" />
							</form>
						</div>
	
						<!--4-3-->
						<div class="myModi">
							<table class="profileModi">
								<thead>
									<tr>
										<th>이름</th>
										<th>닉네임</th>
										<th>지역</th>
										<th>핸드폰 번호</th>
										<th>이메일</th>
										<th>생년월일</th>
									</tr>
								</thead>
								<c:choose>
									<%-- Controller에 request.setAttribute("여기있는 이름") 가져오기--%>
									<c:when test="${!empty userVO}">
										<tbody>
											<tr>
												<td><div>${userVO.user_name}</div></td>
												<td><div>${userVO.nickname}</div></td>
												<td><div>${userVO.user_addr1}</div></td>
												<td><div>${userVO.phone}</div></td>
												<td><div>${userVO.email}</div></td>
												<td><div>${userVO.birthdate}</div></td>
											</tr>
										</tbody>
									</c:when>
								</c:choose>
							</table>
						</div>
					</div>
	
					<div class="myHabi">
						<div class="myHabiText">
							<h3>내 관심사</h3>
							<a href="${contextPath}/mypage/interest/modInterestForm" role="button" class="button">수정</a>
							<!--  수정 버튼 누르면 관심사 바꿀 수 있게 -->
						</div>

						<div class="myHabiI">
							<ul>
								<c:choose>
									<c:when test="${empty interestsList}">
										<li>등록된 관심사가 없습니다.</li>
									</c:when>
									<c:when test="${!empty interestsList}">
										<c:forEach var="interestVO" items="${interestsList}">
											<li>${interestVO.sub_name}</li>
										</c:forEach>
									</c:when>
								</c:choose>
							</ul>
						</div>
					</div>

					<!-- 탈퇴 -->
					<div class="withdrawal">
						<h3>
							<a href="${contextPath}/mypage/delUserForm" class="delMemberlink">회원
								탈퇴하기 &gt;</a>
						</h3>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../footer.jsp" flush="true"></jsp:include>
</body>
</html>