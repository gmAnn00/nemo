package nemo.controller.group;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.service.group.CreateGroupService;
import nemo.vo.group.GroupVO;


@WebServlet("/group/createGroup/*")
public class CreateGroupController extends HttpServlet {
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
		session=request.getSession();

		GroupVO groupVO = new GroupVO();
		String user_id = "";
		user_id = (String) session.getAttribute("user_id");
		
		String action = request.getPathInfo();
		System.out.println("요청 매핑이름 : " + action);
		
		if(action.equals("/form")) {
			String nextPage = "/group/createGroupForm.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}else if (action.equals("/create")) {
			System.out.println(request.getParameter("app_st"));
			CreateGroupService createGroupService = new CreateGroupService();
			
			String grp_name = request.getParameter("grp_name");
			String grp_mng = user_id;
			int mem_no = Integer.parseInt(request.getParameter("mem_no"));
			String grp_zipcode = request.getParameter("grp_zipcode");
			String grp_addr1 = request.getParameter("grp_addr1");
			String grp_addr2 = request.getParameter("grp_addr2");
			String grp_intro = request.getParameter("grp_intro");
			int app_st = Integer.parseInt(request.getParameter("app_st"));
			String main_name = request.getParameter("main_name");
			String sub_name = request.getParameter("sub_name");
			//String grp_mimg = 
			//String grp_pimg = 
			
			groupVO.setGrp_name(grp_name);
			groupVO.setGrp_mng(grp_mng);
			groupVO.setMem_no(mem_no);
			groupVO.setGrp_zipcode(app_st);
			groupVO.setGrp_addr1(grp_addr1);
			groupVO.setGrp_addr2(grp_addr2);
			groupVO.setGrp_intro(grp_intro);
			groupVO.setApp_st(app_st);
			groupVO.setMain_name(main_name);
			groupVO.setSub_name(sub_name);
			

			int group_id = createGroupService.createGroup(groupVO); 
			String nextPage="nemo/group/groupMain?group_id="+ Integer.toString(group_id);
			//response.sendRedirect("/nemo/group/groupMain?group_id=1");
			response.sendRedirect(nextPage);

		}
		
	} // end of doHandle
	
	

}
