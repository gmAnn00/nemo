package nemo.service.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nemo.dao.admin.AdminGroupDAO;
import nemo.vo.group.GroupVO;

public class AdminGroupService {
	AdminGroupDAO adminGrpDAO;
	public AdminGroupService() {
		adminGrpDAO=new AdminGroupDAO();
	}
	
	public List<Map> getGroupList() {
		List<Map> groupList=new ArrayList<Map>();
		groupList=adminGrpDAO.selectAllGroup();
		return groupList;
	}
	
	public void delGroup(int grp_id) {
		adminGrpDAO.delGroup(grp_id);
	}
}
