package com.shed.aop.jdk.factory;

import com.shed.aop.jdk.proxy.ServiceProxy;
import com.shed.aop.jdk.service.UserService;
import org.springframework.context.ApplicationContext;

public class ProxyFactory {

    private ProxyFactory() {}

    public static UserService getServiceInstance(ApplicationContext app) {
        try {
            UserService userService = (UserService) app.getBean("userService");
            return (UserService) new ServiceProxy().bind(userService);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }
}
