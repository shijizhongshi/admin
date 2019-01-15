package com.ola.qh.service.imp;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ola.qh.dao.OrdersDao;
import com.ola.qh.dao.UserBookDao;
import com.ola.qh.entity.Orders;
import com.ola.qh.entity.OrdersProduct;
import com.ola.qh.entity.UserBook;
import com.ola.qh.entity.UserIntomoneyHistory;
import com.ola.qh.service.IOrdersService;
import com.ola.qh.util.KeyGen;

@Service
public class OrdersService implements IOrdersService{

	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private UserBookDao userBookDao;
	
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
					List<OrdersProduct> list=ordersDao.selectByOid(orders.getId(), orders.getOrdersStatus());
					BigDecimal money = BigDecimal.ZERO;
					for(OrdersProduct ordersProduct : list){
						money=money.add(ordersProduct.getPayout());
						ordersDao.updateOrdersProduct("RECEIVED", "发货超过7天已自动确认收货", "DELIVERED", new Date(), ordersProduct.getId());
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

}
