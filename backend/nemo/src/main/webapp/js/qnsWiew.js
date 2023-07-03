$(document).ready(function() {
	ctx = getContextPath();
	// a href='#' 클릭 무시 스크립트
	$('a[href="#"]').click(function(ignore) {
	    ignore.preventDefault();
	});
	
	$(document).on('click','a[href="#"]',function(ignore){
		ignore.preventDefault();
	});

})

function fn_deleteChk(qna_id) {
	let result=confirm("삭제 하시겠습니까?");
	if(result) {
		let newForm = $('<form></form>');
		newForm.attr("name","newForm");
		newForm.attr("method","post");
		newForm.attr("action","/nemo/viewQna/removeArticle.do");
		newForm.append($('<input/>', {type: 'hidden', name: 'qna_id', value:qna_id }));
		newForm.appendTo('body');

	// submit form
	newForm.submit();
	}else {
		
	}
} 
    
 //url 클립보드 복사
 function clip(){
	var origUurl = '';
	var textarea = document.createElement("textarea");
	document.body.appendChild(textarea);
	origUrl = window.document.location.href;
	let index=origUrl.lastIndexOf("#");
	let url;
	if(index!=-1) {
		url=origUrl.substring(0,index);
	} else {
		url=origUrl;
	}
	console.log(url);
	textarea.value = url;
	textarea.select();
	document.execCommand("copy");
	document.body.removeChild(textarea);
	alert("URL이 복사되었습니다.")
}

function backToList() {
	let referrer = document.referrer;
	location.href=referrer;
	console.log(referrer);
}




