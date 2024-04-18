package com.yao.yuapiinterface.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 权限校验 AOP
 *
 */
@Aspect
@Component
@Slf4j
public class InvokeInterceptor {


    /**
     * 执行拦截
     *
     * @param joinPoint
     * @return
     */
    @Around("execution(* com.yao.yuapiinterface.controller.*.*(..))")
    public Object doInterceptor(ProceedingJoinPoint joinPoint) throws Throwable {

        //获得请求

        log.info(joinPoint.getSignature().getName()+"被调用");

        return joinPoint.proceed();
    }
}

