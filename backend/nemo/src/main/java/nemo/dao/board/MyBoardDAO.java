package nemo.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.board.CommentVO;
import nemo.vo.board.MyBoardVO;

public class MyBoardDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	public MyBoardDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("MyBoardDAO 연결 오류");
			e.printStackTrace();
		}
	}
	
	//내가 쓴 댓글 조회하는 메소드 
	public List selectMyComment(String user_id) {
		List<MyBoardVO> myCommentList=new ArrayList<MyBoardVO>();
		try {
			conn=dataFactory.getConnection();
			String query="SELECT g.grp_name, a.title, c.com_cont, u.user_img, u.nickname";
			query+=" FROM group_tbl g, board_tbl a, comment_tbl c, user_tbl u";
			query+=" WHERE c.user_id=? and c.grp_id=g.grp_id and c.user_id=u.user_id and a.article_no=c.article_no";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				String grp_name=rs.getString("grp_name");
				String title=rs.getString("title");
				String com_cont=rs.getString("com_cont");
				String user_img=rs.getString("user_img");
				String nickname=rs.getString("nickname");
				
				MyBoardVO myComment=new MyBoardVO();
				myComment.getGroupVO().setGrp_name(grp_name);
				myComment.getBoardVO().setTitle(title);
				myComment.getCommentVO().setCom_cont(com_cont);
				myComment.getUserVO().setUser_img(user_img);
				myComment.getUserVO().setNickname(nickname);
				myCommentList.add(myComment);		
			}
		} catch (Exception e) {
			System.out.println("내가 작성한 댓글 조회 중 에러 ");
			e.printStackTrace();
		}	
		return myCommentList;
	}
	
	//내가 쓴 글 조회하는 메소드
	
}
