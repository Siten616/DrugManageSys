package com.gem.service;

import com.gem.entity.Drug;
import com.gem.entity.PageInfor;

public interface DrugService {
	//添加药品（有药品，就添加数量，没有该药品就新增）
	boolean addDrug(Drug drug);

	//更新药品信息
	boolean updateDrug(Drug drug);

	//根据药品id删除药品
	boolean deleteDrugById(String did);

	//获取所有药品
	PageInfor<Drug> getAllDrug(int curPage, int pageNum);

	//根据药品名获取药品
	PageInfor<Drug> getDrugByName(String dname, int curPage, int pageNum);

	//根据药品id获取药品
	Drug getDrugById(String did);

}
