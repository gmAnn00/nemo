package com.nemo.web.group.controller;

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

import com.nemo.web.group.dao.GroupMangerDAO;
import com.nemo.web.group.vo.GroupVO;
import com.nemo.web.group.vo.UserVO;

//뷰와 모델 연결

@WebServlet("/group/manager/*")
public class GroupMangerController extends HttpServlet{
	HttpSession session;
	GroupMangerDAO groupMangerDAO;
	UserVO userVO;
	GroupVO groupVO;
	
	@Override
	public void init() throws ServletException {
		groupMangerDAO=new GroupMangerDAO();
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
		/*String login_id = (String)session.getAttribute("user_id");*/
		
		System.out.println("요청 매핑이름: " + action); 
		/*if(login_id != null) {*/
			try {
				
				if(action==null||action.equals("/myGroupMember")) {
					
					List<GroupVO> groupList=groupMangerDAO.groupShow();
					List<UserVO> userList=groupMangerDAO.userShow();
					List<UserVO> approveUserList=groupMangerDAO.approveMemberShow();
					
					request.setAttribute("groupList", groupList);
					request.setAttribute("userList", userList);
					request.setAttribute("approveUserList", approveUserList);
					
					nextPage="/WEB-INF/view/group/myGroupMember.jsp";
					
				}
				
				//위임
				else if(action.equals("/mandate.do")) {
					String user_id=request.getParameter("user_id");
					userVO.setUser_id(user_id);
					groupMangerDAO.mandateGroupManager(userVO);
					out = response.getWriter();
					out.print("<script>");
					out.print("alert('매니저를 위임했습니다');");
						//location 포워딩 
					out.print("location.href='" + request.getContextPath() + 
							"/group/manager/myGroupMember';");
					out.print("</script>");
					return;
			        
					
				}
				
				//추방
				else if(action.equals("/exile.do")) {
					String user_id=request.getParameter("user_id");
					userVO.setUser_id(user_id);
					groupMangerDAO.exileGroupMember(userVO);
					out = response.getWriter();
					out.print("<script>");
					out.print("alert('멤버를 추방했습니다');");
					
					out.print("location.href='" + request.getContextPath() + 
							"/group/manager/myGroupMember';");
					out.print("</script>");
					return;
					
				}
				
				//가입 승인
				else if(action.equals("/approve.do")) {
					String user_id=request.getParameter("user_id");
					userVO.setUser_id(user_id);
					groupMangerDAO.approveUser(userVO);
					groupMangerDAO.approveListUpdate();
					out = response.getWriter();
					out.print("<script>");
					out.print("alert('가입을 승인했습니다');");
					
					out.print("location.href='" + request.getContextPath() + 
							"/group/manager/myGroupMember';");
					out.print("</script>");
					return;
			        
				}
				
				//가입 거절
				else if(action.equals("/reject.do")) {
					String user_id=request.getParameter("user_id");
					userVO.setUser_id(user_id);
					groupMangerDAO.rejectUser(userVO);
					out = response.getWriter();
					out.print("<script>");
					out.print("alert('가입을 거절했습니다');");
					
					out.print("location.href='" + request.getContextPath() + 
							"/group/manager/myGroupMember';");
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
	/*}*/
	
}
