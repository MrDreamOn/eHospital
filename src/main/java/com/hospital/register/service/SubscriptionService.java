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
    
    public void addUserAndSubscription(UserVO userVO);
    
    public void userAddSubscription(UserVO userVO);
    
    public void updateSubscription(UserVO userVO);

}
