/******************************************************************
 *
 *    Java Lib For Android, Powered By Shenzhen Jiuzhou.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.smartparking.car.restapi.service
 *
 *    Filename:    MapServiceImpl.java
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
 *    Create at:   2017年8月14日 下午4:47:42
 *
 *    Revision:
 *
 *    2017年8月14日 下午4:47:42
 *        - first revision
 *
 *****************************************************************/
package com.smartparking.car.restapi.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartparking.car.manager.bean.TCarport;
import com.smartparking.car.manager.bean.TCarportExample;
import com.smartparking.car.manager.bean.TCarportExample.Criteria;
import com.smartparking.car.manager.bean.TMap;
import com.smartparking.car.manager.dao.TCarportMapper;
import com.smartparking.car.manager.dao.TMapMapper;
import com.smartparking.car.restapi.service.MapService;

/**
 * @ClassName MapServiceImpl
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author FeicusSmith
 * @Date 2017年8月14日 下午4:47:42
 * @version 1.0.0
 */
@Service
public class MapServiceImpl implements MapService {
    
    @Autowired
    TMapMapper mapMapper;
    @Autowired
    TCarportMapper carportMapper; 


    /* (非 Javadoc)
     * Description:
     * @see com.smartparking.car.restapi.service.MapService#selectAllMaps()
     */
    @Override
    public List<TCarport> selectAllCarportsByMap() {
        // TODO Auto-generated method stub
        List<TCarport> list = carportMapper.selectAllCarportsByMap();
        return list;
    }

    /* (非 Javadoc)
     * Description:
     * @see com.smartparking.car.restapi.service.MapService#updateMapAbsDistance(java.lang.Integer, java.lang.Double)
     */
    @Override
    public int updateMapAbsDistance(Integer carportId, String absDistance) {
        // TODO Auto-generated method stub  
        int row = mapMapper.updateMapAbsDistance(carportId, absDistance);
        return row;
    }


    /* (非 Javadoc)
     * Description:
     * @see com.smartparking.car.restapi.service.MapService#selectRemainingPlaceNum(java.lang.Integer)
     */
    @Override
    public int selectRemainingPlaceNum(Integer portId) {
        // TODO Auto-generated method stub
      int num = carportMapper.selectRemainingPlaceNum(portId);
        return num;
    }

    /* (非 Javadoc)
     * Description:
     * @see com.smartparking.car.restapi.service.MapService#updateRemainingPlace(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public int updateRemainingPlace(Integer portId, Integer remainingPlace) {
        // TODO Auto-generated method stub
        TCarportExample example=new TCarportExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(portId);
        TCarport carport=new TCarport();
        carport.setRemainingPlace(remainingPlace);
        
        int row = carportMapper.updateByExampleSelective(carport, example);
        
        return row;



    }

    /* (非 Javadoc)
     * Description:
     * @see com.smartparking.car.restapi.service.MapService#selectDisplayData()
     */
    @Override
    public List<Map<String, Object>> selectDisplayData() {
        // TODO Auto-generated method stub
        
        List<Map<String, Object>> data = mapMapper.selectDisplayData();
        
        return data;
    }


}
