package nemo.service.mypage;

import java.util.ArrayList;
import java.util.List;

import nemo.dao.mypage.MyBoardDAO;
import nemo.vo.mypage.MyBoardVO;

public class MyBoardService {
	MyBoardDAO myBoardDAO;
	
	public MyBoardService() {
		myBoardDAO = new MyBoardDAO();
	}
	
	//글정보를 가져오는 메서드
	public List<MyBoardVO> getMyArticleInfo(String user_id) {
		List<MyBoardVO> myArticleList = new ArrayList<>();
		myArticleList = myBoardDAO.selectMyArticle(user_id);
		return myArticleList;
	}
	//댓글정보를 가져오는 메서드
	public List<MyBoardVO> getMyCommentInfo(String user_id) {
		List<MyBoardVO> myCommentList = new ArrayList<MyBoardVO>();
		myCommentList = myBoardDAO.selectMyComment(user_id);
		return myCommentList;
	}
	

}
