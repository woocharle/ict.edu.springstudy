package com.ict.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ArrayController implements Controller{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("output");
		
		String[] horror= {"제이슨", "프레디", "스크림", "텍사스"};
		
		ArrayList<String> hero = new ArrayList<String>();
		hero.add("배트맨");
		hero.add("슈퍼맨");
		hero.add("원더우먼");
		hero.add("아쿠아맨");
			
		mv.addObject("horror", horror);
		mv.addObject("hero", hero);
		
		return mv;
	}
}
