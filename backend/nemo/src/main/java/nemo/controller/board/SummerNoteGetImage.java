package nemo.controller.board;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.google.gson.JsonObject;


@WebServlet("/summernoteImage/*")
public class SummerNoteGetImage extends HttpServlet {
	private static String ARTICLE_IMG_DIR;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getImg(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getImg(request, response);
	}
	public void getImg(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
		String action=request.getPathInfo();
		
		if(action.equals("/getReviewImage.do")) {
			System.out.println("getReviewImage");
			String savedFileName=request.getParameter("savedFileName");
			String filePath;
			ARTICLE_IMG_DIR=this.getClass().getResource("").getPath();
			ARTICLE_IMG_DIR=ARTICLE_IMG_DIR.substring(1,ARTICLE_IMG_DIR.indexOf(".metadata"));
			ARTICLE_IMG_DIR=ARTICLE_IMG_DIR.replace("/", "\\");
			ARTICLE_IMG_DIR+="nemo\\src\\main\\webapp\\boardImages\\temp\\";
			filePath=ARTICLE_IMG_DIR+savedFileName;
			getImage(filePath, response);
			
		} else if(action.equals("/getImage.do")) {
			System.out.println("getImage");
			int article_no=Integer.parseInt(request.getParameter("article_no"));
			String savedFileName=request.getParameter("savedFileName");
			System.out.println(article_no);
			System.out.println(savedFileName);
			String filePath;
			ARTICLE_IMG_DIR=this.getClass().getResource("").getPath();
			ARTICLE_IMG_DIR=ARTICLE_IMG_DIR.substring(1,ARTICLE_IMG_DIR.indexOf(".metadata"));
			ARTICLE_IMG_DIR=ARTICLE_IMG_DIR.replace("/", "\\");
			ARTICLE_IMG_DIR+="nemo\\src\\main\\webapp\\boardImages\\"+article_no+"\\";
			filePath=ARTICLE_IMG_DIR+savedFileName;
			getImage(filePath, response);
		}
	}
	
	public void getImage(String filePath, HttpServletResponse response) throws IOException{
		File file=new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream in = new BufferedInputStream(fis);;
		ByteArrayOutputStream bStream = new ByteArrayOutputStream();
		
		int imgByte;
		while ((imgByte = in.read()) != -1) {
			bStream.write(imgByte);
		}
		String type = "";
		String ext = FilenameUtils.getExtension(file.getName());
		if (ext != null && !"".equals(ext)) {
			if ("jpg".equals(ext.toLowerCase())) {
				type = "image/jpeg";
			} else {
				type = "image/" + ext.toLowerCase();
			}	
		}

		response.setHeader("Content-Type", type);
		response.setContentLength(bStream.size());

		bStream.writeTo(response.getOutputStream());

		response.getOutputStream().flush();
		response.getOutputStream().close();
		bStream.close();
		in.close();
		fis.close();
	}

}
