package com.gem.service;

import java.util.List;

import com.gem.dao.DrugDao;
import com.gem.entity.Drug;
import com.gem.entity.PageInfor;
import com.gem.util.BeanFactory;

public class DrugServiceImpl implements DrugService {
	DrugDao drugDao = (DrugDao) BeanFactory.getBean("com.gem.dao.DrugDaoImpl");

	@Override
	public boolean addDrug(Drug drug) {
		List<Drug> drugs = drugDao.selectAllDrug();
		for (Drug drug1 : drugs) {
			if (drug1.getDid().equals(drug.getDid())) {
				return drugDao.updateDrug(drug);
			}
		}
		return drugDao.insertDrug(drug);
	}

	@Override
	public boolean updateDrug(Drug drug) {
		List<Drug> drugs = drugDao.selectAllDrug();
		for (Drug drug1 : drugs) {
			if (drug1.getDid().equals(drug.getDid())) {
				return drugDao.updateDrug(drug);
			}
		}
		return false;
	}

	@Override
	public boolean deleteDrugById(String did) {
		return drugDao.deleteDrugById(did);
	}

	@Override
	public PageInfor<Drug> getAllDrug(int curPage, int pageNum) {
		List<Drug> drugs = drugDao.selectAllDrug(curPage, pageNum);
		int totalNum = drugDao.selectDrugCount();
		PageInfor<Drug> pageInfor = new PageInfor<Drug>(drugs, curPage, pageNum, totalNum);
		return pageInfor;
	}

	@Override
	public PageInfor<Drug> getDrugByName(String dname, int curPage, int pageNum) {
		List<Drug> drugs = null;
		int totalNum = 0;
		if (dname != null && !dname.trim().equals("")) {
			drugs = drugDao.selectDrugByName(dname, curPage, pageNum);
			totalNum = drugDao.selectDrugCountLikeDame(dname);
		} else {
			drugs = drugDao.selectDrugByName(dname, curPage, pageNum);
			totalNum = drugDao.selectDrugCount();
		}
		PageInfor<Drug> pageInfor = new PageInfor<Drug>(drugs, curPage, pageNum, totalNum);
		return pageInfor;
	}

	@Override
	public Drug getDrugById(String did) {
		return drugDao.selectDrugById(did);
	}

}
