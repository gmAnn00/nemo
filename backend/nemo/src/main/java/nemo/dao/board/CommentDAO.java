package nemo.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.board.CommentVO;
import nemo.vo.board.MyBoardVO;

public class CommentDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	public CommentDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("CommnetDAO 연결 오류");
			e.printStackTrace();
		}
	}
	
	//댓글 추가
	public int insertNewComment(String user_id,int group_id, int article_no, String com_cont, int parent_no) {
		int comment_no=0;
		try {
			conn=dataFactory.getConnection();
			String query="INSERT INTO comment_tbl (comment_no, article_no, user_id, com_cont,parent_no, grp_id)";
			query+=" VALUES (seq_comm_no.NEXTVAL,?,?,?,?,?)";
			
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,article_no);
			pstmt.setString(2, user_id);
			pstmt.setString(3,com_cont);
			pstmt.setInt(4, parent_no);
			pstmt.setInt(5, group_id);
			pstmt.executeUpdate();
			
			query=" SELECT seq_comm_no.CURRVAL AS num FROM dual";
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			comment_no=rs.getInt("num");
			
			rs.next();
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			System.out.println("db 댓글 등록 중 에러 ");
			e.printStackTrace();
		}
		System.out.println("댓글 등록 :"+comment_no);
		return comment_no;
	}
	
	public int getParentNo(int parent_no) {
		int getParentNo=0;
		try {
			conn=dataFactory.getConnection();
			String query="SELECT parent_no FROM comment_tbl WHERE comment_no=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, parent_no);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			if(rs.getInt("parent_no")==0) {
				getParentNo=parent_no;
			}else {
				getParentNo=rs.getInt("parent_no");
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("부모 번호 찾는 중 에러");
		}
		
		return getParentNo;
	}
	
	public int getMaxCommentNo(int parent_no) {
		int maxCommentNo=0;
		try {
			conn=dataFactory.getConnection();
			String query="select max(comment_no) as maxNum from comment_tbl where parent_no=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,parent_no);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			maxCommentNo=rs.getInt("maxNum");
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return maxCommentNo;
	}
	
	//댓글 리스트 
	public List<CommentVO> selectComments(int article_no) {
		List<CommentVO> commentList = new ArrayList<CommentVO>();
		try {
			conn=dataFactory.getConnection();
			
			String query="SELECT LEVEL, c.comment_no, c.article_no, c.user_id, u.nickname, u.user_img, c.create_date, c.com_cont, c.parent_no";
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
	//댓글 수정
	public void updateComment(int comment_no, String com_cont) {
		try {
			conn=dataFactory.getConnection();
			String query="UPDATE comment_tbl SET com_cont=? WHERE comment_no=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, com_cont);
			pstmt.setInt(2, comment_no);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			System.out.println("댓글 수정 중 에러");
			e.printStackTrace();
		}
	}
	
	//댓글 수정 취소시 comment 내용 받아오기
	public String selectCommentCont(int comment_no) {
		String com_cont=null;
		try {
			conn=dataFactory.getConnection();
			String query="SELECT com_cont FROM comment_tbl WHERE comment_no=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, comment_no);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			com_cont=rs.getString("com_cont");
			rs.close();
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			System.out.println("댓글 수정 취소시 comment 받아 오는 중 에러");
			e.printStackTrace();
		}
		return com_cont;
	}
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

	//comment_id로 코멘트 정보 받는 메소드
	public CommentVO selectComment(int comment_no) {
		CommentVO comment= new CommentVO();
		try {
			conn=dataFactory.getConnection();
			String query="SELECT c.comment_no, c.article_no, u.nickname, c.com_cont, c.create_date, a.title, parent_no, u.user_img";
			query+=" FROM comment_tbl c, board_tbl a, user_tbl u";
			query+=" WHERE u.user_id=c.user_id AND c.article_no=a.article_no AND c.comment_no=?";
			
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, comment_no);
			System.out.println(query);
			
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			
			int _comment_no=rs.getInt("comment_no");
			int article_no=rs.getInt("article_no");
			String nickname=rs.getString("nickname");
			String user_img=rs.getString("user_img");
			String com_cont=rs.getString("com_cont");
			Timestamp create_date=rs.getTimestamp("create_date");
			String title = rs.getString("title");
			int parent_no=rs.getInt("parent_no");
			
			comment.setComment_no(_comment_no);
			comment.setArticle_no(article_no);
			comment.getUserVO().setNickname(nickname);
			comment.setParent_no(parent_no);
			comment.setCreate_date(create_date);
			comment.getArticleVO().setTitle(title);
			comment.getUserVO().setUser_img(user_img);
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
	
	//댓글 개수 가져오는 메소드
	public int getCommentCnt(int article_no) {
		int com_cnt=0;
		try {
			conn=dataFactory.getConnection();
			String query = "SELECT COUNT(*) as cnt FROM comment_tbl WHERE article_no=?";
			System.out.println("query");
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, article_no);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			com_cnt=rs.getInt("cnt");
			
		}catch (Exception e) {
			System.out.println("댓글 개수 가져오는 중 에러");
			e.printStackTrace();
		}
		return com_cnt;
	}
	
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
				
				pstmt.close();
				conn.close();
				
			} catch (Exception e) {
				System.out.println("작성자와 조회한 사람이 동일한지 체크 중 오류");
			}
		return isSame;
	}
}
