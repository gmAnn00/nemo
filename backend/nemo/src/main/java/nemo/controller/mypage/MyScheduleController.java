package nemo.controller.mypage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import nemo.service.mypage.MyScheduleService;
import nemo.vo.mypage.InterestVO;
import nemo.vo.user.InterestsVO;
import nemo.vo.user.UserVO;


@WebServlet("/mypage/myschedule/*")
public class MyScheduleController extends HttpServlet {
	HttpSession session;
	MyScheduleService myScheduleService;
	UserVO userVO;
	PrintWriter out;
	
	private static String USER_IMG_REPO;
	private static String USER_IMG_DEF;
	
	public void init() throws ServletException {
		myScheduleService = new MyScheduleService();
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
		
		out = response.getWriter();

		String action = request.getPathInfo();				
		String user_id = (String)session.getAttribute("user_id");
		
		//액션명 확인
		System.out.println("action="+action);
		
}
