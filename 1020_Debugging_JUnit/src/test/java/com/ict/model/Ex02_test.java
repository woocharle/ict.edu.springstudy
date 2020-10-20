package com.ict.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Ex02_test {
	Ex02 test;
	
	
	//테스트 전 실행
	@Before
	public void testBefore() {
		System.out.println("테스트 이전");
		test = new Ex02();

	}
	
	@Test
	public void test1() {
		System.out.println("테스트 시작");
		// 미리값을 넣는다.
		int result = test.add(10,20);
		// 예상값이 맞는지 검사.
		assertEquals(30, result);

	}
	@Test
	public void test2() {
		System.out.println("테스트 시작");
		// 미리값을 넣는다.
		int result =test.sub(10, 20);
		assertEquals(10, result);
		
	}
	
	//테스트 후 
	@After 
	public void testAfter() {
		System.out.println("테스트 이후");
		// 미리값을 넣는다.
	}
	
	
	
}
