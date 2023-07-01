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
	private String create_date;
	
	public MyBoardVO() {
		
	}

	public MyBoardVO(UserVO userVO, GroupVO groupVO, BoardVO boardVO, String create_date) {
		super();
		this.userVO = userVO;
		this.groupVO = groupVO;
		this.boardVO = boardVO;
		this.create_date = create_date;
	}

	public MyBoardVO(UserVO userVO, GroupVO groupVO, BoardVO boardVO, CommentVO commentVO, String create_date) {
		super();
		this.userVO = userVO;
		this.groupVO = groupVO;
		this.boardVO = boardVO;
		this.commentVO = commentVO;
		this.create_date = create_date;
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

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}	

}
