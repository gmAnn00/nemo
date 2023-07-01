package nemo.service.group;


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
}