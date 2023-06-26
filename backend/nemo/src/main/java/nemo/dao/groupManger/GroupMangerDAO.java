package nemo.dao.groupManger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.group.GroupVO;
import nemo.vo.user.UserVO;

//쿼리문 전부 다시 짜고 확인할 것!!
public class GroupMangerDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	//DB연결
	public GroupMangerDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");

		} catch (Exception e) {
			System.out.println("GroupManagerDAO: DB 연결 오류");
			e.printStackTrace();
		}
	}
	
	
	//현재 들어온 그룹보기
	public List<GroupVO> groupShow(int grp_id) {
		List<GroupVO> groupList = new ArrayList<>();
		
		try {
			conn = dataFactory.getConnection();
			
			String query = "select * from group_tbl where grp_id=?";	
			System.out.println(query);
			pstmt.setInt(1, grp_id);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				
				String grp_name = rs.getString("grp_name");
				String grp_mng = rs.getString("grp_mng");
				int mem_no = rs.getInt("mem_no");
				String grp_zipcode = rs.getString("grp_zipcode");
				String grp_addr1 = rs.getString("grp_addr1");
				String grp_addr2 = rs.getString("grp_addr2");
				Date create_date = rs.getDate("create_date");
				String grp_intro = rs.getString("grp_intro");
				int app_st = rs.getInt("app_st");
				String main_name = rs.getString("main_name");
				String sub_name = rs.getString("sub_name");
				String grp_img = rs.getString("grp_img");
				
				
				GroupVO groupVO = new GroupVO(grp_id, grp_name, grp_mng, mem_no, grp_zipcode, grp_addr1, grp_addr2, create_date, grp_intro, app_st, main_name, sub_name, grp_img);		
				groupList.add(groupVO);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("DB 조회 중 에러!!!");
			e.printStackTrace();
		}
		return groupList;
	}
	
	//멤버 목록 메소드
	public List<UserVO> userShow(int grp_id) {
		List<UserVO> userList = new ArrayList<>();
		try {
			conn = dataFactory.getConnection();
			
			String query = "SELECT user_tbl.* FROM user_tbl JOIN grpjoin_tbl ON user_tbl.user_id = grpjoin_tbl.user_id where grp_id=?";
			System.out.println(query);
			pstmt.setInt(1, grp_id);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				
				String user_id = rs.getString("user_id");
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
				
				UserVO userVO = new UserVO(user_id, password, user_name, nickname, zipcode, user_addr1, user_addr2, phone, email, join_date, birthdate, user_img, admin);		
				userList.add(userVO);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("DB 조회 중 에러!!!");
			e.printStackTrace();
		}
		return userList;
	}
	
	//가입승인 대기 멤버 목록 메소드
	public List<UserVO> approveMemberShow() {
		List<UserVO> approveUserList = new ArrayList<>();
		try {
			conn = dataFactory.getConnection();
			
			String query = "SELECT user_tbl.* FROM user_tbl JOIN waitlist_tbl ON user_tbl.user_id = waitlist_tbl.user_id";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				
				String user_id = rs.getString("user_id");
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
				
				UserVO userVO = new UserVO(user_id, password, user_name, nickname, zipcode, user_addr1, user_addr2, phone, email, join_date, birthdate, user_img, admin);		
				approveUserList.add(userVO);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("DB 조회 중 에러!!!");
			e.printStackTrace();
		}
		return approveUserList;
	}
	
	//매니저 위임 메소드
	public void mandateGroupManager(UserVO userVO) {
		String user_id=userVO.getUser_id();
		try {
        	conn = dataFactory.getConnection();
        	String query = "UPDATE group_tbl"
        			+ " SET grp_mng = (SELECT user_id FROM user_tbl WHERE user_id =?)"
        			+ " WHERE grp_id = 1";		//현재 내가 들어간 페이지의 그룹을 받아야함	
        	System.out.println(query);
        	
        	pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("DB 수정 중 에러!!!");
			e.printStackTrace();
		}
		
	}
	
	
	 //회원 추방 메소드										
	 public void exileGroupMember(UserVO userVO) {
		String user_id=userVO.getUser_id();
		
		try {
        	conn = dataFactory.getConnection();
        	String query = "delete from grpjoin_tbl where user_id=? and grp_id=1";			
        	System.out.println(query);
        	
        	pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("DB 수정 중 에러!!!");
			e.printStackTrace();
		}
		
     }
	 
	
	 
	 //소모임 삭제
	 public void deleteGroup(GroupVO groupVO) {
		 
		 int grp_id = groupVO.getGrp_id();
			try {
	        	conn = dataFactory.getConnection();
	        	String query = "DELETE FROM group_tbl WHERE grp_id = ?";			
	        	System.out.println(query);
	        	
	        	pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, grp_id);
				pstmt.executeUpdate();
				
				pstmt.close();
				conn.close();
				
			} catch (Exception e) {
				System.out.println("그룹 삭제 중 에러!!!");
				e.printStackTrace();
			}
	 }
	 
	 //게시글 삭제
	 	 
	 //댓글 삭제
	 
	 //가입 승인
	 public void approveUser(UserVO userVO) {
		 String user_id=userVO.getUser_id();
			
			try {
	        	conn = dataFactory.getConnection();
	        	String query = "INSERT INTO grpjoin_tbl (user_id, grp_id) SELECT user_id, grp_id FROM waitlist_tbl WHERE user_id=?";			
	        	System.out.println(query);
	        	
	        	pstmt = conn.prepareStatement(query);
				pstmt.setString(1, user_id);
				pstmt.executeUpdate();
				
				pstmt.close();
				conn.close();
				
			} catch (Exception e) {
				System.out.println("DB 수정 중 에러!!!");
				e.printStackTrace();
			}
	 }
	 
	 //가입 승인 후 waitlist update
	 public void approveListUpdate() {	
		try {
        	conn = dataFactory.getConnection();
        	String query = "DELETE FROM waitlist_tbl WHERE user_id IN (SELECT user_id FROM grpjoin_tbl)";			
        	System.out.println(query);
        	
        	pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("DB 수정 중 에러!!!");
			e.printStackTrace();
		}
	 }
	 
	 //소모임 정보 수정 -> 따로 페이지 만들어서 할 것
}
