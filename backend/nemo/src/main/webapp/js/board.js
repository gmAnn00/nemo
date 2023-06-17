$(document).ready(function() {
// a href='#' 클릭 무시 스크립트
	$('a[href="#"]').click(function(ignore) {
	    ignore.preventDefault();
	});

});

function submitSearchForm(){
	let keyword=$('#keyword').val();
	
	if(!keyword) {
		alert('검색어를 입력해주세요');
	} else {
		$('form[name="search"]').submit();
		alert($('form[name="search"]').attr('action')); 
		 //$('#search').submit();
	}
}