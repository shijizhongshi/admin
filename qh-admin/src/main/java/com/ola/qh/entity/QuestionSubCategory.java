package com.ola.qh.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class QuestionSubCategory {

	private String id;
	
	@NotEmpty(message="章id不能为空")
	private String categoryId;///大类别的id(章id)
	
	@NotEmpty(message="节名称不能为空")
	private String name;////节名称
	
	private int times;///考试时间
	
	@NotEmpty(message="章id不能为空")
	private String purposes;///用途(试卷还是章节联系)
	
	@NotEmpty(message="是否可见不能为空")
	private String isshow;
	
	private int count;
	
	private Date addtime;
	
	private Date updatetime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public String getPurposes() {
		return purposes;
	}

	public void setPurposes(String purposes) {
		this.purposes = purposes;
	}

	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
