package com.smartparking.car.restapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartparking.car.manager.bean.TCarport;
import com.smartparking.car.manager.bean.TCarportExample;
import com.smartparking.car.manager.bean.TCarportExample.Criteria;
import com.smartparking.car.manager.bean.TUserPortowner;
import com.smartparking.car.manager.dao.TCarportMapper;
import com.smartparking.car.restapi.service.CarportService;

/**
 * 停车场Service实现类
 * @author mxs
 * 2017年8月14日
 * @version 1.0.0
 */
@Service
public class CarportServiceImpl implements CarportService {
    
    @Autowired
    TCarportMapper carportMapper;

    @Override
    public List<TCarport> getAllCarport() {
        
        List<TCarport> list = carportMapper.selectByExample(null);
        return list;
    }
    
    /**
     * 按价格排序
     */
    @Override
    public List<TCarport> getCarportByPrice() {
        
        List<TCarport> list = carportMapper.getCarportByPrice();
        return list;
    }

    /**
     * 按停车场名称查找停车场
     */
    @Override
    public List<TCarport> getCarportByName(String name) {
        List<TCarport> list = carportMapper.getCarportByName(name);
        return list;
    }
    
    @Override
	public TCarport getCarport(TUserPortowner userPortowner) {
		
		TCarport carport = carportMapper.getById(userPortowner.getCarportId());
		System.out.println(userPortowner.getCarportId());
		return carport;
	}

	
	@Override
	public void updateCarport(TCarport carport) {
		
		carportMapper.updateByPrimaryKeySelective(carport);
		
	}


	@Override
	public void updatePicturepath(TCarport carport) {
		
		carportMapper.updateByPrimaryKeySelective(carport);
		
	}

}
