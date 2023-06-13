package nemo.dao.group;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.group.GroupVO;

public class GroupInfoDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	public GroupInfoDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");

		} catch (Exception e) {
			System.out.println("GroupInfoDAO:DB 연결 오류");
			e.printStackTrace();
		}
	} // end of GroupInfoDAO

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
			groupVO.setGrp_zipcode("grp_zipcode");
			groupVO.setGrp_addr1(rs.getString("grp_addr1"));
			groupVO.setGrp_addr2(rs.getString("grp_addr2"));
			groupVO.setCreate_date(rs.getDate("create_date"));
			groupVO.setGrp_intro(rs.getString("grp_intro"));
			groupVO.setApp_st(rs.getInt("app_st"));
			groupVO.setMain_name(rs.getString("main_name"));
			groupVO.setSub_name(rs.getString("sub_name"));
			//groupVO.setGrp_mimg(rs.getString("grp_mimg"));
			//groupVO.setGrp_pimg(rs.getString("grp_pimg"));
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
	
	// 소모임장 찾기
	public String selectManagerById(int group_id) {
		String manager = "";
		
		try {
			conn = dataFactory.getConnection();
			String query = "select nickname from user_tbl where user_id=(select grp_mng from group_tbl where grp_id=?)";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			manager = rs.getString("nickname");
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("selectManagerById:소모임장 구하는 중 오류");
			e.printStackTrace();
		}
		
		return manager;
	}

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

	// 소모임 찜 갯수 찾기
	public int selectBookmarkNumById(int group_id) {
		int bookmarkNum = 0;
		try {
			
			conn = dataFactory.getConnection();
			String query = "select count(*) as cnt from bookmark_tbl where grp_id=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			bookmarkNum = rs.getInt("cnt");
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("selectBookmarkNumById:찜 갯수 구하는 중 오류");
			e.printStackTrace();
		}
		return bookmarkNum;

	} // end of selectBookmarkNumById

	
	public boolean isBookmark(String user_id, int group_id) {
		boolean result = false;
		try {
			conn = dataFactory.getConnection();
			String query = "select decode(count(*) , 1, 'true', 'false') as result "
			+ "from bookmark_tbl where user_id=? and grp_id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, group_id);
			ResultSet rs= pstmt.executeQuery();
			rs.next();
			result = Boolean.parseBoolean(rs.getString("result"));
			System.out.println("result = "+ result);
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("isInterest: 중 오류");
			e.printStackTrace();
		}
		
		return result;
	}

	public Date selectRecentDate(int group_id) {
		Date recentDate = null;
		
		try {
			conn = dataFactory.getConnection();
			String query = "select create_date from "
					+ "(select * from board_tbl order by create_date desc)"
					+ "where grp_id=? and rownum=1";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			recentDate = rs.getDate("create_date");
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("selectRecentDate: 중 오류");
			e.printStackTrace();
		}
		
		return recentDate;
	}

	// 그룹 승인 여부 구함
	public int selectAppSt(int group_id) {
		int app_st = 0;
		
		try {
			conn = dataFactory.getConnection();
			String query = "select app_st from group_tbl where grp_id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			app_st = rs.getInt("app_st");
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("selectAppSt: 중 오류");
			e.printStackTrace();
		}
		
		return app_st;
		
	}

	
} // end of class GroupInfoDAO
