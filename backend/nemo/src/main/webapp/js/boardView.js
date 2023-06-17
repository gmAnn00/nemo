let itemArr=[];

$(document).ready(function() {

// a href='#' 클릭 무시 스크립트
	$('a[href="#"]').click(function(ignore) {
	    ignore.preventDefault();
	});
        
	let ItemNum=$('.commentItem').length;
	for(let i=0; i<ItemNum; i++) {
		itemArr.push(0);
	}

	$('.comReplyBtn').click(function(){
	    
	    //let index=$(this).parent().index();
	    //let index=$(this).parents($('.commentItem')).index();
	    let ItemNum=$('.commentItem').length;
	    console.log("li수:" + ItemNum);
	    //let index=$(this).closest($('.commentItem')).index();
	    // ㄴ 얘는 closest(어쩌고)의 부모에서 index 셈
	    //let index=$(parent).index();
	    let index=$('.commentItem').index($(this).closest($('.commentItem')));
	    // ㄴ 얘는 모든 .commentItem을 기준으로 $(This).close어쩌고의 인덱스를 구함 
	    console.log(".commentItem: " + index);
	    if(itemArr[index]==0){
	        //let parent=$(this).parents($('.commentItem'));
	        let parent=$(this).closest($('.commentItem'));
	        let addInputBox='';
	        addInputBox+='<li class="replyBox commentLi replyCommentItem"><div class="commentReWriter"><div class="commentReInbox">';
	        addInputBox+='<textarea placeholder="댓글을 남겨보세요" class="commentInboxText" rows="1"></textarea></div>';
	        addInputBox+='<div class="commentRegister"><a href="javascript:void(0);" role="button" class="buttonCancle btnSubmitRe btnReCom">등록</a>'
	        addInputBox+='<a href="javascript:void(0);" role="button" class="buttonCancle btnRemoveRe btnReCom">취소</a></div></div></li>';
	        $(parent).after(addInputBox);
	        itemArr[index]++;
	        console.log(itemArr);
	    }
	});

});

	$(document).on('click','.btnRemoveRe', function(){
		let totLi=$('.commentLi').length;
		let replyBox=$('.replyBox').length;
		console.log(itemArr);
		for(let i=0; i<itemArr.length; i++) {
			console.log(totLi+","+replyBox);
			if(itemArr[i]==1 && replyBox>1){
				replyBox--;
				console.log("replyBox : " + replyBox);
			} else if(itemArr[i]==1 && replyBox==1) {
				itemArr[i]--;
				replyBox--;
				console.log("취소후 : "+itemArr+ " item[i]: " + itemArr[i]);
				$(this).closest($('.replyBox')).remove();
				
				break;
			} 
		}
	} );
    
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