package nemo.service.mypage;

import java.util.ArrayList;
import java.util.List;

import nemo.dao.mypage.MyInterestDAO;
import nemo.vo.mypage.InterestVO;
import nemo.vo.user.UserVO;

public class MyInterestService {
	MyInterestDAO myInterestDAO;
	List<InterestVO> interestList;
	
	public MyInterestService() {
		myInterestDAO = new MyInterestDAO();
		interestList = new ArrayList<>();
	}	
	
	
	//마이페이지 관심사 조회
	public List<InterestVO> searchInterestById(String user_id) {
		List<InterestVO> interestList = myInterestDAO.searchInterestById(user_id);
		return interestList;
	}
	
	//관심사 수정
	public void modInterest(String user_id, List interestList) {
		myInterestDAO.modInterest(user_id, interestList);
	}
}
