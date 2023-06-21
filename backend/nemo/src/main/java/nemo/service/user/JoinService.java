package nemo.service.user;

import nemo.dao.user.JoinDAO;
import nemo.vo.common.UserVO;


public class JoinService {
	JoinDAO dao;
	
	public JoinService() {
		dao = new JoinDAO();
	}
	//입력한 회원정보 받기
	public void addMember(UserVO userVO) {
		
		
	}
}