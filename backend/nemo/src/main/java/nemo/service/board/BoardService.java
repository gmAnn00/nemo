package nemo.service.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nemo.dao.board.BoardDAO;
import nemo.vo.board.ArticleVO;
import nemo.vo.board.BoardVO;
import nemo.vo.board.CommentVO;
import nemo.vo.group.*;
import nemo.dao.group.*;
public class BoardService {
	BoardDAO boardDAO;
	GroupInfoDAO groupDAO;
		
		public BoardService() {
			boardDAO=new BoardDAO();
			groupDAO=new GroupInfoDAO();
		}
		
		
		//게시글 목록 메소드
		public Map listArticles(Map<String, Integer> pagingMap, int group_id, String user_id) {
			boolean isMem=boardDAO.isMember(user_id, group_id);
			Map articleMap=new HashMap<>();

			if(isMem) {
				List<BoardVO> articlesList=boardDAO.selectGrpArticles(pagingMap, group_id); // 10개 끊은 자료를 갖고옴
				GroupVO groupVO=groupDAO.selectGroupById(group_id);

				// 총 글 개수를 넘겨 받을 것임 -> 페이징 처리를 하기 위해 총 글 개수 필요
				int totArticles=boardDAO.selectToArticles(group_id);
				articleMap.put("articlesList",articlesList);
				articleMap.put("totArticles", totArticles);
				//articleMap.put("group_id", group_id);
				articleMap.put("group", groupVO);
				System.out.println("소모임"+articleMap.get("group_id"));
				//List<BoardVO> list = (List)articleMap.get("articlesList");
				//BoardVO boardVO = list.get(0);
				//System.out.println("닉네임"+ boardVO.getUserVO().getNickname());
			}
			return articleMap;		
		} // End of 게시글 목록
		
		//게시글 검색
		public Map serchArticles(Map<String, Integer> pagingMap, int group_id, String user_id, String filter, String keyword) {
			boolean isMem=boardDAO.isMember(user_id, group_id);
			Map articleMap=new HashMap<>();
			int totArticles=0;

			if(isMem) {
				
				List<BoardVO> articlesList=new ArrayList<BoardVO>();
				
				articlesList=boardDAO.selectByKeyword(pagingMap, group_id, filter, keyword); // 10개 끊은 자료를 갖고옴
				totArticles=boardDAO.searchTotArticles(group_id, filter, keyword);

				GroupVO groupVO=groupDAO.selectGroupById(group_id);
				
				// 총 글 개수를 넘겨 받을 것임 -> 페이징 처리를 하기 위해 총 글 개수 필요
				articleMap.put("articlesList",articlesList);
				articleMap.put("totArticles", totArticles);
				articleMap.put("group", groupVO);
				
				System.out.println("소모임 글 검색"+groupVO.getGrp_id());
				System.out.println("검색 글개수4:"+totArticles);
			}
			return articleMap;	
		}
		
		//게시글 상세보기 처리하는 서비스 메소드
		public Map viewArticle(int grp_id, int article_no, String user_id) {
			boolean isMem = boardDAO.isMember(user_id, grp_id);
			Map viewArticle=new HashMap<>();

			if(isMem) {
				BoardVO article = new BoardVO();
				GroupVO groupVO=groupDAO.selectGroupById(grp_id);
				
				List<CommentVO> comments = new ArrayList<CommentVO>();
				boolean isSame=boardDAO.isSameUser(user_id, article_no);
				if(!isSame) {
					boardDAO.addViewCnt(article_no);
				}
				article=boardDAO.selectArticle(article_no);
				comments=boardDAO.selectComments(article_no);
				
				viewArticle.put("article", article);
				viewArticle.put("comments", comments);
				viewArticle.put("isSame",isSame);
				viewArticle.put("group", groupVO);
				//viewArticle.put("group_id", grp_id);
				
			} 
			return viewArticle;
			
		}//End of viewArticle

}
