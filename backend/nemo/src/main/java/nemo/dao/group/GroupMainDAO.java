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
			
			
		} catch (Exception e) {
			System.out.println("GroupMainDAO: selectPreviewScheduleById 오류");
			e.printStackTrace();
		}
		
		
		return schedulesList;
	} // end of selectPreviewScheduleById

	

}
