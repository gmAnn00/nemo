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
			String query="SELECt nvl(b.cnt,0) as repCnt, a.grp_id, a.rep_date, a.reporter_id, g.grp_name"
					+ " FROM greport_tbl a, (SELECT COUNT(*) as cnt FROM greport_tbl group by grp_id) b, group_tbl g"
					+" WHERE g.grp_id=a.grp_id";
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				int repCnt=rs.getInt("repCnt");
				int grp_id=rs.getInt("grp_id");
				Date rep_date=rs.getDate("rep_date");
				String reporter_id=rs.getString("reporter_id");
				String grp_name=rs.getString("grp_name");
				
				Map repGrpInfo=new HashMap();
				GroupReportVO grpRepVO=new GroupReportVO();
				grpRepVO.setGrp_id(grp_id);
				grpRepVO.setRep_date(rep_date);
				grpRepVO.setReporter_id(reporter_id);
				grpRepVO.getGroupVO().setGrp_name(grp_name);
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
