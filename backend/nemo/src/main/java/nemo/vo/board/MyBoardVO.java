package nemo.vo.board;

import nemo.vo.group.GroupVO;
import nemo.vo.user.UserVO;

public class MyBoardVO {

	private BoardVO boardVO = new BoardVO();
	private GroupVO groupVO;
	private UserVO  userVO;
	private CommentVO commentVO;
	
	public MyBoardVO() {
		boardVO=new BoardVO();
		groupVO=new GroupVO();
		userVO=new UserVO();
		commentVO=new CommentVO();
	}

	public BoardVO getBoardVO() {
		return boardVO;
	}

	public void setBoardVO(BoardVO boardVO) {
		this.boardVO = boardVO;
	}

	public GroupVO getGroupVO() {
		return groupVO;
	}

	public void setGroupVO(GroupVO groupVO) {
		this.groupVO = groupVO;
	}

	public UserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	public CommentVO getCommentVO() {
		return commentVO;
	}

	public void setCommentVO(CommentVO commentVO) {
		this.commentVO = commentVO;
	}
	
	
	
}
