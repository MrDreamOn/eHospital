package com.hospital.register.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.hospital.register.bean.Schedule;
import com.hospital.register.bean.ScheduleExample;
import com.hospital.register.bean.Subscription;
import com.hospital.register.bean.SubscriptionExample;
import com.hospital.register.bean.SubscriptionExample.Criteria;
import com.hospital.register.bean.User;
import com.hospital.register.bean.UserExample;
import com.hospital.register.conditionVO.RegisterVO;
import com.hospital.register.conditionVO.UserVO;
import com.hospital.register.dao.ScheduleMapper;
import com.hospital.register.dao.SubscriptionMapper;
import com.hospital.register.exception.EhospitalServiceException;
import com.hospital.register.service.ScheduleService;
import com.hospital.register.service.SubscriptionService;
import com.hospital.register.service.UserService;
import com.hospital.register.util.DateUtil;
import com.hospital.register.util.ResponseCode;

/**
 * 预约逻辑处理
 * 
 * @author caiwei
 * @version $Id: SubscriptionServiceImpl.java, v 0.1 2017年12月10日 下午10:22:27 caiwei Exp $
 */

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private ScheduleMapper     scheduleMapper;

    @Autowired
    private SubscriptionMapper subscriptionMapper;

    @Autowired
    private UserService        userService;

    @Autowired
    private ScheduleService    scheduleService;

    private void checkSubscription(Subscription subscription, String openid) {
        checkSameSubscription(subscription.getScheduleId(), subscription.getPatientPhone());
        checkSubscriptionNo(subscription.getScheduleId());
        /**
         * 1.校验是否重复预约
         * 2.校验是否有号源
         * 3.校验预约时间
         * 
         */
    }

    private void checkSameSubscription(int scheduleId, String patientPhone) {
        SubscriptionExample example = new SubscriptionExample();
        Criteria criteria = example.createCriteria();
        criteria.andScheduleIdEqualTo(scheduleId);
        criteria.andPatientPhoneEqualTo(patientPhone);
        criteria.andSubscriptionStatusEqualTo(0);
        List<Subscription> list = subscriptionMapper.selectByExample(example);
        if (list.size() > 0) {
            throw new EhospitalServiceException(ResponseCode.RESPONSE_COMMON_ERROR_MESSAGE,
                "该手机号已预约相同排班,请重新选择");
        }

    }

    private void checkSubscriptionNo(int scheduleId) {
        int clincNo = scheduleMapper.checkScheduleNo(scheduleId);
        if (clincNo <= 0) {
            throw new EhospitalServiceException(ResponseCode.RESPONSE_COMMON_ERROR_MESSAGE,
                "该排班已约满，请选择其他排班");
        }

    }

    @Override
    public String addSubscription(Subscription subscription, String openid) {
        checkSubscription(subscription, openid);
        int result = subtractingScheduleNo(subscription.getScheduleId());
        if (result != 0) {
            int subResult = insertSubscription(subscription);
            if (subResult == 0) {
                throw new EhospitalServiceException(ResponseCode.RESPONSE_COMMON_ERROR_MESSAGE,
                    "新增预约失败");
            }
        }

        return ResponseCode.RESPONSE_COMMON_SUCCESS_MESSAGE;
    }

    private int subtractingScheduleNo(Integer scheduleId) {
        synchronized (scheduleId) {
            return scheduleMapper.updateScheduleNo(scheduleId);
        }
    }

    private int insertSubscription(Subscription subscription) {
        return subscriptionMapper.insertSelective(subscription);
    }

    @Override
    public List<Subscription> querySubscriptionList(RegisterVO registerVO) {

        SubscriptionExample example = new SubscriptionExample();
        example.setOrderByClause("subscription_date desc");
        Criteria criteria = example.createCriteria();
        if (StringUtils.hasText(registerVO.getTelphone())) {
            criteria.andPatientPhoneEqualTo(registerVO.getTelphone());
        }

        if (StringUtils.hasText(registerVO.getStartDate())
            && StringUtils.hasText(registerVO.getEndDate())) {
            criteria.andSubscriptionDateBetween(DateUtil.parseDate(registerVO.getStartDate()),
                DateUtil.parseDate(registerVO.getEndDate()));
        }

        if (StringUtils.hasText(registerVO.getStatus())) {
            criteria.andSubscriptionStatusEqualTo(Integer.parseInt(registerVO.getStatus()));
        }

        return subscriptionMapper.selectByExample(example);
    }

    @Override
    public List<Subscription> querySubscription(SubscriptionExample example) {
        return subscriptionMapper.selectByExample(example);
    }

    @Override
    public void addUserAndSubscription(UserVO userVO) {
        User user = new User();
        user.setBirthday(userVO.getBrithday());
        user.setUserName("游客");
        user.setRealName(userVO.getRealName());
        user.setIdCard(userVO.getIdCard());
        user.setTelephone(userVO.getTelphone());
        user.setSex(userVO.getSex());
        user.setPassword("!qazXsw2");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        int result = userService.addUser(user);
        if (result == 0) {
            throw new EhospitalServiceException(ResponseCode.RESPONSE_COMMON_ERROR_MESSAGE,
                "会员创建失败");
        }
        ScheduleExample examples = new ScheduleExample();
        examples.createCriteria().andClinicDateEqualTo(DateUtil.formatDateToDate(new Date()));
        List<Schedule> schList = scheduleService.getScheduleInfo(examples);
        if (schList.size() == 0) {
            throw new EhospitalServiceException(ResponseCode.RESPONSE_COMMON_ERROR_MESSAGE,
                "医生当天没有排班");

        }
        Schedule sch = schList.get(0);
        //判断当前号源
        checkSubscriptionNo(sch.getScheduleId());

        Subscription sub = new Subscription();
        sub.setUserId(user.getUserId());
        sub.setScheduleId(sch.getScheduleId());
        sub.setSubscriptionStatus(1);
        sub.setSubscriptionFee(sch.getClinicFee().longValue());
        sub.setPatientBirthday(user.getBirthday());
        sub.setPatientName(user.getRealName());
        sub.setPatientPhone(user.getTelephone());
        sub.setPatientSex(user.getSex());
        sub.setCreateTime(new Date());
        sub.setUpdateTime(new Date());
        sub.setSubscriptionDate(sch.getClinicDate());
        sub.setSubscriptionTime(sch.getClinicTime());
        int subResult = subscriptionMapper.insertSelective(sub);
        if (subResult == 0) {
            throw new EhospitalServiceException(ResponseCode.RESPONSE_COMMON_ERROR_MESSAGE,
                "挂号失败，请稍后再试");
        }

    }

    @Override
    public void userAddSubscription(UserVO userVO) {
        User user = new User();
        UserExample uExamples = new UserExample();
        uExamples.createCriteria().andUserIdEqualTo(userVO.getUserId());
        List<User> userList = userService.findUsersByCondition(uExamples);
        if (userList.size() == 0) {
            throw new EhospitalServiceException(ResponseCode.RESPONSE_COMMON_ERROR_MESSAGE,
                "用户信息异常");

        }
        user = userList.get(0);
        ScheduleExample examples = new ScheduleExample();
        examples.createCriteria().andClinicDateEqualTo(DateUtil.formatDateToDate(new Date()));
        List<Schedule> schList = scheduleService.getScheduleInfo(examples);
        if (schList.size() == 0) {
            throw new EhospitalServiceException(ResponseCode.RESPONSE_COMMON_ERROR_MESSAGE,
                "医生当天没有排班");

        }
        Schedule sch = schList.get(0);
        //判断当前号源
        checkSubscriptionNo(sch.getScheduleId());

        Subscription sub = new Subscription();
        sub.setUserId(userVO.getUserId());
        sub.setScheduleId(sch.getScheduleId());
        sub.setSubscriptionStatus(1);
        sub.setSubscriptionFee(sch.getClinicFee().longValue());
        sub.setPatientBirthday(user.getBirthday());
        sub.setPatientName(user.getRealName());
        sub.setPatientPhone(user.getTelephone());
        sub.setPatientSex(user.getSex());
        sub.setCreateTime(new Date());
        sub.setUpdateTime(new Date());
        sub.setSubscriptionDate(sch.getClinicDate());
        sub.setSubscriptionTime(sch.getClinicTime());
        int subResult = subscriptionMapper.insertSelective(sub);
        if (subResult == 0) {
            throw new EhospitalServiceException(ResponseCode.RESPONSE_COMMON_ERROR_MESSAGE,
                "挂号失败，请稍后再试");
        }
    }

    @Override
    public void updateSubscription(UserVO userVO) {
        SubscriptionExample example = new SubscriptionExample();
        example.createCriteria().andSubscriptionIdEqualTo(userVO.getSubscriptionId());
        Subscription sub = new Subscription();
        sub.setSubscriptionStatus(2);
        int subResult = subscriptionMapper.updateByExampleSelective(sub, example);
        if (subResult == 0) {
            throw new EhospitalServiceException(ResponseCode.RESPONSE_COMMON_ERROR_MESSAGE,
                "挂号失败，请稍后再试");
        }
    }

    @Override
    public List<Subscription> querySubscriptionByPage(SubscriptionExample example, int currentPage,
                                                      int pageSize) {
        if (pageSize == 0) {
            pageSize = 100;
        }
        PageHelper.startPage(currentPage, pageSize);
        return subscriptionMapper.selectByExample(example);
    }

    @Override
    public int updateSubStatus(Subscription sub) {
        return subscriptionMapper.updateByPrimaryKeySelective(sub);
    }

    @Override
    public int cancleSubscription(String subcriptionId) {
        
        Subscription sub = subscriptionMapper.selectByPrimaryKey(Integer.parseInt(subcriptionId));
        Subscription example = new Subscription();
        example.setSubscriptionId(Integer.parseInt(subcriptionId));
        example.setSubscriptionStatus(4);
        example.setUpdateTime(new Date());
        int result = subscriptionMapper.updateByPrimaryKeySelective(example);
        Schedule sch = scheduleMapper.selectByPrimaryKey(sub.getScheduleId());
        Schedule schEx = new Schedule();
        schEx.setScheduleId(sub.getScheduleId());
        schEx.setClinicNo(sch.getClinicNo()+1);
        int result2 = scheduleMapper.updateByPrimaryKeySelective(schEx);
        if(result == 0 || result2 == 0){
            throw new EhospitalServiceException(ResponseCode.RESPONSE_COMMON_ERROR_MESSAGE, "预约取消失败");
        }
        return 1;
    }

	@Override
	public long countSubsciption(SubscriptionExample example) {
		return subscriptionMapper.countByExample(example);
	}
}
