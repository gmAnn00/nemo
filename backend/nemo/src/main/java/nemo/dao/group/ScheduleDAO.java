package nemo.dao.group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.group.ScheduleVO;
import nemo.vo.user.UserVO;

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
	        
	        String attendQuery = "INSERT INTO attend_tbl values(?,?,?)";
	        pstmt = conn.prepareStatement(attendQuery);
	        pstmt.setInt(1, grp_id);
	        pstmt.setTimestamp(2, schedule);
	        pstmt.setString(3, user_id);
	        pstmt.executeQuery();
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
		String scheduleOldStr = dateFormat.format(scheduleOldTS);
		
		try {
			conn = dataFactory.getConnection();
			boolean isSameDate = scheduleNewStr.equals(scheduleOldStr);
			
			String checkQuery = "select COUNT(*) from schedule_tbl where grp_id = ? AND TO_CHAR(schedule, 'YYYY-MM-DD') = ?";
			
			String updateQuery = "update schedule_tbl set schedule = ?, user_id = ?, sche_title = ?, sche_cont = ?, location = ?"
					+ " where grp_id = ? and schedule = ?";

			if(isSameDate) {
				// 일정 수정에서 날짜를 변경하지 않았으면 그냥 업데이트
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
			}else {
				// 일정 수정에서 날짜를 변경했으면 변경한 날짜가 이미 있는지 확인하고 있으면 거절, 없으면 업데이트
				pstmt = conn.prepareStatement(checkQuery);
				pstmt.setInt(1, grp_id);
				pstmt.setString(2, scheduleNewStr);
				
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				int count = rs.getInt(1);
				rs.close();
				pstmt.close();
				
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
			}

		} catch (Exception e) {
			System.out.println("일정 수정 중 오류");
			e.printStackTrace();
		}
		return true;
	}
	
	public void joinSchedule(String user_id, int group_id, Date schedule) {
		try {
			System.out.println("DAO schedule = " + schedule);
			java.sql.Date sqlDate = new java.sql.Date(schedule.getTime());
			//System.out.println(sqlDate);
			conn = dataFactory.getConnection();
			String query = "insert into attend_tbl values(?, ?, ?)";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			pstmt.setDate(2, sqlDate);
			pstmt.setString(3, user_id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("스케줄 참가 중 오류");
			e.printStackTrace();
		}
		
	} // end of joinSchedule

	public Boolean isAttend(String user_id, int group_id, String selScheDate) {
		Boolean isAttendResult = false;
		
		try {
			conn = dataFactory.getConnection();
			//int grp_id = scheduleVO.getGrp_id();
			//String user_id = scheduleVO.getUser_id();
			//String query = "select schedule from schedule_tbl where grp_id = ? and schedule = ?";
			String query = "select decode(count(*), '0', 'false', 'true') from attend_tbl where grp_id = ? and TO_CHAR(schedule, 'YYYY-MM-DD') = ? AND user_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			pstmt.setString(2, selScheDate);
			pstmt.setString(3, user_id);

			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				isAttendResult = Boolean.parseBoolean(rs.getString(1));
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("참여 여부 확인 중 오류");
			e.printStackTrace();
		}
		
		return isAttendResult;
	}

	// 일정 참석 취소
	public void cancelSchedule(String user_id, int group_id, Date schedule) {
		try {
			System.out.println("DAO schedule = " + schedule);
			java.sql.Date sqlDate = new java.sql.Date(schedule.getTime());
			//System.out.println(sqlDate);
			conn = dataFactory.getConnection();
			String query = "delete from attend_tbl where grp_id = ? AND schedule = ? AND user_id = ?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			pstmt.setDate(2, sqlDate);
			pstmt.setString(3, user_id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("스케줄 참가 취소 중 오류");
			e.printStackTrace();
		}
		
	}

	// 현재 일정의 참석자 리스트 가져옴
	public List<UserVO> attendUsers(int group_id, String selScheDate) {
		List<UserVO> attendUserList = new ArrayList<UserVO>();
		
		try {
			conn = dataFactory.getConnection();
			String query = "select u.* from attend_tbl a, user_tbl u "
					+ "where a.user_id=u.user_id and a.grp_id = ? and TO_CHAR(a.schedule, 'YYYY-MM-DD') = ?";
			//String query = "select * from schedule_tbl where grp_id = ? and TO_CHAR(schedule, 'YYYY-MM-DD') = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			pstmt.setString(2, selScheDate);
			//pstmt.setString(2, user_id);
			//pstmt.setDate(2, schedule);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				UserVO userVO = new UserVO();
				String user_id = rs.getString("user_id");
				String password = rs.getString("password");
				String user_name = rs.getString("user_name");
				String nickname = rs.getString("nickname");
				String zipcode = rs.getString("zipcode");
				String user_addr1 = rs.getString("user_addr1");
				String user_addr2 = rs.getString("user_addr2");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				java.sql.Date join_date = rs.getDate("join_date");
				java.sql.Date birthdate = rs.getDate("birthdate");
				String user_img = rs.getString("user_img");
				int admin = rs.getInt("admin");
				
				userVO.setUser_id(user_id);
				userVO.setPassword(password);
				userVO.setUser_name(user_name);
				userVO.setNickname(nickname);
				userVO.setZipcode(zipcode);
				userVO.setUser_addr1(user_addr1);
				userVO.setUser_addr2(user_addr2);
				userVO.setPhone(phone);
				userVO.setEmail(email);
				userVO.setJoin_date(join_date);
				userVO.setBirthdate(birthdate);
				userVO.setUser_img(user_img);
				userVO.setAdmin(admin);
				
				attendUserList.add(userVO);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("일정 참석자 불러오는 중 오류");
			e.printStackTrace();
		}
		
		return attendUserList;
	}

	public List<String> getScheduleDate(int group_id, String currentYM) {
		List<String> scheduleDateList = new ArrayList();
		
		try {
			conn=dataFactory.getConnection();
			
			String query = "SELECT substr(schedule, 7, 8) as day";
			query += " FROM schedule_tbl";
			query += " WHERE grp_id=? AND substr(schedule, 1, 5)=? order by day";
			
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			pstmt.setString(2, currentYM);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String day = rs.getString("day");
				scheduleDateList.add(day);			
			}			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return scheduleDateList;	
	}
	
	//다가오는 일정 조회
	public List<Map> selectComSchedule(int group_id) {
		List<Map> commingScheduleList = new ArrayList();
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		
		try {
			conn=dataFactory.getConnection();

			String query = "SELECT ROWNUM as scdNum, A.* FROM (";
			query += " SELECT ROWNUM as scdNum, schedule, sche_title, sche_cont, location";
			query += " FROM schedule_tbl";
			query += " WHERE grp_id=? AND schedule>=sysdate";
			query += " order by schedule) A WHERE scdNum <= 4 ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			//pstmt.setTimestamp(2, now);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Timestamp schedule = rs.getTimestamp("schedule");
				String sche_title = rs.getString("sche_title");
				String sche_cont = rs.getString("sche_cont");
				String location = rs.getString("location");
				
				ScheduleVO scheduleVO = new ScheduleVO();
				scheduleVO.setSchedule(schedule);
				scheduleVO.setSche_title(sche_title);
				scheduleVO.setSche_cont(sche_cont);
				scheduleVO.setLocation(location);
				
			 	String scheduleDate = new SimpleDateFormat("yyyy년 MM월 dd일").format(schedule);			 	
				String scheduleTime = new SimpleDateFormat("HH시 mm분").format(schedule);
			 	
				//Map에 put
				Map scheduleMap = new HashMap();
				scheduleMap.put("scheduleVO", scheduleVO);
				scheduleMap.put("scheduleDate", scheduleDate);
				scheduleMap.put("scheduleTime", scheduleTime);				
				scheduleMap.put("date", schedule); //오늘이랑 DB일정 시간비교용
				
				commingScheduleList.add(scheduleMap);
			}			
			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("그룹의 다가오는 일정 조회 중 에러 ");
			e.printStackTrace();
		}
		
		return commingScheduleList;
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