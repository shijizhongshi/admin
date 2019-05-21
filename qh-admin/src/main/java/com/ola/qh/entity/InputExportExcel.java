package com.ola.qh.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class InputExportExcel {

	@NotEmpty
	private int types;//////////////////////类型至关重要
	
	
	private List<CourseLiveCheck> listLiveCheck=new ArrayList<CourseLiveCheck>();///////////////直播验证数据
	
	private List<QuestionBank> questionBank=new ArrayList<QuestionBank>();///////////////H5题库
	
	private List<PlayLog> playLog=new ArrayList<PlayLog>();///////////////学习记录
	
	private List<UserEnterLeaveActions> userEnterLeaveActions=new ArrayList<UserEnterLeaveActions>();///////////////直播访问记录
	
	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
	}

	public List<CourseLiveCheck> getListLiveCheck() {
		return listLiveCheck;
	}

	public void setListLiveCheck(List<CourseLiveCheck> listLiveCheck) {
		this.listLiveCheck = listLiveCheck;
	}

	public List<QuestionBank> getQuestionBank() {
		return questionBank;
	}

	public void setQuestionBank(List<QuestionBank> questionBank) {
		this.questionBank = questionBank;
	}

	public List<PlayLog> getPlayLog() {
		return playLog;
	}

	public void setPlayLog(List<PlayLog> playLog) {
		this.playLog = playLog;
	}

	public List<UserEnterLeaveActions> getUserEnterLeaveActions() {
		return userEnterLeaveActions;
	}

	public void setUserEnterLeaveActions(List<UserEnterLeaveActions> userEnterLeaveActions) {
		this.userEnterLeaveActions = userEnterLeaveActions;
	}

	
	
}
