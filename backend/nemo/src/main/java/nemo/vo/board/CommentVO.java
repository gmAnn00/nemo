package nemo.vo.board;

import java.sql.Date;

public class CommentVO {
	private int comment_no;
	private int article_no;
	private String user_id;
	private Date create_date;
	private String com_cont; //댓글내용
	private String parent_no;
	
	public CommentVO() {

	}

	public CommentVO(int comment_no, int article_no, String user_id, String com_cont, String parent_no) {
		super();
		this.comment_no = comment_no;
		this.article_no = article_no;
		this.user_id = user_id;
		this.com_cont = com_cont;
		this.parent_no = parent_no;
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

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getCom_cont() {
		return com_cont;
	}

	public void setCom_cont(String com_cont) {
		this.com_cont = com_cont;
	}

	public String getParent_no() {
		return parent_no;
	}

	public void setParent_no(String parent_no) {
		this.parent_no = parent_no;
	}
	
}
