package com.hospital.register.controller.system;

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

import com.hospital.register.bean.SubscriptionExample;
import com.hospital.register.bean.UserExample;
import com.hospital.register.conditionVO.SystemVO;
import com.hospital.register.dao.SubscriptionMapper;
import com.hospital.register.dao.UserMapper;
import com.hospital.register.util.DateUtil;
import com.hospital.register.util.RestResponse;

/**
 * 
 * 
 * @author caiwei
 * @version $Id: SystemController.java, v 0.1 2018年1月16日 下午11:40:34 caiwei Exp $
 */
@Controller
@RequestMapping(value = "/system")
public class SystemController {

    private static final Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private UserMapper          userMapper;

    @Autowired
    private SubscriptionMapper  subscriptionMapper;

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse systemQuery(HttpServletRequest request) {
        List<SystemVO> list = new ArrayList<SystemVO>();
        Date start = new Date();
        //1.今日新增
        list.add(this.queryCountByDate(start, DateUtil.getAfterDay(start)));
        //2.昨日新增
        list.add(this.queryCountByDate(DateUtil.getBeforeDay(start), start));
        //3.本周新增
        list.add(this.queryCountByDate(DateUtil.getWeekstart(start),  DateUtil.getAfterDay(start)));
        //4.上周新增
        list.add(this.queryCountByDate(DateUtil.getBeforWeekendStart(), DateUtil.getAfterDay(DateUtil.getBeforWeekendend())));
        //5.本月新增
        list.add(this.queryCountByDate(DateUtil.getMonthBegin(start), DateUtil.getAfterDay(start)));
        //6.上月新增
        list.add(this.queryCountByDate(DateUtil.getBeforeMonthStart(), DateUtil.getAfterDay(DateUtil.getBeforeMonthEnd())));
        //7.系统总数
        list.add(this.queryCountByDate(null, DateUtil.getAfterDay(start)));
        return RestResponse.successResWithData(list);
    }

    private SystemVO queryCountByDate(Date start, Date end) {
        if (start != null) {
            start = DateUtil.formatDateToDate(start);
        }
        if (end != null) {
            end = DateUtil.formatDateToDate(end);
        }
        SystemVO vo = new SystemVO();
        //1.注册用户数
        UserExample u = new UserExample();
        if (start == null) {
            u.createCriteria().andCreateTimeLessThanOrEqualTo(end);
        } else {
            u.createCriteria().andCreateTimeBetween(start, end);
        }
        vo.setUserCount((int) userMapper.countByExample(u));
        //2.就诊人数
        SubscriptionExample s1 = new SubscriptionExample();
        if (start == null) {
            s1.createCriteria().andSubscriptionStatusEqualTo(1).andUpdateTimeLessThanOrEqualTo(end);
        } else {
            s1.createCriteria().andSubscriptionStatusEqualTo(1).andUpdateTimeBetween(start, end);
        }
        vo.setPatientCount((int) subscriptionMapper.countByExample(s1));
        //3.预约数
        SubscriptionExample s2 = new SubscriptionExample();
        if (start == null) {
            s2.createCriteria().andSubscriptionStatusEqualTo(0).andCreateTimeLessThanOrEqualTo(end);
        } else {
            s2.createCriteria().andSubscriptionStatusEqualTo(0).andCreateTimeBetween(start, end);
        }
        vo.setSubCount((int) subscriptionMapper.countByExample(s2));
        //4.取消数
        SubscriptionExample s3 = new SubscriptionExample();
        if (start == null) {
            s3.createCriteria().andSubscriptionStatusEqualTo(4).andCreateTimeLessThanOrEqualTo(end);
        } else {
            s3.createCriteria().andSubscriptionStatusEqualTo(4).andCreateTimeBetween(start, end);
        }
        vo.setCancelSubCount((int) subscriptionMapper.countByExample(s3));
        //5.实际预约数
        vo.setRealCount(vo.getSubCount() - vo.getCancelSubCount());
        return vo;
    }
}
