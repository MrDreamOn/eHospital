package com.hospital.register.controller.manager;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hospital.register.bean.Subscription;
import com.hospital.register.bean.SubscriptionExample;
import com.hospital.register.bean.SubscriptionExample.Criteria;
import com.hospital.register.bean.User;
import com.hospital.register.bean.UserExample;
import com.hospital.register.conditionVO.UserVO;
import com.hospital.register.service.SubscriptionService;
import com.hospital.register.service.UserService;
import com.hospital.register.util.DateUtil;

@Controller
@RequestMapping(value = "/queryRegister")
public class QueryRegisterController {

    private static final Logger logger = LoggerFactory.getLogger(QueryRegisterController.class);

    
    @Autowired
    private UserService  userService;
    
    @Autowired
    private SubscriptionService subscriptionService;
    
    
    /**
     * 根据手机号判断是否是会员
     * 
     * @param telPhone
     * @return
     */
    public String queryTelphone(String telPhone){
        logger.info("queryUser,telPhone={}", telPhone);
        UserExample example = new UserExample();
        example.createCriteria().andTelephoneEqualTo(telPhone);
        List<User> userList = userService.findUsersByCondition(example);
        if(userList.size() == 0){
            //用户未注册，补充数据挂号
            
            return "";
        }
        User user = userList.get(0);
        SubscriptionExample subExample = new SubscriptionExample();
        Criteria criteria = subExample.createCriteria();
        criteria.andSubscriptionDateEqualTo(DateUtil.formatDateToDate(new Date()));
        criteria.andUserIdEqualTo(user.getUserId());
        List<Subscription> sublist = subscriptionService.querySubscription(subExample);
        if(sublist.size() == 0){
            //会员挂号
            return "";
        }
        
        //预约挂号
        
        return "";
    }
    
    
    public String addRegister(UserVO userVO){
        
        if("1".equals(userVO.getStatus())){
            subscriptionService.addUserAndSubscription(userVO);
            
        }
        
        if("2".equals(userVO.getStatus())){
            subscriptionService.userAddSubscription(userVO);
        }
        
        if("3".equals(userVO.getStatus())){
            subscriptionService.updateSubscription(userVO);
        }
        
        return "";
    }
    
    
}
