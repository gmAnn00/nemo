package nemo.controller.qna;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	HttpSession session;
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
		PrintWriter out=response.getWriter();
		session=request.getSession();  //23-05-25
		String action=request.getPathInfo();	//요청명을 가져옴.
		String user_id=(String)session.getAttribute("user_id");
		request.setAttribute("menuSelected", 3);
		//nextPage = "/views/qna/helpQnA.jsp";
		
		QNA_IMG_REPO = this.getClass().getResource("").getPath();
		QNA_IMG_REPO = QNA_IMG_REPO.substring(1, QNA_IMG_REPO.indexOf(".metadata"));
		QNA_IMG_REPO = QNA_IMG_REPO.replace("/", "\\");
		QNA_IMG_REPO += "nemo\\src\\main\\webapp\\qnaImages\\";
		if(user_id==null) {
			out.print("<script>");
			out.print("alert('로그인 후 이용해주세요.');");
			out.print("location.href='"+request.getContextPath()+"/login/loginForm';");
			out.print("</script>");
		}
		else {
		System.out.println("요청이름 : " + action);
			try {
				List<QnaVO> articlesList=new ArrayList<QnaVO>();
				if (action == null || action.equals("/viewQna")) {
					// Q&A 리스트
					//HttpSession httpSession = request.getSession(true);
					//httpSession.setAttribute("user_id", "1");
					if (user_id!= null) {
						String _section=request.getParameter("section");
						String _pageNum=request.getParameter("pageNum");
						int section=Integer.parseInt((_section == null)?"1":_section);
						int pageNum=Integer.parseInt((_pageNum == null)?"1":_pageNum);
						Map<String, Integer> pagingMap=new HashMap<String, Integer>();
						pagingMap.put("section", section);
						pagingMap.put("pageNum", pageNum);
						int admin = (Integer) session.getAttribute("admin");
						Map articleMap=qnaService.helpQnA(pagingMap, user_id, admin > 0);
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
					//HttpSession httpSession = request.getSession();
					//String user_id = (String)httpSession.getAttribute("user_id");

					String title=request.getParameter("title");
					String content=request.getParameter("content");

					int article_no=qnaService.getNewArticleNo();
					List<String> fileNameList=getImageFileNameNew(content);
					Boolean isImgExist=Boolean.parseBoolean(request.getParameter("isImgExist"));
					
                    if(isImgExist) {
                        String[] imgName=request.getParameterValues("imageName");
                        List<String> getFileList=null;	//jaon으로 push한 배열 담는 리스트
                        if(imgName.length!=0) {
                        	getFileList=new ArrayList<String>();
                            for(int i=0; i<imgName.length; i++) {
                                System.out.println(imgName[i]);
                                getFileList.add(imgName[i]);
                            }
                        } 
						moveImageDir(fileNameList, getFileList, article_no);
						content=content.replace("/getReviewImage.do?", "/getImage.do?article_no="+article_no+"&");
					}
					qnaVO.setParent_no(0);
					qnaVO.setQna_id(article_no);
					qnaVO.setTitle(title);
					qnaVO.setContent(content);
					qnaVO.setUser_id(user_id);
					qnaService.addArticle(qnaVO);
					//새글 추가시 이미지를 첨부한 경우에만수행
					// 업로드 

					out.print("<script>");
					out.print("alert('새 글을 추가했습니다.');");
					out.print("location.href='" +request.getContextPath() + "/viewQna';");
					out.print("</script>");
					return;
				}else if(action.equals("/qnaCancelAddArticle")) { //글 작성 후 등록 취소
					System.out.println("QnA등록취소여기오냐?");
					
					Boolean isImgExist=Boolean.parseBoolean(request.getParameter("isImgExist"));
					System.out.println("등록취소"+isImgExist);
					
					if(isImgExist) {
						System.out.println("등록취소이미지 있음");
						String[] imgName=imgName=request.getParameterValues("imageName");
						List<String> fileList=null;
						if(imgName.length!=0 || imgName!=null) {
							fileList=new ArrayList<String>();
							for(int i=0; i<imgName.length; i++) {
								System.out.println(imgName[i]);
								fileList.add(imgName[i]);
							}
							deleteTempImg(fileList);
						}
					}
					out.print("<script>");
					out.print("location.href='"+request.getContextPath()+"/viewQna';");
					out.print("</script>");
					return;
					
					
				}else if(action.equals("/modArticleForm.do")) {
					int qna_id=Integer.parseInt(request.getParameter("qna_id"));
					QnaVO QnaVO=qnaService.viewArticle(qna_id);
					request.setAttribute("article", QnaVO);
					nextPage="/views/qna/modQnAWrite.jsp";
					
				}else if(action.equals("/modArticle.do")) {
					int qna_id=Integer.parseInt(request.getParameter("qna_id"));
					String title=request.getParameter("title");
					String content=request.getParameter("content");
					List<String> fileNameListNew=getImageFileNameNew(content);	//수정으로 추가 된 이미지 파일 이름 
					List<String> alreadyExsitfileNameList=getImageFileNameExist(content, qna_id);	//컨텐트에 있는 기존에 있는 이미지 파일 이름 
					List<String> dirExistFileList=dirFileList(qna_id);	//폴더에 이미 있는 파일 이름
					for(String item:fileNameListNew) {
						System.out.println("수정으로 추가된 이미지 파일이름: " + item);
					}
					for(String item:alreadyExsitfileNameList) {
						System.out.println("컨텐트에 추가 : " + item);
					}
					for(String item:dirExistFileList) {
						System.out.println("폴더에 있는애: " + item);
					}
					removeDumy(alreadyExsitfileNameList,dirExistFileList, qna_id);
					
					if(fileNameListNew==null&&alreadyExsitfileNameList==null) { //이미지 파일 이름이 없으면 폴더 삭제
						removeDir(qna_id); 
					}
		
					Boolean isImgExist=Boolean.parseBoolean(request.getParameter("isImgExist"));            
					if(isImgExist) {
                        String[] imgName=request.getParameterValues("imageName");
                        List<String> getFileList=null;	//jaon으로 push한 배열 담는 리스트
                        if(imgName.length!=0) {
                        	getFileList=new ArrayList<String>();
                            for(int i=0; i<imgName.length; i++) {
                                System.out.println(imgName[i]);
                                getFileList.add(imgName[i]);
                            }
                        } 
						moveImageDir(fileNameListNew, getFileList, qna_id);	//템프에 있는거 옮김
						content=content.replace("/getReviewImage.do?", "/getImage.do?article_no="+qna_id+"&");
					}

					qnaVO.setQna_id(qna_id);
					qnaVO.setTitle(title);
					qnaVO.setContent(content);
					qnaService.modArticle(qnaVO);
					
					out=response.getWriter();
					out.print("<script>");
					out.print("alert('글을 수정했습니다.');");
					out.print("location.href='" +request.getContextPath() + "/viewQna/QnAView.do?qna_id=" + qna_id + "';");
					out.print("</script>");
					return;
				//23-05-25
				}else if(action.equals("/removeArticle.do")) {
					int qna_id=Integer.parseInt(request.getParameter("qna_id"));
					List<Integer> qna_idList=qnaService.removeArticle(qna_id);
					for(int _qna_id:qna_idList) {
						removeDir(_qna_id);
					}
					out=response.getWriter();
					out.print("<script>");
					out.print("alert('글을 삭제했습니다.');");
					out.print("location.href='" +request.getContextPath() + "/viewQna';");
					out.print("</script>");
					return;
					
					//답변
				}else if(action.equals("/replyForm.do")) {
					String req=request.getParameter("parent_no");
					if(req == null) return;
					int parent_no=Integer.parseInt(req);
					session=request.getSession();
					session.setAttribute("parent_no", parent_no);
					nextPage="/views/qna/replyForm.jsp";
				}else if(action.equals("/addReply.do")) {
					session=request.getSession();
					int parent_no=(Integer)session.getAttribute("parent_no");
					session.removeAttribute("parent_no");
					String title=request.getParameter("title");
					String content=request.getParameter("content");

					int qna_id=qnaService.getNewArticleNo();
					List<String> fileNameList=getImageFileNameNew(content);
					Boolean isImgExist=Boolean.parseBoolean(request.getParameter("isImgExist"));
					
                    if(isImgExist) {
                        String[] imgName=request.getParameterValues("imageName");
                        List<String> getFileList=null;	//jaon으로 push한 배열 담는 리스트
                        if(imgName.length!=0) {
                        	getFileList=new ArrayList<String>();
                            for(int i=0; i<imgName.length; i++) {
                                System.out.println(imgName[i]);
                                getFileList.add(imgName[i]);
                            }
                        } 
						moveImageDir(fileNameList, getFileList, qna_id);
						content=content.replace("/getReviewImage.do?", "/getImage.do?article_no="+qna_id+"&");
					}

					qnaVO.setParent_no(parent_no);
					qnaVO.setUser_id(user_id);
					System.out.println("답변 user_id"+user_id);
					qnaVO.setQna_id(qna_id);
					qnaVO.setTitle(title);
					qnaVO.setContent(content);
					qnaService.addReply(qnaVO);
					out.print("<script>");
					out.print("alert('답글이 등록되었습니다.');");
					out.print("location.href='"+request.getContextPath()+"/viewQna';");
					out.print("</script>");
					return;
				} else if(action.equals("/search")) {
					String filter=request.getParameter("filter");
					String keyword=request.getParameter("keyword");
					
					String _section=request.getParameter("section");
					String _pageNum=request.getParameter("pageNum");
					int section=Integer.parseInt((_section == null)?"1":_section);
					int pageNum=Integer.parseInt((_pageNum == null)?"1":_pageNum);
					Map<String, Integer> pagingMap=new HashMap<String, Integer>();
					pagingMap.put("section", section);
					pagingMap.put("pageNum", pageNum);
					int admin = (Integer) session.getAttribute("admin");
					
					Map articleMap=qnaService.searchQnA(pagingMap, filter, keyword, admin > 0, user_id);
					articleMap.put("section", section);
					articleMap.put("pageNum", pageNum);
					request.setAttribute("articleMap", articleMap);
					nextPage="/views/qna/helpQnA.jsp";
				}
				RequestDispatcher dispatcher =request.getRequestDispatcher(nextPage);// 디스패처로 자료를 넣음
			    dispatcher.forward(request, response); // 그 자료를 포워드 즉 넘겨준는 거임
			} catch (Exception e) {
				System.out.println("요청 처리 중 에러");
				e.printStackTrace();
			}
		}
	}
	//temp 에서 이미지 폴더 이동 하는 메소드 
	private void moveImageDir(List<String> fileNameList, List<String> getFileList, int article_no) {
	//articleno로 폴더 생성
	//tmp에서 articleno폴더로 이동
		try {
			QNA_IMG_REPO=this.getClass().getResource("").getPath();
			QNA_IMG_REPO=QNA_IMG_REPO.substring(1,QNA_IMG_REPO.indexOf(".metadata"));
			QNA_IMG_REPO=QNA_IMG_REPO.replace("/", "\\");
			QNA_IMG_REPO+="nemo\\src\\main\\webapp\\qnaImages";
			
			//dumy파일 지우기 
			for(String item:fileNameList) {
				getFileList.remove(item);
			}
			if(getFileList!=null && getFileList.size()!=0) {
				for(String dumy:getFileList) {
					System.out.println("더미파일지우기 뉴파일:"+dumy);
					File srcFile=new File(QNA_IMG_REPO+"\\temp\\"+dumy);
					srcFile.delete();
				}
			}

			if(fileNameList!=null && fileNameList.size()!=0) {
				for(String imgName:fileNameList) {
					File srcFile=new File(QNA_IMG_REPO+"\\temp\\"+imgName);
					File destDir=new File(QNA_IMG_REPO+"\\"+article_no);
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					srcFile.delete();
				}	
			}
			
		} catch (Exception e) {
			System.out.println("temp에서 이미지 파일 복사하는 중 에러");
			e.printStackTrace();
		}
	}
	
	private void removeDumy(List<String> newfileNameList, List<String> oldFileList, int article_no) {
		try {
			QNA_IMG_REPO=this.getClass().getResource("").getPath();
			QNA_IMG_REPO=QNA_IMG_REPO.substring(1,QNA_IMG_REPO.indexOf(".metadata"));
			QNA_IMG_REPO=QNA_IMG_REPO.replace("/", "\\");
			QNA_IMG_REPO+="nemo\\src\\main\\webapp\\qnaImages\\"+article_no+"\\";
			
			for(String old:oldFileList) {
				System.out.println("기존 파일: "+old);
			}
			//dumy파일 지우기 
			for(String item:newfileNameList) {
				oldFileList.remove(item);
				System.out.println("새로 입력받은거 : "+item);
			}
			for(String old:oldFileList) {
				System.out.println("기존 파일(remove후): "+old);
			}
			if(oldFileList!=null && oldFileList.size()!=0) {
				for(String dumy:oldFileList) {
					System.out.println("더미파일지우기 기존 파일:"+dumy);
					File srcFile=new File(QNA_IMG_REPO+dumy);
					srcFile.delete();
				}
			}
			
		} catch (Exception e) {
			System.out.println("게시글폴더에서 기존 이미지 파일 지우는 중 에러");
			e.printStackTrace();
		}
	}
	
	//게시글 폴더에서 파일 리스트 받아오기
	private List dirFileList(int article_no) {
		List fileList=new ArrayList();
		try {
			QNA_IMG_REPO=this.getClass().getResource("").getPath();
			QNA_IMG_REPO=QNA_IMG_REPO.substring(1,QNA_IMG_REPO.indexOf(".metadata"));
			QNA_IMG_REPO=QNA_IMG_REPO.replace("/", "\\");
			QNA_IMG_REPO+="nemo\\src\\main\\webapp\\qnaImages\\"+article_no;
			
			File dir=new File(QNA_IMG_REPO);
			String[] fileNameArr=dir.list();
			for(String fileName:fileNameArr) {
				fileList.add(fileName);
			}
		} catch (Exception e) {
			System.out.println("게시글 폴더에서 파일 리스트 받아 오는 중 에러");
			e.printStackTrace();
		}
		return fileList;
	}
	
	//글 등록 취소시 temp폴더에서 img 삭제
	private void deleteTempImg(List<String> fileList) {
		try {
			System.out.println("등록취소시 이미지 삭제 하는 메소드");
			QNA_IMG_REPO=this.getClass().getResource("").getPath();
			QNA_IMG_REPO=QNA_IMG_REPO.substring(1,QNA_IMG_REPO.indexOf(".metadata"));
			QNA_IMG_REPO=QNA_IMG_REPO.replace("/", "\\");
			QNA_IMG_REPO+="nemo\\src\\main\\webapp\\qnaImages\\temp\\";
			if(fileList!=null && fileList.size()!=0) {
				for(String imgName:fileList) {
					File srcFile=new File(QNA_IMG_REPO+imgName);
					srcFile.delete();
				}
			}
		}catch (Exception e) {
			System.out.println("글 등록 취소시 temp 폴더에서 이미지 삭제 중 에러");
			e.printStackTrace();
		}
	}

	//글 삭제 시 이미지 폴더 삭제하는 메소드
	private void removeDir(int article_no) {
		try {
			QNA_IMG_REPO=this.getClass().getResource("").getPath();
			QNA_IMG_REPO=QNA_IMG_REPO.substring(1,QNA_IMG_REPO.indexOf(".metadata"));
			QNA_IMG_REPO=QNA_IMG_REPO.replace("/", "\\");
			QNA_IMG_REPO+="nemo\\src\\main\\webapp\\qnaImages\\";
			File file=new File(QNA_IMG_REPO+article_no);
			
			if(!file.exists()) {
				System.out.println("폴더 없음");
				return;
			}
			if(file.isDirectory()) { //폴더이면
				File[] files=file.listFiles();
				if(files!=null && files.length>0) {
					for(int i=0; i<files.length; i++) {
						files[i].delete();
					}
				}
				file.delete();
			}
		}catch (Exception e) {
			System.out.println("폴더 삭제 중 에러");
			e.printStackTrace();
		}
	}
	
	
	//content에서 이미지이름 추출하는 메소드
	private List getImageFileNameNew(String content) {
		
		System.out.println("new 이미지 추출");
		List fileName=new ArrayList();
		Pattern pattern=Pattern.compile("<img[^>]*src=[\\\"']?([^>\\\"']+)[\\\"']?[^>]*>");
		Matcher matcher=pattern.matcher(content);
		while(matcher.find()) {
			String[] array = matcher.group(1).split("getReviewImage\\.do\\?savedFileName=");
			if(array.length>1) {
				fileName.add(array[1]);	
			}
			
		}
		
		return fileName;
	}
	
	//content에서 이미지이름 추출하는 메소드
	private List getImageFileNameExist(String content, int article_no) {
		
		System.out.println("old 이미지 추출");
		List fileName=new ArrayList();
		Pattern pattern=Pattern.compile("<img[^>]*src=[\\\"']?([^>\\\"']+)[\\\"']?[^>]*>");
		Matcher matcher=pattern.matcher(content);
		while(matcher.find()) {
			String[] array = matcher.group(1).split("getImage\\.do\\?article\\_no="+article_no+"&amp\\;savedFileName=");
			System.out.println("aaa:"+array[0]);
			if(array.length>1) {
				fileName.add(array[1]);	
				System.out.println("추출 됐나 old: "+array[1]);
			}
		}
		
		return fileName;
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
