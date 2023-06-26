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
<link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
<link rel="stylesheet" href="${contextPath}/css/normalize.css" />
<link rel="stylesheet" href="${contextPath}/css/common.css" />
<link rel="stylesheet" href="${contextPath}/css/index.css" />
<script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/index.js"></script>
<script src="https://kit.fontawesome.com/f9a2702e84.js" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="header.jsp" flush="true"></jsp:include>
	
	<!--메인 이미지 영역-->
        <div class="intro">
            <div class="intro-slideshow">
                <img src="${contextPath}/images/main_bike2.jpg" />
                <img src="${contextPath}/images/main_book.jpg" />
                <img src="${contextPath}/images/main_music2.jpg" />
                <img src="${contextPath}/images/main_pet.jpg" />
                <img src="${contextPath}/images/main_culture.jpg.jpg" />
            </div>
            <div class="intro-header">
                <h1>새로운 경험과<br> 친구를 만나보세요</h1>
                <p>소모임 멤버들 간의 소통을 위한 온라인 그룹 플랫폼 <br>여러분의 다양한 관심사와 취향을 함께 나눌 수 있는 네모입니다</p>
            </div>
        </div>

        <!-- 카테고리 슬라이드 영역 시작 -->
        <div class="categories">
            <div class="categoriesText">
                <h1 class="categories_title">소모임 카테고리</h1>
            </div>

            <div class="flowBanner1">
                <ul class="list01">
                    <li>
                        <div class="imgBox sle1">
                            <span class="box_text">문화,공연</span>
                        </div>
                    </li>
                    <li>
                        <div class="imgBox sle2">
                            <span class="box_text">음악,악기</span>
                        </div>
                    </li>
                    <li>
                        <div class="imgBox sle3">
                            <span class="box_text">사진,영상</span>
                        </div>
                    </li>
                    <li>
                        <div class="imgBox sle4">
                            <span class="box_text">아웃도어</span>
                        </div>
                    </li>
                    <li>
                        <div class="imgBox sle5"><span class="box_text">스포츠</span></div>
                    </li>
                    <li>
                        <div class="imgBox sle6">
                            <span class="box_text">인문학,책</span>
                        </div>
                    </li>
                    <li>
                        <div class="imgBox sle7"><span class="box_text">언어</span></div>
                    </li>
                    <li>
                        <div class="imgBox sle8"><span class="box_text">공예</span></div>
                    </li>
                </ul>
            </div>

            <div class="flowBanner2">
                <ul class="list02">
                    <li>
                        <div class="imgBox sle9"><span class="box_text">댄스</span></div>
                    </li>
                    <li>
                        <div class="imgBox sle10"><span class="box_text">봉사</span></div>
                    </li>
                    <li>
                        <div class="imgBox sle11"><span class="box_text">사교</span></div>
                    </li>
                    <li>
                        <div class="imgBox sle12"><span class="box_text">자동차</span></div>
                    </li>
                    <li>
                        <div class="imgBox sle13">
                            <span class="box_text">경기관람</span>
                        </div>
                    </li>
                    <li>
                        <div class="imgBox sle14"><span class="box_text">게임</span></div>
                    </li>
                    <li>
                        <div class="imgBox sle15"><span class="box_text">요리</span></div>
                    </li>
                    <li>
                        <div class="imgBox sle16">
                            <span class="box_text">반려동물</span>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <!-- 카테고리 슬라이드 영역 종료 -->
        
        <!-- 소모임 가입하기, 만들기 영역 시작 -->
        <div class="group_jm">
            <div class="jm_text">
                <h2>여기는 다함께 즐기는 공간 <span>네모</span>입니다</h2>
                <p>좋아하는 활동에 참여하여 새로운 친구들을 만들고 즐거운 시간을 함께 보내세요</p>
            </div>
            
            <div class="group_jm_boxes">
                
                <div class="group_jm_box jm_box1">
                    <div class="group_jm_text">
                        <span>소모임 만들기</span>
                        <p>좋아하는 취미생활을 주변사람들과 같이 함께 시작해보세요</p>
                    </div>
                    <div class="group_btn">
                        <span class="btn_more">더보기</span>
                        <i class="fa-solid fa-angle-right"></i>
                    </div>
                    <img class="group_jm_img jm_img1" src="./images/—Pngtree—people make puzzles concept team_5356575.png" alt="bg">
                </div>
                
                <div class="group_jm_box jm_box2">
                    <div class="group_jm_text">
                        <span>소모임 가입하기</span>
                        <p>새로운 아이디어를 공유하고 서로의 경험을 나누어 보세요</p>
                    </div>
                    <div class="group_btn">
                        <span class="btn_more">더보기</span>
                        <i class="fa-solid fa-angle-right"></i>
                    </div>
                    <img class="group_jm_img jm_img2" src="./images/—Pngtree—best friends happy taking selfie_5435706.png" alt="bg">
                </div>

            </div>
        </div>
        
        <!-- 소모임 가입하기, 만들기 영역 종료 -->
        
        
        <div class="group">
            <!-- 관심사, 소모임 보여주는 영역 시작 -->
            <div class="smallPage">
                <div class="smallText">
                    <h3 class="smallText_title">관심사 소모임</h3>
                </div>
                <div class="cards">
                    <div class="card card--1">
                        <div class="card__info-hover">
                            <svg class="card__like"  viewBox="0 0 24 24">
                                <path fill="#000000" d="M12.1,18.55L12,18.65L11.89,18.55C7.14,14.24 4,11.39 4,8.5C4,6.5 5.5,5 7.5,5C9.04,5 10.54,6 11.07,7.36H12.93C13.46,6 14.96,5 16.5,5C18.5,5 20,6.5 20,8.5C20,11.39 16.86,14.24 12.1,18.55M16.5,3C14.76,3 13.09,3.81 12,5.08C10.91,3.81 9.24,3 7.5,3C4.42,3 2,5.41 2,8.5C2,12.27 5.4,15.36 10.55,20.03L12,21.35L13.45,20.03C18.6,15.36 22,12.27 22,8.5C22,5.41 19.58,3 16.5,3Z" />
                            </svg>
                        </div>
                        <div class="card__img"></div>
                        <a href="#" class="card_link">
                            <div class="card__img--hover"></div>
                        </a>
                        <div class="card__info">
                            <span class="card__category">대분류</span>
                            <span class="card__category">소분류</span>
                            <h3 class="card__title">이젠종로학원</h3>
                            <span class="card__by"><i class="fa-solid fa-location-dot"></i> <a href="#" class="card__author">서울 종로구</a></span>
                        </div>
                    </div>
                    
                    
                    <div class="card card--2">
                        <div class="card__info-hover">
                            <svg class="card__like"  viewBox="0 0 24 24">
                                <path fill="#000000" d="M12.1,18.55L12,18.65L11.89,18.55C7.14,14.24 4,11.39 4,8.5C4,6.5 5.5,5 7.5,5C9.04,5 10.54,6 11.07,7.36H12.93C13.46,6 14.96,5 16.5,5C18.5,5 20,6.5 20,8.5C20,11.39 16.86,14.24 12.1,18.55M16.5,3C14.76,3 13.09,3.81 12,5.08C10.91,3.81 9.24,3 7.5,3C4.42,3 2,5.41 2,8.5C2,12.27 5.4,15.36 10.55,20.03L12,21.35L13.45,20.03C18.6,15.36 22,12.27 22,8.5C22,5.41 19.58,3 16.5,3Z" />
                            </svg>
                        </div>
                        <div class="card__img"></div>
                        <a href="#" class="card_link">
                            <div class="card__img--hover"></div>
                        </a>
                        <div class="card__info">
                            <span class="card__category">대분류</span>
                            <span class="card__category">소분류</span>
                            <h3 class="card__title">이젠종로학원</h3>
                            <span class="card__by"><i class="fa-solid fa-location-dot"></i> <a href="#" class="card__author">서울 종로구</a></span>
                        </div>
                    </div>
                    
                    <div class="card card--3">
                        <div class="card__info-hover">
                            <svg class="card__like"  viewBox="0 0 24 24">
                                <path fill="#000000" d="M12.1,18.55L12,18.65L11.89,18.55C7.14,14.24 4,11.39 4,8.5C4,6.5 5.5,5 7.5,5C9.04,5 10.54,6 11.07,7.36H12.93C13.46,6 14.96,5 16.5,5C18.5,5 20,6.5 20,8.5C20,11.39 16.86,14.24 12.1,18.55M16.5,3C14.76,3 13.09,3.81 12,5.08C10.91,3.81 9.24,3 7.5,3C4.42,3 2,5.41 2,8.5C2,12.27 5.4,15.36 10.55,20.03L12,21.35L13.45,20.03C18.6,15.36 22,12.27 22,8.5C22,5.41 19.58,3 16.5,3Z" />
                            </svg>
                        </div>
                        <div class="card__img"></div>
                        <a href="#" class="card_link">
                            <div class="card__img--hover"></div>
                        </a>
                        <div class="card__info">
                            <span class="card__category">대분류</span>
                            <span class="card__category">소분류</span>
                            <h3 class="card__title">이젠종로학원</h3>
                            <span class="card__by"><i class="fa-solid fa-location-dot"></i> <a href="#" class="card__author">서울 종로구</a></span>
                        </div>
                    </div>

                    <div class="card card--4">
                        <div class="card__info-hover">
                            <svg class="card__like"  viewBox="0 0 24 24">
                                <path fill="#000000" d="M12.1,18.55L12,18.65L11.89,18.55C7.14,14.24 4,11.39 4,8.5C4,6.5 5.5,5 7.5,5C9.04,5 10.54,6 11.07,7.36H12.93C13.46,6 14.96,5 16.5,5C18.5,5 20,6.5 20,8.5C20,11.39 16.86,14.24 12.1,18.55M16.5,3C14.76,3 13.09,3.81 12,5.08C10.91,3.81 9.24,3 7.5,3C4.42,3 2,5.41 2,8.5C2,12.27 5.4,15.36 10.55,20.03L12,21.35L13.45,20.03C18.6,15.36 22,12.27 22,8.5C22,5.41 19.58,3 16.5,3Z" />
                            </svg>
                        </div>
                        <div class="card__img"></div>
                        <a href="#" class="card_link">
                            <div class="card__img--hover"></div>
                        </a>
                        <div class="card__info">
                            <span class="card__category">대분류</span>
                            <span class="card__category">소분류</span>
                            <h3 class="card__title">이젠종로학원</h3>
                            <span class="card__by"><i class="fa-solid fa-location-dot"></i> <a href="#" class="card__author">서울 종로구</a></span>
                        </div>
                    </div>
                    
                </div>
            </div>
            <!-- 관심사, 소모임 보여주는 영역 종료 -->
            
            <!--가까운 소모임 영역 시작 -->
            <div class="smallPage">
                <div class="smallText">
                    <h3 class="smallText_title">가까운 소모임</h3>
                </div>
                <div class="cards">
                    
                    <div class="card card--5">
                        <div class="card__info-hover">
                            <svg class="card__like"  viewBox="0 0 24 24">
                                <path fill="#000000" d="M12.1,18.55L12,18.65L11.89,18.55C7.14,14.24 4,11.39 4,8.5C4,6.5 5.5,5 7.5,5C9.04,5 10.54,6 11.07,7.36H12.93C13.46,6 14.96,5 16.5,5C18.5,5 20,6.5 20,8.5C20,11.39 16.86,14.24 12.1,18.55M16.5,3C14.76,3 13.09,3.81 12,5.08C10.91,3.81 9.24,3 7.5,3C4.42,3 2,5.41 2,8.5C2,12.27 5.4,15.36 10.55,20.03L12,21.35L13.45,20.03C18.6,15.36 22,12.27 22,8.5C22,5.41 19.58,3 16.5,3Z" />
                            </svg>
                        </div>
                        <div class="card__img"></div>
                        <a href="#" class="card_link">
                            <div class="card__img--hover"></div>
                        </a>
                        <div class="card__info">
                            <span class="card__category">대분류</span>
                            <span class="card__category">소분류</span>
                            <h3 class="card__title">이젠종로학원</h3>
                            <span class="card__by"><i class="fa-solid fa-location-dot"></i> <a href="#" class="card__author">서울 종로구</a></span>
                        </div>
                    </div>
                    
                    
                    <div class="card card--6">
                        <div class="card__info-hover">
                            <svg class="card__like"  viewBox="0 0 24 24">
                                <path fill="#000000" d="M12.1,18.55L12,18.65L11.89,18.55C7.14,14.24 4,11.39 4,8.5C4,6.5 5.5,5 7.5,5C9.04,5 10.54,6 11.07,7.36H12.93C13.46,6 14.96,5 16.5,5C18.5,5 20,6.5 20,8.5C20,11.39 16.86,14.24 12.1,18.55M16.5,3C14.76,3 13.09,3.81 12,5.08C10.91,3.81 9.24,3 7.5,3C4.42,3 2,5.41 2,8.5C2,12.27 5.4,15.36 10.55,20.03L12,21.35L13.45,20.03C18.6,15.36 22,12.27 22,8.5C22,5.41 19.58,3 16.5,3Z" />
                            </svg>
                        </div>
                        <div class="card__img"></div>
                        <a href="#" class="card_link">
                            <div class="card__img--hover"></div>
                        </a>
                        <div class="card__info">
                            <span class="card__category">대분류</span>
                            <span class="card__category">소분류</span>
                            <h3 class="card__title">이젠종로학원</h3>
                            <span class="card__by"><i class="fa-solid fa-location-dot"></i> <a href="#" class="card__author">서울 종로구</a></span>
                        </div>
                    </div>
                    
                    <div class="card card--7">
                        <div class="card__info-hover">
                            <svg class="card__like"  viewBox="0 0 24 24">
                                <path fill="#000000" d="M12.1,18.55L12,18.65L11.89,18.55C7.14,14.24 4,11.39 4,8.5C4,6.5 5.5,5 7.5,5C9.04,5 10.54,6 11.07,7.36H12.93C13.46,6 14.96,5 16.5,5C18.5,5 20,6.5 20,8.5C20,11.39 16.86,14.24 12.1,18.55M16.5,3C14.76,3 13.09,3.81 12,5.08C10.91,3.81 9.24,3 7.5,3C4.42,3 2,5.41 2,8.5C2,12.27 5.4,15.36 10.55,20.03L12,21.35L13.45,20.03C18.6,15.36 22,12.27 22,8.5C22,5.41 19.58,3 16.5,3Z" />
                            </svg>
                        </div>
                        <div class="card__img"></div>
                        <a href="#" class="card_link">
                            <div class="card__img--hover"></div>
                        </a>
                        <div class="card__info">
                            <span class="card__category">대분류</span>
                            <span class="card__category">소분류</span>
                            <h3 class="card__title">이젠종로학원</h3>
                            <span class="card__by"><i class="fa-solid fa-location-dot"></i> <a href="#" class="card__author">서울 종로구</a></span>
                        </div>
                    </div>

                    <div class="card card--8">
                        <div class="card__info-hover">
                            <svg class="card__like"  viewBox="0 0 24 24">
                                <path fill="#000000" d="M12.1,18.55L12,18.65L11.89,18.55C7.14,14.24 4,11.39 4,8.5C4,6.5 5.5,5 7.5,5C9.04,5 10.54,6 11.07,7.36H12.93C13.46,6 14.96,5 16.5,5C18.5,5 20,6.5 20,8.5C20,11.39 16.86,14.24 12.1,18.55M16.5,3C14.76,3 13.09,3.81 12,5.08C10.91,3.81 9.24,3 7.5,3C4.42,3 2,5.41 2,8.5C2,12.27 5.4,15.36 10.55,20.03L12,21.35L13.45,20.03C18.6,15.36 22,12.27 22,8.5C22,5.41 19.58,3 16.5,3Z" />
                            </svg>
                        </div>
                        <div class="card__img"></div>
                        <a href="#" class="card_link">
                            <div class="card__img--hover"></div>
                        </a>
                        <div class="card__info">
                            <span class="card__category">대분류</span>
                            <span class="card__category">소분류</span>
                            <h3 class="card__title">이젠종로학원</h3>
                            <span class="card__by"><i class="fa-solid fa-location-dot"></i> <a href="#" class="card__author">서울 종로구</a></span>
                        </div>
                    </div>
                    
                </div>
            </div>
        </div>
        <!--가까운 소모임 영역 종료 -->
	
	<jsp:include page="footer.jsp" flush="true"></jsp:include>
</body>
</html>