package com.ola.qh.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ola.qh.entity.OrdersProduct;

public interface OrdersProductDao {
	public List<OrdersProduct> selectByOid(@Param("orderId")String orderId,
			@Param("statusCode")String statusCode);
	
	public int updateOrdersProduct(@Param("statusCode")String statusCode,
			@Param("statusName")String statusName,
			@Param("oldStatusCode")String oldStatusCode,
			@Param("updatetime")Date updatetime,
			@Param("id")String id);
	
	public int selectByOrderIdCount(@Param("orderId")String orderId);
	
	public List<OrdersProduct> selectByOrderId(@Param("orderId")String orderId,
			@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize);
	
}
