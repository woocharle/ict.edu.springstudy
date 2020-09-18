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
import com.ict.db.MVO;
import com.ict.db.VO1;
import com.ict.db.VO2;
import com.ict.model.Paging;

@Controller
public class MainController {
	@Autowired
	private DAO dao;
	@Autowired
	private Paging paging; 
	
	public void setDao(DAO dao) {
		this.dao = dao;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	@RequestMapping(value="login.do", method=RequestMethod.POST)
	public ModelAndView getLogin(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		MVO mvo = null;	
		
		try {
			 mvo = dao.getMember(id);	
			if(password.equals(mvo.getPw())) {
				request.getSession().setAttribute("mvo", mvo);
				mv.setViewName("redirect: list.do?");
				
			}else {
				
				mv.addObject("comment", "ok");
				mv.setViewName("index");
			}
			
		} catch (Exception e) {
			System.out.println(e);
			mv.addObject("comment", "ok");
			mv.setViewName("index");
		}

		return mv;
	}
	
	
	@RequestMapping(value="list.do", method=RequestMethod.GET)
	public ModelAndView getList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		int su = dao.getCount();
		paging.setTotalRecord(su);
		
		// tuple의 개수를 구한 뒤 한 페이지당 보여줄 tuple 개수를 나누어 전체 페이지수 구하기 
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
		
		List<VO1> list = dao.getList1(paging);

		mv.addObject("list", list);
		mv.addObject("paging", paging);
		mv.setViewName("list");
		
		return mv;
	}
	
	@RequestMapping(value="write.do", method=RequestMethod.GET)
	public ModelAndView getWrite(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		String cPage = request.getParameter("cPage");
		
		mv.addObject("cPage", cPage);
		mv.setViewName("write");
		
		return mv;
	}

	@RequestMapping(value="save_go.do", method=RequestMethod.POST)
	public ModelAndView getSave1(VO1 vo1, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		int result = 0;
		MultipartFile file = null;
		String path = null;
		
		try {
			path = request.getSession().getServletContext().getRealPath("/resources/upload");
			file = vo1.getFile();
			
			if(file.isEmpty()) {
				vo1.setFilename("");
			} else {
				vo1.setFilename(vo1.getFile().getOriginalFilename());
				file.transferTo(new File(path + File.separator + vo1.getFilename()));
			}
			
			result = dao.getIDU1(vo1, "Insert");
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
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
		
		String cPage = String.valueOf(paging.getTotalPage());
		
		if(result > 0) {
			mv.setViewName("redirect:list.do?cPage="+cPage);
		}else {
			mv.setViewName("redirect:write.do");
		}
		
		return mv;
	}
	
	
	@RequestMapping(value="onelist.do", method = RequestMethod.GET)
	public ModelAndView getOnelist1(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String b_idx = request.getParameter("b_idx");
		String cPage = request.getParameter("cPage");
		
		dao.getHitup(b_idx);
		
		VO1 vo1 = dao.getOnelist1(b_idx);
		List<VO2> clist = dao.getList2(b_idx);
				
		request.getSession().setAttribute("cPage", cPage);
		request.getSession().setAttribute("vo1", vo1);
		request.getSession().setAttribute("clist", clist);
		
		mv.setViewName("onelist");
		
		return mv;
	}
	
	@RequestMapping(value="update1.do", method=RequestMethod.POST)
	public ModelAndView getUpdate1(VO1 vo1, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		int result = 0;
		MultipartFile file = null;
		String path = null;
		
		try {
			path = request.getSession().getServletContext().getRealPath("/resources/upload");
			file = vo1.getFile();
			
			if(file.isEmpty()) {
				vo1.setFilename(request.getParameter("filename0"));
			} else {
				vo1.setFilename(vo1.getFile().getOriginalFilename());
				file.transferTo(new File(path + File.separator + vo1.getFilename()));
			}
			
			result = dao.getIDU1(vo1, "Update");
			
		} catch (Exception e) {
			// TODO: handle exception
		}		
		
		String cPage = (String) request.getSession().getAttribute("cPage");
		
		mv.setViewName("redirect: onelist.do?b_idx="+ vo1.getB_idx()+"&cPage="+cPage);
		
		return mv;
	}
	
	@RequestMapping(value="delete1.do", method = RequestMethod.POST)
	public ModelAndView delete1(VO1 vo1, HttpServletRequest request) {
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
				
			//원글의 기본기가 외래키로 연결 되었기 때문에 안 된다.
			//댓글을 삭제한 다음에 원글을 삭제하면 되지만, 규정상 그렇게 할 수 없다.  	
			result = dao.getIDU1(vo1, "Delete");
			
			
		} catch (Exception e) {
			mv.setViewName("error");
		} 	
		
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
		
		String cPage = String.valueOf(paging.getTotalPage());
		
		mv.setViewName("redirect:list.do?cPage="+cPage);

		return mv;
				
	}		
	
	//댓글 달기
	
	@RequestMapping(value="cwrite.do", method=RequestMethod.POST)
	public ModelAndView getSave1(VO2 vo2, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		int result = dao.getIDU2(vo2, "Insert");
		
		List<VO2> clist = dao.getList2(vo2.getB_idx());
		request.getSession().setAttribute("clist", clist);	
		
		VO1 vo1 = dao.getOnelist1(vo2.getB_idx());
		request.setAttribute("vo1", vo1);
		
		
		mv.setViewName("onelist");
		
		return mv;
	}
	
	@RequestMapping(value="cupdate.do", method=RequestMethod.POST)
	public ModelAndView getUpdate2(VO2 vo2, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		int result = dao.getIDU2(vo2, "Update");
		
		List<VO2> clist = dao.getList2(vo2.getB_idx());
		request.getSession().setAttribute("clist", clist);			

		VO1 vo1 = dao.getOnelist1(vo2.getB_idx());
		request.setAttribute("vo1", vo1);
		
		mv.setViewName("onelist");		
		
		return mv;
	}
	
	@RequestMapping(value="cdelete.do", method=RequestMethod.POST)
	public ModelAndView getDelete2(VO2 vo2, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		int result = dao.getIDU2(vo2, "Delete");
		
		List<VO2> clist = dao.getList2(vo2.getB_idx());
		request.getSession().setAttribute("clist", clist);			

		VO1 vo1 = dao.getOnelist1(vo2.getB_idx());
		request.setAttribute("vo1", vo1);		
		
		mv.setViewName("onelist");				
		
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
