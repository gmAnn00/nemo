<<<<<<< HEAD
$(document).ready(function () {
  $("#domainList").on("change", function () {
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
  $("#passwordCheck").keyup(function () {
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
  });
});
=======
$(document).ready(function () {
  $("#domainList").on("change", function () {
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
  $("input").keyup(function () {
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
  });
});
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
