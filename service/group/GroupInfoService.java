package nemo.service.group;

import java.sql.Date;

import nemo.dao.group.GroupDAO;
import nemo.dao.group.GroupInfoDAO;
import nemo.vo.group.GroupVO;

public class GroupInfoService {
	GroupInfoDAO groupInfoDAO;
	GroupDAO groupDAO;
	
	GroupVO groupVO;
	
	public GroupInfoService() {
		groupInfoDAO = new GroupInfoDAO();
		groupDAO = new GroupDAO();
	}

	
	public GroupVO selectGroupById(int group_id) {
		groupVO = groupDAO.selectGroupById(group_id);
		return groupVO;
	}

	// 그룹명수 가져오는 메소드
	public int selectGroupNumById(int group_id) {
		int groupNum = groupInfoDAO.selectGroupNumById(group_id);
		return groupNum;
	}

	// 찜갯수 가져오는 메소드
	public int selectBookmarkNumById(int group_id) {
		int bookmarkNum = groupInfoDAO.selectBookmarkNumById(group_id);
		return bookmarkNum;
	}

	// 소모임장 가져오는 메소드
	public String selectManagerById(int group_id) {
		String manager= groupInfoDAO.selectManagerById(group_id);
		return manager;
	}

	// user_id 가 group_id 에 찜했는지 가져오는 메소드 true 면 찜함, false 면 안함
	public boolean isBookmark(String user_id, int group_id) {
		return groupInfoDAO.isBookmark(user_id, group_id);
	}

	// 최근활동일 구함
	public Date selectRecentDate(int group_id) {
		return groupInfoDAO.selectRecentDate(group_id);
	}


	// 승인 여부 구함
	public int selectAppSt(int group_id) {
		return groupInfoDAO.selectAppSt(group_id);
	}
	
	// 승인 여부 구함

}
