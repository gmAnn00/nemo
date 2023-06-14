package nemo.service.group;

import nemo.dao.group.CreateGroupDAO;
import nemo.vo.group.GroupVO;

public class CreateGroupService {
	CreateGroupDAO createGroupDAO;
	
	public CreateGroupService() {
		createGroupDAO = new CreateGroupDAO();
	}

	public int createGroup(GroupVO groupVO) {
		return createGroupDAO.createGroup(groupVO);
	}

}
