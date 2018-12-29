package com.ola.qh.service.imp;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ola.qh.dao.QuestionBankDao;
import com.ola.qh.dao.QuestionCategoryDao;
import com.ola.qh.entity.QuestionAnswer;
import com.ola.qh.entity.QuestionBank;
import com.ola.qh.entity.QuestionCategory;
import com.ola.qh.entity.QuestionSubCategory;
import com.ola.qh.service.IQuestionBankService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@Service
public class QuestionBankService implements IQuestionBankService {

	@Autowired
	private QuestionCategoryDao questionCategoryDao;
	@Autowired
	private QuestionBankDao questionBankDao;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<String> importExcel(MultipartFile file) throws Exception{
		Results<String> result = new Results<String>();
			Workbook wb = WorkbookFactory.create(file.getInputStream());
			for (int z = 0; z < wb.getNumberOfSheets(); z++) {
				Sheet sheet = wb.getSheetAt(z);
				int rowNumber = sheet.getPhysicalNumberOfRows() - 1;
				Iterator<Row> rowIterator = sheet.rowIterator();
				Row titleRow = rowIterator.next();
				int columnNumber = titleRow.getLastCellNum();
				/*String[][] table = new String[rowNumber][columnNumber];*/
				String categoryId = null;/////
				String subId = KeyGen.uuid();
				String bankId = null;
				for (int i = 0; i < rowNumber && rowIterator.hasNext(); i++) {
					Row row = rowIterator.next();
					if (i == 0) {
						QuestionCategory qc1 = questionCategoryDao.singleCategory(checkNull(2, row), checkNull(1, row));
						if(qc1==null){
						///// 保存question_bank_category的信息
							QuestionCategory qc = new QuestionCategory();
							categoryId=KeyGen.uuid();
							qc.setId(categoryId);
							qc.setCourseTypeName(checkNull(0, row));
							qc.setCourseTypeSubclassName(checkNull(1, row));
							qc.setTypes(checkNull(2, row));
							qc.setName(checkNull(2, row));
							qc.setIsshow("1");
							qc.setAddtime(new Date());
							questionCategoryDao.insertCategory(qc);
						}else{
							/////重新赋值
							categoryId=qc1.getId();
						}
						
					}
					if (i == 1) {
						if(questionCategoryDao.existSubCategory(categoryId, checkNull(0, row)).intValue()!=0){
							result.setStatus("1");
							result.setMessage("这个类别下的题库已经上传过了请检查");
							return result;
						}
						///// 保存question_bank_subcategory的信息
						QuestionSubCategory qsc = new QuestionSubCategory();
						qsc.setId(subId);
						qsc.setCategoryId(categoryId);
						qsc.setIsshow("1");
						qsc.setName(checkNull(0, row));
						qsc.setTimes(checkNull(1, row));
						qsc.setPurposes(checkNull(2, row));
						qsc.setAddtime(new Date());
						questionCategoryDao.insertSubCategory(qsc);
					} 
					if(i!=0 && i!=1) {
						////// 保存题库的信息
						if ("共用题干".equals(checkNull(0, row))) {
							/// 保存他下边的集合
							QuestionBank qb = new QuestionBank();
							qb.setAddtime(new Date());
							bankId = KeyGen.uuid();
							qb.setId(bankId);
							qb.setNumberNo(i);
							qb.setTitle(checkNull(1, row));
							qb.setTypes(checkNull(0, row));
							qb.setSubId(subId);
							questionBankDao.insertQuestionBank(qb);
						}
						if ("单选".equals(checkNull(0, row)) || "多选".equals(checkNull(0, row))) {
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
							if (checkNull(3, row) != null) {
								qa.setAnswers(checkNull(3, row));
								qa.setId(KeyGen.uuid());
								qa.setOptions("A");
								if(checkNull(2, row).contains("A")){
									qa.setCorrect(true);
								}else{
									qa.setCorrect(false);
								}
								qa.setOrders(0);
								questionBankDao.insertQuestionAnswer(qa);
							}
							if (checkNull(4, row) != null) {
								qa.setAnswers(checkNull(4, row));
								qa.setId(KeyGen.uuid());
								qa.setOptions("B");
								if(checkNull(2, row).contains("B")){
									qa.setCorrect(true);
								}else{
									qa.setCorrect(false);
								}
								qa.setOrders(1);
								questionBankDao.insertQuestionAnswer(qa);
							}
							if (checkNull(5, row) != null) {
								qa.setAnswers(checkNull(5, row));
								qa.setId(KeyGen.uuid());
								qa.setOptions("C");
								if(checkNull(2, row).contains("C")){
									qa.setCorrect(true);
								}else{
									qa.setCorrect(false);
								}
								qa.setOrders(2);
								questionBankDao.insertQuestionAnswer(qa);
							}
							if (checkNull(6, row) != null) {
								qa.setAnswers(checkNull(6, row));
								qa.setId(KeyGen.uuid());
								qa.setOptions("D");
								if(checkNull(2, row).contains("D")){
									qa.setCorrect(true);
								}else{
									qa.setCorrect(false);
								}
								qa.setOrders(3);
								questionBankDao.insertQuestionAnswer(qa);
							}
							if (checkNull(7, row) != null) {
								qa.setAnswers(checkNull(7, row));
								qa.setId(KeyGen.uuid());
								qa.setOptions("E");
								if(checkNull(2, row).contains("E")){
									qa.setCorrect(true);
								}else{
									qa.setCorrect(false);
								}
								qa.setOrders(4);
								questionBankDao.insertQuestionAnswer(qa);
							}

							if (bankId != null) {
								///// 共同题干的问题
								qb.setBankId(bankId);
								qb.setAnalysis(checkNull(8, row));
								questionBankDao.insertQuestionUnit(qb);
							} else {
								/////// 单纯的单选或者多选的问题
								qb.setAnalysis(checkNull(8, row));
								qb.setNumberNo(i);
								questionBankDao.insertQuestionBank(qb);
							}

						}
					}

				}

			}
			result.setStatus("0");
			return result;
		
	}

	public static String checkNull(int i, Row row) {

		Cell cell = row.getCell(i);
		cell.setCellType(CellType.STRING);
		if (cell.getStringCellValue() != null && !"".equals(cell.getStringCellValue())) {
			return cell.getStringCellValue();
		}
		return null;
	}

}
