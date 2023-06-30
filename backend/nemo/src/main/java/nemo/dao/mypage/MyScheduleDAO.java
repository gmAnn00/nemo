package nemo.dao.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.group.ScheduleVO;

public class MyScheduleDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	public MyScheduleDAO() {
		try {
			//커넥션 풀 - JNDI(Java Naming Directory Interface)
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");			
		} catch (Exception e) {
			//Servers - context.xml 확인
			System.out.println("DB연결오류");
		}		
	}
	
	
	//일정 조회 메서드
	public List<Map> selectSchedule(String user_id) {
		List<Map> scheduleList = new ArrayList();		
		
		try {
			conn=dataFactory.getConnection();

			String query = "SELECT g.grp_id, g.grp_name, g.grp_img, a.schedule, s.sche_title, s.sche_cont, s.location";
			query += " FROM attend_tbl a, group_tbl g, schedule_tbl s, user_tbl u";
			query += " WHERE u.user_id=? AND u.user_id=a.user_id AND g.grp_id=a.grp_id AND a.grp_id=s.grp_id AND a.schedule=s.schedule";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {				
				//user_id = rs.getString("user_id");
				Timestamp schedule = rs.getTimestamp("schedule");				
				int grp_id = rs.getInt("grp_id");
				String grp_name = rs.getString("grp_name");
				String grp_img = rs.getString("grp_img");	
				String sche_title = rs.getString("sche_title");
				String sche_cont = rs.getString("sche_cont");
				String location = rs.getString("location");
								
				ScheduleVO scheduleVO =  new ScheduleVO();	
				scheduleVO.setUser_id(user_id);
				scheduleVO.setSchedule(schedule);
				scheduleVO.setGrp_id(grp_id);
				scheduleVO.setSche_title(sche_title);
				scheduleVO.setSche_cont(sche_cont);
				scheduleVO.setLocation(location);		
											 	
			 	//SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일");
			 	//SimpleDateFormat time = new SimpleDateFormat("HH시 mm분");			 	
			 	//String scheduleDate = date.format(schedule);
			 	//String scheduleTime = time.format(schedule);
			 	//System.out.println(scheduleDate);
			 	//System.out.println(scheduleTime);
			 	String scheduleDate = new SimpleDateFormat("yyyy년 MM월 dd일").format(schedule);
			 	String scheduleTime =  new SimpleDateFormat("HH시 mm분").format(schedule);
			 	
			 	//Map에 put
				Map scheduleMap = new HashMap();
				scheduleMap.put("scheduleVO", scheduleVO);
				scheduleMap.put("grp_name", grp_name);
				scheduleMap.put("grp_img", grp_img);
				scheduleMap.put("scheduleDate", scheduleDate);
				scheduleMap.put("scheduleTime", scheduleTime);
				
				scheduleList.add(scheduleMap);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("나의 전체 일정 조회 중 에러 ");
			e.printStackTrace();
		}	
		return scheduleList;
	}

	//이번달 스케쥴 날짜 조회 메서드
	public List selectThisMonthSchedule(String user_id, String currentYM) {
		List<Map> scheduleDateList = new ArrayList();		
		
		try {
			conn=dataFactory.getConnection();
			
			String query = "SELECT s.grp_id, substr(s.schedule, 7, 8) as day";
			query += " FROM attend_tbl a, schedule_tbl s, user_tbl u";
			query += " WHERE u.user_id=? AND u.user_id=a.user_id AND a.grp_id=s.grp_id AND a.schedule=s.schedule AND substr(s.schedule, 1, 5)=?";
			//그냥 그룹 일정 조회는 attend_tbl없어도 됨
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			pstmt.setString(2, currentYM);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {				
				System.out.println("rs.next 오나요");
				int grp_id = rs.getInt("grp_id");
				String day = rs.getString("day");
								
				ScheduleVO scheduleVO =  new ScheduleVO();	
				scheduleVO.setUser_id(user_id);
				scheduleVO.setGrp_id(grp_id);
				//scheduleVO.setSche_title(day); //일단 String인 title에(아무데나) 집어넣음
					
			 	//Map에 put
				Map scheduleMap = new HashMap();
				scheduleMap.put("scheduleVO", scheduleVO);
				scheduleMap.put("day", day);
				System.out.println(scheduleMap.toString());
				scheduleDateList.add(scheduleMap);
				System.out.println(scheduleDateList.toString());
				
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("나의 이번달 일정 조회 중 에러");
			e.printStackTrace();
		}	
		return scheduleDateList;		
	}


}
