package nemo.controller.qna;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;


@WebServlet("/qnaSnUploadImage/*")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 100,
        maxRequestSize = 1024 * 1024 * 1000
)
public class QnaSummerNoteUploadImage extends HttpServlet {
	private static String QNA_IMG_REPO;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		
		String action=request.getPathInfo();	//요청명 가져오는 메소드 
		System.out.println("현재액션"+action);	
		
		JsonObject json=new JsonObject();
		JSONObject jsonValue=new JSONObject();
		
		QNA_IMG_REPO=this.getClass().getResource("").getPath();
		QNA_IMG_REPO=QNA_IMG_REPO.substring(1,QNA_IMG_REPO.indexOf(".metadata"));
		QNA_IMG_REPO=QNA_IMG_REPO.replace("/", "\\");
		QNA_IMG_REPO+="nemo\\src\\main\\webapp\\qnaImages\\temp\\";
			
		Part part = request.getPart("file");
		//System.out.println(part.getHeader("content-disposition"));
			
		String originalFileName=getFilename(part);
		String extension=originalFileName.substring(originalFileName.lastIndexOf("."));
		String savedFileName=UUID.randomUUID()+extension;
		File targetFile=new File(QNA_IMG_REPO+savedFileName);
		System.out.println(request.getContextPath());
		System.out.println("qna"+QNA_IMG_REPO);
		try { //파일저장
			System.out.println(action+"되니");
			InputStream fileStream=part.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);
			
			json.addProperty("url","/nemo/qnaSummernoteImage/getReviewImage.do?savedFileName="+savedFileName);
			//json.addProperty("responseCode", "success");
			//jsonValue.put("url","/nemo/summernoteImage/getImage.do?savedFileName="+savedFileName);
			//String jsonString=jsonValue.toJSONString();
			String jsonString=json.toString();
			out.print(jsonString);

		}catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);
			//json.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		//String jsonV=json.toString(); //gson방법
		//System.out.println(jsonValue);
		//String jsonString=jsonValue.toJSONString();
		//return jsonV;	//gson방법
		//return jsonString;
}
	
	 private String getFilename(Part part) {
		 String contentDisp = part.getHeader("content-disposition");
	     String[] split = contentDisp.split(";");
	     for (int i = 0; i < split.length; i++) {
	    	 String temp = split[i];
	         if (temp.trim().startsWith("filename")) {
	        	 return temp.substring(temp.indexOf("=") + 2, temp.length() - 1);
	         }
	     }
	        return "";
	 }
	 
}