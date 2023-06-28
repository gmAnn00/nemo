package nemo.controller.qna;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

import nemo.dao.qna.QnaDAO;
import nemo.service.qna.QnaService;
import nemo.vo.qna.QnaVO;

@WebServlet("/viewQna/*")
public class QnaController extends HttpServlet {
	QnaService qnaService;
	QnaVO qnaVO;
	private int idx;
	private static String QNA_IMG_REPO;
	
	
	QnaDAO qnaDAO;
	private Object upload;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		qnaDAO = new QnaDAO();
		qnaService=new QnaService();
		qnaVO=new QnaVO();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage="";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		HttpSession session;  //23-05-25
		String action=request.getPathInfo();	//요청명을 가져옴.
		request.setAttribute("menuSelected", 3);
		//nextPage = "/views/qna/helpQnA.jsp";
		
		QNA_IMG_REPO = this.getClass().getResource("").getPath();
		QNA_IMG_REPO = QNA_IMG_REPO.substring(1, QNA_IMG_REPO.indexOf(".metadata"));
		QNA_IMG_REPO = QNA_IMG_REPO.replace("/", "\\");
		QNA_IMG_REPO += "nemo\\src\\main\\webapp\\qnaImages\\";
		
		System.out.println("요청이름 : " + action);
		try {
			List<QnaVO> articlesList=new ArrayList<QnaVO>();
			if (action == null || action.equals("/viewQna")) {
				// Q&A 리스트
				HttpSession httpSession = request.getSession(true);
				//httpSession.setAttribute("user_id", "1");
				if (httpSession.getAttribute("user_id") != null) {
					String _section=request.getParameter("section");
					String _pageNum=request.getParameter("pageNum");
					int section=Integer.parseInt((_section == null)?"1":_section);
					int pageNum=Integer.parseInt((_pageNum == null)?"1":_pageNum);
					Map<String, Integer> pagingMap=new HashMap<String, Integer>();
					pagingMap.put("section", section);
					pagingMap.put("pageNum", pageNum);
					int admin = (Integer) httpSession.getAttribute("admin");
					Map articleMap=qnaService.helpQnA(pagingMap, (String) httpSession.getAttribute("user_id"), admin > 0);
					articleMap.put("section", section);
					articleMap.put("pageNum", pageNum);
					request.setAttribute("articleMap", articleMap);	
					nextPage="/views/qna/helpQnA.jsp"; 
				}
				else {
					out=response.getWriter();
					out.print("<script>");
					out.print("alert('로그인이 필요한 페이지입니다.');");
					out.print("location.href='" +request.getContextPath() + "/login';");
					//out.print("<script>alert('로그인이 필요한 페이지입니다.'); location.href='/nemo/index'</script>");
					out.print("</script>");
					return;
				}
			}else if(action.equals("/QnAWrite.do")) {
				// Q&A 작성
					nextPage="/views/qna/QnAWrite.jsp";
					
					
					
			}else if(action.equals("/QnAView.do")) {
				// Q&A 1건 조회
				HttpSession httpSession = request.getSession(true);
				String qna_id=request.getParameter("qna_id");
				qnaVO=qnaService.viewArticle(Integer.parseInt(qna_id));
				request.setAttribute("article", qnaVO);
				request.setAttribute("admin", httpSession.getAttribute("admin"));
				nextPage="/views/qna/QnAView.jsp";
				
				//추가
			}else if(action.equals("/addArticle.do")) {
				HttpSession httpSession = request.getSession();
				String user_id = (String)httpSession.getAttribute("user_id");
				int qna_id=0;
				Map<String, String> articleMap=upload(request, response);
				String title=articleMap.get("writeTitle");
				String content=articleMap.get("writeContent");
				String qna_img=articleMap.get("writeQna_img");
				qnaVO.setParent_no(0);
				qnaVO.setTitle(title);
				qnaVO.setContent(content);
				qnaVO.setQna_img(qna_img);
				qnaVO.setUser_id(user_id);
				qna_id=qnaService.addArticle(qnaVO);
				//새글 추가시 이미지를 첨부한 경우에만수행
				// 업로드 
				if(qna_img != null && qna_img.length() != 0) {
					File srcFile=new File(QNA_IMG_REPO + "\\temp\\" + qna_img);
					File destDir=new File(QNA_IMG_REPO + "\\" + qna_id);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, false);
					srcFile.delete();
				}
				out=response.getWriter();
				out.print("<script>");
				out.print("alert('새 글을 추가했습니다.');");
				out.print("location.href='" +request.getContextPath() + "/viewQna/viewQna';");
				out.print("</script>");
				return;
			}else if(action.equals("/modArticle.do")) {
				Map<String, String> articleMap=upload(request, response);
				int qna_id=Integer.parseInt(articleMap.get("qna_id"));
				String title=articleMap.get("title");
				String user_id=articleMap.get("user_id");
				String nickname=articleMap.get("nickname");
				String content=articleMap.get("content");
				String qna_img=articleMap.get("qna_img");
				qnaVO.setQna_id(qna_id);
				qnaVO.setParent_no(0);
				qnaVO.setUser_id(user_id);
				qnaVO.setNickname(nickname);
				qnaVO.setTitle(title);
				qnaVO.setContent(content);
				qnaVO.setQna_img(qna_img);
				qnaService.modArticle(qnaVO);
				
				//이미지를 새로 첨부한 경우에만 수행
				if(qna_img != null && qna_img.length() != 0) {
					String originalFileName=articleMap.get("originalFileName");
					File srcFile=new File(QNA_IMG_REPO + "\\temp\\" + qna_img);
					File destDir=new File(QNA_IMG_REPO + "\\" + qna_id);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					File oldFile=new File(QNA_IMG_REPO + "\\" + qna_id + "\\" + originalFileName);
					oldFile.delete();
				}
				out=response.getWriter();
				out.print("<script>");
				out.print("alert('글을 수정했습니다.');");
				out.print("location.href='" +request.getContextPath() + "/viewQna/QnAView.do?qna_id=" + qna_id + "';");
				out.print("</script>");
				return;
			//23-05-25
			}else if(action.equals("/removeArticle.do")) {
				int qna_id=Integer.parseInt(request.getParameter("qna_id"));
				List<Integer> qna_idList=qnaService.removeArtilce(qna_id);
				for(int _qna_id:qna_idList) {
					File imgDir=new File(QNA_IMG_REPO + "\\" + _qna_id);
					if(imgDir.exists()) {
						FileUtils.deleteDirectory(imgDir);
					}
				}
				out=response.getWriter();
				out.print("<script>");
				out.print("alert('글을 삭제했습니다.');");
				out.print("location.href='" +request.getContextPath() + "/viewQna/helpQnA.do';");
				out.print("</script>");
				return;
				
				//답변
			}else if(action.equals("/replyForm.do")) {
				String req=request.getParameter("parent_no");
				if(req == null) return;
				int parent_no=Integer.parseInt(req);
				session=request.getSession();
				session.setAttribute("parent_no", parent_no);
				nextPage="/qna/replyFrom.jsp";
			}else if(action.equals("/addReply.do")) {
				session=request.getSession();
				int parent_no=(Integer)session.getAttribute("parent_no");
				session.removeAttribute("parent_no");
				Map<String, String> articleMap=upload(request, response);
				String title=articleMap.get("title");
				String content=articleMap.get("content");
				String qna_img=articleMap.get("qna_img");
				qnaVO.setParent_no(parent_no);
				//qnaVO.setId("young");관리자 부분
				qnaVO.setTitle(title);
				qnaVO.setContent(content);
				qnaVO.setQna_img(qna_img);
				int qna_id=qnaService.addReply(qnaVO);
				if(qna_img != null && qna_img.length() != 0) {
					File srcFile=new File(QNA_IMG_REPO + "\\temp\\" + qna_img);
					File destDir=new File(QNA_IMG_REPO + "\\" + qna_id);
					destDir.mkdirs();

				}
				out=response.getWriter();
				out.print("<script>");
				out.print("alert('답글을 추가했습니다.');");
				out.print("location.href='" +request.getContextPath() + "/qna/QnAView.do?articleNo=" + qna_id + "';");
				out.print("</script>");
				return;
			}
			RequestDispatcher dispatcher =request.getRequestDispatcher(nextPage);// 디스패처로 자료를 넣음
		    dispatcher.forward(request, response); // 그 자료를 포워드 즉 넘겨준는 거임
		} catch (Exception e) {
			System.out.println("요청 처리 중 에러");
			e.printStackTrace();
		}
	}
			
	//이미지 파일 업로드 + 새글 관련 정보 저장
	private Map<String, String> upload(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> articleMap = new HashMap<String, String>();
		String encoding="utf-8";
		
		//글 이미지 저장 폴더에 대한 파일 객체를 생성.
		File currentDirPath = new File(QNA_IMG_REPO);
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
					
					
					System.out.println(fileItem.getFieldName() + " = " + fileItem.getString(encoding));
					
				
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
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
						articleMap.put(fileItem.getFieldName(), fileName);
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
		
		return articleMap;
		
	}

}
