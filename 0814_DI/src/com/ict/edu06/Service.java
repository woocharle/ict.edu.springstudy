package com.ict.edu06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("service")   //Bean 객체의 <bean id="service" class="com.ict.edu06.Service"/>와 동일.
public class Service {
	@Autowired		//이곳이 <construct.../>와  <property.../>와 동일
	private DAO dao;

	public Service() {
		System.out.println("Service 생성자");
	}

	public Service(DAO dao) {
		this.dao = dao;
		System.out.println("Service 생성자");
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	public void biz() {
		dao.prn();
	}
	
	
}
