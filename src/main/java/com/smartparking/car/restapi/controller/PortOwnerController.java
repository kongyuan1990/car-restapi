package com.smartparking.car.restapi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartparking.car.manager.bean.TUserMember;
import com.smartparking.car.manager.bean.TUserPortowner;
import com.smartparking.car.manager.dao.TUserPortownerMapper;
import com.smartparking.car.restapi.bean.SmartCarReturn;
import com.smartparking.car.restapi.service.TUserPortownerService;
import com.smartparking.car.restapi.service.TWalletService;

@RequestMapping("/portowner")
@RestController
public class PortOwnerController {

	@Autowired
	TUserPortownerService tUserPortownerService;

	@Autowired
	TWalletService tWalletService;

	@RequestMapping("/portownerRegist")
	public SmartCarReturn<TUserPortowner> regist(TUserPortowner portOwner) {

		TUserPortowner tportOwner = null;
		Map<String, Object> hashMap = new HashMap<>();
		try {
			int tWalletId = tWalletService.createWallet();
			portOwner.setWalletId(tWalletId);
			tportOwner = tUserPortownerService.regist(portOwner);
		} catch (Exception e) {
			e.printStackTrace();
			hashMap.put("msg", "用户名或邮箱已被注册");
		}
		if (tportOwner == null) {
			return SmartCarReturn.fail("注册失败", null, hashMap);
		} else {
			tportOwner.setPassword("");
			return SmartCarReturn.success("注册成功", tportOwner, null);
		}

	}
}
