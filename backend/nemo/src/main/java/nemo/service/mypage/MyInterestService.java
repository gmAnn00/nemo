package nemo.service.mypage;

import java.util.ArrayList;
import java.util.List;

import nemo.dao.mypage.MyInterestDAO;
import nemo.vo.mypage.InterestVO;
import nemo.vo.user.InterestsVO;
import nemo.vo.user.UserVO;

public class MyInterestService {
	MyInterestDAO myInterestDAO;
	List<InterestVO> interestsList;
	
	public MyInterestService() {
		myInterestDAO = new MyInterestDAO();
		interestsList = new ArrayList<>();
	}	
	
	
	//마이페이지 관심사 조회
	public List<InterestVO> searchInterestById(String user_id) {
		List<InterestVO> interestList = myInterestDAO.searchInterestById(user_id);
		return interestList;
	}
	
	//관심사 수정
	public void modInterests(String user_id, List<InterestsVO> interestsList) {
		myInterestDAO.modInterests(user_id, interestsList);
	}
}
