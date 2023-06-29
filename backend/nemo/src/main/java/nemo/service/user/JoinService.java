package nemo.service.user;

import java.util.List;

import nemo.dao.user.JoinDAO;
import nemo.vo.user.InterestVO;
import nemo.vo.user.UserVO;

public class JoinService {
	JoinDAO dao;
	
	public JoinService() {
		dao = new JoinDAO();
	}
	//입력한 회원정보 받기
	public void adduser(UserVO userVO) {
		dao.adduser(userVO);
	}
	//입력한 관심사 정보 받기
		public void addChoice(List<InterestVO> interestsList) {
			dao.addChoice(interestsList);
		}
}
