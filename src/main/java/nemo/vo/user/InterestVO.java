package nemo.vo.user;

public class InterestVO {
	private String user_id;
	private String main_name;
	private String sub_name;
	
	public InterestVO() {
		System.out.println("InterestsVO 생성자 호출");
}
	public InterestVO(String user_id, String sub_name, String main_name) {
		super();
		this.user_id = user_id;
		this.sub_name = sub_name;
		this.main_name = main_name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

}
