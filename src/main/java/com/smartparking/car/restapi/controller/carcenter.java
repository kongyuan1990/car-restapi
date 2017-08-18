package com.smartparking.car.restapi.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartparking.car.manager.bean.TOrder;
import com.smartparking.car.manager.bean.TUserMember;
import com.smartparking.car.manager.bean.TWallet;
import com.smartparking.car.restapi.bean.SmartCarReturn;
import com.smartparking.car.restapi.service.MemberService;

@RequestMapping("/carcenter")
@RestController
public class carcenter {
	
	@Autowired
	MemberService memberService;
	
	
	
	@RequestMapping("/changepassword")
	public SmartCarReturn<Object> changepassword(@RequestParam("old_password")String old_password,
			@RequestParam("new_password")String new_password,
			@RequestParam("id")Integer id
			){
		Boolean password = memberService.checkpassword(id,old_password);
		if(password){
			memberService.updataByName(id,new_password);
			return SmartCarReturn.success("密码修改成功", null, null);
		}else{
			return SmartCarReturn.fail("原密码不正确，请重新输入", null, null);
		}
		
	}
	
	@RequestMapping("/editinformation")
	public SmartCarReturn<TUserMember>  editinformation(@RequestParam("string1")String string){
		TUserMember userMember = null;
		try {
			userMember = new ObjectMapper().readValue(string, TUserMember.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		TUserMember userMember1 = memberService.updatauser(userMember);
		if(userMember1!=null){
			return SmartCarReturn.success("资料获取成功", userMember1, null);
		}else{
			return SmartCarReturn.fail("获取资料失败", null, null);
		}
	}
	
	@RequestMapping("/portrait")
	public void portrait(@RequestParam("iconpath")String iconpath,@RequestParam("id")Integer id){
		memberService.saveiconpath(iconpath,id);
	}
	
	/**
	 * 钱包提现
	 * @param money
	 * @param userMember
	 * @return
	 */
	@RequestMapping("/todelmoney")
	public SmartCarReturn<TWallet> todelmoney(@RequestParam("money")Integer money,TUserMember userMember){
		TWallet wallet = memberService.getUserWallet(userMember);
		if(wallet.getBalance()<money){
			return SmartCarReturn.fail("提现失败，账户余额小于所提金额", null, null);
		}
		memberService.updataBalanceBy(userMember,-money);
		TWallet wallet1 = memberService.getUserWallet(userMember);
		return SmartCarReturn.success("", wallet1, null);
	}
	
	/**
	 * 钱包充值
	 * @param money
	 * @param userMember
	 * @return 
	 */
	@RequestMapping("/addmoney")
	public SmartCarReturn<TWallet> addmoney(@RequestParam("money")Integer money,TUserMember userMember){
		memberService.updataBalanceBy(userMember,money);
		TWallet wallet1 = memberService.getUserWallet(userMember);
		return SmartCarReturn.success("", wallet1, null);
	}
	
	/**
	 * 基本信息资料查询
	 * @param userMember
	 * @return
	 */
	@RequestMapping("/basic")
	public SmartCarReturn<TUserMember> basic(TUserMember userMember){
		TUserMember member = memberService.getMemberAll(userMember);
		if(member!=null){
			return SmartCarReturn.success("资料获取成功", member, null);
		}else{
			return SmartCarReturn.fail("获取资料失败", null, null);
		}
	}
	
	/**
	 * 钱包余额查询
	 * @param userMember
	 * @return
	 */
	@RequestMapping("/wallet")
	public SmartCarReturn<TWallet> wallet(TUserMember userMember){
		TWallet wallet = memberService.getUserWallet(userMember);
		if(wallet!=null){
			return SmartCarReturn.success("资料获取成功", wallet, null);
		}else{
			return SmartCarReturn.fail("获取资料失败", null, null);
		}
		
	}
	
	/**
	 * 车主停车历史记录查询
	 * @param userMember
	 * @return
	 */
	@RequestMapping("/carcard")
	public SmartCarReturn<List<TOrder>> carcard(TUserMember userMember){
		
		List<TOrder> carCards = memberService.getUserCarCards(userMember);
		if(carCards!=null){
			return SmartCarReturn.success("资料获取成功", carCards, null);
		}else{
			return SmartCarReturn.fail("获取资料失败", null, null);
		}
		
	}
	
	
}
