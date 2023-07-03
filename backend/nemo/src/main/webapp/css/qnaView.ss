@charset "utf-8";
/*게시글 조회 영역 상단*/
.sc2_subsection_title {
	border:none;
}

.writerInfo {
	margin-left: 10px;

}

/* 게시글 내용 영역 */
#contentArea {
	position: relative;
}

.contentEditTool {
	position: absolute;
	padding: 10px;
	right:0;
	top:0;
}

/* 게시글 조회 부분 */
.boardView {
  margin: 0 auto;
  /* padding: 30px; */
}

.atricleArea {
    width: 100%;
    box-sizing: border-box;
    padding: 10px;
    margin: 0 auto;
}
/* 목록 버튼 오른쪽 정렬 */
.articleToolBtns {
  overflow: hidden;
  margin-bottom: 10px;
  margin-top: 10px;
  padding: 5px 10px;
}
.articleToolBtns a:last-child {
  float: right;
  cursor: pointer;
}
.articleContentBox {

}
/* 제목 영역 */
.articleHeader {
  border-top: 2px solid #ccc;
  border-bottom: 2px solid #ccc;
  overflow: hidden;
  position: relative;
}
.articleHeader{
	display: grid;
}

.articleTitle {
/*
display:table-cell;
vertical-align:middle;
line-height: 20px;
margin: 15px 0 15px 0;
*/
	padding: 15px 15px;
	display:flex;
  
}
.titleHead {
  /*width: 150px;
  text-align: center;*/
  width: 50px;
  margin: 5px 0;
  display: inline-block;
  vertical-align: middle;
  float: left;
}
.titleHead .brackets {
	display:inline-block;
	margin-right: 10px;
	font-size: 12px;
	font-weight: bold;
	color: #ffbb33;
}
.title {

	  font-size: 20px;

}
.titleArea {
	display: inline-block;
  font-size: 20px;  
  vertical-align: middle;
  /*width: 94%;*/
}

/* 작성자 폰트 사이즈 좀 작게 - common에서 폰트 사이즈 정해지면 그보다 작게 수정 */
.writerInfo {
  font-size: 13px;
  margin: 0 0 10px;
	padding: 0 10px;

}
.thumbArea {
	display: inline-block;
	float:left;
}
.thumbArea img {
  border-radius: 50%;
  width: 40px;
  height: 40px;
  background: #ccc;
}
.profileArea {
	display:table-cell;
	vertical-align: middle;
  padding: 0 10px;
  margin-bottom: 5px;
  width: 150px;
  height: 45px;
  position: relative;
}
.writerNick {
  /* 작성자 닉네임 */
  border: none;
  background-color: transparent;
  font-weight: bold;
  margin: 3px 0;
}
.profileArea .date {
	margin: 3px 0;
}
.articleInfo .date {
  position: absolute;
  top: 20px;
  left: 10px;
}
/* 댓글, URL복사, 더보기 영역 */
.articleTool {
  position: absolute;
  bottom: 15px;
  right: 25px;
  font-size: 13px;
}
.topComment, .viewCnt {
	margin-right:5px;
}

.buttonUrl {
	cursor: pointer;
}


/* 내용 영역 */
.contentArea {
  padding: 30px 10px;
  border-bottom: 2px solid #ccc;
}

.btnRemoveRe:hover, .btnSubmitRe.active:hover{
	color:#fb3;
}
/* 아래쪽 버튼 영역 */
.btn {
  display: inline-block;
  font-size: 15px;
  box-sizing: border-box;
  font-weight: bold;
  text-align: center;
  vertical-align: top;
  margin: 0 10px;

}
.btn:not(.btnTop):hover{
	color:#ffbb33;
}

.articleBottomBtns {
	margin-top: 10px;
  overflow: hidden;
}
.articleBottomBtns .leftArea {
  float: left;
}
.articleBottomBtns .rightArea {
  float: right;
}

.fa-caret-up {
	font-size:15px;
	line-height:15px;
	margin-right:8px;
	vertical-align: middle;
	color:#ffbb33;
}