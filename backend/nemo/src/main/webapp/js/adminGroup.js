$(document).ready(function() {
	$("table.adminGroupTbl").DataTable();
	// a href='#' 클릭 무시 스크립트
	$('a[href="#"]').click(function(ignore) {
	    ignore.preventDefault();
	});
	
});

function fn_delete(grp_id){
		let result=confirm("삭제 하시겠습니까?");
	if(result) {
		let newForm = $('<form></form>');
		newForm.attr("name","newForm");
		newForm.attr("method","post");
		newForm.attr("action","/nemo/adminGroup/delGroup.do");
		newForm.append($('<input/>', {type: 'hidden', name: 'grp_id', value:grp_id }));
		newForm.appendTo('body');

	// submit form
	newForm.submit();
	}else {
		
	}
}

