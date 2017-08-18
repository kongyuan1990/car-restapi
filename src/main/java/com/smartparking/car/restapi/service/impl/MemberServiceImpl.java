package com.smartparking.car.restapi.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartparking.car.manager.bean.TOrder;
import com.smartparking.car.manager.bean.TUserMember;
import com.smartparking.car.manager.bean.TUserMemberExample;
import com.smartparking.car.manager.bean.TUserMemberExample.Criteria;
import com.smartparking.car.manager.bean.TUserPortowner;
import com.smartparking.car.manager.bean.TUserPortownerExample;
import com.smartparking.car.manager.bean.TUserToken;
import com.smartparking.car.manager.bean.TUserTokenExample;
import com.smartparking.car.manager.bean.TWallet;
import com.smartparking.car.manager.dao.TOrderMapper;
import com.smartparking.car.manager.dao.TUserMemberMapper;
import com.smartparking.car.manager.dao.TUserPortownerMapper;
import com.smartparking.car.manager.dao.TUserTokenMapper;
import com.smartparking.car.manager.dao.TWalletMapper;
import com.smartparking.car.restapi.service.MemberService;
import com.smartparking.project.MD5Util;

@Service
public class MemberServiceImpl implements MemberService{
   
    @Autowired
    TUserMemberMapper userMemberMapper;
    
    @Autowired
    TUserPortownerMapper userPortownerMapper;
    
    @Autowired
    TUserTokenMapper userTokenMapper;
    
    @Autowired
	TWalletMapper walletMapper;
    
    @Autowired
	TOrderMapper orderMapper;

    @Override
    public TUserMember carownerLogin(TUserMember userMember) {
        TUserMemberExample userMemberExample = new TUserMemberExample();
        Criteria criteria = userMemberExample.createCriteria();
        criteria.andAccountEqualTo(userMember.getAccount());
        criteria.andPasswordEqualTo(MD5Util.digest(userMember.getPassword()));
        
        List<TUserMember> list = userMemberMapper.selectByExample(userMemberExample);
        
        return list.size() == 1 ? list.get(0) : null;
    }

    @Override
    public TUserPortowner portownerLogin(TUserPortowner userPortowner) {
        TUserPortownerExample portownerExample = new TUserPortownerExample();
        com.smartparking.car.manager.bean.TUserPortownerExample.Criteria criteria = portownerExample.createCriteria();
        criteria.andAccountEqualTo(userPortowner.getAccount());
        criteria.andPasswordEqualTo(MD5Util.digest(userPortowner.getPassword()));
        
        List<TUserPortowner> list = userPortownerMapper.selectByExample(portownerExample);
        
        return list.size() == 1 ? list.get(0) : null;
    }

    @Override
    public boolean sendEmail(String email, String userType) {
        Object object = checkUserByEmail(email,userType);
        if(object != null){
            String newToken = UUID.randomUUID().toString().replace("-", "");
            if("carowner".equals(userType)){
                TUserMember userMember = (TUserMember)object;
                TUserTokenExample userTokenExample = new TUserTokenExample();
                com.smartparking.car.manager.bean.TUserTokenExample.Criteria criteria = userTokenExample.createCriteria();
                criteria.andPhoneNumberEqualTo(new Integer(userMember.getPhoneNumber()));
                
                List<TUserToken> list = userTokenMapper.selectByExample(userTokenExample);
                if(list !=null && list.size() > 0){
                   TUserToken userToken = list.get(0);
                   
                }
                
            }
        } 
        return false;
    }

    @Override
    public Object checkUserByEmail(String email, String userType) {

        if("carowner".equals(userType)){
           TUserMemberExample userMemberExample = new TUserMemberExample();
           Criteria criteria = userMemberExample.createCriteria();
           criteria.andEmailEqualTo(email);
           
           List<TUserMember> list = userMemberMapper.selectByExample(userMemberExample);
           if(list != null && list.size()==1){
               return list.get(0);
           }
        }
        
        if("portowner".equals(userType)){
            TUserPortownerExample userPortownerExample = new TUserPortownerExample();
            com.smartparking.car.manager.bean.TUserPortownerExample.Criteria criteria = userPortownerExample.createCriteria();
            criteria.andEmailEqualTo(email);
            
            List<TUserPortowner> list = userPortownerMapper.selectByExample(userPortownerExample);
            if(list != null && list.size()==1){
                return list.get(0);
            }
        }
        
        return null;
    }
    
    /**
	 * 获取用户所有基本资料
	 */
	@Override
	public TUserMember getMemberAll(TUserMember userMember) {
		Integer id = userMember.getId();
		TUserMember selectByPrimaryKey = userMemberMapper.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}

	/**
	 *获取钱包余额
	 */
	@Override
	public TWallet getUserWallet(TUserMember userMember) {
		Integer id = userMember.getId();
		TWallet wallet = walletMapper.selectByPrimaryKey(id);
		return wallet;
	}

	/**
	 * 获取用户当前所有订单
	 */
	@Override
	public List<TOrder> getUserCarCards(TUserMember userMember) {
		Integer id = userMember.getId();
		List<TOrder> list = orderMapper.selectByName(id);
		return list;
	}

	/**
	 * 用户余额充值
	 */
	@Override
	public void updataBalanceBy(TUserMember userMember, Integer money) {
		Integer id = userMember.getId();
		walletMapper.updateByTuserMember(id,money);
	}

	@Override
	public void saveiconpath(String iconpath, Integer id) {
		userMemberMapper.updatebyname(iconpath,id);
	}

	@Override
	public TUserMember updatauser(TUserMember userMember) {
		TUserMemberExample example = new TUserMemberExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andIdEqualTo(userMember.getId());
		userMemberMapper.updateByExampleSelective(userMember, example);
		List<TUserMember> selectByExample = userMemberMapper.selectByExample(example );
		
		return selectByExample.get(0);
	}

	/**
	 * 根据用户id检查密码是否正确
	 */
	@Override
	public Boolean checkpassword(Integer id, String old_password) {
		String digest = MD5Util.digest(old_password);
		TUserMember selectByPrimaryKey = userMemberMapper.selectByPrimaryKey(id);
		String password = selectByPrimaryKey.getPassword();
		if(password.equals(digest)){
			System.out.println("密码正确");
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 根据用户id修改密码
	 */
	@Override
	public void updataByName(Integer id, String new_password) {
		String digest = MD5Util.digest(new_password);
		TUserMember record = new TUserMember();
		record.setId(id);
		record.setPassword(digest);
		userMemberMapper.updateByPrimaryKeySelective(record );
	}

}
