package com.ola.qh.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ola.qh.util.Results;

@RestController
@RequestMapping("/api/questionbank")
public class QuestionBankController {
	/**
	 * 题库的上传
	 * <p>
	 * Title: improtExcel
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/improtExcel", method = RequestMethod.POST)
	public Results<String> improtExcel(@RequestParam(value = "file") MultipartFile file) {
		Results<String> result = new Results<String>();
		try {
			Workbook wb = WorkbookFactory.create(file.getInputStream());
			String[][] table = null;
			for (int z = 0; z < wb.getNumberOfSheets(); z++) {
				Sheet sheet = wb.getSheetAt(z);
				int rowNumber = sheet.getPhysicalNumberOfRows() - 1;
				Iterator<Row> rowIterator = sheet.rowIterator();
				Row titleRow = rowIterator.next();
				int columnNumber = titleRow.getLastCellNum();
				table = new String[rowNumber][columnNumber];
				for (int i = 0; i < rowNumber && rowIterator.hasNext(); i++) {
					Row row = rowIterator.next();
					for (int j = 0; j < columnNumber; j++) {
						Cell cell = row.getCell(j);
						if (cell != null) {
							cell.setCellType(CellType.STRING);
							table[i][j] = cell.getStringCellValue().trim();
						} else {
							table[i][j] = "";
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
