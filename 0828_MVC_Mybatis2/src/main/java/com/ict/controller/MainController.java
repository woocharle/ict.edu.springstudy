package com.ict.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.db.DAO;
import com.ict.db.VO;
import com.ict.model.Paging;

@Controller
public class MainController {
	@Autowired
	private DAO dao;

	public void setDao(DAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value="list.do", method=RequestMethod.GET)
	public ModelAndView list_cmd(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Paging paging = new Paging();
		
		int su = dao.getCount();
		paging.setTotalRecord(su);
		
		if(paging.getTotalRecord() <= paging.getNumPerpage()) {
			paging.setTotalPage(1);
		} else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerpage());
			// 나머지가 있으면 한 페이지 추가.
			if(paging.getTotalRecord() % paging.getNumPerpage() != 0) {
				paging.setTotalPage(paging.getTotalPage()+1);
			}
			
		}
		
		String cPage = request.getParameter("cPage");
		if(cPage == null) {
			paging.setNowPage(1);
		}else {
			paging.setNowPage(Integer.parseInt(cPage));
		}
		
		paging.setBegin((paging.getNowPage() - 1) * paging.getNumPerpage() + 1);
		paging.setEnd((paging.getBegin() - 1) + paging.getNumPerpage());
		
		paging.setBeginBlock((int)((paging.getNowPage() - 1)/paging.getPagePerBlock()) * paging.getPagePerBlock() + 1);
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() - 1);
		
		if(paging.getEndBlock() > paging.getTotalPage()){
			paging.setEndBlock(paging.getTotalPage());
		}
		
		
		List<VO> list = dao.getList(paging);
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		mv.setViewName("list");
		
		return mv;
	}
	
	
	@RequestMapping(value="write.do", method = RequestMethod.GET)
	public ModelAndView write_cmd() {	
		return new ModelAndView("write");
	}
	
	@RequestMapping(value="save_go.do", method = RequestMethod.POST)
	public ModelAndView save_cmd(VO vo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		int result = 0;
		// 아파치를 이용해서 받는 방식은 강의를 들어서.
		
		try {
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
		
			System.out.println(path);
			System.out.println(vo);
			System.out.println(vo.getName());
			
			result = dao.getIDU(vo, "Insert");
			
		} catch (Exception e) {
			// TODO: handle exception
		} 
		
		if (result > 0) {
			mv.setViewName("redirect: list.do");
		} else {
			mv.setViewName("redirect: write.do");
		}
		
		return mv;
		
	}
	
	@RequestMapping(value="onelist.do", method = RequestMethod.GET)
	public ModelAndView onelist_cmd(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		String idx = request.getParameter("idx");
		VO vo = dao.getOnelist(idx);
				
		request.getSession().setAttribute("vo", vo);
		
		mv.setViewName("onelist");
		
		return mv;
	}
	

}
