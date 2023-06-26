package nemo.service.mypage;

import java.util.ArrayList;
import java.util.List;

import nemo.dao.mypage.MyInterestDAO;
import nemo.vo.user.InterestsVO;
import nemo.vo.user.UserVO;

public class MyInterestService {
	MyInterestDAO myInterestDAO;
	List<InterestsVO> interestsList;
	
	public MyInterestService() {
		myInterestDAO = new MyInterestDAO();
		interestsList = new ArrayList<>();
	}	
	
	
	//마이페이지 관심사 조회
	public List<InterestsVO> searchInterestById(String user_id) {
		List<InterestsVO> interestList = myInterestDAO.searchInterestById(user_id);
		return interestList;
	}
	
	//관심사 수정
	public void modInterests(String user_id, List<InterestsVO> interestsList) {
		myInterestDAO.delInterests(user_id);
		myInterestDAO.modInterests(interestsList);
	}
}
