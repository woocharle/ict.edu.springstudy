package com.ict.model;

import java.util.Calendar;

import org.springframework.stereotype.Service;

@Service("myProcess")
public class MyProcess {
	public MyProcess() {}
	
	//실행하려는 메소드
	
	public String getGreet() {
		//현재 시간
		String result="";
		
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		if(hour>7 && hour<=10){
			result="좋은 아침입니다.";
		} else if (hour>12 && hour<=14){
			result = "점심식사 하셨어요?";
			
		} else if (hour>22 && hour<=24){
			result = "안녕히 주무세요";
			
		} else {
			result="안녕하세요";
		}
		
		return result;
		
	}
	
	
	
}
