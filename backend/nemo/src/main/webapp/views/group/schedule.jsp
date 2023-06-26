<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
 	isELIgnored="false"%>
<%@
	page import = "java.sql.*"
 %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<% 
	request.setCharacterEncoding("utf-8");
%>    
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <title>일정</title>
    <link rel="shortcut icon" href="${contextPath}/images/favicon.png" />
    <link rel="stylesheet" href="${contextPath}/css/normalize.css" />
    <link rel="stylesheet" href="${contextPath}/css/common.css" />    
    <link rel="stylesheet" href="${contextPath}/css/submenu.css" />
    <link rel="stylesheet" href="${contextPath}/css/sectionTitle.css" />
    <link rel="stylesheet" href="${contextPath}/css/schedule.css" />
    <script
      src="https://kit.fontawesome.com/3d4603cd1d.js"
      crossorigin="anonymous"
    ></script>
    <script src="${contextPath}/js/jquery-3.6.4.min.js"></script> 
    <script src="${contextPath}/js/header.js"></script>
    <script src="${contextPath}/js/mySchedule.js"></script>
    <!--<script>
    	const ctx = ${contextPath};
    </script> -->
     
  </head>
  <body>
    <!-- header 시작 -->
    <jsp:include page="../header.jsp" flush="true"></jsp:include>
    <!-- header 종료 -->

    <!-- section1 시작 -->
    <div class="section1">
      <div class="group_containter">
          <div class="group_all">
              <div class="group_img">
                  <img src="${contextPath}/images/free-icon-group-8847475.png" alt="group_img">
              </div>
              <div class="group_name">
                  <a href="${contextPath}/groupMain.html">
                      <span>이젠종로학원</span>
                  </a>
              </div>
              <div class="group_info">
                  <div class="group_info_category">
                      <div class="category_box group_info_category_box">sports</div>
                      <div class="category_box group_info_category_box">game</div>
                  </div>
                  <div class="group_info_member">
                      <div class="group_info_title"><span>멤버수</span></div>
                      <div class="group_info_contents"><span>7</span></div>
                  </div>
                  <div class="group_info_follower">
                      <div class="group_info_title"><span>개설일</span></div>
                      <div class="group_info_contents"><span>2023. 05. 19.</span></div>
                  </div>
              </div>
          </div>
      </div>
    </div>
    <!-- section1 종료 -->

    <!-- 콘텐츠 영역 -->
    <!-- <div id="contentsArea"> -->
    <div class="section2">      
      <div class="sc2_contents">
        <!-- 메뉴바 시작 -->
        <div class="sc2_menu_contents">
          <div class="sc2_menu">
            <h2 class="sc2_menu_title">
              <a href="${contextPath}/groupMain.html">나의 모임</a>
            </h2>
            <ul class="sc2_menu_list">
              <li><a href="${contextPath}/schedule">                    
                <div class="sc2_icon_menu">
                  <div class="menu_submenu_name submenu_select">
                    <span>일정</span>
                  </div>
                  <i class="fa-solid fa-minus submenu_select"></i>
                </div></a>                  
              </li>
              <li><a href="${contextPath}/board/board">
                <div class="sc2_icon_menu">
                  <div class="menu_submenu_name">
                    <span>게시판</span>
                  </div>
                  <i class="fa-solid fa-angle-right menu_angle"></i>
                </div></a>
              </li>
              <li><a href="${contextPath}/myGroupMember.html">
                <div class="sc2_icon_menu">
                  <div class="menu_submenu_name">
                    <span>멤버</span>
                  </div>
                  <i class="fa-solid fa-angle-right menu_angle"></i>
                </div></a>
              </li>
              <li><a href="${contextPath}/groupSetting.html">
                <div class="sc2_icon_menu">
                  <div class="menu_submenu_name">
                    <span>소모임관리</span>
                  </div>
                  <i class="fa-solid fa-angle-right menu_angle"></i>
                </div></a>
              </li>
            </ul>
          </div>
        </div>      
        <!-- 메뉴바 종료 -->
        
        <!-- 일정 등록, 확인 영역 시작 -->
        <div class="schedule sc2_subsection">
          <div class="sc2_subsection_title">
            <h2 class="sc2_subsection_title_name">일정</h2> 
              <!-- nav 바 시작 -->
              <div class="nav_bar">
                <a href="${contextPath}/index">
                  <i class="fa-solid fa-house nav_icon"></i>
                </a>
                <i class="fa-solid fa-angle-right nav_icon"></i>
                <span>나의 모임</span>
                <i class="fa-solid fa-angle-right nav_icon"></i>
                <span>일정</span>
              </div>
            <!-- nav 바 종료 -->           
          </div>

          <div class="calendarSchedule clearfixed">
            <!-- 달력 및 간단일정 영역 -->
            <!-- 달력부분 -->
            <div class="scheduleCalendar">
              <div id="myScheduleCalendarArea">
              
              </div>
            </div>
            <div class="scheduleNP">
              <div class="nextSchedule">
                <strong>예정된 모임일정</strong>
                <div class="nextList">
                  <table>
                    <tr>
                      <th>모임날짜</th>
                      <th>모임제목</th>
                      <th>모임장소</th>
                    </tr>
                    <tr>
                      <td>2023-05-10</td>
                      <td>html,css끝내자</td>
                      <td>이젠종로302호</td>
                    </tr>
                    <tr>
                      <td>2023-05-10</td>
                      <td>html,css끝내자</td>
                      <td>이젠종로302호</td>
                    </tr>
                    <tr>
                      <td>2023-05-10</td>
                      <td>html,css끝내자</td>
                      <td>이젠종로302호</td>
                    </tr>
                  </table>
                </div>
              </div>
              <div class="pastSchedule">
                <strong>지난 모임일정</strong>
                <div class="pastList">
                  <table>
                    <tr>
                      <th>모임날짜</th>
                      <th>모임제목</th>
                      <th>모임장소</th>
                    </tr>
                    <tr>
                      <td>2023-04-27</td>
                      <td>html,css끝내자</td>
                      <td>이젠종로302호</td>
                    </tr>
                    <tr>
                      <td>2023-04-27</td>
                      <td>html,css끝내자</td>
                      <td>이젠종로302호</td>
                    </tr>
                    <tr>
                      <td>2023-04-27</td>
                      <td>html,css끝내자</td>
                      <td>이젠종로302호</td>
                    </tr>
                  </table>
                </div>
              </div>
            </div>
          </div>
          <!-- 상세일정부분 3가지(빈일정, 조회, 등록) 겹쳐져 있는 부분 -->
          
          <div class="scheduleArea clearfixed">
            <!-- 빈 일정 (등록버튼만 있음) -->
            <!-- <div class="scheduleNone">
              <h3>빈 일정</h3>
              <div class="topBtn">
                <a href="#" role="button" class="button btnResi">등록</a>
              </div>
            </div> -->
            <!-- 일정 상세보기 -->
            <form action="">
	            <div class="scheduleDetailArea clearfixed">
	              <div class="scheduleDetail">
	                <div class="detailTop">
	                  <h3>일정 상세보기</h3>
	                </div>
	                <div class="scheduleTitle">
	                  <p><strong>이번에 종각에서 모여요</strong></p>
	                </div>
	                <div class="contentLocationMap">
	                  <div class="content">
	                    <div class="dateTime">
	                      <input type="datetime-local" name="schedule" />
	                    </div>
	                    <div class="contentDetail">
	                      <p>모임 일정 내용</p>
	                      <img src="${contextPath}/images/temp.png" alt="모임사진" />
	                    </div>
	                  </div>
	                  <!-- 모임 위치 -->
	                  <div class="locationMap">
	                    <h4>모임 위치</h4>
	                    <div class="map"></div>
	                    <!-- <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=20b89d26720633d863b9bff60fd0e841"></script>
	                    <script>
	                      var container = document.getElementsByClassName('map')[0];
	                      var options = {
	                        center: new kakao.maps.LatLng(37.569947, 126.986187),
	                        level: 3
	                      };
	
	                      var map = new kakao.maps.Map(container, options);
	                      
	                   // 지도를 클릭한 위치에 표출할 마커입니다
	                      var marker = new kakao.maps.Marker({ 
	                          // 지도 중심좌표에 마커를 생성합니다 
	                          position: map.getCenter() 
	                      }); 
	                      // 지도에 마커를 표시합니다
	                      marker.setMap(map);

	                      // 지도에 클릭 이벤트를 등록합니다
	                      // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
	                      kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
	                          
	                          // 클릭한 위도, 경도 정보를 가져옵니다 
	                          var latlng = mouseEvent.latLng; 
	                          
	                          // 마커 위치를 클릭한 위치로 옮깁니다
	                          marker.setPosition(latlng);
	                          
	                          var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
	                          message += '경도는 ' + latlng.getLng() + ' 입니다';
	                          
	                          var resultDiv = document.getElementById('clickLatlng'); 
	                          resultDiv.innerHTML = message;
	                          
	                      });
	                    </script> -->
	                  </div>
	                </div>
	                <div class="participant clearfixed">
	                  <h4>참석자</h4>
	                  <div class="partMember">
	                    <ul>
	                      <li><img
	                        src="${contextPath}/images/temp.png"
	                        alt="프로필사진"
	                        width="40px"
	                      />
	                        <span>닉네임</span>
	                      </li>
	                      <li><img
	                        src="${contextPath}/images/temp.png"
	                        alt="프로필사진"
	                        width="40px"
	                      />
	                        <span>닉네임</span>
	                      </li>
	                      <li><img
	                        src="${contextPath}/images/temp.png"
	                        alt="프로필사진"
	                        width="40px"
	                      />
	                        <span>닉네임</span>
	                      </li>
	                  </div>
	                
	                  <div class="partBtn clearfixed">
	                    <!-- 한번 더 누르면 참석취소 -->
	                    <a href="#" role="button" class="button btnPart">참석</a>
	                    <!-- <a href="#" role="button" class="button btnPartcan">참석취소</a> -->
	                  </div>
	                </div>
	                <div class="editBtn">
	                <a href="#" role="button" class="buttonCancle btnDel">삭제</a>
	                <a href="#" role="button" class="button btnEdit">수정</a>
	              	</div>
	              </div>
	              
	            </div>
            </form>
            
            <!-- 상세 일정 등록하기 -->
            <form action="${contextPath}/group/schedule/addSchedule?group_id=${param.group_id}" method="post" onkeydown="return handleOuterFormKeyDown(event);">
	            <div class="scheduleEditArea clearfixed">
	              <div class="scheduleEdit">
	                <div class="detailTop">
	                  <h3>상세 일정 등록하기</h3>
	                </div>
	                <div class="scheduleTitle">
	                  <textarea
	                  	name="sche_title"
	                    placeholder="모임일정 제목을 입력하세요"
	                    class="scheduleTitleText"
	                    rows="1"
	                  ></textarea>
	                </div>
	                <div class="contentLocationMap">
	                  <div class="content">
	                    <div class="dateTime">
	                      <input type="datetime-local" name="schedule" id="myDateTimeInput"/>
	                    </div>
	                    <script>
						  var currentDate = new Date().toISOString().slice(0, 16);
						  document.getElementById("myDateTimeInput").min = currentDate;
						</script>
	                    <div class="contentDetail">
	                      <textarea
	                      	name="sche_cont"
	                        placeholder="모임일정 내용을 입력하세요"
	                        class="contentDetailText"
	                        rows="10"
	                      ></textarea>
	                    </div>
	                  </div>
	                  
	                  <!-- 모임 위치 설정 -->
	                  <div class="locationMap">
	                    <h4>모임 위치</h4>
	                    <div class="hAddr">
					        <span class="title">지도중심기준 행정동 주소정보</span>
					        <span id="centerAddr"></span>
					    </div>
	                    <div class="map">
	                    
	                    <!-- 모임 위치 지번 주소 -->
	                    <input type="hidden" id="detailAddr" name="location" value="">
	                    
	                    
	                    <div id="menu_wrap" class="bg_white">
					        <div class="option">
					            <div>
					                <form>
					                    키워드 : <input type="text" value="만남의 광장" id="keyword" size="15" placeholder="만남 장소"> 
					                    <button type="button" onclick="searchPlaces(); return false;">검색하기</button> 
					                </form>
					            </div>
					        </div>
					        <hr>
					        <ul id="placesList"></ul>
					        <div id="pagination"></div>
					    </div>
	                    
	                    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=20b89d26720633d863b9bff60fd0e841&libraries=services"></script>
	                    <script>
	                      var markers = [];
	                    
	                      var container = document.getElementsByClassName('map')[1]; //지도를 표시할 div
	                      var options = {
	                    		  center: new kakao.maps.LatLng(37.569947, 126.986187),	//지도의 중심좌표
	                        level: 3	//지도 확대레벨
	                      };
	                      
						  //지도를 생성
	                      var map = new kakao.maps.Map(container, options);
	                      
	                   	  // 장소 검색 객체를 생성합니다
	                      var ps = new kakao.maps.services.Places();  
						  
	                   // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
	                      var infowindow = new kakao.maps.InfoWindow({zIndex:1});

	                      // 키워드로 장소를 검색합니다
	                      searchPlaces();

	                      // 키워드 검색을 요청하는 함수입니다
	                      function searchPlaces() {

	                          var keyword = document.getElementById('keyword').value;

	                          if (!keyword.replace(/^\s+|\s+$/g, '')) {
	                              alert('키워드를 입력해주세요!');
	                              return false;
	                          }

	                          // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
	                          ps.keywordSearch( keyword, placesSearchCB); 
	                      }

	                      // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
	                      function placesSearchCB(data, status, pagination) {
	                          if (status === kakao.maps.services.Status.OK) {

	                              // 정상적으로 검색이 완료됐으면
	                              // 검색 목록과 마커를 표출합니다
	                              displayPlaces(data);

	                              // 페이지 번호를 표출합니다
	                              displayPagination(pagination);

	                          } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

	                              alert('검색 결과가 존재하지 않습니다.');
	                              return;

	                          } else if (status === kakao.maps.services.Status.ERROR) {

	                              alert('검색 결과 중 오류가 발생했습니다.');
	                              return;

	                          }
	                      }

	                      // 검색 결과 목록과 마커를 표출하는 함수입니다
	                      function displayPlaces(places) {

	                          var listEl = document.getElementById('placesList'), 
	                          menuEl = document.getElementById('menu_wrap'),
	                          fragment = document.createDocumentFragment(), 
	                          bounds = new kakao.maps.LatLngBounds(), 
	                          listStr = '';
	                          
	                          // 검색 결과 목록에 추가된 항목들을 제거합니다
	                          removeAllChildNods(listEl);

	                          // 지도에 표시되고 있는 마커를 제거합니다
	                          removeMarker();
	                          
	                          for ( var i=0; i<places.length; i++ ) {

	                              // 마커를 생성하고 지도에 표시합니다
	                              var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
	                                  marker = addMarker(placePosition, i), 
	                                  itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

	                              // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
	                              // LatLngBounds 객체에 좌표를 추가합니다
	                              bounds.extend(placePosition);

	                              // 마커와 검색결과 항목에 mouseover 했을때
	                              // 해당 장소에 인포윈도우에 장소명을 표시합니다
	                              // mouseout 했을 때는 인포윈도우를 닫습니다
	                              (function(marker, title) {
	                                  kakao.maps.event.addListener(marker, 'mouseover', function() {
	                                      displayInfowindow(marker, title);
	                                  });

	                                  kakao.maps.event.addListener(marker, 'mouseout', function() {
	                                      infowindow.close();
	                                  });

	                                  itemEl.onmouseover =  function () {
	                                      displayInfowindow(marker, title);
	                                  };

	                                  itemEl.onmouseout =  function () {
	                                      infowindow.close();
	                                  };
	                              })(marker, places[i].place_name);

	                              fragment.appendChild(itemEl);
	                          }

	                          // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
	                          listEl.appendChild(fragment);
	                          menuEl.scrollTop = 0;

	                          // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
	                          map.setBounds(bounds);
	                      }

	                      // 검색결과 항목을 Element로 반환하는 함수입니다
	                      function getListItem(index, places) {

	                          var el = document.createElement('li'),
	                          itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
	                                      '<div class="info">' +
	                                      '   <h5>' + places.place_name + '</h5>';

	                          if (places.road_address_name) {
	                              itemStr += '    <span>' + places.road_address_name + '</span>' +
	                                          '   <span class="jibun gray">' +  places.address_name  + '</span>';
	                          } else {
	                              itemStr += '    <span>' +  places.address_name  + '</span>'; 
	                          }
	                                       
	                            itemStr += '  <span class="tel">' + places.phone  + '</span>' +
	                                      '</div>';           

	                          el.innerHTML = itemStr;
	                          el.className = 'item';

	                          return el;
	                      }

	                      // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
	                      function addMarker(position, idx, title) {
	                          var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
	                              imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
	                              imgOptions =  {
	                                  spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
	                                  spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
	                                  offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
	                              },
	                              markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
	                                  marker = new kakao.maps.Marker({
	                                  position: position, // 마커의 위치
	                                  image: markerImage 
	                              });

	                          marker.setMap(map); // 지도 위에 마커를 표출합니다
	                          markers.push(marker);  // 배열에 생성된 마커를 추가합니다

	                          return marker;
	                      }

	                      // 지도 위에 표시되고 있는 마커를 모두 제거합니다
	                      function removeMarker() {
	                          for ( var i = 0; i < markers.length; i++ ) {
	                              markers[i].setMap(null);
	                          }   
	                          markers = [];
	                      }

	                      // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
	                      function displayPagination(pagination) {
	                          var paginationEl = document.getElementById('pagination'),
	                              fragment = document.createDocumentFragment(),
	                              i; 

	                          // 기존에 추가된 페이지번호를 삭제합니다
	                          while (paginationEl.hasChildNodes()) {
	                              paginationEl.removeChild (paginationEl.lastChild);
	                          }

	                          for (i=1; i<=pagination.last; i++) {
	                              var el = document.createElement('a');
	                              el.href = "#";
	                              el.innerHTML = i;

	                              if (i===pagination.current) {
	                                  el.className = 'on';
	                              } else {
	                                  el.onclick = (function(i) {
	                                      return function() {
	                                          pagination.gotoPage(i);
	                                      }
	                                  })(i);
	                              }

	                              fragment.appendChild(el);
	                          }
	                          paginationEl.appendChild(fragment);
	                      }

	                      // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
	                      // 인포윈도우에 장소명을 표시합니다
	                      function displayInfowindow(marker, title) {
	                          var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

	                          infowindow.setContent(content);
	                          infowindow.open(map, marker);
	                      }

	                       // 검색결과 목록의 자식 Element를 제거하는 함수입니다
	                      function removeAllChildNods(el) {   
	                          while (el.hasChildNodes()) {
	                              el.removeChild (el.lastChild);
	                          }
	                      }
	                   	  
	                      //외부 폼태그가 enter키를 먹지 않도록
	                      function handleOuterFormKeyDown(event) {
	                    	  if (event.keyCode === 13) {
	                    	    return false;
	                    	  }
	                    	  
	                      }
	                      
	                      //////////////////////////////////////////////////////////////
	                      // 주소-좌표 변환 객체를 생성합니다
							var geocoder = new kakao.maps.services.Geocoder();
							
							var marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
							    infowindow = new kakao.maps.InfoWindow({zindex:1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다
							
							// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
							searchAddrFromCoords(map.getCenter(), displayCenterInfo);
							
							// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
							kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
							    searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
							        if (status === kakao.maps.services.Status.OK) {
							            var detailAddr = !!result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
							            detailAddr += '<div>지번 주소 : ' + '<span id="detailAddr">' + result[0].address.address_name + '</span>' + '</div>';
							            
							            var content = '<div class="bAddr">' +
							                            '<span class="title">법정동 주소정보</span>' + 
							                            detailAddr + 
							                        '</div>';
							
							            // 마커를 클릭한 위치에 표시합니다 
							            marker.setPosition(mouseEvent.latLng);
							            marker.setMap(map);

							            var spanDetailAddr = result[0].address.address_name;
							            document.getElementById("detailAddr").value = spanDetailAddr;
							            
							            var hiddenValue = document.getElementById("detailAddr").value;
					                    console.log(hiddenValue);
							
							            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
							            infowindow.setContent(content);
							            infowindow.open(map, marker);
							        }   
							        
							    });
							});
							
							
							// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
							kakao.maps.event.addListener(map, 'idle', function() {
							    searchAddrFromCoords(map.getCenter(), displayCenterInfo);
							});
							
							function searchAddrFromCoords(coords, callback) {
							    // 좌표로 행정동 주소 정보를 요청합니다
							    geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
							}
							
							function searchDetailAddrFromCoords(coords, callback) {
							    // 좌표로 법정동 상세 주소 정보를 요청합니다
							    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
							}
							
							// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
							function displayCenterInfo(result, status) {
							    if (status === kakao.maps.services.Status.OK) {
							        var infoDiv = document.getElementById('centerAddr');
							
							        for(var i = 0; i < result.length; i++) {
							            // 행정동의 region_type 값은 'H' 이므로
							            if (result[i].region_type === 'H') {
							                infoDiv.innerHTML = result[i].address_name;
							                break;
							            }
							        }
							    }    
							}
	                   	 	
	                    </script>
	                  </div>
	                  </div>
	                </div>
	                <div class="editBtn">
	                <a href="#" role="button" class="buttonCancle">취소</a>
	                <button type="submit" class="button btnResi" >등록</button>
	              	</div>
	              </div>
	              
	            </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <!-- </div> -->

    <!-- 푸터 영역 시작 -->
    <jsp:include page="../footer.jsp" flush="true"></jsp:include>
    <!-- 푸터 영역 끝 -->
    
  </body>
</html>
    