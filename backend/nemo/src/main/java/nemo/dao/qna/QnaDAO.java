package nemo.dao.qna;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
	//전체 글 목록 수
	public int selectToArticles() {
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
	
	//글 목록 조회 메서드
	public List<QnaVO> selectAllQnas() {
		List<QnaVO> qnasList=new ArrayList<QnaVO>();
		try {
			conn=dataFactory.getConnection();
			String query="SELECT qna_id, q.user_id, u.nickname, parent_no, title, content, create_date FROM qna_tbl q JOIN user_tbl u ON q.user_id = u.user_id ORDER BY create_date DESC";
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
				
				QnaVO qnaVO=new QnaVO();
				qnaVO.setQna_id(qna_id);
				qnaVO.setUser_id(user_id);
				qnaVO.setNickname(nickname);
				qnaVO.setParent_no(parent_no);
				qnaVO.setTitle(title);
				qnaVO.setContent(content);
				qnaVO.setCreate_date(create_date);
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
	
	
	//글 목록 조회 메서드
	public List<QnaVO> selectPagesQnas(int page) {
		List<QnaVO> qnasList=new ArrayList<QnaVO>();
		try {
			conn=dataFactory.getConnection();
			String query=""
					+ " SELECT qna_id, q.user_id, u.nickname, parent_no, title, content, create_date "
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
				
				QnaVO qnaVO=new QnaVO();
				qnaVO.setQna_id(qna_id);
				qnaVO.setUser_id(user_id);
				qnaVO.setNickname(nickname);
				qnaVO.setParent_no(parent_no);
				qnaVO.setTitle(title);
				qnaVO.setContent(content);
				qnaVO.setCreate_date(create_date);
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
	

	//글 목록 조회 메서드
	public QnaVO selectOneQna(int qna_id) {
		QnaVO qnaVo = new QnaVO();
		
		
		
		
		return qnaVo;
	}
}
