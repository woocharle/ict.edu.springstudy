package com.ict.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ict.model.MyProcess;

public class Say_ctrl implements Controller{
	private MyProcess myProcess;
	
	public void setMyProcess(MyProcess myProcess) {
		this.myProcess = myProcess;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("result");
		
		mv.addObject("name", "배트맨");
		mv.addObject("age", 50);
		
		//DI가 아닐 경우: MyProcess process = new MyProcess();
		
		
		String msg = myProcess.getGreet();
		
		mv.addObject("greet", msg);
		
		
		return mv;
	}
}
