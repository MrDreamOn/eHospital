package com.hospital.register.controller.hospital;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.register.bean.Hospital;
import com.hospital.register.dao.HospitalMapper;
import com.hospital.register.util.RestResponse;

/**
 * 
 * 
 * @author caiwei
 * @version $Id: HospitalController.java, v 0.1 2018年1月16日 下午8:52:26 caiwei Exp $
 */
@Controller
@RequestMapping(value = "/hospital")
public class HospitalController {
    
    private static final Logger logger = LoggerFactory.getLogger(HospitalController.class);
    
    @Autowired
    private HospitalMapper hospitalMapper;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse hospitalUpdate(HttpServletRequest request) {
        logger.info("update hospital info ...");
        
        String hospitalLevel = request.getParameter("hospitalLevel");
        
        String hospitalSite = request.getParameter("hospitalSite");
        
        String hospitalTel = request.getParameter("hospitalTel");
        
        String hospitalTra = request.getParameter("hospitalTra");
        
        String hospitalSum = request.getParameter("hospitalSum");
        
        String hospitalAddress = request.getParameter("hospitalAddress");
        
        Hospital hospital = new Hospital();
        hospital.setHospitalId(1);
        hospital.setAddress(hospitalAddress);
        hospital.setCreateTime(new Date());
        hospital.setUpdateTime(new Date());
        hospital.setLevel(hospitalLevel);
        hospital.setSummary(hospitalSum);
        hospital.setTelephone(hospitalTel);
        hospital.setTraffic(hospitalTra);
        hospital.setWebsite(hospitalSite);
        
        hospitalMapper.updateByPrimaryKeySelective(hospital);
        
        return RestResponse.success();
        
    }
    
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse hospitalQuery(HttpServletRequest request) {
        logger.info("query hospital info ...");
        
        Hospital hospital =  hospitalMapper.selectByPrimaryKey(1);
        
        return RestResponse.successResWithData(hospital);
        
    }
    
}
