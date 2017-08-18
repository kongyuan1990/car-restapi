package com.smartparking.car.restapi.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartparking.car.manager.bean.TUserMember;
import com.smartparking.car.manager.bean.TUserPortowner;
import com.smartparking.car.manager.bean.TUserPortownerExample;
import com.smartparking.car.manager.bean.TUserPortownerExample.Criteria;
import com.smartparking.car.manager.dao.TUserPortownerMapper;
import com.smartparking.car.restapi.service.TUserPortownerService;
import com.smartparking.project.MD5Util;

@Service
public class TUserPortownerServiceImpl implements TUserPortownerService{

	@Autowired
	TUserPortownerMapper tUserPortownerMapper;
	
	@Override
	public TUserPortowner regist(TUserPortowner portOwner) {
		TUserPortowner tUserPortownerForOne = getTUserPortownerForOne(portOwner);
		if(tUserPortownerForOne==null){
			String digest = MD5Util.digest(portOwner.getPassword());
			portOwner.setPassword(digest);
			portOwner.setCreatetime(new Date());
			portOwner.setStatus(0);
			tUserPortownerMapper.insertSelective(portOwner);
	        return portOwner;
		}else{
			return null;
		}
		
	}

	@Override
	public TUserPortowner getTUserPortownerForOne(TUserPortowner portOwner) {
		TUserPortownerExample example = new TUserPortownerExample();
		Criteria createCriteria1 = example.createCriteria();
		Criteria createCriteria2 = example.createCriteria();
		createCriteria1.andAccountEqualTo(portOwner.getAccount());
		createCriteria2.andEmailEqualTo(portOwner.getEmail());
		example.or(createCriteria2);
		List<TUserPortowner> selectByExample = tUserPortownerMapper
				.selectByExample(example);
		if (selectByExample.size() >= 1) {
			return selectByExample.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void updateTUserPortowner(TUserPortowner portOwner) {
		tUserPortownerMapper.updateByPrimaryKeySelective(portOwner);
	}

}
