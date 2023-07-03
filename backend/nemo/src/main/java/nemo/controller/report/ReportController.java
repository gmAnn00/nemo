package nemo.controller.report;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.service.report.ReportService;

@WebServlet("/report/*")
public class ReportController extends HttpServlet {
	HttpSession session;
	ReportService reportService;

	public void init(ServletConfig config) throws ServletException {
		reportService = new ReportService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		session=request.getSession();
		PrintWriter out = response.getWriter();
		
		String user_id = (String) session.getAttribute("user_id");
		String action = request.getPathInfo();
		
		if(user_id == null) {
			out.print("<script>");
			out.print("alert('로그인 후 이용가능합니다.');");
			out.print("location.href='/nemo/login/loginForm';");
			out.print("</script>");
		}
		
		if(action.equals("/group")) {
			// 소모임 신고
			int group_id = Integer.parseInt(request.getParameter("group_id"));
			
			Boolean isAlreadyGReport = reportService.isGReport(group_id, user_id);
			
			if(!isAlreadyGReport) {
				reportService.groupReport(group_id, user_id);
				
				out.print("<script>");
				out.print("alert('소모임을 신고하였습니다.');");
				out.print("location.href='/nemo/group/groupMain?group_id=" + group_id + "';");
				out.print("</script>");
			} else {
				out.print("<script>");
				out.print("alert('이미 신고한 소모임입니다.');");
				out.print("location.href='/nemo/group/groupMain?group_id=" + group_id + "';");
				out.print("</script>");
			}
			
			
			
		}else if(action.equals("/member")) {
			// 멤버 신고
			int group_id = Integer.parseInt(request.getParameter("group_id"));
			String accused_id = request.getParameter("accused_id");
			
			Boolean isAreadyMReport = reportService.isMReport(user_id, accused_id);
			System.out.println("isAreadyMReport=" + isAreadyMReport);
			
			if(!isAreadyMReport) {
				reportService.memberReport(user_id, accused_id);
				
				out.print("<script>");
				out.print("alert('소모임 멤버를 신고하였습니다.');");
				out.print("location.href='/nemo/group/groupMain?group_id=" + group_id + "';");
				out.print("</script>");
			}else {
				out.print("<script>");
				out.print("alert('이미 신고한 소모임 멤버입니다.');");
				out.print("location.href='/nemo/group/groupMain?group_id=" + group_id + "';");
				out.print("</script>");
			}
			
			
		}
		
	}
}
