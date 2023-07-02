package nemo.service.mypage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nemo.dao.mypage.MyScheduleDAO;

public class MyScheduleService {
	MyScheduleDAO myScheduleDAO = new MyScheduleDAO();
	
	//일정 조회 메서드
	public List<Map> selectSchedule(String user_id) {
		List<Map> scheduleList = new ArrayList();
		//id로 스케쥴정보를 가져오는 메소드
		scheduleList = myScheduleDAO.selectSchedule(user_id);			
		return scheduleList;
	}
	//이번달 일정있는 날짜 가져오는 메서드
	public List getScheduleDate(String user_id, String currentYM) {		
		List scheduleDateList = new ArrayList();
		
		//id로 스케쥴 있는 날짜 list 가져오기
		scheduleDateList = myScheduleDAO.selectThisMonthSchedule(user_id, currentYM);
		return scheduleDateList;
	}

}
