package nemo.dao.group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.group.GroupVO;

public class GroupDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	public GroupDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");

		} catch (Exception e) {
			System.out.println("GroupInfoDAO:DB 연결 오류");
			e.printStackTrace();
		}
	}

	public GroupVO selectGroupById(int group_id) {
		GroupVO groupVO = new GroupVO();

		try {
			conn = dataFactory.getConnection();
			String query = "select * from group_tbl where grp_id=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			ResultSet rs = pstmt.executeQuery();

			rs.next();

			groupVO.setGrp_id(rs.getInt("grp_id"));
			groupVO.setGrp_name(rs.getString("grp_name"));
			groupVO.setGrp_mng(rs.getString("grp_mng"));
			groupVO.setMem_no(rs.getInt("mem_no"));
			groupVO.setGrp_zipcode(rs.getString("grp_zipcode"));
			groupVO.setGrp_addr1(rs.getString("grp_addr1"));
			groupVO.setGrp_addr2(rs.getString("grp_addr2"));
			groupVO.setCreate_date(rs.getDate("create_date"));
			groupVO.setGrp_intro(rs.getString("grp_intro"));
			groupVO.setApp_st(rs.getInt("app_st"));
			groupVO.setMain_name(rs.getString("main_name"));
			groupVO.setSub_name(rs.getString("sub_name"));
			// groupVO.setGrp_mimg(rs.getString("grp_mimg"));
			// groupVO.setGrp_pimg(rs.getString("grp_pimg"));
			groupVO.setGrp_img(rs.getString("grp_img"));

			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("selectGroupById:소모임 구하는 중 오류");
			e.printStackTrace();
		}

		return groupVO;
	} // end of selectGroupById

	// 소모임 현재 인원 찾기
	public int selectGroupNumById(int group_id) {
		int groupNum = 0;

		try {
			conn = dataFactory.getConnection();
			String query = "select count(*) as cnt from grpjoin_tbl where grp_id=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			ResultSet rs = pstmt.executeQuery();

			rs.next();

			groupNum = rs.getInt("cnt");

			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("selectGroupNumById:소모임 현재 인원수 구하는 중 오류");
			e.printStackTrace();
		}

		return groupNum;
	} // end of selectGroupNumById
	
	// user_id 가 group_id 의 멤버면 true, 멤버가 아니면 false 반환
		public boolean isMember(String user_id, int group_id) {
			int rsCnt = 0;
			try {
				conn = dataFactory.getConnection();
				String query = "select count(*) as cnt from grpjoin_tbl where user_id = ? and grp_id=?";
				System.out.println(query);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, user_id);
				pstmt.setInt(2, group_id);
				ResultSet rs = pstmt.executeQuery();

				rs.next();
				rsCnt = rs.getInt("cnt");
				rs.close();
				pstmt.close();
				conn.close();

			} catch (Exception e) {
				System.out.println("isMember 중 오류");
				e.printStackTrace();
			}

			if (rsCnt == 0) {
				return false;
			} else {
				return true;
			}
		} // end of isMember

		
		public List grpMng(String user_id) {
			List grpMngList = new ArrayList<String>();
			
			try {
				conn = dataFactory.getConnection();
				String query = "select grp_id from group_tbl where grp_mng = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, user_id);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getString("grp_id"));
					grpMngList.add(rs.getString("grp_id"));
				}
				rs.close();
				pstmt.close();
				conn.close();
				
			} catch (Exception e) {
				System.out.println("grpMng 중 오류");
				e.printStackTrace();
			}
			
			return grpMngList;
		}
		
		public List grpMember(int group_id) {
			List grpJustMemberList = new ArrayList<String>();
			try {
				conn = dataFactory.getConnection();
				String query = "select user_id from grpjoin_tbl where grp_id = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, group_id);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getString("user_id"));
					grpJustMemberList.add(rs.getString("user_id"));
				}
				rs.close();
				pstmt.close();
				conn.close();
				
			} catch (Exception e) {
				System.out.println("grpJustMember 중 오류");
				e.printStackTrace();
			}
			
			return grpJustMemberList;
			
		}
		
		public String grpMng(int group_id) {
			String mng_id = "";
			try {
				conn = dataFactory.getConnection();
				String query = "select grp_mng from group_tbl where grp_id = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, group_id);
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				mng_id = rs.getString("grp_mng");
				
				rs.close();
				pstmt.close();
				conn.close();
				
			} catch (Exception e) {
				System.out.println("grpJustMember 중 오류");
				e.printStackTrace();
			}
			
			return mng_id;
		}
}
