
package nemo.controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.dao.board.BoardDAO;
import nemo.service.board.BoardService;
import nemo.vo.board.BoardVO;
import nemo.vo.board.CommentVO;


@WebServlet("/group/board/*")
public class BoardController extends HttpServlet {
	BoardService boardService;
	BoardVO boardVO;
	CommentVO commentVO;
	HttpSession session;
	
	public void init(ServletConfig config) throws ServletException {
		boardService=new BoardService();
		boardVO=new BoardVO();
		commentVO = new CommentVO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	public void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out=response.getWriter();
		
		String action=request.getPathInfo();	//요청명 가져오는 메소드 
		System.out.println("요청 매핑이름: " + action); //요청명 출력
		
		
		String nextPage=null;		
		session=request.getSession();
		
		//String user_id=(String)session.getAttribute("user_id");
		String user_id="hong";
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		
		//게시판 리스트 보기 
		if(action==null || action.equals("")||action.equals("/board")) {
			
			String _section=request.getParameter("section");
			String _pageNum=request.getParameter("pageNum");
			int section=Integer.parseInt((_section==null)? "1":_section);
			int pageNum=Integer.parseInt((_pageNum==null)? "1":_pageNum);
			
			Map<String, Integer> pagingMap=new HashMap<String, Integer>();
			pagingMap.put("section", section);
			pagingMap.put("pageNum", pageNum);
			
			// 글 리스트 뿐만 아니라 section과 page까지 담아 올려고 Map을 사용함
			Map articleMap=boardService.listArticles(pagingMap, group_id, user_id);
				
	
			if(!(articleMap.isEmpty())) {
				articleMap.put("section", section);
				articleMap.put("pageNum", pageNum);
				
				request.setAttribute("articleMap", articleMap);
				nextPage="/views/group/board.jsp";
				//System.out.println("소모임"+articleMap.get("group_id"));

			} else {
				out.print("<script>alert('잘못된 접근입니다.'); location.href='/nemo/index'</script>");
				return;
			}
			
		} else if(action.equals("/search")){
			String filter=request.getParameter("filter");
			String keyword=request.getParameter("keyword");
			
			String _section=request.getParameter("section");
			String _pageNum=request.getParameter("pageNum");
			
			int section=Integer.parseInt((_section==null)? "1":_section);
			int pageNum=Integer.parseInt((_pageNum==null)? "1":_pageNum);
			
			Map<String, Integer> pagingMap=new HashMap<String, Integer>();
			pagingMap.put("section", section);
			pagingMap.put("pageNum", pageNum);
			
			// 글 리스트 뿐만 아니라 section과 page까지 담아 올려고 Map을 사용함
			Map articleMap=boardService.serchArticles(pagingMap, group_id, user_id, filter, keyword);
			
			if(!(articleMap.isEmpty())) {
				articleMap.put("filter", filter);
				articleMap.put("keyword", keyword);
				articleMap.put("section", section);
				articleMap.put("pageNum", pageNum);
				
				request.setAttribute("articleMap", articleMap);
				nextPage="/views/group/board.jsp";

			} else {
				out.print("<script>alert('잘못된 접근입니다.'); location.href='/nemo/index'</script>");
				return;
			}
			
			
		}else if(action.equals("/viewArticle")) {	// 글 상세보기
			
			int article_no = Integer.parseInt(request.getParameter("article_no"));
			Map articleViewMap=boardService.viewArticle(group_id, article_no, user_id);
			
			if(!(articleViewMap.isEmpty())) {
				
				request.setAttribute("articleViewMap", articleViewMap);
				nextPage="/views/group/boardView.jsp";
			}else {
				out.print("<script>alert('잘못된 접근입니다.'); location.href='/nemo/index'</script>");
				//out.print("<script> window.onload");
				
				return;
			}
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);	
		
	}
}
