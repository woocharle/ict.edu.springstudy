package com.ict.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ict.model.MyProcess;


//  사용할 모델 클래스를 가져온다.
@Controller
public class MyController {
	
	@Autowired
	//@Inject
	private MyProcess myProcess;
	//변수 이름을 Service의 이름에 맞춘다.
	public void setMyProcess(MyProcess myProcess) {
		this.myProcess = myProcess;
	}
	
	//인자는 상황에 따라서 변환다.
	// 요청이 hello.do 이면서 GET방식일 때 아래 메소드를 실행할 수 있다. 
	// 단 jsp파일에서 post방식으로 올릴 경우 post방식을 써야한다. 
	
	@RequestMapping(value="hello.do", method = RequestMethod.GET)
	public ModelAndView HelloController() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("views/result");
		
		
		
		mv.addObject("name", "루시퍼");
		mv.addObject("age", 3000);
		mv.addObject("time", myProcess.getGreet());
		
		return mv;
		
	}
	
}
