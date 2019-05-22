package com.ola.qh.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ola.qh.entity.CourseLiveCheck;
import com.ola.qh.entity.PlayLog;
import com.ola.qh.entity.QuestionBank;
import com.ola.qh.entity.UserEnterLeaveActions;
import com.ola.qh.util.Results;

public interface IQuestionBankService {

	public Results<String> importExcel(MultipartFile file,String subId,int status) throws Exception;
	
	public Results<List<QuestionBank>> selectQuestionBank(String subId,int pageNo,int pageSize);
	
	public Results<String> updateQuestionBank(QuestionBank questionBank);
	
	public Results<String> deleteQuestionBank(String id);

	public Results<List<QuestionBank>> selectQuestionBankList(String realname, String courseTypeSubclassName,
			String status, int page);

	public Results<List<CourseLiveCheck>> selectLiveVerifyList( String fromdate,String todate,
			int pageNo,int pageSize, String mobile, String roomId,String courseTypeSubclassName);

	public Results<List<UserEnterLeaveActions>> liveAccess(String notToEnter, String liveId, String pagenum,
			String pageindex);

	public Results<List<PlayLog>> ccVideo(String videoId, String mobile, String date, String numPerPage, String page);
	
}
