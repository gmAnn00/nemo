package nemo.service.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nemo.dao.admin.AdminUserDAO;

public class AdminUserService {
	AdminUserDAO adminUserDAO;
	public AdminUserService() {
		adminUserDAO= new AdminUserDAO();
	}
	
	public List<Map> getUserList(){
		List<Map> userList=new ArrayList<Map>();
		userList=adminUserDAO.selectAllUser();
		return userList;
	}
	
	public void delUser(String user_id) {
		adminUserDAO.delUser(user_id);
	}
}
