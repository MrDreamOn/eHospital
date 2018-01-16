package com.hospital.register.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.register.controller.manager.QueryRegisterController;
import com.hospital.register.exception.EhospitalServiceException;
import com.hospital.register.util.RestResponse;

@ControllerAdvice
public class MyControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(MyControllerAdvice.class);

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public RestResponse errorHandler(Exception ex) {
        logger.error(ex.getMessage());
        return RestResponse.errorRes("系统异常");
    }
    
    
    /**
     * 拦截捕捉自定义异常 MyException.class
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = EhospitalServiceException.class)
    public RestResponse myErrorHandler(EhospitalServiceException ex) {
        return RestResponse.errorRes(ex.getMessage());
    }
    
}
