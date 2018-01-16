package com.hospital.register.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.hospital.register.exception.EhospitalServiceException;
import com.hospital.register.util.ResponseCode;

@Component
@Aspect
public class TokenAccessAspect {

    @Before("@annotation(com.hospital.register.annotation.TokenAccess)")
    public void checkPermission(JoinPoint joinPoint) throws Exception{
        Object[] args = joinPoint.getArgs();

        HttpServletRequest request=(HttpServletRequest)args[0];
        
        String token=request.getParameter("token");
        
//        if(!"123".equals(token)){
//            throw new EhospitalServiceException(ResponseCode.RESPONSE_COMMON_ERROR_MESSAGE,"token 验证失败");
//        }
    }
    
}
