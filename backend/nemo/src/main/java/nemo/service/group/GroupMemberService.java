package nemo.service.group;

import java.util.List;

import nemo.dao.group.GroupMemberDAO;
import nemo.vo.board.user.UserVO;

public class GroupMemberService {
	GroupMemberDAO groupMemberDAO;
	
	public GroupMemberService() {
		groupMemberDAO = new GroupMemberDAO();
	}
	
	
	public List<UserVO> selectMemberById(int group_id) {
		return groupMemberDAO.selectMemberById(group_id);
	}


	public void deleteMember(String user_id, int group_id) {
		groupMemberDAO.deleteMember(user_id, group_id);
		
	}
}
