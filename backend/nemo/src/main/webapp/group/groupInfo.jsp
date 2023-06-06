<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네모: 모임 소개</title>
<link rel="shortcut icon" href="../images/favicon.png" />
<link rel="stylesheet" href="../css/normalize.css" />
<link rel="stylesheet" href="../css/common.css" />
<link rel="stylesheet" href="../css/jquery-ui.min.css" />
<link rel="stylesheet" href="../css/groupInfo.css" />
<script src="../js/jquery-3.6.4.min.js"></script>
<script src="../js/header.js"></script>
<script src="../js/groupInfo.js"></script>
<script src="https://kit.fontawesome.com/f9a2702e84.js" crossorigin="anonymous"></script>
<script>
$(function () {
    let user_id = "${user_id}";
    let group_id = new URL(location.href).searchParams;
    group_id = group_id.get("group_id");
    let isBookmark = "${isBookmark}";

    $(".joinBtn").on("click", function () {
        console.log(user_id);
        //console.log(typeof user_id);
        //console.log(group_id);
        if (user_id === "null" || user_id === "") {
            if (window.confirm("로그인 후 이용해주세요")) {
                location.href = "/nemo/index";
            } else {
                location.href = "/nemo/index";
            }
        } else {
            console.log("user_id" + user_id);
            location.href = "/nemo/group/joinGroup?group_id=" + group_id;
        }
    });

    $(".grpLike").on("click", function () {
    	console.log("grpLike");
        $.ajax({
            type: "post",
            async: true,
            url: "http://127.0.0.1:8090/nemo/group/bookmark",
            data: { "user_id": user_id, "group_id": group_id, "isBookmark": isBookmark },
            success: function (data, textStatus) {
            	console.log(isBookmark);
            	isBookmark = !isBookmark;
            	
            },
            error: function (data, textStatus, error) {
            	console.log(data);
            	console.log(textStatus);
            	console.log(error);
                alert("찜 추가/삭제 에러 발생");
            },
        });
    });
});

</script>
</head>
<body>
	<jsp:include page="../header.jsp" flush="true"></jsp:include>
	
	<!-- main content 시작-->
        <div id="contentsArea" class="mainContent">
            <div class="mainInner">
                <!-- 소모임 이름 출력영역 -->
                <div class="nameArea">
                    <h2 class="grpName">${groupVO.grp_name}</h2>
                    <button type="button" class="grpLikeBtn" title="네모찜하기">
                    <c:if test="${isBookmark}">
                    	
                        <span class="grpLike on">
                            <svg viewBox="0 0 24 24">
                                <use xlink:href="#heart" />
                                <!-- filled heart -->
                                <use xlink:href="#heart" />
                                <!-- outline heart -->
                            </svg>
                            <!-- reference path for both outline, and filled, hearts -->
                            <svg class="hide" viewBox="0 0 24 24">
                                <defs>
                                    <path
                                        id="heart"
                                        d="M12 4.435c-1.989-5.399-12-4.597-12 3.568 0 4.068 3.06 9.481 12 14.997 8.94-5.516 12-10.929 12-14.997 0-8.118-10-8.999-12-3.568z"
                                    />
                                </defs>
                            </svg>
                        </span>
                        <span class="hidden">찜하기</span>
                   
                    </c:if>
                    <c:if test="${!isBookmark}">
                    	
                        <span class="grpLike">
                            <svg viewBox="0 0 24 24">
                                <use xlink:href="#heart" />
                                <!-- filled heart -->
                                <use xlink:href="#heart" />
                                <!-- outline heart -->
                            </svg>
                            <!-- reference path for both outline, and filled, hearts -->
                            <svg class="hide" viewBox="0 0 24 24">
                                <defs>
                                    <path
                                        id="heart"
                                        d="M12 4.435c-1.989-5.399-12-4.597-12 3.568 0 4.068 3.06 9.481 12 14.997 8.94-5.516 12-10.929 12-14.997 0-8.118-10-8.999-12-3.568z"
                                    />
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
                                    <img src="../../images/tmp01.jpg" alt="" class="grpImg" />
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
                            <td><span class="currentNum">${groupMemberNum}</span>/<span class="maxNum">${groupVO.mem_no}</span></td>
                        </tr>
                        <tr>
                            <th>찜한인원</th>
                            <td><span class="likeNum">${groupBookmarkNum}</span><span>명</span></td>
                        </tr>
                        <tr>
                            <th>최근활동일</th>
                            <td class="recentDate">${recentDate}</td>
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
                                <!-- style="width:780px;height:300px;" -->
                                지도 API 출력되는 영역
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
</body>
</html>