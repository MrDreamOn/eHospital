package com.hospital.register.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.register.bean.Patient;
import com.hospital.register.bean.PatientExample;
import com.hospital.register.bean.PatientExample.Criteria;
import com.hospital.register.dao.PatientMapper;
import com.hospital.register.service.PatientService;

/**
 * 
 * 
 * @author caiwei
 * @version $Id: PatientServiceImpl.java, v 0.1 2017年12月9日 下午9:29:46 caiwei Exp $
 */
@Service
public class PatientServiceImpl  implements PatientService {
    
    @Autowired
    private PatientMapper  patientMapper;

    @Override
    public boolean isRegister(String openid) {
        PatientExample example  = new PatientExample();
        Criteria criteria = example.createCriteria();
       
        patientMapper.selectByExample(example);
        return false;
    }

    @Override
    public boolean isSubscribe(String openid) {
        return false;
    }

    @Override
    public int addFollowers(String openid) {
        return 0;
    }

    @Override
    public int updateRegister(Patient patient) {
        return 0;
    }

}
