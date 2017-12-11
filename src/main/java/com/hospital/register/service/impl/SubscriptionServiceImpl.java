package com.hospital.register.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospital.register.bean.Subscription;
import com.hospital.register.bean.SubscriptionExample;
import com.hospital.register.bean.SubscriptionExample.Criteria;
import com.hospital.register.dao.ScheduleMapper;
import com.hospital.register.dao.SubscriptionMapper;
import com.hospital.register.exception.EhospitalServiceException;
import com.hospital.register.service.SubscriptionService;
import com.hospital.register.util.ResponseCode;

/**
 * 预约逻辑处理
 * 
 * @author caiwei
 * @version $Id: SubscriptionServiceImpl.java, v 0.1 2017年12月10日 下午10:22:27 caiwei Exp $
 */

@Service
@Transactional(rollbackFor=RuntimeException.class)
public class SubscriptionServiceImpl implements SubscriptionService{
    
    @Autowired
    private ScheduleMapper  scheduleMapper;
    
    @Autowired
    private SubscriptionMapper  subscriptionMapper;



    private void checkSubscription(Subscription subscription,String openid) {
        checkSameSubscription(subscription.getScheduleId(),subscription.getPatientPhone());
        checkSubscriptionNo(subscription.getScheduleId());
        /**
         * 1.校验是否重复预约
         * 2.校验是否有号源
         * 3.校验预约时间
         * 
         */
    }
    
    private void checkSameSubscription(int scheduleId,String patientPhone){
        SubscriptionExample example = new SubscriptionExample();
        Criteria criteria = example.createCriteria();
        criteria.andScheduleIdEqualTo(scheduleId);
        criteria.andPatientPhoneEqualTo(patientPhone);
        criteria.andSubscriptionStatusEqualTo(0);                                
        List<Subscription> list = subscriptionMapper.selectByExample(example);       
        if(list.size() >0){
            throw new EhospitalServiceException(ResponseCode.RESPONSE_COMMON_ERROR_MESSAGE,"该手机号已预约相同排班,请重新选择"); 
        }
        
    }
    
    private void checkSubscriptionNo(int scheduleId){  
        int clincNo = scheduleMapper.checkScheduleNo(scheduleId);       
        if(clincNo <= 0){
            throw new EhospitalServiceException(ResponseCode.RESPONSE_COMMON_ERROR_MESSAGE,"该排班已约满，请选择其他排班"); 
        }
        
    }

    @Override
    public String addSubscription(Subscription subscription,String openid) {      
        checkSubscription(subscription,openid);
        int result = subtractingScheduleNo(subscription.getScheduleId());
        if(result != 0){
            int subResult = insertSubscription(subscription);
            if(subResult == 0){
                throw new EhospitalServiceException(ResponseCode.RESPONSE_COMMON_ERROR_MESSAGE,"新增预约失败");
            }
        }
        
        return ResponseCode.RESPONSE_COMMON_SUCCESS_MESSAGE;
    }
    
    private int subtractingScheduleNo(Integer scheduleId){
        synchronized (scheduleId) {
            return scheduleMapper.updateScheduleNo(scheduleId);
        }
    }

    private int insertSubscription(Subscription subscription){
        return subscriptionMapper.insertSelective(subscription);
    }
}
