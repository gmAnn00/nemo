package nemo.dao.common;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.common.UserVO;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	
	private DataSource dataFactory;
	
	public UserDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("user 연결 오류");
			e.printStackTrace();
		}
	}
	
	public UserVO selectById(String _user_id) {
		UserVO userVO=new UserVO();
		try {
			conn=dataFactory.getConnection();
			
			String query="SELECT * FROM user_tbl WHERE user_id=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, _user_id);
			
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			
			String user_id=rs.getString("user_id");
			String password=rs.getString("password");
			String user_name=rs.getString("user_name");
			String nickname=rs.getString("nickname");
			String zipcode=rs.getString("zipcode");
			String user_addr1=rs.getString("user_addr1");
			String user_addr2=rs.getString("user_addr2");
			String phone=rs.getString("phone");
			String email=rs.getString("email");
			Date join_date=rs.getDate("join_date");
			Date birthdate=rs.getDate("birthdate");
			String user_img=rs.getString("user_img");
			int admin = rs.getInt("admin");
			
			userVO=new UserVO(user_id, password, user_name, nickname, zipcode, user_addr1, user_addr2, phone, email, join_date, birthdate, user_img, admin);
			
		} catch (Exception e) {
			System.out.println("user_tbl user_id로 조회 중 에러 ");
			e.printStackTrace();
		}
		
		return userVO;
	}
}
