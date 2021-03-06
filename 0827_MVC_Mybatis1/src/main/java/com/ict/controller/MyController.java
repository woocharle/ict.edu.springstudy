package com.ict.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ict.db.DAO;
import com.ict.db.VO;

@Controller
public class MyController {
	//@injection 자바에서 Autowired역할을 하는 것.
	@Autowired
	private DAO dao;

	public void setDao(DAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value="list.do", method=RequestMethod.GET)
	public ModelAndView list_Cmd() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("list");
		
		List<VO> list = dao.getList();
		mv.addObject("list",list);
		
		return mv;
	}
	
	@RequestMapping(value="onelist.do", method=RequestMethod.GET)
	public ModelAndView onelist_Cmd(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("onelist");
		String idx = request.getParameter("idx");
		
		VO vo = dao.getOnelist(idx);
 				
		request.getSession().setAttribute("vo", vo);
		
		return mv;
	}
	
	@RequestMapping(value="write.do", method=RequestMethod.GET)
	public ModelAndView write_Cmd() {
		return new ModelAndView("write");
	}
	
	@RequestMapping(value="save.do", method=RequestMethod.POST)
	public ModelAndView save_Cmd(VO vo){
		ModelAndView mv = new ModelAndView();
				
		int result = dao.getIDU(vo, "Insert");
		
		if(result > 0) {
			mv.setViewName("redirect:list.do");
		}else {
			mv.setViewName("redirect:write.do");
		}
		
		return mv;
	}
	
	@RequestMapping(value="update.do")
	public ModelAndView update_Cmd(VO vo) {
		ModelAndView mv = new ModelAndView();
		int result=0;
		String idx = vo.getIdx();
		
		result = dao.getIDU(vo, "Update");

		mv.setViewName("redirect:onelist.do?idx="+idx);

		
		
		return mv;
	}
	
	@RequestMapping(value="delete.do")
	public ModelAndView delete_Cmd(VO vo) {
		ModelAndView mv = new ModelAndView();
		int result=0;
		String idx = vo.getIdx();
		
		result = dao.getIDU(vo, "Delete");
		
		if(result > 0) {
			mv.setViewName("redirect:list.do");
		}else {
			mv.setViewName("redirect:onelist.do?idx="+idx);
		}
		
		return mv;
	}
	
	
	

}
