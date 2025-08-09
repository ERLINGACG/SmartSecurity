package com.erling.aop.aspect;

import com.erling.aop.base.GroupAspectBase;
import com.erling.utils.log.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ControllerAspect extends GroupAspectBase {



    public void isAccessFunfParam(ProceedingJoinPoint joinPoint) throws Throwable {
       try{
           Object[] args = joinPoint.getArgs();
           if(args.length > 0){
               Logger.getLogger(getClass()).info("方法参数: {}", Arrays.toString(args));
           }else{
               Logger.getLogger(getClass()).info("方法参数为VOID");
           }
       }catch (Throwable e) {
           Logger.getLogger(getClass()).error("方法执行异常", e);

       }
    }

    public void  isAccessCallFunf(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger.getLogger(getClass()).info("方法调用: {}", joinPoint.getSignature().getName());

    }

    public Object  isAccessReturn(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        Logger.getLogger(getClass()).info("方法返回: {}", result);
        return result;
    }


    public Object isAccessAll(ProceedingJoinPoint joinPoint) throws Throwable {
        return super.isAccessAll(joinPoint);
    }



}
