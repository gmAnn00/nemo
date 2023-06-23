package nemo.service.mypage;

import java.util.ArrayList;
import java.util.List;

import nemo.dao.mypage.MyGroupDAO;
import nemo.vo.group.GroupVO;

public class MyGroupService {
	
	MyGroupDAO myGroupDAO;
	GroupVO groupVO;
	
	public MyGroupService() {
		myGroupDAO = new MyGroupDAO();
		groupVO = new GroupVO();
	}
	
	//내가 리더인 소모임조회
	public List<GroupVO> getManagerGrp(String user_id) {
		List<GroupVO> mngGroupList = new ArrayList<>();
		mngGroupList = myGroupDAO.getManagerGrp(user_id);
		return mngGroupList;
	}
	
	//일반회원인 소모임
	public List<GroupVO> getUserGrp(String user_id) {
		List<GroupVO> userGroupList = new ArrayList<>();
		userGroupList = myGroupDAO.getUserGrp(user_id);
		return userGroupList;
	}
	
	//가입 대기중인 소모임
	public List<GroupVO> getWaitGrp(String user_id) {
		List<GroupVO> waitGroupList = new ArrayList<>();
		waitGroupList = myGroupDAO.getWaitGrp(user_id);
		return waitGroupList;
	}

	//찜한 소모임
	public List<GroupVO> getBookMarkGrp(String user_id) {
		List<GroupVO> bookmarkGroupList = new ArrayList<>();
		bookmarkGroupList = myGroupDAO.getBookMarkGrp(user_id);
		return bookmarkGroupList;
	}

}
