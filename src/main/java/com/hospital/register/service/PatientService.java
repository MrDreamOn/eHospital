package com.hospital.register.service;

import com.hospital.register.bean.Patient;

/**
 * 
 * @author caiwei
 * @version $Id: PatientService.java, v 0.1 2017年12月9日 下午9:20:35 caiwei Exp $
 */
public interface PatientService {
    
    /**
     * 
     * 判断是否注册
     * @param openid
     * @return
     */
    public boolean isRegister(String openid);
    
    
    /**
     * 
     * 判断是否关注
     * @param openid
     * @return
     */
    public boolean isSubscribe(String openid);
    
    /**
     * 新增关注人数
     * 
     * @param openid
     * @return
     */
    public int addFollowers(String openid);
    
    
    /**
     * 新增注册信息
     * 
     * @param patient
     * @return
     */
    public int updateRegister(Patient patient);
    
}
