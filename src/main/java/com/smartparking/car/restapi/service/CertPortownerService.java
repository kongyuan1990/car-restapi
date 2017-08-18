package com.smartparking.car.restapi.service;

import java.util.List;

import com.smartparking.car.manager.bean.TCertPortowner;

public interface CertPortownerService {

	public void insertCerts(List<TCertPortowner> certsList);
}
