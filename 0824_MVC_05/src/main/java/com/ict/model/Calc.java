package com.ict.model;

import org.springframework.stereotype.Service;

@Service("calc")
public class Calc {
	public int plus() {
		return 5 + 3;
	}
	
	public int minus() {
		return 5 - 3;
	}
	
	public int mul() {
		return 5 * 3;
	}
	
	public int div() {
		return 5 / 3;
	}
	
}
