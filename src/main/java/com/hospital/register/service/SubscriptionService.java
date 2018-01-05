package com.hospital.register.service;

import java.util.List;

import com.hospital.register.bean.Subscription;
import com.hospital.register.bean.SubscriptionExample;
import com.hospital.register.conditionVO.RegisterVO;
import com.hospital.register.conditionVO.UserVO;

public interface SubscriptionService {

    public String addSubscription(Subscription subscription,String openid);
    
    
    public List<Subscription> querySubscriptionList(RegisterVO registerVO);  
    
    public List<Subscription>  querySubscription(SubscriptionExample  example);
    
    //新增会员并当天挂号
    public void addUserAndSubscription(UserVO userVO);
    
    //会员当天挂号
    public void userAddSubscription(UserVO userVO);
    
    //预约转挂号
    public void updateSubscription(UserVO userVO);
    
    public List<Subscription>  querySubscriptionByPage(SubscriptionExample  example,int currentPage,int pageSize);

    public int updateSubStatus(Subscription sub);
    
    public int cancleSubscription(String subcriptionId);
}
