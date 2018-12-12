package com.ola.qh.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class CommentText {

	private String id;
	
	@NotEmpty(message="文本名称不能为空")
	private String textName;
	
	@NotNull
	private int textStatus;//文本类型(手法专业，价格优惠.....)

	public String getTextName() {
		return textName;
	}

	public void setTextName(String textName) {
		this.textName = textName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTextStatus() {
		return textStatus;
	}

	public void setTextStatus(int textStatus) {
		this.textStatus = textStatus;
	}
	
	
}
