package com.hospital.register.controller.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.register.bean.User;
import com.hospital.register.bean.UserExample;
import com.hospital.register.service.UserService;
import com.hospital.register.util.RestResponse;

/**
 * 管理后台登录
 * 
 * @author caiwei
 * @version $Id: LoginController.java, v 0.1 2018年1月14日 下午11:44:33 caiwei Exp $
 */
@Controller
@RequestMapping(value = "/login")
public class LoginAdminController {

    private static final Logger logger = LoggerFactory.getLogger(LoginAdminController.class);

    @Autowired
    private UserService         userService;

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse userLogin(HttpServletRequest request) {
        logger.info("用户登录成功...");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        request.getSession().setAttribute("user", "test");
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo("admin").andRealNameEqualTo(userName);
        List<User> userList = userService.findUsersByCondition(example);
        if(userList.size() == 0){
            return RestResponse.errorRes("用户名不存在");
        }
        
        if(!userList.get(0).getPassword().equals(password)){
            return RestResponse.errorRes("密码错误");
        }
        
        return RestResponse.successResWithData(userList.get(0));
    }

}
