package com.ola.qh.service.imp;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.ola.qh.dao.CourseLineWhiteDao;
import com.ola.qh.entity.CourseLineWhite;
import com.ola.qh.service.ICourseLineWhiteService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@Service
public class CourseLineWhiteService implements ICourseLineWhiteService {

	@Autowired
	private CourseLineWhiteDao courseLineWhiteDao;

	@Transactional
	@Override
	public Results<List<CourseLineWhite>> lineWhiteList(String liveId, String mobile, int pageNo, int pageSize) {

		Results<List<CourseLineWhite>> results = new Results<List<CourseLineWhite>>();
		try {

			List<CourseLineWhite> list = courseLineWhiteDao.lineWhiteList(liveId, mobile, pageNo, pageSize);

			Integer count = courseLineWhiteDao.lineWhiteListCount(liveId, mobile);

			results.setCount(count);
			results.setData(list);
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
	public Results<String> insertLineWhite(CourseLineWhite courseLineWhite) {
		Results<String> results = new Results<String>();
		try {

			List<CourseLineWhite> list = courseLineWhiteDao.lineWhiteList(courseLineWhite.getLiveId(),
					courseLineWhite.getMobile(), 0, 0);
			if (list.size() > 0) {
				results.setMessage("手机号已存在");
				results.setStatus("1");
				return results;
			}
			courseLineWhite.setAddtime(new Date());
			courseLineWhite.setId(KeyGen.uuid());
			int insert = courseLineWhiteDao.insertLineWhite(courseLineWhite);
			if (insert <= 0) {

				results.setStatus("1");
				return results;
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
	public Results<String> updateLineWhite(CourseLineWhite courseLineWhite) {
		Results<String> results = new Results<String>();
		try {

			List<CourseLineWhite> list = courseLineWhiteDao.lineWhiteList(courseLineWhite.getLiveId(),
					courseLineWhite.getMobile(), 0, 0);
			if (list.size() > 0) {
				results.setMessage("手机号已存在");
				results.setStatus("1");
				return results;
			}
			courseLineWhite.setUpdatetime(new Date());
			int update = courseLineWhiteDao.updateLineWhite(courseLineWhite);
			if (update <= 0) {

				results.setStatus("1");
				return results;
			}

			results.setStatus("0");
			return results;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			results.setStatus("1");
			return results;
		}
	}

	@Override
	public int deleteLineWhite(String id, String liveId) {
		// TODO Auto-generated method stub
		return courseLineWhiteDao.deleteLineWhite(id, liveId);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Results<String> importExcel(MultipartFile file, String liveId) throws Exception {
		Results<String> result = new Results<String>();
		Workbook wb = WorkbookFactory.create(file.getInputStream());

		Sheet sheet = wb.getSheetAt(0);
		int rowNumber = sheet.getPhysicalNumberOfRows() - 1;
		Iterator<Row> rowIterator = sheet.rowIterator();
		/* String[][] table = new String[rowNumber][columnNumber]; */
		for (int i = 0; i < rowNumber && rowIterator.hasNext(); i++) {
			Row row = rowIterator.next();
			////// 保存题库的信息

			/// 保存他下边的集合
			CourseLineWhite courseLineWhite = new CourseLineWhite();
			courseLineWhite.setAddtime(new Date());
			courseLineWhite.setId(KeyGen.uuid());
			courseLineWhite.setMobile(checkNull(0, row));
			courseLineWhite.setLiveId(liveId);
			courseLineWhiteDao.insertLineWhite(courseLineWhite);

		}

		result.setStatus("0");
		return result;

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

	@Override
	public List<CourseLineWhite> selectAllByLiveId(String liveId) {

		return courseLineWhiteDao.selectAllByLiveId(liveId);
	}

}
