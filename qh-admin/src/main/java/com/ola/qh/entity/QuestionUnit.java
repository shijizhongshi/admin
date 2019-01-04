package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionUnit {

	private String id;
	
	private String bankId;
	
	private String title;
	
	private String types;
	
	private String analysis;
	
	private String correct;
	
	private Date addtime;
	
	private Date updatetime;
	
	private List<QuestionAnswer> unitAnswer=new ArrayList<QuestionAnswer>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
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

	public List<QuestionAnswer> getUnitAnswer() {
		return unitAnswer;
	}

	public void setUnitAnswer(List<QuestionAnswer> unitAnswer) {
		this.unitAnswer = unitAnswer;
	}
	
	
}
