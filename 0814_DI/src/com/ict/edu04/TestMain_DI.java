package com.ict.edu04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain_DI {
	public static void main(String[] args) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("com/ict/edu04/config.xml");
		
		System.out.println("배열");
		
		Array arr = (Array)context.getBean("array");
		arr.s_pri();
		
		System.out.println();
		arr.i_pri();
		
		System.out.println("\n - map - \n");
		
		
		Mapprint mp = (Mapprint)context.getBean("map");
		mp.s_prn();
		
		System.out.println();
		
		mp.i_prn();
		
		System.out.println("\n - set - \n");
		
		SetPrint sp = (SetPrint)context.getBean("sp");
		sp.s_prn();
		
		System.out.println();
		
		sp.i_prn();
		
		
		
	}
}
