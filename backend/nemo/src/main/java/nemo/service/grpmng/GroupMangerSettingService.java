package nemo.service.grpmng;

import nemo.dao.grpmng.GroupMangerSettingDAO;
import nemo.vo.group.GroupVO;
import nemo.vo.user.UserVO;

//메소드
public class GroupMangerSettingService {
	UserVO userVO;
	GroupVO groupVO;
	GroupMangerSettingDAO groupMangerSettingDAO;
	
	public GroupMangerSettingService() {
		groupMangerSettingDAO=new GroupMangerSettingDAO();
	}
	
	public void updateGroup(GroupVO groupVO, int grp_id) {
		groupMangerSettingDAO.updateGroup(groupVO, grp_id);
	}
	
}
