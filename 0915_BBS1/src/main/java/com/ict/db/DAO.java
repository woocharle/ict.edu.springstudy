package com.ict.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.ict.model.Paging;


public class DAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	
	public MVO getMember(String id) {
		MVO mvo = sqlSessionTemplate.selectOne("member", id);
		return mvo;
	}
	
	
	//DB처리: VO1
	
	public int getCount() {
		int result = sqlSessionTemplate.selectOne("bcount");
		return result; 
	}

	public int getHitup(String b_idx) {
		int result = sqlSessionTemplate.update("hitup", b_idx);
		return result;		
	}
	
	public List<VO1> getList1(Paging paging){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("begin", paging.getBegin());
		map.put("end", paging.getEnd());
		
		List<VO1> list = sqlSessionTemplate.selectList("blist", map); 
		return list;
	}
	
	public VO1 getOnelist1(String b_idx) {
		VO1 vo1 = sqlSessionTemplate.selectOne("bonelist", b_idx);
		return vo1;
		
	}
	
	public int getIDU1(VO1 vo1, String mth) {
		int result = 0;
		
		switch (mth) {
			case "Insert": result = sqlSessionTemplate.insert("binsert", vo1); break;
			case "Update": result = sqlSessionTemplate.update("bupdate", vo1); break;
			case "Delete": result = sqlSessionTemplate.delete("bdelete", vo1); break;
		}
		return result;
	}

	// DB처리: VO2
	
	public List<VO2> getList2(String b_idx){
		List<VO2> clist = sqlSessionTemplate.selectList("clist", b_idx);
		return clist; 
	}
	
	public int getIDU2(VO2 vo2, String mth) {
		int result = 0;
		
		switch (mth) {
			case "Insert": result = sqlSessionTemplate.insert("cinsert", vo2); break;
			case "Update": result = sqlSessionTemplate.update("cupdate", vo2); break;
			case "Delete": result = sqlSessionTemplate.delete("cdelete", vo2); break;
		}		
		return result;
	}
	
	
	
}
