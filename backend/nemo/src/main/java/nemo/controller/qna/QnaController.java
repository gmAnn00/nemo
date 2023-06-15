package nemo.controller.qna;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.dao.qna.QnaDAO;
import nemo.vo.qna.QnaVO;

@WebServlet("/qna/*")
public class QnaController extends HttpServlet {
	
	QnaDAO qnaDao;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		qnaDao = new QnaDAO();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<QnaVO> qnaList = qnaDao.selectPagesQnas(0);
		
		String nextPage="";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		HttpSession session;
		String action=request.getPathInfo();
		System.out.println();
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

}
