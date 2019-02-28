package com.ola.qh.service.imp;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

import com.ola.qh.dao.CourseSubclassDao;
import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseSection;
import com.ola.qh.entity.QuestionAnswer;
import com.ola.qh.entity.QuestionBank;
import com.ola.qh.entity.QuestionCategory;
import com.ola.qh.entity.QuestionSubCategory;
import com.ola.qh.service.ICourseSubclassService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;
@Service
public class CourseSubclassService implements ICourseSubclassService{

	@Autowired
	private CourseSubclassDao courseSubclassDao;
	
	@Override
	public List<CourseChapter> courseChapterList(String courseId,String courseChapterName,int pageNo,int pageSize,String courseTypeName,String courseTypeSubclassName) {
		// TODO Auto-generated method stub
		return courseSubclassDao.courseChapterList(courseId,courseChapterName,pageNo,pageSize,courseTypeName,courseTypeSubclassName);
	}

	@Override
	public List<CourseSection> courseSectionList(String courseChapterId,int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		return courseSubclassDao.courseSectionList(courseChapterId,pageNo,pageSize);
	}

	@Override
	public Results<String> courseChapterSaveUpdate(CourseChapter ccp) {
		// TODO Auto-generated method stub
		Results<String> result = new Results<String>();
		if(ccp.getId()==null || "".equals(ccp.getId())){
			//////保存用户的信息
			ccp.setId(KeyGen.uuid());
			ccp.setAddtime(new Date());
			int num = courseSubclassDao.insertCourseChapter(ccp);
			if(num>0){
				result.setStatus("0");
				return result;
			}
			result.setStatus("1");
			result.setMessage("保存失败");
			return result;
			
		}
		//////修改用户的信息
		ccp.setUpdatetime(new Date());
		int num = courseSubclassDao.updateCourseChapter(ccp);
		if(num>0){
			result.setStatus("0");
			return result;
		}
		result.setStatus("1");
		result.setMessage("修改失败");
		return result;
	}

	@Override
	public Results<String> courseSectionSaveUpdate(CourseSection cs) {
		// TODO Auto-generated method stub
		Results<String> result = new Results<String>();
		if(cs.getId()!=null && !"".equals(cs.getId())){
			///////修改用户的信息
			cs.setUpdatetime(new Date());
			int num = courseSubclassDao.updateCourseSection(cs);
			if(num>0){
				result.setStatus("0");
				return result;
			}
			result.setStatus("1");
			result.setMessage("更新失败");
			return result;
		}
		//////保存用户的信息
		cs.setId(KeyGen.uuid());
		cs.setAddtime(new Date());
		int num = courseSubclassDao.insertCourseSection(cs);
		if(num>0){
			result.setStatus("0");
			return result;
		}
		result.setStatus("1");
		result.setMessage("保存失败");
		return result;
	}

	@Override
	public int courseChapterListCount(String courseId, String courseChapterName, String courseTypeName,
			String courseTypeSubclassName) {
		// TODO Auto-generated method stub
		return courseSubclassDao.courseChapterListCount(courseId,courseChapterName, courseTypeName, courseTypeSubclassName);
	}

	@Override
	public int deleteChapter(String id) {
		// TODO Auto-generated method stub
		return courseSubclassDao.deleteChapter(id);
	}

	@Override
	public int courseSectionListCount(String courseChapterId) {
		// TODO Auto-generated method stub
		return courseSubclassDao.courseSectionListCount(courseChapterId);
	}

	@Override
	public int deleteSerction(String id) {
		// TODO Auto-generated method stub
		return courseSubclassDao.deleteSerction(id);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<String> importExcel(MultipartFile file,String courseChapterId) throws Exception{
		Results<String> result = new Results<String>();
			Workbook wb = WorkbookFactory.create(file.getInputStream());
			for (int z = 0; z < wb.getNumberOfSheets(); z++) {
				Sheet sheet = wb.getSheetAt(z);
				int rowNumber = sheet.getPhysicalNumberOfRows() - 1;
				Iterator<Row> rowIterator = sheet.rowIterator();
				for (int i = 0; i < rowNumber && rowIterator.hasNext(); i++) {
					Row row = rowIterator.next();
						////// 保存题库的信息
					CourseSection sc = new CourseSection();
					sc.setAddtime(new Date());
					sc.setId(KeyGen.uuid());
					sc.setIsshow("1");////可见
					sc.setCourseChapterId(courseChapterId);
					sc.setSectionName(checkNull(0,row));
					sc.setVideoId(checkNull(1,row));
					///////这个章下的这一节是否已经存在了
					int count = courseSubclassDao.existSection(checkNull(0,row), courseChapterId);
					if(count==0){
						courseSubclassDao.insertCourseSection(sc);
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
