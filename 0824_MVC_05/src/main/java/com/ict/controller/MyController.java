package com.ict.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ict.model.Calc;

@Controller
public class MyController {
	
	@Autowired
	private Calc calc;

	public void setCalc(Calc calc) {
		this.calc = calc;
	}
	
	@RequestMapping(value="add.do", method=RequestMethod.GET)
	public ModelAndView add_Ctrl() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result");
		
		int res = calc.plus();
		mv.addObject("res", res);
		
		return mv;
	}
	
	@RequestMapping(value="sub.do", method=RequestMethod.GET)
	public ModelAndView sub_Ctrl() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result");
		
		int res = calc.minus();
		mv.addObject("res", res);
		
		return mv;
	}
	
	@RequestMapping(value="mul.do", method=RequestMethod.GET)
	public ModelAndView mul_Ctrl() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result");
		
		int res = calc.mul();
		mv.addObject("res", res);
		
		return mv;
	}
	
	@RequestMapping(value="div.do", method=RequestMethod.GET)
	public ModelAndView div_Ctrl() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result");
		
		int res = calc.div();
		mv.addObject("res", res);
		
		return mv;
	}
	
	
	
	
}
