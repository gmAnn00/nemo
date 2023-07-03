package nemo.dao.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReportDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	public ReportDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");

		} catch (Exception e) {
			System.out.println("ReportDAO: DB 연결 오류");
			e.printStackTrace();
		}
	}// end of constructor

	// 이미 신고한 소모임인지 확인
	public Boolean isGReport(int group_id, String user_id) {
		Boolean isAlreadyReport = false;
		try {
			conn = dataFactory.getConnection();

			String query = "SELECT decode(count(*), 0, 'false', 'true')"
					+ " FROM greport_tbl where grp_id=? AND reporter_id=?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			pstmt.setString(2, user_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();

			isAlreadyReport = Boolean.parseBoolean(rs.getString(1));
			
			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("ReportDAO: isGReport 오류");
			e.printStackTrace();
		}

		return isAlreadyReport;
	}

	// 소모임 신고
	public void groupReport(int group_id, String user_id) {

		try {
			conn = dataFactory.getConnection();

			String query = "INSERT INTO greport_tbl(grp_id, reporter_id) values(?, ?)";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			pstmt.setString(2, user_id);
			pstmt.executeUpdate();

			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("ReportDAO: groupReport 오류");
			e.printStackTrace();
		}

	}
	
	// 이미 신고한 소모임 멤버인지 확인
	public Boolean isMReport(String user_id, String accused_id) {
		Boolean isAlreadyReport = false;
		try {
			conn = dataFactory.getConnection();

			String query = "SELECT decode(count(*), 0, 'false', 'true')"
					+ " FROM mreport_tbl where reporter_id=? AND accused_id=?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			pstmt.setString(2, accused_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			System.out.println("decode="+rs.getString(1));
			isAlreadyReport = Boolean.parseBoolean(rs.getString(1));
			
			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("ReportDAO: isMReport 오류");
			e.printStackTrace();
		}

		return isAlreadyReport;
	}

	// 소모임 멤버 신고
	public void memberReport(String user_id, String accused_id) {
		try {
			conn = dataFactory.getConnection();

			String query = "INSERT INTO mreport_tbl(reporter_id, accused_id) values(?, ?)";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			pstmt.setString(2, accused_id);
			pstmt.executeUpdate();

			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("ReportDAO: memberReport 오류");
			e.printStackTrace();
		}
		
	}

}
