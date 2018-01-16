package com.hospital.register.aop;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hospital.register.annotation.OperateLogs;
import com.hospital.register.bean.Log;
import com.hospital.register.dao.LogMapper;

/**
 * 操作日志切点类
 *
 * @author liuzhenjian
 */

@Aspect
@Component
public class OperLogAspect {
    @Autowired
    private LogMapper logMapper;
    
    private final static Logger LOGGER = LoggerFactory.getLogger(OperLogAspect.class);

    @Around(value = "within(com.hospital.register..*) && @annotation(operatelogs)")
    public Object doOperLogAspect(final ProceedingJoinPoint joinPoint,
                                  final OperateLogs operatelogs) throws Throwable {
    	// 调用方法名称
        final String methodName = joinPoint.getSignature().getName();
        // 获取进入的类名
        String className = joinPoint.getSignature().getDeclaringTypeName();
        className = className.substring(className.lastIndexOf('.') + 1).trim();
        // 调用注解信息
        String operateInfo = operatelogs.operateInfo();
        // 调用参数
        final Object[] args = joinPoint.getArgs();
        Object obj = joinPoint.proceed(args);
        Log record = new Log();
        record.setUserId(1);
        record.setOperateTime(new Date(System.currentTimeMillis()));
        record.setOperateRecord(operateInfo);
        record.setCreateTime(new Date(System.currentTimeMillis()));
        logMapper.insert(record);
        LOGGER.info("operation logs inserted by {}.{}()", className, methodName);
        return obj;
    }
    
    /*@Around(value = "within(com.hospital.register..*) && @annotation(loginLog)")
	public Object doLoginLog(final ProceedingJoinPoint joinPoint, final LoginLog loginLog) throws Throwable {
		// 调用参数
		final Object[] args = joinPoint.getArgs();
		final Object obj = joinPoint.proceed(args);
		oroper.writeLoginLog(obj, args);
		return obj;
	}*/

}
