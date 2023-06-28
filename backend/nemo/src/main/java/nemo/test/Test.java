package nemo.test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Test
 */
@WebServlet("/test")
public class Test extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("REST_API_KEY", "c73306afc68803d77548f1b3dea5d5c2");
		request.setAttribute("REDIRECT_URI", "http://127.0.0.1:8090/nemo/oauth");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/test/loginTest2.jsp");
		dispatcher.forward(request, response);


	}

}
