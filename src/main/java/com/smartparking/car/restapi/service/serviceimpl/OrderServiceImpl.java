package com.smartparking.car.restapi.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartparking.car.manager.bean.TOrder;
import com.smartparking.car.manager.bean.TWallet;
import com.smartparking.car.manager.dao.TOrderMapper;
import com.smartparking.car.manager.dao.TUserMemberMapper;
import com.smartparking.car.manager.dao.TWalletMapper;
import com.smartparking.car.restapi.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	TOrderMapper orderMapper;
	@Autowired
	TUserMemberMapper userMemberMapper;
	@Autowired
	TWalletMapper walletMapper;
	
	@Override
	public TOrder getOrderById(Integer id) {
		return orderMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer checkout(Integer id) {
		
		TOrder order = orderMapper.selectByPrimaryKey(id);
		Double totalPrice = order.getTotalPrice();
		//用户付款
		TWallet memberWallent = orderMapper.selectMemberWalletByPrimaryKey(id);	
		if(memberWallent.getBalance()-totalPrice<=0) {
			System.out.println("余额不足");
			return 0;
		}
		memberWallent.setBalance(memberWallent.getBalance()-totalPrice);
		walletMapper.updateByPrimaryKeySelective(memberWallent);
		
		//商家收款
		TWallet portownerWallent = orderMapper.selectPortownerWalletByPrimaryKey(id);
		portownerWallent.setBalance(portownerWallent.getBalance() + totalPrice);
		walletMapper.updateByPrimaryKeySelective(portownerWallent);
		
		//修改订单状态
		order.setStatus(2);
		orderMapper.updateByPrimaryKeySelective(order);
		return 1;
		
	}

	@Override
	public Integer memberCheckout(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer portownerCheckin(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
