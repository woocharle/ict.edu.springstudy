package com.ict.first;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class StartController implements Controller{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("views/output2");
		
		//일처리
		
		
		// 결과저장
		
		request.setAttribute("city", "리야드");
		request.getSession().setAttribute("king", "살만 빈 압둘아지즈 알사우드");
		mv.addObject("prince", "모하마드 빈 살만 빈 알사우드");
		
		return mv;
	}
}
