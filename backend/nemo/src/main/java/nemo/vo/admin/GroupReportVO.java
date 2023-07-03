package nemo.vo.admin;

import java.sql.Date;

import nemo.vo.group.GroupVO;
import oracle.sql.DATE;

public class GroupReportVO {
	private int grp_id;
	private String reporter_id;
	private Date rep_date;
	private String rep_content;
	private GroupVO groupVO;
	
	public GroupReportVO() {
		groupVO=new GroupVO();
	}

	public int getGrp_id() {
		return grp_id;
	}

	public void setGrp_id(int grp_id) {
		this.grp_id = grp_id;
	}

	public String getReporter_id() {
		return reporter_id;
	}

	public void setReporter_id(String reporter_id) {
		this.reporter_id = reporter_id;
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

	public GroupVO getGroupVO() {
		return groupVO;
	}

	public void setGroupVO(GroupVO groupVO) {
		this.groupVO = groupVO;
	}
	
	
	
}