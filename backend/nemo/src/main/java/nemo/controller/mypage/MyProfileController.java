package nemo.controller.mypage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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

import nemo.service.mypage.MyInterestService;
import nemo.service.mypage.MyProfileService;
import nemo.vo.mypage.InterestVO;
import nemo.vo.user.UserVO;


@WebServlet("/mypage/*")
public class MyProfileController extends HttpServlet {
	HttpSession session;
	MyProfileService myProfService;
	MyInterestService myIntesInterestService;
	UserVO userVO;
	PrintWriter out;
	
	private static String USER_IMG_REPO;
	private static String USER_IMG_DEF;
	
	public void init() throws ServletException {
		myProfService = new MyProfileService();
		myIntesInterestService = new MyInterestService();
		userVO = new UserVO();
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

		USER_IMG_REPO = this.getClass().getResource("").getPath();
		//System.out.println(USER_IMG_REPO);
		USER_IMG_REPO = USER_IMG_REPO.substring(1, USER_IMG_REPO.indexOf(".metadata"));
		USER_IMG_REPO = USER_IMG_REPO.replace("/", "\\");
		USER_IMG_REPO += "nemo\\src\\main\\webapp\\userImages\\";
		
		USER_IMG_DEF = this.getClass().getResource("").getPath();
		USER_IMG_DEF = USER_IMG_DEF.substring(1, USER_IMG_DEF.indexOf(".metadata"));
		USER_IMG_DEF = USER_IMG_DEF.replace("/", "\\");
		USER_IMG_DEF += "nemo\\src\\main\\webapp\\images\\dall.png";
		

		
		
		//MyProfileDAO proDAO = new MyProfileDAO();		
		
		//session.setAttribute("user_id", "kim");
		//session.setAttribute("nickname", "김철수닉넴");
		//session.removeAttribute("user_id");
		
		//System.out.println("doHandle");
		System.out.println("action="+action);
		
		if(user_id != null) {				
			//로그인 상태일때만 수행
			try {			
				//조회	
				if(action == null || action.equals("/myprofile")) {
					user_id = (String)session.getAttribute("user_id");		
					//id로 회원정보 찾는 메소드				
					userVO = myProfService.searchProfileById(user_id);
					
					//회원정보 담기
					request.setAttribute("userVO", userVO);
					//System.out.println("controller 유저정보조회" + userVO);
					
					//관심사 담기(list로 담기)
					List<InterestVO> interestsList = myIntesInterestService.searchInterestById(user_id);
					request.setAttribute("interestsList", interestsList);
					
					//프로필 조회 페이지로 이동
					//nextPage="/WEB-INF/mypage/myProfile.jsp";
					nextPage="/views/mypage/myProfile.jsp";
					
					RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
					dispatcher.forward(request, response);
					
				} else if(action.equals("/modProfileForm")) {
					//회원정보 찾아서 수정화면으로 가기					
					userVO = myProfService.searchProfileById(user_id);
					
					//이메일 분리하기
					String email = userVO.getEmail();
					int idx = email.indexOf("@");
					String emailId = email.substring(0, idx);
					String emailDomain = email.substring(idx+1);
					
					//기존 정보 담기
					request.setAttribute("userVO", userVO);
					request.setAttribute("emailId", emailId);
					request.setAttribute("emailDomain", emailDomain);					
					
					//회원정보 수정 페이지로 이동
					nextPage="/views/mypage/modifyProfile.jsp";	
					RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
					dispatcher.forward(request, response);
				
				} else if(action.equals("/modProfileForm")) {
					/*
					user_id = request.getParameter("user_id");
					String password = request.getParameter("password");
					String user_name = request.getParameter("user_name");				
					String nickname = request.getParameter("nickname");
					String zipcode = request.getParameter("zipcode");
					String user_addr1 = request.getParameter("user_addr1");
					String user_addr2 = request.getParameter("user_addr2");
					String phone = request.getParameter("phone");				
					Date birthdate = Date.valueOf(request.getParameter("birthdate"));
					//String user_img = request.getParameter("user_img");
					String emailId = request.getParameter("emailId");
					String emailDomain = request.getParameter("emailDomain");*/
					
					//수정 페이지에서 넘어온 정보 담아서 DB에 보내기
					Map<String, String>userProfileMap = upload(request, response);
					
					user_id = userProfileMap.get("user_id");
					String password = userProfileMap.get("password");
					String user_name = userProfileMap.get("user_name");				
					String nickname = userProfileMap.get("nickname");
					String zipcode = userProfileMap.get("zipcode");
					String user_addr1 = userProfileMap.get("user_addr1");
					String user_addr2 = userProfileMap.get("user_addr2");
					String phone = userProfileMap.get("phone");				
					Date birthdate = Date.valueOf(userProfileMap.get("birthdate"));
					String user_img = null;
					if(userProfileMap.get("user_img")==null) {
						user_img="dall.png";
					}else {
						user_img = userProfileMap.get("user_img");
					}
					//String user_img = request.getParameter("user_img");
					String emailId = userProfileMap.get("emailId");
					String emailDomain = userProfileMap.get("emailDomain");
					//이메일 합치기
					String email = emailId + "@" + emailDomain;
					
					//dString user_img = userProfileMap.get("user_img");					
					
					System.out.println("생년월일 잘 받았나?" + birthdate);
					
					if(user_img != null && user_img.length() != 0) {
						File scrFile = new File(USER_IMG_REPO + "\\temp\\" + user_img);
						File destDir = new File(USER_IMG_REPO + "\\" + user_id);
						destDir.mkdir();
						FileUtils.moveFileToDirectory(scrFile, destDir, true);
						scrFile.delete();
						//옛날 이미지 삭제
						String originalFileName = (String)userProfileMap.get("originalFileName");
						System.out.println("originalFileName" + originalFileName);
						File oldFile = new File(USER_IMG_REPO + "\\" + originalFileName);
						oldFile.delete();
					}
					String message;
					message = "<script>";
					message += "alert('이미지가 수정되었습니다.');";
					message += "location.href='nemo/mypage/myprofile';";
					message += "</script>";	
					
										
					UserVO userVO = new UserVO(user_id, password, user_name, nickname, zipcode, user_addr1, user_addr2, phone, email, birthdate, user_img);
					myProfService.modProfile(userVO);
									
					
					request.setAttribute("msg", "modified");
					nextPage="/nemo/mypage/myprofile";
					response.sendRedirect(nextPage);
					
				} else if(action.equals("/userImgUpload")) {
					//프로필 이미지 수정
					//System.out.println("프로필 이미지 수정 " + user_id);
					Map<String, String>userImageMap = upload(request, response);
					
					String user_img = userImageMap.get("user_img");
					
					if(userImageMap.get("user_img") == null) {
						user_img="dall.png";
					}else {
						user_img = userImageMap.get("user_img");
					}
					String message;
					System.out.println("user_img확인" + user_img);
					//이미지가 있을 때 수정
					if(user_img != null && user_img.length() != 0) {
						File scrFile = new File(USER_IMG_REPO + "\\temp\\" + user_img);
						File destDir = new File(USER_IMG_REPO + "\\" + user_id);
						destDir.mkdir();
						FileUtils.moveFileToDirectory(scrFile, destDir, true);
						scrFile.delete();
						//옛날 이미지 삭제
						String originalFileName = (String)userImageMap.get("originalFileName");
						System.out.println("originalFileName" + originalFileName);
						File oldFile = new File(USER_IMG_REPO + "\\" + user_id + "\\" + originalFileName);
						oldFile.delete();
					}
				
					UserVO userVO = new UserVO(user_id, user_img);
					myProfService.modUserImg(userVO);					

					nextPage= "/nemo/mypage/myprofile";
					
					//request.setAttribute("msg", "modImg");
					/*
					message = "<script>";
					message += "alert('이미지가 수정되었습니다.');";
					message += "location.href='" + nextPage + "';";
					message += "</script>";	
					response.sendRedirect(message);
					*/
					
					//response.sendRedirect(nextPage);
					
			        out.print("<script>");
			        out.print("alert('이미지가 수정되었습니다.');");
			        out.print("location.href='" + nextPage + "';");
			        out.print("</script>");
			        
					
				} else if(action.equals("/delUserForm")) {
				
					nextPage= "/views/mypage/delUser.jsp";
					
					RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
					dispatcher.forward(request, response);
					
				} else if(action.equals("/delUser")) {				
					//회원 탈퇴
					
					user_id = (String)session.getAttribute("user_id");
					System.out.println("user_id="+user_id);
					//입력받은 비밀번호
					String delpassword = (String)request.getParameter("delpassword");
					System.out.println("delpwd=" + delpassword);
					
			        //UserVO userVO = new UserVO(user_id, delpassword);
			        myProfService.delMember(user_id, delpassword);
									
					//알림창
			        System.out.println(user_id + "회원 탈퇴");
					//request.setAttribute("msg", "deleted");
			        
			        nextPage="/nemo/index";
			        
			        out.print("<script>");
			        out.print("alert('회원 탈퇴 되었습니다.');");
			        out.print("location.href='" + nextPage + "';");
			        out.print("</script>");
			        
					
					//response.sendRedirect(nextPage);

				}
//			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
//			dispatcher.forward(request, response);
				
			} catch (Exception e) {
				System.out.println("컨트롤러 요청 처리 중 에러 !");
				e.printStackTrace();
			}
			
		} else {
			//로그인 상태가 아니라면 index페이지로 이동하게 함
			request.setAttribute("msg", "isnotlogOn");
			nextPage="/nemo/index";
			response.sendRedirect(nextPage);
		}
			
	}// doHandle() End
	
	//이미지 파일 업로드
		// = "D:\\mornstarY\\teamProject\\imageUplode";
		
		private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//return값 없으면 에러
			Map<String, String> userImageMap = new HashMap<String, String>();
			String encoding="utf-8";
			
			//글 이미지 저장 폴더에 대한 파일 객체를 생성
			File currnetDirPath = new File(USER_IMG_REPO);
			File tempDirPath = new File(USER_IMG_REPO + "\\temp");
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//저장소 세팅
			factory.setRepository(currnetDirPath);
			//최대 크기. (더 큰 파일이 들어오면, 버퍼를 이용해서 처리하게 됨)
			factory.setSizeThreshold(1024*1024);		
			ServletFileUpload upload = new ServletFileUpload(factory);
					
			//파일 관련은 항상 try-catch
			try {
				//이미지 첨부가 request객체 파일로 날아옴
				List items = upload.parseRequest(request);
				
				for(int i=0; i<items.size(); i++) {
					FileItem fileItem = (FileItem)items.get(i);
					if(fileItem.isFormField()) {
					//form에 관련된 것인지 => (글자 데이터)
						//System.out.println(fileItem.getFieldName() + "=" + 
						//					fileItem.getString(encoding));					
						//파일 업로드할 이미지와 같이 전송된 새글 관련(제목, 내용) 매개변수를 Map(키, 값)으로 저장
						userImageMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
						
					} else {
						File defaultFile = new File(USER_IMG_DEF);
						FileUtils.copyFileToDirectory(defaultFile, tempDirPath, false);
						
						//form 관련이 아닌지 => (이미지)
						System.out.println("매개변수이름 : " + fileItem.getFieldName());
						System.out.println("파일(이미지)이름 : " + fileItem.getName());
						System.out.println("파일(이미지)크기 : " + fileItem.getSize() + "bytes");
						
						
						if(fileItem.getSize() > 0) {
							int idx = fileItem.getName().lastIndexOf("\\");
							if(idx == -1) {
								//idx == -1 : 경로가 c:/ 로 / 처럼 표시될 때
								idx = fileItem.getName().lastIndexOf("/");
							}
							
							//fileName : 실제 첨부한 이미지 이름
							String fileName = fileItem.getName().substring(idx + 1);
							// DB에 이미지 fileName담기
							userImageMap.put(fileItem.getFieldName(), fileName);
							
							//파일 처리(파일 경로 처리)
							File uploadFile = new File(currnetDirPath + "\\temp\\" + fileName);
							// 실제 저장
							fileItem.write(uploadFile);
						}
					}// else End
				}			
				
			} catch (Exception e) {
				System.out.println("이미지 파일 업로드 중 에러!!");
				e.printStackTrace();
			}			
			
			return userImageMap;
		}

		
		
}
