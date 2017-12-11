package com.hospital.register.controller.subscription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.register.controller.register.RegisterController;
import com.hospital.register.service.SubscriptionService;
import com.hospital.register.util.RestResponse;

/**
 * 
 * 
 * @author caiwei
 * @version $Id: SubscriptionController.java, v 0.1 2017年12月10日 下午10:19:04 caiwei Exp $
 */

@Controller
public class SubscriptionController {
    
    private static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);
    
    @Autowired
    private SubscriptionService  subscriptionService;

    @RequestMapping(value = "/addSubcription", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse addSubcription(String openId,String scheduleId, String realName, int sex,String telephone){      
        logger.info("addSubcription,openId={}", openId);
        
        return RestResponse.successResWithTokenData("", "YGdykliy_+@124LK/");
        
        
    }
    
}
