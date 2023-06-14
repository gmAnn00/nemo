package nemo.dao.group;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.group.GroupVO;

public class GroupMainDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public GroupMainDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");

		} catch (Exception e) {
			System.out.println("CreateGroupDAO: DB 연결 오류");
			e.printStackTrace();
		}
	} // end of Constructor

	public GroupVO selectGroupById() {
		// TODO Auto-generated method stub
		return null;
	}

}
