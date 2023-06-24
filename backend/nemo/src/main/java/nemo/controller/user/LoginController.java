package nemo.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.service.user.LoginService;
import nemo.vo.user.UserVO;

@WebServlet("/login/*")
public class LoginController extends HttpServlet {
	LoginService loginService;
	UserVO userVO;

	@Override
	public void init() throws ServletException {
		loginService = new LoginService();
		userVO = new UserVO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		HttpSession session = request.getSession();
		String nextPage = "";

		System.out.println("요청 매핑이름 : " + action);

		if (action == null || action.equals("/loginForm")) {
			
			nextPage = "/views/login.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);

		} else if (action.equals("/logintry")) {

			String user_id = request.getParameter("user_id");
			String password = request.getParameter("password");
			
			userVO.setUser_id(user_id);
			userVO.setPassword(password);
			
			Boolean result = loginService.isExisted(userVO);

			if(result == true) {
				userVO = loginService.selectUserById(user_id);
				session.setAttribute("user_id", userVO.getUser_id());
				session.setAttribute("nickname", userVO.getNickname());
				session.setAttribute("user_img", userVO.getUser_img());
				session.setAttribute("admin", userVO.getAdmin());
				nextPage = "/nemo/index";
				response.sendRedirect(nextPage);
			}else {
				// 회원이 아님 
				System.out.println("로그인 실패");
			}
			
			
			
			
			

		} 
		//RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		//dispatcher.forward(request, response);
	}
}
