package nemo.dao.grpmng;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.catalina.User;

import nemo.vo.group.GroupVO;
import nemo.vo.group.UserVO;

//쿼리문 전부 다시 짜고 확인할 것!!
public class GroupMangerSettingDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	//DB연결
	public GroupMangerSettingDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");

		} catch (Exception e) {
			System.out.println("GroupManagerDAO: DB 연결 오류");
			e.printStackTrace();
		}
	}
	
	
	//소모임 프로필사진 수정
	public void modGroupPhoto (GroupVO groupVO, int grp_id) {
		String grp_img=groupVO.getGrp_img();
		try {
			conn = dataFactory.getConnection();
			String query = "UPDATE group_tbl SET grp_img = ? WHERE grp_id = ?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, grp_img);
			pstmt.setInt(2, grp_id);
			pstmt.executeUpdate();

			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("프로필사진 수정 중 오류");
			e.printStackTrace();
		}

	}
	
}
