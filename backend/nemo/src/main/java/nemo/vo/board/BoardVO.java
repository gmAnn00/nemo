package nemo.vo.board;

import java.sql.Date;

public class BoardVO {
	//필드
	private int article_no;
	private String user_id;
	private int grp_id;
	private Date create_date;
	private String title;
	private String content;
	private String brackets;
	private int view_cnt;
	
	public BoardVO() {

	}

	public BoardVO(int article_no, String user_id, int grp_id, String title, String content, String brackets,
			int view_cnt) {

		this.article_no = article_no;
		this.user_id = user_id;
		this.grp_id = grp_id;
		this.title = title;
		this.content = content;
		this.brackets = brackets;
		this.view_cnt = view_cnt;
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

	public int getGrp_id() {
		return grp_id;
	}

	public void setGrp_id(int grp_id) {
		this.grp_id = grp_id;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBrackets() {
		return brackets;
	}

	public void setBrackets(String brackets) {
		this.brackets = brackets;
	}

	public int getView_cnt() {
		return view_cnt;
	}

	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}
	
	
	
}
