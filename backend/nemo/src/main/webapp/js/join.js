$(document).ready(function() {
	$("#domainList").on("change", function() {
		var emailDomain = $("#emailDomain");
		if ($(this).val() == "self") {
			emailDomain.val("");
			emailDomain.prop("readonly", false);
		} else {
			emailDomain.val($(this).val());
			emailDomain.prop("readonly", true);
		}
	});


	//비밀번호 확인
	$("#alertSuccess").hide();
	$("#alertDanger").hide();
	$("#passwordCheck").keyup(function() {
		let password = $("#password").val();
		let passwordCheck = $("#passwordCheck").val();
		if (password != "" || passwordCheck != "") {
			if (password == passwordCheck) {
				$("#alertSuccess").show();
				$("#alertDanger").hide();
				$("#submit").removeAttr("disabled");
			} else {
				$("#alertSuccess").hide();
				$("#alertDanger").show();
				$("#submit").attr("disabled", "disabled");
			}
		}
	}); //end 비밀번호 확인

});


//아이디 중복 체크
function fn_IdCheck() {
	let user_idVO="";
	if($("#user_id_hidden").val() != null){
		user_idVO = $("#user_id_hidden").val();

	}
	console.log("user_idVO=" + user_idVO);

	let user_id = $("#user_id").val();
	
	//입력 안했을 경우
	if (user_id == "") {
		$("#resultMsgId").show();
		$("#resultMsgId").html("중복 체크할 아이디을 입력해주세요");
		$("#resultMsgId").css("color", "#f43965");
		return; //아래 내용 수행안하고 위로 돌아감

	}

	if (user_id == user_idVO) {
		$("#resultMsgId").show();
		$("#resultMsgId").html("지금 사용하고 있는 아이디입니다.");
		$("#resultMsgId").css("color", "#3384ff");
	} else {
		$.ajax({
			type: "post",
			async: true,
			// dataType: "text",
			url: "/nemo/duplicate/id",
			//왼쪽의 key에 오른쪽의 value값이 들어감
			data: { "user_id": user_id },
			success: function(data, textStatus) {
				if (data == "usable") {
					$("#resultMsgId").show();
					$("#resultMsgId").html("사용할 수 있는 아이디입니다.");
					$("#resultMsgId").css("color", "var(--blue-color)");
				} else {
					$("#resultMsgId").show();
					$("#resultMsgId").html("사용할 수 없는 아이디입니다.");
					$("#resultMsgId").css("color", "#f43965");
				}
			},
			error: function(data, textStatus, error) {
				alert("에러가 발생했습니다.");
			},
		});
	}
}

//닉네임 중복 체크
function fn_nicknameCheck() {
	let nicknameVO = $("#nickname_hidden").val();
	console.log("nickname=" + nicknameVO);

	let nickname = $("#nickname").val();
	//입력 안했을 경우
	if (nickname == "") {
		$("#resultMsgNick").show();
		$("#resultMsgNick").html("중복 체크할 닉네임을 입력해주세요");
		$("#resultMsgNick").css("color", "#f43965");
		return; //아래 내용 수행안하고 위로 돌아감
	}

	if ((nickname == nicknameVO)) {
		$("#resultMsgNick").show();
		$("#resultMsgNick").html("지금 사용하고 있는 닉네임입니다.");
		$("#resultMsgNick").css("color", "#3384ff");
	} else {
		$.ajax({
			type: "post",
			async: true,
			// dataType: "text",
			url: "/nemo/duplicate/nickname",
			//왼쪽의 key에 오른쪽의 value값이 들어감
			data: { "nickname": nickname },
			success: function(data, textStatus) {
				if (data == "usable") {
					$("#resultMsgNick").show();
					$("#resultMsgNick").html("사용할 수 있는 닉네임입니다.");
					$("#resultMsgNick").css("color", "#3384ff");
				} else {
					$("#resultMsgNick").show();
					$("#resultMsgNick").html("사용할 수 없는 닉네임입니다.");
					$("#resultMsgNick").css("color", "#f43965");
				}
			},
			error: function(data, textStatus, error) {
				alert("에러가 발생했습니다.");
			},
		});
	}
}

//이메일 중복체크
function fn_emailCheck() {
	let emailIdVO = $("#emailId_hidden").val();
	console.log("emailId=" + emailIdVO);
	let emailDomainVO = $("#emailDomain_hidden").val();
	console.log("emailDomain=" + emailDomainVO);
	
	let emailId = $("#emailId").val();
	let emailDomain = $("#emailDomain").val();
	
	//입력 안했을 경우
	if (emailId == "" || emailDomain == "") {
		$("#resultMsgEmail").show();
		$("#resultMsgEmail").html("중복 체크할 이메일을 입력해주세요");
		$("#resultMsgEmail").css("color", "#f43965");
		return; //아래 내용 수행안하고 위로 돌아감
	}

	if (emailId == emailIdVO && emailDomain == emailDomainVO) {
		$("#resultMsgEmail").show();
		$("#resultMsgEmail").html("지금 사용하고 있는 이메일입니다.");
		$("#resultMsgEmail").css("color", "#3384ff");
	} else {
		$.ajax({
			type: "post",
			async: true,
			// dataType: "text",
			url: "/nemo/duplicate/email",
			//왼쪽의 key에 오른쪽의 value값이 들어감
			data: { "emailId": emailId, "emailDomain": emailDomain },
			success: function(data, textStatus) {
				if (data == "usable") {
					$("#resultMsgEmail").show();
					$("#resultMsgEmail").html("사용할 수 있는 이메일입니다.");
					$("#resultMsgEmail").css("color", "#3384ff");
				} else {
					$("#resultMsgEmail").show();
					$("#resultMsgEmail").html("사용할 수 없는 이메일입니다.");
					$("#resultMsgEmail").css("color", "#f43965");
				}
			},
			error: function(data, textStatus, error) {
				alert("에러가 발생했습니다.");
			},
		});
	}
}

function fnJoin(){
	
	/*
	let user_id=$("#user_id").val();
	let password=$("#password").val();
	let passwordCheck=$("#passwordCheck").val();
	let name=$("#name").val();
	let nickname=$("#nickname").val();
	let zipcode=$("#zipcode").val();
	let birthdate=$("#birthdate").val();
	let phone=$("#phone").val();
	
	let emailReg= /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
	let emailId=$("#emailId").val();
	let emailDomain=$("#emailDomain").val();
	let email="";
	
	if(!user_id){
		alert("ID를 입력해주세요");
		$("#user_id").focus();
		return false;
	}
	if(!password){
		alert("비밀번호를 입력해주세요");
		$("#password").focus();
		return false;
	}
	if(!passwordCheck){
		alert("비밀번호 확인을 입력해주세요");
		$("#passwordCheck").focus();
		return false;
	}
	if(!nickname){
		alert("닉네임을 입력해주세요");
		$("#nickname").focus();
		return false;
	}
	if(!zipcode){
		alert("우편번호를 입력해주세요");
		$("#findZipcode").focus();
		return false;
	}
	if(!birthdate){
		alert("생년월일 입력해주세요");
		$("#birthdate").focus();
		return false;
	}
	if(!phone){
		alert("전화번호를 입력해주세요");
		$("#phone").focus();
		return false;
	}
	if(!emailId){
		alert("이메일을 입력해주세요");
		$("#emailId").focus();
		return false;
	}
	if(!emailDomain){
		alert("이메일 도메인을 입력해주세요");
		$("#emailDomain").focus();
		return false;
	}
	mail= emailId+"@"+emailDomain;
	
	if(!emailReg.test(mail)){
		alert("이메일을 형식에 맞게 입력해주세요");
		$("#emailDomain").focus();
		return false;
	}
	
	document.getElementById('frm').submit();*/
	
	let isEmpty=false;
	
	$('#frm').find('input[id!="user_addr2"]').each(function(){
		
		$(this).removeClass('isEmpty');
		if(!$(this).val()){
			isEmpty=true;
			$(this).addClass('isEmpty');
		}
	});
	
	if(isEmpty){
		alert('필수항목을 입력해주세요');
		return false;
	} else if(!isEmpty) {
		let emailReg= /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
		let emailId=$("#emailId").val();
		let emailDomain=$("#emailDomain").val();
		let email="";
		mail= emailId+"@"+emailDomain;
	
		$('#emailId').removeClass('isEmpty');
		$('#emailDomain').removeClass('isEmpty');
	
		if(!emailReg.test(mail)){
			$('#emailId').addClass('isEmpty');
			$('#emailDomain').addClass('isEmpty');
			$("#emailId").focus();
			alert('이메일을 형식에 맞게 입력해주세요');
			return false;
		}
	}
	document.getElementById('frm').submit();
}
