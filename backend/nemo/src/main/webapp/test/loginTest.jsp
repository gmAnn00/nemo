<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 테스트</title>
<script src="${contextPath}/js/jquery-3.6.4.min.js"></script>
<script
            src="https://t1.kakaocdn.net/kakao_js_sdk/2.2.0/kakao.min.js"
            integrity="sha384-x+WG2i7pOR+oWb6O5GV5f1KN2Ko6N7PTGPS7UlasYWNxZMKQA63Cj/B2lbUmUfuC"
            crossorigin="anonymous"
        ></script>
        <script>
            Kakao.init("ea6bda86230b8415e663eb00385b3b43");
     
            function loginWithKakao() {
                Kakao.Auth.authorize({
                    redirectUri: "http://127.0.0.1:8090/nemo/oauth",
                });
            }
            console.log("${code}");
            
            $(function () {
                $.ajax({
                    type: 'POST',
                    url: 'https://kauth.kakao.com/oauth/token',
                    data: {
                        grant_type: 'authorization_code',
                        client_id: 'ea6bda86230b8415e663eb00385b3b43',
                        redirect_uri: "http://127.0.0.1:8090/nemo/oauth",
                        code: "${code}",
                    },
                    "Content-type": "application/x-www-form-urlencoded;charset=utf-8",
                    success: function (response) {
                        Kakao.Auth.setAccessToken(response.access_token);
                    },
                });
            });
            
            requestUserInfo();
            function requestUserInfo() {
                Kakao.API.request({
                  url: '/v2/user/me',
                  data: {
                	  property_keys:['kakao_account.profile_nickname', 'kakao_account.profile_image', 'kakao_account.email', 'kakao_account.birthday']
                  }
                })
                  .then(function(res) {
                    //alert(JSON.stringify(res));
                    let account = JSON.stringify(res)
                    $("p").text(account);
                    
                  })
                  .catch(function(err) {
                    alert(
                      'failed to request user information: ' + JSON.stringify(err)
                    );
                  });
              }
            
            // 아래는 데모를 위한 UI 코드입니다.
            displayToken();
            function displayToken() {
                var token = getCookie("authorize-access-token");

                if (token) {
                    Kakao.Auth.setAccessToken(token);
                    document.querySelector("#token-result").innerText = "login success, ready to request API";
                    document.querySelector("button.api-btn").style.visibility = "visible";
                }
            }
            function getCookie(name) {
                var parts = document.cookie.split(name + "=");
                if (parts.length === 2) {
                    return parts[1].split(";")[0];
                }
            }
            
            function logout(){
            	Kakao.Auth.logout()
            	  .then(function(response) {
            	    console.log(Kakao.Auth.getAccessToken()); // null
            	  })
            	  .catch(function(error) {
            	    console.log('Not logged in.');
            	  });
            }
            
            function unlink(){
            	Kakao.API.request({
            		  url: '/v1/user/unlink',
            		})
            		  .then(function(response) {
            		    console.log(response);
            		  })
            		  .catch(function(error) {
            		    console.log(error);
            		  });
            }
        </script>
</head>
<body>
<button id="kakao-login-btn" onclick="loginWithKakao()">카카오로그인</button><br/>
<button onclick="logout()">로그아웃</button>
<button onclick="unlink()">탈퇴하기</button>

<p></p>
</body>
</html>