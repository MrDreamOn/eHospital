package com.hospital.register.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.register.bean.Schedule;
import com.hospital.register.bean.ScheduleExample;
import com.hospital.register.service.ScheduleService;
import com.hospital.register.util.DateUtil;
import com.hospital.register.util.RestResponse;

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
		examples.createCriteria().andClinicDateBetween(DateUtil.formatDateToDate(new Date()), DateUtil.getCurBeforDate(-7))
		.andClinicStatusEqualTo(1);
		List<Schedule> schedules = scheduleService.getScheduleInfo(examples);
		return RestResponse.successResWithData(schedules);
	}
}
