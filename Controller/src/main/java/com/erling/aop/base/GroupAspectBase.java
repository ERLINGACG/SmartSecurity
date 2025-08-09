package com.erling.aop.base;

import com.erling.aop.inf.AspectInterface;
import org.aspectj.lang.ProceedingJoinPoint;

public abstract class GroupAspectBase implements AspectInterface {

    @Override
    public void isAccessCallFunf(){}
    @Override
    public void isAccessFunfParam(){}
    @Override
    public void isAccessReturn(){}

    public Object isAccessAll(ProceedingJoinPoint joinPoint) throws Throwable  {
       return null;
    }
}
