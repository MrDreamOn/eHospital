package com.hospital.register.controller.schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.h2.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.register.annotation.OperateLogs;
import com.hospital.register.bean.Schedule;
import com.hospital.register.bean.ScheduleExample;
import com.hospital.register.service.ScheduleService;
import com.hospital.register.util.DateUtil;
import com.hospital.register.util.RestResponse;
import com.hospital.register.vo.ScheduleView;

@Controller
@RequestMapping(value = "/schedule")
public class ScheduleController {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

	@Autowired
	private ScheduleService scheduleService;

	@RequestMapping(value = "/schedules", method = RequestMethod.POST)
	@ResponseBody
	RestResponse getSchedule() {
		logger.info("getSchedule info");
		ScheduleExample examples = new ScheduleExample();
		examples.createCriteria()
				.andClinicDateBetween(DateUtil.formatDateToDate(new Date()), DateUtil.getCurBeforDate(-6))
				.andClinicStatusEqualTo(1);
		List<Schedule> schedules = scheduleService.getScheduleInfo(examples);
		return RestResponse.successResWithData(schedules);
	}

	@RequestMapping(value = "/querySchedule", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogs(operateInfo="查询排班信息")
	public RestResponse querySchedule(Integer currentPage, Integer pageSize) {
		logger.info("getSchedule info,currentPage:{},pageSize:{}", currentPage, pageSize);
		List<ScheduleView> resultList = new ArrayList<ScheduleView>();
		ScheduleExample examples = new ScheduleExample();
		try {
			examples.createCriteria().andClinicDateBetween(DateUtil.formatDateToDate(new Date()),
					DateUtil.getCurBeforDate(-5));
			long count = scheduleService.countSchedule(examples);
			if (count > 0) {
				List<Schedule> schedules = scheduleService.getScheduleInfoByPage(examples, currentPage, pageSize);
				resultList = wapperScheduleView(schedules);
				count = resultList.size();
			}
			return RestResponse.successRes4Find(resultList, Integer.parseInt(count + ""));
		} catch (Exception e) {
			logger.error("查询排班信息出错,e={}", e);
			return RestResponse.errorRes("查询排班信息出错");
		}
	}

	private List<ScheduleView> wapperScheduleView(List<Schedule> schedules) {
		List<ScheduleView> resultList = new ArrayList<ScheduleView>();
		Map<Long,ScheduleView> scheduleMap = new HashMap<Long,ScheduleView>();
		for (Schedule item : schedules) {
			long longKey = item.getClinicDate().getTime();
			ScheduleView vo = null;
			if(scheduleMap.containsKey(longKey)) {
				vo = scheduleMap.get(longKey);
			}else {
				vo = new ScheduleView();
			}
			
			vo.setClinicDate(item.getClinicDate());
			vo.setDoctorName("朱自清");
			vo.setClinicTimeMorning("09:00 ~ 11:30");
			vo.setClinicTimeAfternoon("13:00 ~ 17:00");
			if(StringUtils.equals("上午", item.getClinicTime())) {
				vo.setMorningLeft(item.getClinicNo()+"");				
			}else if(StringUtils.equals("下午", item.getClinicTime())) {
				vo.setAfternoonLeft(item.getClinicNo()+"");
			}
			vo.setClinicStatus(item.getClinicStatus() == 0 ? false : true);
			scheduleMap.put(longKey, vo);
		}
		resultList.addAll(scheduleMap.values());
		Collections.sort(resultList,new Comparator<ScheduleView>() {
			@Override
			public int compare(ScheduleView o1, ScheduleView o2) {
				return o1.getClinicDate().compareTo(o2.getClinicDate());
			}
		});
		return resultList;
	}

	@RequestMapping(value = "/updateSchedule", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogs(operateInfo="更新排班信息")
	public RestResponse updateSchedule(long clinicDate, boolean clinicStatus) {
		logger.info("updateSchedule info,clinicDate:{},status:{}", clinicDate, clinicStatus);
		int intClinicStatus = 0;
		int tmpClinicStatus = 1;
		String resultInfo = "已停诊";
		if (clinicStatus) {
			intClinicStatus = 1;
			tmpClinicStatus = 0;
			resultInfo = "已出诊";
		}
		ScheduleExample examples = new ScheduleExample();
		try {
			examples.createCriteria().andClinicDateEqualTo(new Date(clinicDate))
					.andClinicStatusEqualTo(tmpClinicStatus);
			Schedule schedule = new Schedule();
			schedule.setClinicStatus(intClinicStatus);
			scheduleService.updateScheduleInfo(schedule, examples);
		} catch (Exception e) {
			logger.error("更新排班信息出错,e={}", e);
			return RestResponse.errorRes("更新排班信息出错");
		}
		return RestResponse.successResWithData(resultInfo);
	}
}
