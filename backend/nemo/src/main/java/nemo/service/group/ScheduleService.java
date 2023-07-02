package nemo.service.group;


import java.sql.Timestamp;

import nemo.dao.group.ScheduleDAO;
import nemo.vo.group.ScheduleVO;

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
	
	
	

	/*public ScheduleVO findGrp(int group_id) {
		return scheduleDAO.findGrp(group_id);
	}*/

	
	
}