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
		//id로 스케쥴정보를 메소드
		scheduleList = myScheduleDAO.selectSchedule(user_id);			
		return scheduleList;
	}

}
