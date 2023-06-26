package nemo.controller.schedule;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import nemo.dao.schedule.ScheduleDAO;
import nemo.service.schedule.ScheduleService;
import nemo.vo.schedule.ScheduleVO;

@WebServlet("/group/schedule/*")
public class ScheduleController extends HttpServlet {
	ScheduleDAO dao;
	HttpSession session;
	ScheduleVO scheduleVO;
	ScheduleService scheduleService;
	
	public void init(ServletConfig config) throws ServletException {
		scheduleService = new ScheduleService();
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
		PrintWriter out = response.getWriter();
		
		String user_id = (String)session.getAttribute("user_id");
        String grp_id = request.getParameter("group_id");
        String action = request.getPathInfo();
        System.out.println("요청 매핑이름: " + action);
		if(action == null || action.equals("/schCompare")) {	//일정 페이지에서 달력의 날짜를 눌렀을때 
			
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String selScheDate = (String)request.getParameter("selScheDate");
				
				
				//System.out.println("받은 날짜 : " + selScheDate);
			    //Date selDate = dateFormat.parse(selScheDate);
			    		//Date.valueOf(selScheDate);
			    		//dateFormat.parse(selScheDate);
			    
			    System.out.println("누른 날짜 : " + selScheDate);
			    scheduleVO = scheduleService.schCompare(7, selScheDate);
			    System.out.println(scheduleVO.getSche_title());
			    System.out.println(scheduleVO.getSchedule());
			    System.out.println(scheduleVO.getSche_cont());
			    System.out.println(scheduleVO.getLocation());
			    
			    
			    //제이슨객체 생성
		        JSONObject scheduleObject = new JSONObject();
		        
		        scheduleObject.put("sche_title", scheduleVO.getSche_title());
		        scheduleObject.put("schedule", scheduleVO.getSchedule() != null ? dateFormat.format(scheduleVO.getSchedule()) : null);  // 포맷팅된 날짜를 JSON 문자열로 변환하여 추가
		        scheduleObject.put("sche_cont", scheduleVO.getSche_cont());
		        scheduleObject.put("location", scheduleVO.getLocation());
		        
		        
		        
		        String ScheduleInfo = scheduleObject.toJSONString();	//JSON객체를 문자열로 변환
				out.print(ScheduleInfo);	//out.print가 문자열로 클라이언트에게 보내주기때문에 문자열로 변환된 문자를 클라이언트에게 보내줌 
		        // JSON 응답을 설정
			    
			    
			    /* if문에서 isExistSchedule이 true면 
			    	서비스에 가서 스케줄 정보를 받아오고
			    	받아온 정보를 jsonobject에 put 하고 리턴
			    	else(isExiztSchedlue이 false일때) 는 그냥 isExistSchedule만 넘겨주기 
			    */
			    
			} catch (Exception e) {
				System.out.println("날짜 매칭중 오류");
		        e.printStackTrace();
			}
			
			
		}else if(action.equals("/schedule")) {	//일정 페이지로 이동
			//List<ScheduleVO> scheduleList = dao.listSchedule();
			//request.setAttribute("scheduleList", scheduleList);	//셋팅한 값으로 jsp에서사용
			int group_id = Integer.parseInt(request.getParameter("group_id")); 
			nextPage = "/views/group/schedule.jsp";			// 슬래쉬는 반드시해줘야 인식한다.
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);	//컨트롤러에서 포워딩해줌
			dispatcher.forward(request, response);
		}/*else if (action.equals("/addSchedule")) {	//일정 추가 부분
			//Map<String, String> scheduleMap = createSchedule(request, response);
			int group_id = Integer.parseInt(request.getParameter("group_id"));
			
			String sche_title = request.getParameter("sche_title");
			String sche_cont = request.getParameter("sche_cont");
			String location = request.getParameter("location");
			
			//날짜 형식 포맷
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		    String inputDateTimeString = request.getParameter("schedule"); //schedule을 가져오는
		    System.out.println(inputDateTimeString);
		    try {
		        Date inputDate = dateFormat.parse(inputDateTimeString);
		        Timestamp schedule = new Timestamp(inputDate.getTime());
		        // inputDateTime을 사용하여 DB에 저장하거나 필요한 작업을 수행합니다.
		        scheduleVO = new ScheduleVO();
		        scheduleVO.setSchedule(schedule);
		        System.out.println("schedule: " + schedule);
		        scheduleVO.setGrp_id(7);
			    scheduleVO.setUser_id("chulsu");
			    scheduleVO.setSche_title(sche_title);
			    scheduleVO.setSche_cont(sche_cont);
			    scheduleVO.setLocation(location);
			    
				scheduleService.addSchedule(scheduleVO);
		    } catch (ParseException e) {
		        System.out.println("날짜 형식 파싱 처리중 오류");
		        e.printStackTrace();
		    }
		    nextPage = "/nemo/group/schedule?group_id=" + Integer.toString(group_id);			// 슬래쉬는 반드시해줘야 인식한다.
			response.sendRedirect(nextPage);
		    
			
		}*//*else if (action.equals("/schCompare")) {
			int group_id = Integer.parseInt(request.getParameter("group_id"));
			String selectedScheduleDate = request.getParameter("selectedScheduleDate");
			String msg = "";
			
			ScheduleService scheduleService = new ScheduleService();
			scheduleVO = new ScheduleVO();
			scheduleVO.setGrp_id("7");
			scheduleVO.setUser_id(user_id);
			ScheduleVO scheduleVO2 = scheduleService.selectScheduletbl(scheduleVO);
			Timestamp schedule = scheduleVO2.getSchedule();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			String scheduleStr = dateFormat.format(schedule);
			if(selectedScheduleDate.equals(scheduleStr)) {
				msg = "1";
			} else {
				msg = "-1";
			}
			nextPage = "/nemo/group/schedule?group_id=" + Integer.toString(group_id) + "?msg=" + msg;			// 슬래쉬는 반드시해줘야 인식한다.
			response.sendRedirect(nextPage);
		}*/
		//RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);	//컨트롤러에서 포워딩해줌
		//dispatcher.forward(request, response);
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
