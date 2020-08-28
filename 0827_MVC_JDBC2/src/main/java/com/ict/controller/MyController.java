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
import org.springframework.web.servlet.ModelAndView;

import com.ict.db.DAO;
import com.ict.db.VO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class MyController {
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
	
	@RequestMapping(value="write.do", method=RequestMethod.GET)
	public ModelAndView write_Cmd() {
		return new ModelAndView("write");
	}
	
	@RequestMapping(value="onelist.do", method=RequestMethod.GET)
	public ModelAndView onelist_Cmd(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();	
		mv.setViewName("onelist");
		String idx = request.getParameter("idx");
		
		List<VO> list = dao.getOnelist(idx);
		
		VO vo = list.get(0);
		
		request.getSession().setAttribute("vo", vo);
		
		return mv;
	}
		 
	
	@RequestMapping(value = "write_ok.do", method = RequestMethod.POST)
	public ModelAndView writeOk2Command(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:list.do");
		
		try {
			String path = request.getSession()
					.getServletContext().getRealPath("/resources/upload");
			MultipartRequest mr = 
					new MultipartRequest(request, path,
						500*1024*1024, "utf-8", new DefaultFileRenamePolicy());

			String name = mr.getParameter("name");
			String subject = mr.getParameter("subject");
			String email = mr.getParameter("email");
			String pwd = mr.getParameter("pwd");
			String content = mr.getParameter("content");
			String filename="";
			
			
			// 파일업로드를 했을 때와 안했을 때 구분
			File file =  mr.getFile("filename");
			if(file!=null) {
				filename = mr.getFilesystemName("filename");
			}
			
			VO vo 
				= new VO("", name, subject, content, filename, email, pwd, "");
			
			int result = dao.getIDU(vo, "Insert"); 
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return mv;
	}

	@RequestMapping(value="update.do", method=RequestMethod.POST)
	public ModelAndView update_Cmd(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		VO vo = new VO();
		String idx = "";
		System.out.println(idx);
		
		int result = 0;
		
		try {
			String path = request.getSession()
					.getServletContext().getRealPath("/resources/upload");
			MultipartRequest mr = 
					new MultipartRequest(request, path,
						500*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			
			idx = mr.getParameter("idx");
			vo.setIdx(idx);
			vo.setName(mr.getParameter("name"));
			vo.setSubject(mr.getParameter("subject"));
			vo.setEmail(mr.getParameter("email"));
			vo.setContent(mr.getParameter("content"));
			String filename="";
		
			// 파일업로드를 했을 때와 안했을 때 구분
			File file =  mr.getFile("filename");
			if(file!=null) {
				filename = mr.getFilesystemName("filename");
			}else {
				filename = mr.getParameter("filename0");
			}
			
			vo.setFilename(filename);
			
			result = dao.getIDU(vo, "Update");
			
			mv.setViewName("redirect:onelist.do?idx="+idx);
			
		} catch (Exception e) {
			System.out.println(e);
		}

		return mv;
	}
	
	@RequestMapping(value="delete.do", method=RequestMethod.POST)
	public ModelAndView delete_Cmd(VO vo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();	
		String idx = vo.getIdx();
		int result = 0;
		System.out.println();
		try {
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			vo = (VO)request.getSession().getAttribute("vo");
			idx = vo.getIdx();
				
			if(vo.getFilename() != ""){
				String file_name = path + File.separator + vo.getFilename();
				File file = new File(file_name);
				boolean res = file.delete();
			}
			
			result = dao.getIDU(vo, "Delete");

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(result > 0) {
			mv.setViewName("redirect:list.do");
		}else {
			mv.setViewName("redirect:onelist.do?idx="+idx);
		}
		
		
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
