package com.ict.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private DAO dao;
	private Paging paging;
	
	@Autowired
	public void setDao(DAO dao) {
		this.dao = dao;
	}
	
	@Autowired
	public void setPaging(Paging paging) {
		this.paging = paging;
	}


	
	@RequestMapping(value="list.do", method=RequestMethod.GET)
	public ModelAndView list_cmd(VO vo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		paging = new Paging();
		
		int su = dao.getCount();
		paging.setTotalRecord(su);
		
		if(paging.getTotalRecord() <= paging.getNumPerpage()) {
			paging.setTotalPage(1);
		}else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerpage());
			if(paging.getTotalRecord() % paging.getNumPerpage() != 0) {
				paging.setTotalPage(paging.getTotalPage() + 1);
			}
		}
		
		String cPage = request.getParameter("cPage");
		if(cPage == null) {
			paging.setNowPage(1);
		}else {
			paging.setNowPage(Integer.parseInt(cPage));
		}		
		
		paging.setBegin((paging.getNowPage() - 1 ) * paging.getNumPerpage() + 1);
		paging.setEnd((paging.getBegin() - 1) + paging.getNumPerpage());
		
		paging.setBeginBlock((int)((paging.getNowPage() - 1)/paging.getPagePerBlock()) * paging.getPagePerBlock() + 1);
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() - 1);
		
		if(paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}
		
		List<VO> list = dao.getList(paging);
		
		mv.addObject("list", list);
		mv.addObject("paging", paging);
		mv.setViewName("list");
		
		return mv;
	}
	
	@RequestMapping(value="write.do", method=RequestMethod.GET)
	public ModelAndView write_Cmd() {
		return new ModelAndView("write");
	}
	
	@RequestMapping(value="send.do", method=RequestMethod.POST)
	public ModelAndView send_Cmd(HttpServletRequest request, VO vo) {
		ModelAndView mv = new ModelAndView();
		int result = 0;
		MultipartFile file = null;
		String path = null;
		
		try {
			path = request.getSession().getServletContext().getRealPath("/resources/upload");
			file = vo.getFile();
			
			if(file.isEmpty()) {
				vo.setFilename("");
			} else {
				vo.setFilename(vo.getFile().getOriginalFilename());
				file.transferTo(new File(path + File.separator + vo.getFilename()));
			}
			
			result = dao.getIDU(vo, "Insert1");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		mv.setViewName("redirect: list.do");
		
		return mv;
	}

	
	
	
	@RequestMapping(value="answer_go.do", method=RequestMethod.GET)
	public ModelAndView anwser_go_Cmd() {
		return new ModelAndView("ans_write");
	}
	
	
	@RequestMapping(value="ans_write.do", method=RequestMethod.POST)
	public ModelAndView answer_Cmd(HttpServletRequest request, VO vo) {
		ModelAndView mv = new ModelAndView();
		int result = 0;
		int result2 = 0;
		MultipartFile file = null;
		String path = null;
		String cPage = request.getParameter("cPage");

		int groups = Integer.parseInt(request.getParameter("groups"));
		int step = Integer.parseInt(request.getParameter("step"));
		int lev = Integer.parseInt(request.getParameter("lev")); 

		
		try {
			path = request.getSession().getServletContext().getRealPath("/resources/upload");
			file = vo.getFile();
			
			if(file.isEmpty()) {
				vo.setFilename("");
			} else {
				vo.setFilename(vo.getFile().getOriginalFilename());
				file.transferTo(new File(path + File.separator + vo.getFilename()));
			}
			
		
			step++;
			lev++;
						
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("groups", groups);
			map.put("lev", lev);
			
			result = dao.getLevup(map);
			
			vo.setStep(String.valueOf(step));
			vo.setLev(String.valueOf(lev));
			result2 = dao.getIDU(vo, "Insert2");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		mv.setViewName("redirect: list.do?cPage="+cPage);
		
		return mv;
	}
	
	@RequestMapping(value="onelist.do", method = RequestMethod.GET)
	public ModelAndView onelist_Cmd(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String idx = request.getParameter("idx");
		String cPage = request.getParameter("cPage");
		
		dao.getHitup(idx);
		
		VO vo = dao.getOnelist(idx);
			
		request.getSession().setAttribute("cPage", cPage);
		request.getSession().setAttribute("vo", vo);
		
		mv.setViewName("onelist");
		
		return mv;
	}	
	
	
	@RequestMapping(value="update_go.do", method=RequestMethod.GET)
	public ModelAndView update_go_Cmd() {
		return new ModelAndView("update");
	}
	
	@RequestMapping(value="update_ok.do", method=RequestMethod.POST)
	public ModelAndView update_Cmd(VO vo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		int result = 0;
		MultipartFile file = null;
		String path = null;
			
		try {
			path = request.getSession().getServletContext().getRealPath("/resources/upload");
			file = vo.getFile();
			
			if(file.isEmpty()) {
				vo.setFilename(request.getParameter("f_name"));
			} else {
				vo.setFilename(vo.getFile().getOriginalFilename());
				file.transferTo(new File(path + File.separator + vo.getFilename()));
			}
			
			result = dao.getIDU(vo, "Update");			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
				
		String cPage = (String) request.getSession().getAttribute("cPage");
		mv.setViewName("redirect: onelist.do?idx="+ vo.getIdx()+"&cPage="+cPage);		
		
		return mv;
	}
	

	@RequestMapping(value="delete_go.do", method=RequestMethod.GET)
	public ModelAndView delete_go_Cmd() {
		return new ModelAndView("delete");
	}

	@RequestMapping(value="delete_ok.do", method = RequestMethod.POST)
	public ModelAndView delete1(VO vo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		int result = 0;
		String filename = request.getParameter("filename");
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
			result = dao.getIDU(vo, "Delete");
			
			
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
