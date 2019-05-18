package com.ola.qh.service.imp;

import com.ola.qh.entity.ExportExcel;
import com.ola.qh.entity.InputExportExcel;
import com.ola.qh.service.ExportExcelBase;
import com.ola.qh.util.ExportExcelEntity;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONArray;


public class PaperMgrExcel extends ExportExcelBase{
	/**
	 * 
	 * @Title: exportExcel @Description: 导出Excel @param param @return 参数 @return
	 * void @throws
	 */
	public void exportExcel(InputExportExcel inputExportExcel,int types,
			HttpServletRequest request, HttpServletResponse response, JSONArray jarr) throws Exception {
		OutputStream os = null;
		try {
 
			ExportExcel export=new ExportExcel();
			
			// 构建sheet表名
			String sheetName = export.sheetName(types);
 
			// 构建标题
			String sheetTitle = export.sheetTitle(types);
 
			// 构建列名
			ArrayList<String> sheetFieldsName = export.sheetFieldsName(types);
			
			// 构建数据
			JSONArray jaDatas = export.jaDatas(types, inputExportExcel);
			
			// 设置列宽
			ArrayList<Integer> sheetColWidth = export.sheetColWidth(types);
			
 
			// title的高度
			int sheetTitleHeight = 500;
 
			// 构建表单内容实体
			ExportExcelEntity expoEntity = new ExportExcelEntity(sheetName, sheetTitle, sheetFieldsName, jaDatas,
					sheetColWidth, sheetTitleHeight);
 
			List<ExportExcelEntity> sheets = new ArrayList<ExportExcelEntity>();
			sheets.add(expoEntity);
			setSheets(sheets);
			os = response.getOutputStream();
 
			// 如果要自定义写入表单数据调用这个方法并复写父类 writeExcelSheetSelf( ExportExcelEntity
			// expoEntity)方法
			// writeExcel(os,true);
 
			// 直接调用父类模板方法
			writeExcel(os);
 
		} catch (Exception e) {
			throw new Exception("Export Excel failed, beacause" + e.getMessage());
		}
	}


}
