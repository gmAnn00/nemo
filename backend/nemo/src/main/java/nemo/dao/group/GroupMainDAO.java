package nemo.dao.group;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.board.BoardVO;
import nemo.vo.board.user.UserVO;
import nemo.vo.group.GroupVO;
import nemo.vo.schedule.ScheduleVO;

public class GroupMainDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public GroupMainDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");

		} catch (Exception e) {
			System.out.println("GroupMainDAO: DB 연결 오류");
			e.printStackTrace();
		}
	} // end of Constructor

	public List<BoardVO> selectPreviewBoardById(int group_id) {
		List<BoardVO> boardsList = new ArrayList<BoardVO>();
		BoardVO boardVO = new BoardVO();
		try {
			conn = dataFactory.getConnection();
			
			String query = "SELECT ROWNUM, b.* FROM "
					+ "(SELECT * FROM board_tbl where grp_id=? order by article_no desc) b "
					+ "WHERE ROWNUM <=3";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String title = rs.getString("title");
				String nickname = rs.getString("user_id");
				Date create_date = rs.getDate("create_date");
				
				boardVO.setTitle(title);
				boardVO.setUser_id(nickname);
				boardVO.setCreate_date(create_date);
				
				boardsList.add(boardVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("GroupMainDAO: selectPreviewBoardById 오류");
			e.printStackTrace();
		}
		
		
		
		return boardsList;
	} // end of selectPreviewBoardById

	public List<ScheduleVO> selectPreviewScheduleById(int group_id) {
		List<ScheduleVO> schedulesList = new ArrayList<ScheduleVO>();
		ScheduleVO scheduleVO = new ScheduleVO();
		
		try {
			conn = dataFactory.getConnection();
			
			String query = "SELECT ROWNUM, s.* FROM "
					+ "(SELECT * FROM schedule_tbl where grp_id=? order by schedule desc) s "
					+ "WHERE ROWNUM <=3";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Timestamp schedule = rs.getTimestamp("schedule");
				String sche_title = rs.getString("sche_title");
				String location = rs.getString("location");
				
				scheduleVO.setSchedule(schedule);
				scheduleVO.setSche_title(sche_title);
				scheduleVO.setLocation(location);

				schedulesList.add(scheduleVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("GroupMainDAO: selectPreviewScheduleById 오류");
			e.printStackTrace();
		}
		
		
		return schedulesList;
	} // end of selectPreviewScheduleById

	// 소모임에서 멤버 불러오는 메소드
	public List<UserVO> selectMemberById(int group_id) {
		List<UserVO> usersList = new ArrayList<UserVO>();
		
		try {
			conn = dataFactory.getConnection();
			String query = "select u.* from user_tbl u, grpjoin_tbl g where g.grp_id = ? and g.user_id = u.user_id";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
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
				Date join_date = rs.getDate("join_date");
				Date birthdate = rs.getDate("birthdate");
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
				
				usersList.add(userVO);
				
			}
			rs.close();
			pstmt.close();
			conn.close();
			
			
		} catch (Exception e) {
			System.out.println("GroupMainDAO: selectMemberById 오류");
			e.printStackTrace();
		}
		
		return usersList;
	} // end of selectMemberById

	

}
