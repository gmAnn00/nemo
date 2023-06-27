package nemo.controller.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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

import nemo.dao.mypage.MyProfileDAO;
import nemo.service.mypage.MyProfileService;
import nemo.vo.user.UserVO;


@WebServlet("/userImageDownload")
public class UserImageDownloadController extends HttpServlet {
	
	private static String USER_IMG_REPO;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	public void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");		

		USER_IMG_REPO = this.getClass().getResource("").getPath();
		USER_IMG_REPO = USER_IMG_REPO.substring(1, USER_IMG_REPO.indexOf(".metadata"));
		USER_IMG_REPO = USER_IMG_REPO.replace("/", "\\");
		USER_IMG_REPO += "nemo\\src\\main\\webapp\\userImages\\";
		
		String user_id = request.getParameter("user_id");
		String user_img = request.getParameter("user_img");
		
		OutputStream outs = response.getOutputStream();
		String path = USER_IMG_REPO + "\\" + user_id + "\\" + user_img;
		File imageFile = new File(path);
		
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + user_img);
		
		FileInputStream fis = new FileInputStream(imageFile);
		// 버퍼를 이용해 8kb씩 전송
		byte[] buffer = new byte[1024*8];
		
		while(true) {
			int count = fis.read(buffer);
			// 더 이상 읽을 것이 없으면 while 문 탈출
			if(count == -1) {
				break;
			}
			outs.write(buffer, 0, count);
			
		} // end of while
		fis.close();
		outs.close();
		
			
	}// doHandle() End
			
}
