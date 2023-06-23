package nemo.controller.mypage;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import nemo.service.mypage.MyInterestService;
import nemo.service.mypage.MyProfileService;
import nemo.vo.mypage.InterestVO;
import nemo.vo.mypage.UserVO;


@WebServlet("/mypage/myBoardList/*")
public class myBoardListController extends HttpServlet {
	HttpSession session;
	MyProfileService myProfService;
	MyInterestService myIntesInterestService;
	UserVO userVO;
	
	private static String USER_IMG_REPO;
	private static String USER_IMG_DEF;
	
	public void init() throws ServletException {
		myProfService = new MyProfileService();
		myIntesInterestService = new MyInterestService();
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
		session=request.getSession();
		String nextPage = "";

		String action = request.getPathInfo();				
		String user_id = (String)session.getAttribute("user_id");

		//System.out.println("action="+action);
		
		if(user_id != null) {				
			//로그인 상태일때만 수행
			try {			
				// 내가 쓴 글, 댓글 조회	
				if(action == null || action.equals("/myBoardList")) {
				
					user_id = (String)session.getAttribute("user_id");
					/*
					//id로 회원정보 찾는 메소드				
					userVO = myProfService.searchProfileById(user_id);
					
					//회원정보 담기
					request.setAttribute("userVO", userVO);
					//System.out.println("controller 유저정보조회" + userVO);
					
					//관심사 담기(list로 담기)
					List<InterestVO> interestList = myIntesInterestService.searchInterestById(user_id);
					request.setAttribute("interestList", interestList);
					*/
					
					
					
					
					nextPage="/views/mypage/myBoardList.jsp";
					
					RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
					dispatcher.forward(request, response);
					
				} else if(action.equals("/delWriting")) {				
					//글 댓글 삭제
					
					
					
					
					
					/*
					user_id = (String)session.getAttribute("user_id");
					System.out.println("user_id="+user_id);
					//입력받은 비밀번호
					String delpassword = (String)request.getParameter("delpassword");
					System.out.println("delpwd=" + delpassword);
					
			        //UserVO userVO = new UserVO(user_id, delpassword);
			        myProfService.delMember(user_id, delpassword);
					*/
					
					
			        System.out.println(user_id + "글 삭제");
					//request.setAttribute("msg", "deleted");
					nextPage="/nemo/mypage/myBoardList";
					response.sendRedirect(nextPage);

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
