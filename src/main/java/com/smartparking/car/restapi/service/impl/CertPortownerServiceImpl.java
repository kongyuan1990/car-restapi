package com.smartparking.car.restapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartparking.car.manager.bean.TCertPortowner;
import com.smartparking.car.manager.bean.TCertPortownerExample;
import com.smartparking.car.manager.bean.TCertPortownerExample.Criteria;
import com.smartparking.car.manager.dao.TCertPortownerMapper;
import com.smartparking.car.restapi.service.CertPortownerService;

@Service
public class CertPortownerServiceImpl implements CertPortownerService{

	@Autowired
	TCertPortownerMapper certPortownerMapper;
	@Override
	public void insertCerts(List<TCertPortowner> certsList) {
		TCertPortownerExample example = new TCertPortownerExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andPortownerIdEqualTo(certsList.get(0).getPortownerId());
		certPortownerMapper.deleteByExample(example  );
		certPortownerMapper.insertBatch(certsList);
	}
	

}
