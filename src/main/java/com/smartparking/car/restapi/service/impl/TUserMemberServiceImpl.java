package com.smartparking.car.restapi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartparking.car.manager.bean.TUserMember;
import com.smartparking.car.manager.bean.TUserMemberExample;
import com.smartparking.car.manager.bean.TUserMemberExample.Criteria;
import com.smartparking.car.manager.dao.TUserMemberMapper;
import com.smartparking.car.restapi.service.TUserMemberService;
import com.smartparking.project.MD5Util;

@Service
public class TUserMemberServiceImpl implements TUserMemberService{

	@Autowired
	TUserMemberMapper TUserMemberMapper;

	@Override
	public TUserMember getTUserMemberForOne(TUserMember member) {
		TUserMemberExample example = new TUserMemberExample();
		Criteria createCriteria1 = example .createCriteria();
		Criteria createCriteria2 = example.createCriteria();
		createCriteria1.andAccountEqualTo(member.getAccount());
		createCriteria2.andEmailEqualTo(member.getEmail());
		example.or(createCriteria2);
		List<TUserMember> selectByExample = TUserMemberMapper
				.selectByExample(example);
		if (selectByExample.size() >= 1) {
			return selectByExample.get(0);
		} else {
			return null;
		}
	}

	@Override
	public TUserMember regist(TUserMember member) {
		TUserMember tUserMemberForOne = getTUserMemberForOne(member);
		if(tUserMemberForOne==null){
			String digest = MD5Util.digest(member.getPassword());
			member.setPassword(digest);
			member.setCreatetime(new Date());
			member.setStatus(0);
			TUserMemberMapper.insertSelective(member);
	        return member;
		}else{
			return null;
		}
	}

	@Override
	public void updateTUserMember(TUserMember member) {
		TUserMemberMapper.updateByPrimaryKeySelective(member);
	}

//	@Override
//	public void sendEmail(Integer id, String email) {
//		System.out.println(email);
//		String code = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
//		System.out.println(code);
//		TUserMember member = new TUserMember();
//		member.setId(id);
//		member.setCode(code);
//		updateTUserMember(member);
//		HtmlEmail htmlEmail = new HtmlEmail();
//		htmlEmail.setHostName("localhost");
//		htmlEmail.setSmtpPort(25);
//		htmlEmail.setAuthentication("", password);
//	}
	
}
