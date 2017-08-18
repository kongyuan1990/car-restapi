package com.smartparking.car.restapi.service;

import com.smartparking.car.manager.bean.TUserMember;


public interface TUserMemberService {

	TUserMember getTUserMemberForOne(TUserMember member);

    TUserMember regist(TUserMember member);
    
    void updateTUserMember(TUserMember member);

//	void sendEmail(Integer id, String email);
}
