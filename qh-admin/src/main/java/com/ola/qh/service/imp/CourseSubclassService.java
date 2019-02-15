package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.CourseSubclassDao;
import com.ola.qh.entity.CourseChapter;
import com.ola.qh.entity.CourseSection;
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
	public int courseChapterListCount(String courseId, String courseTypeName,
			String courseTypeSubclassName) {
		// TODO Auto-generated method stub
		return courseSubclassDao.courseChapterListCount(courseId, courseTypeName, courseTypeSubclassName);
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
	
}
