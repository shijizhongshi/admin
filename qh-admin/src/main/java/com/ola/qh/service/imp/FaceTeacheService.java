package com.ola.qh.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.FaceTeacheDao;
import com.ola.qh.entity.FaceTeache;
import com.ola.qh.service.IFaceTeacheService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@Service
public class FaceTeacheService implements IFaceTeacheService{

	@Autowired
	private FaceTeacheDao faceTeacheDao;

	@Transactional
	@Override
	public Results<List<FaceTeache>> faceTeacheList(String courseTypeSubclassName, String teacherName, String courseName,int pageNo,int pageSize) {
		
		Results<List<FaceTeache>> results=new Results<List<FaceTeache>>();
		try {
			
			int count=faceTeacheDao.faceTeacheCount(courseTypeSubclassName, teacherName, courseName);
			List<FaceTeache> list=faceTeacheDao.faceTeacheList(courseTypeSubclassName, teacherName, courseName, pageNo, pageSize);
			
			results.setData(list);
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
	public Results<String> insertFaceTeache(FaceTeache faceTeache) {
		
		Results<String> results=new Results<String>();
		try {
			faceTeache.setAddtime(new Date());
			faceTeache.setId(KeyGen.uuid());
			faceTeacheDao.insertFaceTeache(faceTeache);
			results.setStatus("0");
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			results.setMessage("保存失败~");
			return results;
		}
	}

	@Transactional
	@Override
	public Results<String> updateFaceTeache(FaceTeache faceTeache) {
		
		Results<String> results=new Results<String>();
		try {
			faceTeache.setUpdatetime(new Date());
			faceTeacheDao.updateFaceTeache(faceTeache);
			results.setStatus("0");
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			results.setMessage("修改失败~");
			return results;
		}
	}

	@Transactional
	@Override
	public Results<String> deleteFaceTeache(String id) {
		
		Results<String> results=new Results<String>();
		try {
			
			faceTeacheDao.deleteFaceTeache(id);
			results.setStatus("0");
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}
	
	
}
