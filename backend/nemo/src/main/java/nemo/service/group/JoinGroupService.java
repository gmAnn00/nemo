<<<<<<< HEAD
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
=======
package nemo.service.group;

import nemo.dao.group.JoinGroupDAO;

public class JoinGroupService {
	JoinGroupDAO joinGroupDAO;
	
	public JoinGroupService() {
		joinGroupDAO = new JoinGroupDAO();
	}

	public boolean joinGroup(String user_id, int group_id) {
		boolean result = false;
		boolean wait = false;
		
		wait = joinGroupDAO.isWait(group_id);
		
		if(wait == false) {
			result = joinGroupDAO.joinGroup(user_id, group_id);
		}else {
			result = joinGroupDAO.joinWaitList(user_id, group_id);
		}
		
		
		return result;
	}

	public boolean isMember(String user_id, int group_id) {
		return joinGroupDAO.isMember(user_id, group_id);
	}

	public boolean isWait(int group_id) {
		return joinGroupDAO.isWait(group_id);
	}

	public void joinWaitList(String user_id, int group_id) {
		joinGroupDAO.joinWaitList(user_id, group_id);
		
	}

}
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
