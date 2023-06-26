package nemo.vo.mypage;

import nemo.vo.board.BoardVO;
import nemo.vo.board.CommentVO;
import nemo.vo.group.GroupVO;
import nemo.vo.user.UserVO;

public class MyBoardVO {
	private UserVO userVO = new UserVO();
	private GroupVO groupVO = new GroupVO();
	private BoardVO boardVO = new BoardVO();
	private CommentVO commentVO = new CommentVO();
	
	public MyBoardVO() {
		
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
