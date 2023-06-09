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
<title>네모: 소모임 만들기</title>
<link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
<link rel="stylesheet" href="${contextPath}/css/normalize.css" />
<link rel="stylesheet" href="${contextPath}/css/common.css" />
<link rel="stylesheet" href="${contextPath}/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${contextPath}/css/createGroup.css" />
<script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/js/header.js"></script>
<script src="https://kit.fontawesome.com/f9a2702e84.js" crossorigin="anonymous"></script>
<!--  
<script>
$(function () {
	let app_st = $("#app_st").prop("checked")? "1" : "0";
	$("#app_st").val(app_st);
	
}
</script>
-->
</head>
<body>
	<jsp:include page="../header.jsp" flush="true"></jsp:include>
	
	<!-- main content 시작-->
        <div id="contentsArea">
            <!--inner Contents영역-->
            <div class="innerCont">
                <!-- 페이지 제목 영역-->
                <div class="pageTitle">
                    <h2>네모 만들기</h2>
                </div>

                <!-- 프로필 사진 등록 영역-->
                <div class="uploadArea">
                    <div class="imgCrop">
                        <img src="${contextPath}/images/tmp01.jpg" alt="" class="grpImg" id="previewImage" />
                    </div>
                    <br />
                    <div>
                        <p></p>
                    </div>
                    <label for="file">
                        <div class="button btnComm">네모 프로필 사진 사진등록</div>
                    </label>
                    <input type="file" name="file" id="file" class="hidden" accept="image/gif, image/jpeg, image/png" onchange="readImage(this);" />
                    <div class="buttonCancle btnComm" onClick="popupImgFileRm();">취소</div>
                </div>

                <!-- 소모임 생성 정보 입력 영역 -->
                <div class="formArea">
                    <p><strong class="require">필수</strong>는 필수항목입니다</p>
                    <form action="${contextPath}/group/createGroup/create" method="post" id="createGroup" name="createGroup" enctype="multipart/form-data">
                        <fieldset>
                            <legend class="hidden">네모 생성 정보 입력</legend>
                            <table class="formTbl">
                                <tr>
                                    <th><strong class="require">필수</strong><span>네모이름</span></th>
                                    <td>
                                        <input type="text" name="grp_name" maxlength="11" required />
                                        <p>※2~10글자 이내</p>
                                    </td>
                                </tr>

                                <tr>
                                    <th><strong class="require">필수</strong><span>네모인원</span></th>
                                    <td>
                                        <input type="text" name="mem_no" maxlength="3" /><span>명</span>
                                        <p>※최소 2명~최대 50명</p>
                                    </td>
                                </tr>

                                <tr>
                                    <th><strong class="require">필수</strong><span>카테고리</span></th>
                                    <td>
                                        <p>※대분류/소분류 모두 선택하셔야 합니다.</p>
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan="2">
                                        <div class="innerTblArea">
                                            <table class="cateTbl">
                                                <tr>
                                                    <th>대분류</th>
                                                    <th>소분류</th>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <div class="selectBox">
                                                            <select
                                                                name="main_name"
                                                                id="largeCate"
                                                                onchange="change(this);"
                                                                class="commonSelect input"
                                                            >
                                                                <option value="0">선택하기</option>
                                                                <option value="1">문화/공연/축제</option>
                                                                <option value="2">음악/악기</option>
                                                                <option value="3">사진/영상</option>
                                                                <option value="4">아웃도어/여행</option>
                                                                <option value="5">운동/스포츠</option>
                                                                <option value="6">인문학/책/글</option>
                                                                <option value="7">업종/직무</option>
                                                                <option value="8">공예/만들기</option>
                                                                <option value="9">댄스/무용</option>
                                                                <option value="10">봉사활동</option>
                                                                <option value="11">사교/인맥</option>
                                                                <option value="12">차/오토바이</option>
                                                                <option value="13">스포츠 관람</option>
                                                                <option value="14">게임/오락</option>
                                                                <option value="15">요리/제조</option>
                                                                <option value="16">반려동물</option>
                                                            </select>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="selectBox">
                                                            <select name="sub_name" id="smallCate" class="commonSelect select">
                                                                <option value="">선택하기</option>
                                                            </select>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </td>
                                </tr>

                                <tr>
                                    <th><strong class="require">필수</strong><span>네모장소</span></th>
                                    <td>
                                        <!--
                                    <input type="text" name="sample6_postcode" id="sample6_postcode" placeholder="우편번호">
                                    <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
                                    <input type="text" id="sample6_address" placeholder="주소"> 
                                    -->
                                        <input type="text" name="grp_zipcode" id="sample6_postcode" placeholder="우편번호" />
                                        <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" /><br />
                                        <input type="text" name="grp_addr1" id="sample6_address" placeholder="주소" /><br />
                                        <input type="text" name="grp_addr2 id="sample6_detailAddress" placeholder="상세주소" />
                                        <!-- <input type="text" id="sample6_extraAddress" placeholder="참고항목">-->
                                    </td>
                                </tr>

                                <tr>
                                    <th><strong class="require">필수</strong><span>네모소개</span></th>
                                    <td>
                                        <p>최소 100자 이상 작성해주세요(최대 1000자)</p>
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan="2" class="textAreaTd">
                                        <textarea name="grp_intro" id="grpDescription" rows="10" maxlength="1000"></textarea>
                                        <p>(<span>0/0</span>)</p>
                                    </td>
                                </tr>
                                <tr>
                                    <th>
                                        <span>가입설정</span>
                                    </th>
                                    <td>
                                        <label for="checkConfirm">
                                            <input type="checkbox" name="checkConfirm" id="checkConfirm" onclick="fn_app_st()"/>
                                            네모장 승인제 (체크시 승인된 사람만 가입가능합니다.)
                                            <input type="hidden" name="app_st" id="app_st" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <label for="b">
                                            <input type="checkbox" name="" id="b" />
                                            <a href="#">네모 이용 약관</a>에 동의합니다.
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" name="createGrpBtn" id="createGrpBtn" class="button" value="네모만들기" />
                                        <input type="reset" name="createCancleBtn" id="createCancleBtn" class="buttonCancle" value="만들기취소" />
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                    </form>
                </div>
            </div>
            <!--innerCont 영역 종료 -->
        </div>
        <!-- main content 종료-->
	
	
	<jsp:include page="../footer.jsp" flush="true"></jsp:include>
	
	<script src="${contextPath}/js/createGroup.js?v=<%=System.currentTimeMillis() %>"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</body>
</html>