let jsonArray=[];

$(document).ready(function() {
	
	// a href='#' 클릭 무시 스크립트
	$('a[href="#"]').click(function(ignore) {
	    ignore.preventDefault();
	});
	
	$('.button').click(function(){
		let headTitle=$('#headTitle').val();
		let writeTitle=$('#writeTitle').val();

		let summernote=$('#summernote').val();
		let text=$('#summernote').summernote('code');
		console.log(text.length);

		if(!headTitle){
			alert('말머리를 선택해주세요');
		}else if(!writeTitle) {
			alert('제목을 입력해주세요');
		}else if($('#summernote').summernote('isEmpty')){
			//summernote.length<=0){
			alert('d내용을 입력해주세요');			
		} else {
			console.log("content");
			 $('textarea[name="content"]').val($('#summernote').summernote('code'));
			console.log($('textarea[name="content"]').val($('#summernote').summernote('code')));
			
			if(jsonArray.length>0) { //
				for(var i=0; i<jsonArray.length; i++) {
					var str=jsonArray[i];
					var result=str.toString().split('=');
					let img="<input type='hidden' name='imageName' value='"+result[1]+"'>";
					console.log(img);
					$('#articleForm').append(img);
				}
				$('#articleForm').append('<input type="hidden" name="isImgExist" value="true">');
			} else {
				$('#articleForm').append('<input type="hidden" name="isImgExist" value="false">');
			}
			$('#articleForm').submit();

		}

	});
	
	//여기 아래 부분
	$('#summernote').summernote({
		  height: 300,                 // 에디터 높이
		  minHeight: null,             // 최소 높이
		  maxHeight: null,             // 최대 높이
		  focus: false,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
		  placeholder: '최대 2048자까지 쓸 수 있습니다'	, //placeholder 설정
          toolbar: [
		    // 글꼴 설정
		    ['fontname', ['fontname']],
		    // 글자 크기 설정
		    ['fontsize', ['fontsize']],
		    // 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
		    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
		    // 글자색
		    ['color', ['forecolor','color']],
		    ['para', ['ul', 'ol', 'paragraph']],    // 글머리 기호, 번호매기기, 문단정렬
		    // 그림첨부, 링크만들기, 동영상첨부
		    ['insert',['picture',]],
		    ['undo', ['undo','redo']]
		  ],
		  // 추가한 글꼴
		fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
		 // 추가한 폰트사이즈
		fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
		callbacks: {
				//이미지 업로드 콜백
				onImageUpload: function(files, editor, welEditable) {
		            for (var i = files.length - 1; i >= 0; i--) {
		            	//sendFile(files[i], this);
		            	uploadSummerNoteImage(files[i], this);
		            }
		        }, onMediaDelete : function(target) {
                    console.log();
                    //deleteFile(target[0].src);
                }
		        
		        //end of onImageUpload 콜백 
		       /* onMediaDelete: function ($target, editor, $editable) {
					let deletedImageUrl=$target
						.attr('src')
						.split('/')
						.pop();
						console.log(deletedImageUrl);
				//		deleteImageFile(deletedImageUrl)
				} //end of onMediaDelete 콜백
			}*/
			}
		});
    	$('#summernote').summernote('code');
    
});
/*
	function sendFile(file, el) {
		//let ctx=getContextPath();
		var form_data = new FormData();
		console.log('콜백오니');
      	form_data.append('file', file);
      	$.ajax({
        	data: form_data,
        	type: "POST",
        	url: '/nemo/snUploadImage',
        	cache: false,
        	contentType: false,
        	enctype: 'multipart/form-data',
        	processData: false,
        	success: function(img_name) {
				console.log("성공~");
				
				let img=JSON.parse(img_name);
				console.log(img.url);
          		$(el).summernote('editor.insertImage', img.url);
        	}
      	});
    }
*/	
function uploadSummerNoteImage(file,el) {
		console.log('업로드 펑션 콜백오니');
		let ctx=getContextPath();
		let data = new FormData();
		console.log(ctx+'/summernote/uploadImage');
		data.append("file",file);
			$.ajax({
				data:data,
				url: '/nemo/snUploadImage',
				type:'POST',
        		cache: false,
        		contentType: false,
        		enctype: 'multipart/form-data',
        		processData: false,
				success :function(data) {
					console.log("~성공");
					let json=JSON.parse(data);
					
					$(el).summernote('editor.insertImage',json["url"]);
						jsonArray.push(json["url"]);
						jsonFn(jsonArray);
				},
				error:function(e) {
					console.log(e);
				}
			});
}

	function fn_cancel(group_id){
		console.log('여긴오니???????????????');
		let cancelForm=document.createElement('form');
		cancelForm.name='cancelForm';
		cancelForm.method='post';
		cancelForm.action="cancelAddArticle?group_id="+group_id;
		console.log(jsonArray.length);
		if(jsonArray.length>0) {
			for(var i=0; i<jsonArray.length; i++) {
				var str=jsonArray[i];
				var result=str.toString().split('=');
				//let img="<input type='hidden' name='imageName' value='"+result[1]+"'>";
				var input = document.createElement('input');
				input.setAttribute("name","imageName");
				input.setAttribute("type","hidden");
				input.setAttribute("value",result[1]);
				console.log(input);
				cancelForm.appendChild(input)
			}
			console.log("여기는 ture 입니다.");
			let msg=document.createElement('input');
			msg.setAttribute('type','hidden');
			msg.setAttribute('name','isImgExist');
			msg.setAttribute('value','true');
			//'<input type="hidden" name="isImgExist" value="true">';
			cancelForm.appendChild(msg);
		} else {
			console.log("여기는 false입니다.");
			let msg=document.createElement('input');
			msg.setAttribute('type','hidden');
			msg.setAttribute('name','isImgExist');
			msg.setAttribute('value','false');
			cancelForm.appendChild(msg);
		}

		document.body.appendChild(cancelForm);
		cancelForm.submit();
	};

function jsonFn(jsonArray) {
	console.log(jsonArray);
}

//에디터 내용 텍스트 제거
function f_SkipTags_html(input, allowed) {
	// 허용할 태그는 다음과 같이 소문자로 넘겨받습니다. (<a><b><c>)
    allowed = (((allowed || "") + "").toLowerCase().match(/<[a-z][a-z0-9]*>/g) || []).join('');
    var tags = /<\/?([a-z][a-z0-9]*)\b[^>]*>/gi,
    commentsAndPhpTags = /<!--[\s\S]*?-->|<\?(?:php)?[\s\S]*?\?>/gi;
    return input.replace(commentsAndPhpTags, '').replace(tags, function ($0, $1) {
        return allowed.indexOf('<' + $1.toLowerCase() + '>') > -1 ? $0 : '';
    });
}

//태그와 줄바꿈, 공백을 제거하고 텍스트 글자수만 가져옵니다.
function setContentsLength(str, index) {
	    var status = false;
	    var textCnt = 0; //총 글자수
	    var maxCnt = 100; //최대 글자수
	    var editorText = f_SkipTags_html(str); //에디터에서 태그를 삭제하고 내용만 가져오기
	    editorText = editorText.replace(/\s/gi,""); //줄바꿈 제거
	    editorText = editorText.replace(/&nbsp;/gi, ""); //공백제거

        textCnt = editorText.length;
	    if(maxCnt > 0) {
        	if(textCnt > maxCnt) {
                status = true;
        	}
	    }

	    if(status) {
        	var msg = "등록오류 : 글자수는 최대 "+maxCnt+"까지 등록이 가능합니다. / 현재 글자수 : "+textCnt+"자";
        	console.log(msg);
	    }
	}


function getContextPath() {
    return sessionStorage.getItem("contextpath");
}