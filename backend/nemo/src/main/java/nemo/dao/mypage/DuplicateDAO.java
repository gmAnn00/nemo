package nemo.dao.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DuplicateDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public DuplicateDAO() {
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

	//ID 중복 체크 메서드
	public boolean overlappedID(String user_id) {
		boolean result = false;
		try {
			conn=dataFactory.getConnection();
			String query = "select decode(count(*), 1, 'true', 'false') as result from user_tbl where user_id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			//처음으로 보냄
			rs.next();
			result = Boolean.parseBoolean(rs.getString("result"));
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("ID 중복체크 처리 중 에러 발생!!");
		}
		return result;
	}

	//닉네임 중복 체크 메서드
		public boolean overlappedNickname(String nickname) {
			boolean result = false;
			try {
				conn=dataFactory.getConnection();
				
				String query = "select decode(count(*), 1, 'true', 'false') as result from user_tbl where nickname=?";
				System.out.println(query);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, nickname);
				ResultSet rs = pstmt.executeQuery();
				//처음으로 보냄
				rs.next();
				result = Boolean.parseBoolean(rs.getString("result"));
				rs.close();
				pstmt.close();
				conn.close();
				
			} catch (Exception e) {
				System.out.println("닉네임 중복체크 처리 중 에러 발생!!");
			}
			return result;
		}
		
		//이메일 중복 체크 메서드
		public boolean overlappedEmail(String email) {
			boolean result = false;
			try {
				conn=dataFactory.getConnection();
				
				String query = "select decode(count(*), 1, 'true', 'false') as result from user_tbl where email=?";
				System.out.println(query);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, email);
				ResultSet rs = pstmt.executeQuery();
				//처음으로 보냄
				rs.next();
				result = Boolean.parseBoolean(rs.getString("result"));
				rs.close();
				pstmt.close();
				conn.close();
				
			} catch (Exception e) {
				System.out.println("이메일 중복체크 처리 중 에러 발생!!");
			}
			return result;
		}
		



}
