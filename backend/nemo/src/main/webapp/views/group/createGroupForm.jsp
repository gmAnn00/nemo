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
 
<script>
function fn_app_st() {
	let app_st = $("#app_st").prop("checked")? "1" : "0";
	$("#app_st").val(app_st);
	console.log($("#app_st").val());
}

function Check_test() {
	var arrSelect=document.getElementsByName("terms");
	let inputLength = $('#grpDescription').val().length;
	console.log(inputLength);
	if(inputLength<100) {
		alert("100자 이상 입력해주세요");
		$('#grpDescription').focus();
	} else {
		for(var i=0; i<arrSelect.length; i++) {
			if(arrSelect[i].checked ) {
				document.getElementById("createGroup").submit();
				return true;
			}
		}
		alert("약관동의 체크 해주세요.");
		return false;	
	}
}


</script>

</head>
<body>
	<jsp:include page="../header.jsp" flush="true"></jsp:include>
	
	<!-- main content 시작-->
    <div id="contentsArea">
      <!--inner Contents영역-->
      <div class="innerCont">
        <!-- 페이지 제목 영역-->
        <div class="sc2_subsection_title">
          <h2 class="sc2_subsection_title_name">소모임 생성</h2>
        </div>

        

        <!-- 소모임 생성 정보 입력 영역 -->
        <div class="formArea">
          <form
            action="${contextPath}/group/createGroup/create"
            method="post"
            id="createGroup"
            name="createGroup"
            enctype="multipart/form-data"
          >
            <fieldset class="formTbl">
              <legend class="hidden">소모임 생성 정보 입력</legend>
              
              <!-- 프로필 사진 등록 영역-->
        <div class="uploadArea">
          <div class="imgCrop">
            <img
              src="${contextPath}/images/sea.jpg"
              alt=""
              class="grpImg"
              id="previewImage"
            />
          </div>
          <br />
          <div class="imgButtonArea">
	          <label for="grp_img" class="imgLabel">
	            <div class="button btnComm">소모임 프로필 사진 등록</div>
	          </label>
	          <input
	            type="file"
	            name="grp_img"
	            id="grp_img"
	            class="hidden"
	            accept="image/gif, image/jpeg, image/png"
	            onchange="readImage(this);"
	          />
	          <div class="buttonCancle btnComm" onClick="popupImgFileRm();">
	            취소
	          </div>
          </div>
        </div>
              
              <div>
                <label for="groupName">소모임이름</label>
                <input
                  type="text"
                  name="grp_name"
                  id="groupName"
                  minlength="2"
                  maxlength="10"
                  placeholder="2~10글자 이내"
                  required
                />
              </div>
              <div class="groupNum">
                <label for="groupNum">소모임인원</label>
                <input
                  type="text"
                  name="mem_no"
                  id="groupNum"
                  maxlength="3"
                  placeholder="2~50"
                /><span>명</span>
              </div>
              <div class="innerTblArea">
                <label class="cateTbl">카테고리</label>
                <table>
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
                          onchange="fn_category_selected(this);"
                          class="commonSelect input"
                        >
                          
                        </select>
                      </div>
                    </td>
                    <td>
                      <div class="selectBox">
                        <select
                          name="sub_name"
                          id="smallCate"
                          class="commonSelect select"
                          onchange="fn_category_selected(this);"
                        >
                          <option value="">선택하기</option>
                        </select>
                      </div>
                    </td>
                  </tr>
                </table>
                <p>※대분류/소분류 모두 선택하셔야 합니다.</p>
              </div>
              <div>
                <label for="findZipcode">모임장소</label>

                <input
                  type="text"
                  id="zipcode"
                  name="grp_zipcode"
                  maxlength="5"
                  size="5"
                  placeholder="우편번호"
                  readonly
                  required
                />
                <button
                  type="button"
                  class="findZipcode button"
                  id="findZipcode"
                  onclick="execDaumPostcode()"
                >
                  우편번호 찾기
                </button>
                <input
                  type="text"
                  id="grp_addr1"
                  name="grp_addr1"
                  placeholder="주소"
                  readonly
                />
                <input
                  type="text"
                  id="grp_addr2"
                  name="grp_addr2"
                  placeholder="상세주소"
                />
              </div>
              <div class="textAreaTd">
                <label for="grpDescription">소모임소개</label>
                <textarea
                  name="grp_intro"
                  id="grpDescription"
                  placeholder="메세지를 입력해주세요."
                  rows="10"
                  minlength="100"
                  maxlength="1000"
                ></textarea>
                <p>(<span>0/1000</span>)</p>
              </div>
              <div class="checkConfirm">
                <label>가입설정</label>
                <input type="checkbox" name="app_st" id="app_st" value="0" onclick="fn_app_st()"/>
                <label class="checkLabel" for="app_st">소모임장 승인제 (체크시 승인된 사람만 가입가능합니다.)</label>

                <input type="checkbox" name="terms" id="terms"/>
                <label class="checkLabel" for="terms">네모 이용 약관에 동의합니다.</label>
              </div>
              <div class="creatGrp">
                <a href="javascript:void(0)"
                  type="button"
                  name="createGrpBtn"
                  id="createGrpBtn"
                  class="button"
                  onclick="Check_test()"
                  value="소모임 만들기"
                > 소모임 만들기 </a>
                <input
                  type="reset"
                  name="createCancleBtn"
                  id="createCancleBtn"
                  class="buttonCancle"
                  value="다시 입력"
                />
              </div>

              
            </fieldset>
          </form>
        </div>
      </div>
      <!--innerCont 영역 종료 -->
    </div>
    <!-- main content 종료-->
	
	
	<jsp:include page="../footer.jsp" flush="true"></jsp:include>
	
	<script src="${contextPath}/js/createGroup.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</body>
</html>