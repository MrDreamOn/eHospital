package com.hospital.register.service;

import java.util.List;

import com.hospital.register.bean.Schedule;
import com.hospital.register.bean.ScheduleExample;

public interface ScheduleService {

	List<Schedule> getScheduleInfo(ScheduleExample examples);
	
	void addSchedules(List<Schedule> schedules) throws Exception;
}
