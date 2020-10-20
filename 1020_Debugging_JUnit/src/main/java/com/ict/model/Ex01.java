package com.ict.model;

public class Ex01 {
	public static void main(String[] args) {
		int total = 0;
		
		for (int i = 0; i < 11; i++) {
			total += i;
			System.out.println("1부터 " + i + "까지의 합은 " + total);
		}
		System.out.println("합: " + total);
		
	}
}
