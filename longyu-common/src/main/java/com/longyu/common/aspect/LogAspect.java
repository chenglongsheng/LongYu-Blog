package com.longyu.common.aspect;

import com.alibaba.fastjson.JSON;
import com.longyu.common.annotation.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Slf4j
public class LogAspect {

    /**
     * 切点
     */
    @Pointcut("@annotation(com.longyu.common.annotation.SystemLog)")
    public void pt() {

    }

    /**
     * 方法增强环绕打印日志
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pt()")
    public Object printLog(ProceedingJoinPoint pjp) throws Throwable {
        Object proceed;
        try {
            handleBefore(pjp);
            proceed = pjp.proceed();
            handleAfter(proceed);
        } finally {
            // 结束后换行
            log.info("========End========" + System.lineSeparator());
        }
        return proceed;
    }

    private void handleBefore(ProceedingJoinPoint pjp) {

        // 获取servlet请求
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //获取被增强方法上的注解对象
        SystemLog systemLog = getSystemLog(pjp);

        MethodSignature signature = (MethodSignature) pjp.getSignature();

        log.info("=======Start=======");
        // 打印请求 URL
        log.info("URL            : {}", request.getRequestURL());
        // 打印描述信息
        log.info("BusinessName   : {}", systemLog.businessName());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", signature.getDeclaringTypeName(), signature.getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteHost());
        // 打印请求入参
        log.info("Request Args   : {}", JSON.toJSONString(pjp.getArgs()));
    }

    private SystemLog getSystemLog(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        return signature.getMethod().getAnnotation(SystemLog.class);
    }

    private void handleAfter(Object proceed) {
        // 打印出参
        log.info("Response       : {}", proceed);
    }

}
