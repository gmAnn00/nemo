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

import nemo.service.admin.AdminReportService;

@WebServlet("/adminReport/*")
public class AdminReportController extends HttpServlet {
	AdminReportService adminRepService;
	HttpSession session;
	
	@Override
	public void init() throws ServletException {
		adminRepService=new AdminReportService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
						if(action == null || action.equals("/adminReport")) {
							List<Map> groupList=new ArrayList<Map>();
							List<Map> userList=new ArrayList<Map>();
							groupList=adminRepService.getRepGroupList();
							userList=adminRepService.getRepUserList();						
							request.setAttribute("groupList", groupList);
							request.setAttribute("userList", userList);
							nextPage="/views/admin/adminReport.jsp";
							System.out.println("어디까지");
							RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
						    dispatcher.forward(request, response);
						    
						}
						
					}catch (Exception e) {
						System.out.println("소모임 관리 처리 중 에러 ");
						e.printStackTrace();
					}
			}
		}
	}

}
