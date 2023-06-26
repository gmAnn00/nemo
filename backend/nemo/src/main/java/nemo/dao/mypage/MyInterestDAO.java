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

import nemo.vo.user.InterestsVO;
import nemo.vo.user.UserVO;

public class MyInterestDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	List<InterestsVO> interestsList = new ArrayList<>();
	
	public MyInterestDAO() {
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
	
	//마이페이지 관심사 조회
	public List<InterestsVO> searchInterestById(String user_id) {
		try {
			conn = dataFactory.getConnection();
			
			String query = "SELECT MAIN_NAME, SUB_NAME FROM INTERESTS_TBL WHERE USER_ID = ?";
			//System.out.println(query);		
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			interestsList.clear();
			
			//몇개인지는 모르고, 최대 3개까지니까 while을 돌림
			while(rs.next()) {				
				String main_name = rs.getString("MAIN_NAME");
				String sub_name = rs.getString("SUB_NAME");
				InterestsVO interestVO = new InterestsVO();
				interestVO.setMain_name(main_name);
				interestVO.setSub_name(sub_name);
				interestsList.add(interestVO);
			}			
			rs.close();
			pstmt.close();
			conn.close();		
			
		} catch (Exception e) {
			System.out.println("관심사 조회 중 에러");
			e.printStackTrace();
		}
		return interestsList;
	}
	//관심사 삭제 메서드
	public void delInterests(String user_id) {
		try {
			// 다 삭제 한 후에...  다시 INSERT 해주기
			//String query = "delete from INTERESTS_TBL WHERE user_id = ?";
			conn=dataFactory.getConnection();
			
			String query = "DELETE interests_tbl WHERE user_id=?";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("관심사 수정을 위한 삭제 중 에러");
			e.printStackTrace();
		}		
	}
	
	//관심사 수정 메서드	
	public void modInterests(List<InterestsVO> interestsList) {
		try {					
			for(InterestsVO interestsVO : interestsList) {
				conn=dataFactory.getConnection();
				
				String user_id = interestsVO.getUser_id();
				String main_name = interestsVO.getMain_name();
				String sub_name = interestsVO.getSub_name();
				
				System.out.println("DAO main_name="+main_name);
				System.out.println("DAO sub_name="+sub_name);
				
				String query = "INSERT INTO interests_tbl(user_id, main_name, sub_name) values(?, ?, ?)";
				System.out.println(query);
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, user_id);
				pstmt.setString(2, main_name);
				pstmt.setString(3, sub_name);
				
				pstmt.executeUpdate();
				
				pstmt.close();
				conn.close();
			}
		} catch (Exception e) {
			System.out.println("관심사 수정 중 에러");
			e.printStackTrace();
		}
		
	}

	

	
}
