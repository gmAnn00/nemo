package nemo.dao.group;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
		try {
			conn = dataFactory.getConnection();
			//String query = "insert into group_tbl(grp_id, grp_name, grp_mng, mem_no, grp_zipcode, grp_addr1, grp_addr2, create_date, grp_intro, app_st, main_name, sub_name, grp_mimg, grp_pimg)"
			//		+" values (seq_group_id.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String query = "insert into group_tbl(grp_id, grp_name, grp_mng, mem_no, grp_zipcode, grp_addr1, grp_addr2, create_date, grp_intro, app_st, main_name, sub_name)"
					+ " values(seq_group_id.nextval,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			
			String grp_name = groupVO.getGrp_name();
			String grp_mng = groupVO.getGrp_mng();
			String mem_no = groupVO.getMem_no();
			
			
			//pstmt.setString(1, );
			
			
		} catch (Exception e) {
			System.out.println("createGroup: 오류");
			e.printStackTrace();
		}
		
		return 0;
	}

}
