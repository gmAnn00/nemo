package nemo.service.user;


import nemo.dao.user.LoginDAO;
import nemo.vo.user.UserVO;

public class LoginService {
	LoginDAO dao;
	
	
	public LoginService() {
		dao = new LoginDAO();	
	
	}
	
	// 회원 여부 확인 메소드(로그인 메소드)
	public Boolean isExisted(UserVO userVO) {
		return dao.isExisted(userVO);
	}

	// 회원정보 가져오는 메소드
	public UserVO selectUserById(String user_id) {
		return dao.selectUserById(user_id);
	}
	//자동로그인 메서드
	
}
