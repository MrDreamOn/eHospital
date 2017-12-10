package com.hospital.register.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
@Component
public class PatientServiceImpl  implements PatientService {
    
    private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);
    
    @Autowired
    private PatientMapper  patientMapper;

    @Override
    public boolean isRegister(String openid) {
        PatientExample example  = new PatientExample();
        Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openid);      
        List<Patient> list = patientMapper.selectByExample(example);
        logger.info("get patient list size,count={}", list.size());
        if(list.size() == 0){
            //未关注
            return false;
        }else{
            Patient patientVO = list.get(0);
            if(StringUtils.hasText(patientVO.getRealName())){
                //关注已注册
                return true;
            }else{
                //关注未注册
                return false;
            }
        }
        
    }

    @Override
    public boolean isSubscribe(String openid) {
        return false;
    }

    @Override
    public int addFollowers(String openid) {
        Patient patient  = new Patient();
        patient.setOpenId(openid);
        patient.setCreateTime(new Date());
        patient.setUpdateTime(new Date());
        return patientMapper.insertSelective(patient);
    }

    @Override
    public int updateRegister(Patient patient) {
        PatientExample example  = new PatientExample();
        Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(patient.getOpenId()); 
        return patientMapper.updateByExampleSelective(patient, example);
    }

}
