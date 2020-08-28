package com.ict.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.ict.model.Paging;

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

	public int getCount() {
		int result = 0;
		result = sqlSessionTemplate.selectOne("tcount");
		
		return result;
	}
	
	public List<VO> getList(){
		List<VO> list = sqlSessionTemplate.selectList("select");
		return list;
	}
	
	public VO getOnelist(String idx){
		VO vo = sqlSessionTemplate.selectOne("onelist", idx);
		return vo;
	}
	
	
	public List<VO> getList(Paging paging){
		List<VO> list = null;
		
		// 파라미터값이 하나 이상이므로 VO 또는 Map를 만들어서 넣어주어야 한다.
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("begin", paging.getBegin());
		map.put("end", paging.getEnd());
		
		list = sqlSessionTemplate.selectList("list", map);
		
		return list;
	}
	
	
	public int getIDU(VO vo, String obj) {
		int result = 0;
		
		switch (obj) {
			case "Insert": result = sqlSessionTemplate.insert("insert", vo); break;

		}
				
		return result;
	}
	
	
}
