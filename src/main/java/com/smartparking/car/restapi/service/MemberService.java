package com.smartparking.car.restapi.service;

import java.util.List;

import com.smartparking.car.manager.bean.TOrder;
import com.smartparking.car.manager.bean.TUserMember;
import com.smartparking.car.manager.bean.TUserPortowner;
import com.smartparking.car.manager.bean.TWallet;


public interface MemberService {

    TUserMember carownerLogin(TUserMember userMember);

    TUserPortowner portownerLogin(TUserPortowner userPortowner);

    boolean sendEmail(String email, String userType);
    
    Object checkUserByEmail(String email, String userType);
    

	TUserMember getMemberAll(TUserMember userMember);

	TWallet getUserWallet(TUserMember userMember);


	void updataBalanceBy(TUserMember userMember, Integer money);

	List<TOrder> getUserCarCards(TUserMember userMember);

	void saveiconpath(String iconpath, Integer id);

	TUserMember updatauser(TUserMember userMember);

	Boolean checkpassword(Integer id, String old_password);

	void updataByName(Integer id, String new_password);

}
