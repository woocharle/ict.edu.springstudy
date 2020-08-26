package com.ict.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ict.db.VO;
import com.ict.model.Calc;

@Controller
public class MyController {

	@Autowired
	private Calc calc;

	public void setCalc(Calc calc) {
		this.calc = calc;
	}

	/*
	 * jsp 방식
	 * 
	 * @RequestMapping(value="play.do", method=RequestMethod.POST) public
	 * ModelAndView play_Ctrl(HttpServletRequest request) { ModelAndView mv = new
	 * ModelAndView(); mv.setViewName("result");
	 * 
	 * int s1 = Integer.parseInt(request.getParameter("s1")); int s2 =
	 * Integer.parseInt(request.getParameter("s2"));
	 * 
	 * String op = request.getParameter("op");
	 * 
	 * int res = calc.calculate(s1, s2, op);
	 * 
	 * String cPage = request.getParameter("cPage");
	 * 
	 * String[] hobby = request.getParameterValues("hobby");
	 * 
	 * mv.addObject("s1", s1); mv.addObject("s2", s2); mv.addObject("op", op);
	 * mv.addObject("res", res); mv.addObject("cPage", cPage); mv.addObject("hobby",
	 * hobby);
	 * 
	 * return mv; }
	 */

	//방법 2: VO를 이용 (가장 많이 사용)
	 /*
	  @RequestMapping(value="play.do", method=RequestMethod.POST) public
	  ModelAndView play_Ctrl(VO vo) { 
		  
		  ModelAndView mv = new
		  ModelAndView(); mv.setViewName("result");
		  
		  int s1 = Integer.parseInt(vo.getS1()); 
		  int s2 = Integer.parseInt(vo.getS2());
		  
		  String op = vo.getOp();
		  
		  int res = calc.calculate(s1, s2, op);
		  
		  String cPage = vo.getcPage();
		  
		  String[] hobby = vo.getHobby();
		  
		  mv.addObject("s1", s1); 
		  mv.addObject("s2", s2); 
		  mv.addObject("op", op);
		  mv.addObject("res", res); 
		  mv.addObject("cPage", cPage); 
		  mv.addObject("hobby",hobby);
	  
		  return mv; 
	  	
	  }
	  */
	
	//방법3: Model 사용, pump 계산에 쓸 것.
	//들어온 파라미터들을 다음에 JSP에 넘어가야 될 때 사용.
	 @RequestMapping(value="play.do", method=RequestMethod.POST) public
	  ModelAndView play_Ctrl(@ModelAttribute("mvo")VO vo) { 
		  
		  ModelAndView mv = new
		  ModelAndView(); mv.setViewName("result");
		  
		  int s1 = Integer.parseInt(vo.getS1()); 
		  int s2 = Integer.parseInt(vo.getS2());
		  String op = vo.getOp();
		  
		  int res = calc.calculate(s1, s2, op);
		  mv.addObject("res", res); 
		  
		  //String cPage = vo.getcPage();
		  //String[] hobby = vo.getHobby();
		  
		  /*
		  mv.addObject("s1", s1); 
		  mv.addObject("s2", s2); 
		  mv.addObject("op", op);
		  
		  mv.addObject("cPage", cPage); 
		  mv.addObject("hobby",hobby);
		  */
		  
		  return mv; 
	  	
	  }
	
}
