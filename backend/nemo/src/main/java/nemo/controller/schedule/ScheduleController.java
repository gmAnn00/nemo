package nemo.controller.schedule;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.dao.schedule.ScheduleDAO;
import nemo.service.schedule.ScheduleService;
import nemo.vo.schedule.ScheduleVO;

@WebServlet("/group/schedule/asdf")
public class ScheduleController extends HttpServlet {
	ScheduleDAO dao;
	HttpSession session;
	ScheduleVO scheduleVO;
	
	public void init(ServletConfig config) throws ServletException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");	//마임정보 전달
		session = request.getSession();	//여러군데에서 쓰일 세션 변수선언
		
		String user_id = (String)session.getAttribute("user_id");
        String grp_id = request.getParameter("group_id");
        String action = request.getPathInfo();
        System.out.println("요청 매핑이름: " + action);
		
		if(action == null || action.equals("/schedule")) {
			//List<ScheduleVO> scheduleList = dao.listSchedule();
			//request.setAttribute("scheduleList", scheduleList);	//셋팅한 값으로 jsp에서사용
			int group_id = Integer.parseInt(request.getParameter("group_id")); 
			nextPage = "/views/group/schedule.jsp";			// 슬래쉬는 반드시해줘야 인식한다.
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);	//컨트롤러에서 포워딩해줌
			dispatcher.forward(request, response);
		}else if (action.equals("/addSchedule")) {
			//Map<String, String> scheduleMap = createSchedule(request, response);
			int group_id = Integer.parseInt(request.getParameter("group_id")); 
			
			String sche_title = request.getParameter("sche_title");
			String sche_cont = request.getParameter("sche_cont");
			
			//날짜 형식 포맷
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		    String inputDateTimeString = request.getParameter("schedule"); //inputDateTime을 가져오는옴
		    System.out.println(inputDateTimeString);
		    try {
		        Date inputDate = dateFormat.parse(inputDateTimeString);
		        Timestamp schedule = new Timestamp(inputDate.getTime());
		        // inputDateTime을 사용하여 DB에 저장하거나 필요한 작업을 수행합니다.
		        scheduleVO = new ScheduleVO();
		        scheduleVO.setSchedule(schedule);
		        System.out.println("schedule: "+schedule);
		        scheduleVO.setGrp_id("7");
			    scheduleVO.setUser_id("chulsu");
			    scheduleVO.setSche_title(sche_title);
			    scheduleVO.setSche_cont(sche_cont);
			    scheduleVO.setLocation("임시위치");
			    
			    ScheduleService scheduleService = new ScheduleService();
				scheduleService.addSchedule(scheduleVO);
		    } catch (ParseException e) {
		        System.out.println("날짜 형식 파싱 처리중 오류");
		        e.printStackTrace();
		    }
		    nextPage = "/nemo/group/schedule?group_id="+Integer.toString(group_id);			// 슬래쉬는 반드시해줘야 인식한다.
			response.sendRedirect(nextPage);
			//RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);	//컨트롤러에서 포워딩해줌
			//dispatcher.forward(request, response);
		}
	}
}
		//로그인 정보 확인
		//boolean isLoggedIn = session.getAttribute("isLoggedIn") != null && (boolean) session.getAttribute("isLoggedIn");
		
		/*if (isLoggedIn) {
	        // 로그인한 경우 처리 로직
	        String user_id = request.getParameter("user_id");
	        String grp_id = request.getParameter("grp_id");
	        String action = request.getPathInfo();
	        System.out.println("요청 매핑이름: " + action);

	        if (action == null || action.equals("/schedule.do")) {
	            List<ScheduleVO> scheduleList = dao.listSchedule();
	            request.setAttribute("scheduleList", scheduleList);
	            // nextPage = "/viewMember/listMembers.jsp";
	        }

	        // 처리 결과에 따라 적절한 응답을 보내거나 다음 페이지로 이동하는 로직을 작성

	    }else {
	        // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
	        response.sendRedirect("/login"); // 로그인 페이지 경로에 맞게 수정
	    }*/
		
		
		
		
		
		
		/*String user_id = request.getParameter("user_id");
		String grp_id = request.getParameter("grp_id");
		String action = request.getPathInfo();	//요청경로정보를 가져오는 변수선언
		System.out.println("요청 매핑이름 : " +  action);
		if(action == null || action.equals("/schedule.do")) {
			List<ScheduleVO> scheduleList = dao.listSchedule();
			request.setAttribute("scheduleList", scheduleList);	//셋팅한 값으로 jsp에서사용
			//nextPage = "/viewMember/listMembers.jsp";			// 슬래쉬는 반드시해줘야 인식한다.
		}
	}*/
