package nemo.controller.mypage;

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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import nemo.service.mypage.MyScheduleService;
import nemo.vo.group.GroupVO;
import nemo.vo.group.ScheduleVO;


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
		session = request.getSession();
		String nextPage = "";
		
		out = response.getWriter();

		String action = request.getPathInfo();				
		String user_id = (String)session.getAttribute("user_id");
		
		//액션명 확인
		System.out.println("action= " + action);
		
		if(user_id != null) {
			//로그인 상태일때만 수행
			try {	
				//다가오는 일정 조회	
				if(action == null) {
					//List<Map> scheduleList = new ArrayList();
					List<Map> commingScheduleList = new ArrayList();
					
					user_id = (String)session.getAttribute("user_id");					

					String currentYear = (String)request.getParameter("year");
				 	String currentMonth = (String)request.getParameter("month");
				 	String currentYM = currentYear + "/" + currentMonth;					
										
					//scheduleList = myScheduleService.selectSchedule(user_id, currentYM);
					commingScheduleList = myScheduleService.selectComSchedule(user_id);
					
					/*
					//시간 포맷 분리하기
				 	SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일");
				 	SimpleDateFormat time = new SimpleDateFormat("HH시 mm분");
				 	//일단 timestamp 타입으로 받은 schedule이 찍히긴 하는지 확인				 	
				 	ScheduleVO schedule = (ScheduleVO) scheduleList.get(0).get("scheduleVO");
				 	Timestamp tsp = schedule.getSchedule();
				 	String scheduleDate = date.format(tsp);
				 	String scheduleTime = time.format(tsp);
				 	System.out.println(scheduleDate);
				 	System.out.println(scheduleTime);
				 	*/				 				 	
					
					//request.setAttribute("scheduleList", scheduleList);
					request.setAttribute("commingScheduleList", commingScheduleList);
										
					//일정 조회 페이지로 이동					
					nextPage="/views/mypage/mySchedule.jsp";					
					RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
					dispatcher.forward(request, response);
					
				} else if(action.equals("/ajaxSchedule")) {					
				 	//System.out.println("아작스 받았습니다");//(달력용)
				 	PrintWriter out = response.getWriter();				 	
				 	
				 	List<Map> scheduleDateList = new ArrayList();
				 					 	
				 	//?year=' + year + '&month=' + month : 매개변수로 이번달과 올 해를 가져옴			 	
				 	String currentYear = (String)request.getParameter("year");
				 	String currentMonth = (String)request.getParameter("month");
				 	String currentYM = currentYear + "/" + currentMonth;
				 	System.out.println("currentYM" + currentYM);
				 	//이번달 일정있는 날짜 리스트를 받아옴
				 	scheduleDateList = myScheduleService.getScheduleDate(user_id, currentYM);	
				 	
				 	JSONArray scheduleArr = new JSONArray();

					for(int i = 0; i <scheduleDateList.size(); i++) {						
				 	   JSONObject scheduleInfo = new JSONObject();
				 	   scheduleInfo.put("day", scheduleDateList.get(i).get("day"));
				 	   //System.out.println(scheduleDateList.get(i).get("day"));				 	   
				 	   scheduleArr.add(scheduleInfo);
				 	}

				 	JSONObject totalSchedule = new JSONObject();
				 	totalSchedule.put("schedules", scheduleArr);
				 	String jsonInfo = totalSchedule.toJSONString();
				 	
				 	//System.out.println("jsonInfo=" + jsonInfo);
				 	out.print(jsonInfo);
				 	
				 	return;
				 	
				} else if(action.equals("/ajaxthisMSchedule")) {					
					 	//이번달 모~든 일정 조회
					 	PrintWriter out = response.getWriter();
					 	//?year=' + year + '&month=' + month : 매개변수로 이번달과 올 해를 가져옴
					 	String currentYear = (String)request.getParameter("year");
					 	String currentMonth = (String)request.getParameter("month");
					 	String currentYM = currentYear + "/" + currentMonth;
					 	System.out.println("currentYM" + currentYM);
					 	
					 	List<Map> scheduleList = new ArrayList();
					 	scheduleList = myScheduleService.selectSchedule(user_id, currentYM);
					 	
					 	String thisMSList = new Gson().toJson(scheduleList);
					 	out.print(thisMSList);
					 	
					 	//request.setAttribute("scheduleList", scheduleList);
					 	
					 	
					 	/*
					 	for(int i = 0; i <scheduleDateList.size()-1; i++) {				 		
					 		scheduleDateObject.put("user_id", ((ScheduleVO) scheduleDateList.get(i).get("scheduleVO")).getUser_id());
					 		scheduleDateObject.put("grp_id", ((ScheduleVO) scheduleDateList.get(i).get("scheduleVO")).getGrp_id());				 	
					 		scheduleDateObject.put("day", scheduleDateList.get(i).get("day"));	
					 	}
					 	*/
					 	
					 	/*
					 	JSONArray scheduleArr = new JSONArray();

						for(int i = 0; i <scheduleList.size(); i++) {						
						 	JSONObject scheduleInfo = new JSONObject();
						 	scheduleInfo.put("grp_name", scheduleList.get(i).get("grp_name"));
						 	scheduleInfo.put("grp_img", scheduleList.get(i).get("grp_img"));
						 	scheduleInfo.put("scheduleDate", scheduleList.get(i).get("scheduleDate"));
						 	scheduleInfo.put("scheduleMonth", scheduleList.get(i).get("scheduleMonth"));
						 	scheduleInfo.put("scheduleTime", scheduleList.get(i).get("scheduleTime"));						 	
						 	scheduleInfo.put("user_id", ((ScheduleVO) scheduleList.get(i).get("scheduleVO")).getUser_id());
						 	scheduleInfo.put("schedule", ((ScheduleVO) scheduleList.get(i).get("scheduleVO")).getSchedule());
						 	scheduleInfo.put("grp_id", ((ScheduleVO) scheduleList.get(i).get("scheduleVO")).getSchedule());
						 	scheduleInfo.put("sche_title", ((ScheduleVO) scheduleList.get(i).get("scheduleVO")).getSche_title());
						 	scheduleInfo.put("sche_cont", ((ScheduleVO) scheduleList.get(i).get("scheduleVO")).getSche_cont());
						 	scheduleInfo.put("location", ((ScheduleVO) scheduleList.get(i).get("scheduleVO")).getLocation());
						 	
					 	   //System.out.println(scheduleDateList.get(i).get("day"));
					 	   scheduleArr.add(scheduleInfo);
					 	}

					 	//JSONObject totalSchedule = new JSONObject();
					 	//totalSchedule.put("thisMschedules", scheduleArr);
					 	String jsonTMScheInfo = scheduleArr.toJSONString();
					 	
					 	//System.out.println("jsonInfo=" + jsonInfo);
					 	out.print(jsonTMScheInfo);
					 	*/
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