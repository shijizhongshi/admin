package com.ola.qh.service.imp;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.QuestionBankDao;
import com.ola.qh.entity.QuestionAnswer;
import com.ola.qh.entity.QuestionBank;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;
@Service
public class InportBankTemplateService {
	
	@Autowired
	private QuestionBankDao questionBankDao;

	public Results<String> BankTemplateAnswerImg(int rowNumber,Iterator<Row> rowIterator,String subId,List<String> urlss){
		
		Results<String> results = new Results<String>();
		
		String bankId = null;
		int n = 0;
		for (int i = 0; i < rowNumber && rowIterator.hasNext(); i++) {
			Row row = rowIterator.next();
			////// 保存题库的信息
			if ("共用题干".equals(checkNull(0, row)) || "共用选项".equals(checkNull(0, row))) {
				/// 保存他下边的集合
				QuestionBank qb = new QuestionBank();
				qb.setAddtime(new Date());
				bankId = KeyGen.uuid();
				qb.setId(bankId);
				// qb.setNumberNo(n+1);
				qb.setTitle(checkNull(1, row));
				qb.setTypes(checkNull(0, row));
				qb.setSubId(subId);
				if(urlss!=null){
					for(String imageurl : urlss){
						if(imageurl.indexOf("/"+(i+1)+"-2")>0){
							qb.setTitleimg(imageurl);
							urlss.remove(imageurl);
							break;
						}
					}
				}
				questionBankDao.insertQuestionBank(qb);
				// n++;
			}
			if ("单选题".equals(checkNull(0, row)) || "多选题".equals(checkNull(0, row))) {
				QuestionBank qb = new QuestionBank();
				qb.setAddtime(new Date());
				String unitId = KeyGen.uuid();
				qb.setId(unitId);
				qb.setTitle(checkNull(1, row));
				qb.setTypes(checkNull(0, row));
				qb.setSubId(subId);
				qb.setCorrect(checkNull(3, row));
				QuestionAnswer qa = new QuestionAnswer();
				qa.setBankUnitId(unitId);
				qa.setAddtime(new Date());
				if(urlss!=null){
					for(String imageurl : urlss){
						if(imageurl.indexOf("/"+(i+1)+"-2")>=0){
							qb.setTitleimg(imageurl);
							urlss.remove(imageurl);
							break;
						}
					}
				}
				if (checkNull(4, row).trim() != null) {
					qa.setAnswers(checkNull(4, row));
					qa.setId(KeyGen.uuid());
					qa.setOptions("A");
					if (checkNull(3, row).contains("A")) {
						qa.setCorrect(true);
					} else {
						qa.setCorrect(false);
					}
					qa.setOrders(0);
					qa.setTitleimg(null);
					for(String imageurl : urlss){
						///////A对应的图片
						if(imageurl.indexOf("/"+(i+1)+"-5")>=0){
							qa.setTitleimg(imageurl);
							urlss.remove(imageurl);
							break;
						}
					}
					questionBankDao.insertQuestionAnswer(qa);
				}
				if (checkNull(6, row)!= null) {
					qa.setAnswers(checkNull(6, row));
					qa.setId(KeyGen.uuid());
					qa.setOptions("B");
					if (checkNull(3, row).contains("B")) {
						qa.setCorrect(true);
					} else {
						qa.setCorrect(false);
					}
					qa.setOrders(1);
					qa.setTitleimg(null);
					for(String imageurl : urlss){
						///////B对应的图片
						if(imageurl.indexOf("/"+(i+1)+"-7")>=0){
							qa.setTitleimg(imageurl);
							urlss.remove(imageurl);
							break;
						}
					}
					questionBankDao.insertQuestionAnswer(qa);
				}
				if (checkNull(8, row) != null) {
					qa.setAnswers(checkNull(8, row));
					qa.setId(KeyGen.uuid());
					qa.setOptions("C");
					if (checkNull(3, row).contains("C")) {
						qa.setCorrect(true);
					} else {
						qa.setCorrect(false);
					}
					qa.setOrders(2);
					qa.setTitleimg(null);
					for(String imageurl : urlss){
						///////C对应的图片
						if(imageurl.indexOf("/"+(i+1)+"-9")>=0){
							qa.setTitleimg(imageurl);
							urlss.remove(imageurl);
							break;
						}
					}
					questionBankDao.insertQuestionAnswer(qa);
				}
				if (checkNull(10, row) != null) {
					qa.setAnswers(checkNull(10, row));
					qa.setId(KeyGen.uuid());
					qa.setOptions("D");
					if (checkNull(3, row).contains("D")) {
						qa.setCorrect(true);
					} else {
						qa.setCorrect(false);
					}
					qa.setOrders(3);
					qa.setTitleimg(null);
					for(String imageurl : urlss){
						///////D对应的图片
						if(imageurl.indexOf("/"+(i+1)+"-11")>=0){
							qa.setTitleimg(imageurl);
							urlss.remove(imageurl);
							break;
						}
					}
					questionBankDao.insertQuestionAnswer(qa);
				}
				if (checkNull(12, row) != null) {
					qa.setAnswers(checkNull(12, row));
					qa.setId(KeyGen.uuid());
					qa.setOptions("E");
					if (checkNull(3, row).contains("E")) {
						qa.setCorrect(true);
					} else {
						qa.setCorrect(false);
					}
					qa.setOrders(4);
					qa.setTitleimg(null);
					for(String imageurl : urlss){
						///////E对应的图片
						if(imageurl.indexOf("/"+(i+1)+"-13")>=0){
							qa.setTitleimg(imageurl);
							urlss.remove(imageurl);
							break;
						}
					}
					questionBankDao.insertQuestionAnswer(qa);
				}

				if (bankId != null) {
					///// 共同题干的问题
					qb.setBankId(bankId);
					qb.setAnalysis(checkNull(14, row));
					qb.setNumberNo(n + 1);

					questionBankDao.insertQuestionUnit(qb);
					n++;
				} else {
					/////// 单纯的单选或者多选的问题
					qb.setAnalysis(checkNull(14, row));
					qb.setNumberNo(n + 1);
					questionBankDao.insertQuestionBank(qb);
					n++;
				}

			}

		}
		results.setStatus("0");
		return results;
	}
	
	public Results<String> BankTemplateTitleImg(int rowNumber,Iterator<Row> rowIterator,String subId,List<String> urlss){
		
		Results<String> results = new Results<String>();
		
		String bankId = null;
		int n = 0;
		for (int i = 0; i < rowNumber && rowIterator.hasNext(); i++) {
			Row row = rowIterator.next();
			////// 保存题库的信息
			if ("共用题干".equals(checkNull(0, row)) || "共用选项".equals(checkNull(0, row))) {
				/// 保存他下边的集合
				QuestionBank qb = new QuestionBank();
				qb.setAddtime(new Date());
				bankId = KeyGen.uuid();
				qb.setId(bankId);
				// qb.setNumberNo(n+1);
				qb.setTitle(checkNull(1, row));
				qb.setTypes(checkNull(0, row));
				qb.setSubId(subId);
				if(urlss!=null){
					for(String imageurl : urlss){
						if(imageurl.indexOf("/"+(i+1)+"-2")>0){
							qb.setTitleimg(imageurl);
							urlss.remove(imageurl);
							break;
						}
					}
				}
				questionBankDao.insertQuestionBank(qb);
				// n++;
			}
			if ("单选题".equals(checkNull(0, row)) || "多选题".equals(checkNull(0, row))) {
				QuestionBank qb = new QuestionBank();
				qb.setAddtime(new Date());
				String unitId = KeyGen.uuid();
				qb.setId(unitId);
				qb.setTitle(checkNull(1, row));
				qb.setTypes(checkNull(0, row));
				qb.setSubId(subId);
				qb.setCorrect(checkNull(3, row));
				QuestionAnswer qa = new QuestionAnswer();
				qa.setBankUnitId(unitId);
				qa.setAddtime(new Date());
				if(urlss!=null){
					for(String imageurl : urlss){
						if(imageurl.indexOf("/"+(i+1)+"-2")>=0){
							qb.setTitleimg(imageurl);
							urlss.remove(imageurl);
							break;
						}
					}
				}
				if (checkNull(4, row).trim() != null) {
					qa.setAnswers(checkNull(4, row));
					qa.setId(KeyGen.uuid());
					qa.setOptions("A");
					if (checkNull(3, row).contains("A")) {
						qa.setCorrect(true);
					} else {
						qa.setCorrect(false);
					}
					qa.setOrders(0);
					qa.setTitleimg(null);
					questionBankDao.insertQuestionAnswer(qa);
				}
				if (checkNull(5, row)!= null) {
					qa.setAnswers(checkNull(5, row));
					qa.setId(KeyGen.uuid());
					qa.setOptions("B");
					if (checkNull(3, row).contains("B")) {
						qa.setCorrect(true);
					} else {
						qa.setCorrect(false);
					}
					qa.setOrders(1);
					qa.setTitleimg(null);
					questionBankDao.insertQuestionAnswer(qa);
				}
				if (checkNull(6, row) != null) {
					qa.setAnswers(checkNull(6, row));
					qa.setId(KeyGen.uuid());
					qa.setOptions("C");
					if (checkNull(3, row).contains("C")) {
						qa.setCorrect(true);
					} else {
						qa.setCorrect(false);
					}
					qa.setOrders(2);
					qa.setTitleimg(null);
					questionBankDao.insertQuestionAnswer(qa);
				}
				if (checkNull(7, row) != null) {
					qa.setAnswers(checkNull(7, row));
					qa.setId(KeyGen.uuid());
					qa.setOptions("D");
					if (checkNull(3, row).contains("D")) {
						qa.setCorrect(true);
					} else {
						qa.setCorrect(false);
					}
					qa.setOrders(3);
					qa.setTitleimg(null);
					questionBankDao.insertQuestionAnswer(qa);
				}
				if (checkNull(8, row) != null) {
					qa.setAnswers(checkNull(8, row));
					qa.setId(KeyGen.uuid());
					qa.setOptions("E");
					if (checkNull(3, row).contains("E")) {
						qa.setCorrect(true);
					} else {
						qa.setCorrect(false);
					}
					qa.setOrders(4);
					qa.setTitleimg(null);
					questionBankDao.insertQuestionAnswer(qa);
				}

				if (bankId != null) {
					///// 共同题干的问题
					qb.setBankId(bankId);
					qb.setAnalysis(checkNull(9, row));
					qb.setNumberNo(n + 1);

					questionBankDao.insertQuestionUnit(qb);
					n++;
				} else {
					/////// 单纯的单选或者多选的问题
					qb.setAnalysis(checkNull(9, row));
					qb.setNumberNo(n + 1);
					questionBankDao.insertQuestionBank(qb);
					n++;
				}

			}

		}
		results.setStatus("0");
		return results;
	}
public Results<String> BankTemplateNoImg(int rowNumber,Iterator<Row> rowIterator,String subId){
		
		Results<String> results = new Results<String>();
		
		String bankId = null;
		int n = 0;
		for (int i = 0; i < rowNumber && rowIterator.hasNext(); i++) {
			Row row = rowIterator.next();
			////// 保存题库的信息
			if ("共用题干".equals(checkNull(0, row)) || "共用选项".equals(checkNull(0, row))) {
				/// 保存他下边的集合
				QuestionBank qb = new QuestionBank();
				qb.setAddtime(new Date());
				bankId = KeyGen.uuid();
				qb.setId(bankId);
				// qb.setNumberNo(n+1);
				qb.setTitle(checkNull(1, row));
				qb.setTypes(checkNull(0, row));
				qb.setSubId(subId);
				questionBankDao.insertQuestionBank(qb);
				// n++;
			}
			if ("单选题".equals(checkNull(0, row)) || "多选题".equals(checkNull(0, row))) {
				QuestionBank qb = new QuestionBank();
				qb.setAddtime(new Date());
				String unitId = KeyGen.uuid();
				qb.setId(unitId);
				qb.setTitle(checkNull(1, row));
				qb.setTypes(checkNull(0, row));
				qb.setSubId(subId);
				qb.setCorrect(checkNull(2, row));
				QuestionAnswer qa = new QuestionAnswer();
				qa.setBankUnitId(unitId);
				qa.setAddtime(new Date());
				if (checkNull(3, row).trim() != null) {
					qa.setAnswers(checkNull(3, row));
					qa.setId(KeyGen.uuid());
					qa.setOptions("A");
					if (checkNull(2, row).contains("A")) {
						qa.setCorrect(true);
					} else {
						qa.setCorrect(false);
					}
					qa.setOrders(0);
					qa.setTitleimg(null);
					questionBankDao.insertQuestionAnswer(qa);
				}
				if (checkNull(4, row)!= null) {
					qa.setAnswers(checkNull(4, row));
					qa.setId(KeyGen.uuid());
					qa.setOptions("B");
					if (checkNull(2, row).contains("B")) {
						qa.setCorrect(true);
					} else {
						qa.setCorrect(false);
					}
					qa.setOrders(1);
					qa.setTitleimg(null);
					questionBankDao.insertQuestionAnswer(qa);
				}
				if (checkNull(5, row) != null) {
					qa.setAnswers(checkNull(5, row));
					qa.setId(KeyGen.uuid());
					qa.setOptions("C");
					if (checkNull(2, row).contains("C")) {
						qa.setCorrect(true);
					} else {
						qa.setCorrect(false);
					}
					qa.setOrders(2);
					qa.setTitleimg(null);
					questionBankDao.insertQuestionAnswer(qa);
				}
				if (checkNull(6, row) != null) {
					qa.setAnswers(checkNull(6, row));
					qa.setId(KeyGen.uuid());
					qa.setOptions("D");
					if (checkNull(2, row).contains("D")) {
						qa.setCorrect(true);
					} else {
						qa.setCorrect(false);
					}
					qa.setOrders(3);
					qa.setTitleimg(null);
					questionBankDao.insertQuestionAnswer(qa);
				}
				if (checkNull(7, row) != null) {
					qa.setAnswers(checkNull(7, row));
					qa.setId(KeyGen.uuid());
					qa.setOptions("E");
					if (checkNull(2, row).contains("E")) {
						qa.setCorrect(true);
					} else {
						qa.setCorrect(false);
					}
					qa.setOrders(4);
					qa.setTitleimg(null);
					questionBankDao.insertQuestionAnswer(qa);
				}

				if (bankId != null) {
					///// 共同题干的问题
					qb.setBankId(bankId);
					qb.setAnalysis(checkNull(8, row));
					qb.setNumberNo(n + 1);

					questionBankDao.insertQuestionUnit(qb);
					n++;
				} else {
					/////// 单纯的单选或者多选的问题
					qb.setAnalysis(checkNull(8, row));
					qb.setNumberNo(n + 1);
					questionBankDao.insertQuestionBank(qb);
					n++;
				}

			}

		}
		results.setStatus("0");
		return results;
	}
	public static String checkNull(int i, Row row) {

		Cell cell = row.getCell(i);
		if (cell != null) {
			cell.setCellType(CellType.STRING);
			if (cell.getStringCellValue() != null && !"".equals(cell.getStringCellValue())) {
				return cell.getStringCellValue();
			}
		}

		return null;
	}
}
