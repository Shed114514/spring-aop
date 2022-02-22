package com.shed.aop.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component("myAspect")
@Aspect
public class MyAspect {

    // 声明一个公共的切点表达式
    @Pointcut("execution(* com.shed.aop.spring..*.*(..))")
    public void myPointcut() {}

    // 前置增强方法
    @Before("myPointcut()")
    public void before() {
        System.out.println("[Before]执行前置增强方法...");
    }

    // 后置增强方法
    @AfterReturning("myPointcut()")
    public void afterReturning() {
        System.out.println("[After-Returning]执行后置增强方法...");
    }

    // 环绕增强方法
    @Around("myPointcut()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("[Around]环绕增强前...");
        System.out.println("打印返回值: " + pjp.proceed());
        System.out.println("[Around]环绕增强后...");
    }

    // 异常抛出增强
    @AfterThrowing("myPointcut()")
    public void exceptionThrowing() {
        System.out.println("[Throwing]异常抛出增强...");
    }

    // 最终增强
    @After("myPointcut()")
    public void after() {
        System.out.println("[After]执行最终增强...");
    }
}
