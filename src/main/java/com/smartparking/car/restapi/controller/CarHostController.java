package com.smartparking.car.restapi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartparking.car.manager.bean.TUserMember;
import com.smartparking.car.restapi.bean.SmartCarReturn;
import com.smartparking.car.restapi.service.TUserMemberService;
import com.smartparking.car.restapi.service.TWalletService;


@RestController
@RequestMapping("/carhostmember")
public class CarHostController {
    
    @Autowired
    TUserMemberService tUserMemberService;

    @Autowired
    TWalletService tWalletService;

    @RequestMapping("/carhostRegist")
    public SmartCarReturn<TUserMember> regist(TUserMember member){
        
        TUserMember tMember = null;
        Map<String,Object> hashMap = new HashMap<>();
        try {
        	int tWalletId = tWalletService.createWallet();
        	member.setWalletId(tWalletId);
            tMember = tUserMemberService.regist(member);
        } catch (Exception e) {
        	e.printStackTrace();
            hashMap.put("msg", "用户名或邮箱已被注册");
        }
        if(tMember==null){
            return SmartCarReturn.fail("注册失败", null, hashMap);
        }else{
            tMember.setPassword("");
            return SmartCarReturn.success("注册成功", tMember, null);
        }
        
    }
    
}
