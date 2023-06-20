package nemo.controller.mypage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.service.mypage.MyGroupService;
import nemo.service.mypage.MyProfileService;
import nemo.vo.group.GroupVO;
import nemo.vo.mypage.UserVO;

@WebServlet("/mypage/myGroupList")
public class MyGroupListController extends HttpServlet {
	HttpSession session;
	MyGroupService myGroupService;


	public void init() throws ServletException {
		myGroupService = new MyGroupService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	public void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		session = request.getSession();
		String nextPage = "";

		String action = request.getPathInfo();
		String user_id = (String) session.getAttribute("user_id");

		if (user_id != null) {
			// 로그인 상태일때만 수행
			try {
				if (action == null || action.equals("/myGroup")) {
					user_id = (String)session.getAttribute("user_id");
					//내가 리더인 소모임	조회
					//GroupVO mngGroupVO = new GroupVO();					
					//mngGroupVO = myGroupService.getManagerGrpId(user_id);					
					List<GroupVO> mngGroupList = new ArrayList<>();
					mngGroupList = myGroupService.getManagerGrpId(user_id);
					
					//일반 회원인 소모임 조회
					//GroupVO userGroupVO = new GroupVO();
					//userGroupVO = myGroupService.getUserGrpId(user_id);
					List<GroupVO> userGroupList = new ArrayList<>();
					userGroupList = myGroupService.getUserGrpId(user_id);
					
					
					
					//찜한 소모임 조회 BOOKMARK_TBL
					
					//뿌려주기 위해 필요한 정보 : 소모임이미지, 대분류, 소분류, 모임이름, 지역 1  
					//지역은 substring으로 oo시 oo구 까지만 나오게 할 수 있나..? 흠...
					
					
					//request.setAttribute("userVO", userVO);
					
					nextPage="/views/mypage/myGroupList.jsp";
					
				} else if(action.equals("/goGroup")) {
					//해당 그룹 소개페이지로 이동 ...
					//클릭한 그룹 번호 가져오기
					//그룹 번호 매개변수로 같이 보내기
					//nextPage="/views/group/groupInfo?" + 매개변수;
					
					
				} else if(action.equals("/clickBookmark")) {
					//북마크 찜, 해제
					//이거는 바로 없애지 말고, 새로고침 할 때까지는 보이도록 만들자
					
				}
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
				dispatcher.forward(request, response);

			} catch (Exception e) {
				System.out.println("컨트롤러 요청 처리 중 에러 !");
				e.printStackTrace();
			}
		} else {
			// 로그인 상태가 아니라면 index페이지로 이동하게 함
			request.setAttribute("msg", "isnotlogin");
			nextPage = "/nemo/index";
			response.sendRedirect(nextPage);
		}

	}
}
