package com.hospital.register.service;

import java.util.List;
import java.util.Map;

import com.hospital.register.bean.Subscription;
import com.hospital.register.bean.SubscriptionExample;
import com.hospital.register.conditionVO.RegisterVO;
import com.hospital.register.conditionVO.UserVO;

public interface SubscriptionService {

    public String addSubscription(Subscription subscription,String openid);
    
    
    public List<Subscription> querySubscriptionList(RegisterVO registerVO);  
    
    public List<Subscription>  querySubscription(SubscriptionExample  example);
    
    //新增会员并当天挂号
    public void addUserAndSubscription(UserVO userVO, String doctorId);
    
    //会员当天挂号
    public void userAddSubscription(UserVO userVO, String doctorId);
    
    //预约转挂号
    public void updateSubscription(UserVO userVO);
    
    public List<Subscription>  querySubscriptionByPage(SubscriptionExample  example,int currentPage,int pageSize);

    public int updateSubStatus(Subscription sub);
    
    public int cancleSubscription(String subcriptionId);
    
    public long countSubsciption(SubscriptionExample example);
    
    public long countSubsciptionByMap(Map<String,Object> paramsMap);
    
    public List<Map<String,Object>> querySubscriptionByMap(Map<String,Object> paramsMap);
}
