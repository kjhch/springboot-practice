package com.hch.aop;

import com.hch.pojo.ErrorEnum;
import com.hch.pojo.response.CommonResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(100)
public class RespLocalizationAspect {
    @Pointcut("execution(public com.hch.pojo.response.CommonResponse+ com.hch.controller.*.*(..))")
    public void commonRespController() {
    }

    // ------------advise-------------

    @Around("commonRespController()")
    public CommonResponse localizeResp(ProceedingJoinPoint joinPoint) throws Throwable {
        CommonResponse response = (CommonResponse) joinPoint.proceed();
        if (response.getCode() == null) {
            response.setCode(ErrorEnum.UNKNOWN_ERROR.getCode());
            response.setMessage(ErrorEnum.UNKNOWN_ERROR.getLocalMessage());
        }
        return response;
    }
}
