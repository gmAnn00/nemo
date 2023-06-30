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
	
	
	//소모임 수정
	public void updateGroup(GroupVO groupVO, int grp_id) {

		int mem_no = groupVO.getMem_no();
		String grp_zipcode = groupVO.getGrp_zipcode();
		String grp_addr1 = groupVO.getGrp_addr1();
		String grp_addr2 = groupVO.getGrp_addr2();
		String grp_intro = groupVO.getGrp_intro();
		String main_name = groupVO.getMain_name();
		String sub_name = groupVO.getSub_name();
		String grp_img = groupVO.getGrp_img();
		
		try {
			conn=dataFactory.getConnection();
			String query = "UPDATE group_tbl SET mem_no=?, main_name=?, sub_name=?, grp_zipcode=?, grp_addr1=?, grp_addr2=?, grp_intro=?";
			//이미지를 바꿨을수도, 아닐 수도 있어서 if 추가
			if(grp_img != null && grp_img.length() != 0) {
				query += ", grp_img=?";
			}
			query += " where grp_id=?";
			//
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mem_no);
			pstmt.setString(2, main_name);
			pstmt.setString(3, sub_name);
			pstmt.setString(4, grp_zipcode);
			pstmt.setString(5, grp_addr1);
			pstmt.setString(6, grp_addr2);
			pstmt.setString(7, grp_intro);
			
			if(grp_img != null && grp_img.length() != 0) {
				pstmt.setString(8, grp_img);
				pstmt.setInt(9, grp_id);
			} else {
				pstmt.setInt(8, grp_id);
			}
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
					
		} catch (Exception e) {
			System.out.println("소모임 수정 중 에러!!");
			e.printStackTrace();
		}
	}
	
}
