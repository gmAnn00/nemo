let idCheck=false;
let pwdCheck=false;
let allPwdCheck=false;
let nickCheck=false;
let phoneCheck=false;

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

	$("#alertSuccess").hide();
	$("#alertDanger").hide();
/*
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
	}); //end 비밀번호 확인*/

});


//아이디 중복 체크
function fn_IdCheck() {
	idCheck=false;
	let user_idVO="";
	let idRegExp = /^(?=.*[a-zA-Z])[-a-zA-Z0-9_-]{5,20}$/;
	if($("#user_id_hidden").val() != null){
		user_idVO = $("#user_id_hidden").val();

	}
	console.log("user_idVO=" + user_idVO);

	let user_id = $("#user_id").val();
	
	//입력 안했을 경우
	if (user_id.length<5) {
		$("#resultMsgId").show();
		$("#resultMsgId").html("중복 체크할 아이디를 5글자 이상으로 입력해주세요");
		$("#resultMsgId").css("color", "#f43965");
		return;
	} else if(user_id.length>=5 || user_id.length<=20) {
		if(!idRegExp.test(user_id)){
			if(user_id.length>20) {
				$("#resultMsgId").show();
				$("#resultMsgId").html("중복 체크할 아이디를 20자 이하로 입력해주세요");
				$("#resultMsgId").css("color", "#f43965");
				return;
			} else {		
				$("#resultMsgId").show();
				$("#resultMsgId").html("중복 체크할 아이디를 형식에 맞게 입력해주세요");
				$("#resultMsgId").css("color", "#f43965");
				return;
			}
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
						idCheck=true;
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
	/*if (user_id == user_idVO) {
		$("#resultMsgId").show();
		$("#resultMsgId").html("지금 사용하고 있는 아이디입니다.");
		$("#resultMsgId").css("color", "#3384ff");
	}*/ 
}

//비밀번호 유효성 체크
function fn_pwdCheck() {
	allPwdCheck=false;
	pwdCheck=false;
	let password = $("#password").val();
	let num = password.search(/[0-9]/g);
	let eng = password.search(/[a-z]/ig);
	//let special = password.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
	let special = password.search(/[`\-=\\\[\];',\./~!@#\$%\^&\*\(\)_\+|\{\}:"<>\?]/gi);
	let pwdRegExp=/^[A-Za-z0-9`\-=\\\[\];',\./~!@#\$%\^&\*\(\)_\+|\{\}:"<>\?]{8,20}$/;
	
	let passwordCheck = $("#passwordCheck").val();
	if(passwordCheck !='' || !(passwordCheck.length<=0)) {
		if (password == passwordCheck) {
			allPwdCheck=true;
			$("#alertSuccess").show();
			$("#alertDanger").hide();
			$("#submit").removeAttr("disabled");
		} else {
			$("#alertSuccess").hide();
			$("#alertDanger").show();
			$("#submit").attr("disabled", "disabled");
		}
	}
	if(password.length<8||password.length>20) {
		$("#resultMsgPwd").show();
		$("#resultMsgPwd").html("비밀번호를 8~20자리 이내로 입력해주세요.");
		$("#resultMsgPwd").css("color", "#f43965");
		return;
	} else if(password.search(/\s/)!=-1){
		$("#resultMsgPwd").show();
		$("#resultMsgPwd").html("비밀번호를 공백 없이 입력해주세요.");
		$("#resultMsgPwd").css("color", "#f43965");
		return;
	} else if(!pwdRegExp.test(password)) {
		$("#resultMsgPwd").show();
		$("#resultMsgPwd").html("비밀번호를 문자, 숫자, 특수문자포함 8~20자로 입력해주세요.");
		$("#resultMsgPwd").css("color", "#f43965");
		return;
	} else {
		pwdCheck=true;
		$("#resultMsgPwd").hide();
		return;
	}
	//
	
}

function fn_pwdDupCheck() {
	allPwdCheck=false;	
	$("#passwordCheck").keyup(function() {
		let password = $("#password").val();
		let passwordCheck = $("#passwordCheck").val();
		if (password != "" || passwordCheck != "") {
			if (password == passwordCheck) {
				allPwdCheck=true;
				$("#alertSuccess").show();
				$("#alertDanger").hide();
				$("#submit").removeAttr("disabled");
			} else {
				$("#alertSuccess").hide();
				$("#alertDanger").show();
				$("#submit").attr("disabled", "disabled");
			}
		}
	});
	
}

//닉네임 중복 체크
function fn_nicknameCheck() {
	nickCheck=false;
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
					nickCheck=true;
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

function fn_phoneCheck() {
	phoneCheck=false;
	let phone=$('#phone').val();
	let phoneRegCheck=/^([0-9]{2,3})([0-9]{3,4})([0-9]{4})$/;
	if(!phoneRegCheck.test(phone)){
		$("#resultMsgPhone").show();
		$("#resultMsgPhone").html("전화번호 형식이 맞지 않습니다. 예)01012345678");
		$("#resultMsgPhone").css("color", "#f43965");
	} else {
		$("#resultMsgPhone").hide();
		phoneCheck=true;
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
		
		if(!idCheck) {
			alert('ID를 다시 입력해주세요');
			$("#user_id").focus();
			return false;
		}
		if(!pwdCheck||!allPwdCheck) {
			alert('비밀번호를 다시 입력해주세요');
			$("#password").focus();
			return false;
		}
		if(!nickCheck) {
			alert('닉네임을 다시 입력해주세요');
			$("#nickname").focus();
			return false;
		}
		if(!phoneCheck) {
			alert('전화번호를 다시 입력해주세요');
			$('#phone').focus();
			return false;
		}
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
