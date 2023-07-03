$(document).ready(function() {
	$("table.adminUserTbl").DataTable();
	// a href='#' 클릭 무시 스크립트
	$('a[href="#"]').click(function(ignore) {
	    ignore.preventDefault();
	});
	
});

function fn_delete(user_id){
		let result=confirm("삭제 하시겠습니까?");
	if(result) {
		let newForm = $('<form></form>');
		newForm.attr("name","newForm");
		newForm.attr("method","post");
		newForm.attr("action","/nemo/adminUser/delUser.do");
		newForm.append($('<input/>', {type: 'hidden', name: 'user_id', value:user_id }));
		newForm.appendTo('body');

	// submit form
	newForm.submit();
	}else {
		
	}
}
