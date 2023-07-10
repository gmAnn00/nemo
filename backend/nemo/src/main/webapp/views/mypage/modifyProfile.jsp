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
<title>네모: 마이페이지</title>
<link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
<link rel="stylesheet" href="${contextPath}/css/normalize.css" />
<link rel="stylesheet" href="${contextPath}/css/common.css" />
<link rel="stylesheet" href="${contextPath}/css/submenu.css" />
<link rel="stylesheet" href="${contextPath}/css/modifyProfile.css" />
<script
  src="https://kit.fontawesome.com/bc604c01cc.js"
  crossorigin="anonymous"
></script>
<script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/js/header.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="${contextPath}/js/join.js"></script>
<script src="${contextPath}/js/modifyProfile.js"></script>
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
	
	<!--1. 전체 부분 -->
	<div class="section2">
      <div class="sc2_contents">
        <!-- 메뉴바 시작 -->
        <div class="sc2_menu_contents">
          <div class="sc2_menu">
            <h2 class="sc2_menu_title">프로필</h2>
            <ul class="sc2_menu_list">
              <li>
                <a href="${contextPath}/mypage">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name submenu_select">
                      <span>프로필</span>
                    </div>
                    <i class="fa-solid fa-minus submenu_select"></i>
                  </div>
                </a>
              </li>
              <li>
                <a href="${contextPath}/mypage/mySchedule">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name"><span>내 일정</span></div>
                    <i class="fa-solid fa-angle-right menu_angle"></i>
                  </div>
                </a>
              </li>
              <li>
                <a href="${contextPath}/mypage/myGroupList">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name menu_angle">
                      <span>내 소모임</span>
                    </div>
                    <i class="fa-solid fa-angle-right menu_angle"></i>
                  </div>
                </a>
              </li>
              <li>
                <a href="${contextPath}/mypage/myBoardList">
                  <div class="sc2_icon_menu">
                    <div class="menu_submenu_name">
                      <span>내가 쓴 글·댓글</span>
                    </div>
                    <i class="fa-solid fa-angle-right menu_angle"></i>
                  </div>
                </a>
              </li>
            </ul>
          </div>
        </div>
        <!-- 메뉴바 종료 -->

        <!--3. 컨텐츠부분-->
        <div class="sc2_subsection">
          <div class="sc2_subsection_title">
            <h2 class="sc2_subsection_title_name">회원정보 수정</h2>
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
          
		<form action="${contextPath}/mypage/modProfile" id="frm" name="frm" method="post" enctype="multipart/form-data">
          	<!--3. myImage-->          
          	<div class="myImage">                         
             <c:choose>
                <c:when test="${empty userVO.user_img}">
                	<img id="userImg" src="" alt=" 프로필 사진" />
                </c:when>
                <c:when test="${!empty userVO.user_img}">                                  
                	<img id="userImg" src="${contextPath}/userImageDownload?user_id=${user_id}&user_img=${userVO.user_img}" alt=" 프로필 사진" />                  
                </c:when>
                </c:choose>     
              <div>
                <label class="imageM button" for="hidden" id="file">수정</label>
                <input type="file" id="hidden" name="user_img" style="display: none" class="ignoreFind" onchange="readImage(this)"/>
                <input type="hidden" name="originalFileName" class="ignoreFind" value="${userVO.user_img}" />
                <input type="hidden" id="isDeleteImg" name="isDeleteImg" class="ignoreFind" value="false" />
                <button type="button" class="delUserImg buttonCancle" onclick="delUserImgSubmit()">삭제</button>
              </div>           
          	</div>

          	<!--4.-->
          	<div class="myMo">
            <!-- 회원가입 폼 + @ -->            
              <div>
                <label for="user_id">아이디</label>
                <input
                  type="text"
                  id="user_id"
                  class="readOnly ignoreFind"
                  name="user_id"
                  value="${userVO.user_id}"
                  required
                  disabled
                />
                <input type="hidden" name="user_id" class="ignoreFind" value="${userVO.user_id}" />
              </div>
              <div>
                <label for="password">비밀번호</label>
                <input
                  type="password"
                  id="password"
                  name="password"
                  placeholder="비밀번호 입력(문자, 숫자, 특수문자포함 8~20자)"
                  minlength="8"
                  maxlength="20"
                  value="${userVO.password}"
                  required
                  oninput="fn_pwdCheck()"
                />
                <div id="resultMsgId" class="alert resultMsg" style=display:none></div>
              </div>
              <div>
                <label for="passwordCheck">비밀번호 확인</label>
                <input
                  type="password"
                  id="passwordCheck"
                  name="passwordCheck"
                  placeholder="비밀번호 재입력"
                  minlength="8"
                  maxlength="20"
                  required
                  oninput="fn_pwdDupCheck()"
                />
                                
	                <div class="alert alertSuccess" id="alertSuccess">
	                비밀번호가 일치합니다.
	              </div>
	              <div class="alert alertDanger" id="alertDanger">
	                비밀번호가 일치하지않습니다.
	              </div>
                
              </div>
              
              <div>
                <label for="user_name">이름</label>
                <input
                  type="text"
                  id="user_name"
                  name="user_name"
                  class="readOnly ignoreFind"
                  value="${userVO.user_name}" 
                  required
                  disabled
                />
                <input type="hidden" name="user_name" class="ignoreFind" value="${userVO.user_name}" />
              </div>
              <div>
                <label for="nickname">닉네임</label>                
                <input
                  type="text"
                  id="nickname"
                  name="nickname"
                  placeholder="닉네임을 입력해주세요"
                  value="${userVO.nickname}"
                  oninput="fn_nicknameCheck()"
                  required
                />
                
                <div id="resultMsgNick" class="alert resultMsg" style=display:none></div>                  
              </div>
              
              <div>
                <label for="findZipcode">주소</label>
                <button
                  type="button"
                  class="findZipcode button"
                  id="findZipcode"
                >
                  우편번호 찾기
                </button>
                <input
                  type="text"
                  id="zipcode"
                  name="zipcode"
                  class="readOnly"
                  maxlength="5"
                  size="5"
                  placeholder="우편 번호"
                  value="${userVO.zipcode}"
                  readonly
                  required
                />
                <input
                  type="text"
                  id="user_addr1"
                  class="readOnly"
                  name="user_addr1"
                  maxlength="100"
                  size="60"
                  placeholder="주소"
                  value="${userVO.user_addr1}"
                  readonly
                  required
                />
                <input
                  type="text"
                  id="user_addr2"
                  name="user_addr2"
                  class="ignoreFind"
                  maxlength="100"
                  size="60"
                  placeholder="상세주소를 적어주세요"
                  value="${userVO.user_addr2}"
                />
              </div>
              <script>
                window.onload = function () {
                  document
                    .getElementById("findZipcode")
                    .addEventListener("click", function () {
                      new daum.Postcode({
                        oncomplete: function (data) {
                          document.getElementById("zipcode").value =
                            data.zonecode; // 우편번호를 zipcode input 태그에 할당
                          document.getElementById("user_addr1").value =
                            data.address; // 주소를 user_addr1 input 태그에 할당
                          // 입력된 값이 있는 input 박스 비활성화
                          // document.getElementById("zipcode").disabled = true;
                          // document.getElementById("user_addr1").disabled = true;

                          $("#zipcode").css({
                            background: "var(--sub3-color)",
                          });
                          $("#user_addr1").css({
                            background: "var(--sub3-color)",
                          });
                          document.getElementById("user_addr2").focus();
                        },
                      }).open();
                    });
                };
              </script>
              <div>
                <label for="birthdate">생년월일</label>
                <input
                  type="date"
                  id="birthdate"
                  name="birthdate"
                  min="1900-01-01"
                  value="${userVO.birthdate}"
                  required
                />
              </div>
              <script>
			  function getCurrentDate() {
			    var today = new Date();
			    var year = today.getFullYear();
			    var month = today.getMonth() + 1;
			    var day = today.getDate();
	
			    if (month < 10) {
			      month = '0' + month;
			    }
			    if (day < 10) {
			      day = '0' + day;
			    }
			
			    return year + '-' + month + '-' + day;
			  }
			
			  var birthdateInput = document.getElementById('birthdate');
			  var maxDate = getCurrentDate();
			  var year = parseInt(maxDate.substr(0, 4)) - 19;
			  maxDate = year + maxDate.substr(4);
			
			  birthdateInput.max = maxDate;
		  </script>
              <div>
                <label for="phone">전화번호</label>
                <input
                  type="tel"
                  id="phone"
                  name="phone"
                  placeholder="예)01012345678"
                  value="${userVO.phone}"
                  required
                  oninput="fn_phoneCheck()"
                />
                <div id="resultMsgPhone" class="alert resultMsg" style=display:none></div>
              </div>
              <div class="email">
              	<!-- 이메일 통째로 받아와서, @ 앞뒤로 나눠서 뿌려줘야 함 -->
                <label for="emailId">이메일주소</label>
                <input type="text" id="emailId" name="emailId" value="${emailId}" oninput="fn_emailCheck()" />
                <span>@</span>
                <input type="text" id="emailDomain" name="emailDomain" value="${emailDomain}" oninput="fn_emailCheck()"/>
                <select name="domainList" id="domainList" onchange="fn_emailSelect(); fn_emailCheck()">
                  <option value="self">직접입력</option>
                  <option value="gmail.com">gmail.com</option>
                  <option value="naver.com">naver.com</option>
                  <option value="daum.net">daum.net</option>
                  <option value="hanmail.net">hanmail.net</option>
                  <option value="yahoo.com">yahoo.com</option>
                  <option value="nate.com">nate.com</option>
                </select>
                
                  <div class="emailCheck">
	              	<div id="resultMsgEmail" class="alert resultMsg" style=display:none></div>	                
	              </div>
              </div>
              
              <div class="submitCancel">
               	<a href="javascript:void(0)" type="submit" class="button submitBtn" onclick="fnModify()">수정하기</a>
                <!-- <button type="submit" class="button">수정하기</button> -->
                <!-- <a href="#" role="button" class="button" onclick="fnJoin();"
                  >수정하기</a
                > -->
                <!-- type을 버튼으로 바꾸고 action과 submit을 jsp 처리하기 -->
                <a href="${contextPath}/mypage" role="button" class="buttonCancle"
                  >수정취소</a
                >
              </div>
          	</div>
       	  </form>
        </div>
      </div>
    </div>
	
	<jsp:include page="../footer.jsp" flush="true"></jsp:include>
	
	<input type="hidden" id="user_id_hidden" value="${userVO.user_id}"/>
	<input type="hidden" id="nickname_hidden" value="${userVO.nickname}"/>
	<input type="hidden" id="emailId_hidden" value="${emailId}"/>
	<input type="hidden" id="emailDomain_hidden" value="${emailDomain}"/>
</body>
</html>