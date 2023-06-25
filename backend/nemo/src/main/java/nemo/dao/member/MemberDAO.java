package nemo.dao.member;

import java.sql.Connection;
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
			dataFactory =(DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB연결 오류");
		}
	}
	
	//회원목록
	public List<MemberVO> listMembers() {
		List<MemberVO> memberList= new ArrayList();
		try {
			conn=dataFactory.getConnection();
			String query="select * from membertbl order by join_date desc";
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				String 
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
