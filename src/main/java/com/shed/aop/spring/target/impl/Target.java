package com.shed.aop.spring.target.impl;

import com.shed.aop.spring.target.TargetInterface;
import org.springframework.stereotype.Component;

@Component("target")
public class Target implements TargetInterface {
    @Override
    public void targetMethodA() {
        System.out.println("[TargetA]执行目标方法(无参数无返回值)");
    }

    @Override
    public String targetMethodB(String str) {
        return "[TargetB]执行目标方法(有参数有返回值)" + " 参数: " + str;
    }
}
