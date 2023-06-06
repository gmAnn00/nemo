package nemo.vo.group;

import java.sql.Date;

public class GroupVO {
	private int grp_id;
	private String grp_name;
	private String grp_mng;
	private int mem_no;
	private int grp_zipcode;
	private String grp_addr1;
	private String grp_addr2;
	private Date create_date;
	private String grp_intro;
	private int app_st;
	private String main_name;
	private String sub_name;
	private String grp_mimg;
	private String grp_pimg;

	public GroupVO() {

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

	public int getGrp_zipcode() {
		return grp_zipcode;
	}

	public void setGrp_zipcode(int grp_zipcode) {
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

	public String getGrp_mimg() {
		return grp_mimg;
	}

	public void setGrp_mimg(String grp_mimg) {
		this.grp_mimg = grp_mimg;
	}

	public String getGrp_pimg() {
		return grp_pimg;
	}

	public void setGrp_pimg(String grp_pimg) {
		this.grp_pimg = grp_pimg;
	}

}
