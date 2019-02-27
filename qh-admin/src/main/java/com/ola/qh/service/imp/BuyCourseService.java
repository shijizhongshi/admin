package com.ola.qh.service.imp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ola.qh.dao.BusinessDao;
import com.ola.qh.dao.CourseClassDao;
import com.ola.qh.dao.CourseDao;
import com.ola.qh.dao.UserBuyCourseDao;
import com.ola.qh.entity.Business;
import com.ola.qh.entity.BusinessBook;
import com.ola.qh.entity.BusinessBookHistory;
import com.ola.qh.entity.BuyCourseDomain;
import com.ola.qh.entity.Course;
import com.ola.qh.entity.CourseClass;
import com.ola.qh.entity.OpenCourse;
import com.ola.qh.entity.UserBuyCourse;
import com.ola.qh.service.IBuyCourseService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

/**
 * 开课/购买课程的展示
 * 
 * @ClassName:BuyCourseService
 * @Description:
 * @author guoyuxue
 * @date:2019年2月23日
 *
 */
@Service
public class BuyCourseService implements IBuyCourseService {

	@Autowired
	private CourseClassDao courseClassDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private UserBuyCourseDao userBuyCourseDao;
	@Autowired
	private BusinessDao businessDao;

	@Transactional
	@Override
	public Results<String> openCourse(OpenCourse oc) {
		// TODO Auto-generated method stub
		BigDecimal account = BigDecimal.ZERO;
		Results<String> result = new Results<String>();
		String businessId=null;
		
		BusinessBook bbold=new BusinessBook();
		
		if(oc.getCourseWays()==2){
			///////通过账号查出对应的加盟商的信息
			Business business=businessDao.single(null, oc.getAdminName(), null);
			if(business!=null){
				if("1".equals(business.getStatus())){
					result.setStatus("1");
					result.setMessage("钱不够了");
					return result;
				}
				if("2".equals(business.getStatus()) || business.getExpireTime().getTime()<new Date().getTime()){
					result.setStatus("1");
					result.setMessage("到期了");
					return result;
				}
				
				
				bbold=businessDao.singlebook(business.getId(), null);
				if(bbold!=null){
					if(bbold.getSurplusaccount().compareTo(oc.getAccount())==-1){
						result.setStatus("1");
						result.setMessage("开课金额已经不足,请先去充值在来开课吧~");
						return result;
					}
				}else{
					result.setStatus("1");
					result.setMessage("账本不存在,请检查");
					return result;
				}
				
				businessId=business.getId();
				String bid = businessDao.singleBusinessUser(null, oc.getUserId());
				if(bid!=null){
					if(!bid.equals(businessId)){
					   //////说明这个用户属于固定的加盟商(不能再找其他加盟商进行开课了)
						result.setStatus("1");
						result.setMessage("说明这个用户属于固定的加盟商");
						return result;
					}
				}else{
					///////用户已经属于某个固定的加盟商了
					businessDao.insertBusinessUser(KeyGen.uuid(), businessId, oc.getUserId());
				}
				
			}else{
				result.setStatus("1");
				result.setMessage("该账号的加盟商不存在");
				return result;
			}
		}
		if (oc.getTypes() == 1) {
			///// 报班学习课程
			List<String> productIds = oc.getProductId();

			for (String classId : productIds) {
				CourseClass cc = courseClassDao.single(classId);
				account = account.add(cc.getClassDiscountPrice());
				/////保证用户没有开这个班级
				/*int count = userBuyCourseDao.selectUserBuyCourseCount(oc.getUserId(), classId, null);
				if(count==0){*/
			        ///////// 为用户添加开课记录
					UserBuyCourse ubc = new UserBuyCourse();
					ubc.setAddtime(new Date());
					ubc.setClassId(classId);
					ubc.setCourseDiscountPrice(cc.getClassDiscountPrice());
					ubc.setCourseImgUrl(cc.getImgUrl());
					ubc.setCourseName(cc.getClassName());
					ubc.setCoursePrice(cc.getClassPrice());
					String buyCourseId = KeyGen.uuid();
					ubc.setId(buyCourseId);
					ubc.setPayType("线下支付");
					ubc.setUserId(oc.getUserId());
					userBuyCourseDao.insertUserCourse(ubc);
					///////// 开课的时候添加加盟商的账本记录(方便之后对账用)
					BusinessBookHistory bbh = new BusinessBookHistory();
					bbh.setAccount(cc.getClassDiscountPrice());
					bbh.setAddtime(new Date());
					bbh.setBusinessId(businessId);
					bbh.setSalesPeople(oc.getSalesName());
					bbh.setSalesMobile(oc.getSalesMobile());
					bbh.setCourseWays(oc.getCourseWays());
					bbh.setId(KeyGen.uuid());
					bbh.setTypes(2);
					bbh.setBuyCourseId(buyCourseId);
					businessDao.insertBookHistory(bbh);
				/*}*/
				
			}
		} else if (oc.getTypes() == 2) {
			//// 报网课学习课程
			List<String> productIds = oc.getProductId();
			for (String courseId : productIds) {
				Course c = courseDao.existCourse(courseId);
				account = account.add(c.getCourseDiscountPrice());
				////////保证这个用户没有开过这个课程的时候
				/*int count = userBuyCourseDao.selectUserBuyCourseCount(oc.getUserId(), null, courseId);
				///////// 为用户添加开课记录
				if(count==0){*/
					UserBuyCourse ubc = new UserBuyCourse();
					ubc.setAddtime(new Date());
					ubc.setCourseId(courseId);
					ubc.setCourseDiscountPrice(c.getCourseDiscountPrice());
					ubc.setCourseImgUrl(c.getCourseImg());
					ubc.setCourseName(c.getCourseName());
					ubc.setCoursePrice(c.getCoursePrice());
					String buyCourseId = KeyGen.uuid();
					ubc.setId(buyCourseId);
					ubc.setPayType("线下支付");
					ubc.setUserId(oc.getUserId());
					userBuyCourseDao.insertUserCourse(ubc);
					///////// 开课的时候添加加盟商的账本记录(方便之后对账用)
					BusinessBookHistory bbh = new BusinessBookHistory();
					bbh.setAccount(c.getCourseDiscountPrice());
					bbh.setAddtime(new Date());
					bbh.setBusinessId(businessId);
					bbh.setSalesPeople(oc.getSalesName());
					bbh.setSalesMobile(oc.getSalesMobile());
					bbh.setCourseWays(oc.getCourseWays());
					bbh.setId(KeyGen.uuid());
					bbh.setTypes(2);
					bbh.setBuyCourseId(buyCourseId);
					businessDao.insertBookHistory(bbh);
				/*}*/
				
			}
		} else {
			result.setStatus("1");
			result.setMessage("只能报网课或者是报班");
			return result;
		}
		if (oc.getAccount().compareTo(account) != 0) {
			///// 传过来的金额和实际计算的金额不一致的话就报错误
			result.setStatus("1");
			result.setMessage("金额和实际计算的兑换金额不一致");
			return result;
		}
		if (oc.getCourseWays()==2) {
			///////// 如果是加盟商的开课的时候
			
			if (bbold != null) {
				//////// 开课的时候修改加盟商的账本
				BusinessBook bbnew = new BusinessBook();
				bbnew.setBusinessId(null);
				bbnew.setSurplusaccount(bbold.getSurplusaccount().subtract(account));///// 剩余的减少
				bbnew.setUsedaccount(bbold.getUsedaccount().add(account));////// 已经用的增多
				bbnew.setUpdatetime(new Date());
				businessDao.updatebook(bbnew);
			}
			
		}
		result.setStatus("0");
		return result;
	}

	
	@Override
	public Results<List<UserBuyCourse>> buyRecord(BuyCourseDomain bcd) {
		// TODO Auto-generated method stub
		Results<List<UserBuyCourse>> result=new Results<List<UserBuyCourse>>();
		List<UserBuyCourse> list = userBuyCourseDao.selectUserBuyCourse(bcd);
		for (UserBuyCourse userBuyCourse : list) {
			//////////查出对应的操作账号
			
			
		}
		result.setStatus("0");
		result.setData(list);
		return result;
	}


	@Override
	public int existOpenCourse(String courseId, String userId, String classId) {
		// TODO Auto-generated method stub
		
		return userBuyCourseDao.selectUserBuyCourseCount(userId, classId, courseId);
	}

}
