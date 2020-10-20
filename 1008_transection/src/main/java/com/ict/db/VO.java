package com.ict.db;

public class VO {
	private String consumerid, amount, countnum;

	public VO() {}
	
	public VO(String consumerid, String amount, String countnum) {
		super();
		this.consumerid = consumerid;
		this.amount = amount;
		this.countnum = countnum;
	}



	public String getConsumerid() {
		return consumerid;
	}

	public void setConsumerid(String consumerid) {
		this.consumerid = consumerid;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCountnum() {
		return countnum;
	}

	public void setCountnum(String countnum) {
		this.countnum = countnum;
	}
	
	
	
	

}
