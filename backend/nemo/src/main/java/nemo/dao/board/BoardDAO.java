package nemo.dao.board;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
<<<<<<< HEAD
=======
import java.sql.Timestamp;
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
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
<<<<<<< HEAD
=======
		
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
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
<<<<<<< HEAD
	public List<BoardVO> selectGrpArticles(Map<String, Integer> pagingMap, int grp_id) {
		List<BoardVO> articleList=new ArrayList<BoardVO>();
=======
	public List<BoardVO> selectGrpArticles(Map<String, Integer> pagingMap, int _group_id) {
		List<BoardVO> articleList=new ArrayList<BoardVO>();
		
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
		int section=pagingMap.get("section");
		int pageNum=pagingMap.get("pageNum");
		
		try {
			conn=dataFactory.getConnection();
<<<<<<< HEAD
			
			String query ="SELECT * FROM"
					+ "	(SELECT ROWNUM AS recNUM, b.article_no, b.user_id, u.nickname, b.grp_id, b.create_date, b.title, b.brackets, b.view_cnt, b.com_cnt"
					+ "	FROM board_tbl b, user_tbl u where u.user_id=b.user_id and b.grp_id=? order by b.article_no desc)"
					+ "WHERE recNUM BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10";
			
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, grp_id);
=======
			/*
			String query ="SELECT * FROM"
					+ "	(SELECT ROWNUM AS recNUM, b.article_no, b.user_id, u.nickname, b.grp_id, b.create_date, b.title, b.brackets, b.view_cnt, b.com_cnt"
					+ "	FROM board_tbl b, user_tbl u where u.user_id=b.user_id and b.grp_id=? )"
					+ "WHERE recNUM BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10 order by article_no desc";
			*/
			String query="SELECT * FROM (SELECT ROWNUM as recNum, a.* FROM (SELECT b.article_no, b.user_id, u.nickname, b.grp_id, b.create_date, b.title, b.brackets, b.view_cnt, b.com_cnt";
			query+=" FROM board_tbl b, user_tbl u where u.user_id=b.user_id and b.grp_id=? order by b.article_no desc) a )";
			query+=" WHERE recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, _group_id);
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
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
<<<<<<< HEAD
				
=======
		
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
				BoardVO boardVO=new BoardVO();
				
				boardVO.setArticle_no(article_no);
				boardVO.setUser_id(user_id);
<<<<<<< HEAD
				boardVO.setNickname(nickname);
=======
				//boardVO.setNickname(nickname);
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
				boardVO.setGrp_id(group_id);
				boardVO.setCreate_date(create_date);
				boardVO.setTitle(title);
				boardVO.setBrackets(brackets);
				boardVO.setView_cnt(view_cnt);
				boardVO.setCom_cnt(com_cnt);
<<<<<<< HEAD
				
=======
				boardVO.getUserVO().setNickname(nickname);
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
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
	
<<<<<<< HEAD
	
	//게시글 작성
	
=======
	//공지사항리스트 메소드
	public List<BoardVO> selectNoticeList(int _group_id) {
		List<BoardVO> noticeList=new ArrayList<BoardVO>();
		try {
			conn=dataFactory.getConnection();

			String query="SELECT * FROM (SELECT ROWNUM as recNum, a.*";
			query+=" FROM (SELECT b.article_no, b.user_id, u.nickname, b.grp_id, b.create_date, b.title, b.brackets, b.view_cnt, b.com_cnt";
			query+=" FROM board_tbl b, user_tbl u where u.user_id=b.user_id and b.grp_id=? and b.brackets='공지'order by create_date desc) a )";
			query+=" WHERE recNum BETWEEN 0 AND 3";

			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, _group_id);
			
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
				//boardVO.setNickname(nickname);
				boardVO.setGrp_id(group_id);
				boardVO.setCreate_date(create_date);
				boardVO.setTitle(title);
				boardVO.setBrackets(brackets);
				boardVO.setView_cnt(view_cnt);
				boardVO.setCom_cnt(com_cnt);
				boardVO.getUserVO().setNickname(nickname);
				System.out.println(boardVO.getUserVO().getNickname());
				noticeList.add(boardVO);
			} // End Of While
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("공지글 받아오는 중 에러");
			e.printStackTrace();	
		}
		return noticeList;
	}
	
	//게시글 작성
	public void insertNewArticle(BoardVO boardVO) {
		try {
			conn=dataFactory.getConnection();
			String query="INSERT INTO board_tbl (article_no, user_id, grp_id, title, content, brackets)";
			query+=" VALUES (?, ?, ?, ?, ?, ?)";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			
			int article_no=boardVO.getArticle_no();
			String user_id=boardVO.getUser_id();
			int grp_id=boardVO.getGrp_id();
			String title=boardVO.getTitle();
			String content=boardVO.getContent();
			String brackets=boardVO.getBrackets();
			pstmt.setInt(1, article_no);
			pstmt.setString(2, user_id);
			pstmt.setInt(3, grp_id);
			pstmt.setString(4, title);
			pstmt.setString(5, content);
			pstmt.setString(6, brackets);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("게시글 작성 중 에러");
			e.printStackTrace();
		}
	}
	
	public int getNewArticleNo() {
		int article_no=0;
		try {
			conn=dataFactory.getConnection();
			String query="SELECT seq_art_no.nextVAL AS article_no FROM DUAL";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			article_no=rs.getInt("article_no");
		} catch (Exception e) {
			System.out.println("새 글 번호 받아 오는 중 에러");
			e.printStackTrace();
		}
		return article_no;
	}
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
	
	//게시글 상세보기 
	public BoardVO selectArticle(int article_no) {
		
		BoardVO article=new BoardVO();
		
		try {
			conn=dataFactory.getConnection();
<<<<<<< HEAD
			String query="SELECT b.article_no, b.user_id, u.nickname, b.grp_id, b.create_date,";
=======
			String query="SELECT b.article_no, b.user_id, u.nickname, u.user_img, b.grp_id, b.create_date,";
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
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
<<<<<<< HEAD
=======
			String user_img=rs.getString("user_img");
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
			int group_id=rs.getInt("grp_id");
			Date create_date=rs.getDate("create_date");
			String title=rs.getString("title");
			String content=rs.getString("content");
			String bracket=rs.getString("brackets");
			int view_cnt=rs.getInt("view_cnt");
			int com_cnt=rs.getInt("com_cnt");
			
			article.setArticle_no(_article_no);
			article.setUser_id(user_id);
<<<<<<< HEAD
			article.setNickname(nickname);
=======
			//article.setNickname(nickname);
			article.getUserVO().setNickname(nickname);
			article.getUserVO().setUser_img(user_img);
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
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

<<<<<<< HEAD
	//댓글 리스트 
=======
	/*
	 * //댓글 리스트 

>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
	public List<CommentVO> selectComments(int article_no) {
		List<CommentVO> commentList = new ArrayList<CommentVO>();
		try {
			conn=dataFactory.getConnection();
			
<<<<<<< HEAD
			String query="SELECT LEVEL, c.comment_no, c.article_no, c.user_id, u.nickname, c.create_date, c.com_cont, c.parent_no";
			query+=" FROM comment_tbl c, user_tbl u where u.user_id=c.user_id and c.article_no=?";
			query+=" START WITH c.parent_no=0";
			query+=" CONNECT BY PRIOR c.comment_no=c.parent_no ORDER SIBLINGS BY c.comment_no DESC";
=======
			String query="SELECT LEVEL, c.comment_no, c.article_no, c.user_id, u.nickname, u.user_img, c.create_date, c.com_cont, c.parent_no";
			query+=" FROM comment_tbl c, user_tbl u where u.user_id=c.user_id and c.article_no=?";
			query+=" START WITH c.parent_no=0";
			query+=" CONNECT BY PRIOR c.comment_no=c.parent_no ORDER SIBLINGS BY c.comment_no";
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, article_no);
			ResultSet rs=pstmt.executeQuery();

			while(rs.next()) {
				int level=rs.getInt("LEVEL");
				int comment_no=rs.getInt("comment_no");
				int _article_no=rs.getInt("article_no");
				String user_id=rs.getString("user_id");
<<<<<<< HEAD
				String nickname=rs.getString("ninckname");
				Date create_date=rs.getDate("create_date");
				String com_cont=rs.getString("com_cont");
				int parent_no=rs.getInt("parent_no");
				CommentVO comment=new CommentVO(level, comment_no, _article_no, user_id, nickname, create_date, com_cont, parent_no);
=======
				String nickname=rs.getString("nickname");
				String user_img=rs.getString("user_img");
				//Date create_date=rs.getDate("create_date");
				Timestamp create_date=rs.getTimestamp("create_date");
				String com_cont=rs.getString("com_cont");
				int parent_no=rs.getInt("parent_no");
				//CommentVO comment=new CommentVO(level, comment_no, _article_no, user_id, nickname, create_date, com_cont, parent_no);
				
				CommentVO comment=new CommentVO();
				comment.setLevel(level);
				comment.setComment_no(comment_no);
				comment.setArticle_no(_article_no);
				comment.setUser_id(user_id);
				comment.getUserVO().setNickname(nickname);
				comment.getUserVO().setUser_img(user_img);
				comment.setCreate_date(create_date);
				comment.setCom_cont(com_cont);
				comment.setParent_no(parent_no);;
				
				
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
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
<<<<<<< HEAD
	
	//게시글 삭제
	
	//댓글 작성
	
	//댓글 삭제
	
	//게시글 검색-제목
	public List<BoardVO> selectByTitle(String in_title, int grp_id) {
		List<BoardVO> articleList=new ArrayList<BoardVO>();
=======
		 */
	

	
	
	//게시글 삭제
	public void deleteArticle(int article_no) {
		try {
			conn=dataFactory.getConnection();
			String query="DELETE FROM board_tbl WHERE article_no=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, article_no);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("게시글 삭제 중 에러");
			
		}
	}
/*	
	//댓글 삭제
	public void deleteComment(int comment_no) {
		try {
			conn=dataFactory.getConnection();
			String query = "DELETE FROM comment_tbl WHERE comment_no IN";
			query+=" (SELECT comment_no FROM comment_tbl";
			query+=" START WITH comment_no=? CONNECT BY PRIOR comment_no=parent_no)";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, comment_no);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("게시글 삭제 중 에러");
			e.printStackTrace();
		}
	}
	
	// 댓글에 자식이 있는지 확인하는 메소드
	public boolean checkComChild(int comment_no) {
		boolean check=false;
		try {
			conn=dataFactory.getConnection();
			String query = "select count(*) as cnt from comment_tbl where parent_no=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, comment_no);
			
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			int cnt=rs.getInt("cnt");
			
			if(cnt>0) {
				check=true; //자식이 있음
			}
			System.out.println("자식이 있니?:"+check);
		} catch (Exception e) {
			System.out.println("댓글에 자식이 있는지 확인 하는 중 에러");
			e.printStackTrace();
		}
		
		return check;
	}
	*/
	//댓글 개수 확인해서 업데이트하는 메소드
	public void updateCommentCnt(int account_no) {
		try {
			conn=dataFactory.getConnection();
			String query = "UPDATE board_tbl SET com_cnt=";
			query+="(select count(*) from comment_tbl where article_no=?)";
			query+=" WHERE article_no=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, account_no);
			pstmt.setInt(2, account_no);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("댓글 개수 업데이트하는 중 에러");
			e.printStackTrace();
		}
	}
	
	//글 수정하는 메소드
	public void updateArticle(BoardVO boardVO) {
		try {
			conn=dataFactory.getConnection();
			String query="UPDATE board_tbl SET title=?, content=?, brackets=?";
			query+=" WHERE article_no=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, boardVO.getTitle());
			pstmt.setString(2, boardVO.getContent());
			pstmt.setString(3, boardVO.getBrackets());
			pstmt.setInt(4, boardVO.getArticle_no());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("글 수정하는 중 에러");
			e.printStackTrace();
		}
	}
	
	
	//검색한 총 글 개수
	public int searchTotArticles(int group_id, String filter, String keyword) {
		int totCount=0;
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
		
		try {
			conn=dataFactory.getConnection();
			
<<<<<<< HEAD
			String query="SELECT b.article_no, b.user_id, u.nickname, b.grp_id, b.create_date,";
			query+=" b.title, b.brackets, b.view_cnt, b.com_cnt FROM board_tbl b, user_tbl u";
			query+=" where u.user_id=b.user_id and b.grp_id=? and b.title LIKE '%'||?||'%'";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, grp_id);
			pstmt.setString(2, in_title);
=======
			String query = "SELECT COUNT(*) FROM board_tbl";
			if(filter.equals("title")) {
				query+=" WHERE grp_id=? AND title LIKE ?";
			} else if(filter.equals("content")) {
				query+=" WHERE grp_id=? AND content LIKE ?";
				
			}else if(filter.equals("writer")) {
				query+=" b, user_tbl u WHERE u.user_id=b.user_id AND grp_id=? AND u.nickname LIKE ?";
			}else if(filter.equals("brackets")) {
				query+=" WHERE grp_id=? AND brackets LIKE ?";
			}
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, group_id);
			pstmt.setString(2, "%"+keyword+"%");
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				totCount=rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			System.out.println("검색한 총 글 개수 처리 중 에러");
			e.printStackTrace();
		}
		
		return totCount;
	}

	
	//게시글 검색
	public List<BoardVO> selectByKeyword(Map<String, Integer> pagingMap, int grp_id , String filter, String keyword) {
		List<BoardVO> articleList=new ArrayList<BoardVO>();
		int section=pagingMap.get("section");
		int pageNum=pagingMap.get("pageNum");
		
		try {
			conn=dataFactory.getConnection();
			/*
			String query ="SELECT * FROM"
					+ "	(SELECT ROWNUM AS recNUM, b.article_no, b.user_id, u.nickname, b.grp_id, b.create_date, b.title, b.brackets, b.view_cnt, b.com_cnt"
					+ "	FROM board_tbl b, user_tbl u WHERE u.user_id=b.user_id AND b.grp_id=? AND";*/
			String query="SELECT * FROM (SELECT ROWNUM as recNUM, a.* FROM (SELECT b.article_no, b.user_id, u.nickname, b.grp_id, b.create_date, b.title, b.brackets, b.view_cnt, b.com_cnt";
			query+=" FROM board_tbl b, user_tbl u where u.user_id=b.user_id and b.grp_id=? AND ";	
			if(filter.equals("title")){
				query+=" b.title LIKE ?";
			}else if(filter.equals("content")) {
				query+=" b.content LIKE ?";
			}else if(filter.equals("writer")) {
				query+=" u.nickname LIKE ?";
			}else if(filter.equals("brackets")) {
				query+=" b.brackets LIKE ?";
			}
			query+=" order by b.article_no desc) a) WHERE recNUM BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10";

			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, grp_id);
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setInt(3, section);
			pstmt.setInt(4, pageNum);
			pstmt.setInt(5, section);
			pstmt.setInt(6, pageNum);
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				int article_no=rs.getInt("article_no");
				String user_id=rs.getString("user_id");
<<<<<<< HEAD
				String nickname=rs.getString("ninckname");
=======
				String nickname=rs.getString("nickname");
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
				int group_id=rs.getInt("grp_id");
				Date create_date=rs.getDate("create_date");
				String title=rs.getString("title");
				//String content=rs.getString("content");
				String bracket=rs.getString("brackets");
				int view_cnt=rs.getInt("view_cnt");
				int com_cnt=rs.getInt("com_cnt");
				BoardVO boardVO=new BoardVO();
<<<<<<< HEAD
				boardVO.setArticle_no(article_no);
				boardVO.setUser_id(user_id);
				boardVO.setNickname(nickname);
				boardVO.setGrp_id(grp_id);
=======
				
				boardVO.setArticle_no(article_no);
				boardVO.setUser_id(user_id);
				//boardVO.setNickname(nickname);
				boardVO.getUserVO().setNickname(nickname);
				boardVO.setGrp_id(group_id);
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
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
<<<<<<< HEAD
			System.out.println("제목으로 게시글 검색중 에러");
=======
			System.out.println("게시글 키워드 검색중 에러 : " + filter + ", " + keyword);
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
			e.printStackTrace();			
		}
		return articleList;
	}
<<<<<<< HEAD
	
	
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
=======
/*
	//comment_id로 코멘트 정보 받는 메소드
	public CommentVO selectComment(int comment_no) {
		CommentVO comment= new CommentVO();
		try {
			conn=dataFactory.getConnection();
			String query="SELECT c.comment_no, c.article_no, c.com_cont, a.title";
			query+=" FROM comment_tbl c, board_tbl a ";
			query+=" WHERE c.article_no=a.article_no AND c.comment_no=?";
			
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, comment_no);
			System.out.println(query);
			
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			
			int _comment_no=rs.getInt("comment_no");
			int article_no=rs.getInt("article_no");
			String com_cont=rs.getString("com_cont");
			String title = rs.getString("title");
			comment.getArticleVO().setTitle(title);
			comment.setComment_no(_comment_no);
			comment.setArticle_no(article_no);
			comment.setCom_cont(com_cont);

			rs.close();
			conn.close();
			pstmt.close();
		
		} catch (Exception e) {
			System.out.println("댓글 정보 가져오는 중 에러 ");
			e.printStackTrace();
		}
		
		return comment;
	}
	*/

	
	//게시글 글 작성자와 액션한 사람이 동일한지 체크하는 메소드
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
	public boolean isSameUser(String user_id, int article_no) {
		boolean isSame=false;
			try {
				conn=dataFactory.getConnection();
<<<<<<< HEAD
=======
				
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
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
<<<<<<< HEAD
=======
				
				pstmt.close();
				conn.close();
				
			} catch (Exception e) {
				System.out.println("작성자와 조회한 사람이 동일한지 체크 중 오류");
			}
		return isSame;
	}
/*	
	//게시글 코멘트 작성한 사람과 액션한 사람이 동일한지 체크하는 메소드
	public boolean isSameUserC(String user_id, int comment_no) {
		boolean isSame=false;
			try {
				conn=dataFactory.getConnection();
				
				String query="SELECT * FROM comment_tbl WHERE user_id=? and comment_no=?";
				System.out.println(query);
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, user_id);
				pstmt.setInt(2, comment_no);
				ResultSet rs=pstmt.executeQuery();
				isSame=rs.next();
				
				if(isSame) {
					System.out.println("동일인");
				} else {
					System.out.println("동일인 아님");
				}
				rs.close();
				
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
				pstmt.close();
				conn.close();
				
			} catch (Exception e) {
				System.out.println("작성자와 조회한 사람이 동일한지 체크 중 오류");
			}
		return isSame;
	}
	
<<<<<<< HEAD
=======
*/
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 2062e277dc0f7c50a22a1237656803c42da8557d
