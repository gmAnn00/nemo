$(function () {
	let path = window.location.pathname;
	console.log(path)
    let user_id = "${user_id}";
    let group_id = new URL(location.href).searchParams;
    group_id = group_id.get("group_id");
    let isBookmark = "${isBookmark}";

    $(".joinBtn").on("click", function () {
        console.log(user_id);
        //console.log(typeof user_id);
        //console.log(group_id);
        if (user_id === "null" || user_id === "") {
            if (window.confirm("로그인 후 이용해주세요")) {
                location.href = "/nemo/index";
            } else {
                location.href = "/nemo/index";
            }
        } else {
            console.log("user_id" + user_id);
            location.href = "/nemo/group/joinGroup?group_id=" + group_id;
        }
    });

    $(".grpLike").on("click", function () {
    	if(user_id === 'null' || user_id ===""){
    		$(this).removeClass("on");
    		alert("로그인 후 이용해주세요");
    		location.href="${contextPath}/index";
    		
    	} else{
    		$.ajax({
                type: "post",
                async: true,
                url: "${contextPath}/group/bookmark",
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
