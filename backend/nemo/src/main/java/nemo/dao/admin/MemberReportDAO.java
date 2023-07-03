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

import nemo.vo.admin.MemberReportVO;

public class MemberReportDAO {
	private Connection conn;	
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public MemberReportDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("회원 신고 데이터 연결 오류");
			e.printStackTrace();
		}
	}
	
	public List<Map> selectRepUser() {
		List<Map> userList=new ArrayList<Map>();
		try {
			conn=dataFactory.getConnection();
			String query="SELECt nvl(b.cnt,0) as repCnt, a.reporter_id, a.accused_id, a.rep_date"+
					" FROM mreport_tbl a, (SELECT COUNT(*) as cnt, accused_id FROM mreport_tbl group by accused_id) b WHERE a.accused_id=b.accused_id";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				int repCnt=rs.getInt("repCnt");
				String reporter_id=rs.getString("reporter_id");
				String accused_id=rs.getString("accused_id");
				Date rep_date=rs.getDate("rep_date");
				System.out.println("a");
				Map repUserInfo=new HashMap();
				MemberReportVO userRepVO=new MemberReportVO();
				userRepVO.setAccused_id(accused_id);
				userRepVO.setRep_date(rep_date);
				userRepVO.setReporter_id(reporter_id);
				repUserInfo.put("userRepVO", userRepVO);
				repUserInfo.put("repCnt", repCnt);
				userList.add(repUserInfo);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("회원 신고 조회하는 중 에러");
			e.printStackTrace();
		}
		return userList;
	}
}