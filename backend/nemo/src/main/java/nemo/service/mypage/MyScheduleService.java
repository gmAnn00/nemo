package nemo.service.mypage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nemo.dao.mypage.MyScheduleDAO;

public class MyScheduleService {
	MyScheduleDAO myScheduleDAO = new MyScheduleDAO();

	//다가오는 일정 가져오는 메서드
	public List<Map> selectComSchedule(String user_id) {
		List<Map> commingScheduleList = new ArrayList();
		commingScheduleList = myScheduleDAO.selectComSchedule(user_id);
		return commingScheduleList;
	}

	//이번달 일정있는 날 체크 메서드(달력용)
	public List getScheduleDate(String user_id, String currentYM) {		
		List scheduleDateList = new ArrayList();
		scheduleDateList = myScheduleDAO.selectThisMonthSchedule(user_id, currentYM);
		return scheduleDateList;
	}
	
	//이번달 일정 조회 메서드
	public List<Map> selectSchedule(String user_id, String currentYM) {
		List<Map> scheduleList = new ArrayList();		
		scheduleList = myScheduleDAO.selectSchedule(user_id, currentYM);			
		return scheduleList;
	}
	
}
