package nemo.vo.board;

import java.sql.Timestamp;

import nemo.vo.user.UserVO;

public class CommentVO {
	private int level;
	private int comment_no;
	private int article_no;
	private String user_id;
	//private String nickname;
	//private Date create_date;
	private String com_cont; //댓글내용
	private int parent_no;
	private Timestamp create_date;
	private UserVO userVO;
	private BoardVO articleVO;
	
	public CommentVO() {
		articleVO = new BoardVO();
		userVO = new UserVO();
	}

	public CommentVO(int comment_no, int article_no, String user_id, String com_cont, int parent_no) {
		this.comment_no = comment_no;
		this.article_no = article_no;
		this.user_id = user_id;
		this.com_cont = com_cont;
		this.parent_no = parent_no;
	}
	

	
	public CommentVO(int level, int comment_no, int article_no, String user_id, Timestamp create_date,
			String com_cont, int parent_no) {
		this.level = level;
		this.comment_no = comment_no;
		this.article_no = article_no;
		this.user_id = user_id;
		this.create_date = create_date;
		this.com_cont = com_cont;
		this.parent_no = parent_no;
	}
	
	public BoardVO getArticleVO() {
		return articleVO;
	}

	public void setArticleVO(BoardVO articleVO) {
		this.articleVO = articleVO;
	}

	public UserVO getUserVO() {
		
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getComment_no() {
		return comment_no;
	}

	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}

	public int getArticle_no() {
		return article_no;
	}

	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/*
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	*/

	public Timestamp getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}

	public String getCom_cont() {
		return com_cont;
	}

	public void setCom_cont(String com_cont) {
		this.com_cont = com_cont;
	}

	public int getParent_no() {
		return parent_no;
	}

	public void setParent_no(int parent_no) {
		this.parent_no = parent_no;
	}
	
}