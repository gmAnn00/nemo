<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="group" value="${groupInfo}" />
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네모: 모임 소개</title>
<link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
<link rel="stylesheet" href="${contextPath}/css/normalize.css" />
<link rel="stylesheet" href="${contextPath}/css/common.css" />
<link rel="stylesheet" href="${contextPath}/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${contextPath}/css/sectionTitle.css" />
<link rel="stylesheet" href="${contextPath}/css/groupInfo.css" />
<script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ea6bda86230b8415e663eb00385b3b43&libraries=services"></script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/groupInfo.js"></script>
<script src="https://kit.fontawesome.com/f9a2702e84.js"
	crossorigin="anonymous"></script>

</head>
<body>
	<jsp:include page="../header.jsp" flush="true"></jsp:include>
	
    <!-- section1 시작 -->
    <div class="section1" style="background-image:url('${contextPath}/groupImageDownload?group_id=${group.groupVO.grp_id}&group_img=${group.groupVO.grp_img}')">
      <div class="group_containter">
        
        <div class="group_all">
            <div class="group_img">
                <img src="${contextPath}/groupImageDownload?group_id=${group.groupVO.grp_id}&group_img=${group.groupVO.grp_img}" />
            </div>
            <div class="group_name">
                <a href="${contextPath}/group/groupMain?group_id=${group.groupVO.grp_id}">
                    <span class="group_name_title">${group.groupVO.grp_name}</span>                    
                </a>
                <button type="button" class="grpLikeBtn" title="네모찜하기">
					<c:if test="${isBookmark}">

						<span class="grpLike on"> <svg viewBox="0 0 24 24">
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
						</span>
						<span class="hidden">찜하기</span>

					</c:if>
				</button>
                <div class="group_info_category">
                    <div class="category_box group_info_category_box">${group.groupVO.main_name} | ${group.groupVO.sub_name}</div>
	                 <div class="group_info_member">
	                     <div class="group_info_title"><span>MEMBER</span></div>
	                     <div class="group_info_contents"><span class="group_info_number">${group.currentMemNo} / ${group.groupVO.mem_no}</span></div>
	                 </div>
	                 <div class="group_info_follower">
	                     <div class="group_info_title"><span>SINCE</span></div>
	                     <div class="group_info_contents"><span class="group_info_date"><fmt:formatDate pattern="yyyy.MM.dd." value="${group.groupVO.create_date}"/></span></div>
	                 </div>
            	</div>
            </div>
        </div>
        
      </div>
    </div>
    <!-- section1 종료 -->


	<!-- main content 시작-->
	<div id="contentsArea" class="mainContent">
		<div class="mainInner">
			<!-- 소모임 이름 출력영역 -->
			<div class="nameArea">
				<!-- <h2 class="grpName">${groupVO.grp_name}</h2> -->
				<button type="button" class="grpLikeBtn" title="네모찜하기">
					<c:if test="${isBookmark}">

						<span class="grpLike on"> <svg viewBox="0 0 24 24">
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
						</span>
						<span class="hidden">찜하기</span>

					</c:if>
				</button>
			</div>

			<!-- 간략 정보 영역 시작-->
			<div class="shortInfoArea">
				<!-- 소모임 간략설명 영역-->
				<table class="shortInfoTbl">
					<!-- 소모임 프로필 사진 영역-->
					<tr>
						<td rowspan="6">
							<div class="imgCrop">
								<img src="${contextPath}/groupImageDownload?group_id=${param.group_id}&group_img=${groupVO.grp_img}" alt="" class="grpImg" />
							</div>
						</td>
					</tr>
					<tr>
						<th>네모개설자</th>
						<td class="founderName">${groupManager}</td>
					</tr>
					<tr>
						<th>네모개설일</th>
						<td class="createDate">${groupVO.create_date}</td>
					</tr>
					<tr>
						<th>네모인원</th>
						<td><span class="currentNum">${groupMemberNum}</span>/<span
							class="maxNum">${groupVO.mem_no}</span></td>
					</tr>
					<tr>
						<th>찜한인원</th>
						<td><span class="likeNum" id="likeNum">${groupBookmarkNum}</span><span>명</span></td>
					</tr>
					<tr>
						<th>최근활동일</th>
						<c:if test="${recentDate != null}">
							<td class="recentDate">${recentDate}</td>
						</c:if>
						<c:if test="${recentDate == null}">
							<td class="recentDate">${groupVO.create_date}</td>
						</c:if>
					</tr>
				</table>
			</div>

			<!-- 상세 정보 영역 시작 -->
			<div class="infoDetailArea">
				<!-- 설명 영역 시작 -->
				<section>
					<h3>네모설명</h3>
					<p class="detailInfo">${groupVO.grp_intro}</p>
				</section>

				<!-- 장소 영역 시작-->
				<section>
					<h3>네모장소</h3>
					<!-- 지도영역 부분 -->
					<div class="mapArea">
						<div id="map">
							
						</div>
					</div>
				</section>

				<!-- 카테고리 영역 시작-->
				<section>
					<h3>카테고리</h3>
					<span class="cateIcon largeCate" title="대분류">${groupVO.main_name}</span>
					<span class="cateIcon smallCate" title="소분류">${groupVO.sub_name}</span>
				</section>

				<!-- 기타 정보 영역 시작-->
				<section>
					<h3>기타정보</h3>
					<c:if test="${app_st == 0}">
						<p class="etcInfo">가입 즉시 활동할 수 있는 소모임입니다.</p>
					</c:if>
					<c:if test="${app_st == 1}">
						<p class="etcInfo">가입 시 소모임장의 승인이 필요합니다.</p>
					</c:if>

				</section>
			</div>

			<!-- main content 버튼영역 시작-->
			<div class="btnArea">
				<button class="button joinBtn" title="네모가입하기">가입하기</button>
			</div>
		</div>
	</div>
	<!-- main content 끝-->

	<jsp:include page="../footer.jsp" flush="true"></jsp:include>
	<input type="hidden" id="user_id_hidden" name="user_id_hidden" value="${user_id}"/>
	<input type="hidden" id="grp_addr1_hidden" name="grp_addr1_hidden" value="${groupVO.grp_addr1}" />
	
</body>
</html>