package com.ict.first;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class IndexController implements Controller {
	
	// jsp의 모델처런 일처리하고 뷰의 위치값을 리턴한다.
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		// 뷰의 값을 mv에 넣어준다.
		
		mv.setViewName("views/output");
		
		//일처리 
		
		//일처리 결과를 저장		
		
		//저장방법 기존: request, session 신규: ModelAndView (request와 동일한 생명주기)
		
		request.setAttribute("name", "브루스웨인");
		request.getSession().setAttribute("age", 50);
		mv.addObject("addr", "고담시 웨인컴퍼니");
		
		return mv;
	}
}
