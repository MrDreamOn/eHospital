package com.hospital.register.controller.register;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hospital.register.bean.Patient;
import com.hospital.register.service.PatientService;

/**
 * 
 * 微信端注册
 * @author caiwei
 * @version $Id: RegisterController.java, v 0.1 2017年12月10日 下午10:00:31 caiwei Exp $
 */
@Controller
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private PatientService  patientService;
    
    /**
     * 判断是否注册
     */
    public void  isRegister(){
        patientService.isRegister("");
    }
    
    
    public void addRegister(){
        
        Patient patientVO = new Patient();
        patientVO.setBirthday(new Date());
        patientVO.setIdCard("");
        patientVO.setOpenId("");
        patientVO.setPhone("");
        patientVO.setRealName("");
        patientVO.setSex(1);
        patientVO.setScore(0);
        patientVO.setUpdateTime(new Date());
        
        int result = patientService.updateRegister(patientVO);
    }
}
