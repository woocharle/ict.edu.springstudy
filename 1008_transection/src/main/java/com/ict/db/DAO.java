package com.ict.db;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

public class DAO {
	private SqlSessionTemplate sqlSessionTemplate;
	private DataSourceTransactionManager transactionmanager;
	
	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	} 
	@Autowired
	public void setTransactionmanager(DataSourceTransactionManager transactionmanager) {
		this.transactionmanager = transactionmanager;
	}	
	
	public int getIDU1(VO vo, String mth) {
		int result = 0;
		switch (mth) {
			case "Insert": result = sqlSessionTemplate.insert("cardinsert", vo); break;
			

		}
		
		return result;

	}
	

	public int getIDU2(VO vo, String mth) {
		int result = 0;
		switch (mth) {
			case "Insert": result = sqlSessionTemplate.insert("ticketinsert", vo);break;
				
		}
		
		return result;
		
	}
	
	
}
