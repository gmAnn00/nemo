package nemo.dao.admin;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.jasper.tagplugins.jstl.core.When;

import nemo.vo.admin.GroupReportVO;

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
			System.out.println("그룹 신고 데이터 연결 오류");
			e.printStackTrace();
		}
	}
	

	public List<Map> selectRepGrp(){
		List<Map> groupList=new ArrayList<Map>();
		try {
			conn=dataFactory.getConnection();
			String query="select a.rCnt, a.grp_id, a.reporter_id, a.rep_date, b.grp_name, b.grp_mng"+
					" from (select nvl(r.cnt,0) as rCnt, g.grp_id, g.reporter_id, g.rep_date from"+
					"(SELECT count(*) as cnt,grp_id FROM greport_tbl group by grp_id) r , greport_tbl g where r.grp_id=g.grp_id) a, "
					+ " group_tbl b where a.grp_id=b.grp_id";
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				int repCnt=rs.getInt("rCnt");
				int grp_id=rs.getInt("grp_id");
				Date rep_date=rs.getDate("rep_date");
				String reporter_id=rs.getString("reporter_id");
				String grp_name=rs.getString("grp_name");
				String grp_mng=rs.getString("grp_mng");
				
				Map repGrpInfo=new HashMap();
				GroupReportVO grpRepVO=new GroupReportVO();
				grpRepVO.setGrp_id(grp_id);
				grpRepVO.setRep_date(rep_date);
				grpRepVO.setReporter_id(reporter_id);
				grpRepVO.getGroupVO().setGrp_name(grp_name);
				grpRepVO.getGroupVO().setGrp_mng(grp_mng);
				repGrpInfo.put("repCnt", repCnt);
				repGrpInfo.put("grpRepVO", grpRepVO);
				groupList.add(repGrpInfo);
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			System.out.println("그룹 신고 조회 중 에러");
			e.printStackTrace();
		}
		return groupList;
	}
	
}