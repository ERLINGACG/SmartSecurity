package com.erling.aop.opencv;

import com.erling.aop.aspect.ControllerAspect;
import com.erling.utils.log.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CVDnnFaceDetectorAspect extends ControllerAspect {
    @Around("execution(* com.erling.controller.opencv.face.CVDnnFaceController.*(..))")
    public Object isAccessAll(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Logger.getLogger(getClass()).info("----------------------------------------------");
        isAccessFunfParam(joinPoint);
        isAccessCallFunf(joinPoint);

        return isAccessReturn(joinPoint,startTime);
    }
}
