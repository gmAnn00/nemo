package nemo.service.schedule;

import nemo.dao.schedule.ScheduleDAO;
import nemo.vo.schedule.ScheduleVO;

public class ScheduleService {
	ScheduleDAO scheduleDAO;
	
	//생성자 생성
	public ScheduleService() {
		scheduleDAO  = new ScheduleDAO();
	}
	
	public ScheduleVO addSchedule(ScheduleVO scheduleVO) {
		return scheduleDAO.createSchedule(scheduleVO);
	}
}