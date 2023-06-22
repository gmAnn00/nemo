
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
import nemo.service.board.CommentService;
import nemo.vo.board.BoardVO;
import nemo.vo.board.CommentVO;
import nemo.vo.group.GroupVO;



@WebServlet("/group/board/*")
public class BoardController extends HttpServlet {
	BoardService boardService;
	CommentService commentService;
	BoardVO boardVO;
	CommentVO commentVO;
	Map groupInfo;
	HttpSession session;
	
	public void init(ServletConfig config) throws ServletException {
		boardService=new BoardService();
		commentService=new CommentService();
		boardVO=new BoardVO();
		commentVO = new CommentVO();
		groupInfo=new HashMap();
		
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
		String user_id=(String)session.getAttribute("user_id");
	
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		groupInfo=boardService.getGroupInfo(group_id);
		request.setAttribute("groupInfo", groupInfo);
		
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
			
		} else if(action.equals("/search")){ // 검색기능
			String filter=request.getParameter("filter");
			System.out.println("필터"+filter);
			String keyword=request.getParameter("keyword");
			System.out.println("키워드"+keyword);
			
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
			
		} else if(action.equals("/write")) { //글쓰는 페이지로이동 
			
			nextPage="/views/group/boardWrite.jsp";
			
		} else if(action.equals("/addArticle")) { 
			String _brackets=request.getParameter("brackets");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			System.out.println(content);
			
			boardVO.setTitle(title);
			//boardVO.setBrackets(brackets);
			boardVO.setUser_id(user_id);
			boardVO.setGrp_id(group_id);
			boardVO.setContent(content);
			boardService.addArticle(boardVO, _brackets);
			
			out.print("<script>alert('글이 등록되었습니다.')");
			//response.sendRedirect("/nemo/group/groupMain?group_id=1");
			
			nextPage="/nemo/group/board?group_id="+group_id;
			response.sendRedirect(nextPage);
			return;
			
		} else if(action.equals("/deleteArticle")) { 
			//게시글 정보를 가지고 게시글 삭제 페이지로
			String _article_no=request.getParameter("article_no");
			int article_no=Integer.parseInt(_article_no);
			BoardVO articleInfo=boardService.getArticleInfo(group_id, article_no, user_id);
			if(articleInfo!=null) {
				request.setAttribute("articleInfo", articleInfo);
				request.setAttribute("deleteInfo", "deleteArticle");
				nextPage="/views/group/boardDelete.jsp";
				System.out.println(articleInfo.getArticle_no());
			} else {
				out.print("<script>alert('잘못된 접근입니다.'); location.href='/nemo/index'</script>");
				return;
			}
			
		} else if(action.equals("/deleteComment")) {
			//댓글 정보를 가지고 댓글 삭제 페이지로
			String _comment_no=request.getParameter("comment_no");
			int comment_no=Integer.parseInt(_comment_no);
			//CommentVO commentInfo=boardService.getCommentInfo(group_id, comment_no,user_id);
			CommentVO commentInfo=commentService.getCommentInfo(group_id, comment_no,user_id);
			if(commentInfo !=null) {
				request.setAttribute("commentInfo", commentInfo);
				request.setAttribute("deleteInfo", "deleteComment");
				nextPage="/views/group/boardDelete.jsp";
			} else {
				out.print("<script>alert('잘못된 접근입니다.'); location.href='/nemo/index'</script>");
				return;
			}
			
		} else if(action.equals("/delete")) { //실제 삭제
			String msg=request.getParameter("deleteInfo");
			int num=Integer.parseInt(request.getParameter("number"));
	
			if(msg.equals("deleteArticle")) { //게시글 삭제시
				// 사진 폴더 삭제하는 작업 해야함
				boardService.deleteArticle(num);
				
				//number에는 article_no가 들어있음
			} else if(msg.equals("deleteComment")) { // 댓글 삭제
				String article_no=request.getParameter("article_no");
				int _article_no=Integer.parseInt(article_no);
				//boolean check = boardService.deleteComment(num,_article_no);
				boolean check=commentService.deleteComment(num, _article_no);
				if(!check) {
					out.print("<script>");
					out.print("alert('삭제 되었습니다.');");
					out.print("location.href='"+ request.getContextPath()+"/group/board/viewArticle?group_id="+group_id+"&article_no="+article_no+"';");
					out.print("</script>");
					return;
				} else {
					System.out.println("자식 있어 컨트롤러");
					out.print("<script>");
					out.print("alert('댓글이 달린 댓글은 삭제할 수 없습니다.');");
					out.print("location.href='"+request.getContextPath()+"/group/board/viewArticle?group_id="+group_id+"&article_no="+article_no+"';");
					out.print("</script>");
					return;
				}
			}	
		}else if(action.equals("/addComment")) {
			System.out.println("여기오나");
			String com_cont=request.getParameter("com_cont");
			int article_no=Integer.parseInt(request.getParameter("article_no"));
			int parent_no=Integer.parseInt(request.getParameter("parent_no"));
			
			System.out.println(article_no);
			System.out.println(com_cont);
			
			commentService.addComment(user_id, group_id, article_no, com_cont,parent_no);
			out.print("success");
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);	
		
	}
}