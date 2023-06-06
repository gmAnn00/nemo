package nemo.service.group;

import nemo.dao.group.JoinGroupDAO;

public class JoinGroupService {
	JoinGroupDAO joinGroupDAO;
	
	public JoinGroupService() {
		joinGroupDAO = new JoinGroupDAO();
	}

	public boolean joinGroup(String user_id, int group_id) {
		boolean result = false;
		
		result = joinGroupDAO.joinGroup(user_id, group_id);
		
		return result;
	}

}
