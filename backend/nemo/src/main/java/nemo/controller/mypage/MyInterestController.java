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
import nemo.service.user.JoinService;
import nemo.vo.user.InterestsVO;
import nemo.vo.user.UserVO;

@WebServlet("/mypage/interest/*")
public class MyInterestController extends HttpServlet {
	
	HttpSession session;
	MyInterestService myInterestService;
	UserVO userVO;
	InterestsVO interestsVO;	

	@Override
	public void init() throws ServletException {
		myInterestService = new MyInterestService();
		userVO = new UserVO();
		interestsVO = new  InterestsVO();
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

		String action = request.getPathInfo();				
		String user_id = (String)session.getAttribute("user_id");
		
		if(action.equals("/modinterest-form")) {
			//관심사 수정페이지로 이동
			user_id = (String)session.getAttribute("user_id");
			List<InterestsVO> interestsList = myInterestService.searchInterestById(user_id);
						
			request.setAttribute("interestsList", interestsList);			
			nextPage= "/views/mypage/modInterest.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
			
		} else if(action.equals("/modinterest")) {
			//관심사 수정
			user_id = (String)session.getAttribute("user_id");			
			int inputNum = Integer.parseInt(request.getParameter("inputNum"));			
			List<InterestsVO> interestsList = new ArrayList<InterestsVO>();
			//System.out.println("inputNum = " + inputNum);
			for(int i = 0; i<inputNum; i++) {
				interestsVO = new InterestsVO();
				String main_name = request.getParameter("main_name"+i);
				String sub_name = request.getParameter("sub_name"+i);
				
				System.out.println("main_name="+main_name);
				System.out.println("sub_name="+sub_name);
				
				interestsVO.setUser_id(user_id);
				interestsVO.setMain_name(main_name);
				interestsVO.setSub_name(sub_name);
				interestsList.add(interestsVO);
			}
			
			myInterestService.modInterests(user_id, interestsList);
			
			nextPage="/nemo/mypage/myprofile";			
			response.sendRedirect(nextPage);
			
		}
			
	} //doHandle End

}
