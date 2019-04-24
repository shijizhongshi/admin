package com.ola.qh.service.imp;

import com.ola.qh.entity.CourseLiveCheck;
import com.ola.qh.service.ExportExcelBase;
import com.ola.qh.util.ExportExcelEntity;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class PaperMgrExcel extends ExportExcelBase{
	/**
	 * 
	 * @Title: exportExcel @Description: 导出Excel @param param @return 参数 @return
	 * void @throws
	 */
	public void exportExcel(List<CourseLiveCheck> courseLiveList,HttpServletRequest request, HttpServletResponse response, JSONArray jarr) throws Exception {
		OutputStream os = null;
		try {
 
			// 构建sheet名
			String sheetName = "直播验证数据";
 
			// 构建标题
			String sheetTitle = "直播验证数据";
 
			// 构建列名
			ArrayList<String> sheetFieldsName = new ArrayList<String>();
			sheetFieldsName.add("手机号");
			sheetFieldsName.add("用户属性");
			sheetFieldsName.add("校验码");
			sheetFieldsName.add("专业名称");
			sheetFieldsName.add("房间号");
			sheetFieldsName.add("最后一次操作的时间");
 
			// 构建数据
			JSONArray jaDatas = new JSONArray();
			for (CourseLiveCheck courseLiveCheck : courseLiveList) {
				
				ArrayList<Object> arr = new ArrayList<Object>();
				JSONObject jo = new JSONObject();
				arr.add(courseLiveCheck.getMobile());
				if(courseLiveCheck.getIsRegister()==1){
					arr.add("注册用户");
				}else if(courseLiveCheck.getIsRegister()==0){
					arr.add("游客用户");
				}
				arr.add(courseLiveCheck.getToken());
				arr.add(courseLiveCheck.getCourseTypeSubclassName());
				arr.add(courseLiveCheck.getRoomId());
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				if(courseLiveCheck.getUpdatetime()!=null){
					arr.add(sf.format(courseLiveCheck.getUpdatetime()));
				}else{
					arr.add(sf.format(courseLiveCheck.getAddtime()));
				}
				jo.put("data", arr);
				jaDatas.add(jo);
			}
			
				
			
 
			// 设置列宽
			ArrayList<Integer> sheetColWidth = new ArrayList<Integer>();
			sheetColWidth.add(0, 2000);
			sheetColWidth.add(1, 3000);
			sheetColWidth.add(2, 4000);
			sheetColWidth.add(3, 2000);
			sheetColWidth.add(4, 3000);
			sheetColWidth.add(5, 4000);
 
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
