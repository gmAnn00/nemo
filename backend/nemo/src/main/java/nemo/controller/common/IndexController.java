package nemo.controller.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.service.common.IndexService;
import nemo.vo.group.GroupVO;
import nemo.vo.user.InterestsVO;
import nemo.vo.user.UserVO;

@WebServlet("/index")
public class IndexController extends HttpServlet {
	HttpSession session;
	IndexService indexService;
	
	@Override
	public void init() throws ServletException {
		indexService = new IndexService();
	}

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
		
		String user_id = (String) session.getAttribute("user_id");
		
		// 로그인 안했을 시 아무(랜덤) 소모임 보여줌
		if(user_id == null) {
			List<GroupVO> randomGroupsList = indexService.findRandomGroup();
			request.setAttribute("randomGroupsList", randomGroupsList);
			
		}else {
			// 로그인 했을 떄 관심사/가까운 소모임 보여줌
			UserVO userVO = new UserVO();
			userVO = indexService.findUserById(user_id);
			
			List<InterestsVO> interestsList = new ArrayList<InterestsVO>();
			interestsList = indexService.findInterests(user_id);
			
			// 관심사 소모임
			List<GroupVO> interestGroupsList = indexService.findInterestGroups(interestsList);
			int cnt =  4-interestGroupsList.size();
			if(interestGroupsList.size() < 4) {
				List<GroupVO> temp1 = indexService.findRandomGroup();
				for(int i = 0; i < cnt; i++) {
					System.out.println("i="+i);
					interestGroupsList.add(temp1.get(i));
				}
			}
			System.out.println("interestGroupsList=" + interestGroupsList.size());
			request.setAttribute("interestGroupsList", interestGroupsList);
			
			// 가까운 소모임
			List<GroupVO> nearGroupsList = indexService.findNearGroups(userVO.getUser_addr1());
			cnt = 4-nearGroupsList.size();
			if(nearGroupsList.size() < 4) {
				List<GroupVO> temp2 = indexService.findRandomGroup();
				for(int i = 0; i < cnt; i++) {
					nearGroupsList.add(temp2.get(i));
				}
			}
			System.out.println("nearGroupsList=" + nearGroupsList.size());
			request.setAttribute("nearGroupsList", nearGroupsList);

			
		}

		nextPage="/views/index.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);

	}

}
