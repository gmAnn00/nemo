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

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import nemo.dao.qna.QnaDAO;
import nemo.service.qna.QnaService;
import nemo.vo.qna.QnaVO;

@WebServlet("/qna/*")
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
		
		QNA_IMG_REPO = this.getClass().getResource("").getPath();
		QNA_IMG_REPO = QNA_IMG_REPO.substring(1, QNA_IMG_REPO.indexOf(".metadata"));
		QNA_IMG_REPO = QNA_IMG_REPO.replace("/", "\\");
		QNA_IMG_REPO += "nemo\\src\\main\\webapp\\qnaImages\\";
		
		System.out.println("요청이름 : " + action);
		try {
			List<QnaVO> articlesList=new ArrayList<QnaVO>();
			if (action == null || action.equals("/helpQnA.do")) {
				String _section=request.getParameter("section");  //23-05-25
				String _pageNum=request.getParameter("pageNum");
				int section=Integer.parseInt((_section == null)?"1":_section);
				int pageNum=Integer.parseInt((_pageNum == null)?"1":_pageNum);
				Map<String, Integer> pagingMap=new HashMap<String, Integer>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				Map articleMap=qnaService.helpQnA(pagingMap);
				articleMap.put("section", session);
				request.setAttribute("articleMap", articleMap);
				nextPage="/view/qna/helpQnA.jsp"; 
			}else if(action.equals("/글쓰기 창.do")) {
				nextPage="/viewBoard/글쓰기 창.jsp";
			}else if(action.equals("/추가하기.do")) {
				int qna_id=0;
				Map<String, String> articleMap=upload(request, response);
				String title=articleMap.get("title");
				String content=articleMap.get("content");
				String imageFileName=articleMap.get("imageFileName");
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
					FileUtils.moveFileToDirectory(srcFile, destDir, true);  //false 안옮김 , frue 옮김
					srcFile.delete();
				}
				out=response.getWriter();
				out.print("<script>");
				out.print("alert('새 글을 추가했습니다.');");
				out.print("location.href='" +request.getContextPath() + "/board/listArticles.do';");
				out.print("</script>");
				return;
			}else if(action.equals("/viewArticle.do")) {
				String articleNo=request.getParameter("articleNo");
				qnaVO=qnaService.viewArticle(Integer.parseInt(articleNo));
				request.setAttribute("article", qnaVO);
				nextPage="/qna/viewArticle.jsp";
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
				out.print("location.href='" +request.getContextPath() + "/qna/viewArticle.do?qna_id=" + qna_id + "';");
				out.print("</script>");
				return;
			//23-05-25
			}else if(action.equals("/removeArticle.do")) {
				int qna_id=Integer.parseInt(request.getParameter("qna_id"));
				List<Integer> qna_idList=qnaService.removeArticle(qna_id);
				for(int _qna_id:qna_idList) {
					File imgDir=new File(QNA_IMG_REPO + "\\" + _qna_id);
					if(imgDir.exists()) {
						FileUtils.deleteDirectory(imgDir);
					}
				}
				out=response.getWriter();
				out.print("<script>");
				out.print("alert('글을 삭제했습니다.');");
				out.print("location.href='" +request.getContextPath() + "/qna/listArticles.do';");
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
				int qna_id=qnaService.addReply(qna_id);
				if(imageFileName != null && imageFileName.length() != 0) {
					File srcFile=new File(QNA_IMG_REPO + "\\temp\\" + imageFileName);
					File destDir=new File(QNA_IMG_REPO + "\\" + qna_id);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
				out=response.getWriter();
				out.print("<script>");
				out.print("alert('답글을 추가했습니다.');");
				out.print("location.href='" +request.getContextPath() + "/qna/viewArticle.do?articleNo=" + articleNo + "';");
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
		
		
		
		
		
		/*List<QnaVO> qnaList = qnaDao.selectPagesQnas(0);
		
		String nextPage="";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		HttpSession session;
		String action=request.getPathInfo();  //요청명 가져오기
		request.setAttribute("menuSelected", 2);
		nextPage = "/views/qna/helpQnA.jsp";
		System.out.println("요청이름 : " + action);
		
		try {
			RequestDispatcher dispatcher =request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
			List<QnaVO> qnasList=new ArrayList<QnaVO>();
			if(action == null || action.equals("helpQnA.do")) {
				String _section=request.getParameter("section");
				String _pageNum=request.getParameter("pageNum");
				int section=Integer.parseInt((_section == null)?"1":_section);
				int pageNum=Integer.parseInt((_pageNum == null)?"1":_pageNum);
				Map<String, Integer> pagingMap=new HashMap<String, Integer>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				Map qnaMap=QnaService.helpQnA(pagingMap);
				qnaMap.put("section", section);
				qnaMap.put("pageNum", pageNum);
				request.setAttribute("qnaMap", qnaMap);
				nextPage="/views/qna/helpQnA.jsp"; 
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}*/
	
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> qnaMap = new HashMap<String, String>();
		String encoding="utf-8";
		
		//글 이미지 저장 폴더에 대한 파일 객체를 생성.
		File currentDirPath=new File(ARTICLE_IMG_REPO);
		DiskFileItemFactory factory=new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload=new ServletFileUpload(factory);
		try {
			List<E> items=upload.parseRequest(request);
			for(int i=0; i<irems.size(); i++) {
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

}
