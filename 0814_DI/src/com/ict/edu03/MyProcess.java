package com.ict.edu03;

public class MyProcess {
	private String name = "홍길동";
	private int age = 27;
	
	public MyProcess() {
		// TODO Auto-generated constructor stub
	}

	public MyProcess(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public void prn() {
		System.out.println("이름: " + name);
		System.out.println("나이: " + age);
	}
	
}
