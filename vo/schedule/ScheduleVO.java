package nemo.vo.schedule;

import java.sql.Timestamp;

public class ScheduleVO {
	//필드
	private Timestamp schedule;	//DB의 컬럼명과 같아야 관리가 편하다.
	private String grp_id;
	private String user_id;
	private String sche_title;
	private String sche_cont;
	private String location;
	
	//생성자
	public ScheduleVO() {
		
	}

	public ScheduleVO(Timestamp schedule, String grp_id, String user_id, String sche_title, String sche_cont,
			String location) {
		this.schedule = schedule;
		this.grp_id = grp_id;
		this.user_id = user_id;
		this.sche_title = sche_title;
		this.sche_cont = sche_cont;
		this.location = location;
	}

	//get set
	
	public Timestamp getSchedule() {
		return schedule;
	}

	public void setSchedule(Timestamp schedule) {
		this.schedule = schedule;
	}

	public String getGrp_id() {
		return grp_id;
	}

	public void setGrp_id(String grp_id) {
		this.grp_id = grp_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getSche_title() {
		return sche_title;
	}

	public void setSche_title(String sche_title) {
		this.sche_title = sche_title;
	}

	public String getSche_cont() {
		return sche_cont;
	}

	public void setSche_cont(String sche_cont) {
		this.sche_cont = sche_cont;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
}