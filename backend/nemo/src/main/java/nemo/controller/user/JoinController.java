package nemo.controller.user;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;

import nemo.service.user.JoinService;
import nemo.vo.user.InterestVO;
import nemo.vo.user.UserVO;


@WebServlet("/join/*")
public class JoinController extends HttpServlet {
	UserVO userVO;
	InterestVO interestVO;
	JoinService joinService;
	
	private static String USER_IMG_REPO;
	private static String USER_IMG_DEF;
	
	@Override
	public void init() throws ServletException {
		joinService = new JoinService();
		userVO = new UserVO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doHandle(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		String nextPage = "";
		System.out.println("요청 매핑이름 : " +  action);
		HttpSession session;
		
		USER_IMG_REPO = this.getClass().getResource("").getPath();
		USER_IMG_REPO = USER_IMG_REPO.substring(1, USER_IMG_REPO.indexOf(".metadata"));
		USER_IMG_REPO = USER_IMG_REPO.replace("/", "\\");
		USER_IMG_REPO += "nemo\\src\\main\\webapp\\userImages\\";
		
		USER_IMG_DEF = this.getClass().getResource("").getPath();
		USER_IMG_DEF = USER_IMG_DEF.substring(1, USER_IMG_DEF.indexOf(".metadata"));
		USER_IMG_DEF = USER_IMG_DEF.replace("/", "\\");
		USER_IMG_DEF += "nemo\\src\\main\\webapp\\images\\pingoo.jpg";
		
		if(action.equals("/agreeForm")) { //약관동의
			
			nextPage = "/views/join/agree.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
			
		}else if(action.equals("/joinForm")) {
			nextPage="/views/join/join.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}
		
		else if (action == null || action.equals("/join")) { //회원가입
			String user_id = request.getParameter("user_id");
			String password = request.getParameter("password");
			String user_name = request.getParameter("user_name");
			String nickname = request.getParameter("nickname");
			String zipcode = request.getParameter("zipcode");
			String user_addr1 = request.getParameter("user_addr1");
			String user_addr2 = request.getParameter("user_addr2");
			Date birthdate = Date.valueOf(request.getParameter("birthdate"));
			String phone = request.getParameter("phone");
			String emailId = request.getParameter("emailId");
			String emilDomain = request.getParameter("emailDomain");
			String email = emailId + "@" + emilDomain;
			
			userVO.setUser_id(user_id);
			userVO.setPassword(password);
			userVO.setUser_name(user_name);
			userVO.setNickname(nickname);
			userVO.setZipcode(zipcode);
			userVO.setUser_addr1(user_addr1);
			userVO.setUser_addr2(user_addr2);
			userVO.setBirthdate(birthdate);
			userVO.setPhone(phone);
			userVO.setEmail(email);
			
			joinService.adduser(userVO);
			
			request.setAttribute("msg", "adduser");
			
			PrintWriter out=response.getWriter();
			System.out.println("회원가입 성공");
			
			session = request.getSession();
			session.setAttribute("user_id_temp", user_id);
			
			File scrFile = new File(USER_IMG_DEF);
			File destDir = new File(USER_IMG_REPO + "\\" + user_id);
			destDir.mkdir();
			FileUtils.copyFileToDirectory(scrFile, destDir, false);
			
			out.print("<script>");
			out.print("alert('회원가입에 성공하셨습니다.');");
			out.print("location.href='" + request.getContextPath() + "/join/interestForm';");
			out.print("</script>");
			
			//RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			//dispatcher.forward(request, response);
			
		}else if (action.equals("/interestForm")) { //관심사이트
			nextPage = "/views/join/interest.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		
		}else if (action.equals("/interestsChoice")) {
			session=request.getSession();
			String user_id = (String)session.getAttribute("user_id_temp");
			session.removeAttribute("user_id_temp");
			
			int inputNum = Integer.parseInt(request.getParameter("inputNum"));
			
			List<InterestVO> interestsList = new ArrayList<InterestVO>();
			
			for(int i = 0; i<inputNum; i++) {
				interestVO = new InterestVO();
				String main_name = request.getParameter("main_name"+i);
				String sub_name = request.getParameter("sub_name"+i);
				
				//System.out.println("main_name="+main_name);
				//System.out.println("sub_name="+sub_name);
				
				interestVO.setUser_id(user_id);
				interestVO.setMain_name(main_name);
				interestVO.setSub_name(sub_name);
				interestsList.add(interestVO);
			}
			
			
			
			joinService.addChoice(interestsList);
			
			request.setAttribute("msg", "addChoice");
			
			PrintWriter out=response.getWriter();
			System.out.println("관심사 추가 성공");
			out.print("<script>");
			out.print("alert('관심사를 추가하셨습니다.');");
			out.print("location.href='" + request.getContextPath() + "/login/loginForm';");
			out.print("</script>");
			
		}
		
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		//dispatcher.forward(request, response);
	}
	
}
