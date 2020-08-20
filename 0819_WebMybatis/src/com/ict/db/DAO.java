package com.ict.db;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class DAO {
	//jsp는 SqlSession을 사용했지만, spring은 SqlSesssionTemplate를 사용.
	private SqlSessionTemplate sqlSessionTemplate;
	
	//DI를 활용
	public DAO() {}
	
	public DAO(SqlSessionTemplate sqlSessionTemplate) {
		super();
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	//DB처리하는 메소드 (사용방법은 SqlSession과 같다.)
	
	public List<VO> getList(){
		List<VO> list= null;
		list = sqlSessionTemplate.selectList("select");
		return list;
	}
	
	public VO getOne(String idx) {
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
