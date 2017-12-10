package com.hospital.register.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospital.register.bean.Subscription;
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

    /**
     *  
     * @see com.hospital.register.service.SubscriptionService#checkSubscription(com.hospital.register.bean.Subscription)
     */
    @Override
    public String checkSubscription(Subscription subscription) {
        
        /**
         * 1.校验是否重复预约
         * 2.校验是否有号源
         * 3.校验预约时间
         * 
         */
        
        return null;
    }

    @Override
    public String addSubscription(Subscription subscription) {
        int result = subtractingScheduleNo(subscription.getScheduleId());
        if(result != 0){
            int subResult = insertSubscription(subscription);
            if(subResult == 0){
                throw new EhospitalServiceException(ResponseCode.RESPONSE_COMMON_ERROR_MESSAGE,"新增预约失败");
            }
        }
        
        return null;
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
