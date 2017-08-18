package com.smartparking.car.restapi.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.smartparking.car.manager.bean.TUserPortowner;
import com.smartparking.car.restapi.bean.SmartCarReturn;
import com.smartparking.car.restapi.service.PersonageMaterialService;

@RestController
@RequestMapping("/persionage")
public class PersonageMaterialController {
	
	@Autowired
	PersonageMaterialService service;
	
	/**
	 * api层 处理文件上传请求
	 * @param uId
	 * @param netUrl
	 */
	@RequestMapping("/apiUploadFile")
	public SmartCarReturn<TUserPortowner> uploadFile(@RequestParam("uId")Integer uId,
			@RequestParam("netUrl")String netUrl){
		//new一个userPortowner对象
		TUserPortowner userPortowner = new TUserPortowner();
		//给对象设置一些值
		try {
			userPortowner.setId(uId);
			userPortowner.setIconpath(netUrl);
			
			System.out.println(userPortowner);
			//调用service的方法
			service.uploadFile(userPortowner);
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return SmartCarReturn.fail("上传失败", null, null);
		}
		
			return SmartCarReturn.success("上传成功", userPortowner, null);
	}
	
	
	
	/**
	 * api层查询个人信息
	 * @param userPortowner
	 * @return
	 */
	@RequestMapping("/getPersionage")
	public SmartCarReturn<TUserPortowner> getPersonage(TUserPortowner userPortowner) {
		
		TUserPortowner userPortowners =service.getPersonage(userPortowner);
		
		return SmartCarReturn.success("成功", userPortowners, null);
		
	}
	
	/**
	 * api层修改个人信息
	 * @param userPortowner
	 */
	@RequestMapping("/updatePersonage")
	public SmartCarReturn<Object> updataPersonage(TUserPortowner userPortowner){
		
		System.out.println("updatePersonage---aaaaa"+ userPortowner);
		
		try {
			service.updatePersonage(userPortowner);
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return SmartCarReturn.fail("修改失败", null, null);
		}
		
			return SmartCarReturn.success("修改成功", null,null);
	}

}
