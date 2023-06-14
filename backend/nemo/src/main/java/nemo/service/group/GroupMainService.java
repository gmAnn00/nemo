package nemo.service.group;

import nemo.dao.group.GroupMainDAO;
import nemo.vo.group.GroupVO;

public class GroupMainService {
	GroupMainDAO groupMainDAO;
	
	public GroupMainService() {
		groupMainDAO = new GroupMainDAO();
	}

	public GroupVO selectGroupById(int group_id) {
		GroupVO groupVO = groupMainDAO.selectGroupById();
		return groupVO;
	}

}
