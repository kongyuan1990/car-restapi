package com.smartparking.car.restapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartparking.car.manager.bean.TUserPortowner;
import com.smartparking.car.manager.bean.TWallet;
import com.smartparking.car.manager.dao.TWalletMapper;
import com.smartparking.car.restapi.service.WalletService;

@Service
public class WalletServiceImpl implements WalletService{
	
	@Autowired
	TWalletMapper mapper;

	@Override
	public TWallet getWallet(TUserPortowner userPortowner) {
		
		TWallet wallet = mapper.getWalletById(userPortowner.getWalletId());
		
		return wallet;
	}

	@Override
	public void updataBalance(Double money, Integer wId) {
		
		System.out.println("充值金额："+money+"用户钱包ID："+wId);
		
		mapper.updateByAddBalance(money, wId);
		
	}

	@Override
	public void updataLessenBalabce(Double money, Integer wId) {
		
		System.out.println("提现金额："+money+"用户钱包ID："+wId);
		
		mapper.updateByLesenBalance(money, wId);
		
	}

}
