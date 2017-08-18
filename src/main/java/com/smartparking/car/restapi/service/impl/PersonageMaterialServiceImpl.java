package com.smartparking.car.restapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartparking.car.manager.bean.TUserMemberExample;
import com.smartparking.car.manager.bean.TUserMemberExample.Criteria;
import com.smartparking.car.manager.bean.TUserPortowner;
import com.smartparking.car.manager.dao.TUserMemberMapper;
import com.smartparking.car.manager.dao.TUserPortownerMapper;
import com.smartparking.car.restapi.service.PersonageMaterialService;

@Service
public class PersonageMaterialServiceImpl implements PersonageMaterialService{

	@Autowired
	TUserPortownerMapper mapper;
	
	/**
	 * 查询个人信息
	 */
	@Override
	public TUserPortowner getPersonage(TUserPortowner userPortowner) {
		
		TUserPortowner tUserPortowner = mapper.selectByPrimaryKey(userPortowner.getId());
		System.out.println("1"+tUserPortowner);
		return tUserPortowner;
		 
	}

	/**
	 * 修改个人信息
	 */
	@Override
	public void updatePersonage(TUserPortowner userPortowner) {
		
		mapper.updateByPrimaryKeySelective(userPortowner);
	}


	/**
	 * 文件上传
	 */
	@Override
	public void uploadFile(TUserPortowner userPortowner) {
		
		mapper.updateByPrimaryKeySelective(userPortowner);
		
	}

	

	

}
