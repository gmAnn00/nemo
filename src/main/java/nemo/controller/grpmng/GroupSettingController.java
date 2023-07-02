package nemo.controller.grpmng;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

import nemo.service.group.GroupInfoService;
import nemo.service.grpmng.GroupMangerService;
import nemo.service.grpmng.GroupMangerSettingService;
import nemo.vo.group.GroupVO;
import nemo.vo.user.UserVO;

//뷰와 모델 연결

@WebServlet("/group/manager/setting/*")
public class GroupSettingController extends HttpServlet{
	HttpSession session;
	GroupMangerService groupMangerService;
	GroupMangerSettingService groupMangerSettingService;
	GroupInfoService groupInfoService;
	UserVO userVO;
	GroupVO groupVO;
	private static String GROUP_IMG_REPO;
	private static String GROUP_DEF_IMG;
	Map groupInfo;
	
	@Override
	public void init() throws ServletException {
		groupMangerSettingService=new GroupMangerSettingService();
		groupMangerService=new GroupMangerService();
		groupInfoService=new GroupInfoService();
		userVO=new UserVO();
		groupVO=new GroupVO();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("컨트롤러 도착");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		GROUP_IMG_REPO = this.getClass().getResource("").getPath();
		GROUP_IMG_REPO = GROUP_IMG_REPO.substring(1, GROUP_IMG_REPO.indexOf(".metadata"));
		GROUP_IMG_REPO = GROUP_IMG_REPO.replace("/", "\\");
		GROUP_IMG_REPO += "nemo\\src\\main\\webapp\\groupImages\\";
		
		GROUP_DEF_IMG = this.getClass().getResource("").getPath();
		GROUP_DEF_IMG = GROUP_DEF_IMG.substring(1, GROUP_DEF_IMG.indexOf(".metadata"));
		GROUP_DEF_IMG = GROUP_DEF_IMG.replace("/", "\\");
		GROUP_DEF_IMG += "nemo\\src\\main\\webapp\\images\\free-icon-group-8847475.png";
		
		PrintWriter out;
		String nextPage="";
		String action=request.getPathInfo();
		
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		System.out.println(group_id);
		session=request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		groupInfo=groupInfoService.getGroupInfo(group_id);
		request.setAttribute("groupInfo", groupInfo);
		
		System.out.println("요청 매핑이름: " + action);
		try {
			
			if(action==null||action.equals("/modForm")) {
				
				List<GroupVO> groupList=groupMangerService.groupShow(group_id);
				request.setAttribute("groupList", groupList);
				
				nextPage = "/views/group/groupSetting.jsp";
				
			}
			
			else if(action.equals("/modGroup")) {
				
				Map<String, String> groupMap = upload(request, response);
				
				String grp_name = groupMap.get("grp_name");
				String grp_mng = user_id;
				int mem_no = Integer.parseInt(groupMap.get("mem_no"));
				String grp_zipcode = groupMap.get("grp_zipcode");
				String grp_addr1 = groupMap.get("grp_addr1");
				String grp_addr2 = groupMap.get("grp_addr2");
				String grp_intro = groupMap.get("grp_intro");
				String main_name = groupMap.get("main_name");
				String sub_name = groupMap.get("sub_name");
				String grp_img=groupMap.get("grp_img");
				
				//인원수를 50명 초과해서 넣었을 시
				if(mem_no>50) {
					out = response.getWriter();
					out.print("<script>");
					out.print("alert('그룹 인원수를 초과했습니다 (최대 50명)');");
					out.print("location.href='" + request.getContextPath() + 
							"/group/manager/setting?group_id=" + group_id + "';");
					out.print("</script>");
				} 
				else {
					
					groupVO.setGrp_name(grp_name);
					groupVO.setGrp_mng(grp_mng);
					groupVO.setMem_no(mem_no);
					groupVO.setGrp_zipcode(grp_zipcode);
					groupVO.setGrp_addr1(grp_addr1);
					groupVO.setGrp_addr2(grp_addr2);
					groupVO.setGrp_intro(grp_intro);
					groupVO.setMain_name(main_name);
					groupVO.setSub_name(sub_name);
					groupVO.setGrp_img(grp_img);
					
					groupMangerSettingService.updateGroup(groupVO, group_id);
					System.out.println(groupVO.toString());
				}
								
				//이미지를 새로 첨부한 경우에만 수행
				if(grp_img != null && grp_img.length() != 0) {
					//새 이미지를 글 쓴것처럼 경로에 삽입하고, 기존이미지(oldFile)는 그 경로 위치에서 삭제  
					String originalFileName = groupMap.get("originalFileName");
					File srcFile = new File(GROUP_IMG_REPO + "\\temp\\" + grp_img);
					File destDir = new File(GROUP_IMG_REPO + "\\" + group_id);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					File oldFile = new File(GROUP_IMG_REPO + "\\" + group_id + "\\" + originalFileName);
					oldFile.delete();					
				}
				
				
				
				// 알림창과 수정된 글 보여주기
				out = response.getWriter();
				out.print("<script>");
				out.print("alert('수정했습니다');");
				out.print("location.href='" + request.getContextPath() + 
						"/group/manager/setting?group_id=" + group_id + "';");
				out.print("</script>");
				return;
						
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		
		} catch (Exception e) {
			System.out.println("요청 처리 중 에러!!");
			e.printStackTrace();
		}
		
		
	}
	
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Map<String, String> groupMap = new HashMap<String, String>();
		
		String encoding = "utf-8";
		
		File currentDirPath = new File(GROUP_IMG_REPO);
		File tempDirPath = new File(GROUP_IMG_REPO+"\\temp");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		System.out.println("upload 호출");
		
		try {
			List items = upload.parseRequest(request);
			
			for(int i = 0; i<items.size(); i++) {
				FileItem fileItem = (FileItem)items.get(i);
				if(fileItem.isFormField()) {
					// isFormField() : 글자 데이터냐(글자 데이터가 아니면 파일임)
					// 글자 데이터일 때 이 안 실행
					
					System.out.println(fileItem.getFieldName() + " = " + fileItem.getString(encoding));
					// 키 : 필드명(title, content), 값 : 그 내용
					// 파일 업로드할 이미지와 간이 전송된 새글 관련(제목, 내용) 매개변수를 Map(키, 값)으로 저장
					//System.out.println(fileItem.getFieldName());
				
					groupMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				}
				else {
					File defaultFile = new File(GROUP_DEF_IMG);
					FileUtils.copyFileToDirectory(defaultFile, tempDirPath, false);
					// 이미지일 때 이 안 실행
					System.out.println("필드명: " + fileItem.getFieldName());
					System.out.println("파일(이미지) 이름: " + fileItem.getName());
					System.out.println("파일(이미지) 크기: " + fileItem.getSize() + "bytes");
					
					if(fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}// end of if (idx == -1)
						
						String fileName = fileItem.getName().substring(idx+1);
						System.out.println("파일명: " + fileName);
						// articleMap 에 imageFileName과 실제 이미지 이름 저장
						groupMap.put(fileItem.getFieldName(), fileName);
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						// 실제로 파일을 업로드(쓰는) 명령
						fileItem.write(uploadFile);
					} // end of if(fileItem.getSize() > 0)
					
				} // end of else
			}
			
		} catch (Exception e) {
			System.out.println("파일 업로드 중 에러 : ");
			e.printStackTrace();
		}
		
		return groupMap;
		
	}
	

	
}
