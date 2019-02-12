package com.easyboot.config.log.aspect;


import com.easyboot.bean.User;
import com.easyboot.config.log.annotation.Log;
import com.easyboot.config.log.annotation.LogSubject;
import com.easyboot.config.log.utils.IPUtils;
import com.easyboot.service.ILogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private ILogService logService;
    @Autowired
    private HttpServletRequest request;

    @Around("within(com.*..*) && @annotation(archivesLog)")
    public Object around(ProceedingJoinPoint pjd, Log archivesLog) throws Throwable {

        //执行方法，获取返回参数
        Object result  = pjd.proceed();

        com.easyboot.bean.Log log = new com.easyboot.bean.Log();

        log.setLogId(null);
        log.setCtime(LocalDateTime.now());
        long startTime=System.currentTimeMillis();

        //类名
        Class<?> classTarget = pjd.getTarget().getClass();
        String className = classTarget.getName();
        log.setClasz(className);

        String message = archivesLog.value();
        LogSubject logSubject = classTarget.getAnnotation(LogSubject.class);
        if (logSubject != null) {
            message = message + logSubject.value();
        }
        log.setMessage(message);

        //方法名
        String methodName = pjd.getSignature().getName();
        log.setMethod(methodName);

        //获取请求参数
        Object[] params = pjd.getArgs();
        String paramsStr = "";
        for(Object param:params) {
            paramsStr += (param + ",");
        }
        log.setParams(paramsStr);

        //请求地址
        String uri = request.getRequestURI();
        log.setUri(uri);

        //IP
        String host = IPUtils.getIpAddr(request);
        log.setHost(host);

        //操作人
        User thisUser = (User) SecurityUtils.getSubject().getPrincipal();
        if (thisUser != null){
            int userId = thisUser.getUserId();
            log.setUserId(userId);
        }

        //结束时间
        long endTime=System.currentTimeMillis();
        float excTime=(float)(endTime-startTime) / 1000;
        log.setEndtime(LocalDateTime.now());
        log.setExctime(excTime);

        //录入数据库
        logService.save(log);

        return result;
    }


}