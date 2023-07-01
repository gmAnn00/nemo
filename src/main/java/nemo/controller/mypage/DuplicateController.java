package nemo.controller.mypage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.service.mypage.DuplicateService;
import nemo.service.mypage.MyProfileService;
import nemo.vo.user.UserVO;

@WebServlet("/duplicate/*")
public class DuplicateController extends HttpServlet {
	HttpSession session;
	DuplicateService duplService;
	UserVO userVO;
	
	public void init() throws ServletException {
		duplService = new DuplicateService();
		userVO = new UserVO();
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
		//session=request.getSession();
		//String user_id = (String)session.getAttribute("user_id");
		String action = request.getPathInfo();
		
		PrintWriter out = response.getWriter();
						
		if(action.equals("/id")){
			String user_id = (String)request.getParameter("user_id");
			System.out.println("id: " + user_id);
			boolean overlappedCheck = duplService.overlappedID(user_id);
			
			if(overlappedCheck == true) {
				out.print("not_usable");
			} else {
				out.print("usable");
			}
			
			
		} else if(action.equals("/nickname")) {
			String nickname = (String)request.getParameter("nickname");
			System.out.println("닉네임: " + nickname);
			boolean overlappedCheck = duplService.overlappedNn(nickname);
			
			if(overlappedCheck == true) {
				out.print("not_usable");
			} else {
				out.print("usable");
			}
			
		} else if(action.equals("/email")) {
			String emailId = (String)request.getParameter("emailId");
			String emailDomain = (String)request.getParameter("emailDomain");
			String email = emailId + "@" + emailDomain;
			System.out.println("이메일: " + email);
			boolean overlappedCheck = duplService.overlappedEmail(email);
			
			if(overlappedCheck == true) {
				out.print("not_usable");
			} else {
				out.print("usable");
			}
			
		} //else if end
		
		
		
	}	
}
