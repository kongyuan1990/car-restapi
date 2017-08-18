package com.smartparking.car.restapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartparking.car.manager.bean.TCert;
import com.smartparking.car.manager.bean.TCertMember;
import com.smartparking.car.manager.bean.TCertMemberExample;
import com.smartparking.car.manager.bean.TCertMemberExample.Criteria;
import com.smartparking.car.manager.dao.TCertMapper;
import com.smartparking.car.manager.dao.TCertMemberMapper;
import com.smartparking.car.restapi.service.CertMemberService;

@Service
public class CertMemberServiceImpl implements CertMemberService{
	
	@Autowired
	TCertMapper certMapper;
	
	@Autowired
	TCertMemberMapper certMemberMapper;

	@Override
	public List<TCert> getCertsByType(Integer type) {
		List<TCert> selectByType = certMapper.selectByType(type);
		System.out.println(selectByType);
		return selectByType;
	}

	@Override
	public void insertCerts(List<TCertMember> certsList) {
		TCertMemberExample example = new TCertMemberExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andMemberIdEqualTo(certsList.get(0).getMemberId());
		certMemberMapper.deleteByExample(example );
		certMemberMapper.insertBatch(certsList);
	}
	
}
