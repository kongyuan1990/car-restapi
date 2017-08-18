/******************************************************************
 *
 *    Java Lib For Android, Powered By Shenzhen Jiuzhou.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.smartparking.car.restapi.controller
 *
 *    Filename:    MapController.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    Company:     Digital Telemedia Co.,Ltd
 *
 *    @author:     FeicusSmith
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年8月14日 下午6:05:23
 *
 *    Revision:
 *
 *    2017年8月14日 下午6:05:23
 *        - first revision
 *
 *****************************************************************/
package com.smartparking.car.restapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartparking.car.manager.bean.TCarport;
import com.smartparking.car.manager.bean.TMap;
import com.smartparking.car.restapi.service.MapService;

/**
 * @ClassName MapController
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author FeicusSmith
 * @Date 2017年8月14日 下午6:05:23
 * @version 1.0.0
 */
@RequestMapping("/map")
@RestController
public class MapController {
    
    @Autowired
    MapService mapService;
    
    
    
    @RequestMapping("/getMapsData")
    public List<TCarport> getMapsData(){
        List<TCarport> list = mapService.selectAllCarportsByMap();
        //System.out.println("RESTAPI-------mapList:------------>"+maps);
        return list;
    }
    
    
    @RequestMapping("/updateAbsDistance")
    public String updateAbsDistance(Integer carportId,String absDistance){  
        int row = mapService.updateMapAbsDistance(carportId, absDistance);
        return row==1?"true":"false";
  
    }
    
    
    
    @RequestMapping("/getRemainingPlaceNum/{portId}")
    public Map< String, Integer> selectRemainingPlaceNum(@PathVariable("portId")Integer portId){
     
        Map< String, Integer> numMap=new HashMap<>();
        
        int num = mapService.selectRemainingPlaceNum(portId);
        numMap.put("num", num); 
        return numMap;
    }
    
    @RequestMapping("/updateRemainingPlace")
    public Integer updateRemainingPlace(@RequestParam("portId")Integer portId,@RequestParam("remainingPlace")Integer remainingPlace){
        int row = mapService.updateRemainingPlace(portId, remainingPlace);
        return row; 
    }
    
    @RequestMapping("/getDisplayData")
    public List<Map<String, Object>> getDisplayData(){
        List<Map<String, Object>> data = mapService.selectDisplayData();
        return data;
    }
    
    

}
