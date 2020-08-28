package com.ict.db;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class DAO {
	private SqlSessionTemplate sqlSessionTemplate;
	
	public DAO() {}

	public DAO(SqlSessionTemplate sqlSessionTemplate) {
		super();
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public List<VO> getList(){
		List<VO> list= null;
		list = sqlSessionTemplate.selectList("select");
		return list;
	}
	
	public VO getOnelist(String idx){
		VO vo = new VO();
		vo = sqlSessionTemplate.selectOne("onelist", idx);
				
		return vo;
	}
	
	public int getIDU(VO vo, String mth) {
		int result = 0;
		switch (mth) {
			case "Insert": result = sqlSessionTemplate.insert("insert", vo); break;
			case "Update": result = sqlSessionTemplate.update("update", vo); break;
			case "Delete": result = sqlSessionTemplate.delete("delete", vo); break;
		}

		return result;
	}
	
	
	
}
