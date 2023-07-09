package nemo.service.group;

import nemo.dao.group.BookmarkDAO;
import nemo.dao.group.JoinGroupDAO;

public class JoinGroupService {
	JoinGroupDAO joinGroupDAO;
	BookmarkDAO bookmarkDAO;
	
	public JoinGroupService() {
		joinGroupDAO = new JoinGroupDAO();
		bookmarkDAO = new BookmarkDAO();
	}

	public boolean joinGroup(String user_id, int group_id) {
		boolean result = false;
		boolean wait = false;
		boolean bookmark = false;
		
		bookmark= bookmarkDAO.isBookmark(user_id, group_id);
		wait = joinGroupDAO.isWait(group_id);
		//bookmark에 있는지 확인
		
		if(wait == false) {
			result = joinGroupDAO.joinGroup(user_id, group_id);
			if(bookmark) {
				bookmarkDAO.deleteBookmark(user_id, group_id);
			}
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
