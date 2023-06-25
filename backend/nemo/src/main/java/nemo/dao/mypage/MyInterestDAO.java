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

import nemo.vo.mypage.InterestVO;
import nemo.vo.mypage.UserVO;

public class MyInterestDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	List<InterestVO> interestList = new ArrayList<>();
	
	public MyInterestDAO() {
		try {
			//커넥션 풀은 JNDI(Java Naming Directory Interface)를 이용
			// JNDI : 필요한 자원을 키와 값의 쌍으로 저장 한 후, 키를 이용하여 값을 얻는 방식
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			//DataSource : Servers - context.xml에 있는 resource 부분을 읽어옴
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");			
		} catch (Exception e) {
			//연결오류 메시지가 난다면 이 메시지가 뜨고 Servers프로젝트 - context.xml확인
			System.out.println("DB연결오류");
		}		
	}
	
	//마이페이지 관심사 조회
	public List<InterestVO> searchInterestById(String user_id) {
		try {
			conn = dataFactory.getConnection();
			
			String query = "select SUB_NAME from INTERESTS_TBL where user_id = ?";
			//System.out.println(query);		
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			interestList.clear();
			
			//몇개인지는 모르고, 최대 3개까지니까 while을 돌림
			while(rs.next()) {				
				String interest = rs.getString("SUB_NAME");
				InterestVO interestVO = new InterestVO();
				interestVO.setSub_name(interest);
				interestList.add(interestVO);
			}			
			rs.close();
			pstmt.close();
			conn.close();		
			
		} catch (Exception e) {
			System.out.println("관심사 조회 중 에러");
			e.printStackTrace();
		}
		return interestList;
	}
	
	//관심사 수정 메서드
	public void modInterest(String user_id, List interestList) {
		try {
			conn = dataFactory.getConnection();
			//sub_name, main_name 이 다 필요한데
			//InterestVO를 만들어야 할듯 그걸 list 에 담아
			//흠; 다 삭제 한 후에...
			//String query = "delete from INTERESTS_TBL WHERE user_id = ?";
			//반복문 돌면서 update 해주고..? 아니 다 삭제하면 create 가 되는거 아님? ㄷ;;
			//일부만 수정하면... 수정한 부분만 알아서 그 걸 update해야하나..?			
			String query = "update INTERESTS_TBL set SUB_NAME = ? , MAIN_NAME = ? WHERE user_id = ?";
			//System.out.println(query);		
			
			pstmt = conn.prepareStatement(query);
			//pstmt.setString(1, sub_name);
			//pstmt.setString(2, main_name);
			pstmt.setString(3, user_id);
			ResultSet rs = pstmt.executeQuery();
			interestList.clear();
			
			//한번에 가져와서
			//몇개인지는 모르고, 최대 3개까지니까 while을 돌림
			while(rs.next()) {
				//컬럼 이름 확인해야 할 듯 (컬럼 없는듯?)
				String interest = rs.getString("SUB_NAME");
				InterestVO interestVO = new InterestVO();
				interestList.add(interestVO);
			}			
			rs.close();
			pstmt.close();
			conn.close();		
			
		} catch (Exception e) {
			System.out.println("관심사 수정 중 에러");
			e.printStackTrace();
		}
		
	}

	
}
