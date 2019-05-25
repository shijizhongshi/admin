package com.ola.qh.service.imp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.ola.qh.dao.CourseLineWhiteDao;
import com.ola.qh.dao.CourseNofreeDao;
import com.ola.qh.dao.CourseSubclassDao;
import com.ola.qh.dao.QuestionBankDao;
import com.ola.qh.dao.UserDao;
import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseLineShow;
import com.ola.qh.entity.CourseLineWhite;
import com.ola.qh.entity.CourseLiveCheck;
import com.ola.qh.entity.LiveAccess;
import com.ola.qh.entity.PlayLog;
import com.ola.qh.entity.QuestionAnswer;
import com.ola.qh.entity.QuestionBank;
import com.ola.qh.entity.QuestionBankAsk;
import com.ola.qh.entity.QuestionUnit;
import com.ola.qh.entity.UserEnterLeaveActions;
import com.ola.qh.entity.VideoPlaybackRecord;
import com.ola.qh.service.IQuestionBankService;
import com.ola.qh.service.IStoreService;
import com.ola.qh.util.Json;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Patterns;
import com.ola.qh.util.Results;
import com.ola.qh.util.Thqs;
import com.ola.qh.weixin.handler.Requests;

@Service
public class QuestionBankService implements IQuestionBankService {

	@Autowired
	private QuestionBankDao questionBankDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CourseLineWhiteDao courseLineWhiteDao;
	@Autowired
	private CourseSubclassDao courseSubclassDao;
	@Autowired
	private IStoreService storeService;
	@Autowired
	private CourseNofreeDao courseNofreeDao;
	@Autowired
	private InportBankTemplateService inportBankTemplateService;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<String> importExcel(MultipartFile file, String subId, int status) throws Exception {
		Results<String> results = new Results<String>();
		Workbook wb = WorkbookFactory.create(file.getInputStream());

		Sheet sheet = wb.getSheetAt(0);
		// 判断用07还是03的方法获取图片
		String filename = file.getOriginalFilename();
		List<String> urlss = new ArrayList<String>();

		if (status == 0 || status == 1) {
			Map<String, PictureData> maplist = new HashMap<>();
			if (filename.contains(".xls")) {
				maplist = getPictures1((HSSFSheet) sheet);
			} else if (filename.contains(".xlsx")) {
				maplist = getPictures2((XSSFSheet) sheet);
			}
			try {
				urlss = printImg(maplist);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		int rowNumber = sheet.getPhysicalNumberOfRows() - 1;
		Iterator<Row> rowIterator = sheet.rowIterator();
		Row titleRow = rowIterator.next();
		titleRow.getLastCellNum();
		/* String[][] table = new String[rowNumber][columnNumber]; */
		/*
		 * String bankId = null; int n = 0; for (int i = 0; i < rowNumber &&
		 * rowIterator.hasNext(); i++) { Row row = rowIterator.next(); ////// 保存题库的信息 if
		 * ("共用题干".equals(checkNull(0, row)) || "共用选项".equals(checkNull(0, row))) { ///
		 * 保存他下边的集合 QuestionBank qb = new QuestionBank(); qb.setAddtime(new Date());
		 * bankId = KeyGen.uuid(); qb.setId(bankId); // qb.setNumberNo(n+1);
		 * qb.setTitle(checkNull(1, row)); qb.setTypes(checkNull(0, row));
		 * qb.setSubId(subId); if (urlss != null) { for (String imageurl : urlss) { if
		 * (imageurl.indexOf("/" + (i + 1) + "-2") > 0) { qb.setTitleimg(imageurl);
		 * urlss.remove(imageurl); } } } questionBankDao.insertQuestionBank(qb); // n++;
		 * } if ("单选题".equals(checkNull(0, row)) || "多选题".equals(checkNull(0, row))) {
		 * QuestionBank qb = new QuestionBank(); qb.setAddtime(new Date()); String
		 * unitId = KeyGen.uuid(); qb.setId(unitId); qb.setTitle(checkNull(1, row));
		 * qb.setTypes(checkNull(0, row)); qb.setSubId(subId);
		 * qb.setCorrect(checkNull(3, row)); QuestionAnswer qa = new QuestionAnswer();
		 * qa.setBankUnitId(unitId); qa.setAddtime(new Date()); if (urlss != null) { for
		 * (String imageurl : urlss) { if (imageurl.indexOf("/" + (i + 1) + "-2") >= 0)
		 * { qb.setTitleimg(imageurl); urlss.remove(imageurl); break; } } } if
		 * (checkNull(4, row).trim() != null) { qa.setAnswers(checkNull(4, row));
		 * qa.setId(KeyGen.uuid()); qa.setOptions("A"); if (checkNull(3,
		 * row).contains("A")) { qa.setCorrect(true); } else { qa.setCorrect(false); }
		 * qa.setOrders(0); qa.setTitleimg(null); for (String imageurl : urlss) {
		 * /////// A对应的图片 if (imageurl.indexOf("/" + (i + 1) + "-5") >= 0) {
		 * qa.setTitleimg(imageurl); urlss.remove(imageurl); break; } }
		 * questionBankDao.insertQuestionAnswer(qa); } if (checkNull(6, row) != null) {
		 * qa.setAnswers(checkNull(6, row)); qa.setId(KeyGen.uuid());
		 * qa.setOptions("B"); if (checkNull(3, row).contains("B")) {
		 * qa.setCorrect(true); } else { qa.setCorrect(false); } qa.setOrders(1);
		 * qa.setTitleimg(null); for (String imageurl : urlss) { /////// B对应的图片 if
		 * (imageurl.indexOf("/" + (i + 1) + "-7") >= 0) { qa.setTitleimg(imageurl);
		 * urlss.remove(imageurl); break; } } questionBankDao.insertQuestionAnswer(qa);
		 * } if (checkNull(8, row) != null) { qa.setAnswers(checkNull(8, row));
		 * qa.setId(KeyGen.uuid()); qa.setOptions("C"); if (checkNull(3,
		 * row).contains("C")) { qa.setCorrect(true); } else { qa.setCorrect(false); }
		 * qa.setOrders(2); qa.setTitleimg(null); for (String imageurl : urlss) {
		 * /////// C对应的图片 if (imageurl.indexOf("/" + (i + 1) + "-9") >= 0) {
		 * qa.setTitleimg(imageurl); urlss.remove(imageurl); break; } }
		 * questionBankDao.insertQuestionAnswer(qa); } if (checkNull(10, row) != null) {
		 * qa.setAnswers(checkNull(10, row)); qa.setId(KeyGen.uuid());
		 * qa.setOptions("D"); if (checkNull(3, row).contains("D")) {
		 * qa.setCorrect(true); } else { qa.setCorrect(false); } qa.setOrders(3);
		 * qa.setTitleimg(null); for (String imageurl : urlss) { /////// D对应的图片 if
		 * (imageurl.indexOf("/" + (i + 1) + "-11") >= 0) { qa.setTitleimg(imageurl);
		 * urlss.remove(imageurl); break; } } questionBankDao.insertQuestionAnswer(qa);
		 * } if (checkNull(12, row) != null) { qa.setAnswers(checkNull(12, row));
		 * qa.setId(KeyGen.uuid()); qa.setOptions("E"); if (checkNull(3,
		 * row).contains("E")) { qa.setCorrect(true); } else { qa.setCorrect(false); }
		 * qa.setOrders(4); qa.setTitleimg(null); for (String imageurl : urlss) {
		 * /////// E对应的图片 if (imageurl.indexOf("/" + (i + 1) + "-13") >= 0) {
		 * qa.setTitleimg(imageurl); urlss.remove(imageurl); break; } }
		 * questionBankDao.insertQuestionAnswer(qa); }
		 * 
		 * if (bankId != null) { ///// 共同题干的问题 qb.setBankId(bankId);
		 * qb.setAnalysis(checkNull(14, row)); qb.setNumberNo(n + 1);
		 * 
		 * questionBankDao.insertQuestionUnit(qb); n++; } else { /////// 单纯的单选或者多选的问题
		 * qb.setAnalysis(checkNull(14, row)); qb.setNumberNo(n + 1);
		 * questionBankDao.insertQuestionBank(qb); n++; }
		 * 
		 * }
		 */

		if (status == 0) {
			return inportBankTemplateService.BankTemplateAnswerImg(rowNumber, rowIterator, subId, urlss);
		} else if (status == 1) {
			return inportBankTemplateService.BankTemplateTitleImg(rowNumber, rowIterator, subId, urlss);
		} else if (status == 2) {
			return inportBankTemplateService.BankTemplateNoImg(rowNumber, rowIterator, subId);
		}
		results.setStatus("1");
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

	/**
	 * 获取图片和位置 (xls)
	 * 
	 * @param sheet
	 * @return
	 * @throws IOException
	 */
	public static Map<String, PictureData> getPictures1(HSSFSheet sheet) throws IOException {
		Map<String, PictureData> map = new HashMap<String, PictureData>();
		List<HSSFShape> list = sheet.getDrawingPatriarch().getChildren();
		for (HSSFShape shape : list) {
			if (shape instanceof HSSFPicture) {
				HSSFPicture picture = (HSSFPicture) shape;
				HSSFClientAnchor cAnchor = (HSSFClientAnchor) picture.getAnchor();
				PictureData pdata = picture.getPictureData();
				String key = cAnchor.getRow1() + "-" + cAnchor.getCol1(); // 行号-列号
				map.put(key, pdata);
			}
		}
		return map;
	}

	/**
	 * 获取图片和位置 (xlsx)
	 * 
	 * @param sheet
	 * @return
	 * @throws IOException
	 */
	public static Map<String, PictureData> getPictures2(XSSFSheet sheet) throws IOException {
		Map<String, PictureData> map = new HashMap<String, PictureData>();
		List<POIXMLDocumentPart> list = sheet.getRelations();
		for (POIXMLDocumentPart part : list) {
			if (part instanceof XSSFDrawing) {
				XSSFDrawing drawing = (XSSFDrawing) part;
				List<XSSFShape> shapes = drawing.getShapes();
				for (XSSFShape shape : shapes) {
					XSSFPicture picture = (XSSFPicture) shape;
					XSSFClientAnchor anchor = picture.getPreferredSize();
					CTMarker marker = anchor.getFrom();
					String key = marker.getRow() + "-" + marker.getCol();
					map.put(key, picture.getPictureData());
				}
			}
		}
		return map;
	}

	// 图片写出
	public List<String> printImg(Map<String, PictureData> sheetList) throws Exception {

		// for (Map<String, PictureData> map : sheetList) {
		Object key[] = sheetList.keySet().toArray();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < sheetList.size(); i++) {
			// 获取图片流
			PictureData pic = sheetList.get(key[i]);
			// 获取图片索引
			String picName = key[i].toString();
			// 获取图片格式
			String ext = pic.suggestFileExtension();

			byte[] data = pic.getData();

			// 图片保存路径
			String tupianurl = storeService.storeUrl(picName + "." + ext, data);
			list.add(tupianurl);
//	                FileOutputStream out = new FileOutputStream("D:\\img\\pic" + );  
//	                out.write(data);  
//	                out.close();  

		}
		return list;
		// }

	}

	@Transactional
	@Override
	public Results<List<QuestionBank>> selectQuestionBank(String subId, int pageNo, int pageSize) {

		Results<List<QuestionBank>> results = new Results<List<QuestionBank>>();
		try {

			List<QuestionBank> listbank = questionBankDao.selectQuestionBank(subId, pageNo, pageSize);

			int count = questionBankDao.countQuestionBank(subId);

			for (QuestionBank questionBank : listbank) {

				List<QuestionAnswer> listanswer = questionBankDao.selectQuestionAnswer(questionBank.getId());

				questionBank.setAnswer(listanswer);

				List<QuestionUnit> listunit = questionBankDao.selectQuestionUnit(questionBank.getId());

				for (QuestionUnit questionUnit : listunit) {

					List<QuestionAnswer> listanswerunit = questionBankDao.selectQuestionAnswer(questionUnit.getId());

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

		Results<String> results = new Results<String>();

		try {
			questionBankDao.deleteQuestionBank(id);
			questionBankDao.deleteQuestionAnswer(id);
			List<QuestionUnit> listunit = questionBankDao.selectQuestionUnit(id);

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

		Results<String> results = new Results<String>();
		try {

			questionBank.setUpdatetime(new Date());
			questionBankDao.updateQuestionBank(questionBank);

			List<QuestionAnswer> listanswer = questionBank.getAnswer();
			List<QuestionUnit> unit = questionBank.getUnit();

			if (listanswer != null && !"".equals(listanswer)) {

				for (QuestionAnswer questionAnswer : listanswer) {
					questionAnswer.setUpdatetime(new Date());
					questionBankDao.updateQuestionAnswer(questionAnswer);
				}
			}

			if (unit != null && !"".equals(unit)) {

				for (QuestionUnit questionUnit : unit) {

					List<QuestionAnswer> unitAnswer = questionUnit.getUnitAnswer();
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

	/**
	 * H5题库管理
	 */
	@Override
	public Results<List<QuestionBank>> selectQuestionBankList(String realname, String courseTypeSubclassName,
			String status, int page) {
		Results<List<QuestionBank>> results = new Results<>();

		Integer pageSize = Patterns.pageSize;
		Integer pageNo = (page - 1) * pageSize;
		// 查询集合 展示
		List<QuestionBank> list = questionBankDao.questionBankList(realname, courseTypeSubclassName, status, pageNo,
				pageSize);
		Integer count = questionBankDao.questionBankListCount(realname, courseTypeSubclassName, status);
		results.setStatus("0");
		results.setData(list);
		results.setCount(count);

		return results;
	}

	/**
	 * 直播验证数据
	 */
	@Override
	public Results<List<CourseLiveCheck>> selectLiveVerifyList(String fromdate, String todate, int pageNo, int pageSize,
			String mobile, String roomId, String courseTypeSubclassName) {
		Results<List<CourseLiveCheck>> results = new Results<List<CourseLiveCheck>>();

		// 条件查询
		List<CourseLiveCheck> list = questionBankDao.liveVerifyList(fromdate, todate, pageNo, pageSize, mobile, roomId,
				courseTypeSubclassName);
		for (CourseLiveCheck courseLiveCheck : list) {
			// 根据mobile查询user 有数据说明是注册用户 赋值展示
			Integer count = userDao.selectUserCount(courseLiveCheck.getMobile(), null, null);
			if (count == 0) {
				courseLiveCheck.setIsRegister(0);
			} else {
				courseLiveCheck.setIsRegister(1);
			}
		}
		// 查询数量
		Integer countInteger = questionBankDao.selectLiveVerifyCount(fromdate, todate, mobile, roomId,
				courseTypeSubclassName);

		results.setStatus("0");
		results.setCount(countInteger);
		results.setData(list);

		return results;
	}

	/**
	 * cc直播 获取观看视频接口 直播访问记录页面
	 */
	@Override
	public Results<List<UserEnterLeaveActions>> liveAccess(String notToEnter, String liveId, String pagenum,
			String pageindex) {
		Results<List<UserEnterLeaveActions>> results = new Results<List<UserEnterLeaveActions>>();
		// treemap
		TreeMap<String, String> treeMap = new TreeMap<>();
		treeMap.put("userid", Patterns.accountId);
		treeMap.put("liveid", liveId);
		treeMap.put("pagenum", pagenum);
		treeMap.put("pageindex", pageindex);

		String address = Thqs.getThqstreeMap(Patterns.liveToken, treeMap);

		try {
			Results<byte[]> testByte = Requests.testGet(Patterns.useraction, null, address);
			byte[] bytes = testByte.getData();
			String byteString = new String(bytes);
			// json转实体类
			LiveAccess liveAccess = Json.from(byteString, LiveAccess.class);
			List<UserEnterLeaveActions> list = liveAccess.getUserEnterLeaveActions();
			// 如果选定查询未进入直播间用户 返回的是白名单中的用户
			if ("1".equals(notToEnter)) {
				List<UserEnterLeaveActions> userList = new ArrayList<>();
				// 获取本直播id的白名单全部数据
				// 先根据live_id查询course_line_show表
				CourseLineShow courseLineShow = courseNofreeDao.singleLive(liveId);
				// 根据course_line_show表的ID查询白名单集合
				List<CourseLineWhite> whithList = courseLineWhiteDao.selectAllByLiveId(courseLineShow.getId());
				// 迭代器 匹配两个集合中的内容 相同就remove掉
				for (UserEnterLeaveActions userEnterLeaveActions : list) {
					for (Iterator<CourseLineWhite> iterator = whithList.iterator(); iterator.hasNext();) {
						if (userEnterLeaveActions.getViewerName().equals(iterator.next().getUsername())) {
							iterator.remove();
						}
					}
				}
				// 赋值返回
				UserEnterLeaveActions userEnterLeaveActions = null;
				for (int i = 0; i < whithList.size(); i++) {
					userEnterLeaveActions = new UserEnterLeaveActions();
					userEnterLeaveActions.setViewerName(whithList.get(i).getUsername());
					userList.add(userEnterLeaveActions);
				}
				results.setStatus("0");
				results.setData(userList);

				return results;
			}

			results.setStatus("0");
			results.setCount(liveAccess.getCount());
			results.setData(list);

			return results;

		} catch (IOException e) {
			e.printStackTrace();
		}

		results.setStatus("1");
		results.setMessage("错误！");
		return results;
	}

	/**
	 * cc点播 学习记录页面
	 */
	@Override
	public Results<List<PlayLog>> ccVideo(String videoId, String mobile, String date, String numPerPage, String page) {

		Results<List<PlayLog>> results = new Results<List<PlayLog>>();
		// 获取视频播放记录接口
		if (videoId != null && mobile == null) {
			// 必须为 treemap传参
			TreeMap<String, String> treeMap = new TreeMap<>();
			treeMap.put("userid", Patterns.accountId);
			treeMap.put("videoid", videoId);
			treeMap.put("date", date);
			treeMap.put("num_per_page", numPerPage);
			treeMap.put("page", page);

			// 拼接地址
			// token 是cc视频的API key
			String address = Thqs.getThqstreeMap(Patterns.token, treeMap);
			try {
				Results<byte[]> testByte = Requests.testGet(Patterns.videoV2, null, address);
				byte[] bytess = testByte.getData();
				String byteString = new String(bytess);

				// json字符串转换
				VideoPlaybackRecord videoPlaybackRecord = Json.from(byteString, VideoPlaybackRecord.class);
				List<PlayLog> list = videoPlaybackRecord.getPlay_logs().getPlay_log();
				// for循环内部测试完成
				for (PlayLog playLog : list) {
					// 根据用户id 查name
					String userName = userDao.selectNameById(playLog.getCustom_id());
					playLog.setUserName(userName);
					// 根据视频id 查视频名和所属专业
					CourseChapter courseChapter = courseSubclassDao.selectNameAndCTSN(playLog.getVideoid());
					if (courseChapter != null) {
						playLog.setCourseTypeSubclassName(courseChapter.getCourseTypeSubclassName());
						playLog.setSectionName(courseChapter.getSectionName());
					}
				}
				if (list.isEmpty() || list == null) {
					results.setStatus("1");
					results.setMessage("根据条件未查询到记录~");
					return results;
				}
				results.setStatus("0");
				results.setCount(videoPlaybackRecord.getPlay_logs().getTotal());
				results.setData(list);

				return results;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 获取用户自定义参数播放记录
		if (mobile != null && videoId == null) {
			// 根据手机号查询id
			String id = userDao.selectIdByMobile(mobile);
			if (id == null) {
				results.setStatus("1");
				results.setMessage("根据此手机号未查询到用户，请核对~");

				return results;
			}
			TreeMap<String, String> treeMap = new TreeMap<>();
			treeMap.put("userid", Patterns.accountId);
			treeMap.put("customid", id);
			treeMap.put("date", date);
			treeMap.put("num_per_page", numPerPage);
			treeMap.put("page", page);

			String address = Thqs.getThqstreeMap(Patterns.token, treeMap);
			try {
				Results<byte[]> testByte = Requests.testGet(Patterns.customUserV2, null, address);
				byte[] bytess = testByte.getData();
				String byteString = new String(bytess);

				// json字符串转换
				VideoPlaybackRecord videoPlaybackRecord = Json.from(byteString, VideoPlaybackRecord.class);
				List<PlayLog> list = videoPlaybackRecord.getPlay_logs().getPlay_log();
				// 展示具体的foreach循环添加
				for (PlayLog playLog : list) {
					// 根据用户id 查name
					String userName = userDao.selectNameById(playLog.getCustom_id());
					playLog.setUserName(userName);
					// 根据视频id 查视频名和所属专业
					CourseChapter courseChapter = courseSubclassDao.selectNameAndCTSN(playLog.getVideoid());
					if (courseChapter != null) {
						playLog.setCourseTypeSubclassName(courseChapter.getCourseTypeSubclassName());
						playLog.setSectionName(courseChapter.getSectionName());
					}
				}
				if (list.isEmpty() || list == null) {
					results.setStatus("1");
					results.setMessage("根据条件未查询到记录~");
					return results;
				}
				results.setStatus("0");
				results.setCount(videoPlaybackRecord.getPlay_logs().getTotal());
				results.setData(list);

				return results;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 获取视频自定义参数播放记录
		if (mobile != null && videoId != null) {
			// 根据手机号查询id
			String id = userDao.selectIdByMobile(mobile);
			if (id == null) {
				results.setStatus("1");
				results.setMessage("根据此手机号未查询到用户，请核对~");

				return results;
			}
			TreeMap<String, String> treeMap = new TreeMap<>();
			treeMap.put("userid", Patterns.accountId);
			treeMap.put("videoid", videoId);
			treeMap.put("customid", id);
			treeMap.put("date", date);
			treeMap.put("num_per_page", numPerPage);
			treeMap.put("page", page);

			String address = Thqs.getThqstreeMap(Patterns.token, treeMap);
			try {
				Results<byte[]> testByte = Requests.testGet(Patterns.customVideoV2, null, address);
				byte[] bytess = testByte.getData();
				String byteString = new String(bytess);

				// json字符串转换
				VideoPlaybackRecord videoPlaybackRecord = Json.from(byteString, VideoPlaybackRecord.class);
				List<PlayLog> list = videoPlaybackRecord.getPlay_logs().getPlay_log();
				// 展示具体的foreach循环添加
				for (PlayLog playLog : list) {
					// 根据用户id 查name
					String userName = userDao.selectNameById(playLog.getCustom_id());
					playLog.setUserName(userName);
					// 根据视频id 查视频名和所属专业
					CourseChapter courseChapter = courseSubclassDao.selectNameAndCTSN(playLog.getVideoid());
					if (courseChapter != null) {
						playLog.setCourseTypeSubclassName(courseChapter.getCourseTypeSubclassName());
						playLog.setSectionName(courseChapter.getSectionName());
					}
				}
				if (list.isEmpty() || list == null) {
					results.setStatus("1");
					results.setMessage("根据条件未查询到记录~");
					return results;
				}
				results.setStatus("0");
				results.setCount(videoPlaybackRecord.getPlay_logs().getTotal());
				results.setData(list);

				return results;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		results.setStatus("1");
		results.setMessage("错误！手机号与视频Id不能同时为空");

		return results;
	}

	@Override
	public Results<List<QuestionBankAsk>> questionList(String courseTypeSubclassName) {
		Results<List<QuestionBankAsk>> results = new Results<List<QuestionBankAsk>>();
		List<QuestionBankAsk> list = questionBankDao.questionList(courseTypeSubclassName);
		results.setStatus("0");
		results.setData(list);

		return results;
	}

	@Override
	public Results<String> addQuestion(QuestionBankAsk questionBankAsk) {
		Results<String> results = new Results<String>();
		questionBankAsk.setId(KeyGen.uuid());
		questionBankAsk.setAddtime(new Date());
		Integer count = questionBankDao.addQuestion(questionBankAsk);
		if (count == 1) {
			results.setStatus("0");
			results.setMessage("添加成功");

			return results;
		}
		results.setStatus("1");
		results.setMessage("添加失败");

		return results;
	}

	@Override
	public Results<String> deleteQuestion(String id) {
		Results<String> results = new Results<String>();
		Integer count = questionBankDao.deleteQuestion(id);
		if (count == 1) {
			results.setStatus("0");
			results.setMessage("删除成功");

			return results;
		}
		results.setStatus("1");
		results.setMessage("删除失败");

		return results;
	}
	/**
	 * 考官提问页面使用excel批量上传
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<Integer> uploadExcel(MultipartFile file,String courseTypeSubclassName) {
		Results<Integer> result = new Results<Integer>();
		Workbook wb;
		int count = 0;
		try {
			wb = WorkbookFactory.create(file.getInputStream());

			Sheet sheet = wb.getSheetAt(0);
			int rowNumber = sheet.getPhysicalNumberOfRows() - 1;
			Iterator<Row> rowIterator = sheet.rowIterator();
			for (int i = 0; i < 2; i++) {
				Row titleRow = rowIterator.next();
				titleRow.getLastCellNum();
			}
			/* String[][] table = new String[rowNumber][columnNumber]; */
			for (int i = 0; i < rowNumber && rowIterator.hasNext(); i++) {
				Row row = rowIterator.next();
				if (checkNull(0, row) != null && checkNull(1, row) != null) {
					count ++;
					QuestionBankAsk questionBankAsk = new QuestionBankAsk();
					questionBankAsk.setId(KeyGen.uuid());
					questionBankAsk.setQuestionAsk(checkNull(0, row));
					questionBankAsk.setQuestionAnswer(checkNull(1, row));
					questionBankAsk.setCourseTypeSubclassName(courseTypeSubclassName);
					questionBankAsk.setAddtime(new Date());
					questionBankDao.addQuestion(questionBankAsk);
				}
			}
			result.setStatus("0");
			result.setData(count);
			return result;
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void inputStreamToFile(InputStream ins, File file) {
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
