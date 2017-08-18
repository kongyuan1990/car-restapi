package com.smartparking.car.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartparking.car.manager.bean.TCarport;
import com.smartparking.car.manager.bean.TUserPortowner;
import com.smartparking.car.restapi.bean.CarReturn;
import com.smartparking.car.restapi.bean.SmartCarReturn;
import com.smartparking.car.restapi.service.CarportService;
/**
 * 停车场控制器
 * @author mxs
 * 2017年8月14日
 * @version 1.0.0
 */
@RequestMapping("/carport")
@RestController
public class CarportController {
    
    @Autowired
    CarportService carportService;
    
    @RequestMapping("/list")
    public CarReturn<List<TCarport>> carportList(){
        System.out.println("carportList_restapi");
        
        List<TCarport> list;
        try {
            list = carportService.getAllCarport();
            
            if(list!=null){
                return CarReturn.success("停车场列表成功",list,null);
            }else{
                return CarReturn.fail("停车场列表为空",null,null);
            }
        } catch (Exception e) {
            e.getStackTrace();
            return CarReturn.fail("停车场列表失败",null,null);
        }
    }
    
    
    @RequestMapping("/carportByCond")
    public CarReturn<List<TCarport>> carportByCond(@RequestParam(value="cond",required=false)String cond,
            @RequestParam(value="name",required=false)String name){
        System.out.println("carportByCond_restapi.....");
        
        List<TCarport> list =null;
        try {
            if(cond.equals("distance")){
                //按距离排序
                list = carportService.getAllCarport();
            }else if(cond.equals("price")){
                //按价格排序
                list = carportService.getCarportByPrice();
            }else if(cond.equals("name")){
                //按停车场名称查找
                list = carportService.getCarportByName(name);
            }
            else{
                list = carportService.getAllCarport();
            }
            
            if(list!=null){
                return CarReturn.success("停车场列表成功",list,null);
            }else{
                return CarReturn.fail("停车场列表为空",null,null);
            }
        } catch (Exception e) {
            e.getStackTrace();
            return CarReturn.fail("停车场列表失败",null,null);
        }
    }
    
    /**
	 * api层处理文件上传的控制器
	 */
	@RequestMapping("/apiUploadCarportPhone")
	public SmartCarReturn<TCarport> uploadFile(@RequestParam("carportId")Integer carportId,
			@RequestParam("netUrl")String netUrl){
		//获取车库对象
		TCarport carport = new TCarport();
		try {
			//设置值
			carport.setId(carportId);
			carport.setPicturepath(netUrl);
			//调用service的方法
			carportService.updatePicturepath(carport);
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return SmartCarReturn.fail("上传失败", carport, null);
		}
			System.out.println(carport);
			return SmartCarReturn.success("上传成功", null, null);
	}
	
	/**
	 * api层修改我的车库信息 返回json数据
	 * @param carport
	 * @return
	 */
	@RequestMapping("/editCarport")
	public SmartCarReturn<Object> editCarport(TCarport carport){
		
		System.out.println("AAA" + carport);
		
		try {
			carportService.updateCarport(carport);
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return SmartCarReturn.fail("车库修改失败", null, null);
		}
		
			return SmartCarReturn.success("修改成功", null, null);
	}
	
	
	/**
	 * api层查询我的车库信息返回json数据
	 * @param userPortowner
	 * @return
	 */
	@RequestMapping("/getCarport")
	public SmartCarReturn<TCarport> getCarport(TUserPortowner userPortowner) {
		//调用CarportService的方法
		TCarport carport = carportService.getCarport(userPortowner);
		System.out.println(carport);
		//返回数据
		return SmartCarReturn.success("成功", carport, null);
	}

}
