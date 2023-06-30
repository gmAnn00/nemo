function createKakaoMap1(address) {
  var container = document.getElementById('map');
  var options = {
    center: new kakao.maps.LatLng(37.569947, 126.986187),
    level: 3
  };
  var map = new kakao.maps.Map(container, options);

  var geocoder = new kakao.maps.services.Geocoder();

  geocoder.addressSearch(address, function(result, status) {
    if (status === kakao.maps.services.Status.OK) {
      var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	  var message = "모임 위치 : " + address;
      var resultDiv = document.getElementById('clickLatlng');
      resultDiv.innerHTML = message;

      var marker = new kakao.maps.Marker({
        map: map,
        position: coords
      });

      var infowindow = new kakao.maps.InfoWindow({
        content: '<div style="width:150px;text-align:center;padding:6px 0;">장소</div>'
      });
      infowindow.open(map, marker);

      map.setCenter(coords);
    }
  });
}

//외부 폼태그가 enter키를 먹지 않도록
function handleOuterFormKeyDown(
		event) {
	if (event.keyCode === 13) {
		return false;
	}

}

//함수를 나누는 부분


function createKakaoMap2() {
	var markers = [];

	var container2 = document
			.getElementById('map2'); //지도를 표시할 div
	var options = {
		center : new kakao.maps.LatLng(
				37.569947,
				126.986187), //지도의 중심좌표
		level : 3
	//지도 확대레벨
	};

	//지도를 생성
	var map = new kakao.maps.Map(
			container2, options);

	// 장소 검색 객체를 생성합니다
	var ps = new kakao.maps.services.Places();

	// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
	var infowindow = new kakao.maps.InfoWindow(
			{
				zIndex : 1
			});

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
		ps.keywordSearch(keyword, placesSearchCB);
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

		var listEl = document
				.getElementById('placesList'), menuEl = document
				.getElementById('menu_wrap'), fragment = document
				.createDocumentFragment(), bounds = new kakao.maps.LatLngBounds(), listStr = '';

		// 검색 결과 목록에 추가된 항목들을 제거합니다
		removeAllChildNods(listEl);

		// 지도에 표시되고 있는 마커를 제거합니다
		removeMarker();

		for (var i = 0; i < places.length; i++) {

			// 마커를 생성하고 지도에 표시합니다
			var placePosition = new kakao.maps.LatLng(
					places[i].y,
					places[i].x), marker = addMarker(
					placePosition,
					i), itemEl = getListItem(
					i, places[i]); // 검색 결과 항목 Element를 생성합니다

			// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
			// LatLngBounds 객체에 좌표를 추가합니다
			bounds.extend(placePosition);

			// 마커와 검색결과 항목에 mouseover 했을때
			// 해당 장소에 인포윈도우에 장소명을 표시합니다
			// mouseout 했을 때는 인포윈도우를 닫습니다
			(function(marker, title) {
				kakao.maps.event.addListener(marker,'mouseover',function() {
					displayInfowindow(
					marker,
					title);
				});
				kakao.maps.event.addListener(marker,'mouseout',function() {
					infowindow.close();
				});

				itemEl.onmouseover = function() {
					displayInfowindow(marker,title);
				};

				itemEl.onmouseout = function() {
					infowindow.close();
				};
			})
				(marker,places[i].place_name);
				fragment.appendChild(itemEl);
		}

		// 검색결과 항목들을 검색결과 목록 Element에 추가합니다
		listEl.appendChild(fragment);
		menuEl.scrollTop = 0;

		// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
		map.setBounds(bounds);
	}

	// 검색결과 항목을 Element로 반환하는 함수입니다
	function getListItem(index,
			places) {

		var el = document
				.createElement('li'), itemStr = '<span class="markerbg marker_'
				+ (index + 1)
				+ '"></span>'
				+ '<div class="info">'
				+ '   <h5>'
				+ places.place_name
				+ '</h5>';

		if (places.road_address_name) {
			itemStr += '    <span>'
					+ places.road_address_name
					+ '</span>'
					+ '   <span class="jibun gray">'
					+ places.address_name
					+ '</span>';
		} else {
			itemStr += '    <span>'
					+ places.address_name
					+ '</span>';
		}

		itemStr += '  <span class="tel">'
				+ places.phone
				+ '</span>'
				+ '</div>';

		el.innerHTML = itemStr;
		el.className = 'item';

		return el;
	}

	// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
	function addMarker(position,
			idx, title) {
		var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
		imageSize = new kakao.maps.Size(
				36, 37), // 마커 이미지의 크기
		imgOptions = {
			spriteSize : new kakao.maps.Size(
					36, 691), // 스프라이트 이미지의 크기
			spriteOrigin : new kakao.maps.Point(
					0,
					(idx * 46) + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
			offset : new kakao.maps.Point(
					13, 37)
		// 마커 좌표에 일치시킬 이미지 내에서의 좌표
		}, markerImage = new kakao.maps.MarkerImage(
				imageSrc,
				imageSize,
				imgOptions), marker = new kakao.maps.Marker(
				{
					position : position, // 마커의 위치
					image : markerImage
				});

		marker.setMap(map); // 지도 위에 마커를 표출합니다
		markers.push(marker); // 배열에 생성된 마커를 추가합니다

		return marker;
	}

	// 지도 위에 표시되고 있는 마커를 모두 제거합니다
	function removeMarker() {
		for (var i = 0; i < markers.length; i++) {
			markers[i].setMap(null);
		}
		markers = [];
	}

	// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
	function displayPagination(
			pagination) {
		var paginationEl = document
				.getElementById('pagination'), fragment = document
				.createDocumentFragment(), i;

		// 기존에 추가된 페이지번호를 삭제합니다
		while (paginationEl
				.hasChildNodes()) {
			paginationEl
					.removeChild(paginationEl.lastChild);
		}

		for (i = 1; i <= pagination.last; i++) {
			var el = document
					.createElement('a');
			el.href = "#";
			el.innerHTML = i;

			if (i === pagination.current) {
				el.className = 'on';
			} else {
				el.onclick = (function(
						i) {
					return function() {
						pagination
								.gotoPage(i);
					}
				})(i);
			}

			fragment
					.appendChild(el);
		}
		paginationEl
				.appendChild(fragment);
	}

	// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
	// 인포윈도우에 장소명을 표시합니다
	function displayInfowindow(
			marker, title) {
		var content = '<div style="padding:5px;z-index:1;">'
				+ title + '</div>';

		infowindow
				.setContent(content);
		infowindow
				.open(map, marker);
	}

	// 검색결과 목록의 자식 Element를 제거하는 함수입니다
	function removeAllChildNods(el) {
		while (el.hasChildNodes()) {
			el
					.removeChild(el.lastChild);
		}
	}


	//////////////////////////////////////////////////////////////
	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();

	var marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
	infowindow = new kakao.maps.InfoWindow(
			{
				zindex : 1
			}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다

	// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
	searchAddrFromCoords(map
			.getCenter(),
			displayCenterInfo);

	// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
	kakao.maps.event
			.addListener(
					map,
					'click',
					function(
							mouseEvent) {
						searchDetailAddrFromCoords(
								mouseEvent.latLng,
								function(
										result,
										status) {
									if (status === kakao.maps.services.Status.OK) {
										var detailAddr = !!result[0].road_address ? '<div>도로명주소 : '
												+ result[0].road_address.address_name
												+ '</div>'
												: '';
										detailAddr += '<div>지번 주소 : '
												+ '<span id="detailAddr">'
												+ result[0].address.address_name
												+ '</span>'
												+ '</div>';

										var content = '<div class="bAddr">'
												+ '<span class="title">법정동 주소정보</span>'
												+ detailAddr
												+ '</div>';

										// 마커를 클릭한 위치에 표시합니다 
										marker
												.setPosition(mouseEvent.latLng);
										marker
												.setMap(map);

										var spanDetailAddr = result[0].address.address_name;
										document
												.getElementById("detailAddr").value = spanDetailAddr;

										var hiddenValue = document
												.getElementById("detailAddr").value;
										console
												.log(hiddenValue);

										// 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
										infowindow
												.setContent(content);
										infowindow
												.open(
														map,
														marker);
									}

								});
					});

	// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
	kakao.maps.event
			.addListener(
					map,
					'idle',
					function() {
						searchAddrFromCoords(
								map
										.getCenter(),
								displayCenterInfo);
					});

	function searchAddrFromCoords(
			coords, callback) {
		// 좌표로 행정동 주소 정보를 요청합니다
		geocoder.coord2RegionCode(
				coords.getLng(),
				coords.getLat(),
				callback);
	}

	function searchDetailAddrFromCoords(
			coords, callback) {
		// 좌표로 법정동 상세 주소 정보를 요청합니다
		geocoder.coord2Address(
				coords.getLng(),
				coords.getLat(),
				callback);
	}

	// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
	function displayCenterInfo(
			result, status) {
		if (status === kakao.maps.services.Status.OK) {
			var infoDiv = document
					.getElementById('centerAddr');

			for (var i = 0; i < result.length; i++) {
				// 행정동의 region_type 값은 'H' 이므로
				if (result[i].region_type === 'H') {
					infoDiv.innerHTML = result[i].address_name;
					break;
				}
			}
		}
	}
}


function scheduleChk(selScheDate) {
	$.ajax({
		type: "get",
		dataType: "text",
		async: true,
		url: "http://127.0.0.1:8090/nemo/group/schedule/schCompare",
		data: { selScheDate: selScheDate },
		success: function(data) {			//매개변수들은 내가 정해주는 것이지만 보통 쓰는 단어들이 있다.
			console.log(data);
			if (data) {
				let scheduleInfo = JSON.parse(data);
				if (scheduleInfo.schedule && scheduleInfo.schedule !== "null") {
					// 값이 있는 경우 처리
					$('.scheduleDetailArea').css('display', 'block');
					$('.scheduleEdit').css('display', 'none');
					console.log("Date: " + scheduleInfo.schedule.substring(0, 10));
					var address = scheduleInfo.location;
					
					setTimeout(function() {
			            createKakaoMap1(address);
			            $('.scheduleTitle strong').text(scheduleInfo.sche_title);
			            $('#schedule').text("날짜 :" + scheduleInfo.schedule);
			            $('#sche_time').text("시간 : " + scheduleInfo.sche_time);
			            $('#sche_cont').text(scheduleInfo.sche_cont);
			          }, 1);
					
					//setTimeout(createKakaoMap1(address), 1);
					
				} else {
					// 값이 없는 경우 처리
					console.log("값이 없습니다.");
					$('.scheduleDetailArea').css('display', 'none');
					$('.scheduleEdit').css('display', 'block');
					
					setTimeout(function() {
			            createKakaoMap2();
			            $('.scheduleTitle strong').empty();
			            $('#sche_cont').empty();
			          }, 1);
					
					//setTimeout(createKakaoMap2(), 1);
				}
			} else {
				// 값이 없는 경우 처리
				console.log("값이 없습니다.");
			}
		},
		error: function(xhr, status, error) {
			alert("에러 발생: " + error + ", " + status);
		},
		/*complete : function (data) {
			alert("작업 확인");						//작업 완료후 처리할 내용
		}*/
	});
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
window.onload = function(){
	calendarMaker($("#myScheduleCalendarArea"), new Date());
	
	let nowDate = new Date();
	
	function calendarMaker(target, date) {
	  if (date == null || undefined) {
	    date = new Date();
	  }
	  let nowDate = date;
	  if ($(target).length > 0) {
	    let year = nowDate.getFullYear();
	    let month = nowDate.getMonth() + 1;
	    $(target).empty().append(assembly(year, month));
	  } else {
	    console.error("calendar Target is empty");
	    return;
	  }
	
	  let thisMonth = new Date(nowDate.getFullYear(), nowDate.getMonth(), 1);
	  let thisLastDay = new Date(nowDate.getFullYear(), nowDate.getMonth() + 1, 0);
	
	  let tag = "<tr>";
	  let cnt = 0;
	
	  // 빈 공백 만들어주기
	  for (let i = 0; i < thisMonth.getDay(); i++) {
	    tag += "<td></td>";
	    cnt++;
	  }
	
	  // 날짜 채우기
	  for (let i = 1; i <= thisLastDay.getDate(); i++) {
	    if (cnt % 7 == 0) {
	      tag += "<tr>";
	    }
	    tag += "<td>" + i + "<div class='displayReserveContainer'></div></td>";
	    cnt++;
	    if (cnt % 7 == 0) {
	      tag += "</tr>";
	    }
	  }
	
	  $(target).find("#setDate").append(tag);
	  calMoveEvtFn();
	
	  function assembly(year, month) {
	    let calendarHTMLCode =
	      "<table class='calendarTable'>" +
	      "<caption class='calDate'>" +
	      "<button type='button' class='prev btn'>< 이전 달</button>" +
	      "<span>" +
	      year +
	      "년 " +
	      month +
	      "월</span>" +
	      "<button type='button' class='today btn'>오늘</button>" +
	      "<button type='button' class='next btn'>다음 달 ></button>" +
	      "</caption>" +
	      "<thead  class='calWeek'>" +
	      "<th class='red'>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th class='blue'>토</th>" +
	      "</thead>" +
	      "<tbody id='setDate'>" +
	      "</tbody>" +
	      "</table>";
	    return calendarHTMLCode;
	  } // end of assembly
	
	  function calMoveEvtFn() {
	    // 이전 달 클릭
	    $(".calendarTable").on("click", ".prev", function () {
	      nowDate = new Date(
	        nowDate.getFullYear(),
	        nowDate.getMonth() - 1,
	        nowDate.getDate()
	      );
	      calendarMaker($(target), nowDate);
	      passedDay(nowDate);
	    });
	
	    // 다음달 클릭
	    $(".calendarTable").on("click", ".next", function () {
	      nowDate = new Date(
	        nowDate.getFullYear(),
	        nowDate.getMonth() + 1,
	        nowDate.getDate()
	      );
	      calendarMaker($(target), nowDate);
	      passedDay(nowDate);
	    });
	
	    //일자 선택 클릭
	    $(".calendarTable").on("click", "td", function () {
	      let cName = $(this).attr("class");
	      //   console.log(cName);
	      let canSelect = cName.indexOf("passedDay");
	      $("td.selectDay").removeClass("selectDay");
	      $(this).removeClass("selectDay").addClass("selectDay");
	      if (canSelect == -1) {
	        
	      }
	      
	      
	      //추가
	      let selectedYear = nowDate.getFullYear(); // 선택한 년도
	      let selectedMonth = nowDate.getMonth() + 1; // 선택한 월
	      let selectedDate = $(this).text(); // 선택한 일
	      
		  if (selectedMonth < 10) {
		    selectedMonth = "0" + selectedMonth;
		  }
		  if (selectedDate < 10) {
		    selectedDate = "0" + selectedDate;
		  }
		  
	      
	      let selScheDate = selectedYear + "-" + selectedMonth + "-" + selectedDate;
	
	      console.log("선택된 날짜:", selScheDate);
	      
	      scheduleChk(selScheDate);
	      
	      
	    });
	
	    // 오늘 클릭
	    $(".calendarTable").on("click", ".today", function () {
	      nowDate = new Date();
	      calendarMaker($(target), nowDate);
	      passedDay(nowDate);
	      $("td")
	        .filter(function () {
	          return $(this).text() == nowDate.getDate();
	        })
	        .removeClass("selectDay")
	        .addClass("selectDay");
	    });
	
	    // 빈칸 선택 안함
	    $("td")
	      .filter(function () {
	        return $(this).text() !== "";
	      })
	      .addClass("hasDay");
	
	    // 토요일들 파란색 줌
	    $("td")
	      .filter(function () {
	        return $(this).index("td") % 7 == 6;
	      })
	      .addClass("blue");
	
	    // 일요일들 빨간색 줌
	    $("td")
	      .filter(function () {
	        return $(this).index("td") % 7 == 0;
	      })
	      .addClass("red");
	  } // end of calMoveEvtFn
	
	  // 오늘 이전은 선택/호버 안되게 함
	  function passedDay(date) {
	    let today = new Date();
	  }
	  /*
	  function passedDay(date) {
	    let today = new Date();
	    if (
	      date.getFullYear() == today.getFullYear() &&
	      date.getMonth() == today.getMonth()
	    ) {
	      $("td")
	        .filter(function () {
	          return parseInt($(this).text()) < parseInt(today.getDate());
	        })
	        .addClass("passedDay");
	    } else if (date < today) {
	      $("td").addClass("passedDay");
	    }
	  }
	
	  passedDay(nowDate);
	  */
	} // end of calendarMaker

}