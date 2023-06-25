package nemo.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.user.UserVO;

public class SearchDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public SearchDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB연결 오류");
		}
	}
	//아이디 확인 메서드
	public String findidCheck(UserVO userVO) {
		String user_id=null;
		String name = userVO.getUser_name();
		String email = userVO.getEmail();
		
		try {
			conn=dataFactory.getConnection();
			
			String query= "SELECT user_id FROM user_tbl WHERE user_name=? AND email=?";
			
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			ResultSet rs=pstmt.executeQuery();//쿼리문을 실행한 결과 저장공간
			if(rs.next()) {
				user_id = rs.getString("user_id");	
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		
		} catch (Exception e) {
			System.out.println("회원 확인 여부 처리중 에러!!");
			e.printStackTrace();
		}
		return user_id;
	}
	
	//비밀번호 확인 메서드
	public Boolean findpassCheck(UserVO userVO) {
		boolean password = false;
		String user_id = userVO.getUser_id();
		String user_name =userVO.getUser_name();
		String email = userVO.getEmail();
		
		try {
			conn = dataFactory.getConnection();
			
			String query = "SELECT DECODE(count(*), 1,'true', 'false') FROM user_tbl WHERE user_id=? AND user_name=? AND email=? ";
			
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_name);
			pstmt.setString(3, email);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			
			password = Boolean.parseBoolean(rs.getString(1));
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("회원 확인 여부 처리중 에러!!");
			e.printStackTrace();
		}
		
		return password;
	}
		
	//비밀번호 수정 메서드
	public void findpassinfo(UserVO userVO) {
		String user_id = userVO.getUser_id();
		String password  = userVO.getPassword();
		System.out.println(user_id);
		System.out.println(password);
				try {
					conn=dataFactory.getConnection();
					String query = "update user_tbl set password=? WHERE user_id=?";
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, password);
					pstmt.setString(2, user_id);
					pstmt.executeUpdate();
					pstmt.close();
					conn.close();
					
				} catch (Exception e) {
					System.out.println("비밀번호 수정 여부 에러!");
					e.printStackTrace();
				}
	}
}
