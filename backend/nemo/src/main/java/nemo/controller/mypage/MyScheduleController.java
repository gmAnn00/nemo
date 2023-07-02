package nemo.controller.mypage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import nemo.service.mypage.MyScheduleService;
import nemo.vo.group.GroupVO;
import nemo.vo.group.ScheduleVO;
import nemo.vo.user.InterestsVO;


@WebServlet("/mypage/mySchedule/*")
public class MyScheduleController extends HttpServlet {
	HttpSession session;
	PrintWriter out;
	MyScheduleService myScheduleService;
	ScheduleVO scheduleVO;
	GroupVO groupVO;
	
	
	public void init() throws ServletException {
		myScheduleService = new MyScheduleService();
		scheduleVO = new ScheduleVO();
		groupVO = new GroupVO();
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
		
		if(user_id != null) {
			//로그인 상태일때만 수행
			try {	
				//일정 조회	
				if(action == null || action.equals("/mySchedule")) {
					user_id = (String)session.getAttribute("user_id");
					List<Map> scheduleList = new ArrayList();				
					scheduleList = myScheduleService.selectSchedule(user_id);
					//시간 포맷 분리하기
				 	SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일");
				 	SimpleDateFormat time = new SimpleDateFormat("HH시 mm분");
				 	//일단 timestamp 타입으로 받은 schedule이 찍히긴 하는지 확인
				 	/*
				 	ScheduleVO schedule = (ScheduleVO) scheduleList.get(0).get("scheduleVO");
				 	Timestamp tsp = schedule.getSchedule();
				 	String scheduleDate = date.format(tsp);
				 	String scheduleTime = time.format(tsp);
				 	System.out.println(scheduleDate);
				 	System.out.println(scheduleTime);
				 	*/
				 
				 	
					request.setAttribute("scheduleList", scheduleList);
					
					
					//일정 조회 페이지로 이동					
					nextPage="/views/mypage/mySchedule.jsp";
					
					RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
					dispatcher.forward(request, response);
				} else if(action.equals("/ajaxSchedule")) {
					//아작스
				 	System.out.println("아작스 받았습니다");

				 	PrintWriter out = response.getWriter();
				 	//out.print("alert('안녕하세요');");
				 	
				 	List<Map> scheduleDateList = new ArrayList();
				 	
				 	//매개변수로 이번달과 올 해를 가져옴..
				 	//?year=' + year + '&month=' + month			 	
				 	String currentYear = (String)request.getParameter("year");
				 	String currentMonth = (String)request.getParameter("month");
				 	String currentYM = currentYear + "/" + currentMonth;
				 	System.out.println("currentYM"+currentYM);
				 	//이번달 일정있는 날짜 리스트를 받아옴
				 	scheduleDateList = myScheduleService.getScheduleDate(user_id, currentYM);
				 	
				 	//json객체를 어떤 식으로 만들까?				 	
				 	JSONObject scheduleDateObject = new JSONObject();
				 	
				 	//아래 형변환 메서드 하나 만들어서 vo로 꺼내오...
				 	/*
				 	for(int i = 0; i <scheduleDateList.size()-1; i++) {
				 		
				 		scheduleDateObject.put("user_id", ((ScheduleVO) scheduleDateList.get(i).get("scheduleVO")).getUser_id());
				 		scheduleDateObject.put("grp_id", ((ScheduleVO) scheduleDateList.get(i).get("scheduleVO")).getGrp_id());				 	
				 		scheduleDateObject.put("day", scheduleDateList.get(i).get("day"));	
				 	}
				 	*/
				 	JSONArray scheduleArr=new JSONArray();

					for(int i = 0; i <scheduleDateList.size()-1; i++) {
						System.out.println("for문입니다");
				 	   JSONObject scheduleInfo = new JSONObject();
				 	   scheduleInfo.put("day", scheduleDateList.get(i).get("day"));
				 	   System.out.println(scheduleDateList.get(i).get("day"));
				 	   scheduleArr.add(scheduleInfo);
				 	}

				 	JSONObject totalSchedule = new JSONObject();
				 	totalSchedule.put("schedules", scheduleArr);
				 	String jsonInfo = totalSchedule.toJSONString();
				 	out.print(jsonInfo);

				 	
				 	return;
				}
				
			} catch (Exception e) {
				System.out.println("컨트롤러 요청 처리 중 에러 !");
				e.printStackTrace();
			}
			
		} else {
			//로그인 상태가 아니라면 index페이지로 이동하게 함			
			nextPage="/nemo/index";
			  
	        out.print("<script>");
	        out.print("alert('로그인 상태가 아닙니다.');");
	        out.print("location.href='" + nextPage + "';");
	        out.print("</script>");
	        
			//response.sendRedirect(nextPage);
		}
			
	}		
	
}// doHandle() End
