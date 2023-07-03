package nemo.dao.qna;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.qna.QnaVO;

public class QnaDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	private static final int PAGE_ROW_SIZE = 10;
	
	public QnaDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB연결 오류");
		}
	}
	
	//페이징 부분
	public List<QnaVO> selectQnas(Map<String, Integer> pagingMap, String param_user_id, boolean isAdmin) {
		List<QnaVO> articlesList=new ArrayList<QnaVO>();
		int section=pagingMap.get("section");
		int pageNum=pagingMap.get("pageNum");
		try {
			ResultSet rs = null;
			conn=dataFactory.getConnection();
			if (isAdmin) {
				// 관리자모드로 Q&A 리스트를 조회 했을 때
				String query="SELECT * FROM(SELECT ROWNUM AS recNum, LVL, qna_id," +
						" parent_no, title, user_id, create_date, nickname FROM (SELECT LEVEL AS LVL, q.qna_id," +
						" q.parent_no, q.title, q.user_id, q.create_date, u.nickname FROM qna_tbl q, user_tbl u WHERE q.user_id=u.user_id START WITH q.parent_no=0" +
						" CONNECT BY PRIOR q.qna_id=q.parent_no ORDER SIBLINGS BY q.qna_id DESC))" +
						" WHERE recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10"; 
				System.out.println(query);
				pstmt=conn.prepareStatement(query);
				pstmt.setInt(1, section);
				pstmt.setInt(2, pageNum);
				pstmt.setInt(3, section);
				pstmt.setInt(4, pageNum);
			}
			else {
				// 일반유저가 Q&A 리스트를 조회 했을 때
				String query="SELECT * FROM(SELECT ROWNUM AS recNum, LVL, qna_id," +
						" parent_no, title, user_id, create_date, nickname FROM (SELECT LEVEL AS LVL, q.qna_id," +
						" q.parent_no, q.title, q.user_id, q.create_date, u.nickname FROM qna_tbl q, user_tbl u"+
						" WHERE q.user_id=u.user_id AND q.user_id = ? OR q.parent_no IN"+
						" (SELECT qna_id FROM qna_tbl WHERE user_id=?)"+ 
						" START WITH q.parent_no=0" +
						" CONNECT BY PRIOR q.qna_id=q.parent_no ORDER SIBLINGS BY q.qna_id DESC))" +
						" WHERE recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10"; 
				System.out.println(query);
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, param_user_id);
				pstmt.setString(2, param_user_id);
				pstmt.setInt(3, section);
				pstmt.setInt(4, pageNum);
				pstmt.setInt(5, section);
				pstmt.setInt(6, pageNum);
			}
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int level=rs.getInt("LVL");
				int qna_id=rs.getInt("qna_id");
				int parent_no=rs.getInt("parent_no");
				String title=rs.getString("title");
				String user_id = rs.getString("user_id");
				String nickname=rs.getString("nickname");
				Date create_datersDate=rs.getDate("create_date");
				QnaVO qnaVO=new QnaVO();
				qnaVO.setLevel(level);
				qnaVO.setQna_id(qna_id);
				qnaVO.setParent_no(parent_no);
				qnaVO.setTitle(title);
				qnaVO.getUserVO().setNickname(nickname);
				qnaVO.setUser_id(user_id);
				qnaVO.setCreate_date(create_datersDate);
				System.out.println(level);
				System.out.println("qnaVO="+qnaVO.toString()); 
				articlesList.add(qnaVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("글 목록 페이징 조회 중 에러");
			e.printStackTrace();
		}
		return articlesList;
	}
	
	//전체 글 목록 수
	public int selectAdminToArticles() {
		int totCount=0;
		try {
			conn=dataFactory.getConnection();
			String query="select count(*) from qna_tbl";
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				totCount=rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("전체 글 목록 수 처리중 에러");
			e.printStackTrace();
		}
		return totCount;
	}
	//
	public int selectUserToArticles(String user_id) {
		int totCount=0;
		try {
			conn=dataFactory.getConnection();
			String query="SELECT count(*) as cnt FROM qna_tbl WHERE user_id=? or";
			query+=" parent_no IN (SELECT qna_id FROM qna_tbl WHERE user_id=?)";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_id);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				totCount=rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("전체 글 목록 수 처리중 에러");
			e.printStackTrace();
		}
		return totCount;
	}
	
	//글 목록 조회 메서드
	public List<QnaVO> selectAllQna() {
		List<QnaVO> qnasList=new ArrayList<QnaVO>();
		try {
			conn=dataFactory.getConnection();
			String query="SELECT qna_id, q.user_id, u.nickname, parent_no, title, content, create_date, case when q.qna_id = q.parent_no THEN 0 ELSE 1 END as depth FROM qna_tbl q JOIN user_tbl u ON q.user_id = u.user_id ORDER BY create_date DESC";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			
			//번호, 사용자 이름, 제목, 작성일
			
			while (rs.next()) {
				int qna_id=rs.getInt("qna_id");
				String user_id=rs.getString("user_id");
				String nickname=rs.getString("nickname");
				int parent_no=rs.getInt("parent_no");
				String title=rs.getString("title");
				String content=rs.getString("content");
				Date create_date=rs.getDate("create_date");
				int level = rs.getInt("depth");
				
				QnaVO qnaVO=new QnaVO();
				qnaVO.setQna_id(qna_id);
				qnaVO.setUser_id(user_id);
				qnaVO.setNickname(nickname);
				qnaVO.setParent_no(parent_no);
				qnaVO.setTitle(title);
				qnaVO.setContent(content);
				qnaVO.setCreate_date(create_date);
				qnaVO.setLevel(level);
				qnaVO.setLevel(qna_id == parent_no ? 0 : 1);
				qnasList.add(qnaVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("글 목록 조회 중 에러");
			e.printStackTrace();
		}
		return qnasList;
	}
	
	
	//한페이지 당 몇개씩 불러올 건지
	public List<QnaVO> selectPagesQnas(int page) {
		List<QnaVO> qnasList=new ArrayList<QnaVO>();
		try {
			conn=dataFactory.getConnection();
			String query=""
					+ " SELECT qna_id, q.user_id, u.nickname, parent_no, title, content, create_date, case when q.qna_id = q.parent_no THEN 0 ELSE 1 END as depth "
					+ " FROM qna_tbl q "
					+ " JOIN user_tbl u ON q.user_id = u.user_id "
					+ " ORDER BY create_date DESC, qna_id DESC "
					+ " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
			
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, page * PAGE_ROW_SIZE);
			pstmt.setInt(2, PAGE_ROW_SIZE);
			ResultSet rs = pstmt.executeQuery();
			
			//번호, 사용자 이름, 제목, 작성일
			
			int cnt = 0;
			
			while (rs.next()) {
				int qna_id=rs.getInt("qna_id");
				String user_id=rs.getString("user_id");
				String nickname=rs.getString("nickname");
				int parent_no=rs.getInt("parent_no");
				String title=rs.getString("title");
				String content=rs.getString("content");
				Date create_date=rs.getDate("create_date");
				int level = rs.getInt("depth");
				
				QnaVO qnaVO=new QnaVO();
				qnaVO.setQna_id(qna_id);
				qnaVO.setUser_id(user_id);
				qnaVO.setNickname(nickname);
				qnaVO.setParent_no(parent_no);
				qnaVO.setTitle(title);
				qnaVO.setContent(content);
				qnaVO.setCreate_date(create_date);
				qnaVO.setLevel(level);
				qnaVO.setLevel(qna_id == parent_no ? 0 : 1);
				qnasList.add(qnaVO);
				cnt++;
			}
			System.out.println("행의 총 갯수 = " + cnt);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("글 목록 조회 중 에러");
			e.printStackTrace();
		}
		return qnasList;
	}
	
	//새글 추가하는 메서드
	public void insertNewArticle(QnaVO qnaVO) {
		try {
			conn=dataFactory.getConnection();
			int parent_no=qnaVO.getParent_no();
			int article_no=qnaVO.getQna_id();
			String title=qnaVO.getTitle();
			String content=qnaVO.getContent();
			String qna_img=qnaVO.getQna_img();
			String user_id=qnaVO.getUser_id();
			String query="insert into qna_tbl (qna_id, parent_no, title, content, user_id) values(?,?,?,?,?)";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, article_no);
			pstmt.setInt(2, parent_no);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, user_id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("새글 추가중 에러");
			e.printStackTrace();
		}
	}

	//글 내용 보는 메서드
	public QnaVO selectArticle(int qna_id) {
		QnaVO qnaVO = new QnaVO();
		try {
			conn=dataFactory.getConnection();
			String query="SELECT qna_id, q.user_id, u.nickname, parent_no, title, content, create_date, u.user_img " + 
						" FROM qna_tbl q " +
						" JOIN user_tbl u ON q.user_id = u.user_id " + 
						" WHERE q.qna_id = ? " +
						" ORDER BY create_date DESC";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, qna_id);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			int _qna_id=rs.getInt("qna_id");
			String user_id=rs.getString("user_id");
			String nickname=rs.getString("nickname");
			int parent_no=rs.getInt("parent_no");
			String title=rs.getString("title");
			String content=rs.getString("content");
			String user_img=rs.getString("user_img");
			// 좀 당황스러운데
			//String qna_img = null;
			//if (rs.getString("qna_img") != null) {
			//	qna_img=URLDecoder.decode(rs.getString("qna_img"),"utf-8");
			//}
			
			Date create_date=rs.getDate("create_date");
			qnaVO.setQna_id(_qna_id);
			qnaVO.setUser_id(user_id);
			qnaVO.getUserVO().setNickname(nickname);
			qnaVO.getUserVO().setUser_img(user_img);
			qnaVO.setTitle(title);
			qnaVO.setContent(content);
			//qnaVO.setQna_img(qna_img);
			qnaVO.setCreate_date(create_date);
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("글 상세 구현 중 에러");
			e.printStackTrace();
		}			
		return qnaVO;
	}
	
	//글번호 생성 메서드
	public int getNewArticleNo() {
		int _qna_id=1;
		try {
			conn=dataFactory.getConnection();
			String query="select max(qna_id) from qna_tbl";
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				_qna_id=rs.getInt(1)+1;
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("글 번호 생성중 에러");
			e.printStackTrace();
		}
		return _qna_id;
	}
	

	
	//글 수정하기 메서드
	public void updateArticle(QnaVO qnaVO) {
		String title=qnaVO.getTitle();
		String content=qnaVO.getContent();
		int qna_id=qnaVO.getQna_id();
		try {
			conn=dataFactory.getConnection();
			String query="UPDATE qna_tbl set title=?, content=? WHERE qna_id=?";
			System.out.println(query);
			System.out.println(title);
			System.out.println(content);
			System.out.println(qna_id);
			pstmt=conn.prepareStatement(query);
			System.out.println(1);
			pstmt.setString(1, title);
			System.out.println(2);
			pstmt.setString(2, content);
			System.out.println(3);
			pstmt.setInt(3, qna_id);
			System.out.println(4);
			pstmt.executeUpdate();
			System.out.println(5);
			pstmt.close();
			System.out.println(6);
			conn.close();
			System.out.println(7);
		}catch (Exception e) {
			System.out.println("글 수정 중 에러");
			e.printStackTrace();
		}
	}
	
	//검색할 총 글 개수
	public int searchTotArticles(String filter, String keyword,boolean isAdmin, String user_id) {
		int totArticles=0;
		String error=null;
		try {
			conn=dataFactory.getConnection();
			if(isAdmin) { // 관리자 일떄
				error="admin";
				String query="SELECT COUNT(*) FROM qna_tbl";
				if(filter.equals("title")) {
					query+=" WHERE title LIKE ?";
				}else if(filter.equals("content")) {
					query+=" WHERE content LIKE ?";
				}else if(filter.equals("writer")) {
					query+=" q, user_tbl u WHERE u.user_id=q.user_id"+
						" AND u.nickname LIKE ?";
				}
				System.out.println(query);
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, "%"+keyword+"%");

			} else {
				error="user";
				String query="SELECT count(*) as cnt FROM"+
						" (SELECT ROWNUM AS recNUM, LVL, qna_id, parent_no, title, user_id, create_date, nickname, content"+
						" FROM (SELECT LEVEL as LVL, q.qna_id, q.parent_no, q.title, q.user_id, q.create_date, u.nickname, q.content"+
						" FROM qna_tbl q, user_tbl u WHERE q.user_id=u.user_id AND q.user_id=? OR q.parent_no IN"+
						" (SELECT q.qna_id FROM qna_tbl q, user_tbl u WHERE q.user_id=?";  
				if(filter.equals("title")) {
					query+=") START WITH q.parent_no=0 CONNECT BY PRIOR q.qna_id=q.parent_no ORDER SIBLINGS BY q.qna_id DESC))";
					query+=" WHERE title LIKE ?";

				} else if(filter.equals("content")) {
					query+=") START WITH q.parent_no=0 CONNECT BY PRIOR q.qna_id=q.parent_no ORDER SIBLINGS BY q.qna_id DESC))";
					query+=" WHERE content LIKE ?";
				} else if(filter.equals("writer")) {
					query+=") START WITH q.parent_no=0 CONNECT BY PRIOR q.qna_id=q.parent_no ORDER SIBLINGS BY q.qna_id DESC))";
					query+=" WHERE nickname LIKE ?";
				}
				System.out.println(query);
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, user_id);
				pstmt.setString(2, user_id);
				pstmt.setString(3, "%"+keyword+"%");
				
			}
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				totArticles=rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(error+"검색한 총글 개수 구하는 중 에러");
			e.printStackTrace();
		}
		return totArticles;
	}
	
	//검색한 글 목록
	public List<QnaVO> searchArticlesAdmin(Map<String, Integer>pagingMap, String filter, String keyword,boolean isAdmin,String user_id){
		List<QnaVO> articlesList=new ArrayList<>();
		int section=pagingMap.get("section");
		int pageNum=pagingMap.get("pageNum");
		String error=null;
		try {
			conn=dataFactory.getConnection();
			
			if(isAdmin) {
				error="admin";
				String query="SELECT * FROM (SELECT ROWNUM AS recNUM, LVL, qna_id," +
						" parent_no, title, user_id, create_date, nickname, content FROM"+
						" (SELECT LEVEL AS LVL, q.qna_id, q.parent_no, q.title, q.user_id,q.create_date, u.nickname, q.content"+
						" FROM qna_tbl q, user_tbl u WHERE q.user_id=u.user_id"+
						" START WITH q.parent_no=0 CONNECT BY PRIOR q.qna_id=q.parent_no ORDER SIBLINGS BY q.qna_id DESC))";
				if(filter.equals("title")) {
					query+=" WHERE recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10 AND title LIKE ?";
				} else if(filter.equals("content")) {
					query+=" WHERE recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10 AND content LIKE ?";

				}else if(filter.equals("writer")) {
					query+=" WHERE recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10 AND nickname LIKE ?";
				}
				System.out.println(query);
				pstmt=conn.prepareStatement(query);
				pstmt.setInt(1, section);
				pstmt.setInt(2, pageNum);
				pstmt.setInt(3, section);
				pstmt.setInt(4, pageNum);
				pstmt.setString(5, "%"+keyword+"%");

			} else {
				error="user";
				String query="SELECT * FROM (SELECT ROWNUM AS recNum, LVL, qna_id, parent_no, title, user_id, create_date, nickname, content"+
						" FROM (SELECT LEVEL AS LVL, q.qna_id, q.parent_no, q.title, q.user_id, q.create_date, u.nickname, q.content "+
						" FROM qna_tbl q, user_tbl u WHERE q.user_id=u.user_id AND q.user_id = ? or q.parent_no in ";
						
				if(filter.equals("title")) {
					query+="(SELECT qna_id FROM qna_tbl WHERE user_id=?)"+
						" START WITH q.parent_no=0 CONNECT BY PRIOR q.qna_id=q.parent_no ORDER SIBLINGS BY q.qna_id DESC))"+
						" WHERE recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10 AND title LIKE ?";

				} else if(filter.equals("content")) {
					query+="(SELECT qna_id FROM qna_tbl WHERE user_id=?)"+
							" START WITH q.parent_no=0 CONNECT BY PRIOR q.qna_id=q.parent_no ORDER SIBLINGS BY q.qna_id DESC))"+
							" WHERE recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10 AND CONTENT LIKE ?";
						
					
				} else if(filter.equals("writer")) {
		    		query+=" (SELECT q.qna_id FROM qna_tbl q, user_tbl u WHERE q.user_id=? AND"+
		    			" u.user_id=q.user_id)"+
		    			" START WITH q.parent_no=0 CONNECT BY PRIOR q.qna_id=q.parent_no ORDER SIBLINGS BY q.qna_id DESC))"+
		    			" WHERE recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10 AND nickname LIKE ?";
				}	
				
				System.out.println(query);
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, user_id);
				pstmt.setString(2, user_id);
				pstmt.setInt(3, section);
				pstmt.setInt(4, pageNum);
				pstmt.setInt(5, section);
				pstmt.setInt(6, pageNum);
				pstmt.setString(7, "%"+keyword+"%");
			}
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				int level=rs.getInt("LVL");
				int qna_id=rs.getInt("qna_id");
				int parent_no=rs.getInt("parent_no");
				String title=rs.getString("title");
				String _user_id = rs.getString("user_id");
				String nickname=rs.getString("nickname");
				Date create_datersDate=rs.getDate("create_date");
				QnaVO qnaVO=new QnaVO();
				qnaVO.setLevel(level);
				qnaVO.setQna_id(qna_id);
				qnaVO.setParent_no(parent_no);
				qnaVO.setTitle(title);
				qnaVO.getUserVO().setNickname(nickname);
				qnaVO.setUser_id(_user_id);
				qnaVO.setCreate_date(create_datersDate);
				System.out.println(level);
				System.out.println("qnaVO="+qnaVO.toString()); 
				articlesList.add(qnaVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println(error+"검색한 결과 구하는 중 에러");
			e.printStackTrace();
		}
		return articlesList;
	}
	
	//삭제할 글 번호 가져오기
	public List<Integer> selectRemovedArticles(int qna_id) {
		List<Integer> qnaList=new ArrayList<Integer>();
		try {
			conn=dataFactory.getConnection();
			String query="SELECT qna_id FROM qna_tbl START WITH qna_id=? CONNECT BY PRIOR qna_id=parent_no";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, qna_id);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				qna_id=rs.getInt("qna_id");
				qnaList.add(qna_id);
			}
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("삭제할 글 번호 목록 가져오기 중 에러");
			e.printStackTrace();
		}
		return qnaList;
	}
	
	//글삭제 하기
	public void deleteArticle(int qna_id) {
		try {
			conn=dataFactory.getConnection();
			String query="DELETE FROM qna_tbl WHERE qna_id in(SELECT qna_id FROM qna_tbl START WITH qna_id=? CONNECT BY PRIOR qna_id=parent_no)";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, qna_id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("글 삭제중 에러");
			e.printStackTrace();
		}
	}
}