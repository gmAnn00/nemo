let schedulesThisMonthList = [];
let schedulesThisMonthArr = [];
let year = 0;
let month = 0;
let memNum=0;// 소모임 멤버 받아오기

function fn_enable(obj) {
  //this.form이라는 객체를 받아 obj라고 매개변수를 주었다.
  document.getElementById("scheduleTitle").disabled = false; //글제목과 내용과 이미지를 수정할 수 있게끔 만들어주는 작업
  document.getElementById("sche_dateTime").disabled = false;
  document.getElementById("sche_cont").disabled = false;
  document.getElementById("button_modify").style.display = "block";
  document.getElementById("editButton").style.display = "none";
  //let address = document.getElementById("addressForMod").innerText;
  //createKakaoMap3(address);
  $(".menu_wrap_class").css("display", "block");
  //document.getElementsByClassName("menu_wrap2").style.display= "block";
  setTimeout(function () {
    /*
		let oldMap = document.getElementById('map');
		oldMap.remove();
		let newMapDiv = document.getElementById('newMap');
		let childrenList = newMapDiv.childNodes;
		let h4 = childrenList[0];
		
		let newMap = document.createElement("div");
		newMap.className = "map";
		newMap.id = "map";
		
		h4.after(newMap);
		*/
    //document.getElementById('map').style = "";
    let address = document.getElementById("addressForMod").innerText;

    createKakaoMap3(address);
  }, 1);
}

function fn_mod_schedule() {
  //document.getElementById("menu_wrap").style.display="block";
  //
  setTimeout(function () {
    /*
		let oldMap = document.getElementById('map');
		oldMap.remove();
		let newMapDiv = document.getElementById('newMap');
		let childrenList = newMapDiv.childNodes;
		let h4 = childrenList[0];
		
		let newMap = document.createElement("div");
		newMap.className = "map";
		newMap.id = "map";
		
		h4.after(newMap);
		*/
    //document.getElementById('map').style = "";
    let address = document.getElementById("addressForMod").innerText;

    createKakaoMap3(address);
  }, 1);
}

function createKakaoMap1(address) {
  let container = document.getElementById("map");
  let options = {
    center: new kakao.maps.LatLng(37.569947, 126.986187),
    level: 3,
  };
  let map = new kakao.maps.Map(container, options);

  let geocoder = new kakao.maps.services.Geocoder();

  geocoder.addressSearch(address, function (result, status) {
    if (status === kakao.maps.services.Status.OK) {
      let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
      let message = "<span id='addressForMod'>" + address + "</span>";
      let resultDiv = document.getElementById("clickLatlng");
      resultDiv.innerHTML = message;

      let marker = new kakao.maps.Marker({
        map: map,
        position: coords,
      });

      let infowindow = new kakao.maps.InfoWindow({
        content: '<div style="width:150px;text-align:center;padding:6px 0;">장소</div>',
      });
      infowindow.open(map, marker);

      map.setCenter(coords);
    }
  });
}

//외부 폼태그가 enter키를 먹지 않도록

function handleOuterFormKeyDown(event) {
  if (event.keyCode === 13) {
    return false;
  }
}

//함수를 나누는 부분

function createKakaoMap2() {
  let markers = [];

  let container2 = document.getElementById("map2"); //지도를 표시할 div
  let options = {
    center: new kakao.maps.LatLng(37.569947, 126.986187), //지도의 중심좌표
    level: 3,
    //지도 확대레벨
  };

  //지도를 생성
  let map = new kakao.maps.Map(container2, options);

  // 장소 검색 객체를 생성합니다
  let ps = new kakao.maps.services.Places();

  // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
  let infowindow = new kakao.maps.InfoWindow({
    zIndex: 1,
  });

  // 키워드로 장소를 검색합니다
  searchPlaces();

  // 키워드 검색을 요청하는 함수입니다
  function searchPlaces() {
    let keyword = document.getElementById("keyword2").value;
    console.log("keyword2=" + keyword);

    if (!keyword.replace(/^\s+|\s+$/g, "")) {
      alert("키워드를 입력해주세요!");
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
      //displayPagination(pagination);
    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
      alert("검색 결과가 존재하지 않습니다.");
      return;
    } else if (status === kakao.maps.services.Status.ERROR) {
      alert("검색 결과 중 오류가 발생했습니다.");
      return;
    }
  }

  // 검색 결과 목록과 마커를 표출하는 함수입니다
  function displayPlaces(places) {
    let listEl = document.getElementById("placesList2"),
      menuEl = document.getElementById("menu_wrap2"),
      fragment = document.createDocumentFragment(),
      bounds = new kakao.maps.LatLngBounds(),
      listStr = "";

    // 검색 결과 목록에 추가된 항목들을 제거합니다
    removeAllChildNods(listEl);

    // 지도에 표시되고 있는 마커를 제거합니다
    removeMarker();

    for (let i = 0; i < places.length; i++) {
      // 마커를 생성하고 지도에 표시합니다
      let placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
        marker = addMarker(placePosition, i),
        itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
      // LatLngBounds 객체에 좌표를 추가합니다
      bounds.extend(placePosition);

      // 마커와 검색결과 항목에 mouseover 했을때
      // 해당 장소에 인포윈도우에 장소명을 표시합니다
      // mouseout 했을 때는 인포윈도우를 닫습니다
      (function (marker, title) {
        kakao.maps.event.addListener(marker, "mouseover", function () {
          displayInfowindow(marker, title);
        });
        kakao.maps.event.addListener(marker, "mouseout", function () {
          infowindow.close();
        });

        itemEl.onmouseover = function () {
          displayInfowindow(marker, title);
        };

        itemEl.onmouseout = function () {
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
    let el = document.createElement("li"),
      itemStr = '<span class="markerbg marker_' + (index + 1) + '"></span>' + '<div class="info">' + "   <h5>" + places.place_name + "</h5>";

    if (places.road_address_name) {
      itemStr += "    <span>" + places.road_address_name + "</span>" + '   <span class="jibun gray">' + places.address_name + "</span>";
    } else {
      itemStr += "    <span>" + places.address_name + "</span>";
    }

    itemStr += '  <span class="tel">' + places.phone + "</span>" + "</div>";

    el.innerHTML = itemStr;
    el.className = "item";

    return el;
  }

  // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
  function addMarker(position, idx, title) {
    let imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
      imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
      imgOptions = {
        spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
        spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
        offset: new kakao.maps.Point(13, 37),
        // 마커 좌표에 일치시킬 이미지 내에서의 좌표
      },
      markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
      marker = new kakao.maps.Marker({
        position: position, // 마커의 위치
        image: markerImage,
      });

    marker.setMap(map); // 지도 위에 마커를 표출합니다
    markers.push(marker); // 배열에 생성된 마커를 추가합니다

    return marker;
  }

  // 지도 위에 표시되고 있는 마커를 모두 제거합니다
  function removeMarker() {
    for (let i = 0; i < markers.length; i++) {
      markers[i].setMap(null);
    }
    markers = [];
  }

  // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
  function displayPagination(pagination) {
    let paginationEl = document.getElementById("pagination"),
      fragment = document.createDocumentFragment(),
      i;

    // 기존에 추가된 페이지번호를 삭제합니다
    while (paginationEl.hasChildNodes()) {
      paginationEl.removeChild(paginationEl.lastChild);
    }

    for (i = 1; i <= pagination.last; i++) {
      let el = document.createElement("a");
      el.href = "#";
      el.innerHTML = i;

      if (i === pagination.current) {
        el.className = "on";
      } else {
        el.onclick = (function (i) {
          return function () {
            pagination.gotoPage(i);
          };
        })(i);
      }

      fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
  }

  // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
  // 인포윈도우에 장소명을 표시합니다
  function displayInfowindow(marker, title) {
    let content = '<div style="padding:5px;z-index:1;">' + title + "</div>";

    infowindow.setContent(content);
    infowindow.open(map, marker);
  }

  // 검색결과 목록의 자식 Element를 제거하는 함수입니다
  function removeAllChildNods(el) {
    while (el.hasChildNodes()) {
      el.removeChild(el.lastChild);
    }
  }

  //////////////////////////////////////////////////////////////
  // 주소-좌표 변환 객체를 생성합니다
  let geocoder = new kakao.maps.services.Geocoder();

  let marker = new kakao.maps.Marker(); // 클릭한 위치를 표시할 마커입니다
  /*
	infowindow = new kakao.maps.InfoWindow(
			{
				zindex : 1
			}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다
	*/
  // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
  searchAddrFromCoords(map.getCenter(), displayCenterInfo);

  // 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
  kakao.maps.event.addListener(map, "click", function (mouseEvent) {
    searchDetailAddrFromCoords(mouseEvent.latLng, function (result, status) {
      if (status === kakao.maps.services.Status.OK) {
        let detailAddr = !!result[0].road_address ? "<div>도로명주소 : " + result[0].road_address.address_name + "</div>" : "";
        detailAddr += "<div>지번 주소 : " + '<span id="detailAddr">' + result[0].address.address_name + "</span>" + "</div>";

        let content = '<div class="bAddr">' + '<span class="title">법정동 주소정보</span>' + detailAddr + "</div>";

        // 마커를 클릭한 위치에 표시합니다
        marker.setPosition(mouseEvent.latLng);
        marker.setMap(map);

        let spanDetailAddr = result[0].address.address_name;
        document.getElementById("detailAddr").value = spanDetailAddr;

        let hiddenValue = document.getElementById("detailAddr").value;
        console.log(hiddenValue);

        // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
        infowindow.setContent(content);
        infowindow.open(map, marker);
      }
    });
  });

  // 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
  kakao.maps.event.addListener(map, "idle", function () {
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
      let infoDiv = document.getElementById("centerAddr");

      for (let i = 0; i < result.length; i++) {
        // 행정동의 region_type 값은 'H' 이므로
        if (result[i].region_type === "H") {
          infoDiv.innerHTML = result[i].address_name;
          break;
        }
      }
    }
  }
} // end of createKakaoMap2

function createKakaoMap3(address) {
  let markers = [];

  let container3 = document.getElementById("map"); //지도를 표시할 div
  let options = {
    center: new kakao.maps.LatLng(37.569947, 126.986187), //지도의 중심좌표
    level: 3,
    //지도 확대레벨
  };

  //지도를 생성
  let map = new kakao.maps.Map(container3, options);

  // 주소-좌표 변환 객체를 생성합니다
  let geocoder = new kakao.maps.services.Geocoder();

  // 주소로 좌표를 검색합니다
  geocoder.addressSearch(address, function (result, status) {
    // 정상적으로 검색이 완료됐으면
    if (status === kakao.maps.services.Status.OK) {
      searchPlaces2();
      let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
      console.log("address=", address);
      // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
      map.setCenter(coords);
      //
    }
  });

  // 장소 검색 객체를 생성합니다
  let ps = new kakao.maps.services.Places();

  // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
  /*
	let infowindow = new kakao.maps.InfoWindow(
			{
				zIndex : 1
			});
	*/
  // 키워드로 장소를 검색합니다
  //

  // 키워드 검색을 요청하는 함수입니다
  function searchPlaces2() {
    console.log("searchPlaces");
    //let keyword = address;
    let keyword = document.getElementById("keyword").value;

    if (!keyword.replace(/^\s+|\s+$/g, "")) {
      alert("키워드를 입력해주세요!");
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
      //displayPagination(pagination);
    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
      return;
    } else if (status === kakao.maps.services.Status.ERROR) {
      alert("검색 결과 중 오류가 발생했습니다.");
      return;
    }
  }

  // 검색 결과 목록과 마커를 표출하는 함수입니다
  function displayPlaces(places) {
    let listEl = document.getElementById("placesList"),
      menuEl = document.getElementById("menu_wrap"),
      fragment = document.createDocumentFragment(),
      bounds = new kakao.maps.LatLngBounds(),
      listStr = "";

    // 검색 결과 목록에 추가된 항목들을 제거합니다
    removeAllChildNods(listEl);

    // 지도에 표시되고 있는 마커를 제거합니다
    removeMarker();

    for (let i = 0; i < places.length; i++) {
      // 마커를 생성하고 지도에 표시합니다
      let placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
        marker = addMarker(placePosition, i),
        itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
      // LatLngBounds 객체에 좌표를 추가합니다
      bounds.extend(placePosition);

      // 마커와 검색결과 항목에 mouseover 했을때
      // 해당 장소에 인포윈도우에 장소명을 표시합니다
      // mouseout 했을 때는 인포윈도우를 닫습니다
      (function (marker, title) {
        kakao.maps.event.addListener(marker, "mouseover", function () {
          displayInfowindow(marker, title);
        });
        kakao.maps.event.addListener(marker, "mouseout", function () {
          infowindow.close();
        });

        itemEl.onmouseover = function () {
          displayInfowindow(marker, title);
        };

        itemEl.onmouseout = function () {
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
    let el = document.createElement("li"),
      itemStr = '<span class="markerbg marker_' + (index + 1) + '"></span>' + '<div class="info">' + "   <h5>" + places.place_name + "</h5>";

    if (places.road_address_name) {
      itemStr += "    <span>" + places.road_address_name + "</span>" + '   <span class="jibun gray">' + places.address_name + "</span>";
    } else {
      itemStr += "    <span>" + places.address_name + "</span>";
    }

    itemStr += '  <span class="tel">' + places.phone + "</span>" + "</div>";

    el.innerHTML = itemStr;
    el.className = "item";

    return el;
  }

  // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
  function addMarker(position, idx, title) {
    let imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
      imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
      imgOptions = {
        spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
        spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
        offset: new kakao.maps.Point(13, 37),
        // 마커 좌표에 일치시킬 이미지 내에서의 좌표
      },
      markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
      marker = new kakao.maps.Marker({
        position: position, // 마커의 위치
        image: markerImage,
      });

    marker.setMap(map); // 지도 위에 마커를 표출합니다
    markers.push(marker); // 배열에 생성된 마커를 추가합니다

    return marker;
  }

  // 지도 위에 표시되고 있는 마커를 모두 제거합니다
  function removeMarker() {
    for (let i = 0; i < markers.length; i++) {
      markers[i].setMap(null);
    }
    markers = [];
  }

  // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
  function displayPagination(pagination) {
    let paginationEl = document.getElementById("pagination2"),
      fragment = document.createDocumentFragment(),
      i;

    // 기존에 추가된 페이지번호를 삭제합니다
    while (paginationEl.hasChildNodes()) {
      paginationEl.removeChild(paginationEl.lastChild);
    }

    for (i = 1; i <= pagination.last; i++) {
      let el = document.createElement("a");
      el.href = "#";
      el.innerHTML = i;

      if (i === pagination.current) {
        el.className = "on";
      } else {
        el.onclick = (function (i) {
          return function () {
            pagination.gotoPage(i);
          };
        })(i);
      }

      fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
  }

  // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
  // 인포윈도우에 장소명을 표시합니다
  function displayInfowindow(marker, title) {
    let content = '<div style="padding:5px;z-index:1;">' + title + "</div>";

    infowindow.setContent(content);
    infowindow.open(map, marker);
  }

  // 검색결과 목록의 자식 Element를 제거하는 함수입니다
  function removeAllChildNods(el) {
    while (el.hasChildNodes()) {
      el.removeChild(el.lastChild);
    }
  }

  //////////////////////////////////////////////////////////////

  let marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
    infowindow = new kakao.maps.InfoWindow({ zindex: 1 }); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다

  // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
  searchAddrFromCoords(map.getCenter(), displayCenterInfo);

  // 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
  kakao.maps.event.addListener(map, "click", function (mouseEvent) {
    searchDetailAddrFromCoords(mouseEvent.latLng, function (result, status) {
      if (status === kakao.maps.services.Status.OK) {
        let detailAddr = !!result[0].road_address ? "<div>도로명주소 : " + result[0].road_address.address_name + "</div>" : "";
        detailAddr += "<div>지번 주소 : " + '<span id="detailAddr2">' + result[0].address.address_name + "</span>" + "</div>";

        let content = '<div class="bAddr">' + '<span class="title">법정동 주소정보</span>' + detailAddr + "</div>";

        // 마커를 클릭한 위치에 표시합니다
        marker.setPosition(mouseEvent.latLng);
        marker.setMap(map);

        let spanDetailAddr = result[0].address.address_name;
        document.getElementById("detailAddr2").value = spanDetailAddr;

        let hiddenValue = document.getElementById("detailAddr2").value;
        console.log(hiddenValue);

        // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
        infowindow.setContent(content);
        infowindow.open(map, marker);
      }
    });
  });

  // 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
  kakao.maps.event.addListener(map, "idle", function () {
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
      let infoDiv = document.getElementById("centerAddr");

      for (let i = 0; i < result.length; i++) {
        // 행정동의 region_type 값은 'H' 이므로
        if (result[i].region_type === "H") {
          infoDiv.innerHTML = result[i].address_name;
          break;
        }
      }
    }
  }
} // end of createKakaoMap3

let date = 0;
let time = 0;
function scheduleChk(selScheDate) {
  let locationURL = location.href;
  let searchParams = new URL(locationURL).searchParams;
  let group_id = searchParams.get("group_id");

  let url = contextPath + "/group/schedule/schCompare";

  $.ajax({
    type: "get",
    dataType: "text",
    async: true,
    url: url,
    data: { group_id: group_id, selScheDate: selScheDate },
    success: function (data) {
      //매개변수들은 내가 정해주는 것이지만 보통 쓰는 단어들이 있다.
      console.log(data);
      if (data) {
        let scheduleInfo = JSON.parse(data);
        let attendUserList = JSON.parse(scheduleInfo.attendUserJson);
        //console.log("scheduleInfo=", scheduleInfo);
        //console.log("scheduleInfo.attendUserJson=",scheduleInfo.attendUserJson);
        //console.log("attendUserList=",attendUserList);
        memNum = attendUserList.length;
        
        if(memNum==0) {
		  $(".btnEventPrev").hide();
	      $(".btnEventNext").hide();
      } else {
		  $(".btnEventPrev").show();
	      $(".btnEventNext").show();      
	  }
        
        $("#attendCnt").text("현재 참석인원 " + memNum + " 명");
        
        //console.log("attendUserList=", attendUserList);
        if (scheduleInfo.schedule && scheduleInfo.schedule !== "null") {
          // 값이 있는 경우 처리
          $(".scheduleDetailArea").css("display", "block");
          $(".scheduleEdit").css("display", "none");
          console.log("날짜와 시간: " + scheduleInfo.sche_dateTime);
          console.log("날: " + scheduleInfo.schedule.substring(0, 10));
          let address = scheduleInfo.location;

          setTimeout(function () {
            createKakaoMap1(address);
            $(".scheduleTitle").val(scheduleInfo.sche_title).prop("disabled", true);

            //$('#schedule').val("날짜 : " + scheduleInfo.schedule);
            //$('#sche_time').val("시간 : " + scheduleInfo.sche_time);
            $("#sche_cont").text(scheduleInfo.sche_cont).prop("disabled", true);
            $("#sche_dateTime").val(scheduleInfo.sche_dateTime).prop("disabled", true);
            $("#sche_dateTime_old").val(scheduleInfo.sche_dateTime);

            $("#keyword").val(address);

            date = scheduleInfo.schedule;
            time = scheduleInfo.sche_time;
          }, 1);

          console.log("isAttend=", scheduleInfo.isAttend);
          if (scheduleInfo.isAttend) {
            let fn_name = "fn_cancelSchedule(" + group_id + ")";
            $("#joinSchedule").text("참석 취소");
            $("#joinSchedule").attr("onclick", fn_name);
          }else{
			let fn_name = "fn_joinSchedule(" + group_id + ")";
			$("#joinSchedule").text("참석");
            $("#joinSchedule").attr("onclick", fn_name);
		  }
          let content = "";
          $("#sliderPanel_id").html("");
          for (key in attendUserList) {
            content += `
							<div class="slideContent">
								<div class="memImg">
					                <img src="/nemo/userImageDownload?user_id=${attendUserList[key].user_id}&user_img=${attendUserList[key].user_img}" alt="프로필사진" />
					            </div>
					            <br/>
					            <div class="memName">
					                <span>${attendUserList[key].nickname}</span>
					            </div>
				          	</div>  
				            `;
          }
          $("#sliderPanel_id").html(content);
          //setTimeout(createKakaoMap1(address), 1);
        } else {
          // 값이 없는 경우 처리
          console.log("값이 없습니다.");
          $(".scheduleDetailArea").css("display", "none");
          $(".scheduleEdit").css("display", "block");

          setTimeout(function () {
            createKakaoMap2();
            $(".scheduleTitle strong").empty();
            $("#sche_cont").empty();
          }, 1);

          //setTimeout(createKakaoMap2(), 1);
        }
      } else {
        // 값이 없는 경우 처리
        console.log("값이 없습니다.");
      }
    },
    error: function (xhr, status, error) {
      alert("에러 발생: " + error + ", " + status);
    },
  });
}

window.onload = function () {
  /* 이미지 슬라이더(참석자 영역) 시작 */
  let slideIndx = 0; //image slide index
  let aniSlidewidth = 0; //animationSlide width 값 받아오는 변수
  let viewNum = 0; //animationSlide 화면에 보여질 멤버 수
  let slidContpadding = 0; //slideContent div의 padding
  let quotient = 0;
  let mod = 0;

  $(".sliderPanel").css("width", memNum * 160);

  memImageSlidePanel();
  console.log("ani width : " + aniSlidewidth);
  console.log("갯수: " + viewNum);
  console.log("padding : " + slidContpadding);
  console.log("멤버수: " + memNum);
  console.log("memnum/viewnum:" + quotient);
  console.log("mod: " + mod);

  $(".fa-chevron-left").click(function () {
    if (slideIndx == 0) {
    } else {
      slideIndx--;
      memImgSlide(slideIndx);
    }
  });

  $(".fa-chevron-right").click(function () {
    //if(slideIndx>=(memNum-viewNum)) {
    if (slideIndx >= quotient) {
    } else {
      slideIndx++;
      memImgSlide(slideIndx);
    }
  });

  console.log("memNum=" + memNum);

  //이미지 슬라이드 패널 생성하는 함수
  function memImageSlidePanel() {
    console.log("width=" + $(".animationSlide").width());
    //let width=(memNum*125)+((memNum+2)*13);
    aniSlidewidth = $(".animationSlide").width();

    console.log("aniSlidewidth=" + aniSlidewidth);
    //viewNum=Math.floor(aniSlidewidth/125);
    viewNum = 6;
    //slidContpadding=Math.floor((aniSlidewidth-(viewNum*125))/(viewNum*2));
    let slidContpaddingNew = 10;
    slidContpadding = 11;
    quotient = Math.floor(memNum / viewNum);
    mod = memNum % viewNum;

    $(".memImg").css("padding-left", slidContpaddingNew);
    $(".memImg").css("padding-right", slidContpaddingNew);

    $(".currentNum").html(memNum);
    //$('.sliderPanel').css('width', (125+(slidContpadding*2))*memNum);

    //소모임 멤버 수 만큼 멤버프로필 div생성
    /*
    for(let i=0; i<memNum; i++) {
        let item='';
        item+='<div class="slideContent">';
        $('.slideContent').css("padding",slidContpadding);  //padding 값 세팅 
        item+='<div class="memImg"></div><br>';        //이 부분에서 멤버 프로필 사진 받아와서 프사넣기
        item+='<span class="memName">member'+(i+1)+'</span>'; //// 여기 멤버이름
        item+='</div>';
        $('.sliderPanel').append(item); //생성한 멤버 프로필 슬라이더패널에 추가하기
    }
*/
    memImgSlide(slideIndx);
  }

  // 이미지 슬라이드 함수
  function memImgSlide(slideIndx) {
    console.log("memnum/viewnum:" + quotient);
    console.log("sliIndx: " + slideIndx);
    console.log("mod: " + mod);
    console.log("quotient: " + quotient);
/*
	if(memNum==0) {
		  $(".btnEventPrev").hide();
	      $(".btnEventNext").hide();
      } else {
		  $(".btnEventPrev").show();
	      $(".btnEventNext").show();      
	  }
*/
    if (memNum <= viewNum) {
      // 총멤버수가 한번에 보여지는 수보다 작을떄
      $(".fa-solid").removeClass("btnActive");
      $(".btnEventPrev").css({
        cursor: "default",
        color: "#999",
      });
      $(".btnEventNext").css({
        cursor: "default",
        color: "#999",
      });
    } 
    
    if (slideIndx == 0 && quotient > 0) {
      $(".btnEventPrev").css({
        cursor: "default",
        color: "#999",
      });
      $(".btnEventNext").css({
        cursor: "pointer",
        color: "#222",
      });
      $(".sliderPanel").animate({
        left: -((125 + slidContpadding * 2) * slideIndx * viewNum),
      });

      console.log("여긴가1");
    }

    if (slideIndx >= quotient && mod != 0 && quotient != 0) {
      $(".btnEventPrev").css({
        cursor: "pointer",
        color: "#222",
      });
      $(".btnEventNext").css({
        cursor: "default",
        color: "#999",
      });
      $(".sliderPanel").animate({
        left: -((125 + slidContpadding * 2) * slideIndx * viewNum - (125 + slidContpadding * 2) * (viewNum - mod)),
      });
      console.log(-((125 + slidContpadding * 2) * slideIndx * viewNum));
      console.log($(".sliderPanel").width());
      console.log("여긴가2");
    }
    if (slideIndx != 0 && slideIndx < quotient) {
      $(".btnEventPrev").css({
        cursor: "pointer",
        color: "#222",
      });
      $(".btnEventNext").css({
        cursor: "pointer",
        color: "#222",
      });
      $(".sliderPanel").animate({
        left: -((125 + slidContpadding * 2) * slideIndx * viewNum),
      });
      console.log("여긴가3");
    }

    if (slideIndx == quotient - 1 && mod == 0) {
      $(".btnEventNext").css({
        cursor: "default",
        color: "#999",
      });
      $(".sliderPanel").animate({
        left: -((125 + slidContpadding * 2) * slideIndx * viewNum),
      });
      console.log("여긴가4");
    }
  }
  /* 이미지 슬라이더(참석자 영역) 종료 */

  calendarMaker($("#myScheduleCalendarArea"), new Date());

  let nowDate = new Date();

  function calendarMaker(target, date) {
    if (date == null || undefined) {
      date = new Date();
    }
    let nowDate = date;
    if ($(target).length > 0) {
      year = nowDate.getFullYear();
      month = nowDate.getMonth() + 1;
      $(target).empty().append(assembly(year, month));
    } else {
      console.error("calendar Target is empty");
      return;
    }

    let arr = [];
    let promise1 = fn_ajax2(year, month);
    promise1.then((value) => {
      arr = value;
      //console.log("typeofvalue=" + typeof value);
      // console.log("value=" + value);
      console.log("arr=" + arr);

      //console.log("schedulesList" + schedulesList);
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
        /*
	    if (cnt % 7 == 0) {
	      tag += "<tr>";
	    }
	    tag += "<td>" + i + "<div class='displayReserveContainer'></div></td>";
	    cnt++;
	    if (cnt % 7 == 0) {
	      tag += "</tr>";
	    }*/

        // if( schedulesList.includes(i.toString())  ){
        let newI = i;
        if (newI < 10) {
          newI = "0" + newI;
        }
        newI = newI.toString();
        if (arr.includes(newI)) {
          //console.log("i=" + i);
          if (cnt % 7 == 0) {
            tag += "<tr>";
          }
          tag += "<td><div class='displayReserveContainer scheduleDate'></div>" + i + "</td>";
          cnt++;
          if (cnt % 7 == 0) {
            tag += "</tr>";
          }
        } else {
          // 날짜 채우기
          // for (let i = 1; i <= thisLastDay.getDate(); i++) {
          if (cnt % 7 == 0) {
            tag += "<tr>";
          }
          tag += "<td><div class='displayReserveContainer'></div>" + i + "</td>";
          cnt++;
          if (cnt % 7 == 0) {
            tag += "</tr>";
          }
          //  }
        } //else End
      }

      $(target).find("#setDate").append(tag);

      calMoveEvtFn();
    }); // end of promise1 - then

    /*
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
	*/
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
        nowDate = new Date(nowDate.getFullYear(), nowDate.getMonth() - 1, nowDate.getDate());
        calendarMaker($(target), nowDate);
        passedDay(nowDate);
      });

      // 다음달 클릭
      $(".calendarTable").on("click", ".next", function () {
        nowDate = new Date(nowDate.getFullYear(), nowDate.getMonth() + 1, nowDate.getDate());
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

        function fn_disable() {
          document.getElementById("scheduleTitle").disabled = true;
          document.getElementById("sche_dateTime").disabled = true;
          document.getElementById("sche_cont").disabled = true;
          document.getElementById("button_modify").style.display = "none";
          document.getElementById("editButton").style.display = "block";
          $(".menu_wrap_class").css("display", "none");
          //document.getElementsByClassName("menu_wrap2").style.display= "block";
          setTimeout(function () {
            /*
			let oldMap = document.getElementById('map');
			oldMap.remove();
			let newMapDiv = document.getElementById('newMap');
			let childrenList = newMapDiv.childNodes;
			let h4 = childrenList[0];
			
			let newMap = document.createElement("div");
			newMap.className = "map";
			newMap.id = "map";
			
			h4.after(newMap);
			*/
            //document.getElementById('map').style = "";
            let address = document.getElementById("addressForMod").innerText;
            createKakaoMap3(address);
          }, 1);
        }

        // 변경된 내용을 되돌리기 위해 fn_disable 호출
        fn_disable();
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
  } // end of calendarMaker

  year = nowDate.getFullYear();
  month = nowDate.getMonth() + 1;
}; // end of window.onload

function delSchedule(group_id) {
  //2023-07-01
  let result = confirm("삭제하시겠습니까?")
  if(result) {
	let sendDate = date.substring(2, 4) + "/" + date.substring(5, 7) + "/" + date.substring(8, 10);
    let sendTime = time;
    //console.log(schedule);
    //href="${contextPath}/group/schedule/delSchedule?group_id=${param.group_id}&schedule=${}"
    location.href = "/nemo/group/schedule/delSchedule?group_id=" + group_id + "&date=" + sendDate + "&time=" + sendTime;
  }
}

function toSchedule(obj, group_id) {
  location.href = "/nemo/group/schedule?group_id=" + group_id;
}

function fn_modify_schedule(obj, group_id) {
  //let sendDate = date.substring(2,4)+"/"+date.substring(5,7) + "/" + date.substring(8, 10);
  //let sendTime = time;
  //location.href="/nemo/group/schedule/modSchedule?group_id=" + group_id + "&date=" + sendDate + "&time=" + sendTime;
  let groupNoInput = document.createElement("input"); //필요에따라 동적으로 글번호를 담을 input태그 생성
  groupNoInput.setAttribute("type", "hidden");
  groupNoInput.setAttribute("name", "group_id");
  groupNoInput.setAttribute("value", group_id);

  obj.appendChild(groupNoInput);

  obj.action = "/nemo/group/schedule/modSchedule";
  obj.submit();
}

function fn_joinSchedule(group_id) {
  let sendDate = date.substring(2, 4) + "/" + date.substring(5, 7) + "/" + date.substring(8, 10);
  let sendTime = time;
  console.log("joinSchedule 호출");
  //href="${contextPath}/group/schedule/delSchedule?group_id=${param.group_id}&schedule=${}"
  location.href = "/nemo/group/schedule/joinSchedule?group_id=" + group_id + "&date=" + sendDate + "&time=" + sendTime;
}

function fn_cancelSchedule(group_id) {
  let sendDate = date.substring(2, 4) + "/" + date.substring(5, 7) + "/" + date.substring(8, 10);
  let sendTime = time;
  console.log("joinSchedule 호출");
  //href="${contextPath}/group/schedule/delSchedule?group_id=${param.group_id}&schedule=${}"
  location.href = "/nemo/group/schedule/cancelSchedule?group_id=" + group_id + "&date=" + sendDate + "&time=" + sendTime;
}

function fn_ajax2(year, month) {
  return new Promise((resolve, reject) => {
    //이번달 일정 있는 날/없는 날 구분해서 채우기 아작스
    let ajaxYear = year % 100;
    let ajaxMonth = month;
    if (ajaxMonth < 10) {
      ajaxMonth = "0" + month;
    }

    let locationURL = location.href;
    let searchParams = new URL(locationURL).searchParams;
    let group_id = searchParams.get("group_id");
    console.log("group_id=", group_id);
    console.log("ajaxMonth=", ajaxMonth);
    $.ajax({
      type: "get",
      cache: false,
      contentType: false,
      ajax: false,
      url: "/nemo/group/schedule/ajaxCalendar?group_id=" + group_id + "&year=" + ajaxYear + "&month=" + ajaxMonth,
      //data: { "jsonInfo": jsonInfo },
      success: function (data, textStatus) {
        let jsonInfo = JSON.parse(data);
        
        console.log(jsonInfo);
        resolve(jsonInfo);
        /*
        let jsonInfo = JSON.parse(data);

        for (key in jsonInfo.schedules) {
          //schedulesList.push(jsonInfo.schedules[key].day);
          arr.push(jsonInfo.schedules[key].day);
          //arr.push(10);
        }
        console.log("success안:" + arr);
        console.log("data=" + data);
        resolve(arr);
        */
      },
      error: function (data, textStatus, error) {
        //console.log(data);
        //console.log(textStatus);
        //console.log(error);
        //alert("찜 추가/삭제 에러 발생");
      },
    });
  });
} // end of fn_ajax2
