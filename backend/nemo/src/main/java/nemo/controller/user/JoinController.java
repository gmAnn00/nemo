package nemo.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nemo.service.user.JoinService;
import nemo.vo.common.UserVO;



@WebServlet("/join/*")
public class JoinController extends HttpServlet {
	UserVO uservo;
	JoinService joinService;
	
	@Override
	public void init() throws ServletException {
		joinService = new JoinService();
		uservo = new UserVO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doHandle(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		String nextPage = "";
		System.out.println("요청 매핑이름 : " +  action);
		
		if(action.equals("/agreeForm")) { //약관동의
			
			nextPage = "/views/agree.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
			
		}else if(action.equals("/joinForm")) {
			nextPage="/views/join.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}
		
		else if (action == null || action.equals("/join")) { //회원가입
			String User_id = request.getParameter("user_id");
			String Password = request.getParameter("password");
			String User_name = request.getParameter("user_name");
			String nickname = request.getParameter("nickname");
			String User_addr1 = request.getParameter("user_addr1");
			String User_addr2 = request.getParameter("user_addr2");
			Date birthdate = Date.valueOf(request.getParameter("birthdate"));
			String phone = request.getParameter("phone");
			
			String emailId = request.getParameter("email");
			String emilDomain = request.getParameter("emailDomain");
			String email = emailId + "@" + emilDomain;
			
			uservo.setUser_id(User_id);
			uservo.setPassword(Password);
			uservo.setUser_name(User_name);
			uservo.setNickname(nickname);
			uservo.setUser_addr1(User_addr1);
			uservo.setUser_addr2(User_addr2);
			uservo.setBirthdate(birthdate);
			uservo.setPhone(phone);
			uservo.setEmail(email);
			
			
			
			nextPage = "/views/intertest.jsp";
		}else if (action.equals("/intertestForm")) { //관심사체크
			
			
			request.setAttribute("msg", "addMember");
			nextPage = "/index.jsp";
		}
		
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		//dispatcher.forward(request, response);
	}
	
}