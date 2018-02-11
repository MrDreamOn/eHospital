package com.hospital.register.controller.doctor;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.register.annotation.OperateLogs;
import com.hospital.register.bean.Doctor;
import com.hospital.register.bean.DoctorExample;
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
	@OperateLogs(operateInfo = "更新医生信息")
	public RestResponse doctorUpdate(HttpServletRequest request) {
		logger.info("update hospital info ...");

		String docPosition = request.getParameter("docPosition");

		String docSummery = request.getParameter("docSummery");

		String docHonor = request.getParameter("docHonor");
		
		String doctorId = request.getParameter("doctorId");
		
		int docId = StringUtils.isEmpty(doctorId)? 1 :Integer.parseInt(doctorId);

		Doctor doc = new Doctor();
		doc.setDoctorId(docId);
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
		//根据doctorId获取医生信息
		String doctorId = request.getParameter("doctorId");
		Doctor doctor = doctorMapper.selectByPrimaryKey(StringUtils.isEmpty(doctorId)? 1 :Integer.parseInt(doctorId));

		return RestResponse.successResWithData(doctor);

	}

	@RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse queryAllDoctor(HttpServletRequest request) {
    	DoctorExample example = new DoctorExample();
    	example.createCriteria();
    	List<Doctor> resultList = doctorMapper.selectByExample(example);
    	return RestResponse.successResWithData(resultList);
    }
}
