package nemo.dao.report;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.report.GroupReportVO;
import oracle.sql.DATE;

public class GroupReportDAO {

	private Connection conn;	
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public GroupReportDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("GroupReportDAO 연결 오류");
			e.printStackTrace();
		}
	}
	
	public List selectGroupReport() {
		List grpReplist=new ArrayList();
		
		try {
			conn=dataFactory.getConnection();
			String query="SELECT r.grp_id, r.reporter_id, r.rep_date, g.grp_name"
					+" FROM greport_tbl r, group_tbl g WHERE r.grp_id=g.grp_id";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				
				int grp_id=rs.getInt("grp_id");
				String grp_name=rs.getString("grp_name");
				String reporter_id=rs.getString("reporter_id");
				Date rep_date=rs.getDate("rep_date");
				
				GroupReportVO grpRepVO=new GroupReportVO();
				grpRepVO.setGrp_id(grp_id);
				grpRepVO.getGroupVO().setGrp_name(grp_name);
				grpRepVO.setReporter_id(reporter_id);
				grpRepVO.setRep_date(rep_date);
				grpReplist.add(grpRepVO);
			}
		}catch (Exception e) {
			System.out.println("그룹신고 조회하는 중 에러");
			e.printStackTrace();
		}
		return grpReplist;
	}
	
}
