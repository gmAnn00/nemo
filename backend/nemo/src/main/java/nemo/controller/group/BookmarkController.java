package nemo.controller.group;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.service.group.BookmarkService;

/**
 * Servlet implementation class Bookmark
 */
@WebServlet("/group/bookmark")
public class BookmarkController extends HttpServlet {
	HttpSession session;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String nextPage = "";
		session=request.getSession();
		
		String user_id = (String) request.getParameter("user_id");
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		Boolean isBookmark = Boolean.parseBoolean(request.getParameter("isBookmark"));
		
		BookmarkService bookmarkService = new BookmarkService();
		bookmarkService.toggleBookmark(user_id, group_id, isBookmark);
		
	}

}
