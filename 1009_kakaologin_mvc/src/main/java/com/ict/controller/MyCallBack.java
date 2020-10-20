package com.ict.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyCallBack {

	@RequestMapping("login.do")
	public ModelAndView callBack(HttpServletRequest request) {
		// 1. 인증 코드 받기
		String code = request.getParameter("code");

		// 2. 토큰 받기
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = 
					new BufferedWriter(
							new OutputStreamWriter(
									conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=3b4a6321cd8a2b5b3c9ab33e7ed7e322");
			sb.append("&redirect_uri=http://localhost:8090/login.do");
			sb.append("&code=" + code);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			// System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = 
					new BufferedReader(
							new InputStreamReader(
									conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			br.close();
			bw.close();

			// System.out.println(result);

			JSONParser parsing = new JSONParser();
			Object obj = parsing.parse(result.toString());
			JSONObject jsonobj = (JSONObject) obj;

			String access_token = (String) jsonobj.get("access_token");
			String refresh_token = (String) jsonobj.get("refresh_token");
			request.getSession().setAttribute("access_token", access_token);
			request.getSession().setAttribute("refresh_token", refresh_token);

			// System.out.println("access_token : " + access_token);
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("result");
	}
}
