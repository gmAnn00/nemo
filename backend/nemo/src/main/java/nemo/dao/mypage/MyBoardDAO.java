package nemo.dao.mypage;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.mypage.MyBoardVO;

public class MyBoardDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public MyBoardDAO() {
		try {
			//커넥션 풀 - JNDI(Java Naming Directory Interface)
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");			
		} catch (Exception e) {
			//Servers - context.xml 확인
			System.out.println("DB연결오류");
		}		
	}
	
	// 마이페이지 내가 쓴 글 조회 메서드 
	public List selectMyArticle(String user_id) {
		
		List<MyBoardVO> myArticleList = new ArrayList<MyBoardVO>();
		
		try {
			conn=dataFactory.getConnection();
			
			String query = "SELECT g.grp_id, g.grp_name, b.article_no, b.title, b.content, b.create_date, u.user_img, u.nickname";
			query += " FROM group_tbl g, board_tbl b, user_tbl u";
			query += " WHERE b.user_id=? and b.user_id=u.user_id and b.grp_id=g.grp_id";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int grp_id = rs.getInt("grp_id");
				String grp_name = rs.getString("grp_name");
				int article_no = rs.getInt("article_no");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date create_date = rs.getDate("create_date");
				String user_img = rs.getString("user_img");
				String nickname = rs.getString("nickname");
				
				MyBoardVO myArticle = new MyBoardVO();
				myArticle.getGroupVO().setGrp_id(grp_id);
				myArticle.getGroupVO().setGrp_name(grp_name);
				myArticle.getBoardVO().setArticle_no(article_no);
				myArticle.getBoardVO().setTitle(title);
				myArticle.getBoardVO().setContent(content);
				myArticle.getBoardVO().setCreate_date(create_date);
				myArticle.getUserVO().setUser_id(user_id);
				myArticle.getUserVO().setUser_img(user_img);
				myArticle.getUserVO().setNickname(nickname);
				
				myArticleList.add(myArticle);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("내가 작성한 글 조회 중 에러 ");
			e.printStackTrace();
		}	
		return myArticleList;
	}

	//내가 쓴 댓글 조회하는 메소드 
	public List selectMyComment(String user_id) {
		List<MyBoardVO> myCommentList = new ArrayList<MyBoardVO>();
		try {
			conn=dataFactory.getConnection();
			String query = "SELECT g.grp_id, g.grp_name,  b.article_no, b.title, c.com_cont, c.create_date, u.user_img, u.nickname";
			query += " FROM group_tbl g, board_tbl b, comment_tbl c, user_tbl u";
			query += " WHERE c.user_id=? and c.grp_id=g.grp_id and c.user_id=u.user_id and b.article_no=c.article_no";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int grp_id = rs.getInt("grp_id");
				String grp_name = rs.getString("grp_name");
				int article_no = rs.getInt("article_no");
				String title = rs.getString("title");
				String com_cont = rs.getString("com_cont");
				//Date create_date = rs.getDate("create_date");
				Timestamp create_dateT = rs.getTimestamp("create_date");
				String user_img = rs.getString("user_img");
				String nickname = rs.getString("nickname");
				
				SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일");
				String create_date = date.format(create_dateT);
				
				MyBoardVO myComment = new MyBoardVO();
				myComment.getGroupVO().setGrp_id(grp_id);
				myComment.getGroupVO().setGrp_name(grp_name);
				myComment.getBoardVO().setArticle_no(article_no);
				myComment.getBoardVO().setTitle(title);
				myComment.getCommentVO().setCom_cont(com_cont);
				//myComment.getCommentVO().setCreate_date(create_date);
				myComment.setCreate_date(create_date);
				myComment.getUserVO().setUser_id(user_id);
				myComment.getUserVO().setUser_img(user_img);
				myComment.getUserVO().setNickname(nickname);
				
				myCommentList.add(myComment);		
			}
			
			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("내가 작성한 댓글 조회 중 에러 ");
			e.printStackTrace();
		}	
		return myCommentList;
	}

	
}
