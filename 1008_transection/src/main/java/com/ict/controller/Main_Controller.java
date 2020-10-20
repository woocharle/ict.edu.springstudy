package com.ict.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ict.db.DAO;
import com.ict.db.VO;

@Controller
public class Main_Controller {
	@Autowired
	private DAO dao;
	private DataSourceTransactionManager transactionManager;
	
	public void setDao(DAO dao) {
		this.dao = dao;
	}
	@Autowired
	public void setTransactionmanager(DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}	
	
	
	@RequestMapping(value="result.do", method=RequestMethod.POST)
	public ModelAndView result_Cmd(VO vo) {
		int res = 0;
		TransactionDefinition def = null;
		TransactionStatus status = null;
		
		ModelAndView mv = null;
		
		try {
			
			mv = new ModelAndView();
			def = new DefaultTransactionDefinition();
			status = transactionManager.getTransaction(def);
		
			res += dao.getIDU1(vo, "Insert");
			res += dao.getIDU2(vo, "Insert");
			transactionManager.commit(status);
			mv.addObject("vo", vo);
			
			System.out.println("결제, 발권 성공");
		} catch (Exception e) {
			System.out.println("오류발생, 결재취소, 발권취소");
			System.out.println(e);
		}
		
		
		mv.addObject("res", res);
		mv.setViewName("result");
		
		return mv;
	}
	
	
}
