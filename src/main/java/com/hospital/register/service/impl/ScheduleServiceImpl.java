package com.hospital.register.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.hospital.register.bean.Schedule;
import com.hospital.register.bean.ScheduleExample;
import com.hospital.register.dao.ScheduleMapper;
import com.hospital.register.service.ScheduleService;

@Component
@Transactional(rollbackFor = RuntimeException.class)
public class ScheduleServiceImpl implements ScheduleService {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);
	@Autowired
	private ScheduleMapper scheduleMapper;

	@Override
	public List<Schedule> getScheduleInfo(ScheduleExample example) {
		logger.info("getScheduleInfo");
		return scheduleMapper.selectByExample(example);
	}

	@Override
	public void addSchedules(List<Schedule> schedules) throws Exception {
		logger.info("addSchedules");
		for (Schedule schedule : schedules) {
			scheduleMapper.insert(schedule);
		}
	}

	@Override
	public void updateScheduleInfo(Schedule record,ScheduleExample example) throws Exception {
		scheduleMapper.updateByExampleSelective(record, example);
	}

	@Override
	public List<Schedule> getScheduleInfoByPage(ScheduleExample example, int currentPage, int pageSize)
			throws Exception {
		if (pageSize == 0) {
			pageSize = 100;
		}
		PageHelper.startPage(currentPage, pageSize);
		return scheduleMapper.selectByExample(example);

	}

	@Override
	public long countSchedule(ScheduleExample example) {
		return scheduleMapper.countByExample(example);
	}

}
