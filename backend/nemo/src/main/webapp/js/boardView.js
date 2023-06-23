let itemArr=[];
let ctx;




$(document).ready(function() {
	ctx = getContextPath();
	// a href='#' 클릭 무시 스크립트
	$('a[href="#"]').click(function(ignore) {
	    ignore.preventDefault();
	});
    adjustHeight();
	let ItemNum=$('.commentItem').length;
	for(let i=0; i<ItemNum; i++) {
		itemArr.push(0);
	}

	//답글 달기 눌렀을 떄 
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
	        addInputBox+='<textarea placeholder="댓글을 남겨보세요" class="commentInboxText" rows="1" id="textArea'+itemArr[index]+'" onkeydown="resize(this)" onkeyup="resize(this)"></textarea></div>';
	        addInputBox+='<div class="commentRegister"><a href="javascript:void(0);" role="button" class="buttonCancle btnSubmitRe btnReCom" ';
	        addInputBox+='id=regBtn'+itemArr[index]+' onclick="fn_regCommentChild('+itemArr[index]+')">등록</a>';
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
	});
	

    $(document).on('keyup', '.commentInboxText', function(){
        let inputLength = $(this).val().length;
        let parentSiblings=$(this).parent().siblings();
        let btnRecom=parentSiblings.find('.btnSubmitRe');
        if(inputLength>0){
            btnRecom.addClass('active');
        } else {
            btnRecom.removeClass('active');
        }

        
    });
	/*
	$(document).on('click','.btnSubmitRe', function(){
        let parentSiblings=$(this).parent().siblings();
        let textArea=parentSiblings.find('textarea');
        if(!textArea.val()){
            alert('내용을 입력해주세요');
        } else {
            //ajax로 댓글 등록 하는거 처리하기~

        }
	});*/
	
	//textarea 자동크기조절
	function resize(obj) {
  		obj.style.height = "auto";
  		obj.style.height = obj.scrollHeight+"px";
	}
	
	//댓글 등록 함수
	function fn_regComment() {
		let parentSiblings=$(this).parent().siblings();
        //let textArea=parentSiblings.find('textarea');
		let content=$('#textArea').val();
		let article_no=$('#article_no').val();
		let group_id=$('#group_id').val();


		//console.log("fn_regComment()");
		console.log(content+"article_no"+article_no);
		console.log(ctx);
		console.log(group_id);
        if(!content){
            alert('내용을 입력해주세요');
        } else {
            //ajax로 댓글 등록 하는거 처리하기~
            let ctx =getContextPath();
            url=ctx+"/group/board/addComment?group_id="+group_id;
            console.log(url);
            $.ajax({
				url: url,
				async: true,
				data: {
					"com_cont": content,
					"article_no": article_no,
					"parent_no": 0
				},
				type: "post",
				success:function(result) {
					if(result=="success") {
						alert("댓글이 등록 되었습니다.");
						
					}
					$('#textArea').val('')
					
				},
				error: {
					
				}
			});
            

        }	
	}
	
	//수정버튼 누를때 textarea 활성화
	function fn_enable(obj,cnt) {
		//alert(cnt+'클릭');
		let textAreaId="#viewTextArea"+cnt;
		let comModId="#comModBtn"+cnt;
		let comDelID="#comDelBtn"+cnt;
		let comReplyId="#comReplyBtn"+cnt;
		let modReplyId="#modReply"+cnt;
		document.getElementById('viewTextArea'+cnt).disabled=false;
		document.getElementById('viewTextArea'+cnt).style.border='1px solid #ccc';

		$(modReplyId).css({
			visibility:'visible'
		});
		$(comModId).css({
			visibility:'hidden'
		});
		$(comDelID).css({
			visibility:'hidden'
		});
		$(comReplyId).css({
			visibility:'hidden'
		});
		console.log(textAreaId);
		console.log(document.getElementById(textAreaId));
	}
	
	//댓글 수정 완료하는 부분
	function fn_modComment(obj, cnt) {
		let textAreaId="#viewTextArea"+cnt;
		let comModId="#comModBtn"+cnt;
		let comDelID="#comDelBtn"+cnt;
		let comReplyId="#comReplyBtn"+cnt;
		let modReplyId="#modReply"+cnt;
		let group_id=$('#group_id').val();
		let content=$(textAreaId).val();
		
        if(!content){
			alert('내용을 입력해주세요');
        } else {
		    document.getElementById('viewTextArea'+cnt).disabled=true;
			document.getElementById('viewTextArea'+cnt).style.border='none';
			$(modReplyId).css({
				visibility:'hidden'
			});
			$(comModId).css({
				visibility:'visible'
			});
			$(comDelID).css({
				visibility:'visible'
			});
			$(comReplyId).css({
				visibility:'visible'
			});

            let ctx =getContextPath();
            url=ctx+"/group/board/modComment?group_id="+group_id;
            console.log(url);
            
            $.ajax({
				url: url,
				async: true,
				data: {
					"com_cont": content,
					"comment_no": cnt
				},
				type: "post",
				success:function(result) {
					if(result=="success") {
						alert("댓글이 수정 되었습니다.");
					}					
				},
				error: {
					
				}
			});
 
        }	
	} //end of fn_modComment
	
	//댓글 수정 취소하기 
	function fn_cancleMod(obj, cnt) {
		let textAreaId="#viewTextArea"+cnt;
		let comModId="#comModBtn"+cnt;
		let comDelID="#comDelBtn"+cnt;
		let comReplyId="#comReplyBtn"+cnt;
		let modReplyId="#modReply"+cnt;
		let group_id=$('#group_id').val();
		let content=$(textAreaId).val();
		
		document.getElementById('viewTextArea'+cnt).disabled=true;
		document.getElementById('viewTextArea'+cnt).style.border='none';
		
		$(modReplyId).css({
				visibility:'hidden'
		});
		$(comModId).css({
			visibility:'visible'
		});
		$(comDelID).css({
			visibility:'visible'
		});
		$(comReplyId).css({
			visibility:'visible'
		});
		
		let ctx =getContextPath();
            url=ctx+"/group/board/modCancle?group_id="+group_id;
            console.log(url);
            
            $.ajax({
				url: url,
				async: true,
				data: {
					"comment_no": cnt
				},
				type: "post",
				success:function(content) {
					console.log(content);
					$(textAreaId).val(content);	
				},
				error: {
					
				}
			});
 
		
		
	}
	
	
	
	
	//대댓 등록 함수
	function fn_regCommentChild(count) {
		let parentSiblings=$(this).parent().siblings();
        //let textArea=parentSiblings.find('textarea');
		let content=$('#textArea').val();
		let article_no=$('#article_no').val();
		let group_id=$('#group_id').val();
		let parent_no;
		//console.log("fn_regComment()");
		console.log(content+"article_no"+article_no);
		console.log(ctx);
		console.log(group_id);
        if(!content){
            alert('내용을 입력해주세요');
        } else {
            //ajax로 댓글 등록 하는거 처리하기~
            let ctx =getContextPath();
            url=ctx+"/group/board/addComment?group_id="+group_id;
            console.log(ctx);
            
            $.ajax({
				url: url,
				async: true,
				data: {
					"com_cont": content,
					"article_no": article_no,
					"parent_no": 0
				},
				type: "post",
				success:function(result) {
					if(result=="success") {
						alert("댓글이 등록 되었습니다.")
					}
					$('#textArea').val('')
					
				},
				error: {
					
				}
			});
            

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

function adjustHeight() {
  let textElement = $('textarea');
  for(let i=0; i<textElement.length; i++) {
		console.log(textElement[i].val);
		textElement[i].style.height = 'auto';
  		var textEleHeight = textElement.prop('scrollHeight');
  		textElement.css('height', textEleHeight);
	}
}


function getContextPath() {
    return sessionStorage.getItem("contextpath");
}