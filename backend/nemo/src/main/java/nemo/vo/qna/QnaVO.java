package nemo.vo.qna;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

public class QnaVO {
	private int qna_id;
	private String user_id;
	private String nickname;
	private int parent_no;
	private String title;
	private String content;
	private Date create_date;
	private int level;
	private String qna_img;

	
	
	public QnaVO() {
		System.out.println("QnaVO 생성");
	}


	public QnaVO(int qna_id, String user_id, String nickname, int parent_no, String title, String content, Date create_date, int level, String qna_img) {
		super();
		this.qna_id = qna_id;
		this.user_id = user_id;
		this.nickname = nickname;
		this.parent_no = parent_no;
		this.title = title;
		this.content = content;
		this.create_date = create_date;
		this.level = level;
		this.qna_img=qna_img;
	}


	public int getQna_id() {
		return qna_id;
	}


	public void setQna_id(int qna_id) {
		this.qna_id = qna_id;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public int getParent_no() {
		return parent_no;
	}


	public void setParent_no(int parent_no) {
		this.parent_no = parent_no;
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


	public Date getCreate_date() {
		return create_date;
	}


	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public String getQna_img() {
		/*try {
			if(qna_img != null && qna_img.length() !=0 ) {
				qna_img = URLDecoder.decode(qna_img,"utf-8");
			}
		} catch (Exception e) {
			System.out.println("이미지 읽는 중 에러");
		}*/
		return qna_img;
	}


	public void setQna_img(String qna_img) {
		/*try {
			if(qna_img != null && qna_img.length() !=0 ) {
				this.qna_img = URLEncoder.encode(qna_img,"utf-8");
			}else {
				this.qna_img=null;
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println("이미지 저장 중 에러");
		}*/
		this.qna_img = qna_img;
	}


	@Override
	public String toString() {
		return "QnaVO [qna_id=" + qna_id + ", user_id=" + user_id + ", nickname=" + nickname + ", parent_no="
				+ parent_no + ", title=" + title + ", content=" + content + ", create_date=" + create_date + ", level="
				+ level + ", qna_img=" + qna_img + "]";
	}



	
	
	
	

	
	
}

