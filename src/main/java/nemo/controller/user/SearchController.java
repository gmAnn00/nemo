package nemo.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.service.user.SearchService;
import nemo.vo.user.UserVO;


@WebServlet("/search/*")
public class SearchController extends HttpServlet {
	UserVO userVO;
	SearchService searchService;
	HttpSession session;

	public void init(ServletConfig config) throws ServletException {
		userVO = new UserVO();
		searchService = new SearchService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		
		String nextPage= "";
		
		System.out.println("요청 메핑이름 : " + action);
		//아이디 영역 메서드
		if (action == null || action.equals("/findidForm")) {
			
			nextPage="/views/login/findid.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
			
		}else if (action.equals("/findid")) {
			
			String user_name = request.getParameter("user_name");
			String email = request.getParameter("email");
			/*
			String emailId = request.getParameter("emailId");
			String emilDomain = request.getParameter("emailDomain");
			String email = emailId + "@" + emilDomain;
			*/
			
			userVO.setUser_name(user_name);
			userVO.setEmail(email);
			
			String user_id = searchService.findidCheck(userVO);

			PrintWriter out=response.getWriter();
			
			if (user_id != null) {
				session.setAttribute("user_id", user_id); //세션을 통해 DAO에서 받은 아이디 정보
				//아이디를 찾을 경우
				System.out.println("아이디 찾기 성공");
				out.print("<script>");
				out.print("alert('아이디 찾기에 성공하셨습니다.');");
				out.print("location.href='" + request.getContextPath() + "//search/idcompleteForm';");
				out.print("</script>");
			} else {
				//아이디를 찾지 못한경우
				System.out.println("아이디 찾기 실패");
				out.print("<script>");
				out.print("alert('아이디를 찾을 수 없습니다.');");
				out.print("location.href='" + request.getContextPath() + "/login/loginForm';");
				out.print("</script>");
			}
			
		} else if (action.equals("/idcompleteForm")) {
			session = request.getSession();
			String user_id = (String) session.getAttribute("user_id");
			session.removeAttribute("user_id");// 세선정보 삭제
			request.setAttribute("user_id_find", user_id);
			
			nextPage="/views/login/findidComplete.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
			
		}
		
		//비밀번호 영역 메서드
		if(action == null || action.equals("/findpassForm")) {
			nextPage="/views/login/findpass.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}else if (action.equals("/findpass")) {
			session = request.getSession();
			String user_id = request.getParameter("user_id");
			String user_name =request.getParameter("user_name");
			String email = request.getParameter("email");
			
			session.setAttribute("find_user_id", user_id);
			System.out.println("find_user_id="+user_id);
			
			userVO.setUser_id(user_id);
			userVO.setUser_name(user_name);
			userVO.setEmail(email);
			
			Boolean password = searchService.findpassCheck(userVO);
			PrintWriter out=response.getWriter();
			
			if(password == true) {
				//비밀번호가 참일때
				session.setAttribute("password", password);
				System.out.println("비밀번호 찾기 성공");
				out.print("<script>");
				out.print("alert('비밀번호 찾기에 성공하셨습니다.');");
				out.print("location.href='" + request.getContextPath() + "/search/passcompleteForm';");
				out.print("</script>");
			}else {
				//비밀번호가 거짓일때
				System.out.println("비밀번호 찾기 실패");
				out.print("<script>");
				out.print("alert('비밀번호를 찾을 수 없습니다.');");
				out.print("location.href='" + request.getContextPath() + "/search/findpassForm';");
				out.print("</script>");
			}
			
			
		}else if (action.equals("/passcompleteForm")) {
			nextPage="/views/login/findPassReset.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}else if (action.equals("/findPassReset")) {
			session = request.getSession();
			
			userVO = new UserVO();
			
			String user_id = (String)session.getAttribute("find_user_id");
			String password = request.getParameter("password");
			
			System.out.println("user_id="+user_id);
			System.out.println("password="+password);
			
			session.removeAttribute("find_user_id");
			
			userVO.setUser_id(user_id);
			userVO.setPassword(password);
			
			System.out.println(userVO.toString());
			
			searchService.findpassinfo(userVO);
			
			PrintWriter out=response.getWriter();
			System.out.println("비밀번호 재설정 완료");
			
			out.print("<script>");
			out.print("alert('비밀번호 재설정에 성공하셨습니다.');");
			out.print("location.href='" + request.getContextPath() + "/login/loginForm';");
			out.print("</script>");
			
		}
		
	}
}
