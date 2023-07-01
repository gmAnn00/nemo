package nemo.vo.group;

import java.sql.Date;

public class GroupVO {
	private int grp_id;
	private String grp_name;
	private String grp_mng;
	private int mem_no;
	private String grp_zipcode;
	private String grp_addr1;
	private String grp_addr2;
	private Date create_date;
	private String grp_intro;
	private int app_st;
	private String main_name;
	private String sub_name;
	private String grp_img;

	public GroupVO() {
		// TODO Auto-generated constructor stub
	}
	

	public GroupVO(int grp_id, String grp_name, String grp_mng, int mem_no, String grp_zipcode, String grp_addr1,
			String grp_addr2, Date create_date, String grp_intro, int app_st, String main_name, String sub_name,
			String grp_img) {
		this.grp_id = grp_id;
		this.grp_name = grp_name;
		this.grp_mng = grp_mng;
		this.mem_no = mem_no;
		this.grp_zipcode = grp_zipcode;
		this.grp_addr1 = grp_addr1;
		this.grp_addr2 = grp_addr2;
		this.create_date = create_date;
		this.grp_intro = grp_intro;
		this.app_st = app_st;
		this.main_name = main_name;
		this.sub_name = sub_name;
		this.grp_img = grp_img;
	}


	public int getGrp_id() {
		return grp_id;
	}

	public void setGrp_id(int grp_id) {
		this.grp_id = grp_id;
	}

	public String getGrp_name() {
		return grp_name;
	}

	public void setGrp_name(String grp_name) {
		this.grp_name = grp_name;
	}

	public String getGrp_mng() {
		return grp_mng;
	}

	public void setGrp_mng(String grp_mng) {
		this.grp_mng = grp_mng;
	}

	public int getMem_no() {
		return mem_no;
	}

	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}

	public String getGrp_zipcode() {
		return grp_zipcode;
	}

	public void setGrp_zipcode(String grp_zipcode) {
		this.grp_zipcode = grp_zipcode;
	}

	public String getGrp_addr1() {
		return grp_addr1;
	}

	public void setGrp_addr1(String grp_addr1) {
		this.grp_addr1 = grp_addr1;
	}

	public String getGrp_addr2() {
		return grp_addr2;
	}

	public void setGrp_addr2(String grp_addr2) {
		this.grp_addr2 = grp_addr2;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getGrp_intro() {
		return grp_intro;
	}

	public void setGrp_intro(String grp_intro) {
		this.grp_intro = grp_intro;
	}

	public int getApp_st() {
		return app_st;
	}

	public void setApp_st(int app_st) {
		this.app_st = app_st;
	}

	public String getMain_name() {
		return main_name;
	}

	public void setMain_name(String main_name) {
		this.main_name = main_name;
	}

	public String getSub_name() {
		return sub_name;
	}

	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}

	public String getGrp_img() {
		return grp_img;
	}

	public void setGrp_img(String grp_img) {
		this.grp_img = grp_img;
	}

	@Override
	public String toString() {
		return "GroupVO [grp_id=" + grp_id + ", grp_name=" + grp_name + ", grp_mng=" + grp_mng + ", mem_no=" + mem_no
				+ ", grp_zipcode=" + grp_zipcode + ", grp_addr1=" + grp_addr1 + ", grp_addr2=" + grp_addr2
				+ ", create_date=" + create_date + ", grp_intro=" + grp_intro + ", app_st=" + app_st + ", main_name="
				+ main_name + ", sub_name=" + sub_name + ", grp_img=" + grp_img + "]";
	}
	
	
	
	
}
