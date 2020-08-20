package com.ict.edu04;

public class Array {
	String[] arr1 = {"100", "200", "300", "400"};
	int[] arr2 = {100, 200, 300, 400};
	
	
	public String[] getArr1() {
		return arr1;
	}
	public void setArr1(String[] arr1) {
		this.arr1 = arr1;
	}
	public int[] getArr2() {
		return arr2;
	}
	public void setArr2(int[] arr2) {
		this.arr2 = arr2;
	}
	
	public void s_pri() {
		for (String k : arr1) {
			System.out.println(k + 10);
		}
	}
	
	public void i_pri() {
		for (int k : arr2) {
			System.out.println(k);
		}
	}
	
	
	
	
}
