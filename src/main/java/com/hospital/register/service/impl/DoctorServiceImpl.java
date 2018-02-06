package com.hospital.register.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hospital.register.bean.Doctor;
import com.hospital.register.bean.DoctorExample;
import com.hospital.register.dao.DoctorMapper;
import com.hospital.register.service.DoctorService;

@Component
public class DoctorServiceImpl implements DoctorService{
	private static final Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

	@Autowired
	private DoctorMapper doctorMapper;
	
	@Override
	public List<Doctor> queryAllDoctor() {
		logger.info("query all doctors");
		DoctorExample example = new DoctorExample();
    	example.createCriteria();
    	return doctorMapper.selectByExample(example);
	}

	@Override
	public Doctor getDoctorByPrimaryKey(int doctorId) {
		return doctorMapper.selectByPrimaryKey(doctorId);
	}

}
