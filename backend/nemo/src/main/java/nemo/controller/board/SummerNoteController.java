package nemo.controller.board;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.JsonObject;


@WebServlet("/summernote/*")
public class SummerNoteController extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action=request.getPathInfo();	//요청명 가져오는 메소드 
		
		if(action.equals("/summer_imgae.do")) {
			String tempDir=request.getContextPath()+"\\boardImg\\temp";
			String originFileName;
		} else if(action.equals("/getImg.do")) {
			
		}else if(action.equals("/getImgCopy.do")) {
			
		}

	}
}
