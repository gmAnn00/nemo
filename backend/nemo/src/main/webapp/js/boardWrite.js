$(document).ready(function() {
	//여기 아래 부분
	$('#summernote').summernote({
		  height: 300,                 // 에디터 높이
		  minHeight: null,             // 최소 높이
		  maxHeight: null,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
<<<<<<< HEAD
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
			onImageUpload: function(files, editor, welEditable) {
				for(var i=files.lenght-1; i>=0; i--) {
					sendFile(files[i], this);
				}
			}
		}
		
	});
});

function sendFile(file, el) {
	let form_data = new FormData();
	form_data.append('file',file);
	$.ajax({
		date:form_data,
		
	});
}
=======
		  placeholder: '최대 2048자까지 쓸 수 있습니다'	//placeholder 설정
          
	});
});
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
