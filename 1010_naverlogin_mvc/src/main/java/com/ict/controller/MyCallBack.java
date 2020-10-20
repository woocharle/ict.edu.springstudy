package com.ict.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyCallBack {
	
	@RequestMapping("callback.do")
	public ModelAndView callBack(HttpServletRequest request) {
		
		// 인증코드 받기 
		String clientId = "JKVUnk675bZWjaeT7mei";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "4oNoukX_cr";//애플리케이션 클라이언트 시크릿값";
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");
	    String redirectURI;
		try {
			redirectURI = URLEncoder.encode("http://localhost:8090/callback.do", "UTF-8");
	 
	    String apiURL;
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
	    
	    String access_token = "";
	    String refresh_token = "";
	   
	    System.out.println("apiURL="+apiURL);
	 
	      URL url = new URL(apiURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod("GET");
	      int responseCode = con.getResponseCode();
	      BufferedReader br;
	      
	      // 2. 토근 받기 
	      System.out.print("responseCode="+responseCode);
	      
	      if(responseCode==200) { // 정상 호출
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      
	      } else {  // 에러 발생
	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	      }
	      String inputLine;
	      StringBuffer res = new StringBuffer();
	      while ((inputLine = br.readLine()) != null) {
	        res.append(inputLine);
	      }
	      br.close();
	      
	      if(responseCode==200) {
	    	// jsp 
	    	// out.println(res.toString());
	        // System.out.println(res.toString());
	       
	    	// 3. 사용자 정보를 받기 위해서 access_token를 다시 보낸다.
	    	JSONParser parsing = new JSONParser();  
	      	Object obj = parsing.parse(res.toString());
	      	JSONObject jsonobj = (JSONObject)obj;
	      	access_token = (String)jsonobj.get("access_token");
	      	refresh_token = (String)jsonobj.get("refresh_token");
	      	request.getSession().setAttribute("access_token", access_token);
	      	request.getSession().setAttribute("refresh_token", refresh_token);
	      	
	      	// response.sendRedirect("result.jsp");
	      	// return new ModelAndView("result.jsp");
	      }
	    } catch (UnsupportedEncodingException e) {
	      System.out.println(e);
	    } catch (Exception e1) {
		  e1.printStackTrace();
		}
		return new ModelAndView("result");
	}
}
