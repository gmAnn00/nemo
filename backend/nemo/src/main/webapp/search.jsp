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
<title>네모: 동네모임</title>
<link rel="shortcut icon" href="./images/favicon.png" />
<link rel="stylesheet" href="./css/normalize.css" />
<link rel="stylesheet" href="./css/common.css" />
<link rel="stylesheet" href="./css/search.css" />
<script src="./js/jquery-3.6.4.min.js"></script>
<script src="./js/header.js"></script>
<script src="./js/search.js"></script>

<script src="https://kit.fontawesome.com/f9a2702e84.js" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="header.jsp" flush="true"></jsp:include>
	
	<!--카테고리 영역-->
        <div id="contentsArea">
            <div class="categoriesArea">
                <div class="nameSearch">
                    <div class="searchText1">
                        <h2>소모임 이름 검색</h2>
                    </div>
                    <div class="nameBtn">
                        <input type="text" class="searchText" />
                        <form action="#" method="get" class="searchBtn">
                            <button type="button" class="button">검색</button>
                        </form>
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
                <div class="resultText"><h3>검색결과(개수)</h3></div>
                <div class="resultBtn">
                    <button type="button" id="buttonAble" class="buttonCancle">가입가능한 소모임만 표시</button>
                    <button type="button" id="buttonName" class="buttonSort buttonCancle">이름순정렬</button>
                    <button type="button" id="buttonInterest" class="buttonSort buttonCancle">찜순정렬</button>
                    <button type="button" id="buttonMember"class="buttonSort buttonCancle">사람많은순</button>
                </div>
            </div>
            <div class="searchResult">
                <div class="resultGroup">
                    <div class="group">
                        <div class="groupImg Gimg01"></div>
                        <div class="SteamedImg">
                            <button type="button" class="grpLikeBtn" title="네모찜하기">
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
                            </button>
                        </div>
                        <div class="groupText">
                            <div class="groupText01">
                                <span>소모임이름:일렉트로닉 뮤직LA</span>
                            </div>
                            <div class="groupText02"><span>대분류:음악</span><span>소분류:일렉트로닉</span></div>
                            <div class="groupText03">
                                <span>지역:로스엔젤레스,켈리포니아</span>
                            </div>
                            <div class="groupText04">
                                <span
                                    >소모임 소개글:우리의 사명은 로스앤젤레스 카운티에서 전자 음악 행사를 홍보하고 차기 DJ, 프로듀서, 보컬리스트 및
                                    음악 비즈니스 전문가를 위한 기회 네트워크를 만드는 것입니다. 이벤트에 대한 아이디어가 있거나 LA, OC 또는 80인용
                                    요트 이벤트에 대해 협력하고 싶은 경우 support@electronicmusicla.com -- Electronic Music LA</span
                                >
                            </div>
                            <div class="groupText05"><span>개설일:2023년 5월 21일</span></div>
                            <div class="groupText06"><span>찜갯수:30개</span></div>
                        </div>
                    </div>
                </div>
                <div class="GroupMap">
                    <iframe
                        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d395.2960818763124!2d126.98575285615492!3d37.56993589038084!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357ca2e884abcbd9%3A0x153663d78acba97f!2z7J207KCg7JWE7Lm0642w66-47Lu07ZOo7YSw7ZWZ7JuQIOyiheuhnOy6oO2NvOyKpA!5e0!3m2!1sko!2skr!4v1683524555277!5m2!1sko!2skr"
                        frameborder="0"
                        width="100%"
                        height="100%"
                    >
                    </iframe>
                </div>
            </div>
            <div class="PageBtn">
                <span>1 2 3 4 5</span>
            </div>
        </div>
	
	<jsp:include page="footer.jsp" flush="true"></jsp:include>
</body>
</html>