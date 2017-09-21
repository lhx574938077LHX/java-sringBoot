package com.lhx.interceptor.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author lihongxiang
 * @date
 */
@Aspect // FOR AOP
@Order(-99) // 控制多个Aspect的执行顺序，越小越先执行
@Component
public class TestAspect {

//    @Pointcut("execution(* com.lhx.controller.LoginController.*(..))")
//    private void anyMethod(){}


    @Around("execution(* com.lhx.controller.LoginController.*(..))")
    public void AroundTest(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("=====AroundTest Start====");
        Object object = pjp.proceed();//执行该方法
        System.out.println("=====AroundTest End====");
    }


}
