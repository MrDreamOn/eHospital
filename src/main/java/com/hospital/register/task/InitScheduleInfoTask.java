package com.hospital.register.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hospital.register.bean.Schedule;
import com.hospital.register.bean.ScheduleExample;
import com.hospital.register.service.ScheduleService;
import com.hospital.register.util.DateUtil;

@Component
@EnableScheduling
public class InitScheduleInfoTask {
	private static final Logger logger = LoggerFactory.getLogger(InitScheduleInfoTask.class);
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Scheduled(cron = "${schedule.init.schedulded:0 0 0 * * ?}")
	public void initHandler() throws Exception {
		logger.info("initHandler start");
		Long startTime = System.currentTimeMillis();
		List<Schedule> schedules = initSchedules();
		try {
			scheduleService.addSchedules(schedules);
		} catch (Exception e) {
			logger.error("initHandler exception,{}", e);
		} finally {
			Long endTime = System.currentTimeMillis();
			logger.info("time-consuming:[{}ms]", endTime - startTime);

		}
		
	}

	private List<Schedule> initSchedules() throws Exception {
		ScheduleExample examples = new ScheduleExample();
		examples.setOrderByClause("clinic_date desc");
		List<Schedule> resultSchedules = scheduleService.getScheduleInfo(examples);
		Schedule schedule = new Schedule();
		if(CollectionUtils.isNotEmpty(resultSchedules)) {
			schedule = resultSchedules.get(0);
		}
		List<Schedule> schedules = new ArrayList<Schedule>();
		Date clinicDate = schedule.getClinicDate();
		Date clinicDateNew =DateUtil.getAfterDay(clinicDate);
		int dayOfWeek = DateUtil.dayForWeek(DateUtil.formatDate(clinicDateNew));
		schedule.setScheduleId(0);
		schedule.setClinicDate(clinicDateNew);
		schedule.setClinicWeek(dayOfWeek);
		schedule.setCreateTime(new Date());
		schedule.setUpdateTime(new Date());
		schedules.add(schedule);
		return schedules;
	}


}
