package com.gem.dao;

import java.util.List;

import com.gem.entity.Drug;

public interface DrugDao {
	//添加药品（有药品，就添加数量，没有该药品就新增）
	boolean insertDrug(Drug drug);

	//更新药品信息
	boolean updateDrug(Drug drug);

	//根据药品id删除药品
	boolean deleteDrugById(String did);

	//获取所有药品
	List<Drug> selectAllDrug();

	List<Drug> selectAllDrug(int curPage, int pageNum);

	//根据药品名获取药品
	List<Drug> selectDrugByName(String dname, int curPage, int pageNum);

	//根据药品id获取药品
	Drug selectDrugById(String did);

	//获取药品总个数
	int selectDrugCount();

	//获取名字是xx的药品的总个数
	int selectDrugCountLikeDame(String dname);
}
