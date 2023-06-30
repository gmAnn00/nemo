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

import nemo.dao.group.GroupDAO;
import nemo.dao.grpmng.GroupMangerDAO;
import nemo.service.grpmng.GroupMangerService;
import nemo.vo.group.GroupVO;
import nemo.vo.group.UserVO;

//뷰와 모델 연결

@WebServlet("/group/manager/member/*")
public class GroupMangerController extends HttpServlet{
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

		session=request.getSession();
			
		String login_id = (String) session.getAttribute("user_id");
		System.out.println(login_id);
		
		//jsp에서 컨트롤러로 넘어올 때 받을 grp_id값
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		System.out.println(group_id);
		
		System.out.println("요청 매핑이름: " + action); 
		try {
			
			if(action==null||action.equals("/myGroupMember")) {
				
				List<GroupVO> groupList=groupMangerService.groupShow(group_id);
				List<UserVO> userList=groupMangerService.userShow(group_id);
				List<UserVO> approveUserList=groupMangerService.approveMemberShow(group_id);
				
				request.setAttribute("groupList", groupList);
				request.setAttribute("userList", userList);
				request.setAttribute("approveUserList", approveUserList);
				
				nextPage="/views/group/myGroupMember.jsp";
				
			}
			
			//위임
			else if(action.equals("/mandate.do")) {
				
				String user_id=request.getParameter("user_id");
				userVO.setUser_id(user_id);
				groupMangerService.mandateGroupManager(userVO, group_id);
				out = response.getWriter();
				out.print("<script>");
				out.print("alert('매니저를 위임했습니다');");
					//location 포워딩 
				out.print("location.href='" + request.getContextPath() + 
						"/group/manager/member/myGroupMember?group_id="+group_id+"';");
				out.print("</script>");
				return;
		        
			}
			//추방
			else if(action.equals("/exile.do")) {
				String user_id=request.getParameter("user_id");
				userVO.setUser_id(user_id);
				groupMangerService.exileGroupMember(userVO, group_id);
				out = response.getWriter();
				out.print("<script>");
				out.print("alert('멤버를 추방했습니다');");
				
				out.print("location.href='" + request.getContextPath() + 
						"/group/manager/member/myGroupMember?group_id="+group_id+"';");
				out.print("</script>");
				return;
				
			}
			
			//승인
			else if(action.equals("/approve.do")) {
				String user_id=request.getParameter("user_id");
				userVO.setUser_id(user_id);
				groupMangerService.approveUser(userVO);
				groupMangerService.approveListUpdate();
				out = response.getWriter();
				out.print("<script>");
				out.print("alert('가입을 승인했습니다');");
				
				out.print("location.href='" + request.getContextPath() + 
						"/group/manager/member/myGroupMember?group_id="+group_id+"';");
				out.print("</script>");
				return;
		        
			}
			
			//거절
			else if(action.equals("/reject.do")) {
				String user_id=request.getParameter("user_id");
				userVO.setUser_id(user_id);
				groupMangerService.rejectUser(userVO, group_id);
				out = response.getWriter();
				out.print("<script>");
				out.print("alert('가입 승인을 거절했습니다');");
				
				out.print("location.href='" + request.getContextPath() + 
						"/group/manager/member/myGroupMember?group_id="+group_id+"';");
				out.print("</script>");
				return;
				
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);

		
		} catch (Exception e) {
			System.out.println("요청 처리 중 에러!!");
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
}