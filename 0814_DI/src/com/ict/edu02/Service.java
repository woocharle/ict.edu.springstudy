package com.ict.edu02;

public class Service {
	private DAO dao;
	
	public Service() {}
	
	public Service(DAO dao) {
		super();
		this.dao = dao;
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}
	
	//실행하고 싶은 메소드
	public void biz() {
		dao.prn();
	}

}
