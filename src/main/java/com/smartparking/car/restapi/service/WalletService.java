package com.smartparking.car.restapi.service;

import com.smartparking.car.manager.bean.TUserPortowner;
import com.smartparking.car.manager.bean.TWallet;

public interface WalletService {

	TWallet getWallet(TUserPortowner userPortowner);

	void updataBalance(Double money, Integer wId);

	void updataLessenBalabce(Double money, Integer wId);

}
