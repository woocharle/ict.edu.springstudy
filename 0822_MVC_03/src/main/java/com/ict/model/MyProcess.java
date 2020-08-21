package com.ict.model;

import java.util.Calendar;

public class MyProcess {
	public MyProcess() {
		System.out.println("MyProcess 생성자");
	}
	
	public String getGreet() {
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		String greet = "";
		
		if (hour >=7 && hour<=11) {
			greet = "좋은 아침 입니다.";
		}else if (hour >11 && hour<=14) {
			greet = "점심식사 하셨어요?";
		}else if (hour >=22 && hour<=24) {
			greet = "좋은 꿈 꾸세요";
		}else {
			greet = "안녕하세요";
		}
		
		return greet;
		
	}
	
}
