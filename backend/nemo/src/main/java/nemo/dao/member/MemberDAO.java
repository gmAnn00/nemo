package nemo.dao.member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.member.MemberVO;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContxt =(Context)ctx.lookup("java:/comp/env");
			dataFactory=(DataSource)envContxt.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB연결 오류");
		}
	}
	
	//회원목록
	public List<MemberVO> listMembers() {
		List<MemberVO> memberList= new ArrayList<MemberVO>();
		try {
			conn=dataFactory.getConnection();
			String query="select u.*, A.report_cnt " + 
					" from user_tbl u " +
					" left outer join (select accused_id, count(*) as report_cnt " +
					" from mreport_tbl " + 
					" group by accused_id " + 
					" ) A on A.accused_id = u.user_id";
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				String user_id=rs.getString("user_id");
				String user_name=rs.getString("user_name");
				Date birthdate=rs.getDate("birthdate");
				String email=rs.getString("email");
				String phone=rs.getString("phone");
				Date join_date=rs.getDate("join_date");
				int report_cnt = rs.getInt("report_cnt");
				//String accused_id=rs.getString("accused_id");
				MemberVO memVo=new MemberVO(user_id, user_name, birthdate, email, phone, join_date);
				memVo.setReport_cnt(report_cnt);
				memberList.add(memVo);
			}
			rs.close();
			pstmt.close();
			conn.close();
			System.out.println(query);		
		} catch (Exception e) {
			System.out.println("DB조회중 에러");
			e.printStackTrace();
		}
		return memberList;
	}
	
	//회원삭제 
	public void delMember(String user_id) {
		try {
			conn=dataFactory.getConnection();
			String query = "delete from user_tbl where user_id=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("회원정보 삭제중 에러");
			e.printStackTrace();
		}
		
	}
	
}
