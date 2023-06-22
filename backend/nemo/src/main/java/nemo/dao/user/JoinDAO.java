package nemo.dao.member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.member.UserVO;

public class JoinDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public JoinDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/xomp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB 연결오류");
		}
	}
	//회원가입 메서드
	public void addMember(UserVO uservo) {
		
		try {
			conn=dataFactory.getConnection(); //커넥션 풀을 가지고 데이터베이스연결
			String User_id=uservo.getUser_id();
			String password=uservo.getPassword();
			String User_name=uservo.getUser_name();
			String User_nickname=uservo.getNickname();
			String User_addr1=uservo.getUser_addr1();
			String User_addr2=uservo.getUser_addr2();
			Date birthdate=uservo.getBirthdate();
			String phone=uservo.getPhone();
			String email=uservo.getEmail();
			
			String query = "insert into Usertbl (User_id, password, User_name, User_nickname, User_addr1, User_addr2, birthdate, phone, email) values(?,?,?,?,?,?,?,?,?)";
			
			pstmt=conn.prepareStatement(query); //values에 있는 물음표 순서대로 값이 
			pstmt.setString(1,User_id);
			pstmt.setString(2, password);
			pstmt.setString(3, User_name);
			pstmt.setString(4, User_nickname);
			pstmt.setString(5, User_addr1);
			pstmt.setString(6, User_addr2);
			pstmt.setDate(7, birthdate);
			pstmt.setString(8, phone);
			pstmt.setString(9, email);
			
			pstmt.executeUpdate(); //등록 실행 데이터를 저장한다.
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			System.out.println("회원등록 중 에러!!"+ e.getMessage());
			e.printStackTrace(); //에러 메세지 보기 기능
		}
	
	}
	
}
