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
		request.setAttribute("menuSelected", 2);
		//nextPage = "/views/qna/helpQnA.jsp";
		
		QNA_IMG_REPO = this.getClass().getResource("").getPath();
		QNA_IMG_REPO = QNA_IMG_REPO.substring(1, QNA_IMG_REPO.indexOf(".metadata"));
		QNA_IMG_REPO = QNA_IMG_REPO.replace("/", "\\");
		QNA_IMG_REPO += "nemo\\src\\main\\webapp\\qnaImages\\";
		
		System.out.println("요청이름 : " + action);
		try {
			
			
			List<QnaVO> articlesList=new ArrayList<QnaVO>();
			if (action == null || action.equals("/viewQna")) {
				String _section=request.getParameter("section");
				String _pageNum=request.getParameter("pageNum");
				int section=Integer.parseInt((_section == null)?"1":_section);
				int pageNum=Integer.parseInt((_pageNum == null)?"1":_pageNum);
				Map<String, Integer> pagingMap=new HashMap<String, Integer>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				Map articleMap=qnaService.helpQnA(pagingMap);
				articleMap.put("section", section);
				articleMap.put("pageNum", pageNum);
				request.setAttribute("articleMap", articleMap);	
				nextPage="/views/qna/helpQnA.jsp"; 
			}else if(action.equals("/QnAWrite.do")) {
				nextPage="/views/qna/QnAWrite.jsp";
			}else if(action.equals("/addArticle.do")) {
				int qna_id=0;
				Map<String, String> articleMap=upload(request, response);
				String title=articleMap.get("title");
				String content=articleMap.get("content");
				String imageFileName=articleMap.get("imageFileName");
				String nickname=articleMap.get("nicknQnAView");
				qnaVO.setParent_no(0);
				qnaVO.setNickname(nickname);
				qnaVO.setTitle(title);
				qnaVO.setContent(content);
				qnaVO.setImageFileName(imageFileName);
				qna_id=qnaService.addArticle(qnaVO);
				//새글 추가시 이미지를 첨부한 경우에만수행
				if(imageFileName != null && imageFileName.length() != 0) {
					File srcFile=new File(QNA_IMG_REPO + "\\temp\\" + imageFileName);
					File destDir=new File(QNA_IMG_REPO + "\\" + qna_id);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					srcFile.delete();
				}
				out=response.getWriter();
				out.print("<script>");
				out.print("alert('새 글을 추가했습니다.');");
				out.print("location.href='" +request.getContextPath() + "/viewQna/helpQnA.do';");
				out.print("</script>");
				return;
			}else if(action.equals("/QnAView.do")) {
				String qna_id=request.getParameter("qna_id");
				qnaVO=qnaService.viewArticle(Integer.parseInt(qna_id));
				request.setAttribute("article", qnaVO);
				nextPage="/qna/QnAView.jsp";
			}else if(action.equals("/modArticle.do")) {
				Map<String, String> articleMap=upload(request, response);
				int qna_id=Integer.parseInt(articleMap.get("qna_id"));
				String title=articleMap.get("title");
				String nickname=articleMap.get("nickname");
				String content=articleMap.get("content");
				String imageFileName=articleMap.get("imageFileName");
				qnaVO.setQna_id(qna_id);
				qnaVO.setParent_no(0);
				qnaVO.setNickname(nickname);
				qnaVO.setTitle(title);
				qnaVO.setContent(content);
				qnaVO.setImageFileName(imageFileName);
				qnaService.modArticle(qnaVO);
				//이미지를 새로 첨부한 경우에만 수행
				if(imageFileName != null && imageFileName.length() != 0) {
					String originalFileName=articleMap.get("originalFileName");
					File srcFile=new File(QNA_IMG_REPO + "\\temp\\" + imageFileName);
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
				String imageFileName=articleMap.get("imageFileName");
				qnaVO.setParent_no(parent_no);
				//qnaVO.setId("young");관리자 부분
				qnaVO.setTitle(title);
				qnaVO.setContent(content);
				qnaVO.setImageFileName(imageFileName);
				int qna_id=qnaService.addReply(qnaVO);
				if(imageFileName != null && imageFileName.length() != 0) {
					File srcFile=new File(QNA_IMG_REPO + "\\temp\\" + imageFileName);
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
		
		
		
		
		
		
	
	private Map<String, String> upload(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> articleMap = new HashMap<String, String>();
		String encoding="utf-8";
		
		//글 이미지 저장 폴더에 대한 파일 객체를 생성.
		File currentDirPath=new File(QNA_IMG_REPO);
		DiskFileItemFactory factory=new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload=new ServletFileUpload(factory);
		try {
			List items=upload.parseRequest(request);
			for(int i=0; i<items.size(); i++) {
				FileItem fileItem=(FileItem)items.get(i);
				if(fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				}else {
					System.out.println("매개변수이름 : " + fileItem.getFieldName());
					System.out.println("파일(이미지)이름 : " + fileItem.getName());
					System.out.println("파일(이미지)크기 : " + fileItem.getSize() + "bytes");
					if(fileItem.getSize() > 0) {
						int idx=fileItem.getName().lastIndexOf("\\");
						if(idx == -1) {
							idx=fileItem.getName().lastIndexOf("/");
						}
					}
					String fileName=fileItem.getName().substring(idx+1);
					articleMap.put(fileItem.getFieldName(), fileName);
					File uploadFile=new File(currentDirPath + "\\temp\\" + fileName);
					fileItem.write(uploadFile);
				}
			}
		} catch (Exception e) {
			System.out.println("파일 업러드 중 에러");
			e.printStackTrace();
		}
		return articleMap;
	}

}
