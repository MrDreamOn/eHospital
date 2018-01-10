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
import com.hospital.register.bean.SubscriptionExample;
import com.hospital.register.bean.User;
import com.hospital.register.bean.UserExample;
import com.hospital.register.conditionVO.ScheduleVO;
import com.hospital.register.conditionVO.SubscriptionVO;
import com.hospital.register.conditionVO.UserVO;
import com.hospital.register.exception.EhospitalServiceException;
import com.hospital.register.service.ScheduleService;
import com.hospital.register.service.SubscriptionService;
import com.hospital.register.service.UserService;
import com.hospital.register.util.DateUtil;
import com.hospital.register.util.RestResponse;
import com.hospital.register.util.idCard.IdcardInfoExtractor;
import com.hospital.register.util.idCard.IdcardValidator;

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
    public RestResponse addSubcription(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String scheduleId = request.getParameter("scheduleId");
        String idCard = request.getParameter("idCard");
        String telPhone = request.getParameter("telPhone");
        String name = request.getParameter("name");
        UserExample ue = new UserExample();
        ue.createCriteria().andUserIdEqualTo(Integer.parseInt(userId));
        User user = userService.findUsersByCondition(ue).get(0);
        ScheduleExample se = new ScheduleExample();
        se.createCriteria().andScheduleIdEqualTo(Integer.parseInt(scheduleId));
        Schedule schedule = scheduleService.getScheduleInfo(se).get(0);
        logger.info("addSubcription,userId={}", userId);
        
        Subscription subVO = new Subscription();
        subVO.setScheduleId(schedule.getScheduleId());
        subVO.setUserId(Integer.parseInt(userId));
        subVO.setPatientName(name);
        IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(idCard);
        subVO.setPatientBirthday(idcardInfoExtractor.getBirthday());
        subVO.setPatientSex(Integer.parseInt(idcardInfoExtractor.getGender()));
        subVO.setPatientPhone(telPhone);
        subVO.setSubscriptionDate(schedule.getClinicDate());
        subVO.setSubscriptionTime(schedule.getClinicTime());
        subVO.setSubscriptionStatus(0);
        subVO.setSubscriptionFee(schedule.getClinicFee().longValue());
        subVO.setCreateTime(new Date());
        subVO.setUpdateTime(new Date());
        try {
            subscriptionService.addSubscription(subVO, user.getOpenId());
        } catch (EhospitalServiceException eh) {
            logger.info("addSubcription error,message={}", eh.getMessage());
            return RestResponse.errorRes(eh.getMessage());
        } catch (Exception e) {
            logger.info("addSubcription error,message={}", e.getMessage());
            return RestResponse.errorRes("系统异常,请稍后再试");
        }
        return RestResponse.successResWithTokenData(subVO.getSubscriptionId(), "YGdykliy_+@124LK/");

    }

    @RequestMapping(value = "/querySub", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse querySubscription(HttpServletRequest request) {
        ScheduleExample examples = new ScheduleExample();
        Criteria criteria = examples.createCriteria();
        criteria.andClinicDateBetween(DateUtil.getNotToday(new Date(), 1),
            DateUtil.getNotToday(new Date(), 7));
        examples.setOrderByClause("clinic_date,clinic_time");
        List<Schedule> listSch = scheduleService.getScheduleInfo(examples);
        List<ScheduleVO> listVO = new ArrayList<ScheduleVO>();
        for (Schedule sch : listSch) {
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
        if (list.size() > 0) {
            return RestResponse.successResWithTokenData(list.get(0), "YGdykliy_+@124LK/");
        } else {
            return RestResponse.errorRes("系统数据异常，用户信息不存在");
        }
    }

    @RequestMapping(value = "/checkSubInfo", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse checkSubInfo(HttpServletRequest request) {
        //String openId = request.getParameter("openId");
        String openId = "test_01_02";
        String name = request.getParameter("name");
        String idCard = request.getParameter("idCard");
        String telPhone = request.getParameter("telPhone");
        String scheduleId = request.getParameter("scheduleId");
        UserExample example = new UserExample();
        example.createCriteria().andOpenIdEqualTo(openId);
        List<User> list = userService.findUsersByCondition(example);
        User user2 = list.get(0);
//        if (!StringUtils.hasLength(user.getRealName())) {
            IdcardValidator idcardValidator = new IdcardValidator();
            if (!idcardValidator.isValidatedAllIdcard(idCard)) {
                return RestResponse.errorRes("身份证格式错误！");
            }
            UserVO user = new UserVO();
            IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(idCard);
           
            user.setRealName(name);
            user.setIdCard(idCard);
            user.setTelphone(telPhone);
            user.setSexStr(idcardInfoExtractor.getGender().equals("1")?"男":"女");
            user.setAge(idcardInfoExtractor.getAge()+"");
//        }
        SubscriptionExample exampleSub = new SubscriptionExample();
        com.hospital.register.bean.SubscriptionExample.Criteria criteria = exampleSub
            .createCriteria();
        criteria.andScheduleIdEqualTo(Integer.parseInt(scheduleId));
        criteria.andUserIdEqualTo(user2.getUserId());
        criteria.andSubscriptionStatusNotEqualTo(4);
        List<Subscription> listsub = subscriptionService.querySubscription(exampleSub);
        if (listsub.isEmpty()) {
            return RestResponse.successResWithData(user);
        }
        return RestResponse.errorRes("您已预约过该排班,请选择其他时间");
    }

    /**
     * 用于展示确认预约页面
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryInfo", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse queryInfo(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String scheduleId = request.getParameter("scheduleId");
        UserExample ue = new UserExample();
        ue.createCriteria().andUserIdEqualTo(Integer.parseInt(userId));
        User user = userService.findUsersByCondition(ue).get(0);
        ScheduleExample se = new ScheduleExample();
        se.createCriteria().andScheduleIdEqualTo(Integer.parseInt(scheduleId));
        Schedule schedule = scheduleService.getScheduleInfo(se).get(0);
        SubscriptionVO vo = new SubscriptionVO();
        vo.setScheduleId(scheduleId);
        vo.setUserId(userId);
        IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(user.getIdCard());
        vo.setAge(idcardInfoExtractor.getAge()+"");
        vo.setName(user.getRealName());
        vo.setSex(idcardInfoExtractor.getGender().equals("1")?"男":"女");
        vo.setTelphone(user.getTelephone());
        StringBuilder sb = new StringBuilder();
        sb.append(DateUtil.formatDate(schedule.getClinicDate()));
        sb.append(" ");
        sb.append(DateUtil.getWeekByDate(schedule.getClinicWeek()));
        sb.append(" ");
        sb.append(schedule.getClinicTime());
        vo.setDateString(sb.toString());
        return RestResponse.successResWithData(vo);

    }
    
    /**
     * 取消预约
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/cancleSubscription", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse cancleSubscription(HttpServletRequest request) {
        String subScriptionId =  request.getParameter("subId");
        try{
        subscriptionService.cancleSubscription(subScriptionId);
        }catch(EhospitalServiceException ex){
           return RestResponse.errorRes(ex.getMessage());
        }catch(Exception e){
           return RestResponse.errorRes(e.getMessage());
        }
        return RestResponse.success();
    }
    
    
    /**
     * 查询预约记录
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/querySubList", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse querySubscriptionList(HttpServletRequest request) {
        //String userId =  request.getParameter("userId");
        int userId = 31;
        List<SubscriptionVO> subVOList = new ArrayList<SubscriptionVO>();
        try{
        SubscriptionExample example = new SubscriptionExample();
        example.createCriteria().andUserIdEqualTo(userId);
        example.setOrderByClause("subscription_date DESC ");
        List<Subscription> subList = subscriptionService.querySubscriptionByPage(example, 1, 50);
        for(Subscription sub : subList){
            SubscriptionVO vo = new SubscriptionVO();
            vo.setName(sub.getPatientName());
            vo.setTelphone(sub.getPatientPhone());
            vo.setStatus(sub.getSubscriptionStatus().toString());
            StringBuilder sb = new StringBuilder();
            sb.append(DateUtil.formatDate(sub.getSubscriptionDate()));
            sb.append(" ");
            sb.append(DateUtil.getWeekByDate(DateUtil.dayForWeek(sub.getSubscriptionDate())));
            sb.append(" ");
            sb.append(sub.getSubscriptionTime());
            vo.setDateString(sb.toString());
            subVOList.add(vo);
        }
        }catch(EhospitalServiceException ex){
           return RestResponse.errorRes(ex.getMessage());
        }catch(Exception e){
           return RestResponse.errorRes(e.getMessage());
        }
        return RestResponse.successResWithData(subVOList);
    }

}
