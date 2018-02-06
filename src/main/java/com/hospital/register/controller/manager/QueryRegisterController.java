package com.hospital.register.controller.manager;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.register.annotation.OperateLogs;
import com.hospital.register.annotation.TokenAccess;
import com.hospital.register.bean.Bonus;
import com.hospital.register.bean.BonusDetail;
import com.hospital.register.bean.Schedule;
import com.hospital.register.bean.ScheduleExample;
import com.hospital.register.bean.Subscription;
import com.hospital.register.bean.SubscriptionExample;
import com.hospital.register.bean.SubscriptionExample.Criteria;
import com.hospital.register.bean.User;
import com.hospital.register.bean.UserExample;
import com.hospital.register.conditionVO.RegisterVO;
import com.hospital.register.conditionVO.UserVO;
import com.hospital.register.exception.EhospitalServiceException;
import com.hospital.register.service.BonusService;
import com.hospital.register.service.ScheduleService;
import com.hospital.register.service.SubscriptionService;
import com.hospital.register.service.UserService;
import com.hospital.register.util.DateUtil;
import com.hospital.register.util.RestResponse;
import com.hospital.register.util.idCard.IdcardInfoExtractor;
import com.hospital.register.util.idCard.IdcardValidator;

@Controller
@RequestMapping(value = "/queryRegister")
public class QueryRegisterController {

    private static final Logger logger = LoggerFactory.getLogger(QueryRegisterController.class);

    @Autowired
    private UserService         userService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private ScheduleService     scheduleService;
    
    @Autowired
    private BonusService        bonusService;

    /**
     * 根据手机号判断是否是会员
     * 
     * @param telPhone
     * @return
     */
    
    @RequestMapping(value = "/queryTelphone", method = RequestMethod.POST)
    @ResponseBody
    @TokenAccess
    @OperateLogs(operateInfo="查询预约信息")
    public RestResponse queryTelphone(HttpServletRequest request) {
        try{
        String telPhone = request.getParameter("telPhone");
        logger.info("queryUser,telPhone={}", telPhone);
        RegisterVO vo = new RegisterVO();
        UserExample example = new UserExample();
        example.createCriteria().andTelephoneEqualTo(telPhone);
        List<User> userList = userService.findUsersByCondition(example);
        if (userList.size() == 0) {
            vo.setIsRegister("1");
            return RestResponse.successResWithTokenData(vo, "YGdykliy_+@124LK/");
        }
        User user = userList.get(0);
        SubscriptionExample subExample = new SubscriptionExample();
        Criteria criteria = subExample.createCriteria();
        criteria.andSubscriptionDateEqualTo(DateUtil.formatDateToDate(new Date()));
        criteria.andUserIdEqualTo(user.getUserId());
        criteria.andSubscriptionStatusEqualTo(0);
        List<Subscription> sublist = subscriptionService.querySubscription(subExample);
        IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(user.getIdCard());
        if (sublist.size() == 0) {
            SubscriptionExample subExample2 = new SubscriptionExample();
            Criteria criteria2 = subExample2.createCriteria();
            criteria2.andSubscriptionStatusEqualTo(1);
            criteria2.andSubscriptionDateEqualTo(DateUtil.formatDateToDate(new Date()));
            criteria2.andUserIdEqualTo(user.getUserId());
            List<Subscription> sublist2 = subscriptionService.querySubscription(subExample2);
            if (sublist2.size() == 0) {
                vo.setIsRegister("2");
                vo.setUser(user);
                vo.setAge(idcardInfoExtractor.getAge());
                return RestResponse.successResWithTokenData(vo, "YGdykliy_+@124LK/");
            }else{
                vo.setIsRegister("4");
                vo.setSubscription(sublist2.get(0));
                ScheduleExample schExample = new ScheduleExample();
                schExample.createCriteria().andScheduleIdEqualTo(sublist2.get(0).getScheduleId());
                Schedule sch = scheduleService.getScheduleInfo(schExample).get(0);
                vo.setClinicTime(DateUtil.formatDate(sch.getClinicDate()) + " "
                                 + DateUtil.getWeekByDate(sch.getClinicWeek()) + " 上午 10:00-12:00");
                vo.setCreateTime(DateUtil.formatDateTime(sublist2.get(0).getUpdateTime()));
                vo.setSubscription(sublist2.get(0));
                return RestResponse.successResWithTokenData(vo, "YGdykliy_+@124LK/");
                
            }
        }
        vo.setIsRegister("3");
        vo.setSubscription(sublist.get(0));
        ScheduleExample schExample = new ScheduleExample();
        schExample.createCriteria().andScheduleIdEqualTo(sublist.get(0).getScheduleId());
        Schedule sch = scheduleService.getScheduleInfo(schExample).get(0);
        vo.setClinicTime(DateUtil.formatDate(sch.getClinicDate()) + " "
                         + DateUtil.getWeekByDate(sch.getClinicWeek()) + " 上午 10:00-12:00");
        vo.setCreateTime(DateUtil.formatDateTime(sublist.get(0).getCreateTime()));
        return RestResponse.successResWithTokenData(vo, "YGdykliy_+@124LK/");
        }catch(Exception e){
        	System.out.println(e.getMessage());
            return RestResponse.errorRes(e.getMessage());
        }
    }


	/**
    * 根据输入信息新增挂号
    * @param userVO
    * @return
    */
    
    @RequestMapping(value = "/addRegister", method = RequestMethod.POST)
    @ResponseBody
    @OperateLogs(operateInfo="新增挂号")
    public RestResponse addRegister(String telPhone, String idCard, String name, String status,
                                    int userId, int subId) {
        try {
            UserVO userVO = new UserVO();
            IdcardValidator idcardValidator = new IdcardValidator();
            if (!"3".equals(status)) {
                if (!idcardValidator.isValidatedAllIdcard(idCard)) {
                    return RestResponse.errorRes("身份证格式错误！");

                }
                IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(idCard);
                userVO.setTelphone(telPhone);
                userVO.setIdCard(idCard);
                userVO.setRealName(name);
                userVO.setSex(Integer.parseInt(idcardInfoExtractor.getGender()));
                userVO.setBrithday(idcardInfoExtractor.getBirthday());
                userVO.setUserId(userId);
            }
            if ("1".equals(status)) {
                subscriptionService.addUserAndSubscription(userVO);
            }

            if ("2".equals(status)) {
                subscriptionService.userAddSubscription(userVO);
            }

            if ("3".equals(status)) {
                userVO.setSubscriptionId(subId);
                subscriptionService.updateSubscription(userVO);
            }

            return RestResponse.success();
        } catch (EhospitalServiceException eh) {
            logger.error(eh.getMessage());
            return RestResponse.errorRes(eh.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return RestResponse.errorRes(e.getMessage());
        }
    }
    /**
     * 查询预约记录
     * 
     * @param telPhone
     * @param startDate
     * @param endDate
     * @param clincSartDate
     * @param clincEndDate
     * @param currentPage
     * @param pageSize
     * @param status
     * @return
     */
    @RequestMapping(value = "/querySubscription", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse querySubscriptionList(String telPhone,String startDate,String endDate,String clincStartDate,String clincEndDate,int currentPage,int pageSize,int status){
        SubscriptionExample subExample = new SubscriptionExample();
        Criteria criteria = subExample.createCriteria();
        if(StringUtils.hasText(telPhone)){
            criteria.andPatientPhoneEqualTo(telPhone);
        }
        if(StringUtils.hasText(startDate)){
            String[] dateArr = startDate.split(",");
            String start = DateUtil.parseGMTDate(dateArr[0]);
            String end = DateUtil.parseGMTDate(dateArr[1]);
            criteria.andCreateTimeBetween(DateUtil.parseDateString(start,"yyyy-MM-dd"), DateUtil.parseDateString(end,"yyyy-MM-dd"));
        }
        if(StringUtils.hasText(clincStartDate)){
            String[] dateArr = clincStartDate.split(",");
            String start = DateUtil.parseGMTDate(dateArr[0]);
            String end = DateUtil.parseGMTDate(dateArr[1]);
            criteria.andSubscriptionDateBetween(DateUtil.parseDateString(start,"yyyy-MM-dd"), DateUtil.parseDateString(end,"yyyy-MM-dd"));
        }
        
        if(-1 == status){
            criteria.andSubscriptionStatusEqualTo(0);
        }else if(0 == status){
            criteria.andSubscriptionStatusEqualTo(0);
            criteria.andSubscriptionDateBetween(DateUtil.formatDateToDate(new Date()),DateUtil.formatDateToDate(new Date()));
        }else{
            criteria.andSubscriptionStatusEqualTo(status);
        }
        
       
        List<Subscription> sublist2 = subscriptionService.querySubscriptionByPage(subExample,currentPage,pageSize);
        
        return RestResponse.successResWithTokenData(sublist2, "YGdykliy_+@124LK/");
    }
    
    @RequestMapping(value = "/updateSubStatus", method = RequestMethod.POST)
    @ResponseBody
    @OperateLogs(operateInfo="更新预约状态")
    public RestResponse updateSubStatus(HttpServletRequest request){
        String subid = request.getParameter("subid");
        String status = request.getParameter("status");
        String score = request.getParameter("score");
        Subscription sub = new Subscription();
        sub.setSubscriptionId(Integer.parseInt(subid));
        sub.setSubscriptionStatus(Integer.parseInt(status));
        sub.setUpdateTime(new Date());
        SubscriptionExample ex = new SubscriptionExample();
        ex.createCriteria().andSubscriptionIdEqualTo(sub.getSubscriptionId());
        Subscription subVO = subscriptionService.querySubscription(ex).get(0);
        if("6".equals(status)){
            if(StringUtils.hasText(score)){
                Bonus bonusVO = new Bonus();
                bonusVO.setUserId(subVO.getUserId());
               List<Bonus> bList =  bonusService.queryBonusByCondition(bonusVO);
               if(bList.size() == 0){
                   Bonus bonus = new Bonus();
                   bonus.setBonusPoints(Integer.parseInt(score));
                   bonus.setCreateTime(new Date());
                   bonus.setUpdateTime(new Date());
                   bonus.setUserId(subVO.getUserId());
                   bonusService.addBonus(bonus);
                   BonusDetail detail = new BonusDetail();
                   detail.setBonusId(bonus.getBonusId());
                   detail.setBonusPoints(Integer.parseInt(score));
                   detail.setCompleteTime(new Date());
                   detail.setSettleAmount(subVO.getSubscriptionFee());
                   detail.setSettleMode(1);
                   detail.setSubscriptionId(subVO.getSubscriptionId());
                   bonusService.addBonusDetail(detail);
               }else{
                   Bonus  b = bList.get(0);
                   BonusDetail detail = new BonusDetail();
                   detail.setBonusId(b.getBonusId());
                   detail.setBonusPoints(Integer.parseInt(score));
                   detail.setCompleteTime(new Date());
                   detail.setSettleAmount(subVO.getSubscriptionFee());
                   detail.setSettleMode(1);
                   detail.setSubscriptionId(subVO.getSubscriptionId());
                   bonusService.addBonusDetail(detail);
                   b.setBonusPoints(b.getBonusPoints()+detail.getBonusPoints());
                   b.setUpdateTime(new Date());
                   bonusService.updateBonus(b);
               }
          
            }else{
                return RestResponse.errorRes("请输入积分");
            }
      
        }
        subscriptionService.updateSubStatus(sub);
        return RestResponse.successResWithTokenData(1, "YGdykliy_+@124LK/");
    }
    
    @RequestMapping(value = "/querySubscriptionDetail", method = RequestMethod.POST)
    @ResponseBody
    @OperateLogs(operateInfo="查询预约详情")
    public RestResponse querySubscriptionDetail(int subid){
        SubscriptionExample subExample = new SubscriptionExample();
        Criteria criteria = subExample.createCriteria();
        criteria.andSubscriptionIdEqualTo(subid);
        List<Subscription> sublist = subscriptionService.querySubscription(subExample);
        if(sublist.size() >0 ){
            RegisterVO vo = new RegisterVO();
            vo.setSubscription(sublist.get(0));
            vo.setClinicTime(DateUtil.formatDate(sublist.get(0).getSubscriptionDate()) + " "
                    + sublist.get(0).getSubscriptionTime());
            vo.setCreateTime(DateUtil.formatDateTime(sublist.get(0).getUpdateTime()));
            return RestResponse.successResWithTokenData(vo, "YGdykliy_+@124LK/");  
        }
        return RestResponse.successResWithTokenData(new RegisterVO(), "YGdykliy_+@124LK/");
    }
}
