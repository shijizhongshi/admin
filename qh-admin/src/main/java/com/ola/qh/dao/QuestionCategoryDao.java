package com.ola.qh.dao;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.QuestionCategory;
import com.ola.qh.entity.QuestionSubCategory;

public interface QuestionCategoryDao {

	public int insertCategory(QuestionCategory qc);
	
	public int insertSubCategory(QuestionSubCategory qsc);
	
	public Integer existCategory(@Param("categoryName")String categoryName,@Param("subclassName")String subclassName);
	
	public Integer existSubCategory(@Param("categoryId")String categoryId,@Param("subName")String subName);
}
