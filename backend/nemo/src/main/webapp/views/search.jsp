<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
			<form action="${contextPath}/groupSearch" method="get"
				class="searchBtn">
				<div class="categoriesArea">
					<div class="nameSearch">
						<div class="searchText1">
							<h2>소모임 이름 검색</h2>
						</div>

						<div class="nameBtn">
							<input type="text" class="searchText" name="searchText"
								value="${searchMap.searchText}" />

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
								<select name="smallCate" class="barSel"
									onchange="this.form.submit()"></select>
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
						<h3>검색결과(${fn:length(resultList)} 개)</h3>
					</div>
					<div class="resultBtn">
						<input type="radio" class="hidden" name="joinAble" id="joinAble" />
						<label for="joinAble" id="joinAbleLabel">가입가능한 소모임만 표시</label>
						
						<input type="radio" class="hidden" name="sort" id="sortByName"value="sortByName" />
						<label id="buttonName" for="sortByName" class="buttonSort">이름순정렬</label>
						
						<input type="radio" class="hidden" name="sort" id="sortByBookmark" value="sortByBookmark" />
						<label id="buttonInterest" for="sortByBookmark" class="buttonSort">찜순정렬</label>
						
						<input type="radio" class="hidden" name="sort" id="sortByNumber" value="sortByNumber" />
						<label id="buttonMember" for="sortByNumber" class="buttonSort">사람많은순</label>
					</div>
				</div>
			</form>
		</div>
		<div class="searchResult">
			<div class="resultGroup">
				<c:if test="${empty resultList}">
					<div id="noResultArea">
						<div>
							<h4>검색 결과가 없습니다.</h4>
						</div>
						<div id="gotoCreateDiv">
							<a id="gotoCreate" class="button"
								href="${contextPath}/group/createGroup/form">소모임 만들기</a>
						</div>
					</div>
				</c:if>
				<c:if test="${!empty resultList}">
					<c:forEach var="resultMap" items="${resultList}">
						<c:set var="groupVO" value="${resultMap.groupVO}" />
						<input type="hidden" id="isBookmark${groupVO.grp_id}" name="isBookmark${groupVO.grp_id}" value="${resultMap.isBookmark}" />
						<div class="group">
							<div class="groupImg Gimg01"
								style="background-image: url('${contextPath}/groupImages/${groupVO.grp_id}/${groupVO.grp_img}')"></div>
							<div class="SteamedImg">
								<button type="button" class="grpLikeBtn" title="네모찜하기">
									<c:if test="${resultMap.isBookmark}">
										<span class="grpLike grpLike${groupVO.grp_id} on" onclick="bookmarkClick('${user_id}', '${groupVO.grp_id}')"> <svg viewBox="0 0 24 24">
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
									</c:if>
									<c:if test="${!resultMap.isBookmark}">
										<span class="grpLike grpLike${groupVO.grp_id}" onclick="bookmarkClick('${user_id}', '${groupVO.grp_id}')"> <svg viewBox="0 0 24 24">
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
									</c:if>
								</button>
							</div>
							<a
								href="${contextPath}/group/groupInfo?group_id=${groupVO.grp_id}">
								<div class="groupText">
									<div class="groupText01 gt">
										<span>${groupVO.main_name}</span> | <span>${groupVO.sub_name}</span>
									</div>
									<div class="groupText02 gt">
										<span>${groupVO.grp_name}</span>
									</div>
									<div class="groupText03 gt">
										<i class="fas fa-map-marker-alt"></i> <span>
											${groupVO.grp_addr1}</span>
									</div>
									<div class="groupText04 gt">
										<i class="fa-solid fa-comment-dots"></i> <span>
											${groupVO.grp_intro}</span>
									</div>
									<div class="groupText05 gt">
										<i class="fa-solid fa-user"></i> <span>${resultMap.groupMemberNum}명</span>
									</div>
									<div class="groupText06 gt">
										<i class="fa-solid fa-heart"></i> <span>찜
											${resultMap.bookmarkNum}</span>
									</div>
								</div>
							</a>
						</div>

					</c:forEach>

				</c:if>


			</div>

			<div id="groupMap" class="GroupMap"></div>
		</div>
		<div class="PageBtn">
			<span>1 2 3 4 5</span>
		</div>
	</div>
	<input type="hidden" id="joinAble_hidden" value="${searchMap.joinAble}" />
	<input type="hidden" id="sort_hidden" value="${searchMap.sort}" />
	<input type="hidden" id="main_name_hidden"
		value="${searchMap.main_name}" />
	<input type="hidden" id="sub_name_hidden" value="${searchMap.sub_name}" />

	<jsp:include page="footer.jsp" flush="true"></jsp:include>
</body>
</html>
