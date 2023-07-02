$(function() {
	let path = window.location.pathname;
	console.log("path=" + path)
	//let user_id = "${user_id}";
	let user_id = $("#user_id_hidden").val();
	console.log("user_id=" + user_id);
	let group_id = new URL(location.href).searchParams;
	group_id = group_id.get("group_id");
	console.log("group_id=" + group_id);

	$(".joinBtn").on("click", function() {
		console.log(user_id);
		//console.log(typeof user_id);
		//console.log(group_id);
		if (user_id === "null" || user_id === "") {
			alert("로그인 후 이용해주세요");
			location.href="/nemo/login/loginForm";
		} else {
			console.log("user_id" + user_id);
			location.href = "/nemo/group/joinGroup?group_id=" + group_id;
		}
	});

	$(".grpLike").on("click", function() {
		if (user_id === 'null' || user_id === "" || user_id == null) {
			$(this).removeClass("on");
			alert("로그인 후 이용해주세요");
			location.href = "/nemo/login/loginForm";

		} else {
			let hostIndex = location.href.indexOf(location.host) + location.host.length;
			let contextPath = location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
			let url = contextPath + "/group/bookmark";
			//console.log(contextPath);
			$.ajax({
				type: "post",
				async: true,
				url: url,
				data: { "user_id": user_id, "group_id": group_id},
				success: function(data, textStatus) {
					let newNum=0;
					//console.log("data=", typeof data);
					if(data=="false"){
						//console.log("-1 : ", data);
						newNum = Number($("#likeNum").text()) - 1;
					}
					else{
						//console.log("+1 : ", data);
						newNum = Number($("#likeNum").text()) + 1;
					}

					$(".grpLike").toggleClass("on");
					
					$("#likeNum").text(newNum);

				},
				error: function(data, textStatus, error) {
					console.log(data);
					console.log(textStatus);
					console.log(error);
					alert("찜 추가/삭제 에러 발생");
				},
			});
		}

	});

	let grp_addr1 = $("#grp_addr1_hidden").val();
	console.log(grp_addr1);
	//console.log("grp_zipcode=" + grp_zipcode);
	let container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	let options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
		level: 3 //지도의 레벨(확대, 축소 정도)
	};

	let map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	

	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();

	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(grp_addr1, function(result, status) {

		// 정상적으로 검색이 완료됐으면 
		if (status === kakao.maps.services.Status.OK) {

			var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

			// 결과값으로 받은 위치를 마커로 표시합니다
			var marker = new kakao.maps.Marker({
				map: map,
				position: coords
			});

			// 인포윈도우로 장소에 대한 설명을 표시합니다
			var infowindow = new kakao.maps.InfoWindow({
				content: '<div style="width:150px;height:25px;text-align:center;padding:6px 0;font-weight:bold;">소모임 위치</div>'
			});
			infowindow.open(map, marker);

			// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			map.setCenter(coords);
		}
	});

});