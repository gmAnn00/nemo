function bookmarkClick(user_id, group_id){
	
	let hostIndex = location.href.indexOf(location.host) + location.host.length;
	let contextPath = location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
	let url = contextPath + "/group/bookmark";
	//console.log(contextPath);
	
	let isBookmark_hidden = $("#isBookmark"+group_id).val();
	console.log("isBookmark_hidden=" ,isBookmark_hidden);
	
	$.ajax({
		type: "post",
		async: true,
		url: url,
		data: { "user_id": user_id, "group_id": group_id},
		success: function(data, textStatus) {
			let target = ".grpLike"+group_id;		
			$(target).toggleClass("on");

		},
		error: function(data, textStatus, error) {
			console.log(data);
			console.log(textStatus);
			console.log(error);
			alert("찜 추가/삭제 에러 발생");
		},
	});
}
