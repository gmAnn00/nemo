package nemo.service.group;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import nemo.dao.group.ScheduleDAO;
import nemo.vo.group.ScheduleVO;
import nemo.vo.user.UserVO;

public class ScheduleService {
	ScheduleDAO scheduleDAO;
	
	//생성자 생성
	public ScheduleService() {
		scheduleDAO  = new ScheduleDAO();
	}
	
	public boolean addSchedule(ScheduleVO scheduleVO) {
		return scheduleDAO.createSchedule(scheduleVO);
	}
	/*
	public boolean schCompare(ScheduleVO scheduleVO) {
		return scheduleDAO.schduleCompare(scheduleVO);
	}*/
	public ScheduleVO schCompare(int grp_id, String schedule) {
		return scheduleDAO.schduleCompare(grp_id, schedule);
	}

	public boolean modSchedule(ScheduleVO scheduleVO, Timestamp scheduleOldTS) {
		return scheduleDAO.modSchedule(scheduleVO, scheduleOldTS);
	}

	public void joinSchedule(String user_id, int group_id, Date schedule) {
		scheduleDAO.joinSchedule(user_id, group_id, schedule);
		
	}

	public Boolean isAttend(String user_id, int group_id, String selScheDate) {
		return scheduleDAO.isAttend(user_id, group_id, selScheDate);
	}
	
	// 일정 참석 취소
	public void cancelSchedule(String user_id, int group_id, Date schedule) {
		scheduleDAO.cancelSchedule(user_id, group_id, schedule);
		
	}

	// 
	public List<UserVO> attendUsers(int group_id, String selScheDate) {
		return scheduleDAO.attendUsers(group_id, selScheDate);
	}

	public List<String> getScheduleDate(int group_id, String currentYM) {
		return scheduleDAO.getScheduleDate(group_id, currentYM);
	}

	public List<Map> selectComSchedule(int group_id) {		
		return scheduleDAO.selectComSchedule(group_id);
	}
	
	
	
	

	/*public ScheduleVO findGrp(int group_id) {
		return scheduleDAO.findGrp(group_id);
	}*/

	
	
}