package com.smartparking.car.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartparking.car.manager.bean.TOrder;
import com.smartparking.car.restapi.bean.ParkingReturn;
import com.smartparking.car.restapi.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping("orderdetails")
	public ParkingReturn<TOrder> OrderDetail(Integer orderId) {
		System.out.println("--------------torestapi-order-----------------------");
		TOrder order = orderService.getOrderById(orderId);
		if(order != null) {
			return ParkingReturn.success("获取订单成功", order, null);
		}else {
			return ParkingReturn.fail("获取失败", null, null);
		}
	}
	
	@RequestMapping("/pay")
	public ParkingReturn<Object> checkout(Integer orderId) {
		System.out.println("--------------restapi-checkout----------------------");
		Integer i = orderService.checkout(orderId);
		if(i == 1) {
			return ParkingReturn.success("结账成功", null, null);
		}else if(i ==0) {
			return ParkingReturn.fail("结账失败,余额不足", null, null);

		}else {
			return ParkingReturn.fail("系统异常，结账失败", null, null);
		}
		
	}
}
