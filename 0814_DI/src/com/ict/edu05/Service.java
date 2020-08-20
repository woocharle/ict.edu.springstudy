package com.ict.edu05;

public class Service {
	private DAO dao;
	private String name;
	
	public Service() {
		System.out.println("Service 생성자");
	}

	public Service(DAO dao) {
		super();
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
