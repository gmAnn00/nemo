package nemo.vo.group;

import java.sql.Date;

public class UserReportVO {
	private String reporter_id;
	private String accused_id;
	private Date rep_date;
	private String rep_content;
	
	public UserReportVO(String reporter_id, String accused_id, Date rep_date, String rep_content) {
		super();
		this.reporter_id = reporter_id;
		this.accused_id = accused_id;
		this.rep_date = rep_date;
		this.rep_content = rep_content;
	}

	public String getReporter_id() {
		return reporter_id;
	}

	public void setReporter_id(String reporter_id) {
		this.reporter_id = reporter_id;
	}

	public String getAccused_id() {
		return accused_id;
	}

	public void setAccused_id(String accused_id) {
		this.accused_id = accused_id;
	}

	public Date getRep_date() {
		return rep_date;
	}

	public void setRep_date(Date rep_date) {
		this.rep_date = rep_date;
	}

	public String getRep_content() {
		return rep_content;
	}

	public void setRep_content(String rep_content) {
		this.rep_content = rep_content;
	}
	
	
	
	
}
