package nemo.dao.board;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.board.*;

public class BoardDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	
	private DataSource dataFactory;
	
	public BoardDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("BoardDAO 연결 오류");
			e.printStackTrace();
		}
	}
	
	//게시글 리스트(목록보기)
	public List<BoardVO> selectGrpArticles(Map<String, Integer> pagingMap, int grp_id) {
		List<BoardVO> articleList=new ArrayList<BoardVO>();
		int section=pagingMap.get("section");
		int pageNum=pagingMap.get("pageNum");
		
		try {
			conn=dataFactory.getConnection();
			
			String query ="SELECT * FROM"
					+ "	(SELECT ROWNUM AS recNUM, b.article_no, b.user_id, u.nickname, b.grp_id, b.create_date, b.title, b.brackets, b.view_cnt, b.com_cnt"
					+ "	FROM board_tbl b, user_tbl u where u.user_id=b.user_id and b.grp_id=? order by b.article_no desc)"
					+ "WHERE recNUM BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10";
			
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, grp_id);
			pstmt.setInt(2, section);
			pstmt.setInt(3, pageNum);
			pstmt.setInt(4, section);
			pstmt.setInt(5, pageNum);
			
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int article_no=rs.getInt("article_no");
				String user_id=rs.getString("user_id");
				String nickname=rs.getString("nickname");
				int group_id=rs.getInt("grp_id");
				Date create_date=rs.getDate("create_date");
				String title=rs.getString("title");
				String brackets=rs.getString("brackets");
				int view_cnt=rs.getInt("view_cnt");
				int com_cnt=rs.getInt("com_cnt");
				
				BoardVO boardVO=new BoardVO();
				
				boardVO.setArticle_no(article_no);
				boardVO.setUser_id(user_id);
				boardVO.setNickname(nickname);
				boardVO.setGrp_id(group_id);
				boardVO.setCreate_date(create_date);
				boardVO.setTitle(title);
				boardVO.setBrackets(brackets);
				boardVO.setView_cnt(view_cnt);
				boardVO.setCom_cnt(com_cnt);
				
				articleList.add(boardVO);
			} // End Of While
			
			rs.close();
			pstmt.close();
			conn.close();
			
			
		} catch (Exception e) {
			System.out.println("게시글 리스트 확인 중 에러");
			e.printStackTrace();			
		}
		return articleList;
	}

	//총 글 개수
	public int selectToArticles(int group_id) {
		int totCount=0;
		
		try {
			conn=dataFactory.getConnection();
			String query = "SELECT COUNT(*) FROM board_tbl WHERE grp_id=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				totCount=rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			System.out.println("전체 글 목록 수 처리 중 에러");
			e.printStackTrace();
		}
		return totCount;
	}
	
	
	//게시글 작성
	
	
	//게시글 상세보기 
	public BoardVO selectArticle(int article_no) {
		
		BoardVO article=new BoardVO();
		
		try {
			conn=dataFactory.getConnection();
			String query="SELECT b.article_no, b.user_id, u.nickname, b.grp_id, b.create_date,";
			query+=" b.title,b.content, b.brackets, b.view_cnt, b.com_cnt FROM board_tbl b, user_tbl u";
			query+=" where u.user_id=b.user_id and b.article_no=?";

			
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, article_no);
			System.out.println(query);
			
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			
			int _article_no=rs.getInt("article_no");
			String user_id=rs.getString("user_id");
			String nickname=rs.getString("nickname");
			int group_id=rs.getInt("grp_id");
			Date create_date=rs.getDate("create_date");
			String title=rs.getString("title");
			String content=rs.getString("content");
			String bracket=rs.getString("brackets");
			int view_cnt=rs.getInt("view_cnt");
			int com_cnt=rs.getInt("com_cnt");
			
			article.setArticle_no(_article_no);
			article.setUser_id(user_id);
			article.setNickname(nickname);
			article.setGrp_id(group_id);
			article.setCreate_date(create_date);
			article.setTitle(title);
			article.setContent(content);
			article.setBrackets(bracket);
			article.setView_cnt(view_cnt);
			article.setCom_cnt(com_cnt);

			rs.close();
			conn.close();
			pstmt.close();
		
		} catch (Exception e) {
			System.out.println("게시글 상세 조회 중 에러 ");
			e.printStackTrace();
		}
		return article;
	}

	//댓글 리스트 
	public List<CommentVO> selectComments(int article_no) {
		List<CommentVO> commentList = new ArrayList<CommentVO>();
		try {
			conn=dataFactory.getConnection();
			
			String query="SELECT LEVEL, c.comment_no, c.article_no, c.user_id, u.nickname, c.create_date, c.com_cont, c.parent_no";
			query+=" FROM comment_tbl c, user_tbl u where u.user_id=c.user_id and c.article_no=?";
			query+=" START WITH c.parent_no=0";
			query+=" CONNECT BY PRIOR c.comment_no=c.parent_no ORDER SIBLINGS BY c.comment_no";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, article_no);
			ResultSet rs=pstmt.executeQuery();

			while(rs.next()) {
				int level=rs.getInt("LEVEL");
				int comment_no=rs.getInt("comment_no");
				int _article_no=rs.getInt("article_no");
				String user_id=rs.getString("user_id");
				String nickname=rs.getString("nickname");
				//Date create_date=rs.getDate("create_date");
				Timestamp create_date=rs.getTimestamp("create_date");
				
				String com_cont=rs.getString("com_cont");
				int parent_no=rs.getInt("parent_no");
				CommentVO comment=new CommentVO(level, comment_no, _article_no, user_id, nickname, create_date, com_cont, parent_no);
				commentList.add(comment);
			} // End Of While
			rs.close();
			conn.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println("댓글 리스트 조회 중 에러 ");
			e.printStackTrace();
		}
		return commentList;
		
	}
	
	//게시글 삭제
	
	//댓글 작성
	
	//댓글 삭제
	
	//게시글 검색-제목
	public List<BoardVO> selectByTitle(String in_title, int grp_id) {
		List<BoardVO> articleList=new ArrayList<BoardVO>();
		
		try {
			conn=dataFactory.getConnection();
			
			String query="SELECT b.article_no, b.user_id, u.nickname, b.grp_id, b.create_date,";
			query+=" b.title, b.brackets, b.view_cnt, b.com_cnt FROM board_tbl b, user_tbl u";
			query+=" where u.user_id=b.user_id and b.grp_id=? and b.title LIKE '%'||?||'%'";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, grp_id);
			pstmt.setString(2, in_title);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				int article_no=rs.getInt("article_no");
				String user_id=rs.getString("user_id");
				String nickname=rs.getString("ninckname");
				int group_id=rs.getInt("grp_id");
				Date create_date=rs.getDate("create_date");
				String title=rs.getString("title");
				//String content=rs.getString("content");
				String bracket=rs.getString("brackets");
				int view_cnt=rs.getInt("view_cnt");
				int com_cnt=rs.getInt("com_cnt");
				BoardVO boardVO=new BoardVO();
				boardVO.setArticle_no(article_no);
				boardVO.setUser_id(user_id);
				boardVO.setNickname(nickname);
				boardVO.setGrp_id(grp_id);
				boardVO.setCreate_date(create_date);
				boardVO.setTitle(title);
				//boardVO.setContent(content);
				boardVO.setBrackets(bracket);
				boardVO.setView_cnt(view_cnt);
				boardVO.setCom_cnt(com_cnt);
				articleList.add(boardVO);
			} // End Of While
			rs.close();
			conn.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println("제목으로 게시글 검색중 에러");
			e.printStackTrace();			
		}
		return articleList;
	}
	
	
	//게시글 검색-내용
	public List<BoardVO> selectByContent(String in_cont, int grp_id) {
		List<BoardVO> articleList=new ArrayList<BoardVO>();
		
		try {
			conn=dataFactory.getConnection();
			
			String query="SELECT b.article_no, b.user_id, u.nickname, b.grp_id, b.create_date,";
			query+=" b.title, b.brackets, b.view_cnt, b.com_cnt FROM board_tbl b, user_tbl u";
			query+=" where u.user_id=b.user_id and b.grp_id=? and b.cont LIKE '%'||?||'%'";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, grp_id);
			pstmt.setString(2, in_cont);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				int article_no=rs.getInt("article_no");
				String user_id=rs.getString("user_id");
				String nickname=rs.getString("ninckname");
				int group_id=rs.getInt("grp_id");
				Date create_date=rs.getDate("create_date");
				String title=rs.getString("title");
				String bracket=rs.getString("brackets");
				int view_cnt=rs.getInt("view_cnt");
				int com_cnt=rs.getInt("com_cnt");
				BoardVO boardVO=new BoardVO();
				boardVO.setArticle_no(article_no);
				boardVO.setUser_id(user_id);
				boardVO.setNickname(nickname);
				boardVO.setGrp_id(grp_id);
				boardVO.setCreate_date(create_date);
				boardVO.setTitle(title);
				boardVO.setBrackets(bracket);
				boardVO.setView_cnt(view_cnt);
				boardVO.setCom_cnt(com_cnt);
				articleList.add(boardVO);
			} // End Of While
			rs.close();
			conn.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println("제목으로 게시글 검색중 에러");
			e.printStackTrace();			
		}
		return articleList;
	}
	
	
	//게시글 검색-작성자
	public List<BoardVO> selectByNickName(String in_nick, int grp_id) {
		List<BoardVO> articleList=new ArrayList<BoardVO>();
		
		try {
			conn=dataFactory.getConnection();
			
			String query="SELECT b.article_no, b.user_id, u.nickname, b.grp_id, b.create_date,";
			query+=" b.title, b.brackets, b.view_cnt, b.com_cnt FROM board_tbl b, user_tbl u";
			query+=" where u.user_id=b.user_id and b.grp_id=? and u.nickname LIKE '%'||?||'%'";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, grp_id);
			pstmt.setString(2, in_nick);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				int article_no=rs.getInt("article_no");
				String user_id=rs.getString("user_id");
				String nickname=rs.getString("ninckname");
				int group_id=rs.getInt("grp_id");
				Date create_date=rs.getDate("create_date");
				String title=rs.getString("title");
				String bracket=rs.getString("brackets");
				int view_cnt=rs.getInt("view_cnt");
				int com_cnt=rs.getInt("com_cnt");
				BoardVO boardVO=new BoardVO();
				boardVO.setArticle_no(article_no);
				boardVO.setUser_id(user_id);
				boardVO.setNickname(nickname);
				boardVO.setGrp_id(grp_id);
				boardVO.setCreate_date(create_date);
				boardVO.setTitle(title);
				boardVO.setBrackets(bracket);
				boardVO.setView_cnt(view_cnt);
				boardVO.setCom_cnt(com_cnt);
				articleList.add(boardVO);
			} // End Of While
			rs.close();
			conn.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println("제목으로 게시글 검색중 에러");
			e.printStackTrace();			
		}
		return articleList;
	}
	
	//게시글 상세조회한 사람과 글 작성자가 동일한지 체크하는 메소드
	public boolean isSameUser(String user_id, int article_no) {
		boolean isSame=false;
			try {
				conn=dataFactory.getConnection();
				String query="SELECT * FROM board_tbl WHERE user_id=? and article_no=?";
				System.out.println(query);
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, user_id);
				pstmt.setInt(2, article_no);
				ResultSet rs=pstmt.executeQuery();
				isSame=rs.next();
				
				if(isSame) {
					System.out.println("동일인");
				} else {
					System.out.println("동일인 아님");
				}
				rs.close();
				pstmt.close();
				conn.close();
				
			} catch (Exception e) {
				System.out.println("작성자와 조회한 사람이 동일한지 체크 중 오류");
			}
		return isSame;
	}
	
	//글 조회시 조회수 증가시키는 메소드
	public void addViewCnt(int article_no) {
		try {
			conn=dataFactory.getConnection();
			String query="UPDATE BOARD_TBL SET VIEW_CNT=VIEW_CNT+1 WHERE article_no=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, article_no);
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("조회수 증가 중 에러");
		}
	}
	
	
	//유저가 그룹 멤버인지 확인하는 메소드
	public boolean isMember(String user_id, int grp_id) {
		boolean isMem=false;
		try {
			conn = dataFactory.getConnection();
			String query = "select * from grpjoin_tbl where user_id=? and grp_id=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, grp_id);
			ResultSet rs=pstmt.executeQuery();
			isMem=rs.next();
			if(isMem) {
				System.out.println("소모임에 유저 가입");
			} else {
				System.out.println("소모임에 유저가입X");
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("isMember 메소드 처리 중 오류");
			e.printStackTrace();
		}
		return isMem;
	}
}
