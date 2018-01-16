package com.hospital.register.controller.doctor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.register.bean.Doctor;
import com.hospital.register.dao.DoctorMapper;
import com.hospital.register.util.RestResponse;

/**
 * 
 * 
 * @author caiwei
 * @version $Id: DoctorController.java, v 0.1 2018年1月16日 下午11:01:55 caiwei Exp $
 */
@Controller
@RequestMapping(value = "/doctor")
public class DoctorController {

    private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);
    
    @Autowired
    private DoctorMapper doctorMapper;   
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse doctorUpdate(HttpServletRequest request) {
        logger.info("update hospital info ...");
        
        String docPosition = request.getParameter("docPosition");
        
        String docSummery = request.getParameter("docSummery");
        
        String docHonor = request.getParameter("docHonor");
       
       Doctor doc = new Doctor();
       doc.setDoctorId(1);
       doc.setPositionName(docPosition);
       doc.setDoctorSummary(docSummery);
       doc.setDoctorHonor(docHonor);
       doc.setUpdateTime(new Date());
       doctorMapper.updateByPrimaryKeySelective(doc);
        
        return RestResponse.success();
        
    }
    
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse doctorQuery(HttpServletRequest request) {
        logger.info("query hospital info ...");
        
        Doctor doctor =  doctorMapper.selectByPrimaryKey(1);
        
        return RestResponse.successResWithData(doctor);
        
    }
}
