package com.ict.edu01;

public class TestMain {
	public static void main(String[] args) {
		/*
		   service.biz() 안에 dao는 인터페이스라 객체 생성이 안 되기 때문에 오류 발생. 
		   Service service = new Service(); 
		   service.biz();
		   
		   생성자를 이용해서 dao 인터페이스를 상속받는 클래스를 넣을 수 있다.(injection)
		 */
		//DAO dao = new OracleDAO();
		DAO dao = new MariaDAO();
		Service service = new Service(dao);
		service.biz();
		
		System.out.println("\n setter 이용\n");
		
		DAO dao1 = new OracleDAO();
		Service service1 = new Service();
		service1.setDao(dao1);
		service1.biz();
		
	} 
}
