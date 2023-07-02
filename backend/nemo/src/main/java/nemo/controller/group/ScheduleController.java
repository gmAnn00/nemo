package nemo.controller.group;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import nemo.dao.group.ScheduleDAO;
import nemo.service.group.GroupInfoService;
import nemo.service.group.ScheduleService;
import nemo.vo.group.GroupVO;
import nemo.vo.group.ScheduleVO;
import nemo.vo.user.UserVO;

@WebServlet("/group/schedule/*")
public class ScheduleController extends HttpServlet {
	ScheduleDAO dao;
	HttpSession session;
	ScheduleVO scheduleVO;
	ScheduleService scheduleService;
	GroupInfoService groupInfoService;
	Map groupInfo;
	
	public void init(ServletConfig config) throws ServletException {
		scheduleService = new ScheduleService();
		dao = new ScheduleDAO(); // ScheduleDAO 객체 생성
		groupInfoService=new GroupInfoService();
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
				
		int grp_id = Integer.parseInt(request.getParameter("group_id"));
        String user_id = (String) session.getAttribute("user_id");
        System.out.println("user_id=" + user_id);
        String action = request.getPathInfo();
        System.out.println("요청 매핑이름: " + action);
        
        GroupVO groupVO = new GroupVO();
		groupInfo=groupInfoService.getGroupInfo(grp_id);
		request.setAttribute("groupInfo", groupInfo);
       
		if(action==null || action.equals("/schedule")) {	//일정 페이지로 이동
			int group_id = Integer.parseInt(request.getParameter("group_id"));
			//다가오는 일정 조회
			List<Map> commingScheduleList = new ArrayList<Map>();
			
			String currentYear = (String)request.getParameter("year");
		 	String currentMonth = (String)request.getParameter("month");
		 	String currentYM = currentYear + "/" + currentMonth;
		 	
		 	commingScheduleList = scheduleService.selectComSchedule(group_id);
			request.setAttribute("commingScheduleList", commingScheduleList);

			nextPage = "/views/group/schedule.jsp";			// 슬래쉬는 반드시해줘야 인식한다.
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);	//컨트롤러에서 포워딩해줌
			dispatcher.forward(request, response);
			
		}else if(action.equals("/schCompare")) {	//일정 페이지에서 달력의 날짜를 눌렀을때 
			try {
				int group_id = Integer.parseInt(request.getParameter("group_id")); 
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String selScheDate = (String)request.getParameter("selScheDate");
				
				SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
				
				SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
				
				//System.out.println("받은 날짜 : " + selScheDate);
			    //Date selDate = dateFormat.parse(selScheDate);
			    		//Date.valueOf(selScheDate);
			    		//dateFormat.parse(selScheDate);
			    System.out.println("누른 날짜 : " + selScheDate);
			    scheduleVO = scheduleService.schCompare(group_id, selScheDate);

			    // 현재 유저가 이 일정에 참석했는지 참석이면 true, 아니면 false 반환
			    Boolean isAttend = scheduleService.isAttend(user_id, group_id, selScheDate);
			    //request.setAttribute("isAttend", isAttend);
			    
			    // 이 일정의 참석자 가져옴
			    List<UserVO> attendUserList = new ArrayList<UserVO>();
			    attendUserList = scheduleService.attendUsers(group_id, selScheDate);
			    
			    System.out.println(scheduleVO.getSche_title());
			    System.out.println(scheduleVO.getSchedule());
			    System.out.println(scheduleVO.getSche_cont());
			    System.out.println(scheduleVO.getLocation());
			    
			    //제이슨객체 생성		
			    JsonObject scheduleObject = new JsonObject();
			    scheduleObject.addProperty(action, isAttend);
			    
			    Gson gson = new Gson();
			    String attendUserJson = gson.toJson(attendUserList);
			    
		        scheduleObject.addProperty("sche_title", scheduleVO.getSche_title());
		        scheduleObject.addProperty("schedule", scheduleVO.getSchedule() != null ? dateFormat.format(scheduleVO.getSchedule()) : null);  // 포맷팅된 날짜를 JSON 문자열로 변환하여 추가
		        scheduleObject.addProperty("sche_time", scheduleVO.getSchedule() != null ? timeFormat.format(scheduleVO.getSchedule()) : null);
		        scheduleObject.addProperty("sche_dateTime", scheduleVO.getSchedule() != null ? dateTimeFormat.format(scheduleVO.getSchedule()) : null);
		        scheduleObject.addProperty("sche_cont", scheduleVO.getSche_cont());
		        scheduleObject.addProperty("location", scheduleVO.getLocation());
		        scheduleObject.addProperty("isAttend", isAttend);
		        scheduleObject.addProperty("attendUserJson", attendUserJson);		        
		       
		        String ScheduleInfo = gson.toJson(scheduleObject);
		        out.print(ScheduleInfo);
		    
			} catch (Exception e) {
				System.out.println("날짜 매칭중 오류");
		        e.printStackTrace();
			}
			
		}else if (action.equals("/addSchedule")) {	//일정 추가 부분
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
		        scheduleVO.setGrp_id(group_id);
			    scheduleVO.setUser_id(user_id);
			    scheduleVO.setSche_title(sche_title);
			    scheduleVO.setSche_cont(sche_cont);
			    scheduleVO.setLocation(location);
			    boolean scheduleResult = scheduleService.addSchedule(scheduleVO);
				if(scheduleResult) {
					System.out.println("result=" + scheduleResult);
					out.print("<script>");
					out.print("alert('일정을 추가했습니다.');");
					out.print("location.href='/nemo/group/schedule?group_id=" + Integer.toString(group_id) + "';");
					out.print("</script>");
				}else {
					System.out.println("result=" + scheduleResult);
					out.print("<script>");
					out.print("alert('위치를 설정하지않았거나 일정이 이미 존재합니다.');");
					out.print("location.href='/nemo/group/schedule?group_id=" + Integer.toString(group_id) + "';");
					out.print("</script>");
				}
			} catch (ParseException e) {
		        System.out.println("일정 생성 중 오류");
		        e.printStackTrace();
		    }
		   
		}else if (action.equals("/delSchedule")) {
			int group_id = Integer.parseInt(request.getParameter("group_id"));
			System.out.println("delSchedule = "+request.getParameter("schedule"));
			String scheduleStr = "";
			String dateStr = request.getParameter("date");
			String timeStr = request.getParameter("time");
			scheduleStr = dateStr + " " + timeStr;
			System.out.println("controller scheduleStr = " + scheduleStr);
			SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd KK:mm a", Locale.KOREA);
			Date schedule = new Date();
			try {
				schedule = formatter.parse(scheduleStr);
				System.out.println("CONTROLLER schedule = " + schedule);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		    dao = new ScheduleDAO();		    
		    dao.delSchedule(group_id, schedule);
		    nextPage = "/nemo/group/schedule?group_id=" + Integer.toString(group_id);
		    response.sendRedirect(nextPage);
		   
		    
		}else if (action.equals("/modSchedule")) {	//일정 수정
			int group_id = Integer.parseInt(request.getParameter("group_id"));
			
		    String sche_title = request.getParameter("scheduleTitle_new");
		    String sche_cont = request.getParameter("sche_cont_new");
		    String location = request.getParameter("location_new");

			String scheduleStrNew = request.getParameter("sche_dateTime_new");
			String scheduleStrOld = request.getParameter("sche_dateTime_old");

			System.out.println("controller scheduleStr = " + scheduleStrNew);
		    try {
		    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
				Date scheduleNew = formatter.parse(scheduleStrNew);
				Date scheduleOld = formatter.parse(scheduleStrOld);
		       
		        Timestamp scheduleNewTS = new Timestamp(scheduleNew.getTime());
		        Timestamp scheduleOldTS = new Timestamp(scheduleOld.getTime());
		        
		        scheduleVO = new ScheduleVO();
		        scheduleVO.setSchedule(scheduleNewTS);
		        //System.out.println("schedule: " + schedule);
		        scheduleVO.setGrp_id(group_id);
		        scheduleVO.setUser_id(user_id);
		        scheduleVO.setSche_title(sche_title);
		        scheduleVO.setSche_cont(sche_cont);
		        scheduleVO.setLocation(location);
		        
		        boolean scheduleResult = scheduleService.modSchedule(scheduleVO, scheduleOldTS);
		        if (scheduleResult) {
		            System.out.println("result=" + scheduleResult);
		            out.print("<script>");
		            out.print("alert('일정을 수정했습니다.');");
		            out.print("location.href='/nemo/group/schedule?group_id=" + Integer.toString(group_id) + "';");
		            out.print("</script>");
		        } else {
		            System.out.println("result=" + scheduleResult);
		            out.print("<script>");
		            out.print("alert('위치를 설정하지 않았거나 일정이 이미 존재합니다.');");
		            out.print("location.href='/nemo/group/schedule?group_id=" + Integer.toString(group_id) + "';");
		            out.print("</script>");
		        }
		    } catch (ParseException e) {
		        System.out.println("일정 수정 중 오류");
		        e.printStackTrace();
		    }
		    
		}else if (action.equals("/joinSchedule")) {
			int group_id = Integer.parseInt(request.getParameter("group_id"));
			System.out.println("delSchedule = "+request.getParameter("schedule"));
			String scheduleStr = "";
			String dateStr = request.getParameter("date");
			String timeStr = request.getParameter("time");
			scheduleStr = dateStr + " " + timeStr;
			System.out.println("controller scheduleStr = " + scheduleStr);
			SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd KK:mm a", Locale.KOREA);
			Date schedule = new Date();
			try {
				schedule = formatter.parse(scheduleStr);
				System.out.println("CONTROLLER schedule = " + schedule);
			} catch (ParseException e) {
				e.printStackTrace();
			}
						
		    dao = new ScheduleDAO();
		 
		    
		    scheduleService.joinSchedule(user_id ,group_id, schedule);
		    out.print("<script>");
		    out.print("alert('일정에 참석을 신청하였습니다.');");
		    out.print("location.href='/nemo/group/schedule?group_id=" + Integer.toString(group_id) + "';");
		    out.print("</script>");
		   
		}else if (action.equals("/cancelSchedule")) {
			int group_id = Integer.parseInt(request.getParameter("group_id"));
			System.out.println("cancelSchedule = "+request.getParameter("schedule"));
			String scheduleStr = "";
			String dateStr = request.getParameter("date");
			String timeStr = request.getParameter("time");
			scheduleStr = dateStr + " " + timeStr;
			System.out.println("controller scheduleStr = " + scheduleStr);
			SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd KK:mm a", Locale.KOREA);
			Date schedule = new Date();
			try {
				schedule = formatter.parse(scheduleStr);
				System.out.println("CONTROLLER schedule = " + schedule);
			} catch (ParseException e) {
				e.printStackTrace();
			}
						
		    dao = new ScheduleDAO();
		    		    
		    scheduleService.cancelSchedule(user_id ,group_id, schedule);
		    out.print("<script>");
		    out.print("alert('일정 참석을 취소하였습니다.');");
		    out.print("location.href='/nemo/group/schedule?group_id=" + Integer.toString(group_id) + "';");
		    out.print("</script>");
		    		    
		}else if(action.equals("/ajaxCalendar")) {
						 	
		 	List<String> scheduleDateList = new ArrayList();
		 	
		 	int group_id = Integer.parseInt(request.getParameter("group_id"));
		 	String currentYear = (String)request.getParameter("year");
		 	String currentMonth = (String)request.getParameter("month");
		 	String currentYM = currentYear + "/" + currentMonth;
		 	
		 	scheduleDateList = scheduleService.getScheduleDate(group_id, currentYM);
		 	Gson gson = new Gson();
		 	String scheduleDateJson = gson.toJson(scheduleDateList);
		 	out.print(scheduleDateJson);		 	
		 	
		 	return;
		 	
		}
	}
}