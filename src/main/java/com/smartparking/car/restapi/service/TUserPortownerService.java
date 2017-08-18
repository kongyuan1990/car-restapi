package com.smartparking.car.restapi.service;

import java.util.List;

import com.smartparking.car.manager.bean.TCertPortowner;
import com.smartparking.car.manager.bean.TUserPortowner;

public interface TUserPortownerService {
	
	TUserPortowner getTUserPortownerForOne(TUserPortowner portOwner);

	TUserPortowner regist(TUserPortowner portOwner);

	void updateTUserPortowner(TUserPortowner portOwner);

}
