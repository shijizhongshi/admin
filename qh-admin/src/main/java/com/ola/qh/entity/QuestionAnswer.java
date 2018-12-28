package com.ola.qh.entity;

import java.util.Date;

public class QuestionAnswer {

	private String id;
	
	private String bankUnitId;
	
	private String answers;
	
	private String options;/////A B类似于这种的
	
	private Date addtime;

	
	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBankUnitId() {
		return bankUnitId;
	}

	public void setBankUnitId(String bankUnitId) {
		this.bankUnitId = bankUnitId;
	}

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}


	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	
}
