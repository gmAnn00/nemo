package nemo.dao.groupSearch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.group.GroupVO;

public class GroupSearchDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	GroupVO gruopVO;
	
	public GroupSearchDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");

		} catch (Exception e) {
			System.out.println("SearchDAO: DB 연결 오류");
			e.printStackTrace();
		}
	}

	public List<Map> search(Map searchMap) {
		List<Map> resultList = new ArrayList<Map>();
		Map resultMap;

		try {
			conn = dataFactory.getConnection();
			
			String user_id = (String)searchMap.get("user_id");
			String searchText = (String)searchMap.get("searchText");
			String main_name = (String)searchMap.get("main_name");
			String sub_name = (String)searchMap.get("sub_name");
			String joinAble = (String)searchMap.get("joinAble");
			String sort = (String)searchMap.get("sort");
			
			System.out.println("DAO searchText=" + searchText);
			System.out.println("DAO main_name=" + main_name);
			System.out.println("DAO sub_name=" + sub_name);
			System.out.println("DAO joinAble=" + joinAble);
			System.out.println("DAO sort=" + sort);

			String query = "SELECT * FROM group_tbl WHERE grp_name LIKE ?";
			pstmt = conn.prepareStatement(query);
			String searchTexts = '%' + searchText + '%';
			pstmt.setString(1, searchTexts);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				resultMap = new HashMap();
				GroupVO groupVO = new GroupVO();
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
				groupVO.setGrp_img(rs.getString("grp_img"));
				
				
				int groupMemberNum = groupMemberNum(groupVO.getGrp_id());
				int bookmarkNum = bookmarkNum(groupVO.getGrp_id());
				boolean isBookmark = isBookmark(user_id, groupVO.getGrp_id());
				boolean isFull = isFull(groupVO.getGrp_id());
				
				resultMap.put("isBookmark", isBookmark);
				resultMap.put("groupVO", groupVO);
				resultMap.put("groupMemberNum", groupMemberNum);
				resultMap.put("bookmarkNum", bookmarkNum);
				resultMap.put("isFull", isFull);
				resultList.add(resultMap);
				//System.out.println(groupVO.toString());
				//System.out.println("groupMemberNum=" + groupMemberNum);
				//System.out.println("bookmarkNum="+bookmarkNum);

			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
			
		} catch (Exception e) {
			System.out.println("SearchDAO: search 오류");
			e.printStackTrace();
		}
		
		return resultList;
		
	}
	
	public int groupMemberNum(int group_id) {
		int result = 0;
		try {
			conn = dataFactory.getConnection();
			
			String query = "select count(j.grp_id) cnt from group_tbl g, grpjoin_tbl j where g.grp_id = j.grp_id and g.grp_id=? group by j.grp_id";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,group_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("SearchDAO: groupMemberNum 오류");
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public int bookmarkNum(int group_id) {
		int result = 0;
		try {
			conn = dataFactory.getConnection();
			
			String query = "select count(b.grp_id) cnt from group_tbl g, bookmark_tbl b where g.grp_id = b.grp_id and g.grp_id=? group by b.grp_id";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,group_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("SearchDAO: bookmarkNum 오류");
			e.printStackTrace();
		}
		
		return result;
		
	}
	
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
	
	// 소모임이 꽉 차있으면 true, 아니면 false 반환
		public boolean isFull(int group_id) {
			int groupNum = -1;
			int maxNum = -2;
			String query;

			try {
				conn = dataFactory.getConnection();
				query = "select count(*) as cnt from grpjoin_tbl where grp_id=?";
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
				System.out.println("isFull 중 오류");
				e.printStackTrace();
			}

			try {
				conn = dataFactory.getConnection();
				query = "select mem_no from group_tbl where grp_id=?";
				System.out.println(query);
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, group_id);
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				maxNum = rs.getInt("mem_no");

				rs.close();
				pstmt.close();
				conn.close();

			} catch (Exception e) {
				System.out.println("isFull 중 오류");
				e.printStackTrace();
			}

			if (groupNum == maxNum) {
				return true;
			} else {
				return false;
			}

		} // end of isFull

}
