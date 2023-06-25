package nemo.dao.mypage;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.user.UserVO;

public class MyProfileDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public MyProfileDAO() {
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
	
	// 마이페이지 프로필 조회
	public UserVO searchMyProfileById(String user_id) {
		UserVO userVO = new UserVO();
		
		try {
			conn = dataFactory.getConnection();		
			
			String query = "SELECT * FROM USER_TBL WHERE USER_ID = ?";
			System.out.println(query);			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			//("")안의 값은 컬럼명과 같은지 꼭 확인			
			user_id = rs.getString("user_id");
			String password = rs.getString("password");
			String user_name = rs.getString("user_name");
			String nickname = rs.getString("nickname");
			String zipcode = rs.getString("zipcode");
			String user_addr1 = rs.getString("user_addr1");
			String user_addr2 = rs.getString("user_addr2");
			String phone = rs.getString("phone");
			String email = rs.getString("email");
			Date join_date = rs.getDate("join_date");
			Date birthdate = rs.getDate("birthdate");
			String user_img = rs.getString("user_img");
			int admin = rs.getInt("admin");
	//		MyProfileVO myProVO = new MyProfileVO(user_id, password, user_name, nickname, zipcode, user_addr1, user_addr2, phone, email, joinDate, birthdate, user_img, admin);
			userVO.setUser_id(user_id);
			userVO.setPassword(password);
			userVO.setUser_name(user_name);
			userVO.setNickname(nickname);
			userVO.setZipcode(zipcode);
			userVO.setUser_addr1(user_addr1);
			userVO.setUser_addr2(user_addr2);
			userVO.setPhone(phone);
			userVO.setEmail(email);
			userVO.setJoin_date(join_date);
			userVO.setBirthdate(birthdate);
			userVO.setUser_img(user_img);
			userVO.setAdmin(admin);	
							
			//System.out.println("DAO내부" + myProVO );
			rs.close();
			pstmt.close();
			conn.close();					
			
		} catch (Exception e) {
			System.out.println("DB 조회 중 에러!!");
			e.printStackTrace();
		}
		
		//System.out.println("DAO" + myProVO );
		return userVO;
		
	}	//searchById 메서드

	//마이페이지 프로필 이미지 찾기
	public String searchProImgById(String user_id) {
		
		String myProImg = "";
		try {
			conn = dataFactory.getConnection();		
			
			String query = "SELECT USER_IMG FROM USER_TBL WHERE USER_ID = ?";
			System.out.println(query);		
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			myProImg = rs.getString("SUB_NAME");
			rs.close();
			pstmt.close();
			conn.close();		
			
		} catch (Exception e) {
			System.out.println("프로필 이미지 조회 중 에러");
			e.printStackTrace();
		}
		return myProImg;
	}
	//마이페이지 관심사 조회
	public List<String> searchProInterestById(String user_id) {
		List<String> interestList = new ArrayList<>();
		
		try {
			conn = dataFactory.getConnection();
			
			String query = "SELECT SUB_NAME FROM INTERESTS_TBL WHERE USER_ID = ?";
			//System.out.println(query);		
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			
			//한번에 가져와서
			//몇개인지는 모르고, 최대 3개까지니까 while을 돌림
			while(rs.next()) {
				//컬럼 이름 확인해야 할 듯 (컬럼 없는듯?)
				String interest = rs.getString("SUB_NAME");
				interestList.add(interest);
			}			
			rs.close();
			pstmt.close();
			conn.close();		
			
		} catch (Exception e) {
			System.out.println("프로필 이미지 조회 중 에러");
			e.printStackTrace();
		}
		return interestList;
	}

	//회원 정보 수정
	public void modProfile(UserVO userVO) {
		String user_id = userVO.getUser_id();
		String password = userVO.getPassword();
		String user_name = userVO.getUser_name();
		String nickname = userVO.getNickname();
		String zipcode = userVO.getZipcode();
		String user_addr1 = userVO.getUser_addr1();
		String user_addr2 = userVO.getUser_addr2();
		String phone = userVO.getPhone();
		String email = userVO.getEmail();
		Date birthdate = userVO.getBirthdate();		
		String user_img = userVO.getUser_img();
		
		//값 잘 들어왔는지 확인
		try {
	        Object obj = userVO;
	         // 반복문을 이용하여 해당 클래스에 정의된 필드를 가져옵니다.
	            for (Field field : obj.getClass().getDeclaredFields()) {
	                field.setAccessible(true);
					Object value = field.get(obj); // 필드에 해당하는 값을 가져옵니다.
					System.out.println("field : "+field.getName()+" | value : " + value);
	            }
		    }catch (Exception e) {
	        e.printStackTrace();
			}
		 // Console 확인 끝
		
		try {
			conn = dataFactory.getConnection();		
			
			String query = "UPDATE USER_TBL SET PASSWORD=?, USER_NAME=?, NICKNAME=?, ZIPCODE=?, USER_ADDR1=?, USER_ADDR2=?, PHONE=?, EMAIL=?, BIRTHDATE=?, USER_IMG=? WHERE USER_ID=?";
			System.out.println(query);		
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, password);
			pstmt.setString(2, user_name);
			pstmt.setString(3, nickname);
			pstmt.setString(4, zipcode);
			pstmt.setString(5, user_addr1);
			pstmt.setString(6, user_addr2);		
			pstmt.setString(7, phone);
			pstmt.setString(8, email);
			pstmt.setDate(9, birthdate);
			pstmt.setString(10, user_img);
			pstmt.setString(11, user_id);			
			pstmt.executeQuery();
			
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("회원정보 수정 중 에러!!");
			e.printStackTrace();
		}
		
	}
	//이미지 수정
	public void modUserimg(UserVO userVO) {
		String user_id = userVO.getUser_id();
		String user_img = userVO.getUser_img();
		
		try {
			conn = dataFactory.getConnection();		
			
			String query = "UPDATE USER_TBL SET USER_IMG=? WHERE USER_ID=?";
			System.out.println(query);		
			
			pstmt = conn.prepareStatement(query);			
			pstmt.setString(1, user_img);
			pstmt.setString(2, user_id);			
			pstmt.executeQuery();
			
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("프로필 이미지 수정중 에러!!");
			e.printStackTrace();
		}
		
	}	
	
	//회원 탈퇴(삭제) 메서드
	 	public void delMember(String user_id, String delpassword) {
	 		//DB는 오류가 날 수 있으므로 항상 try-catch 사용
	 		try {
	 			conn = dataFactory.getConnection();
				String query = "DELETE USER_TBL WHERE USER_ID=? AND PASSWORD=?";
				System.out.println(query);
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, user_id);
				pstmt.setString(2, delpassword);
				pstmt.executeUpdate();
							
				pstmt.close();
				conn.close();
				
			} catch (Exception e) {
				System.out.println("회원 삭제 중 에러!!");
				e.printStackTrace();
			}
	 	}

	
}
