package com.smartparking.car.restapi.service;

import java.util.List;

import com.smartparking.car.manager.bean.TCert;
import com.smartparking.car.manager.bean.TCertMember;


public interface CertMemberService {

	public List<TCert> getCertsByType(Integer type);
	
	public void insertCerts(List<TCertMember> certsList);
	
}
