package com.smartparking.car.restapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartparking.car.manager.bean.TCarport;
import com.smartparking.car.manager.dao.TCarportMapper;
import com.smartparking.car.restapi.service.TCarportService;

@Service
public class TCarportServiceImpl implements TCarportService{

	@Autowired
	TCarportMapper carportMapper;
	
	@Override
	public int addTCarport(TCarport carport) {
		carportMapper.insertSelective(carport);
		return carport.getId();
	}

}
