package com.ola.qh.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ola.qh.dao.CourseClassTemplateDao;
import com.ola.qh.entity.CourseClassTemplate;
import com.ola.qh.service.ICourseClassTemplateService;

@Service
public class CourseClassTemplateService implements ICourseClassTemplateService{

	@Autowired
	private CourseClassTemplateDao  courseClassTemplateDao;

	@Override
	public List<CourseClassTemplate> selectCourseClassTemplate(String id,String templateName,int pageNo,int pageSize) {
		
		return courseClassTemplateDao.selectCourseClassTemplate(id,templateName,pageNo,pageSize);
	}

	@Override
	public int insertCourseClassTemplate(CourseClassTemplate courseClassTemplate) {
		
		return courseClassTemplateDao.insertCourseClassTemplate(courseClassTemplate);
	}

	@Override
	public int updateCourseClassTemplate(CourseClassTemplate courseClassTemplate) {
		
		return courseClassTemplateDao.updateCourseClassTemplate(courseClassTemplate);
	}

	@Override
	public int deleteCourseClassTemplate(String id) {
		
		return courseClassTemplateDao.deleteCourseClassTemplate(id);
	}
}
