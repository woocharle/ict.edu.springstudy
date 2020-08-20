package com.ict.edu06;

import org.springframework.stereotype.Component;

@Component("dao")
public class MariaDAO implements DAO{
	public MariaDAO() {
		System.out.println("MariaDAO 생성자");
	}

	@Override
	public void prn() {
		System.out.println("MariaDAO 실행 메소드");
		
	}
}
