package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.QuestionCategory;
import com.ola.qh.entity.QuestionSubCategory;

public interface QuestionCategoryDao {

	public int insertCategory(QuestionCategory qc);
	
	public int insertSubCategory(QuestionSubCategory qsc);
	
	public QuestionCategory singleCategory(@Param("categoryName")String categoryName,@Param("subclassName")String subclassName);
	
	public Integer existSubCategory(@Param("categoryId")String categoryId,@Param("subName")String subName);
	
	public List<QuestionCategory> selectCategory(@Param("courseTypeSubclassName")String courseTypeSubclassName);
	
	public int updateCategory(QuestionCategory qc);
	
	
}
