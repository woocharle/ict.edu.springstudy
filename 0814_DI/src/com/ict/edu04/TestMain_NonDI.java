package com.ict.edu04;



public class TestMain_NonDI {
	public static void main(String[] args) {
		Array arr = new Array();
		
		System.out.println("일반 배열에 적용");
		arr.s_pri();
		
		System.out.println();
		arr.i_pri();
		
		System.out.println("\nMap에 적용\n");
		
		Mapprint mp = new Mapprint();
		mp.input();
		
		mp.s_prn();
		System.out.println("==========");
		mp.i_prn();
		
		
		System.out.println("\nSet에 적용\n");
		
		SetPrint sp = new SetPrint();
		sp.input();
		
		sp.s_prn();
		System.out.println("==========");
		sp.i_prn();
		
		
		
	}
}
