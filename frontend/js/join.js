$(document).ready(function(){
	$("#domainList").on("change", function() {
        var emailDomain = $("#emailDomain");
        if ($(this).val() == "self") {
            emailDomain.val('');
            emailDomain.prop("readonly", false);
        } else {
            emailDomain.val($(this).val());
            emailDomain.prop("readonly", true);
        }
    });
});