package com.ola.qh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.QuestionSubCategory;

public interface QuestionSubcategoryDao {

	public List<QuestionSubCategory> selectQuestionSubCategory(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,@Param("categoryId")String categoryId);
	
	public int countSubCategory(@Param("categoryId")String categoryId);
	
	public int insertQuestionSubCategory(QuestionSubCategory questionSubCategory);
	
	public int updateQuestionSubCategory(QuestionSubCategory questionSubCategory);
	
	public int deleteQuestionSubCategory(@Param("id")String id,@Param("categoryId")String categoryId);
}
