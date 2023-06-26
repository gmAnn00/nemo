package nemo.controller.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/groupImageDownload")
public class GroupImageDownloadController extends HttpServlet {
	private static String GROUP_IMG_REPO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		GROUP_IMG_REPO = this.getClass().getResource("").getPath();
		GROUP_IMG_REPO = GROUP_IMG_REPO.substring(1, GROUP_IMG_REPO.indexOf(".metadata"));
		GROUP_IMG_REPO = GROUP_IMG_REPO.replace("/", "\\");
		GROUP_IMG_REPO += "nemo\\src\\main\\webapp\\groupImages\\";
		
		String group_id = request.getParameter("group_id");
		String group_img = request.getParameter("group_img");
		
		OutputStream outs = response.getOutputStream();
		String path = GROUP_IMG_REPO + "\\" + group_id + "\\" + group_img;
		File imageFile = new File(path);
		
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + group_img);
		
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
		
		
	}

}
