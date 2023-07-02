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
        	<!-- 하트 표시 시작 -->
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
			<!-- 하트 표시 종료 -->
            <div class="group_img">
                <img src="${contextPath}/groupImageDownload?group_id=${group.groupVO.grp_id}&group_img=${group.groupVO.grp_img}" />
            </div>
            <div class="group_name">
                <a href="${contextPath}/group/groupMain?group_id=${group.groupVO.grp_id}">
                    <span class="group_name_title">${group.groupVO.grp_name}</span>                    
                </a>

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
			
			<!-- 설명 영역 시작 -->
			<div class="shortInfoArea">
				<div class="shortInfoDetail">
					<h3>네모설명</h3>
					<p class="detailInfo">${groupVO.grp_intro}</p>
					
					<!-- 기타 정보 영역 시작-->
					<div class="etcInfoArea">
						<h3>기타정보</h3>
						<c:if test="${app_st == 0}">
							<p class="etcInfo">가입 즉시 활동할 수 있는 소모임입니다.</p>
						</c:if>
						<c:if test="${app_st == 1}">
							<p class="etcInfo">가입 시 소모임장의 승인이 필요합니다.</p>
						</c:if>
					</div>
					
				</div>
				
				

				<!-- 간략 정보 영역 시작-->
			
				<div class="shortInfoTbl">
					<div class="shortInfoTblMng">
						<h3>개설자</h3>
						<div class="shortInfoTblMngImgArea">
							<img src="${contextPath}/userImageDownload?user_id=${mngUserVO.user_id}&user_img=${mngUserVO.user_img}" alt="grp_mng">
							<span>${mngUserVO.nickname}</span>
						</div>				
					</div>
					<div class="shortInfoTblHeart">
						<span class="shortInfoTblTitle">찜한 인원</span>
						<span class="shortInfoTblHeartContent">${groupBookmarkNum}명</span>					
					</div>
					<div class="shortInfoTblRecent">
						<span class="shortInfoTblTitle">최근활동일</span>
						<c:if test="${recentDate != null}">
							<span class="recentDate">${recentDate}</td>
						</c:if>
						<c:if test="${recentDate == null}">
							<span class="recentDate">${groupVO.create_date}</td>
						</c:if>					
					</div>
				 </div>
				 
			 </div>	
				
		</div>

			<!-- 상세 정보 영역 시작 -->
			<div class="infoDetailArea">
				

				<!-- 장소 영역 시작-->
				<div class="mapAreaSection">
					<h3>네모장소</h3>
					<!-- 지도영역 부분 -->
					<div class="mapArea">
						<div id="map">
							
						</div>
						<span>${groupVO.grp_addr1}</span>
					</div>
				</div>


				
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