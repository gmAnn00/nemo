package nemo.dao.group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.board.BoardVO;
import nemo.vo.group.GroupVO;

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
		List<BoardVO> boardsList = null;
		try {
			conn = dataFactory.getConnection();
			
			String query = "SELECT ROWNUM, b.* FROM "
					+ "(SELECT * FROM board_tbl where grp_id=1 order by article_no desc) "
					+ "WHERE ROWNUM <=3";
			
			
		} catch (Exception e) {
			System.out.println("GroupMainDAO: selectPreviewBoardById 오류");
			e.printStackTrace();
		}
		
		
		
		return boardsList;
	}

	

}
