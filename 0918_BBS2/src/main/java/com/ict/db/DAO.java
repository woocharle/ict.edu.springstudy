package com.ict.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.ict.model.Paging;

public class DAO {
	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public int getCount() {
		int result = sqlSessionTemplate.selectOne("count1");
		return result;
	}
	

	public List<VO> getList(Paging paging){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("begin", paging.getBegin());
		map.put("end", paging.getEnd());
	
		List<VO> list = sqlSessionTemplate.selectList("list", map);
		
		return list;
	}
	
	public VO getOnelist(String idx) {
		VO vo = sqlSessionTemplate.selectOne("onelist", idx);
		return vo;
	}
	
	public int getHitup(String idx) {
		int result = sqlSessionTemplate.update("hitup", idx);
		return result;
	}
	
	public int getLevup(Map<String, Integer> map) {
		int result = sqlSessionTemplate.update("lev_up", map);
		return result;
	}
	
	
	public int getIDU(VO vo, String mth) {
		int result = 0;
		
		switch (mth) {
			case "Insert1": result=sqlSessionTemplate.insert("insert1", vo);  break;
			case "Insert2": result=sqlSessionTemplate.insert("insert2", vo);  break;
			case "Update": result=sqlSessionTemplate.update("update", vo);  break;
			case "Delete": result=sqlSessionTemplate.delete("delete", vo);  break;

		}
		
		return result;
	}
	
	

}
