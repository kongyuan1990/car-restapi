package com.smartparking.car.restapi.service;

import com.smartparking.car.manager.bean.TUserPortowner;


public interface PersonageMaterialService {

	//查询个人信息
	TUserPortowner getPersonage(TUserPortowner userPortowner);

	//修改个人信息
	void updatePersonage(TUserPortowner userPortowner);
	
	//文件上传
	void uploadFile(TUserPortowner userPortowner);





}
