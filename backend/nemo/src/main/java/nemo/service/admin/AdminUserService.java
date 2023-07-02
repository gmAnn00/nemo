package nemo.service.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nemo.dao.admin.AdminUserDAO;
import nemo.dao.user.UserDAO;

public class AdminUserService {
	AdminUserDAO adminUserDAO;
	UserDAO userDAO;
	public AdminUserService() {
		adminUserDAO= new AdminUserDAO();
		userDAO=new UserDAO();
	}
	
	public List<Map> getUserList(){
		List<Map> userList=new ArrayList<Map>();
		userList=adminUserDAO.selectAllUser();
		return userList;
	}
	
	public boolean delUser(String user_id) {
		boolean isAdmin=false;
		isAdmin=userDAO.checkAdmin(user_id);
		if(!isAdmin) {
			adminUserDAO.delUser(user_id);
		}
		return isAdmin;
	}
}
