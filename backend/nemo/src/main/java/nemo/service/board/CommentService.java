package nemo.service.board;

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
	public void addComment(String user_id, int group_id, int article_no, String com_cont, int parent_no) {
		commentDAO.insertNewComment(user_id, group_id, article_no,com_cont, parent_no);
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
	
	//댓글 삭제
	public boolean deleteComment(int comment_no, int article_no) {
		boolean check = commentDAO.checkComChild(comment_no);
		if(!check) {
			System.out.println("deleteComment : 여기 오나 ?"+check);
			commentDAO.deleteComment(comment_no);
			boardDAO.countComment(article_no);
		}
		System.out.println("deleteComment : if문 밖임"+check);
		return check;
	}

}
