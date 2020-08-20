package com.ict.edu04;

import java.util.HashSet;
import java.util.Set;

public class SetPrint {
	Set<String> set1 = new HashSet<String>();
	Set<Integer> set2 = new HashSet<Integer>();
	
	public void input() {
		set1.add("100");
		set1.add("200");
		set1.add("300");
		set1.add("400");
		set1.add("500");
		
		set2.add(10);
		set2.add(20);
		set2.add(30);
		set2.add(40);
		set2.add(50);
	}
	
	public void s_prn() {
		for (String k : set1) {
			System.out.println(k+10);
		}
	}
	public void i_prn() {
		for (Integer k : set2) {
			System.out.println(k+10);
		}
	}
	public Set<String> getSet1() {
		return set1;
	}
	public void setSet1(Set<String> set1) {
		this.set1 = set1;
	}
	public Set<Integer> getSet2() {
		return set2;
	}
	public void setSet2(Set<Integer> set2) {
		this.set2 = set2;
	}
}
