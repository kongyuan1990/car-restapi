package com.smartparking.car.restapi.service;

import java.util.List;

import com.smartparking.car.manager.bean.TCarport;
import com.smartparking.car.manager.bean.TUserPortowner;

/**
 * 停车场Service
 * @author mxs
 * 2017年8月14日
 * @version 1.0.0
 */
public interface CarportService {

    /**
     * 获取所有的停车场信息
     * @Description
     * @return
     */
    List<TCarport> getAllCarport();

    /**
     * 按价格排序停车场列表
     * @Description
     * @return
     */
    List<TCarport> getCarportByPrice();

    /**
     * 按停车场名称查找停车场
     * @Description
     * @return
     */
    List<TCarport> getCarportByName(String name);
    
    TCarport getCarport(TUserPortowner userPortowner);

	void updateCarport(TCarport carport);

	void updatePicturepath(TCarport carport);

}
