package nemo.dao.group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.group.ScheduleVO;

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
	/*public ScheduleVO createSchedule(ScheduleVO scheduleVO) {
		try {
			conn = dataFactory.getConnection();
			Timestamp schedule = scheduleVO.getSchedule();
			int grp_id = scheduleVO.getGrp_id();
			String user_id = scheduleVO.getUser_id();
			String sche_title = scheduleVO.getSche_title();
			String sche_cont = scheduleVO.getSche_cont();
			String location = scheduleVO.getLocation();
			String query = "insert into schedule_tbl (schedule, grp_id, user_id, sche_title, sche_cont, location) values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setTimestamp(1, schedule);
			pstmt.setInt(2, grp_id);
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
	}*/
	
	//새일정 추가하기 1개이상 불가
	public boolean createSchedule(ScheduleVO scheduleVO) {
	    try {
	        conn = dataFactory.getConnection();
	        Timestamp schedule = scheduleVO.getSchedule();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        String scheduleString = dateFormat.format(schedule);
	        
	        int grp_id = scheduleVO.getGrp_id();
	        String user_id = scheduleVO.getUser_id();
	        String sche_title = scheduleVO.getSche_title();
	        String sche_cont = scheduleVO.getSche_cont();
	        String location = scheduleVO.getLocation();

	        String checkQuery = "SELECT COUNT(*) FROM schedule_tbl WHERE TO_CHAR(schedule, 'YYYY-MM-DD') = ?";
	        pstmt = conn.prepareStatement(checkQuery);
	        pstmt.setString(1, scheduleString);
	        ResultSet rs = pstmt.executeQuery();
	        rs.next();
	        int existingCount = rs.getInt(1);
	        pstmt.close();
	        if (location == "" || location == null || existingCount > 0) {
	        	conn.close();
	        	return false;
	        }
	        // 일정 추가 쿼리
	        String insertQuery = "INSERT INTO schedule_tbl (schedule, grp_id, user_id, sche_title, sche_cont, location) VALUES (?, ?, ?, ?, ?, ?)";
	        pstmt = conn.prepareStatement(insertQuery);
	        pstmt.setTimestamp(1, schedule);
	        pstmt.setInt(2, grp_id);
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
	    return true;
	}
	
	//날짜 선택시 선택한 날짜를 가져오는 메소드
	public ScheduleVO schduleCompare(int grp_id, String schedule) {
		ScheduleVO scheduleVO = new ScheduleVO();
		try {
			conn = dataFactory.getConnection();
			//int grp_id = scheduleVO.getGrp_id();
			//String user_id = scheduleVO.getUser_id();
			//String query = "select schedule from schedule_tbl where grp_id = ? and schedule = ?";
			String query = "select * from schedule_tbl where grp_id = ? and TO_CHAR(schedule, 'YYYY-MM-DD') = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, grp_id);
			pstmt.setString(2, schedule);
			//pstmt.setString(2, user_id);
			//pstmt.setDate(2, schedule);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				scheduleVO.setSchedule(rs.getTimestamp("schedule"));
				scheduleVO.setSche_title(rs.getString("sche_title"));
				scheduleVO.setSche_cont(rs.getString("sche_cont"));
				scheduleVO.setLocation(rs.getString("location"));
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("날짜 선택 이벤트중 오류");
			e.printStackTrace();
		}
		return scheduleVO;
	}
	
	//일정 삭제 메소드
	public void delSchedule(int group_id, Date schedule) {
		try {
			System.out.println("DAO schedule = " + schedule);
			java.sql.Date sqlDate = new java.sql.Date(schedule.getTime());
			//System.out.println(sqlDate);
			conn = dataFactory.getConnection();
			String query = "delete from schedule_tbl where grp_id = ? AND schedule = ?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			pstmt.setDate(2, sqlDate);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("일정 삭제중 오류");
			e.printStackTrace();
		}
	}

	
	public List<Timestamp> getSchedule(int group_id) {
	    List<Timestamp> scheduleList = new ArrayList<>();
	    
	    try {
	        conn = dataFactory.getConnection();
	        String query = "SELECT schedule FROM schedule_tbl WHERE group_id = ?";
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, group_id);
	        ResultSet rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            Timestamp schedule = rs.getTimestamp("schedule");
	            scheduleList.add(schedule);
	        }
	        rs.close();
	        pstmt.close();
	        conn.close();
	    } catch (Exception e) {
	        System.out.println("일정 가져오기 중 오류");
	        e.printStackTrace();
	    }
	    return scheduleList;
	}
	
	//일정 수정
	public boolean modSchedule(ScheduleVO scheduleVO, Timestamp scheduleOldTS) {	
		
		Timestamp schedule = scheduleVO.getSchedule();
		int grp_id = scheduleVO.getGrp_id();
		String user_id = scheduleVO.getUser_id();
		String sche_title = scheduleVO.getSche_title();
		String sche_cont = scheduleVO.getSche_cont();
		String location = scheduleVO.getLocation();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String scheduleNewStr = dateFormat.format(schedule);
		
		try {
			conn = dataFactory.getConnection();
			
			String checkQuery = "select COUNT(*) from schedule_tbl where grp_id = ? AND TO_CHAR(schedule, 'YYYY-MM-DD') = ?";
			
			String updateQuery = "update schedule_tbl set schedule = ?, user_id = ?, sche_title = ?, sche_cont = ?, location = ?"
						+ " where grp_id = ? and schedule = ?";
			
			pstmt = conn.prepareStatement(checkQuery);
			pstmt.setInt(1, grp_id);
			pstmt.setString(2, scheduleNewStr);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			pstmt.close();
			rs.close();
			if (count > 0 || location == "" || location == null) {
				conn.close();
				return false;
			} else {
				System.out.println("count=0");
				pstmt = conn.prepareStatement(updateQuery);
			    pstmt.setTimestamp(1, schedule);
			    pstmt.setString(2, user_id);
			    pstmt.setString(3, sche_title);
			    pstmt.setString(4, sche_cont);
			    pstmt.setString(5, location);
			    pstmt.setInt(6, grp_id);
			    pstmt.setTimestamp(7, scheduleOldTS);
			    pstmt.executeUpdate();
				pstmt.close();
				conn.close();
			}
		} catch (Exception e) {
			System.out.println("일정 수정 중 오류");
			e.printStackTrace();
		}
		return true;
	}
	/*public ScheduleVO findGrp(int group_id) {
		ScheduleVO grpFind = null;
		try {
			conn = dataFactory.getConnection();
			String query = "select * from schedule_tbl where grp_id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			ResultSet rs = pstmt.executeQuery();	//쿼리문을 수행해달라는 명령
			rs.next();
			Timestamp schedule = rs.getTimestamp("schedule");
			int grp_id = rs.getInt("grp_id");
			String sche_title = rs.getString("sche_title");
			String sche_cont = rs.getString("sche_cont");
			String location = rs.getString("location");
			
			grpFind = new ScheduleVO(schedule, grp_id, sche_title, sche_cont, location);
			pstmt.close();
			rs.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("수정할 DB 자료 찾는 중 에러");
			e.printStackTrace();
		}
		return grpFind;
	}*/
	
	
	//날짜선택시 스케줄 유무 확인
	/*public ScheduleVO selectScheduletbl(ScheduleVO scheduleVO) {
		try {
			conn = dataFactory.getConnection();
			
			int grp_id = scheduleVO.getGrp_id();
			String user_id = scheduleVO.getUser_id();
			String query = "SELECT schedule, grp_id, user_id, sche_title, sche_cont, location FROM SCHEDULE_TBL WHERE USER_ID = ? AND GRP_ID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, grp_id);
			pstmt.setString(2, user_id);
			pstmt.executeQuery(query);
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				
				int article_no=rs.getInt("article_no");
				String user_id=rs.getString("user_id");
				String nickname=rs.getString("nickname");
				int group_id=rs.getInt("grp_id");
				Date create_date=rs.getDate("create_date");
				String title=rs.getString("title");
				String brackets=rs.getString("brackets");
				int view_cnt=rs.getInt("view_cnt");
				int com_cnt=rs.getInt("com_cnt");
				
				ScheduleVO sch=new ScheduleVO();
				
				sch.setGrp_id(grp_id);
				boardVO.setArticle_no(article_no);
				boardVO.setUser_id(user_id);
				boardVO.setNickname(nickname);
				boardVO.setGrp_id(group_id);
				boardVO.setCreate_date(create_date);
				boardVO.setTitle(title);
				boardVO.setBrackets(brackets);
				boardVO.setView_cnt(view_cnt);
				boardVO.setCom_cnt(com_cnt);
				
				articleList.add(boardVO);
			} // End Of While
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("새 일정 추가하기 중 오류");
			e.printStackTrace();
		}
		return scheduleVO;
	}*/
}
