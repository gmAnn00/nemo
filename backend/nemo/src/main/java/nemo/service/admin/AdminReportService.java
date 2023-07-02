package nemo.service.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nemo.dao.admin.GroupReportDAO;
import nemo.dao.admin.MemberReportDAO;

public class AdminReportService {
	GroupReportDAO grpRepDAO;
	MemberReportDAO memRepDAO;
	
	public AdminReportService() {
		grpRepDAO=new GroupReportDAO();
		memRepDAO=new MemberReportDAO();
	}
	
	public List<Map> getRepGroupList(){
		List<Map> groupList=new ArrayList<Map>();
		groupList=grpRepDAO.selectRepGrp();
		return groupList;
	}
	
	public List<Map> getRepUserList(){
		List<Map> userList=new ArrayList<Map>();
		userList=memRepDAO.selectRepUser();
		return userList;
	}

}
