package nemo.vo.user;

import java.sql.Date;

public class UserVO {
	private String user_id;
	private String password;
	private String user_name;
	private String nickname;
	private String zipcode;
	private String user_addr1;
	private String user_addr2;
	private String phone;
	private String email;
	private Date join_date;
	private Date birthdate;
	private String user_img;
	private int admin;
	
	
	public UserVO() {	
	}

	public UserVO(String user_id, String user_img) {		
		this.user_id = user_id;
		this.user_img = user_img;
	}

	public UserVO(String user_id, String password, String user_name, String nickname, String zipcode,
			String user_addr1, String user_addr2, String phone, String email, Date birthdate, String user_img) {		
		this.user_id = user_id;
		this.password = password;
		this.user_name = user_name;
		this.nickname = nickname;
		this.zipcode = zipcode;
		this.user_addr1 = user_addr1;
		this.user_addr2 = user_addr2;
		this.phone = phone;
		this.email = email;
		this.birthdate = birthdate;
		this.user_img = user_img;
	}

	public UserVO(String user_id, String password, String user_name, String nickname, String zipcode,
			String user_addr1, String user_addr2, String phone, String email, Date join_date, Date birthdate,
			String user_img, int admin) {
		this.user_id = user_id;
		this.password = password;
		this.user_name = user_name;
		this.nickname = nickname;
		this.zipcode = zipcode;
		this.user_addr1 = user_addr1;
		this.user_addr2 = user_addr2;
		this.phone = phone;
		this.email = email;
		this.join_date = join_date;
		this.birthdate = birthdate;
		this.user_img = user_img;
		this.admin = admin;
	}

	

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getUser_addr1() {
		return user_addr1;
	}

	public void setUser_addr1(String user_addr1) {
		this.user_addr1 = user_addr1;
	}

	public String getUser_addr2() {
		return user_addr2;
	}

	public void setUser_addr2(String user_addr2) {
		this.user_addr2 = user_addr2;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getJoin_date() {
		return join_date;
	}

	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getUser_img() {
		return user_img;
	}

	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	
}