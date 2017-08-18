package com.smartparking.car.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.smartparking.car.manager.bean.TUserMember;
import com.smartparking.car.manager.bean.TUserPortowner;
import com.smartparking.car.restapi.bean.SmartCarReturn;
import com.smartparking.car.restapi.service.MemberService;

@RestController
public class MemberController {
    
    @Autowired
    MemberService memberService;
    
    /**
     * 车主登录api
     */
    @RequestMapping("/carownerLogin")
    public SmartCarReturn<TUserMember> carownerLogin(TUserMember userMember){
        
        try {
            TUserMember carownerLogin = memberService.carownerLogin(userMember);
            if(carownerLogin != null){
                return SmartCarReturn.success("登录成功", carownerLogin, null);
            }else{
                return SmartCarReturn.fail("用户名或密码错误，请从新登录", carownerLogin, null);
            }
        } catch (Exception e) {
            return SmartCarReturn.fail("登录异常，请重新登录", null, null);
        }
       
    }
    
    /**
     * 车库经营者登录api
     */
    @RequestMapping("/portownerLogin")
    public SmartCarReturn<TUserPortowner> portownerLogin(TUserPortowner userPortowner){
        try {
            TUserPortowner portownerLogin = memberService.portownerLogin(userPortowner);
            if(portownerLogin != null){
                return SmartCarReturn.success("登录成功", portownerLogin, null);
            }else{
                return SmartCarReturn.fail("用户名或密码错误，请从新登录", portownerLogin, null);
            }
        } catch (Exception e) {
            return SmartCarReturn.fail("登录异常，请重新登录", null, null);
        }
        
    }
    
    /**
     * 获取车主信息
     */
    @ResponseBody
    @RequestMapping("/getUserMember")
    public TUserMember getUserMember(TUserMember userMember){
        
        return memberService.carownerLogin(userMember);
    }
    
    /**
     * 获取车库经营者信息
     */
    @RequestMapping("/getUserPortowner")
    public TUserPortowner getUserPortowner(TUserPortowner userPortowner){
        return memberService.portownerLogin(userPortowner);
    }
    
    @RequestMapping("/sendEmail")
    public SmartCarReturn<Object> sendEmail(String email, String userType){
        
        boolean b = memberService.sendEmail(email, userType);
            if(b){
                return SmartCarReturn.success("发送成功", null, null);  
            }else{
                return SmartCarReturn.fail("发送失败", null, null);
            }
        
    }
     
}
