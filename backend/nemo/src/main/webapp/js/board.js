$(document).ready(function() {
// a href='#' 클릭 무시 스크립트
	$('a[href="#"]').click(function(ignore) {
	    ignore.preventDefault();
	});
	
	let urlStr=window.location.href;
	let url=new URL(urlStr);
	let urlParams = url.searchParams;
	let value=urlParams.get('keyword');
	console.log(value);
	if(value==='공지'){
		console.log('여기오니');
		$('.bracketFilter').css({
			display:'none'
		});
		$('.notice').css({
			display:'inline-block'
		});
	}else if(value==='자유') {
		$('.bracketFilter').css({
			display:'none'
		});
		$('.free').css({
			display:'inline-block'
		});
		
	}else if(value==='후기'){
		$('.bracketFilter').css({
			display:'none'
		});
		$('.review').css({
			display:'inline-block'
		});
	} else{
		$('.bracketFilter').css({
			display:'none'
		});
	}
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

