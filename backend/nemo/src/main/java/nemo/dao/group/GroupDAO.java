package nemo.dao.group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}