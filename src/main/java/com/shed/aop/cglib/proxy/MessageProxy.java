package com.shed.aop.cglib.proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MessageProxy implements MethodInterceptor { // 底层使用拦截器实现

    // 目标实体类
    private Object target;

    public MessageProxy(Object target) {
        this.target = target;
    }

    // 前置增强
    public boolean before() {
        System.out.println("[Before]进行网络资源的连接...");
        return true;
    }

    // 后置增强
    public void afterReturning() {
        System.out.println("[After-Returning]断开网络资源的连接...");
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
                                                                        throws Throwable {
        Object returnValue = null;
        if (this.before()) {
            returnValue = method.invoke(this.target,objects);
            this.afterReturning();
        }
        return returnValue;
    }
}
