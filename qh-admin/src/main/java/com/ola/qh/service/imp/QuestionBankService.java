package com.ola.qh.service.imp;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.ola.qh.dao.QuestionBankDao;
import com.ola.qh.entity.QuestionAnswer;
import com.ola.qh.entity.QuestionBank;
import com.ola.qh.entity.QuestionUnit;
import com.ola.qh.service.IQuestionBankService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@Service
public class QuestionBankService implements IQuestionBankService {

	@Autowired
	private QuestionBankDao questionBankDao;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<String> importExcel(MultipartFile file,String subId) throws Exception{
		Results<String> result = new Results<String>();
			Workbook wb = WorkbookFactory.create(file.getInputStream());
			
				Sheet sheet = wb.getSheetAt(0);
				int rowNumber = sheet.getPhysicalNumberOfRows() - 1;
				Iterator<Row> rowIterator = sheet.rowIterator();
				Row titleRow = rowIterator.next();
				int columnNumber = titleRow.getLastCellNum();
				/*String[][] table = new String[rowNumber][columnNumber];*/
				String bankId = null;
				int n=0;
				for (int i = 0; i < rowNumber && rowIterator.hasNext(); i++) {
					Row row = rowIterator.next();
						////// 保存题库的信息
						if ("共用题干".equals(checkNull(0, row)) || "共用选项".equals(checkNull(0, row))) {
							/// 保存他下边的集合
							QuestionBank qb = new QuestionBank();
							qb.setAddtime(new Date());
							bankId = KeyGen.uuid();
							qb.setId(bankId);
							//qb.setNumberNo(n+1);
							qb.setTitle(checkNull(1, row));
							qb.setTypes(checkNull(0, row));
							qb.setSubId(subId);
							questionBankDao.insertQuestionBank(qb);
							//n++;
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
								qb.setNumberNo(n+1);
								questionBankDao.insertQuestionUnit(qb);
								n++;
							} else {
								/////// 单纯的单选或者多选的问题
								qb.setAnalysis(checkNull(8, row));
								qb.setNumberNo(n+1);
								questionBankDao.insertQuestionBank(qb);
								n++;
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

	@Transactional
	@Override
	public Results<List<QuestionBank>> selectQuestionBank(String subId,int pageNo,int pageSize) {
		
		Results<List<QuestionBank>> results=new Results<List<QuestionBank>>();
		try {
			
			List<QuestionBank> listbank=questionBankDao.selectQuestionBank(subId,pageNo,pageSize);
			
			int count=questionBankDao.countQuestionBank(subId);
			
			for (QuestionBank questionBank : listbank) {
				
				List<QuestionAnswer> listanswer=questionBankDao.selectQuestionAnswer(questionBank.getId());
				
				questionBank.setAnswer(listanswer);
				
				List<QuestionUnit> listunit=questionBankDao.selectQuestionUnit(questionBank.getId());
				
				for (QuestionUnit questionUnit : listunit) {
					
					List<QuestionAnswer> listanswerunit=questionBankDao.selectQuestionAnswer(questionUnit.getId());
					
					questionUnit.setUnitAnswer(listanswerunit);
				}
				questionBank.setUnit(listunit);
				
				
			}
			
			
			
			results.setData(listbank);
			results.setCount(count);
			results.setStatus("0");
			return results;
			
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
		
	}

	@Transactional
	@Override
	public Results<String> deleteQuestionBank(String id) {
		
		Results<String> results=new Results<String>();
		
		try {
			questionBankDao.deleteQuestionBank(id);
			questionBankDao.deleteQuestionAnswer(id);
			List<QuestionUnit> listunit=questionBankDao.selectQuestionUnit(id);
			
			for (QuestionUnit questionUnit : listunit) {
				
				questionBankDao.deleteQuestionUnit(questionUnit.getId());
				questionBankDao.deleteQuestionAnswer(questionUnit.getId());
			}
			
			
			results.setStatus("0");
			return results;
			
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
		
	}

	@Transactional
	@Override
	public Results<String> updateQuestionBank(QuestionBank questionBank) {
		
		Results<String> results=new  Results<String>();
		try {
			
			questionBank.setUpdatetime(new Date());
			questionBankDao.updateQuestionBank(questionBank);
			
			List<QuestionAnswer> listanswer=questionBank.getAnswer();
			List<QuestionUnit> unit=questionBank.getUnit();
			
			if(listanswer!=null && !"".equals(listanswer)){
				
				for (QuestionAnswer questionAnswer : listanswer) {
					questionAnswer.setUpdatetime(new Date());
					questionBankDao.updateQuestionAnswer(questionAnswer);
				}
			}
			
			if(unit!=null && !"".equals(unit)){
				
				for (QuestionUnit questionUnit : unit) {
					
					List<QuestionAnswer> unitAnswer=questionUnit.getUnitAnswer();
					questionUnit.setUpdatetime(new Date());
					questionBankDao.updateQuestionUnit(questionUnit);
					
					for (QuestionAnswer questionAnswer : unitAnswer) {
						questionAnswer.setUpdatetime(new Date());
						questionBankDao.updateQuestionAnswer(questionAnswer);
					}
				}
			}
			
			results.setStatus("0");
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
		
	}

	
	

}
