package nemo.service.group;

import nemo.dao.group.GroupDAO;
import nemo.dao.group.GroupMainDAO;
import nemo.vo.group.GroupVO;

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

}
