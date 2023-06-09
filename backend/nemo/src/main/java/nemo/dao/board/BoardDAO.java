package nemo.dao.board;

import nemo.vo.common.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import nemo.vo.board.*;

public class BoardDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	
	private DataSource datafactory;
	
	public BoardDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			datafactory=(DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("BoardDAO 연결 오류");
			e.printStackTrace();
		}
	}
	
	//게시글 리스트(목록보기)
	
	
	//게시글 작성
	
	//게시글 조회 
	
	//게시글 삭제
	
	//댓글 작성
	
	//댓글 삭제
	
	//게시글 검색
}
