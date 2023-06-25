package nemo.service.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nemo.dao.board.BoardDAO;
import nemo.dao.board.CommentDAO;
import nemo.dao.common.UserDAO;
import nemo.dao.group.GroupInfoDAO;
import nemo.dao.group.JoinGroupDAO;
import nemo.vo.board.BoardVO;
import nemo.vo.board.CommentVO;
import nemo.vo.group.GroupVO;

public class BoardService {
	BoardDAO boardDAO;
	GroupInfoDAO groupDAO;
	CommentDAO commentDAO;
	UserDAO userDAO;
		
	public BoardService() {
		boardDAO=new BoardDAO();
		commentDAO=new CommentDAO();
		groupDAO=new GroupInfoDAO();
		userDAO=new UserDAO();
		
	}
	
	//게시글 목록 메소드
	public Map listArticles(Map<String, Integer> pagingMap, int group_id, String user_id) {
		boolean isMem=boardDAO.isMember(user_id, group_id);
		Map articleMap=new HashMap<>();

		//if(isMem||userDAO.checkAdmin(user_id)) {
			List<BoardVO> articlesList=boardDAO.selectGrpArticles(pagingMap, group_id); // 10개 끊은 자료를 갖고옴
			//GroupVO groupVO=groupDAO.selectGroupById(group_id);
			//int currentMemNo=groupDAO.selectGroupNumById(group_id);
			// 총 글 개수를 넘겨 받을 것임 -> 페이징 처리를 하기 위해 총 글 개수 필요
			
			List<BoardVO> noticeList=boardDAO.selectNoticeList(group_id);
			
			int totArticles=boardDAO.selectToArticles(group_id);
			
			articleMap.put("articlesList",articlesList);
			articleMap.put("totArticles", totArticles);
			articleMap.put("noticeList", noticeList);
			//articleMap.put("group_id", group_id);
			
			//articleMap.put("group", groupVO);
			//articleMap.put("currentMemNo", currentMemNo);
			
		//}
		return articleMap;		
	} // End of 게시글 목록
	
	//게시글 검색
	public Map serchArticles(Map<String, Integer> pagingMap, int group_id, String user_id, String filter, String keyword) {
		boolean isMem=boardDAO.isMember(user_id, group_id);
		Map articleMap=new HashMap<>();
		int totArticles=0;

		//if(isMem||userDAO.checkAdmin(user_id)) {
			
			List<BoardVO> articlesList=new ArrayList<BoardVO>();
			
			articlesList=boardDAO.selectByKeyword(pagingMap, group_id, filter, keyword); // 10개 끊은 자료를 갖고옴
			totArticles=boardDAO.searchTotArticles(group_id, filter, keyword);

			//GroupVO groupVO=groupDAO.selectGroupById(group_id);
			
			// 총 글 개수를 넘겨 받을 것임 -> 페이징 처리를 하기 위해 총 글 개수 필요
			articleMap.put("articlesList",articlesList);
			articleMap.put("totArticles", totArticles);
			//articleMap.put("group", groupVO);
			
			//System.out.println("소모임 글 검색"+groupVO.getGrp_id());
			System.out.println("검색 글개수4:"+totArticles);
		//}
		return articleMap;	
	}
	
	//게시글 상세보기 처리하는 서비스 메소드
	public Map viewArticle(int grp_id, int article_no, String user_id) {
		//나중에 
		boolean isMem = boardDAO.isMember(user_id, grp_id);
		Map viewArticle=new HashMap<>();

		//if(isMem||userDAO.checkAdmin(user_id)) {
			BoardVO article = new BoardVO();
			//GroupVO groupVO=groupDAO.selectGroupById(grp_id);
			
			List<CommentVO> comments = new ArrayList<CommentVO>();
			boolean isSame=boardDAO.isSameUser(user_id, article_no);
			if(!isSame) {
				boardDAO.addViewCnt(article_no);
			}
			article=boardDAO.selectArticle(article_no);
			comments=commentDAO.selectComments(article_no);
			viewArticle.put("article", article);
			viewArticle.put("comments", comments);
			//viewArticle.put("isSame",isSame);
			//viewArticle.put("group", groupVO);
			//viewArticle.put("group_id", grp_id);
			
		//} 
		return viewArticle;
		
	}//End of viewArticle
	
	
	//게시글 등록하는 메소드
	public void addArticle(BoardVO boardVO, String _brackets) {	
		if(_brackets.equals("notice")) {
			_brackets="공지";
		}else if(_brackets.equals("freeArticle")) {
			_brackets="자유";
		}else if(_brackets.equals("afterMeeting")) {
			_brackets="후기";
		}
		boardVO.setBrackets(_brackets);
		//멤버인지체크해야함;;
		boardDAO.insertNewArticle(boardVO);
	}//End of addArticle
	
	//한개의 게시글 정보를 받아오는 서비스
	public BoardVO getArticleInfo(int group_id, int article_no, String user_id) {
		//Map articleInfo=new HashMap();
		BoardVO article=new BoardVO();
		boolean isSameUser=boardDAO.isSameUser(user_id, article_no);
		if(isSameUser) {
			article=boardDAO.selectArticle(article_no);
			//articleInfo.put("article", articleVO);
			//articleInfo.put("", articleVO)
		} else {
			return null;
		}
		return article;
	}
	
	/*
	//한개의 댓글 정보를 받아오는 서비스 
	public CommentVO getCommentInfo(int group_id, int comment_no, String user_id) {
		//Map commentInfo=new HashMap();
		CommentVO comment=new CommentVO();
		boolean isSameUser=boardDAO.isSameUserC(user_id, comment_no);
		if(isSameUser) {
			comment=boardDAO.selectComment(comment_no);
			//commentInfo.put("comment", comment);
		} else {
			return null;
		}
		return comment;
	}
	*/
	//그룹 정보 요청하는 서비스
	public Map getGroupInfo(int group_id) {
		Map groupInfo=new HashMap();
		GroupVO groupVO=new GroupVO();
		groupVO=groupDAO.selectGroupById(group_id);
		int currentMemNo=groupDAO.selectGroupNumById(group_id);
		System.out.println(groupVO.getGrp_mng());
		groupInfo.put("groupVO", groupVO);
		groupInfo.put("currentMemNo", currentMemNo);
		return groupInfo;
	}
	
	//컨텐츠 삭제하는 메소드
	public void deleteArticle(int article_no) {
		
	}
	
	public boolean checkAdmin(String user_id) {
		boolean isAdmin=false;
		isAdmin=userDAO.checkAdmin(user_id);
		return isAdmin;
	} 
	
	public boolean isMember(String user_id, int group_id) {
		boolean isMem=false;
		isMem=boardDAO.isMember(user_id, group_id);
		return isMem;
	}

	public boolean isAuthorized(String user_id,int group_id) {
		return (boardDAO.isMember(user_id, group_id)||userDAO.checkAdmin(user_id));
	}
	
	/*
	//댓글 삭제
	public boolean deleteComment(int comment_no, int article_no) {
		boolean check = boardDAO.checkComChild(comment_no);
		if(!check) {
			System.out.println("deleteComment : 여기 오나 ?"+check);
			boardDAO.deleteComment(comment_no);
			boardDAO.countComment(article_no);
		}
		System.out.println("deleteComment : if문 밖임"+check);
		return check;
	}
*/
}
