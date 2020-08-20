package com.ict.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class DAO {
	private JdbcTemplate jdbcTemplate;
	public DAO() {}
	
	public DAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<VO> getList(){
		String sql = "select * from members order by idx";
		return jdbcTemplate.query(sql, new RowMapper() {
			@Override
			public VO mapRow(ResultSet rs, int rowNum) throws SQLException {
				VO vo = new VO();
				vo.setIdx(rs.getString("idx"));
				vo.setM_id(rs.getString("m_id"));
				vo.setM_pw(rs.getString("m_pw"));
				vo.setM_name(rs.getString("m_name"));
				vo.setM_age(rs.getString("m_age"));
				vo.setM_reg(rs.getString("m_reg"));
				return vo;

			}		
		});
	}
	
	
	public List<VO> getOne(String idx){
		String sql = "select * from members where idx = ?";
		return jdbcTemplate.query(sql, new RowMapper() {
			@Override
			public VO mapRow(ResultSet rs, int rowNum) throws SQLException {
				VO vo = new VO();
				vo.setIdx(rs.getString("idx"));
				vo.setM_id(rs.getString("m_id"));
				vo.setM_pw(rs.getString("m_pw"));
				vo.setM_name(rs.getString("m_name"));
				vo.setM_age(rs.getString("m_age"));
				vo.setM_reg(rs.getString("m_reg"));
				return vo;

			}		
		}, idx);
	}
	
	
	
	public int getIDU(VO vo, String mth) {
		int result=0;
		
		String sql = "";
		
		switch (mth) {
			case "Insert":	
				sql = "insert into members \n"
					+ "values(members_seq.nextval, ? ,?, ?, ?, sysdate)";
				
				result = jdbcTemplate.update(sql, vo.getM_id(), vo.getM_pw(), vo.getM_name(), vo.getM_age());
				
			break;
			
			case "Update":	
				sql = "Update members Set m_id = ?, m_name = ?, m_age = ? \n"
						+ "where idx = ?";
				result = jdbcTemplate.update(sql, vo.getM_id(), vo.getM_name(), vo.getM_age(), vo.getIdx());
				
			break;
				
			case "Delete":
				sql = "Delete from members where idx = ?";
				result = jdbcTemplate.update(sql, vo.getIdx());				
			
			break;
			
		}
		
		return result;
	}
	
}
