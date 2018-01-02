package com.hospital.register.controller.subscription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.register.bean.Subscription;
import com.hospital.register.controller.register.RegisterController;
import com.hospital.register.exception.EhospitalServiceException;
import com.hospital.register.service.SubscriptionService;
import com.hospital.register.util.DateUtil;
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
    public RestResponse addSubcription(String openId,int scheduleId, String patientName, String patientBirthday,int patientSex,String patientPhone){      
        logger.info("addSubcription,openId={}", openId);
        Subscription subVO = new Subscription();
        subVO.setScheduleId(scheduleId);
        subVO.setPatientName(patientName);
        subVO.setPatientBirthday(DateUtil.parseDate(patientBirthday));
        subVO.setPatientSex(patientSex);
        subVO.setPatientPhone(patientPhone);
        try{
            subscriptionService.addSubscription(subVO,openId);
        }catch(EhospitalServiceException eh){
            logger.info("addSubcription error,message={}", eh.getMessage()); 
            RestResponse.errorRes(eh.getMessage());
        }catch(Exception e){
            logger.info("addSubcription error,message={}", e.getMessage()); 
            RestResponse.errorRes("系统异常,请稍后再试");
        }
        return RestResponse.successResWithTokenData("预约成功", "YGdykliy_+@124LK/");
        
        
    }
    
    @RequestMapping(value = "/addTest", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse Rest(String openId){
         System.out.println("openId:"+openId); 
        return RestResponse.successResWithTokenData("预约成功", "YGdykliy_+@124LK/");
        
        
    }
    
}
