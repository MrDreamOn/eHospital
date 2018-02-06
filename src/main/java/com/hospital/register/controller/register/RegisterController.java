package com.hospital.register.controller.register;

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

import com.hospital.register.bean.User;
import com.hospital.register.bean.UserExample;
import com.hospital.register.service.PatientService;
import com.hospital.register.service.UserService;
import com.hospital.register.util.RestResponse;
import com.hospital.register.util.idCard.IdcardInfoExtractor;
import com.hospital.register.util.idCard.IdcardValidator;

/**
 * 
 * 微信端注册
 * @author caiwei
 * @version $Id: RegisterController.java, v 0.1 2017年12月10日 下午10:00:31 caiwei Exp $
 */
@Controller
@RequestMapping(value = "/addRegister")
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private PatientService  patientService;
    
    @Autowired
    private UserService         userService;
    /**
     * 判断是否注册
     */
    public void  isRegister(){
        patientService.isRegister("");
    }
    
    /**
     * 会员注册
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse addRegister(HttpServletRequest request){
        String telPhone = request.getParameter("telPhone");
        String name = request.getParameter("name");
        String idCard = request.getParameter("idCard");
        String userId = request.getParameter("userId");
        logger.info("queryUser,telPhone={}", telPhone);
        UserExample example = new UserExample();
        example.createCriteria().andTelephoneEqualTo(telPhone);
        List<User> userList = userService.findUsersByCondition(example);
        if (userList.size() == 0) {
            IdcardValidator idcardValidator = new IdcardValidator();           
                if (!idcardValidator.isValidatedAllIdcard(idCard)) {
                    return RestResponse.errorRes("身份证格式错误！");
                }

           IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(idCard);
            User user = new User();
            user.setBirthday(idcardInfoExtractor.getBirthday());
            user.setUserName("游客");
            user.setRealName(name);
            user.setIdCard(idCard);
            user.setTelephone(telPhone);
            user.setSex(Integer.parseInt(idcardInfoExtractor.getGender()));
            user.setUserId(Integer.parseInt(userId));
            user.setPassword("!qazXsw2");
           
            user.setUpdateTime(new Date());
            int result = userService.updateByPrimaryKeySelective(user);
            if (result == 0) {
                RestResponse.errorRes("会员创建失败");
            }
            return RestResponse.success();
        }else{
            return RestResponse.errorRes("改手机号已被注册，请跟换其他手机号");
        }
    }
}
