package nemo.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.service.admin.AdminGroupService;
import nemo.service.admin.AdminUserService;

@WebServlet("/adminUser/*")
public class AdminUserController extends HttpServlet {
	AdminUserService adminUserService;
	HttpSession session;
	
	@Override
	public void init() throws ServletException {
		adminUserService=new AdminUserService();
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
		PrintWriter out = response.getWriter();
		
		String action=request.getPathInfo();
		String nextPage=null;
		session=request.getSession();
		String user_id=(String)session.getAttribute("user_id");
		if(user_id==null) {
			out.print("<script>");
			out.print("alert('로그인 후 이용해주세요.');");
			out.print("location.href='"+request.getContextPath()+"/login/loginForm';");
			out.print("</script>");
		} else {
			int admin = (Integer) session.getAttribute("admin");
			if(admin<1) {
				out.print("<script>");
				out.print("alert('잘못된 접근 입니다.');");
				out.print("location.href='"+request.getContextPath()+"/index';");
				out.print("</script>");
			}else {
					System.out.println("요청 매핑 이름 : " + action);
					try {
						if(action == null || action.equals("/adminUser")) {
							List<Map> userList=new ArrayList<Map>();
							userList=adminUserService.getUserList();
							request.setAttribute("userList", userList);
							nextPage="/views/admin/adminUser.jsp";
							RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
						    dispatcher.forward(request, response);
						} else if (action.equals("/delUser.do")) {			
							String _user_id=request.getParameter("user_id");
							boolean isAdmin=adminUserService.delUser(_user_id);
							if(!isAdmin) {
								nextPage="/nemo/adminUser";
								out.println("<script>alert('회원이 삭제 되었습니다.'); location.href='"+nextPage+"';</script>");
								out.flush();
								//response.sendRedirect(nextPage);
							} else {
								nextPage="/nemo/adminUser";
								out.println("<script>alert('관리자는 삭제할 수 없습니다.'); location.href='"+ nextPage+ "';</script>");
								out.flush();
							}
							
						}
						
					}catch (Exception e) {
						System.out.println("회원 관리 처리 중 에러 ");
						e.printStackTrace();
					}
			}
		}
	}
}
