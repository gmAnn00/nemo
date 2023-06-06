editorLoding : function (title, contents){
  //첫 호출 시 editorLoding("", "");
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: document.getElementById('contents'), // html editor가 들어갈 textarea id 입니다.
		sSkinURI: "/framework/smartEditor/SmartEditor2Skin.html",  // html editor가 skin url 입니다.
		htParams : {
	  // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
      bUseToolbar : true,             
	    // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
      bUseVerticalResizer : true,     
	    // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
      bUseModeChanger : true,         
      fOnBeforeUnload : function(){
        
      }
		}, 
	
		/**
		 * 수정 시 에디터에 데이터 저장
		 */
	fOnAppLoad: function () {
	    //수정모드를 구현할 때 사용할 부분입니다. 로딩이 끝난 후 값이 체워지게 하는 구현을 합니다.
	     oEditors.getById["contents"].exec("PASTE_HTML", [contents]); //로딩이 끝나면 contents를 txtContent에 넣습니다.
	},
	
	fCreator: "createSEditor2",
	});
}
//https://www.wrapuppro.com/programing/view/ZPLdxEBiyJG38IG