package nemo.controller.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.service.board.BoardService;
import nemo.vo.board.BoardVO;
import nemo.vo.board.CommentVO;


@WebServlet("/group/board")
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
		
		String action=request.getPathInfo();	//요청명 가져오는 메소드 
		System.out.println("요청 매핑이름: " + action); //요청명 출력 
		
		session=request.getSession();

		String nextPage=null;
		
		String user_id=(String) session.getAttribute("user_id");
		int grp_id=Integer.parseInt(request.getParameter("grp_id"));


		List<BoardVO> articlesList=new ArrayList<BoardVO>();

			nextPage="/group/board/board.jsp";
		
	}
}
