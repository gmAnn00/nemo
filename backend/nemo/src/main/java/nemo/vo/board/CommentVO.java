package nemo.vo.board;

<<<<<<< HEAD
import java.sql.Date;
=======
import java.sql.Timestamp;

import nemo.vo.common.UserVO;
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d

public class CommentVO {
	private int level;
	private int comment_no;
	private int article_no;
	private String user_id;
<<<<<<< HEAD
	private String nickname;
	private Date create_date;
	private String com_cont; //댓글내용
	private int parent_no;
	
	public CommentVO() {

	}

	public CommentVO(int comment_no, int article_no, String user_id, String nickname, String com_cont, int parent_no) {
		super();
		this.comment_no = comment_no;
		this.article_no = article_no;
		this.user_id = user_id;
		this.nickname = nickname;
=======
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
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
		this.com_cont = com_cont;
		this.parent_no = parent_no;
	}
	

	
<<<<<<< HEAD
	public CommentVO(int level, int comment_no, int article_no, String user_id, String nickname, Date create_date,
=======
	public CommentVO(int level, int comment_no, int article_no, String user_id, Timestamp create_date,
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
			String com_cont, int parent_no) {
		this.level = level;
		this.comment_no = comment_no;
		this.article_no = article_no;
		this.user_id = user_id;
<<<<<<< HEAD
		this.nickname = nickname;
=======
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
		this.create_date = create_date;
		this.com_cont = com_cont;
		this.parent_no = parent_no;
	}
<<<<<<< HEAD
=======
	
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
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d

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

<<<<<<< HEAD
=======
	/*
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
<<<<<<< HEAD

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
=======
	*/

	public Timestamp getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Timestamp create_date) {
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
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
