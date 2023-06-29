
package nemo.controller.board;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import nemo.dao.board.BoardDAO;
import nemo.service.board.BoardService;
import nemo.service.board.CommentService;
import nemo.vo.board.BoardVO;
import nemo.vo.board.CommentVO;
import nemo.vo.group.GroupVO;



@WebServlet("/group/board/*")
public class BoardController extends HttpServlet {
	private static String ARTICLE_IMG_DIR;
	BoardService boardService;
	CommentService commentService;
	BoardVO boardVO;
	CommentVO commentVO;
	Map groupInfo;
	HttpSession session;
	
	public void init(ServletConfig config) throws ServletException {
		boardService=new BoardService();
		commentService=new CommentService();
		boardVO=new BoardVO();
		commentVO = new CommentVO();
		groupInfo=new HashMap();
		
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

		PrintWriter out=response.getWriter();
		
		String action=request.getPathInfo();	//요청명 가져오는 메소드 
		System.out.println("요청 매핑이름: " + action); //요청명 출력
		
		
		String nextPage=null;		
		session=request.getSession();
		String user_id=(String)session.getAttribute("user_id");
		
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		groupInfo=boardService.getGroupInfo(group_id);
		request.setAttribute("groupInfo", groupInfo);
	
		if (boardService.checkAdmin(user_id)) {
			request.setAttribute("isAdmin", "true");
		} else {
			request.setAttribute("isAdmin", "false");
		}
		
		if(user_id==null) {
			out.print("<script>");
			out.print("alert('로그인 후 이용해주세요.');");
			out.print("location.href='"+request.getContextPath()+"/login/loginForm';");
			out.print("</script>");
		}else if(!boardService.isAuthorized(user_id, group_id)) {
			System.out.println("야 왜 안떠");
			out.print("<script>");
			out.print("alert('잘못된 접근 입니다.');");
			out.print("location.href='"+request.getContextPath()+"/index';");
			out.print("</script>");
		}else {		
			try {
				//게시판 리스트 보기 
				if(action==null || action.equals("")||action.equals("/board")) {
					
					String _section=request.getParameter("section");
					String _pageNum=request.getParameter("pageNum");
					int section=Integer.parseInt((_section==null)? "1":_section);
					int pageNum=Integer.parseInt((_pageNum==null)? "1":_pageNum);
					
					Map<String, Integer> pagingMap=new HashMap<String, Integer>();
					pagingMap.put("section", section);
					pagingMap.put("pageNum", pageNum);
					
					// 글 리스트 뿐만 아니라 section과 page까지 담아 올려고 Map을 사용함
					Map articleMap=boardService.listArticles(pagingMap, group_id, user_id);
					articleMap.put("section", section);
					articleMap.put("pageNum", pageNum);
					request.setAttribute("articleMap", articleMap);
					nextPage="/views/group/board.jsp";
					//System.out.println("소모임"+articleMap.get("group_id"));
					
				} else if(action.equals("/search")){ // 검색기능
					String filter=request.getParameter("filter");
					System.out.println("필터"+filter);
					String keyword=request.getParameter("keyword");
					System.out.println("키워드"+keyword);
					
					String _section=request.getParameter("section");
					String _pageNum=request.getParameter("pageNum");
					
					int section=Integer.parseInt((_section==null)? "1":_section);
					int pageNum=Integer.parseInt((_pageNum==null)? "1":_pageNum);
					
					Map<String, Integer> pagingMap=new HashMap<String, Integer>();
					pagingMap.put("section", section);
					pagingMap.put("pageNum", pageNum);
					
					// 글 리스트 뿐만 아니라 section과 page까지 담아 올려고 Map을 사용함
					Map articleMap=boardService.serchArticles(pagingMap, group_id, user_id, filter, keyword);
					articleMap.put("filter", filter);
					articleMap.put("keyword", keyword);
					articleMap.put("section", section);
					articleMap.put("pageNum", pageNum);
						
					request.setAttribute("articleMap", articleMap);
					nextPage="/views/group/board.jsp";

				}else if(action.equals("/viewArticle")) {	// 글 상세보기
					int article_no = Integer.parseInt(request.getParameter("article_no"));
					Map articleViewMap=boardService.viewArticle(group_id, article_no, user_id);
					request.setAttribute("articleViewMap", articleViewMap);
					nextPage="/views/group/boardView.jsp";
			
				} else if(action.equals("/write")) { //글쓰는 페이지로이동 
					boolean isAdmin=boardService.checkAdmin(user_id);
					if(isAdmin) {
						out.print("<script>alert('관리자는 글을 작성할 수 없습니다.');");
						out.print("location.href='"+request.getContextPath()+"/group/board?group_id="+group_id+"';");
						out.print("</script>");
					} else {
						nextPage="/views/group/boardWrite.jsp";
					}
					
				} else if(action.equals("/addArticle")) { // 게시글 작성
					boolean isAdmin=boardService.checkAdmin(user_id);
					boolean isMem=boardService.isMember(user_id, group_id);
					if(isAdmin) {
						out.print("<script>alert('관리자는 글을 작성할 수 없습니다.');");
						out.print("location.href='"+request.getContextPath()+"/group/board?group_id="+group_id+"';");
						out.print("</script>");
					} else {
						String _brackets=request.getParameter("brackets");
						String title=request.getParameter("title");
						String content=request.getParameter("content");
						int article_no=boardService.getNewArticleNo();
						Boolean isImgExist=Boolean.parseBoolean(request.getParameter("isImgExist"));
						if(isImgExist) {
							System.out.println("들어는 와?여기루? isImg이거 ");
							String[] imgName=request.getParameterValues("imageName");
							List<String> fileList=null;
							if(imgName.length!=0) {
								fileList=new ArrayList<String>();
								for(int i=0; i<imgName.length; i++) {
									System.out.println(imgName[i]);
									fileList.add(imgName[i]);
								}
								System.out.println("moveImage는 안가?");
								moveImageDir(fileList, article_no);
								content=content.replace("/getReviewImage.do?", "/getImage.do?article_no="+article_no+"&");
							}
						}
						boardVO.setArticle_no(article_no);
						boardVO.setTitle(title);
						boardVO.setUser_id(user_id);
						boardVO.setGrp_id(group_id);
						boardVO.setContent(content);
						boardService.addArticle(boardVO, _brackets);
						
						out.print("<script>alert('글이 등록되었습니다.');");
						out.print("location.href='"+request.getContextPath()+"/group/board/viewArticle?group_id="+group_id+"&article_no="+article_no+"';");
						out.print("</script>");
						
						//nextPage="/nemo/group/board?group_id="+group_id;
						//response.sendRedirect(nextPage);
						return;
					}
				}else if(action.equals("/cancelAddArticle")) { //글 작성 취소시 등록된 이미지 삭제
					System.out.println("등록취소여기오냐?");
					
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
					out.print("location.href='"+request.getContextPath()+"/group/board?group_id="+group_id+"';");
					out.print("</script>");
					return;
					
				} else if(action.equals("/deleteArticle")) { 
					//게시글 정보를 가지고 게시글 삭제 페이지로
					String _article_no=request.getParameter("article_no");
					int article_no=Integer.parseInt(_article_no);
					BoardVO articleInfo=boardService.getArticleInfo(group_id, article_no, user_id);
					request.setAttribute("articleInfo", articleInfo);
					request.setAttribute("deleteInfo", "deleteArticle");
					nextPage="/views/group/boardDelete.jsp";
					System.out.println(articleInfo.getArticle_no());
					
				} else if(action.equals("/deleteComment")) {
					//댓글 정보를 가지고 댓글 삭제 페이지로
					String _comment_no=request.getParameter("comment_no");
					int comment_no=Integer.parseInt(_comment_no);
					//CommentVO commentInfo=boardService.getCommentInfo(group_id, comment_no,user_id);
					CommentVO commentInfo=commentService.getCommentInfo(group_id, comment_no,user_id);
					request.setAttribute("commentInfo", commentInfo);
					request.setAttribute("deleteInfo", "deleteComment");
					nextPage="/views/group/boardDelete.jsp";
					
				} else if(action.equals("/delete")) { //실제 삭제
					String msg=request.getParameter("deleteInfo");
					int article_no=Integer.parseInt(request.getParameter("article_no"));
					int num=Integer.parseInt(request.getParameter("number"));
					boolean isMem=boardService.isMember(user_id, group_id);

					if(msg.equals("deleteArticle")) { //게시글 삭제시
						// 사진 폴더 삭제하는 작업 해야함
						//boardService.deleteArticle(num);
						boardService.deleteArticle(article_no);
						removeDir(article_no);
						//number에는 article_no가 들어있음
						out.print("<script>");
						out.print("alert('삭제 되었습니다.');");
						out.print("location.href='"+ request.getContextPath()+"/group/board?group_id="+group_id+"';");
						out.print("</script>");
						return;
						
					} else if(msg.equals("deleteComment")) { // 댓글 삭제
						//String article_no=request.getParameter("article_no");
						//int _article_no=Integer.parseInt(article_no);
						//boolean check=commentService.deleteComment(num, _article_no);
						boolean check=commentService.deleteComment(num, article_no);
						if(!check) {
							out.print("<script>");
							out.print("alert('삭제 되었습니다.');");
							out.print("location.href='"+ request.getContextPath()+"/group/board/viewArticle?group_id="+group_id+"&article_no="+article_no+"';");
							out.print("</script>");
							return;
						} else {
							System.out.println("자식 있어 컨트롤러");
							out.print("<script>");
							out.print("alert('댓글이 달린 댓글은 삭제할 수 없습니다.');");
							out.print("location.href='"+request.getContextPath()+"/group/board/viewArticle?group_id="+group_id+"&article_no="+article_no+"';");
							out.print("</script>");
							return;
						}
					}	
				}else if(action.equals("/addComment")) {
					boolean isAdmin=boardService.checkAdmin(user_id);
					boolean isMem=boardService.isMember(user_id, group_id);
					if(isAdmin) {
						out.print("<script>alert('관리자는 댓글을 작성할 수 없습니다.');");
						out.print("location.href='"+request.getContextPath()+"/group/board?group_id="+group_id+"';");
						out.print("</script>");
					}
					JSONObject comInfo=new JSONObject();
					
					Map commentInfo=new HashMap();
					String com_cont=request.getParameter("com_cont");
					int article_no=Integer.parseInt(request.getParameter("article_no"));
					int parent_no=Integer.parseInt(request.getParameter("parent_no"));
					
					System.out.println(article_no);
					System.out.println(com_cont);
					
					commentInfo=commentService.addComment(user_id, group_id, article_no, com_cont,parent_no);
					comInfo=commentMapToJson(commentInfo);
					comInfo.put("group_id", group_id);
					String jsonInfo=comInfo.toJSONString();
					out.print(jsonInfo);
					return;
			
				}else if(action.equals("/addReply")) {
					boolean isAdmin=boardService.checkAdmin(user_id);
					boolean isMem=boardService.isMember(user_id, group_id);
					if(isAdmin) {
						out.print("<script>alert('관리자는 댓글을 작성할 수 없습니다.');");
						out.print("location.href='"+request.getContextPath()+"/group/board?group_id="+group_id+"';");
						out.print("</script>");
						return;
					}
					
					JSONObject comInfo=new JSONObject();
					
					Map commentInfo=new HashMap();
					
					String com_cont=request.getParameter("com_cont");
					int article_no=Integer.parseInt(request.getParameter("article_no"));
					int parent_no=Integer.parseInt(request.getParameter("parent_no"));
					commentInfo=commentService.addComment(user_id, group_id, article_no, com_cont, parent_no);
					comInfo=commentMapToJson(commentInfo);
					comInfo.put("group_id", group_id);
					String jsonInfo=comInfo.toJSONString();
					out.print(jsonInfo);
					return;
					
					
				}else if(action.equals("/modComment")) {
					int comment_no=Integer.parseInt(request.getParameter("comment_no"));
					String com_cont=request.getParameter("com_cont");
					commentService.modComment(comment_no, com_cont);
					out.print("success");
					return;
				}else if(action.equals("/modCancle")) {
					int comment_no=Integer.parseInt(request.getParameter("comment_no"));
					String com_cont=commentService.getCommentCont(comment_no);
					System.out.println(com_cont);
					out.print(com_cont);
					return;
				}else if(action.equals("/modArticle")) { // 글 수정 폼으로 보내기
					boolean isAdmin=boardService.checkAdmin(user_id);
					int article_no=Integer.parseInt(request.getParameter("article_no"));
					if(isAdmin) {
						out.print("<script>alert('관리자는 댓글을 작성할 수 없습니다.');");
						out.print("location.href='"+request.getContextPath()+"/group/board?group_id="+group_id+"';");
						out.print("</script>");
						return;
					} else {
						//서비스에서 수정할 내용 찾아서 세팅해줘야함
						Map articleViewMap=boardService.viewArticle(group_id, article_no, user_id);
						request.setAttribute("articleViewMap", articleViewMap);
						nextPage="/views/group/modArticle.jsp";
					}
				} else if(action.equals("/updateArticle")) {
					String _brackets=request.getParameter("brackets");
					String title=request.getParameter("title");
					String content=request.getParameter("content");
					int article_no=Integer.parseInt(request.getParameter("article_no"));
					System.out.println(content);
					
					boardVO.setTitle(title);
					//boardVO.setBrackets(brackets);
					boardVO.setUser_id(user_id);
					boardVO.setGrp_id(group_id);
					boardVO.setContent(content);
					boardVO.setArticle_no(article_no);
					boardService.modArticle(boardVO, _brackets);
					
					out.print("<script>alert('글이 수정되었습니다.');");
					out.print("location.href='"+request.getContextPath()+"/group/board/viewArticle?group_id="+group_id+"&article_no="+article_no+"';");
					out.print("</script>");
					return;
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
				dispatcher.forward(request, response);
			} catch (Exception e) {
				System.out.println("board controller 처리 중 에러 ");
				e.printStackTrace();
			}
		} 
		
	}
	
	//이미지 폴더 이동 하는 메소드 
	private void moveImageDir(List<String> fileList, int article_no) {
	//articleno로 폴더 생성
	//tmp에서 articleno폴더로 이동
		try {
			ARTICLE_IMG_DIR=this.getClass().getResource("").getPath();
			ARTICLE_IMG_DIR=ARTICLE_IMG_DIR.substring(1,ARTICLE_IMG_DIR.indexOf(".metadata"));
			ARTICLE_IMG_DIR=ARTICLE_IMG_DIR.replace("/", "\\");
			ARTICLE_IMG_DIR+="nemo\\src\\main\\webapp\\boardImages";
			
			if(fileList!=null && fileList.size()!=0) {
				
				for(String imgName:fileList) {
					File srcFile=new File(ARTICLE_IMG_DIR+"\\temp\\"+imgName);
					File destDir=new File(ARTICLE_IMG_DIR+"\\"+article_no);
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					srcFile.delete();
				}	
			}
		} catch (Exception e) {
			System.out.println("이미지 파일 복사하는 중 에러");
			e.printStackTrace();
		}
	}
	
	//글 등록 취소시 temp폴더에서 img 삭제
	private void deleteTempImg(List<String> fileList) {
		try {
			System.out.println("등록취소시 이미지 삭제 하는 메소드");
			ARTICLE_IMG_DIR=this.getClass().getResource("").getPath();
			ARTICLE_IMG_DIR=ARTICLE_IMG_DIR.substring(1,ARTICLE_IMG_DIR.indexOf(".metadata"));
			ARTICLE_IMG_DIR=ARTICLE_IMG_DIR.replace("/", "\\");
			ARTICLE_IMG_DIR+="nemo\\src\\main\\webapp\\boardImages\\temp\\";
			if(fileList!=null && fileList.size()!=0) {
				for(String imgName:fileList) {
					File srcFile=new File(ARTICLE_IMG_DIR+imgName);
					srcFile.delete();
				}
			}
		}catch (Exception e) {
			System.out.println("글 등록 취소시 temp 폴더에서 이미지 삭제 중 에러");
			e.printStackTrace();
		}
	}

	//이미지 폴더 삭제하는 메소드
	private void removeDir(int article_no) {
		try {
			ARTICLE_IMG_DIR=this.getClass().getResource("").getPath();
			ARTICLE_IMG_DIR=ARTICLE_IMG_DIR.substring(1,ARTICLE_IMG_DIR.indexOf(".metadata"));
			ARTICLE_IMG_DIR=ARTICLE_IMG_DIR.replace("/", "\\");
			ARTICLE_IMG_DIR+="nemo\\src\\main\\webapp\\boardImages\\";
			File file=new File(ARTICLE_IMG_DIR+article_no);
			
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
	
	private JSONObject commentMapToJson(Map commentInfo) {
		JSONObject comInfo=new JSONObject();
		CommentVO commentVO = (CommentVO)commentInfo.get("commentVO");
		
		//String create_date=(String)commentInfo.get(commentVO.getCreate_date());
		//String create_date=(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(commentInfo.get(commentVO.getCreate_date()));
		//System.out.println(create_date);
		String create_date=(new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(commentVO.getCreate_date());
		comInfo.put("comment_no", commentVO.getComment_no());
		comInfo.put("article_no", commentVO.getArticle_no());
		comInfo.put("nickname", commentVO.getUserVO().getNickname());
		comInfo.put("com_cont", commentVO.getCom_cont());
		comInfo.put("create_date", create_date);
		comInfo.put("user_img", commentVO.getUserVO().getUser_img());
		comInfo.put("com_cnt", commentInfo.get("com_cnt")); //댓글수
		System.out.println(commentInfo.get("appendLocation"));
		comInfo.put("appendLocation", commentInfo.get("appendLocation"));
		
		return comInfo;
	}
}