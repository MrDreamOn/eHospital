package com.hospital.register.service;

import java.util.List;

import com.hospital.register.bean.Doctor;

public interface DoctorService {
	List<Doctor> queryAllDoctor();
	
	Doctor getDoctorByPrimaryKey(int doctorId);
}
