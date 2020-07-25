package com.gem.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gem.entity.Drug;
import com.gem.util.JDBCUtil;

public class DrugDaoImpl implements DrugDao {
	JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());

	@Override
	public boolean insertDrug(Drug drug) {
		String sql = "insert into tb_drug values(?,?,?,?,?,?,?)";
		int count = template.update(sql, drug.getDid(), drug.getDname(), drug.getAddress(), drug.getProduction_date(),
				drug.getDeadline(), drug.getPrice(), drug.getStock());
		return count > 0;
	}

	@Override
	public boolean updateDrug(Drug drug) {
		String sql = "update tb_drug set dname=?,address=?,production_date=?,deadline=?,price=?,stock=? where did=?";
		int count = template.update(sql, drug.getDname(), drug.getAddress(), drug.getProduction_date(),
				drug.getDeadline(), drug.getPrice(), drug.getStock(), drug.getDid());
		return count > 0;
	}

	@Override
	public boolean deleteDrugById(String did) {
		String sql = "delete from tb_drug where did=?";
		int count = template.update(sql, did);
		return count > 0;
	}

	@Override
	public List<Drug> selectAllDrug() {
		String sql = "select * from tb_drug order by did asc";
		List<Drug> drugs = template.query(sql, new BeanPropertyRowMapper<Drug>(Drug.class));
		return drugs;
	}

	@Override
	public List<Drug> selectAllDrug(int curPage, int pageNum) {
		String sql = "select * from tb_drug limit ?,?";
		List<Drug> drugs = template.query(sql, new BeanPropertyRowMapper<Drug>(Drug.class), (curPage - 1) * pageNum,
				pageNum);
		return drugs;
	}

	@Override
	public List<Drug> selectDrugByName(String dname, int curPage, int pageNum) {
		String sql = "select  *  from tb_drug where dname like concat('%',?,'%') limit ?,?";
		List<Drug> drugs = template.query(sql, new BeanPropertyRowMapper<Drug>(Drug.class), dname,
				(curPage - 1) * pageNum, pageNum);
		return drugs;
	}

	@Override
	public Drug selectDrugById(String did) {
		String sql = "select * from tb_drug where did=?";
		List<Drug> drugs = template.query(sql, new BeanPropertyRowMapper<Drug>(Drug.class), did);
		if (drugs != null && drugs.size() > 0)
			return drugs.get(0);
		return null;
	}

	@Override
	public int selectDrugCount() {
		String sql = "select count(did) from tb_drug";
		int count = template.queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public int selectDrugCountLikeDame(String dname) {
		String sql = "select count(did) from tb_drug where dname like concat('%',?,'%')";
		int count = template.queryForObject(sql, Integer.class, dname);
		return count;
	}

}
