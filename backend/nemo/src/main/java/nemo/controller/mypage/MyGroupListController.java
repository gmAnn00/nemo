package nemo.controller.mypage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.service.group.GroupInfoService;
import nemo.service.mypage.MyGroupService;
import nemo.service.mypage.MyProfileService;
import nemo.vo.group.GroupVO;
import nemo.vo.mypage.UserVO;

@WebServlet("/mypage/myGroupList")
public class MyGroupListController extends HttpServlet {
	HttpSession session;
	MyGroupService myGroupService;


	public void init() throws ServletException {
		myGroupService = new MyGroupService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	public void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		session = request.getSession();
		String nextPage = "";
		String action = request.getPathInfo();
		String user_id = (String) session.getAttribute("user_id");
		
		boolean isBookmark = false;
		GroupInfoService groupInfoService = new GroupInfoService();


		if (user_id != null) {
			// 로그인 상태일때만 수행
			try {
				if (action == null || action.equals("/myGroup")) {
					user_id = (String)session.getAttribute("user_id");
					//내가 리더인 소모임	조회
					List<GroupVO> mngGroupList = new ArrayList<>();
					mngGroupList = myGroupService.getManagerGrp(user_id);					
					
					//일반 회원인 소모임 조회
					List<GroupVO> userGroupList = new ArrayList<>();
					userGroupList = myGroupService.getUserGrp(user_id);										
					
					//가입 대기중인 소모임 조회
					List<GroupVO> waitGroupList = new ArrayList<>();
					waitGroupList = myGroupService.getWaitGrp(user_id);
					
					//찜한 소모임 조회 BOOKMARK_TBL
					List<GroupVO> bookmarkGroupList = new ArrayList<>();
					bookmarkGroupList  = myGroupService.getBookMarkGrp(user_id);
					
					
					request.setAttribute("mngGroupList", mngGroupList);
					request.setAttribute("userGroupList", userGroupList);
					request.setAttribute("waitGroupList", waitGroupList);
					request.setAttribute("bookmarkGroupList", bookmarkGroupList);
					request.setAttribute("isBookmark", true); //찜한 소모임 jsp ${isbookmark}용
					
					
					nextPage="/views/mypage/myGroupList.jsp";
					
				} 
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
				dispatcher.forward(request, response);

			} catch (Exception e) {
				System.out.println("컨트롤러 요청 처리 중 에러 !");
				e.printStackTrace();
			}
		} else {
			// 로그인 상태가 아니라면 index페이지로 이동하게 함
			request.setAttribute("msg", "isnotlogin");
			nextPage = "/nemo/index";
			response.sendRedirect(nextPage);
		}

	}
}
