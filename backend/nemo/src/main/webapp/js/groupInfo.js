<<<<<<< HEAD
$(function () {
	let path = window.location.pathname;
	console.log("path="+path)
    //let user_id = "${user_id}";
    let user_id = $("#user_id_hidden").val();
    console.log("user_id="+user_id);
    let group_id = new URL(location.href).searchParams;
    group_id = group_id.get("group_id");
    console.log("group_id="+group_id);
    //let isBookmark = "${isBookmark}";
    let isBookmark = $("#isBookmark_hidden").val();
    console.log("isBookmark="+isBookmark)
    
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
    	if(user_id === 'null' || user_id ==="" || user_id==null){
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
=======
$(function () {
    $(".grpLike").on("click", function () {
        // grpLike 클래스 클릭시 ♡♥ 토글됨
        $(this).toggleClass("on");

        // 찜했는지 안했는지 체크하는 부분
        if ($(this).hasClass("on")) {
            checkOnOff = true;
        } else {
            checkOnOff = false;
        }
        console.log(checkOnOff);
    });

    /*$(".joinBtn").on("click", function () {
        alert("로그인 후 이용해주세요");
    });*/
});

let checkOnOff = false; //찜인지 아닌지 체크하는 변수
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
