package nemo.controller.grpmng;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.dao.grpmng.GroupMangerDAO;
import nemo.service.grpmng.GroupMangerService;
import nemo.vo.group.GroupVO;
import nemo.vo.group.UserVO;

//뷰와 모델 연결

@WebServlet("/group/manager/setting/*")
public class GroupSettingController extends HttpServlet{
	HttpSession session;
	GroupMangerService groupMangerService;
	GroupMangerDAO groupMangerDAO;
	UserVO userVO;
	GroupVO groupVO;
	
	@Override
	public void init() throws ServletException {
		groupMangerDAO=new GroupMangerDAO();
		groupMangerService=new GroupMangerService();
		userVO=new UserVO();
		groupVO=new GroupVO();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("컨트롤러 도착");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out;
		String nextPage="";
		String action=request.getPathInfo();
		
		int grp_id = Integer.parseInt(request.getParameter("grp_id"));
		System.out.println(grp_id);
		session=request.getSession();
		String login_id = (String) session.getAttribute("user_id");
		
		System.out.println("요청 매핑이름: " + action);
		try {
			
			if(action==null||action.equals("/myGroupSetting")) {
				
				//List<GroupVO> groupList=groupMangerDAO.groupShow();
				//List<UserVO> userList=groupMangerDAO.userShow();
				List<UserVO> approveUserList=groupMangerDAO.approveMemberShow();
				
				//request.setAttribute("groupList", groupList);
				//request.setAttribute("userList", userList);
				request.setAttribute("approveUserList", approveUserList);
				
				nextPage="/WEB-INF/view/group/groupSetting.jsp";
				
			}
			
		
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);

		
		} catch (Exception e) {
			System.out.println("요청 처리 중 에러!!");
			e.printStackTrace();
		}
		
		
		
		
	}

	
}
