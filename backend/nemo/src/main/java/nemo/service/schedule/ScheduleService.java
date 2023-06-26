package nemo.service.schedule;


import nemo.dao.schedule.ScheduleDAO;
import nemo.vo.schedule.ScheduleVO;

public class ScheduleService {
	ScheduleDAO scheduleDAO;
	
	//생성자 생성
	public ScheduleService() {
		scheduleDAO  = new ScheduleDAO();
	}
	
	public void addSchedule(ScheduleVO scheduleVO) {
		scheduleDAO.createSchedule(scheduleVO);
	}
	/*
	public boolean schCompare(ScheduleVO scheduleVO) {
		return scheduleDAO.schduleCompare(scheduleVO);
	}*/
	public ScheduleVO schCompare(int grp_id, String schedule) {
		return scheduleDAO.schduleCompare(grp_id, schedule);
	}
}