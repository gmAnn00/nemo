$(document).ready(function() {
	
});

function delUserImgSubmit(){
  	document.getElementById("userImg").src = "../images/dall.png";
  	document.getElementById("isDeleteImg").value="true";
  	console.log("이미지 삭제");
  	 //form.upload.select();    
     //document.selection.clear();
	
	/*
	$.ajax({
		type: "post",
		async: false,
		// dataType: "text",
		url: "/nemo/mypage/userImgDelete",
		//왼쪽의 key에 오른쪽의 value값이 들어감,
		success: function(data, textStatus) {
			$("#userImg").attr('scr', '/nemo/userImages/'+user_id+'/dall.png');
			alert('프로필 이미지가 삭제되었습니다.');
		},
		error: function(data, textStatus, error) {
			alert("에러가 발생했습니다.");
		},
	});
	*/
}