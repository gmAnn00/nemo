package nemo.service.group;

import java.util.List;

import nemo.dao.group.GroupDAO;
import nemo.dao.group.GroupMainDAO;
import nemo.vo.board.BoardVO;
import nemo.vo.group.GroupVO;
import nemo.vo.group.ScheduleVO;
import nemo.vo.user.UserVO;

public class GroupMainService {
	GroupMainDAO groupMainDAO;
	GroupDAO groupDAO;
	
	public GroupMainService() {
		groupMainDAO = new GroupMainDAO();
		groupDAO = new GroupDAO();
	}

	public GroupVO selectGroupById(int group_id) {
		GroupVO groupVO = groupDAO.selectGroupById(group_id);
		return groupVO;
	}

	public int selectGroupNumById(int group_id) {
		return groupDAO.selectGroupNumById(group_id);
	}

	public List<BoardVO> selectPreviewBoardById(int group_id) {
		return groupMainDAO.selectPreviewBoardById(group_id);
	}

	public List<ScheduleVO> selectPrviewScheduleById(int group_id) {
		return groupMainDAO.selectPreviewScheduleById(group_id);
	}

	public List<UserVO> selectMemberById(int group_id) {
		return groupMainDAO.selectMemberById(group_id);
	}
	
	public Boolean isMember(String user_id, int group_id) {
		return groupDAO.isMember(user_id, group_id);
	}

	public List<String> grpMng(String user_id) {
		return groupDAO.grpMng(user_id);
	}
	
	public List<String> grpMember(int group_id){
		return groupDAO.grpMember(group_id);
	}
	
	public String grpMng(int group_id) {
		return groupDAO.grpMng(group_id);
	}
	
	

}
