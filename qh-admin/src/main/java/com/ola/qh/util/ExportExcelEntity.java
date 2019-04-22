package com.ola.qh.util;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;

public class ExportExcelEntity {
	// sheet名称
		private String sheetName;
	 
		// sheet的title
		private String sheetTitle;
	 
		// sheet的列名
		private ArrayList<String> sheetFieldsName;
	 
		/**
		 * sheet的数据<br/>
		 * 
		 */
		private JSONArray sheetData;
	 
		// 设置列宽
		private ArrayList<Integer> sheetColWidth;
	 
		// title的高度
		private int sheetTitleHeight = 500;
	 
		public ExportExcelEntity(String sheetName, String sheetTitle, ArrayList<String> sheetFieldsName,
				JSONArray sheetData) {
			super();
			this.sheetName = sheetName;
			this.sheetTitle = sheetTitle;
			this.sheetFieldsName = sheetFieldsName;
			this.sheetData = sheetData;
		}
	 
		public ExportExcelEntity(String sheetName, String sheetTitle, ArrayList<String> sheetFieldsName,
				JSONArray sheetData, ArrayList<Integer> sheetColWidth, int sheetTitleHeight) {
			super();
			this.sheetName = sheetName;
			this.sheetTitle = sheetTitle;
			this.sheetFieldsName = sheetFieldsName;
			this.sheetData = sheetData;
			this.sheetColWidth = sheetColWidth;
			this.sheetTitleHeight = sheetTitleHeight;
		}
	 
		public String getSheetName() {
			return sheetName;
		}
	 
		public void setSheetName(String sheetName) {
			this.sheetName = sheetName;
		}
	 
		public String getSheetTitle() {
			return sheetTitle;
		}
	 
		public void setSheetTitle(String sheetTitle) {
			this.sheetTitle = sheetTitle;
		}
	 
		public ArrayList<String> getSheetFieldsName() {
			return sheetFieldsName;
		}
	 
		public void setSheetFieldsName(ArrayList<String> sheetFieldsName) {
			this.sheetFieldsName = sheetFieldsName;
		}
	 
		public JSONArray getSheetData() {
			return sheetData;
		}
	 
		public void setSheetData(JSONArray sheetData) {
			this.sheetData = sheetData;
		}
	 
		public ArrayList<Integer> getSheetColWidth() {
			return sheetColWidth;
		}
	 
		public void setSheetColWidth(ArrayList<Integer> sheetColWidth) {
			this.sheetColWidth = sheetColWidth;
		}
	 
		public int getSheetTitleHeight() {
			return sheetTitleHeight;
		}
	 
		public void setSheetTitleHeight(int sheetTitleHeight) {
			this.sheetTitleHeight = sheetTitleHeight;
		}

	
}
