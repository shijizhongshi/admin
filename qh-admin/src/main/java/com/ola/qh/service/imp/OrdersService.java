package com.ola.qh.service.imp;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ola.qh.dao.OrdersDao;
import com.ola.qh.dao.OrdersProductDao;
import com.ola.qh.dao.ShopDao;
import com.ola.qh.dao.UserBookDao;
import com.ola.qh.entity.Orders;
import com.ola.qh.entity.OrdersPayment;
import com.ola.qh.entity.OrdersProduct;
import com.ola.qh.entity.OrdersProductRefund;
import com.ola.qh.entity.Shop;
import com.ola.qh.entity.UserBook;
import com.ola.qh.entity.UserIntomoneyHistory;
import com.ola.qh.service.IOrdersService;
import com.ola.qh.service.IPayService;
import com.ola.qh.util.KeyGen;
import com.ola.qh.util.Results;

@Service
public class OrdersService implements IOrdersService{

	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private OrdersProductDao ordersProductDao;
	@Autowired
	private UserBookDao userBookDao;
	@Autowired
	private IPayService payService;
	
	@Transactional
	@Override
	public void comfirmOrders() {
		// TODO Auto-generated method stub
		List<Orders> orderslist = ordersDao.autoComfirmOrders();
		for (Orders orders : orderslist) {
			if(orders.getDeliveredtime()!=null){
				Date deliveredtime = orders.getDeliveredtime();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(deliveredtime);
				calendar.add(Calendar.DAY_OF_YEAR, 7);
				Date date = new Date();
				if (date.getTime() > calendar.getTime().getTime() && "DELIVERED".equals(orders.getOrdersStatus())){
					///////超过了7天
					List<OrdersProduct> list=ordersProductDao.selectByOid(orders.getId(), orders.getOrdersStatus());
					BigDecimal money = BigDecimal.ZERO;
					for(OrdersProduct ordersProduct : list){
						money=money.add(ordersProduct.getPayout());
						ordersProductDao.updateOrdersProduct("RECEIVED", "发货超过7天已自动确认收货", "DELIVERED", new Date(), ordersProduct.getId());
					}
					UserIntomoneyHistory uh = new UserIntomoneyHistory();
					uh.setAddtime(new Date());
					uh.setIntoStatus(0);
					uh.setId(KeyGen.uuid());
					uh.setMoney(money);
					uh.setOrderId(orders.getId());
					////// 往商家店铺里打钱~~~
					uh.setUserId(orders.getMuserId());
					userBookDao.saveUserIntomoneyHistory(uh);
					UserBook ub = userBookDao.single(orders.getMuserId());
					/// 修改总账本
					BigDecimal accountMoney = ub.getAccountMoney().add(money).setScale(2, BigDecimal.ROUND_DOWN);
					BigDecimal canWithdraw = ub.getCanWithdraw().add(money).setScale(2, BigDecimal.ROUND_DOWN);
					BigDecimal waitMoney = ub.getFortheMoney().subtract(money).setScale(2, BigDecimal.ROUND_DOWN);
					UserBook userbook = new UserBook();
					userbook.setAccountMoney(accountMoney);//// 总的金额
					userbook.setCanWithdraw(canWithdraw);/// 可提现金额
					userbook.setFortheMoney(waitMoney);//// 待结算的金额
					userbook.setUserId(orders.getMuserId());
					userbook.setUpdatetime(new Date());
					userBookDao.updateUserBook(userbook);
					
					ordersDao.updateOrders("RECEIVED","DELIVERED", new Date(), orders.getId());
				}
			}
			
		}
	}

	@Override
	public List<Orders> list(int pageNo, int pageSize, String ordersType, String mobile, String todate,
			String fromdate,String orderno,String ordersStatus, String recommendTeacher,String receiver) {
		// TODO Auto-generated method stub
		List<Orders> list = ordersDao.ordersList(pageNo, pageSize, ordersType, mobile, todate, fromdate, orderno, ordersStatus, recommendTeacher,receiver);
		for (Orders orders : list) {
			String shopType=null;
			if("0".equals(ordersType)){
				/////商城订单  商城店铺
				shopType="2";
			}else if("2".equals(ordersType)){
				/////服务订单
				shopType="1";
			}
			if(shopType!=null){
				Shop s=shopDao.selectShopSingle(null, orders.getMuserId(), shopType);
				orders.setShopName(s.getShopName());
				orders.setShopServeDomain(s.getServeDomain());
			}
			
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			orders.setShowtime(sf.format(orders.getAddtime()));
			if(orders.getPaidtime()!=null){
				orders.setPaidtimes(sf.format(orders.getPaidtime()));
			}else{
				orders.setPaidtimes("订单还未支付");
			}
		}
		return list;
	}

	@Override
	public int listCount(String ordersType, String mobile, String todate, String fromdate,String orderno,String ordersStatus, String recommendTeacher,String receiver) {
		// TODO Auto-generated method stub
		return ordersDao.ordersListCount(ordersType, mobile, todate, fromdate, orderno, ordersStatus, recommendTeacher,receiver);
				
	}

	@Override
	public List<OrdersProduct> productList(String orderId) {
		// TODO Auto-generated method stub
		List<OrdersProduct> list = ordersProductDao.selectByOid(orderId, null);
		for (OrdersProduct ordersProduct : list) {
			OrdersProductRefund refund = ordersProductDao.singleRefund(ordersProduct.getId());
			if(refund!=null && refund.getAddtime()!=null){
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				refund.setShowtime(sf.format(refund.getAddtime()));
			}
			ordersProduct.setOpr(refund);
		}
		return list;
	}
	
	
	/***
	 * 商品的订单
	 * 管理员同意退款与拒绝退款
	 * @param ordersProductId
	 * @param statusCode
	 * @return
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Results<String> updateRefund(String ordersProductId, String statusCode) {
		Results<String> result = new Results<String>();
		try {
			String statusName="";
			OrdersProduct op = ordersProductDao.singleOrdersProduct(ordersProductId);
			
			/////商家同意退款
			if("AGREEREFUNED".equals(statusCode)){
				statusName="管理员,同意退款";
				//////把他的退款信息改成退款成功的状态
				if(!"APPLYREFUNED".equals(op.getStatusCode())){
					result.setStatus("1");
					result.setMessage("状态不符");
					return result;
				}
				//////把钱直接退还给买家(完成之后改成退款成功)
				OrdersPayment orderspayment = ordersDao.singlePayment(op.getOrdersId());
				List<OrdersPayment> lisOrdersPayment = ordersDao.listByExtransno(orderspayment.getExtransno());
				OrdersProductRefund refund = ordersProductDao.singleRefund(ordersProductId);
				BigDecimal money = refund.getRefundMoney();
				BigDecimal totalMoney=BigDecimal.ZERO;
				for (OrdersPayment opt:lisOrdersPayment) {
					totalMoney = totalMoney.add(opt.getMoney());
				}
				int totalfee=totalMoney.multiply(new BigDecimal(100)).intValue();
				int refundfee=money.multiply(new BigDecimal(100)).intValue();
				if(orderspayment!=null){
					Map<String,String> map =new HashMap<String, String>();
					if("ALIPAY".equals(orderspayment.getPaytypeCode())){
						///////
						map=payService.aliOrdersRefund(orderspayment.getExtransno(), money.setScale(2, BigDecimal.ROUND_DOWN).doubleValue());
					}else if("WXPAY".equals(orderspayment.getPaytypeCode())){
						map=payService.wxOrderRefund(orderspayment.getExtransno(), totalfee, refundfee);
					}
					if("0".equals(map.get("status"))){
						statusCode="REFUNED";/////退款成功的也就意味着退款工作已完成
						statusName="管理员,同意退款,退款成功~";
						///////待结算的金额减少
						UserBook ub = userBookDao.single(op.getMuserId());
						if(ub!=null){
							BigDecimal waitMoney=ub.getFortheMoney().subtract(op.getPayout()).setScale(2, BigDecimal.ROUND_DOWN);
							UserBook userBook=new UserBook();
							userBook.setFortheMoney(waitMoney);///待结算金额
							userBook.setUserId(op.getMuserId());////
							userBook.setUpdatetime(new Date());
							userBookDao.updateUserBook(userBook);
						}
						
						
					}else{
						result.setStatus("1");
						result.setMessage(map.get("error"));
						return result;
					}
				}
			}
			/////商家拒绝退款
			if("REJECTREFUNED".equals(statusCode)){
				statusName="管理员拒绝退款";
				/////把订单的信息改成oldstatus原来的状态
				if(!"APPLYREFUNED".equals(op.getStatusCode())){
					result.setStatus("1");
					result.setMessage("状态不符");
					return result;
				}
			}
			///////修改申请退款的状态
			OrdersProductRefund or=new OrdersProductRefund();
			or.setOrdersProductId(ordersProductId);
			or.setStatusCode(statusCode);
			or.setUpdatetime(new Date());
			ordersProductDao.updateRefund(or);
			ordersProductDao.updateOrdersProduct(statusCode, statusName, null, new Date(), ordersProductId);

			if(ordersProductDao.selectByOid(op.getOrdersId(), null).size()==1){
				ordersDao.updateOrders(statusCode, null, new Date(), op.getOrdersId());
			}
			result.setStatus("0");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setStatus("1");
			result.setMessage("更新失败");
			return result;
		}
	}

	/**
	 * 管理员操作
	 * 服务订单的同意与拒绝
	 * @param ordersId
	 * @param statusCode
	 * @return
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Results<String> updateServeRefund(String ordersId,String statusCode){
		Results<String> result = new Results<String>();
		try {
			
			String statusName="";
			Orders orders = ordersDao.singleOrders(ordersId);
			List<OrdersProduct> list = ordersProductDao.selectByOid(ordersId, null);
			if (orders.getOrdersType() == "2") {
			
		    /////商家同意退款
			if("AGREEREFUNED".equals(statusCode)){
				
				//////把他的退款信息改成退款成功的状态
				if(!"CANCELSERVE".equals(orders.getOrdersStatus())){
					result.setStatus("1");
					result.setMessage("状态不符");
					return result;
				}
				//////把钱直接退还给买家(完成之后改成退款成功)
				OrdersPayment orderspayment = ordersDao.singlePayment(ordersId);
				//List<OrdersPayment> lisOrdersPayment = ordersDao.listByExtransno(orderspayment.getExtransno());
				
				int totalfee=orders.getPayaccount().multiply(new BigDecimal(100)).intValue();
				int refundfee=orders.getPayaccount().multiply(new BigDecimal(100)).intValue();
				if(orderspayment!=null){
					Map<String,String> map =new HashMap<String, String>();
					if("ALIPAY".equals(orderspayment.getPaytypeCode())){
						///////
						map=payService.aliOrdersRefund(orderspayment.getExtransno(), orders.getPayaccount().setScale(2, BigDecimal.ROUND_DOWN).doubleValue());
					}else if("WXPAY".equals(orderspayment.getPaytypeCode())){
						map=payService.wxOrderRefund(orderspayment.getExtransno(), totalfee, refundfee);
					}
					if("0".equals(map.get("status"))){
						statusCode="CANCELSERVEED";
						statusName="管理员,同意买家的取消申请,订单是已取消的状态";
						///////待结算的金额减少
						UserBook ub = userBookDao.single(orders.getMuserId());
						if(ub!=null){
							BigDecimal waitMoney=ub.getFortheMoney().subtract(orders.getPayaccount()).setScale(2, BigDecimal.ROUND_DOWN);
							UserBook userBook=new UserBook();
							userBook.setFortheMoney(waitMoney);///待结算金额
							userBook.setUserId(orders.getMuserId());////
							userBook.setUpdatetime(new Date());
							userBookDao.updateUserBook(userBook);
						}
						
						
					}else{
						result.setStatus("1");
						result.setMessage(map.get("error"));
						return result;
					}
				}
			}
			/////商家拒绝退款
			if("REJECTREFUNED".equals(statusCode)){
				statusCode="REJECTCANCELSERVEED";
				statusName="管理员,已拒绝取消服务";
				/////把订单的信息改成oldstatus原来的状态
				if(!"CANCELSERVE".equals(orders.getOrdersStatus())){
					result.setStatus("1");
					result.setMessage("状态不符");
					return result;
				}
			}
			///////修改申请取消服务的状态
			ordersProductDao.updateOrdersProduct(statusCode, statusName, null, new Date(), list.get(0).getId());
			if(ordersProductDao.selectByOid(list.get(0).getOrdersId(), null).size()==1){
				ordersDao.updateOrders(statusCode, null, new Date(), ordersId);
			}
			result.setStatus("0");
			return result;
			
			}else{
				result.setStatus("1");
				result.setMessage("必须是服务订单才可以走这个接口");
				return result;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setStatus("1");
			result.setMessage("更新失败");
			return result;
		}
	}
}
