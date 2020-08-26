package com.ict.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class DAO {
	private JdbcTemplate jdbcTemplate;
	public DAO() {}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<VO> getList(){
		String sql = "select * from guestbook order by idx";
		return jdbcTemplate.query(sql, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				VO vo = new VO();
				vo.setIdx(rs.getString("idx"));
				vo.setName(rs.getString("name"));
				vo.setSubject(rs.getString("subject"));
				vo.setContent(rs.getString("content"));
				vo.setEmail(rs.getString("email"));
				vo.setPwd(rs.getString("pwd"));
				vo.setRegdate(rs.getString("regdate"));
				return vo;
			}
			
		});
		
	}
	
	public List<VO> getOnelist(String idx){
		String sql = "select * from guestbook where idx = ?";
		return jdbcTemplate.query(sql, new RowMapper() {
			@Override
			public VO mapRow(ResultSet rs, int rowNum) throws SQLException {
				VO vo = new VO();
				vo.setIdx(rs.getString("idx"));
				vo.setName(rs.getString("name"));
				vo.setSubject(rs.getString("subject"));
				vo.setContent(rs.getString("content"));
				vo.setEmail(rs.getString("email"));
				vo.setPwd(rs.getString("pwd"));
				vo.setRegdate(rs.getString("regdate"));
				return vo;

			}		
		}, idx);
	}
	
	
	public int getIDU(VO vo, String mth) {
		int result = 0;
		String sql = "";
		
		switch (mth) {
			case "Insert":	
				sql = "insert into guestbook \n"
					+ "values(guestbook_seq.nextval, ? ,?, ?, ?, ?, sysdate)";
				
				result = jdbcTemplate.update(sql, vo.getName(), vo.getSubject(), vo.getContent(), vo.getEmail(), vo.getPwd());
				
			break;
			
			case "Update":
				sql = "update guestbook \n"+
					  "set name=?, subject=?, content =?, email=? where idx=?";
				
				result = jdbcTemplate.update(sql, vo.getName(), vo.getSubject(), vo.getContent(), vo.getEmail(), vo.getIdx());
			
			break;
			
			case "Delete":
				
				sql = "delete from guestbook where idx = ?";
				result = jdbcTemplate.update(sql, vo.getIdx());
			
			break;
			
		}
		return result;
	
	}
	
}














