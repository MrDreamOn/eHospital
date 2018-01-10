package com.hospital.register.service;

import java.util.List;

import com.hospital.register.bean.Schedule;
import com.hospital.register.bean.ScheduleExample;

public interface ScheduleService {

	List<Schedule> getScheduleInfo(ScheduleExample examples);
	
	void addSchedules(List<Schedule> schedules) throws Exception;
	
	/**
	 * @param paramMap
	 * @return
	 */
	List<Schedule> getScheduleInfoByPage(ScheduleExample example,int currentPage,int pageSize) throws Exception;
	
	void updateScheduleInfo(Schedule record,ScheduleExample example) throws Exception;
	
	long countSchedule(ScheduleExample example);
}
