package com.ola.qh.entity;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
@RestController
public class ExportExcel {
	
	/////////////types:1表示直播验证数据2表示H5题库3表示学习记录4表示直播访问记录
	/////////////List<CourseLiveCheck> 表示直播验证数据的集合
	/////////////List<QuestionBank> 表示H5题库的集合
	/////////////List<PlayLog> 表示学习记录的集合
	/////////////List<UserEnterLeaveActions> 表示直播访问记录的集合
	
///////////////////////////////表格名称//////////////////////////
	public  String fileName(int types){
		
		String fileName =new String();
		if(types==1){
			fileName = "直播验证数据";
		}else if(types==2){
			fileName = "H5题库";
		}else if(types==3){
			fileName = "学习记录";
		}else if(types==4){
			fileName = "直播访问记录";
		}else if(types==5){
			fileName = "直播购买记录";
		}
		return fileName;
	};
///////////////////////////////表名称///////////////////////////////
	public  String sheetName(int types){
		
		String sheetName =new String();
		if(types==1){
			sheetName = "直播验证数据";
		}else if(types==2){
			sheetName = "H5题库";
		}else if(types==3){
			sheetName = "学习记录";
		}else if(types==4){
			sheetName = "直播访问记录";
		}else if(types==5){
			sheetName = "直播购买记录";
		}
		return sheetName;
	};
///////////////////////////////表的标题名称/////////////////////
	public  String sheetTitle(int types){
		
		String sheetTitle =new String();
		if(types==1){
			sheetTitle = "直播验证数据";
		}else if(types==2){
			sheetTitle = "H5题库";
		}else if(types==3){
			sheetTitle = "学习记录";
		}else if(types==4){
			sheetTitle = "直播访问记录";
		}else if(types==5){
			sheetTitle = "直播购买记录";
		}
		return sheetTitle;
	};
///////////////////////////////构筑列名称/////////////////////
	public  ArrayList<String> sheetFieldsName(int types){
		
		ArrayList<String> sheetFieldsName = new ArrayList<String>();
		if(types==1){
			
			sheetFieldsName.add("手机号");
			sheetFieldsName.add("用户属性");
			sheetFieldsName.add("校验码");
			sheetFieldsName.add("专业名称");
			sheetFieldsName.add("房间号");
			sheetFieldsName.add("最后一次操作的时间");
		}else if(types==2){
			sheetFieldsName.add("姓名");
			sheetFieldsName.add("手机号");
			sheetFieldsName.add("用户属性");
			sheetFieldsName.add("专业分类");
			sheetFieldsName.add("答题数");
			sheetFieldsName.add("准确率");
			sheetFieldsName.add("视频数");
			sheetFieldsName.add("最后一次操作时间");
			
		}else if(types==3){
			sheetFieldsName.add("用户名");
			sheetFieldsName.add("视频名称");
			sheetFieldsName.add("专业");
			sheetFieldsName.add("观看时长");
			sheetFieldsName.add("视频总长");
			
		}else if(types==4){
			sheetFieldsName.add("用户昵称");
			sheetFieldsName.add("用户地域");
			sheetFieldsName.add("进入时间");
			sheetFieldsName.add("离开时间");
			sheetFieldsName.add("直播观看时长");
			sheetFieldsName.add("终端类型");
			
		}else if(types==5){
			sheetFieldsName.add("真实姓名");
			sheetFieldsName.add("密码");
		}
		
		return sheetFieldsName;
	};
///////////////////////////////构建数据/////////////////////
	public  JSONArray jaDatas(int types,InputExportExcel inputExportExcel){
		
		JSONArray jaDatas = new JSONArray();
		if(types==1){
			
			for (CourseLiveCheck courseLiveCheck : inputExportExcel.getListLiveCheck()) {
				
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
		}else if(types==2){
			
			for (QuestionBank questionBank : inputExportExcel.getQuestionBank()) {
				
				NumberFormat nf = NumberFormat.getPercentInstance();
				nf.setMinimumFractionDigits(2); //保留到小数点后几位
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				ArrayList<Object> arr = new ArrayList<Object>();
				JSONObject jo = new JSONObject();
				arr.add(questionBank.getRealname());
				arr.add(questionBank.getMobile());
				if(questionBank.getStatus()==0){
					arr.add("注册用户");
				}else if(questionBank.getStatus()==1){
					arr.add("游客用户");
				}
				arr.add(questionBank.getCourseTypeSubclassName());
				arr.add(questionBank.getBanktotal()-questionBank.getNobank());
				if(questionBank.getBanktotal()-questionBank.getNobank()!=0){
					arr.add(nf.format(questionBank.getBanktrue()/(questionBank.getBanktotal()-questionBank.getNobank())));
				}else{
					arr.add("用户未开始做题");
				}
				arr.add(4);
				if(questionBank.getUpdatetime()!=null){
					arr.add(sf.format(questionBank.getUpdatetime()));
				}else{
					arr.add(sf.format(questionBank.getAddtime()));
				}
				jo.put("data", arr);
				jaDatas.add(jo);
			}
		}else if(types==3){
			
			for (PlayLog playLog : inputExportExcel.getPlayLog()) {
				
				ArrayList<Object> arr = new ArrayList<Object>();
				JSONObject jo = new JSONObject();
				arr.add(playLog.getUserName());
				arr.add(playLog.getSectionName());
				arr.add(playLog.getCourseTypeSubclassName());
				arr.add(playLog.getPlay_duration()/60+"分钟");
				arr.add(playLog.getVideo_duration()/60+"分钟");
				jo.put("data", arr);
				jaDatas.add(jo);
			}
		}else if(types==4){
			
			for (UserEnterLeaveActions userEnterLeaveActions : inputExportExcel.getUserEnterLeaveActions()) {
				
				ArrayList<Object> arr = new ArrayList<Object>();
				JSONObject jo = new JSONObject();
				arr.add(userEnterLeaveActions.getViewerName());
				arr.add(userEnterLeaveActions.getCity());
				arr.add(userEnterLeaveActions.getEnterTime());
				arr.add(userEnterLeaveActions.getLeaveTime());
				if(userEnterLeaveActions.getWatchTime()!=null){
					arr.add(userEnterLeaveActions.getWatchTime()/60+"分钟");
				}else{
					arr.add("未观看直播");
				}
				if(userEnterLeaveActions.getTerminal()==0){
					arr.add("电脑端");
				}else if(userEnterLeaveActions.getTerminal()==1){
					arr.add("移动端");
				}else if(userEnterLeaveActions.getTerminal()==null){
					arr.add("无终端类型");
				}
				jo.put("data", arr);
				jaDatas.add(jo);
			}
		}else if(types==5){
			for (LivePay LivePay : inputExportExcel.getLivePay()) {
				
				ArrayList<Object> arr = new ArrayList<Object>();
				JSONObject jo = new JSONObject();
				arr.add(LivePay.getRealname());
				arr.add("zhongshi");
				
				jo.put("data", arr);
				jaDatas.add(jo);
			}
			
		}
		
		return jaDatas;
	};
///////////////////////////////构建列宽/////////////////////
	public  ArrayList<Integer> sheetColWidth(int types){
		
		ArrayList<Integer> sheetColWidth = new ArrayList<Integer>();
		if(types==1){
			
			sheetColWidth.add(0, 5000);
			sheetColWidth.add(1, 5000);
			sheetColWidth.add(2, 5000);
			sheetColWidth.add(3, 10000);
			sheetColWidth.add(4, 14000);
			sheetColWidth.add(5, 9000);
		}else if(types==2){
			sheetColWidth.add(0, 5000);
			sheetColWidth.add(1, 5000);
			sheetColWidth.add(2, 5000);
			sheetColWidth.add(3, 7000);
			sheetColWidth.add(4, 5000);
			sheetColWidth.add(5, 5000);
			sheetColWidth.add(6, 5000);
			sheetColWidth.add(7, 9000);
		}else if(types==3){
			sheetColWidth.add(0, 10000);
			sheetColWidth.add(1, 15000);
			sheetColWidth.add(2, 5000);
			sheetColWidth.add(3, 5000);
			sheetColWidth.add(4, 5000);
			
		}else if(types==4){
			sheetColWidth.add(0, 7000);
			sheetColWidth.add(1, 5000);
			sheetColWidth.add(2, 10000);
			sheetColWidth.add(3, 10000);
			sheetColWidth.add(4, 5000);
			sheetColWidth.add(5, 5000);
		}else if(types==5){
			sheetColWidth.add(0, 5000);
			sheetColWidth.add(1, 5000);
			
		}
		return sheetColWidth;
	};
	
}
