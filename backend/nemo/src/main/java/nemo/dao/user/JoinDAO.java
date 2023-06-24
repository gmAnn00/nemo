package nemo.dao.user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.user.InterestsVO;
import nemo.vo.user.UserVO;

public class JoinDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public JoinDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB연결 오류");
		}
	}
	//회원가입 메서드
	public void adduser(UserVO userVO) {
		
		try {
			conn=dataFactory.getConnection(); //커넥션 풀을 가지고 데이터베이스연결
			String user_id=userVO.getUser_id();
			String password=userVO.getPassword();
			String user_name=userVO.getUser_name();
			String nickname=userVO.getNickname();
			String zipcode=userVO.getZipcode();
			String user_addr1=userVO.getUser_addr1();
			String user_addr2=userVO.getUser_addr2();
			Date birthdate=userVO.getBirthdate();
			int phone=userVO.getPhone();
			String email=userVO.getEmail();
			
			String query = "insert into user_tbl (user_id, password, user_name, nickname, zipcode, user_addr1, user_addr2, birthdate, phone, email) values(?,?,?,?,?,?,?,?,?,?)";
			
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,user_id);
			pstmt.setString(2, password);
			pstmt.setString(3, user_name);
			pstmt.setString(4, nickname);
			pstmt.setString(5, zipcode);
			pstmt.setString(6, user_addr1);
			pstmt.setString(7, user_addr2);
			pstmt.setDate(8, birthdate);
			pstmt.setInt(9, phone);
			pstmt.setString(10, email);
			pstmt.executeUpdate(); //등록 실행 데이터를 저장한다.
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			System.out.println("회원등록 중 에러!!"+ e.getMessage());
			e.printStackTrace();
		}
	
	}
	//관심사 체크 메서드
	public void addChoice(InterestsVO interestsVO) {
		try {
			conn=dataFactory.getConnection();
			//String user_id = interestsVO.getUser_id();
			String main_name = interestsVO.getMain_name();
			String sub_name = interestsVO.getSub_name();
			
			String insertquery = "insert into interests_tbl (main_name, sub_name) values(?,?,?)";
			String selectquery = "select user_id from user_tbl";
			
			System.out.println(insertquery);
			pstmt=conn.prepareStatement(insertquery);
			pstmt.setString(1, main_name);
			pstmt.setString(2, sub_name);
			pstmt.executeUpdate();
			pstmt.close();
			pstmt=conn.prepareStatement(selectquery);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String user_id = rs.getString("user_id");
			}
			rs.close();
			pstmt.close();
			
			conn.close();
		} catch (Exception e) {
			System.out.println("관심사 등록 중 에러!!"+ e.getMessage());
			e.printStackTrace();
		
	}
	
}
}
