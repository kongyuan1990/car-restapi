package com.smartparking.car.restapi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartparking.car.manager.bean.TUserPortowner;
import com.smartparking.car.manager.bean.TWallet;
import com.smartparking.car.restapi.bean.SmartCarReturn;
import com.smartparking.car.restapi.service.WalletService;

/**
 * api端处理钱包请求的处理器
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/wallet")
public class WalletController {
	
	@Autowired
	WalletService service;
	
	@RequestMapping("/getWallet")
	public SmartCarReturn<TWallet> getWallet(TUserPortowner userPortowner) {
		
		TWallet wallet;
		try {
			wallet = service.getWallet(userPortowner);
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return SmartCarReturn.fail("查询失败,请输入正确数据", null, null);
		}
		
			return SmartCarReturn.success("查询成功", wallet, null);
	}
	
	
	@RequestMapping("/addMoney")
	public SmartCarReturn<Object> addMoney(@RequestParam("value")Double money,@RequestParam("wId")Integer wId) {
		
		try {
			service.updataBalance(money,wId);
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return SmartCarReturn.fail("充值失败,请输入有效充值金额", null, null);
		}
		
			return SmartCarReturn.success("充值成功", null, null);
	}
	
	@RequestMapping("/lessenMoney")
	public SmartCarReturn<Object> lessenMoney(@RequestParam("value")Double money,@RequestParam("wId")Integer wId){
		
		try {
			service.updataLessenBalabce(money,wId);
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return SmartCarReturn.fail("提现失败", null, null);
		}
		
			return SmartCarReturn.success("提现成功", null, null);
	}

}
