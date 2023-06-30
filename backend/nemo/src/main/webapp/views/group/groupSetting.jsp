<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
<%
	request.setCharacterEncoding("utf-8");
%>   
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>네모</title>
        <link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
        <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
        <link rel="stylesheet" href="${contextPath}/css/common.css" />
        <link rel="stylesheet" href="${contextPath}/css/submenu.css" />
        <link rel="stylesheet" href="${contextPath}/css/sectionTitle.css" />
        <link rel="stylesheet" href="${contextPath}/css/groupSetting.css" />
        <script src="https://kit.fontawesome.com/bc604c01cc.js" crossorigin="anonymous"></script>
        <script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
        <script src="${contextPath}/js/header.js"></script>
        
    </head>
    <body>
        <jsp:include page="../header.jsp" flush="true"></jsp:include>
		<c:forEach var="group" items="${groupList}">
		
        	<!-- section1 시작 -->
            <div class="section1" style="background-image:url('${contextPath}/groupImages/${group.grp_id}/${group.grp_img}')">
           		<div class="group_containter">
	                 <div class="group_all">
	                     <div class="group_img">
	                         <img src="${contextPath}/groupImages/${group.grp_id}/${group.grp_img}" alt="group_img" />
	                     </div>
	                     <div class="group_name">
	                         <a href="groupMain.html">
	                             <span>${group.grp_name}</span>
	                         </a>
	                     </div>
	                     <div class="group_info">
	                         <div class="group_info_category">
	                             <div class="category_box group_info_category_box">${group.main_name}</div>
	                             <div class="category_box group_info_category_box">${group.sub_name}</div>
	                         </div>
	                         <div class="group_info_member">
	                             <div class="group_info_title"><span>멤버수</span></div>
	                             <div class="group_info_contents"><span>${group.mem_no}</span></div>
	                         </div>
	                         <div class="group_info_follower">
	                             <div class="group_info_title"><span>개설일</span></div>
	                             <div class="group_info_contents"><span><fmt:formatDate pattern="yyyy-MM-dd" value="${group.create_date}"/></span></div>
	                         </div>
	                     </div>
	                 </div>
           		</div>
            </div>
        	<!-- section1 종료 -->

	        <!-- section2 시작 -->
	        <div class="section2">
	            <div class="sc2_contents">
	                <!-- 메뉴바 시작 -->
	                <div class="sc2_menu_contents">
	                    <div class="sc2_menu">
	                        <h2 class="sc2_menu_title">나의 모임</h2>
	                        <ul class="sc2_menu_list">
	                            <li>
	                                <a href="schedule.html">
	                                    <div class="sc2_icon_menu">
	                                        <div class="menu_submenu_name"><span>일정</span></div>
	                                        <i class="fa-solid fa-angle-right menu_angle"></i>
	                                    </div>
	                                </a>
	                            </li>
	                            <li>
	                                <a href="board.html">
	                                    <div class="sc2_icon_menu">
	                                        <div class="menu_submenu_name"><span>게시판</span></div>
	                                        <i class="fa-solid fa-angle-right menu_angle"></i>
	                                    </div>
	                                </a>
	                            </li>
	                            <li>
	                                <a href="myPageGroup.html">
	                                    <div class="sc2_icon_menu">
	                                        <div class="menu_submenu_name"><span>멤버</span></div>
	                                        <i class="fa-solid fa-angle-right menu_angle"></i>
	                                    </div>
	                                </a>
	                            </li>
	                            <li>
	                                <a href="groupSetting.html">
	                                    <div class="sc2_icon_menu">
	                                        <div class="menu_submenu_name submenu_select"><span>소모임관리</span></div>
	                                        <i class="fa-solid fa-minus submenu_select"></i>
	                                    </div>
	                                </a>
	                            </li>
	                        </ul>
	                    </div>
	                </div>
	                <!-- 메뉴바 종료 -->
	
	                <!-- 컨텐츠 시작 -->
	                <div class="sc2_subsection">
	                    <div class="sc2_subsection_title">
	                        <span class="sc2_subsection_title_name">소모임관리</span>
	                        <!-- nav 바 시작 -->
	                        <div class="nav_bar">
	                            <a href="../index.html">
	                                <i class="fa-solid fa-house nav_icon"></i>
	                            </a>
	                            <i class="fa-solid fa-angle-right nav_icon"></i>
	                            <span>나의 모임</span>
	                            <i class="fa-solid fa-angle-right nav_icon"></i>
	                            <span>소모임관리</span>
	                        </div>
	                        <!-- nav 바 종료 -->
	                    </div>
	                
	                    
	

	                    <!-- 소모임 생성 정보 입력 영역 -->
	                    <div class="formArea">
	                        <form
	                            action="${contextPath}/group/manager/setting/modGroup?group_id=${group.grp_id}"
	                            method="post"
	                            id="modGroup"
	                            name="modGroup"
	                            enctype="multipart/form-data"
	                        >
	                            <!-- 프로필 사진 등록 영역-->
	                            <div class="uploadArea">
	                                <c:choose>
						                <c:when test="${empty group.grp_img}">
						                	<div class="imgCrop">
						                		<img id="previewImage" src="" alt=" 프로필 사진" />
						                	</div>
						                </c:when>
						                <c:when test="${!empty group.grp_img}">  
						                    <div class="imgCrop">
						                    	<img id="previewImage" class="grpImg" src="${contextPath}/groupImageDownload?group_id=${group.grp_id}&group_img=${group.grp_img}" alt=" 프로필 사진" />
						                    </div>                      
						                </c:when>
					                </c:choose>
	                                <div class="profile_btn">
	                                    <label for="file" class="mod_btn">
	                                        <div class="button btnComm">수정</div>
	                                    </label>
	                                    <input
	                                        type="file"
	                                        name="grp_img"
	                                        id="file"
	                                        class="hidden"
	                                        accept="image/gif, image/jpeg, image/png"
	                                        onchange="readImage(this);"
	                                    />
	                                    <div class="buttonCancle btnComm" onClick="popupImgFileRm();">취소</div>
	                                    <input type="hidden" name="originalFileName" value="${group.grp_img}" />
	                                </div>
	                            </div>
	                            <fieldset class="formTbl">
	                                <legend class="hidden">네모 생성 정보 입력</legend>
	                                    
	
	                                <div class="form_div">
	                                    <label class="profile_label" for="groupName">네모이름</label>
	                                    <span>${group.grp_name}</span>
	                                </div>
	                                <div class="groupNum form_div">
	                                    <label for="groupNum" class="profile_label">네모인원</label>
	                                    <input
	                                    type="text"
	                                    name="mem_no"
	                                    id="groupNum"
	                                    maxlength="3"
	                                    placeholder="2~50"
	                                    value="${group.mem_no}"
	                                    /><span>명</span>
	                                </div>
	                                <div class="innerTblArea form_div">
	                                	
	                                    <label class="cateTbl profile_label">카테고리</label>
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
						                          onchange="change(this);"
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
						                        >
						                        <option value="">선택하기</option>
						                        </select>
						                      </div>
						                    </td>
	                                    </tr>
	                                    </table>
	                                    <p>※대분류/소분류 모두 선택하셔야 합니다.</p>
	                                </div>
	                                <div class="form_div">
	                                    <label for="findZipcode" class="profile_label">모임장소</label>
	
	                                    <input
	                                    type="text"
	                                    id="zipcode"
	                                    name="grp_zipcode"
	                                    maxlength="5"
	                                    size="5"
	                                    placeholder=""
	                                    value="${group.grp_zipcode}"
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
	                                    placeholder=""
	                                    value="${group.grp_addr1}"
	                                    readonly
	                                    />
	                                    <input
	                                    type="text"
	                                    id="grp_addr2"
	                                    name="grp_addr2"
	                                    placeholder=""
	                                    value="${group.grp_addr2}"
	                                    />
	                                </div>
	                                <div class="textAreaTd form_div">
	                                    <label for="grpDescription" class="profile_label">네모소개</label>
	                                    <textarea
	                                    name="grp_intro"
	                                    id="grpDescription"
	                                    placeholder=""
	                                    rows="10"
	                                    maxlength="1000"
	                                    >${group.grp_intro}</textarea>
	                                    <p>(<span>0/1000</span>)</p>
	                                    
	                                </div>
	                                <div class="creatGrp form_div">
	                                    <input
	                                    type="submit"
	                                    name="createGrpBtn"
	                                    id="createGrpBtn"
	                                    class="button"
	                                    value="적용"
	                                    />
	                                    <input
	                                    type="reset"
	                                    name="createCancleBtn"
	                                    id="createCancleBtn"
	                                    class="buttonCancle"
	                                    value="취소"
	                                    />
	                                </div>
	                            </fieldset>
	                        </form>
	                    </div>
	                    
	                </div>
	                <!-- 컨텐츠 종료 -->
	                
	            </div>
	
	        </div>
	        <!-- section2 종료 -->
	        <script>
			  var curMain = "${group.main_name}";
			  var curSub = "${group.sub_name}"
			</script>
	        <script src="${contextPath}/js/modGroup.js"></script>
		</c:forEach>
        <jsp:include page="../footer.jsp" flush="true"></jsp:include>
    </body>
    
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</html>