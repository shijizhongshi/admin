package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionBank {

	private String id;

	private String subId;///// 属于哪个下边的题库

	private int numberNo;/// 题号

	private String title;/// 题干

	private String types;//// 问题的类型(单选)

	private String analysis;/// 问题的解析

	private String correct;/// 正确的答案

	private Date addtime;

	private Date updatetime;

	private String bankId;///// 共用题干类型的题 小单元题

	private List<QuestionAnswer> answer = new ArrayList<QuestionAnswer>();

	private List<QuestionUnit> unit = new ArrayList<QuestionUnit>();

	private String mobile;// 手机号

	private String realname;// 姓名

	private Integer status;// 用户属性 0：注册用户 | 1：游客

	private String courseTypeSubclassName;// 专业名

	private Integer banktotal;// 题目总数

	private Integer banktrue;// 答对题目数

	private Integer nobank;// 未答题数

	
	
	public Integer getBanktotal() {
		return banktotal;
	}

	public void setBanktotal(Integer banktotal) {
		this.banktotal = banktotal;
	}

	public Integer getBanktrue() {
		return banktrue;
	}

	public void setBanktrue(Integer banktrue) {
		this.banktrue = banktrue;
	}

	public Integer getNobank() {
		return nobank;
	}

	public void setNobank(Integer nobank) {
		this.nobank = nobank;
	}

	public String getCourseTypeSubclassName() {
		return courseTypeSubclassName;
	}

	public void setCourseTypeSubclassName(String courseTypeSubclassName) {
		this.courseTypeSubclassName = courseTypeSubclassName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public int getNumberNo() {
		return numberNo;
	}

	public void setNumberNo(int numberNo) {
		this.numberNo = numberNo;
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

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public List<QuestionAnswer> getAnswer() {
		return answer;
	}

	public void setAnswer(List<QuestionAnswer> answer) {
		this.answer = answer;
	}

	public List<QuestionUnit> getUnit() {
		return unit;
	}

	public void setUnit(List<QuestionUnit> unit) {
		this.unit = unit;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}
