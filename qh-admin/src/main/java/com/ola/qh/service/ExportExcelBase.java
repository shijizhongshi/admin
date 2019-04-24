package com.ola.qh.service;

import java.io.OutputStream;
import java.util.List;
 
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
 
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ola.qh.util.ExportExcelEntity;


public class ExportExcelBase {
	// protected final Logger logger = Logger.getLogger(getClass());
	 
		protected HSSFWorkbook wb = new HSSFWorkbook();;
	 
		protected HSSFCellStyle styleTitle = null;
	 
		protected HSSFCellStyle styleTitle2 = null;
	 
		protected HSSFCellStyle cellStyle = null;
	 
		private List<ExportExcelEntity> sheets;
	 
		HSSFFont font = null;
	 
		public void setStyleTitle(HSSFCellStyle styleTitle) {
			this.styleTitle = styleTitle;
		}
	 
		// ----------------一级标题格样式----------------------------------
		public HSSFCellStyle getStyleTitle() {
	 
			HSSFCellStyle styleTitle = wb.createCellStyle(); // 标题样式
			styleTitle.setAlignment(HorizontalAlignment.CENTER);
			styleTitle.setVerticalAlignment(VerticalAlignment.CENTER);
			Font ztFont = wb.createFont();
			ztFont.setItalic(false); // 设置字体为斜体字
			ztFont.setColor(Font.COLOR_NORMAL); // 设置字体颜色
			ztFont.setFontHeightInPoints((short) 20); // 将字体大小设置为18px
			ztFont.setFontName("宋体"); // 将“宋体”字体应用到当前单元格上
			ztFont.setBold(true); // 加粗
			styleTitle.setFont(ztFont);
	 
			return styleTitle;
		}
	 
		public void setStyleTitle2(HSSFCellStyle styleTitle2) {
			this.styleTitle2 = styleTitle2;
		}
	 
		// ----------------二级标题格样式----------------------------------
		public HSSFCellStyle getStyleTitle2() {
	 
			HSSFCellStyle styleTitle2 = wb.createCellStyle(); // 表格样式
			styleTitle2.setAlignment(HorizontalAlignment.CENTER);
			styleTitle2.setVerticalAlignment(VerticalAlignment.CENTER);
			Font ztFont2 = wb.createFont();
			ztFont2.setItalic(false); // 设置字体为斜体字
			ztFont2.setColor(Font.COLOR_NORMAL); // 设置字体颜色
			ztFont2.setFontHeightInPoints((short) 15); // 将字体大小设置为12px
			ztFont2.setFontName("宋体"); // 字体应用到当前单元格上
			ztFont2.setBold(true); // 加粗
			styleTitle2.setFont(ztFont2);
	 
			return styleTitle2;
		}
	 
		public void SetCellStyle(HSSFCellStyle cellStyle) {
			this.cellStyle = cellStyle;
		}
	 
		// ----------------单元格样式----------------------------------
		public HSSFCellStyle getCellStyle() {
	 
			HSSFCellStyle cellStyle = wb.createCellStyle(); // 表格样式
			cellStyle.setAlignment(HorizontalAlignment.CENTER);
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			//cellStyle.setBorderBottom(BorderStyle.THIN); // 下边框若需要打开即可
			//cellStyle.setBorderLeft(BorderStyle.THIN);// 左边框
			//cellStyle.setBorderTop(BorderStyle.THIN);// 上边框
			//cellStyle.setBorderRight(BorderStyle.THIN);// 右边框
			Font cellFont = wb.createFont();
			cellFont.setItalic(false); // 设置字体为斜体字
			cellFont.setColor(Font.COLOR_NORMAL); // 设置字体颜色
			cellFont.setFontHeightInPoints((short) 14); // 将字体大小设置为12px
			cellFont.setFontName("宋体"); // 字体应用到当前单元格上
			// cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cellStyle.setFont(cellFont);
	 
			return cellStyle;
		}
	 
		protected void writeExcel(OutputStream os) {
			try {
				// 创建Excel的 Workbook,对应到一个excel文档
				wb = new HSSFWorkbook();
	 
				for (ExportExcelEntity d : this.sheets) {
					writeExcelSheet(d);
				}
				wb.write(os);
			} catch (Exception e) {
				// e.printStackTrace();
	 
				try {
					if (os != null)
						os.close();
				} catch (Exception oe) {
	 
				}
			}
		}
	 
		protected void writeExcel(OutputStream os, boolean isSelf) {
			try {
				// 创建Excel的 Workbook,对应到一个excel文档
				wb = new HSSFWorkbook();
	 
				for (ExportExcelEntity d : this.sheets) {
					if (isSelf) {
						writeExcelSheetSelf(d);
					} else {
						writeExcelSheet(d);
					}
				}
				wb.write(os);
			} catch (Exception e) {
				// e.printStackTrace();
	 
				try {
					if (os != null)
						os.close();
				} catch (Exception oe) {
	 
				}
			}
		}
	 
		protected void writeExcelSheetSelf(ExportExcelEntity expoEntity) {
	 
		}
	 
		/**
		 * 创建excel Sheet
		 * 
		 * @param expoEntity
		 */
		protected void writeExcelSheet(ExportExcelEntity expoEntity) {
	 
			// 创建Excel的工作sheet,对应到一个excel文档的tab
			HSSFSheet sheet = wb.createSheet(expoEntity.getSheetName());
			/*---------------------------------------
			 * 创建sheet的title
			 *--------------------------------------*/
			// 总列数 =
			int colsCount = expoEntity.getSheetFieldsName().size();
			// 创建Excel的sheet的一行
			HSSFRow row = sheet.createRow(0);
			row.setHeight((short) expoEntity.getSheetTitleHeight());// 设定行的高度
			// 创建一个Excel的单元格
			HSSFCell cell_title = row.createCell(0);
	 
			// 合并单元格(startRow，endRow，startColumn，endColumn)
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colsCount - 1));
	 
			// 给Excel的单元格设置样式和赋值
			cell_title.setCellStyle(this.getStyleTitle());
			cell_title.setCellValue(expoEntity.getSheetTitle());
	 
			/*---------------------------------------
			 * 创建sheet的列名
			 *--------------------------------------*/
			row = sheet.createRow(1);
			HSSFCellStyle headerStyle = this.getStyleTitle2();
			headerStyle.setWrapText(true);
			for (int i = 0; i < colsCount; i++) {
				HSSFCell cell_header = row.createCell(i);
	 
				String h = expoEntity.getSheetFieldsName().get(i);
	 
				cell_header.setCellValue(h);
	 
				// 设置自定义列宽
				if (expoEntity.getSheetColWidth() != null) {
					sheet.setColumnWidth(i, expoEntity.getSheetColWidth().get(i));
				}
	 
				cell_header.setCellStyle(headerStyle);
			}
	 
			/*---------------------------------------
			 * 创建sheet的数据
			 *--------------------------------------*/
			JSONArray datas = expoEntity.getSheetData();
			HSSFCellStyle cellStyle = this.getCellStyle();
			for (int i = 0; i < datas.size(); i++) {
				JSONObject rowData = datas.getJSONObject(i);
				row = sheet.createRow(2 + i);
				JSONArray cellDatas = rowData.getJSONArray("data");
				Object[] _cellDatas = cellDatas.toArray();
				for (int j = 0; j < _cellDatas.length; j++) {
					HSSFCell cell_Data = row.createCell(j);
					// 给Excel的单元格设置样式和赋值
					cell_Data.setCellStyle(cellStyle);
					Object cellData = _cellDatas[j];
					if (cellData == null)
						continue;
					if ("class java.lang.String".equalsIgnoreCase(cellData.getClass().toString())) {
						cell_Data.setCellValue((String) cellData);
						// cell_Data.getCellStyle().setAlignment(HSSFCellStyle.ALIGN_RIGHT);
					}
					if ("class java.lang.Double".equalsIgnoreCase(cellData.getClass().toString())) {
						// cell_Data.getCellStyle().setAlignment(HSSFCellStyle.ALIGN_RIGHT);
						cell_Data.setCellValue((Double) cellData);
					}
					if ("class java.lang.Integer".equalsIgnoreCase(cellData.getClass().toString())) {
						// cell_Data.getCellStyle().setAlignment(HSSFCellStyle.ALIGN_RIGHT);
						cell_Data.setCellValue((Integer) cellData);
					}
				}
	 
			}
		}
	 
		public List<ExportExcelEntity> getSheets() {
			return sheets;
		}
	 
		public void setSheets(List<ExportExcelEntity> sheets) {
			this.sheets = sheets;
		}

	
}
