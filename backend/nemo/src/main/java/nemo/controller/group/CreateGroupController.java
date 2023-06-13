package nemo.controller.group;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import nemo.service.group.CreateGroupService;
import nemo.vo.group.GroupVO;


@WebServlet("/group/createGroup/*")
public class CreateGroupController extends HttpServlet {
	private static String GROUP_IMG_REPO;
	HttpSession session;
	
	public void init(ServletConfig config) throws ServletException {
		
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
		session=request.getSession();
		
		GROUP_IMG_REPO = this.getClass().getResource("").getPath();
		GROUP_IMG_REPO = GROUP_IMG_REPO.substring(1, GROUP_IMG_REPO.indexOf(".metadata"));
		GROUP_IMG_REPO = GROUP_IMG_REPO.replace("/", "\\");
		GROUP_IMG_REPO += "nemo\\src\\main\\webapp\\groupImages\\";
		
		GroupVO groupVO = new GroupVO();
		String user_id = "";
		user_id = (String) session.getAttribute("user_id");
		
		String action = request.getPathInfo();
		System.out.println("요청 매핑이름 : " + action);
		System.out.println(GROUP_IMG_REPO);
		
		if(action.equals("/form")) {
			String nextPage = "/group/createGroupForm.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}else if (action.equals("/create")) {
			//System.out.println(request.getParameter("app_st"));
			CreateGroupService createGroupService = new CreateGroupService();
			
			Map<String, String>groupMap = upload(request, response);
			
			/*
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
			*/
			String grp_name = groupMap.get("grp_name");
			String grp_mng = user_id;
			int mem_no = Integer.parseInt(groupMap.get("mem_no"));
			
			String grp_zipcode = groupMap.get("grp_zipcode");
			String grp_addr1 = groupMap.get("grp_addr1");
			String grp_addr2 = groupMap.get("grp_addr2");
			String grp_intro = groupMap.get("grp_intro");
			int app_st = 0;
			//int app_st = Integer.parseInt(groupMap.get("app_st"));
			if(groupMap.get("app_st") != null) {
				app_st = Integer.parseInt(groupMap.get("app_st"));
			}
			String main_name = groupMap.get("main_name");
			String sub_name = groupMap.get("sub_name");
			String grp_img = groupMap.get("grp_img");

			groupVO.setGrp_name(grp_name);
			groupVO.setGrp_mng(grp_mng);
			groupVO.setMem_no(mem_no);
			groupVO.setGrp_zipcode(grp_zipcode);
			groupVO.setGrp_addr1(grp_addr1);
			groupVO.setGrp_addr2(grp_addr2);
			groupVO.setGrp_intro(grp_intro);
			groupVO.setApp_st(app_st);
			groupVO.setMain_name(main_name);
			groupVO.setSub_name(sub_name);
			groupVO.setGrp_img(grp_img);
			
			System.out.println(groupVO.toString());
			
			
			int group_id = createGroupService.createGroup(groupVO); 
			
			// 새 글 등록시 이미지를 첨부할 경우 if문 내부를 수행
			if(grp_img != null && grp_img.length() != 0) {
				File srcFile = new File(GROUP_IMG_REPO + "\\temp\\" + grp_img);
				File destDir = new File(GROUP_IMG_REPO + "\\" + group_id);
				// (ARTICLE_IMG_REPO + "\\" + articleNo) 경로에 폴더를 만든다
				destDir.mkdir();
				// temp에 있던 이미지를 destDir 폴더로 이동시킨다.
				// false: 안옮김, true: 옮김
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
				// temp의 이미지 삭제
				srcFile.delete();
			}
			
			//String nextPage="/nemo/group/groupMain?group_id="+ Integer.toString(group_id);
			//response.sendRedirect("/nemo/group/groupMain?group_id=1");
			//response.sendRedirect(nextPage);

		}
		
	} // end of doHandle


	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Map<String, String> groupMap = new HashMap<String, String>();
		
		String encoding = "utf-8";
		
		File currentDirPath = new File(GROUP_IMG_REPO);
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
				}else {
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
