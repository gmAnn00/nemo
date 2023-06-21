package nemo.controller.mypage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.service.mypage.MyInterestService;

@WebServlet("/mypage/interest")
public class MyInterestController extends HttpServlet {
	
	HttpSession session;
	MyInterestService myIntesInterestService;	
	
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

		String action = request.getPathInfo();				
		String user_id = (String)session.getAttribute("user_id");
		
		if(action.equals("/modInterestForm")) {
			//관심사 수정페이지로 이동
			user_id = (String)session.getAttribute("user_id");
			List<String> interestList = myIntesInterestService.searchInterestById(user_id);
			request.setAttribute("interestList", interestList);
			
			nextPage= "/views/mypage/modInterest.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
			
		} else if(action.equals("/modInterest")) {
			//관심사 수정
			//넘겨준 interestList 이용
			//List<String> interestList = new ArrayList<>();
			
			myIntesInterestService.modInterest(user_id, interestList);
			nextPage="/nemo/mypage/myprofile";
			response.sendRedirect(nextPage);
			
		}
			
	} //doHandle End

}
