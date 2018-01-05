package com.hospital.register.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TokenAccessAspect {

    @Before("@annotation(com.hospital.register.annotation.TokenAccess)")
    public void checkPermission(JoinPoint joinPoint) throws Exception{
        Object[] args = joinPoint.getArgs();

        HttpServletRequest request=(HttpServletRequest)args[0];
        
        String token=request.getParameter("token");
        
        if(!"123".equals(token)){
            // 以下代码从JSON.java中拷过来
            throw new Exception("token 验证失败");
        }
    }
    
}
