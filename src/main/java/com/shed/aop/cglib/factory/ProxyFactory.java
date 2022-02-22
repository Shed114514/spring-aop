package com.shed.aop.cglib.factory;

import com.shed.aop.cglib.bean.Message;
import com.shed.aop.cglib.proxy.MessageProxy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.ApplicationContext;

public class ProxyFactory {

    // 负责代理控制的类
    private static final Enhancer ENHANCER = new Enhancer();

    private ProxyFactory() {}

    public static Message getInstance(ApplicationContext app) {
        Message target = (Message) app.getBean("message");
        ENHANCER.setSuperclass(target.getClass());
        ENHANCER.setCallback(new MessageProxy(target));
        return (Message) ENHANCER.create();
    }

}
