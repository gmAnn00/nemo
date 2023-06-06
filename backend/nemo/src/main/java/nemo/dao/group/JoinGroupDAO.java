package nemo.dao.group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JoinGroupDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	public JoinGroupDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");

		} catch (Exception e) {
			System.out.println("JoinGroupDAO: DB 연결 오류");
			e.printStackTrace();
		}
	}

	public boolean joinGroup(String user_id, int group_id) {
		boolean member = isMember(user_id, group_id);
		boolean full = isFull(group_id);

		if (!member && !full) {
			try {
				conn = dataFactory.getConnection();
				String query = "insert into grpjoin_tbl (user_id, grp_id) values(?,?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, user_id);
				pstmt.setInt(2, group_id);
				pstmt.executeUpdate();
				
				pstmt.close();
				conn.close();
				return true;

			} catch (Exception e) {
				System.out.println("joinGroup 중 오류");
				e.printStackTrace();
			}
			return false;
		} else {
			return false;
		}


	}

	// user_id 가 group_id 의 멤버면 true, 멤버가 아니면 false 반환
	public boolean isMember(String user_id, int group_id) {
		int rsCnt = 0;
		try {
			conn = dataFactory.getConnection();
			String query = "select count(*) as cnt from grpjoin_tbl where user_id = ? and grp_id=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, group_id);
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			rsCnt = rs.getInt("cnt");
			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("isMember 중 오류");
			e.printStackTrace();
		}

		if (rsCnt == 0) {
			return false;
		} else {
			return true;
		}
	} // end of isMember

	// 소모임이 꽉 차있으면 true, 아니면 false 반환
	public boolean isFull(int group_id) {
		int groupNum = -1;
		int maxNum = -2;
		String query;

		try {
			conn = dataFactory.getConnection();
			query = "select count(*) as cnt from grpjoin_tbl where grp_id=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			groupNum = rs.getInt("cnt");

			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("isFull 중 오류");
			e.printStackTrace();
		}
		
		try {
			conn = dataFactory.getConnection();
			query = "select mem_no from group_tbl where grp_id=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			maxNum = rs.getInt("mem_no");
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("isFull 중 오류");
			e.printStackTrace();
		}

		if (groupNum == maxNum) {
			return true;
		} else {
			return false;
		}

	} // end of isFull

}
