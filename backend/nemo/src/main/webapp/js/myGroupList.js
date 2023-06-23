$(function () {
	//let path = window.location.pathname;
	//console.log("path="+path)
    //let user_id = "${user_id}";
    let user_id = $("#user_id_hidden").val();
    console.log("user_id="+user_id);
    //let group_id = new URL(location.href).searchParams;
    //group_id = group_id.get("group_id");
    
    let group_id = $("#grp_id_hidden").val();
    console.log(typeof $("#grp_id_hidden").val());
    //let group_id = Number($("#grp_id_hidden").val());
          
    console.log("group_id="+group_id);
    //let isBookmark = "${isBookmark}";
    let isBookmark = $("#isBookmark_hidden").val();
    console.log("isBookmark="+isBookmark)


    $(".grpLike").on("click", function () {
    	if(user_id === 'null' || user_id ==="" || user_id == null){
    		$(this).removeClass("on");
    		alert("로그인 후 이용해주세요");
    		location.href="/nemo/index";
    		
    	} else{
			let hostIndex = location.href.indexOf(location.host) + location.host.length;
			let contextPath = location.href.substring(hostIndex, location.href.indexOf('/', hostIndex+1));
    		let url = contextPath + "/group/bookmark";
    		console.log(contextPath);
    		$.ajax({
                type: "post",
                async: true,
                url: url,
                data: { "user_id": user_id, "group_id": group_id, "isBookmark": isBookmark },
                success: function (data, textStatus) {
                	console.log(isBookmark);
                	isBookmark = !isBookmark;
                	$(".grpLike").toggleClass("on");

                },
                error: function (data, textStatus, error) {
                	console.log(data);
                	console.log(textStatus);
                	console.log(error);
                    alert("찜 추가/삭제 에러 발생");
                },
            });
    	}
        
    });
});
