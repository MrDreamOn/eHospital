package com.hospital.register.controller.subscription;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.register.bean.Schedule;
import com.hospital.register.bean.ScheduleExample;
import com.hospital.register.bean.ScheduleExample.Criteria;
import com.hospital.register.bean.Subscription;
import com.hospital.register.bean.User;
import com.hospital.register.bean.UserExample;
import com.hospital.register.conditionVO.ScheduleVO;
import com.hospital.register.exception.EhospitalServiceException;
import com.hospital.register.service.ScheduleService;
import com.hospital.register.service.SubscriptionService;
import com.hospital.register.service.UserService;
import com.hospital.register.util.DateUtil;
import com.hospital.register.util.RestResponse;

/**
 * 
 * 
 * @author caiwei
 * @version $Id: SubscriptionController.java, v 0.1 2017年12月10日 下午10:19:04 caiwei Exp $
 */

@Controller
@RequestMapping(value = "/subscription")
public class SubscriptionController {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private ScheduleService     scheduleService;
    
    @Autowired
    private UserService         userService;

    @RequestMapping(value = "/addSubcription", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse addSubcription(String openId, int scheduleId, String patientName,
                                       String patientBirthday, int patientSex,
                                       String patientPhone) {
        logger.info("addSubcription,openId={}", openId);
        Subscription subVO = new Subscription();
        subVO.setScheduleId(scheduleId);
        subVO.setPatientName(patientName);
        subVO.setPatientBirthday(DateUtil.parseDate(patientBirthday));
        subVO.setPatientSex(patientSex);
        subVO.setPatientPhone(patientPhone);
        try {
            subscriptionService.addSubscription(subVO, openId);
        } catch (EhospitalServiceException eh) {
            logger.info("addSubcription error,message={}", eh.getMessage());
            RestResponse.errorRes(eh.getMessage());
        } catch (Exception e) {
            logger.info("addSubcription error,message={}", e.getMessage());
            RestResponse.errorRes("系统异常,请稍后再试");
        }
        return RestResponse.successResWithTokenData("预约成功", "YGdykliy_+@124LK/");

    }

    @RequestMapping(value = "/querySub", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse querySubscription(HttpServletRequest request) {
        ScheduleExample examples = new ScheduleExample();
        Criteria criteria =  examples.createCriteria();
        criteria.andClinicDateBetween(DateUtil.getNotToday(new Date(), 1), DateUtil.getNotToday(new Date(), 7));
        examples.setOrderByClause("clinic_date,clinic_time");
        List<Schedule> listSch = scheduleService.getScheduleInfo(examples);
        List<ScheduleVO> listVO = new ArrayList<ScheduleVO>();
        for(Schedule sch : listSch){
            ScheduleVO vo = new ScheduleVO();
            vo.setScheduleId(sch.getScheduleId());
            vo.setClinicNo(sch.getClinicNo());
            vo.setClincTime(sch.getClinicTime());
            vo.setWeekStr(DateUtil.getWeekByDate(sch.getClinicWeek()));
            vo.setDateStr(DateUtil.parseDateToString(sch.getClinicDate()));
            listVO.add(vo);
        }
        return RestResponse.successResWithTokenData(listVO, "YGdykliy_+@124LK/");

    }
    
    @RequestMapping(value = "/queryUser", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse queryUser(HttpServletRequest request) {
        //String openId = request.getParameter("openId");
        String openId = "test_01_02";
        UserExample example = new UserExample();
        example.createCriteria().andOpenIdEqualTo(openId);
        List<User> list = userService.findUsersByCondition(example);
        if(list.size() >0){
        return RestResponse.successResWithTokenData(list.get(0), "YGdykliy_+@124LK/");
        }else{
        return RestResponse.errorRes("系统数据异常，用户信息不存在");
        }
    }

}
