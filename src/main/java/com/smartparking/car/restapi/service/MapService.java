/******************************************************************
 *
 *    Java Lib For Android, Powered By Shenzhen Jiuzhou.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.smartparking.car.restapi.service
 *
 *    Filename:    MapService.java
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
 *    Create at:   2017年8月14日 下午4:28:33
 *
 *    Revision:
 *
 *    2017年8月14日 下午4:28:33
 *        - first revision
 *
 *****************************************************************/
package com.smartparking.car.restapi.service;

import java.util.List;
import java.util.Map;

import com.smartparking.car.manager.bean.TCarport;

/**
 * @ClassName MapService
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author FeicusSmith
 * @Date 2017年8月14日 下午4:28:33
 * @version 1.0.0
 */

public interface MapService {
    
 
    public List<TCarport> selectAllCarportsByMap();
    
    int updateMapAbsDistance(Integer carportId,String absDistance);
    
 
    int selectRemainingPlaceNum(Integer portId);
    
    public int updateRemainingPlace(Integer portId,Integer remainingPlace);
    
    List<Map<String,Object>> selectDisplayData();
    

}
