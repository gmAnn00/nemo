package nemo.controller.group;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.service.board.BoardService;
import nemo.service.group.GroupInfoService;
import nemo.vo.group.GroupVO;

@WebServlet("/group/groupInfo")
public class GroupInfoController extends HttpServlet {
	HttpSession session;	
	GroupInfoService groupInfoService;
	Map groupInfo;

	
	@Override
	public void init() throws ServletException {
		groupInfoService=new GroupInfoService();		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String nextPage = "";
		session=request.getSession();

		GroupVO groupVO = new GroupVO();
		String groupManager = "";
		int groupMemberNum = 0;
		int groupBookmarkNum = 0;
		boolean isBookmark = false;
		String user_id = "";
		Date recentDate;
		int app_st = 0;
		

		//http://127.0.0.1:8090/nemo_agm/group/groupInfo?group_id=1
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		user_id = (String) session.getAttribute("user_id");
		//int group_id = 1;

		GroupInfoService groupInfoService = new GroupInfoService();
		groupVO = groupInfoService.selectGroupById(group_id);
		groupManager = groupInfoService.selectManagerById(group_id);
		groupMemberNum = groupInfoService.selectGroupNumById(group_id);
		groupBookmarkNum = groupInfoService.selectBookmarkNumById(group_id);
		recentDate = groupInfoService.selectRecentDate(group_id);
		app_st = groupInfoService.selectAppSt(group_id);		
		
		
		if(user_id != null) {
			isBookmark = groupInfoService.isBookmark(user_id, group_id);
			//System.out.println(user_id);
			//System.out.println(group_id);
			//System.out.println(isBookmark);
		}		

		groupInfo = groupInfoService.getGroupInfo(group_id);
		request.setAttribute("groupInfo", groupInfo);
		
		request.setAttribute("groupVO", groupVO);
		request.setAttribute("groupManager", groupManager);
		request.setAttribute("groupMemberNum", groupMemberNum);
		request.setAttribute("groupBookmarkNum", groupBookmarkNum);
		request.setAttribute("isBookmark", isBookmark);
		request.setAttribute("recentDate", recentDate);
		request.setAttribute("app_st", app_st);

		nextPage = "/views/group/groupInfo.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);

	}
}
