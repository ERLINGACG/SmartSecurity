package com.erling.aop.group;

import com.erling.aop.aspect.ControllerAspect;
import com.erling.utils.log.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GroupControllerAspect extends ControllerAspect {
    @Around("execution(* com.erling.controller.group.GroupController.*(..))")
    public Object isAccessAll(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Logger.getLogger(getClass()).info("----------------------------------------------");
        isAccessFunfParam(joinPoint);
        isAccessCallFunf(joinPoint);

        return isAccessReturn(joinPoint,startTime);
    }
}
