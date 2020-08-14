package com.ict.edu03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain_NonDI {
	public static void main(String[] args) {
		MyProcess process = new MyProcess("둘리", 8);
		process.prn();
		
		ApplicationContext context 
		= new ClassPathXmlApplicationContext("com/ict/edu03/config.xml");
		
		System.out.println();
		
		MyProcess process1 = (MyProcess)context.getBean("process");
		process1.prn();
		
		MyProcess2 process2 = (MyProcess2)context.getBean("process2");
		process2.prn();
		
	}
}
