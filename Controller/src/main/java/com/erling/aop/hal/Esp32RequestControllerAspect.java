package com.erling.aop.hal;

import com.erling.aop.aspect.ControllerAspect;
import com.erling.utils.log.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Esp32RequestControllerAspect extends ControllerAspect {
    @Around("execution(* com.erling.controller.hal.Esp32RequestController.*(..))")
    public Object isAccessAll(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Logger.getLogger(getClass()).info("----------------------------------------------");
        isAccessFunfParam(joinPoint);
        isAccessCallFunf(joinPoint);

        return isAccessReturn(joinPoint,startTime);
    }
}
