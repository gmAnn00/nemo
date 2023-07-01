package nemo.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@WebServlet("/oauth")
public class KakaoLoginController extends HttpServlet {
	private String REST_API_KEY = "c73306afc68803d77548f1b3dea5d5c2";
	private String REDIRECT_URI = "http://127.0.0.1:8090/nemo/oauth";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		
		try {
			String accessToken = getKakaoAccessToken(code);
	        System.out.println(accessToken);

	        // 액세스 토큰을 이용하여 카카오 서버에서 유저 정보(닉네임, 이메일) 받아오기
	        HashMap<String, Object> userInfo = getUserInfo(accessToken);
	        System.out.println("login Controller : " + userInfo);
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}
	
	private String getKakaoAccessToken(String code) {
		String accessToken = "";
	    String refreshToken = "";
	    String requestURL = "https://kauth.kakao.com/oauth/token";
	    
	    try {
	    	URL url = new URL(requestURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	        conn.setRequestMethod("POST");
	        // setDoOutput()은 OutputStream으로 POST 데이터를 넘겨 주겠다는 옵션이다.
	        // POST 요청을 수행하려면 setDoOutput()을 true로 설정한다.
	        conn.setDoOutput(true);

	        // POST 요청에서 필요한 파라미터를 OutputStream을 통해 전송
	        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	        String sb = "grant_type=authorization_code" +
	                "&client_id="+ REST_API_KEY + // REST_API_KEY
	                "&redirect_uri="+ REDIRECT_URI + // REDIRECT_URI
	                "&code=" + code;
	        bufferedWriter.write(sb);
	        bufferedWriter.flush();

	        int responseCode = conn.getResponseCode();
	        System.out.println("responseCode : " + responseCode);

	        // 요청을 통해 얻은 데이터를 InputStreamReader을 통해 읽어 오기
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line = "";
	        StringBuilder result = new StringBuilder();

	        while ((line = bufferedReader.readLine()) != null) {
	            result.append(line);
	        }
	        System.out.println("response body : " + result);
	        
	        JsonElement element = JsonParser.parseString(result.toString());

	        accessToken = element.getAsJsonObject().get("access_token").getAsString();
	        refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();

	        System.out.println("accessToken : " + accessToken);
	        System.out.println("refreshToken : " + refreshToken);

	        bufferedReader.close();
	        bufferedWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	    return accessToken;
	}
	
	public HashMap<String, Object> getUserInfo(String accessToken) {
	    HashMap<String, Object> userInfo = new HashMap<>();
	    String postURL = "https://kapi.kakao.com/v2/user/me";

	    try {
	        URL url = new URL(postURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");

	        conn.setRequestProperty("Authorization", "Bearer " + accessToken);

	        int responseCode = conn.getResponseCode();
	        System.out.println("responseCode : " + responseCode);

	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line = "";
	        StringBuilder result = new StringBuilder();

	        while ((line = br.readLine()) != null) {
	            result.append(line);
	        }
	        System.out.println("response body : " + result);

	        JsonElement element = JsonParser.parseString(result.toString());
	        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
	        JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

	        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
	        String email = kakaoAccount.getAsJsonObject().get("email").getAsString();

	        userInfo.put("nickname", nickname);
	        userInfo.put("email", email);

	    } catch (IOException exception) {
	        exception.printStackTrace();
	    }

	    return userInfo;
	}
	

}
