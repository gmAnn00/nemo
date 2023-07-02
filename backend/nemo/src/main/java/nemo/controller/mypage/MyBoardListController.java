package nemo.controller.mypage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.service.mypage.MyBoardService;
import nemo.vo.mypage.MyBoardVO;


@WebServlet("/mypage/myBoardList/*")
public class MyBoardListController extends HttpServlet {
	HttpSession session;
	MyBoardService myBoardService;	
	
	
	public void init() throws ServletException {
		myBoardService = new MyBoardService();
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
		session=request.getSession();
		String nextPage = "";
		PrintWriter out = response.getWriter();

		String action = request.getPathInfo();				
		String user_id = (String)session.getAttribute("user_id");

		//System.out.println("action="+action);
		
		if(user_id != null) {				
			//로그인 상태일때만 수행
			try {			
				// 내가 쓴 글, 댓글 조회	
				if(action == null || action.equals("/myBoardList")) {
				
					user_id = (String)session.getAttribute("user_id");
					//System.out.println("user_id =" + user_id);
					
					//내가 쓴 글정보 가져오는 메소드
					List<MyBoardVO> myArticleList = new ArrayList<>();
					myArticleList = myBoardService.getMyArticleInfo(user_id);
					
					//내가 쓴 댓글정보 가져오는 메소드
					List<MyBoardVO> myCommentList = new ArrayList<MyBoardVO>();
					myCommentList = myBoardService.getMyCommentInfo(user_id);
					
					//System.out.println("myArticleList = " + myArticleList);
					//System.out.println("myCommentList = " + myCommentList);
					
					request.setAttribute("myArticleList", myArticleList);
					request.setAttribute("myCommentList", myCommentList);
					
					
					nextPage="/views/mypage/myBoardList.jsp";
					
					RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
					dispatcher.forward(request, response);
					
				} else if(action.equals("/delArticle")) {				
					//내 작성글 삭제
					String group_id = request.getParameter("group_id");
					int article_no = Integer.parseInt(request.getParameter("article_no"));
					
					myBoardService.delMyArticle(article_no);

					
					/*
					user_id = (String)session.getAttribute("user_id");
					System.out.println("user_id="+user_id);
					//입력받은 비밀번호
					String delpassword = (String)request.getParameter("delpassword");
					System.out.println("delpwd=" + delpassword);
					
					*/
				
			        System.out.println(user_id + "글 삭제");
			        out.print("<script>");
			        out.print("alert('작성글이 삭제되었습니다.');");
			        out.print("location.href='/nemo/mypage/myBoardList';");
			        out.print("</script>");
			        return;
					//request.setAttribute("msg", "deleted");
					//nextPage="/nemo/mypage/myBoardList";
					//response.sendRedirect(nextPage);

				}else if(action.equals("/delComment")) {
					//내 댓글글 삭제
					int comment_no = Integer.parseInt(request.getParameter("comment_no"));
					
					myBoardService.delMyComment(comment_no);
				
			        System.out.println(user_id + "댓글 삭제");
			        out.print("<script>");
			        out.print("alert('댓글이 삭제되었습니다.');");
			        out.print("location.href='/nemo/mypage/myBoardList';");
			        out.print("</script>");
			        return;
					//request.setAttribute("msg", "deleted");
					//nextPage="/nemo/mypage/myBoardList";
					//response.sendRedirect(nextPage);
					
				}
//			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
//			dispatcher.forward(request, response);
				
			} catch (Exception e) {
				System.out.println("컨트롤러 요청 처리 중 에러 !");
				e.printStackTrace();
			}
			
		} else {
			//로그인 상태가 아니라면 index페이지로 이동하게 함
			request.setAttribute("msg", "isnotlogOn");
			nextPage="/nemo/index";
			response.sendRedirect(nextPage);
		}
			
	}// doHandle() End

}