package nemo.dao.group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.group.GroupVO;

public class CreateGroupDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public CreateGroupDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");

		} catch (Exception e) {
			System.out.println("CreateGroupDAO: DB 연결 오류");
			e.printStackTrace();
		}
	} // end of Constructor

	public int createGroup(GroupVO groupVO) {
		int grp_id = 0;
		try {

			conn = dataFactory.getConnection();
			//String query = "insert into group_tbl(grp_id, grp_name, grp_mng, mem_no, grp_zipcode, grp_addr1, grp_addr2, create_date, grp_intro, app_st, main_name, sub_name, grp_mimg, grp_pimg)"
			//		+" values (seq_group_id.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//			String query = "insert into group_tbl"
//					+" (grp_id, grp_name, grp_mng, mem_no, grp_zipcode, grp_addr1, grp_addr2, grp_intro, app_st, main_name, sub_name, grp_img, create_date)"
//					+ " values(seq_group_id.nextval,?,?,?,?,?,?,?,?,?,?,?, sysdate)";
			String query = "insert into group_tbl"
					+ " values(seq_group_id.nextval,?,?,?,?,?,?,sysdate,?,?,?,?,?)";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);

			String grp_name = groupVO.getGrp_name();
			String grp_mng = groupVO.getGrp_mng();
			int mem_no = groupVO.getMem_no();
			String grp_zipcode = groupVO.getGrp_zipcode();
			String grp_addr1 = groupVO.getGrp_addr1();
			String grp_addr2 = groupVO.getGrp_addr2();
			String grp_intro = groupVO.getGrp_intro();
			int app_st = groupVO.getApp_st();
			String main_name = groupVO.getMain_name();
			String sub_name = groupVO.getSub_name();
			String grp_img = groupVO.getGrp_img();
			
			pstmt.setString(1, grp_name);
			pstmt.setString(2, grp_mng);
			pstmt.setInt(3, mem_no);
			pstmt.setString(4, grp_zipcode);
			pstmt.setString(5, grp_addr1);
			pstmt.setString(6, grp_addr2);
			pstmt.setString(7, grp_intro);
			pstmt.setInt(8, app_st);
			pstmt.setString(9, main_name);
			pstmt.setString(10, sub_name);
			pstmt.setString(11, grp_img);
			
			int ret = pstmt.executeUpdate();

			pstmt.close();
			conn.close();

			
		} catch (Exception e) {
			System.out.println("createGroup: 오류");
			e.printStackTrace();
		}
		
		
		try {
			conn = dataFactory.getConnection();
			String query = "select seq_group_id.currval as grp_id from group_tbl";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			grp_id = rs.getInt("grp_id");
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("createGroup: 오류");
			e.printStackTrace();
		}
		
		return grp_id;
	} // class of

}
