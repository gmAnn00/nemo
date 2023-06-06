package nemo.dao.group;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BookmarkDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public BookmarkDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");

		} catch (Exception e) {
			System.out.println("BookmarkDAO: DB 연결 오류");
			e.printStackTrace();
		}
	} // end of BookmarkDAO


	// 찜 추가
	public void insertBookmark(String user_id, int group_id) {
		try {
			conn = dataFactory.getConnection();
			String query = "insert into bookmark_tbl(user_id, grp_id) values(?, ?)";
			pstmt = conn.prepareStatement(query);
			System.out.println(query);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, group_id);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("insertBookmark:오류");
			e.printStackTrace();
		}
		
	} // end of insertBookmark
	
	// 찜 삭제
	public void deleteBookmark(String user_id, int group_id) {
		try {
			conn = dataFactory.getConnection();
			String query = "delete from bookmark_tbl where user_id=? and grp_id=?";
			pstmt = conn.prepareStatement(query);
			System.out.println(query);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, group_id);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("deleteBookmark:오류");
			e.printStackTrace();
		}
		
		
	} // end of deleteBookmark

	
	
}// end of class BookmarkDAO
