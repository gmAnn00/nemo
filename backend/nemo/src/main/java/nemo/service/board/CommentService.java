package nemo.service.board;

import java.util.HashMap;
import java.util.Map;

import nemo.dao.board.BoardDAO;
import nemo.dao.board.CommentDAO;
import nemo.vo.board.CommentVO;

public class CommentService {
	BoardDAO boardDAO;
	CommentDAO commentDAO;
	
	public CommentService() {
		boardDAO=new BoardDAO();
		commentDAO= new CommentDAO();
	}
	//댓글 등록
	public Map addComment(String user_id, int group_id, int article_no, String com_cont, int parent_no) {
		Map commentInfo=new HashMap();
		CommentVO commentVO=new CommentVO();
		if(parent_no!=0) {
			parent_no=commentDAO.getParentNo(parent_no);
			
		}
		int appendLocation=commentDAO.getMaxCommentNo(parent_no);
		int comment_no=commentDAO.insertNewComment(user_id, group_id, article_no,com_cont, parent_no);
	
		//한개의 정보를 받아오는 메소드 호출
		commentVO=commentDAO.selectComment(comment_no);
		//댓글 개수 정보 가져오기
		int comCnt=commentDAO.getCommentCnt(article_no);
		
		commentInfo.put("commentVO", commentVO);
		commentInfo.put("com_cnt", comCnt);
		commentInfo.put("appendLocation", appendLocation);
		System.out.println("ㅇㅕ기 서비스"+((CommentVO)commentInfo.get("commentVO")).getArticle_no());
		
		return commentInfo;
	}
	
	//대댓 등록
	
	//한개의 댓글 정보를 받아오는 서비스 
	public CommentVO getCommentInfo(int group_id, int comment_no, String user_id) {
		//Map commentInfo=new HashMap();
		CommentVO comment=new CommentVO();
		boolean isSameUser=commentDAO.isSameUserC(user_id, comment_no);
		if(isSameUser) {
			comment=commentDAO.selectComment(comment_no);
			//commentInfo.put("comment", comment);
		} else {
			return null;
		}
		return comment;
	}
	
	//댓글 수정취소시 댓글 내용을 받아오는 서비스
	public String getCommentCont(int comment_no) {
		String com_cont=commentDAO.selectCommentCont(comment_no);
		
		return com_cont;
	}
	
	//댓글 수정 
	public void modComment(int comment_no,String com_cont) {
		commentDAO.updateComment(comment_no,com_cont);
	}
	
	//댓글 삭제
	public boolean deleteComment(int comment_no, int article_no) {
		boolean check = commentDAO.checkComChild(comment_no);
		if(!check) {
			System.out.println("deleteComment : 여기 오나 ?"+check);
			commentDAO.deleteComment(comment_no);
			boardDAO.updateCommentCnt(article_no);
		}
		System.out.println("deleteComment : if문 밖임"+check);
		return check;
	}

}
