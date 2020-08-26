package com.ict.db;

import java.util.List;


public class VO {
	private String s1, s2, op, cPage;
	private String[] hobby;
	private List<String> hobby2;
	
	public VO() {}


	public VO(String s1, String s2, String op, String cPage, String[] hobby, List<String> hobby2) {
		super();
		this.s1 = s1;
		this.s2 = s2;
		this.op = op;
		this.cPage = cPage;
		this.hobby = hobby;
		this.hobby2 = hobby2;
	}

	public String getS1() {
		return s1;
	}

	public void setS1(String s1) {
		this.s1 = s1;
	}

	public String getS2() {
		return s2;
	}

	public void setS2(String s2) {
		this.s2 = s2;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getcPage() {
		return cPage;
	}

	public void setcPage(String cPage) {
		this.cPage = cPage;
	}

	public String[] getHobby() {
		return hobby;
	}

	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}

	public List<String> getHobby2() {
		return hobby2;
	}

	public void setHobby2(List<String> hobby2) {
		this.hobby2 = hobby2;
	}

	
}
