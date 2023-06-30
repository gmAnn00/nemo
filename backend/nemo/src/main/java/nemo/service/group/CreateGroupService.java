<<<<<<< HEAD
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
=======
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
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
