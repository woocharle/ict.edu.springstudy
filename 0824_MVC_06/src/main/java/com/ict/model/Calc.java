package com.ict.model;

import org.springframework.stereotype.Service;

@Service("calc")
public class Calc  {
	
	public int calculate(int s1, int s2, String op) {
		int res = 0;
		
		switch (op) {
			case "+": res = s1 + s2; break;
			case "-": res = s1 - s2; break;
			case "x": res = s1 * s2; break;
			case "/": res = s1 / s2; break;
		}
		
		return res;
	}
	
}
