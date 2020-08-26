package com.ict.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ict.model.Calc;

@Controller
public class MyController2 {
	@Autowired
	Calc calc;

	public void setCal(Calc calc) {
		this.calc = calc;
	}
	
	@RequestMapping(value="calc.ict", method = RequestMethod.GET)
	public ModelAndView calcController(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result");
		int res = 0;
		
		String cmd = req.getParameter("cmd");
		
		switch (cmd) {
			case "1": res = calc.plus(); break;
			case "2": res = calc.minus(); break;
			case "3": res = calc.mul(); break;
			case "4": res = calc.div(); break;

		}
		
		mv.addObject("res", res);
		
		return mv;
	}
	
}
