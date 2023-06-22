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
<meta charset="UTF-8" />
<title>네모: 동네모임</title>
<link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
<link rel="stylesheet" href="${contextPath}/css/normalize.css" />
<link rel="stylesheet" href="${contextPath}/css/common.css" />
<link rel="stylesheet" href="${contextPath}/css/search.css" />
<script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ea6bda86230b8415e663eb00385b3b43&libraries=services"></script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/search.js"></script>
<script src="https://kit.fontawesome.com/f9a2702e84.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="header.jsp" flush="true"></jsp:include>

	<!--카테고리 영역-->
	<div id="contentsArea">
		<div class="formArea">
			<form action="/search" method="get" class="searchBtn">
				<div class="categoriesArea">
					<div class="nameSearch">
						<div class="searchText1">
							<h2>소모임 이름 검색</h2>
						</div>

						<div class="nameBtn">
							<input type="text" class="searchText" />

							<button type="submit" class="button">검색</button>
						</div>
					</div>

					<!--카테고리-->
					<div class="categories01">
						<div class="bar">
							<!--대분류-->
							<div class="bar01">
								<select name="bigCate" class="barSel"></select>
							</div>
							<!--소분류-->
							<div class="bar02">
								<select name="smallCate" class="barSel"></select>
							</div>
							<!--지역-->
							<div class="bar03">
								<select name="areaBar" class="barSel">
									<option value="1">지역1</option>
									<option value="2">지역2</option>
									<option value="3">지역3</option>
									<option value="4">지역4</option>
									<option value="5">지역5</option>
								</select>
							</div>
						</div>
					</div>
				</div>

				<div class="result">
					<div class="resultText">
						<h3>검색결과(개수)</h3>
					</div>
					<div class="resultBtn">
						<button type="button" id="buttonAble" class="buttonCancle">가입가능한
							소모임만 표시</button>
						<button type="button" id="buttonName"
							class="buttonSort buttonCancle">이름순정렬</button>
						<button type="button" id="buttonInterest"
							class="buttonSort buttonCancle">찜순정렬</button>
						<button type="button" id="buttonMember"
							class="buttonSort buttonCancle">사람많은순</button>
					</div>
				</div>
			</form>
		</div>
		<div class="searchResult">
			<div class="resultGroup">
				<div class="group">
					<div class="groupImg Gimg01"></div>
					<div class="SteamedImg">
						<button type="button" class="grpLikeBtn" title="네모찜하기">
							<span class="grpLike"> <svg viewBox="0 0 24 24">
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
							</span> <span class="hidden">찜하기</span>
						</button>
					</div>
					<div class="groupText">
						<div class="groupText01 gt">
							<span>음악</span> | <span>일렉트로닉</span>
						</div>
						<div class="groupText02 gt">
							<span>일렉트로닉 뮤직LA</span>
						</div>
						<div class="groupText03 gt">
							<i class="fas fa-map-marker-alt"></i> <span> 로스엔젤레스,켈리포니아</span>
						</div>
						<div class="groupText04 gt">
							<i class="fa-solid fa-comment-dots"></i> <span> 우리의 사명은
								로스앤젤레스 카운티에서 전자 음악 행사를 홍보하고 차기 DJ, 프로듀서, 보컬리스트 및 음악 비즈니스 전문가를 위한
								기회 네트워크를 만드는 것입니다.</span>
						</div>
						<div class="groupText05 gt">
							<i class="fa-solid fa-user"></i> <span>n명</span>
						</div>
						<div class="groupText06 gt">
							<i class="fa-solid fa-heart"></i> <span>찜 30</span>
						</div>
					</div>
				</div>

				<div class="group">
					<div class="groupImg Gimg02"></div>
					<div class="SteamedImg">
						<button type="button" class="grpLikeBtn" title="네모찜하기">
							<span class="grpLike"> <svg viewBox="0 0 24 24">
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
							</span> <span class="hidden">찜하기</span>
						</button>
					</div>
					<div class="groupText">
						<div class="groupText01 gt">
							<span>기타</span> | <span>IT</span>
						</div>
						<div class="groupText02 gt">
							<span>이젠 아카데미 종로</span>
						</div>
						<div class="groupText03 gt">
							<i class="fas fa-map-marker-alt"></i> <span> 서울시 종로구</span>
						</div>
						<div class="groupText04 gt">
							<i class="fa-solid fa-comment-dots"></i> <span> 우리 모임은
								IT공부를 재밌게 열심히 하고 멋진 프로젝트를 만들어서 다들 각자 원하는 좋은 곳에 취업하려는 사람이 모였습니다.</span>
						</div>
						<div class="groupText05 gt">
							<i class="fa-solid fa-user"></i> <span>n명</span>
						</div>
						<div class="groupText06 gt">
							<i class="fa-solid fa-heart"></i> <span>찜 30</span>
						</div>
					</div>
				</div>

				<div class="group">
					<div class="groupImg Gimg03"></div>
					<div class="SteamedImg">
						<button type="button" class="grpLikeBtn" title="네모찜하기">
							<span class="grpLike"> <svg viewBox="0 0 24 24">
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
							</span> <span class="hidden">찜하기</span>
						</button>
					</div>
					<div class="groupText">
						<div class="groupText01 gt">
							<span>음악</span> | <span>일렉트로닉</span>
						</div>
						<div class="groupText02 gt">
							<span>일렉트로닉 뮤직LA</span>
						</div>
						<div class="groupText03 gt">
							<i class="fas fa-map-marker-alt"></i> <span> 로스엔젤레스,켈리포니아</span>
						</div>
						<div class="groupText04 gt">
							<i class="fa-solid fa-comment-dots"></i> <span> 우리의 사명은
								로스앤젤레스 카운티에서 전자 음악 행사를 홍보하고 차기 DJ, 프로듀서, 보컬리스트 및 음악 비즈니스 전문가를 위한
								기회 네트워크를 만드는 것입니다.</span>
						</div>
						<div class="groupText05 gt">
							<i class="fa-solid fa-user"></i> <span>n명</span>
						</div>
						<div class="groupText06 gt">
							<i class="fa-solid fa-heart"></i> <span>찜 30</span>
						</div>
					</div>
				</div>

				<div class="group">
					<div class="groupImg Gimg04"></div>
					<div class="SteamedImg">
						<button type="button" class="grpLikeBtn" title="네모찜하기">
							<span class="grpLike"> <svg viewBox="0 0 24 24">
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
							</span> <span class="hidden">찜하기</span>
						</button>
					</div>
					<div class="groupText">
						<div class="groupText01 gt">
							<span>음악</span> | <span>일렉트로닉</span>
						</div>
						<div class="groupText02 gt">
							<span>일렉트로닉 뮤직LA</span>
						</div>
						<div class="groupText03 gt">
							<i class="fas fa-map-marker-alt"></i> <span> 로스엔젤레스,켈리포니아</span>
						</div>
						<div class="groupText04 gt">
							<i class="fa-solid fa-comment-dots"></i> <span> 우리의 사명은
								로스앤젤레스 카운티에서 전자 음악 행사를 홍보하고 차기 DJ, 프로듀서, 보컬리스트 및 음악 비즈니스 전문가를 위한
								기회 네트워크를 만드는 것입니다.</span>
						</div>
						<div class="groupText05 gt">
							<i class="fa-solid fa-user"></i> <span>n명</span>
						</div>
						<div class="groupText06 gt">
							<i class="fa-solid fa-heart"></i> <span>찜 30</span>
						</div>
					</div>
				</div>

				<div class="group">
					<div class="groupImg Gimg05"></div>
					<div class="SteamedImg">
						<button type="button" class="grpLikeBtn" title="네모찜하기">
							<span class="grpLike"> <svg viewBox="0 0 24 24">
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
							</span> <span class="hidden">찜하기</span>
						</button>
					</div>
					<div class="groupText">
						<div class="groupText01 gt">
							<span>음악</span> | <span>일렉트로닉</span>
						</div>
						<div class="groupText02 gt">
							<span>일렉트로닉 뮤직LA</span>
						</div>
						<div class="groupText03 gt">
							<i class="fas fa-map-marker-alt"></i> <span> 로스엔젤레스,켈리포니아</span>
						</div>
						<div class="groupText04 gt">
							<i class="fa-solid fa-comment-dots"></i> <span> 우리의 사명은
								로스앤젤레스 카운티에서 전자 음악 행사를 홍보하고 차기 DJ, 프로듀서, 보컬리스트 및 음악 비즈니스 전문가를 위한
								기회 네트워크를 만드는 것입니다.</span>
						</div>
						<div class="groupText05 gt">
							<i class="fa-solid fa-user"></i> <span>n명</span>
						</div>
						<div class="groupText06 gt">
							<i class="fa-solid fa-heart"></i> <span>찜 30</span>
						</div>
					</div>
				</div>
			</div>
			<div id="groupMap" class="GroupMap"></div>
		</div>
		<div class="PageBtn">
			<span>1 2 3 4 5</span>
		</div>
	</div>

	<jsp:include page="footer.jsp" flush="true"></jsp:include>
</body>
</html>
