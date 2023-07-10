package nemo.dao.group;

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

public class GroupMemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public GroupMemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");

		} catch (Exception e) {
			System.out.println("GroupMemberDAO: DB 연결 오류");
			e.printStackTrace();
		}
	}
	
	// 소모임에서 멤버 불러오는 메소드
		public List<UserVO> selectMemberById(int group_id) {
			List<UserVO> usersList = new ArrayList<UserVO>();
			
			try {
				conn = dataFactory.getConnection();
				String query = "select u.* from user_tbl u, grpjoin_tbl g where g.grp_id = ? and g.user_id = u.user_id";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, group_id);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					UserVO userVO = new UserVO();
					
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
					
					usersList.add(userVO);
					
				}
				rs.close();
				pstmt.close();
				conn.close();
				
				
			} catch (Exception e) {
				System.out.println("GroupMemberDAO: selectMemberById 오류");
				e.printStackTrace();
			}
			
			return usersList;
		} // end of selectMemberById

		public void deleteMember(String user_id, int group_id) {
			try {
				conn = dataFactory.getConnection();
				String query = "delete grpjoin_tbl where user_id = ? AND grp_id = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, user_id);
				pstmt.setInt(2, group_id);
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
				
				
			} catch (Exception e) {
				System.out.println("GroupMainDAO: deleteMember 오류");
				e.printStackTrace();
			}
			
		}

		public void cancelMember(String user_id, int group_id) {
			try {
				conn = dataFactory.getConnection();
				String query = "delete waitlist_tbl where user_id = ? AND grp_id = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, user_id);
				pstmt.setInt(2, group_id);
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
				
				
			} catch (Exception e) {
				System.out.println("GroupMainDAO: cancelMember 오류");
				e.printStackTrace();
			}
			
		}
}
