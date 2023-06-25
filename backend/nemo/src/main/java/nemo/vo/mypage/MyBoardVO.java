package nemo.vo.mypage;

import nemo.vo.board.BoardVO;
import nemo.vo.board.CommentVO;
import nemo.vo.group.GroupVO;

public class MyBoardVO {
	private UserVO userVO;
	private GroupVO groupVO;
	private BoardVO boardVO;
	private CommentVO commentVO;
	
	public MyBoardVO() {
		// TODO Auto-generated constructor stub
	}
	
	public MyBoardVO(UserVO userVO, GroupVO groupVO, BoardVO boardVO) {
		super();
		this.userVO = userVO;
		this.groupVO = groupVO;
		this.boardVO = boardVO;
	}

	public MyBoardVO(UserVO userVO, GroupVO groupVO, BoardVO boardVO, CommentVO commentVO) {
		super();
		this.userVO = userVO;
		this.groupVO = groupVO;
		this.boardVO = boardVO;
		this.commentVO = commentVO;
	}

	public UserVO getUserVO() {
		return userVO;
	}
	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}
	public GroupVO getGroupVO() {
		return groupVO;
	}
	public void setGroupVO(GroupVO groupVO) {
		this.groupVO = groupVO;
	}
	public BoardVO getBoardVO() {
		return boardVO;
	}
	public void setBoardVO(BoardVO boardVO) {
		this.boardVO = boardVO;
	}
	public CommentVO getCommentVO() {
		return commentVO;
	}
	public void setCommentVO(CommentVO commentVO) {
		this.commentVO = commentVO;
	}
	
	
	 
}
