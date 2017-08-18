package com.smartparking.car.restapi.service;

import com.smartparking.car.manager.bean.TOrder;

public interface OrderService {
	
	public  TOrder getOrderById(Integer id);
	
	public Integer checkout(Integer id);
	
	public Integer memberCheckout(Integer id);
	
	public Integer portownerCheckin(Integer id);
}
