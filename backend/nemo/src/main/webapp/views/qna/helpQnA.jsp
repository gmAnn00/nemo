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
<meta charset="EUC-KR">
<title>관리자-소모임 관리 페이지</title>
        <link rel="shortcut icon" href="../../images/favicon.png" />
        <link rel="stylesheet" href="../../css/normalize.css" />
        <link rel="stylesheet" href="../../css/common.css" />
        <link rel="stylesheet" href="../../css/header.css" />
        <link rel="stylesheet" href="../../css/submenu.css" />
        <link rel="stylesheet" href="../../css/adminHelpQnA.css" />
        <script src="../../js/jquery-3.6.4.min.js"></script>
        <script src="https://kit.fontawesome.com/3d4603cd1d.js" crossorigin="anonymous"></script>
        <script src="../../js/header.js"></script>
    </head>
<body>
	<jsp:include page="../../header.jsp" flush="true"></jsp:include>
	
	        <!-- 콘텐츠 영역 시작 -->
        <div id="section2">
            <div class="sc2_contents">
                <!-- 메뉴바 시작 -->
                <div class="sc2_menu_contents">
                    <div class="sc2_menu">
                        <h2 class="sc2_menu_title">관리자</h2>
                        <ul class="sc2_menu_list">
                            <li>
                                <a href="adminGroup.html">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name">
                                            <span>소모임 관리</span>
                                        </div>
                                        <i class="fa-solid fa-angle-right"></i>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="adminMember.html">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name"><span>회원 관리</span></div>
                                        <i class="fa-solid fa-angle-right"></i>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="adminHelpQnA.html">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name">
                                            <span>고객센터 Q&A</span>
                                        </div>
                                        <i class="fa-solid fa-minus"></i>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="adminHelp.html">
                                    <div class="sc2_icon_menu">
                                        <div class="menu_submenu_name">
                                            <span>고객센터 1:1 문의</span>
                                        </div>
                                        <i class="fa-solid fa-angle-right"></i>
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <!-- 메뉴바 종료 -->

                <div class="sc2_subsection">
                    <div class="sc2_subsection_title">
                        <h2 class="sc2_subsection_title_name">고객센터 Q&A</h2>
                        <!-- nav 바 시작 -->
                        <div class="nav_bar">
                            <a href="index.html">
                                <i class="fa-solid fa-house"></i>
                            </a>
                            <i class="fa-solid fa-angle-right"></i>
                            <span>고객센터</span>
                            <i class="fa-solid fa-angle-right"></i>
                            <span>Q&A</span>
                        </div>
                        <!-- nav 바 종료 -->
                    </div>
                    <div class="searchContainer">
                        <form action="#">
                            <select name="searchKeyword" id="searchKeyword">
                                <option value="title" selected>제목</option>
                                <option value="content">내용</option>
                                <option value="writer">작성자</option>
                            </select>
                            <input type="text" name="searchInput" />
                            <button href="#" class="btn">검색</button>
                        </form>
                        <div class="boardListArea">
                            <table class="boardList">
                                <tr class="boardListHead">
                                    <th>번호</th>

                                    <th>제목</th>
                                    <th>작성자</th>
                                    <th>작성일</th>
                                </tr>
                                <tbody>
                                    <tr>
                                        <td>100010</td>
                                        <td>자유</td>
                                        <td>제목입니다</td>
                                        <td>mornstar</td>
                                        <td>23.05.06</td>
                                    </tr>
                                    <tr>
                                        <td>100009</td>
                                        <td>자유</td>
                                        <td>제목입니다</td>
                                        <td>mornstar</td>
                                        <td>23.05.06</td>
                                    </tr>
                                    <tr>
                                        <td>100008</td>
                                        <td>자유</td>
                                        <td>제목입니다</td>
                                        <td>mornstar</td>
                                        <td>23.05.06</td>
                                    </tr>
                                    <tr>
                                        <td>100007</td>
                                        <td>자유</td>
                                        <td>제목입니다</td>
                                        <td>mornstar</td>
                                        <td>23.05.06</td>
                                    </tr>
                                    <tr>
                                        <td>100006</td>
                                        <td>자유</td>
                                        <td>제목입니다</td>
                                        <td>mornstar</td>
                                        <td>23.05.06</td>
                                    </tr>
                                    <tr>
                                        <td>100005</td>
                                        <td>자유</td>
                                        <td>제목입니다</td>
                                        <td>mornstar</td>
                                        <td>23.05.06</td>
                                    </tr>
                                    <tr>
                                        <td>100004</td>
                                        <td>자유</td>
                                        <td>제목입니다</td>
                                        <td>mornstar</td>
                                        <td>23.05.06</td>
                                    </tr>
                                    <tr>
                                        <td>100003</td>
                                        <td>자유</td>
                                        <td>제목입니다</td>
                                        <td>mornstar</td>
                                        <td>23.05.06</td>
                                    </tr>
                                    <tr>
                                        <td>100002</td>
                                        <td>자유</td>
                                        <td>제목입니다</td>
                                        <td>mornstar</td>
                                        <td>23.05.06</td>
                                    </tr>
                                    <tr>
                                        <td>100001</td>
                                        <td>자유</td>
                                        <td>제목입니다</td>
                                        <td>mornstar</td>
                                        <td>23.05.06</td>
                                    </tr>
                                </tbody>
                            </table>
                            <div class="pagenation">
                                <span>&lt&lt</span><span>&lt</span><span>1</span><span>2</span><span>3</span><span>4</span><span>5</span
                                ><span>&gt</span><span>&gt&gt</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
	
	<jsp:include page="../../footer.jsp" flush="true"></jsp:include>
</body>
</html>