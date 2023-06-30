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


import nemo.vo.group.GroupVO;

public class AdminGroupDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public AdminGroupDAO() {
		try {
			Context ctx= new InitialContext();
			Context envContext =(Context)ctx.lookup("java:/comp/env");
			dataFactory =(DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB연결 오류");
		}
	}
	
	//소모임 목록
	public List<GroupVO> listGroups() {
		List<GroupVO> groupList = new ArrayList<>();
		try {
			conn=dataFactory.getConnection();
			String query="select u.*, A.report_cnt from group_tbl u left outer join (select reporter_id, count(*) as report_cnt from greport_tbl group by reporter_id) A on A.reporter_id = u.grp_id";
			pstmt=conn.prepareStatement(query);
			System.out.println(query);
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {				
				int grp_id=rs.getInt("grp_id");
				String grp_name=rs.getString("grp_name");
				String grp_mng=rs.getString("grp_mng");
				int mem_no=rs.getInt("mem_no");
				String grp_zipcode=rs.getString("grp_zipcode");
				String grp_addr1=rs.getString("grp_addr1");
				String grp_addr2=rs.getString("grp_addr2");
				Date create_date=rs.getDate("create_date");
				String grp_intro=rs.getString("grp_intro");
				int app_st=rs.getInt("app_st");
				String main_name=rs.getString("main_name");
				String sub_name=rs.getString("sub_name");
				String grp_mimg=rs.getString("grp_mimg");
				String grp_pimg=rs.getString("grp_pimg");
				int report_cnt=rs.getInt("report_cnt");				
				System.out.println(grp_id);
				GroupVO groVo = new GroupVO(grp_id, grp_name, grp_mng, mem_no, grp_zipcode, grp_addr1, grp_addr2, create_date, grp_intro, app_st, main_name, sub_name, grp_mimg, grp_pimg);
				groVo.setReport_cnt(report_cnt);
				
				groupList.add(groVo);
				System.out.println(groVo);
				System.out.println(report_cnt);
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("DB조회중 에러");
			e.printStackTrace();
		}
		return groupList;
	}
	
	
	//소모임 삭제
	public void delGroup(int grp_id) {
		try {
			conn=dataFactory.getConnection();
			String query = "delete from group_tbl where grp_id=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, grp_id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("소모임 삭제중 에러");
			e.printStackTrace();
		}
	}
}
