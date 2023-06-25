package nemo.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.user.UserVO;

public class LoginDAO {
	
	private Connection conn;	
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	public LoginDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
		}catch (Exception e) {
			System.out.println("커넥션풀 연결실패!!");
		}
	}
	
	//회원확인 메서드
	public Boolean isExisted(UserVO userVO) {
		boolean result=false;
		String id=userVO.getUser_id();
		String pwd=userVO.getPassword();
		
		try {
			conn=dataFactory.getConnection();
			String query="select decode(count(*), 1,'true', 'false') from user_tbl where user_id=? and password=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,id);
			pstmt.setString(2, pwd);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			result = Boolean.parseBoolean(rs.getString(1));
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch (Exception e) {
			System.out.println("회원 확인 여부 처리중 에러!!");
			e.printStackTrace();
		}
		return result;
	}


	// 회원정보 가져오는 메소드
	public UserVO selectUserById(String user_id) {
		UserVO userVO = new UserVO();
		 try {
			 conn=dataFactory.getConnection();
			 String query = "select * from user_tbl where user_id=?";
			 pstmt=conn.prepareStatement(query);
			 pstmt.setString(1, user_id);
			 ResultSet rs=pstmt.executeQuery();
			 
			 rs.next();
			 String _user_id = rs.getString("user_id");
			 String _nickname = rs.getString("nickname");
			 String _user_img = rs.getString("user_img");
			 int _admin = rs.getInt("admin");
			 
			 userVO.setUser_id(_user_id);
			 userVO.setNickname(_nickname);
			 userVO.setUser_img(_user_img);
			 userVO.setAdmin(_admin);
			 
			 rs.close();
			 pstmt.close();
			 
			 
		 }catch(Exception e){
			 System.out.println("회원 가져오는 중 에러!!");
				e.printStackTrace();
		 }
		
		return userVO;
	} // end of selectUserById
	
	

}