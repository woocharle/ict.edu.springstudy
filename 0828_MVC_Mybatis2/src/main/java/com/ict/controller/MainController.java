package com.ict.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
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
		MultipartFile file = null;
		String path = null;
		// 아파치를 이용해서 받는 방식은 강의를 들어서.
		
		try {
			path = request.getSession().getServletContext().getRealPath("/resources/upload");
			file = vo.getFile();
			
			if(file.isEmpty()) {
				vo.setFilename("");
			} else {
				vo.setFilename(vo.getFile().getOriginalFilename());
			}
			
			result = dao.getIDU(vo, "Insert");
			file.transferTo(new File(path+File.separator +vo.getFilename()));
			
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
	
	
	@RequestMapping(value="update.do", method = RequestMethod.POST)
	public ModelAndView update_cmd(VO vo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		int result = 0;
		MultipartFile file = null;
		String path = null;
		// 아파치를 이용해서 받는 방식은 강의를 들어서.
		
		try {
			path = request.getSession().getServletContext().getRealPath("/resources/upload");
			file = vo.getFile();
			
			if(file.isEmpty()) {
				vo.setFilename("");
			} else {
				
				vo.setFilename(vo.getFile().getOriginalFilename());
			}
			
			result = dao.getIDU(vo, "Update");
			file.transferTo(new File(path+File.separator +vo.getFilename()));
			
		} catch (Exception e) {
			// TODO: handle exception
		} 	
		
		
		mv.setViewName("redirect:onelist.do?idx="+vo.getIdx());

		return mv;
				
	}
	
	
	@RequestMapping(value="delete.do", method = RequestMethod.POST)
	public ModelAndView delete_cmd(VO vo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		int result = 0;
		String filename = request.getParameter("filename0");
		String path = null;
		
		// 아파치를 이용해서 받는 방식은 강의를 들어서.
		
		try {
			path = request.getSession().getServletContext().getRealPath("/resources/upload");

				if(filename != "") {
					String filepath = path + File.separator + filename;
					File file = new File(filepath);
					file.delete();
				}

			result = dao.getIDU(vo, "Delete");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} 	
		
		mv.setViewName("redirect:list.do");

		return mv;
				
	}	
	
	@RequestMapping(value="down.do", method=RequestMethod.GET)
	public void getDown2(@RequestParam("filename") String filename,
						HttpServletRequest request, HttpServletResponse response) {
		
		FileInputStream fis = null;
		BufferedInputStream in = null;
		BufferedOutputStream bos = null;
		
		try {
			String path = request.getSession()
					.getServletContext()
					.getRealPath("/resources/upload/"+filename);
			// 브라우저 설정
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition",
					"attachment; filename="+URLEncoder.encode(filename,"utf-8"));
			// 실제 IO
			File file = new File(new String(path.getBytes("utf-8")));
			int b;
			fis = new FileInputStream(file);
			in = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(response.getOutputStream());
			
			FileCopyUtils.copy(in, bos);
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				bos.close();
				in.close();
				fis.close();
			} catch (Exception e2) {
			}
		}
		
	}
		
	
	
	
}
