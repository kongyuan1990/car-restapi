package com.smartparking.car.restapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartparking.car.manager.bean.TWallet;
import com.smartparking.car.manager.dao.TWalletMapper;
import com.smartparking.car.restapi.service.TWalletService;

@Service
public class TWalletServiceImpl implements TWalletService{
	@Autowired
	TWalletMapper tWalletMapper;

	@Override
	public int createWallet() {
		TWallet tWallet = new TWallet();
		tWallet.setBalance(0.0);
		tWalletMapper.insertSelective(tWallet);
		return tWallet.getId();
	}

}
