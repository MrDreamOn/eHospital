package com.hospital.register.task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
	
	
	@Scheduled(cron = "${schedule.init.schedulded:0 30 23 * * ?}")
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
			logger.info("耗时:[{}ms]", endTime - startTime);

		}
		
	}

	private List<Schedule> initSchedules() throws Exception {
		ScheduleExample examples = new ScheduleExample();
		examples.setOrderByClause("clinic_date desc");
		List<Schedule> resultSchedules = scheduleService.getScheduleInfo(examples);
		Date clinicDate = new Date(System.currentTimeMillis());
		if(CollectionUtils.isNotEmpty(resultSchedules)) {
			clinicDate = resultSchedules.get(0).getClinicDate();
		}
		List<Schedule> schedules = new ArrayList<Schedule>();
		Date clinicDateNew =DateUtil.getAfterDay(clinicDate);
		int dayOfWeek = DateUtil.dayForWeek(clinicDateNew);
		Schedule schedule = new Schedule();
		schedule.setHospitalId(1);
		schedule.setDoctorId(1);
		schedule.setDepartmentId(1);
		schedule.setClinicDate(clinicDateNew);
		schedule.setClinicTime("上午");
		schedule.setClinicWeek(dayOfWeek);
		schedule.setClinicType(1);
		schedule.setClinicStatus(1);
		schedule.setClinicNo(10);
		schedule.setClinicFee(new BigDecimal(10));
		schedule.setCreateTime(new Date());
		schedule.setUpdateTime(new Date());
		
		Schedule schedule2 = new Schedule();
		schedule2.setHospitalId(1);
		schedule2.setDoctorId(1);
		schedule2.setDepartmentId(1);
		schedule2.setClinicDate(clinicDateNew);
		schedule2.setClinicTime("下午");
		schedule2.setClinicWeek(dayOfWeek);
		schedule2.setClinicType(1);
		schedule2.setClinicStatus(1);
		schedule2.setClinicNo(10);
		schedule2.setClinicFee(new BigDecimal(10));
		schedule2.setCreateTime(new Date());
		schedule2.setUpdateTime(new Date());
		
		schedules.add(schedule);
		schedules.add(schedule2);
		return schedules;
	}
	
	public static void main(String[] args) {
	    Random random = new Random();
	    
	    System.out.println( random.nextBoolean());
    }


}
