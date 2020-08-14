package com.ict.edu01;

public class OracleDAO implements DAO{
	public OracleDAO() {
		System.out.println("oracle 생성자");
	}
	
	@Override
	public void prn() {
		System.out.println("oracle 메소드");
		
	}
}
