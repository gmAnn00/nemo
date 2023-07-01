package nemo.dao.schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.schedule.ScheduleVO;

public class ScheduleDAO {
	private Connection conn;	//라이브러리에 커넥션풀과 DB가 있어야한다.
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public ScheduleDAO() {
		try {
			//JNDI(Java Naming and Directory Interface)를 이용해 DB연결
			// 필요한 자원을 키/값의 쌍으로 저장한 후 필요할 때 키를 이용해 값을 얻는 방법
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");	//(읽어 올 데이터베이스)
		} catch (Exception e) {
			System.out.println("DB연결 오류");
		}
	}
	
	//스케줄 보여주기
	public List<ScheduleVO> listSchedule(){
		List<ScheduleVO> scheduleVOList = new ArrayList<ScheduleVO>();
		
		return scheduleVOList;
	}
	
	//새 일정 추가하기
	public ScheduleVO createSchedule(ScheduleVO scheduleVO) {
		try {
			conn = dataFactory.getConnection();
			Timestamp schedule = scheduleVO.getSchedule();
			
			String grp_id = scheduleVO.getGrp_id();
			String user_id = scheduleVO.getUser_id();
			String sche_title = scheduleVO.getSche_title();
			String sche_cont = scheduleVO.getSche_cont();
			String location = scheduleVO.getLocation();
			String query = "insert into schedule_tbl (schedule, grp_id, user_id, sche_title, sche_cont, location) values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setTimestamp(1, schedule);
			pstmt.setString(2, grp_id);
			pstmt.setString(3, user_id);
			pstmt.setString(4, sche_title);
			pstmt.setString(5, sche_cont);
			pstmt.setString(6, location);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("새 일정 추가하기 중 오류");
			e.printStackTrace();
		}
		return scheduleVO;
	}
	
}
