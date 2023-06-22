package nemo.service.member;

import nemo.dao.member.JoinDAO;
import nemo.vo.member.UserVO;

public class JoinService {
	JoinDAO dao;
	
	public JoinService() {
		dao = new JoinDAO();
	}
	//입력한 회원정보 받기
	public void addMember(UserVO userVO) {
		
		
	}
}
